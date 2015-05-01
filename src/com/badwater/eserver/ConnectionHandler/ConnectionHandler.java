package com.badwater.eserver.ConnectionHandler;

import com.badwater.eserver.EulerCalculator.EulerCalculator;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Krystal on 2015-04-30.
 */
public class ConnectionHandler implements Runnable {
	private final double[] data;
	private final Socket   clientConnection;

	public ConnectionHandler(Socket clientSocket, EulerCalculator eCalc) {
		clientConnection = clientSocket;
		data = eCalc.getData();
	}


	@Override
	public void run() {
		try {
			if (clientConnection != null) {
				System.out.println(
					   "New Connection Received From: " + clientConnection.getInetAddress().getHostAddress());
				PrintWriter out = new PrintWriter(clientConnection.getOutputStream());
				out.print("HTTP/1.1 200 OK\r\n");
				out.print("Content-Type: text/html\r\n");
				out.print("\r\n");
				out.print("<html><head><title>Euler Calculator</title></head><center>Current Euler Value: " +
					             data[0] + "<br />For Value Of: " + data[1] + "</center></html>\r\n");
				out.flush();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		/*try {
			clientConnection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
}

