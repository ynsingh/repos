<%-- 
    Document   : Use to Display Staff Account Create Message
    Created on : Jun 28, 2010, 8:11:42 PM
    Author     : Kedar Kumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
 <jsp:include page="header.jsp" flush="true" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String email_id=(String)request.getAttribute("user_id");
String staff_id=(String)request.getAttribute("staff_id");
String user_name=(String)request.getAttribute("user_name");
String role=(String)request.getAttribute("role");

String lib=(String)request.getAttribute("library_id");
String sublib=(String)request.getAttribute("sublibrary");
String msg=(String)request.getAttribute("msg");
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
    <body onload="return message()">
   
<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;">

  
                   
                    <br><br>

                    <p dir="<%=rtl%>" align="<%=align%>" class="mess"> <%=resource.getString("admin.createaccount1.loginid")%>:<b><%=email_id%></b>
                    <br><br>
                  <p dir="<%=rtl%>" align="<%=align%>" class="mess"> <%=resource.getString("admin.createaccount1.role")%>:<b><%=role%></b><br>
                           <br>
               <%=resource.getString("admin.createaccount1.username")%>:<b><%=user_name%></b><br><br>
                    <input type="hidden" name="user_name" value="<%=user_name%>"/>
                   <%=resource.getString("admin.staffaccount.libid")%>:<b><%=lib%></b><br><br>
                    
                    <%=resource.getString("admin.staffaccount.mail")%>
                    <%
                    session.setAttribute("staff_name",user_name);
                    %>



    </p>










        </div>
   

    </body>
    <script language="javascript">
    function message()
    {
        var a=alert("<%=resource.getString("admin.staffaccount.priv")%>:<%=user_name%>");


        location.href="<%=request.getContextPath()%>/admin/ask_for_assign_privilege.jsp?staff_id=<%=staff_id%>&user_name=<%=user_name%>";

        return false;
    }
</script>
</html>
