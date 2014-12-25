/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.min1D;

import java.util.Random;
import population.PopulationFactory;

/**
 *
 * @author group12
 */
public class Min1DPopulationFactory extends PopulationFactory<Min1DBeing> {

    Interval interval;

    public Min1DPopulationFactory(Interval interval) {
        if (interval != null) {
            this.interval = interval;
        } else {
            throw new NullPointerException("Interval of Min1DPopulationFactory can not be null");
        }
    }

    @Override
    public Min1DBeing generateRandomCandidate() {
        double max = this.interval.getMaxBoundary();
        double min = this.interval.getMinBoundary();
        Random r = new Random();
        return new Min1DBeing((r.nextDouble() * (max - min)) + min);
    }
}
