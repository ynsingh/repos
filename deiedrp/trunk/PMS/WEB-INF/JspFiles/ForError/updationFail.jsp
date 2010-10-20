<%@ page language="java"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html>
<link rel="stylesheet" href="style/style.css" type="text/css"></link>
   <body>
        <h2><font size="2" color="#0000ff"><bean:message key="taskUpdation.fail"/></font></h2>
        <br>
        <html:button property="back" value="Back" styleClass="butStnd" onclick="history.back();" />
  </body>
</html>
