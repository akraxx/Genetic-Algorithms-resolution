/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.TSP;

import common.Observable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author group12
 */
public class CreationPanel extends JPanel implements MouseListener, ActionListener {

    private Observable<CreationListener> observable = new Observable<>();
    private Color bgColor = Color.WHITE;
    List<CityModel> cities = new LinkedList<>();

    private void fireMapCreatedEvent() {
        for (CreationListener l : this.observable.getListeners()) {
            l.mapCreated(new MapCreatedEvent(this, this.cities, this.getSize().width, this.getSize().height));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(bgColor);
        super.paintComponent(g);
        for (CityModel city : this.cities) {
            if (city != null) {
                DrawComponents.drawCity(g, city);
            }
        }
        DrawComponents.drawPath(g, cities);
    }

    private void initComponent() {
        JButton jButton = new JButton("BEGIN");
        jButton.addActionListener(this);
        this.add(jButton, BorderLayout.SOUTH);
        this.addMouseListener(this);

        this.add(new JLabel("Click on the container to add some cities, then click BEGIN to start"), BorderLayout.NORTH);
    }

    public CreationPanel() {
        super(new BorderLayout());
        initComponent();

    }

    public void addCity(CityModel city) {
        if (city != null) {
            this.cities.add(city);
            this.repaint();
        } else {
            throw new NullPointerException("MapModel can not add a null CityModel");
        }
    }

    /**
     *
     * @param l : listener to add
     */
    public void addListener(CreationListener l) {
        this.observable.addListener(l);
    }

    /**
     *
     * @param l : listener to add
     */
    public void removeListener(CreationListener l) {
        this.observable.removeListener(l);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof JPanel) {
            JPanel source = (JPanel) e.getSource();
            int scale = (source.getWidth() / 100 > 35) ? 35 : source.getWidth() / 100;
            this.addCity(new CityModel(e.getX(), e.getY(), scale));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.fireMapCreatedEvent();
    }
}
