/**
 * 
 */
package org.salever.rcp.demo.xmleditor.editor;

/**
 * @author salever@126.com
 * 
 */
public interface CustomConstants {

/**
	 * "<"
	 */
	public static final int TAG_BEGIN = 0; // "<"

	/**
	 * ">"
	 */
	public static final int TAG_END = 1; // ">"

	/**
	 * "?"
	 */
	public static final int TAG_HEAD = 2; // "?"

	/**
	 * "/"
	 */
	public static final int TAG_NAIL = 3; // "/"

	/**
	 * "="
	 */
	public static final int TAG_EQUAL = 4; // "="

	/**
	 * "'" or """
	 */
	public static final int QUOTATION = 5; // "'" or """

	/**
	 * letter or digit or other symbols
	 */
	public static final int XML_VAR = 6; // "a,1"

	/**
	 * " " or "\r\n"
	 */
	public static final int SPACE_OR_LINE = 7; // " " or "\r\n"

	public static final int STATE_TAG = 10;

	public static final int STATE_ELEMENT = 11;

	public static final int STATE_ATTRIBUTE = 12;

	public static final int STATE_VALUE = 13;

	public static final int STATE_FINISH = 14;

	public static final int STATE_AFTER_VALUE = 15;
	/**
	 * 
	 */
	public static final String XML_VERSION_ATTR = "version";

	/**
	 * 
	 */
	public static final String XML_ENCODING_ATTR = "encoding";

	/**
	 * 
	 */
	public static final String XML_PATH_ATTR = "path";

	/**
	 * 
	 */
	public static final String XML_NAME_ATTR = "name";

	/**
	 * 
	 */
	public static final String XML_POSITION_X_ATTR = "position_x";
	/**
	 * 
	 */
	public static final String XML_POSITION_Y_ATTR = "position_y";
	/**
	 * 
	 */
	public static final String XML_TYPE_ATTR = "type";
	/**
	 * 
	 */
	public static final String XML_LOCAL_ROUTER_ATTR = "local_router";
	/**
	 * 
	 */
	public static final String XML_REMOTE_ROUTER_ATTR = "remote_router";

	public static final String ACTION_OPEN_CONFIG_TARGET = "com.vire.internal.topology_viewer.actions.OpenConfigureAction";

	public static final int IMPORT_CONFIG = 1;

	public static final int NEW_CONFIG = 2;

	public static final int CANCEL_CONFIG = 3;

	public static final String P_CONSTRAINT = "_constraint";

	public static final String P_ID = "_id";

	public static final String P_NAME = "_name";

	public static final String P_TYPE = "_type";

	public static final String P_CONFIG_FILE = "_config_file";

	public static final String P_SOURCE_CONNECTION = "_source_connection";

	public static final String P_TARGET_CONNECTION = "_target_connection";

	public static final String P_SOURCE_NODE = "_source_node";

	public static final String P_TARGET_NODE = "_target_node";

	public static final String LARGE_ICON = "large";

	public static final String SMALL_ICON = "small";

}
