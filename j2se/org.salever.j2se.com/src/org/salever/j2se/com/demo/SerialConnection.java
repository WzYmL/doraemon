package org.salever.j2se.com.demo;

/* @(#)SerialConnection.java	1.6 98/07/17 SMI
 *
 * Copyright (c) 1998 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license
 * to use, modify and redistribute this software in source and binary
 * code form, provided that i) this copyright notice and license appear
 * on all copies of the software; and ii) Licensee does not utilize the
 * software in a manner which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind.
 * ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES,
 * INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND
 * ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THE
 * SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS
 * BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES,
 * HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING
 * OUT OF THE USE OF OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control
 * of aircraft, air traffic, aircraft navigation or aircraft
 * communications; or in the design, construction, operation or
 * maintenance of any nuclear facility. Licensee represents and
 * warrants that it will not use or redistribute the Software for such
 * purposes.
 */

import java.awt.TextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.OutputStream;

import javax.comm.CommPortOwnershipListener;
import javax.comm.SerialPortEvent;
import javax.comm.UnsupportedCommOperationException;

import org.salever.j2se.com.model.AbstractSerialConnection;
import org.salever.j2se.com.model.SerialConnectionException;
import org.salever.j2se.com.model.SerialParameters;


/**
 * A class that handles the details of a serial connection. Reads from one
 * TextArea and writes to a second TextArea. Holds the state of the connection.
 */
public class SerialConnection extends AbstractSerialConnection {
	private SerialDemo parent;

	// private TextArea messageAreaOut;
	private TextArea messageAreaIn;
	private KeyHandler keyHandler;

	/**
	 * Creates a SerialConnection object and initilizes variables passed in as
	 * params.
	 * 
	 * @param parent
	 *            A SerialDemo object.
	 * @param parameters
	 *            A SerialParameters object.
	 * @param messageAreaOut
	 *            The TextArea that messages that are to be sent out of the
	 *            serial port are entered into.
	 * @param messageAreaIn
	 *            The TextArea that messages comming into the serial port are
	 *            displayed on.
	 */
	public SerialConnection(SerialDemo parent, SerialParameters parameters,
	// TextArea messageAreaOut,
			TextArea messageAreaIn) {
		super(parameters);
		this.parent = parent;

		// this.messageAreaOut = messageAreaOut;
		this.messageAreaIn = messageAreaIn;
		open = false;
	}

	/**
	 * Attempts to open a serial connection and streams using the parameters in
	 * the SerialParameters object. If it is unsuccesfull at any step it returns
	 * the port to a closed state, throws a
	 * <code>SerialConnectionException</code>, and returns.
	 * 
	 * Gives a timeout of 30 seconds on the portOpen to allow other applications
	 * to reliquish the port if have it open and no longer need it.
	 */

	/**
	 * Sets the connection parameters to the setting in the parameters object.
	 * If set fails return the parameters object to origional settings and throw
	 * exception.
	 */
	public void setConnectionParameters() throws SerialConnectionException {

		// Save state of parameters before trying a set.
		int oldBaudRate = sPort.getBaudRate();
		int oldDatabits = sPort.getDataBits();
		int oldStopbits = sPort.getStopBits();
		int oldParity = sPort.getParity();
		int oldFlowControl = sPort.getFlowControlMode();

		// Set connection parameters, if set fails return parameters object
		// to original state.
		try {
			sPort.setSerialPortParams(
					115200,// parameters.getBaudRate(),
					parameters.getDatabits(), parameters.getStopbits(),
					parameters.getParity());
		} catch (UnsupportedCommOperationException e) {
			parameters.setBaudRate(oldBaudRate);
			parameters.setDatabits(oldDatabits);
			parameters.setStopbits(oldStopbits);
			parameters.setParity(oldParity);
			throw new SerialConnectionException("Unsupported parameter");
		}

		// Set flow control.
		try {
			sPort.setFlowControlMode(parameters.getFlowControlIn()
					| parameters.getFlowControlOut());
		} catch (UnsupportedCommOperationException e) {
			throw new SerialConnectionException("Unsupported flow control");
		}
	}

