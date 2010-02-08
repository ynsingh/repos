<%@ page language="java"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html>
   <body>
        <h2><font size="2" color="#0000ff"><bean:message key="page.fail"/></font></h2>
        <br>
        <html:button property="back" value="Back to Previous Page" onclick="history.back();" />
  </body>
</html>
