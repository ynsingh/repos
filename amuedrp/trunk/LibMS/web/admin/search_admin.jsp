<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html><head>
<title>Browsing.....</title>
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
document.Form1.action="search_institute.do";
document.Form1.method="post";
document.Form1.target="f1";
document.Form1.submit();
}

</script>
</head>
<body onload="fun()">
    <div id="wb_Text1" style="position: absolute; left: 117px; top: 1px;
width: 650px; height: 19px; background-color: rgb(255, 255, 255);
z-index: 0;" >
<font style="font-size: 16px;" color="#c0003b" face="Arial" align="center"><b>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        Browsing...</b></font></div>
<div id="wb_Form1" style="position: absolute; left: 1px; top: 4px; width: 692px; height: 90px; z-index: 9;">
<form name="Form1" action="search_institute.do" >
<div style="border: 1px solid rgb(0, 85, 0); position: absolute; left: 24px; top: 62px; width: 122px; height: 18px;border:1px #C0C0C0 solid; z-index: 0;">
    <select name="search_by" onChange="fun()" id="search_by" size="1" style="border-width: 0px; left: 0px; top: 0px; width: 100%; height: 100%; font-family: Arial; font-size: 13px;">
<option value="institute_name">Institute Name</option>
<option value="library_name">Library Name</option>
<option value="library_id">Library Id</option>
<option value="city">City</option>


</select>
</div>

<div id="wb_Text1" style="position: absolute; left: 24px; top: 39px; width: 39px; height: 20px; z-index: 2;" align="left">
<font style="font-size: 13px;" color="#000000" face="Arial"><b>Field</b></font></div>
<div id="wb_Text4" style="position: absolute; left: 24px; top: 12px; width: 63px; height: 16px; z-index: 3;" align="left">
<font style="font-size: 13px;" color="#000000" face="Arial"><b>Database</b></font></div>
    <input  name="search_keyword" type="text" id="search_keyword" onkeyup="fun()"  style="border: 1px solid rgb(0, 64, 64); position: absolute; left: 165px; top: 62px; width: 300px; height: 18px; font-family: Courier New; font-size: 13px; z-index: 4;">
<div id="wb_Text3" style="position: absolute; left: 164px; top: 39px; width: 200px; height: 16px; z-index: 5;" align="left">
<font style="font-size: 13px;" color="#000000" face="Arial"><b>Enter Starting Keyword(s)</b></font></div>
<input type="reset" id="Button1" name="clear" value="CLEAR" style="position: absolute; left: 602px; top: 58px; width: 68px; height: 25px; font-family: Arial; font-weight: bold; font-size: 13px; z-index: 6;" >

<div id="wb_Text5" style="position:absolute;left:481px;top:30px;width:28px;height:16px;z-index:11;" align="left">
<img src='/LibMS-Struts/images/sortby.gif'></div>
<div style="position:absolute;left:483px;top:62px;width:102px;height:18px;border:1px #C0C0C0 solid;z-index:8">

    <select name="sort_by" id="sort_by" size="1" onChange="fun()" id="" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family: Arial; font-size:13px;">
<option value="registration_id">Registeration Id</option>
<option  value="library_name">Library Name</option>
<option value="institute_name">Institute Name</option>
<option value="city">City</option>
</select>
</div>
</form>
</div>
<IFRAME  name="f1" src="#" frameborder=0 scrolling="YES" style="position:absolute;color:deepskyblue;top:96px;left:24px;height:370px;width:650px;visibility:true;" id="f1"></IFRAME>
<IFRAME  name="f2" src="#" frameborder=0 scrolling="NO" style="position:absolute;color:deepskyblue;top:96px;left:582px;height:370px;width:650px;visibility:true;" id="f2"></IFRAME>
</body>
</html>