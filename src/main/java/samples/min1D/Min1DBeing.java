/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.min1D;

import population.Being;

/**
 *
 * @author group12
 */
public class Min1DBeing extends Being<Min1DBeing>{
    
    private double x;

    public double getX() {
        return x;
    }
    
    public Min1DBeing(double fitnessScore, double x) {
        super(fitnessScore);
        this.x = x;
    }
    
    public Min1DBeing(double x) {
        this(0, x);
    }
    
    @Override
    public String toString() {
        return String.valueOf(x) + ", fitness :" + super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(Min1DBeing o) {
        if(this.getFitnessScore() < o.getFitnessScore()) {
            return 1;
        }
        else if(this.getFitnessScore() > o.getFitnessScore()) {
            return -1;
        }
        else {
            return 0;
        }
    }

    @Override
    public Min1DBeing clone() {
        return new Min1DBeing(this.getFitnessScore(), this.x);
    }
}
