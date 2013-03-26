/**
 * @file HelloModel.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-13  ����02:18:51
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.models;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.salever.rcp.tools.topology_viewer.descriptor.ReadOnlyTextPropertyDescriptor;
import org.salever.rcp.tools.topology_viewer.system.CustomConstants;
import org.salever.rcp.tools.topology_viewer.system.CustomFactory;
import org.salever.rcp.tools.topology_viewer.system.ImagesContext;
import org.salever.rcp.tools.topology_viewer.system.Messages;


/**
 * @author salever@126.com
 * 
 * @name HelloModel
 * 
 */
public class RouterModel_ extends AbstractModel {

	public static final String P_CONSTRAINT = "_constraint";

	public static final String P_ID = "_id";

	public static final String P_NAME = "_name";

	public static final String P_TYPE = "_type";

	public static final String P_CONFIG_FILE = "_config_file";

	public static final String P_HEIGHT = "_height";

	public static final String P_WIDTH = "_width";

	public static final String P_ICON_TYPE = "_icon_type";

	public static final String P_SOURCE_CONNECTION = "_source_connection";

	public static final String P_TARGET_CONNECTION = "_target_connection";

	private String id = CustomFactory.getDefaultID();

	private String name = "R" + id;

	private String type = "A";

	private Rectangle constraint = new Rectangle();

	private int height = constraint.height;

	private int width = constraint.width;

	private Image icon = ImagesContext.getImage(ImagesContext.MODEL_ROUTER);

	private String iconType = CustomConstants.LARGE_ICON;

	private String filePath = "";

	private IEditorInput input;

	private List<Object> sourceConnection = new ArrayList<Object>();

	private List<Object> targetConnection = new ArrayList<Object>();

	/**
	 * 
	 */
	public void addSourceConnection(Object obj) {
		sourceConnection.add(obj);
		firePropertyChange(P_SOURCE_CONNECTION, null, null);
	}

	/**
	 * @description
	 * 
	 * @param
	 * 
	 * @return void
	 */
	public void addTargetConnection(Object obj) {
		targetConnection.add(obj);
		firePropertyChange(P_TARGET_CONNECTION, null, null);
	}

