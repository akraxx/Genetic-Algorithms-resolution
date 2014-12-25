/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.strings;

import population.Being;

/**
 *
 * @author group12
 */
public class RandomStringBeing extends Being {

    private String string;

    public RandomStringBeing(String s) {
        this.string = s;
    }

    public String getString() {
        return string;
    }

    public void setCore(String string) {
        this.string = string;
    }

    @Override
    public int compareTo(Being o) {

        if (this.getFitnessScore() < o.getFitnessScore()) {
            return 1;
        } else if (this.getFitnessScore() > o.getFitnessScore()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public Being clone() {
        return new RandomStringBeing(this.getString());
    }
}
