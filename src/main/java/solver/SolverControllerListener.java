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
public interface SolverControllerListener extends Listener {

    public void startSolver();

    public void stopSolver();

    public void stepSolver();

    public void pauseSolver();

    public void resetSolver();
}
