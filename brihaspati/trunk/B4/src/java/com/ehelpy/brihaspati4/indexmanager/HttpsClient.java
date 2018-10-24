package com.ehelpy.brihaspati4.indexmanager;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class HttpsClient extends IndexManagement{
		
	public void client(String self_nodeid,String successor) throws Exception
	{
		
	String rxv_ip = rout.get_ip_address(successor);				// here finding the ip address of the recived successor from routing class method get_ip_address
//	System.out.println(rxv_ip);
    Socket sock = new Socket(rxv_ip, 2316);
    
    if(!sock.isConnected())
        System.out.println("Socket Connection Not established");
    else
        System.out.println("Socket Connection established : "+sock.getInetAddress());
    
  /*  File myfile = new File(file_to_pass);
    
    if(!myfile.exists())
        System.out.println("File Not Existing.");
    else
        System.out.println("File Existing.");*/
    
    byte[] mybytearray = new byte[1024];
    
    InputStream is = sock.getInputStream();
    
    DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();			// here is the code to create an empty xml file in which these details will be copied
	DocumentBuilder db = f.newDocumentBuilder();
	Document doc = db.newDocument();
	
	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	Transformer transformer = transformerFactory.newTransformer();
	DOMSource source = new DOMSource(doc);
	StreamResult result = new StreamResult(new  FileOutputStream("IndexCopyof"+self_nodeid+".xml"));
	transformer.transform(source, result);
	
	/*
	 t.transform(new DOMSource(doc), new StreamResult(new FileOutputStream("E:/Personal/Studies/M.Tech/Thesis/Code_Detail.xml"))); */
	System.out.println("XML file Generated.");
    
    
    File fff =  new File("IndexCopyof"+self_nodeid+".xml");
	FileOutputStream fos = new FileOutputStream(fff);
    BufferedOutputStream bos = new BufferedOutputStream(fos);
    int bytesRead = is.read(mybytearray, 0, mybytearray.length);
    bos.write(mybytearray, 0, bytesRead);
    bos.close();
    
  //  sock.setKeepAlive(false);
  //  sock.setSoLinger(true, 0);
 //   sock.setReuseAddress(true);
   // sock.setTcpNoDelay(true);
    //sock.setSoTimeout(15);
   sock.close();
    System.out.println("File Transfered...");
	}
	
	
	public void client_handle_request(String successor) throws Exception
	{
	System.out.println("Request received at successor " + successor);	
	String rxv_ip = rout.get_ip_address(successor);				// here finding the ip address of the recived successor from routing class method get_ip_address
//	System.out.println(rxv_ip);
    Socket sock = new Socket(rxv_ip, 2315);
    
    if(!sock.isConnected())
        System.out.println("Socket Connection Not established");
    else
        System.out.println("Socket Connection established : "+sock.getInetAddress());
    
    
    byte[] mybytearray = new byte[1024];
    
    InputStream is = sock.getInputStream();
    
    DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();			// here is the code to create an empty xml file in which these details will be copied
	DocumentBuilder db = f.newDocumentBuilder();
	Document doc = db.newDocument();
	
	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	Transformer transformer = transformerFactory.newTransformer();
	DOMSource source = new DOMSource(doc);
	StreamResult result = new StreamResult(new  FileOutputStream("Received_Request.xml"));
	transformer.transform(source, result);
	
	System.out.println("XML file Generated.");
        
    File fff =  new File("Received_Request.xml");
	FileOutputStream fos = new FileOutputStream(fff);
    BufferedOutputStream bos = new BufferedOutputStream(fos);
    int bytesRead = is.read(mybytearray, 0, mybytearray.length);
    bos.write(mybytearray, 0, bytesRead);
    bos.close();
    sock.close();
    System.out.println("File Transfered...");
  //  System.out.println("Hello");
    String query = "Received_Request.xml";					// here i am pasrsing the request (which contains xml file name creating a request) to xml parser to check the tag value
    String details = "";
    try{
		
		File fxmlFile = new File(query);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db1 = dbf.newDocumentBuilder();
		Document doc1 = db1.parse(fxmlFile);
	//	Element rootElement = doc.getDocumentElement();					
	//	System.out.println("root element " + rootElement.getNodeName());
	//	System.out.println("---------------------  " + rootElement.getAttribute());
		
		NodeList nlist = doc1.getElementsByTagName("Request_for_Indexing");
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
				
				NodeList nodeList = doc1.getElementsByTagName("Query_");
				for(int x=0,size= nodeList.getLength(); x<size; x++) 			// to get  tag value from each xml file.
				{						  
				//System.out.println(nodeList.item(x).getAttributes().getNamedItem("tag").getNodeValue());
				details = nodeList.item(x).getAttributes().getNamedItem("tag").getNodeValue();
				}
				//details[1] = eElement.getElementsByTagName("hash_id").item(0).getTextContent();				//	System.out.println("Reading hash_id in readxmlfile : " + details);
				//details[2] = eElement.getElementsByTagName("node_id").item(0).getTextContent();				//	System.out.println("Reading node_id in readxmlfile : " + nodeid);	
			}
		}
	}
	catch (Exception e)
		{
			e.printStackTrace();
		}
    
     // System.out.println(details);
	 if(details.equals("0002"))
	 {
		 indmgt.Generate_Indexing();
		 indmgt.Send_Indexing();
	 }
	 else
		 System.out.println("Ye kya hai");
     
    
	}
	
}

