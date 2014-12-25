/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.TSP;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author group12
 */
public class DrawComponents {

    public static void drawCity(Graphics g, CityModel cityModel, double scalex, double scaley) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(Color.BLACK);
        int radius = cityModel.getRadius();
        Shape ovale = new Ellipse2D.Float((int) (cityModel.getX() / scalex) - radius, (int) (cityModel.getY() / scaley) - radius, radius * 2, radius * 2);
        graphics2D.draw(ovale);
    }

    public static void drawPath(Graphics g, List<CityModel> cities, double scalex, double scaley) {
        int size = cities.size();
        if (size > 0) {
            Graphics2D graphics2D = (Graphics2D) g;

            List<Integer> xpoints = new LinkedList<>();
            List<Integer> ypoints = new LinkedList<>();
            for (CityModel cityModel : cities) {
                xpoints.add((int) (cityModel.getX() / scalex));
                ypoints.add((int) (cityModel.getY() / scaley));
            }

            GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, size);

            polygon.moveTo(xpoints.get(0), ypoints.get(0));

            for (int i = 1; i < size; i++) {
                polygon.lineTo(xpoints.get(i), ypoints.get(i));
            };

            polygon.closePath();

            graphics2D.draw(polygon);
        }
    }

    public static void drawCity(Graphics g, CityModel cityModel) {
        DrawComponents.drawCity(g, cityModel, 1, 1);
    }

    public static void drawPath(Graphics g, List<CityModel> cities) {
        drawPath(g, cities, 1, 1);
    }

    public static void drawMap(Graphics g, List<CityModel> cities, double scalex, double scaley) {
        for (CityModel cityModel : cities) {
            DrawComponents.drawCity(g, cityModel, scalex, scaley);
        }

        DrawComponents.drawPath(g, cities, scalex, scaley);
    }
}
