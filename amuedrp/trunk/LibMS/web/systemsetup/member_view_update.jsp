<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <%@page contentType="text/html" pageEncoding="UTF-8" import="com.myapp.struts.hbm.*"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>
<%EmployeeType employeetype=(EmployeeType)request.getAttribute("employeetype");
session.setAttribute("employeetype", employeetype);
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

    <html:form action="/update2_view_delete" method="post">

  <table width="40%" class="table"  border="1" align="center">

         <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Manage MemberType</td></tr>
                <tr><td valign="top" align="right" style=" padding-left: 5px;">



            <table align="center">
     <tr>
    <td align="left"><strong>Member ShortName :</strong></td>
    <td><html:text property="emptype_id" styleClass="textBoxWidth" value="<%=employeetype.getId().getEmptypeId() %>" readonly="true"/></td>
  </tr>
    
   <tr>
    <td width="150" align="left"><strong>Member Name </strong> </td>
    <td width="200">


        <html:text  property="emptype_full_name" styleId="emptype_full_name" styleClass="textBoxWidth" readonly="<%=read%>" value="<%=employeetype.getEmptypeFullName() %>" /></td>
    

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
    <html:submit property="button" value="<%=button%>" styleClass="btn" onclick="return validation();" style="left:80px"  />
     <%}%><%}%>
    <input type="button" onclick="return quit();" class="btn" style="left:150px" value="Back"/></td>
</tr>
            </table></td></tr></table>

    
  </html:form>
        </div>

</body>
</html>

<script language="javascript" type="text/javascript">
  function quit()
  {

      window.location="<%=request.getContextPath()%>/systemsetup/manage_member.jsp";
      return false;
  }

 function validation()
    {





    var sublib_name=document.getElementById('emptype_full_name');










var str="Enter Following Values:-";



    if(sublib_name.value=="")
        {str+="\n Enter Member Name ";
             alert(str);
             document.getElementById('emptype_full_name').focus();
            return false;

        }







if(str=="Enter Following Values:-")
   {
       return true;

   }
else
    {

        alert(str);
        document.getElementById('emptype_id').focus();
        return false;
    }




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