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
 * Create on Mar 27, 2012 2:48:13 PM
 *******************************************************************************/
package org.xmlsoap.schemas.wsdl.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.xmlsoap.schemas.schema.TComplexType;
import org.xmlsoap.schemas.schema.TElement;
import org.xmlsoap.schemas.schema.TSchema;
import org.xmlsoap.schemas.schema.TSequence;
import org.xmlsoap.schemas.wsdl.ObjectFactory;
import org.xmlsoap.schemas.wsdl.TBinding;
import org.xmlsoap.schemas.wsdl.TBindingOperation;
import org.xmlsoap.schemas.wsdl.TBindingOperationMessage;
import org.xmlsoap.schemas.wsdl.TDefinitions;
import org.xmlsoap.schemas.wsdl.TMessage;
import org.xmlsoap.schemas.wsdl.TOperation;
import org.xmlsoap.schemas.wsdl.TParam;
import org.xmlsoap.schemas.wsdl.TPart;
import org.xmlsoap.schemas.wsdl.TPort;
import org.xmlsoap.schemas.wsdl.TPortType;
import org.xmlsoap.schemas.wsdl.TService;
import org.xmlsoap.schemas.wsdl.TTypes;
import org.xmlsoap.schemas.wsdl.soap.TAddress;
import org.xmlsoap.schemas.wsdl.soap.TBody;
import org.xmlsoap.schemas.wsdl.soap.TStyleChoice;
import org.xmlsoap.schemas.wsdl.soap.UseChoice;

/**
 * @author LiXP
 * 
 */
public class WSDLGenerator {

