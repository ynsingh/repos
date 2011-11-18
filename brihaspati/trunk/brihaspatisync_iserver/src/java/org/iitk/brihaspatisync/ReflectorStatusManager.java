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
 */

public class ReflectorStatusManager 
{
	
	private static ReflectorStatusManager rm=null;
	//private Document doc=null;
        //private DocumentBuilder db=null;
	private ServletContext context=null;
	
	/**
         * This method returns controller for PeerManager class.
         */
	public static ReflectorStatusManager getController(){
		if(rm==null){
			rm=new ReflectorStatusManager();
		}
		return rm;
	}
	
	public void setContext(ServletContext context1) throws Exception {
                context=context1;
        }
	
	protected String searchElement(String userid,String sessionid,String RoleId) {
		String message_ip="UnSuccessfull";
		try {
		/*************************************/	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(getFile());
		/*************************************/
		Element root = doc.getDocumentElement();
                NodeList peerList = doc.getElementsByTagName("ReflectorStatus");	
		for( int i=0; i<peerList.getLength(); i++ ) {
               		Node node = peerList.item(i);
                        if( node.getNodeType() == node.ELEMENT_NODE ) {
                        	Element element = (Element)node;
                                String user_id=element.getAttribute("UserId");
                                String session_id=element.getAttribute("SessionId");
				if((user_id.equals(userid)) && (session_id.equals(sessionid))) {
					Attr attrNode = element.getAttributeNode("Status");
                                       	attrNode.setValue("active");
                                        if(saveXML(doc).equals("Successfull")){
						String ip=element.getAttribute("IP");		
						message_ip="current"+ip+","+"parent"+searchParentIP(ip ,sessionid);		
					}
				}
			}
		}
		}catch(Exception e){}
		return message_ip;
	}

	
	protected String Register(String userid,String sessionid,String RoleId){
		String message_ip="";
		try{
			/******************************************/
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse(getFile());
			/******************************************/
			message_ip=searchElement(userid,sessionid,RoleId);
			if(message_ip.equals("UnSuccessfull")) {
				String ip=ReflectorManager.getController().searchElement(sessionid);	
				if((!ip.equals("Reflector have insufficient Load !!")) && (!ip.equals("Reflector is not available !!")) && (!ip.equals("UnSuccessfull"))) {
		                        Element root = doc.getDocumentElement();
        		                Element reflector = doc.createElement("ReflectorStatus");
					reflector.setAttribute("IP", ip);
					reflector.setAttribute("UserId", userid);
					reflector.setAttribute("SessionId", sessionid);
					reflector.setAttribute("RoleId", RoleId);
					reflector.setAttribute("Status", "active");
                	        	root.appendChild(reflector);
	                	        if(saveXML(doc).equals("Successfull"))
						message_ip="current"+ip+","+"parent"+searchParentIP(ip ,sessionid);
				}
			}
		} catch(Exception ex) {	ServerLog.getController().Log("Error on creating xml file : "+ex.getMessage()); }
		return message_ip;
	}
	
	protected String searchParentIP(String reflector_ip ,String sessionid) {
                String message_ip="";
		try {
			/******************************/
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        	        DocumentBuilder builder = factory.newDocumentBuilder();
                	Document doc = builder.parse(getFile());
			/*****************************/
        	        Element root = doc.getDocumentElement();
                	NodeList peerList = doc.getElementsByTagName("ReflectorStatus");
	                for( int i=0; i<peerList.getLength(); i++ ) {
        	                Node node = peerList.item(i);
                	        if( node.getNodeType() == node.ELEMENT_NODE ) {
                        	        Element element = (Element)node;
                                	String reflectorip=element.getAttribute("IP");
	                                String session_id=element.getAttribute("SessionId");
        	                        if(session_id.equals(sessionid)) {
						if(!(reflector_ip.equals(reflectorip))){
                        	                       	message_ip=element.getAttribute("IP");
						}
	                                }
        	                }
                	}
		}catch(Exception e){}
                return message_ip;
        }
	
