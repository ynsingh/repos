package org.iitk.brihaspatisync;

/*@(#)ProcessRequest.java
 * See licence file for usage and redistribution terms
 * Copyright (c) 2007-2008.All Rights Reserved.
 */

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Vector;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;
import java.text.ParsePosition;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.*;
import java.net.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException; 
import org.iitk.brihaspati.modules.utils.EncryptionUtil;
import org.iitk.brihaspatisync.util.ServerLog;
import org.iitk.brihaspatisync.util.ServerUtil;


import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;



 /**
 * @author <a href="mailto:ayadav@iitk.ac.in"> Ashish Yadav </a>
 */

public class ProcessRequest extends HttpServlet {
	
	private Vector indexServerList=null;
	private Vector result=null;
        private String message="";
        private String reqType=null;
        private InetAddress clientIP=null;


	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		//Retriving client sent req parameters
		reqType=request.getParameter("req");
		//result=new Vector();
		PrintWriter out = response.getWriter();
		
		if(reqType.equals("getISList")){
			try{
				
				clientIP  = InetAddress.getByName(request.getRemoteAddr());
				ServerLog.getController().Log("Client = "+clientIP+" want Connect to index Server");
				File file=new File("/home/suneel/brihaspati_sync/webapps/brihaspatisync_mserver/indexServer.txt");
				BufferedReader in = new BufferedReader(new FileReader(file));
                        	String line;
                        	while ((line = in.readLine()) != null) {
                        		response.setContentLength(line.length());
	                                out.println(line);
        	                        out.flush();
					System.out.println(line);
                        	}
				out.close();
                        	if(in != null) in.close();
					
			}catch(Exception e){}
		}
	}//end of post method

	/*public Vector getIndexServerList(){

                indexServerList = new Vector();
                indexServerList.addElement("Select");
                try{
                        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                        DocumentBuilder db = dbf.newDocumentBuilder();
                        Document doc = db.parse("/home/suneel/brihaspati_sync/webapps/brihaspatisync_mserver/indexServer.xml");
                        doc.getDocumentElement().normalize();
                        NodeList nodeLst = doc.getElementsByTagName("entry");
                        for (int s = 0; s < nodeLst.getLength(); s++) {
                                Node fstNode = nodeLst.item(s);
                                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
                                        Element fstElmnt = (Element) fstNode;
                                        NodeList urlElmntLst = fstElmnt.getElementsByTagName("url");
                                        Element urlElmnt = (Element) urlElmntLst.item(0);
                                        NodeList url = urlElmnt.getChildNodes();
                                        String urlValue=((Node) url.item(0)).getNodeValue();

                                        NodeList statusElmntLst = fstElmnt.getElementsByTagName("status");
                                        Element statusElmnt = (Element) statusElmntLst.item(0);
                                        NodeList status = statusElmnt.getChildNodes();
                                        String statusValue=((Node) status.item(0)).getNodeValue();
                                        if(statusValue.equals("active"))
                                                indexServerList.addElement(urlValue);

                                }
                        }
                } catch (Exception e) {e.printStackTrace();}
                return indexServerList;
        }*/

}//end of class



