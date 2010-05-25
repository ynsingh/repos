<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html>
  <body> 
  
   <font size="3" color="#000099"><%=request.getAttribute("msginfo") %></font><br>
    <br><html:button property="back" value="Back to Previous Page" onclick="history.back();" />
  </body>
</html>
