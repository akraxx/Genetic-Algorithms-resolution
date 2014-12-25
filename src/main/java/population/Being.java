/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package population;

import common.Observable;

/**
 *
 * @author max
 */
public abstract class Being<T extends Being> implements Comparable<T>, Cloneable {
    private Observable<BeingListener> observable = new Observable<>();
    
    private double fitnessScore;
    public Being(double fitnessScore) {
        this.fitnessScore = fitnessScore;
    }
    
    public Being() {
        this(0);
    }
        
    private void fireMarkChangedEvent(double oldFitnessScore) {
        for (BeingListener l : this.observable.getListeners()) {
            l.markChangedEvent(new MarkChangedEvent(this, oldFitnessScore, this));
        }
    }
    
    @Override
    public abstract int compareTo(T o);

    public double getFitnessScore() {
        return fitnessScore;
    }
    
    public void setFitnessScore(double newFitnessScore) {
        double oldFitnessScore = this.fitnessScore;
        this.fitnessScore = newFitnessScore;
        this.fireMarkChangedEvent(oldFitnessScore);
        
    }
    

    /**
     *
     * @param l : listener to add
     */
    public void addListener(BeingListener l) {
        this.observable.addListener(l);
        this.fireMarkChangedEvent(0);
    }
    
    /**
     *
     * @param l : listener to add
     */
    public void removeListener(BeingListener l) {
        double oldFitnessScore = this.fitnessScore;
        this.fitnessScore = 0;
        this.fireMarkChangedEvent(oldFitnessScore);
        this.observable.removeListener(l);
    }

    @Override
    public String toString() {
        return String.valueOf(fitnessScore); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public abstract T clone();
    
    
}
