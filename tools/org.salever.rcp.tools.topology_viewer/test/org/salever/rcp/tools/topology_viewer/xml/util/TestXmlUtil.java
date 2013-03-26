package org.salever.rcp.tools.topology_viewer.xml.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.salever.rcp.tools.topology_viewer.models.Network;
import org.salever.rcp.tools.topology_viewer.xml.XmlConfig;
import org.salever.rcp.tools.topology_viewer.xml.XmlParser;
import org.salever.rcp.tools.topology_viewer.xml.util.XmlException;
import org.salever.rcp.tools.topology_viewer.xml.util.XmlUtil;


public class TestXmlUtil {

	@Test
	public void testDecorator() {
		try {
			XmlConfig c = XmlParser
					.parseFromXml("E:\\Eclipse_Workspace\\_for_TopoDisplay\\com.vire.tools.topology_viewer\\test\\com\\vire\\tools\\topology_viewer\\xml\\util\\topo.xml");
			XmlConfig cc = XmlParser
					.parseFromXml("E:\\Eclipse_Workspace\\_for_TopoDisplay\\com.vire.tools.topology_viewer\\test\\com\\vire\\tools\\topology_viewer\\xml\\util\\mapping.xml");
			XmlUtil.decorate(c, cc);
			assertEquals(150, c.getSubnets().get(0).getRouters().get(0)
					.getIcon().getPositionX());
		} catch (XmlException e) {
			fail("exception");
			e.printStackTrace();
		}
	}

	@Test
	public void testTranslate() {
		try {
			XmlConfig c = XmlParser
					.parseFromXml("E:\\Eclipse_Workspace\\_for_TopoDisplay\\com.vire.tools.topology_viewer\\test\\com\\vire\\tools\\topology_viewer\\xml\\util\\topo_l.xml");
			Network network = new Network();
			XmlUtil.translate(network, c);
			assertEquals(4, network.getChildren().size());
		} catch (XmlException e) {
			fail("exception");
			e.printStackTrace();
		}
	}

}
