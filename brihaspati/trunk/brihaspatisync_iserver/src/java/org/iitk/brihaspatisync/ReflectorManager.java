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
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import java.util.Vector;
import org.iitk.brihaspatisync.util.ServerLog;

/**
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal </a>
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>
 */

public class ReflectorManager 
{
	
	private static ReflectorManager rm=null;
	private NodeList ref_List=null;
	private final static int maxload = 10;	
	private final static int maxcource = 2;	
	
	/**
         * This method returns controller for PeerManager class.
         */
	public static ReflectorManager getController(){
		if(rm==null){
			rm=new ReflectorManager();
		}
		return rm;
	}

	protected String getIP(String courceid , String ins_or_stu){
		try {
			if(ins_or_stu.equals("instructor")){
				String ip=checkLoadFreeReflector(courceid);
				if(ip.equals("Reflector is not running")){	
					return "Reflector is not running";
				}
				if(!(ip.equals("UnSuccessfull"))){
					return ip;
                                }
				ip=checkloadAndRunningCourse(courceid,"Instructor");
				if(!(ip.equals("UnSuccessfull"))){
					return ip;
				}else {
					return "Reflector is not running";
				}
			} else {
				String ip=checkInsLogin(courceid);
				if((ip.equals("UnSuccessfull")) || (ip.equals("Reflector is not running"))){
					if(ip.equals("UnSuccessfull"))
						ip="Instructor is not login";
					if(ip.equals("Reflector is not running"))
						ip="Reflector is not running";
                                        return ip;
                                }
				ip=insAvilable(courceid);	
				if(!ip.equals("UnSuccessfull")){
                                        return ip;
                                }
				ip=checkloadAndRunningCourse(courceid,"Student");
				if(!ip.equals("UnSuccessfull")){
                                        return ip;
                                } else{
					return "Reflector is not running";
				}
			}
		}catch(Exception e){}	
		return "";
	}
	private String checkInsLogin(String courceid){
		Element element=null;
                Node node=null;
                String ip="UnSuccessfull";
                try {
                        DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
                        factory.setValidating(false);
                        factory.setIgnoringElementContentWhitespace(false);
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document document = builder.parse(getFile("Reflector.xml"));
                        Element root = document.getDocumentElement();
                        ref_List = root.getElementsByTagName("Reflector");
			if(ref_List.getLength()==0)
				return "Reflector is not running";
                        for( int i=0; i<ref_List.getLength(); i++ ){
                                node = ref_List.item(i);
                                if( node.getNodeType() == node.ELEMENT_NODE ){
                                        element = (Element)node;
                                        String ins=element.getAttribute("Instructor");
                                        String ins1[]= ins.split(",");
					if(checkIns(courceid,ins1)!=0){
						ip="Successfull";
						return ip;
					}	
				}				
			}
                }catch(Exception e){}
                return ip;
	}
	
	private String insAvilable(String courceid){
		Element element=null;
                Node node=null;
                String ip="UnSuccessfull";
                try {
                        DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
                        factory.setValidating(false);
                        factory.setIgnoringElementContentWhitespace(false);
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document document = builder.parse(getFile("Reflector.xml"));
                        Element root = document.getDocumentElement();
                        ref_List = root.getElementsByTagName("Reflector");
                        for( int i=0; i<ref_List.getLength(); i++ ){
                                node = ref_List.item(i);
                                if( node.getNodeType() == node.ELEMENT_NODE ){
                                        element = (Element)node;
					String ins=element.getAttribute("Instructor");
					String ins1[]= ins.split(",");
					if(checkRunningIns(courceid,ins1)){
						int load=Integer.parseInt(element.getAttribute("Load"));
        	                                if(load<maxload){
        	                                        ip=element.getAttribute("IP");
                	                                String newload=Integer.toString(load+1);
                        	                        Attr attrNode = element.getAttributeNode("Load");
                                	                attrNode.setValue(newload);
	                                                saveXML(document,getFile("Reflector.xml"));
							ip="current"+ip+","+"parent"+"";
        	                                        return ip;
						}else {
							return ip;
						}
					}
                                }
                        }
                }catch(Exception e){}
		return ip;
	}	

