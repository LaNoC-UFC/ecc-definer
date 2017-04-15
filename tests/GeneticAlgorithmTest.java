import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class GeneticAlgorithmTest {

    Individual one;
    Individual two;
    ArrayList<Individual> population;
    ArrayList<Integer> dataFlow;
    ArrayList<Double> correctionRate;
    ArrayList<Double> correctionCost;

    @Before
    public void setUp(){
        dataFlow = new ArrayList<>();
        dataFlow.add(2);
        dataFlow.add(4);
        dataFlow.add(10);

        correctionRate = new ArrayList<>();
        correctionRate.add(1.0);
        correctionRate.add(1.0);
        correctionRate.add(3.0);
        correctionRate.add(6.0);

        correctionCost = new ArrayList<>();
        correctionCost.add(1.0);
        correctionCost.add(1.0);
        correctionCost.add(3.0);
        correctionCost.add(6.0);

        one = new Individual(dataFlow, correctionRate, correctionCost);
        two = new Individual(dataFlow, correctionRate, correctionCost);

        population = new ArrayList<>();
    }

    @Test
    public void crossoverTest(){

        GeneticAlgorithm ga = new GeneticAlgorithm(dataFlow, correctionRate, correctionCost);
        ArrayList<Individual> newGeneration = new ArrayList<>();

        int index = ga.crossover(one, two, newGeneration);

        Assert.assertEquals(one.getGenotype().subList(0, index),
                newGeneration.get(0).getGenotype().subList(0, index));
        Assert.assertEquals(two.getGenotype().subList(index, two.getGenotype().size()),
                newGeneration.get(0).getGenotype().subList(index, two.getGenotype().size()));
        Assert.assertEquals(two.getGenotype().subList(0, index),
                newGeneration.get(newGeneration.size() - 1).getGenotype().subList(0, index));
        Assert.assertEquals(one.getGenotype().subList(index, two.getGenotype().size()),
                newGeneration.get(newGeneration.size() - 1).getGenotype().subList(index, two.getGenotype().size()));
    }
}
