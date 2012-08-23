package org.bss.brihaspatisync.monitor;

/**
 * ReflectorManager.java
 *
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2011 ETRG, IIT Kanpur.
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
import java.util.StringTokenizer; 

/**
 * @author <a href="mailto:arvindjss17@gmail.com">Arvind Pal </a>
 * @author <a href="mailto:shikhashuklaa@gmail.com">Shikha Shukla </a>
 * @date 05/12/2011
 * Modified for getting courses dynamically 19/04/12	
 */

public class ReflectorManager {
	
	private static ReflectorManager rm=null;
	private NodeList ref_List=null;
	private NodeList user_List=null;
	private final static int maxload = 10;	
	private final static int maxcource = 2;
	private Vector CourseList=new Vector();
	private Vector courses=new Vector();
	private Vector LoadOnReflector=new Vector();
	private int SessionsRunning;	
	
	/**
         * This method returns controller for PeerManager class.
         */

	public static ReflectorManager getController(){
		if(rm==null){
			rm=new ReflectorManager();
		}
		return rm;
	}

	public Vector getReflectorList(){
                Vector v=new Vector();
		v.clear();
		System.gc();
                Element element=null;
                Node node=null;
                try {
                        DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
                        factory.setValidating(false);
                        factory.setIgnoringElementContentWhitespace(false);
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document document = builder.parse(getFile("Monitor.xml"));
                        Element root = document.getDocumentElement();
                        ref_List = root.getElementsByTagName("Reflector");
                        if(ref_List.getLength()==0)
                                System.out.println(" ");

                        for( int i=0; i<ref_List.getLength(); i++ ){
                                node = ref_List.item(i);
                                if( node.getNodeType() == node.ELEMENT_NODE ){
					element = (Element)node;
				        NodeList nodeList = element.getElementsByTagName("IP");
                                        String IP=nodeList.item(0).getChildNodes().item(0).getNodeValue();
                                        if(!v.contains(IP))
                                         v.add(IP.replace("/",""));
                                }
                        } 
                }catch(Exception e){System.out.print("Error in getreflectorList catch in ReflectorManager.java !!");}
                        return v;
        }

	public Vector getReflectorIP(){
		Vector v=new Vector();
		v.clear();
		System.gc();
                Element element=null;
                Node node=null;
                try {
                        DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
                        factory.setValidating(false);
                        factory.setIgnoringElementContentWhitespace(false);
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document document = builder.parse(getFile("MonitorStatus.xml"));
                        Element root = document.getDocumentElement();
                        ref_List = root.getElementsByTagName("ReflectorStatus");
                        if(ref_List.getLength()==0)
				System.out.println("");

                        for( int i=0; i<ref_List.getLength(); i++ ){
                                node = ref_List.item(i);
                                if( node.getNodeType() == node.ELEMENT_NODE ){
                                        element = (Element)node;
					String IP=element.getAttribute("IP");
					if(!v.contains(IP))
                                       	 v.add(element.getAttribute("IP").replace("/",""));
				}
                        }
                }catch(Exception e){System.out.print("");}
		return v;
        }


	public void add(){
		 LoadOnReflector.add(0);
	         LoadOnReflector.add(0);
	}

	public Vector getLoadOnReflector(String ip){
                Element element=null;
                Node node=null;
                int j=0;
                try {
                        DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
                        factory.setValidating(false);
                        factory.setIgnoringElementContentWhitespace(false);
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document document = builder.parse(getFile("MonitorStatus.xml"));
                        Element root = document.getDocumentElement();
                        ref_List = root.getElementsByTagName("ReflectorStatus");
                        if(ref_List.getLength()==0){}
                        for( int i=0; i<ref_List.getLength(); i++ ){
                                node = ref_List.item(i);
                                if( node.getNodeType() == node.ELEMENT_NODE ){
                                        element = (Element)node;
                                        if(element.getAttribute("IP").equals(ip)){
                                                String Status=element.getAttribute("Status");
						if(Status.equals("active"))
                                                j++;
                                                SessionsRunning=j;
                                        }
                                }
                        }
                                        LoadOnReflector.add(SessionsRunning);
					
                }catch(Exception e){}
		return LoadOnReflector;

        }  

	public Vector getReflectorCourses(String ip){
                Element element=null;
                Node node=null;
		String SessionId;
		String status;
                int j=0;
		courses.clear();
		System.gc();
                try {
                        DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
                        factory.setValidating(false);
                        factory.setIgnoringElementContentWhitespace(false);
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document document = builder.parse(getFile("MonitorStatus.xml"));
                        Element root = document.getDocumentElement();
                        ref_List = root.getElementsByTagName("ReflectorStatus");
                        if(ref_List.getLength()==0)
                                System.out.println("");

                        for( int i=0; i<ref_List.getLength(); i++ ){
                                node = ref_List.item(i);
                                if( node.getNodeType() == node.ELEMENT_NODE ){
                                        element = (Element)node;
					status=element.getAttribute("Status");
                                        if(element.getAttribute("IP").equals(ip) ){
						if(status.equals("active")){
                                                SessionId=element.getAttribute("SessionId");
                                          	if(!courses.contains(SessionId))
						courses.add(SessionId);}
                                        }else{courses.clear();}
                                }
                        }

                }catch(Exception e){}
		return courses;
        }

	public Vector getPeerValue(String lectid){
                Vector peerIP=new Vector();
		peerIP.clear();
		Element element=null;
                Node node=null;
                try {

                        DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
                        factory.setValidating(false);
                        factory.setIgnoringElementContentWhitespace(false);
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document document = builder.parse(getFile(lectid+".xml"));
                        Element root = document.getDocumentElement();
                        user_List = root.getElementsByTagName("Peer");
                        if(user_List.getLength()==0)
                                System.out.println("");

                        for( int i=0; i<user_List.getLength(); i++ ){
                                node = user_List.item(i);
                                if( node.getNodeType() == node.ELEMENT_NODE ){
                                        element = (Element)node;
                                        String PrivateIP=element.getAttribute("PrivateIP");
					String str[]=PrivateIP.split("/");
					if(!peerIP.contains(str[1]))
					peerIP.add(str[1]);

                                }
                        }
                }catch(Exception e){}
		return peerIP;
        }

	public static Vector removeDuplicates(Vector a, Vector b) {
		int i = 0;
    		int j = 0;
    		boolean present = true;
    		Vector v = new Vector();
		for (i = 0; i < a.size(); i++) {
      			present = false;
      		for (j = 0; j < b.size(); j++) {
        		if (a.elementAt(i).toString().equalsIgnoreCase(
            			b.elementAt(j).toString())) {
          			present = true;
        		}
      		}		
      			if (!(present)) {
        			v.addElement(a.elementAt(i));
      			}
    		}		

    		return v;
  	}


	
	/**
	 * This method save reflector's information in XML file.
	 */


        private File getFile(String reflector){
                File existingFile=new File(reflector);
                String Path = existingFile.getAbsolutePath();
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
                        }catch (Exception ex) {}
                        return file;
                }
        }
}//end class
