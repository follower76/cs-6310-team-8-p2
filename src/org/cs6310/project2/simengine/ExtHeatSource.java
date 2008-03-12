package org.cs6310.project2.simengine;

/**
 * Abstract heat source that creates heat.
 */
public abstract class ExtHeatSource {
	
	protected int rows,cols;
	protected double time;

	/** Get the amount of heat added to a particular row and 	
         column. Called from performStep in SimGrid */
	abstract double getHeatAt(int row, int col);
   
	/** called from setParameters in SimModel */
	void setSize(int rows, int cols){
		this.rows = rows;
		this.cols = cols;
	}
	
	/** called from simulate() In SimModel */
      void updateTime(double time){
    	  this.time = time;
      }
}

	
