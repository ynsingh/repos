<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html>
<link rel="stylesheet" href="style/style.css" type="text/css"></link>
  <body>
  <div>
   <font color="#0000ff" size="3"><%=request.getAttribute("message") %></font>
   <br><br><input type="button" value='<bean:message key="label.back.button" />' class="butStnd" onclick="history.back();" />
  </div>
  </body>
</html>
