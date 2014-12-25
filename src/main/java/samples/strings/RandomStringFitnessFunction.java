/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.strings;

import operator.evaluation.FitnessEvaluator;
import population.Being;

/**
 *
 * @author group12
 */
public class RandomStringFitnessFunction extends FitnessEvaluator {

    private final String targetString;

    public RandomStringFitnessFunction(String targetString) {
        this.targetString = targetString;
    }

    @Override
    public void evaluateBeing(Being being) {
        String s = ((RandomStringBeing) being).getString();
        {
            int errors = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != targetString.charAt(i)) {
                    ++errors;
                }
            }
            being.setFitnessScore(errors);
        }
    }
}
