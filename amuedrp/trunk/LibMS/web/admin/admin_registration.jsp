<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp" flush="true" />
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Untitled Page</title>

<link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>


</head>
<body>
  
        <div
   style="  top:60px;
   left:350px;
   right:5px;
      position: absolute;

      visibility: show;">

         

   <html:form  method="post" action="/registration"  >

 <table  align="left" width="1000px" >




     
     <tr><td valign="top" width="700px" align="left">
            
          
             <table align="left" width="700px" class="btn3"  style="">
  <tr><td align="center" colspan="2" style="background-color: #c0003b ;height:30px;color:white;border:solid 1px  #c0003b; margin:0px 0px 0px 0px;font-family:Tahoma;">
          <b>Institute Registration</b></td><td align="right"><b>Fields marked with (*) are complusory</b></td></tr>
     <tr><td width="150px">Institute Name*</td><td width="150px"><html:text property="institute_name" name="AdminRegistrationActionForm" />

         </td><td width="300px" class="err"> <html:messages id="err_name"  property="institute_name">
            <bean:write name="err_name" />
             </html:messages>
         </td></tr>
             <tr><td>Institute Abbrivation</td><td><html:text property="abbreviated_name" name="AdminRegistrationActionForm" />  </td><td  class="err">   <html:messages id="err_name" property="abbreviated_name">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td>Institute Address*</td><td><html:text    property="institute_address" name="AdminRegistrationActionForm"/></td><td  class="err">   <html:messages id="err_name" property="institute_address">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td>City*</td><td><html:text  property="city"/></td><td  class="err">   <html:messages id="err_name" property="city">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td>State*</td><td><html:text  property="state"/></td><td  class="err">   <html:messages id="err_name" property="state">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td>Country*</td><td><html:text  property="country" /></td><td  class="err">   <html:messages id="err_name" property="country">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td>Pin Code</td><td><html:text  property="pin" /></td><td  class="err">   <html:messages id="err_name" property="pin">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
              <tr><td>Courtesy</td><td>
 <html:select property="courtesy" style="width:146px">



 <html:option value="">Select </html:option>


                    <html:option value="mr">Mr</html:option>

                    <html:option value="mrs">Mrs</html:option>
                    <html:option value="ms">Ms.</html:option>

                    </html:select>





                 </td><td  class="err">   <html:messages id="err_name" property="courtesy">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td>Admin First Name*</td><td><html:text  property="admin_fname" /></td><td  class="err">   <html:messages id="err_name" property="admin_fname">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td>Admin Last Name*</td><td><html:text  property="admin_lname" /></td><td  class="err">   <html:messages id="err_name" property="admin_lname">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
              <tr><td>Gender*</td><td>
 <html:select property="gender" style="width:146px">




   <html:option value="">Select </html:option>

                    <html:option value="male">Male</html:option>
                    <html:option value="female">Female</html:option>

                    </html:select>








                 </td><td  class="err">   <html:messages id="err_name" property="gender">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td>Admin Designation</td><td><html:text  property="admin_designation"/></td><td  class="err">   <html:messages id="err_name" property="admin_designation">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td>Landline no<br>(With STD)</td><td><html:text  property="land_line_no" /></td><td  class="err">   <html:messages id="err_name" property="land_line_no">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td>Mobile no*</td><td><html:text  property="mobile_no" /></td><td  class="err">   <html:messages id="err_name" property="mobile_no">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>

             <tr><td>Password*</td><td><html:password  property="admin_password" style="width: 143px"/></td><td  class="err">   <html:messages id="err_name" property="admin_password">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
                <tr><td>Library Name*</td><td><html:text  property="library_name"/></td><td  class="err">   <html:messages id="err_name" property="library_name">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>

             <tr><td>Type of Institute*</td><td>
                     <html:select property="type_of_institute"  style="width:146px">




 <html:option value="">Select</html:option>

                    <html:option value="self_financed">Self Financed</html:option>
                    <html:option value="semi_govt">Semi Govt</html:option>
                    <html:option value="govt">Govt</html:option>
                    <html:option value="other">Other</html:option>

                    </html:select>


                 </td><td  class="err">   <html:messages id="err_name" property="type_of_institute">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
            
            
             <tr><td>Website Name<br>
                  (http://www.xyz.com)</td><td><html:text  property="institute_website"/></td><td  class="err">   <html:messages id="err_name" property="institute_website">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td>Email ID*</td><td>
                    <html:text  property="admin_email"/>
                   
                 
                 </td><td  class="err">   <html:messages id="err_name" property="admin_email">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
              <tr><td>Institute Domain
                  </td><td><html:select property="institute_domain" style="width:146px" >




   <html:option value="">Select </html:option>

                    <html:option value="com">.com</html:option>
                  <html:option value="edu">.edu</html:option>

                    </html:select>
</td><td  class="err">   <html:messages id="err_name" property="institute_domain">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>

              <tr><td></td><td colspan="2" align="left"><input type="submit"  id="submit" name="submit" value="Register" class="txt2">&nbsp;<input type="button"   name="cancel" value="Cancel" class="txt2" onclick="quit()"><br></td></tr>
        </table>
          </td></tr>
     <tr><td width="1000px" align="center">
             <span style="font-family: Tahoma;font-size:14px">&copy; 2010 , ERP Mission Project (AMU Aligarh) INDIA</span>

         </td></tr></table>
 </html:form>
</div>

        </body>
        <script>
    function quit()
    {
        window.location="/LibMS-Struts/login.jsp";
    }
    </script>

<%
String msg=(String)request.getAttribute("r_msg");
if(msg!=null)
    {
    %>
    <script language="javascript" type="text/javascript">
        window.location="/LibMS-Struts/admin/admin_registration.jsp";
        alert("<%=msg%>");
    </script>
    <%}%>

  
</html>
