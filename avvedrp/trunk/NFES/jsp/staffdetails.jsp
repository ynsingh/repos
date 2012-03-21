<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">

<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="javax.sql.DataSource,javax.naming.Context,javax.naming.InitialContext,java.sql.*,java.util.*,java.sql.ResultSetMetaData,java.io.FileInputStream,javax.xml.parsers.DocumentBuilderFactory,javax.xml.parsers.DocumentBuilder,org.w3c.dom.*,java.io.File" errorPage="" %>
<%@ taglib prefix="app" uri="http://www.springframework.org/security/tags" %>

<jsp:useBean id="db" class="com.erp.nfes.ConnectDB" scope="session"/> 

<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml">

<HEADprofile=http://gmpg.org/xfn/11>
<TITLE>Staff Details</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<META content="MSHTML 6.00.2900.5694" name=GENERATOR>
<LINK media=screen href="../css/oiostyles.css" type=text/css rel=stylesheet>

<script language="javascript">	 
	
	
</script>
</HEAD>
<BODY class="bodystyle">

<form name="staffdetails" method="post">
<div  class="listdiv">
<div style="border: 1px #9dc6d9 solid;"  align="center">
<br>
<%
String str=null;
String rowclass="1";
String userName = null;
String university=null;
String institution=null;
String user_Id=request.getParameter("userid");
int userId;
String email = null;
String userFullName = null;
Connection conn1=null;
Connection conn2=null;
String imageName="";	
  String entity="";
  String tabHead="";
  String query="";
  String displayed_fields="";
try
{	
	userId= Integer.parseInt(user_Id);		
	conn1 = db.getMysqlConnection();
	
	/* =================== To display Staff Image 19-02-11 Rajitha ================ */
		PreparedStatement pst=conn1.prepareStatement("SELECT upload_photo FROM staff_profile_report_v0_values WHERE idf=?");
		pst.setInt(1,userId);
		ResultSet rs=pst.executeQuery();
		while(rs.next())
		{	imageName=userId+"/photo/"+rs.getString(1);					
	
		}	
	/* ================================ End ======================================= */
	
	
	/*============================XML FILE==================================================*/
		  File file = new File(getServletContext().getRealPath("/")+"xml/basic_search_settings.xml");		  
		  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		  DocumentBuilder docbuilder = dbf.newDocumentBuilder();
		  Document doc = docbuilder.parse(file);
		  doc.getDocumentElement().normalize();
		  NodeList nodeLst = doc.getElementsByTagName("table");
		  

		  for (int s = 0; s < nodeLst.getLength(); s++) {
		    Node fstNode = nodeLst.item(s);		    
		    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {		  
		      Element rootElmnt = (Element) fstNode;		      
		      

		      NodeList fldNameElmntLstentity = rootElmnt.getElementsByTagName("entity");
		      Element fld_nameElmtentity = (Element) fldNameElmntLstentity.item(0);
		      NodeList fld_nameentity = fld_nameElmtentity.getChildNodes();
		      entity=((Node) fld_nameentity.item(0)).getNodeValue();
//		      out.println("entity "  + entity);
		      		      
		      NodeList fldCaptionElmntLst = rootElmnt.getElementsByTagName("description");
		      Element fld_caption_Elmnt = (Element) fldCaptionElmntLst.item(0);
		      NodeList fld_caption = fld_caption_Elmnt.getChildNodes();
		      tabHead=((Node) fld_caption.item(0)).getNodeValue();
//		      out.println("Description: "  + description);

		      NodeList fldNameElmntLst = rootElmnt.getElementsByTagName("query");
		      Element fld_nameElmt = (Element) fldNameElmntLst.item(0);
		      NodeList fld_name = fld_nameElmt.getChildNodes();
			  query=((Node) fld_name.item(0)).getNodeValue();
//		      out.println("Query :" + query);


		      NodeList fldNameElmntLstfields = rootElmnt.getElementsByTagName("displayed_fields");
		      Element fld_nameElmtfields = (Element) fldNameElmntLstfields.item(0);
		      NodeList fld_namefields = fld_nameElmtfields.getChildNodes();
		      displayed_fields=((Node) fld_namefields.item(0)).getNodeValue();
//			  out.println("fields "  + displayed_fields);

			  if(entity.equals("masterdetails")){
			    	pst=conn1.prepareStatement(query+" where userid="+userId);		    	    	
				    ResultSet rs_details=pst.executeQuery();  	
					%>
	    	    	<table width=90% >
	    			<tr>
	    			<td rowspan="4" width="100" height="100"><img src= "../GetImageServlet?filename=<%=imageName%>" height="100%" width="100%"></td>		
					<% while(rs_details.next()){%>
						<td>Name:</td><td><b><%=rs_details.getString("user_full_name")%></b></td></tr>
						<tr><td>University:</td><td><%=rs_details.getString("university")%></td></tr>		
						<tr><td>Institution:</td><td><%=rs_details.getString("Institution")%></td></tr>								
						<tr><td>Department:</td><td><%=rs_details.getString("department")%></td></tr>								
						</table><br><%
					out.println("<table  class=\"searchtab\" width=90% border=\"0\"><tr class=\"search_resul_head\" ><td>"+ tabHead + "</td></tr><tr><td>");  
	    			out.println("<ul>");
	    			String profile_data="";	    			
	    			String[] displayedfields = displayed_fields.split(",");
			        for(int colindex=0;colindex<displayedfields.length;colindex++){
		    			if(rs_details.getString(displayedfields[colindex])!=null){	    			
		    			  out.println("<li>"+rs_details.getString(displayedfields[colindex])+"</li>") ;
	    				 } 			           
			        } 			    			
	    			out.println("</ul>");
		  		}	
		  		out.println("</td></tr></table><br>");					
				
		    }else{
			   	pst=conn1.prepareStatement(query+" and userid="+userId + " and entity_document_master.approved_yesno=1");		    
			   	//out.println(query+" and userid="+userId + " and entity_document_master.approved_yesno=1");
			    ResultSet rs_details=pst.executeQuery(); 
			    
			    int rowcount=0;
				while(rs_details.next()){			    
					if(rowcount==0){
					out.println("<table  class=\"searchtab\"  width=90% border=\"0\"><tr class=\"search_resul_head\"><td>"+ tabHead + "</td></tr><tr ><td>");   	    	    
			        out.println("<ul>");	       
					}
					rowcount=rowcount+1;
			    	String profile_data="";
	    			String[] displayedfields = displayed_fields.split(",");
			        for(int colindex=0;colindex<displayedfields.length;colindex++){
					    profile_data=profile_data+rs_details.getString(displayedfields[colindex]) ;
			  		    if (colindex+1!=displayedfields.length && (!profile_data.equals(""))){
						  profile_data=profile_data+", ";
						    }			  
					}  
				if(profile_data.substring(profile_data.length()-2).equals(", ")){
					profile_data=profile_data.substring(0,profile_data.length()-2);
				}
				out.println("<li>"+profile_data+"</li>");	    		
				}	//while
				  if(rowcount>0){
				  out.println("</ul>");
				  out.println("</td></tr></table><br>");
				  }	//if
		  	}	//else    
		}//if
} //for
}		//try
catch(Exception e)	{e.printStackTrace();	}
conn1.close();

%>
<br>

</div>
</div>
</form>

</BODY>

</HTML>
