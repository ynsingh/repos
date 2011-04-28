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
		<script>
		function changeBody()
	{
	 var blurDiv = document.createElement("div");
	 blurDiv.id = "blurDiv";
	 blurDiv.style.cssText = "position:absolute; top:0; right:0; width:" + screen.width + "px; height:" + screen.height + "px; background-color:#3B6AA0; opacity:0.4; filter:alpha(opacity=40);";
	// var ImgTag = document.createElement("Img");
	// ImgTag.setAttribute("id", "ImgTag");
	// ImgTag.setAttribute("src", "img/ajax-loader.gif");
	//ImgTag.src="img/ajax-loader.gif";
//	document.getElementById("blurDiv").appendChild(ImgTag);
	//blurDiv.appendChild(ImgTag);
	document.getElementsByTagName("body")[0].appendChild(blurDiv);	
	}
		</script>
	</head>
	<body>
	 <table width="100%">
  <tr><td>  <jsp:include page="header.jsp"></jsp:include></td></tr>
  <tr><td>	<hr width="100%"> </td></tr>
 <tr><td> <jsp:include page="Menu.jsp"></jsp:include></td></tr>
</table>	
		<%
		HttpSession hs = request.getSession();
		String testName =(String) hs.getAttribute("testName");
		 %>
			
				 
				<html:form action="/correctBrowse" method="post" enctype="multipart/form-data" onsubmit="changeBody();"> 
					
					<blockquote><font face="Arial" color="#000040"> 
				 
<strong>	<bean:message key="msg.correct"/><br/>
<bean:message key="label.testname"/> : <%=testName %>
</strong></font></blockquote>
				
					<center> 
					<font color="red" size="2">
					<%
					try{
					if(!((String)hs.getAttribute("correctSheetMsg")).equals("NA")){
					 %>	
					 <%=(String) hs.getAttribute("correctSheetMsg") %>	
					 <%} 
					 }catch(Exception e){
					 System.out.println("Exception in corr : " + e);
					 }%>						  	
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
<html:cancel value="Cancel"></html:cancel>					</center> 
				</html:form> 
				
	</body>
</html>