	/**
	 * @param args
	 * @throws JAXBException
	 * @throws IOException
	 */
	public static void main(String[] args) throws JAXBException, IOException {
		// JAXBContext jc = JAXBContext.newInstance(TDefinitions.class,
		// org.xmlsoap.schemas.wsdl.soap.TBinding.class);
		JAXBContext jc = JAXBContext
				.newInstance("org.xmlsoap.schemas.schema:org.xmlsoap.schemas.wsdl:org.xmlsoap.schemas.wsdl.soap");
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		ObjectFactory factory = new ObjectFactory();
		org.xmlsoap.schemas.wsdl.soap.ObjectFactory soapFactory = new org.xmlsoap.schemas.wsdl.soap.ObjectFactory();
		TDefinitions definitions = factory.createTDefinitions();
		String namespaceURI = "http://www.talend.org/service/";
		String name = "demoService";

		definitions.setTargetNamespace(namespaceURI);
		definitions.setName(name);

		// Create type
		TTypes types = factory.createTTypes();
		// Schema
		TSchema schema = factory.createTSchema();
		schema.setTargetNamespace(namespaceURI);

		{
			TElement element = factory.createTElement();
			element.setName("DemoServiceOperationRequest");

			TComplexType complexType = factory.createTComplexType();
			TSequence sequence = factory.createTSequence();
			TElement childElement = factory.createTElement();
			childElement.setName("in");
			childElement.setType("xsd:string");
			sequence.getElements().add(childElement);
			complexType.getSequences().add(sequence);
			element.getChildren().add(complexType);

			schema.getElements().add(element);
		}

		{
			TElement element = factory.createTElement();
			element.setName("DemoServiceOperationResponse");

			TComplexType complexType = factory.createTComplexType();
			TSequence sequence = factory.createTSequence();
			TElement childElement = factory.createTElement();
			childElement.setName("out");
			childElement.setType("xsd:string");
			sequence.getElements().add(childElement);
			complexType.getSequences().add(sequence);
			element.getChildren().add(complexType);

			schema.getElements().add(element);
		}

		types.getSchemas().add(schema);

		definitions.getAnyTopLevelOptionalElement().add(types);

		// Message
		TMessage requestMessage = factory.createTMessage();
		requestMessage.setName("DemoServiceOperationResponse");
		TPart reqPart = factory.createTPart();
		reqPart.setElement(new QName(namespaceURI,
				"DemoServiceOperationResponse", "tns"));
		reqPart.setName("parameters");
		requestMessage.getPart().add(reqPart);
		definitions.getAnyTopLevelOptionalElement().add(requestMessage);

		TMessage reponseMessage = factory.createTMessage();
		reponseMessage.setName("DemoServiceOperationRequest");
		TPart repPart = factory.createTPart();
		repPart.setElement(new QName(namespaceURI,
				"DemoServiceOperationRequest", "tns"));
		repPart.setName("parameters");
		reponseMessage.getPart().add(repPart);
		definitions.getAnyTopLevelOptionalElement().add(reponseMessage);

		// Port type
		TPortType portType = factory.createTPortType();
		portType.setName("DemoServicePortType");
		TOperation operation = factory.createTOperation();
		operation.setName("DemoServiceOperation");

		TParam inputParam = factory.createTParam();
		inputParam.setMessage(new QName(namespaceURI,
				"DemoServiceOperationRequest", "tns"));

		JAXBElement<TParam> inputParamElement = new JAXBElement<TParam>(
				new QName("http://schemas.xmlsoap.org/wsdl/", "input", "wsdl"),
				TParam.class, inputParam);
		inputParamElement.setNil(false);
		operation.getRest().add(inputParamElement);

		TParam outputParam = factory.createTParam();
		outputParam.setMessage(new QName(namespaceURI,
				"DemoServiceOperationResponse", "tns"));
		JAXBElement<TParam> outputParamElement = new JAXBElement<TParam>(
				new QName("http://schemas.xmlsoap.org/wsdl/", "output", "wsdl"),
				TParam.class, outputParam);
		outputParamElement.setNil(false);
		operation.getRest().add(outputParamElement);
		portType.getOperation().add(operation);
		definitions.getAnyTopLevelOptionalElement().add(portType);

		// Binding
		TBinding binding = factory.createTBinding();
		binding.setName("DemoServiceBinding");
		binding.setType(new QName(namespaceURI, "DemoServicePortType", "tns"));

		// Soap binding
		org.xmlsoap.schemas.wsdl.soap.TBinding soapBinding = soapFactory
				.createTBinding();
		soapBinding.setTransport("http://schemas.xmlsoap.org/soap/http");
		soapBinding.setStyle(TStyleChoice.DOCUMENT);
		binding.getAny().add(soapBinding);

		TBindingOperation bindingOperation = factory.createTBindingOperation();
		bindingOperation.setName("DemoServiceOperation");

		// SOAP operation
		org.xmlsoap.schemas.wsdl.soap.TOperation soapOperation = soapFactory
				.createTOperation();
		soapOperation
				.setSoapAction("http://www.talend.org/service/DemoServiceOperation");
		bindingOperation.getAny().add(soapOperation);

		TBindingOperationMessage bindingOperationMessageIn = factory
				.createTBindingOperationMessage();

		// SOAP body
		TBody soaBodyIn = soapFactory.createTBody();
		soaBodyIn.setUse(UseChoice.LITERAL);
		bindingOperationMessageIn.getAny().add(soaBodyIn);

		bindingOperation.setInput(bindingOperationMessageIn);

		TBindingOperationMessage bindingOperationMessageOut = factory
				.createTBindingOperationMessage();
		bindingOperation.setOutput(bindingOperationMessageOut);

		// SOAP body
		TBody soaBodyOut = soapFactory.createTBody();
		soaBodyOut.setUse(UseChoice.LITERAL);
		bindingOperationMessageOut.getAny().add(soaBodyOut);

		binding.getOperation().add(bindingOperation);
		definitions.getAnyTopLevelOptionalElement().add(binding);

		TService service = factory.createTService();
		service.setName("DemoService");
		TPort port = factory.createTPort();
		port.setName("DemoServicePort");
		port.setBinding(new QName(namespaceURI, "DemoServiceBinding", "tns"));

		// SOAP address
		TAddress soapAddress = soapFactory.createTAddress();
		soapAddress.setLocation("http://localhost:8040/services/DemoService");
		port.getAny().add(soapAddress);

		service.getPort().add(port);

		definitions.getAnyTopLevelOptionalElement().add(service);

		File file = new File("demo.wsdl");
		FileOutputStream stream = new FileOutputStream(file);
		marshaller.marshal(definitions, stream);
		stream.close();
	}
}
