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

<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<html>
	<head>
		<title>Online OMR Evaluation System</title>
	</head>
	<body>
	<div>
    <jsp:include page="header.jsp"></jsp:include>
	</div>
	<hr width="100%">
		<jsp:include page="Menu.jsp"></jsp:include>
		
		<%
		HttpSession hs = request.getSession();
		String testName =(String) hs.getAttribute("testName");
		 %>
			
				<html:javascript formName="correctSheetBrowseForm123" />  
				 
				<html:form action="/correctBrowse" method="post" enctype="multipart/form-data" onsubmit="return validateCorrectSheetBrowseForm123(this);"> 
					
					<blockquote><font face="Arial" color="#000040"> 
				 
<strong>	<bean:message key="msg.correct"/><br/>
<bean:message key="label.testname"/> : <%=testName %>
</strong></font></blockquote>

				
					<center> 
					<font color="red" size="2">								  	
				          <html:errors property="correctPath" /> 
					</font>
						<table> 
							<tr> 
								 
								<td> 
									<html:hidden property="test" value="<%= testName%>"/> 
 
										 
								
								</td> 
							</tr> 
							<tr> 
								<td> 
									<font face="Arial" color="#000040"><bean:message key="label.corrrectSheet" /></font> 
								</td> 
								<td> 
									<html:file property="correctPath"></html:file> 
								</td> 
							</tr> 
 
						</table> 
 
						<html:submit></html:submit> 
						&nbsp; 
<html:button value="Cancel" property="btn" onclick="history.back();"></html:button>					</center> 
				</html:form> 
				
	</body>
</html>

