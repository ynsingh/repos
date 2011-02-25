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

<%@ page language="java"  import="in.ac.dei.mhrd.omr.misprintedQues.*, java.util.*"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 
<html> 
	<head>
		<title>Online OMR Evaluation System</title>
	</head>
	<body>
	<%!
	  TreeMap<Integer, String> wrongQuesList=null;
	  String val="";
	  String test="";
	  int key=0;
	 %>
	 <%
	 test= (String)session.getAttribute("testName");
	  %>
	  <div>
    <jsp:include page="header.jsp"></jsp:include>
	</div>
	<hr width="100%">
	<jsp:include page="Menu.jsp"></jsp:include>
							<html:javascript formName="delQues" />  
	
		<html:form action="/delQuesPath" onsubmit="return validateDelQues(this);">
		<font face="Arial" color="#000040"><strong><bean:message key="label.testname"/> : &nbsp;<%=test %></strong></font><br/>
		<center><font face="Arial" color="#000040"><strong><bean:message key="msg.existingQues"/></strong></font>
		<br/><br/>
		<%
		try{
		wrongQuesList= (TreeMap<Integer, String>)session.getAttribute("qnoList");
		//TreeMap<Integer, String> wrongQuesList = ExistingWrongQues.getWrongQues(test);
		System.out.println("List cize " + wrongQuesList.size());
		 %>
		<table align="center" cellspacing="5">
		 <tr><td><font face="Arial" color="#000040"><strong><bean:message key="label.qno"/></strong></font></td><td><font face="Arial" color="#000040"><strong>**<bean:message key="label.status"/></strong></font></td></tr>
		
		 <%
		 for (Map.Entry<Integer, String> entry : wrongQuesList.entrySet()) {
         val = entry.getValue();
         key = entry.getKey();
        System.out.println("val : " + val +" key : "+ key);
		  %>
		  <tr><td align="center"><%=key %></td><td align="center"><%=val%></td></tr>
		  <%
		  } 
		  System.out.println("After for");
		  }catch(Exception e){
		  System.out.println("excptn in jsp : " + e);
		  }
		  %>
		 </table><br/>
		<font face="Arial" color="#000040" size="2"> <bean:message key="acronyms.wrongQues"/> </font><br/><br/>
		<font color="red" size="2"><html:errors property="qno"/></font> <br/>
		<font face="Arial" color="#000040">	<bean:message key="label.delQues"/> </font><html:text property="qno" value=""/>							<font color="red" size="2">*</font>
		<br/>
			<html:hidden property="testName" value="<%= test %>"/>
			<html:submit/><html:cancel value="Back"/>
		</html:form>
	</body>
</html>

