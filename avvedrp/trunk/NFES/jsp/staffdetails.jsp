<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">

<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="javax.sql.DataSource,javax.naming.Context,javax.naming.InitialContext,java.sql.*,java.util.*,java.sql.ResultSetMetaData,java.io.FileInputStream" errorPage="" %>


<%@ taglib prefix="app" uri="http://www.springframework.org/security/tags" %>

<HTML lang=en-US dir=ltr xmlns="http://www.w3.org/11001/xhtml">

<HEADprofile=http://gmpg.org/xfn/11>
<TITLE>Staff List</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<META content="MSHTML 6.00.2900.5694" name=GENERATOR>
<LINK media=screen href="../css/oiostyles.css" type=text/css rel=stylesheet>

<script language="javascript">	 
	
	
</script>
</HEAD>
<BODY class="bodystyle">

<form name="staffdetails" method="post">
<div class="\search_links_div\" align=center >

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
String tabHead="";
try
{	
	userId= Integer.parseInt(user_Id);		
	Properties properties = new Properties();
	properties.load(new FileInputStream("../conf/db.properties"));
	String dbname = properties.getProperty("dbname");		
	String username = properties.getProperty("username");
	String password = properties.getProperty("password");				
	Class.forName("org.gjt.mm.mysql.Driver");
	conn1=DriverManager.getConnection("jdbc:mysql:"+dbname,username,password);			
	
	/* =================== To display Staff Image 19-02-11 Rajitha ================ */
		conn1=DriverManager.getConnection("jdbc:mysql:"+dbname,username,password);	
		PreparedStatement pst=conn1.prepareStatement("SELECT upload_photo FROM staff_profile_report_v0_values WHERE idf=?");
		pst.setInt(1,userId);
		ResultSet rs=pst.executeQuery();
		while(rs.next())
		{	imageName=userId+"/photo/"+rs.getString(1);					
	
		}	
	//	conn1.close();	
	/* ================================ End ======================================= */
	 
	 pst=conn1.prepareStatement("SELECT * from search_result_fields order by sequence");		
	 rs=pst.executeQuery();	
	 while(rs.next()){  
	 
	    //out.println(rs.getString("result_fields"));
	    tabHead=rs.getString("description");
	    if(rs.getString("entity").equals("masterdetails")){
	    	pst=conn1.prepareStatement(rs.getString("result_fields")+" and userid="+userId);		    
	    }
	    else
	    {
	    	pst=conn1.prepareStatement(rs.getString("result_fields")+" and userid="+userId + " and entity_document_master.approved_yesno=1");		    
	    }	
	    ResultSet rs_details=pst.executeQuery();
	    if (rs.getInt("sequence")==1){   %>
	    	    		<table width=70%>
	    			<tr>
	    			<td rowspan="4" width="100" height="100"><img src= "../GetImageServlet?filename=<%=imageName%>" height="100%" width="100%"></td>		
			<%
	    		    while(rs_details.next()){%>
				<td>Name:</td><td><b><%=rs_details.getString("user_full_name")%></b></td></tr>
				<tr><td>University:</td><td><%=rs_details.getString("university")%></td></tr>		
				<tr><td>Institution:</td><td><%=rs_details.getString("Institution")%></td></tr>								
				<tr><td>Department:</td><td><%=rs_details.getString("department")%></td></tr>								
				</table><br><%
				out.println("<table  class=\"searchtab\" width=70% border=\"0\"><tr class=\"search_resul_head\" ><td>"+ tabHead + "</td></tr><tr><td>");  
	    			ResultSetMetaData rsMetaData = rs_details.getMetaData();
	    			int numberOfColumns = rsMetaData.getColumnCount(); 
	    			out.println("<ul>");
	    			String profile_data="";
	    			for(int col=12;col<=numberOfColumns;col++){
	    			  out.println("<li>"+rs_details.getString(rsMetaData.getColumnName(col))+"</li>") ;
	    			  //if (col!=numberOfColumns){profile_data=profile_data+",";}
	    			} 
	    			//out.println("<li>"+profile_data);
	    			out.println("</ul>");
		  }		out.println("</td></tr></table><br>");
		  
	    }
	    else{
	    	    
	    	    
	    	    out.println("<table  class=\"searchtab\"  width=70% border=\"0\"><tr class=\"search_resul_head\"><td>"+ tabHead + "</td></tr><tr ><td>");   	    	    
	    	    out.println("<ul>");
		    while(rs_details.next()){
		    	String profile_data="";
			ResultSetMetaData rsMetaData = rs_details.getMetaData();
			int numberOfColumns = rsMetaData.getColumnCount(); 	   		
			for(int col=12;col<=numberOfColumns;col++){
			  //out.println(rs_details.getString(rsMetaData.getColumnName(col)) );
			  profile_data=profile_data+rs_details.getString(rsMetaData.getColumnName(col)) ;
			  if (col!=numberOfColumns){//out.println(",");
			  profile_data=profile_data+",";
			  }			  
			}  
			out.println("<li>"+profile_data+"</li>");	    		
		  }		
		  out.println("</ul>");
		  out.println("</td></tr></table><br>");			    
	    		
	    }
	}
}
catch(Exception e)	{e.printStackTrace();	}

%>



</div>
</form>

</BODY>

</HTML>
