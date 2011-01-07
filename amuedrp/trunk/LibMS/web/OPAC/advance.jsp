<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>
<%@page import="java.sql.ResultSet"%>
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Faraz Hasan" content="MCA,AMU">
<title>Advance Search...</title>

<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
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

<body>
     <%if(page.equals(true)){%>

    <div id="wb_Text1" style="position: absolute; left: 270px; top: 0px;
width: 500px; height: 19px; background-color: rgb(255, 255, 255);
z-index: 0;" >
<font style="font-size: 16px;" color="#c0003b" face="Arial" align="center"><b>
            <%=resource.getString("opac.advance.advancesearchtext")%></b></font></div>
<div id="wb_Form1" style="position: absolute; left: 7px; top: 25px; width: 875px; height: 370px; z-index: 42;">
<html:form  method="post" action="/OPAC/advance_search">
<div style="border: 1px solid rgb(0, 85, 0); position: absolute; left: 50px; top: 35px; width: 104px; height: 18px; z-index: 0;">
<select name="CMBFIELD1" size="1" style="border-width: 0px; left: 0px; top: 0px; width: 100%; height: 100%; font-family: Arial; font-size: 13px;">
<option selected value="title">ANY FIELD</option>
<option value="author">AUTHOR</option>
<option value="title">TITLE</option>
<option value="isbn">ISBN</option>
<option value="subject">SUBJECT</option>
<option value="pubplace">PLACE</option>
<option value="publisher">PUBLISHER</option>
</select>
</div>

<div style="border: 1px solid rgb(0, 85, 0); position: absolute; left: 50px; top: 91px; width: 104px; height: 18px; z-index: 27;">
<select name="CMBFIELD2" size="1"  style="border-width: 0px; left: 0px; top: 0px; width: 100%; height: 100%; font-family: Arial; font-size: 13px;">
<option selected value="author">ANY FIELD</option>
<option value="author">AUTHOR</option>
<option value="title">TITLE</option>
<option value="isbn">ISBN</option>
<option value="subject">SUBJECT</option>
<option value="pubplace">PLACE</option>
<option value="publisher">PUBLISHER</option>
</select>
</div>

<div style="border: 1px solid rgb(0, 85, 0); position: absolute; left: 50px; top: 159px; width: 104px; height: 18px; z-index: 26;">
<select name="CMBFIELD3" size="1" style="border-width: 0px; left: 0px; top: 0px; width: 100%; height: 100%; font-family: Arial; font-size: 13px;">
<option selected value="subject">ANY FIELD</option>
<option value="author">AUTHOR</option>
<option value="title">TITLE</option>
<option value="isbn">ISBN</option>
<option value="subject">SUBJECT</option>
<option value="pubplace">PLACE</option>
<option value="publisher">PUBLISHER</option>
</select>
</div>

<div id="wb_Text1" style="position: absolute; left: 4px; top: 38px; width: 39px; height: 16px; z-index: 1;" align="left">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.advance.field")%></b></font></div>

<input name="TXTPHRASE1" style="border: 1px solid rgb(0, 64, 64); position: absolute; left: 245px; top: 34px; width: 296px; height: 18px; font-family: Courier New; font-size: 13px; z-index: 2;" type="text">
<input name="TXTPHRASE2" style="border: 1px solid rgb(0, 64, 64); position: absolute; left: 245px; top: 93px; width: 296px; height: 18px; font-family: Courier New; font-size: 13px; z-index: 24;"type="text">
<input name="TXTPHRASE3" style="border: 1px solid rgb(0, 64, 64); position: absolute; left: 245px; top: 158px; width: 296px; height: 18px; font-family: Courier New; font-size: 13px; z-index: 25;"type="text">
<input name="TXTYR2" style="border: 1px dotted rgb(139, 0, 0); position: absolute; left: 217px; top: 251px; width: 52px; height: 18px; font-family: Courier New; font-size: 13px; z-index: 11;" type="text">
<input name="TXTYR1" style="border: 1px dotted rgb(139, 0, 0); position: absolute; left: 130px; top: 252px; width: 52px; height: 18px; font-family: Courier New; font-size: 13px; z-index: 12;" type="text">


<div id="wb_Text7" style="position: absolute; left: 190px; top: 249px; width: 21px; height: 14px; z-index: 6;" align="left">---</div>
<input id="Button2" name="" value="<%=resource.getString("opac.advance.find")%>" style="position: absolute; left: 250px; top: 280px; width: 68px; height: 25px; font-family: Arial; font-weight: bold; font-size: 13px; z-index: 15;" type="submit">
<input id="Button1" name="" value="<%=resource.getString("opac.advance.clear")%>" style="position: absolute; left: 360px; top: 280px; width: 67px; height: 25px; font-family: Arial; font-weight: bold; font-size: 13px; z-index: 23;" type="reset">

<div style="border: 1px solid rgb(0, 85, 0); position: absolute; left: 326px; top: 66px; width: 61px; height: 18px; z-index: 35;">
  <select name="CMB1" size="1" style="border-width: 0px; left: 0px; top: 0px; width: 100%; height: 100%; font-family: Courier New; font-size: 13px;">
    <option value="or">OR</option>
    <option value="and">AND</option>
  </select>
</div>

<div style="border: 1px solid rgb(0, 85, 0); position: absolute; left: 326px; top: 126px; width: 61px; height: 18px; z-index: 19;">
  <select name="CMB2" size="1"  style="border-width: 0px; left: 0px; top: 0px; width: 100%; height: 100%; font-family: Courier New; font-size: 13px;">
    <option value="or">OR</option>
    <option value="and">AND</option>
  </select>
</div>

<div style="border: 1px solid rgb(0, 85, 0); position: absolute; left: 326px; top: 186px; width: 61px; height: 18px; z-index: 19;">
  <select name="CMB3" size="1"  style="border-width: 0px; left: 0px; top: 0px; width: 100%; height: 100%; font-family: Courier New; font-size: 13px;">
    <option value="or">OR</option>
    <option value="and">AND</option>
  </select>
</div>

<div style="border: 1px solid rgb(0, 85, 0); position: absolute; left: 622px; top: 94px; width: 60px; height: 18px; z-index: 31;">
<select name="CMBF2" size="1" style="border-width: 0px; left: 0px; top: 0px; width: 100%; height: 100%; font-family: Courier New; font-size: 13px;">
<option value="or">OR</option>
<option value="and">AND</option>
</select>
</div>

<div style="border: 1px solid rgb(0, 85, 0); position: absolute; left: 622px; top: 156px; width: 60px; height: 18px; z-index: 33;">
<select name="CMBF3" size="1"  style="border-width: 0px; left: 0px; top: 0px; width: 100%; height: 100%; font-family: Courier New; font-size: 13px;">
<option value="or">OR</option>
<option value="and">AND</option>
</select>
</div>

<div style="border: 1px solid rgb(0, 85, 0); position: absolute; left: 622px; top: 33px; width: 60px; height: 18px; z-index: 30;">
<select name="CMBF1" size="1" style="border-width: 0px; left: 0px; top: 0px; width: 100%; height: 100%; font-family: Courier New; font-size: 13px;">
<option value="or">OR</option>
<option value="and">AND</option>
</select>
</div>



<div id="wb_Text11" style="position: absolute; left: 546px; top: 36px; width: 76px; height: 16px; z-index: 20;" align="left">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.advance.connectas")%></b></font></div>
<div id="wb_Text14" style="position: absolute; left: 4px; top: 94px; width: 39px; height: 16px; z-index: 28;" align="left">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.advance.field")%></b></font></div>
<div id="wb_Text15" style="position: absolute; left: 4px; top: 163px; width: 39px; height: 16px; z-index: 29;" align="left">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.advance.field")%></b></font></div>
<div id="wb_Text16" style="position: absolute; left: 546px; top: 96px; width: 76px; height: 16px; z-index: 32;" align="left">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.advance.connectas")%></b></font></div>
<div id="wb_Text17" style="position: absolute; left: 545px; top: 160px; width: 76px; height: 16px; z-index: 34;" align="left">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.advance.connectas")%></b></font></div>
<div id="wb_Text4" style="position: absolute; left: 546px; top: 6px; width: 65px; height: 16px; z-index: 36;" align="left">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.advance.database")%></b></font></div>
<div id="wb_Text12" style="position: absolute; left: 159px; top: 95px; width: 95px; height: 14px; z-index: 38;" align="left">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.advance.enterphrase")%></b></font></div>
<div id="wb_Text13" style="position: absolute; left: 159px; top: 161px; width: 92px; height: 16px; z-index: 39;" align="left">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.advance.enterphrase")%></b></font></div>
<div id="wb_Text3" style="position: absolute; left: 157px; top: 36px; width: 91px; height: 16px; z-index: 40;" align="left">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.advance.enterphrase")%></b></font></div>
<div style="border: 1px solid rgb(0, 85, 0); position: absolute; left:622px; top: 5px; width: 127px; height: 18px; z-index: 37;">
<select name="CMBDB" size="1" style="border-width: 0px; left: 0px; top: 0px; width: 100%; height: 100%; font-family: Courier New; font-size: 13px;">
    <option  selected value="combined">COMBINED</option>
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
<%
System.out.println(resource.getString("opac.advance.publicationyear"));%>
<div id="wb_Text13" style="position: absolute; left: 6px; top: 222px; width: 130px; height: 16px; z-index: 29;" align="left">
 <font style="font-size: 13px;" color="#000000" face="Arial"><b><%= resource.getString("opac.advance.publicationyear")%></b></font>
</div>
 
<div style="border: 1px solid rgb(0, 85, 0); position: absolute; left: 130px; top: 220px; width: 120px; height: 19px; z-index: 19;">
<select name="CMBYR" onChange="f()" size="1" style="border-width: 0px; left: 0px; top: 0px; width: 100%; height: 100%; font-family: Courier New; font-size: 13px;">
    <option value="all">Select Year</option>
    <option value="all">ALL YEARS</option>
    <option value="between">BETWEEN</option>
    <option value="upto">UPTO</option>
<option value="after">AFTER</option>
</select>
</div>
<div id="wb_Text2" style="position:absolute;left:4px;top:6px;width:67px;height:16px;z-index:2;" align="left">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.advance.library")%></b></font></div>
<div style="position:absolute;left:50px;top:6px;width:100px;height:18px;border:1px #C0C0C0 solid;z-index:9">
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
</html:form>
</div>

     <%}else{%>

     <div  align="right"id="wb_Text1" style="position: absolute; right: 275px; top: 0px;
width: 500px; height: 19px; background-color: rgb(255, 255, 255);
z-index: 0;" >
<font style="font-size: 16px;" color="#c0003b" face="Arial" align="center"><b>
             <%=resource.getString("opac.advance.advancesearchtext")%></b></font></div>
<div id="wb_Form1" style="position: absolute; right: 7px; top: 25px; width: 875px; height: 370px; z-index: 42;">
<html:form  method="post" action="/OPAC/advance_search">
<div style="border: 1px solid rgb(0, 85, 0); position: absolute; right: 45px; top: 35px; width: 104px; height: 18px; z-index: 0;">
<select name="CMBFIELD1" size="1" style="border-width: 0px; left: 0px; top: 0px; width: 100%; height: 100%; font-family: Arial; font-size: 13px;">
<option selected value="title">ANY FIELD</option>
<option value="author">AUTHOR</option>
<option value="title">TITLE</option>
<option value="isbn">ISBN</option>
<option value="subject">SUBJECT</option>
<option value="pubplace">PLACE</option>
<option value="publisher">PUBLISHER</option>
</select>
</div>

<div style="border: 1px solid rgb(0, 85, 0); position: absolute; right: 45px; top: 91px; width: 104px; height: 18px; z-index: 27;">
<select name="CMBFIELD2" size="1"  style="border-width: 0px; left: 0px; top: 0px; width: 100%; height: 100%; font-family: Arial; font-size: 13px;">
<option selected value="author">ANY FIELD</option>
<option value="author">AUTHOR</option>
<option value="title">TITLE</option>
<option value="isbn">ISBN</option>
<option value="subject">SUBJECT</option>
<option value="pubplace">PLACE</option>
<option value="publisher">PUBLISHER</option>
</select>
</div>

<div style="border: 1px solid rgb(0, 85, 0); position: absolute; right: 45px; top: 159px; width: 104px; height: 18px; z-index: 26;">
<select name="CMBFIELD3" size="1" style="border-width: 0px; left: 0px; top: 0px; width: 100%; height: 100%; font-family: Arial; font-size: 13px;">
<option selected value="subject">ANY FIELD</option>
<option value="author">AUTHOR</option>
<option value="title">TITLE</option>
<option value="isbn">ISBN</option>
<option value="subject">SUBJECT</option>
<option value="pubplace">PLACE</option>
<option value="publisher">PUBLISHER</option>
</select>
</div>

<div id="wb_Text1" style="position: absolute; right: 4px; top: 38px; width: 39px; height: 16px; z-index: 1;" align="left">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.advance.field")%></b></font></div>

<input name="TXTPHRASE1" style="border: 1px solid rgb(0, 64, 64); position: absolute; right: 245px; top: 34px; width: 296px; height: 18px; font-family: Courier New; font-size: 13px; z-index: 2;"     type="text">
<input name="TXTPHRASE2" style="border: 1px solid rgb(0, 64, 64); position: absolute; right: 245px; top: 93px; width: 296px; height: 18px; font-family: Courier New; font-size: 13px; z-index: 24;"    type="text">
<input name="TXTPHRASE3" style="border: 1px solid rgb(0, 64, 64); position: absolute; right: 245px; top: 158px; width: 296px; height: 18px; font-family: Courier New; font-size: 13px; z-index: 25;"   type="text">
<input name="TXTYR2" style="border: 1px dotted rgb(139, 0, 0); position: absolute; right: 180px; top: 251px; width: 52px; height: 18px; font-family: Courier New; font-size: 13px; z-index: 11;"  type="text">
<input name="TXTYR1" style="border: 1px dotted rgb(139, 0, 0); position: absolute; right: 90px; top: 252px; width: 52px; height: 18px; font-family: Courier New; font-size: 13px; z-index: 12;"  type="text">


<div id="wb_Text7" style="position: absolute; right: 150px; top: 249px; width: 21px; height: 14px; z-index: 6;" align="right">---</div>
<input id="Button2" name="" value="<%=resource.getString("opac.advance.find")%>" style="position: absolute; right: 250px; top: 280px; width: 68px; height: 25px; font-family: Arial; font-weight: bold; font-size: 13px; z-index: 15;" type="submit">
<input id="Button1" name="" value="<%=resource.getString("opac.advance.clear")%>" style="position: absolute; right: 360px; top: 280px; width: 67px; height: 25px; font-family: Arial; font-weight: bold; font-size: 13px; z-index: 23;" type="reset">

<div style="border: 1px solid rgb(0, 85, 0); position: absolute; right: 326px; top: 66px; width: 61px; height: 18px; z-index: 35;">
  <select name="CMB1" size="1" style="border-width: 0px; right: 0px; top: 0px; width: 100%; height: 100%; font-family: Courier New; font-size: 13px;">
    <option value="or">OR</option>
    <option value="and">AND</option>
  </select>
</div>

<div style="border: 1px solid rgb(0, 85, 0); position: absolute; right: 326px; top: 126px; width: 61px; height: 18px; z-index: 19;">
  <select name="CMB2" size="1"  style="border-width: 0px; right: 0px; top: 0px; width: 100%; height: 100%; font-family: Courier New; font-size: 13px;">
    <option value="or">OR</option>
    <option value="and">AND</option>
  </select>
</div>

<div style="border: 1px solid rgb(0, 85, 0); position: absolute; right: 326px; top: 186px; width: 61px; height: 18px; z-index: 19;">
  <select name="CMB3" size="1"  style="border-width: 0px; right: 0px; top: 0px; width: 100%; height: 100%; font-family: Courier New; font-size: 13px;">
    <option value="or">OR</option>
    <option value="and">AND</option>
  </select>
</div>

<div style="border: 1px solid rgb(0, 85, 0); position: absolute;right: 622px; top: 94px; width: 60px; height: 18px; z-index: 31;">
<select name="CMBF2" size="1" style="border-width: 0px; right: 0px; top: 0px; width: 100%; height: 100%; font-family: Courier New; font-size: 13px;">
<option value="or">OR</option>
<option value="and">AND</option>
</select>
</div>

<div style="border: 1px solid rgb(0, 85, 0); position: absolute;right: 622px; top: 156px; width: 60px; height: 18px; z-index: 33;">
<select name="CMBF3" size="1"  style="border-width: 0px; right: 0px; top: 0px; width: 100%; height: 100%; font-family: Courier New; font-size: 13px;">
<option value="or">OR</option>
<option value="and">AND</option>
</select>
</div>

<div style="border: 1px solid rgb(0, 85, 0); position: absolute; right: 622px; top: 33px; width: 60px; height: 18px; z-index: 30;">
<select name="CMBF1" size="1" style="border-width: 0px; right: 0px; top: 0px; width: 100%; height: 100%; font-family: Courier New; font-size: 13px;">
<option value="or">OR</option>
<option value="and">AND</option>
</select>
</div>



<div id="wb_Text11" style="position: absolute; right: 546px; top: 36px; width: 76px; height: 16px; z-index: 20;" align="right">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.advance.connectas")%></b></font></div>
<div id="wb_Text14" style="position: absolute; right: 4px; top: 94px; width: 39px; height: 16px; z-index: 28;" align="right">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.advance.field")%></b></font></div>
<div id="wb_Text15" style="position: absolute; right: 4px; top: 163px; width: 39px; height: 16px; z-index: 29;" align="right">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.advance.field")%></b></font></div>
<div id="wb_Text16" style="position: absolute; right: 546px; top: 96px; width: 76px; height: 16px; z-index: 32;" align="right">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.advance.connectas")%></b></font></div>
<div id="wb_Text17" style="position: absolute; right: 545px; top: 160px; width: 76px; height: 16px; z-index: 34;" align="right">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.advance.connectas")%></b></font></div>
<div id="wb_Text4" style="position: absolute; right: 546px; top: 6px; width: 65px; height: 16px; z-index: 36;" align="right">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.advance.database")%></b></font></div>
<div id="wb_Text12" style="position: absolute;right: 159px; top: 95px; width: 95px; height: 14px; z-index: 38;" align="right">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.advance.enterphrase")%></b></font></div>
<div id="wb_Text13" style="position: absolute; right: 159px; top: 161px; width: 92px; height: 16px; z-index: 39;" align="right">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.advance.enterphrase")%></b></font></div>
<div id="wb_Text3" style="position: absolute; right: 157px; top: 36px; width: 91px; height: 16px; z-index: 40;" align="right">
<font style="font-size: 13px;" color="#000000" face="Arial"><b><%=resource.getString("opac.advance.enterphrase")%></b></font></div>
<div style="border: 1px solid rgb(0, 85, 0); position: absolute; right:622px; top: 5px; width: 127px; height: 18px; z-index: 37;">
<select name="CMBDB" size="1" style="border-width: 0px; left: 0px; top: 0px; width: 100%; height: 100%; font-family: Courier New; font-size: 13px;">
    <option  selected value="combined">COMBINED</option>
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

<div id="wb_Text13" style="position: absolute;right: 4px; top: 222px; width: 130px; height: 16px; z-index: 29;" align="right">
 <font style="font-size: 13px;" color="#000000" face="Arial"><b>:<%=resource.getString("opac.advance.publicationyear")%></b></font>
</div>

<div style="border: 1px solid rgb(0, 85, 0); position: absolute; right: 90px; top: 220px; width: 120px; height: 19px; z-index: 19;">
<select name="CMBYR" onChange="f()" size="1" style="border-width: 0px; left: 0px; top: 0px; width: 100%; height: 100%; font-family: Courier New; font-size: 13px;">
    <option value="all">Select Year</option>
    <option value="all">ALL YEARS</option>
    <option value="between">BETWEEN</option>
    <option value="upto">UPTO</option>
<option value="after">AFTER</option>
</select>
</div>
<div id="wb_Text2" style="position:absolute;right:4px;top:6px;width:67px;height:16px;z-index:2;" align="right">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b><%=resource.getString("opac.advance.library")%></b></font></div>
<div style="position:absolute;right:45px;top:6px;width:100px;height:18px;border:1px #C0C0C0 solid;z-index:9">
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
</html:form>
</div>

     <%}%>
</body>
</html>
