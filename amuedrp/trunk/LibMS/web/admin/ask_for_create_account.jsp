

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <jsp:include page="header.jsp" flush="true" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
 <%
String staff_id=(String)request.getParameter("staff_id");
String first_name=(String)request.getParameter("first_name");
String last_name=(String)request.getParameter("last_name");
String email_id=(String)request.getParameter("email_id");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
        <title>LibMS</title>
    </head>
    <body>

<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;"><br><br><br>
<table width="400px" height="300px"  valign="top"
align="center">
        <tr><td   width="400px" height="300px"
valign="top" style="" class="mess" align="center">
                <fieldset style="border:solid 1px brown;height:200px;padding-left: 5px">
                    <legend><img src="/LibMS-Struts/images/StaffAccountLogin.png"></legend>
						<br>
						<br>

                                                Do You Want To Create Account Now for:&nbsp; <b><%=first_name%>&nbsp;<%=last_name%></b>?<br>
<input type="hidden" value="<%=first_name%>" name="first_name"/>
<input type="hidden" value="<%=last_name%>" name="last_name"/>
                    </b>

                    <br><br>&nbsp;&nbsp;
                    <input type="button" value="Create Account" onClick="show()" class="txt2"/>
                    <input type="button" value="Skip" class="txt2" onClick="window.location='/LibMS-Struts/admin/main.jsp';"/>

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

<script>
    function show()
    {
        location.href="createaccount.jsp?staff_id=<%=staff_id%>&first_name=<%=first_name%>&last_name=<%=last_name%>&email_id=<%=email_id%>";
    }
    </script>


