package org.cs6310.project2.simengine;

public class SphereLattice extends SimLattice{

    public void getWeights(double[] wLRTB, int r, int c) {
        double topAngle = ((r + 0.0) / rows) - 0.5;
        double botAngle = ((r + 1.0) / cols) - 0.5;
        wLRTB[2] = Math.cos(topAngle * Math.PI);
        wLRTB[3] = Math.cos(botAngle * Math.PI);
        double sum = wLRTB[2] + wLRTB[3] + 2;
        wLRTB[0] = 1.0 / sum;
        wLRTB[1] = 1.0 / sum;
        wLRTB[2] /= sum;
        wLRTB[3] /= sum;
    }
}
