<%@page import="java.sql.ResultSet"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Mayank Saxena" content="MCA,AMU">
<title>Simple Search...</title>
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
    <div id="wb_Text1" style="position: absolute; left: 200px; top: 0px;
width: 650px; height: 10px; background-color: rgb(255, 255, 255);
z-index: 0;" >
<font style="font-size: 16px;" color="#c0003b" face="Arial" align="center"><b>
        <%=resource.getString("opac.simplesearch.simplesearchtext")%></b></font></div>
<div id="wb_Form1" style="position:absolute;left:3px;top:17px;width:511px;height:301px;z-index:15">
    <html:form  method="post" action="/simple_search">
<div style="position:absolute;left:16px;top:60px;width:97px;height:18px;border:1px #C0C0C0 solid;z-index:0">
<select name="CMBFIELD" size="1" id="CMBFIELD" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option value="any field" selected>ANY FIELD</option>
<option  value="author">AUTHOR</option>
<option value="title">TITLE</option>
<option value="isbn">ISBN</option>
<option value="subject">SUBJECT</option>
<option value="pubplace">PLACE</option>
<option value="publisher">PUBLISHER</option>

</select>
</div>
<div style="position:absolute;left:370px;top:5px;width:131px;height:18px;border:1px #C0C0C0 solid;z-index:1">
<select name="CMBDB" size="1" id="CMBDB" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option value="combined" selected>COMBINED</option>
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
<div id="wb_Text1" style="position:absolute;left:16px;top:42px;width:45px;height:16px;z-index:2;" align="left">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.simplesearch.field")%></b></font></div>
<div id="wb_Text2" style="position:absolute;left:300px;top:5px;width:69px;height:16px;z-index:3;" align="left">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.simplesearch.database")%></b></font></div>
<div id="wb_Text6" style="position:absolute;left:16px;top:5px;width:69px;height:16px;z-index:0;" align="left">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.simplesearch.library")%></b></font></div>
<input type="text" id="TXTPHRASE" style="position:absolute;left:140px;top:63px;width:330px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:4" name="TXTPHRASE">
<div id="wb_Text3" style="position:absolute;left:140px;top:42px;width:155px;height:18px;z-index:5;" align="left">
<font style="font-size:13px" color="#000000" face="Arial"><b><%=resource.getString("opac.simplesearch.enterwordorphrase")%></b></font></div>
<input type="submit" id="Button1" name="" value="<%=resource.getString("opac.simplesearch.find")%>" style="position:absolute;left:190px;top:231px;width:68px;height:25px;font-family:Arial;font-weight: bold;font-size:13px;z-index:6">
<input type="reset" id="Button2" name="" value="<%=resource.getString("opac.simplesearch.clear")%>" style="position:absolute;left:290px;top:231px;width:67px;height:25px;font-family:Arial;font-weight: bold;font-size:13px;z-index:7">
<div style="position:absolute;left:220px;top:95px;width:80px;height:18px;border:1px #C0C0C0 solid;z-index:8">
<select name="CMBCONN" size="1" id="CMBCONN" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="or">OR</option>
<option value="and">AND</option>
</select>
</div>
<div style="position:absolute;left:92px;top:5px;width:100px;height:18px;border:1px #C0C0C0 solid;z-index:9">
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
<div id="wb_Text4" style="position:absolute;left:140px;top:99px;width:80px;height:16px;z-index:10;" align="left">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.simplesearch.connectas")%></b></font></div>
<div style="position:absolute;left:72px;top:147px;width:118px;height:18px;border:1px #C0C0C0 solid;z-index:11">
<select name="CMBYR" onChange=f() size="1" id="CMBYR" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="all">Select Year</option>
<option value="all">ALL YEARS</option>
<option value="between">BETWEEN</option>
<option value="upto">UPTO</option>
<option value="after">AFTER</option>
</select>
</div>

<div id="wb_Text8" style="position:absolute;left:144px;top:186px;width:23px;height:14px;z-index:12;" align="left">
<font style="font-size:11px" color="#000000" face="Arial"><b>---</b></font></div>
<input type="text" id="TXTYR1" style="position:absolute;left:72px;top:183px;width:62px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:13" name="TXTYR1">
<input type="text" id="TXTYR2" style="position:absolute;left:162px;top:183px;width:62px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:14" name="TXTYR2">
<div id="wb_Text5" style="position:absolute;left:34px;top:121px;width:28px;height:16px;z-index:11;" align="left">
<img src='/LibMS-Struts/images/publishingYear.gif'></div>
<div id="wb_Text5" style="position:absolute;left:299px;top:132px;width:28px;height:16px;z-index:11;" align="left">
<img src='/LibMS-Struts/images/sortby.gif'></div>
<div style="position:absolute;left:304px;top:162px;width:102px;height:18px;border:1px #C0C0C0 solid;z-index:8">
 <select name="CMBSORT" size="1" id="CMBSORT" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option  value="author">AUTHOR</option>
<option value="title">TITLE</option>
<option value="isbn">ISBN</option>
<option value="subject">SUBJECT</option>
<option value="accessionno">ACCESSION NO</option>
<option value="publisher">PUBLISHER</option>
</select>
</div>
    </html:form>
