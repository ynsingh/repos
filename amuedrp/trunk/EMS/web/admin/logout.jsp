
 <jsp:include page="../header.jsp" flush="true" />
<%-- 
    Document   : test
    Created on : Jun 28, 2010, 8:11:42 PM
    Author     : Dushyant
--%>
<META HTTP-EQUIV="Expires" CONTENT="Tue, 01 Jan 1980 1:00:00 GMT"> <META HTTP-EQUIV="Pragma" CONTENT="no-cache">

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/EMS-Struts/css/page.css"/>
        <title>EMS</title>
    </head>
    <body >
   
<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;">

    <table height="300px"  valign="top" align="center" id="tab1">
        <tr><td   width="400px" height="300px" valign="top" align="center">
                <fieldset style="border:solid 1px brown;height:200px;padding-left: 5px">
                    <legend><img src="/EMS-Struts/images/Logout.png"></legend>
                    <br><br><br>

                  <p align="center" class="txt2">Staff Logged Out Successfully
                    <br><br>
                    Click Here <a href="/EMS-Struts/login.jsp">Home</a></p>
                    
                    
                   

</fieldset>

    
</td></tr></table>
        </div>
   

    </body>
    <%
                    session.invalidate();



                    %>
</html>
 