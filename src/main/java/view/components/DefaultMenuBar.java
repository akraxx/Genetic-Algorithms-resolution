/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.components;

import annotations.ClassDescription;
import annotations.RequiredParameterAnnotation;
import annotations.UtilsAnnotation;
import common.Observable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import logger.GeneralLogger;
import operator.evolution.AbstractEvolutionaryOperator;
import operator.selection.AbstractSelectionOperator;
import operator.selection.SelectionOperatorInterface;
import population.Being;
import solver.SelectionOperatorNotChangedEvent;
import solver.SolverParameterEvent;
import solver.SolverParameterListener;
import solver.SolverParameterResponseEvent;
import solver.SolverParameterResponseListener;
import termination.TerminateConditionInterface;

/**
 *
 * @author group12
 */
public class DefaultMenuBar<T extends Being> implements ActionListener, SolverParameterResponseListener {

    private JMenuBar jMenuBar = new JMenuBar();
    private List<ButtonGroup> buttonGroups = new LinkedList<>();
    private Observable<SolverParameterListener> observable = new Observable<>();

    private void fireParametersAddedEvent(Object parameter, JMenuItem controller) {
        for (SolverParameterListener l : this.observable.getListeners()) {
            l.parameterAdded(new SolverParameterEvent(this, parameter, controller));
        }
    }

    private void fireParametersRemovedEvent(Object parameter, JMenuItem controller) {
        for (SolverParameterListener l : this.observable.getListeners()) {
            l.parameterRemoved(new SolverParameterEvent(this, parameter, controller));
        }
    }
    
    private void fireSelectionOperatorNotChangedEvent() {
        for (SolverParameterListener l : this.observable.getListeners()) {
            l.selectionOperatorNotChanged();
        }
    }
    
    private void constructMenuItem(JMenuItem jMenuItem, Class availableChoice) {
        ClassDescription evolutionaryOperatorAnnotation = (ClassDescription) availableChoice.getAnnotation(ClassDescription.class);
        jMenuItem.setText(evolutionaryOperatorAnnotation.name());
        jMenuItem.setName(availableChoice.getName());
        jMenuItem.addActionListener(this);
    }

    private void constructRadioButtonMenu(String name, List<Class> availableChoices, Object selected) {
        ButtonGroup group = new ButtonGroup();
        JMenu jMenu = new JMenu(name);

        for (Class c : availableChoices) {
            JRadioButtonMenuItem jMenuItem = new JRadioButtonMenuItem();
            this.constructMenuItem(jMenuItem, c);

            if (selected!= null && selected.getClass().equals(c)) {
                jMenuItem.setSelected(true);
            }

            jMenu.add(jMenuItem);
            group.add(jMenuItem);
        }

        this.jMenuBar.add(jMenu);
        this.buttonGroups.add(group);
    }

    private void constructCheckBoxMenu(String name, List<Class> availableChoices, List<?> selected) {
        JMenu jMenu = new JMenu(name);

        for (Class c : availableChoices) {
            JCheckBoxMenuItem jMenuItem = new JCheckBoxMenuItem();

            this.constructMenuItem(jMenuItem, c);

            for (Object object : selected) {
                if (object.getClass().equals(c)) {
                    jMenuItem.setState(true);
                }
            }

            jMenu.add(jMenuItem);
        }

        this.jMenuBar.add(jMenu);
    }
    
    public DefaultMenuBar(  List<Class> availableEvolutionnaryOperators, List<AbstractEvolutionaryOperator<T> > evolutionOperators,
                            List<Class> availableStopCriterias, List<TerminateConditionInterface<T> > stopCriterias,
                            List<Class> availableSelectionOperators, SelectionOperatorInterface selectionOperator
                            ) {
        this.constructCheckBoxMenu("Evolutionary Operators", availableEvolutionnaryOperators, evolutionOperators);
        this.constructCheckBoxMenu("Terminate Conditions", availableStopCriterias, stopCriterias);
        this.constructRadioButtonMenu(AbstractSelectionOperator.CLASS_DESCRIPTION, availableSelectionOperators, selectionOperator);
    }

    public JMenuBar getJMenuBar() {
        return jMenuBar;
    }

    /**
     *
     * @param l : listener to add
     */
    public void addListener(SolverParameterListener l) {
        this.observable.addListener(l);
    }

    /**
     *
     * @param l : listener to add
     */
    public void removeListener(SolverParameterListener l) {
        this.observable.removeListener(l);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JMenuItem) {
            try {
                JMenuItem jMenuItem = (JMenuItem) e.getSource();
                Class c = Class.forName(jMenuItem.getName());


                Object instance = c.newInstance();

                // Remove element
                if ((jMenuItem instanceof JCheckBoxMenuItem) && !((JCheckBoxMenuItem) e.getSource()).isSelected()) {
                    this.fireParametersRemovedEvent(instance, jMenuItem);
                } // Add element
                else {
                    
                    UtilsAnnotation.createClassByRequiredParameters(instance);

                    this.fireParametersAddedEvent(instance, jMenuItem);
                }


            } catch (Exception exc) {
                GeneralLogger.logger.log(Level.OFF, "Error : parameter can not be set [ " + exc.getMessage() + " ]");
                if (e.getSource() instanceof JRadioButtonMenuItem ) {
                    this.fireSelectionOperatorNotChangedEvent();
                } else if (e.getSource() instanceof JCheckBoxMenuItem) {
                    ((JCheckBoxMenuItem) e.getSource()).setSelected(false);
                }
            }

        }
    }

    private void parameterChanged(JMenuItem jMenuItem, boolean treated, boolean add) {
        if (jMenuItem instanceof JCheckBoxMenuItem) {
            JCheckBoxMenuItem jCheckBoxMenuItem = (JCheckBoxMenuItem) jMenuItem;
            if (treated) {
                jCheckBoxMenuItem.setSelected(add);
            } else {
                jCheckBoxMenuItem.setSelected(!add);
            }
        } else if (jMenuItem instanceof JRadioButtonMenuItem) {
            JRadioButtonMenuItem jRadioButtonMenuItem = (JRadioButtonMenuItem) jMenuItem;
            if (treated) {
                jRadioButtonMenuItem.setSelected(add);
            } else {
                jRadioButtonMenuItem.setSelected(!add);
            }
        }
    }
    
    @Override
    public void parameterAdded(SolverParameterResponseEvent e) {
        if (e != null && e.getController() instanceof JMenuItem) {
            this.parameterChanged((JMenuItem) e.getController(), e.isTreated(), true);
        }
    }

    @Override
    public void parameterRemoved(SolverParameterResponseEvent e) {
        if (e != null && e.getController() instanceof JMenuItem) {
            this.parameterChanged((JMenuItem) e.getController(), e.isTreated(), false);
        }
    }

    @Override
    public void selectionOperatorNotChanged(SelectionOperatorNotChangedEvent e) {
        if (e != null && e.getOperator() != null) {
            SelectionOperatorInterface selectionOperatorInterface = e.getOperator();
            Iterator<ButtonGroup> iterator = this.buttonGroups.iterator();
            boolean found = false;
            while (iterator.hasNext() && !found) {
                ButtonGroup buttonGroup = iterator.next();
                Enumeration<AbstractButton> elements = buttonGroup.getElements();
                while (elements.hasMoreElements() && !found) {
                    AbstractButton nextElement = elements.nextElement();
                    found = nextElement.getName().equals(selectionOperatorInterface.getClass().getName());
                    if (found && nextElement instanceof JRadioButtonMenuItem) {
                        ((JRadioButtonMenuItem) nextElement).setSelected(true);
                    }
                }
            }
        }
    }
}
