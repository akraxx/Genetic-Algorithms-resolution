/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import common.Event;
import population.Being;
import population.Population;

/**
 *
 * @author group12
 */
public class UpdateEvent<T extends Being> extends Event {

    private Population<T> population;
    private int generationNumber;
    
    public UpdateEvent(Object source, Population<T> population, int generationNumber) {
        super(source);
        if (population != null) {
            this.population = population;
            this.generationNumber = generationNumber;
        } else {
            throw new NullPointerException("UpdateEvent can not have a null population");
        }
    }

    public Population<T> getPopulation() {
        return population;
    }

    public int getGenerationNumber() {
        return generationNumber;
    }
    
}
