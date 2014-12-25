/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.components;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import logger.GeneralLogger;
import problem.Problem;
import solver.GeneticAlgorithmSolver;
import solver.SolverControllerListener;

/**
 *
 * @author group12
 */
public class DefaultView extends JFrame implements WindowListener{
    private SolverControllerListener listener;
    private JComponent container;

    public DefaultView(Problem problem, GeneticAlgorithmSolver geneticAlgorithmSolver, JComponent container) {
        this.container = container;
        this.listener = geneticAlgorithmSolver;
        
        this.setSize(1000, 800);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(this);
        this.setLayout(new BorderLayout(10, 10));

        DefaultMenuBar defaultMenuBar = new DefaultMenuBar(geneticAlgorithmSolver.getAvailableEvolutionnaryOperators(),
                                                                        geneticAlgorithmSolver.getEvolutionOperators(),
                                                                        geneticAlgorithmSolver.getAvailableStopCriterias(),
                                                                        geneticAlgorithmSolver.getStopCriterias(),
                                                                        geneticAlgorithmSolver.getAvailableSelectionOperators(),
                                                                        geneticAlgorithmSolver.getSelectionOperator());
        
        defaultMenuBar.addListener(geneticAlgorithmSolver);
        geneticAlgorithmSolver.addParameterResponseListener(defaultMenuBar);
        
        DefaultControllerView solverViewController = new DefaultControllerView();
        solverViewController.addListener(geneticAlgorithmSolver);

        this.add(solverViewController, BorderLayout.WEST);

        DefaultGenerationsView solverViewBottom = new DefaultGenerationsView();
        geneticAlgorithmSolver.addListener(solverViewBottom);

        this.add(solverViewBottom, BorderLayout.SOUTH);

        DefaultLogView defaultLogView = new DefaultLogView(5, 60);
        GeneralLogger.generalHandler.addListener(defaultLogView);

        this.add(defaultLogView, BorderLayout.NORTH);

        this.add(this.container, BorderLayout.CENTER);


        this.setJMenuBar(defaultMenuBar.getJMenuBar());

        this.revalidate();
    }

    public JComponent getContainer() {
        return container;
    }

    public void setContainer(JComponent container) {
        this.remove(this.container);
        this.container = container;
        this.add(this.container, BorderLayout.CENTER);
        this.revalidate();
    }

    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        
    }

    @Override
    public void windowClosed(WindowEvent e) {
        if(this.listener != null) {
            this.listener.stopSolver();
        }
    }

    @Override
    public void windowIconified(WindowEvent e) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        
    }
}
