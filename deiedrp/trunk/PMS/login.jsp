<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="in.ac.dei.edrp.pms.control.CustomRequestProcessor;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

 <html:html> 
	<head>
		<title>Login Form</title>
	<link rel="stylesheet" href="style/style.css" type="text/css"></link>
	</head>
	<link rel="icon" href="img/logo.ico" type="image/x-icon">
	<body>

	<%
	//System.out.println("session new in login jsp="+session.isNew());
	try
	{
		session.removeAttribute("mysession");
	}
	catch(Exception e){}
		new CustomRequestProcessor().processNoCache(request, response);
	 %>
<table style="border:1px solid #000066;width:100%;height:100%;" cellspacing="0px" cellpadding="0px" >
<tr style="height:15%;width:100%;background-image: url('img/backimage.PNG');background-repeat: repeat;">
	<td>
			<table style="height:100%;width:100%;">
			<tr><td align="center">
			<div style="color:#000066;font-size:30px;font-family:algerian;width:100%;height:100%;text-align: center;text-shadow: aqua;">
			<bean:message key="header.title" />
			</div></td></tr>
			<tr><td align="center" style="font: normal;font-family: Arial, Helvetica, sans-serif;font-size:14px;color:#000000;" >
			<b><font size=3><bean:message key="header.subtitle" /></font></b><br><br>
			<font size=2>(<bean:message key="header.subtitle1" />)</font><br><br> 
			</td></tr>
			</table>
	</td>
</tr>
	
<tr valign="top" bgcolor="white">
<td  width=90%>	
	<table><tr>	<td valign="top">
		<table><tr>	<td style="float:none;" align="left"></td></tr>
			<tr>		
			<td valign="middle" style="float:none;width:50%;" align="left">
				<div style="font: normal;font-family: Arial, Helvetica, sans-serif;color:#000000;" align="justify">
				<font size=-1><b><bean:message key="label.feature"/> :</b><br><br>
				<a href="#"><font color="blue"><bean:message key="label.heading3"/></font></a> <bean:message key="label.feature3"/>.<br/><br/>
				<a href="#"><font color="blue"><bean:message key="label.heading1"/></font></a> <bean:message key="label.feature1"/>.<br/><br/>
				<a href="#"><font color="blue"><bean:message key="label.heading2"/></font></a> <bean:message key="label.feature2"/>.<br/><br/>
				<a href="#"><font color="blue"><bean:message key="label.heading4"/></font></a> <bean:message key="label.feature4"/>.<br/><br/>
				<a href="#"><font color="blue"><bean:message key="label.heading5"/></font></a> <bean:message key="label.feature5"/>.<br/><br/>
				<a href="#"><font color="blue"><bean:message key="label.heading6"/></font></a> <bean:message key="label.feature6"/>.<br/><br/>
				<a href="#"><font color="blue"><bean:message key="label.heading7"/></font></a> <bean:message key="label.feature7"/>.<br/><br/>
				<a href="#"><font color="blue"><bean:message key="label.heading8"/></font></a> <bean:message key="label.feature8"/>.<br/>
				</font></div>
			</td></tr>
		</table></td>
			<td width=10%>
			<table><tr><td valign="top"></td></tr>
			<tr><td valign="middle" bgcolor="#e8eefa" bordercolor=#000000 style="border:1px solid #ccc;">	
				<div style="float:left;padding-top:10px">
					<div style="font: normal;font-family: Arial, Helvetica, sans-serif;font-size:14px;text-align: center;" align="center" style="color:#000000;">
					   <bean:message key="label.sign" /><br>
						</div>
						   <div align="center" style="background-color:#e8eefa">
						    <html:errors property="login"/>
						    <html:errors property="uid"/><br>
							<html:errors property="pass"/>
							</div>
			<html:form action="/login" focus="uid" onsubmit="return validateLoginform(this);">
					
			<table cellspacing="2" cellpadding="6" border="0" align="right" style="background-color: #e8eefa;font: normal;font-family: Arial, Helvetica, sans-serif;font-size: 14px;">
					<tr></tr>
						<tr>			
							<td valign="top" style="font: normal;font-family: Arial, Helvetica, sans-serif;font-size:14px;text-align: right;" nowrap="nowrap"><font size="-1">
							<bean:message key="label.user" />:</font></td>
							<td><html:text property="uid" value="" size="30"/></td>
						</tr>
						<tr>
							<td valign="top" style="font: normal;font-family: Arial, Helvetica, sans-serif;font-size:14px;text-align: right;" nowrap="nowrap"><font size="-1">
							<bean:message key="label.password" />:</font></td>
							<td><html:password property="pass" redisplay="false" value="" size="30"/></td>
						</tr>
						<tr>
							<td></td><td valign="top" style="font: normal;font-family: Arial, Helvetica, sans-serif;font-size:14px;text-align: center" nowrap="nowrap">
							<input type="submit" class="butStnd" value='<bean:message key="label.button" />'/></td>
						</tr>
						<tr></tr>
			</table>
			</html:form></div></td></tr>
			</table></td></tr></table>
	</td></tr>
					
		<tr><td  class="footerpage">
		<table width="100%">
		<tr><td valign="top" style="font: normal;font-family: Arial, Helvetica, sans-serif;font-size:12px;text-align: center" nowrap="nowrap">
		
   	<jsp:include page="WEB-INF/JspFiles/common/footer.jsp"/>
  	
  </td></tr></table></td></tr></table>
</body>
		
</html:html>

