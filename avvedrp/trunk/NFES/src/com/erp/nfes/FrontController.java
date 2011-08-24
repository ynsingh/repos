package com.erp.nfes;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/*
 * Servlet implementation class for Servlet: FrontController
 *
 */
public class FrontController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{


 protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
 {
	 /*
    System.out.println("----------------------------------------------------------------------------");
    System.out.println("FrontController.doGet() - request LocalName [" + request.getLocalName() + "]");
    System.out.println("FrontController.doGet() - request PathInfo [" + request.getPathInfo() + "]");
    System.out.println("FrontController.doGet() - request PathTranslated [" + request.getPathTranslated() + "]");
    System.out.println("FrontController.doGet() - request RequestURI [" + request.getRequestURI() + "]");
    System.out.println("FrontController.doGet() - request ServerName [" + request.getServerName() + "]");
    System.out.println("FrontController.doGet() - request ServletPath [" + request.getServletPath() + "]");
    System.out.println("FrontController.doGet() - request RequestURL [" + request.getRequestURL() + "]");
    System.out.println("FrontController.doGet() - servlet ServletName [" + this.getServletName() + "]");
    System.out.println("FrontController.doGet() - ServletConfig ServletName [" + this.getServletConfig().getServletName() + "]");
    System.out.println("FrontController.doGet() - ServletContext RealPath [" + this.getServletContext().getRealPath("/") + "]");
      */
    
   String urn = request.getPathInfo();
   String url = "";
   String f = "0";
 //  System.out.println("++++++++++++++++++++++");
 //  System.out.println("URN: "+urn);
   if(urn!=""&&urn!=null&&urn.length()>=7){
     if(urn.equals("/search")){
       url="/profileIndex.jsp";f="1";	 
     }else if(urn.substring(0,7).equals("/index/")){
       if(urn.substring(7)!=null&&urn.substring(7)!=""){url="/staffdetails.jsp?userid="+urn.substring(7);f="1";}	 
     }else if(urn.equals("/GetImageServlet")){
    	 url="/GetImageServlet";f="1"; 	 
     }else if(urn.substring(0,5).equals("/css/")||urn.substring(0,9).equals("/website/")){
    	 url=urn;f="1"; 	 
     }else{
    	 response.sendRedirect("/nfes/login.jsp");
     }
   }else{
  	 response.sendRedirect("/nfes/login.jsp");
   }
   if(f.equals("1")){
    // System.out.println("URL1: "+url);
     ServletContext context = request.getSession().getServletContext();
     url = response.encodeRedirectURL(url);
     // System.out.println("URL2: "+url);
     RequestDispatcher dispatcher = context.getRequestDispatcher(url);
     dispatcher.include(request, response);
   }

 }
 
 protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
{

	 /*
	    System.out.println("----------------------------------------------------------------------------");
	    System.out.println("FrontController.doPost() - request LocalName [" + request.getLocalName() + "]");
	    System.out.println("FrontController.doPost() - request PathInfo [" + request.getPathInfo() + "]");
	    System.out.println("FrontController.doPost() - request PathTranslated [" + request.getPathTranslated() + "]");
	    System.out.println("FrontController.doPost() - request RequestURI [" + request.getRequestURI() + "]");
	    System.out.println("FrontController.doPost() - request ServerName [" + request.getServerName() + "]");
	    System.out.println("FrontController.doPost() - request ServletPath [" + request.getServletPath() + "]");
	    System.out.println("FrontController.doPost() - request RequestURL [" + request.getRequestURL() + "]");
	    System.out.println("FrontController.doPost() - servlet ServletName [" + this.getServletName() + "]");
	    System.out.println("FrontController.doPost() - ServletConfig ServletName [" + this.getServletConfig().getServletName() + "]");
	    System.out.println("FrontController.doPost() - ServletContext RealPath [" + this.getServletContext().getRealPath("/") + "]");
	      */
	    
	   String urn = request.getPathInfo();
	   String url = "";
	   String f = "0";
	 //  System.out.println("++++++++++++++++++++++");
	 //  System.out.println("URN: "+urn);
	   if(urn!=""&&urn!=null&&urn.length()>=7){
	     if(urn.equals("/search")){
	       url="/profileIndex.jsp";f="1";	 
	     }else if(urn.substring(0,7).equals("/index/")){
	       if(urn.substring(7)!=null&&urn.substring(7)!=""){url="/staffdetails.jsp?userid="+urn.substring(7);f="1";}	 
	     }else if(urn.equals("/GetImageServlet")){
	    	 url="/GetImageServlet";f="1"; 	 
	     }else if(urn.substring(0,5).equals("/css/")||urn.substring(0,9).equals("/website/")){
	    	 url=urn;f="1"; 	 
	     }else{
	    	 response.sendRedirect("/nfes/login.jsp");
	     }
	   }else{
	  	 response.sendRedirect("/nfes/login.jsp");
	   }
	   if(f.equals("1")){
	     // System.out.println("URL1: "+url);
	     ServletContext context = request.getSession().getServletContext();
	     url = response.encodeRedirectURL(url);
	     // System.out.println("URL2: "+url);
	     RequestDispatcher dispatcher = context.getRequestDispatcher(url);
	     dispatcher.include(request, response);
	   }
	 
}

  }