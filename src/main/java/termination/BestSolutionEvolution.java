/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package termination;

import annotations.ClassDescription;
import population.Being;
import solver.Statistics;

/**
 *
 * @author group12
 */
@ClassDescription(name = "Best Solution criteria", description = "Best solution criteria")
public class BestSolutionEvolution<T extends Being> extends AbstractTerminateCondition<T> {

    public static String CLASS_DESCRIPTION = "Best solution condition";
    public static double DEFAULT_PERCENT_BEST_SOLUTION = 0.2;
    private Statistics<T> previousStatistics = null;
    private double threshold;

    public BestSolutionEvolution() {
    }

    public BestSolutionEvolution(double threshold) {
        this.threshold = threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public double getThreshold() {
        return threshold;
    }

    @Override
    public boolean isFinished(Statistics<T> statsAboutGeneration) {
        boolean finished = false;
        if (previousStatistics != null) {
            double evolutionPercentage = (statsAboutGeneration.getAverageFitness() - this.previousStatistics.getAverageFitness()) / this.previousStatistics.getAverageFitness();
            if (evolutionPercentage > this.threshold) {
                finished = true;
            }
        }

        this.previousStatistics = statsAboutGeneration;

        return finished;
    }

    @Override
    public String toString() {
        return BestSolutionEvolution.CLASS_DESCRIPTION + " [ " + this.threshold + " ] ";
    }
}
