

<%@page contentType="text/html" pageEncoding="UTF-8" %>
 <jsp:include page="header.jsp" flush="true" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
 <%
String staff_id=(String)request.getParameter("staff_id");
request.setAttribute("staff_id",staff_id);

String first_name=(String)request.getParameter("user_name");



%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
        <title>LibMS</title>
    </head>
    <body>

<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;"><br><br><br>
 <table border="1" class="table" width="400px" height="200px" align="center">


                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Manage Privileges</td></tr>
                <tr><td valign="top" align="center"> <br/>
      						<br>
						<br>
                                                <%
                                                String library_id=(String)session.getAttribute("library_id");

                                               
                                                String staff_name="";
                                               
                                              staff_name=first_name;
                                                %>
                                                Do You Want To Assign privilege Now for:&nbsp;<b><%=staff_name%>?</b> <br>
<input type="hidden" value="<%=staff_id%>" name="staff_id"/>

                    

                    <br><br>&nbsp;&nbsp;
                    <input type="button" value="Assign Privilege" onClick="show()" class="txt2"/>
                    <input type="button" value="Skip" class="txt2" onClick="window.location='<%=request.getContextPath()%>/admin/main.jsp';"/>

               
<input type="hidden" name="staff_id" value="<%=staff_id%>">
                    </td></tr></table>



        </div>
    

    </body>

<script language="javascript" type="text/javascript">
    function show()
    {

location.href="./CheckedPrivilege.do?staff_id=<%=staff_id%>";

    }
    </script>




</html>

    
