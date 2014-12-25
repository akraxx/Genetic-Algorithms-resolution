/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.TSP;

import annotations.ClassDescription;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import operator.evolution.AbstractCrossOver;
import population.Being;

/**
 *
 * @author group12
 */
@ClassDescription(name = "TSP default crossover", description = "default crossover")
public class TSPCrossOver extends AbstractCrossOver<TSPBeing> {

    @Override
    public TSPBeing cross(TSPBeing parent1, TSPBeing parent2) {
        List<CityModel> citiesList = parent1.getCitiesList();
        List<CityModel> citiesList2 = parent2.getCitiesList();

        int size = citiesList.size();
        if (size != citiesList2.size()) {
            throw new IllegalArgumentException("The paths don't have the same size");
        } else {
            Random r = new Random();
            int number1 = r.nextInt(size - 1);
            int number2 = r.nextInt(size);

            int start = Math.min(number1, number2);
            int end = Math.max(number1, number2);

            List<CityModel> child1 = new ArrayList<>();
            List<CityModel> child2 = new ArrayList<>();

            child1.addAll(citiesList.subList(start, end));
            child2.addAll(citiesList2.subList(start, end));

            int currentCityIndex = 0;
            CityModel currentCityInTour1;
            CityModel currentCityInTour2;

            for (int i = 0; i < size; i++) {

                // get the index of the current city
                currentCityIndex = (end + i) % size;

                // get the city at the current index in each of the two parent tours
                currentCityInTour1 = citiesList.get(currentCityIndex);
                currentCityInTour2 = citiesList2.get(currentCityIndex);

                // if child 1 does not already contain the current city in tour 2, add it
                if (!child1.contains(currentCityInTour2)) {
                    child1.add(currentCityInTour2);
                }

                if (!child2.contains(currentCityInTour1)) {
                    child2.add(currentCityInTour1);
                }
            }

            Collections.rotate(child1, start);

            return new TSPBeing(child1);
        }
    }
}
