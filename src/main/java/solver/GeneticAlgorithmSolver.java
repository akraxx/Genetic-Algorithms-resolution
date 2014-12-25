/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

import annotations.UtilsAnnotation;
import common.Observable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logger.GeneralLogger;
import operator.evaluation.FitnessEvaluator;
import operator.evolution.AbstractEvolutionaryOperator;
import operator.selection.AbstractSelectionOperator;
import operator.selection.SelectionOperatorInterface;
import population.Being;
import population.Population;
import problem.Problem;
import termination.AbstractTerminateCondition;
import termination.TerminateConditionInterface;

/**
 *
 * @author group12
 */
public class GeneticAlgorithmSolver<T extends Being> implements SolverControllerListener, SolverParameterListener, Runnable {

    public static long DEFAULT_TIME_TO_WAIT = 100;
    private Problem<T> problem;
    private long elapsedTime;
    private int numberOfGenerations;
    private long timeToWait;
    boolean running = false;
    private Observable<SolverListener<T>> observable = new Observable<>();
    private Observable<SolverParameterResponseListener> parametersResponseObservable = new Observable<>();
    
    private FitnessEvaluator<T> evaluator;
    private SelectionOperatorInterface<T> selectionOperator;
    private List<TerminateConditionInterface<T> > stopCriterias = new LinkedList<>();
    private List<AbstractEvolutionaryOperator<T> > evolutionOperators = new LinkedList<>();
    private List<Class<? extends AbstractEvolutionaryOperator<T> >> availableEvolutionnaryOperators = new LinkedList<>();
    private List<Class<? extends SelectionOperatorInterface >> availableSelectionOperators = new LinkedList<>();
    private List<Class<? extends TerminateConditionInterface >> availableStopCriterias = new LinkedList<>();
    
    private void fireParametersAddedEvent(boolean treated, Object controller) {
        for (SolverParameterResponseListener l : this.parametersResponseObservable.getListeners()) {
            l.parameterAdded(new SolverParameterResponseEvent(this, treated, controller));
        }
    }

    private void fireParametersRemovedEvent(boolean treated, Object controller) {
        for (SolverParameterResponseListener l : this.parametersResponseObservable.getListeners()) {
            l.parameterRemoved(new SolverParameterResponseEvent(this, treated, controller));
        }
    }
    
    private void fireSelectionOperatorNotChangedEvent() {
        for (SolverParameterResponseListener l : this.parametersResponseObservable.getListeners()) {
            l.selectionOperatorNotChanged(new SelectionOperatorNotChangedEvent(l, selectionOperator));
        }
    }

    public FitnessEvaluator<T> getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(FitnessEvaluator<T> evaluator) {
        this.evaluator = evaluator;
        this.resetSolver();
    }
    
    public void setSelectionOperator(SelectionOperatorInterface<T> selectionOperator) {
        this.selectionOperator = selectionOperator;
    }

    public SelectionOperatorInterface<T> getSelectionOperator() {
        return selectionOperator;
    }
    
    public void addAvailabeEvolutionaryOperator(Class<? extends AbstractEvolutionaryOperator<T> > choice) {
        if (choice != null) {
            this.availableEvolutionnaryOperators.add(choice);
        } else {
            throw new NullPointerException("an available choice condition can not be null");
        }
    }

    public void removeAvailableEvolutionaryOperator(Class<? extends AbstractEvolutionaryOperator<T> > choice) {
        if (choice != null) {
            this.availableEvolutionnaryOperators.remove(choice);
        } else {
            throw new NullPointerException("an available choice condition can not be null");
        }
    }
    
    public void addAvailabeStopCriteria(Class<? extends TerminateConditionInterface> choice) {
        if (choice != null) {
            this.availableStopCriterias.add(choice);
        } else {
            throw new NullPointerException("an available choice condition can not be null");
        }
    }

    public void removeAvailableStopCriteria(Class<? extends  TerminateConditionInterface> choice) {
        if (choice != null) {
            this.availableStopCriterias.remove(choice);
        } else {
            throw new NullPointerException("an available choice condition can not be null");
        }
    }
    
    public void addAvailabeSelectionOperator(Class<? extends SelectionOperatorInterface> choice) {
        if (choice != null) {
            this.availableSelectionOperators.add(choice);
        } else {
            throw new NullPointerException("an available choice condition can not be null");
        }
    }

    public void removeAvailableSelectionOperator(Class<? extends  SelectionOperatorInterface> choice) {
        if (choice != null) {
            this.availableSelectionOperators.remove(choice);
        } else {
            throw new NullPointerException("an available choice condition can not be null");
        }
    }
    
