/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.min1D;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import population.Population;
import problem.Problem;

/**
 *
 * @author group12
 */
public class Min1DProblem extends Problem<Min1DBeing> {

    private Calculable equation;
    private Interval interval;

    public Interval getInterval() {
        return interval;
    }

    public Calculable getEquation() {
        return equation;
    }

    private void initMin1DProblem(String equation, Interval interval, int nbIndiv) {
        try {
            double x = 0;
            this.equation = new ExpressionBuilder(equation).withVariable("x", x).build();
            this.interval = interval;

        } catch (UnknownFunctionException | UnparsableExpressionException e) {
            System.err.println(e.getMessage());
        }
    }

    public Min1DProblem(String equation, Interval interval, int nbIndiv) {
        super(new Min1DPopulationFactory(interval), nbIndiv);
        this.initMin1DProblem(equation, interval, nbIndiv);
    }

    public Min1DProblem(String equation, Double leftBoundary, Double rightBoundary, int nbIndiv) {
        this(equation, new Interval(leftBoundary, rightBoundary), nbIndiv);
    }

    public Min1DProblem(Population population, String equation, Double leftBoundary, Double rightBoundary, int nbIndiv) {
        super(population);
        this.initMin1DProblem(equation, new Interval(leftBoundary, rightBoundary), nbIndiv);
    }
}
