/**
 * 
 */
package org.salever.j2se.common.xml;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * @author LiXP
 * 
 */
public class StAXWriter {

	public static void main(String[] args) throws XMLStreamException,
			IOException {
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		Writer stream = new FileWriter("StAXWriter.xml");
		XMLStreamWriter writer = factory.createXMLStreamWriter(stream);

		// 产生XML开头
		writer.writeStartDocument();
		// 写元素
		writer.writeStartElement("font");
		// 写属性
		writer.writeAttribute("size", "10");
		// 写文本
		writer.writeCharacters("text");
		// 元素关闭
		writer.writeEndElement();
		// 写空元素
		writer.writeEmptyElement("emptyElement");
		// 产生XML结尾
		writer.writeEndDocument();
		// 刷新缓冲，输出XML
		writer.flush();
	}
}
