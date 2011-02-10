<%--
    Document   : admin_view
    Created on : Jun 13, 2010, 9:19:07 AM
    Author     : Dushyant
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>
 <jsp:include page="header.jsp" flush="true" />
<%@page pageEncoding="UTF-8"%>
<%@page import="java.sql.*,com.myapp.struts.MyQueryResult" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%

String id1=request.getParameter("id");
ResultSet rst;


         rst=(ResultSet)request.getAttribute("simple_resultset");

rst.next();

String user_id=rst.getString("user_id");
if(user_id==null)
    user_id="";
String user_name=rst.getString("user_name");
if(user_name==null)
    user_name="";

String library_id=rst.getString("library_id");
if(library_id==null)
    library_id="";
String staff_id=rst.getString("staff_id");
if(staff_id==null)
    staff_id="";
String role=rst.getString("role");
if(role==null)
    role="";
%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LibMS(Library Management System) Staff Account View</title>

<link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>

</head>
<body >


 

      
<div
   style="  top:150px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
    <table width="800px" height="600px"  valign="top" align="center" >

        <tr><td valign="top" height="800px" width="600px" align="center">
                
            
<fieldset style="border:solid 1px brown;padding-left: 10px;padding-right: 20px" ><legend><img src="/LibMS-Struts/images/StaffAccountLogin.png" /></legend>
                    <br>
                    <br>
                    <span class="txt">Account Details</span>
                    <hr>
                    <br>
                    <table  class="txt2" >
                                           
                        <tr>
                            <td width="250px" >Email Id</td><td><input type="text" id="email_id"  tabindex="2"  name="email_id" value="<%=user_id%>" readonly></td>
                        <td width="250px">Login Role</td><td width="250px"><input type="text" id="role"  tabindex="5"  name="role" value="<%=role%>" readonly></td>
                        </tr>
                                  <tr><td colspan="4" height="5px"></td></tr>
                                <tr><td width="250px">User Name
                                   


                                    </td><td width="250px"><input type="text" id="user_name"  tabindex="4" name="user_name" value="<%=user_name%>" readonly></td>
                                    <td width="250px">Staff ID</td><td width="250px"><input type="text" id="staff_id" readonly tabindex="9"  name="staff_id" value="<%=staff_id%>"></td>
                                 
                                </tr>
                                
                                
                                
                                <tr><td colspan="4" height="5px"></td></tr>
                            <tr><td width="250px">Library ID</td><td width="250px"><input type="text" readonly id="library_id"  tabindex="8"  name="library_id" value="<%=library_id%>"></td>
                           
                            </tr>
                             <tr><td colspan="4" height="5px"></td></tr>
                             <tr><td colspan="4" height="5px"></td></tr>
                             <tr><td height="10px" colspan="4" align="center">&nbsp;&nbsp;&nbsp; <input type="button" id="Button3" name="button" value="Back" onclick="return send()" class="txt2" /></td></tr>
                   <tr><td colspan="4" height="5px"></td></tr>
                    </table>
</fieldset>

                                </td>
                                </tr>
                                        </table>
                             
                            

                        




<input type="hidden" id="Editbox" tabindex="1"  name="employee_id" value="<%=rst.getString(1)%>">



</div>



    
<script type="text/javascript" language="javascript">
    function send()
    {
        window.location="/LibMS-Struts/admin/main.jsp";
    }

</script>


</html>

