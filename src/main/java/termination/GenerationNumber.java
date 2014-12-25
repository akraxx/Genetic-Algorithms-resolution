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
@ClassDescription(name = "Generation criteria", description = "Generation criteria")
public class GenerationNumber<T extends Being> extends AbstractTerminateCondition<T> {

    public static String CLASS_DESCRIPTION = "Number of elapsed generations condition";
    public static int DEFAULT_NUMBER_OF_GENERATIONS = 300;
    @RequiredParameterAnnotation(description = "How many generations", type = "Number > 0")
    private int numberOfGeneration;

    public GenerationNumber(int generationNumber) {
        if (generationNumber > 0) {
            this.numberOfGeneration = generationNumber;
        } else {
            this.numberOfGeneration = GenerationNumber.DEFAULT_NUMBER_OF_GENERATIONS;
        }
    }

    public GenerationNumber() {
        this(GenerationNumber.DEFAULT_NUMBER_OF_GENERATIONS);
    }

    public int getNumberOfGeneration() {
        return numberOfGeneration;
    }

    public void setNumberOfGeneration(int numberOfGeneration) {
        this.numberOfGeneration = numberOfGeneration;
    }

    @Override
    public boolean isFinished(Statistics<T> statsAboutGeneration) {
        return statsAboutGeneration.getGenerationNumber() >= this.numberOfGeneration;
    }

    @Override
    public String toString() {
        return GenerationNumber.CLASS_DESCRIPTION + " [ " + this.numberOfGeneration + " ] ";
    }
}
