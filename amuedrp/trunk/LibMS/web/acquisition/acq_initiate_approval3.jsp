<%-- 
    Document   : acq_initiate_approval3
    Created on : Apr 7, 2011, 1:26:37 PM
    Author     : maqbool
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
        <table>
            <tr><td width="20">Control No</td><td width="200">Title</td><td width="200">Author</td><td width="100">ISBN</td><td width="50">No of Copies</td></tr>
            <logic:iterate id="AcqBibliographyDetails" name="opacList">
            
                <tr>
                    <td><bean:write name="AcqBibliographyDetails" property="id.controlNo"/></td>
                    <td><bean:write name="AcqBibliographyDetails" property="acqBibliography.title"/></td>
                    <td><bean:write name="AcqBibliographyDetails" property="acqBibliography.author"/></td>
                    <td><bean:write name="AcqBibliographyDetails" property="acqBibliography.isbn"/></td>
                    <td><bean:write name="AcqBibliographyDetails" property="noOfCopies"/></td>
                </tr>
            </table>
        </logic:iterate>
    </body>
</html>
