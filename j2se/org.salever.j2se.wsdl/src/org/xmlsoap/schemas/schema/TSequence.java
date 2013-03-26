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
 * Create on Aug 1, 2012 3:46:00 PM
 *******************************************************************************/
package org.xmlsoap.schemas.schema;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

import org.xmlsoap.schemas.wsdl.TExtensibleDocumented;

/**
 * @author LiXP
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tSequence", propOrder = { "elements" })
public class TSequence extends TExtensibleDocumented {

	@XmlElements({ @XmlElement(name = "element", type = TElement.class) })
	protected List<TElement> elements;

	public List<TElement> getElements() {
		if (elements == null) {
			elements = new ArrayList<TElement>();
		}
		return elements;
	}

}