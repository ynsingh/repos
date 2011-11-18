package org.iitk.brihaspatisync;

/* @(#)ReflectorManager.java
 * See licence file for usage and redistribution terms
 * Copyright (c) 2007-2008.All Rights Reserved.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedOutputStream;
import org.w3c.dom.NodeList; 
import org.w3c.dom.Node; 
import org.w3c.dom.Document; 
import org.w3c.dom.Element; 
import org.w3c.dom.Attr;
import org.w3c.dom.Text;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import java.util.Vector;
import org.iitk.brihaspatisync.util.ServerLog;
import javax.xml.parsers.ParserConfigurationException;
import javax.servlet.ServletContext;
/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal </a>
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>
 */

public class ReflectorManager 
{
	
	private static ReflectorManager rm=null;
	private final static int maxload = 10;	
	private ServletContext context=null;
	/**
         * This method returns controller for PeerManager class.
         */
	public static ReflectorManager getController(){
		if(rm==null){
			rm=new ReflectorManager();
		}
		return rm;
	}

	public void setContext(ServletContext context1) throws Exception {
                context=context1;
        }
	
	protected String removePeer(String reflector_ip){
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse(getFile());
                        reflector_ip=reflector_ip.replaceAll("/","");
                        NodeList nodeList = doc.getElementsByTagName("IP");
                        for( int i=0; i<nodeList.getLength(); i++ ){
                                Node node = nodeList.item(i);
                                String ip=node.getFirstChild().getNodeValue();
				ServerLog.getController().Log("   asdsadasdasdasd daas ds-------> "+ip);
                                if(ip.equals(reflector_ip)){
					NodeList removenodeList=doc.getElementsByTagName("Reflector");
                		        doc.getDocumentElement().removeChild(removenodeList.item(i));
                     			saveXML(doc);
					ReflectorStatusManager.getController().removeReflector_IP_Peer(reflector_ip);
					removePeer(reflector_ip);
                                }
                        }
                }catch(Exception e){
                        ServerLog.getController().Log("   Error from update Status -------> ");
                }
                return "UnSuccessfull";
	}
	
	protected String removeLoad(String reflector_ip ,String courseid){
		try {
			/********************************************/
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse(getFile());
			/*******************************************/
                        NodeList nodeList = doc.getElementsByTagName("IP");
                        for( int i=0; i<nodeList.getLength(); i++ ){
                                Node node = nodeList.item(i);
                                String ip=node.getFirstChild().getNodeValue();
                                if(ip.equals(reflector_ip)) {
					NodeList coursenodeList = doc.getElementsByTagName("Courses");
					Node course = coursenodeList.item(i);
                                        String courses=course.getFirstChild().getNodeValue();
                                        if((courses.indexOf(courseid))> -1){
						courses=courses.replaceAll(courseid,"");	
						if((courses.indexOf(","))>-1)
							courses=courses.replaceAll(",","");	
					}
					if(courses.length()<1)
						courses="0";
					course.getFirstChild().setNodeValue(courses);
					saveXML(doc);//saveXML();
				}
			}
			nodeList=null;
			nodeList = doc.getElementsByTagName("IP");
                        for( int i=0; i<nodeList.getLength(); i++ ){
                                Node node = nodeList.item(i);
                                String ip=node.getFirstChild().getNodeValue();
				node=null;
                                if(ip.equals(reflector_ip)) {
					NodeList loadnodeList = doc.getElementsByTagName("Load");
                                        node = loadnodeList.item(i);
					String load=node.getFirstChild().getNodeValue();
					int load_int=Integer.parseInt(load);
					load_int=load_int-1;
                                        node.getFirstChild().setNodeValue(Integer.toString(load_int));
                                        saveXML(doc);//saveXML();
                                }
                        }
                }catch(Exception e){
                        ServerLog.getController().Log("   Error from removeLoad load decrease -------> "+e.getMessage());
                }
                return "UnSuccessfull";
        }
	
	protected String searchElement(String courseid) {
		File file=new File(context.getRealPath("Reflector.xml"));
		String message_for_reflector="UnSuccessfull";
		try {
		if(file.exists()) {
			/********************************/
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse(getFile());
			/*******************************/	
			NodeList nodeList = doc.getElementsByTagName("Courses");
			for( int i=0; i<nodeList.getLength(); i++ ) {
				NodeList newnodeList = doc.getElementsByTagName("Load");
	                        Node loadnode = newnodeList.item(i);
        	                String load=loadnode.getFirstChild().getNodeValue();
                	        int load_int=Integer.parseInt(load);
                        	if(load_int<maxload) {
					Node course = nodeList.item(i);
					String courses=course.getFirstChild().getNodeValue();
					if(courses.equals("0")) {
						courses=courseid;
						course.getFirstChild().setNodeValue(courses);
                                	        loadnode.getFirstChild().setNodeValue(Integer.toString(load_int+1));
                                        	saveXML(doc);//saveXML();
	                                        newnodeList = doc.getElementsByTagName("IP");
        	                                loadnode = newnodeList.item(i);
                	                        return loadnode.getFirstChild().getNodeValue();
					} else {
						int k=courses.indexOf(courseid);
						if(k>-1) {
							//if session id exists then load will increase only 
							loadnode.getFirstChild().setNodeValue(Integer.toString(load_int+1));
                	                                saveXML(doc);//saveXML();
							newnodeList = doc.getElementsByTagName("IP");
                                	                loadnode = newnodeList.item(i);
                                        	        return loadnode.getFirstChild().getNodeValue();
	                                     	} else {
							//if session id not exists then now load sessionid and load will increase only 
							// check single session sessionid or doble 
							k=courses.indexOf(",");
                                	        	if(k < 0) {
                        					courses=courses+","+courseid;	
								course.getFirstChild().setNodeValue(courses);
								loadnode.getFirstChild().setNodeValue(Integer.toString(load_int+1));
        	                                        	saveXML(doc);//saveXML();
                	                                	newnodeList = doc.getElementsByTagName("IP");
                        	                        	loadnode = newnodeList.item(i);
                                	                	return loadnode.getFirstChild().getNodeValue();
                                        		}
						}
					}	
				}else
					message_for_reflector="Reflector have insufficient Load !!";	
			}//for
		}else
			message_for_reflector="Reflector is not available !!";		
		}catch(Exception e){}
		return message_for_reflector;
	}

	protected String Register(String reflector_ip, String status){
		try{
			/****************************************/
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse(getFile());
			/*****************************************/
			
                        Element root = doc.getDocumentElement();
                        Element reflector = doc.createElement("Reflector");

                        Element ip = doc.createElement("IP");
                        Text ipText = doc.createTextNode(reflector_ip.replaceAll("/",""));
                        ip.appendChild(ipText);

                        Element courses = doc.createElement("Courses");
                        Text coursesText = doc.createTextNode("0");
                        courses.appendChild(coursesText);

                        Element load = doc.createElement("Load");
                        Text loadText = doc.createTextNode("0");
                        load.appendChild(loadText);

                        Element status_e = doc.createElement("Status");
                        Text statusText = doc.createTextNode(status);
                        status_e.appendChild(statusText);

                        reflector.appendChild(ip);
                        reflector.appendChild(courses);
                        reflector.appendChild(load);
                        reflector.appendChild(status_e);
                        root.appendChild(reflector);
                        return saveXML(doc);//saveXML();
		}catch(Exception ex){
			ServerLog.getController().Log("Error on creating xml file : "+ex.getMessage());
		} return "";
	}
		
	protected String updateStatus(String reflector_ip,String status){
		try {
			/******************************************/
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse(getFile());
			/*****************************************/
			reflector_ip=reflector_ip.replaceAll("/","");
                        NodeList nodeList = doc.getElementsByTagName("IP");
                        for( int i=0; i<nodeList.getLength(); i++ ){	
	                        Node node = nodeList.item(i);
        	                String ip=node.getFirstChild().getNodeValue();
				if(ip.equals(reflector_ip)){
					NodeList statusnodeList = doc.getElementsByTagName("Status");
					Node node1 = statusnodeList.item(i);	
					node1.getFirstChild().setNodeValue(status);
                	       		return saveXML(doc);//saveXML();
				}
			}
		}catch(Exception e){
			ServerLog.getController().Log("   Error from update Status -------> ");
		}	
		return "UnSuccessfull";
	}
	
	private String saveXML(Document doc) {
		try{
			
                        OutputFormat format = new OutputFormat(doc);
                        format.setIndenting(true);
                        XMLSerializer output = new XMLSerializer(new FileOutputStream(context.getRealPath("Reflector.xml")), format);
                        output.serialize(doc);
			return "Successfull";
                }catch(Exception e){ServerLog.getController().Log(e.getMessage());}
		return "UnSuccessfull";
	}

	/**
         * This method is used to create a xml file if it is not exist.
         */

        private File getFile() {
		File file=new File(context.getRealPath("Reflector.xml"));
		
               	if(!file.exists()){
               		try {
				/************************************/
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	                        DocumentBuilder builder = factory.newDocumentBuilder();
				/************************************/
				Document doc = builder.newDocument();
	                        Element rootEle = doc.createElement("Reflector_Peers");
                                doc.appendChild(rootEle);
               	                saveXML(doc);
	               	}catch (Exception ex) { ServerLog.getController().Log("Error in ex "+ex.getMessage()); }
               	} return file;
        }
}//end class
