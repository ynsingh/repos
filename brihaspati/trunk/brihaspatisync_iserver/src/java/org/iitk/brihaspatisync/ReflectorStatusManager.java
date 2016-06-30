package org.iitk.brihaspatisync;

/* @(#)ReflectorStatusManager.java
 * See licence file for usage and redistribution terms
 * Copyright (c) 2007-2008,2013,2014 All Rights Reserved.
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
import org.iitk.brihaspatisync.ReflectorManager;

/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal </a> modified date march 2014
 */

public class ReflectorStatusManager 
{
	private static ServletContext context=null;

	public static void setContext(ServletContext context1) throws Exception {
                context=context1;
        }
		
	//private static String searchreRunningReflector(String sessionid,String publicip,String privateip) {
          
         /**
         * This method is replaced by searchreRunningReflector addedd some parameters for ip4 and ip6.
         */

          private static String searchreRunningReflector(String sessionid,String publicip, String publicip6, String privateip, String privateip6) {

		String message_ip="UnSuccessfull";
                try {
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse(getFile());
                        Element root = doc.getDocumentElement();
                        NodeList peerList = doc.getElementsByTagName("ReflectorStatus");
                        for( int i=0; i<peerList.getLength(); i++ ) {
                                Node node = peerList.item(i);
                                if( node.getNodeType() == node.ELEMENT_NODE ) {
                                        Element element = (Element)node;
					String session_id = element.getAttribute("SESSIONID");
					if(session_id.equals(sessionid)){
                                        String public_ip=element.getAttribute("PUBLICIP");
					if(check_proxy_addr(public_ip) && check_proxy_addr(publicip)) {
	                                        int load=Integer.parseInt(element.getAttribute("LOAD"));
						//if(load<10) {
							int loadint=load+1;
							Attr attrNode = element.getAttributeNode("LOAD");
                                	                attrNode.setValue(""+loadint);
                                        	        saveXML(doc);
							String ip=ReflectorManager.searchElement(sessionid);
							return message_ip="current"+ip+","+"parent"+"";
						//	return message_ip="current"+element.getAttribute("PRIVATEIP")+","+"parent"+"";
						//}
					} else {
						int load=Integer.parseInt(element.getAttribute("LOAD"));
                                               // if(public_ip.equals(publicip) && (load<5) ) {
                                                        int loadint=load+1;
                                                        Attr attrNode = element.getAttributeNode("LOAD");
                                                        attrNode.setValue(""+loadint);
                                                        saveXML(doc);
							String ip=ReflectorManager.searchElement(sessionid);
                                                        return message_ip="current"+ip+","+"parent"+"";

//                                                        return message_ip="current"+element.getAttribute("PRIVATEIP")+","+"parent"+"";
                                               // }
					}
					}
					else{
					message_ip="UnSuccessfull";
					continue;
					}
				}
			}
		} catch(Exception e) { ServerLog.log("Exception in searchreRunningReflector method "+e.getMessage()); }	
		ServerLog.log("searchreRunningReflector message_ip "+message_ip);
		return message_ip;
	}
		
	//protected static String Register(String sessionid,String publicip,String privateip,String role) {

         /**
         * This method is replaced by Register addedd some parameters for ip4 and ip6.
         */          

