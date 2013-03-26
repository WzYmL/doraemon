/**
 * 
 */
package org.salever.j2se.com.connection;

import org.eclipse.jface.preference.IPreferenceStore;
import org.salever.j2se.com.ClientComPlugin;
import org.salever.j2se.com.model.SerialConnectionException;
import org.salever.j2se.com.model.SerialParameters;
import org.salever.j2se.com.preferences.PreferenceConstants;


/**
 * @author salever
 * 
 */
public class SerialConnectionManager {

	/**
	 * 根据首选项，读取配置参数
	 * 
	 * @return
	 */
	public static SerialParameters getSerialParameters() {
		SerialParameters param = new SerialParameters();

		IPreferenceStore store = ClientComPlugin.getDefault()
				.getPreferenceStore();
		param.setBaudRate(store.getInt(PreferenceConstants.P_BAUD_RATE));
		param.setDatabits(store.getInt(PreferenceConstants.P_DATA_BITS));
		param.setFlowControlIn(store
				.getString(PreferenceConstants.P_FOLLOW_CONTROL_IN));
		param.setFlowControlOut(store
				.getString(PreferenceConstants.P_FOLLOW_CONTROL_OUT));
		param.setParity(store.getString(PreferenceConstants.P_PARITY));
		param.setPortName(store.getString(PreferenceConstants.P_PORT_NAME));
		param.setStopbits(store.getInt(PreferenceConstants.P_STOP_BITS));
		return param;
	}

	private static BusinessSerialConnection connection;

	/**
	 * 连接串口
	 * 
	 * @param forceRestart
	 * @throws SerialConnectionException
	 */
	public static BusinessSerialConnection connectSerialCom()
			throws SerialConnectionException {
		IPreferenceStore store = ClientComPlugin.getDefault()
				.getPreferenceStore();
		int interval = store.getInt(PreferenceConstants.P_READ_INTERVAL);
		connection = new BusinessSerialConnection(getSerialParameters(),
				interval);
		connection.openConnection();
		return connection;
	}

	/**
	 * 检查运行状态
	 * 
	 * @return
	 */
	public static boolean chechConnectionRunning() {
		if (connection != null && connection.isOpen()) {
			return true;
		}
		return false;
	}

	public static BusinessSerialConnection getConnection() {
		return connection;
	}
}
