package com.ehelpy.brihaspati4.indexmanager;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class ReadXmlFile{

	String[] details = {"","",""};	
	int k =0;
	public String[] ParseXml(String filetoparse)
	{
		
		try{
			
			File fxmlFile = new File(filetoparse);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(fxmlFile);
		//	Element rootElement = doc.getDocumentElement();					
		//	System.out.println("root element " + rootElement.getNodeName());
		//	System.out.println("---------------------  " + rootElement.getAttribute());
			
			NodeList nlist = doc.getElementsByTagName("Query"+k);
	//		NodeList nlist = doc.getElementsByTagName("Request_for_Indexing");
	//		System.out.println("---------------------  " + nlist.getLength());
	
			for(int i=0; i<nlist.getLength(); i++)
			{
				Node nNode = nlist.item(i);
				
				
		//		System.out.println("current element " + nNode.getNodeName());
		//		System.out.println("nodetype " + nNode.getNodeType());
		//		System.out.println("ELEMENT NODE " + Node.ELEMENT_NODE);
									
				if(nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element eElement = (Element) nNode;					//System.out.println(eElement.getAttribute("id"));
					
					NodeList nodeList = doc.getElementsByTagName("Query_");
					for(int x=0,size= nodeList.getLength(); x<size; x++) 			// to get  tag value from each xml file.
					{						  
					//System.out.println(nodeList.item(x).getAttributes().getNamedItem("tag").getNodeValue());
					details[0] = nodeList.item(x).getAttributes().getNamedItem("tag").getNodeValue();
					}
					details[1] = eElement.getElementsByTagName("hash_id").item(0).getTextContent();				//	System.out.println("Reading hash_id in readxmlfile : " + details);
					details[2] = eElement.getElementsByTagName("node_id").item(0).getTextContent();				//	System.out.println("Reading node_id in readxmlfile : " + nodeid);	
				}
			}
		}
		catch (Exception e)
			{
				e.printStackTrace();
			}
	//System.out.println("\n");
	//	System.out.println("tag value is " + details[0]);
	//	System.out.println("Parsing XML file " + filetoparse +"  and Returning hash_id from ReadXmlFile class to IndexManagement Class  : " + details[1]  + " : its corresponding nodeid is : "+ details[2] );
		return details;
	}
	
}