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
<%@ page language="java" import="java.util.*, in.ac.dei.mhrd.omr.correctAnswer.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 
<html> 
	<head>
		<title> Online OMR Evaluation System</title>
	</head>
	<body>
	<%!
	ArrayList<String> ansList=null;
	String test="0";
	int i=0, j=0;
	HttpSession hs = null;
	 %>
	<%
	try{
	hs = request.getSession();
	if(!((String)hs.getAttribute("correctSheetMsg")).equals("NA"))
	{
	%>
	<%=(String)request.getAttribute("correctSheetMsg")%>
	<%} else{%>
	
		<html:form action="/confirm">
		
		<center><strong><font face="Arial" color="#000040"><bean:message key="label.plsVerify"/></font> </strong><br/></center><br/>
		<table align="center" width="30%" border="1">
		
		
		
		<tr><th><font face="Arial" color="#000040"><bean:message key="label.qno"/> </font></th><th><font face="Arial" color="#000040"><bean:message key="label.ans"/></font> </th></tr>
		<%
		 ansList = (ArrayList<String>)request.getAttribute("confirmAnsList");
		test= (String)request.getAttribute("correctAnsId");
		String path=(String)request.getAttribute("path");
		String ques = (String)request.getAttribute("Ques");
		   i=1;
		 for( j = 1;j<ansList.size();j++){%>
		     <tr> <td align="center"><%=j %></td><td><%=ansList.get(j)%></td></tr>
		<%		
		}
		
		 %>
		 <tr><td><html:hidden property="testid" value="<%=test%>"/></td></tr>
		 <tr><td><html:hidden property="path" value="<%=path%>"/></td></tr>
		 <tr><td><html:hidden property="ques" value="<%=ques%>"/></td></tr>
		<tr>
<td>
</td>			</tr>
			</table><br/>
			<center>
			<font face="Arial" color="#000040"><bean:message key="label.wantSubmit"/></font> <html:submit value="Yes"/><html:cancel value="No"/>
		</center>
		</html:form>
		<%} }
		catch(Exception e){
		System.out.println("error in confirm ans");
		}%>
	</body>
</html>

