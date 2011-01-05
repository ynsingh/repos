<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<html>
	<head>
		<title>Online OMR Evaluation System</title>
	</head>
	<body>
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
									<html:errors property="correctPath" /> 
								</td> 
							</tr> 
 
						</table> 
 
						<html:submit></html:submit> 
						&nbsp; 
<html:button value="cancel" property="btn" onclick="history.back();"></html:button>					</center> 
				</html:form> 
				
	</body>
</html>

