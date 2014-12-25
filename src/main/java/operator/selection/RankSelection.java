/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operator.selection;

import annotations.ClassDescription;
import java.util.Collections;
import java.util.List;
import population.Being;
import population.Population;

/**
 *
 * @author max
 */
@ClassDescription(name = "Rank", description = "Rank Operator Selection")
public class RankSelection<T extends Being> extends AbstractSelectionOperator<T> {

    public static String CLASS_DESCRIPTION = "Rank Operator Selection";

    public RankSelection(double percentOfpopulation) {
        super(percentOfpopulation);
    }

    public RankSelection() {
    }

    @Override
    public Population<T> selectPopulation(Population<T> population) {
        List<T> beings = population.getBeings();

        int sizeOfNewPopulation = (int) (beings.size() * this.percentOfpopulation);
        int sizeOfPopulation = beings.size();

        Population newPopulation = new Population(sizeOfNewPopulation);

        Collections.sort(beings);

        for (int i = sizeOfPopulation - 1; i > (sizeOfPopulation - sizeOfNewPopulation); i--) {
            newPopulation.addBeing(beings.get(i));
            population.removeBeing(beings.get(i));
        }

        return newPopulation;
    }

    @Override
    public String toString() {
        return RankSelection.CLASS_DESCRIPTION + " [ " + super.toString() + "]";
    }
}
