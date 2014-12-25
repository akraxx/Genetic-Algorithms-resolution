/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @param <E> : listener on a component
 * @author group12
 */
public class Observable<E extends Listener> {

    private List<E> listeners = new LinkedList<>();

    /**
     *
     * @return the component's list of listeners
     */
    public List<E> getListeners() {
        return listeners;
    }

    /**
     *
     * @param listener : the listener to add
     */
    public void addListener(E listener) {
        if (listener != null) {
            this.listeners.add(listener);
        } else {
            throw new NullPointerException("ComponentListener can not be null");
        }
    }

    /**
     *
     * @param listeners
     */
    public void addListener(List<E> listeners) {
        if (listeners != null) {
            for (E l : listeners) {
                if (l != null) {
                    this.listeners.add(l);
                } else {
                    throw new NullPointerException("Listener can not be null");
                }
            }
        } else {
            throw new NullPointerException("List<Listener> can not be null");
        }
    }

    /**
     *
     * @param listener : the listener to remove
     */
    public void removeListener(E listener) {
        if (listener != null && this.listeners.contains(listener)) {
            this.listeners.remove(listener);
        } else {
            throw new NullPointerException("ComponentListener can not be null");
        }
    }
}