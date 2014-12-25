/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package termination;

import annotations.ClassDescription;
import annotations.RequiredParameterAnnotation;
import population.Being;
import solver.Statistics;

/**
 *
 * @author group12
 */
@ClassDescription(name = "Spent Time", description = "Spent Time")
public class SpentTime<T extends Being> extends AbstractTerminateCondition<T> {

    public static String CLASS_DESCRIPTION = "Spent time condition";
    public static long DEFAULT_TIME_LIMIT = 60;
    @RequiredParameterAnnotation(description = "Duration", type = "Number > 0 [ milliseconds ]")
    private long limit;

    public SpentTime(long limit) {
        if (limit > 0) {
            this.limit = limit;
        } else {
            this.limit = SpentTime.DEFAULT_TIME_LIMIT;
        }
    }

    public SpentTime() {
        this(SpentTime.DEFAULT_TIME_LIMIT);
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public long getLimit() {
        return limit;
    }

    @Override
    public boolean isFinished(Statistics<T> statsAboutGeneration) {
        return (statsAboutGeneration.getSpentTime() / 1000000) >= limit;
    }

    @Override
    public String toString() {
        return SpentTime.CLASS_DESCRIPTION + " [ " + this.limit + " ] ";
    }
}
