package org.iitk.brihaspatisync;

/*@(#)HandRaisePortHandler.java
 * See licence file for usage and redistribution terms
 * Copyright (c) 2010-2011.All Rights Reserved.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedOutputStream;
import java.util.Vector;
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

import javax.swing.JOptionPane;

/**
 * @author <a href="mailto:ayadav@iitk.ac.in"> Ashish Yadav </a>
 * @author <a href="mailto:@arvindjss17@gmai..com"> Arvind Pal </a>
 */

public class HandRaisePortHandler {
	
	private int port=2004;
	
	private static HandRaisePortHandler xmlhandle=null;
	
	public static HandRaisePortHandler getController(){
		if(xmlhandle==null)
			xmlhandle=new HandRaisePortHandler();
		return xmlhandle;
	}
	
	private File getFile(){
		String Path="../webapps/brihaspatisync_iserver/HandRaisePort.xml";
                File existingFile=new File(Path);
                Path = existingFile.getAbsolutePath();
                File xmlFile=new File(Path);
       		return xmlFile;
	}	

      		
	private String createQuestionBank(){
		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	       		DocumentBuilder builder = factory.newDocumentBuilder();
        		Document doc = builder.newDocument();
        		Element root = doc.getDocumentElement();
	    		Element rootElement = doc.createElement("HandRaisePort");
			doc.appendChild(rootElement);
      			saveXML(doc,getFile());
        		return "ok";	
		}catch(Exception e){  }
		return "";
	}

	
	public String handRaisePortHandler(String lecture_id){
		try{
			if(!(getFile().exists()))
                                createQuestionBank();
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(getFile());
			Element rootElement=doc.getDocumentElement();
			Element chapElement=doc.createElement("LecturePort");
			rootElement.appendChild(chapElement);
			String searchport=searchPort();
			if(statusLectureID(lecture_id)){
				if(searchport.equals("")) {	
					searchport=Integer.toString(port);
				}else{
					int portnew=port+2;
					searchport=Integer.toString(portnew);
				}
				chapElement.setAttribute("LECTURE_ID",lecture_id);
    				chapElement.setAttribute("PORT",searchport);
	            		saveXML(doc,getFile());
        		   	return "ok";
			}
		}catch(Exception e){ }
		return "";
	}

		
	private boolean statusLectureID(String lecture_id){

                Element element=null;
                Node node=null;
                String lectureid="";
                try{
                        DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse(getFile());
                        Element root = doc.getDocumentElement();
                        NodeList list = root.getElementsByTagName("LecturePort");
                        if(list!=null){
				for(int i=0;i<list.getLength();i++){
                	                node = list.item(i);
        	                        if( node.getNodeType() == node.ELEMENT_NODE ){
	                                        element = ( Element )node;
                                        	lectureid = element.getAttribute("LECTURE_ID").trim();
						if(lectureid.equals(lecture_id)){
							return false;				
						}
					}
				}
			}
                } catch( Exception e ){
                        e.printStackTrace();
                }
                return  true;
        }
	
	public String getPort(String id) {
                Element element=null;
                Node node=null;
                try{
                        DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse(getFile());
                        Element root = doc.getDocumentElement();
                        NodeList list = root.getElementsByTagName("LecturePort");
                        if(list!=null){
                                for( int i=0; i<list.getLength(); i++ ){
                                        node = list.item(i );
                                        if( node.getNodeType() == node.ELEMENT_NODE ){
                                                element = ( Element )node;
                                                String id_name=element.getAttribute("LECTURE_ID");
                                                if(id_name.equals(id)){
							return element.getAttribute("PORT");	
                                                }
                                        }
                                }
                        }

                } catch( Exception e ){}
		return "";
        }
		
	private String searchPort(){
			
                Element element=null;
                Node node=null;
		String searchport="";
                try{
                        DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document doc = builder.parse(getFile());
                        Element root = doc.getDocumentElement();
                        NodeList list = root.getElementsByTagName("LecturePort");
                        if(list!=null){
                        	node = list.item(list.getLength()-1);
                                if( node.getNodeType() == node.ELEMENT_NODE ){
                                	element = ( Element )node;
                                        searchport = element.getAttribute("PORT");
                               	}
                        }
                } catch( Exception e ){
                        e.printStackTrace();
                }
		return  searchport;
        }

	
	private void saveXML(Document doc, File fileName){
		try{
			OutputFormat format = new OutputFormat(doc);
        		format.setIndenting(true);
        		FileOutputStream out=new FileOutputStream(fileName);
            		XMLSerializer output = new XMLSerializer(out, format);
            		output.serialize(doc);
            		out.close();
      		}catch(Exception e){ }
	}

	public void deleteLectureIDAndPort(String id){
		Element element=null;
		Node node=null;
		try{
			DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
           		Document doc = builder.parse(getFile());
           		Element root = doc.getDocumentElement();
           		NodeList list = root.getElementsByTagName("LecturePort");
           		if(list!=null){
           			for( int i=0; i<list.getLength(); i++ ){
            				node = list.item(i );
                			if( node.getNodeType() == node.ELEMENT_NODE ){
                  				element = ( Element )node;
                  				String id_name=element.getAttribute("LECTURE_ID");
                  				if(id_name.equals(id)){
							synchronized (this) {
	                        				root.removeChild(element);
                	          				saveXML(doc,getFile());
							}
                     				}
					}
           			}
           		}
		} catch( Exception e ){}
	}
}
