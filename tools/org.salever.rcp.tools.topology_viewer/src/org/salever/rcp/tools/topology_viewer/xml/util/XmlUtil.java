/*******************************************************************************
 * Copyright (c) 2003, 2010 salever@126.com All rights reserved. 
 * 
 *
 * @author salever@126.com
 * @created 2010-4-30 下午12:24:37
 * @version 
 * @description 
 *******************************************************************************/
package org.salever.rcp.tools.topology_viewer.xml.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.betwixt.io.BeanReader;
import org.apache.commons.betwixt.io.BeanWriter;
import org.eclipse.draw2d.geometry.Rectangle;
import org.salever.rcp.tools.topology_viewer.models.AbstractModel;
import org.salever.rcp.tools.topology_viewer.models.DnsServer;
import org.salever.rcp.tools.topology_viewer.models.DoubleArrowConnection;
import org.salever.rcp.tools.topology_viewer.models.Interface;
import org.salever.rcp.tools.topology_viewer.models.Network;
import org.salever.rcp.tools.topology_viewer.models.Router;
import org.salever.rcp.tools.topology_viewer.models.RoutingProtocol;
import org.salever.rcp.tools.topology_viewer.models.Subnet;
import org.salever.rcp.tools.topology_viewer.models.TopoModel;
import org.salever.rcp.tools.topology_viewer.xml.XmlConfig;
import org.salever.rcp.tools.topology_viewer.xml.XmlDnsServer;
import org.salever.rcp.tools.topology_viewer.xml.XmlInterface;
import org.salever.rcp.tools.topology_viewer.xml.XmlLink;
import org.salever.rcp.tools.topology_viewer.xml.XmlParser;
import org.salever.rcp.tools.topology_viewer.xml.XmlRouter;
import org.salever.rcp.tools.topology_viewer.xml.XmlRoutingProtocol;
import org.salever.rcp.tools.topology_viewer.xml.XmlSubnet;
import org.salever.rcp.tools.topology_viewer.xml.XmlSubnetRef;
import org.salever.rcp.tools.topology_viewer.xml.ex.XmlConfigEx;
import org.salever.rcp.tools.topology_viewer.xml.ex.XmlExWriter;
import org.salever.rcp.tools.topology_viewer.xml.ex.XmlIconEx;
import org.salever.rcp.tools.topology_viewer.xml.ex.XmlMappingFileEx;
import org.salever.rcp.tools.topology_viewer.xml.ex.XmlRouterEx;
import org.salever.rcp.tools.topology_viewer.xml.ex.XmlSubnetEx;


/**
 * The Class XmlUtil.
 */
public class XmlUtil {

	/**
	 * 
	 * @param source
	 * @param decorator
	 */
	public static void decorate(XmlConfig source, XmlConfig decorator) {
		Map<String, XmlSubnet> subnetMap = new HashMap<String, XmlSubnet>();
		Map<String, XmlRouter> routerMap = new HashMap<String, XmlRouter>();
		initMap(subnetMap, routerMap, decorator);
		XmlSubnet subnet = null, cSubnet = null;
		XmlRouter router = null, cRouter = null;
		for (Iterator<XmlSubnet> it = source.getSubnets().iterator(); it
				.hasNext();) {
			subnet = it.next();
			cSubnet = subnetMap.get(subnet.getName());
			if (cSubnet != null && cSubnet.getIcon() != null) {
				subnet.setIcon(cSubnet.getIcon());
			}
			for (Iterator<XmlRouter> itt = subnet.getRouters().iterator(); itt
					.hasNext();) {
				router = itt.next();
				cRouter = routerMap.get(subnet.getName() + "."
						+ router.getName());
				if (cRouter != null && cRouter.getIcon() != null) {
					router.setIcon(cRouter.getIcon());
				}
			}
		}

		for (Iterator<XmlRouter> it = source.getRouters().iterator(); it
				.hasNext();) {
			router = it.next();
			cRouter = routerMap.get(router.getName());
			if (cRouter != null && cRouter.getIcon() != null) {
				router.setIcon(cRouter.getIcon());
			}
		}

	}

