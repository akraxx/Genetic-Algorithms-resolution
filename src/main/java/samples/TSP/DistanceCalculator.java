/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.TSP;

/**
 *
 * @author group12
 */
public class DistanceCalculator {
    
    /**
     *
     * @param source
     * @param dest
     * @return
     */
    public static double getDistance(CityModel source, CityModel dest){
        int xDistance = Math.abs(source.getX() - dest.getX());
        int yDistance = Math.abs(source.getY() - dest.getY());
        
        double distance = Math.sqrt( Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
        return distance;
    }
}
