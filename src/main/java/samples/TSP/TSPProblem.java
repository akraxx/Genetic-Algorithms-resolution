/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samples.TSP;

import java.util.List;
import population.Population;
import problem.Problem;

/**
 *
 * @author group12
 */
public class TSPProblem extends Problem<TSPBeing> {
    
    private void initTSPProblem() {
        Population.numberOfBestBeings = 5;
    }

    public TSPProblem(List<CityModel> list, int size) {
        super(new TSPPopulationFactory(list), size);
        this.initTSPProblem();
    }

    public TSPProblem(int size, int maxWidth, int maxHeight) {
        super(new TSPPopulationFactory(size, maxWidth, maxHeight), size);
        this.initTSPProblem();
    }

    public TSPProblem(int size) {
        super(size);
        this.initTSPProblem();
    }

    public TSPProblem() {
        this.initTSPProblem();
    }
    
    public void mapCreated(List<CityModel> list, int size) {
        this.initPopulation(new TSPPopulationFactory(list), size);
    }
}