	/*
	 * private static XStream xstream = new XStream();
	 * 
	 * public static void registerClass(Class clazz){
	 * xstream.alias(clazz.getSimpleName(), clazz); } public static void
	 * toXml(Object object,OutputStream out){ xstream.toXML(object,out); }
	 * 
	 * public static String toXml(Object object){ return xstream.toXML(object);
	 * }
	 * 
	 * public static Object fromXml(String xml){ return xstream.fromXML(xml); }
	 * 
	 * public static Object fromXml(InputStream in){ return xstream.fromXML(in);
	 * }
	 */

	/**
	 * to deserialize an object from an xml file.
	 * 
	 * @param clazz
	 *            the clazz
	 * @param in
	 *            the in
	 * 
	 * @return the object deserialized
	 * 
	 * @throws XmlException
	 *             the xml exception
	 */
	@SuppressWarnings("unchecked")
	public static Object fromXml(InputStream in, Class clazz)
			throws XmlException {
		BeanReader beanReader = new BeanReader();
		Object object = null;
		try {
			beanReader.registerBeanClass(clazz);
			beanReader.getXMLIntrospector().setWrapCollectionsInElement(true);
			object = beanReader.parse(in);
		} catch (Exception e) {
			throw new XmlException(e);
		}
		return object;
	}

	private static void initMap(Map<String, XmlSubnet> subnetMap,
			Map<String, XmlRouter> routerMap, XmlConfig decorator) {
		XmlSubnet subnet = null;
		XmlRouter router = null;
		for (Iterator<XmlSubnet> it = decorator.getSubnets().iterator(); it
				.hasNext();) {
			subnet = it.next();
			subnetMap.put(subnet.getName(), subnet);
			for (Iterator<XmlRouter> itt = subnet.getRouters().iterator(); itt
					.hasNext();) {
				router = itt.next();
				routerMap
						.put(subnet.getName() + "." + router.getName(), router);
			}
		}

		for (Iterator<XmlRouter> itt = decorator.getRouters().iterator(); itt
				.hasNext();) {
			router = itt.next();
			routerMap.put(router.getName(), router);
		}
	}

	private static void parseSubnetChild(Subnet subnet,
			Map<Integer, Subnet> maps) {
		if (subnet.getSubnetRefs().size() != 0) {
			for (Iterator<Integer> itt = subnet.getSubnetRefs().iterator(); itt
					.hasNext();) {
				int id = itt.next().intValue();
				Subnet child = maps.get(Integer.valueOf(id));
				if (child.getSubnets().size() != 0) {
					parseSubnetChild(subnet, maps);
				}
				subnet.addSubnet(child);
			}
		}
	}

	private static void postTranslateSubnet(Network network,
			Map<Integer, Subnet> maps) {
		for (Iterator<Subnet> it = maps.values().iterator(); it.hasNext();) {
			Subnet subnet = it.next();
			parseSubnetChild(subnet, maps);
			if (subnet.getLevel() == 1) {
				network.addChild(subnet);
			}
		}
	}

	/**
	 * To serialize an object to an xml file.
	 * 
	 * @param out
	 *            the out
	 * @param object
	 *            the object to be serialized
	 * 
	 * @throws XmlException
	 *             the xml exception
	 */
	public static void toXml(Object object, OutputStream out)
			throws XmlException {
		try {
			BeanWriter beanWriter = new BeanWriter(out, "UTF-8"); //$NON-NLS-1$
			beanWriter.enablePrettyPrint();
			beanWriter.setIndent("    "); //$NON-NLS-1$
			beanWriter.setEndOfLine(System.getProperty("line.separator")); //$NON-NLS-1$
			beanWriter
					.writeXmlDeclaration("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"); //$NON-NLS-1$
			beanWriter.getXMLIntrospector().setWrapCollectionsInElement(true);
			beanWriter.setWriteEmptyElements(false);
			beanWriter.write(object);
			beanWriter.close();
		} catch (Exception e) {
			throw new XmlException(e);
		}
	}

	/**
	 * 
	 * @param network
	 * @param config
	 */
	public static void translate(Network network, XmlConfig config) {

		network.setLevel(Network.TOP_LEVEL);
		network.setMappingFile(config.getMappingFile().getPath());

		Subnet subnet = null;
		Router router = null;
		XmlRouter xRouter = null;
		XmlSubnet xSubnet = null;

		for (Iterator<XmlRouter> it = config.getRouters().iterator(); it
				.hasNext();) {
			xRouter = it.next();
			router = translateRouter(xRouter);
			network.addChild(router);
		}

		Map<Integer, Subnet> maps = new HashMap<Integer, Subnet>();
		for (Iterator<XmlSubnet> it = config.getSubnets().iterator(); it
				.hasNext();) {
			xSubnet = it.next();
			subnet = translateSubnet(xSubnet);
			maps.put(Integer.valueOf(xSubnet.getId()), subnet);
		}
		postTranslateSubnet(network, maps);

		translateLinks(network, config.getLinks());

		network.setName("Topology");
	}

