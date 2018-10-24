package com.ehelpy.brihaspati4.indexmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.Map;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

// Sqn ldr Sandeep Sharma Dated 15 Apr 2017 ; 1255 Hrs
// this code is only for simulation purpose
// Here i have made a code to create xml files from hashmap entries 

public class BuildXml {
	
	public void convert_hashmap_toxml(Map<String, String> myindex1) throws FileNotFoundException, TransformerException, ParserConfigurationException
	{
		//System.out.println("IIndextable " + myindex1);
		Collection<String> hash_id_extracted = myindex1.keySet();			/// code to extract hash_id from array by first converting it into collection then to an array
		Object[] hashid_array = hash_id_extracted.toArray();
		
		Collection<String> node_id_extracted = myindex1.values();/// code to extract node_id from array by first converting it into collection then to an array
		Object[] nodeid_array = node_id_extracted.toArray();
		
	/*	for(int i=0;i<hashid_array.length;i++)
			System.out.println(hashid_array[i]);
		
		for(int j=0;j<nodeid_array.length;j++)
			System.out.println(nodeid_array[j]);
			
		
		System.out.println(myindex1.keySet());
		System.out.println(myindex1.values());*/
		
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = f.newDocumentBuilder();
		Document doc = db.newDocument();
		
		Element rootele = doc.createElement("XML");
		doc.appendChild(rootele);
		
		int i=0;
		//System.out.println("hashid array length " + hashid_array.length);	
		for(int j=0; j<hashid_array.length;j++)
			{			
			Element codeele = doc.createElement("xml");
			rootele.appendChild(codeele);
			((Element)codeele).setAttribute("tag", Integer.toString(i));			// here using i as tagvalue
			i++;	
			
			Element hashidele = doc.createElement("hash_id");
			hashidele.appendChild(doc.createTextNode((String) hashid_array[j]));
			codeele.appendChild(hashidele);
			
			Element nodeidele = doc.createElement("node_id");
			nodeidele.appendChild(doc.createTextNode((String) nodeid_array[j]));
			codeele.appendChild(nodeidele);
		
			}
			
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		
		StreamResult result = new StreamResult(new FileOutputStream("XML.xml"));
		transformer.transform(source, result);
	
		
	}
	
	public File returnxmlFile() 
	{ 
	return (new File("XML.xml")); 
	}
	
  }
