package edu.avv.ws.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.avv.util.GetConsole;
import edu.avv.util.GetPropertiesFile;
import edu.avv.ws.service.ServiceRequest;
import edu.avv.ws.xml.MarshalXml;
import edu.avv.ws.xml.UnmarshalXml;
import edu.avv.ws.xml.request.WSRequest;
import edu.avv.ws.xml.response.WSResponse;

/**
 * Servlet implementation class WSServlet
 */
public class WSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WSRequest wsrequest = new WSRequest();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WSServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  response.setContentType("text/html; charset=UTF-8");
	  PrintWriter out = response.getWriter();
	  out.println("<Html><Head><Title>Access Denied.</Title></Head><Body>" +
	  		"<h1>Access Denied!</h1>" +
	  		"<p>You are not allowed to access this resource!</p></Body></Html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  response.setContentType("text/xml");
	  GetConsole.info("---------- [Server:"+GetPropertiesFile.GetPropertiesFromPropertiesFile("gms-ws.properties","SERVER")+"] ----------");
	  if(readXmlRequest(request)){ 
	    if(securityCheck()){
	      getService("SERVICE_PROCESS",response);	
	    }else{
	      getService("SERVICE_SEND_ERROR",response);	
	    }
	  }else{
	    getService("SERVICE_SEND_ERROR",response); 
	  }
	  GetConsole.info("Completed Service Lifecycle");
	}

  public boolean readXmlRequest(HttpServletRequest request){
    try {
      GetConsole.info("Reading XML Request");	
	  wsrequest = UnmarshalXml.unmarshalRequest(request.getInputStream());
	} catch (IOException e) {
	  GetConsole.info("Error While Reading XML Request");	
	  e.printStackTrace();
	  return false;	
	}
    GetConsole.info("Received request from "+wsrequest.getHeader().getClient()+" With Time:"+wsrequest.getHeader().getTime()+" And Date:"+wsrequest.getHeader().getDate());
	return true;
  }
  public boolean securityCheck(){
	String userName = GetPropertiesFile.GetPropertiesFromPropertiesFile("gms-ws.properties","SERVER_SERVICE_USERNAME");
	String password = GetPropertiesFile.GetPropertiesFromPropertiesFile("gms-ws.properties","SERVER_SERVICE_PASSWORD");
	if(userName.equals(wsrequest.getHeader().getUserName())&&password.equals(wsrequest.getHeader().getPassword())){
	  GetConsole.info("Authentication Successfull: UserName:"+userName);
	  return true;	
	}else{
	  GetConsole.info("Authentication Failed: Invalid Username or Password");
	  return false;	
	}
  }
  public void getService(String service,HttpServletResponse response){
	
	WSResponse wsresponse = new WSResponse();
	if(service.equals("SERVICE_PROCESS")){
	  GetConsole.info("Processing WS Method "+wsrequest.getBody().getMethod());	
      wsresponse = ServiceRequest.createResponse(wsrequest);
    }else if(service.equals("SERVICE_SEND_ERROR")){
      GetConsole.info("Processing Error Report [Error:"+GetConsole.getMessage()+"]");
      wsresponse = ServiceRequest.createErrorResponse(GetConsole.getMessage());
    }
	try {
	  MarshalXml.marshalResponse(wsresponse,response.getOutputStream());
	  GetConsole.info("Sending XML Response To Client");
	} catch (IOException e) {
	  e.printStackTrace();
	  GetConsole.info("Error while Sending XML");
	}
  }

}