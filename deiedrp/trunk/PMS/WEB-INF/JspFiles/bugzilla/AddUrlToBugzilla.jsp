<%@ page language="java" pageEncoding="UTF-8"%>
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
  
 <div style="padding-left:100px;padding-right:100px;padding-top:40px;">
	<div id="accordion">
	<h3><a href="#"><bean:message key="label.addUrl"/> -</a></h3>
	<div> 		 
		<html:javascript formName="urlConfigForm" dynamicJavascript="true" staticJavascript="true" />			 
		<html:form action="addUrlToBugzilla" onsubmit="return validateUrlConfigForm(this);">
     	<br><br>     
	  	<div align="center">
		   <html:errors property="urlConfigMsg"/>
		   <font color="#ff0000" size="3">
	   		<html:errors property="url"/>
		   </font>
	   </div>
	  <br>		  		 		  
      <table cellspacing="1" cellpadding="6" border="0" align="center">            
      <tr class="form-element">
		<td  class="form-label"><bean:message key="label.url"/> :</td>
		<td class="form-widget"><html:text property="url" size="40"/><font color="red" size="2">*</font></td>
		<td>
			 <input type="submit" value='<bean:message key="label.add.button"/>' class="butStnd"/>
		   	 <input type="button" value='<bean:message key="label.cancel.button" />' class="butStnd" onclick="location.href='welcome.do'" />
		 </td>
	 </tr>          
     </table>     
    </html:form>
    </div></div></div>
  </body>
</html:html>