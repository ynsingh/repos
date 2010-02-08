<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html>
  <body>
  <div>
   <font color="#f90000" size="3">Project Creation fail.because this Project already exist.</font> <br>
  </div>
  <br>
  <html:button property="back" value="Back" onclick="history.back();" />
  </body>
</html>
