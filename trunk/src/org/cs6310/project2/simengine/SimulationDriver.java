package org.cs6310.project2.simengine;

import org.cs6310.project2.simcontroller.SimulationController;

/*
 * Class Details: SimulationDriver
 * This class although is physically part of simulation engine, design-wise belongs to Controller
 * It is the Thread implemented as the Driver part of the Engine.
 * As a thread it always executes before relinquishing to the Controller. Has the function push to pass on data to Controller.
 *  
 */
public class SimulationDriver implements Runnable {

	private SimulationController ctrlRef;
	private SimulationBuffer buffer;
	private double[][] localCopyOfGrid;
	private boolean flagPush, flagPull, flagUseBuffer;
	private boolean isDone;
	private boolean isIterationDone;
	
	//Thread references
	private Thread driverThread;
	private Thread ctrlerThread;

	private int iterationCount;//Only for testing. After 3 iterations End the program.
	
	//Also has a reference to EarthImpl

	
	//Initialize & start threads
	public void initThreads() {
		setDriverThread(new Thread(this));
		setControllerThread(new Thread(this.getControllerRef()));
		
		
		driverThread.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ctrlerThread.start();

	}
	public void push()
	{
//		System.out.println("In SimulationDriver::push function: Copy the local copy of Grid into controller.");
		synchronized(localCopyOfGrid) {
			ctrlRef.push(localCopyOfGrid);			
		}

	}

	//To be set to true when the iterations are over.
	//Program ends execution when this fn returns true
	public boolean isDone() {
		iterationCount++;
		System.out.println("driver::isDone function: iterationCount "+iterationCount);
//		if ( iterationCount == 17) {
//			localCopyOfGrid = DoubleGrid.getFinalDummyData();
//			return true;
//		}
		return false;
	}
	
	public boolean isIterationDone() {
		//Should contain the code to check with Simulation Engine.
		//Returning true implies that one set of iterations can be displayed in UI.
//		System.out.println("Starting driver::isIterationDone function.");
		try {
//			System.out.println("driver::isIterationDone function Going to sleep 6s.");
			Thread.sleep(6000);
//			System.out.println("driver::isIterationDone function Coming out of sleep.");
		} catch (InterruptedException e) {
			System.out.println("InterruptedException in driver::isIterationDone function.");
			e.printStackTrace();
		}
		System.out.println("Done driver::isIterationDone function.");
		
		return true;
	}

	/**
	 * The init function implements the initialization of this class.
	 *  Initialize controller as well.
	 */
	public void init() {
		System.out.println("Starting driver::init function.");
		
		//These initialization functions are from SimulationDriver.java authored by Aliva
		SimModel model = new EarthModelImpl();
		//SimGrid grid = model.getGrid();
		model.setParameters(10, 10, 0.002);
		model.startSimulation();
		model.stopSimulation();

		//till here - Aliva.
		buffer = new SimulationBuffer();
		buffer.init();
		
		ctrlRef = new SimulationController();
		ctrlRef.init();
		
		ctrlRef.setSimulationDriverReference(this);
		
		//Initialization EarthModelImpl object also goes here.
		
		
		//Based on user input set 1 of the flag
		//flagPush enabled for testing
		flagPush = true;
//		flagPull = true;
//		flagUseBuffer = true;
		
		//This is just dummy data
//		localCopyOfGrid = DoubleGrid.getDummyData();

		//Initiate the threads
		initThreads();
		System.out.println("Done driver::init function.");
		
	}
	/**
	 * The run function implements the thread driver. 
	 */
	public void run() {
		System.out.println("In driver::run function: Copying dummy data done.");
		//SHould be removed later.
		
		// To be replaced with something like: while(!doneWithInterations)
		
		
		// Thread code goes here.
		// Driver thread should run first. Then Controller Thread.
		//This order has to be preserved.
		while(! isDone()) {
			synchronized(localCopyOfGrid) {
				System.out.println("In driver::run function: First wait for Iterations to be done.");
				while(!isIterationDone()) {
					
				}
				System.out.println("In driver::run function: Iterations are done. Do push\\pull\\insertIntoBuffer.");
				
				
					if(flagPush) {
						System.out.println("driver::run function PUSH FUNCTION TO BE EXECUTED.");
						push();
					} else if (flagPull) {
						System.out.println("driver::run function PULL FUNCTION TO BE EXECUTED.");
						
					} else if (flagUseBuffer) {
						System.out.println("driver::run function BUFFER TO BE USED.");
						insertIntoBuffer();
					}
				}
				//Continue from the step of consecutive if block.
				System.out.println("Done synchronize block driver::run function.");
		}

	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Check the functions for validity

		SimulationDriver simDriver = new SimulationDriver();
		simDriver.init();

		
	}

	public double[][] getLocalCopyOfGrid() {
		return localCopyOfGrid;
	}

	public void updateLocalCopyOfGrid(double[][] localCopyOfGrid) {
		synchronized(localCopyOfGrid) {
			this.localCopyOfGrid = localCopyOfGrid;
		}
	}
	
	/*
	 * Sets the sequence of execution of this driver and the Controller.
	 */
	public void setSequenceOfExec() {
		synchronized(localCopyOfGrid) {

		}
	}
	
	public void insertIntoBuffer() {
		//set the buffer with the local copy of values.
		System.out.println("driver::insertIntoBuffer function: iterationCount "+iterationCount);
		
		synchronized(localCopyOfGrid) {
			while(buffer.isBufferFull()) {
				try {
					System.out.println("driver::insertIntoBuffer Buffer is full. Wait()");
					localCopyOfGrid.wait();
				} catch (InterruptedException e) {
					System.out.println("Interrupted Exception in driver::insertIntoBuffer()");
					e.printStackTrace();
				}
			}
			System.out.println("driver::insertIntoBuffer function: ENTER INTO BUFFER");
			buffer.pushIntoList(localCopyOfGrid);
			localCopyOfGrid.notifyAll();
		}

	}

	public SimulationController getControllerRef(){
		//Consider not making it synchronized.
		synchronized(localCopyOfGrid) {
			return ctrlRef;
			
		}
	}
	
	public Thread getDriverThread() {
		return driverThread;
	}


	public void setDriverThread(Thread driver) {
		this.driverThread = driver;
	}


	public Thread getControllerThread() {
		return ctrlerThread;
	}


	public void setControllerThread(Thread controller) {
		this.ctrlerThread = controller;
	}

}
