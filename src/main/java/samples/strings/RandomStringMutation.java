/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.strings;

import java.util.Random;
import operator.evolution.AbstractMutation;
import population.Being;

/**
 *
 * @author group12
 */
public class RandomStringMutation extends AbstractMutation {

    @Override
    public Being mutate(Being target) {
        Random rnd = new Random();
        String s = ((RandomStringBeing) target).getString();

        StringBuilder buffer = new StringBuilder(s);

        buffer.setCharAt(rnd.nextInt(s.length()), Alphabet.alphabet[rnd.nextInt(Alphabet.alphabet.length)]);

        return new RandomStringBeing(buffer.toString());
    }
}
