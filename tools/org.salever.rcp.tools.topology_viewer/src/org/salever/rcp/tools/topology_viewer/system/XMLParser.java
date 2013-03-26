/**
 * ConfigParser.java
 * salever@126.com
 * 2009-8-12下午03:02:33
 */
package org.salever.rcp.tools.topology_viewer.system;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.util.NLS;
import org.salever.rcp.tools.topology_viewer.editors.TextEditorInput;
import org.salever.rcp.tools.topology_viewer.models.DoubleArrowConnection;
import org.salever.rcp.tools.topology_viewer.models.Router;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * @author salever@126.com
 * 
 */
@Deprecated
public class XMLParser {

	public static XMLParser newInstance() {
		if (parser != null) {
			return parser;
		}
		return new XMLParser();
	}

	private boolean missing = false;

	private static XMLParser parser;
	private HashMap<String, Router> routersMap;

	private String mappingFilePath;
	/**
	 * 工厂变量
	 */
	private DocumentBuilderFactory docBuilderFac;

	private DocumentBuilder docBuilder;

	/**
	 * 输入流
	 */
	private InputStream in;

	/**
	 * 构造函数
	 */
	private XMLParser() {

		docBuilderFac = DocumentBuilderFactory.newInstance();
		try {
			docBuilder = docBuilderFac.newDocumentBuilder();
		} catch (Exception e) {
			return;
		}
		routersMap = new HashMap<String, Router>();

	}

	public String getMappingFilePath() {
		return mappingFilePath;
	}

	public HashMap<String, Router> getRouters(String path) {
		parseRouterInfoFromFile(path);
		parseConnectionInfoFromFile(path);
		if (missing) {
			MessageDialog.openWarning(null, Messages
					.getString("Dialog.Warning_Title"), Messages
					.getString("Dialog.Warning_Text"));
		}
		return routersMap;
	}

	private void parseConnectionInfoFromFile(String filename) {

		if (routersMap.size() == 0) {
			return;
		}
		try {
			in = new FileInputStream(filename);
			Document doc = docBuilder.parse(in);
			Element root = doc.getDocumentElement();
			NodeList firstLevNodes = root.getChildNodes();

			for (int index = 0; index < firstLevNodes.getLength(); index++) {

				if (!firstLevNodes.item(index).getNodeName().equals("links")) {
					continue;
				}
				NodeList secondLevNodes = firstLevNodes.item(index)
						.getChildNodes();
				for (int j = 0; j < secondLevNodes.getLength(); j++) {
					String localRouter = "";
					String remoteRouter = "";
					Node secondLevNode = secondLevNodes.item(j);

					if (secondLevNode.getNodeName().equals("link")) {
						localRouter = secondLevNode.getAttributes()
								.getNamedItem("local_router").getNodeValue();
						remoteRouter = secondLevNode.getAttributes()
								.getNamedItem("remote_router").getNodeValue();

						if (!localRouter.equals("")) {
							DoubleArrowConnection model = new DoubleArrowConnection();
							Router srcRouter = routersMap.get(localRouter);

							Router tagRouter = routersMap.get(remoteRouter);
							if (srcRouter == null) {
								missing = true;
								continue;
							}
							if (tagRouter == null) {
								missing = true;
								continue;
							}
							model.setSource(srcRouter);
							model.setTarget(tagRouter);
							srcRouter.addSourceConnection(model);
							tagRouter.addTargetConnection(model);
						}
					}
				}
			}
		} catch (Exception e) {

		}
	}

	/**
	 * 提取路由器信息
	 */
	private void parseRouterInfoFromFile(String filename) {

		missing = false;
		routersMap.clear();

		try {

			in = new FileInputStream(filename);
			Document doc = docBuilder.parse(in);
			Element root = doc.getDocumentElement();
			NodeList firstLevNodes = root.getChildNodes();

			for (int index = 0; index < firstLevNodes.getLength(); index++) {

				if (firstLevNodes.item(index).getNodeName().equals(
						"mapping_file")) {

					Node secondLevNode = firstLevNodes.item(index);
					mappingFilePath = secondLevNode.getAttributes().item(0)
							.getNodeValue();

					continue;
				}

				if (!firstLevNodes.item(index).getNodeName().equals("routers")) {
					continue;
				}

				NodeList secondLevNodes = firstLevNodes.item(index)
						.getChildNodes();

				for (int i = 0; i < secondLevNodes.getLength(); ++i) {

					Node secondLevNode = secondLevNodes.item(i);

					String name = "";
					String id = "";
					String type = "";
					String position_x = "";
					String position_y = "";
					String configFilePath = "";

					if (secondLevNode.getNodeName().equals("router")) {
						id = secondLevNode.getAttributes().getNamedItem("id")
								.getNodeValue();
						name = secondLevNode.getAttributes().getNamedItem(
								"name").getNodeValue();
						type = secondLevNode.getAttributes().getNamedItem(
								"type").getNodeValue();

						for (Node thirdLevNode = secondLevNode.getFirstChild(); thirdLevNode != null; thirdLevNode = thirdLevNode
								.getNextSibling()) {
							if (thirdLevNode.getNodeName().equals("icon")) {
								position_x = thirdLevNode.getAttributes()
										.getNamedItem("position_x")
										.getNodeValue();
								position_y = thirdLevNode.getAttributes()
										.getNamedItem("position_y")
										.getNodeValue();
							} else if (thirdLevNode.getNodeName().equals(
									"config_file")) {
								configFilePath = thirdLevNode.getAttributes()
										.getNamedItem("path").getNodeValue();
							}

						}
					}
					if (!id.equals("")) {

						Router router = new Router();
						// router.setId(id);
						router.setType(type);
						router.setName(name);
						router.setConfigFile(configFilePath);
						router.setInput(new TextEditorInput(configFilePath));
						int width = 40;
						if (name.length() >= 5) {
							width = name.length() * 7;
						}
						router.setConstraint(new Rectangle(
								string2Int(position_x), string2Int(position_y),
								width, 50));
						if (routersMap.put(id, router) != null) {
							MessageDialog
									.openError(
											null,
											Messages
													.getString("Dialog.Error_Title"),
											NLS
													.bind(
															Messages
																	.getString("Dialog.XML_Error_Message_1"),
															id));
							routersMap.clear();
							return;
						}
					}

				}
			}
		} catch (Exception e) {

		}
	}

	private int string2Int(String str) {
		return Integer.parseInt(str);
	}

}
