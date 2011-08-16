

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%-- 
Document   : log
Created on : Jun 2, 2011, 3:19:14 PM
Author     : imran
--%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">


<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Log File</title>

</head>
<script type="text/javascript" language="javascript">

function set(x,y)
{

    document.getElementById(x).value=y;
alert(document.getElementById(x).value);

}

function printlog()
{

 window.open("<%=request.getContextPath()%>/log.do");
   
}

function printlog1()
{
    window.location="<%=request.getContextPath()%>/showlog.do";
    return false;
}
function fun()
{
document.Form1.action="logGrid.do";
document.Form1.method="post";
document.Form1.target="f1";
document.Form1.submit();
}
</script>
<body>
<html:form method="post"  action="/userlog" styleId="form1" >

<table  align="left" width="100%" class="datagrid"  style="border:solid 1px #e0e8f5" >
<tr bgcolor="#7697BC" >
<td  width="100%" align="center" colspan="2" ><font color="white"><b>Generation Of Log</b></font></td></tr>

</table>
<table align="left" width="100%" class="datagrid"  style="border:solid 1px #e0e8f5" >
<tr>
    <td colspan="2"><input type="checkbox" onclick="set('Checkbox1','login_id')" id="Checkbox1" name="login_id" value="" tabindex="17" >&nbsp;&nbsp;<b>Login User Id</b>&nbsp;</td>
<td colspan="2"><input type="checkbox" onclick="set('Checkbox2','date')" id="Checkbox2" name="date" value="" tabindex="17" >&nbsp;&nbsp;<b>Logger Date</b>&nbsp;</td>
</tr>

<tr>
<td colspan="2"><input type="checkbox" onclick="set('Checkbox3','time')" id="Checkbox3" name="time" value="" tabindex="17" >&nbsp;&nbsp;<b>Login Time</b>&nbsp;</td>
<td colspan="2"><input type="checkbox" onclick="set('Checkbox4','class_name')" id="Checkbox4" name="class_name" value="" tabindex="17" >&nbsp;&nbsp;<b>Class Name</b>&nbsp;</td>
</tr>
<tr>
<td colspan="2"><input type="checkbox" onclick="set('Checkbox5','url')" id="Checkbox5" name="url" value="" tabindex="17" >&nbsp;&nbsp;<b>URL</b>&nbsp;</td>
<td colspan="2"><input type="checkbox" onclick="set('Checkbox6','activity')" id="Checkbox6" name="activity" value="" tabindex="17" >&nbsp;&nbsp;<b>Activity Performed</b>&nbsp;</td>
</tr>
<tr>
<td colspan="2"><input type="checkbox" onclick="set('Checkbox7','action_result')" id="Checkbox7" name="action_result" value="" tabindex="17" >&nbsp;&nbsp;<b>User Action Result</b>&nbsp;</td>

</tr>
<tr>
<td colspan="2"><input type="submit" id="Button1"  value="Generate Log"><input type="button"  onclick="printlog1();" value="View Log"><input type="submit" id="Button1" onclick="printlog();"  value="Show Pictorial Analysis"></td>
<td colspan="2"></td>
<td colspan="2"></td>

</tr>
<%

String msg=(String)request.getAttribute("msg");
if(msg!=null)
{%><%=msg%><%}%>

<%

String select[] = request.getParameterValues("id");
if (select != null && select.length != 0) {
out.println("You have selected: ");
for (int i = 0; i < select.length; i++) {
out.println(select[i]);
}
}
%>

</table>



</html:form>
</body>

