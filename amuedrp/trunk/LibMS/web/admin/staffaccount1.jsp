
<%@page contentType="text/html" import="com.myapp.struts.hbm.*,com.myapp.struts.AdminDAO.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
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
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
%>
<%
try{
locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
       // System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);


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

                    <p dir="<%=rtl%>" align="<%=align%>" class="mess"> <%=resource.getString("admin.createaccount1.loginid")%>:<b><%=login_id%></b>
                    <br><br>
                   <p dir="<%=rtl%>" align="<%=align%>" class="mess"> <%=resource.getString("admin.createaccount1.role")%>:<b><%=role%></b>
                    <br><br>

                          
                    <%=resource.getString("admin.createaccount1.username")%>:<b><%=user_name%></b><br>
                    <input type="hidden" name="user_name" value="<b><%=user_name%></b>"/>
                    <br> <%=resource.getString("admin.staffaccount.libid")%>:<b><%=lib%></b>
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

                    <%if(btn.equals("Create Account")){%><%=resource.getString("admin.staffaccount1.mess1")%><%}%>

                    <%
                    session.setAttribute("staff_name",user_name);
                    %>



  

   </div>
   
    </body>

<script language="javascript">
    function message()
    {


        var a=confirm("<b><%=resource.getString("admin.staffaccount1.mess2")%><%=login_id %> <%=resource.getString("admin.staffaccount1.mess3")%></b>");
        if(a==true)
            location.href="<%=request.getContextPath()%>/admin/privilege_tree.jsp?login_id=<%=login_id%>";

        return false;
    }
</script>
</html>
