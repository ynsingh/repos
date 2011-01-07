<%-- 
    Document   : journalinput
    Created on : Oct 22, 2004, 8:10:43 PM
    Author     : Client
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.myapp.struts.opac.OpacDoc"%>
<%@ page import="java.util.*"%>
<%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
<%@ page import="org.apache.taglibs.datagrid.DataGridTag"%>
 <%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="Iqubal Ahmad" content="MCA,VBU">
        <title>Journal Search.....</title>

        <style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
  <style>
    th a:link      { text-decoration: none; color: black }
     th a:visited   { text-decoration: none; color: black }
     .rows          { background-color: white }
     .hiliterows    { background-color: pink; color: #000000; font-weight: bold }
     .alternaterows { background-color: #efefef }
     .header        { background-color: #c0003b; color: #FFFFFF;font-weight: bold }

     .datagrid      { border: 1px solid #C7C5B2; font-family: arial; font-size: 9pt;
	    font-weight: normal }
</style>

<script language="javascript">
function fun()
{
/*document.Form1.action="number.jsp";
document.Form1.method="post";
document.Form1.target="f1";
document.Form1.submit();*/
}
function funcSearch()
{
    document.Form1.action="JournalAction.do";
   document.Form1.method="post";
    document.Form1.submit();
}
</script>
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



    </head>

    <body onload="funcSearch()">
         <%if(page.equals(true)){%>

        <form method="post" action="JournalAction.do" target="f1" name="Form1">
         <div id="wb_Text6" style="position:absolute;left:50px;top:0px;width:69px;height:16px;z-index:0;" align="left">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.journals.library")%></b></font></div>
<div style="position:absolute;left:100px;top:0px;width:100px;height:18px;border:1px #C0C0C0 solid;z-index:9">
    <select name="CMBLib" size="1" onchange="funcSearch()" id="CMBLib" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
    <%
        ResultSet rs = (ResultSet)session.getAttribute("libRs");
        String lib_id = (String)session.getAttribute("library_id");
        rs.beforeFirst();
    %>
    <option selected value="<%=lib_id%>"><%=lib_id.toUpperCase()%></option>
    <option value="all">ALL</option>
    <%

    while (rs.next())
            {
    %>
    <option value="<%= rs.getString(1) %>"><%=rs.getString(1).toUpperCase()%></option>
    <% } %>
</select>
</div>


        </form>

 <IFRAME  name="f1" src="#" frameborder=0 scrolling="NO" style="position:absolute;color:deepskyblue;top:96px;left:24px;height:370px;width:550px;visibility:true;" id="f1"></IFRAME>
<IFRAME  name="f2" src="#" frameborder=0 scrolling="NO" style="position:absolute;color:deepskyblue;top:96px;left:582px;height:370px;width:450px;visibility:true;" id="f2"></IFRAME>

<%}else{%>

 <form method="post" action="JournalAction.do" target="f1" name="Form1">
         <div id="wb_Text6" style="position:absolute;right:50px;top:0px;width:69px;height:16px;z-index:0;" align="right">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.journals.library")%></b></font></div>
<div style="position:absolute;right:100px;top:0px;width:100px;height:18px;border:1px #C0C0C0 solid;z-index:9">
    <select name="CMBLib" size="1" onchange="funcSearch()" id="CMBLib" style="right:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
    <%
        ResultSet rs = (ResultSet)session.getAttribute("libRs");
        String lib_id = (String)session.getAttribute("library_id");
        rs.beforeFirst();
    %>
    <option selected value="<%=lib_id%>"><%=lib_id.toUpperCase()%></option>
    <option value="all">ALL</option>
    <%

    while (rs.next())
            {
    %>
    <option value="<%= rs.getString(1) %>"><%=rs.getString(1).toUpperCase()%></option>
    <% } %>
</select>
</div>


        </form>

 <IFRAME  name="f1" src="#" frameborder=0 scrolling="NO" style="position:absolute;color:deepskyblue;top:96px;right:24px;height:370px;width:550px;visibility:true;" id="f1"></IFRAME>
<IFRAME  name="f2" src="#" frameborder=0 scrolling="NO" style="position:absolute;color:deepskyblue;top:96px;right:582px;height:370px;width:450px;visibility:true;" id="f2"></IFRAME>
   
   
   
   
   
   <%}%>

    </body>
</html>
