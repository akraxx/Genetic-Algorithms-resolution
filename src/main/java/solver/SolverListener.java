/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import common.Listener;
import population.Being;

/**
 *
 * @author group12
 */
public interface SolverListener<T extends Being> extends Listener {
    
    public void update(UpdateEvent<T> e);
}
