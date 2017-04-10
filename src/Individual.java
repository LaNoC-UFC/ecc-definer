import java.util.ArrayList;

public class Individual {
    private ArrayList<Integer> genotype;
    private ArrayList<Integer> weights;
    private ArrayList<Double> reliabilities;
    private ArrayList<Double> areas;
    private double fitness;

    public Individual(ArrayList<Integer> weights, ArrayList<Double> reliabilites, ArrayList<Double> areas) {
        genotype = new ArrayList<>();
        this.weights = weights;
        this.reliabilities = reliabilites;
        this.areas = areas;
        generateGenes();
        calculateFitness();
    }

    public Individual(ArrayList<Integer> genotype, ArrayList<Integer> weights, ArrayList<Double> reliabilites, ArrayList<Double> areas) {
        this.genotype = genotype;
        this.weights = weights;
        this.reliabilities = reliabilites;
        this.areas = areas;
        calculateFitness();
    }

    private void generateGenes() {
        genotype = new ArrayList<>();

        for(Integer index : weights){
            genotype.add((int)(Math.random() * areas.size()));
        }

    }

    private void calculateFitness() {
        fitness = 0.0;

        double area = 0.0;
        for (Integer a : genotype)
            area += areas.get(a);

        double reliability = 0.0;
        int w = 0;
        for (Integer r : genotype) {
            reliability += (reliabilities.get(r) / (double) weights.get(w));
            w++;
        }
        fitness = (reliability / area);
    }

    public void mutate(){
        int gene = (int) (Math.random() * genotype.size());
        int newGene;

        do{
            newGene = (int) (Math.random() * 4);
        }while(genotype.get(gene) == newGene);

        genotype.set(gene, newGene);
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
}
