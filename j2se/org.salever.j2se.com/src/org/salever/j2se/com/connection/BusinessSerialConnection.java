/**
 * 
 */
package org.salever.j2se.com.connection;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.comm.SerialPortEvent;

import org.salever.j2se.com.model.AbstractSerialConnection;
import org.salever.j2se.com.model.SerialParameters;


/**
 * @author salever
 * 
 */
public class BusinessSerialConnection extends AbstractSerialConnection {

	private Set<ISerialDataHandler> handlers;

	protected BusinessSerialConnection(SerialParameters parameters, int interval) {
		super(parameters, interval);
		handlers = new HashSet<ISerialDataHandler>();
	}

	@Override
	public void serialEvent(SerialPortEvent e) {

		// Create a StringBuffer and int to receive input data.
		StringBuffer inputBuffer = new StringBuffer();
		int newData = 0;

		// Determine type of event.
		switch (e.getEventType()) {

		// Read data until -1 is returned. If \r is received substitute
		// \n for correct newline handling.
		case SerialPortEvent.DATA_AVAILABLE:
			while (newData != -1) {
				try {
					newData = is.read();
					if (newData == -1) {
						break;
					}

					// if ('\r' == (char)newData) {
					// inputBuffer.append('\n');
					// } else {
					inputBuffer.append((char) newData);
					// }
				} catch (IOException ex) {
					System.err.println(ex);
					return;
				}
			}

			// Append received data to messageAreaIn.
			break;

		// If break event append BREAK RECEIVED message.
		case SerialPortEvent.BI:
			// messageAreaIn.append("\n--- BREAK RECEIVED ---\n");
		}

		handleReadData(inputBuffer.toString());

	}

	private void handleReadData(String string) {
		for (ISerialDataHandler handler : handlers) {
			handler.handle(string);
		}
	}

	@Override
	public void ownershipChange(int type) {
		// TODO Auto-generated method stub

	}

	public void addSerialDataHandler(ISerialDataHandler handler) {
		if (handler != null) {
			this.handlers.add(handler);
		}
	}

	public void removeSerialDataHandler(ISerialDataHandler handler) {
		if (handler != null) {
			this.handlers.remove(handler);
		}
	}

	public void clearSerialDataHandler() {
		this.handlers.clear();
	}

}
