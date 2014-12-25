/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.min1D;

import de.congrace.exp4j.Calculable;
import operator.evaluation.FitnessEvaluator;

/**
 *
 * @author group12
 */
public class Min1DFitnessFunction extends FitnessEvaluator<Min1DBeing> {

    private Calculable equation;

    public Min1DFitnessFunction(Calculable equation) {
        this.equation = equation;
    }

    @Override
    public void evaluateBeing(Min1DBeing being) {
        being.setFitnessScore(this.equation.calculate(being.getX()));
    }
}
