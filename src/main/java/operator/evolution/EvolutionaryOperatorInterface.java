/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operator.evolution;

import population.Being;
import population.Population;

/**
 *
 * @author Maximilien
 */
public interface EvolutionaryOperatorInterface<T extends Being> {
    
    public Population<T> apply(Population<T> selectedCandidates);
    
}