	private String checkloadAndRunningCourse(String courceid,String clientrole){
		Element element=null;
                Node node=null;
                String ip="UnSuccessfull";
                try {
                        DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
                        factory.setValidating(false);
                        factory.setIgnoringElementContentWhitespace(false);
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document document = builder.parse(getFile("Reflector.xml"));
                        Element root = document.getDocumentElement();
                        ref_List = root.getElementsByTagName("Reflector");
                        for( int i=0; i<ref_List.getLength(); i++ ){
                                node = ref_List.item(i);
                                if( node.getNodeType() == node.ELEMENT_NODE ){
                                        element = (Element)node;
                                        int load=Integer.parseInt(element.getAttribute("Load"));
                                        if(load<maxload){
						String course = element.getAttribute("Courses");
						String temp[]= course.split(",");
                                                int inttemp=checkRunningCource(course.split(","));
						if(inttemp<=maxcource){
							boolean flag=false;
							if(clientrole.equals("Instructor")){
								if(inttemp==maxcource){
									ip="current"+ip+","+"parent"+"";
									return ip;	
								}
		                                       		temp[inttemp]=courceid;
							}else {
								if(inttemp==1){
									if(temp[0].equals(courceid)) {
	                                                                        temp[0]=courceid;
										flag=true;
									} else {
										temp[1]=courceid;
										flag=true;
									}
								} else if(inttemp==2){
									if(temp[0].equals(courceid)){
										flag=true;
                                                                        	temp[0]=courceid;
									}
                                                                	if(temp[1].equals(courceid)){
										flag=true;
                                                                        	temp[1]=courceid;
									}
                                                                }else {
									flag=true;
									temp[inttemp]=courceid;
								}
							}
							if(!(clientrole.equals("Instructor"))){
                                                                if(!flag)
                                                                        return ip;
                                                        }
        	                                        // set existing load +1
                	                                ip=element.getAttribute("IP");
                        	                        String newload=Integer.toString(load+1);
                                	                Attr attrNode = element.getAttributeNode("Load");
                                        	        attrNode.setValue(newload);
							if(clientrole.equals("Instructor")){
								course = element.getAttribute("Instructor");
		                                                String ins[]= course.split(",");

								int kk=checkIns1(courceid,ins);	
								attrNode = element.getAttributeNode("Instructor");
								if(kk==1){
									ins[0]=courceid+clientrole;
								}else {
									ins[1]=courceid+clientrole;
								}
        		                                      	attrNode.setValue(ins[0]+","+ins[1]);
								ip="current"+ip+","+"parent"+"";
							}else {
								String ip1=checkparentIP(courceid);
								if(!ip.equals(ip1)){
									ip="current"+ip+","+"parent"+ip1;
								}else {
									ip="current"+ip+","+"parent"+"";
								}
							}
							attrNode = element.getAttributeNode("Courses");
                                                        attrNode.setValue(temp[0]+","+temp[1]);
	                                             	saveXML(document,getFile("Reflector.xml"));
                        	                        return ip;
						}

                                        }
                                }
                        }
                }catch(Exception e){}
                return ip;
	}

	private String checkLoadFreeReflector(String courceid){
		Element element=null;
                Node node=null;
                String ip="UnSuccessfull";
                try {
                        DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
                        factory.setValidating(false);
                        factory.setIgnoringElementContentWhitespace(false);
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document document = builder.parse(getFile("Reflector.xml"));
                        Element root = document.getDocumentElement();
                        ref_List = root.getElementsByTagName("Reflector");
			if(ref_List.getLength()==0)
                                return "Reflector is not running";

                        for( int i=0; i<ref_List.getLength(); i++ ){
                                node = ref_List.item(i);
                                if( node.getNodeType() == node.ELEMENT_NODE ){
                                        element = (Element)node;
                                        int load=Integer.parseInt(element.getAttribute("Load"));
                                        if(load==0){
                                                ip=element.getAttribute("IP");
                                                String newload=Integer.toString(load+1);
                                                Attr attrNode = element.getAttributeNode("Load");
                                                attrNode.setValue(newload);
				
						attrNode = element.getAttributeNode("Instructor");
                                                attrNode.setValue(courceid+"Instructor"+","+"notInstructor");
                                                //set value of Cource
                                                attrNode = element.getAttributeNode("Courses");
                                                attrNode.setValue(courceid+","+"0");
                                                saveXML(document,getFile("Reflector.xml"));
						ip="current"+ip+","+"parent"+"";
                                                return ip;
					}
				}
			}
		}catch(Exception e){}
                return ip;
	}
	
	private String checkparentIP(String courceid){
		Element element=null;
                Node node=null;
                String ip="UnSuccessfull";
                try {
                        DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
                        factory.setValidating(false);
                        factory.setIgnoringElementContentWhitespace(false);
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document document = builder.parse(getFile("Reflector.xml"));
                        Element root = document.getDocumentElement();
                        ref_List = root.getElementsByTagName("Reflector");
                        for( int i=0; i<ref_List.getLength(); i++ ){
                                node = ref_List.item(i);
                                if( node.getNodeType() == node.ELEMENT_NODE ){
                                        element = (Element)node;
                                       	String course = element.getAttribute("Courses");
                                        String temp[]= course.split(",");
					boolean flag=false;
                                        if(temp[0].equals(courceid)){
						flag=true;	
					}      
					if(temp[1].equals(courceid)){
                                                flag=true;
                                        }
					if(flag){
 						ip=element.getAttribute("IP");
                                        }
                                }
                        }
                }catch(Exception e){}
                return ip;
	
	}
		

