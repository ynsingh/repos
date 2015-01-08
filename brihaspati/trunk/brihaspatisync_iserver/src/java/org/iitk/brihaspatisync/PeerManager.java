package org.iitk.brihaspatisync;

/*@(#)LecturePeerNode.java
 * See licence file for usage and redistribution terms
 * Copyright (c) 2007-2008, 2013 All Rights Reserved.
 */

import java.util.Vector;

import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedOutputStream;

import org.w3c.dom.Attr;
import org.w3c.dom.Node; 
import org.w3c.dom.Element; 
import org.w3c.dom.NodeList; 
import org.w3c.dom.Document; 

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.iitk.brihaspatisync.util.ServerLog;


/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal </a>
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>
 */

public class PeerManager {
	private static ServletContext context=null;

	public static void setContext(ServletContext context1) throws Exception {
                context=context1;
	}
	/**
	 * This method is used to create a xml file if it is not exist.
	 */
	private static File getFile(String lectureID) {
		File file=new File(context.getRealPath(lectureID+".xml"));
		try {
	                if(!file.exists()) {
			      	OutputStream fout= new FileOutputStream(file);
	        	        OutputStream bout= new BufferedOutputStream(fout);
        	                OutputStreamWriter out = new OutputStreamWriter(bout, "8859_1");
                	       	out.write("<?xml version=\"1.0\" ");
	                       	out.write("encoding=\"ISO-8859-1\"?>\r\n");
		                out.write("<Lecture_Peers>\r\n");
        		        out.write("\r\n");
                	        out.write("</Lecture_Peers>\r\n");
                        	out.flush(); 
		                out.close();
			}
      		} catch (Exception ex) { ServerLog.log(" Exception in getFile method of PeerManager class "+ex.getMessage());  }
		return file;
	}
		
