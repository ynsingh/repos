
<%@page contentType="text/html" import="java.sql.*,com.myapp.struts.MyQueryResult" pageEncoding="UTF-8"%>

 <jsp:include page="header.jsp" flush="true" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String email_id=(String)request.getAttribute("user_id");
ResultSet rs=MyQueryResult.getMyExecuteQuery("select * from staff_detail where emai_id='"+email_id+"'");
String staff_id="";
if(rs.next())
    staff_id=rs.getString("staff_id");


String user_name=(String)request.getAttribute("user_name");

String lib=(String)request.getAttribute("library_id");
String msg=(String)request.getAttribute("msg");
%>
<%



             session.setAttribute("button", "Assign Privilege");
            request.setAttribute("staff_name", user_name);
            request.setAttribute("staff_id", staff_id);

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/page.css"/>
        <title>LibMS</title>
    </head>
    <body >
   
<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;">

  
                   
                    <br><br><br>

                    <p align="left" class="mess"> Login Id:<b><%=email_id%>
                    <br><br>
                  
                          </b>
                    Staff Name:<b><%=user_name%></b><br>
                    <input type="hidden" name="user_name" value="<b><%=user_name%></b>"/>
                    <br>Library ID:<b><%=lib%></b>
                    <br><br>
                      <%
                    if(msg!=null)
                        {
                        %>
                        <%=msg%><b><%=user_name%></b>
                        <%
                        }
                        else
                            {
                           %>

                           <%}%><br><br>
                    The confirmation email sent successfully.

                    <%
                    session.setAttribute("staff_name",user_name);
                    %>



    </p>










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
       
        
        var a=confirm("<b>Assign Privilege for staff with Login Id:<%=email_id%> Now?</b>");
        if(a==true)
            location.href="/LibMS-Struts/admin/privilege_tree.jsp?login_id=<%=email_id%>";
   
        return false;
    }
</script>