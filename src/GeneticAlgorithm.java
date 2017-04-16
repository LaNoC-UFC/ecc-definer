import java.util.ArrayList;
import java.util.Collections;

public class GeneticAlgorithm {
    private static int populationSize = 50;
    private static int generations = 100;
    private static double crossoverProbability = 0.7;
    private static double mutationProbability = 0.1;

    private ArrayList<Individual> population;
    private ArrayList<Integer> dataFlow;
    private ArrayList<Double> correctionRate;
    private ArrayList<Double> correctionCost;
    private double bestFitness;

    public static void main(String[] args){

        //input args processing

        ArrayList<Integer> dataFlow = null; //read from text file
        ArrayList<Double> correctionRate = null; //read from text file
        ArrayList<Double> correctionCost = null; //read from text file

        GeneticAlgorithm ga = new GeneticAlgorithm(dataFlow, correctionRate, correctionCost);
        ga.initPopulation();
        ga.selection();
        
        System.out.println("Final Best Fitness: " + ga.getPopulation().get(ga.getPopulation().size() - 1).getFitness());
        System.out.println("Genotype: " + ga.getPopulation().get(ga.getPopulation().size() - 1).getGenotype().toString());
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

    public void selection(){
        ArrayList<Individual> newGeneration;
        Individual parentOne, parentTwo;
        double fitnessSum;

        for(int g = 0; g < generations; g++){
            newGeneration = new ArrayList<>();

            while(population.size() > 0) {
                //do the roulette wheel selection method for two individuals
                fitnessSum = rouletteWheelInit();
                parentOne = rouletteWheelSelection(fitnessSum);
                parentTwo = rouletteWheelSelection(fitnessSum);

                //do crossover for generate two sons
                crossover(parentOne, parentTwo, newGeneration);

                //do mutation on each son
                newGeneration.get(newGeneration.size() - 2).mutate(mutationProbability); //before last individual
                newGeneration.get(newGeneration.size() - 1).mutate(mutationProbability); //last individual

                //delete the parents from actual generation
                population.remove(parentOne);
                population.remove(parentTwo);
            }

            population = newGeneration;
        }
        Collections.sort(population);
    }

    public double rouletteWheelInit(){
        double fitnessSum = 0.0;
        bestFitness = 0.0;

        for(Individual i : population){
            fitnessSum += i.getFitness();
            if(i.getFitness() > bestFitness)
                bestFitness = i.getFitness();
        }

        Collections.sort(population);
        System.out.println("Best Fitness: " + bestFitness);
        return fitnessSum;
    }

    public Individual rouletteWheelSelection(double fitnessSum){
        double partialSum = 0.0;
        double selected = Math.random() * fitnessSum;

        for(Individual i : population){
            partialSum += i.getFitness();

            if(partialSum >= selected)
                return i;
        }

        return null;
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

    public ArrayList<Individual> getPopulation(){
        return population;
    }
}
