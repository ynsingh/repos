

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*,com.myapp.struts.MyQueryResult;"%>
 <jsp:include page="header.jsp" flush="true" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
 <%
String staff_id=(String)request.getParameter("staff_id");
request.setAttribute("staff_id",staff_id);



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
                    <legend><img src="/LibMS-Struts/images/StaffAssignPrivilege.png"></legend>
						<br>
						<br>
                                                <%
                                                ResultSet rs=MyQueryResult.getMyExecuteQuery("select user_name from login where staff_id='"+staff_id+"'");
                                                String staff_name="";
                                                if(rs.next())
                                              staff_name=rs.getString("user_name");
                                                %>
                                                Do You Want To Assign privilege Now for:&nbsp;<b><%=staff_name%>?</b> <br>
<input type="hidden" value="<%=staff_id%>" name="staff_id"/>

                    

                    <br><br>&nbsp;&nbsp;
                    <input type="button" value="Assign Privilege" onClick="show()" class="txt2"/>
                    <input type="button" value="Skip" class="txt2" onClick="window.location='/LibMS-Struts/admin/main.jsp';"/>

                </fieldset>
<input type="hidden" name="staff_id" value="<%=staff_id%>">
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

location.href="./CheckedPrivilege.do?staff_id=<%=staff_id%>";

    }
    </script>




    
