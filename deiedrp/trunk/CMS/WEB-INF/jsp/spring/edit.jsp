<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html> 
 <style>
.error {
color: #ff0000;
font-style: italic;
}
</style>
  <body>
    <form:form method="POST" commandName="userBean" action="edit.htm">
   
    
     <table border="1" bgcolor="#ffffcc">
    	<tr>
			<td><spring:message code="user.name"/></td>
			<td><form:input path="userName" /></td>
			<td><form:errors path="userName" cssClass="error" /></td>
		</tr>
		<tr>
			<td><spring:message code="user.password"/></td>
			<td><form:password path="passWord" /></td>
			<td><form:errors path="passWord" cssClass="error" /></td>
		</tr>
    <tr>
    <td></td>
    <td><input type="submit" value="<spring:message code="login.button"/>"/></td>
    </tr>
    </table>
    </form:form>
  </body>
</html>
