/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operator.evolution;

import annotations.EvolutionaryOperatorAnnotation;
import annotations.RequiredParameterAnnotation;
import population.Being;
import population.Population;

/**
 *
 * @author group12
 */
@EvolutionaryOperatorAnnotation
public abstract class AbstractEvolutionaryOperator<T extends Being> implements EvolutionaryOperatorInterface<T> {
    @RequiredParameterAnnotation(description = "Rate", type = "Number [0, 1]")
    private double rate;

    public AbstractEvolutionaryOperator(double rate) {
        if (rate >= 0 && rate <= 1) {
            this.rate = rate;
        }
    }

    public AbstractEvolutionaryOperator() {
    }
    
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
    
    public abstract Population<T> apply(Population<T> selectedCandidates);
}
