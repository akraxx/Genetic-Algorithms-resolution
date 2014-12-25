/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher;

import annotations.RequiredParameterAnnotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import problem.Problem;
import problem.ProblemFactory;

/**
 *
 * @author group12
 */
public abstract class Launcher<T extends Problem> {
    
    @RequiredParameterAnnotation(description = "Choose the population size", type = "Number > 0")
    private int populationSize;
    
    private String factoryName;
    
    protected abstract void init(T problem);
    
    public void lauch() {
        try {
            T problem = null;
            if (this.factoryName != null && this.factoryName != "") {
                problem = this.generateProblemFromFactory(this.factoryName + this.getProblemPrefix());
            } else {
                problem = this.getDefaultProblem();
            }

            if (problem != null) {
                this.init(problem);
            } else {
                throw new IllegalArgumentException("Problem can not be set");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Launcher(int populationSize, String factoryName) {
        this.populationSize = populationSize;
        this.factoryName = factoryName;
    }
    
    public abstract String getProblemPrefix();
    
    public abstract T getDefaultProblem();
    
    public abstract ProblemFactory<T> getProblemFactory();

    public T generateProblemFromFactory(String methodName) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ProblemFactory problemFactory = this.getProblemFactory();
        Class c = problemFactory.getClass();
        Method[] methods = c.getDeclaredMethods();
        boolean found = false;
        T problem = null;

        for (int i = 0; i < methods.length && !found; i++) {
            Method m = methods[i];
            if (m.getName().equals(methodName)) {
                problem = (T) m.invoke(problemFactory);
                found = true;
            }
        }

        return problem;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }
    
}
