<%@ page language="java"  import="in.ac.dei.mhrd.omr.*, java.util.*"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 
<html> 
	<head>
		<title>JSP for DelQuesForm form</title>
	</head>
	<body>
	<%!
	  ArrayList<ExistingWrongQues> wrongQuesList=null;
	 %>
	<jsp:include page="Menu.jsp"></jsp:include>
							<html:javascript formName="delQues" />  
	
		<html:form action="/delQuesPath" onsubmit="return validateDelQues(this);">
		<font face="Arial" color="#000040"><strong><bean:message key="label.testname"/> : &nbsp;<%=(String)request.getAttribute("testName") %></strong></font><br/>
		<center><font face="Arial" color="#000040"><strong><bean:message key="msg.existingQues"/></strong></font>
		<br/><br/>
		<%
		wrongQuesList= (ArrayList<ExistingWrongQues>)request.getAttribute("qnoList");
		 %>
		<table align="center" cellspacing="5">
		 <tr><td><font face="Arial" color="#000040"><strong><bean:message key="label.qno"/></strong></font></td><td><font face="Arial" color="#000040"><strong>**<bean:message key="label.status"/></strong></font></td></tr>
		
		 <%
		 for(ExistingWrongQues wrongQuesObj: wrongQuesList){
		 
		  %>
		  <tr><td align="center"><%=wrongQuesObj.quesNo %></td><td align="center"><%=wrongQuesObj.status %></td></tr>
		  <%} %>
		 </table><br/>
		<font face="Arial" color="#000040"> <bean:message key="acronyms.wrongQues"/> </font><br/><br/>
		<font face="Arial" color="#000040">	<bean:message key="label.delQues"/> </font><html:text property="qno"/><html:errors property="qno"/><br/>
			<html:hidden property="testName" value="<%=(String)request.getAttribute("testName") %>"/>
			<html:submit/><html:cancel onclick="history.back();"/>
		</html:form>
	</body>
</html>

