

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <jsp:include page="header.jsp" flush="true" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
 <%
String staff_id=(String)request.getParameter("staff_id");
String first_name=(String)request.getParameter("first_name");
String last_name=(String)request.getParameter("last_name");

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
        <title>LibMS : Manage Staff Create Account </title>
    </head>
    <body>

<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;"><br><br><br>
 <table border="1" class="table" width="400px" height="200px" align="center">


                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Manage Staff Account</td></tr>
                <tr><td valign="top" align="center"> <br/>
      						<br>
						<br>

                                                Do You Want To Create Account Now for:&nbsp; <b><%=first_name%>&nbsp;<%=last_name%></b>?<br>
<input type="hidden" value="<%=first_name%>" name="first_name"/>
<input type="hidden" value="<%=last_name%>" name="last_name"/>
                    

                    <br><br>&nbsp;&nbsp;
                    <input type="button" value="Create Account" onClick="show()" class="txt2"/>
                    <input type="button" value="Skip" class="txt2" onClick="window.location='<%=request.getContextPath()%>/admin/main.jsp';"/>

              
                    </td></tr></table>



        </div>
   

    </body>

<script language="javascript" type="text/javascript">
    function show()
    {
        location.href="<%=request.getContextPath()%>/admin/account2.do?staff_id=<%=staff_id%>&first_name=<%=first_name%>&last_name=<%=last_name%>";
    }
    </script>



</html>
