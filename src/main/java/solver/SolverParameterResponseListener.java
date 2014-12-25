/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import common.Listener;

/**
 *
 * @author group12
 */
public interface SolverParameterResponseListener extends Listener {

    public void parameterAdded(SolverParameterResponseEvent e);

    public void parameterRemoved(SolverParameterResponseEvent e);
    
    public void selectionOperatorNotChanged(SelectionOperatorNotChangedEvent e);
}
