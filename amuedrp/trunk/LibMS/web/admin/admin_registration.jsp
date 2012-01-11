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
          <table  style="background-color: #BFDBFF;  background-image: url('<%=request.getContextPath()%>/images/body-bg.png'); border:  solid 1px black;margin: 0px 0px 0px 0px" height="100px" width="100%">
                        <tr class="search"><td class="homepage"  valign="middle" align="left">&nbsp;&nbsp;<span style="color:white;font-size: 30px;font-family: arial;font-weight: bold">Lib</span><span style="color:pink;font-size: 30px;font-family: arial;font-weight: bold">MS</span>

                                <br>( A Complete Online Library Membership just 2 Steps Ahead )
                </td><td  valign="bottom" align="right" >
<a style="text-decoration: none" class="txt2" href="<%=request.getContextPath()%>/help/help2.jsp#step2">Get Help</a>
                </td>    </tr>


                    </table>

 <table class="table" width="50%" align="center">

                <tr><td align="center" class="header" height="25px;"><span class="homepage"><%=resource.getString("admin.admin_registration.heading")%></span></td></tr>
                <tr><td align="right" class="err">
                                        <%
                                        String msg=(String)request.getAttribute("msg");
                                        if(msg!=null)
                                            {

                                        %><%=msg%><%}%>

                    </td></tr>
                <tr><td valign="top" align="center" width="800px" >
                       

                        <table width="100%" >
                    
                         
                   
                
                            <tr><td align="left" width="25%" class="txtStyle"><%=resource.getString("login.ems.institutename")%>*</td>
         <td width="25%"><html:text property="institute_name" name="AdminRegistrationActionForm" styleClass="txtTextBox"/>

         </td>
         <td  class="err" >
             <html:messages id="err_name"  property="institute_name">
           <%-- <bean:write name="err_name" />--%>
           <%=resource.getString("admin.admin_registration.err1")%>
             </html:messages>
         </td></tr>
         <tr><td class="txtStyle"><%=resource.getString("login.viewpending.instituteabbrevation")%></td><td><html:text property="abbreviated_name" name="AdminRegistrationActionForm" styleClass="txtTextBox" />  </td><td  class="err">

         </td></tr>
             <tr><td class="txtStyle"><%=resource.getString("instituteAddress")%>*</td><td><html:text    property="institute_address" name="AdminRegistrationActionForm" styleClass="txtTextBox"/></td><td  class="err">   <html:messages id="err_name" property="institute_address">
				 <%-- <bean:write name="err_name" />--%>
           <%=resource.getString("admin.admin_registration.err2")%>

			</html:messages>
         </td></tr>
             <tr><td class="txtStyle"><%=resource.getString("city")%>*</td><td><html:text  property="city" styleClass="txtTextBox"/></td><td  class="err">   <html:messages id="err_name" property="city">
				 <%-- <bean:write name="err_name" />--%>
           <%=resource.getString("admin.admin_registration.err3")%>

			</html:messages>
         </td></tr>
             <tr><td class="txtStyle"><%=resource.getString("state")%>*</td><td><html:text  property="state" styleClass="txtTextBox"/></td><td  class="err">   <html:messages id="err_name" property="state">
				 <%-- <bean:write name="err_name" />--%>
           <%=resource.getString("admin.admin_registration.err4")%>

			</html:messages>
         </td></tr>
             <tr><td class="txtStyle"><%=resource.getString("country")%>*</td><td><html:text  property="country" styleClass="txtTextBox"/></td><td  class="err">   <html:messages id="err_name" property="country">
				 <%-- <bean:write name="err_name" />--%>
           <%=resource.getString("admin.admin_registration.err5")%>

			</html:messages>
         </td></tr>
             <tr><td class="txtStyle"><%=resource.getString("pin")%></td><td><html:text  property="pin" styleClass="txtTextBox"/></td><td  class="err">

         </td></tr>
              <tr><td class="txtStyle"><%=resource.getString("courtesy")%></td><td>
                      <html:text property="courtesy" styleClass="txtTextBox"/>
 

                 </td><td  class="err">  
         </td></tr>
             <tr><td class="txtStyle"><%=resource.getString("circulation.cir_newmember.fname")%>*</td><td><html:text  property="admin_fname" styleClass="txtTextBox"/></td><td  class="err">
                     <html:messages id="err_name" property="admin_fname">
				 <%-- <bean:write name="err_name" />--%>
           <%=resource.getString("admin.admin_registration.err6")%>

			</html:messages>
         </td></tr>
             <tr><td class="txtStyle"><%=resource.getString("circulation.cir_newmember.lname")%>*</td><td><html:text  property="admin_lname" styleClass="txtTextBox"/></td><td  class="err">   <html:messages id="err_name" property="admin_lname">
				 <%-- <bean:write name="err_name" />--%>
           <%=resource.getString("admin.admin_registration.err7")%>

			</html:messages>
         </td></tr>
              <tr><td class="txtStyle"><%=resource.getString("gender")%>*</td><td>
 <html:select property="gender"  styleClass="txtTextBox">




   <html:option value=""><%=resource.getString("select")%> </html:option>

                    <html:option value="male"><%=resource.getString("male")%></html:option>
                    <html:option value="female"><%=resource.getString("female")%></html:option>

                    </html:select>








                 </td><td  class="err">   <html:messages id="err_name" property="gender">
				 <%-- <bean:write name="err_name" />--%>
           <%=resource.getString("admin.admin_registration.err8")%>

			</html:messages>
         </td></tr>
             <tr><td class="txtStyle"><%=resource.getString("designation")%></td><td><html:text  property="admin_designation" styleClass="txtTextBox"/></td><td  class="err">

         </td></tr>
             <tr><td class="txtStyle"><%=resource.getString("landlineno")%><br>(<%=resource.getString("admin.acq_registerstaff.contactformat")%>)</td><td><html:text  property="land_line_no" styleClass="txtTextBox"/></td><td  class="err">

         </td></tr>
             <tr><td class="txtStyle"><%=resource.getString("admin.acq_registerstaff.mobile_no")%>*</td><td><html:text  property="mobile_no" styleClass="txtTextBox" /></td><td  class="err">   <html:messages id="err_name" property="mobile_no">
				 <%-- <bean:write name="err_name" />--%>
           <%=resource.getString("admin.admin_registration.err9")%>

			</html:messages>
         </td></tr>
             <tr><td class="txtStyle"><%=resource.getString("admin.createaccount1.loginid")%>*</td><td><html:text  property="login_id" styleId="login_id" styleClass="txtTextBox"/></td><td  class="err">   <html:messages id="err_name" property="login_id">
				 <%-- <bean:write name="err_name" />--%>
           <%=resource.getString("admin.admin_registration.err10")%>

			</html:messages>
         </td></tr>
                <tr><td class="txtStyle"><%=resource.getString("opac.newmemberentry.libraryname")%>*</td><td><html:text  property="library_name" styleClass="txtTextBox"/></td><td  class="err">   <html:messages id="err_name" property="library_name">
				 <%-- <bean:write name="err_name" />--%>
           <%=resource.getString("admin.admin_registration.err11")%>

			</html:messages>
         </td></tr>

             <tr><td class="txtStyle"><%=resource.getString("login.viewpending.typeofinstitute")%></td><td>
                     <html:select property="type_of_institute"  styleClass="txtTextBox">




 <html:option value=""><%=resource.getString("select")%></html:option>

                    <html:option value="self_financed"><%=resource.getString("admin.admin_registration.selffin")%></html:option>
                    <html:option value="semi_govt"><%=resource.getString("admin.admin_registration.semi-gov")%>Semi Govt</html:option>
                    <html:option value="govt"><%=resource.getString("admin.admin_registration.gov")%></html:option>
                    <html:option value="other"><%=resource.getString("admin.admin_registration.others")%></html:option>

                    </html:select>


                 </td><td  class="err">   
         </td></tr>
            
            
             <tr><td class="txtStyle"><%=resource.getString("websitename")%><br>
                  (http://www.xyz.com)</td><td><html:text  property="institute_website" styleClass="txtTextBox"/></td><td  class="err">

         </td></tr>
             <tr><td class="txtStyle"><%=resource.getString("emailid")%>*</td><td>
                    <html:text  property="admin_email" styleClass="txtTextBox"/>
                   
                 
                 </td><td  class="err">   <html:messages id="err_name" property="admin_email">
				 <%-- <bean:write name="err_name" />--%>
           <%=resource.getString("admin.admin_registration.err12")%>

			</html:messages>
         </td></tr>
             <tr><td></td><td align="left" colspan="2" ><br/>&nbsp;&nbsp;<input type="submit"  id="submit" name="submit" value="<%=resource.getString("register")%>" onclick="return check();" Class="buttonhome">&nbsp;<input type="button"   name="cancel" value="<%=resource.getString("cancel")%>" Class="buttonhome" onclick="quit()">
            <br/><br/>

         </td></tr>
              

              
        </table>
          </td></tr>
     </table>
 </html:form>


 </body>




  
</html>
