<%-- 
    Document   : newjsp
    Created on : Dec 27, 2010, 9:19:09 PM
    Author     : Algox
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <f:view>
        


        <%

String forward = request.getParameter("fwdLink");
%>
<rich:panel header="Report Exporter">
<form method="post" action=<%= forward%>>
            <select onchange="submit();" name="type">
                <option value="HTML">HTML</option>
                <option value="PDF">PDF</option>
                <option value="EXCEL">EXCEL</option>
                <option value="XML">XML</option>
            </select>
            <h:commandButton value="Export" onclick="submit();"/>
        </form>

        </rich:panel>
            </f:view>
    </body>
</html>
