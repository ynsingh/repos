package com.ehelpy.brihaspati4.indexmanager;

import java.io.FileOutputStream;	
import java.util.Random;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

// Sqn ldr Sandeep Sharma Dated 15 Apr 2017 ; 1255 Hrs
// this code is only for simulation purpose
// Here i have made a code to generate xml files whose tags will be randomly chosen out of 
//0000 (signifies a query of type query where one node wants to communicate to another node) or 
//0001	(signifies a query of type advertisement where one newly joined node wants to make his entry in index table of its root node)
public class CreateXmlFile {
	public static void main (String args[]) throws Exception
	{
		for(int i =0;i<10;i++)
		{
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = f.newDocumentBuilder();
		Document doc = db.newDocument();
		
		Element rootele = doc.createElement("Query"+i);
		Element codeele = doc.createElement("Query_");
		Element hashidele = doc.createElement("hash_id");
		Element nodeidele = doc.createElement("node_id");
		Element ipele = doc.createElement("ip_address");
		Element portele = doc.createElement("port_no");
		
			
		String[] tag = {"0000", "0001"};
        Random random = new Random();

        int select = random.nextInt(tag.length); 			/// randomly chooses the string index from the string array
        
        String tagvalue = tag [select];						// use random index to store corresponding string value in another string
        
        System.out.println("Random String selected: " + tagvalue);			// prints out the value at the randomly selected index
		
		((Element)codeele).setAttribute("tag", tagvalue);
		
		Text t1 = doc.createTextNode("cdef"+i);
		Text t2 = doc.createTextNode("1234"+i);
		Text t3 = doc.createTextNode("192.36.45.22"+i);
		Text t4 = doc.createTextNode("2367"+i);
		
		hashidele.appendChild(t1);
		nodeidele.appendChild(t2);
		ipele.appendChild(t3);
		portele.appendChild(t4);
		
		codeele.appendChild(hashidele);
		codeele.appendChild(nodeidele);
		codeele.appendChild(ipele);
		codeele.appendChild(portele);
		
		rootele.appendChild(codeele);
		doc.appendChild(rootele);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new  FileOutputStream("Query"+i+".xml"));
		transformer.transform(source, result);
		}
		/*
		 t.transform(new DOMSource(doc), new StreamResult(new FileOutputStream("E:/Personal/Studies/M.Tech/Thesis/Code_Detail.xml"))); */
		System.out.println("XML file Generated.");
		
		
		
	}
	
}
