package org.iitk.livetv;

/*@(#) ProcessRequest.java
 *
 * See licence file for usage and redistribution terms
 * Copyright (c) 2011-2012. All Rights Reserved.
 *
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

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

 /**
 * @author <a href="mailto:ashish.knp@gmail.com"> Ashish Yadav </a>implemented on dec2011 
 *
 * This class handle http request from livetv client and provide index server list as response.
 */

public class ProcessRequest extends HttpServlet {
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		//Retriving client sent req parameters
		String reqType=request.getParameter("req");
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



