import java.util.ArrayList;

public class GeneticAlgorithm {
    private static int populationSize = 50;
    private static int tournaments = 30;
    private static int generations = 100;
    private static double crossoverProbability = 0.7;
    private static double mutationProbability = 0.1;

    private ArrayList<Individual> population;
    private ArrayList<Integer> dataFlow;
    private ArrayList<Double> correctionRate;
    private ArrayList<Double> correctionCost;
    private double bestFitness;

    public GeneticAlgorithm(){
        population = new ArrayList<>();
        dataFlow = new ArrayList<>();
        correctionRate = new ArrayList<>();
        correctionCost = new ArrayList<>();
        bestFitness = 0.0;
    }

    public GeneticAlgorithm(ArrayList<Integer> dataFlow, ArrayList<Double> correctionRate, ArrayList<Double> correctionCost){
        population = new ArrayList<>();
        this.dataFlow = dataFlow;
        this.correctionRate = correctionRate;
        this.correctionCost = correctionCost;
        bestFitness = 0.0;
    }

    public void initPopulation(){

        for(int n = 0; n < populationSize; n++){
            population.add(new Individual(dataFlow, correctionRate, correctionCost));
        }
    }

    public void rouletteWheel(){

        //todo
    }

    public int crossover(Individual one, Individual two, ArrayList<Individual> newGeneration){
        int index;

        do{
            index = (int) (Math.random() * one.getGenotype().size());
        } while(index == 0 || index == one.getGenotype().size());

        ArrayList<Integer> sonOne = new ArrayList<>();
        sonOne.addAll(one.getGenotype().subList(0, index));
        sonOne.addAll(two.getGenotype().subList(index, two.getGenotype().size()));
        ArrayList<Integer> sonTwo = new ArrayList<>();
        sonTwo.addAll(two.getGenotype().subList(0, index));
        sonTwo.addAll(one.getGenotype().subList(index, two.getGenotype().size()));

        newGeneration.add(new Individual(sonOne, dataFlow, correctionRate, correctionCost));
        newGeneration.add(new Individual(sonTwo, dataFlow, correctionRate, correctionCost));

        return index;
    }
}
