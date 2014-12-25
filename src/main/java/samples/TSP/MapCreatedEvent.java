/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.TSP;

import common.Event;
import java.util.List;

/**
 *
 * @author group12
 */
public class MapCreatedEvent extends Event {

    List<CityModel> cities;
    private int defaultHeight;
    private int defaultWidth;

    public MapCreatedEvent(Object source, List<CityModel> cities, int defaultWidth, int degaultHeight) {
        super(source);
        if (cities != null) {
            this.defaultWidth = defaultWidth;
            this.defaultHeight = degaultHeight;
            this.cities = cities;
        } else {
            throw new NullPointerException("MapCreatedEvent can not have a null list of cities");
        }
    }

    public List<CityModel> getCities() {
        return cities;
    }

    public int getDefaultHeight() {
        return defaultHeight;
    }

    public int getDefaultWidth() {
        return defaultWidth;
    }
}
