package org.salever.j2se.com.preferences;

/**
 * Constant definitions for plug-in preferences
 */
public class PreferenceConstants {

	public static final String P_PORT_NAME = "p_port_name_24";

	public static final String P_BAUD_RATE = "p_baud_rate_24";

	public static final String P_FOLLOW_CONTROL_IN = "p_follow_control_in_24";

	public static final String P_FOLLOW_CONTROL_OUT = "p_follow_control_out_24";

	public static final String P_DATA_BITS = "p_data_bits_24";

	public static final String P_STOP_BITS = "p_stop_bits_24";

	public static final String P_PARITY = "p_parity";

	public static final String[][] FOLLOW_CONTROL_IN_VALUES = {
			{ "None", "None" }, { "Xon/Xoff In", "Xon/Xoff In" },
			{ "RTS/CTS In", "RTS/CTS In" } };
	public static final String[][] FOLLOW_CONTROL_OUT_VALUES = {
			{ "None", "None" }, { "Xon/Xoff In", "Xon/Xoff In" },
			{ "RTS/CTS In", "RTS/CTS In" } };
	public static final String[][] STOP_BITS_VALUES = { { "1", "1" },
			{ "1.5", "1.5" }, { "2", "2" } };
	public static final String[][] DATA_BITS_VALUES = { { "5", "5" },
			{ "6", "6" }, { "7", "7" }, { "8", "8" } };
	public static final String[][] PARITY_VALUES = { { "None", "None" },
			{ "Even", "Even" }, { "Odd", "Odd" } };

	public static final String P_READ_INTERVAL = "p_read_interval";
}
