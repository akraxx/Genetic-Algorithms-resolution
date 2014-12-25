/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.strings;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import population.Being;
import population.Population;
import population.PopulationFactory;

/**
 *
 * @author group12
 */
public class RandomStringPopulationFactory extends PopulationFactory {

    private int beingsSize;

    public RandomStringPopulationFactory(int beingsSize) {
        if (beingsSize > 0) {
            this.beingsSize = beingsSize;
        } else {
            throw new NullPointerException("Size of a population cannot be negative");
        }
    }

    @Override
    public Being generateRandomCandidate() {
        Random rnd = new Random();

        StringBuilder buffer = new StringBuilder(this.beingsSize);


        for (int i = 0; i < this.beingsSize; i++) {
            buffer.append(Alphabet.alphabet[rnd.nextInt(Alphabet.alphabet.length)]);
        }

        return new RandomStringBeing(buffer.toString());
    }
}
