/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.components;

import common.Observable;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import javax.swing.JButton;
import javax.swing.JPanel;
import solver.SolverState;
import solver.SolverControllerListener;

/**
 *
 * @author group12
 */
public class DefaultControllerView extends JPanel implements ActionListener {

    GridLayout gridLayout = new GridLayout();
    private Observable<SolverControllerListener> observable = new Observable<>();

    private void fireStateChangedEvent(String action) {
        for (SolverControllerListener l : this.observable.getListeners()) {
            String functionName = action.concat("Solver");
            Class c = l.getClass();
            try {
                Method m = c.getMethod(functionName);
                m.invoke(l);
            } catch (Exception ex) {
                System.out.println("Event can not be sended");
                ex.printStackTrace();
            }
        }
    }

    private void initButtons() {
        this.gridLayout.setRows(SolverState.values().length);
        this.gridLayout.setColumns(1);

        for (SolverState state : SolverState.values()) {
            JButton button = new JButton(state.toString());
            button.setName(state.toString());
            button.addActionListener(this);
            this.add(button);
        }
    }

    public DefaultControllerView() {
        this.setLayout(this.gridLayout);
        this.initButtons();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton jButton = (JButton) e.getSource();
            this.fireStateChangedEvent(jButton.getName().toLowerCase());
        }
    }

    /**
     *
     * @param l : listener to add
     */
    public void addListener(SolverControllerListener l) {
        this.observable.addListener(l);
    }

    /**
     *
     * @param l : listener to add
     */
    public void removeListener(SolverControllerListener l) {
        this.observable.removeListener(l);
    }
}
