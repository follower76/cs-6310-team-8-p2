package org.cs6310.project2.simengine;

public class EarthModelImpl extends SimModel{
	
	public  EarthModelImpl(){
		super();
	}
	protected SimGrid createGrid(){
		return new DoubleGrid();
	}
	protected  SimLattice  createLattice(){
		return new SphereLattice();
	}
	protected  ExtHeatSource createExtHeatSurce(){
		return new SolarSource(1440);
	}

	
	
	
	
	
	


}
