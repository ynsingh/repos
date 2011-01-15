<%@ page language="java" import="java.util.*, in.ac.dei.mhrd.omr.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 
<html> 
	<head>
		<title> ConfirmAnsForm form</title>
	</head>
	<body>
	<%!
	ArrayList<String> ansList=null;
	int test=0;
	int i=0, j=0;
	 %>
	<%
	if(!((String)request.getAttribute("correctSheetMsg")==" "))
	{
	%>
	<%=(String)request.getAttribute("correctSheetMsg")%>
	<%} else{%>
	
		<html:form action="/confirm">
		
		<center><strong><font face="Arial" color="#000040"><bean:message key="label.plsVerify"/></font> </strong><br/></center><br/>
		<table align="center">
		
		
		
		<tr><th><font face="Arial" color="#000040"><bean:message key="label.qno"/> </font></th><th><font face="Arial" color="#000040"><bean:message key="label.ans"/></font> </th></tr>
		<%
		 ansList = (ArrayList<String>)request.getAttribute("confirmAnsList");
		test= (Integer)request.getAttribute("correctAnsId");
		String path=(String)request.getAttribute("path");
		String ques = (String)request.getAttribute("Ques");
		   i=1;
		 for( j = 1;j<ansList.size();j++){%>
		     <tr> <td><%=j %></td><td><%=ansList.get(j)%></td></tr>
		<%		
		}
		
		 %>
		 <tr><td><html:hidden property="testid" value="<%=String.valueOf(test)%>"/>
		 <tr><td><html:hidden property="path" value="<%=path%>"/>
		 <tr><td><html:hidden property="ques" value="<%=ques%>"/>
		<tr>
<td><font face="Arial" color="#000040"><bean:message key="label.wantSubmit"/></font> <html:submit value="Yes"/><html:button value="No" property="btn" onclick="history.back();"/>
</td>			</tr>
			</table>
			
		</html:form>
		<%} %>
	</body>
</html>

