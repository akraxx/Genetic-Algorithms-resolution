/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.TSP;

import launcher.Launcher;
import operator.selection.FortuneWheelSelection;
import operator.selection.RankSelection;
import operator.selection.TournamentSelection;
import problem.Problem;
import problem.ProblemFactory;
import samples.min1D.Min1D;
import solver.GeneticAlgorithmSolver;
import termination.GenerationNumber;
import termination.SpentTime;
import view.components.DefaultView;

/**
 *
 * @author group12
 */
public class TSP extends Launcher<TSPProblem> implements CreationListener {

    private TSPProblem problem;
    private DefaultView defaultView;
    private TSPView tspView;
    public static String problemPrefix = "TSPProblem";
    public static final int DEFAULT_TSP_POPULATION_SIZE = 1000;
    
    @Override
    protected void init(TSPProblem problem) {
        this.problem = problem;
        GeneticAlgorithmSolver<TSPBeing> geneticAlgorithmSolver = new GeneticAlgorithmSolver<>(problem);
        
        geneticAlgorithmSolver.setEvaluator(new TSPFitnessFunction());
        geneticAlgorithmSolver.setSelectionOperator(new TournamentSelection(0.7, 0.6));
        
        geneticAlgorithmSolver.addStopCriteria(new GenerationNumber(500));
        geneticAlgorithmSolver.addEvolutionOperators(new TSPCrossOver());
        geneticAlgorithmSolver.addEvolutionOperators(new TSPMutation());

        geneticAlgorithmSolver.addAvailabeSelectionOperator(RankSelection.class);
        geneticAlgorithmSolver.addAvailabeSelectionOperator(FortuneWheelSelection.class);
        geneticAlgorithmSolver.addAvailabeSelectionOperator(TournamentSelection.class);
        geneticAlgorithmSolver.addAvailabeEvolutionaryOperator(TSPCrossOver.class);
        geneticAlgorithmSolver.addAvailabeEvolutionaryOperator(TSPMutation.class);
        geneticAlgorithmSolver.addAvailabeStopCriteria(SpentTime.class);
        geneticAlgorithmSolver.addAvailabeStopCriteria(GenerationNumber.class);
        
        CreationPanel mapView = new CreationPanel();
        mapView.addListener(this);
        defaultView = new DefaultView(problem, geneticAlgorithmSolver, mapView);
        tspView = new TSPView(800, 800);

        geneticAlgorithmSolver.addListener(tspView);
    }
    
    public TSP(int populationSize, String factoryName) {
        super(populationSize, factoryName);
    }
    
    public TSP(int populationSize) {
        this(populationSize, null);
    }

    public TSP() {
        this(TSP.DEFAULT_TSP_POPULATION_SIZE);
    }

    @Override
    public void mapCreated(MapCreatedEvent e) {
        if (e != null) {
            this.problem.mapCreated(e.getCities(), this.getPopulationSize());
            this.tspView.setDefaultHeight(e.getDefaultHeight());
            this.tspView.setDefaultWidth(e.getDefaultWidth());
            this.defaultView.setContainer(tspView);
        }
    }

    @Override
    public String getProblemPrefix() {
        return TSP.problemPrefix;
    }

    @Override
    public TSPProblem getDefaultProblem() {
        return new TSPProblem();
    }

    @Override
    public ProblemFactory<TSPProblem> getProblemFactory() {
        return new TSPProblemFactory(this.getPopulationSize());
    }
}
