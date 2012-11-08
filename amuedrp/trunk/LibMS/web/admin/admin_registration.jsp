<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
This Page is to accept Institute Request and Send to SuperAdmin
-->
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,org.apache.struts.upload.FormFile"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%--<html:html xhtml="true">--%>
<html:html>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LibMS : Institute Registration Page</title>

<head>
<base href="<%=request.getScheme()%>://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/images/"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css" type="text/css"/>
<script language="javascript" type="text/javascript">
    <%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";
%>
<%
try{
locale1=(String)session.getAttribute("locale");

    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
       // System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;align="left";}
    else{ rtl="RTL";page=false;align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>


var availableSelectList;


function check1()
{
    KeyValue=document.getElementById('login_id').value;
        KeyValue1=document.getElementById('password1').value;

        if(KeyValue==KeyValue1)
            {
               availableSelectList = document.getElementById("searchResult");

               availableSelectList.innerHTML="LoginId and Password Should not be same";
                document.getElementById('password1').value="";

            }
else{
 availableSelectList = document.getElementById("searchResult");

               availableSelectList.innerHTML="";

}

}
   function fun()
        {

        document.forms['f'].action='<%=request.getContextPath()%>/admin/language.jsp';
        
        document.forms['f'].submit();
        
        }







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
<body style="margin: 0px 0px 0px 0px" >
    
   <html:form styleId="f"  method="post" action="/registration" >
       
    <%-- <%if(locale1.equals("hi")){%>  <html:errors bundle="hi"/><%}else{%>
     <html:errors/>
     <%}%>--%>
   
       <%--<html:javascript formName="AdminRegistrationActionForm" dynamicJavascript="true" staticJavascript="true" />--%>
        <table align="center" width="100%" height="100%" style="margin: 0px 0px 0px 0px;padding: 0px 0px 0px 0px;border-collapse: collapse;  border-spacing: 0;" dir="<%=rtl%>" >
      <tr><td class="homepage" style="background-color: black;color:white;" align="right" colspan="2">



         <a style="color:white" href="<%=request.getContextPath()%>">Home</a>&nbsp;|&nbsp;     <a style="color:white" href="http://www.ignouonline.ac.in/sakshatproposal/default.aspx">NME-ICT ERP Mission</a>&nbsp;|&nbsp;<a  style="color:white" href="<%=request.getContextPath()%>/contactus.jsp">Contact Us</a>&nbsp;|&nbsp; <a style="color:white" href="<%=request.getContextPath()%>/admin/admin_registration.jsp"><%= resource.getString("login.href.institute.registration") %></a>
         &nbsp;|&nbsp;            <a style="color:white" href="<%=request.getContextPath()%>/relnotes.jsp">   Release Notes</a>&nbsp;|&nbsp;  <a style="color:white" href="instantUserManual_LibMS-2012.pdf">UserManual</a>&nbsp;|&nbsp; <a style="color:white" href="<%=request.getContextPath()%>/admin/view_instiapproved.jsp">View All Registered Institutes</a>&nbsp;<%--|&nbsp;<%=resource.getString("login.message.selectlanguage")%><select name="locale" class="selecthome" onchange="fun()"><option dir="<%=rtl%>"<%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("en")){ %>selected<%}%>>English</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ur")){ %>selected<%}%>>Urdu</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ar")){ %>selected<%}%>>Arabic</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("hi")){ %>selected<%}%>>Hindi</option></select>--%>



                                         </td>

                                     </tr>
    
            <tr><td><table align="center"  width="100%"  style="background-color: white;"   dir="<%=rtl%>" >

    <tr ><td>
                                     <table width="100%" style="margin: 0px 0px 0px 0px;padding: 0px 0px 0px 0px;"><tr>
                                         <td align="left"  style="background-color: white;color:blue;height: 50px;  margin: 0px 0px 0px 0px;font-style: italic;font-size: 18px;valign:bottom" valign="bottom" align="center">

                                             &nbsp;&nbsp;<span style="font-style: italic;font-size: 18px;">LibMS....</span>        "<%=resource.getString("login.message.logo.under")%>"




                            </td>
                            <td align="right">
  <img src="logo.png" alt=""  border="0" align="top" id="Image1" style="height:70px;width:160px;">

                            </td>


                                         </tr></table>
                                          <hr color="cyan">
                                         </td>

           </tr>

           
                <tr><td align="right" class="err">
                                        <%
                                        String msg=(String)request.getAttribute("msg");
                                        if(msg!=null)
                                            {

                                        %><%=msg%><%}%>

                    </td></tr>
                <tr><td valign="top" align="center" width="50%"  >
                       

                        <table   width="60%" align="center"  >
                    <tr><td colspan="3" align="left" height="25px;"><%=resource.getString("admin.admin_registration.heading")%><hr></td></tr>
                         
                   
                
                            <tr ><td align="left" width="25%"   class="admintxtStyle"><%=resource.getString("login.ems.institutename")%>*</td>
                                <td ><html:text property="institute_name" name="AdminRegistrationActionForm" styleClass="txtTextBox"/>
             <span class="err">

                  <%if(locale1.equals("hi")){%>

                  <html:messages id="err_name" bundle="hi"  property="institute_name">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ar")){%>
                  <html:messages id="err_name" bundle="ar" property="institute_name">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ur")){%>
                  <html:messages id="err_name" bundle="ur" property="institute_name">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}else{%>
                 <html:messages id="err_name"  property="institute_name">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}%>
                 </span>
         </td>
         </tr>
         <tr><td class="admintxtStyle"><%=resource.getString("login.viewpending.instituteabbrevation")%></td><td><html:text property="abbreviated_name" name="AdminRegistrationActionForm" styleClass="txtTextBox" />
               <span class="err">

                 <%if(locale1.equals("hi")){%>

                  <html:messages id="err_name" bundle="hi"  property="abbreviated_name">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ar")){%>
                  <html:messages id="err_name" bundle="ar" property="abbreviated_name">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ur")){%>
                  <html:messages id="err_name" bundle="ur" property="abbreviated_name">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}else{%>
                 <html:messages id="err_name"  property="abbreviated_name">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}%>

               </span>

             </td>     </tr>
             <tr><td class="admintxtStyle"><%=resource.getString("instituteAddress")%>*</td><td><html:text    property="institute_address" name="AdminRegistrationActionForm" styleClass="txtTextBox"/><span class="err">
                      <%if(locale1.equals("hi")){%>

                  <html:messages id="err_name" bundle="hi"  property="institute_address">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ar")){%>
                  <html:messages id="err_name" bundle="ar" property="institute_address">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ur")){%>
                  <html:messages id="err_name" bundle="ur" property="institute_address">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}else{%>
                 <html:messages id="err_name"  property="institute_address">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}%>

                         <%--<html:messages id="err_name" property="institute_address">
				  <bean:write name="err_name" />
           <%=resource.getString("admin.admin_registration.err2")%>

			</html:messages>--%>
                     </span>    </td></tr>
             <tr><td class="admintxtStyle"><%=resource.getString("city")%>*</td><td>
                     <html:text  property="city" styleClass="txtTextBox"/><span  class="err">
     <%--                    <html:messages id="err_name" property="city">
				  <bean:write name="err_name" />
           <%=resource.getString("admin.admin_registration.err3")%>

			</html:messages>--%>
                            <%if(locale1.equals("hi")){%>

                  <html:messages id="err_name" bundle="hi"  property="city">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ar")){%>
                  <html:messages id="err_name" bundle="ar" property="city">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ur")){%>
                  <html:messages id="err_name" bundle="ur" property="city">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}else{%>
                 <html:messages id="err_name"  property="city">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}%>
                     </span></td></tr>
             <tr><td class="admintxtStyle"><%=resource.getString("state")%>*</td><td><html:text  property="state" styleClass="txtTextBox"/><span  class="err">
                          <%if(locale1.equals("hi")){%>

                  <html:messages id="err_name" bundle="hi"  property="state">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ar")){%>
                  <html:messages id="err_name" bundle="ar" property="state">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ur")){%>
                  <html:messages id="err_name" bundle="ur" property="state">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}else{%>
                 <html:messages id="err_name"  property="state">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}%>
                                 </span>  </td></tr>
             <tr><td class="admintxtStyle"><%=resource.getString("country")%>*</td><td><html:text  property="country" styleClass="txtTextBox"/><span  class="err">
                          <%if(locale1.equals("hi")){%>

                  <html:messages id="err_name" bundle="hi"  property="country">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ar")){%>
                  <html:messages id="err_name" bundle="ar" property="country">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ur")){%>
                  <html:messages id="err_name" bundle="ur" property="country">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}else{%>
                 <html:messages id="err_name"  property="country">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}%>
                     </span>   </td></tr>
             <tr><td class="admintxtStyle"><%=resource.getString("pin")%></td><td><html:text  property="pin" styleClass="txtTextBox"/>
  <span class="err">
      <%if(locale1.equals("hi")){%>

                  <html:messages id="err_name" bundle="hi"  property="pin">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ar")){%>
                  <html:messages id="err_name" bundle="ar" property="pin">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ur")){%>
                  <html:messages id="err_name" bundle="ur" property="pin">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}else{%>
                 <html:messages id="err_name"  property="pin">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}%>
  </span>
         </td></tr>
              <tr><td class="admintxtStyle"><%=resource.getString("courtesy")%></td><td>
                      <html:text property="courtesy" styleClass="txtTextBox"/>
   <span class="err">
       <%if(locale1.equals("hi")){%>

                  <html:messages id="err_name" bundle="hi"  property="courtesy">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ar")){%>
                  <html:messages id="err_name" bundle="ar" property="courtesy">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ur")){%>
                  <html:messages id="err_name" bundle="ur" property="courtesy">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}else{%>
                 <html:messages id="err_name"  property="courtesy">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}%>

   </span>

                 </td></tr>
             <tr><td class="admintxtStyle"><%=resource.getString("circulation.cir_newmember.fname")%>*</td><td><html:text  property="admin_fname" styleClass="txtTextBox"/>
                     <span class="err">
                          <%if(locale1.equals("hi")){%>

                  <html:messages id="err_name" bundle="hi"  property="admin_fname">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ar")){%>
                  <html:messages id="err_name" bundle="ar" property="admin_fname">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ur")){%>
                  <html:messages id="err_name" bundle="ur" property="admin_fname">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}else{%>
                 <html:messages id="err_name"  property="admin_fname">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}%>
                     </span></td></tr>
             <tr><td class="admintxtStyle"><%=resource.getString("circulation.cir_newmember.lname")%>*</td><td><html:text  property="admin_lname" styleClass="txtTextBox"/><span  class="err">

                           <%if(locale1.equals("hi")){%>

                  <html:messages id="err_name" bundle="hi"  property="admin_lname">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ar")){%>
                  <html:messages id="err_name" bundle="ar" property="admin_lname">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ur")){%>
                  <html:messages id="err_name" bundle="ur" property="admin_lname">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}else{%>
                 <html:messages id="err_name"  property="admin_lname">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}%>
                     </span></td></tr>
              <tr><td class="admintxtStyle"><%=resource.getString("gender")%>*</td><td>
 <html:select property="gender"  styleClass="txtTextBox">




   <html:option value=""><%=resource.getString("select")%> </html:option>

                    <html:option value="male"><%=resource.getString("male")%></html:option>
                    <html:option value="female"><%=resource.getString("female")%></html:option>

                    </html:select>








                 <span  class="err">     <%if(locale1.equals("hi")){%>

                  <html:messages id="err_name" bundle="hi"  property="gender">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ar")){%>
                  <html:messages id="err_name" bundle="ar" property="gender">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ur")){%>
                  <html:messages id="err_name" bundle="ur" property="gender">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}else{%>
                 <html:messages id="err_name"  property="gender">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}%>
                 </span></td></tr>
             <tr><td class="admintxtStyle"><%=resource.getString("designation")%></td><td><html:text  property="admin_designation" styleClass="txtTextBox"/>
               <span  class="err">

                     <%if(locale1.equals("hi")){%>

                  <html:messages id="err_name" bundle="hi"  property="admin_designation">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ar")){%>
                  <html:messages id="err_name" bundle="ar" property="admin_designation">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ur")){%>
                  <html:messages id="err_name" bundle="ur" property="admin_designation">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}else{%>
                 <html:messages id="err_name"  property="admin_designation">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}%>
               </span>
                 </td>

         </tr>
             <tr><td class="admintxtStyle"><%=resource.getString("landlineno")%><br>(<%=resource.getString("admin.acq_registerstaff.contactformat")%>)</td><td><html:text  property="land_line_no" styleClass="txtTextBox"/>
                 <span  class="err">      <%if(locale1.equals("hi")){%>

                  <html:messages id="err_name" bundle="hi"  property="land_line_no">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ar")){%>
                  <html:messages id="err_name" bundle="ar" property="land_line_no">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ur")){%>
                  <html:messages id="err_name" bundle="ur" property="land_line_no">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}else{%>
                 <html:messages id="err_name"  property="land_line_no">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}%>
               </span>

                 </td>

         </tr>
         <tr><td class="admintxtStyle"><%=resource.getString("admin.acq_registerstaff.mobile_no")%><br><span class="txtStyle">(Ex. +91XXXXXXXXXXX)</span></td><td><html:text  property="mobile_no" styleClass="txtTextBox" />
                     <span  class="err">     <%if(locale1.equals("hi")){%>

                  <html:messages id="err_name" bundle="hi"  property="mobile_no">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ar")){%>
                  <html:messages id="err_name" bundle="ar" property="mobile_no">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ur")){%>
                  <html:messages id="err_name" bundle="ur" property="mobile_no">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}else{%>
                 <html:messages id="err_name"  property="mobile_no">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}%>
                                 </span> </td></tr>
            <%-- <tr><td class="admintxtStyle"><%=resource.getString("admin.createaccount1.loginid")%>*</td><td><html:text  property="login_id" styleId="login_id" styleClass="txtTextBox"/></td><td  class="err">   <html:messages id="err_name" property="login_id">
				  <bean:write name="err_name" />
           <%=resource.getString("admin.admin_registration.err10")%>

			</html:messages>
         </td></tr>--%>
                <tr><td class="admintxtStyle"><%=resource.getString("opac.newmemberentry.libraryname")%>*</td><td><html:text  property="library_name" styleClass="txtTextBox"/><span  class="err">
                              <%if(locale1.equals("hi")){%>

                  <html:messages id="err_name" bundle="hi"  property="library_name">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ar")){%>
                  <html:messages id="err_name" bundle="ar" property="library_name">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ur")){%>
                  <html:messages id="err_name" bundle="ur" property="library_name">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}else{%>
                 <html:messages id="err_name"  property="library_name">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}%>
                        </span></td></tr>

             <tr><td class="admintxtStyle"><%=resource.getString("login.viewpending.typeofinstitute")%></td><td>
                     <html:select property="type_of_institute"  styleClass="txtTextBox">




 <html:option value=""><%=resource.getString("select")%></html:option>

                    <html:option value="self_financed"><%=resource.getString("admin.admin_registration.selffin")%></html:option>
                    <html:option value="semi_govt"><%=resource.getString("admin.admin_registration.semi-gov")%></html:option>
                    <html:option value="govt"><%=resource.getString("admin.admin_registration.gov")%></html:option>
                    <html:option value="other"><%=resource.getString("admin.admin_registration.others")%></html:option>

                    </html:select>


                 </td></tr>
            
            
             <tr><td class="admintxtStyle"><%=resource.getString("websitename")%><br>
                  (http://www.xyz.com)</td><td><html:text  property="institute_website" styleClass="txtTextBox"/>
                 <span  class="err">      <%if(locale1.equals("hi")){%>

                  <html:messages id="err_name" bundle="hi"  property="institute_website">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ar")){%>
                  <html:messages id="err_name" bundle="ar" property="institute_website">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ur")){%>
                  <html:messages id="err_name" bundle="ur" property="institute_website">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}else{%>
                 <html:messages id="err_name"  property="institute_website">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}%>
                                 </span>

                 </td>

         </tr>
             <tr><td class="admintxtStyle"><%=resource.getString("emailid")%>*</td><td>
                    <html:text  property="admin_email" styleClass="txtTextBox"/>
                   
                 
                 <span  class="err">   <%if(locale1.equals("hi")){%>

                  <html:messages id="err_name" bundle="hi"  property="admin_email">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ar")){%>
                  <html:messages id="err_name" bundle="ar" property="admin_email">
                     <bean:write name="err_name" />
                 </html:messages>
                  <%}else if(locale1.equals("ur")){%>
                  <html:messages id="err_name" bundle="ur" property="admin_email">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}else{%>
                 <html:messages id="err_name"  property="admin_email">
                     <bean:write name="err_name" />
                 </html:messages>
                 <%}%>
                 </span></td></tr>
             <tr><td align="left" colspan="3" >&nbsp;&nbsp;<input type="submit"  id="submit" name="submit" value="<%=resource.getString("register")%>" onclick="return check();" Class="buttonhome">&nbsp;<input type="button"   name="cancel" value="<%=resource.getString("cancel")%>" Class="buttonhome" onclick="quit()">
            <br/><br/>

         </td></tr>
              

                        </table>
                    </td></tr></table></td></tr></table>
                   
          
 </html:form>


 </body>


 <jsp:include page="/OPAC/opacfooter.jsp"/>

  
</html:html>
