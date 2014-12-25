/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.min1D;

import annotations.ClassDescription;
import operator.evolution.AbstractCrossOver;

/**
 *
 * @author group12
 */
@ClassDescription(name = "Min1D default crossover", description = "default crossover")
public class Min1DCrossOver extends AbstractCrossOver<Min1DBeing> {

    public Min1DCrossOver() {
    }

    public Min1DCrossOver(double crossoverRate) {
        super(crossoverRate);
    }
    
    @Override
    public Min1DBeing cross(Min1DBeing parent1, Min1DBeing parent2) {
        double x1 = parent1.getX();
        double x2 = parent2.getX();
        double min = (x1 < x2 ? x1 : x2);

        double rnd = Math.random();

        return new Min1DBeing(min + rnd * (Math.abs(x1 - x2)));
    }
}
