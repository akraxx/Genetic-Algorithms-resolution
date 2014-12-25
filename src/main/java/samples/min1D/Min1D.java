/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.min1D;

import annotations.RequiredParameterAnnotation;
import launcher.Launcher;
import operator.selection.FortuneWheelSelection;
import operator.selection.RankSelection;
import operator.selection.TournamentSelection;
import problem.Problem;
import problem.ProblemFactory;
import samples.TSP.CreationPanel;
import samples.TSP.TSPView;
import solver.GeneticAlgorithmSolver;
import termination.GenerationNumber;
import termination.SpentTime;
import view.components.DefaultView;

/**
 *
 * @author group12
 */
public class Min1D extends Launcher<Min1DProblem> {

    public final static String problemPrefix = "Min1DProblem";
    public final static int DEFAULT_MIN1D_POPULATION_SIZE = 100;
    
    @Override
    protected void init(Min1DProblem problem) {
        GeneticAlgorithmSolver<Min1DBeing> geneticAlgorithmSolver = new GeneticAlgorithmSolver<>(problem);
        
        geneticAlgorithmSolver.setEvaluator(new Min1DFitnessFunction(problem.getEquation()));
        geneticAlgorithmSolver.setSelectionOperator(new TournamentSelection(0.7, 0.6));

        geneticAlgorithmSolver.addStopCriteria(new GenerationNumber(50));
        geneticAlgorithmSolver.addStopCriteria(new SpentTime(1000));
        geneticAlgorithmSolver.addEvolutionOperators(new Min1DCrossOver(0.6));
        geneticAlgorithmSolver.addEvolutionOperators(new Min1DMutation(0.02));

        geneticAlgorithmSolver.addAvailabeSelectionOperator(RankSelection.class);
        geneticAlgorithmSolver.addAvailabeSelectionOperator(FortuneWheelSelection.class);
        geneticAlgorithmSolver.addAvailabeSelectionOperator(TournamentSelection.class);
        geneticAlgorithmSolver.addAvailabeEvolutionaryOperator(Min1DCrossOver.class);
        geneticAlgorithmSolver.addAvailabeEvolutionaryOperator(Min1DMutation.class);
        geneticAlgorithmSolver.addAvailabeStopCriteria(SpentTime.class);
        geneticAlgorithmSolver.addAvailabeStopCriteria(GenerationNumber.class);
        
        geneticAlgorithmSolver.setUp();
        Min1DView chart = new Min1DView(problem);
        DefaultView defaultSolverView = new DefaultView(problem, geneticAlgorithmSolver, chart.getPanel());
        geneticAlgorithmSolver.addListener(chart);
    }
    
    public Min1D(int populationSize, String factoryName) {
        super(populationSize, factoryName);
    }
    
    public Min1D(int populationSize) {
        this(populationSize, null);
    }

    public Min1D() {
        this(Min1D.DEFAULT_MIN1D_POPULATION_SIZE);
    }
    
    @Override
    public Min1DProblem getDefaultProblem() {
        return new Min1DProblem("x^2 + 1", -100.0, 100.0, 100);
    }

    @Override
    public ProblemFactory<Min1DProblem> getProblemFactory() {
        return new Min1DProblemFactory(this.getPopulationSize());
    }

    @Override
    public String getProblemPrefix() {
        return Min1D.problemPrefix;
    }

    

    
}