	protected String Register(String reflector_ip, String status){
		String parentIP="";
		String load="0",cource ="0";
		try{			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        		DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse(getFile("Reflector.xml"));
                        Element root = doc.getDocumentElement();
                      	ref_List = root.getElementsByTagName("Reflector");
                        Element peer = doc.createElement("Reflector");
                        if(status!=""){
                        	peer.setAttribute("IP",reflector_ip);
                                peer.setAttribute("Status",status);
                                peer.setAttribute("Load",load);
                                peer.setAttribute("Instructor","notInstructor"+","+"notInstructor");
				peer.setAttribute("Courses","0"+","+"0");
                                root.appendChild(peer);
                                saveXML(doc,getFile("Reflector.xml"));
                    	}else{
                         	ServerLog.getController().Log("Error in insert value to xml file by any null value==>");
                   	}
			/**
                         * Increase load of parent reflector
                         */
		}catch(Exception ex){
			System.out.println("Error on creating xml file : "+ex.getMessage());
		}
		return parentIP;
	}
		
	protected String  updateStatus(String reflector_ip,String status){
		Element element=null;
                Node node=null;
              	try {		
			DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
	                factory.setValidating(false);
        	        factory.setIgnoringElementContentWhitespace(false);
                	DocumentBuilder builder = factory.newDocumentBuilder();
	                Document document = builder.parse(getFile("Reflector.xml"));
        	        Element root = document.getDocumentElement();
                	ref_List = root.getElementsByTagName("Reflector");
			for( int i=0; i<ref_List.getLength(); i++ ){
        	        	node = ref_List.item(i);
                	        if( node.getNodeType() == node.ELEMENT_NODE ){
                        		element = (Element)node;
                	               	synchronized (element) {
						if((status.equals("active")) || (status.equals("inactive"))) {
                                			String ip=element.getAttribute("IP");
							if(ip.equals(reflector_ip)){
                	       	                       		Attr attrNode = element.getAttributeNode("Status");
								if(!(attrNode.equals("Active")))
                                	                		attrNode.setValue(status);
                                        	        	saveXML(document,getFile("Reflector.xml"));
								return "Successfull";
        	                     			}
						} else if((status.equals("getsingleins"))){

							String courceid=reflector_ip;
                                                        String course = element.getAttribute("Courses");
                                                        String temp[]= course.split(",");
							if((temp[1].equals("0"))) {	
								temp[1]=courceid;

								String ip=element.getAttribute("IP");
								int load=Integer.parseInt(element.getAttribute("Load"));
								String newload=Integer.toString(load+1);
		                                                Attr attrNode = element.getAttributeNode("Load");
                		                                attrNode.setValue(newload);
								//
								attrNode = element.getAttributeNode("Courses");
                                                		attrNode.setValue(temp[0]+","+temp[1]);
                                		                //	
								saveXML(document,getFile("Reflector.xml"));
								return ip;
							}
						} else if(status.equals("getins")){
							String courceid=reflector_ip;
							String course = element.getAttribute("Courses");
							String temp[]= course.split(",");
							if((temp[0].equals(courceid)) || (temp[1].equals(courceid))) {
								int load=Integer.parseInt(element.getAttribute("Load"));
                                                                if(load<3){
                                                             		String ip=element.getAttribute("IP");
        	                                                        String newload=Integer.toString(load+1);
	                                                                Attr attrNode = element.getAttributeNode("Load");
                	                                                attrNode.setValue(newload);
									// 
									attrNode = element.getAttributeNode("Courses");
                                                			attrNode.setValue(temp[0]+","+temp[1]);
									// 
									saveXML(document,getFile("Reflector.xml"));
                                                                	return ip;
                                                                }
							}
						}
                                   	}
				}
			}
		}catch(Exception e){
			ServerLog.getController().Log("   Error from update Status -------> ");
		}	
		return "UnSuccessfull";
	}
	
