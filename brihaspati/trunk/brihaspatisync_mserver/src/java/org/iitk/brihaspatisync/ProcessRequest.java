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
 * @author <a href="mailto:arvindjss17@gmail.com"> Arvind Pal </a>
 */

public class ProcessRequest extends HttpServlet {
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		//Retriving client sent req parameters
		String reqType=request.getParameter("req");
		//result=new Vector();
		PrintWriter out = response.getWriter();
		
		if(reqType.equals("getISList")){
			try{
				
				InetAddress clientIP  = InetAddress.getByName(request.getRemoteAddr());
				ServletContext context = getServletContext();
                                File file=new File(context.getRealPath("indexServer.txt"));
				
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
}//end of class