          protected static String Register(String sessionid,String publicip,String publicip6,String privateip,String privateip6, String role) {
		String message_ip="";
		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse(getFile());
		      //message_ip=searchreRunningReflector(sessionid,publicip,privateip);
                        message_ip=searchreRunningReflector(sessionid,publicip,publicip6, privateip,privateip6);
			String ip=ReflectorManager.searchElement(sessionid);
			if((message_ip.equals("UnSuccessfull"))&&(role.equals("instructor"))) {
			//	String ip=ReflectorManager.searchElement(sessionid);
				String load=PeerManager.updateLoad(sessionid); 	
				if((!ip.equals("Reflector have insufficient Load !!")) && (!ip.equals("Reflector is not available !!")) && (!ip.equals("UnSuccessfull"))) {
							
		                        Element root = doc.getDocumentElement();
        		                Element reflector = doc.createElement("ReflectorStatus");
					reflector.setAttribute("SESSIONID", sessionid);
					reflector.setAttribute("LOAD", load);
					reflector.setAttribute("PUBLICIP4", publicip);
                                        reflector.setAttribute("PUBLICIP6", publicip6);
					reflector.setAttribute("PRIVATEIP4", privateip);
                                        reflector.setAttribute("PRIVATEIP6", privateip6);
                	        	root.appendChild(reflector);
	                	        if(saveXML(doc).equals("Successfull"))
						message_ip="current"+ip+","+"parent"+"";//searchParentIP(ip ,sessionid);
				}
			}else{ 
				 message_ip="current"+ip+","+"parent"+"";
				}
		} catch(Exception ex) {	ServerLog.log("Error on creating xml file : "+ex.getMessage()); }
		ServerLog.log(message_ip);
		return message_ip;
	}
	
	//private static String updateLoad( String  sessionid) {
	//	String message="";
		
	protected static String searchParentIP(String reflector_ip ,String sessionid) {
                String message_ip="";
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        	        DocumentBuilder builder = factory.newDocumentBuilder();
                	Document doc = builder.parse(getFile());
        	        Element root = doc.getDocumentElement();
                	NodeList peerList = doc.getElementsByTagName("ReflectorStatus");
	                for( int i=0; i<peerList.getLength(); i++ ) {
        	                Node node = peerList.item(i);
                	        if( node.getNodeType() == node.ELEMENT_NODE ) {
                        	        Element element = (Element)node;
                                	String reflectorip=element.getAttribute("PUBLICIP");
	                                String session_id=element.getAttribute("SESSIONID");
        	                        if(session_id.equals(sessionid)) {
						if(!(reflector_ip.equals(reflectorip))){
                        	                       	message_ip=element.getAttribute("PUBLICIP");
						}
	                                }
        	                }
                	}
		}catch(Exception e){}
                return message_ip;
        }
	
	protected static void updateStatusPeer(String ip, String sessionid){
		try {
			//ServerLog.log("inside updatestatuspeer method");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
        	        Document doc = builder.parse(getFile());
			NodeList peerList = doc.getElementsByTagName("ReflectorStatus");
			//ServerLog.log("peerlist length is :"+peerList.getLength());
	                for( int i=0; i<peerList.getLength(); i++ ) {
				//ServerLog.log("inside for loop in updatestatuspeer method");
        	        	Node node = peerList.item(i);
                	        if( node.getNodeType() == node.ELEMENT_NODE ){
				//	ServerLog.log("inside first ifelse in updatestatuspeer method");
                        		Element element = ( Element )node;
					String session_id=element.getAttribute("SESSIONID");
					if(session_id.equals(sessionid)){

					 int load=Integer.parseInt(element.getAttribute("LOAD")); /*storing current load value */
					load = load-1;                                       /*decrementing the load parameter */
					  Attr attrNode = element.getAttributeNode("LOAD");
                                                        attrNode.setValue(""+load);
					saveXML(doc); 
                                	//String getip=element.getAttribute("PRIVATEIP").trim();
                                	String getip=element.getAttribute("PUBLICIP").trim();
				//	ServerLog.log("value of getip in updatestatuspeer"+getip);
	                          //      ServerLog.log("value of ip in updatestatuspeer is "+ip);
					if(getip.equals(ip)) {
						ServerLog.log("inside updatestatus peer method");
						ServerLog.log("value of getip :"+getip);
						ServerLog.log("value of ip:"+ip);
						ReflectorManager.removeLoad(sessionid);
				//	ServerLog.log("inside second ifelse in updatestatuspeer method");
						doc.getDocumentElement().removeChild(peerList.item(i));
                                                saveXML(doc);
                                           } 
                              		}
						
	                   	}
        	    	}	
		}catch(Exception e){}
	}		
		
