<!-- 
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 * Author: Anshul Agarwal

 -->

<%@ page language="java" import="java.util.*, java.sql.*, in.ac.dei.mhrd.omr.dbConnection.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    
    <title>Online OMR Evaluation System</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <div>
    <jsp:include page="header.jsp"></jsp:include>
	</div>
	<hr width="100%">
  <jsp:include page="Menu.jsp"></jsp:include> 
  
   <br/><br/>
   <%!
   String TestName="";
   int TestId=0;
   Connection con=null;
   PreparedStatement ps=null;
   ResultSet rs=null;
    %>
   <%
   	if((String) request.getAttribute("TestName")!=""){
   		 TestName  =(String) request.getAttribute("TestName");
   		TestId = (Integer)request.getAttribute("testid");
   		String table = (String)request.getAttribute("tableName");
   		try{
   		  con = (Connection) Connect.prepareConnection();
   		     
                 ps = con.prepareStatement(
                        "select  * from "+ table +" where TestId = ?");
                 ps.setInt(1, TestId);
                 rs = ps.executeQuery();
   %>
   
  <font color="#000040"><font face="Arial"><strong><bean:message key="label.errLog"/><br><bt>
  <bean:message key="label.testname"/>:&nbsp;<%=TestName %></font>
  
		
		<table align ="center" width="60%" border="1">
		<tr> 
		<%if(table.equalsIgnoreCase("log")){ %>
		  <th> <font face="Arial" color="#000040"><bean:message key="label.filename"/> </font> </th><th><font face="Arial" color="#000040"> <bean:message key="label.code"/> </font></th><th><font face="Arial" color="#000040">  <bean:message key="label.reason"/> </font></th>
		 <%} else{ %>
		 		  <th> <font face="Arial" color="#000040"><bean:message key="label.TotalSheets"/> </font> </th><th><font face="Arial" color="#000040"> <bean:message key="label.RejectedSheets"/> </font></th><th><font face="Arial" color="#000040">  <bean:message key="label.Processed"/> </font></th>
		 <%} %>
		  </tr>
		  <% while(rs.next()){ %>
		  <tr><td align="left"><%=rs.getString(2) %></td><td align="center"><%=rs.getString(3) %></td><td align="center"><%=rs.getString(4)%></td></tr>
		  <%}
		  } catch (Exception e){
		  System.out.println("error while retrieve from r log : " +e);
		  }
		  finally{
		  Connect.freeConnection(con);
		  }
		  
		  }
		   %>
		   <tr><td> <html:button value="Back" property="btn" onclick="history.back();"/></td></tr>
		  </table>
		  
		 
		 
		  </body>
</html>
