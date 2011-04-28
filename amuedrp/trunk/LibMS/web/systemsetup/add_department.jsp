<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Library Details
-->
<%@page  import="java.util.List" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
 <jsp:include page="../admin/header.jsp" flush="true" />

<%
String faculty_id=(String)request.getAttribute("fac_id");
System.out.println(faculty_id);

String new_dept_id=(String)request.getAttribute("dept_id");

%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>LibMS : Manage Department </title>
<link href="common" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/newformat.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/acquisition/dhtmlgoodies_calendar/dhtmlgoodies_calendar.css" media="screen"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/acquisition/dhtmlgoodies_calendar/dhtmlgoodies_calendar.js"></script>
</head>
<body>
 <div
   style="  top:120px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
    
     <html:form action="/add_dept" method="post">
 <table border="1" class="table" width="400px" height="200px" align="center">


                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Manage Department</td></tr>
                <tr><td valign="top" align="center"> <br/>
                <table cellspacing="10px">

                    <tr><td align="left" width="150px">Faculty Name </td><td align="left">
                       <html:hidden property="faculty_name"   value="<%=faculty_id%>" />
                       <% if(faculty_id.equals("Select")){%>
                       <html:text property="faculty_name" disabled="true" value="" styleClass="textBoxWidth"/>

                            <%}else{%>

                            <html:select   property="faculty_name" style="width:207px" value="<%=faculty_id%>" disabled="true" tabindex="3">
                          <html:options collection="faculty" property="id.facultyId" labelProperty="facultyName"></html:options>
                             
                     </html:select>
<%}%>

               </td></tr>
  <tr><td height="5px" colspan="4" ></td></tr>
   <tr>

    <td align="left"><strong>Department Id<a class="star"></a></strong></td>
    <html:hidden property="dept_id" styleId="dept_id"   value="<%=new_dept_id%>" styleClass="textBoxWidth" />
    <td><html:text property="dept_id" styleId="dept_id" disabled="true"  value="<%=new_dept_id%>" styleClass="textBoxWidth" />

    </td>
  </tr>
  
  <tr>
    
    <td align="left"><strong>Department Name<a class="star">*</a></strong></td>
    <td><html:text property="dept_name" styleId="department_name"  value="" styleClass="textBoxWidth"/>
       
    </td>
  </tr>
  <tr><td height="5px" colspan="4" ></td></tr>
 
  
 
 
  <tr><td height="5px" colspan="4" ></td></tr>
 
<tr>
    <td colspan="4" align="center"><input type="submit"  value="Submit"  onClick="return validation();"/>

        <input type="button"  value="Back" onclick="return quit();" />
 </td>
</tr>
</table>
                    </td></tr></table>
</html:form>>
 
</div>
</body>


 <script language="javascript" type="text/javascript">
  function quit()
  {

      window.location="<%=request.getContextPath()%>/systemsetup/manage_department.jsp";
      return false;
  }
   function validation()
    {




 
    var sublib_name=document.getElementById('department_name');

  
    







var str="Enter Following Values:-";


   
    if(sublib_name.value=="")
        {str+="\n Enter Deprtment Name ";
             alert(str);
             document.getElementById('department_name').focus();
            return false;

        }
   




  

if(str=="Enter Following Values:-")
   {
       return true;

   }
else
    {

        alert(str);
        document.getElementById('department_name').focus();
        return false;
    }


return true;

    }
 </script>

  <%
 String msg=(String)request.getAttribute("msg");
           if (msg!=null){
%>
 <script language="javascript">
 window.location="<%=request.getContextPath()%>/systemsetup/add_department.jsp";
 alert("<%=msg%>");
 </script>
 <%
}

%>
</html>

 