	protected void updateStatusPeer(String userid){
		userid=userid.trim();
		try {
		/*****************************/
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(getFile());
		/*****************************/
		NodeList peerList = doc.getElementsByTagName("ReflectorStatus");
                for( int i=0; i<peerList.getLength(); i++ ) {
                	Node node = peerList.item(i);
                        if( node.getNodeType() == node.ELEMENT_NODE ){
                        	Element element = ( Element )node;
                                String user_id=element.getAttribute("UserId").trim();
                                if(user_id.equals(userid)) {
					Attr attrNode = element.getAttributeNode("Status");
					attrNode.setValue("deactive");
					saveXML(doc);	
                              	}
                   	}
            	}	
		}catch(Exception e){}
	}		
	
	protected String removeReflector_IP_Peer(String reflector_ip){
		String message_ip="UnSuccessfull";	
                try{
			/**************************************/
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse(getFile());
			/*************************************/
			NodeList peerList = doc.getElementsByTagName("ReflectorStatus");
                        for( int i=0; i<peerList.getLength(); i++ ){
                                Node node = peerList.item(i );
                                if( node.getNodeType() == node.ELEMENT_NODE ){
                                        Element element = ( Element )node;
                                        String ip=element.getAttribute("IP");
                                        /** 
 					 * Remove Peer from Peer List
					 */
                                        if(ip.equals(reflector_ip)){
						doc.getDocumentElement().removeChild(peerList.item(i));
                        			message_ip=saveXML(doc);
						removeReflector_IP_Peer(reflector_ip);	
                                        }
                                }
                        }
                } catch( Exception e ){ ServerLog.getController().Log("Error in removeReflector_IP_Peer "+e.getMessage());	}
		return message_ip;
        }
	
	public String removeLoad_and_Sessionid_Peer(String session_id){
                String message_ip="UnSuccessfull";      
                try{
			/*************************************/
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse(getFile());
			/**************************************/
                        NodeList peerList = doc.getElementsByTagName("ReflectorStatus");
                        for( int i=0; i<peerList.getLength(); i++ ){
                                Node node = peerList.item(i );
                                if( node.getNodeType() == node.ELEMENT_NODE ){
                                        Element element = ( Element )node;
                                        String sessionid=element.getAttribute("SessionId");
                                        if(sessionid.equals(session_id)){
						String ip=element.getAttribute("IP");	
						ReflectorManager.getController().removeLoad(ip,session_id);
                                                doc.getDocumentElement().removeChild(peerList.item(i));
                                        }
                                }
                        }       
                        message_ip=saveXML(doc);
                } catch( Exception e ){ ServerLog.getController().Log("Error in removeLoad_and_Sessionid_Peer  "+e.getMessage());    }
                return message_ip;
        }

	private String saveXML(Document doc){
		try{
                        OutputFormat format = new OutputFormat(doc);
                        XMLSerializer output = new XMLSerializer(new FileOutputStream(context.getRealPath("ReflectorStatus.xml")), format);
                        output.serialize(doc);
			return "Successfull";
                }catch(Exception e){ServerLog.getController().Log(e.getMessage()); return "UnSuccessfull"; }
	}

        private File getFile() {
	    	File file=new File(context.getRealPath("ReflectorStatus.xml"));
        	if(!file.exists()){
                	try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                                DocumentBuilder builder = factory.newDocumentBuilder();
                                Document doc = builder.newDocument();//parse(getFile());
                               	Element rootEle = doc.createElement("ReflectorStatus_Peers");
	                        doc.appendChild(rootEle);
        	                saveXML(doc);
                                doc=null;
                	}catch (Exception ex) { ServerLog.getController().Log("Error in get file ========> "+ex.getMessage());}
	        } return file;
        }
}//end class
