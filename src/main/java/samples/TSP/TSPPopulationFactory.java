/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.TSP;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import population.PopulationFactory;

/**
 *
 * @author group12
 */
public class TSPPopulationFactory extends PopulationFactory<TSPBeing> {

    List<CityModel> initialList = null;

    public TSPPopulationFactory(List<CityModel> initialList) {
        if (initialList != null) {
            this.initialList = initialList;
        } else {
            throw new IllegalArgumentException("Initial list of cities can not be null");
        }
    }

    public TSPPopulationFactory(int size, int maxWidth, int maxHeight) {
        if (size > 0) {
            this.initialList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                initialList.add(new CityModel((int) (Math.random() * maxWidth), (int) (Math.random() * maxHeight), 20));
            }
        } else {
            throw new IllegalArgumentException("size of a list of paths cannot be negative");
        }
    }

    @Override
    public TSPBeing generateRandomCandidate() {
        List<CityModel> list = new LinkedList<>(initialList);
        Collections.shuffle(list);
        return new TSPBeing(list);
    }
}