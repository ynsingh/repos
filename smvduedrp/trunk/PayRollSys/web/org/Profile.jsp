<%-- 
    Document   : Profile
    Created on : Nov 22, 2010, 9:16:36 AM
    Author     :  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
* 
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
--%>

<%@page import="org.smvdu.payroll.beans.Org"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="../css/table.css"/>
    </head>
    <body>

        <p class="xerror">Update Institute Information</p>

         <%
    Org org = new Org().getProfile();
    
%>
         <form action="upload_profile_image.jsp" method="post" enctype="multipart/form-data" name="form1" id="form1">
   <center>
   <table border="2">
       <tr>
	       <td align="right"><b>Tag Line</b></td>
               <td ><input type="text" name="emp_name" value="<%=org.tagLine %>"></td>
	   </tr>
	   <tr>
	       <td align="right"><b>Address:</b> </td>
		   <td ><input type="text" value="<%=org.address1 %>" name="address1"></td>

	   </tr>
	   <tr>
	       <td>
		   </td>
		   <td>
		      <input type="text" value="<%=org.address2 %>" name="address2">
		   </td>
	   </tr>
	   <tr>
	       <td align="right"><b>Contact Number:</b></td>
		   <td ><input type="text" value="<%=org.phone %>" name="contact_number"></td>
	   </tr>
       <tr>
	       <td align="right"><b>Email ID:</b> </td>
		   <td ><input type="text" value="<%=org.email %>" name="email_id"></td>
	   </tr>

           <tr>
	       <td align="right"><b>Web Site Link</b> </td>
		   <td ><input type="text" value="<%=org.web %>" name="web_id"></td>
	   </tr>

       <tr>
	       <td align="right">Logo Image </td>
	       <td>
		       <input name="file" type="file" id="file">
		   <td>
	   </tr>
         <tr><td colspan="2"><img align="center" alt=""  src="org/uploadimg/logo.gif" width="100px" height="100px"/><br></tr>

		 <tr>
		    <td align="center">
               <input type="submit" name="Submit" value="Submit"/>
			   <input type="reset" name="Reset" value="Reset"/>

			</td>
		 </tr>
    </table>
	</center>
 </form>
    </body>
</html>
