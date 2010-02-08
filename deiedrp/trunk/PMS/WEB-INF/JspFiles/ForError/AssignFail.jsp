<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html>
  <body> 
   <font size="3" color="#000099">This user is already added for the same project.</font><br>
    <h2>Your user id=<font size="2" color="#0000ff"><%=request.getAttribute("uidinfo") %></font></h2><br>
  <h2>Password=<font size="2" color="#0000ff"><%=request.getAttribute("passinfo") %></font></h2>
  <br><html:button property="back" value="Back to Previous Page" onclick="history.back();" />
  </body>
</html>
