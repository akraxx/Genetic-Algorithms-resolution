/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import population.Being;

/**
 *
 * @author group12
 */
public final class Statistics<T extends Being> {

    private final double averageFitness;
    private final int generationNumber;
    private final double spentTime;

    public Statistics(double averageFitness, int generationNumber,
            double spentTime) {
        this.averageFitness = averageFitness;
        this.generationNumber = generationNumber;
        this.spentTime = spentTime;
    }

    public double getAverageFitness() {
        return averageFitness;
    }

    public int getGenerationNumber() {
        return generationNumber;
    }

    public double getSpentTime() {
        return spentTime;
    }
}
