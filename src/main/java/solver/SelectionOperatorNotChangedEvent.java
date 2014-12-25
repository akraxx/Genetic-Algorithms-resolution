/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import common.Event;
import operator.selection.SelectionOperatorInterface;

/**
 *
 * @author group12
 */
public class SelectionOperatorNotChangedEvent extends Event {

    private SelectionOperatorInterface operator;

    public SelectionOperatorNotChangedEvent(Object source, SelectionOperatorInterface operator) {
        super(source);
        if(operator != null) {
            this.operator = operator;
        }
        else {
            throw new NullPointerException("SelectionOperatorNotChangedEvent can not have a null selection operator");
        }
    }

    public SelectionOperatorInterface getOperator() {
        return operator;
    }
    
}
