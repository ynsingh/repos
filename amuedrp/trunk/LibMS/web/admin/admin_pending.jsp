<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.sql.*,com.myapp.struts.hbm.*" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<link rel="stylesheet" href="/LibMS/css/page.css"/>
<%@page import="java.util.*,java.io.*,java.net.*"%>

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


<%
try{
if(session.getAttribute("library_id")!=null){
System.out.println("institute_id"+session.getAttribute("library_id"));
}
else{
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"logout.do?session=\"expired\"";</script><%
    }
}catch(Exception e){
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %>sessionout();<%
    }


List rst = (List)request.getAttribute("resultset");

if(rst==null){System.out.println("ok"+rst); rst = (List)session.getAttribute("resultset");}
else{session.setAttribute("resultset", rst);}
System.out.println("ok"+rst);
AdminRegistration adminReg = new AdminRegistration();
if (!rst.isEmpty()){
    adminReg = (AdminRegistration)rst.get(0);
    }
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
</head>
<body>
    <html:form  action="/confirm" method="post"  >
                  <table align="center" dir="<%=rtl%>"  class="table" bgcolor="#7697BC" width="60%" >




     <tr><td  align="center" class="txtStyle1" height="20px">Institute Details


         </td></tr>


            <%if(!rst.isEmpty()){%>
     <%
     String registration_request_id=String.valueOf(adminReg.getRegistrationId());
     String institute_name=adminReg.getInstituteName();
     String abbreviated_name=adminReg.getAbbreviatedName();
     String institute_address=adminReg.getInstituteAddress();
     String city=adminReg.getCity();
     String state1=adminReg.getState();
     String country=adminReg.getCountry();
     String pin=adminReg.getPin();
     String land_line_no=adminReg.getLandLineNo();
     String mobile_no=adminReg.getMobileNo();
     
     String type_of_institute=adminReg.getTypeOfInstitute();
     String website=adminReg.getWebsite();
     String admin_fname=adminReg.getAdminFname();
     String admin_lname=adminReg.getAdminLname();
     String admin_designation=adminReg.getAdminDesignation();
     String admin_email=adminReg.getAdminEmail();
     String admin_password=adminReg.getAdminPassword();
     String status=adminReg.getStatus();
     String courtesy=adminReg.getCourtesy();
     String gender=adminReg.getGender();
     String login_id = adminReg.getLoginId();
     String library_name=adminReg.getLibraryName();
     if(registration_request_id==null)
         registration_request_id="";
     if(institute_name==null)
         institute_name="";
     if(abbreviated_name==null)
         abbreviated_name="";
     if(institute_address==null)
             institute_address="";
     if(city==null)
         city="";
     if(state1==null)
         state1="";
     if(country==null)
         country="";
     if(pin==null)
         pin="";
     if(land_line_no==null)
         land_line_no="";
     if(mobile_no==null)
         mobile_no="";
     
     if(type_of_institute==null)
         type_of_institute=null;
     if(website==null)
         website="";
     if(admin_fname==null)
         admin_fname="";
     if(admin_lname==null)
         admin_lname="";
     if(admin_designation==null)
         admin_designation="";
     if(admin_email==null)
         admin_email="";
     if(admin_password==null)
         admin_password="";
     if(status==null)
         status="";
     if(courtesy==null)
         courtesy="";
     if(gender==null)
         gender="";
    
     if(library_name==null)
         library_name="";

     %>
      <tr><td>
            <table class="txtStyle" bgcolor="white" >

     <tr><td width="15%" dir="<%=rtl%>" width="150px"><%=resource.getString("institutename")%></td><td><input type="text" id="Editbox1"   name="institute_name" value="<%=institute_name%>" tabindex="1" title="Enter Instutute Name" readonly></td><td width="15%" dir="<%=rtl%>"><%=resource.getString("registrationid")%></td><td><input type="text" id="Editbox18"  name="registration_request_id" value="<%=registration_request_id%>" tabindex="18" readonly></td></tr>

             <tr><td dir="<%=rtl%>"><%=resource.getString("instituteabbrevation")%></td><td><input type="text" id="Editbox2"   name="abbreviated_name" value="<%=abbreviated_name%>" tabindex="2" readonly title="Abbrivated name e.g. AMU(aligarh muslim University)"></td><td dir="<%=rtl%>"><%=resource.getString("courtesy")%></td><td>
                     <input type="text" id="courtesy" readonly name="courtesy" value="<%=courtesy%>"/>


                     <input type="hidden" id="courtesy" name="courtesy" value="<%=courtesy%>"/>
                      
</td></tr>

             <tr><td><%=resource.getString("instituteAddress")%></td><td><input type="text" id="Editbox3"   name="institute_address" value="<%=institute_address%>" tabindex="3" readonly title="Enter Address of Institute"></td><td dir="<%=rtl%>"><%=resource.getString("firstname")%></td><td><input type="text" id="Editbox13"  name="admin_fname" value="<%=admin_fname%>" tabindex="13" title="Enter first Name" readonly></td></tr>
             <tr><td dir="<%=rtl%>"><%=resource.getString("admin.adminpending.libraryname")%></td><td><input type="text"  id="Editbox14" readonly name="library_name" value="<%=library_name%>" tabindex="16" title="Enter Library Name"></td><td dir="<%=rtl%>"><%=resource.getString("lastname")%></td><td><input type="text" id="Editbox16"  name="admin_lname" value="<%=admin_lname%>" tabindex="14" title="Enter Last Name" readonly></td></tr>
              <tr><td dir="<%=rtl%>"><%=resource.getString("city")%></td><td>

                 <input type="text" id="Editbox4"   name="city" value="<%=city%>" tabindex="4" title="Enter City " readonly>


                 </td><td dir="<%=rtl%>"><%=resource.getString("designation")%></td><td><input type="text" id="Editbox15"  name="admin_designation" value="<%=admin_designation%>" tabindex="15" title="Enter Designation" readonly></td></tr>
             <tr><td dir="<%=rtl%>"><%=resource.getString("state")%></td><td><input type="text" id="Editbox5"   name="state" value="<%=state1%>" tabindex="5" title="Enter State" readonly></td><td dir="<%=rtl%>"><%=resource.getString("mobileno")%>
                 </td><td><input type="text" id="Editbox9"   name="mobile_no" value="<%=mobile_no%>" tabindex="9" title="Enter Mobile No with STD Code" readonly></td></tr>
             <tr><td dir="<%=rtl%>"><%=resource.getString("country")%></td><td><input type="text" id="Editbox6"   name="country" value="<%=country%>" tabindex="6" title="Enter Country" readonly></td>
             <td dir="<%=rtl%>"><%=resource.getString("emailid")%></td><td><input type="text"  id="Editbox14" readonly name="admin_email" value="<%=admin_email%>" tabindex="16" title="Enter E-mail Id">
             </tr>
             <tr><td dir="<%=rtl%>"><%=resource.getString("pin")%></td><td><input type="text" id="Editbox7"  name="pin" value="<%=pin%>" tabindex="7" title="Enter PIN/ZIP Code" readonly></td>
             <td dir="<%=rtl%>"><%=resource.getString("gender")%></td><td> <select name="gender" size="1" disabled id="gender" style="width:148px"   tabindex="11" title="gender">
            <%if(gender.equals("male")){%>
            <option selected value="male"><%=resource.getString("male")%></option>
            <option value="female"><%=resource.getString("female")%></option>


            <%}%>
            <%if(gender.equals("female")){%>
            <option  value="male"><%=resource.getString("male")%></option>
            <option selected value="female"><%=resource.getString("female")%></option>

            <%}%>

            </select>
            </td>
            </tr>




<input type="hidden" id="gender" name="gender" value="<%=gender%>"/>




             <tr><td dir="<%=rtl%>"><%=resource.getString("landlineno")%></td><td><input type="text" id="Editbox8"   name="land_line_no" value="<%=land_line_no%>" tabindex="8" title="Enter Land Line No" readonly></td>
             <%--<td><%=resource.getString("password")%></td><td><input type="password" id="Editbox11" readonly  name="admin_password" value="<%=admin_password%>" tabindex="17" title="Enter Password" readonly></td>--%>
             </tr>
             <input type="hidden" name="type_of_institute" value="<%=type_of_institute%>"/>
             <tr><td dir="<%=rtl%>"><%=resource.getString("typeofinstitute")%></td><td><select name="type_of_institute" disabled id="type_of_institute" style="width:148px" >



    <%if(type_of_institute.equals("govt")){%>
<option selected value="govt"><%=resource.getString("govt")%></option>
<option value="semi govt"><%=resource.getString("semigovt")%></option>
<option value="self financed"><%=resource.getString("selffinanced")%></option>
<option value="other"><%=resource.getString("other")%></option>
<option  value="Select"><%=resource.getString("select")%></option>
            <%}%>
            <%if(type_of_institute.equals("semi_govt")){%>
<option value="govt"><%=resource.getString("govt")%></option>
<option selected value="semi govt"><%=resource.getString("semigovt")%></option>
<option value="self financed"><%=resource.getString("selffinanced")%></option>
<option value="other"><%=resource.getString("other")%></option>
<option value="Select"><%=resource.getString("select")%></option>
            <%}%>
            <%if(type_of_institute.equals("self_financed")){%>
<option  value="govt"><%=resource.getString("govt")%></option>
<option value="semi govt"><%=resource.getString("semigovt")%></option>
<option selected value="self financed"><%=resource.getString("selffinanced")%></option>
<option value="other"><%=resource.getString("other")%></option>
<option  value="Select"><%=resource.getString("select")%></option>
            <%}%>
            <%if(type_of_institute.equals("other")){%>
<option  value="govt"><%=resource.getString("govt")%></option>
<option value="semi govt"><%=resource.getString("semigovt")%></option>
<option value="self financed"><%=resource.getString("selffinanced")%></option>
<option selected value="other"><%=resource.getString("other")%></option>
<option  value="Select"><%=resource.getString("select")%></option>
            <%}%>

</select></td>

             </tr>
             
             <tr><td dir="<%=rtl%>"><%=resource.getString("userid")%></td><td><input type="text" readonly  name="login_id" value="<%=login_id%>"/></td></tr>


             <tr><td dir="<%=rtl%>"><%=resource.getString("websitename")%></td><td><input type="text" id="Editbox12" readonly name="institute_website" value="<%=website%>" tabindex="12" title="Enter Institute Website"></td></tr>
             <tr> <td style="color:blue" dir="<%=rtl%>"><%=resource.getString("admin.adminpending.libraryid")%></td><td><input type="text"  id="library_id" name="library_id" value="" tabindex="17" title="Enter Library Id"></td>

              </tr>
<tr><td dir="<%=rtl%>" colspan="4" align="center"><br><br>
        <input type="submit"  class="btn1"  id="submit" name="button" onclick="return validation();" value="Accept">
        <input type="submit"  class="btn1"  id="submit" name="button" value="Reject">
        <input type="button" class="btn1"    name="cancel" value="<%=resource.getString("back")%>"  onclick="quit();">
</td></tr>
            </table>
          </td></tr>
        </table>

<%}%>

 </html:form>

</body>
<%
String msg=(String)request.getAttribute("msg");
if(msg!=null){
%>
<script language="javascript">
    alert("<%=msg%>");

    //parent.location = "<%=request.getContextPath()%>/superadmin.do"
   // parent.document.getElementById('library_id').value="";

</script>
<%}%>
<script type="text/javascript"  language="javascript">


function validation()
    {

        var t= document.getElementById('library_id');
        var str="";
        if(t.value=="")
        {
            str+="\nPlease Enter  Library ID";
            alert(str);
            document.getElementById('library_id').focus();

             return false;
            }
            return true;

         }
function quit()
{

   parent.location="<%=request.getContextPath()%>/superadmin.do";
}
</script>

</html>
