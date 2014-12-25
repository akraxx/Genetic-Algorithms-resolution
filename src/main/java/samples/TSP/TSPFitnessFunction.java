/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.TSP;

import java.util.List;
import operator.evaluation.FitnessEvaluator;

/**
 *
 * @author group12
 */
public class TSPFitnessFunction extends FitnessEvaluator<TSPBeing> {

    @Override
    public void evaluateBeing(TSPBeing being) {
        List<CityModel> citiesList = being.getCitiesList();

        double totalDistance = 0;
        int cityCount = citiesList.size();
        for (int i = 0; i < cityCount; i++) {
            totalDistance += DistanceCalculator.getDistance(citiesList.get(i), citiesList.get((i + 1) % cityCount));
        }

        being.setFitnessScore(totalDistance);
    }
}
