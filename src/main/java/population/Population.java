/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package population;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author group12
 */
public class Population<T extends Being> implements BeingListener, Cloneable {

    private List<T> beings;
    private double totalFitness = 0;
    private int size;
    private double bestFitness = 0;
    private List<T> bestBeings = new LinkedList<>();
    public static int numberOfBestBeings = 1;

    public void updateBestFitness() {
        this.bestBeings.clear();
        for (T being : this.getBeings()) {
            int bestBeingsSize = this.bestBeings.size();
            if (bestBeingsSize < this.numberOfBestBeings) {
                this.bestBeings.add(being);
                Collections.sort(this.bestBeings, Collections.reverseOrder());
            } else if (being.compareTo(this.bestBeings.get(bestBeingsSize - 1)) > 0) {
                this.bestBeings.remove(bestBeingsSize - 1);
                this.bestBeings.add(being);
                Collections.sort(this.bestBeings, Collections.reverseOrder());
            }
        }
    }

    public Population(int nb) {
        this.beings = new ArrayList<>(nb);
        this.size = nb;
    }

    public Population(List<T> list) {
        this.beings = list;
        this.size = list.size();
    }

    public int getNumberOfBestBeings() {
        return numberOfBestBeings;
    }

    public void addBeings(List<T> beings) {
        if (beings != null) {
            for (T being : beings) {
                this.addBeing(being);
            }
        } else {
            throw new NullPointerException("List of Beings can not be null");
        }
    }

    public void addBeing(T being) {
        if (being != null) {
            this.beings.add(being);
            being.addListener(this);
        } else {
            throw new NullPointerException("Being can not be null");
        }
    }

    public void removeBeing(T being) {
        if (being != null && this.beings.contains(being)) {
            being.removeListener(this);
            this.totalFitness -= being.getFitnessScore();
            this.beings.remove(being);
        } else {
            throw new NullPointerException("Unable to remove this being");
        }
    }

    public List<T> getBeings() {
        return beings;
    }

    public double getTotalFitness() {
        return totalFitness;
    }

    public List<T> getBestBeings() {
        return bestBeings;
    }

    public double getBestFitness() {
        return bestFitness;
    }

    public int getSize() {
        return this.beings.size();
    }

    @Override
    public void markChangedEvent(MarkChangedEvent e) {
        if (e != null && e.getBeing() != null) {
            this.totalFitness -= e.getOldMark();
            this.totalFitness += e.getBeing().getFitnessScore();
        }
    }

    @Override
    public String toString() {
        String r = "";

        for (T being : this.beings) {
            r = r.concat(being + System.getProperty("line.separator"));
        }

        return r;
    }

    @Override
    public Population clone() {
        Population newPopulation = new Population(this.size);

        for (T being : this.beings) {
            newPopulation.addBeing(being);
        }

        return newPopulation;
    }
}
