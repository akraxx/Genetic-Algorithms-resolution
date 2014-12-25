/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logger;

import common.Observable;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import javax.swing.SwingUtilities;

/**
 *
 * @author group12
 */
public class GeneralHandler extends Handler {

    private Observable<LoggerListener> observable = new Observable<>();

    private void fireLogAddedEvent(LogRecord record) {
        for (LoggerListener l : this.observable.getListeners()) {
            l.logAdded(new LoggerEvent(l, record));
        }
    }

    @Override
    public void publish(final LogRecord record) {
        final GeneralHandler thiz = this;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                thiz.fireLogAddedEvent(record);
//                out.printf("[%s] [Thread-%d]: %s.%s -> %s", record.getLevel(),
//                        record.getThreadID(), record.getSourceClassName(),
//                        record.getSourceMethodName(), record.getMessage());
//                textArea.setText(text.toString());
            }
        });
    }

    /**
     *
     * @param l : listener to add
     */
    public void addListener(LoggerListener l) {
        this.observable.addListener(l);
    }

    /**
     *
     * @param l : listener to add
     */
    public void removeListener(LoggerListener l) {
        this.observable.removeListener(l);
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() throws SecurityException {
    }
}
