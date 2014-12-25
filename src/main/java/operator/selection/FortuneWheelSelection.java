/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operator.selection;

import annotations.ClassDescription;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import population.Being;
import population.Population;

/**
 *
 * @author max
 */
@ClassDescription(name = "Fortune Wheel", description = "Fortune Wheel Operator Selection")
public class FortuneWheelSelection<T extends Being> extends AbstractSelectionOperator<T> {

    public static final String CLASS_DESCRIPTION = "Fortune Wheel Operator Selection";

    public FortuneWheelSelection(double percentOfpopulation) {
        super(percentOfpopulation);
    }

    public FortuneWheelSelection() {
    }

    @Override
    public Population<T> selectPopulation(Population<T> population) {
        List<T> beings = population.getBeings();

        int sizeOfNewPopulation = (int) (beings.size() * this.percentOfpopulation);
        Population newPopulation = new Population(sizeOfNewPopulation);
        List<T> newBeings = newPopulation.getBeings();

        double totalFitness = population.getTotalFitness();
        Random random = new Random();

        double randomFitness = random.nextDouble() * totalFitness;

        Collections.shuffle(beings);

        for (int i = 0; i < beings.size() && newBeings.size() < sizeOfNewPopulation; i++) {
            T being = beings.get(i);
            if (being.getFitnessScore() <= randomFitness) {
                newPopulation.addBeing(being);
                population.removeBeing(being);
            }
        }

        return newPopulation;
    }

    @Override
    public String toString() {
        return FortuneWheelSelection.CLASS_DESCRIPTION + " [ " + super.toString() + "]";
    }
}
