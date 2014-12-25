/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.strings;

import launcher.Launcher;
import operator.selection.FortuneWheelSelection;
import operator.selection.RankSelection;
import operator.selection.TournamentSelection;
import problem.ProblemFactory;
import solver.GeneticAlgorithmSolver;
import termination.GenerationNumber;
import termination.SpentTime;

/**
 *
 * @author group12
 */
public class RandomString extends Launcher<RandomStringProblem> {

    public static String problemPrefix = "StringProblem";
    public final static int DEFAULT_RANDOMSTRING_POPULATION_SIZE = 100;
    
    @Override
    protected void init(RandomStringProblem problem) {
        GeneticAlgorithmSolver geneticAlgorithmSolver = new GeneticAlgorithmSolver(problem);
        
        geneticAlgorithmSolver.setEvaluator(new RandomStringFitnessFunction(problem.getTargetString()));
        geneticAlgorithmSolver.setSelectionOperator(new TournamentSelection(0.7, 0.6));

        geneticAlgorithmSolver.addStopCriteria(new GenerationNumber(50));
        geneticAlgorithmSolver.addStopCriteria(new SpentTime(1000));
        geneticAlgorithmSolver.addEvolutionOperators(new RandomStringCrossover(1));
        geneticAlgorithmSolver.addEvolutionOperators(new RandomStringMutation());

        geneticAlgorithmSolver.addAvailabeStopCriteria(RankSelection.class);
        geneticAlgorithmSolver.addAvailabeStopCriteria(FortuneWheelSelection.class);
        geneticAlgorithmSolver.addAvailabeStopCriteria(TournamentSelection.class);
        geneticAlgorithmSolver.addAvailabeStopCriteria(RandomStringCrossover.class);
        geneticAlgorithmSolver.addAvailabeStopCriteria(RandomStringMutation.class);
        geneticAlgorithmSolver.addAvailabeStopCriteria(SpentTime.class);
        geneticAlgorithmSolver.addAvailabeStopCriteria(GenerationNumber.class);
    }
    
    public RandomString(int populationSize, String factoryName) {
        super(populationSize, factoryName);
    }
    
    public RandomString(int populationSize) {
        this(populationSize, null);
    }

    public RandomString() {
        this(RandomString.DEFAULT_RANDOMSTRING_POPULATION_SIZE);
    }

    @Override
    public String getProblemPrefix() {
        return RandomString.problemPrefix;
    }

    @Override
    public RandomStringProblem getDefaultProblem() {
        return new RandomStringProblem("qzdqzd");
    }

    @Override
    public ProblemFactory<RandomStringProblem> getProblemFactory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
