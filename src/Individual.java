import java.util.ArrayList;

public class Individual implements Comparable<Individual>{
    private ArrayList<Integer> genotype;
    private ArrayList<Integer> dataFlow;
    private ArrayList<Double> correctionRate;
    private ArrayList<Double> correctionCost;
    private double fitness;

    public Individual(ArrayList<Integer> dataFlow, ArrayList<Double> correctionRate, ArrayList<Double> correctionCost) {
        genotype = new ArrayList<>();
        this.dataFlow = dataFlow;
        this.correctionRate = correctionRate;
        this.correctionCost = correctionCost;
        generateGenes();
        calculateFitness();
    }

    public Individual(ArrayList<Integer> genotype, ArrayList<Integer> dataFlow, ArrayList<Double> correctionRate, ArrayList<Double> correctionCost) {
        this.genotype = genotype;
        this.dataFlow = dataFlow;
        this.correctionRate = correctionRate;
        this.correctionCost = correctionCost;
        calculateFitness();
    }

    private void generateGenes() {
        genotype = new ArrayList<>();

        for(Integer index : dataFlow){
            genotype.add((int)(Math.random() * correctionCost.size()));
        }

    }

    private void calculateFitness() {
        fitness = 0.0;
        int i = 0;
        for(Integer g : genotype){
            fitness += (Math.pow((dataFlow.get(i) + 1), correctionRate.get(g)) / correctionCost.get(g));
            i++;
        }
    }

    public void mutate(double probability){
        boolean changed = false;

        int i = 0;
        for(Integer g : genotype){
            if(probability < (Math.random()))
                continue;

            int newGene;
            do{
                newGene = (int) (Math.random() * correctionCost.size());
            }while(genotype.get(i) == newGene);

            genotype.set(i, newGene);
            changed = true;
            i++;
        }

        if(changed)
            calculateFitness();
    }

    public double getFitness() {

        return fitness;
    }

    public ArrayList<Integer> getGenotype() {

        return genotype;
    }

    @Override
    public boolean equals(Object obj){

        return ((obj instanceof Individual) && ((Individual) obj).getGenotype().equals(this.genotype));
    }

    @Override
    public int compareTo(Individual other) {
        if(this.getFitness() > other.getFitness())
            return 1;

        if(this.getFitness() < other.getFitness())
            return -1;

        return 0;
    }
}
