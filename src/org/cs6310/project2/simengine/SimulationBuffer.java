package org.cs6310.project2.simengine;

import java.util.*;

/*
 * SimulationBuffer is the ArrayList of double[][] elements. Implemented as a Queue.
 * Used as a buffer for the third scenario of execution.
 */
public class SimulationBuffer {
	
	private ArrayList<double[][]> bufferList;
	private static final int limitEnforced = 5;
	
	public void init() {
		bufferList = new ArrayList<double[][]>();
		
	}
	public boolean isBufferFull(){
		if ( bufferList.size() == limitEnforced){
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<double[][]> getBufferList() {
		return bufferList;
	}

	public void setBufferList(ArrayList<double[][]> bufferList) {
		this.bufferList = bufferList;
	}
	public double[][] popFromList() {
		int zero = 0;
		double[][] returnFromList = null;
		if(! isBufferEmpty()){
			returnFromList = bufferList.get(zero);
			bufferList.remove(zero);
		}
			
		return returnFromList;
		
	}
	
	public void pushIntoList(double[][] grid) {
		if(!isBufferFull()) {
			bufferList.add(grid);
		}
	}
	public boolean isBufferEmpty() {
		// Check done before getting the array from list Controller
		if (bufferList.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	

}
