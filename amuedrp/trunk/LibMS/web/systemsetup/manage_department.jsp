<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Staff ID 
-->

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,com.myapp.struts.systemsetupDAO.FacultyDAO,com.myapp.struts.hbm.*"%>

 <jsp:include page="/admin/header.jsp" flush="true" />

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<%
String library_id=(String)session.getAttribute("library_id");
String msg=(String)request.getAttribute("msg");
String msg1=(String)request.getAttribute("msg1");




%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LibMS : Manage Faculty </title>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
 <script language="javascript" type="text/javascript">


function check1()
{
     if(document.getElementById('faculty_id').value=="Select")
    {
        alert("Select Faculty");

        document.getElementById('faculty_id').focus();

        return false;
    }


    if(document.getElementById('dept_id').value=="")
    {
        alert("Enter Department Id...");

        document.getElementById('dept_id').focus();

        return false;
    }

  }





  function quit()
  {

      window.location="<%=request.getContextPath()%>/admin/main.jsp";
      return false;
  }



    </script>
</head>
<body>
 
    <html:form action="/deptregistration" method="post"  onsubmit="return check1()">
       
<div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

    <table border="1" class="table" width="400px" height="200px" align="center">

  
        <tr><td align="center" colspan="2" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Manage Department</td></tr>
                <tr><td valign="middle" align="center"> 
                <table valign="">
 <tr><td align="left">Faculty Name </td><td align="left">

         <html:select   property="faculty_name"  styleId="faculty_id" value="Select" >
                          <html:option value="Select">Select</html:option>
                          <html:options name="Faculty" collection="faculty" property="id.facultyId" labelProperty="facultyName"></html:options>

                     </html:select>

                      <br/>
               </td></tr>
                    
                    <tr>
                     
                        <td  >Enter Department ID</td><td>
                        <input type="text" id="dept_id" name="dept_id" value=""/></td>
                    </tr>
                </table>
                    </td><td>
                        <table>
                    <tr><td> <input type="submit" class="btn" id="Button1" name="button" value="Register" /></td> </tr>
                    <tr><td><input type="submit" id="Button2" class="btn" name="button" value="Update"  /></td></tr>
                    <tr><td><input type="submit" id="Button3" name="button" value="View" class="btn"  /></td></tr>
                    <tr><td><input type="submit" id="Button4" name="button" value="Delete" class="btn" /></td></tr>
                         <tr><td align="center"><input type="button" id="Button5" name="button" value="Back" class="btn" onclick="return quit()"/></td></tr>
 

                </table>
       

    <input type="hidden" name="library_id" value="<%=library_id%>">
   








</td></tr>
         <tr><td align="left" colspan="2" height="25px;" class="mess">
                             <%
          if (msg!=null)
          {
        %>


        <p class="mess">  <%=msg%></p>

        <%
        }
        %>
         <%if (msg1!=null)
          {
        %>


        <p class="err">  <%=msg1%></p>

        <%
        }
        %>

                 </td></tr>
              <tr><td align="justify" colspan="2"><font color="blue" size="-1"><b>Example:</b> cs for Computer Science, phy for Physics & so on, if Department exists.</font></td></tr>


    </table>
        </div>
  
</html:form>

</body>

    
</html>
