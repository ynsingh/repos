<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html>
  <body>
  <div>
   <font color="#0000ff" size="3"><%=request.getAttribute("message") %></font>
   <br><br><html:link styleClass="underline" action="addorg_in_portal"><font color="#00f00f" size="3">Back to previous page</font></html:link>
  </div>
  </body>
</html>
