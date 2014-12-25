/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operator.selection;

import annotations.RequiredParameterAnnotation;
import annotations.SelectionOperatorAnnotation;
import population.Being;
import population.Population;

/**
 *
 * @author group12
 */
@SelectionOperatorAnnotation
public abstract class AbstractSelectionOperator<T extends Being> implements SelectionOperatorInterface<T> {

    public static double DFAULT_PERCENT_OF_POPULATION = 0.7;
    public static String CLASS_DESCRIPTION = "Selection Operator";
    @RequiredParameterAnnotation(description = "Percent of population to select", type = "Number [0, 1]")
    protected double percentOfpopulation;

    public AbstractSelectionOperator(double percentOfpopulation) {
        if (percentOfpopulation >= 0 && percentOfpopulation <= 1) {
            this.percentOfpopulation = percentOfpopulation;
        } else {
            this.percentOfpopulation = AbstractSelectionOperator.DFAULT_PERCENT_OF_POPULATION;
        }
    }

    public AbstractSelectionOperator() {
        this(AbstractSelectionOperator.DFAULT_PERCENT_OF_POPULATION);
    }

    @Override
    public abstract Population<T> selectPopulation(Population<T> population);

    public void setPercentOfpopulation(double percentOfpopulation) {
        this.percentOfpopulation = percentOfpopulation;
    }

    public double getPercentOfpopulation() {
        return percentOfpopulation;
    }

    @Override
    public String toString() {
        return "percent of population to select : " + this.percentOfpopulation; //To change body of generated methods, choose Tools | Templates.
    }
}
