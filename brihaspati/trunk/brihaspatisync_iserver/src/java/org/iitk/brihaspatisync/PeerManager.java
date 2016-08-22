package org.iitk.brihaspatisync;

/*@(#)LecturePeerNode.java
 * See licence file for usage and redistribution terms
 * Copyright (c) 2007-2008, 2013,2015,2016 All Rights Reserved.
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
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.iitk.brihaspatisync.util.ServerUtil;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.iitk.brihaspatisync.util.ServerLog;
//import static java.util.concurrent.TimeUnit.*;


/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal </a>
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>
 * @author <a href="mailto:pradeepmca30@gmail.com"> Pradeep Kumar Pal </a>
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
        protected static String createPeerguest(String mail_id,String publicip,String status,String logintime) {
                String message="";
                String subject="This is a verification link from brihaspatisync.";
                String lect_id="guestuser";
                try {
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse(getFile(lect_id));
                        Element root = doc.getDocumentElement();
                        Element peer = doc.createElement("Peer");
                        String logintime_date=ServerUtil.getSystemDateTime();
                        String[] array = logintime_date.split(" ");
                        String date_string= array[0];
                        date_string =date_string.substring(12,14);
                        NodeList peerList = root.getElementsByTagName("Peer");
                        int lenth=peerList.getLength();
                        String existvalue = getAttributeValueExist(lect_id,"Emailid",mail_id,"Status");
                        int key=ServerUtil.RandomInteger();
                        File filenew =new File(context.getRealPath(lect_id+".xml"));
                        double bytes = filenew.length();
                        double kilobytes = (bytes / 1024);

                        if(existvalue.equals("true")){
                       	        message="Write unsuccfully";
                        }
                        else if(existvalue.equals("nottrue")){
                                MailNotification.getController().sendMailotp(context,subject,mail_id,Integer.toString(key));
                                message="Write succfully";
                              
                        }
                        else{
                             	peer.setAttribute("Emailid",mail_id);
                                peer.setAttribute("PublicIP",publicip);
                                peer.setAttribute("Status",status);
                                peer.setAttribute("Lastlogin" ,logintime);
                                peer.setAttribute("OTPNO","NULL");
                                root.appendChild(peer);
                                saveXML(doc,getFile(lect_id));
                                message="Write succfully";
                                //MailNotification.getController().sendMailotp(context,subject,mail_id, date,Integer.toString(key));
                                MailNotification.getController().sendMailotp(context,subject,mail_id,Integer.toString(key));
                       }
                       if(kilobytes>500){
                                File file=new File(context.getRealPath(lect_id+".xml"));
                                file.delete();
                       }

                } catch(Exception e){ ServerLog.log(" Exception in createpeerguest method of emailid class "+e.getMessage()+"get==>"+e); }
                return message;
        }


		
	/*	
         * This Method Returns Parent Peer's IPAddress for Peer Connection .and also increase load of this parent peer.
         */
