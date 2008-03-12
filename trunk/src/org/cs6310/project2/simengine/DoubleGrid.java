package org.cs6310.project2.simengine;

public class DoubleGrid {

	public static double[][] getDummyData() {
		return getGridTemp(0.0);
	}
	
	public static double[][] getFinalDummyData() {
		return getGridTemp(19.0);
	}

	public  static double[][]  getGridTemp(double assignTemp){
		double[][] gridTemp = new double[180][360];
		
		for(int i=0; i < gridTemp.length;i++) {
			for (int j=0 ; j < gridTemp[i].length;j++){
				if(assignTemp == 0)
					gridTemp[i][j] = 15.0;
				else if(i > 20 && i < 35)
					gridTemp[i][j] = assignTemp;
				else
					gridTemp[i][j] = 15.0;
			}
		}
		return gridTemp;
	}

}
