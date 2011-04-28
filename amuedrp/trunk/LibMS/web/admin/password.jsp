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
String staff_id=(String)session.getAttribute("staff_id");
String user_name=(String)session.getAttribute("username");

String lib=(String)session.getAttribute("library_name");

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/page.css"/>
        <title>LibMS : Manage Staff Section</title>
    </head>
    <body >
   
<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;">

    <table width="400px" height="600px"  valign="top" align="left" id="tab1">
        <tr><td   width="300px" height="400px" valign="top" style="" align="left">
              
                  
                    <br>
               
                       
                    <p align="left" class="mess">Staff Details:-<br></p>
                    
                    &nbsp;&nbsp;&nbsp;<p align="left" class="mess">Name :<b><%=user_name%></b></p>
                    &nbsp;&nbsp;&nbsp;<p align="left" class="mess">Library Name:<b><%=lib%></b></p>
                 
Password Successfully Changed


    
</td></tr></table>
        </div>
   

    </body>
    <script language="javascript">
  
</script>
</html>
