/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.min1D;

import problem.ProblemFactory;

/**
 *
 * @author group12
 */
public class Min1DProblemFactory extends ProblemFactory<Min1DProblem> {
    

    public Min1DProblemFactory(int populationSize) {
        super(populationSize);
    }

    public Min1DProblemFactory() {
    }
    
    public Min1DProblem DefaultMin1DProblem() {
        return new Min1DProblem("x^2", -100.0, 100.0, this.getPopulationSize());
    }

    public Min1DProblem CubeMin1DProblem() {
        return new Min1DProblem("x^3", -100.0, 100.0, this.getPopulationSize());
    }
}
