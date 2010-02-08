<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    
  </head>
  
  <body>
   <div id=main_title><font color="#0044ff"><%= (String)request.getAttribute("message") %></font></div><br>
   For more uploading files,plz click here<html:link action="upload"> <font color="#0000ff"> Upload files</font></html:link>
 <br><br>
 <html:button property="back" value="Back" onclick="history.back();" />
  </body>
</html:html>
