<%--
    Document   : Member.jsp
    Created on : Jun 10, 2010, 1:15:40 PM
    Author     : Mayank Saxena
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>
 <%@page import="java.sql.ResultSet"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Mayank Saxena" content="MCA,AMU">
<title>Member LogIn Page</title>
<link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
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
    
   


<html:form method="post" action="/OPAC/MemberDetails">

     <%if(page.equals(true)){%>

       <table  align="left" width="300px" height="200px" style="background-color: white;border:#c0003b 1px solid;margin:0px 0px 0px 0px;">
           <tr><td style="background-color: #c0003b;color:white;" colspan="2" class="btn1" height="30px"><b><%=resource.getString("opac.myaccount.memberlogin")%></b> </td></tr>
            <tr><td width="100px" class="btn1" align="left"><%=resource.getString("opac.myaccount.memberid")%></td><td><input id="TXTMEMID"  name="TXTMEMID" type="text"></td></tr>
        <tr><td class="btn1" align="left"><%=resource.getString("opac.myaccount.password")%></td><td><input id="TXTPASS" name="TXTPASS" type="password">
               


            </td></tr>
        <tr><td class="btn1" align="left">Library ID</td><td width="200px">
                <select name="CMBLib"  size="1" id="CMBLib" class="">
    <%
        ResultSet rs = (ResultSet)session.getAttribute("libRs");
        String lib_id = (String)session.getAttribute("library_id");

        rs.beforeFirst();

    if(lib_id==null)
    {%>

    <option selected value="all">ALL</option>
    <%}
    else
    {%>
    <option selected value="<%=lib_id%>"><%=lib_id.toUpperCase()%></option>
    <option value="all">ALL</option>

    <%
    }
    while (rs.next())
            {
    %>
    <option value="<%= rs.getString(1) %>"><%=rs.getString(1).toUpperCase()%></option>
    <% } %>
</select>
 <br>
                 <%
     message=request.getParameter("msg");
     if (message==null) message =(String) request.getAttribute("msg");
    if(message!=null) {%>
            <font size="3px" align=center color=red><b><%=message%></b></font>
   <% }else
        message="";
    %>



            </td></tr>
        <tr><td></td><td width="200px"><input id="Button1" class="btn2" value="<%=resource.getString("opac.myaccount.submit")%>"  type="submit"></td></tr>
        <tr><td></td><td  align="left" width="200px">
                  <form method="post" action="">


                <font style="font-size: 13px;" color="#000000" face="Arial"><b><a href="./OpacLib.do?name=newmember" style="text-decoration: none;"><%=resource.getString("opac.myaccount.newmember")%></a></b></font>

                  </form>
                <br><br></td></tr>
    </table>


<%}
    else
{%>

<table  align="right" width="300px" height="200px" style="background-color: white;border:#c0003b 1px solid;margin:0px 0px 0px 0px;">
           <tr><td style="background-color: #c0003b;color:white;" colspan="2" class="btn1" height="30px"><b><%=resource.getString("opac.myaccount.memberlogin")%></b> </td></tr>
            <tr><td width="150px" align="right"><input id="TXTMEMID"  name="TXTMEMID" type="text" align="right"></td><td  class="btn1" align="left"><%=resource.getString("opac.myaccount.memberid")%></td></tr>
            <tr><td align="right"><input id="TXTPASS" name="TXTPASS" type="password" align="right">
                

            </td><td class="btn1" align="left"><%=resource.getString("opac.myaccount.password")%></td></tr>
            <tr><td width="150px" align="right"><select name="CMBLib"  size="1" id="CMBLib" class="">
    <%
        ResultSet rs = (ResultSet)session.getAttribute("libRs");
        String lib_id = (String)session.getAttribute("library_id");

        rs.beforeFirst();

    if(lib_id==null)
    {%>

    <option selected value="all">ALL</option>
    <%}
    else
    {%>
    <option selected value="<%=lib_id%>"><%=lib_id.toUpperCase()%></option>
    <option value="all">ALL</option>

    <%
    }
    while (rs.next())
            {
    %>
    <option value="<%= rs.getString(1) %>"><%=rs.getString(1).toUpperCase()%></option>
    <% } %>
</select>
<br>
                 <%
     message=request.getParameter("msg");
     if (message==null) message =(String) request.getAttribute("msg");
    if(message!=null) {%>
            <font size="3px" align=center color=red><b><%=message%></b></font>
   <% }else
        message="";
    %>


                </td><td  class="btn1" align="left">Library Id</td></tr>
        <tr><td align="right"><input id="Button1" class="btn2" value="<%=resource.getString("opac.myaccount.submit")%>"  type="submit"></td><td></td></tr>
        <tr><td  align="right" width="200px">
                  <form method="post" action="">


                <font style="font-size: 13px;" color="#000000" face="Arial"><b><a href="./OpacLib.do?name=newmember" style="text-decoration: none;"><%=resource.getString("opac.myaccount.newmember")%></a></b></font>

                  </form>
                <br><br></td><td></td></tr>
    </table>

<%}%>


</html:form>

   
    


</body></html>