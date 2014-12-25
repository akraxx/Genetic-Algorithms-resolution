/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.TSP;

import java.util.List;
import population.Being;

/**
 *
 * @author group12
 */
public class TSPBeing extends Being<TSPBeing> {

    private List<CityModel> citiesList;

    public TSPBeing(List<CityModel> list) {
        this.citiesList = list;
    }

    public List<CityModel> getCitiesList() {
        return citiesList;
    }

    @Override
    public int compareTo(TSPBeing o) {
        if (this.getFitnessScore() < o.getFitnessScore()) {
            return 1;
        } else if (this.getFitnessScore() > o.getFitnessScore()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public TSPBeing clone() {
        return new TSPBeing(this.getCitiesList());
    }
}
