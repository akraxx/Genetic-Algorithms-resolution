/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import common.Event;

/**
 *
 * @author group12
 */
public class SolverParameterResponseEvent extends Event {

    private Object controller;
    private boolean treated;

    public SolverParameterResponseEvent(Object source, boolean treated, Object controller) {
        super(source);

        this.controller = controller;
        this.treated = treated;
    }

    public SolverParameterResponseEvent(Object source, boolean treated) {
        this(source, treated, null);
    }

    public Object getController() {
        return controller;
    }

    public boolean isTreated() {
        return treated;
    }
}
