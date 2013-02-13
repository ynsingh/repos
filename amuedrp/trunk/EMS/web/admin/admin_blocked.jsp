<%--
    Document   : admin_view
    Created on : Jun 13, 2010, 9:19:07 AM
    Author     : Dushyant
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.sql.*,com.myapp.struts.admin.*,com.myapp.struts.hbm.*,java.util.*" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>



<%@page import="java.util.*,java.io.*,java.net.*"%>

<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";
    String regid="";
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
   // regid = resource.getString("registrationid");
   // System.out.println("Reg id="+regid);
    %>



<link rel="stylesheet" href="/EMS/css/page.css"/>
<%
System.out.println("Session Id"+ session.getId());

try{
if(session.getAttribute("institute_id")!=null){
System.out.println("institute_id"+session.getAttribute("institute_id"));
}
else{
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %>sessionout();<%
    }
}catch(Exception e){
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %>sessionout();<%
    }

String id1=request.getParameter("id");
int id2=Integer.parseInt(id1);
List rst;
rst=(List)session.getAttribute("blocked_resultset");
AdminReg_Institute adminReg = new AdminReg_Institute();
if (rst!=null){
adminReg =(AdminReg_Institute) rst.get(0);}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Module</title>
<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
</head>
<body style=" background-image: url('/EMS/images/paperbg.gif'); margin-top:0; margin-bottom:0;">
    <html:form  action="/changeWorkingStatus" method="post"  onsubmit="return validation();">
         <table align="center"   width="800px" style="font-family: arial;font-size:13px" dir="<%=rtl%>" align="<%=align%>">


             <tr><td  align="left" colspan="2" >
                     Institute Details
                     <hr><br>

         </td></tr>

            <%if(rst!=null){%>
     <%
     Integer registration_request_id=adminReg.getAdminRegistration().getRegistrationId();

     String institute_name=adminReg.getAdminRegistration().getInstituteName();
     String abbreviated_name=adminReg.getAdminRegistration().getAbbreviatedName();
     String institute_address=adminReg.getAdminRegistration().getInstituteAddress();
     String city=adminReg.getAdminRegistration().getCity();
     String state1=adminReg.getAdminRegistration().getState();
     String country=adminReg.getAdminRegistration().getCountry();
     String pin=adminReg.getAdminRegistration().getPin();
     String land_line_no=adminReg.getAdminRegistration().getLandLineNo();
     String mobile_no=adminReg.getAdminRegistration().getMobileNo();
     String domain=adminReg.getAdminRegistration().getDomain();
     String type_of_institute=adminReg.getAdminRegistration().getTypeOfInstitute();
     String website=adminReg.getAdminRegistration().getWebsite();
     String admin_fname=adminReg.getAdminRegistration().getAdminFname();
     String admin_lname=adminReg.getAdminRegistration().getAdminLname();
     String admin_designation=adminReg.getAdminRegistration().getAdminDesignation();
     String admin_email=adminReg.getAdminRegistration().getAdminEmail();
     String admin_password=adminReg.getAdminRegistration().getAdminPassword();
     String status=adminReg.getAdminRegistration().getStatus();
     String institute_id=adminReg.getInstitute().getInstituteId();
     String working_status=adminReg.getAdminRegistration().getWorkingStatus();
    String user_id = adminReg.getAdminRegistration().getUserId();
     String courtesy=adminReg.getAdminRegistration().getCourtesy();
     String gender=adminReg.getAdminRegistration().getGender();

     if(registration_request_id==null)
         registration_request_id=-1;
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
     if(domain==null)
         domain="";
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
     if(institute_id==null)
         institute_id="";
     if(user_id==null)
         user_id="";
     
     if(courtesy==null)
         courtesy="";
     if(gender==null)
         gender="";
     %>
              <tr><td width="150px" dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("institutename")%></td><td><input type="text" id="Editbox1"   name="institute_name" value="<%=institute_name%>" tabindex="1" title="Enter Instutute Name" readonly></td><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("registrationid")%></td><td><input type="text" id="Editbox18"  name="registration_request_id" value="<%=registration_request_id%>" tabindex="18" readonly></td></tr>

             <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("instituteabbrevation")%></td><td><input type="text" id="Editbox2"   name="abbreviated_name" value="<%=abbreviated_name%>" tabindex="2" readonly title="Abbrivated name e.g. AMU(aligarh muslim University)"></td><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("courtesy")%></td><td>
       <input type="text" name="courtesy"  dir="<%=rtl%>" value="<%=courtesy%>" disabled size="1" id="courtesy"   tabindex="11" title="courtesy" style="width:148px"/>

        <%--             <select name="courtesy"  disabled size="1" id="courtesy"   tabindex="11" title="courtesy" style="width:148px">
     <%if(courtesy.equals("mr")){%>
<option selected value="mr"><%=resource.getString("Mr")%></option>
<option value="mrs"><%=resource.getString("Mrs")%></option>
<option value="ms"><%=resource.getString("Ms")%></option>

            <%}%>
            <%if(courtesy.equals("mrs")){%>
<option  value="mr"><%=resource.getString("Mr")%></option>
<option selected value="mrs"><%=resource.getString("Mrs")%></option>
<option value="ms"><%=resource.getString("Ms")%></option>
            <%}%>
            <%if(courtesy.equals("ms")){%>
<option value="mr"><%=resource.getString("Mr")%></option>
<option value="mrs"><%=resource.getString("Mrs")%></option>
<option  selected value="ms"><%=resource.getString("Ms")%></option>
            <%}%>

</select>--%>
                     <input type="hidden" id="courtesy" name="courtesy" value="<%=courtesy%>"/>
</td></tr>

             <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("instituteAddress")%></td><td><input type="text" id="Editbox3"   name="institute_address" value="<%=institute_address%>" tabindex="3" readonly title="Enter Address of Institute"></td><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("firstname")%></td><td><input type="text" id="Editbox13"  name="admin_fname" value="<%=admin_fname%>" tabindex="13" title="Enter first Name" readonly></td></tr>
             <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("lastname")%></td><td><input type="text" id="Editbox16"  name="admin_lname" value="<%=admin_lname%>" tabindex="14" title="Enter Last Name" readonly></td><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("userid")%></td><td><input type="text" id="userId"  name="userId" value="<%=user_id%>" tabindex="13" title="Enter UserId" readonly></td></tr>
              <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("city")%></td><td>

                 <input type="text" id="Editbox4"   name="city" value="<%=city%>" tabindex="4" title="Enter City " readonly>


                 </td><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("designation")%></td><td><input type="text" id="Editbox15"  name="admin_designation" value="<%=admin_designation%>" tabindex="15" title="Enter Designation" readonly></td></tr>
             <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("state")%></td><td><input type="text" id="Editbox5"   name="state" value="<%=state1%>" tabindex="5" title="Enter State" readonly></td><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("mobileno")%>
                 </td><td><input type="text" id="Editbox9"   name="mobile_no" value="<%=mobile_no%>" tabindex="9" title="Enter Mobile No with STD Code" readonly></td></tr>
             <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("country")%></td><td><input type="text" id="Editbox6"   name="country" value="<%=country%>" tabindex="6" title="Enter Country" readonly></td>
             <td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("emailid")%></td><td><input type="text"  id="Editbox14" readonly name="admin_email" value="<%=admin_email%>" tabindex="16" title="Enter E-mail Id">
             </tr>
             <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("pin")%></td><td><input type="text" id="Editbox7"  name="pin" value="<%=pin%>" tabindex="7" title="Enter PIN/ZIP Code" readonly></td>
             <td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("gender")%></td><td> <select name="gender" size="1" disabled id="gender" style="width:148px"   tabindex="11" title="gender">
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




             <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("landlineno")%></td><td><input type="text" id="Editbox8"   name="land_line_no" value="<%=land_line_no%>" tabindex="8" title="Enter Land Line No" readonly></td>
            <input type="hidden" id="Editbox11" readonly  name="admin_password" value="<%=admin_password%>" tabindex="17" title="Enter Password" readonly>
             </tr>

             <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("typeofinstitute")%></td><td><select name="type_of_institute" disabled id="type_of_institute" style="width:148px" >



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
             <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("institutedomain")%></td><td><input type="text" id="Editbox10" name="institute_domain" value="<%=domain%>" tabindex="10" title="Enter Institute Domain e.g amu.com" readonly></td></tr>
             <tr><td></td><td></td></tr>


             <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("websitename")%></td><td><input type="text" id="Editbox12" readonly name="institute_website" value="<%=website%>" tabindex="12" title="Enter Institute Website"></td></tr>
             
              <tr> <td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("instituteid")%></td><td><input type="text"  id="institute_id" name="institute_id" value="<%=institute_id%>" tabindex="17" title="Enter Institute Id" readonly></td>

              </tr>

               <tr> <td style="color:blue" dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("workingstatus")%></td><td><select  id="working_status" name="working_status"  tabindex="18" title="Select Working Status">
                         <%
                         if(working_status.equals("OK"))
                             {
                            %>
                            <option selected value="OK"><%=resource.getString("ok")%></option>
                            <option value="Blocked"><%=resource.getString("blocked")%></option>
                 <%
                            }
                            else
                                {
                    %>
                    <option selected value="Blocked"><%=resource.getString("blocked")%></option>
                    <option value="OK"><%=resource.getString("ok")%></option>
                    <%
                    }
                        %>
                         </select>
                 </td>

              </tr>
<tr><td colspan="4" align="center"><br><br>
        <input type="submit" class="txt2"  id="submit" name="submit" value="<%=resource.getString("accept")%>"><input type="button" class="txt2"    name="cancel" value="<%=resource.getString("back")%>" onclick="quit();">
</td></tr>
<%}%>
        </table>


 </html:form>

</body>

<script type="text/javascript"  language="javascript">
function validation()
    {

        var t= document.getElementById('institute_id');
        var str="";
        if(t.value=="")
        {
            str+="\nPlease Enter  Institute ID";
            alert(str);
            document.getElementById('institute_id').focus();

             return false;
            }
            return true;

         }
function quit()
{
    location.href="<%=request.getContextPath()%>"+"/admin/view_blocked_institute.jsp";
}
function sessionout()
{

    parent.location = "<%=request.getContextPath()%>"+"/login.jsp";

}
</script>

</html>



