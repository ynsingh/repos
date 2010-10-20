<%@ page language="java"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html>
<link rel="stylesheet" href="style/style.css" type="text/css"></link>
  <body>
        <h2><font size="2" color="#0000ff"><bean:message key="chart.fail"/></font></h2>
        <br>
        <input type="button" value='<bean:message key="label.back.button" />' class="butStnd" onclick="history.back();" />
  </body>
</html>
