<%-- 
    Document   : show_election
    Created on : Sep 6, 2011, 4:12:18 PM
    Author     : faraz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
    <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div><html:form action="/electionshow">
                <table>
                    <tr><td bgcolor="#7697BC" colspan="2" align="center">Election Details</td></tr>
                    <tr><td colspan="2">Election Name : <strong><bean:write name="ShowElectionActionForm" property="electionName"/></strong></td></tr>
                    <tr><td> Election Start Date <html:text readonly="true" name="ShowElectionActionForm" property="startDate"/>
                        </td><td> Election End Date <html:text readonly="true" name="ShowElectionActionForm" property="endDate"/></td></tr>
                    <tr><td>    Scrutiny Start Date <html:text readonly="true" name="ShowElectionActionForm" property="scrutnyDate"/>
            </td><td>   Scrutiny End Date <html:text readonly="true" name="ShowElectionActionForm" property="scrutnyEndDate"/></td></tr>
                    <tr><td>  Withdrawl Start Date <html:text readonly="true" name="ShowElectionActionForm" property="withdrawlDate"/>
              </td><td> Withdrawl End Date <html:text readonly="true" name="ShowElectionActionForm" property="withdrawlEndDate"/></td></tr>
            </html:form>
                    <tr><td> No of voters  <bean:write name="ShowElectionActionForm" property="no_of_voters"/><%--<a href="<%= request.getContextPath() %>/institute_admin/show_voters.jsp"><bean:write name="ShowElectionActionForm" property="no_of_voters"/></a>--%></td></tr>
               <tr><td> No of candidates  <bean:write name="ShowElectionActionForm" property="noofcandi"/><%--<a href="<%= request.getContextPath() %>/institute_admin/show_voters.jsp"><bean:write name="ShowElectionActionForm" property="no_of_voters"/></a>--%></td></tr>
                </table>
        </div>
        <div></div>
    </body>
</html>
