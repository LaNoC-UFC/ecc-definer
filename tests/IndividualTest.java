import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class IndividualTest {

    private ArrayList<Integer> newGenotype;
    private ArrayList<Integer> anotherGenotype;
    private ArrayList<Integer> weights;
    private ArrayList<Double> areas;
    private ArrayList<Double> reliabilities;

    @Before
    public void setUp(){
        newGenotype = new ArrayList<>();
        newGenotype.add(2);
        newGenotype.add(2);
        newGenotype.add(0);
        newGenotype.add(3);

        anotherGenotype = new ArrayList<>();
        anotherGenotype.add(2);
        anotherGenotype.add(1);
        anotherGenotype.add(0);
        anotherGenotype.add(3);

        weights = new ArrayList<>();
        weights.add(2);
        weights.add(1);
        weights.add(1);
        weights.add(1);

        areas = new ArrayList<>();
        areas.add(1.0);
        areas.add(2.0);
        areas.add(3.0);
        areas.add(4.0);

        reliabilities = new ArrayList<>();
        reliabilities.add(2.0);
        reliabilities.add(4.0);
        reliabilities.add(6.0);
        reliabilities.add(8.0);
    }

    @Test
    public void fitnessValidation() {
        Individual individual = new Individual(newGenotype, weights, reliabilities, areas);

        Assert.assertEquals(1.7272, individual.getFitness(), 0.0001);
    }

    @Test
    public void sameGenotype() {
        Individual individual = new Individual(newGenotype, weights, reliabilities, areas);

        Assert.assertEquals(newGenotype.toString(), individual.getGenotype().toString());
    }

    @Test
    public void sameIndividual() {
        Individual individual = new Individual(newGenotype, weights, reliabilities, areas);
        Individual clone = new Individual(newGenotype, weights, reliabilities, areas);

        Assert.assertEquals(individual, clone);
    }

    @Test
    public void differentIndividuals() {
        Individual individual = new Individual(newGenotype, weights, reliabilities, areas);
        Individual another = new Individual(anotherGenotype, weights, reliabilities, areas);

        Assert.assertNotEquals(individual, another);
    }

    @Test
    public void mutateValidation() {
        Individual individual = new Individual(newGenotype, weights, reliabilities, areas);

        ArrayList<Integer> g = new ArrayList<>();
        g.add(2);
        g.add(2);
        g.add(0);
        g.add(3);
        Individual mutation = new Individual(g, weights, reliabilities, areas);
        mutation.mutate();

        Assert.assertNotEquals(individual, mutation);
    }

}