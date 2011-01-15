<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="in.ac.dei.mhrd.omr.img.*" %>
<%@ page import="java.io.*" %>

<%@ page import ="javax.servlet.http.HttpServletRequest"%>
<%@ page import ="javax.servlet.http.HttpServletResponse" %>
<%@page import="in.ac.dei.mhrd.omr.CollectData"%>

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
    
    <title>My JSP 'displayResponse.jsp' starting page</title>
    
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
  <jsp:include page="Menu.jsp"></jsp:include><br/>
  <%!
  String qry;
  int testid;
  String fileName=null; 
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
   File sheetName = new File(fileName);						
   rollno=(Integer)request.getAttribute("rollNo");    
    %>
   
  <div align="left"><strong><font face="Arial" color="#000040"><bean:message key="label.rollno"/></font> 

  <%=rollno%></strong>
   <br/> <strong><font face="Arial" color="#000040"><bean:message key="label.filename"/></font> <%=sheetName.getName() %></strong>
    </div>
   <br/>
  	<img src=<%=fileName%>  alt="response sheet" align="left" border="1"></img>
  	
	
				<table align="left" width="35%" height="95%">
 	 				
 	 				<tr>
 	 				<td><b><bean:message key="label.qno"/> </b></td>
 	 				<td><b><bean:message key="label.corrans"/> </b></td>
 	 				<td><b> <bean:message key="label.response"/></b></td>
 	 				</tr>
  				  	<%
  				  	
  				  	
  				  				i=0;	
  				  				corrAns=(ArrayList<String>)request.getAttribute("corrAns");
  				  				resAns=(ArrayList<String>)request.getAttribute("responseAns");
  				  				questionNO=(ArrayList<Integer>)request.getAttribute("questionNo");
  				  				resQues=(ArrayList<Integer>)request.getAttribute("resQues");
  				  				
  				  				for(Integer ques:questionNO)
  				  				{
  				  				if(ques==resQues.get(i))
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
  								out.print("Exception:"+e.getMessage());
  								out.print("exception cause"+e);
  								}
  							 %>	
  				</table>
  				<Input Type="button" value = "BACK" onclick="history.back();"/>
  </body>
</html>
