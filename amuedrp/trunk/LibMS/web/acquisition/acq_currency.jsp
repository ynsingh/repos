<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Staff ID
-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<//jsp:include page="/admin/header.jsp"/>
<html>
<head>
       <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Budget </title>
 <script language="javascript" type="text/javascript">


function check1()
{
    if(document.getElementById('conid').value=="")
    {
        alert("Enter Conversion Id...");
        document.getElementById('conid').focus();
        return false;
    }
  }
  function quit()
  {

   //   window.location="<%=request.getContextPath()%>/admin/main.jsp";
      return false;
  }
    </script>
</head>
<body>

    <html:form  onsubmit="return check1()" action="/currency">

<div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
    <table border="1" class="table" width="600px" height="200px" align="center">
                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Currency Conversion</td></tr>
                <tr><td valign="top" align="center"> <br/>
                <table cellspacing="10px">
                    <tr><td rowspan="5" class="txt2">Enter Conversion ID:<br><br>
                    <input type="text" id="conid" name="conid" value=""/>
                    </td><td width="150px" align="center"> <input type="submit" class="btn" id="Button1" name="button" value="New" /></td></tr>
                    <tr><td width="150px" align="center"><input type="submit" id="Button2" class="btn" name="button" value="Update"  /></td></tr>
                    <tr><td width="150px" align="center"><input type="submit" id="Button2" class="btn" name="button" value="Delete"  /></td></tr>
                    <!--  <tr><td width="150px" align="center"><input type="submit" id="Button3" name="button" value="View" class="btn"  /></td></tr>-->
                    <tr><td width="150px" align="center"><input type="submit" id="Button4" name="button" value="View" class="btn" /></td></tr>
                    <tr><td width="150px" align="center"><input type="button" id="Button5" name="button" value="Back" class="btn" onclick="return quit()"/></td></tr>
                </table>
</td></tr></table>
        </div>
</html:form>
</body>
</html>