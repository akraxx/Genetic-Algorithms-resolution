/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operator.selection;

import annotations.ClassDescription;
import annotations.RequiredParameterAnnotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import population.Being;
import population.Population;

/**
 *
 * @author max
 */
@ClassDescription(name = "Tournament", description = "Tournament Operator Selection")
public class TournamentSelection<T extends Being> extends AbstractSelectionOperator {

    public static String CLASS_DESCRIPTION = "Tournament Operator Selection";
    public static double DFAULT_PROPBABILITY_TO_CHOOSE_THE_BEST = 0.7;
    @RequiredParameterAnnotation(description = "Propability to choose the best beings", type = "Number [0, 1]")
    private double probabilityToChooseTheBest = 0.6;

    public TournamentSelection(double percentOfpopulation, double probabilityToChooseTheBest) {
        super(percentOfpopulation);
        if (probabilityToChooseTheBest >= 0 && probabilityToChooseTheBest <= 1) {
            this.probabilityToChooseTheBest = probabilityToChooseTheBest;
        } else {
            this.probabilityToChooseTheBest = TournamentSelection.DFAULT_PROPBABILITY_TO_CHOOSE_THE_BEST;
        }
    }

    public TournamentSelection(double probabilityToChooseTheBest) {
        if (probabilityToChooseTheBest >= 0 && probabilityToChooseTheBest <= 1) {
            this.probabilityToChooseTheBest = probabilityToChooseTheBest;
        } else {
            this.probabilityToChooseTheBest = TournamentSelection.DFAULT_PROPBABILITY_TO_CHOOSE_THE_BEST;
        }
    }

    public TournamentSelection() {
        this(TournamentSelection.DFAULT_PROPBABILITY_TO_CHOOSE_THE_BEST);
    }

    public double getProbabilityToChooseTheBest() {
        return probabilityToChooseTheBest;
    }

    public void setProbabilityToChooseTheBest(double probabilityToChooseTheBest) {
        this.probabilityToChooseTheBest = probabilityToChooseTheBest;
    }

    @Override
    public Population selectPopulation(Population population) {
        Random rnd = new Random();
        List<T> beings = population.getBeings();
        int sizeOfNewPopulation = (int) (beings.size() * this.percentOfpopulation);
        List<T> selection = new ArrayList<>(sizeOfNewPopulation);
        for (int i = 0; i < sizeOfNewPopulation; i++) {
            int size = beings.size();
            T candidate1 = beings.get(rnd.nextInt(size - 1));
            T candidate2 = beings.get(rnd.nextInt(size - 1));
            T bestCandidate = null;
            //probabilité de sélectionner le meilleur d'un duel
            double prob = Math.random();
            if (prob < this.probabilityToChooseTheBest) {
                bestCandidate = candidate2.compareTo(candidate1) > 0 ? candidate2 : candidate1;
            } else {
                bestCandidate = candidate2.compareTo(candidate1) > 0 ? candidate1 : candidate2;
            }
            selection.add(bestCandidate);
            population.removeBeing(bestCandidate);
        }

        return new Population(selection);
    }

    @Override
    public String toString() {
        return TournamentSelection.CLASS_DESCRIPTION + " [ probability to choose the best : " + this.probabilityToChooseTheBest + ", " + super.toString() + "]"; //To change body of generated methods, choose Tools | Templates.
    }
}
