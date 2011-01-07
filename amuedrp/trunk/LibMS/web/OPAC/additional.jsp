<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Faraz Hasan" content="MCA,AMU">
<title>Additional Search...</title>
<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
<style type="text/css">
a:active
{
   color: #0000FF;
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

<SCRIPT language="JAVASCRIPT">
    function f()
    {
       if(document.F1.CMBYR.value=="upto" || document.F1.CMBYR.value=="after")
         {
            document.F1.TXTYR1.disabled=false;
            document.F1.TXTYR1.style.backgroundColor = "#ffffff";
            document.F1.TXTYR2.disabled=true;
            document.F1.TXTYR2.style.backgroundColor = "#eeeeee";

	   }
	 if(document.F1.CMBYR.value=="all")
         {
            document.F1.TXTYR1.disabled=true;
            document.F1.TXTYR1.style.backgroundColor = "#eeeeee";
            document.F1.TXTYR2.disabled=true;
            document.F1.TXTYR2.style.backgroundColor = "#eeeeee";

	   }
        if(document.F1.CMBYR.value=="between")
         {
            document.F1.TXTYR1.disabled=false;
            document.F1.TXTYR1.style.backgroundColor = "#ffffff";
            document.F1.TXTYR2.disabled=false;
            document.F1.TXTYR2.style.backgroundColor = "#ffffff";

	   }

    }
</SCRIPT>

</head>
<body>
    <%if(page.equals(true)){%>
    <div id="wb_Text1" style="position: absolute; left: 250px; top: 0px;
width: 500px; height: 19px; background-color: rgb(255, 255, 255);
z-index: 0;" >
<font style="font-size: 16px;" color="#c0003b" face="Arial" align="center"><b>
        <%=resource.getString("opac.additional.additionalsearchtext")%></b></font></div>
<div id="wb_Form1" style="position:absolute;left:8px;top:25px;width:600px;height:335px;z-index:25">
<form name="F1" method="post" action="additional.do">
<div style="position:absolute;left:473px;top:4px;width:124px;height:18px;border:1px #C0C0C0 solid;z-index:0">
<select name="CMBDB" size="1" id="CMBDB" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="combined">COMBINED</option>
    <option value="book">BOOKS</option>
    <option value="thesis">THESIS</option>
    <option value="tsd/psd">TSD/PSD</option>
    <option value="rd/r/rs">RD/R/RS</option>
    <option value="journal">JOURNALS</option>
    <option value="cd">CDs</option>
    <option value="ebook">EBOOKS</option>
    <option value="articles">ARTICLES DATA</option>
</select>
</div>
<div id="wb_Text1" style="position:absolute;left:29px;top:31px;width:49px;height:16px;z-index:1;" align="left">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b> <%=resource.getString("opac.additional.author")%></b></font></div>
<div id="wb_Text2" style="position:absolute;left:392px;top:6px;width:67px;height:16px;z-index:2;" align="left">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b> <%=resource.getString("opac.additional.database")%></b></font></div>
<input type="text" id="TXTTITLE" style="position:absolute;left:81px;top:60px;width:300px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:3" name="TXTTITLE">
<input type="text" id="TXTYR1" style="position:absolute;left:119px;top:186px;width:52px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:4" name="TXTYR1">
<input type="text" id="TXTYR2" style="position:absolute;left:200px;top:186px;width:52px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:5" name="TXTYR2">
<input type="submit" id="Button1" name="" value="<%=resource.getString("opac.additional.find")%>" style="position:absolute;left:230px;top:250px;width:68px;height:25px;font-family:Arial;font-weight: bold;font-size:13px;z-index:6">
<div style="position:absolute;left:473px;top:94px;width:60px;height:18px;border:1px #C0C0C0 solid;z-index:7">
<select name="CMBCONN3" size="1" id="CMBCONN3" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="or">OR</option>
<option value="and">AND</option>
<option value="phrase">PHRASE</option>
</select>
</div>
<div id="wb_Text10" style="position:absolute;left:392px;top:31px;width:81px;height:16px;z-index:8;" align="left">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b> <%=resource.getString("opac.additional.connectas1")%></b></font></div>
<div id="wb_Text11" style="position:absolute;left:42px;top:59px;width:34px;height:16px;z-index:9;" align="left">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.additional.title")%></b></font></div>
<input type="text" id="TXTAUTHOR" style="position:absolute;left:81px;top:32px;width:240px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:10" name="TXTAUTHOR">
<input type="text" id="TXTSUBJECT" style="position:absolute;left:81px;top:90px;width:300px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:11" name="TXTSUBJECT">
<input type="text" id="TXTOTHER" style="position:absolute;left:81px;top:121px;width:300px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:12" name="TXTOTHER">
<div id="wb_Text12" style="position:absolute;left:25px;top:91px;width:55px;height:16px;z-index:13;" align="left">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.additional.subject")%></b></font></div>
<div id="wb_Text13" style="position:absolute;left:5px;top:124px;width:77px;height:16px;z-index:14;" align="left">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.additional.otherfield")%></b></font></div>
<div style="position:absolute;left:472px;top:30px;width:60px;height:18px;border:1px #C0C0C0 solid;z-index:15">
<select name="CMBCONN1" size="1" id="CMBCONN1" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font- New;font-size:13px;">
<option selected value="or">OR</option>family:Courier
<option value="and">AND</option>
<option value="phrase">PHRASE</option>
</select>
</div>
<div style="position:absolute;left:472px;top:63px;width:60px;height:18px;border:1px #C0C0C0 solid;z-index:16">
<select name="CMBCONN2" size="1" id="CMBCONN2" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="or">OR</option>
<option value="and">AND</option>
<option value="phrase">PHRASE</option>
</select>
</div>
<div style="position:absolute;left:473px;top:125px;width:60px;height:18px;border:1px #C0C0C0 solid;z-index:17">
<select name="CMBCONN4" size="1" id="CMBCONN4" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="or">OR</option>
<option value="and">AND</option>
<option value="phrase">PHRASE</option>
</select>
</div>
<div id="wb_Text2" style="position:absolute;left:25px;top:6px;width:67px;height:16px;z-index:2;" align="left">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.additional.library")%></b></font></div>
<div style="position:absolute;left:81px;top:4px;width:100px;height:18px;border:1px #C0C0C0 solid;z-index:9">
<select name="CMBLib" size="1" id="CMBLib" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
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
<input type="reset" id="Button2" name="" value="<%=resource.getString("opac.additional.clear")%>" style="position:absolute;left:330px;top:250px;width:67px;height:25px;font-family:Arial;font-weight: bold;font-size:13px;z-index:18">
<div id="wb_Text14" style="position:absolute;left:392px;top:62px;width:77px;height:16px;z-index:19;" align="left">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.additional.connectas2")%></b></font></div>
<div id="wb_Text15" style="position:absolute;left:392px;top:93px;width:80px;height:16px;z-index:20;" align="left">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.additional.connectas3")%></b></font></div>
<div id="wb_Text16" style="position:absolute;left:392px;top:124px;width:79px;height:16px;z-index:21;" align="left">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.additional.connectas4")%></b></font></div>
<div style="position:absolute;left:127px;top:158px;width:118px;height:18px;border:1px #C0C0C0 solid;z-index:22">
<select name="CMBYR" onChange="f()" size="1" id="CMBYR" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="all">Select Year</option>
<option value="all">ALL YEARS</option>
<option value="between">BETWEEN</option>
<option value="upto">UPTO</option>
<option value="after">AFTER</option>
</select>
</div>
<div id="wb_Text3" style="position:absolute;left:3px;top:158px;width:117px;height:16px;z-index:23;" align="left">
<font style="font-size:13px" color="#000000" face="Arial"><b><%=resource.getString("opac.additional.publicationyear")%></b></font></div>
<div id="wb_Text4" style="position:absolute;left:178px;top:186px;width:17px;height:16px;z-index:24;" align="left">
<font style="font-size:13px" color="#000000" face="Arial"><b>---</b></font></div>
</form>
</div>
<%}else{%>

<div id="wb_Text1" style="position: absolute; right: 240px; top: 0px;
width: 500px; height: 19px; background-color: rgb(255, 255, 255);
z-index: 0;" align="right" >
<font style="font-size: 16px;" color="#c0003b" face="Arial" align="right"><b>
        <%=resource.getString("opac.additional.additionalsearchtext")%></b></font></div>
<div id="wb_Form1" style="position:absolute;right:8px;top:25px;width:600px;height:335px;z-index:25">
<form name="F1" method="post" action="additional.do">
<div style="position:absolute;right:473px;top:4px;width:124px;height:18px;border:1px #C0C0C0 solid;z-index:0">
<select name="CMBDB" size="1" id="CMBDB" style="right:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="combined">COMBINED</option>
    <option value="book">BOOKS</option>
    <option value="thesis">THESIS</option>
    <option value="tsd/psd">TSD/PSD</option>
    <option value="rd/r/rs">RD/R/RS</option>
    <option value="journal">JOURNALS</option>
    <option value="cd">CDs</option>
    <option value="ebook">EBOOKS</option>
    <option value="articles">ARTICLES DATA</option>
</select>
</div>
<div id="wb_Text1" style="position:absolute;right:29px;top:31px;width:49px;height:16px;z-index:1;" align="right">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.additional.author")%></b></font></div>
<div id="wb_Text2" style="position:absolute;right:392px;top:6px;width:67px;height:16px;z-index:2;" align="right">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.additional.database")%></b></font></div>
<input type="text" id="TXTTITLE" style="position:absolute;right:81px;top:60px;width:300px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:3" name="TXTTITLE">
<input type="text" id="TXTYR1" style="position:absolute;right:119px;top:186px;width:52px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:4" name="TXTYR1">
<input type="text" id="TXTYR2" style="position:absolute;right:200px;top:186px;width:52px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:5" name="TXTYR2">
<input type="submit" id="Button1" name="" value="<%=resource.getString("opac.additional.find")%>" style="position:absolute;right:200px;top:250px;width:68px;height:25px;font-family:Arial;font-size:13px;z-index:6">
<div style="position:absolute;right:473px;top:94px;width:60px;height:18px;border:1px #C0C0C0 solid;z-index:7">
<select name="CMBCONN3" size="1" id="CMBCONN3" style="right:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="or">OR</option>
<option value="and">AND</option>
<option value="phrase">PHRASE</option>
</select>
</div>
<div id="wb_Text10" style="position:absolute;right:392px;top:31px;width:81px;height:16px;z-index:8;" align="right">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.additional.connectas1")%></b></font></div>
<div id="wb_Text11" style="position:absolute;right:42px;top:59px;width:34px;height:16px;z-index:9;" align="right">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.additional.title")%></b></font></div>
<input type="text" id="TXTAUTHOR" style="position:absolute;right:81px;top:32px;width:240px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:10" name="TXTAUTHOR">
<input type="text" id="TXTSUBJECT" style="position:absolute;right:81px;top:90px;width:300px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:11" name="TXTSUBJECT">
<input type="text" id="TXTOTHER" style="position:absolute;right:81px;top:121px;width:300px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:12" name="TXTOTHER">
<div id="wb_Text12" style="position:absolute;right:25px;top:91px;width:55px;height:16px;z-index:13;" align="right">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.additional.subject")%></b></font></div>
<div id="wb_Text13" style="position:absolute;right:5px;top:124px;width:77px;height:16px;z-index:14;" align="right">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.additional.otherfield")%></b></font></div>
<div style="position:absolute;right:472px;top:30px;width:60px;height:18px;border:1px #C0C0C0 solid;z-index:15">
<select name="CMBCONN1" size="1" id="CMBCONN1" style="right:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="or">OR</option>
<option value="and">AND</option>
<option value="phrase">PHRASE</option>
</select>
</div>
<div style="position:absolute;right:472px;top:63px;width:60px;height:18px;border:1px #C0C0C0 solid;z-index:16">
<select name="CMBCONN2" size="1" id="CMBCONN2" style="right:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="or">OR</option>
<option value="and">AND</option>
<option value="phrase">PHRASE</option>
</select>
</div>
<div style="position:absolute;right:473px;top:125px;width:60px;height:18px;border:1px #C0C0C0 solid;z-index:17">
<select name="CMBCONN4" size="1" id="CMBCONN4" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="or">OR</option>
<option value="and">AND</option>
<option value="phrase">PHRASE</option>
</select>
</div>
<div id="wb_Text2" style="position:absolute;right:16px;top:6px;width:67px;height:16px;z-index:2;" align="right">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.additional.library")%></b></font></div>
<div style="position:absolute;right:81px;top:4px;width:100px;height:18px;border:1px #C0C0C0 solid;z-index:9">
<select name="CMBLib" size="1" id="CMBLib" style="right:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
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
<input type="reset" id="Button2" name="" value="<%=resource.getString("opac.additional.clear")%>" style="position:absolute;right:280px;top:250px;width:67px;height:25px;font-family:Arial;font-size:13px;z-index:18">
<div id="wb_Text14" style="position:absolute;right:392px;top:62px;width:77px;height:16px;z-index:19;" align="right">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.additional.connectas2")%></b></font></div>
<div id="wb_Text15" style="position:absolute;right:392px;top:93px;width:80px;height:16px;z-index:20;" align="right">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.additional.connectas3")%></b></font></div>
<div id="wb_Text16" style="position:absolute;right:392px;top:124px;width:79px;height:16px;z-index:21;" align="right">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.additional.connectas4")%></b></font></div>
<div style="position:absolute;right:127px;top:158px;width:118px;height:18px;border:1px #C0C0C0 solid;z-index:22">
<select name="CMBYR" onChange="f()" size="1" id="CMBYR" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="all">Select Year</option>
<option value="all">ALL YEARS</option>
<option value="between">BETWEEN</option>
<option value="upto">UPTO</option>
<option value="after">AFTER</option>
</select>
</div>
<div id="wb_Text3" style="position:absolute;right:3px;top:158px;width:117px;height:16px;z-index:23;" align="right">
<font style="font-size:13px" color="#000000" face="Arial"><b>:<%=resource.getString("opac.additional.publicationyear")%></b></font></div>
<div id="wb_Text4" style="position:absolute;right:178px;top:186px;width:17px;height:16px;z-index:24;" align="right">
<font style="font-size:13px" color="#000000" face="Arial"><b>---</b></font></div>
</form>
</div>


<%}%>
</body>
</html>
