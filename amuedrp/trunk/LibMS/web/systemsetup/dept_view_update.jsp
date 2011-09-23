<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <%@page contentType="text/html" pageEncoding="UTF-8" import="com.myapp.struts.hbm.*,java.util.*"%>

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
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
     <%! String button1;%>

    <%

 if(button.equals("Update"))
 {
   button1=resource.getString("circulation.cir_member_reg.update");
     read=false;

   button_visibility=true;
 }
 if(button.equals("Delete"))
 {
   button1=resource.getString("circulation.cir_member_reg.delete");
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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/helpdemo.js"></script>

<script language="javascript" type="text/javascript">
  function  loadHelp()
    {
        window.status="Press F1 for Help";
    }
</script>
</head>
<body onload="loadHelp()">
    <div
   style="  top:120px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

    <html:form action="/up_view_delete" method="post">

  <table dir="<%=rtl%>" width="40%" class="table"  border="1" align="center">

         <tr><td dir="<%=rtl%>" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;"><%=resource.getString("systemsetup.manage_dept.managedept")%></td></tr>
                <tr><td dir="<%=rtl%>" valign="top" align="<%=align%>" style=" padding-left: 5px;">



            <table dir="<%=rtl%>" align="center">
                <tr><td dir="<%=rtl%>" align="<%=align%>" width="150px"><%=resource.getString("systemsetup.add_faculty.facultyname")%> </td><td align="<%=align%>">
                       <html:hidden property="faculty_name"   value="<%=faculty_id%>" />
                            <html:select   property="faculty_name" style="width:207px" value="<%=faculty_id%>" disabled="true" tabindex="3">
                          <html:options collection="faculty" property="id.facultyId" labelProperty="facultyName"></html:options>

                     </html:select>


               </td></tr>
    <tr>
    <td dir="<%=rtl%>" align="<%=align%>"><strong><%=resource.getString("systemsetup.add_dept.deptid")%></strong></td>
    <td dir="<%=rtl%>"><html:text property="dept_id" styleClass="textBoxWidth" value="<%=dept.getId().getDeptId() %>" readonly="true"/></td>
  </tr>
   <tr>
    <td dir="<%=rtl%>" width="150" align="<%=align%>"><strong><%=resource.getString("systemsetup.add_dept.deptname")%> </strong> </td>
    <td dir="<%=rtl%>" width="200"> <html:text  property="dept_name"  styleClass="textBoxWidth" readonly="<%=read%>" value="<%=dept.getDeptName() %>" onfocus="statwords('Enter Department Name')" onblur="loadHelp()" /></td>
    

  </tr>
   
    <tr><td dir="<%=rtl%>" height="5px" colspan="4" ></td></tr>
  
  
   
    
  
    <tr><td dir="<%=rtl%>" height="5px" colspan="4" ></td></tr>
 
     
 
  <tr><td dir="<%=rtl%>" height="5px" colspan="4" ></td></tr>
  
  
 
<tr>
    <td dir="<%=rtl%>" colspan="4" align="center">
    <%if(button_visibility){
    if(button.equals("Delete")){%>
    <input type="submit"  styleId="button" value="<%=button1%>" onclick="return confirm1();" styleClass="btn" style="left:80px"  />
    <%}
    else{%>
    <input type="submit"  value="<%=button1%>" styleClass="btn" onclick="return Update();" style="left:80px"  />
     <%}%><%}%>
    <input type="button" onclick="return quit();" class="btn" style="left:150px" value="<%=resource.getString("circulation.cir_member_reg.back")%>"/></td>
</tr>
            </table></td></tr></table>

     <input type="hidden" id="button" name="button" />
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
document.getElementById('button').value="<%=button%>" ;
    var option=document.getElementById('button').value;
    if(option=="Delete"){
        var a=confirm("<%=resource.getString("circulation.cir_newmember.douwanttodelrec")%>");
       // alert(a);
        if(a!=true)
            {
                document.getElementById('button').focus();
               return false;

        }
        else
            return true;
    }
}
 function Update()
    {


         var buttonvalue="Update";
    document.getElementById("button").setAttribute("value", buttonvalue);
    }

 </script>