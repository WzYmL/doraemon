package org.salever.rcp.tools.topology_viewer.xml;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.salever.rcp.tools.topology_viewer.xml.XmlConfig;
import org.salever.rcp.tools.topology_viewer.xml.XmlParser;
import org.salever.rcp.tools.topology_viewer.xml.XmlWriter;
import org.salever.rcp.tools.topology_viewer.xml.util.XmlException;


public class TestXmlParser {

	@Test
	public void testParseFromXml() {
		try {
			XmlConfig c = XmlParser
					.parseFromXml("E:\\Eclipse_Workspace\\_for_TopoDisplay\\com.vire.tools.topology_viewer\\test\\com\\vire\\tools\\topology_viewer\\xml\\topo_l.xml");
			assertNotSame(0, c.getSubnets().size());
			XmlWriter.writeToXml(c, "E:\\Eclipse_Workspace\\_for_TopoDisplay\\com.vire.tools.topology_viewer\\test\\com\\vire\\tools\\topology_viewer\\xml\\topo3.xml");
			//assertNotSame(0, c.getRouters().size());
			//assertNotSame(0, c.getSubnets().size());
		} catch (XmlException e) {
			fail();
			e.printStackTrace();
		}

	}

}
