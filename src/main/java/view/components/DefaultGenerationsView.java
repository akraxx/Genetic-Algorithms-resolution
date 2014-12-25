/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.components;

import javax.swing.JLabel;
import javax.swing.JPanel;
import solver.UpdateEvent;
import solver.SolverListener;

/**
 *
 * @author group12
 */
public class DefaultGenerationsView extends JPanel implements SolverListener {

    private JLabel numberOfGenerationsLabel = new JLabel();

    private void setNumberOfGenerations(int i) {
        this.numberOfGenerationsLabel.setText("Number of generations : " + String.valueOf(i));
    }

    private void initComponents() {
        this.add(this.numberOfGenerationsLabel);
        this.setNumberOfGenerations(0);
    }

    public DefaultGenerationsView() {
        this.initComponents();
    }

    @Override
    public void update(UpdateEvent e) {
        if (e != null) {
            this.setNumberOfGenerations(e.getGenerationNumber());
        }
    }
}