/*	protected static String removeReflector_IP_Peer(String reflector_ip){
		String message_ip="UnSuccessfull";	
                try{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse(getFile());
			NodeList peerList = doc.getElementsByTagName("ReflectorStatus");
                        for( int i=0; i<peerList.getLength(); i++ ){
                                Node node = peerList.item(i );
                                if( node.getNodeType() == node.ELEMENT_NODE ){
                                        Element element = ( Element )node;
                                        String ip=element.getAttribute("PUBLICIP");
                                        if(ip.equals(reflector_ip)){
						doc.getDocumentElement().removeChild(peerList.item(i));
                        			message_ip=saveXML(doc);
						removeReflector_IP_Peer(reflector_ip);	
                                        }
                                }
                        }
                } catch( Exception e ){ ServerLog.log("Error in removeReflector_IP_Peer "+e.getMessage());	}
		return message_ip;
        }*/


/*	public static String removeLoad_and_Sessionid_Peer(String session_id){
                String message_ip="UnSuccessfull";
                try{
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse(getFile());
                        NodeList peerList = doc.getElementsByTagName("ReflectorStatus");
                ServerLog.log("Error01 in removeLoad_and_Sessionid_Peer");
                        for( int i=0; i<peerList.getLength(); i++ ){
                                Node node = peerList.item(i );
                                if( node.getNodeType() == node.ELEMENT_NODE ){
                                        Element element = ( Element )node;
                                        String sessionid=element.getAttribute("SessionId");
                                        if(sessionid.equals(session_id)){
                                                String ip=element.getAttribute("IP");
						
                                                doc.getDocumentElement().removeChild(peerList.item(i));
                                        }
                                }
			}
                        message_ip=saveXML(doc);
                } catch( Exception e ) { ServerLog.log("Error02 in removeLoad_and_Sessionid_Peer  "+e.getMessage());    }
                return message_ip;
        }*/ 


		
	private static String saveXML(Document doc) {
		try {
                        OutputFormat format = new OutputFormat(doc);
                        XMLSerializer output = new XMLSerializer(new FileOutputStream(context.getRealPath("ReflectorStatus.xml")), format);
                        output.serialize(doc);
			return "Successfull";
                }catch(Exception e) { ServerLog.log(e.getMessage()); return "UnSuccessfull"; }
	}

        private static File getFile() {
	ServerLog.log("Enter in get file function");
	    	File file=new File(context.getRealPath("ReflectorStatus.xml"));
        	if(!file.exists()){
                	try {
			ServerLog.log("inside if else");
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                                DocumentBuilder builder = factory.newDocumentBuilder();
                                Document doc = builder.newDocument();
                               	Element rootEle = doc.createElement("ReflectorStatus_Peers");
	                        doc.appendChild(rootEle);
        	                saveXML(doc);
                                doc=null;
                	}catch (Exception ex) { ServerLog.log("Error in get file "+ex.getMessage());}
	        } return file;
        }
	
	protected static boolean check_proxy_addr(String private_ip) {
               	boolean proxyflag=false;
                try {
                        /**************  private ip range *************** 
                         * 10.0.0.0    - 10.255.255.255
                         * 172.16.0.0  - 172.31.255.255
                         * 192.168.0.0 - 192.160.255.255
                         **/
				
			private_ip=private_ip.trim();
			private_ip=private_ip.replace(".",":");
                        String privateip[]=private_ip.split(":");
					
                        int pri1=Integer.parseInt(privateip[0]);
                        int pri2=Integer.parseInt(privateip[1]);
			ServerLog.log("==========>>>> "+privateip[0]+"  "+privateip[1]);
                        switch (pri1) {
                                case 10 : if((pri2>=0) && (pri2<255))
                                                proxyflag=true;
                                        break;
                                case 172 : if((pri2 >= 16) && (pri2<=31)) {
                                                proxyflag=true;
						ServerLog.log("check proxy  "+proxyflag);
					  }	
                                        break;
                                case 192 : if(pri2 == 168)
                                                proxyflag=true;
                                        break;
                                default : ServerLog.log("vxcvvxcvxv  ");
                                        break;
                        }
                } catch(Exception e) { ServerLog.log("Exception in check proxy  "+e.getMessage()); }
		return proxyflag;	
        }
}//end class
