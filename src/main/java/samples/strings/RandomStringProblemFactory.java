/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.strings;

import problem.ProblemFactory;

/**
 *
 * @author group12
 */
public class RandomStringProblemFactory extends ProblemFactory<RandomStringProblem> {

    public RandomStringProblemFactory(int populationSize) {
        super(populationSize);
    }
    
    
    public RandomStringProblem DefaultStringProblem() {
        return new RandomStringProblem("Hello World !", 10, 13);
    }
}
