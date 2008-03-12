package org.cs6310.project2.simengine;

/** Abstract interface for a model.
 * 
 * @author Aliva Pattnaik
 *
 */
public abstract class SimModel {
	
	private SimGrid grid;
    private ExtHeatSource extSource;
    private double timeStep;
    private double startTimeSimulation;
    private long startTimeWall;
    private long elapsedTime;
    private double time;

    /**
     * Constructor for SimModel which creates the grid, lattice, 
     * heat source for this model
     */
    public SimModel(){
    	grid = createGrid();
		SimLattice lattice = createLattice();
		extSource = createExtHeatSurce();
		grid.setLattice(lattice);
		grid.setExtHeatSource(extSource);
    }

    /** factory methods to create grid, lattice and heat 
     * 	source. Called from the SimModel()
     */
    protected abstract SimGrid createGrid();
    protected abstract SimLattice  createLattice();
    protected abstract ExtHeatSource createExtHeatSurce();
    
    /** 
    gridSize - value from 1 to 180, degrees between lines 	of 
    lat and long
    timestep - by which to alter data, between 1 and 1440 in minutes
    dissipationrate Ð rate at which heat is radiated from the earth
    */
	public void setParameters(int gridSize, int timestep, double dissipationrate){
		
		int rows = 180/gridSize;
		int cols = 360/gridSize;
		grid.setSize(rows, cols);
		grid.setDissipationRate(dissipationrate);
		grid.initializeGrid();
		this.timeStep = timestep;
		extSource.setSize(rows, cols);
	}
	
    public SimGrid getGrid(){
    	return this.grid;
    }

	/**
    * Starts a simulation from its current state.
	*/
	public void startSimulation(){
		 startTimeSimulation = time;
		 startTimeWall = System.currentTimeMillis();
		 this.simulate();
	}

   	/**
    * pause a simulation
    */
	public void pauseSimulation(){
		measureTime();
	}

	/**
    * stop a simulation
    */
	public void stopSimulation(){
		measureTime();
	}

   	/**
    * Measure elapsed time.
    */
	public void measureTime(){
		 double elapsedSimulation = time - startTimeSimulation;
	     long elapsedWall = System.currentTimeMillis() - startTimeWall;
	     System.out.print("total Time is -" + elapsedSimulation);
	}
        
	/**
    * Get the current simulated time.
    * @return simulation time since start (in minutes)
    */
	public double getTime(){
		return this.time;
		
	}
 
    /** keeps simulating until paused or stopped
    */
     private void simulate(){
    	 extSource.updateTime(time);
         grid.performStep(timeStep);
         time += timeStep;    	 
     }

}
