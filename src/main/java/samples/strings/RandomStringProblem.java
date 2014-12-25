/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.strings;

import java.util.Map;
import java.util.Map.Entry;
import operator.selection.FortuneWheelSelection;
import operator.selection.RankSelection;
import operator.selection.TournamentSelection;
import problem.Problem;
import termination.GenerationNumber;
import termination.SpentTime;

/**
 *
 * @author group12
 */
public class RandomStringProblem extends Problem {

    private String targetString;

    public String getTargetString() {
        return targetString;
    }

    private void initStringProblem(String target) {
        if(target != null) {
            this.targetString = target;
        }
        else {
            throw new NullPointerException("RandomStringProblem can not have a null target String");
        }
    }

    public RandomStringProblem(String target, int nbIndiv, int sizeofBeings) {
        super(new RandomStringPopulationFactory(sizeofBeings), nbIndiv);
        this.initStringProblem(target);
    }

    public RandomStringProblem(RandomStringPopulationFactory factory, String target, int nbIndiv) {
        super(factory.generateInitialPopulation(nbIndiv));
        this.initStringProblem(target);
    }
    
    public RandomStringProblem(String target) {
        this.initStringProblem(target);
    }
}
