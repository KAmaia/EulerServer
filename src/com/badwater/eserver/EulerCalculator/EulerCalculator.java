package com.badwater.eserver.EulerCalculator;

/**
 * Created by Krystal on 2015-04-30.
 */
public class EulerCalculator implements Runnable {
	private double[] data = new double[2];
	private double i;

	@Override
	public void run() {
		System.out.println("Calculator Started!");
		while (true) {
			data[0] = Math.pow(((1 + 1 / i)), i);
			data[1] = i;
			i++;
		}
	}
	public synchronized double[] getData(){
		return data;
	}
}
