/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package population;

import java.util.List;

/**
 *
 * @author group12
 */
public abstract class PopulationFactory<T extends Being> {

    public Population<T> generateInitialPopulation(int size) {
        Population<T> population = new Population<>(size);
        for (int i = 0; i < size; i++) {
            population.addBeing(generateRandomCandidate());
        }
        return population;
    }

    public Population<T> generateInitialPopulation(List<T> beings) {
        Population<T> population = new Population<>(beings.size());

        population.addBeings(beings);

        return population;
    }

    public abstract T generateRandomCandidate();
}
