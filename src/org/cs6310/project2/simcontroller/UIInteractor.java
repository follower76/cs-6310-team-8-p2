package org.cs6310.project2.simcontroller;

import org.cs6310.project2.simengine.*;

/*
 * UIInteractor implements the Adapter pattern when co-ordinating the execution of UI through the Controller
 */
public class UIInteractor {
	
	double[][] latestSetOfValues;

	public double[][] getLatestSetOfValues() {
		return latestSetOfValues;
	}

	public void setLatestSetOfValues(double[][] latestSetOfValues) {
		this.latestSetOfValues = latestSetOfValues;
	}
	
	public void updateLatestValuesInUI() {
		System.out.println("Current values in UI.");
		//Dummy to be replaced with real execution code.
		//To update in UI
		for(int i=0;i < latestSetOfValues.length; i++) {
			System.out.println("\n");
			for(int j=0; j < latestSetOfValues[i].length; j++) {
				//System.out.println(latestSetOfValues[i][j] + "\t");
			}
		}
	}
	
	
}
