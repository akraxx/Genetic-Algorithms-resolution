/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package population;

import common.Listener;
import population.MarkChangedEvent;

/**
 *
 * @author group12
 */
public interface BeingListener extends Listener {

    public void markChangedEvent(MarkChangedEvent e);
}
