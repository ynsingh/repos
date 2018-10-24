package com.ehelpy.brihaspati4.routingtable;
//19 May 2017 1240
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlParsing {
	public static String selfID = null;
	public static String[] rTable = new String[120];
	public static String [] predSuccTable = new String [8];

	// Method to get SElf ID from xml file
	public static String getSelfID() {
		try {
			File inputFile = new File("NewSelfRouteTable_router.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory	.newInstance();
			dbFactory.setNamespaceAware(true); // never forget this!
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			// Getting selfID from xml file using X Path
			Element root = doc.getDocumentElement();
			selfID = root.getAttribute("LocalNodeID");
				} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return selfID;
	}

	// Method to get P[],S[] and M[]

	public static String [] getRTableEntries() {
		try {
			File inputFile = new File("NewSelfRouteTable_router.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			dbFactory.setNamespaceAware(true); // never forget this!
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			XPath xPath = XPathFactory.newInstance().newXPath();
			String expression = "RoutingTable/column/row/NodeID/text()";
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
					doc, XPathConstants.NODESET);
			// Storing all <nodeID> under element <col> in Array rTable[]
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node nNode = nodeList.item(i);
				if(nNode.getNodeValue()==null){
					rTable[i]=null;
				}
				else
				rTable[i] = nNode.getNodeValue();
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return rTable;
	}

	// Method to get my  4 Predecessors and 4 successors  on the ring

		public static String[] getPredAndSuccArray() {
			try {
				File inputFile = new File("PredAndSucc.xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory	.newInstance();
				dbFactory.setNamespaceAware(true); // never forget this!
				DocumentBuilder dBuilder;
				dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(inputFile);
				doc.getDocumentElement().normalize();
				XPath xPath = XPathFactory.newInstance().newXPath();
				String expression = "routingTable/col/nodeID/text()";
				NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
						doc, XPathConstants.NODESET);
				// Storing all <nodeID> under element <col> in Array rTable[]
				for (int i = 0; i < nodeList.getLength(); i++) {
					Node nNode = nodeList.item(i);					
					predSuccTable[i] = nNode.getNodeValue();
				}

			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XPathExpressionException e) {
				e.printStackTrace();
			}
			return predSuccTable;
		}
}
