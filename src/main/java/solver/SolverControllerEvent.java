/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import common.Event;
import solver.SolverState;

/**
 *
 * @author group12
 */
public class SolverControllerEvent extends Event {

    SolverState state;

    public SolverControllerEvent(Object source, SolverState state) {
        super(source);
    }

    public SolverState getState() {
        return state;
    }
}
