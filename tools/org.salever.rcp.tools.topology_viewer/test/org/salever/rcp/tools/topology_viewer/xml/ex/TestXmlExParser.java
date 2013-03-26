package org.salever.rcp.tools.topology_viewer.xml.ex;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.salever.rcp.tools.topology_viewer.xml.ex.XmlConfigEx;
import org.salever.rcp.tools.topology_viewer.xml.ex.XmlExParser;
import org.salever.rcp.tools.topology_viewer.xml.ex.XmlExWriter;
import org.salever.rcp.tools.topology_viewer.xml.util.XmlException;


public class TestXmlExParser {

	@Test
	public void testParseFromXml() {
		try {
			XmlConfigEx c = XmlExParser
					.parseFromXml("E:\\Eclipse_Workspace\\_for_TopoDisplay\\com.vire.tools.topology_viewer\\test\\com\\vire\\tools\\topology_viewer\\xml\\ex\\topo.xml");
			assertNotSame(0, c.getSubnets().size());
			XmlExWriter.writeToXml(c, "E:\\Eclipse_Workspace\\_for_TopoDisplay\\com.vire.tools.topology_viewer\\test\\com\\vire\\tools\\topology_viewer\\xml\\ex\\topo1.xml");
			//assertNotSame(0, c.getRouters().size());
			//assertNotSame(0, c.getSubnets().size());
		} catch (XmlException e) {
			fail();
			e.printStackTrace();
		}

	}

}