	/*	
         * This Method Returns Parent Peer's IPAddress for Peer Connection .and also increase load of this parent peer.
         */
	protected static String createPeer(String lect_id, String publicIP,String user,String role,String status,String privateIP,String proxy,String ref_ip,String first_lst_name) {
		String message="";
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	               	DocumentBuilder builder = factory.newDocumentBuilder();
        	        Document doc = builder.parse(getFile(lect_id));
                	Element root = doc.getDocumentElement();
        	        Element peer = doc.createElement("Peer");
	                NodeList peerList = root.getElementsByTagName("Peer");
                	if( (publicIP!="") && (user!=null) && (role!="") && (status!="")) {
				if(!searchUserName(lect_id,user)) {
		                	peer.setAttribute("PublicIP",publicIP);
	        		        peer.setAttribute("User",user);
	        		        peer.setAttribute("UserName",first_lst_name);
        	        	        peer.setAttribute("Role",role);
                	        	peer.setAttribute("Status",status);
	                	       	peer.setAttribute("Reflector",ref_ip);
	        	                peer.setAttribute("PrivateIP",privateIP);
        	        	       	peer.setAttribute("Proxy",proxy);
	                	       	root.appendChild(peer);
		                        saveXML(doc,getFile(lect_id));
					message="Write succfully";	
				} else {
					removePeer(lect_id,user);	
					createPeer(lect_id,publicIP,user,role,status,privateIP,proxy,ref_ip,first_lst_name);
				}
      			} else   ServerLog.log("Exception in insert value to xml file "); 
		} catch(Exception e){ ServerLog.log(" Exception in getFile method of PeerManager class "+e.getMessage()+"get==>"+e); }
		return message;
	}
				
	protected static String updateStatus(String action,String userName,String LectureID) {
		String message="unSuccessfull";
		try {	
                	DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
	                factory.setValidating(false);
        	        factory.setIgnoringElementContentWhitespace(false);
			DocumentBuilder builder = factory.newDocumentBuilder();
	                Document document = builder.parse(getFile(LectureID));
        	        Element root = document.getDocumentElement();
                	NodeList peerList = root.getElementsByTagName("Peer");
			for( int i=0; i<peerList.getLength(); i++ ){
        	        	Node node = peerList.item(i);
                	        if( node.getNodeType() == node.ELEMENT_NODE ){
                        		Element element = (Element)node;
                                	String user=element.getAttribute("User");
	                                
					/**
        	                         * update Reflector of Parent Peer
                	                 */
                        	        if(user.equals(userName)){
                        	        	Attr attrNode = element.getAttributeNode("Status");
                                	        attrNode.setValue(action);
                                        	saveXML(document,getFile(LectureID));
						message="Successfull";
        	                     	}
				}
			}
		}catch(Exception e) { ServerLog.log(" Exception in updateStatus method of PeerManager class "+e.getMessage()); }	
		return message;
	}
	

	 protected static String updateLoad( String  sessionid) {
                String message="";
		int count=0;
		try{
		/*	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse(getFile(lect_id));*/
			NodeList nodelist = getNodeList(getFile(sessionid)); 
			count =  nodelist.getLength();
		//	return Integer.toString(count);
			}catch ( Exception e ) { ServerLog.log(" Exception in updateLoad method of PeerManager class "+e.getMessage()); }
			 return Integer.toString(count);
 		}

	
	protected static boolean searchUserName(String lectID,String username) {
		boolean flag=false;
                try{
                        DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
                        factory.setValidating(false);
                        factory.setIgnoringElementContentWhitespace(false);
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document document = builder.parse(getFile(lectID));
                        Element root = document.getDocumentElement();
                        NodeList peerList = root.getElementsByTagName("Peer");
                        for( int i=0; i<peerList.getLength(); i++ ){
                                Node node = peerList.item(i );
                                if( node.getNodeType() == node.ELEMENT_NODE ){
                                        Element element = ( Element )node;
                                        String User=element.getAttribute("User");
                                        if(User.equals(username)){
						if(!User.equals("guest"))
							flag=true;
                                        }
                                }
                        }
                } catch( Exception e ) { ServerLog.log(" Exception in searchUserName method of PeerManager class "+e.getMessage()); }
		return flag;
        }


	/**
	 * This method is set to remove peer from peer list and decrease load of parent peer. 
	 */
	protected static void removePeer(String lectID,String username){
                try{
                        DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
                        factory.setValidating(false);
                        factory.setIgnoringElementContentWhitespace(false);
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document document = builder.parse(getFile(lectID));
                        Element root = document.getDocumentElement();
                        NodeList peerList = root.getElementsByTagName("Peer");
                        for( int i=0; i<peerList.getLength(); i++ ){
				//ServerLog.log("inside for loop in removepeer method");
                        	Node node = peerList.item(i );
                                if( node.getNodeType() == node.ELEMENT_NODE ){
				//ServerLog.log("inside ifelse in removepeer method");
                                	Element element = ( Element )node;
					String User=element.getAttribute("User");
					//ServerLog.log("value of user from xml file :"+User);
					//ServerLog.log("value of username"+username);
					/** 
					* Remove Peer from Peer List
					*/
					if(User.equals(username)){
					//	ServerLog.log("inside second ifelse ");
	                                	root.removeChild(element);
                	                        saveXML(document,getFile(lectID));
					}
					
                                }
                        }
		       	
          	} catch( Exception e ){ ServerLog.log(" Exception in removePeer method of PeerManager class "+e.getMessage()); }
   	}

	
	/**
	 * This method save peer's information in XML file.
	 */

	private static void saveXML(Document doc, File fileName){
		try{
             		OutputFormat format = new OutputFormat(doc);
             		XMLSerializer output = new XMLSerializer(new FileOutputStream(fileName), format);
             		output.serialize(doc);
         	}catch(Exception e) { ServerLog.log(" Exception in saveXML method of PeerManager class "+e.getMessage());}
	}


	
	/**
	 * This method returns Peer List. 
	 */		

	private static synchronized NodeList getNodeList(File xmlFile){
		try{
                        DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
             		factory.setIgnoringElementContentWhitespace(false);
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document document = builder.parse(xmlFile );
                        Element root = document.getDocumentElement();
                        return root.getElementsByTagName("Peer");
                } catch( Exception e ) {  ServerLog.log(" Exception in saveXML method of PeerManager class "+e.getMessage()); 	}
		return null;
	}
	
	/**
         * This Method Returns UserList with username and Status.
         */
        protected static synchronized Vector getPeerList(String Lect_ID){
                NodeList peerList = getNodeList(getFile(Lect_ID));
                Vector userList=new Vector();
                if(peerList!=null){
                	for( int i=0; i<peerList.getLength(); i++ ){
                        	Node node = peerList.item( i );
				if( node.getNodeType() == node.ELEMENT_NODE ){
                                	Element element = ( Element )node;
                                  	String userName=element.getAttribute("User");
					String status=element.getAttribute("Status");
					String fullusername=element.getAttribute("UserName");
        		                StringBuffer string=new StringBuffer(100);
	                                string=string.append(userName);
        	                        string=string.append("$");
                	                string=string.append(status);
        	                        string=string.append("$");
        	                        string=string.append(fullusername);
                        	        userList.addElement(string.toString());
                        	}
				 
                	}
		}
                return userList;
        }
}//end class