</div>

     <%}else{%>

     <div align="right" id="wb_Text1"  style="position: absolute;right : 200px; top: 0px;
width: 650px; height: 10px; background-color: rgb(255, 255, 255);
z-index: 0;" >
<font style="font-size: 16px;" color="#c0003b" face="Arial" align="center"><b>
       <%=resource.getString("opac.simplesearch.simplesearchtext")%></b></font></div>
<div id="wb_Form1" style="position:absolute;right:3px;top:17px;width:511px;height:301px;z-index:15">
    <html:form  method="post" action="/simple_search">

       <div id="wb_Text3" style="position:absolute;right:140px;top:42px;width:145px;height:18px;z-index:5;" align="right">
<font style="font-size:13px" color="#000000" face="Arial"><b><%=resource.getString("opac.simplesearch.enterwordorphrase")%></b></font></div>
        <div style="position:absolute;right:16px;top:60px;width:97px;height:18px;border:1px #C0C0C0 solid;z-index:0">
<select name="CMBFIELD" size="1" id="CMBFIELD" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option value="any field" selected>ANY FIELD</option>
<option  value="author">AUTHOR</option>
<option value="title">TITLE</option>
<option value="isbn">ISBN</option>
<option value="subject">SUBJECT</option>
<option value="pubplace">PLACE</option>
<option value="publisher">PUBLISHER</option>

</select>
</div>
        <div align="right" style="position:absolute;right:92px;top:5px;width:100px;height:18px;border:1px #C0C0C0 solid;z-index:9">
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

<div style="position:absolute;right:370px;top:9px;width:131px;height:18px;border:1px #C0C0C0 solid;z-index:1">
<select name="CMBDB" size="1" id="CMBDB" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option value="combined" selected>COMBINED</option>
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
<div id="wb_Text1" style="position:absolute;right:16px;top:42px;width:45px;height:16px;z-index:2;" align="right">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.simplesearch.field")%></b></font></div>
<div id="wb_Text2" style="position:absolute;right:300px;top:9px;width:69px;height:16px;z-index:3;" align="right">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.simplesearch.database")%></b></font></div>
<div dir="rtl" id="wb_Text6" style="position:absolute;right:16px;top:5px;width:69px;height:16px;z-index:0;" align="right">
    <font dir="rtl" style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.simplesearch.library")%></b></font></div>
<input type="text" id="TXTPHRASE" style="position:absolute;right:140px;top:63px;width:330px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:4" name="TXTPHRASE">



<input type="submit" id="Button1" name="" value="<%=resource.getString("opac.simplesearch.find")%>" style="position:absolute;left:175px;top:231px;width:68px;height:25px;font-family:Arial;font-size:13px;z-index:6">
<input type="reset" id="Button2" name="" value="<%=resource.getString("opac.simplesearch.clear")%>" style="position:absolute;left:262px;top:231px;width:67px;height:25px;font-family:Arial;font-size:13px;z-index:7">
<div style="position:absolute;right:220px;top:95px;width:80px;height:18px;border:1px #C0C0C0 solid;z-index:8">
<select name="CMBCONN" size="1" id="CMBCONN" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="or">OR</option>
<option value="and">AND</option>
</select>
</div>

<div id="wb_Text4" style="position:absolute;right:140px;top:99px;width:80px;height:16px;z-index:10;" align="right">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.simplesearch.connectas")%></b></font></div>
<div style="position:absolute;right:72px;top:147px;width:118px;height:18px;border:1px #C0C0C0 solid;z-index:11">
<select name="CMBYR" onChange=f() size="1" id="CMBYR" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="all">Select Year</option>
<option value="all">ALL YEARS</option>
<option value="between">BETWEEN</option>
<option value="upto">UPTO</option>
<option value="after">AFTER</option>
</select>
</div>

<div id="wb_Text8" style="position:absolute;right:144px;top:186px;width:23px;height:14px;z-index:12;" align="right">
<font style="font-size:11px" color="#000000" face="Arial"><b>---</b></font></div>
<input type="text" id="TXTYR1" style="position:absolute;right:72px;top:183px;width:62px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:13" name="TXTYR1">
<input type="text" id="TXTYR2" style="position:absolute;right:162px;top:183px;width:62px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:14" name="TXTYR2">
<div id="wb_Text5" style="position:absolute;right:34px;top:121px;width:28px;height:16px;z-index:11;" align="right">
<img src='/LibMS-Struts/images/publishingYear.gif'></div>
<div id="wb_Text5" style="position:absolute;right:380px;top:132px;width:28px;height:16px;z-index:11;" align="right">
<img src='/LibMS-Struts/images/sortby.gif'></div>
<div style="position:absolute;right:304px;top:162px;width:102px;height:18px;border:1px #C0C0C0 solid;z-index:8">
 <select name="CMBSORT" size="1" id="CMBSORT" style="right:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option  value="author">AUTHOR</option>
<option value="title">TITLE</option>
<option value="isbn">ISBN</option>
<option value="subject">SUBJECT</option>
<option value="accessionno">ACCESSION NO</option>
<option value="publisher">PUBLISHER</option>
</select>
</div>
    </html:form>
</div>



     <%}%>




</body>
</html>
