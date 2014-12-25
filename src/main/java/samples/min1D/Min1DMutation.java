/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.min1D;

import annotations.ClassDescription;
import operator.evolution.AbstractMutation;

/**
 *
 * @author group12
 */
@ClassDescription(name = "Min1D default mutation", description = "default mutation")
public class Min1DMutation extends AbstractMutation<Min1DBeing> {

    public Min1DMutation() {
    }
    
    public Min1DMutation(double mutationRate) {
        super(mutationRate);
    }

    @Override
    public Min1DBeing mutate(Min1DBeing target) {
        double x1 = target.getX();
        double offset = Math.random() / 10;
        double x2 = (Math.random() <= 0.5) ? (1 - offset)*x1 : (1 + offset)*x1;
        
        return new Min1DBeing(x2);
    }
    
}
