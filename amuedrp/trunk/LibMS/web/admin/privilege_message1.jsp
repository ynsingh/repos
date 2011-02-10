<%--
    Document   : test
    Created on : Jun 28, 2010, 8:11:42 PM
    Author     : Dushyant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <jsp:include page="header.jsp" flush="true" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String staff_id=(String)request.getAttribute("staff_id");


    String staff_name=(String)request.getAttribute("staff_name");


%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/page.css"/>
        <title>LibMS</title>
        <link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
    </head>
    <body >

<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;">

    <table width="400px" height="600px"  valign="top" align="left" id="tab1">
        <tr><td   width="300px" height="400px" valign="top" class="mess" align="left">


                       Privilege Successfully Assigned for<br><br> Staff ID :<b><%=staff_id%></b> <br><br>Name :<b><%=staff_name%></b>

                    





</td></tr></table>
        </div>
   
    </body>
</html>






                