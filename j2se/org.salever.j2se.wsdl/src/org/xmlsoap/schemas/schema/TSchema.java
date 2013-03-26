/*******************************************************************************
 * Copyright (c) 2010 liXiaopeng. All rights reserved. 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *
 * Contributors:
 *     LiXiaopeng - initial API and implementation
 *
 * Create on Aug 1, 2012 3:44:44 PM
 *******************************************************************************/
package org.xmlsoap.schemas.schema;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author LiXP
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tSchema", propOrder = { "elements" })
@XmlRootElement(name = "schema")
public class TSchema {

	@XmlElements({ @XmlElement(name = "element", type = TElement.class) })
	protected List<TElement> elements;
	@XmlAttribute
	@XmlSchemaType(name = "anyURI")
	protected String targetNamespace;

	/**
	 * Gets the value of the anyTopLevelOptionalElement property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the anyTopLevelOptionalElement property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getElements().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link TElement }
	 * 
	 */
	public List<TElement> getElements() {
		if (elements == null) {
			elements = new ArrayList<TElement>();
		}
		return this.elements;
	}

	/**
	 * Gets the value of the targetNamespace property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTargetNamespace() {
		return targetNamespace;
	}

	/**
	 * Sets the value of the targetNamespace property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTargetNamespace(String value) {
		this.targetNamespace = value;
	}

}
