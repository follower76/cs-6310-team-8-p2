package org.cs6310.project2.simengine;

import org.cs6310.project2.simengine.ExtHeatSource;
import org.cs6310.project2.simengine.SimLattice;

public class DoubleGrid extends SimGrid{

	private double newGrid[][];
	private double oldGrid[][];
	private boolean oldIsOld = true;
	private boolean firstTime = true;
	
	
	public  void setTempAt(int r, int c, double temp){
		oldGrid[r][c] = (double)temp;
	}
	public double getTempAt(int r, int c){
		return oldGrid[r][c];
	}
	public double[][] getGridTemp(){
		return oldGrid;
	}

	protected void initializeGrid(){
		newGrid = new double[rows][cols];
		oldGrid = new double[rows][cols];

		for (int i=0; i<rows; i++){
			for (int j=0; j<cols;j++){
				newGrid[i][j]=15.0;
				oldGrid[i][j]=15.0;
			}
			
		}
	}
	void performStep(double timeSteps){
		double heatFactor = Math.exp(-dissipationRate * timeSteps);
        double[] wLRTB = new double[4]; // weights for left, right, top, bottom
        double np[][]; double op[][];
		
		if(firstTime)
			for(int i = 0; i < rows; i++)
				for(int j = 0; j < cols; j++)
					newGrid[i][j] = oldGrid[i][j];
		firstTime = false;
		
		if(oldIsOld){ np = this.newGrid; op = this.oldGrid; }
		else{ np = this.oldGrid; op = this.newGrid; }
		
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                double d;
                lattice.getWeights(wLRTB, r, c);
                d = op[r][(c+cols-1)%cols] * wLRTB[0]
                  + op[r][(c+1)%cols] * wLRTB[1]
                  + op[(r+rows-1)%rows][c] * wLRTB[2]
                  + op[(r+1)%rows][c] * wLRTB[3];
                d += this.extSource.getHeatAt(r, c) * timeSteps;
                d = (d + 273.15) * heatFactor - 273.15;
                np[r][c] = d;
            }
        }
        
        oldIsOld = !oldIsOld;

	}	

}	
	
	
	
	
	
	
	
	
	