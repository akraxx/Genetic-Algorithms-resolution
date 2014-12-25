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
public interface SolverParameterListener extends Listener {

    public void parameterAdded(SolverParameterEvent e);

    public void parameterRemoved(SolverParameterEvent e);
    
    public void selectionOperatorNotChanged();
}
