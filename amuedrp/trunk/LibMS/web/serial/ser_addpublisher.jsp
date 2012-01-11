<%--
    Document   : cat_biblio_entry
    Created on : Jan 13, 2011, 12:02:47 PM
    Author     : Client
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>
<%! boolean read=false;%>
<%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
String button=(String)request.getAttribute("button");
String lan_id=(String)request.getAttribute("pub_id");
String lan_name=(String)request.getAttribute("lan_name");
//request.setAttribute("back", request.getAttribute("back"));

if (button.equals("View")||button.equals("Delete"))
read=true;

else
    read=false;
String msg1=(String) request.getAttribute("msg1");
%>
<script type="text/javascript">

function check1()
{
    if(document.getElementById('pub_name').value=="")
    {
        alert("Enter Publisher Name");

        document.getElementById('pub_name').focus();

        return false;
    }

    if(document.getElementById('pub_add').value=="")
    {
        alert("Enter Publisher Address");

        document.getElementById('pub_add').focus();

        return false;
    }

    if(document.getElementById('phone').value=="")
    {
        alert("Enter Publisher Phone");

        document.getElementById('phone').focus();

        return false;
    }


}

function confirm1()
{
var answer = confirm ("Do you want to delete record?")
if (answer!=true)
    {
        document.getElementById('button1').focus();
        return false;
    }
   else{ return true;}
}
function send()
{
    window.location="<%=request.getContextPath()%>/serial/ser_publisher.jsp";
    return false;
}
</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="common" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
    </head>
    <body>


   <%if(msg1!=null){%>   <span style=" position:absolute; top: 120px; font-size:12px;font-weight:bold;color:red;" ><%=msg1%></span>  <%}%>
   <div
   style="  top:150px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
   <html:form method="post" action="/add_pub"  onsubmit="return check1()" >
       <table border="1" class="table" align="center">
                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" ><b>Publisher Detail Entry</b></td></tr>
                <tr><td>
                        <table width="400px" border="0" cellspacing="4" cellpadding="1" align="left">
                        <tr>


                        </tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr>
    <td align="left" class="txtStyle"><strong>Serial Language ID</strong></td>
    <td><html:text readonly="true" property="pub_id" name="SerPublisherActionForm" styleClass="textBoxWidth" /></td>
</tr>
  <tr><td colspan="5" height="5px"></td>
</tr>
<tr>
    <td width="150" align="left" class="txtStyle"><strong>Publisher Name<a class="star">*</a></strong> </td>
    <td><html:text readonly="<%=read%>"  property="pub_name" styleClass="textBoxWidth" name="SerPublisherActionForm" styleId="pub_name" />
    </td>
    <td> <input type="hidden" name="lang_id" />
    </td>
</tr>
  <tr><td colspan="5" height="5px"></td>
</tr>
<tr>
    <td width="150" align="left" class="txtStyle"><strong>Publisher Address<a class="star">*</a></strong> </td>
    <td><html:text readonly="<%=read%>"  property="pub_add" styleClass="textBoxWidth" name="SerPublisherActionForm" styleId="pub_add" />
    </td>

</tr>
  <tr><td colspan="5" height="5px"></td>
</tr>
<tr>
    <td width="150" align="left" class="txtStyle"><strong>City</strong> </td>
    <td><html:text readonly="<%=read%>"  property="city" styleClass="textBoxWidth" name="SerPublisherActionForm" styleId="city" />
    </td>

</tr>
  <tr><td colspan="5" height="5px"></td>
</tr>

<tr>
    <td width="150" align="left" class="txtStyle"><strong>State</strong> </td>
    <td><html:text readonly="<%=read%>"  property="state" styleClass="textBoxWidth" name="SerPublisherActionForm" styleId="state" />
    </td>

</tr>
  <tr><td colspan="5" height="5px"></td>
</tr>

<tr>
    <td width="150" align="left" class="txtStyle"><strong>Country</strong> </td>
    <td><html:text readonly="<%=read%>"  property="country" styleClass="textBoxWidth" name="SerPublisherActionForm" styleId="country" />
    </td>

</tr>

<tr><td colspan="5" height="5px"></td>
</tr>
<tr>
    <td width="150" align="left" class="txtStyle"><strong>Phone<a class="star">*</a></strong> </td>
    <td><html:text readonly="<%=read%>"  property="phone" styleClass="textBoxWidth" name="SerPublisherActionForm" styleId="phone" />
    </td>

</tr>

<tr>
    <td width="150" align="left" class="txtStyle"><strong>Email</strong> </td>
    <td><html:text readonly="<%=read%>"  property="email" styleClass="textBoxWidth" name="SerPublisherActionForm" styleId="email" />
    </td>

</tr>
  <tr><td colspan="5" height="5px"></td>
</tr>

<tr>
    <td width="150" align="left" class="txtStyle"><strong>Fax</strong> </td>
    <td><html:text readonly="<%=read%>"  property="fax" styleClass="textBoxWidth" name="SerPublisherActionForm" styleId="fax" />
    </td>

</tr>
  <tr><td colspan="5" height="5px"></td>
</tr>

<tr>
    <td width="150" align="left" class="txtStyle"><strong>Publisher Url</strong> </td>
    <td><html:text readonly="<%=read%>"  property="pub_url" styleClass="textBoxWidth" name="SerPublisherActionForm" styleId="pub_url" />
    </td>

</tr>

  <tr><td colspan="5" height="5px"></td>
</tr>

<tr><td></td><td></td></tr>
<tr><td colspan="5" height="10px"></td>
</tr>

<tr><td colspan="5" height="10px"></td>
</tr>
<tr>
<td align="center" colspan="5">
    <%if(button.equals("Update")){%>
    <input id="button1"  name="button" type="submit" value="<%=button%>"/>
    &nbsp;&nbsp;&nbsp;<input name="button" type="submit" value="Back" onclick="return send()" />
    <%}else if(button.equals("Delete")){%>
    <input id="button1"  name="button" type="submit" onClick="return confirm1()" value="<%=button%>"  />
    &nbsp;&nbsp;&nbsp;<input name="button" type="submit" onclick="return send()"  value="Back" />
   <%}else if(button.equals("Add")){%>
    <input id="button1"  name="button" type="submit" value="Submit" />
    &nbsp;&nbsp;&nbsp;<input name="button" type="submit" value="Back" onclick="return send()" />
    <%}else{%>
    <input  name="button" type="submit" value="Back" onclick="return send()" /><%}%>
	</td>
</tr><tr><td colspan="5" height="5px"></td>
</tr>
</table>
</td></tr> </table>

  </html:form>
       </div>
    </body>
</html>
