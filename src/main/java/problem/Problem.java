/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package problem;

import population.Population;
import population.PopulationFactory;
import population.Being;

/**
 *
 * @author group12
 */
public abstract class Problem<T extends Being> {

    private PopulationFactory<T> populationFactory;
    private Population<T> initialPopulation;
    private Population<T> population;
    private int initialPopulationSize;

    public Problem(PopulationFactory<T> populationFactory, int initialPopulationSize) {
        this.initPopulation(populationFactory, initialPopulationSize);
    }

    public Problem(Population<T> population) {
        if (population != null) {
            this.setPopulation(population);
        } else {
            throw new NullPointerException("A problem can not have a null population");
        }
    }
    
    public Problem(int size) {
        if(size > 0) {
            this.initialPopulationSize = size;
        }
    }
    
    public Problem() {
    }
    
    public final void initPopulation(PopulationFactory populationFactory, int initialPopulationSize) {
        if (populationFactory != null) {
            this.populationFactory = populationFactory;
            this.initialPopulationSize = initialPopulationSize;
            this.setPopulation(populationFactory.generateInitialPopulation(initialPopulationSize));
            this.setInitialPopulation(this.population.clone());
        } else {
            throw new NullPointerException("A problem without initial population can not have a null populationFactory");
        }
    }

    public PopulationFactory getPopulationFactory() {
        return populationFactory;
    }
    
    public int getInitialPopulationSize() {
        return initialPopulationSize;
    }

    public Population getPopulation() {
        return population;
    }

    public Population getInitialPopulation() {
        return initialPopulation;
    }

    public void setInitialPopulation(Population initialPopulation) {
        this.initialPopulation = initialPopulation;
    }

    public void setPopulationFactory(PopulationFactory populationFactory) {
        this.populationFactory = populationFactory;
    }

    public void setInitialPopulationSize(int initialPopulationSize) {
        this.initialPopulationSize = initialPopulationSize;
    }

    public final void setPopulation(Population population) {
        this.population = population;
    }
}
