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
import operator.evolution.AbstractMutation;
import population.Being;

/**
 *
 * @author group12
 */
@ClassDescription(name = "TSP default mutation", description = "default mutation")
public class TSPMutation extends AbstractMutation<TSPBeing> {

    @Override
    public TSPBeing mutate(TSPBeing target) {
        List<CityModel> initialList = target.getCitiesList();

        Random rnd = new Random();
        int size = initialList.size();
        int threshold1 = rnd.nextInt(size);
        int threshold2;
        do {
            threshold2 = rnd.nextInt(size);
        } while (threshold2 == threshold1);


        Collections.swap(initialList, threshold1, threshold2);


        int min = Math.min(threshold1, threshold2);
        int max = Math.max(threshold1, threshold2);
        int gap = max - min - 1;

        if (gap >= 2) {
            for (int i = 0; i < (gap / 2); i++) {
                Collections.swap(initialList, min + 1 + i, max - 1 - i);
            }
        }

        return new TSPBeing(initialList);
    }
}
