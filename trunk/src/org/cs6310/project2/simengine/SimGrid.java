package org.cs6310.project2.simengine;

/**
 * @author Aliva Pattnaik 
 */
/**
 * Abstract grid for simulation.  This is the lower implementation layer
 * below Model.
 */

public abstract class SimGrid {
	
	protected SimLattice lattice;
	protected ExtHeatSource extSource;
	protected int rows, cols;
	protected double dissipationRate;
	
     /** sets the temp of a particular cell defined by r and c
     */
	public abstract double getTempAt(int r, int c);
	
	/** sets the temp of a particular cell defined by r and c
     */
  	public abstract void setTempAt(int r, int c, double v);
     
	/** gets the cell temperatures of the whole grid as a 2D 
         array of doubles.
     */
	public abstract double[][] getGridTemp();
     
     /** performs one step of the simulation. Called from
         simulate() in SimModel
	 */
     abstract void performStep(double timeStep);

     /** called from setParameters of SimModel
     */
	void setDissipationRate(double newRate){
		dissipationRate = newRate;
	}
	
	/** called from setParameters of SimModel
     */
	void setSize(int rows, int cols){
		this.rows = rows;
		this.cols = cols;
		lattice.setSize(rows, cols);
		
	}
	
     /** allocates memory for grid and initializes with      
         default values. Called from setSize.
     */
	protected abstract void initializeGrid();     
 
	/** called from the SimModel()
      */
     void setLattice(SimLattice lattice){
    	 this.lattice = lattice;
     } 

	/** called from the SimModel()
      */
     void setExtHeatSource(ExtHeatSource heatSource){
    	 this.extSource = heatSource;
     } 
}
	
	
	
	
	
	
