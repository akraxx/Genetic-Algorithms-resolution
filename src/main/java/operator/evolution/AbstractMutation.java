/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operator.evolution;

import annotations.EvolutionaryOperatorAnnotation;
import java.util.List;
import java.util.ListIterator;
import population.Being;
import population.Population;

/**
 *
 * @author group12
 */
public abstract class AbstractMutation<T extends Being> extends AbstractEvolutionaryOperator<T> {
    public static double DEFAULT_MUTATION_RATE = 0.002;

    public AbstractMutation(double mutationRate) {
        super(mutationRate);
    }

    public AbstractMutation() {
        this(AbstractMutation.DEFAULT_MUTATION_RATE);
    }

    @Override
    public Population<T> apply(Population<T> population) {
        List<T> candidates = population.getBeings();

        Population result = new Population(population.getSize());
        List<T> newCandidates = result.getBeings();

        ListIterator<T> iterator = candidates.listIterator();

        while (iterator.hasNext()) {
            double prob = Math.random();
            T candidate = iterator.next();
            if (prob < this.getRate()) {
                newCandidates.add(mutate(candidate));
            } else {
                newCandidates.add(candidate);
            }
        }

        return result;
    }

    public abstract T mutate(T target);
}