	protected synchronized String  removePeer(String reflectorIP){
		Element element=null;
                Node node=null;
                String ip="";

                try{
			DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
                        factory.setValidating(false);
                        factory.setIgnoringElementContentWhitespace(false);
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document document = builder.parse(getFile("Reflector.xml"));
                        Element root = document.getDocumentElement();
                        ref_List = root.getElementsByTagName("Reflector");
                        for( int i=0; i<ref_List.getLength(); i++ ){
                                node = ref_List.item(i);
                                if( node.getNodeType() == node.ELEMENT_NODE ){
                                        element = ( Element )node;
                                        ip=element.getAttribute("IP");
                                        /**
                                        * Remove Peer from Peer List
                                        */
                                        if(ip.equals(reflectorIP)){
                                                synchronized (this) {
                                                        root.removeChild(element);
                                                        saveXML(document,getFile("Reflector.xml"));
                                                }
					 return "successfull" ;	
                                        }

                                }
                        }
                } catch( Exception e ){
			e.printStackTrace();
			return "notsuccessfull";
                }
		return "notsuccessfull";
        }


	
	/**
	 * This method is set to remove reflector from reflector list and decrease load of parent reflector. 
	 */

	protected synchronized void removeLoad(String reflector_ip){
		
		Element element=null;
                Node node=null;
                String ip="";
                try{
                        DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
                        factory.setValidating(false);
                        factory.setIgnoringElementContentWhitespace(false);
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document document = builder.parse(getFile("Reflector.xml"));
                        Element root = document.getDocumentElement();
                        ref_List = root.getElementsByTagName("Reflector");
                        for( int i=0; i<ref_List.getLength(); i++ ){
				node = ref_List.item(i);
                                if( node.getNodeType() == node.ELEMENT_NODE ){
                                	element = ( Element )node;
					ip=element.getAttribute("IP");
					/** 
					* Remove Peer from Peer List
					*/
										
					if(ip.equals("/"+reflector_ip)){
						int ref_load=Integer.parseInt(element.getAttribute("Load"));
                                                if(ref_load > 0){
                                                        String newload=Integer.toString(ref_load-1);
                                                        synchronized (this) {
                                                                Attr attrNode = element.getAttributeNode("Load");
                                                                attrNode.setValue(newload);
                                                                saveXML(document,getFile("Reflector.xml"));
                                                        }
                                                }
                                        }
				}
                     	}
          	} catch( Exception e ){
			e.printStackTrace();
		}
   	}
	
	/**
	 * This method save reflector's information in XML file.
	 */

	private void saveXML(Document doc, File fileName){
		try{
             		OutputFormat format = new OutputFormat(doc);
             		XMLSerializer output = new XMLSerializer(new FileOutputStream(fileName), format);
             		output.serialize(doc);
         	}catch(Exception e){e.printStackTrace();}
	}
	
	private int checkRunningCource(String []temp){
		int kk=0;
		if(!temp[0].equals("0")){
			kk=1;		
		}
		if(!temp[1].equals("0")){
			kk=2;	             	
 		}
		return kk;
	}
	private int  checkIns1(String courceid,String []temp){
                int kk=0;
                if(temp[0].equals("notInstructor")){
			kk=1;
                }
		if(temp[1].equals("notInstructor")){
                	kk=2;
		}
		return kk;
	}

	private int  checkIns(String courceid,String []temp){
                int kk=0;
                if(!(temp[0].equals("notInstructor"))){
                        kk=1;
                }
                if(!(temp[1].equals("notInstructor"))){
                        kk=2;
                }
                return kk;
        }

	private boolean checkRunningIns(String courceid,String []temp){
		boolean flag=false;	
		if(!(temp[0].equals("notInstructor"))){
			if(temp[0].startsWith(courceid)){
				flag=true;
					
                        }
                }
		 if(!(temp[1].equals("notInstructor"))){
                        if(temp[1].startsWith(courceid)){
				flag=true;
		         }
                }
		return flag;
        }
	
	/**
         * This method is used to create a xml file if it is not exist.
         */

        private File getFile(String reflector){
                String Path="../webapps/brihaspatisync_iserver/"+reflector;
                File existingFile=new File(Path);
                Path = existingFile.getAbsolutePath();
                File file=new File(Path);
		if(file.exists()){
                        return file;
                }else{
                        try {
                                OutputStream fout= new FileOutputStream(file);
                                OutputStream bout= new BufferedOutputStream(fout);
                                OutputStreamWriter out = new OutputStreamWriter(bout, "8859_1");
                                out.write("<?xml version=\"1.0\" ");
                                out.write("encoding=\"ISO-8859-1\"?>\r\n");
                                out.write("<Reflector_Peers>\r\n");
                                out.write("\r\n");
                                out.write("</Reflector_Peers>\r\n");
                                out.flush();
                                out.close();
                        }catch (Exception ex) {ServerLog.getController().Log("Error in creating XML file"+ex.getMessage());}
                        return file;
                }
        }
}//end class
