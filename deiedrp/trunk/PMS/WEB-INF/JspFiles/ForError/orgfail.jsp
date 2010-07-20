<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html>
<link rel="stylesheet" href="style/style.css" type="text/css"></link>
  <body>
  <div>
   <font color="#0000ff" size="3"><%=request.getAttribute("message") %></font>
   <br><br><html:button property="back" value="Back" styleClass="butStnd" onclick="history.back();" />
  </div>
  </body>
</html>
