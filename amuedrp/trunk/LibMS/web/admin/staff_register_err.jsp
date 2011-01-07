<%-- 
    Document   : test
    Created on : Jun 28, 2010, 8:11:42 PM
    Author     : Dushyant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String staff_id=(String)request.getAttribute("staff_id");
String first_name=(String)request.getAttribute("first_name");
String last_name=(String)request.getAttribute("last_name");
String lib=(String)request.getAttribute("Library_id");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/page.css"/>
        <title>LibMS</title>
    </head>
    <body >
    <jsp:include page="header.jsp" flush="true" />
<div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
    <table width="400px" height="500px"  valign="top" align="center">
        <tr><td   width="400px" height="400px" valign="top" style="" align="center">
                <fieldset style="border:solid 2px brown;height:200px">
                    <legend><img src="images/staff_login.jpg"></legend>
                    <br><br><br>

                    <p align="center" class="txt">Sorry Record Cannot Be Inserted </p>
                    

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
<script language="javascript">
    function message()
    {
        
        var a=confirm("Do you want to create account of staff with staff Id:<%=staff_id%>");
        if(a==true)
            location.href="createaccount.jsp?staff_id=<%=staff_id%>&first_name=<%=first_name%>&last_name=<%=last_name%>";
   
        return false;
    }
</script>