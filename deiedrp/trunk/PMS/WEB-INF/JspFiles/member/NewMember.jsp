<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    	
	<link rel="stylesheet" href="style/main.css" type="text/css"></link>
	<link rel="stylesheet" href="style/style.css" type="text/css"></link>
<!-- You have to include these two JavaScript files -->
        <script type='text/javascript' src='dwr/engine.js'></script>
        <script type='text/javascript' src='dwr/util.js'></script>
	<!-- This JavaScript file is generated specifically for your application -->
         <script type='text/javascript' src='dwr/interface/DynamicList.js'></script>
	<link rel="stylesheet" type="text/css" href="style/jquery-ui.css" />
<script type="text/javascript" src="javascript/jquery.js"></script>
<script type="text/javascript" src="javascript/jquery-ui.min.js"></script>
<script type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){

jQuery(function() {
		jQuery("#accordion").accordion({ collapsible: true,
		header: 'h3',
		fillSpace: false
		});
				
	});
});
</script>
	<script type="text/javascript">
	function seeMember() {
	var emailid = DWRUtil.getValue("emailid");
	var user = DWRUtil.getValue("user");
	var orgportal = DWRUtil.getValue("orgportal");
	
   DynamicList.seeUserExistence(emailid,user,orgportal,function(data)
  {
  	DWRUtil.setValue("emailid2",data);
  }
  ); 
 }
 </script>
  </head>
  
  <body>
  <%
	String mysession=(String)session.getAttribute("mysession");
	if(mysession==null)
	{		
		response.sendRedirect("login.jsp");  
	}
  %>
 <div style="padding-left:100px;padding-right:100px;padding-top:40px;">
	<div id="accordion">
	<h3><a href="#"> Add New Member -</a></h3>
	<div>
 		 <html:javascript formName="newmemberform" dynamicJavascript="true"
			staticJavascript="true" />
  		  <html:form action="addnewmember" onsubmit="return validateNewmemberform(this)">
     	  <br>
     	  <div align="center">
		  <html:errors property="emailid"/>
		  <html:errors property="membermsg"/>
		   </div>
		  <br>
      <table cellspacing="1" cellpadding="6" border="0" align="center">
      <tr><td>
      <input type="hidden" name="orgportal" id="orgportal" value="<%=(String)session.getAttribute("validOrgInPortal") %>" size="20" readonly="readonly"/>
		<html:errors property="orgportal"/>
		<input type="hidden" name="user" id="user" value="<%=(String)session.getAttribute("authority") %>" size="20" readonly="readonly"/>
			<html:errors property="user"/>	
			</td></tr>
        <tr class="form-element">
          <td class="form-label">Email-id :</td>
          <td class="form-widget"><html:text property="emailid" indexed="emailid" value="" size="40" onchange="seeMember()" /><font color="red" size="2">*</font></td>
          <td class="form-label">Phone No. :</td>
          <td class="form-widget"><html:text property="phoneno" value="" size="40"/>
          <html:errors property="phoneno"/>
          </td>
        </tr> 	
        <tr class="form-element">
          <td class="form-label">First Name :</td>
          <td class="form-widget"><html:text property="firstname" value="" size="40" /><font color="red" size="2">*</font></td>
          <td class="form-label">Last Name :</td>
          <td class="form-widget"><html:text property="lastname" value="" size="40"/><font color="red" size="2">*</font></td>
        </tr>
        
       <tr class="form-element">
          <td class="form-label">Experience :</td>
          <td class="form-widget">
           <html:select property="experience" value="--Select--">
           <html:option value="--Select--"></html:option>
           <%
           for(int i=0;i<=20;i++)
            {
            %>
          <html:option value="<%=String.valueOf(i) %>"></html:option>
          <%} %>
          </html:select>Year<font color="red" size="2">*</font></td>
          <td class="form-label">Skills :</td>
          <td class="form-widget"><html:textarea property="skill" value="" rows="2" cols="38"/></td>
        </tr>
        <c:if test="${sessionScope.authority=='Super Admin'}">
        <input type="hidden" value="a" name="rolename" id="rolename"/>
        </c:if>
        <c:if test="${sessionScope.authority=='User'}">
      <tr class="form-element">
		<td  class="form-label">Role in Organization :</td>
		<td class="form-widget">
		<html:select property="rolename" style="width: 270px;">
		<html:option value="--Select--"></html:option>
		<sql:query var="orgrole" dataSource="jdbc/mydb">
				select role_name from role where validorgportal=? order by role_Name asc
				<sql:param value="${sessionScope.validOrgInPortal}"/>
			</sql:query>
			<c:forEach var="row" items="${orgrole.rows}">
			<html:option value="${row.role_name}"></html:option>
			</c:forEach>
		 </html:select><font color="red" size="2">*</font>
		 <html:errors property="rolename"/>
			 </td></tr>
      </c:if>
        
        
      </table>
      <table align="center">	
			  
			<tr><td><BR><br>	  
			<html:submit value="Add Member" styleClass="butStnd"/>
			<html:reset styleClass="butStnd"/>
			<html:button property="back" value="Cancel" styleClass="butStnd" onclick="history.back();" />
			<input type="hidden" name="emailid2" id="emailid2" value="" size="20" readonly="readonly"/>
			<html:errors property="emailid2"/>
            </td>
			</tr>
			</table>
    </html:form>
    </div></div></div>
  </body>
</html:html>