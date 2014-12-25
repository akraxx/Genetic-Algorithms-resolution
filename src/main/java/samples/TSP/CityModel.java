/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.TSP;

/**
 *
 * @author group12
 */
public class CityModel {

    private int x;
    private int y;
    private int radius;

    public CityModel(int x, int y, int diameter) {
        this.x = x;
        this.y = y;
        this.radius = diameter / 2;
    }

    public int getRadius() {
        return radius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "City [ x = " + this.x + ", y = " + this.y + " ]";
    }
}
