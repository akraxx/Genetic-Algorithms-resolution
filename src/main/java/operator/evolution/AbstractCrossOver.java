/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operator.evolution;

import annotations.EvolutionaryOperatorAnnotation;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import population.Being;
import population.Population;

/**
 *
 * @author group12
 */
public abstract class AbstractCrossOver<T extends Being> extends AbstractEvolutionaryOperator<T> {
    public final static double DEFAULT_CROSSOVER_RATE = 0.7;
    
    public AbstractCrossOver(double crossoverRate) {
        super(crossoverRate);
    }

    public AbstractCrossOver() {
        this(AbstractCrossOver.DEFAULT_CROSSOVER_RATE);
    }

    @Override
    public Population<T> apply(Population<T> population) {
        List<T> candidates = population.getBeings();
        Collections.shuffle(candidates);
        int size = candidates.size();

        Population result = new Population(population.getSize());
        List<T> newCandidates = result.getBeings();

        for (int i = 0; i < size; i++) {
            T parent1 = candidates.get(i);
            double rnd = Math.random();
            if (rnd < this.getRate()) {
                Random r = new Random();
                int randomedBeing = r.nextInt(size);
                if (i != randomedBeing) {
                    newCandidates.add(cross(parent1, candidates.get(randomedBeing)));
                } else {
                    newCandidates.add(parent1);
                }
            } else {
                newCandidates.add(parent1);
            }
        }
        return result;
    }

    public abstract T cross(T parent1, T parent2);
}