//	protected static String createPeer(String lect_id, String publicIP,String user,String role,String status,String privateIP,String proxy,String ref_ip,String first_lst_name,String ins_audio,String video) { 

        /**
         * This method is replaced by createPeer addedd some parameters for ip4 and ip6.
         */
        protected static String createPeer(String lect_id, String publicIP,String publicIP6,String user,String role,String status,String privateIP ,String privateip6 ,String proxy,String ref_ip,String first_lst_name,String ins_audio,String video) {
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
		                	peer.setAttribute("PublicIP4",publicIP);
                                        peer.setAttribute("PublicIP6",publicIP6);
	        		        peer.setAttribute("User",user);
	        		        peer.setAttribute("UserName",first_lst_name);
        	        	        peer.setAttribute("Role",role);
                	        	peer.setAttribute("Status",status);
	                	       	peer.setAttribute("Reflector",ref_ip);
	        	                peer.setAttribute("PrivateIP4",privateIP);
                                        peer.setAttribute("PrivateIP6",privateip6);
        	        	       	peer.setAttribute("Proxy",proxy);
					peer.setAttribute("INS_AUD",ins_audio);
                                        peer.setAttribute("VIDEO",video);
	                	       	root.appendChild(peer);
		                        saveXML(doc,getFile(lect_id));
					message="Write succfully";	
				} else {
					removePeer(lect_id,user);	
					createPeer(lect_id,publicIP,publicIP6,user,role,status,privateIP,privateip6,proxy,ref_ip,first_lst_name,ins_audio,video);
				}
      			} else   ServerLog.log("Exception in insert value to xml file "); 
		} catch(Exception e){ ServerLog.log(" Exception in createpeer method of PeerManager class "+e.getMessage()+"get==>"+e); }
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
  
          /**
         * This method is used to verify otp getting from client side.
         */

     
	 protected static String verifyotp(String userName,String OTP, String OTPtime) {
                String message="unSuccessfull";
                String LectureID="guestuser";

                try {
                        DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
                        factory.setValidating(false);
                        factory.setIgnoringElementContentWhitespace(false);
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document document = builder.parse(getFile(LectureID));
                        Element root = document.getDocumentElement();
                        NodeList peerList = root.getElementsByTagName("Peer");
                        int lenth=peerList.getLength();
                        for( int i=0; i<peerList.getLength(); i++ ){
                                Node node = peerList.item(i);
                                if( node.getNodeType() == node.ELEMENT_NODE ){
                                        Element element = (Element)node;
                                        String user=element.getAttribute("Emailid");
                                         String attrNode = element.getAttributeNode("OTPNO").getNodeValue();

                                        /**
                                         * check otp no.
                                         */
                                        if(OTP.equals(attrNode)){
                                                String attrNode1 = element.getAttributeNode("Lastlogin").getNodeValue(); 
                                                SimpleDateFormat format = new SimpleDateFormat("yyyy/mm/dd HH:mm");
                        				Date d1 = format.parse(OTPtime);
                     				        Date d2 = format.parse(attrNode1);
                       					 long diff = d1.getTime() - d2.getTime();
                        				 long diffSeconds = diff / 1000 % 60;
                        				 long diffMinutes = diff / (60 * 1000) % 60;
                        				 long diffHours = diff / (60 * 60 * 1000) % 24;
                        				 long diffDays = diff / (24 * 60 * 60 * 1000);
                                                         if (diffMinutes<60){
                                                                      message=updateStatusxml(userName);
                                                         }
                                                         else {
                                                           root.removeChild(element);
                                                           saveXML(document,getFile(LectureID));
                                                           message="unSuccessfull";
                                                       }
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
			NodeList nodelist = getNodeList(getFile(sessionid)); 
			count =  nodelist.getLength();
			}catch ( Exception e ) { ServerLog.log(" Exception in updateLoad method of PeerManager class "+e.getMessage()); }
			 return Integer.toString(count);
 		}

        /**
         * This method is used to update the status in xml.
         */

         protected static String updateStatusxml(String mail_id) {
                String message="unSuccessfull";
                String LectureID="guestuser";
                try {
                        DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
                        factory.setValidating(false);
                        factory.setIgnoringElementContentWhitespace(false);
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document document = builder.parse(getFile(LectureID));
                        Element root = document.getDocumentElement();
                        NodeList peerList = root.getElementsByTagName("Peer");
                        for( int i=0; i<peerList.getLength(); i++ ){
                            int c =peerList.getLength();
                                Node node = peerList.item(i);
                                if( node.getNodeType() == node.ELEMENT_NODE ){
                                         Element element = (Element)node;
                                         String emailid = element.getAttribute("Emailid");
                                         String ip = element.getAttribute("PublicIP");
                                         
                                        /**
                                         * update status of Peer
                                         */
                                        if(emailid.equals(mail_id)){
                                                Attr attrNode = element.getAttributeNode("Status");
                                                Attr attrNodeotp = element.getAttributeNode("OTPNO");
                                                attrNode.setValue("Verified");
                                                attrNodeotp.setValue("otp used");
                                                saveXML(document,getFile(LectureID));
                                                message="Successfull";
                                        }                                }
                        }
                }catch(Exception e) { ServerLog.log(" Exception in updateStatus method of PeerManager class "+e.getMessage()); }
                return message;
        }

        /**
         * This method is used to update the otpno in xml.
         */

        protected static String updateotpxml(String mail_id,String otp) {
                String message="unSuccessfull";
                String LectureID="guestuser";
                try {
                        DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
                        factory.setValidating(false);
                        factory.setIgnoringElementContentWhitespace(false);
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document document = builder.parse(getFile(LectureID));
                        Element root = document.getDocumentElement();
                        NodeList peerList = root.getElementsByTagName("Peer");
                        for( int i=0; i<peerList.getLength(); i++ ){
                            int c =peerList.getLength();
                                Node node = peerList.item(i);
                                if( node.getNodeType() == node.ELEMENT_NODE ){
                                         Element element = (Element)node;
                                         String emailid = element.getAttribute("Emailid");
                                         String ip = element.getAttribute("PublicIP");
                                        /**
                                         * update status of Peer
                                         */
                                        if(emailid.equals(mail_id)){
                                                Attr attrNode = element.getAttributeNode("OTPNO");
                                                attrNode.setValue(otp);
                                                saveXML(document,getFile(LectureID));
                                                message="Successfull";
                                        }                                }
                        }
                }catch(Exception e) { ServerLog.log(" Exception in updateStatus method of PeerManager class "+e.getMessage()); }
                return message;
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
                        	Node node = peerList.item(i );
                                if( node.getNodeType() == node.ELEMENT_NODE ){
                                	Element element = ( Element )node;
					String User=element.getAttribute("User");
					/** 
					* Remove Peer from Peer List
					*/
					if(User.equals(username)){
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
                                        String role=(String)element.getAttribute("Role");
        		                StringBuffer string=new StringBuffer(100);
	                                string=string.append(userName);
        	                        string=string.append("$");
                	                string=string.append(status);
        	                        string=string.append("$");
        	                        string=string.append(fullusername);
                                        string=string.append("$");
                                        string=string.append(role);
                        	        userList.addElement(string.toString());
                        	}
				 
                	}
    		}
                return userList;
        }

      /**
         * This Method is used to check element exist in xml or not.
         */


       public static String getAttributeValueExist(String filePath, String attributeName,String value,String attributeName1){
       String nodeid="";
       String nodestatus="";
       String headExist="false";
       String lect_id="guestuser";
                try{
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse(getFile(lect_id));
                        Element root = doc.getDocumentElement();
                        NodeList nodeList = doc.getElementsByTagName("Peer");
                        for(int x=0,size= nodeList.getLength(); x<size; x++) {
                     		   Node node2 = nodeList.item( x );
                                   if( node2.getNodeType() == node2.ELEMENT_NODE ){
                                   		Element element = ( Element )node2;
                                		nodeid = nodeList.item(x ).getAttributes().getNamedItem(attributeName).getNodeValue();
                                		nodestatus= nodeList.item(x ).getAttributes().getNamedItem(attributeName1).getNodeValue();
                                		if(nodeid.equals(value)){
                                     			if( nodestatus.equals("Verified")){
                                        			headExist="true";
                                        		}
                                        		else{
                                          			headExist="nottrue";

                                       			}
                                		}	
                                   }
                    }
                }catch(Exception ex) {ServerLog.log(" Exception in  getAttributeValueExist  method of PeerManager class "+ex.getMessage());};
                 return headExist;


    }
  


}//end class
