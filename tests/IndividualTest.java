import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class IndividualTest {

    private ArrayList<Integer> newGenotype;
    private ArrayList<Integer> sameGenotype;
    private ArrayList<Integer> anotherGenotype;
    private ArrayList<Integer> dataFlow;
    private ArrayList<Double> correctionCost;
    private ArrayList<Double> correctionRate;

    @Before
    public void setUp(){
        newGenotype = new ArrayList<>();
        newGenotype.add(2);
        newGenotype.add(2);
        newGenotype.add(0);
        newGenotype.add(3);

        sameGenotype = new ArrayList<>();
        sameGenotype.add(2);
        sameGenotype.add(2);
        sameGenotype.add(0);
        sameGenotype.add(3);

        anotherGenotype = new ArrayList<>();
        anotherGenotype.add(2);
        anotherGenotype.add(1);
        anotherGenotype.add(0);
        anotherGenotype.add(3);

        dataFlow = new ArrayList<>();
        dataFlow.add(2);
        dataFlow.add(1);
        dataFlow.add(5);
        dataFlow.add(1);

        correctionCost = new ArrayList<>();
        correctionCost.add(1.0);
        correctionCost.add(2.0);
        correctionCost.add(3.0);
        correctionCost.add(4.0);

        correctionRate = new ArrayList<>();
        correctionRate.add(2.0);
        correctionRate.add(4.0);
        correctionRate.add(6.0);
        correctionRate.add(8.0);
    }

    @Test
    public void fitnessValidation() {
        Individual individual = new Individual(newGenotype, dataFlow, correctionRate, correctionCost);

        Assert.assertEquals(364.333, individual.getFitness(), 0.001);
    }

    @Test
    public void sameGenotype() {
        Individual individual = new Individual(newGenotype, dataFlow, correctionRate, correctionCost);

        Assert.assertEquals(newGenotype.toString(), individual.getGenotype().toString());
    }

    @Test
    public void sameIndividual() {
        Individual individual = new Individual(newGenotype, dataFlow, correctionRate, correctionCost);
        Individual clone = new Individual(sameGenotype, dataFlow, correctionRate, correctionCost);

        Assert.assertEquals(individual, clone);
    }

    @Test
    public void differentIndividuals() {
        Individual individual = new Individual(newGenotype, dataFlow, correctionRate, correctionCost);
        Individual another = new Individual(anotherGenotype, dataFlow, correctionRate, correctionCost);

        Assert.assertNotEquals(individual, another);
    }

    @Test
    public void mutateEvent() {
        Individual individual = new Individual(newGenotype, dataFlow, correctionRate, correctionCost);

        ArrayList<Integer> g = new ArrayList<>();
        g.add(2);
        g.add(2);
        g.add(0);
        g.add(3);

        Individual mutation = new Individual(g, dataFlow, correctionRate, correctionCost);
        mutation.mutate(1.0);

        Assert.assertNotEquals(individual, mutation);
    }

    @Test
    public void noMutateEvent() {
        Individual individual = new Individual(newGenotype, dataFlow, correctionRate, correctionCost);

        ArrayList<Integer> g = new ArrayList<>();
        g.add(2);
        g.add(2);
        g.add(0);
        g.add(3);
        Individual mutation = new Individual(g, dataFlow, correctionRate, correctionCost);
        mutation.mutate(0.0);

        Assert.assertEquals(individual, mutation);
    }
}