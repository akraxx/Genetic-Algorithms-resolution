/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logger;

import common.Listener;

/**
 *
 * @author group12
 */
public interface LoggerListener extends Listener {

    public void logAdded(LoggerEvent e);
}
