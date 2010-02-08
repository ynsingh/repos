<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html>
  <body>
  <div>
   <font color="#0000ff" size="3"><%=request.getAttribute("message") %></font>
   <br><html:button property="back" value="Back to Previous Page" onclick="history.back();" />
  </div>
  </body>
</html>
