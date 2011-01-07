<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Mayank Saxena" content="MCA,AMU">
<title>New Arrivals.....</title>
<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
    <script language="javascript">
function fun()
{
document.form1.action = "NewArrival.do"
document.form1.method="post";
document.form1.target="f1";
document.form1.submit();
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

<body onload="fun()">
    <%if(page.equals(true)){%>

<div id="wb_Form1" style="position:absolute;left:30px;top:24px;width:348px;height:46px;z-index:7">
    <form name ="form1" method="post" action="/NewArrival">

<div style="border: 1px solid rgb(0, 85, 0); position: absolute; left: 270px; top: 45px; width: 122px; height: 18px;border:1px #C0C0C0 solid; z-index: 0;">
<select name="CMBPERIOD" onChange="fun()" size="1" style="border-width: 0px; left: 0px; top: 0px; width: 100%; height: 100%; font-family: Arial; font-size: 13px;">
<option value="2">within 2 months</option>
<option value="6">within 6 months</option>
<option value="12">within 1 year</option>
</select>
</div>
<div id="wb_Text1" style="position: absolute; left: 270px; top: 25px; width: 100px; height: 20px; z-index: 2;" align="left">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.newarrivals.selectperiod")%></b></font></div>
<input type="radio" id="RadioButton1" onclick="fun()" name="r" value="book" style="position:absolute;left:0px;top:45px;z-index:0">
<input type="radio" id="RadioButton2" name="r" onclick="fun()" value="journal" style="position:absolute;left:80px;top:45px;z-index:1">
<div id="wb_Text1" style="position:absolute;left:23px;top:45px;width:45px;height:16px;z-index:2;" align="left">
<font style="font-size:13px" color="#000000" face="Arial"><b><%=resource.getString("opac.newarrivals.books")%></b></font></div>
<div id="wb_Text2" style="position:absolute;left:105px;top:45px;width:61px;height:16px;z-index:3;" align="left">
<font style="font-size:13px" color="#000000" face="Arial"><b><%=resource.getString("opac.newarrivals.journals")%></b></font></div>
<input type="radio" id="RadioButton3" name="r" onclick="fun()" value="others" style="position:absolute;left:175px;top:45px;z-index:4">
<div id="wb_Text3" style="position:absolute;left:205px;top:45px;width:48px;height:16px;z-index:5;" align="left">
<font style="font-size:13px" color="#000000" face="Arial"><b><%=resource.getString("opac.newarrivals.other")%></b></font></div>

<div id="wb_Text6" style="position:absolute;left:5px;top:0px;width:69px;height:16px;z-index:0;" align="left">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.newarrivals.library")%></b></font></div>
<div style="position:absolute;left:55px;top:0px;width:100px;height:18px;border:1px #C0C0C0 solid;z-index:9">
    <select name="CMBLib" size="1" onchange="fun()" id="CMBLib" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
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
</div>
<IFRAME  name="f1" src="#" frameborder=0 scrolling="NO" style="position:absolute;color:deepskyblue;top:96px;left:24px;height:370px;width:550px;visibility:true;" id="f1"></IFRAME>
<IFRAME  name="f2" src="#" frameborder=0 scrolling="NO" style="position:absolute;color:deepskyblue;top:96px;left:582px;height:370px;width:450px;visibility:true;" id="f2"></IFRAME>

<%}else{%>
 
 <div id="wb_Form1" style="position:absolute;right:177px;top:24px;width:348px;height:46px;z-index:7">
    <form name ="form1" method="post" action="/NewArrival">

<div style="border: 1px solid rgb(0, 85, 0); position: absolute; right: 290px; top: 13px; width: 122px; height: 18px;border:1px #C0C0C0 solid; z-index: 0;">
<select name="CMBPERIOD" onChange="fun()" size="1" style="border-width: 0px; right: 0px; top: 0px; width: 100%; height: 100%; font-family: Arial; font-size: 13px;">
<option value="2">within 2 months</option>
<option value="6">within 6 months</option>
<option value="12">within 1 year</option>
</select>
</div>
<div id="wb_Text1" style="position: absolute; right: 290px; top: -5px; width: 100px; height: 20px; z-index: 2;" align="right">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.newarrivals.selectperiod")%></b></font></div>
<input type="radio" id="RadioButton1" onclick="fun()" name="r" value="book" style="position:absolute;right:38px;top:11px;z-index:0">
<input type="radio" id="RadioButton2" name="r" onclick="fun()" value="journal" style="position:absolute;right:116px;top:11px;z-index:1">
<div id="wb_Text1" style="position:absolute;right:61px;top:13px;width:45px;height:16px;z-index:2;" align="right">
<font style="font-size:13px" color="#000000" face="Arial"><b><%=resource.getString("opac.newarrivals.books")%></b></font></div>
<div id="wb_Text2" style="position:absolute;right:142px;top:13px;width:61px;height:16px;z-index:3;" align="right">
<font style="font-size:13px" color="#000000" face="Arial"><b><%=resource.getString("opac.newarrivals.journals")%></b></font></div>
<input type="radio" id="RadioButton3" name="r" onclick="fun()" value="others" style="position:absolute;right:211px;top:11px;z-index:4">
<div id="wb_Text3" style="position:absolute;right:233px;top:13px;width:48px;height:16px;z-index:5;" align="right">
<font style="font-size:13px" color="#000000" face="Arial"><b><%=resource.getString("opac.newarrivals.other")%></b></font></div>

<div id="wb_Text6" style="position:absolute;right:5px;top:50px;width:69px;height:16px;z-index:0;" align="right">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.newarrivals.library")%></b></font></div>
<div style="position:absolute;right:55px;top:50px;width:100px;height:18px;border:1px #C0C0C0 solid;z-index:9">
    <select name="CMBLib" size="1" onchange="fun()" id="CMBLib" style="right:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
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
</div>
<IFRAME  name="f1" src="#" frameborder=0 scrolling="YES" style="position:absolute;color:deepskyblue;top:96px;right:24px;height:370px;width:550px;visibility:true;" id="f1"></IFRAME>
<IFRAME  name="f2" src="#" frameborder=0 scrolling="YES" style="position:absolute;color:deepskyblue;top:96px;right:582px;height:370px;width:450px;visibility:true;" id="f2"></IFRAME>
 
 
 
  <%}%>

</body>
</html>