    public void addEvolutionOperators(AbstractEvolutionaryOperator<T> evolutionOperator) {
        if (evolutionOperator != null) {
            this.evolutionOperators.add(evolutionOperator);
        } else {
            throw new NullPointerException("an evolution operator can not be null");
        }
    }

    public void removeEvolutionOperators(AbstractEvolutionaryOperator<T> evolutionOperator) {
        if (evolutionOperator != null && this.evolutionOperators.contains(evolutionOperator)) {
            this.evolutionOperators.remove(evolutionOperator);
        } else {
            throw new NullPointerException("Unable to remove this evolution operator");
        }
    }
    
    public void addStopCriteria(TerminateConditionInterface<T> terminateCondition) {
        if (terminateCondition != null) {
            this.stopCriterias.add(terminateCondition);
        } else {
            throw new NullPointerException("a terminate condition can not be null");
        }
    }

    public void removeStopCriteria(TerminateConditionInterface<T> terminateCondition) {
        if (terminateCondition != null) {
            this.stopCriterias.remove(terminateCondition);
        } else {
            throw new NullPointerException("a terminate condition can not be null");
        }
    }

    public List<TerminateConditionInterface<T>> getStopCriterias() {
        return stopCriterias;
    }
    
    public List<AbstractEvolutionaryOperator<T>> getEvolutionOperators() {
        return evolutionOperators;
    }

    public List<Class<? extends AbstractEvolutionaryOperator<T>>> getAvailableEvolutionnaryOperators() {
        return availableEvolutionnaryOperators;
    }

    public List<Class<? extends SelectionOperatorInterface>> getAvailableSelectionOperators() {
        return availableSelectionOperators;
    }

    public List<Class<? extends TerminateConditionInterface>> getAvailableStopCriterias() {
        return availableStopCriterias;
    }
    
    private boolean isProblemRunnable() {
        boolean runnable = false;
        if (this.problem != null && this.problem.getPopulation() != null && this.getSelectionOperator() != null && this.getEvaluator() != null) {
            runnable = true;
        }

        return runnable;
    }

    private void fireUpdateChangedEvent() {
        for (SolverListener l : this.observable.getListeners()) {
            l.update(new UpdateEvent(this, this.problem.getPopulation(), this.numberOfGenerations));
        }
    }

    private void turnSolver() {

        while (this.running) {
            this.actionSolver();
        }
    }

    private boolean isFinished(List<TerminateConditionInterface<T> > terminateConditions, Statistics statistics) {
        boolean finished = false;
        Iterator<TerminateConditionInterface<T> > iterator = terminateConditions.iterator();

        while (iterator.hasNext() && !finished) {
            finished = iterator.next().isFinished(statistics);
        }

        return finished;
    }

    private Statistics generateStatistics() {
        double averageFitness = this.getProblem().getPopulation().getTotalFitness() / this.problem.getPopulation().getSize();
        return new Statistics(averageFitness,
                this.getNumberOfGenerations(),
                this.elapsedTime);
    }

