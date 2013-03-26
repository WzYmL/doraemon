/** Copyright (c) 2010 salever@126.com. All rights reserved. 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *
 * Contributors:
 *     salever@126.com - initial API and implementation
 *
 * Create on  上午10:11:21 2011-8-18 ye2011
 *******************************************************************************/
package org.salever.rcp.tools.springxmlgenerator.xml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ICompilationUnit;
import org.salever.rcp.tools.springxmlgenerator.utils.JavaClazzUtils;

/**
 * @author LiXP
 * 
 */
public class SpringXMLWriter {

	private static final String BEANS = "beans";
	private static final String BEAN = "bean";

	private static void buildBeanElement(Element beansElement,
			List<ICompilationUnit> units) {
		for (ICompilationUnit unit : units) {
			Element beanElement = beansElement.addElement(BEAN);
			beanElement.addAttribute("id", JavaClazzUtils.getBeanId(unit));
			beanElement
					.addAttribute("class", JavaClazzUtils.getBeanClass(unit));
		}
	}

	private static Element createBeansElement(Document document) {
		Element beansElement = document.addElement(BEANS);
		beansElement.addAttribute("xmlns:xsi",
				"http://www.w3.org/2001/XMLSchema-instance");
		beansElement
				.addAttribute(
						"xsi:schemaLocation",
						"http://www.springframework.org/schema/beans\n     http://www.springframework.org/schema/beans/spring-beans-3.0.xsd");
		beansElement.addAttribute("xmlns",
				"http://www.springframework.org/schema/beans");
		return beansElement;
	}

	/**
	 * 
	 * @param units
	 * @return
	 */
	private static Document createXMLDocument(List<ICompilationUnit> units) {
		Document document = DocumentHelper.createDocument();
		Element beansElement = createBeansElement(document);
		buildBeanElement(beansElement, units);
		return document;
	}

	/**
	 * 
	 * @param units
	 * @param xmlPath
	 * @return
	 * @throws IOException
	 */
	public static IPath writeSpringXML(List<ICompilationUnit> units,
			IPath xmlPath) throws IOException {

		IPath filePath = JavaClazzUtils.getXMLFilePath(xmlPath);
		Document document = createXMLDocument(units);
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setNewlines(true);
		format.setTrimText(true);
		format.setPadText(true);
		XMLWriter output = new XMLWriter(new FileWriter(filePath.toFile()),
				format);
		output.write(document);
		output.close();

		return filePath;
	}
}
