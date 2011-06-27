<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to accept Institute Request and Send to SuperAdmin
-->


<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,org.apache.struts.upload.FormFile"%>
<jsp:include page="/header.jsp"/>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LibMS : Institute Registration Page</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css" type="text/css"/>
<script language="javascript" type="text/javascript">

var availableSelectList;






</script>


<script type="text/javascript" language="javascript">

    function quit()
    {
        window.location="<%=request.getContextPath()%>/login.jsp";
    }





    function check()
    {

       




      
      var x1=document.getElementById('password1');
        var x2=document.getElementById('password2');
        if(x1.value!=x2.value)
            {
                alert("password mismatch");
                document.getElementById('password2').focus();
                return false;
            }
            else
                return true;




                return true;


    }


            </script>

 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
</head>
<body>
   <html:form  method="post" action="/registration">
 <table class="table" width="50%" align="center">

                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Institute Registration</td></tr>
                <tr><td align="right" class="err">
                                        <%
                                        String msg=(String)request.getAttribute("msg");
                                        if(msg!=null)
                                            {

                                        %><%=msg%><%}%>

                    </td></tr>
                <tr><td valign="top" align="center" width="800px" >
                       

                        <table width="100%" >
                    
                         
                   
                
                            <tr><td align="left" width="25%" class="txtStyle">Institute Name*</td>
         <td width="25%"><html:text property="institute_name" name="AdminRegistrationActionForm" styleClass="txtTextBox"/>

         </td>
         <td  class="err" >
             <html:messages id="err_name"  property="institute_name">
            <bean:write name="err_name" />
             </html:messages>
         </td></tr>
         <tr><td class="txtStyle">Institute Abbrivation</td><td><html:text property="abbreviated_name" name="AdminRegistrationActionForm" styleClass="txtTextBox" />  </td><td  class="err">   <html:messages id="err_name" property="abbreviated_name">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td class="txtStyle">Institute Address*</td><td><html:text    property="institute_address" name="AdminRegistrationActionForm" styleClass="txtTextBox"/></td><td  class="err">   <html:messages id="err_name" property="institute_address">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td class="txtStyle">City*</td><td><html:text  property="city" styleClass="txtTextBox"/></td><td  class="err">   <html:messages id="err_name" property="city">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td class="txtStyle">State*</td><td><html:text  property="state" styleClass="txtTextBox"/></td><td  class="err">   <html:messages id="err_name" property="state">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td class="txtStyle">Country*</td><td><html:text  property="country" styleClass="txtTextBox"/></td><td  class="err">   <html:messages id="err_name" property="country">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td class="txtStyle">Pin Code</td><td><html:text  property="pin" styleClass="txtTextBox"/></td><td  class="err">   <html:messages id="err_name" property="pin">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
              <tr><td class="txtStyle">Courtesy</td><td>
                      <html:text property="courtesy" styleClass="txtTextBox"/>
 

                 </td><td  class="err">  
         </td></tr>
             <tr><td class="txtStyle">Admin First Name*</td><td><html:text  property="admin_fname" styleClass="txtTextBox"/></td><td  class="err">   <html:messages id="err_name" property="admin_fname">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td class="txtStyle">Admin Last Name*</td><td><html:text  property="admin_lname" styleClass="txtTextBox"/></td><td  class="err">   <html:messages id="err_name" property="admin_lname">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
              <tr><td class="txtStyle">Gender*</td><td>
 <html:select property="gender"  styleClass="txtTextBox">




   <html:option value="">Select </html:option>

                    <html:option value="male">Male</html:option>
                    <html:option value="female">Female</html:option>

                    </html:select>








                 </td><td  class="err">   <html:messages id="err_name" property="gender">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td class="txtStyle">Admin Designation</td><td><html:text  property="admin_designation" styleClass="txtTextBox"/></td><td  class="err">   <html:messages id="err_name" property="admin_designation">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td class="txtStyle">Landline no<br>(With STD)</td><td><html:text  property="land_line_no" styleClass="txtTextBox"/></td><td  class="err">   <html:messages id="err_name" property="land_line_no">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td class="txtStyle">Mobile no*</td><td><html:text  property="mobile_no" styleClass="txtTextBox" /></td><td  class="err">   <html:messages id="err_name" property="mobile_no">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td class="txtStyle">Login ID*</td><td><html:text  property="login_id" styleId="login_id" styleClass="txtTextBox"/></td><td  class="err">   <html:messages id="err_name" property="login_id">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
           
                <tr><td class="txtStyle">Library Name*</td><td><html:text  property="library_name" styleClass="txtTextBox"/></td><td  class="err">   <html:messages id="err_name" property="library_name">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>

             <tr><td class="txtStyle">Type of Institute*</td><td>
                     <html:select property="type_of_institute"  styleClass="txtTextBox">




 <html:option value="">Select</html:option>

                    <html:option value="self_financed">Self Financed</html:option>
                    <html:option value="semi_govt">Semi Govt</html:option>
                    <html:option value="govt">Govt</html:option>
                    <html:option value="other">Other</html:option>

                    </html:select>


                 </td><td  class="err">   
         </td></tr>
            
            
             <tr><td class="txtStyle">Website Name<br>
                  (http://www.xyz.com)</td><td><html:text  property="institute_website" styleClass="txtTextBox"/></td><td  class="err">   <html:messages id="err_name" property="institute_website">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td class="txtStyle">Email ID*</td><td>
                    <html:text  property="admin_email" styleClass="txtTextBox"/>
                   
                 
                 </td><td  class="err">   <html:messages id="err_name" property="admin_email">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td></td><td align="left" colspan="2" ><br/>&nbsp;&nbsp;<input type="submit"  id="submit" name="submit" value="Register" onclick="return check();" styleClass="textBoxWidth">&nbsp;<input type="button"   name="cancel" value="Cancel" styleClass="textBoxWidth" onclick="quit()">
            <br/><br/>

         </td></tr>
              

              
        </table>
          </td></tr>
     </table>
 </html:form>


 </body>




  
</html>
