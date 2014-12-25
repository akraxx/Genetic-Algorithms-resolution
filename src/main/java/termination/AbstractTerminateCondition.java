/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package termination;

import annotations.TerminateConditionAnnotation;
import population.Being;
import solver.Statistics;

/**
 *
 * @author group12
 */
@TerminateConditionAnnotation
public abstract class AbstractTerminateCondition<T extends Being> implements TerminateConditionInterface<T> {

    @Override
    public abstract boolean isFinished(Statistics<T> statsAboutGeneration);
}