	/**
	 * 
	 * @param network
	 * @param links
	 */
	private static void translateLinks(Network network, List<XmlLink> links) {
		for (Iterator<XmlLink> it = links.iterator(); it.hasNext();) {
			XmlLink xmlLink = it.next();
			int local = xmlLink.getLocalRouter();
			int remote = xmlLink.getRemoteRouter();
			TopoModel localModel, remoteModel;
			localModel = network.getChildById(local);
			remoteModel = network.getChildById(remote);

			// balance(localModel, remoteModel);

			if (localModel == null || remoteModel == null) {
				continue;
			}
			AbstractModel localParent = localModel.getParent();
			AbstractModel remoteParent = remoteModel.getParent();

			int localLevel = localModel.getLevel();
			int remoteLevel = remoteModel.getLevel();

			while (localLevel != remoteLevel) {
				if (localLevel < remoteLevel) {
					remoteModel = (TopoModel) remoteParent;
					remoteLevel = remoteModel.getLevel();
				} else {
					localModel = (TopoModel) localParent;
					localLevel = localModel.getLevel();
				}
			}
			localParent = localModel.getParent();
			remoteParent = remoteModel.getParent();

			if (localParent != remoteParent) {
				localModel = (TopoModel) localParent;
				remoteModel = (TopoModel) remoteParent;
			}

			if (localModel != null && remoteModel != null) {
				DoubleArrowConnection con = new DoubleArrowConnection();
				con.setSource(localModel);
				con.setTarget(remoteModel);
				localModel.addSourceConnection(con);
				remoteModel.addTargetConnection(con);
			}
		}
	}

	@Deprecated
	public static Network translate(XmlConfig config) {
		Network network = new Network();
		network.setLevel(Network.TOP_LEVEL);
		network.setMappingFile(config.getMappingFile().getPath());
		Subnet subnet = null;
		Router router = null;
		XmlRouter xRouter = null;
		XmlSubnet xSubnet = null;

		for (Iterator<XmlRouter> it = config.getRouters().iterator(); it
				.hasNext();) {
			xRouter = it.next();
			router = translateRouter(xRouter);
			network.addChild(router);
		}

		for (Iterator<XmlSubnet> it = config.getSubnets().iterator(); it
				.hasNext();) {
			xSubnet = it.next();
			subnet = translateSubnet(xSubnet);
			network.addChild(subnet);
		}

		network.setName("Topology");
		return network;
	}

	/**
	 * Translate <XmlRouter> to <Router>.
	 * 
	 * @param xRouter
	 * @return
	 */
	private static Router translateRouter(XmlRouter xRouter) {
		Router router = new Router();
		router.setId(xRouter.getId());
		router.setName(xRouter.getName());
		router.setType(xRouter.getType());
		router.setConfigFile(xRouter.getConfigFile().getPath());

		Rectangle constraint = new Rectangle();
		constraint.x = xRouter.getIcon().getPositionX();
		constraint.y = xRouter.getIcon().getPositionY();
		router.setConstraint(constraint);

		RoutingProtocol protocol = new RoutingProtocol();
		XmlRoutingProtocol xProtocol = xRouter.getRoutingProtocol();
		if (xProtocol != null) {
			protocol.setExteriorGatewayProtocol(xProtocol.getExProtocol());
			protocol.setInteriorGatewayProtocol(xProtocol.getInProtocol());
		}

		router.setProtocol(protocol);

		for (Iterator<XmlDnsServer> it = xRouter.getDnsServers().iterator(); it
				.hasNext();) {
			XmlDnsServer dns = it.next();
			router.addDnsServer(new DnsServer(dns.getIpAddress()));
		}

		for (Iterator<XmlInterface> it = xRouter.getNetInterfaces().iterator(); it
				.hasNext();) {
			XmlInterface i = it.next();
			Interface in = new Interface();
			in.setIpAddress(i.getIpAddress());
			in.setName(i.getName());
			in.setSubnetMask(i.getSubnetMask());
			router.addInterface(in);
		}
		return router;
	}

