<%--
    Document   : admin_view
    Created on : Jun 13, 2010, 9:19:07 AM
    Author     : Dushyant
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>
 <jsp:include page="/admin/header.jsp" flush="true" />
<%@page pageEncoding="UTF-8"%>
<%@page import="java.sql.*,com.myapp.struts.hbm.*,com.myapp.struts.AdminDAO.*" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%


         Login rst=(Login)request.getAttribute("data");



String user_id=rst.getLoginId();
if(user_id==null)
    user_id="";
String user_name=rst.getUserName();
if(user_name==null)
    user_name="";

String sublibrary_id=rst.getSublibraryId();
if(sublibrary_id==null)
    sublibrary_id="";
SubLibrary temp=SubLibraryDAO.getLibName(rst.getId().getLibraryId(), sublibrary_id);
String sublibrary_name=null;
if(temp!=null)
sublibrary_name=temp.getSublibName();
String staff_id=rst.getId().getStaffId();
if(staff_id==null)
    staff_id="";
String role=rst.getRole();
if(role==null)
    role="";
%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LibMS : Manage Staff Account</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

</head>
<body >


 

      
<div
   style="  top:150px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
     <table width="80%"   valign="top" align="center">

        <tr><td valign="top" height="100%" width="80%" align="center">


                <table width="60%" class="table"  border="1" align="center">

         <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Manage Staff</td></tr>
                  <tr><td valign="top" align="right" style=" padding-left: 5px;">
                          <br/>
                          <table align="center">
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
                            <tr><td width="250px">Library </td><td width="250px"><input type="text" readonly id="library_id"  tabindex="8"  name="library_id" value="<%=sublibrary_name%>"></td>
                           
                            </tr>
                             <tr><td colspan="4" height="5px"></td></tr>
                             <tr><td colspan="4" height="5px"></td></tr>
                             <tr><td height="10px" colspan="4" align="center">&nbsp;&nbsp;&nbsp; <input type="button" id="Button3" name="button" value="Back" onclick="return send()" class="txt2" /></td></tr>
                   <tr><td colspan="4" height="5px"></td></tr>
                    </table>


                                </td>
                                </tr>
                                        </table>
            </td></tr></table>
                             
                            

                        




<input type="hidden" id="Editbox" tabindex="1"  name="employee_id" value="<%=staff_id %>">



</div>



    
<script type="text/javascript" language="javascript">
    function send()
    {
        window.location="<%=request.getContextPath()%>/admin/viewallaccount.do";
    }

</script>


</html>

