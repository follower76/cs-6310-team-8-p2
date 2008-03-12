package org.cs6310.project2.simengine;

/**
 * Abstraction for lattice weights across the grid.
 */

public abstract class SimLattice {
	
	protected int rows, cols;
	
	/**
 	* Get the left, right, top, bottom weights into an array.     
	/** called from performStep in SimGrid */	
	abstract void getWeights(double[] wLRTB, int r, int c);

	/** called from setParameters in SimModel */
	void setSize(int rows, int cols){
		this.rows = rows;
		this.cols = cols;
	}
	
}
