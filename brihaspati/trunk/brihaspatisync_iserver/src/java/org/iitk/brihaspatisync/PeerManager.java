package org.iitk.brihaspatisync;

/*@(#)LecturePeerNode.java
 * See licence file for usage and redistribution terms
 * Copyright (c) 2007-2008.All Rights Reserved.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedOutputStream;
import java.net.URLDecoder;
import org.w3c.dom.NodeList; 
import org.w3c.dom.Node; 
import org.w3c.dom.Document; 
import org.w3c.dom.Element; 
import org.w3c.dom.Attr;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import java.util.Vector;
import org.iitk.brihaspatisync.util.ServerLog;

import javax.servlet.ServletContext;

/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal </a>
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>
 */

public class PeerManager 
{
	private static PeerManager pm=null;
	private NodeList peerList=null;
	private ServletContext context=null;

	/**
         * This method returns controller for PeerManager class.
         */
	public static PeerManager getController(){
		if(pm==null){
			pm=new PeerManager();
		}
		return pm;
	}

	public void setContext(ServletContext context1) throws Exception {
                context=context1;
	}
	/**
	 * This method is used to create a xml file if it is not exist.
	 */
	
	private File getFile(String lectureID){
		File file=new File(context.getRealPath(lectureID+".xml"));
                if(file.exists()){
                       	return file;
		}else{
			try {
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
        	        }catch (Exception ex) {ServerLog.getController().Log("Error in creating XML file"+ex.getMessage());}
			return file;
		}
	}

	/**
         * This Method Returns Parent Peer's IPAddress for Peer Connection .and also increase load of this parent peer.
         */
	protected String createPeer(String lect_id, String publicIP,String user,String role,String status,String privateIP,String proxy,String ref_ip){
		String parentIP="";
		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	               	DocumentBuilder builder = factory.newDocumentBuilder();
        	        Document doc = builder.parse(getFile(lect_id));
                	Element root = doc.getDocumentElement();
	                peerList = root.getElementsByTagName("Peer");
        	        Element peer = doc.createElement("Peer");
                	if((publicIP!="")&&(user!=null)&&(role!="")&&(status!="")){
	                	peer.setAttribute("PublicIP",publicIP);
        	                peer.setAttribute("User",user);
                	        peer.setAttribute("Role",role);
                        	peer.setAttribute("Status",status);
	                        peer.setAttribute("Reflector",ref_ip);
        	                peer.setAttribute("PrivateIP",privateIP);
                	        peer.setAttribute("Proxy",proxy);
                        	root.appendChild(peer);
	                        saveXML(doc,getFile(lect_id));
				return "Write succfully";	
      			} else{
              			ServerLog.getController().Log("Error in insert value to xml file by any null value==>");
			}
		}catch(Exception e){ 
			ServerLog.getController().Log("Error create xml line no 127 "+e.getMessage()+"get==>"+e);
		}
		return parentIP;
			
	}
				
	protected String  updateStatus(String action,String userName,String LectureID){
		Element element=null;
                Node node=null;
                String user="";
		try {	
			userName=URLDecoder.decode(userName, "UTF-8");
                	ServerLog.getController().Log("action-----> "+action+" userName ---->  "+userName+" LectureID "+LectureID);
                	DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
	                factory.setValidating(false);
        	        factory.setIgnoringElementContentWhitespace(false);
                	DocumentBuilder builder = factory.newDocumentBuilder();
	                Document document = builder.parse(getFile(LectureID));
        	        Element root = document.getDocumentElement();
                	peerList = root.getElementsByTagName("Peer");
			for( int i=0; i<peerList.getLength(); i++ ){
        	        	node = peerList.item(i);
                	        if( node.getNodeType() == node.ELEMENT_NODE ){
                        		element = (Element)node;
                                	user=element.getAttribute("User");
	                                
					/**
        	                         * update Reflector of Parent Peer
                	                 */
					ServerLog.getController().Log("user===========>  "+user+" userName ====  "+userName);
                        	        if(user.equals(userName)){
						ServerLog.getController().Log("Iner loop  userName @@@@@  "+userName);
						synchronized (this) {
                        	                       	Attr attrNode = element.getAttributeNode("Status");
                                	                attrNode.setValue(action);
                                        	        saveXML(document,getFile(LectureID));
							return "Successfull";
                                               	}
        	                     	}
				}
			}
		}catch(Exception e){
			ServerLog.getController().Log("   Error from updateStatus -------> ");
		}	
		return "UnSuccessfull";
	}

	/**
	 * This method is set to remove peer from peer list and decrease load of parent peer. 
	 */
	protected synchronized void removePeer(String lectID,String username){
               
		Element element=null;
                Node node=null;
                String ip="";

                try{
                        DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
                        factory.setValidating(false);
                        factory.setIgnoringElementContentWhitespace(false);
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document document = builder.parse(getFile(lectID));
                        Element root = document.getDocumentElement();
                        peerList = root.getElementsByTagName("Peer");
                        for( int i=0; i<peerList.getLength(); i++ ){
                        	node = peerList.item(i );
                                if( node.getNodeType() == node.ELEMENT_NODE ){
                                	element = ( Element )node;
					String User=element.getAttribute("User");
					
					/** 
					* Remove Peer from Peer List
					*/
					if(User.equals(username)){
						synchronized (this) {
	                                                root.removeChild(element);
                	                                saveXML(document,getFile(lectID));
						}
                                        }
					
                            	}
                     	}
          	} catch( Exception e ){
			e.printStackTrace();
		}
   	}
	
	/**
	 * This method save peer's information in XML file.
	 */

	private void saveXML(Document doc, File fileName){
		try{
             		OutputFormat format = new OutputFormat(doc);
             		XMLSerializer output = new XMLSerializer(new FileOutputStream(fileName), format);
             		output.serialize(doc);
         	}catch(Exception e){e.printStackTrace();}
	}
	
	/**
	 * This method returns Peer List. 
	 */		

	private synchronized NodeList getNodeList(File xmlFile){
		NodeList list=null;
		try{
			
                        DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
             		factory.setIgnoringElementContentWhitespace(false);
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document document = builder.parse(xmlFile );
                        Element root = document.getDocumentElement();
                        list = root.getElementsByTagName("Peer");
			
                } catch( Exception e ){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
         * This Method Returns UserList with username and Status.
         */

        protected synchronized Vector getPeerList(String Lect_ID){
                Element element=null;
                Node node=null;
                String userName="";
                String status="";
                String lect_id="";
                StringBuffer str=null;
                Vector userList=null;
                peerList=getNodeList(getFile(Lect_ID));
                userList=new Vector();
                if(peerList!=null){
                	for( int i=0; i<peerList.getLength(); i++ ){
                        	node = peerList.item( i );
				if( node.getNodeType() == node.ELEMENT_NODE ){
                                	element = ( Element )node;
                                  	userName=element.getAttribute("User");
					status=element.getAttribute("Status");
        		                StringBuffer string=new StringBuffer(100);
	                                string=string.append(userName);
        	                        string=string.append("$");
                	                string=string.append(status);
                        	        userList.addElement(string.toString());
                        	}
                	}
		}
                return userList;
        }
}//end class
