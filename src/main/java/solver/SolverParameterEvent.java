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
public class SolverParameterEvent extends Event {

    private Object parameter;
    private Object controller;

    public SolverParameterEvent(Object source, Object parameter, Object controller) {
        super(source);
        if (parameter != null) {
            this.parameter = parameter;
            this.controller = controller;
        } else {
            throw new NullPointerException("ProblemEvent can not have a null parameter");
        }
    }

    public SolverParameterEvent(Object source, Object parameter) {
        this(source, parameter, null);
    }

    public Object getParameter() {
        return parameter;
    }

    public Object getController() {
        return controller;
    }
}
