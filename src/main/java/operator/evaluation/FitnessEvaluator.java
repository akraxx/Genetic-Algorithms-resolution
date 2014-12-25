/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operator.evaluation;

import java.util.Iterator;
import population.Being;
import population.Population;

/**
 *
 * @author group12
 */
public abstract class FitnessEvaluator<T extends Being> {

    public void evaluatePopulation(Population<T> population) {
        Iterator<T> it = population.getBeings().iterator();

        while (it.hasNext()) {
            this.evaluateBeing(it.next());
        }

    }

    public abstract void evaluateBeing(T being);
}
