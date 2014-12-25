/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.strings;

import java.util.Random;
import operator.evolution.AbstractCrossOver;
import population.Being;

/**
 *
 * @author group12
 */
public class RandomStringCrossover extends AbstractCrossOver {

    private int crossoverPoints = 1;

    public void setCrossoverPoints(int crossoverPoints) {
        this.crossoverPoints = crossoverPoints;
    }

    public RandomStringCrossover() {
    }

    public RandomStringCrossover(int crossoverPoints) {
        super();
        this.setCrossoverPoints(crossoverPoints);
    }

    @Override
    public Being cross(Being parent1, Being parent2) {
        String s1 = ((RandomStringBeing) parent1).getString();
        String s2 = ((RandomStringBeing) parent2).getString();
        Random rnd = new Random();

        if (s1.length() != s2.length()) {
            throw new IllegalArgumentException("Cannot cross two beings of different sizes");
        }
        StringBuilder child1 = new StringBuilder(s1);
        StringBuilder child2 = new StringBuilder(s2);


        for (int i = 0; i < this.crossoverPoints; i++) {
            int index = (1 + rnd.nextInt(s1.length() - 1));

            for (int j = 0; j < index; j++) {
                char temp = child1.charAt(j);
                child1.setCharAt(j, child2.charAt(j));
                child2.setCharAt(j, temp);
            }
        }


        return new RandomStringBeing(child1.toString());
    }
}
