<%--
    Document   : Member.jsp
    Created on : Jun 10, 2010, 1:15:40 PM
    Author     : Mayank Saxena
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Mayank Saxena" content="MCA,AMU">
<title>Member LogIn Page</title>

<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
%>
<%
try{
locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;}
    else{ rtl="RTL";page=false;}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>


</head><body>
    <%! String message;%>
    
    <%if(page.equals(true)){%>
    

<div id="wb_Form1" style="position: absolute; left: 0px; top: 21px; width: 492px; height: 84px; z-index: 5;">
<html:form method="post" action="/OPAC/MemberDetails">
<input id="TXTMEMID" style="border: 1px solid rgb(192, 192, 192); position: absolute; left: 100px; top: 60px; width: 247px; height: 18px; font-family: Courier New; font-size: 13px; z-index: 0;" name="TXTMEMID" type="text">
<input id="Button1" name="" value="<%=resource.getString("opac.myaccount.submit")%>"  style="position: absolute; left: 372px; top: 60px; width: 69px; height: 25px; font-family: Arial; font-weight: bold; font-size: 13px; z-index: 1;" type="submit">
</html:form>
</div>

    <%
     message=request.getParameter("msg");
     if (message==null) message =(String) request.getAttribute("msg");
    if(message!=null) {%>
        <br><br><br><br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <font size=3 align=center color=red><b><%=message%></b></font>
   <% }else
        message="";
    %>
<div id="wb_Text1" style="position: absolute; left: 15px; top: 85px; width: 77px; height: 16px; z-index: 6;" align="left">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.memberid")%></b></font></div>
<div id="wb_Text3" style="position: absolute; left: 25px; top: 30px; width: 391px; height: 22px; background-color: rgb(255, 255, 224); z-index: 7;" align="left">
<font style="font-size: 19px;" color="#000000" face="Arial"><b>&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;<%=resource.getString("opac.myaccount.memberlogin")%></b></font></div>
<div id="wb_Form2" style="position: absolute; left: 0px; top: 122px; width: 492px; height: 82px; z-index: 8;">
    <form method="post" action="">
<div id="wb_Text2" style="position: absolute; left: 100px; top: 20px; width: 244px; height: 32px; background-color: rgb(255, 255, 224); z-index: 2;" align="left">
    <font style="font-size: 13px;" color="#000000" face="Arial"><b><a href="../OPAC/newmember.jsp"><%=resource.getString("opac.myaccount.newmember")%></a></b></font></div>
</form>
</div>

<%}else{%>

<div id="wb_Form1" style="position: absolute; right: 0px; top: 50px; width: 492px; height: 84px; z-index: 5;">
<html:form method="post" action="/OPAC/MemberDetails">
<input id="TXTMEMID" style="border: 1px solid rgb(192, 192, 192); position: absolute; right: 120px; top: 50px; width: 247px; height: 18px; font-family: Courier New; font-size: 13px; z-index: 0;" name="TXTMEMID" type="text">
<input id="Button1" name="" value="<%=resource.getString("opac.myaccount.submit")%>"  style="position: absolute; right: 400px; top: 50px; width: 69px; height: 25px; font-family: Arial; font-weight: bold; font-size: 13px; z-index: 1;" type="submit">
</html:form>
</div>

    <%
     message=request.getParameter("msg");
    if(message!=null) {%>
        <br><br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <font size=3 align=center color=red><b><%=message%></b></font>
   <% }else
        message="";
    %>
<div id="wb_Text1" style="position: absolute; right: 25px; top: 100px; width: 77px; height: 16px; z-index: 6;" align="right">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.memberid")%></b></font></div>
<div id="wb_Text3" style="position: absolute; right: 130px; top: 50px; width: 391px; height: 22px; background-color: rgb(255, 255, 224); z-index: 7;" align="right">
<font style="font-size: 19px;" color="#000000" face="Arial"><b>&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;<%=resource.getString("opac.myaccount.memberlogin")%> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font></div>
<div id="wb_Form2" style="position: absolute; right: 0px; top: 122px; width: 492px; height: 82px; z-index: 8;">
    <form method="post" action="">
<div id="wb_Text2" style="position: absolute; right: 130px; top: 20px; width: 244px; height: 32px; background-color: rgb(255, 255, 224); z-index: 2;" align="right">
    <font style="font-size: 13px;" color="#000000" face="Arial"><b><a href="../OPAC/newmember.jsp"><%=resource.getString("opac.myaccount.newmember")%></a></b></font></div>
</form>
</div>




 <%}%>

</body></html>