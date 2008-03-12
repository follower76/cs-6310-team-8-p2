package org.cs6310.project2.simcontroller;

import org.cs6310.project2.simengine.*;

public class EarthHeatingSimulation {

	private Thread driverThread;
	private Thread ctrlerThread;

	/**
	 * Main program which implements the simulation. Used for testing purposes.
	 */
	public static void main(String[] args) {
		// Dummy test code goes here.
		EarthHeatingSimulation sim = new EarthHeatingSimulation();
		
		SimulationDriver simDriver = new SimulationDriver();
		simDriver.init();
		
		sim.setDriverThread(new Thread(simDriver));
		sim.setControllerThread(new Thread(simDriver.getControllerRef()));
		
		Thread dThread = sim.getDriverThread();
		Thread cThread = sim.getControllerThread();
		
		dThread.start();
		cThread.start();
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
