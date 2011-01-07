
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="java.sql.ResultSet"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>
<title>Browsing.... </title>
<%!
    static Integer count=0;
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
%>
<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
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

<script language="javascript">
function fun()
{

document.Form1.action="browse.do";
document.Form1.method="post";
document.Form1.target="f1";
document.Form1.submit();
}

</script>
</head>
<body onload="fun()">
    <%if(page.equals(true)){%>

    <div id="wb_Text1" style="position: absolute; left: 300px; top: 1px;
width: 50px; height: 19px; background-color: rgb(255, 255, 255);
z-index: 0;" >
<font style="font-size: 16px;" color="#c0003b" face="Arial" align="center"><b>

        <%=resource.getString("opac.browse.browsetext")%></b></font></div>
<div id="wb_Form1" style="position: absolute; left: 1px; top: 4px; width: 692px; height: 90px; z-index: 9;">
    <form action="/browse.do"  name="Form1">
<div style="border: 1px solid rgb(0, 85, 0); position: absolute; left: 24px; top: 64px; width: 122px; height: 18px;border:1px #C0C0C0 solid; z-index: 0;">
<select name="CMBFIELD" onChange="fun()" size="1" style="border-width: 0px; left: 0px; top: 0px; width: 100%; height: 100%; font-family: Arial; font-size: 13px;">
<option value="any field" selected>ANY FIELD</option>
<option value="author">AUTHOR</option>
<option value="title">TITLE</option>
<option value="isbn">ISBN</option>
<option value="subject">SUBJECT</option>
<option value="pubplace">PLACE</option>
<option value="publisher">PUBLISHER</option>

</select>
</div>
<div style="border: 1px solid rgb(0, 85, 0); position: absolute; left: 600px; top: 20px; width: 127px; height: 18px;border:1px #C0C0C0 solid; z-index: 1;">
<select name="CMBDB" onChange="fun()" size="1" style="border-width: 0px; left: 0px; top: 0px; width: 100%; height: 100%; font-family: Courier New; font-size: 13px;">
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
<div id="wb_Text1" style="position: absolute; left: 24px; top: 43px; width: 39px; height: 20px; z-index: 2;" align="left">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.browse.field")%></b></font></div>
<div id="wb_Text4" style="position: absolute; left: 535px; top: 20px; width: 63px; height: 16px; z-index: 3;" align="left">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.browse.database")%></b></font></div>
<input  name="TXTTITLE" type="text" onkeyup="fun()"  style="border: 1px solid rgb(0, 64, 64); position: absolute; left: 165px; top: 64px; width: 300px; height: 18px; font-family: Courier New; font-size: 13px; z-index: 4;">
<div id="wb_Text3" style="position: absolute; left: 164px; top: 43px; width: 200px; height: 16px; z-index: 5;" align="left">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.browse.enterstartingkeyword")%></b></font></div>
<input id="Button1" name="clear" value="<%=resource.getString("opac.browse.clear")%>" style="position: absolute; left: 300px; top:250px; width: 68px; height: 25px; font-family: Arial; font-weight: bold; font-size: 13px; z-index: 6;" type="reset">

<div id="wb_Text5" style="position:absolute;left:481px;top:30px;width:28px;height:16px;z-index:11;" align="left">

 <div id="wb_Text6" style="position:absolute;left:55px;top:60px;width:69px;height:16px;z-index:0;" align="left">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.browse.title")%></b></font></div>
<div id="wb_Text5" style="position:absolute;left:55px;top:30px;width:28px;height:16px;z-index:11;" align="left">
<img src='/LibMS-Struts/images/sortby.gif'></div></div>
<div style="position:absolute;left:100px;top:20px;width:100px;height:18px;border:1px #C0C0C0 solid;z-index:9">
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
<div id="wb_Text6" style="position:absolute;left:25px;top:20px;width:85px;height:16px;z-index:0;" align="left">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.browse.library")%></b></font></div>

<div style="position:absolute;left:600px;top:86px;width:102px;height:18px;border:1px #C0C0C0 solid;z-index:8">
 <select name="CMBSORT" size="1" onChange="fun()" id="CMBSORT" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option value="title">TITLE</option>
<option  value="author">AUTHOR</option>
<option value="isbn">ISBN</option>
<option value="subject">SUBJECT</option>
<option value="accessionno">ACCESSION NO</option>
<option value="publisher">PUBLISHER</option>
</select>
</div>

 </form>
</div>
<IFRAME  name="f1" src="#" frameborder=0 scrolling="NO" style="position:absolute;color:deepskyblue;top:96px;left:24px;height:370px;width:550px;visibility:true;" id="f1"></IFRAME>
 <IFRAME  name="f2" src="#" frameborder=0 scrolling="NO" style="position:absolute;color:deepskyblue;top:120px;left:590px;height:370px;width:450px;visibility:true;" id="f2"></IFRAME>

 <%}else{%>

 <div id="wb_Text1" style="position: absolute; right: 300px; top: 1px;
width: 50px; height: 19px; background-color: rgb(255, 255, 255);
z-index: 0;" >
<font style="font-size: 16px;" color="#c0003b" face="Arial" align="right"><b>
<%=resource.getString("opac.browse.browsetext")%></b></font></div>
<div id="wb_Form1" style="position: absolute; right: 1px; top: 4px; width: 692px; height: 90px; z-index: 9;">
    <form action="/browse.do"  name="Form1">
<div style="border: 1px solid rgb(0, 85, 0); position: absolute; right: 24px; top: 62px; width: 122px; height: 18px;border:1px #C0C0C0 solid; z-index: 0;">
<select name="CMBFIELD" onChange="fun()" size="1" style="border-width: 0px; left: 0px; top: 0px; width: 100%; height: 100%; font-family: Arial; font-size: 13px;">
<option value="any field" selected>ANY FIELD</option>
<option value="author">AUTHOR</option>
<option value="title">TITLE</option>
<option value="isbn">ISBN</option>
<option value="subject">SUBJECT</option>
<option value="pubplace">PLACE</option>
<option value="publisher">PUBLISHER</option>

</select>
</div>
<div style="border: 1px solid rgb(0, 85, 0); position: absolute; right: 550px; top: 9px; width: 127px; height: 18px;border:1px #C0C0C0 solid; z-index: 1;">
<select name="CMBDB" onChange="fun()" size="1" style="border-width: 0px; right: 0px; top: 0px; width: 100%; height: 100%; font-family: Courier New; font-size: 13px;">
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
<div id="wb_Text1" style="position: absolute; right: 24px; top: 39px; width: 39px; height: 20px; z-index: 2;" align="right">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.browse.field")%></b></font></div>
<div id="wb_Text4" style="position: absolute; right: 500px; top: 9px; width: 63px; height: 16px; z-index: 3;" align="right">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.browse.database")%></b></font></div>
<input  name="TXTTITLE" type="text" onkeyup="fun()"  style="border: 1px solid rgb(0, 64, 64); position: absolute; right: 165px; top: 62px; width: 300px; height: 18px; font-family: Courier New; font-size: 13px; z-index: 4;">
<div id="wb_Text3" style="position: absolute; right: 164px; top: 39px; width: 270px; height: 16px; z-index: 5;" align="right">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.browse.enterstartingkeyword")%></b></font></div>
<input id="Button1" name="clear" value="<%=resource.getString("opac.browse.clear")%>" style="position: absolute; right: 300px; top:250px; width: 68px; height: 25px; font-family: Arial; font-weight: bold; font-size: 13px; z-index: 6;" type="reset">

<div id="wb_Text5" style="position:absolute;right:481px;top:30px;width:28px;height:16px;z-index:11;" align="right">

   <div id="wb_Text6" style="position:absolute;right:40px;top:35px;width:69px;height:16px;z-index:0;" align="right">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.browse.title")%></b></font></div>
<div id="wb_Text5" style="position:absolute;right:150px;top:8px;width:28px;height:16px;z-index:11;" align="left">
<img src='/LibMS-Struts/images/sortby.gif'></div></div>
<div id="wb_Text6" style="position:absolute;right:30px;top:10px;width:85px;height:16px;z-index:0;" align="right">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.browse.library")%></b></font></div>
    <div style="position:absolute;right:70px;top:10px;width:100px;height:18px;border:1px #C0C0C0 solid;z-index:9">
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
<div style="position:absolute;right:555px;top:66px;width:102px;height:18px;border:1px #C0C0C0 solid;z-index:8">
 <select name="CMBSORT" size="1" onChange="fun()" id="CMBSORT" style="right:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option value="title">TITLE</option>
<option  value="author">AUTHOR</option>
<option value="isbn">ISBN</option>
<option value="subject">SUBJECT</option>
<option value="accessionno">ACCESSION NO</option>
<option value="publisher">PUBLISHER</option>
</select>
</div>

 </form>
</div>

<IFRAME  name="f1" src="#" frameborder=0 scrolling="NO" style="position:absolute;color:deepskyblue;top:96px;left:350px;height:370px;width:500px;visibility:true;" id="f1"></IFRAME>
 <IFRAME  name="f2" src="#" frameborder=0 scrolling="NO" style="position:absolute;color:deepskyblue;top:90px;left:0px;height:400px;width:350px;visibility:true;" id="f2"></IFRAME>


 <%}%>
 
</body>
</html>