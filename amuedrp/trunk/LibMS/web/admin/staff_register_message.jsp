<%-- 
    Document   : Use to Display Successfull Staff Creation Message
    Created on : Jun 28, 2010, 8:11:42 PM
    Author     : Kedar Kumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
 <jsp:include page="header.jsp" flush="true" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String staff_id=(String)request.getAttribute("staff_id");
String first_name=(String)request.getAttribute("first_name");
String last_name=(String)request.getAttribute("last_name");
String lib=(String)request.getAttribute("library_id");
String sublib=(String)request.getAttribute("sublibrary_id");
String email_id=(String)request.getAttribute("email_id");
//out.println()
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
        <title>LibMS : Manage Staff Section</title>
    </head>
    <body onload="return message();">
   
<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;">

    <table width="400px" height="600px"  valign="top"  dir="<%=rtl%>" align="<%=align%>" id="tab1">
        <tr><td   width="300px" height="400px" valign="top" style=""  dir="<%=rtl%>" align="<%=align%>">
              
                  
                    <br>
               
                       
                    <p  dir="<%=rtl%>" align="<%=align%>" class="mess"><%=resource.getString("admin.staff_register_message.staffdetail")%>:-<br></p>
                    
                    &nbsp;&nbsp;&nbsp;<p dir="<%=rtl%>"  align="<%=align%>" class="mess"><%=resource.getString("admin.staff_register_message.name")%> :<b><%=first_name%>&nbsp;<%=last_name%></b></p>
                    &nbsp;&nbsp;&nbsp;<p  dir="<%=rtl%>" align="<%=align%>" class="mess"><%=resource.getString("admin.staff_register_message.libname")%>:<b><%=lib%></b></p>
                   &nbsp;&nbsp;&nbsp;<p dir="<%=rtl%>" align="<%=align%>" class="mess"><%=resource.getString("admin.staff_register_message.sublibname")%>:<b><%=sublib%></b></p>



    
</td></tr></table>
        </div>
   

    </body>
    <script language="javascript">
    function message()
    {

        var a=alert("<%=resource.getString("admin.staff_register_message.mess")%> :<%=staff_id%>");


        location.href="<%=request.getContextPath()%>/admin/ask_for_create_account.jsp?staff_id=<%=staff_id%>&first_name=<%=first_name%>&last_name=<%=last_name%>&email_id=<%=email_id%>";

    }
</script>
</html>
