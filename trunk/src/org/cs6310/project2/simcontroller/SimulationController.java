package org.cs6310.project2.simcontroller;

import org.cs6310.project2.simengine.*;
/*
 * SimulationController co-ordinates the thread execution and interaction between Model and View.
 * It is the Controller part of MVC pattern. It also follows a pipe & design structure of execution.
 * It implements Runnable interface. 
 */
public class SimulationController implements Runnable {

	private double[][] localCopyOfGrid;
	private SimulationDriver driverRef;//should be used to call pull
	private UIInteractor uiRef;
	private SimulationBuffer buffer;
	private boolean flagPush, flagPull, flagUseBuffer;

	
	private int iterationCount;//Only for testing. After 3 iterations End the program.
	
	/*
	 * run() function interacts with the UI.  
	 * It should run always after SimulationDriver is done.
	 */
	public void run() {
		System.out.println("Controller::run function Begin.");
		while(!isDone()) {
			synchronized (localCopyOfGrid) {
				while(!driverRef.isIterationDone()){
					
				}
					if(flagPush) {
						
					} else if (flagPull) {
						pull();
					} else if (flagUseBuffer) {
						popFromBuffer();
					}
					System.out.println("Controller::run function Values updated at UI.");	
				uiRef.setLatestSetOfValues(localCopyOfGrid);
				uiRef.updateLatestValuesInUI();
			}
		}
		
		System.out.println("Controller::run function End.");
	}
	
	private boolean isDone() {
		// TODO Auto-generated method stub
		iterationCount++;
		System.out.println("Controller::isDone function: iterationCount "+iterationCount);
		if ( iterationCount == 3) {
			localCopyOfGrid = DoubleGrid.getFinalDummyData();
			return true;
		}
		return false;

	}

	/*
	 * pull is the function that should be called when SimulationController 
	 * wants to get the data from SimulationDriver
	 */
	public void pull() {
		synchronized (localCopyOfGrid) {
			setLocalCopyOfGrid(driverRef.getLocalCopyOfGrid());
		}
	}

	/*
	 * Set driver reference
	 */
	public void setSimulationDriverReference(SimulationDriver driverRef)
	{
		this.driverRef = driverRef; 
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void init() {
		// Initialization Functions
		
		//Iteration Count to be checked till 3
		iterationCount = 0;
		//This is just dummy data
		localCopyOfGrid = DoubleGrid.getDummyData();
		//set the flags
		uiRef = new UIInteractor();
		
		
	}

	public double[][] getLocalCopyOfGrid() {
		return localCopyOfGrid;
	}

	public void setLocalCopyOfGrid(double[][] localCopyOfGrid) {
		this.localCopyOfGrid = localCopyOfGrid;
	}

	public void push(double[][] grid){
		//Refresh the changes onto UI.
		//updateUI(grid);
		setLocalCopyOfGrid(grid);
	}

	public void updateUI(double[][] grid){
		
	}

	public void popFromBuffer() {
		//When set in get from buffer mode
		//get from the top of buffer.
		synchronized (localCopyOfGrid) {
			while(buffer.isBufferEmpty()) {
				try {
					wait();
				} catch (InterruptedException e) {
					System.out.println("Interrupted Exception in popFromBuffer()");
					e.printStackTrace();
				}
			}
			setLocalCopyOfGrid(buffer.popFromList());
			notifyAll();
		}
	}
}
