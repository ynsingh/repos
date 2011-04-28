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
if (button.equals("View")||button.equals("Delete"))
read=true;

else
    read=false;
String msg1=(String) request.getAttribute("msg1");
%>
<script type="text/javascript">

function check1()
{
   
    if(document.getElementById('document_category_name').value=="")
    {
        alert("Enter document category name");

        document.getElementById('document_category_name').focus();

        return false;
    }
 if(document.getElementById('issue_check').value=="Select")
    {
        alert("Select Issue Type");

        document.getElementById('issue_check').focus();

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
}
function send()
{
    window.location="<%=request.getContextPath()%>/systemsetup/document_category.jsp";
    return false;
}
</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bibliographic Detail Entry Form</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
    </head>
    <body>
   <%if(msg1!=null){%>   <span style=" position:absolute; top: 120px; font-size:12px;font-weight:bold;color:red;" ><%=msg1%></span>  <%}%>
   <div
   style="  top:120px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">


   <html:form method="post" action="/document_cat_manage"  onsubmit="return check1()" >
       <table border="1" class="table"  align="center">
                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" ><b>Document Category Entry</b></td></tr>
                 
                <tr><td>
                        <table width="500" border="0" cellspacing="4" cellpadding="1" align="left">
                       
                        <html:hidden property="library_id" name="DocumentCategoryActionForm" value="<%=library_id%>" />
                        <html:hidden property="sub_library_id" name="DocumentCategoryActionForm" value="<%=sub_library_id%>" /><td></td>
                       
<tr><td height="5px" colspan="4" ></td></tr>
<tr>
    <td align="left" class="txtStyle"><strong>Document Category Id</strong></td>
    <td><html:text readonly="true" property="document_category_id"  name="DocumentCategoryActionForm" styleClass="textBoxWidth" /></td>
</tr>
   <tr><td height="5px" colspan="4" ></td></tr>

<tr>
    <td width="150" align="left" class="txtStyle"><strong>Document Category Name<a class="star">*</a>:</strong> </td>
    <td><html:text readonly="<%=read%>"  property="document_category_name" name="DocumentCategoryActionForm" styleClass="textBoxWidth" styleId="document_category_name" />
    </td>
  </tr>
 
 <tr><td height="5px" colspan="4" ></td></tr>
  <tr>
    <td align="left" class="txtStyle"><strong>Issue Check</strong></td>
    <td>
        <%if(button.equals("Update") || button.equals("Add")){%>
  <html:select  property="issue_check" name="DocumentCategoryActionForm" styleId="issue_check"   styleClass="textBoxWidth">
            <html:option value="Select">Select</html:option>
            <html:option value="Issuable">Issuable</html:option>
            <html:option value="NotIssuable">NonIssuable</html:option>
        </html:select>

    <%}else {%>
    <input type="hidden" name="issue_check" styleId="issue_check"   styleClass="textBoxWidth"/>
    <html:select  property="issue_check" name="DocumentCategoryActionForm" disabled="true" styleId="issue_check"   styleClass="textBoxWidth">
            <html:option value="Select">Select</html:option>
            <html:option value="Issuable">Issuable</html:option>
            <html:option value="NotIssuable">NotIssuable</html:option>
        </html:select>
   <%}%>



  </td>
  </tr>
 <tr><td height="5px" colspan="4" ></td></tr>





<tr>
<td align="center" colspan="5"><br>
    <%if(button.equals("Update")){%>
    <input id="button1"  name="button" type="submit" onclick="return check1();" value="<%=button%>" />
    &nbsp;&nbsp;&nbsp;<input name="button" type="submit" value="Back" onclick="return send()"  />
    <%}else if(button.equals("Delete")){%>
    <input id="button1"  name="button" type="submit" value="<%=button%>" onclick="return confirm1();"/>
    &nbsp;&nbsp;&nbsp;<input name="button" type="submit" onclick="return send()"  value="Back" />
   <%}else if(button.equals("Add")){%>
    <input id="button1"  name="button" type="submit" onclick="return check1();" value="Submit" />
    &nbsp;&nbsp;&nbsp;<input name="button" type="submit" value="Back" onclick="return send()" />
    <%}else{%>
    <input  name="button" type="submit" value="Back"  /><%}%>
    <br/><br/>	</td>
</tr>
</table>
                    </td></tr></table>
   </div>

  </html:form>
    </body>
</html>
