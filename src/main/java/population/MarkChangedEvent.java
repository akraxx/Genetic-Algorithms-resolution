/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package population;

import common.Event;
import population.Being;

/**
 *
 * @author group12
 */
public class MarkChangedEvent extends Event {

    private double oldMark;
    private Being being;

    public MarkChangedEvent(Object source, double oldMark, Being being) {
        super(source);
        if (being != null) {
            this.oldMark = oldMark;
            this.being = being;
        } else {
            throw new NullPointerException("MarkChangedEvent can not have a null being");
        }
    }

    public Being getBeing() {
        return being;
    }

    public double getOldMark() {
        return oldMark;
    }
}
