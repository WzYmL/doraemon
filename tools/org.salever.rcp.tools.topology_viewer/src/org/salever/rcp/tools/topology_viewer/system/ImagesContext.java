/**
 * @file ImageContext.java
 *
 * @author salever@126.com
 * 
 * @version 
 * 
 * @Create on 2009-7-8  ����11:21:53
 * 
 * @description 
 * 
 */
package org.salever.rcp.tools.topology_viewer.system;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.salever.rcp.tools.topology_viewer.Activator;


/**
 * @author salever@126.com
 * 
 * @name ImageContext
 * 
 */
public class ImagesContext {

	private final static String ICONS_PATH = "icons/";

	public final static String MODEL_ROUTER = "MODEL_ROUTER";
	public final static String NEW = "NEW";
	public final static String SIMPLE_ARROW = "SIMPLE_ARROW";
	public final static String SINGLE_ARROW = "SINGLE_ARROW";
	public final static String OPEN = "OPEN";
	public final static String RELOAD = "RELOAD";
	public final static String OPEN_MAPPING_FILE = "OPEN_MAPPING_FILE";
	public final static String DOUBLE_ARROW = "DOUBLE_ARROW";
	public final static String OPEN_CONFIG_FILE = "OPEN_CONFIG_FILE";
	public final static String XML = "XML";
	public final static String ROUTER_SMALL = "ROUTER_SMALL";
	public final static String SWITCH_ICON = "SWITCH_ICON";
	public final static String LAST = "LAST";
	public final static String BOOK = "BOOK";
	public final static String REFRESH = "REFRESH";
	public final static String BOOK_MANAGE = "BOOK_MANAGE";
	public final static String BOOK_PRO = "BOOK_PRO";
	public final static String BOOK_UNPRO = "BOOK_UNPRO";
	public final static String PALETTE_ROUTER = "PALETTE_ROUTER";
	public final static String PALETTE_SUBNET = "PALETTE_SUBNET";
	public final static String MODEL_SUBNET = "MODEL_SUBNET";
	public final static String OUT_LINE_SUBNET = "OUT_LINE_SUBNET";
	public final static String OUT_LINE_ROUTER = "OUT_LINE_ROUTER";
	public final static String OUT_LINE_PROTOCOL = "OUT_LINE_PROTOCOL";
	public final static String OUT_LINE_DNS = "OUT_LINE_DNS";
	public final static String OUT_LINE_INTERFACE = "OUT_LINE_INTERFACE";

	private static ImageRegistry imageRegistry;

	private final static void declareImages() {
		declareRegistryImage(MODEL_ROUTER, ICONS_PATH + "model/router.png");
		declareRegistryImage(NEW, ICONS_PATH + "new.png");
		declareRegistryImage(SIMPLE_ARROW, ICONS_PATH + "simple_arrow.gif");
		declareRegistryImage(SINGLE_ARROW, ICONS_PATH + "single_arrow.png");
		declareRegistryImage(OPEN, ICONS_PATH + "open.png");
		declareRegistryImage(RELOAD, ICONS_PATH + "reload.png");
		declareRegistryImage(OPEN_MAPPING_FILE, ICONS_PATH + "open_map.gif");
		declareRegistryImage(DOUBLE_ARROW, ICONS_PATH + "double_arrow_s.png");
		declareRegistryImage(OPEN_CONFIG_FILE, ICONS_PATH + "open_config.gif");
		declareRegistryImage(XML, ICONS_PATH + "xml.gif");
		declareRegistryImage(ROUTER_SMALL, ICONS_PATH + "router_s.png");
		declareRegistryImage(SWITCH_ICON, ICONS_PATH + "switch_icon.png");
		declareRegistryImage(BOOK, ICONS_PATH + "router.png");
		declareRegistryImage(REFRESH, ICONS_PATH + "router.png");
		declareRegistryImage(BOOK_MANAGE, ICONS_PATH + "router.png");
		declareRegistryImage(BOOK_PRO, ICONS_PATH + "router.png");
		declareRegistryImage(BOOK_UNPRO, ICONS_PATH + "router.png");
		declareRegistryImage(PALETTE_ROUTER, ICONS_PATH + "palette/router.png");
		declareRegistryImage(PALETTE_SUBNET, ICONS_PATH + "palette/subnet.png");
		declareRegistryImage(MODEL_SUBNET, ICONS_PATH + "model/subnet.png");
		declareRegistryImage(OUT_LINE_SUBNET, ICONS_PATH + "outline/subnet.png");
		declareRegistryImage(OUT_LINE_ROUTER, ICONS_PATH + "outline/router.png");
		declareRegistryImage(OUT_LINE_PROTOCOL, ICONS_PATH
				+ "outline/protocol.gif");
		declareRegistryImage(OUT_LINE_DNS, ICONS_PATH + "outline/dns.gif");
		declareRegistryImage(OUT_LINE_INTERFACE, ICONS_PATH
				+ "outline/interface.gif");
	}

	private final static void declareRegistryImage(String key, String path) {
		try {
			URL url = FileLocator.find(Activator.getDefault().getBundle(),
					new Path(path), null);
			if (url != null) {
				ImageDescriptor image = ImageDescriptor.createFromURL(url);
				getImageRegistry().put(key, image);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Image getImage(String key) {
		return getImageRegistry().get(key);
	}

	public static ImageDescriptor getImageDescriptor(String key) {
		return getImageRegistry().getDescriptor(key);
	}

	public static ImageRegistry getImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ImageRegistry();
			declareImages();
		}
		return imageRegistry;
	}
}
