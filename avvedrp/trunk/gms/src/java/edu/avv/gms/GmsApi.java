package edu.avv.gms;

import java.io.*;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import edu.avv.util.DateUtils;
import edu.avv.util.GetConsole;
import edu.avv.util.GetPropertiesFile;
import edu.avv.ws.xml.MarshalXml;
import edu.avv.ws.xml.PostXmlRequest;
import edu.avv.ws.xml.UnmarshalXml;
import edu.avv.ws.xml.request.RequestObjectFactory;
import edu.avv.ws.xml.request.WSRequest;
import edu.avv.ws.xml.response.WSResponse;
import edu.avv.ws.xml.response.WSResponse.Body.Message;

public class GmsApi {
  
	public ByteArrayInputStream getProjectNamesFromGMS(String institute, String subinstitute,String department){
	GetConsole.info("---------- [Client:"+GetPropertiesFile.GetPropertiesFromPropertiesFile("gms-ws.properties","CLIENT")+"] ----------");  
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	ByteArrayInputStream inStream = null;
	WSResponse wsresponse = new WSResponse();
    RequestObjectFactory object = new RequestObjectFactory();
    WSRequest wsrequest = object.createWSRequest();
    wsrequest.setHeader(object.createWSRequestHeader());
    wsrequest.setBody(object.createWSRequestBody());
    wsrequest.getHeader().setClient(GetPropertiesFile.GetPropertiesFromPropertiesFile("gms-ws.properties","CLIENT"));
    wsrequest.getHeader().setDate(DateUtils.now("yyyy-MM-dd"));
    wsrequest.getHeader().setTime(DateUtils.now("K:mm:ss a z"));
    wsrequest.getHeader().setUserName(GetPropertiesFile.GetPropertiesFromPropertiesFile("gms-ws.properties","CLIENT_SERVICE_USERNAME"));
    wsrequest.getHeader().setPassword(GetPropertiesFile.GetPropertiesFromPropertiesFile("gms-ws.properties","CLIENT_SERVICE_PASSWORD"));
  //change the method name and add curresponding parameters
    wsrequest.getBody().setMethod("getProjectNamesFromGMS");
    WSRequest.Body.Parameter x = object.createWSRequestBodyParameter();
    //String institute = "MGU";
    //String subinstitute = "MHRD";
    //String department = "ECE";
    x.setName("institute");x.setValue(institute);
    wsrequest.getBody().getParameter().add(x);
    WSRequest.Body.Parameter y = object.createWSRequestBodyParameter();
    y.setName("subinstitute");y.setValue(subinstitute);
    WSRequest.Body.Parameter z = object.createWSRequestBodyParameter();
    z.setName("department");z.setValue(department);
    //end
    wsrequest.getBody().getParameter().add(y);
    wsrequest.getBody().getParameter().add(z);
	MarshalXml.marshalRequest(wsrequest, out);
	InputStream in = PostXmlRequest.postXml(out);
	GetConsole.info("---------- [Client:"+GetPropertiesFile.GetPropertiesFromPropertiesFile("gms-ws.properties","CLIENT")+"] ----------");
	GetConsole.info("Reading XML Response");
	wsresponse = UnmarshalXml.unmarshalResponse(in);
	
	StringBuffer xmlObj = new StringBuffer();
	if(!(wsresponse.getBody().getMessage().get(0).getName().toString().equals("ERROR_MESSAGE"))){
	xmlObj.append("<?xml version='1.0' encoding='UTF-8' standalone='yes'?>");
	xmlObj.append("<INSTITUTENAME>");
		 	xmlObj.append("<INSTITUTE-NAME>");
		    	xmlObj.append(wsresponse.getBody().getMessage().get(0).getValue().get(0));
		    	xmlObj.append("</INSTITUTE-NAME>");
  	
	xmlObj.append("<SUB-INSTITUTE>");
	xmlObj.append("<SUB-INSTITUTE-NAME>");
		xmlObj.append(wsresponse.getBody().getMessage().get(1).getValue().get(0));
	xmlObj.append("</SUB-INSTITUTE-NAME>");
	xmlObj.append("<DEPARTMENT>");
	xmlObj.append("<DEPARTMENT-NAME>");
	xmlObj.append(wsresponse.getBody().getMessage().get(2).getValue().get(0));
xmlObj.append("</DEPARTMENT-NAME>");
	
	for(int i=0;i<wsresponse.getBody().getMessage().get(3).getValue().size();i++){
    	xmlObj.append("<PROJECT>");
    		 	xmlObj.append("<PROJECTNAME>");
		    	xmlObj.append(wsresponse.getBody().getMessage().get(3).getValue().get(i));
		    	xmlObj.append("</PROJECTNAME>");
		    	xmlObj.append("<PROJECTCODE>");
		    	xmlObj.append(wsresponse.getBody().getMessage().get(4).getValue().get(i));
		    	xmlObj.append("</PROJECTCODE>");
    	
    	xmlObj.append("</PROJECT>");
    }
		xmlObj.append("</DEPARTMENT>");
	xmlObj.append("</SUB-INSTITUTE>");
	xmlObj.append("</INSTITUTENAME>");
	System.out.print("xmlObj  - "+xmlObj);
	inStream = new ByteArrayInputStream(xmlObj.toString().getBytes());
	}
	else {
		inStream=new ByteArrayInputStream(wsresponse.toString().getBytes());;
		
	}
	return inStream;
  }
	public ByteArrayInputStream getBudgetHeadsFromGMS(String project){
		GetConsole.info("---------- [Client:"+GetPropertiesFile.GetPropertiesFromPropertiesFile("gms-ws.properties","CLIENT")+"] ----------");  
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream inStream = null;
		WSResponse wsresponse = new WSResponse();
	    RequestObjectFactory object = new RequestObjectFactory();
	    WSRequest wsrequest = object.createWSRequest();
	    wsrequest.setHeader(object.createWSRequestHeader());
	    wsrequest.setBody(object.createWSRequestBody());
	    wsrequest.getHeader().setClient(GetPropertiesFile.GetPropertiesFromPropertiesFile("gms-ws.properties","CLIENT"));
	    wsrequest.getHeader().setDate(DateUtils.now("yyyy-MM-dd"));
	    wsrequest.getHeader().setTime(DateUtils.now("K:mm:ss a z"));
	    wsrequest.getHeader().setUserName(GetPropertiesFile.GetPropertiesFromPropertiesFile("gms-ws.properties","CLIENT_SERVICE_USERNAME"));
	    wsrequest.getHeader().setPassword(GetPropertiesFile.GetPropertiesFromPropertiesFile("gms-ws.properties","CLIENT_SERVICE_PASSWORD"));
	  //change the method name and add curresponding parameters
	    wsrequest.getBody().setMethod("getBudgetHeadsFromGMS");
	    WSRequest.Body.Parameter x = object.createWSRequestBodyParameter();
	   
	    //String project = "Devecontent";
	    x.setName("project");x.setValue(project);
	    wsrequest.getBody().getParameter().add(x);
	    
		MarshalXml.marshalRequest(wsrequest, out);
		InputStream in = PostXmlRequest.postXml(out);
		GetConsole.info("---------- [Client:"+GetPropertiesFile.GetPropertiesFromPropertiesFile("gms-ws.properties","CLIENT")+"] ----------");
		GetConsole.info("Reading XML Response");
		wsresponse = UnmarshalXml.unmarshalResponse(in);
		StringBuffer xmlObj = new StringBuffer();
		if(!(wsresponse.getBody().getMessage().get(0).getName().toString().equals("ERROR_MESSAGE"))){
		xmlObj.append("<?xml version='1.0' encoding='UTF-8' standalone='yes'?>");
		xmlObj.append("<INSTITUTENAME>");
			 	xmlObj.append("<INSTITUTE-NAME>");
			    	xmlObj.append(wsresponse.getBody().getMessage().get(0).getValue().get(0));
			    	xmlObj.append("</INSTITUTE-NAME>");
	  	
		xmlObj.append("<SUB-INSTITUTE>");
		xmlObj.append("<SUB-INSTITUTE-NAME>");
			xmlObj.append(wsresponse.getBody().getMessage().get(1).getValue().get(0));
		xmlObj.append("</SUB-INSTITUTE-NAME>");
		xmlObj.append("<DEPARTMENT>");
		xmlObj.append("<DEPARTMENT-NAME>");
		xmlObj.append(wsresponse.getBody().getMessage().get(2).getValue().get(0));
	xmlObj.append("</DEPARTMENT-NAME>");
	xmlObj.append("<PROJECT>");
	xmlObj.append("<PROJECTNAME>");
	xmlObj.append(wsresponse.getBody().getMessage().get(3).getValue().get(0));
	xmlObj.append("</PROJECTNAME>");
		for(int i=0;i<wsresponse.getBody().getMessage().get(3).getValue().size();i++){
	    	xmlObj.append("<BUDGET>");
	    		 	xmlObj.append("<BUDGET-ID>");
			    	xmlObj.append(wsresponse.getBody().getMessage().get(4).getValue().get(i));
			    	xmlObj.append("</BUDGET-ID>");
			    	xmlObj.append("<BUDGET-HEAD>");
			    	xmlObj.append(wsresponse.getBody().getMessage().get(5).getValue().get(i));
			    	xmlObj.append("</BUDGET-HEAD>");
	    	
	    	xmlObj.append("</BUDGET>");
	    }
		xmlObj.append("</PROJECT>");
		xmlObj.append("</DEPARTMENT>");
		xmlObj.append("</SUB-INSTITUTE>");
		xmlObj.append("</INSTITUTENAME>");
		System.out.print("xmlObj  - "+xmlObj);
		inStream = new ByteArrayInputStream(xmlObj.toString().getBytes());
		}
		else {
			inStream=new ByteArrayInputStream(wsresponse.toString().getBytes());
			
		}
		return inStream;
	
	}
public ByteArrayInputStream getBudgetAmountFromGMS(String project,String budgetId){
		GetConsole.info("---------- [Client:"+GetPropertiesFile.GetPropertiesFromPropertiesFile("gms-ws.properties","CLIENT")+"] ----------");  
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream inStream = null;
		WSResponse wsresponse = new WSResponse();
	    RequestObjectFactory object = new RequestObjectFactory();
	    WSRequest wsrequest = object.createWSRequest();
	    wsrequest.setHeader(object.createWSRequestHeader());
	    wsrequest.setBody(object.createWSRequestBody());
	    wsrequest.getHeader().setClient(GetPropertiesFile.GetPropertiesFromPropertiesFile("gms-ws.properties","CLIENT"));
	    wsrequest.getHeader().setDate(DateUtils.now("yyyy-MM-dd"));
	    wsrequest.getHeader().setTime(DateUtils.now("K:mm:ss a z"));
	    wsrequest.getHeader().setUserName(GetPropertiesFile.GetPropertiesFromPropertiesFile("gms-ws.properties","CLIENT_SERVICE_USERNAME"));
	    wsrequest.getHeader().setPassword(GetPropertiesFile.GetPropertiesFromPropertiesFile("gms-ws.properties","CLIENT_SERVICE_PASSWORD"));
	  //change the method name and add curresponding parameters
	    wsrequest.getBody().setMethod("getBudgetAmountFromGMS");
	    WSRequest.Body.Parameter x = object.createWSRequestBodyParameter();
	   
	    //String project = "ERP/Pilot/IITKanpur";
		// String budgetId = "Grant Funding";
	    x.setName("project");x.setValue(project.toString());
	    wsrequest.getBody().getParameter().add(x);
	    WSRequest.Body.Parameter y = object.createWSRequestBodyParameter();
		   
	    
	    y.setName("budgetId");y.setValue(budgetId.toString());
	    wsrequest.getBody().getParameter().add(y);
	    
		MarshalXml.marshalRequest(wsrequest, out);
		InputStream in = PostXmlRequest.postXml(out);
		GetConsole.info("---------- [Client:"+GetPropertiesFile.GetPropertiesFromPropertiesFile("gms-ws.properties","CLIENT")+"] ----------");
		GetConsole.info("Reading XML Response");
		wsresponse = UnmarshalXml.unmarshalResponse(in);
		StringBuffer xmlObj = new StringBuffer();
		if(!(wsresponse.getBody().getMessage().get(0).getName().toString().equals("ERROR_MESSAGE"))){
			xmlObj.append("<?xml version='1.0' encoding='UTF-8' standalone='yes'?>");
			xmlObj.append("<INSTITUTENAME>");
			 	xmlObj.append("<INSTITUTE-NAME>");
			    	xmlObj.append(wsresponse.getBody().getMessage().get(0).getValue().get(0));
			    	xmlObj.append("</INSTITUTE-NAME>");
	  	
		xmlObj.append("<SUB-INSTITUTE>");
		xmlObj.append("<SUB-INSTITUTE-NAME>");
			xmlObj.append(wsresponse.getBody().getMessage().get(1).getValue().get(0));
		xmlObj.append("</SUB-INSTITUTE-NAME>");
		xmlObj.append("<DEPARTMENT>");
		xmlObj.append("<DEPARTMENT-NAME>");
		xmlObj.append(wsresponse.getBody().getMessage().get(2).getValue().get(0));
	xmlObj.append("</DEPARTMENT-NAME>");
	xmlObj.append("<PROJECT>");
	xmlObj.append("<PROJECTNAME>");
	xmlObj.append(wsresponse.getBody().getMessage().get(3).getValue().get(0));
	xmlObj.append("</PROJECTNAME>");
		for(int i=0;i<wsresponse.getBody().getMessage().get(3).getValue().size();i++){
	    	xmlObj.append("<BUDGET>");
	    		 	xmlObj.append("<BUDGET-ID>");
			    	xmlObj.append(wsresponse.getBody().getMessage().get(4).getValue().get(i));
			    	xmlObj.append("</BUDGET-ID>");
			    	xmlObj.append("<BUDGET-HEAD>");
			    	xmlObj.append(wsresponse.getBody().getMessage().get(5).getValue().get(i));
			    	xmlObj.append("</BUDGET-HEAD>");
			    	xmlObj.append("<BUDGET-SANCTIONED>");
			    	xmlObj.append(wsresponse.getBody().getMessage().get(6).getValue().get(i));
			    	xmlObj.append("</BUDGET-SANCTIONED>");
			    	xmlObj.append("<BUDGET-AVAILABLE>");
			    	xmlObj.append(wsresponse.getBody().getMessage().get(7).getValue().get(i));
			    	xmlObj.append("</BUDGET-AVAILABLE>");
	    	
	    	xmlObj.append("</BUDGET>");
	    }
		xmlObj.append("</PROJECT>");
		xmlObj.append("</DEPARTMENT>");
		xmlObj.append("</SUB-INSTITUTE>");
		xmlObj.append("</INSTITUTENAME>");
		System.out.print("xmlObj  - "+xmlObj);
		inStream = new ByteArrayInputStream(xmlObj.toString().getBytes());
		
		}
		else {
			inStream=new ByteArrayInputStream(wsresponse.toString().getBytes());
			
		}
		return inStream;
	}
  
}
