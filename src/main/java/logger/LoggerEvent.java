/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logger;

import common.Event;
import java.util.logging.LogRecord;

/**
 *
 * @author group12
 */
public class LoggerEvent extends Event {

    LogRecord record;

    public LoggerEvent(Object source, LogRecord record) {
        super(source);
        if (record != null) {
            this.record = record;
        } else {
            throw new NullPointerException("LogRecord of LoggerEvent can not be null");
        }
    }

    public LogRecord getRecord() {
        return record;
    }
}
