/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operator.selection;

import population.Being;
import population.Population;

/**
 *
 * @author max
 */
public interface SelectionOperatorInterface<T extends Being> {

    public Population<T> selectPopulation(Population<T> population);
}
