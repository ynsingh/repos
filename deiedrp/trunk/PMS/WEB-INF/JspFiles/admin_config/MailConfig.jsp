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
	<h3><a href="#">Mail Configuration -</a></h3>
	<div>
  		<html:javascript formName="mailconfigform" dynamicJavascript="true"
			staticJavascript="true" />
    <html:form action="addMailConfig" onsubmit="return validateMailconfigform(this)">
     <br><div align="center">
		<font color="#0000ff"> Note:-</font>mail server name, "smtp.mail.yahoo.com" for Yahoo server<br>
          "smtp.gmail.com" for Gmail server and both are using port "465"
		   </div>
     <br><br>
		  <div align="center">
		   <html:errors property="mailConfigMsg"/>
		   </div>
		  <br>
      <table cellspacing="1" cellpadding="6" width="50%" border="0" align="center">
        <tr class="form-element">
          <td class="form-label">Mail Server Name :</td>
          <td class="form-widget"><html:text property="smtpServerName" size="40"/><font color="red" size="2">*</font>
                <html:errors property="smtpServerName"/>
        </td>
        </tr>
      <tr class="form-element">
          <td class="form-label">Mail Server Port :</td>
          <td class="form-widget"><html:text property="smtpServerPort" size="40" /><font color="red" size="2">*</font>
         <html:errors property="smtpServerPort"/>
          </td>
          </tr>
        <tr class="form-element">
          <td class="form-label">Mail from:</td>
          <td class="form-widget"><html:text property="mailFrom" title="from which you want to send mails." size="40"/><font color="red" size="2">*</font>
          <html:errors property="mailFrom"/>
          </td>
        </tr>
        
        <tr class="form-element">
          <td class="form-label">Password :</td>
          <td class="form-widget"><html:password property="password" title="for authenticate your mail account." size="40"/><font color="red" size="2">*</font>
          <html:errors property="password"/>
          </td>
        </tr>
          <tr class="form-element">
          <td class="form-label">Subject:</td>
          <td class="form-widget"><html:text property="subject" title="Subject of your mail" size="40"/><font color="red" size="2">*</font>
          <html:errors property="subject"/>
          </td>
        </tr>    
      </table>
      <table align="center">	
			  
			<tr><td><BR><br>	  
			<html:submit value="Submit" styleClass="butStnd"/>
			<html:reset styleClass="butStnd"/>
			<html:button property="back" value="Cancel" styleClass="butStnd" onclick="history.back();" />
			</td>
			</tr>
			</table>
    </html:form>
    </div></div></div>
  </body>
</html:html>