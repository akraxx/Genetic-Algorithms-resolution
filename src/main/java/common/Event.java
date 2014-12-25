/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author group12
 */
public class Event {

    private Object source;

    /**
     *
     * @param source : the component which generated the event
     */
    public Event(Object source) {
        if (source != null) {
            this.source = source;
        } else {
            throw new NullPointerException("Source of event can not be null");
        }
    }

    /**
     *
     * @return
     */
    public Object getSource() {
        return source;
    }
}
