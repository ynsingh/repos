<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <%@page contentType="text/html" pageEncoding="UTF-8" import="com.myapp.struts.hbm.*"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>
<%
Department dept=(Department)request.getAttribute("sublib");
String faculty_id=(String)request.getAttribute("faculty_id");
session.setAttribute("dept", dept);
String button=(String)request.getAttribute("button");
  boolean read=true;
  boolean button_visibility=true;
%>
<%
 if(button.equals("View"))
 {
   read=true;
   button_visibility=false;
 }
 if(button.equals("Update"))
 {
   read=false;
   button_visibility=true;
 }
 if(button.equals("Delete"))
 {
   read=true;
   button_visibility=true;
 }
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Serial Entry</title>
<link href="common" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/newformat.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div
   style="  top:120px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

    <html:form action="/up_view_delete" method="post">

  <table width="40%" class="table"  border="1" align="center">

         <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Manage Department</td></tr>
                <tr><td valign="top" align="right" style=" padding-left: 5px;">



            <table align="center">
                <tr><td align="left" width="150px">Faculty Name </td><td align="left">
                       <html:hidden property="faculty_name"   value="<%=faculty_id%>" />
                            <html:select   property="faculty_name" style="width:207px" value="<%=faculty_id%>" disabled="true" tabindex="3">
                          <html:options collection="faculty" property="id.facultyId" labelProperty="facultyName"></html:options>

                     </html:select>


               </td></tr>
    <tr>
    <td align="left"><strong>Department Id</strong></td>
    <td><html:text property="dept_id" styleClass="textBoxWidth" value="<%=dept.getId().getDeptId() %>" readonly="true"/></td>
  </tr>
   <tr>
    <td width="150" align="left"><strong>Department Name </strong> </td>
    <td width="200"> <html:text  property="dept_name"  styleClass="textBoxWidth" readonly="<%=read%>" value="<%=dept.getDeptName() %>" /></td>
    

  </tr>
   
    <tr><td height="5px" colspan="4" ></td></tr>
  
  
   
    
  
    <tr><td height="5px" colspan="4" ></td></tr>
 
     
 
  <tr><td height="5px" colspan="4" ></td></tr>
  
  
 
<tr>
    <td colspan="4" align="center">
    <%if(button_visibility){
    if(button.equals("Delete")){%>
    <html:submit property="button" styleId="button" value="<%=button%>" onclick="return confirm1();" styleClass="btn" style="left:80px"  />
    <%}
    else{%>
    <html:submit property="button" value="<%=button%>" styleClass="btn" style="left:80px"  />
     <%}%><%}%>
    <input type="button" onclick="return quit();" class="btn" style="left:150px" value="Back"/></td>
</tr>
            </table></td></tr></table>

    
  </html:form>
        </div>

</body>
</html>
<div
   style="
      top: 750px;

      position: absolute;

      visibility: show;">

<jsp:include page="/admin/footer.jsp"/>
  </div>
<script language="javascript" type="text/javascript">
  function quit()
  {

      window.location="<%=request.getContextPath()%>/systemsetup/manage_department.jsp";
      return false;
  }

 function confirm1()
{
var answer = confirm ("Do you want to delete record?")
if (answer!=true)
    {
        document.getElementById('button').focus();
        return false;
    }
}

 </script>