	/**
	 * @return the constraint
	 */
	public Rectangle getConstraint() {
		return constraint;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return the icon
	 */
	public Image getIcon() {
		return icon;
	}

	/**
	 * @return the iconType
	 */
	public String getIconType() {
		return iconType;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the input
	 */
	public IEditorInput getInput() {
		return input;
	}

	/**
	 * @description
	 * 
	 * @param
	 * 
	 * @return List<Object>
	 */
	public List<Object> getModelSourceConnection() {
		return sourceConnection;
	}

	/**
	 * @description
	 * 
	 * @param
	 * 
	 * @return List<Object>
	 */
	public List<Object> getModelTargetConnection() {
		return targetConnection;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		// TODO Auto-generated method stub
		IPropertyDescriptor[] descriptor = new IPropertyDescriptor[] {
				new TextPropertyDescriptor(P_ID, Messages
						.getString("Property.Id")),
				new TextPropertyDescriptor(P_NAME, Messages
						.getString("Property.Name")),
				new TextPropertyDescriptor(P_TYPE, Messages
						.getString("Property.Type")),
				new TextPropertyDescriptor(P_CONFIG_FILE, Messages
						.getString("Property.Config_File_Path")),
				new TextPropertyDescriptor(P_HEIGHT, Messages
						.getString("Property.Height")),
				new TextPropertyDescriptor(P_WIDTH, Messages
						.getString("Property.Width")),
				new TextPropertyDescriptor(P_CONSTRAINT, Messages
						.getString("Property.Size")),
				new ReadOnlyTextPropertyDescriptor(P_ICON_TYPE, Messages
						.getString("Property.Icon_Type")) };
		return descriptor;
	}

	@Override
	public Object getPropertyValue(Object id) {
		// TODO Auto-generated method stub
		if (id.equals(P_NAME)) {
			return name;
		} else if (id.equals(P_TYPE)) {
			return type;
		} else if (id.equals(P_CONFIG_FILE)) {
			return filePath;
		} else if (id.equals(P_ID)) {
			return this.id;
		} else if (id.equals(P_HEIGHT)) {
			return this.constraint.height;
		} else if (id.equals(P_WIDTH)) {
			return this.constraint.width;
		} else if (id.equals(P_CONSTRAINT)) {
			return "(" + constraint.width + " , " + constraint.width + ")";
		} else if (id.equals(P_ICON_TYPE)) {
			return this.iconType;
		}
		return null;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	@Override
	public boolean isPropertySet(Object id) {
		// TODO Auto-generated method stub
		if (id.equals(P_NAME) || id.equals(P_TYPE) || id.equals(P_CONFIG_FILE)
				|| id.equals(P_ID) || id.equals(P_HEIGHT) || id.equals(P_WIDTH)
				|| id.equals(P_CONSTRAINT) || id.equals(P_ICON_TYPE)) {
			return true;
		}
		return false;
	}

	/**
	 * @description
	 * 
	 * @param
	 * 
	 * @return void
	 */
	public void removeSourceConnection(Object obj) {
		sourceConnection.remove(obj);
		firePropertyChange(P_SOURCE_CONNECTION, null, null);
	}

	/**
	 * @description
	 * 
	 * @param
	 * 
	 * @return void
	 */
	public void removeTargetConnection(Object obj) {
		targetConnection.remove(obj);
		firePropertyChange(P_TARGET_CONNECTION, null, null);
	}

	/**
	 * @param constraint
	 *            the constraint to set
	 */
	public void setConstraint(Rectangle constraint) {
		this.constraint = constraint;
		this.firePropertyChange(P_CONSTRAINT, null, constraint);
		this.firePropertyChange(P_WIDTH, null, width);
		this.firePropertyChange(P_HEIGHT, null, height);
	}

	/**
	 * @param filePath
	 *            the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
		this.firePropertyChange(P_CONFIG_FILE, null, filePath);
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
		this.firePropertyChange(P_HEIGHT, null, height);
	}

	/**
	 * @param icon
	 *            the icon to set
	 */
	public void setIcon(Image icon) {
		this.icon = icon;
	}

	/**
	 * @param iconType
	 *            the iconType to set
	 */
	public void setIconType(String iconType) {
		this.iconType = iconType;
		if (iconType.equals(CustomConstants.LARGE_ICON)) {
			setIcon(ImagesContext.getImage(ImagesContext.MODEL_ROUTER));
		} else if (iconType.equals(CustomConstants.SMALL_ICON)) {
			setIcon(ImagesContext.getImage(ImagesContext.ROUTER_SMALL));
		}
		this.firePropertyChange(P_ICON_TYPE, null, iconType);
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
		this.firePropertyChange(P_ID, null, id);
	}

	/**
	 * @param input
	 *            the input to set
	 */
	public void setInput(IEditorInput input) {
		this.input = input;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
		this.firePropertyChange(P_NAME, null, name);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		// TODO Auto-generated method stub
		if (id.equals(P_NAME)) {
			this.setName((String) value);
		} else if (id.equals(P_TYPE)) {
			this.setType(type);
		} else if (id.equals(P_CONFIG_FILE)) {
			setFilePath((String) value);
		} else if (id.equals(P_ID)) {
			setId((String) value);
		} else if (id.equals(P_HEIGHT)) {
			setHeight(Integer.parseInt(value.toString()));
			constraint.height = Integer.parseInt(value.toString());
			this.firePropertyChange(P_CONSTRAINT, null, constraint);
		} else if (id.equals(P_WIDTH)) {
			setWidth(Integer.parseInt(value.toString()));
			constraint.width = Integer.parseInt(value.toString());
			this.firePropertyChange(P_CONSTRAINT, null, constraint);
		} else if (id.equals(P_CONSTRAINT)) {
			String[] values = ((String) value).split(",");
			int width = Integer.parseInt(values[0].replace("(", "").trim());
			setConstraint(new Rectangle(constraint.x, constraint.y, width, 20));
		} else if (id.equals(P_ICON_TYPE)) {
			setIconType((String) value);
		}
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
		this.firePropertyChange(P_TYPE, null, type);
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
		firePropertyChange(P_WIDTH, null, width);
	}

}