    private void actionSolver() {
        try {
            if (this.isProblemRunnable()) {
                long startTime = System.nanoTime();
                this.incrementeNumberOfGenerations();
                Population initialPopulation = this.problem.getPopulation();

                Population selectedPopulation = this.getSelectionOperator().selectPopulation(initialPopulation);
                List<AbstractEvolutionaryOperator<T>> evolutionaryOperators = this.getEvolutionOperators();

                for (AbstractEvolutionaryOperator evolutionaryOperator : evolutionaryOperators) {
                    selectedPopulation = evolutionaryOperator.apply(selectedPopulation);
                }

                initialPopulation.addBeings(selectedPopulation.getBeings());

                this.getEvaluator().evaluatePopulation(initialPopulation);
                initialPopulation.updateBestFitness();

                Statistics statistics = this.generateStatistics();
                this.fireUpdateChangedEvent();

                long stopTime = System.nanoTime();

                this.elapsedTime += (stopTime - startTime);
                if (this.isFinished(this.getStopCriterias(), statistics)) {
                    this.running = false;
                }

                Thread.sleep(this.timeToWait);
            } else {
                this.running = false;
                GeneralLogger.logger.log(Level.SEVERE, "Problem can be solved, some parameters are missing");
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(GeneticAlgorithmSolver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public GeneticAlgorithmSolver(Problem<T> problem, long timeToWait) {
        this.problem = problem;
        this.timeToWait = timeToWait;
        this.resetSolver();
    }

    public GeneticAlgorithmSolver(Problem problem) {
        this(problem, GeneticAlgorithmSolver.DEFAULT_TIME_TO_WAIT);
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public Problem getProblem() {
        return problem;
    }

    public int getNumberOfGenerations() {
        return numberOfGenerations;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public void setNumberOfGenerations(int numberOfGenerations) {
        this.numberOfGenerations = numberOfGenerations;

    }

    public void incrementeNumberOfGenerations() {
        this.setNumberOfGenerations(++this.numberOfGenerations);
    }

    @Override
    public void startSolver() {
        Thread thread = new Thread(this);
        thread.start();
    }

    private void initSolver() {
        this.running = true;
        this.turnSolver();
    }

    @Override
    public void stepSolver() {
        this.running = false;
        this.actionSolver();
    }

    @Override
    public void pauseSolver() {
        this.running = false;
    }

    @Override
    public void stopSolver() {
        this.pauseSolver();
        this.resetSolver();
    }

    @Override
    public final void resetSolver() {
        this.elapsedTime = 0;
        this.setNumberOfGenerations(0);

        this.setUp();

        this.fireUpdateChangedEvent();
        
    }

    @Override
    public void run() {
        this.initSolver();
    }
    
    public void setUp() {
        if (this.isProblemRunnable()) {
            this.problem.setPopulation(this.problem.getInitialPopulation().clone());
            this.getEvaluator().evaluatePopulation(this.problem.getPopulation());
            this.problem.getPopulation().updateBestFitness();
        }
    }
    /**
     *
     * @param l : listener to add
     */
    public void addListener(SolverListener<T> l) {
        this.observable.addListener(l);
    }

    /**
     *
     * @param l : listener to add
     */
    public void removeListener(SolverListener<T> l) {
        this.observable.removeListener(l);
    }
    
    /**
     *
     * @param l : listener to add
     */
    public void addParameterResponseListener(SolverParameterResponseListener l) {
        this.parametersResponseObservable.addListener(l);
    }

    /**
     *
     * @param l : listener to add
     */
    public void removeParameterResponseListener(SolverParameterResponseListener l) {
        this.parametersResponseObservable.removeListener(l);
    }

    @Override
    public void parameterAdded(SolverParameterEvent e) {
        boolean treated = false;
        Object parameter = e.getParameter();
        if (parameter instanceof AbstractTerminateCondition) {
            if (!UtilsAnnotation.containClass(stopCriterias, parameter.getClass())) {
                this.addStopCriteria((TerminateConditionInterface<T>) parameter);

                String message = "Stop criteria added : " + parameter.toString();
                GeneralLogger.logger.log(Level.OFF, message);
                treated = true;
            }
        } else if (parameter instanceof AbstractSelectionOperator) {
            if (this.selectionOperator == null || !this.selectionOperator.getClass().equals(parameter.getClass())) {
                this.setSelectionOperator((SelectionOperatorInterface<T>) parameter);

                String message = "Selection operator changed : " + parameter.toString();
                GeneralLogger.logger.log(Level.OFF, message);
                treated = true;
            }
        } else if (parameter instanceof AbstractEvolutionaryOperator) {
            if (!UtilsAnnotation.containClass(evolutionOperators, parameter.getClass())) {
                this.addEvolutionOperators((AbstractEvolutionaryOperator<T>) parameter);

                String message = "Evolutionary operator added : " + parameter.getClass().getSimpleName();
                GeneralLogger.logger.log(Level.OFF, message);
                treated = true;
            }
        }

        this.fireParametersAddedEvent(treated, e.getController());
    }

    @Override
    public void parameterRemoved(SolverParameterEvent e) {
        boolean treated = false;
        Object parameter = e.getParameter();

        if (parameter instanceof AbstractTerminateCondition) {
            if (UtilsAnnotation.containClass(stopCriterias, parameter.getClass())) {
                UtilsAnnotation.removeElementsByClass(stopCriterias, parameter.getClass());

                String message = "Stop criteria removed : " + parameter.getClass().getSimpleName();
                GeneralLogger.logger.log(Level.OFF, message);
                treated = true;
            }
        } else if (parameter instanceof AbstractEvolutionaryOperator) {
            if (UtilsAnnotation.containClass(evolutionOperators, parameter.getClass())) {
                UtilsAnnotation.removeElementsByClass(evolutionOperators, parameter.getClass());

                String message = "Evolutionary operator removed : " + parameter.getClass().getSimpleName();
                GeneralLogger.logger.log(Level.OFF, message);
                treated = true;
            }
        }

        this.fireParametersRemovedEvent(treated, e.getController());
    }

    @Override
    public void selectionOperatorNotChanged() {
        this.fireSelectionOperatorNotChangedEvent();
    }
}
