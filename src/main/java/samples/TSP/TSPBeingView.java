/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.TSP;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author group12
 */
public class TSPBeingView extends JPanel {

    private TSPBeing being = null;
    private int defaultHeight;
    private int defaultWidth;

    public TSPBeingView() {
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public int getDefaultHeight() {
        return defaultHeight;
    }

    public int getDefaultWidth() {
        return defaultWidth;
    }

    public void setDefaultHeight(int defaultHeight) {
        this.defaultHeight = defaultHeight;
    }

    public void setDefaultWidth(int defaultWidth) {
        this.defaultWidth = defaultWidth;
    }

    public void setBeing(TSPBeing being) {
        this.being = being;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (being != null) {
            DrawComponents.drawMap(g, being.getCitiesList(), (double) this.defaultWidth / (double) this.getWidth(), (double) this.defaultHeight / (double) this.getHeight());
        }
    }
}
