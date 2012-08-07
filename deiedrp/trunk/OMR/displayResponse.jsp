
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

 --> <%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<%@ page import="java.io.*" %>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>displayResponse.jsp</title>
    
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
  <table width="100%">
  <tr><td>  <jsp:include page="header.jsp"></jsp:include></td></tr>
  <tr><td>	<hr width="100%"> </td></tr>
 <tr><td> <jsp:include page="Menu.jsp"></jsp:include></td></tr>
</table>
  <%!
  String qry;
  int testid;
  String fileName=null; 
  String imgLocation="";
    int rollno=0;
    int row=0;
    byte [] responseAnswer;
    byte [] correctAnswer;
    ArrayList<String> resAns=new ArrayList<String>();
    ArrayList<String> corrAns=new ArrayList<String>();
    ArrayList<Integer> questionNO=new ArrayList<Integer>();
    ArrayList<Integer>resQues=new ArrayList<Integer>();
    int i=0;
    %>
    <%
  	try{	       
		
   %>			
   <%
   testid = (Integer)request.getAttribute("testid");
   fileName=(String)request.getAttribute("filename");	
   System.out.println(fileName);
   File sheetName = new File(fileName);						
   rollno=(Integer)request.getAttribute("rollNo");   
  System.out.println("file name aftr replace :" + fileName);
  imgLocation = "processedFolder" +"/"+testid+"/"+ sheetName.getName();
  
    %>
   
  <div align="left"><strong><font face="Arial" color="#000040"><bean:message key="label.rollno"/></font> 

  <%=rollno%></strong>
   <br/> <strong><font face="Arial" color="#000040"><bean:message key="label.filename"/></font> <%=sheetName.getName() %></strong>
    </div>
   <br/>
   <table>
       <tr><td valign="top">   
     	<html:img src="<%=imgLocation%>"  height="1200px" width="750px" alt="response sheet" align="left" border="1"></html:img>
	</td><td valign="top">
				<table cellspacing="4" border="1">
 	 				
 	 				<tr>
 	 				<td><font face="Arial" color="#000040"><bean:message key="label.qno"/> </font></td>
 	 				<td><font face="Arial" color="#000040"><bean:message key="label.corrans"/> </font></td>
 	 				<td><font face="Arial" color="#000040"> <bean:message key="label.response"/></font></td>
 	 				<td><Input Type="button" value = "BACK" onclick="history.back();"/></td>
 	 				</tr>
  				  	<%
  				  	
  				  	
  				  				i=0;	
  				  				corrAns=(ArrayList<String>)request.getAttribute("corrAns");
  				  				resAns=(ArrayList<String>)request.getAttribute("responseAns");
  				  				questionNO=(ArrayList<Integer>)request.getAttribute("questionNo");
  				  				resQues=(ArrayList<Integer>)request.getAttribute("resQues");
  				  				System.out.println("ques size : " + questionNO.size());
  				  				for(Integer ques:questionNO)
  				  				{ 
  				  				if(resQues.size()>0 && ques==resQues.get(i))
								{  				  				
   				  	%>
						<tr><td><%=ques%></td><td> 
  						<%=corrAns.get(ques-1)%>
  						</td><td><%=resAns.get(i)%></td></tr>
  								
  							<%i++;
  							if(i==resQues.size())
  								{
  								i=i-1;
  								}
  							}
  							else{
  							%>
  							<tr><td><%=ques%></td><td> 
  						<%=corrAns.get(ques-1)%>
  						</td><td>---</td></tr>
  							<%
  							}
  							}
  							i=0;
  							}catch(Exception e)
  								{
  								//System.out.println("Ex " + e);
  								}
  							 %>	
  				</table>
  				</td></tr></table>
  				<Input Type="button" value = "BACK" onclick="history.back();"/>
  </body>
</html>
