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
String msg=(String)request.getAttribute("msg");
String staff_name=(String)request.getAttribute("staff_name");

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
        <title>LibMS</title>
    </head>
    <body >
   
<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;">
    <br><br>
    <table width="500px" height="600px"  valign="top" align="center">
        <tr><td   width="500px" height="200px" valign="top" align="center">
                <fieldset style="border:solid 1px brown;height:180px;padding-left: 10px">
                    <legend><img src="images/UpdateStaff.png"></legend>
                    <br><br><br>

                    <p  class="mess">
                          <% if(msg!=null){%>
                            <%=msg%><%}%>
                            <b><%=staff_name%></b>
                   
</p>
</fieldset>

    
</td></tr></table>
        </div>
    <div
   style="
      top: 650px;

      position: absolute;

      visibility: show;">
        <jsp:include page="footer.jsp" />

</div>

    </body>
</html>
