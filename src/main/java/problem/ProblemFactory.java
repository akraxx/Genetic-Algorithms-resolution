/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package problem;

/**
 *
 * @author group12
 */
public abstract class ProblemFactory<T extends Problem> {
    public static int DEFAULT_POPULATION_SIZE = 500;
    private int populationSize;

    public ProblemFactory(int populationSize) {
        if(populationSize > 0) {
            this.populationSize = populationSize;
        }
        else {
            this.populationSize = ProblemFactory.DEFAULT_POPULATION_SIZE;
        }
    }

    public ProblemFactory() {
        this(ProblemFactory.DEFAULT_POPULATION_SIZE);
    }
    
    
    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }
    
    
}
