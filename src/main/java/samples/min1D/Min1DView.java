/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.min1D;

import de.congrace.exp4j.Calculable;
import java.awt.Color;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import population.Population;
import solver.UpdateEvent;
import solver.SolverListener;

/**
 *
 * @author group12
 */
public class Min1DView implements SolverListener<Min1DBeing> {

    private static final int FUNCTION_INDEX = 0;
    private static final int BEINGS_INDEX = 1;
    private static final int BEST_BEINGS_INDEX = 2;
    private static Color functionColor = Color.BLACK;
    private static Color populationColor = new Color(0x9bfc); //blue
    private static Color bestBeingColor = new Color(0xff232b); //red
    private ChartPanel panel;
    private XYLineAndShapeRenderer renderer;
    private XYSeriesCollection valuesCollection;
    private XYSeries baseFunction = new XYSeries("Function");
    private XYSeries beingsSet = new XYSeries("Population");
    private XYSeries bestBeingsSet = new XYSeries("Fittest individual(s)");

    private void fillEquationSeries(Interval interval, Calculable function) {
        double min = interval.getMinBoundary();
        double max = interval.getMaxBoundary();
        double step = interval.getLength() / 1000;

        this.baseFunction.clear();

        for (double i = min; i < max; i += step) {
            this.baseFunction.add(i, function.calculate(i));
        }

    }

    private void fillPopulationSeries(Population population) {
        this.beingsSet.clear();
        this.bestBeingsSet.clear();
        List<Min1DBeing> beings = population.getBeings();
        List<Min1DBeing> bestBeings = population.getBestBeings();

        for (Min1DBeing being : beings) {
            double targetScore = being.getFitnessScore();
            if (bestBeings.contains(being)) {
                this.bestBeingsSet.add(being.getX(), targetScore);
            } else {
                this.beingsSet.add(being.getX(), targetScore);
            }
        }
    }

    private void initChart(Min1DProblem problem) {
        this.fillEquationSeries(problem.getInterval(), problem.getEquation());
        this.fillPopulationSeries(problem.getPopulation());

        this.valuesCollection = new XYSeriesCollection();

        this.valuesCollection.addSeries(this.baseFunction);
        this.valuesCollection.addSeries(this.beingsSet);
        this.valuesCollection.addSeries(this.bestBeingsSet);

        JFreeChart chart = ChartFactory.createXYLineChart("1D Function you typed", "X", "F(X)",
                this.valuesCollection, PlotOrientation.VERTICAL, true, true, true);
        this.panel = new ChartPanel(chart);

        this.renderer = new XYLineAndShapeRenderer(true, false);
        this.renderer.setSeriesPaint(FUNCTION_INDEX, functionColor);
        this.renderer.setSeriesPaint(BEINGS_INDEX, populationColor);
        this.renderer.setSeriesPaint(BEST_BEINGS_INDEX, bestBeingColor);

        this.renderer.setSeriesLinesVisible(FUNCTION_INDEX, true);
        chart.getXYPlot().setRenderer(this.renderer);

        this.renderer.setSeriesLinesVisible(BEINGS_INDEX, false);
        this.renderer.setSeriesShapesVisible(BEINGS_INDEX, true);

        this.renderer.setSeriesLinesVisible(BEST_BEINGS_INDEX, false);
        this.renderer.setSeriesShapesVisible(BEST_BEINGS_INDEX, true);
    }

    public Min1DView(Min1DProblem problem) {
        this.initChart(problem);
    }

    public ChartPanel getPanel() {
        return panel;
    }

    @Override
    public void update(UpdateEvent e) {
        if (e != null) {
            try {
                this.valuesCollection.removeSeries(this.beingsSet);
                this.valuesCollection.removeSeries(this.bestBeingsSet);
                this.fillPopulationSeries(e.getPopulation());

                this.valuesCollection.addSeries(this.beingsSet);
                this.valuesCollection.addSeries(this.bestBeingsSet);
                this.panel.repaint();
            } catch (Exception exc) {
                System.out.println("Software was unable to draw the chart");
            }
        }
    }
}
