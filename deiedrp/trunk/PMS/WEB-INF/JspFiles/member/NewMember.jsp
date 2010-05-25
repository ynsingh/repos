<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

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
 
  <html:javascript formName="newmemberform" dynamicJavascript="true"
			staticJavascript="true" />
    <html:form action="addnewmember" onsubmit="return validateNewmemberform(this)">
     <div id="main_title"><font color="#0044ff">
     Add New Member:
     </font></div><br><br>
     <br>
		  <div align="center">
		  <html:errors property="emailid"/>
		  <html:errors property="membermsg"/>
		   </div>
		  <br>
      <table cellspacing="1" cellpadding="6" width="40%" border="0" align="center">
      <tr><td>
      <input type="hidden" name="orgportal" id="orgportal" value="<%=(String)session.getAttribute("validOrgInPortal") %>" size="20" readonly="readonly"/>
		<html:errors property="orgportal"/>
		<input type="hidden" name="user" id="user" value="<%=(String)session.getAttribute("authority") %>" size="20" readonly="readonly"/>
			<html:errors property="user"/>	
			</td></tr>
        <tr class="form-element">
          <td class="form-label">Email-id :</td>
          <td class="form-widget"><html:text property="emailid" indexed="emailid" value="" size="40" onchange="seeMember()" /><font color="red" size="2">*</font></td>
        </tr>
        	
        <tr class="form-element">
          <td class="form-label">First Name :</td>
          <td class="form-widget"><html:text property="firstname" value="" size="40" /></td>
        </tr>
          <tr class="form-element">
          <td class="form-label">Last Name :</td>
          <td class="form-widget"><html:text property="lastname" value="" size="40"/></td>
        </tr>
        <tr class="form-element">
          <td class="form-label">Phone No. :</td>
          <td class="form-widget"><html:text property="phoneno" value="" size="40"/>
          <html:errors property="phoneno"/>
          </td>
        </tr>
        <tr class="form-element">
          <td class="form-label">Skills :</td>
          <td class="form-widget"><html:textarea property="skill" value="" rows="3" cols="38"/></td>
        </tr>
        
        <tr class="form-element">
          <td class="form-label">Experience :</td>
          <td class="form-widget">
           <html:select property="experience" value="--Select--">
           <html:option value="--Select--"></html:option>
           <%
           for(int i=0;i<=10;i++)
            {
            %>
          <html:option value="<%=String.valueOf(i) %>"></html:option>
          <%} %>
          </html:select>Year<font color="red" size="2">*</font></td>
        </tr>
      
      </table>
      <table align="center">	
			  
			<tr><td><BR>	  
			<html:submit value="Add Member"/>
			<html:reset/>
			<html:button property="back" value="Cancel" onclick="history.back();" />
			<input type="hidden" name="emailid2" id="emailid2" value="" size="20" readonly="readonly"/>
			<html:errors property="emailid2"/>
            </td>
			</tr>
			</table>
    </html:form>
  </body>
</html:html>