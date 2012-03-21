package com.erp.nfes;

import java.io.IOException;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Servlet implementation class for Servlet: URLTranslater
 *
 */
public class URLTranslater extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{	
 protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException {	
    if(request.getServletPath().equals("/webpage")){  
    	String physical_url="/CreateWebpageServlet?action=WEBPAGE&entityID="+request.getPathInfo().replace("/", "");
    	RequestDispatcher dispatcher=getServletContext().getRequestDispatcher(physical_url);    	
    	dispatcher.forward( request, response );
    }
 }

}