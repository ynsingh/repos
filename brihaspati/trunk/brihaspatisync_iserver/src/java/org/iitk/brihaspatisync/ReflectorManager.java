package org.iitk.brihaspatisync;

/* @(#)ReflectorManager.java
 * See licence file for usage and redistribution terms
 * Copyright (c) 2007-2008,2014 2013 All Rights Reserved.
 */

import java.util.Vector;
import java.util.StringTokenizer;

import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedOutputStream;


import org.w3c.dom.Node; 
import org.w3c.dom.Attr;
import org.w3c.dom.Text;
import org.w3c.dom.Element; 
import org.w3c.dom.NodeList; 
import org.w3c.dom.Document; 

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.iitk.brihaspatisync.util.ServerLog;
import org.iitk.brihaspatisync.ReflectorStatusManager;


/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal </a> modified data march 2014
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>
 */

public class ReflectorManager 
{
	private static ServletContext context=null;

	public static void setContext(ServletContext context1) throws Exception {
                context=context1;
        }
	
	protected static String removePeer(String reflector_ip){
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse(getFile());
                        reflector_ip=reflector_ip.replaceAll("/","");
                        NodeList nodeList = doc.getElementsByTagName("IP");
                        for( int i=0; i<nodeList.getLength(); i++ ) {
                                Node node = nodeList.item(i);
                                String ip=node.getFirstChild().getNodeValue();
                                if(ip.equals(reflector_ip)) {
					NodeList removenodeList=doc.getElementsByTagName("Reflector");
                		        doc.getDocumentElement().removeChild(removenodeList.item(i));
                     			saveXML(doc);
//					ReflectorStatusManager.removeReflector_IP_Peer(reflector_ip);
					removePeer(reflector_ip);
                                }
                        }
                }catch(Exception e) { ServerLog.log(" Exception in removePeer method of ReflectorManager class "); }
                return "UnSuccessfull";
	}
	
	
	protected static String removeLoad(String sessionid){
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse(getFile());
			// NodeList coursenodeList = doc.getElementsByTagName("Session_Id");
                      //  NodeList nodeList = doc.getElementsByTagName("IP");
                        
			 NodeList nodeList = doc.getElementsByTagName("Session_Id");

			for( int i=0; i<nodeList.getLength(); i++ ){
                                Node node = nodeList.item(i);
				
                                String courses = node.getFirstChild().getNodeValue();
                          //      if(ip.equals(reflector_ip)) {
					//NodeList coursenodeList = doc.getElementsByTagName("Session_Id");
					//Node course = coursenodeList.item(i);
                                        //String courses=course.getFirstChild().getNodeValue();
                                       /* if((courses.indexOf(sessionid))> -1){
						courses=courses.replaceAll(sessionid, "");	
					//	if((courses.indexOf(","))>-1)
					//		courses=courses.replaceAll(",","");	
				//	}*/
					int token_counter = 0;
					String total_sessionid = new String();
					total_sessionid = "";
					StringTokenizer course_token = new StringTokenizer(courses, ",");
					while(course_token.hasMoreElements()) {
						String temp = course_token.nextElement().toString();
						if(!temp.equals(sessionid)){
						if(token_counter ==0){
								total_sessionid =  temp;
								token_counter++;
							}
						else{
							total_sessionid = total_sessionid +","+temp;
							token_counter++;
						}
						}
						temp = "";
					}
						
					
					if(total_sessionid.length()<1){
						total_sessionid="0";
					}
					node.getFirstChild().setNodeValue(total_sessionid);
					saveXML(doc);
					//removeLoad(reflector_ip ,courseid);
					}
				
				
				 
			
	//	}
	//		nodeList=null;
			
			
		//	nodeList = doc.getElementsByTagName("IP");
                  //      for( int i=0; i<nodeList.getLength(); i++ ){
                    //            Node node = nodeList.item(i);
                      //          String ip=node.getFirstChild().getNodeValue();
			//	node=null;
                          //      if(ip.equals(reflector_ip)) {
			//		NodeList loadnodeList = doc.getElementsByTagName("Load");
                          //              node = loadnodeList.item(i);
			//		String load=node.getFirstChild().getNodeValue();
			//		int load_int=Integer.parseInt(load);
			//		load_int=load_int-1;
                          //              node.getFirstChild().setNodeValue(Integer.toString(load_int));
                            //            saveXML(doc);
                              //  }
                       // } 
                }catch(Exception e) { ServerLog.log("   Exception in removeLoad method of ReflectorManager class "+e.getMessage()); }
                return "UnSuccessfull";
        }
	
	private static boolean search_IP_Element(String reflector_ip) {
                File file=new File(context.getRealPath("Reflector.xml"));
                try {
                        if(file.exists()) {
                	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        	DocumentBuilder builder = factory.newDocumentBuilder();
	                        Document doc = builder.parse(getFile());
        	                NodeList nodeList = doc.getElementsByTagName("Session_Id");
                	        for( int i=0; i<nodeList.getLength(); i++ ) {
                        	        NodeList newnodeList = doc.getElementsByTagName("IP");
                                	Node loadnode = newnodeList.item(i);
	                                String ip=loadnode.getFirstChild().getNodeValue();
                                	if(ip.equals(reflector_ip)) 
						return true;		
                        	}
                        }
		} catch(Exception e) { ServerLog.log("   Exception in search_IP_Element method of ReflectorManager class "+e.getMessage()); }
		return false;
	}
			
	protected static String searchElement(String courseid) {
		File file=new File(context.getRealPath("Reflector.xml"));
		String message_for_reflector="UnSuccessfull";
		try {
		if(file.exists()) {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse(getFile());
			NodeList nodeList = doc.getElementsByTagName("Session_Id");
			for( int i=0; i<nodeList.getLength(); i++ ) {
	                       	NodeList newnodeList = doc.getElementsByTagName("IP");
				Node course = nodeList.item(i);
				String courses=course.getFirstChild().getNodeValue();
				if(courses.equals("0")) {
					courses=courseid;
					course.getFirstChild().setNodeValue(courses);
                                       	saveXML(doc);
        	                        Node ipnode = newnodeList.item(i);
                	                return ipnode.getFirstChild().getNodeValue();
				} else {
					int k=courses.indexOf(courseid);
					if(k>-1) {
                                	        Node ipnode = newnodeList.item(i);
                                        	return ipnode.getFirstChild().getNodeValue();
	                            	} else {
						/** if session id not exists then now load sessionid and load will increase only 
						 * check single session sessionid or doble 
						 */ 
						//k=courses.indexOf(",");
                                	       	//if(k < 0) {
                        				courses=courses+","+courseid;	
							course.getFirstChild().setNodeValue(courses);
        	                                       	saveXML(doc);
                        	                       	Node ipnode = newnodeList.item(i);
                                	               	return ipnode.getFirstChild().getNodeValue();
                                        	//}
					}
				}	
			}//for
		}else
			message_for_reflector="Reflector is not available !!";		
		} catch(Exception e) { ServerLog.log(" Exception in searchElement method of ReflectorManager class  "+e.getMessage()); }
		return message_for_reflector;
	}
	

	

	protected static String register(String reflector_ip){
		try{
			if(!search_IP_Element(reflector_ip.replaceAll("/",""))) {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        	                DocumentBuilder builder = factory.newDocumentBuilder();
                	        Document doc = builder.parse(getFile());
                        	Element root = doc.getDocumentElement();
	                        Element reflector = doc.createElement("Reflector");
	
        	                Element ip = doc.createElement("IP");
                	        Text ipText = doc.createTextNode(reflector_ip.replaceAll("/",""));
                        	ip.appendChild(ipText);

	                        Element courses = doc.createElement("Session_Id");
        	                Text coursesText = doc.createTextNode("0");
                	        courses.appendChild(coursesText);
			//	reflector.setAttribute("SESSIONID", sessionid);


	                        reflector.appendChild(ip);
        	                reflector.appendChild(courses);
	                        root.appendChild(reflector);
        	                return saveXML(doc);
			}	
		}catch(Exception ex) { ServerLog.log(" Exception in update Status method of ReflectorManager class  "+ex.getMessage());	} 
		return "";
	}
		
	private static String saveXML(Document doc) {
		try{
                        OutputFormat format = new OutputFormat(doc);
                        format.setIndenting(true);
                        XMLSerializer output = new XMLSerializer(new FileOutputStream(context.getRealPath("Reflector.xml")), format);
                        output.serialize(doc);
			return "Successfull";
                }catch(Exception e){ ServerLog.log(" Exception in saveXML method of ReflectorManager class "+e.getMessage());}
		return "UnSuccessfull";
	}

	/**
         * This method is used to create a xml file if it is not exist.
         */

        private static File getFile() {
		File file=new File(context.getRealPath("Reflector.xml"));
               	if(!file.exists()) {
               		try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	                        DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.newDocument();
	                        Element rootEle = doc.createElement("Reflector_Peers");
                                doc.appendChild(rootEle);
               	                saveXML(doc);
	               	} catch (Exception ex) { ServerLog.log(" Exception in getFile() method of ReflectorManager class "+ex.getMessage()); }
               	} return file;
        }
}//end class