	/**
	 * Translate <XmlSubnet> to <Subnet>.
	 * 
	 * @param xSubnet
	 * @return
	 */
	private static Subnet translateSubnet(XmlSubnet xSubnet) {
		Subnet subnet = new Subnet();
		subnet.setLevel(xSubnet.getLevel());
		subnet.setId(xSubnet.getId());
		subnet.setName(xSubnet.getName());
		subnet.setType(xSubnet.getType());

		Rectangle constraint = new Rectangle();
		constraint.x = xSubnet.getIcon().getPositionX();
		constraint.y = xSubnet.getIcon().getPositionY();
		subnet.setConstraint(constraint);

		XmlRouter xRouter = null;
		Router router = null;

		for (Iterator<XmlRouter> it = xSubnet.getRouters().iterator(); it
				.hasNext();) {
			xRouter = it.next();
			router = translateRouter(xRouter);
			subnet.addRouter(router);
		}

		for (Iterator<XmlSubnetRef> it = xSubnet.getSubnets().iterator(); it
				.hasNext();) {
			subnet.addSubnetRef(it.next().getId());
		}

		return subnet;
	}

	/**
	 * The Constructor.
	 */
	private XmlUtil() {
	}

	/**
	 * 
	 * @param network
	 * @param path
	 */
	public static void generateMappingFile(Network network, String path) {
		XmlConfigEx config = new XmlConfigEx();
		XmlMappingFileEx mappingFile = new XmlMappingFileEx();
		mappingFile.setPath(network.getMappingFile());
		for (Iterator<TopoModel> it = network.getChildren().iterator(); it
				.hasNext();) {
			TopoModel model = it.next();
			if (model instanceof Subnet) {
				parseXmlSubnetEx(model, config);
			}
			if (model instanceof Router) {
				config.addRouter(parserXmlRouterEx(model));
			}
		}
		try {
			XmlExWriter.writeToXml(config, path);
		} catch (XmlException e) {
			e.printStackTrace();
		}
	}

	private static XmlRouterEx parserXmlRouterEx(TopoModel model) {
		XmlRouterEx router = new XmlRouterEx();
		router.setName(model.getName());
		router.setId(model.getId());
		router.setType(model.getType());
		XmlIconEx icon = new XmlIconEx();
		icon.setPositionX(model.getConstraint().x);
		icon.setPositionY(model.getConstraint().y);
		router.setIcon(icon);
		return router;
	}

	private static void parseXmlSubnetEx(TopoModel model, XmlConfigEx config) {
		XmlSubnetEx subnetEx = new XmlSubnetEx();
		subnetEx.setName(model.getName());
		subnetEx.setId(model.getId());
		subnetEx.setLevel(model.getLevel());
		subnetEx.setType(model.getType());
		XmlIconEx icon = new XmlIconEx();
		icon.setPositionX(model.getConstraint().x);
		icon.setPositionY(model.getConstraint().y);
		subnetEx.setIcon(icon);
		config.addSubnet(subnetEx);

		Subnet subnet = (Subnet) model;
		if (subnet.getSubnets().size() > 0) {
			for (Iterator<Subnet> it = subnet.getSubnets().iterator(); it
					.hasNext();) {
				parseXmlSubnetEx(it.next(), config);
			}
		}
		if (subnet.getRouters().size() > 0) {
			for (Iterator<Router> it = subnet.getRouters().iterator(); it
					.hasNext();) {
				subnetEx.addRouter(parserXmlRouterEx(it.next()));
			}
		}
	}

	/**
	 * Get network from given XML file path.
	 * 
	 * @param path
	 * @return
	 */
	public static Network generateNetwork(String path) {

		XmlConfig config = null;
		XmlConfig configEx = null;
		try {
			config = XmlParser.parseFromXml(path);
			String mappingFile = config.getMappingFile().getPath();
			if (mappingFile != null && !mappingFile.equals("")) {
				configEx = XmlParser.parseFromXml(mappingFile);
				XmlUtil.decorate(config, configEx);
			}

		} catch (XmlException e) {
			e.printStackTrace();
		}

		Network network = new Network();
		XmlUtil.translate(network, config);
		return network;
	}
}
