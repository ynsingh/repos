package com.erp.nfes;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CreateWebpageServlet extends HttpServlet 
{  
	private static final long serialVersionUID = 1L;
	StringBuffer str = new StringBuffer(10000);
	Connection conn;
	
  public void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
	 ConnectDB conObj = new ConnectDB();
     conn = conObj.getMysqlConnection();
    
     String action = request.getParameter("action");    
     String entityID = request.getParameter("entityID");
     String documentID="";
     
try {
	 
	 StringBuffer servlet_url=request.getRequestURL();
	 String application_url= servlet_url.toString();
	 application_url=application_url.replace("/CreateWebpageServlet","");
	 Statement stDetails;	 
	 stDetails = conn.createStatement();	
     ResultSet rsDetails=null;
     String title="",table_name = "", query="",field_displayed = "",visibility_status="";    
     Statement theStatement = this.conn.createStatement();            
     ResultSet theResult;   
    
     /*---------------------------------To fetch document id ----------------------*/
    Statement stDocumentID=conn.createStatement(); 
 	ResultSet rsdocumentId=stDocumentID.executeQuery("select document_id from entity_document_master where entity_id="+entityID + " and entity_type='staff'");
 	while(rsdocumentId.next())
 	{
 		documentID=rsdocumentId.getString("document_id");				
 	}
 	/*----------------------------------------------------------------------------*/
 	
     rsDetails=stDetails.executeQuery("Select * from staff_profile_masterdetails_v0_values where userid="+entityID);
     String facultyName="", 
    		institution_name="",
    		department="",
    		designation="",
    		university_name="",
    		email="",
    		username="";    		   		
     
	if(rsDetails.next()){
		facultyName=rsDetails.getString("full_name");
		institution_name=rsDetails.getString("Institution");
		department=rsDetails.getString("Department");
		designation=rsDetails.getString("Designation");
		university_name=rsDetails.getString("university");
		email=rsDetails.getString("email");
		username=rsDetails.getString("username");
	}        	   
    str.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
    str.append("<?xml-stylesheet type=\"text/xsl\" href=\""+application_url+"/xml/faculty_webpage.xml\" ?>\n");    
    str.append("<FacultyProfile>\n");    
    str.append("<app_path>"+ application_url +"</app_path>\n");
    str.append("<entityID>" + entityID + "</entityID>\n");
    str.append("<Name>" + facultyName + "</Name>\n");    
    str.append("<DocumentId>" + documentID + "</DocumentId>\n");
    str.append("<Institution>" + institution_name + "</Institution>\n");
    str.append("<University>" +   university_name + "</University>\n");
    str.append("<Department>" + department + "</Department>\n");
    str.append("<Designation>" + designation + "</Designation>\n");
    str.append("<Email>" + email + "</Email>\n");
    str.append("<UserName>" + username + "</UserName>\n");
    str.append("<GetPhoto>../GetImageServlet?filename=" + entityID + "/photo/" + "</GetPhoto>\n");
    

   if(request.getSession().getAttribute("userName")!=null){
	   if((request.getSession().getAttribute("userName")).equals(username)){
	    	str.append("<Mode>Edit</Mode>\n");	
	    }else{
	    	str.append("<Mode>View</Mode>\n");	
	    }
   }else{
	   str.append("<Mode>View</Mode>\n");	
   }
	File file = new File(getServletConfig().getServletContext().getRealPath("")+"/xml/faculty_webpage_settings.xml");		  
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	DocumentBuilder db = dbf.newDocumentBuilder();
	Document doc = db.parse(file);
	doc.getDocumentElement().normalize();	  
	NodeList nodeLst = doc.getElementsByTagName("category");
  
	for (int i = 0; i < nodeLst.getLength(); i++) {
	    Node fstNode = nodeLst.item(i);		    
	    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
	    	  Element rootElmnt = (Element) fstNode;
	    	  
	    	  NodeList visibility_statusElmntLst = rootElmnt.getElementsByTagName("visibility_status");
			  Element visibility_statusElmt = (Element) visibility_statusElmntLst.item(0);
			  NodeList visibility_status_value = visibility_statusElmt.getChildNodes();
			  visibility_status=((Node) visibility_status_value.item(0)).getNodeValue();
			  //System.out.println("visibility_status :" + visibility_status);	
			  
	    	  if(visibility_status.equals("Y")){	    	  
		    	  NodeList titleElmntLst = rootElmnt.getElementsByTagName("title");
			      Element titleElmt = (Element) titleElmntLst.item(0);
			      NodeList title_value = titleElmt.getChildNodes();
			      title=((Node) title_value.item(0)).getNodeValue();
			      //System.out.println("entity "  + title);		
			      
				  NodeList table_nameElmntLst = rootElmnt.getElementsByTagName("table_name");
				  Element table_name_Elmnt = (Element) table_nameElmntLst.item(0);
				  NodeList table_name_value = table_name_Elmnt.getChildNodes();
				  table_name=((Node) table_name_value.item(0)).getNodeValue();
				  //System.out.println("table_name: "  + table_name);
				
				  NodeList queryElmntLst = rootElmnt.getElementsByTagName("query");
				  Element queryElmt = (Element) queryElmntLst.item(0);
				  NodeList query_value = queryElmt.getChildNodes();
				  query=((Node) query_value.item(0)).getNodeValue();
				  //System.out.println("Query :" + query);						
				
				  NodeList field_displayedElmntLst = rootElmnt.getElementsByTagName("field_displayed");
				  Element field_displayedElmt = (Element) field_displayedElmntLst.item(0);
				  NodeList field_displayed_value = field_displayedElmt.getChildNodes();
				  field_displayed=((Node) field_displayed_value.item(0)).getNodeValue();						  
				  //System.out.println("field_displayed "  + field_displayed);	
				  String[] fields_displayed = field_displayed.split(",");  
				  
				  //System.out.println(query +" where "+ table_name +".idf="+entityID);
				  theResult = theStatement.executeQuery(query +" where "+ table_name +".idf="+entityID);
				  str.append("<"+table_name+"_title>\n");
				  str.append("<title>"+title+"</title>\n");
				  str.append("</"+table_name+"_title>\n");
				  while(theResult.next()){
					  str.append("<"+table_name+">\n");						 
					  for(int ind=0;ind<fields_displayed.length;ind++){
						  //System.out.println(fields_displayed[ind]);
						  str.append("<"+  fields_displayed[ind]+">"+ theResult.getString(fields_displayed[ind])  +"</"+  fields_displayed[ind]+">\n");
					  }			
					  str.append("</"+table_name+">\n");
				  }// END While					  
				  theResult.close();
	    	  }//End if Visibility=Y				
	       }  // End if
	    }

  theStatement.close();
  this.conn.close();
  str.append("</FacultyProfile>\n");
  response.setContentType("application/xml; charset=UTF-8");
  response.getWriter().println(str); 
  str.delete(0, str.length());
}
    catch (Exception e3) {
      e3.printStackTrace();
    }    
  }

}
