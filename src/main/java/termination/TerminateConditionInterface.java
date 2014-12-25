/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package termination;

import population.Being;
import solver.Statistics;

/**
 *
 * @author group12
 */
public interface TerminateConditionInterface<T extends Being> {

    /**
     *
     * @param statsAboutGeneration
     * @return
     */
    public boolean isFinished(Statistics<T> statsAboutGeneration);
}
