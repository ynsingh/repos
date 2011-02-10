


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%
String library_id=(String)session.getAttribute("library_id");
String msg1=(String)request.getAttribute("msg1");
String msg2=(String)request.getAttribute("msg2");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Free Gifts Entry Form</title>
        <link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
        <link rel="stylesheet" href="/LibMS-Struts/css/formstyle.css"/>
    </head>
    <body>

    <%  if(msg1!=null)
    {%>
    &nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:12px;font-weight:bold;color:blue; position: absolute; top: 130px;" ><%=msg1%></span>
<%}%>
    <%  if(msg2!=null)
    {%>
    &nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:12px;font-weight:bold;color:red; position: absolute; top: 130px;" ><%=msg2%></span>
<%}%>
<html:form action="/MarcEntryAction" method="post"  style="position:absolute; left:10px; top:140px;">

<html:hidden property="library_id" name="MarcTagEntryActionForm" value="<%=library_id%>"/>
<table width="950" border="0"  cellpadding="1">
    <tr><td colspan="4" align="center"><span class="headerStyle">&nbsp;&nbsp;&nbsp;MARC Tag Entry</span><br><br><br></td></tr>
<tr>
    <td width="250" align="right" class="txtStyle"><strong>MARC Tag No<a class="star">*</a>:</strong> </td>
    <td width="200" height="20px;"><html:text property="tagno" name="MarcTagEntryActionForm"  styleClass="textBoxWidth"/></td>
      
</tr>
  <tr><td colspan="5" height="5px"></td></tr>

<tr>
    <td width="250" align="right" class="txtStyle"><strong>MARC Tag Name<a class="star">*</a>:</strong> </td>
    <td width="200" height="20px;"><html:text property="tagname" name="MarcTagEntryActionForm"  styleClass="textBoxWidth"/></td>

</tr>

 
</table>
        </html:form>
 </body>
</html>

        