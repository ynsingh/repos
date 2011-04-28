
<%@page contentType="text/html" import="com.myapp.struts.hbm.*,com.myapp.struts.AdminDAO.*" pageEncoding="UTF-8"%>

 <jsp:include page="header.jsp" flush="true" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String login_id=(String)request.getAttribute("user_id");
Login rs=LoginDAO.searchLoginID(login_id);
String staff_id="";
if(rs!=null)
    staff_id=rs.getId().getStaffId();


String user_name=(String)request.getAttribute("user_name");

String lib=(String)request.getAttribute("library_id");
String role=(String)request.getAttribute("role");
String msg=(String)request.getAttribute("msg");
String msg1=(String)request.getAttribute("msg1");
String btn=(String)request.getAttribute("btn");
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

                    <p align="left" class="mess"> Login Id:<b><%=login_id%></b>
                    <br><br>
                   <p align="left" class="mess"> User Role:<b><%=role%></b>
                    <br><br>

                          
                    Staff Name:<b><%=user_name%></b><br>
                    <input type="hidden" name="user_name" value="<b><%=user_name%></b>"/>
                    <br>Library ID:<b><%=lib%></b>
                    <br><br>
                      <%
                    if(msg!=null)
                        {
                        %>
                        <%=msg%>
                        <%
                        }
                        else
                            {
                           %>

                           <%}%>
                             <%
                    if(msg1!=null)
                        {
                        %>
                   <p class="err" >    <%=msg1%></p>
                        <%
                        }
                        else
                            {
                           %>

                           <%}%>


                           <br><br>

                    <%if(btn.equals("Create Account")){%>The confirmation email sent successfully.<%}%>

                    <%
                    session.setAttribute("staff_name",user_name);
                    %>



  

   </div>
   
    </body>

<script language="javascript">
    function message()
    {


        var a=confirm("<b>Assign Privilege for staff with Login Id:<%=login_id %> Now?</b>");
        if(a==true)
            location.href="<%=request.getContextPath()%>/admin/privilege_tree.jsp?login_id=<%=login_id%>";

        return false;
    }
</script>
</html>
