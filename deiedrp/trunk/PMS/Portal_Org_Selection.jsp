<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="in.ac.dei.edrp.pms.home.*"%>
<%@ page import="in.ac.dei.edrp.pms.control.CustomRequestProcessor"%>
<%@ page import="java.util.*;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:html lang="true">
  <head>
   <title>Portal_Org Selection</title>
   <link rel="icon" href="img/logo.ico" type="image/x-icon">
<link rel="stylesheet" href="style/dropdown.css" type="text/css"></link>
<style type="text/css" media="screen">
.answerbox
{
border: 1px solid black; /*Add 1px solid border, use any color you want*/
background-color:#fffff1; /*Add a background color to the box*/
text-align:center; /*Align the text to the center*/
border-bottom-style:ridge;

}
</style>
   <script type='text/javascript' src='dwr/engine.js'></script>
  <script type='text/javascript' src='dwr/util.js'></script>
	<!-- This JavaScript file is generated specifically for your application -->
    <script type='text/javascript' src='dwr/interface/DynamicList.js'></script>
	<script type="text/javascript">
	 
   function organisationPortalList() {
   var portalname = DWRUtil.getValue("portalname");
   var username = DWRUtil.getValue("uname");
  DynamicList.organisationPortalList(portalname,username,function(data)
  {
  	DWRUtil.removeAllOptions("orgname");
  	DWRUtil.addOptions("orgname",data);
   }
  ); 
 }
  </script>
  </head>
  
  <body bgcolor="#fffffd">
  <noscript><h1>Warning: either you have javascript disabled or your browser does not support javascript. To work properly, this page requires javascript to be enabled.</h1></noscript>
  <%! String mysession=null; %>
	
	<%
		new CustomRequestProcessor().processNoCache(request, response);
		mysession=(String)session.getAttribute("mysession");
		if(mysession==null)
		{
			response.sendRedirect("login.jsp");  
		}
	else{
	%>
  <table height="100%" width="100%" cellspacing="0px" cellpadding="0px">
  <tr height="10%">
  <td width="100%">
  <table style="border:0px solid #000066;width:100%;" cellspacing="0px" cellpadding="0px" bgcolor=#C3D9FF>
<tr>
	<td valign="top"><div>
					<div style="float:left;width:70%;color:#000066;background-color:C3D9FF;font-size:30px;font-family:algerian;background-image:url(header-gif.rev.gif);background-repeat: no-repeat;background-position: left;height:13%;text-align: center;text-shadow: aqua;">
					<bean:message key="header.title" />
					<br><div style="padding-bottom:15px;font: normal;font-family: Arial, Helvetica, sans-serif;color:#000000;">
					<b><font size="3"><bean:message key="header.subtitle" /></font></b><br>
					<font size="2">(<bean:message key="header.subtitle1" />)</font></div>
					</div>
					</div>
					<div align="right" style="padding-right: 10px;">
		<b><font size="2">Welcome,</font></b>
		<font size="2">
		  		<%
		  		/*The user_id which is currently logged In.*/
		  		String uid=(String)session.getAttribute("uid");
		  		out.println(uid);
		   		%> |</font>
		 
				<html:link action="logout" styleClass="B"> LogOut</html:link>
				</div>				
		 </td></tr>
		</table></td></tr>
  
  <tr height="80%">
  <td align="justify" style="type:text/css;color:#000000;background-color:#FFFFFF;" width="50%">
	<table width="100%" height="100%" >
		<tr>
		<td class="answerbox" style="padding-bottom: 100px;" align="center">
			
			 <b>PMS Portals<br/><br/>
			 Hello , <b><%=(String)session.getAttribute("uid")%></b><br/>
			<br/>You have access to the following portals. Select the portal and organization you wish to access. <br/><br/>
			
	     <%
	   PortalOrgBeanList pol=(PortalOrgBeanList)request.getAttribute("portal");
	   List<PortalOrgBean> list=pol.getList();
	   Iterator<PortalOrgBean> i=list.iterator();
	   %>
	   </b>
	  <table class="answerbox" align="center" style="padding: 20px;">
	<html:javascript formName="portalnameform" dynamicJavascript="true"	staticJavascript="true" />
	<html:form action="/mainwelcome" onsubmit="return validatePortalnameform(this)">
	  
	  <tr><td>
	   <input type="hidden" name="uname" id="uname" value="<%=(String)session.getAttribute("uid") %>" size="20" readonly="readonly"/>
	   </td></tr>
	  <tr valign="top"><td align="left">
	  Portal Name :
	   </td>
	   <td>
	   <select name="portalname" id="portalname" onchange="organisationPortalList()" style="width: 270px;height: 22px;">
	   <option value="--Select--">--Select--</option>
	   <%
	   while(i.hasNext())
	   {
	   PortalOrgBean pob=i.next();
	   %>
	   <option value="<%= pob.getPortalname()%>"><%= pob.getPortalname()%></option>
	   <%
	   }
	    %>
	   </select><html:errors property="portalname"/>
	   </td></tr>
	   <tr><td align="left">
	   Organization Name :
	  </td>
	   <td style="padding-top: 10px;">
	   <select id="orgname" name="orgname" style="width: 270px;height: 22px;">
	   <option value="--Select--"/>
	   </select><html:errors property="orgname"/>
	   </td>
	   </tr><tr></tr><tr></tr>
	  <tr><td align="right" style="padding-top: 10px;">
	   <html:submit></html:submit></td></tr>
	   </html:form>
	   </table> </td> </tr>
	  </table> </td> </tr> 
   <tr height="10%">
   <td width="100%" bgcolor="c3d9ff"><jsp:include page="/WEB-INF/JspFiles/common/footer.jsp"></jsp:include> 
   </td></tr> 
   <%} %>
 </table> </body>
</html:html>
