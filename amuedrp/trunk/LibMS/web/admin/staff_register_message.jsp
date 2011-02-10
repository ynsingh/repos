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
String first_name=(String)request.getAttribute("first_name");
String last_name=(String)request.getAttribute("last_name");
String lib=(String)request.getAttribute("library_id");
String email_id=(String)request.getAttribute("email_id");
//out.println()
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/page.css"/>
        <title>LibMS</title>
    </head>
    <body onload="return message();">
   
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
                    
                    &nbsp;&nbsp;&nbsp;<p align="left" class="mess">Name :<b><%=first_name%>&nbsp;<%=last_name%></b></p>
                    &nbsp;&nbsp;&nbsp;<p align="left" class="mess">Library Name:<b><%=lib%></b></p>
                    



    
</td></tr></table>
        </div>
   

    </body>
    <script language="javascript">
    function message()
    {

        var a=alert("Staff registered  Successfully with Staff Id :<%=staff_id%>");


        location.href="/LibMS-Struts/admin/ask_for_create_account.jsp?staff_id=<%=staff_id%>&first_name=<%=first_name%>&last_name=<%=last_name%>&email_id=<%=email_id%>";

    }
</script>
</html>
