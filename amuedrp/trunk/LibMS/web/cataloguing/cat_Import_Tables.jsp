<%-- 
    Document   : ImportIntoTables
    Created on : May 18, 2011, 7:44:19 PM
    Author     : khushnood
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="com.myapp.struts.cataloguing.StrutsUploadForm"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
      
         <html:form action="/TempToDatabase" method="post"  enctype="multipart/form-data">
         <input align="center" name="button" style="text-align: center;color:green;  " type="submit" value="Import Data" />
         </html:form>
         <font size="2" color="red">
                        <h3 style="text-align: center; ">
                            <%if (request.getAttribute("msg") != null) {
                                            out.println(request.getAttribute("msg"));
                                        }
                            %></h3>   </font>
                             <font size="2" color="red">
                        <h3 style="text-align: center; ">
                            <%if (request.getAttribute("error") != null) {
                                            out.println(request.getAttribute("error"));
                                        }
                            %></h3>   </font>
         </body>
</html>