	/**
	 * Close the port and clean up associated elements.
	 */
	public void closeConnection() {
		// If port is alread closed just return.
		if (!open) {
			return;
		}

		// Remove the key listener.
		// messageAreaOut.removeKeyListener(keyHandler);

		// Check to make sure sPort has reference to avoid a NPE.
		if (sPort != null) {
			try {
				// close the i/o streams.
				os.close();
				is.close();
			} catch (IOException e) {
				System.err.println(e);
			}

			// Close the port.
			sPort.close();

			// Remove the ownership listener.
			portId.removePortOwnershipListener(this);
		}

		open = false;
	}

	/**
	 * Send a one second break signal.
	 */
	public void sendBreak() {
		sPort.sendBreak(1000);
	}

	/**
	 * Reports the open status of the port.
	 * 
	 * @return true if port is open, false if port is closed.
	 */
	public boolean isOpen() {
		return open;
	}

	/**
	 * Handles SerialPortEvents. The two types of SerialPortEvents that this
	 * program is registered to listen for are DATA_AVAILABLE and BI. During
	 * DATA_AVAILABLE the port buffer is read until it is drained, when no more
	 * data is availble and 30ms has passed the method returns. When a BI event
	 * occurs the words BREAK RECEIVED are written to the messageAreaIn.
	 */

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
			messageAreaIn.append(new String(inputBuffer));
			break;

		// If break event append BREAK RECEIVED message.
		case SerialPortEvent.BI:
			messageAreaIn.append("\n--- BREAK RECEIVED ---\n");
		}

	}

	/**
	 * Handles ownership events. If a PORT_OWNERSHIP_REQUESTED event is received
	 * a dialog box is created asking the user if they are willing to give up
	 * the port. No action is taken on other types of ownership events.
	 */
	public void ownershipChange(int type) {
		if (type == CommPortOwnershipListener.PORT_OWNERSHIP_REQUESTED) {
			PortRequestedDialog prd = new PortRequestedDialog(parent);
		}
	}

	/**
	 * A class to handle <code>KeyEvent</code>s generated by the messageAreaOut.
	 * When a <code>KeyEvent</code> occurs the <code>char</code> that is
	 * generated by the event is read, converted to an <code>int</code> and
	 * writen to the <code>OutputStream</code> for the port.
	 */
	class KeyHandler extends KeyAdapter {
		OutputStream os;

		/**
		 * Creates the KeyHandler.
		 * 
		 * @param os
		 *            The OutputStream for the port.
		 */
		public KeyHandler(OutputStream os) {
			super();
			this.os = os;
		}

		/**
		 * Handles the KeyEvent. Gets the
		 * <code>char</char> generated by the <code>KeyEvent</code>, converts it
		 * to an <code>int</code>, writes it to the <code>
	OutputStream</code> for the
		 * port.
		 */
		public void keyTyped(KeyEvent evt) {
			char newCharacter = evt.getKeyChar();
			while (true) {
				try {
					os.write((int) newCharacter);
					// String message = "USR_LISTCARD\r\n";
					// os.write(message.getBytes());
				} catch (IOException e) {
					System.err.println("OutputStream write error: " + e);
				}
			}
		}
	}

	class WriteThread extends Thread {
		OutputStream os;

		/**
		 * Creates the KeyHandler.
		 * 
		 * @param os
		 *            The OutputStream for the port.
		 */
		public WriteThread(OutputStream os) {
			super();
			this.os = os;
		}

		/**
		 * Handles the KeyEvent. Gets the
		 * <code>char</char> generated by the <code>KeyEvent</code>, converts it
		 * to an <code>int</code>, writes it to the <code>
	OutputStream</code> for the
		 * port.
		 */
		public void run() {
			while (true) {
				try {
					String message = "USR_LISTCARD\r\n";
					os.write(message.getBytes());
					sleep(250);
					if (!open) {
						return;
					}
				} catch (Exception e) {
					System.err.println("OutputStream write error: " + e);
				}
			}
		}
	}
}
