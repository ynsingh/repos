<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<html>
	<head>
		<title>JSP for testSetUpForm form</title>
		<script type="text/javascript" src="javascript/datetimepicker.js" /></script>
		<script type="text/javascript" src="javascript/testSetUp.js"></script>
	</head>
	
	<body>
		<jsp:include page="Menu.jsp"></jsp:include>
		<html:javascript formName="testSetUpForm"/>
		<html:form action="/test" onsubmit="return validateTestSetUpForm(this);">
						<br />
			
			<br/><blockquote><font face="Arial" color="#000040"><strong><bean:message key="link.testsetup"/></strong></font></blockquote>
			
			<br />
			
				<table align="left">

					<tr>
						<td>
						<font face="Arial" color="#000040">	<bean:message key="label.testname" /></font>
												</td>
						<td>
						<font face="Arial" color="#000040">
							<html:text property="testName" size="20" /></font>
							<font color="red" size="2">*</font>
							<html:errors property="testName" />
						</td>
					</tr>
					<tr>
						<td>
						<font face="Arial" color="#000040">	<bean:message key="label.testID" /></font>
												</td>
						<td>
						<font face="Arial" color="#000040">
							<html:text property="testNo" size="20" /></font>
							<font color="red" size="2">*</font>
							<html:errors property="testNo" />
						</td>
					</tr>
					
					<tr>
						<td>
						<font face="Arial" color="#000040">	<bean:message key="label.totalQues" /></font>
						</td>
						<td>
							<font face="Arial" color="#000040"><html:text property="totalQues" /></font>
							<font color="red" size="2">*</font>
							<html:errors property="totalQues" />
						</td>
					</tr>

					<tr>
						<td>
						<font face="Arial" color="#000040">	<bean:message key="label.ConductDate"/> </font>
						</td>
						<td>
							<input type="text" name="sdate" id="sdate" />
							<a href="javascript:NewCssCal('sdate','yyyymmdd')"><img
									src="img/cal.gif" width="16" height="16" border="0"
									alt="Pick a date"> </a> <bean:message key="format.date"/>
							<font color="red" size="2">*</font>
							<html:errors property="sdate" />
						</td></tr>
						
						
			
			
					<tr><td><bean:message key="label.section"/> </td><td><html:select styleId="section" property="totalSec" onchange="display();">
					<html:option value="0">--select--</html:option>
			<html:option value="1">1</html:option>
			<html:option value="2">2</html:option>
			<html:option value="3">3</html:option>
			<html:option value="4">4</html:option>
					</html:select>	<html:errors property="totalSec" /></td></tr>
						
					<tr><td colspan="2"><div id="divId">
						
			         </div></td></tr>
					
			
			<tr><td></td> <td><html:submit onclick="return verify();"></html:submit><html:button value="Cancel" property="btn" onclick="history.back();"/></td></tr>
			</table>
		</html:form>

	</body>
</html>

