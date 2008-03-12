package org.cs6310.project2.simengine;

public class SolarSource extends ExtHeatSource{

	private double dayLength;
	private double[] latFactor;
	private double[] longFactor;

	public SolarSource(double dayLength){
		this.dayLength = dayLength;
		
		
	}

	double getHeatAt(int r, int c){
		return this.latFactor[r] * this.longFactor[c] / 4;
	}
 	void updateTime(double time){
 		this.time = time;
 		computeLongFactor();
        computeLatFactor();
 	}

	private void computeLongFactor(){
		this.longFactor = new double[cols];
        for (int c = 0; c < cols; c++) {
            double longFactor = Math.cos(
                (((c+0.5)/cols) + (time/dayLength) % 1.0) * Math.PI * 2);
            this.longFactor[c] = longFactor < 0 ? 0 : longFactor;
        }
	}      
	private void computeLatFactor(){
		 this.latFactor = new double[rows];
	        for (int r = 0; r < rows; r++) {
	            double latFactor = Math.cos(
	                ((r+0.5)/rows - 0.5) * Math.PI);
	            this.latFactor[r] = latFactor;
	        }
	}

}	
	
	
	

