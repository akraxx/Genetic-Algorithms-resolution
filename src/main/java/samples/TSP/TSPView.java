/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.TSP;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;
import solver.UpdateEvent;
import solver.SolverListener;

/**
 *
 * @author group12
 */
public class TSPView extends JPanel implements SolverListener<TSPBeing> {
    JPanel mainPanel;
    JPanel bottom = new JPanel();
    List<TSPBeingView> beingsView = new LinkedList<>();
    private int defaultHeight;
    private int defaultWidth;
    
    public TSPView(int width, int height) {
        this.setSize(width, height);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BorderLayout(10, 10));
        
        this.beingsView.add(new TSPBeingView());
        this.beingsView.add(new TSPBeingView());
        this.beingsView.add(new TSPBeingView());
        this.beingsView.add(new TSPBeingView());
        this.beingsView.add(new TSPBeingView());
        
        this.mainPanel = this.beingsView.get(0);
        this.add(mainPanel, BorderLayout.CENTER);

        mainPanel.setPreferredSize(new Dimension(width, (int)(height * 0.8)));
        bottom.setLayout(new GridLayout(1, 4, 10, 10));
        bottom.setPreferredSize(new Dimension(width, (int)(height * 0.2)));
        this.add(bottom, BorderLayout.SOUTH);
        for(int i = 1; i < 5; i++) {
            this.bottom.add(this.beingsView.get(i));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void update(UpdateEvent e) {
        if(e != null) {
            List<TSPBeing> beings = e.getPopulation().getBestBeings();
            int size = beings.size();
            if(size > 0) {
                this.beingsView.get(0).setBeing(beings.get(0));
                for(int i = 1; i < size; i++) {
                    this.beingsView.get(i).setBeing(beings.get(i));
                }
            }
            this.repaint();
        }
    }

    public void setDefaultHeight(int defaultHeight) {
        this.defaultHeight = defaultHeight;
        for(TSPBeingView beingView : this.beingsView) {
            beingView.setDefaultHeight(defaultHeight);
        }
    }

    public void setDefaultWidth(int defaultWidth) {
        this.defaultWidth = defaultWidth;
        for(TSPBeingView beingView : this.beingsView) {
            beingView.setDefaultWidth(defaultWidth);
        }
    }
    
    public int getDefaultHeight() {
        return defaultHeight;
    }

    public int getDefaultWidth() {
        return defaultWidth;
    }
}
