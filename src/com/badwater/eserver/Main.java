package com.badwater.eserver;
/**
 *
 * @author Krystal Amaia
 * This is a small little httpd that is designed to calculate the value of Euler's Number
 * And Display It On Client Connect.
 *
 */

import com.badwater.eserver.ConnectionHandler.ConnectionHandler;
import com.badwater.eserver.EulerCalculator.EulerCalculator;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;

class Main {

	public static void main(String[] args) throws IOException {
		//Make the port number super high, so that we can use this on
		// linux without sudo rights.
		final int portNumber = 65532;
		final EulerCalculator ecalc = new EulerCalculator();

		// Create a new ECalculator and start it
		Thread t = new Thread(ecalc);
		t.start();
		ServerSocket listener = null;
		boolean running = true;
		//infinite loop so the server never closes
		while (running) {
			try {
				listener = new ServerSocket(portNumber);
				Thread ch = new Thread(new ConnectionHandler(listener.accept(), ecalc));
				ch.start();

			}
			catch (BindException e) {
				System.out.println("Error Binding Port: This Usually Means The Server Is Running Already.");

				running = false;
			}

			listener.close();
		}

	}
}
