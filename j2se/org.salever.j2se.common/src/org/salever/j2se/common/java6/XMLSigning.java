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
 * Create on Apr 10, 2012 2:21:37 PM
 *******************************************************************************/
package org.salever.j2se.common.java6;

import java.io.IOException;
import java.io.PrintStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Collections;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author LiXP
 * 
 */
public class XMLSigning {

	/**
	 * @param args
	 * @throws SOAPException
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws NoSuchAlgorithmException
	 * @throws TransformerException
	 * @throws KeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws XMLSignatureException
	 * @throws MarshalException
	 * @throws javax.xml.crypto.MarshalException
	 */
	public static void main(String[] args) throws SOAPException,
			ParserConfigurationException, SAXException, IOException,
			NoSuchAlgorithmException, TransformerException,
			InvalidAlgorithmParameterException, KeyException, MarshalException,
			XMLSignatureException, javax.xml.crypto.MarshalException {
		SOAPMessage soapMessage = createSOAPMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();
		Source source = soapPart.getContent();

		Node root = generateDOM(source);
		dumpDocument(root);
		KeyPair keypair = generateDSAKeyPair();
		XMLSignature sig = generateXMLSignature(keypair);
		System.out.println("Signing the message...");
		signTree(root, keypair.getPrivate(), sig);
		dumpDocument(root);
		System.out.println("Validate the signature...");
		boolean valid = validateXMLSignature(keypair.getPublic(), root, sig);
		System.out.println("Signature valid? " + valid);
	}

	private static SOAPMessage createSOAPMessage() throws SOAPException {
		SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();
		SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
		SOAPHeader soapHeader = soapEnvelope.getHeader();
		SOAPHeaderElement headerElement = soapHeader
				.addHeaderElement(soapEnvelope.createName("Signature",
						"SOAP-SEC",
						"http://schemas.xmlsoap.org/soap/security/2000-12"));
		SOAPBody soapBody = soapEnvelope.getBody();
		soapBody.addAttribute(soapEnvelope.createName("id", "SOAP-SEC",
				"http://schemas.xmlsoap.org/soap/security/2000-12"), "Body");
		Name bodyName = soapEnvelope.createName("FooBar", "z",
				"http://example.com");
		SOAPBodyElement gltp = soapBody.addBodyElement(bodyName);
		return soapMessage;
	}

	private static Node generateDOM(Source source)
			throws ParserConfigurationException, SAXException, IOException {
		Node root;
		if (source instanceof DOMSource) {
			root = ((DOMSource) source).getNode();
		} else if (source instanceof SAXSource) {
			InputSource inSource = ((SAXSource) source).getInputSource();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = null;
			synchronized (dbf) {
				db = dbf.newDocumentBuilder();
			}
			Document doc = db.parse(inSource);
			root = (Node) doc.getDocumentElement();
		} else {
			throw new IllegalArgumentException("Class type: "
					+ source.getClass().getName());
		}
		return root;
	}

	private static KeyPair generateDSAKeyPair() throws NoSuchAlgorithmException {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
		kpg.initialize(1024, new SecureRandom());
		return kpg.generateKeyPair();
	}

	private static XMLSignature generateXMLSignature(KeyPair keypair)
			throws NoSuchAlgorithmException,
			InvalidAlgorithmParameterException, KeyException {
		XMLSignatureFactory sigFactory = XMLSignatureFactory.getInstance();
		Reference ref = sigFactory.newReference("#Body",
				sigFactory.newDigestMethod(DigestMethod.SHA1, null));
		SignedInfo signedInfo = sigFactory.newSignedInfo(sigFactory
				.newCanonicalizationMethod(
						CanonicalizationMethod.INCLUSIVE_WITH_COMMENTS,
						(C14NMethodParameterSpec) null), sigFactory
				.newSignatureMethod(SignatureMethod.DSA_SHA1, null),
				Collections.singletonList(ref));
		KeyInfoFactory kif = sigFactory.getKeyInfoFactory();
		KeyValue kv = kif.newKeyValue(keypair.getPublic());
		KeyInfo keyInfo = kif.newKeyInfo(Collections.singletonList(kv));
		return sigFactory.newXMLSignature(signedInfo, keyInfo);
	}

	private static void signTree(Node root, PrivateKey privateKey,
			XMLSignature sig) throws MarshalException, XMLSignatureException,
			javax.xml.crypto.MarshalException {
		Element envelope = getFirstChildElement(root);
		Element header = getFirstChildElement(envelope);
		DOMSignContext sigContext = new DOMSignContext(privateKey, header);
		sigContext.putNamespacePrefix(XMLSignature.XMLNS, "ds");
		sigContext.setIdAttributeNS(getNextSiblingElement(header),
				"http://schemas.xmlsoap.org/soap/security/2000-12", "id");
		sig.sign(sigContext);
	}

	private static boolean validateXMLSignature(PublicKey publicKey, Node root,
			XMLSignature sig) throws XMLSignatureException {
		Element envelope = getFirstChildElement(root);
		Element header = getFirstChildElement(envelope);
		Element sigElement = getFirstChildElement(header);
		DOMValidateContext valContext = new DOMValidateContext(publicKey,
				sigElement);
		valContext.setIdAttributeNS(getNextSiblingElement(header),
				"http://schemas.xmlsoap.org/soap/security/2000-12", "id");
		return sig.validate(valContext);
	}

	private static void dumpDocument(Node root) throws TransformerException {
		PrintStream console = System.out;
		console.printf("%n");
		Transformer transformer = TransformerFactory.newInstance()
				.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(new DOMSource(root), new StreamResult(console));
		console.printf("%n");
	}

	private static Element getFirstChildElement(Node node) {
		Node child = node.getFirstChild();
		while ((child != null) && (child.getNodeType() != Node.ELEMENT_NODE)) {
			child = child.getNextSibling();
		}
		return (Element) child;
	}

	public static Element getNextSiblingElement(Node node) {
		Node sibling = node.getNextSibling();
		while ((sibling != null)
				&& (sibling.getNodeType() != Node.ELEMENT_NODE)) {
			sibling = sibling.getNextSibling();
		}
		return (Element) sibling;
	}
}