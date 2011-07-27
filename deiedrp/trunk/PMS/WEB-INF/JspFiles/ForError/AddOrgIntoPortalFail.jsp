<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html>
  <body>
  <div>
   <font color="#0000ff" size="3"><%=request.getAttribute("message") %></font>
   <br><br><html:link styleClass="underline" page="/forwardPmsPage.do?parameter=addOrgPortal"><font color="#00f00f" size="3">Back to previous page</font></html:link>
  </div>
  </body>
</html>
