
<%-- 
    Document   : admin_view
    Created on : Jun 13, 2010, 9:19:07 AM
    Author     : Dushyant
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>

<%@page pageEncoding="UTF-8"%>
<%@page import="java.sql.*,com.myapp.struts.admin.AdminReg_Institute,com.myapp.struts.hbm.*,java.util.*" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

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
System.out.println("library_id"+session.getAttribute("library_id"));
}
else{
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/login.jsp?session=\"expired\"";</script><%
    }
}catch(Exception e){
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %>sessionout();<%
    }

List rst=(List)request.getAttribute("resultset");
//int id2=Integer.parseInt(id1);
//List rst;
//AdminRegistrationDAO admindao = new AdminRegistrationDAO();
AdminReg_Institute adminReg=new AdminReg_Institute();
System.out.println("AdminRegistration"+rst);
         //rst=admindao.getAdminDeatilsById(id2);
if (rst!=null && rst.get(0).getClass().equals(AdminReg_Institute.class))
   {
    adminReg = (AdminReg_Institute)rst.get(0);
    System.out.println("AdminReg_Institute"+rst.getClass());
}
if (rst!=null && rst.get(0).getClass().equals(AdminRegistration.class))
{
    AdminRegistration admin = (AdminRegistration)rst.get(0);
    adminReg.setAdminRegistration(admin);
    System.out.println("AdminRegistration"+rst.getClass());
}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Untitled Page</title>
<link rel="stylesheet" href="/LibMS/css/page.css"/>
<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
</head>
<body>
    <html:form  method="post" action="/confirm">
        <table align="center" dir="<%=rtl%>"  class="table" bgcolor="#7697BC" width="60%" >


            <tr><td  align="center" class="txtStyle1" height="20px">Institute Details


         </td></tr>
            <%if(rst!=null){%>
     <%
     String registration_id=String.valueOf(adminReg.getAdminRegistration().getRegistrationId());
     String institute_name=adminReg.getAdminRegistration().getInstituteName();
     String abbreviated_name=adminReg.getAdminRegistration().getAbbreviatedName();
     String institute_address=adminReg.getAdminRegistration().getInstituteAddress();
     String city=adminReg.getAdminRegistration().getCity();
     String state1=adminReg.getAdminRegistration().getState();
     String country=adminReg.getAdminRegistration().getCountry();
     String pin=adminReg.getAdminRegistration().getPin();
     String land_line_no=adminReg.getAdminRegistration().getLandLineNo();
     String mobile_no=adminReg.getAdminRegistration().getMobileNo();
     
     String type_of_institute=adminReg.getAdminRegistration().getTypeOfInstitute();
     String website=adminReg.getAdminRegistration().getWebsite();
     String admin_fname=adminReg.getAdminRegistration().getAdminFname();
     String admin_lname=adminReg.getAdminRegistration().getAdminLname();
     String admin_designation=adminReg.getAdminRegistration().getAdminDesignation();
     String admin_email=adminReg.getAdminRegistration().getAdminEmail();
     String admin_password=adminReg.getAdminRegistration().getAdminPassword();
     String status=adminReg.getAdminRegistration().getStatus();
     String courtesy=adminReg.getAdminRegistration().getCourtesy();
     String gender=adminReg.getAdminRegistration().getGender();
     String user_id = adminReg.getAdminRegistration().getLoginId();
     String library_name=adminReg.getAdminRegistration().getLibraryName();
     String institute_Id=null;
     if(adminReg.getLibrary()!=null){
     institute_Id = adminReg.getLibrary().getLibraryId();}
     if(registration_id==null)
         registration_id="";
     if(institute_name==null)
         institute_name="";
     if(abbreviated_name==null)
         abbreviated_name="";
     if(library_name==null)
         library_name="";
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
     if(institute_Id==null)
         institute_Id="";
     if(user_id==null)
         user_id="";
     %>
            <tr><td>
            <table class="txtStyle" bgcolor="white" >
            <tr><td width="15%" dir="<%=rtl%>"><%=resource.getString("institutename")%></td><td width="15%"><input type="text" id="Editbox1"   name="institute_name" value="<%=institute_name%>" tabindex="1" title="Enter Instutute Name" readonly></td><td width="15%"><%=resource.getString("registrationid")%></td><td width="15%"><input type="text" id="Editbox18"  name="registration_request_id" value="<%=registration_id%>" tabindex="18" readonly></td></tr>

            <tr><td dir="<%=rtl%>"><%=resource.getString("instituteabbrevation")%></td><td><input type="text" id="Editbox2" dir="<%=rtl%>"   name="abbreviated_name" value="<%=abbreviated_name%>" tabindex="2" readonly title="Abbrivated name e.g. AMU(aligarh muslim University)"></td><td dir="<%=rtl%>"><%=resource.getString("courtesy")%></td><td>
                    <input type="text" id="courtesy" readonly name="courtesy" value="<%=courtesy%>"/>

                   
</td></tr>

     <tr><td dir="<%=rtl%>"><%=resource.getString("instituteAddress")%></td><td><input type="text" id="Editbox3" dir="<%=rtl%>"   name="institute_address" value="<%=institute_address%>" tabindex="3" readonly title="Enter Address of Institute"></td><td dir="<%=rtl%>"><%=resource.getString("firstname")%></td><td><input type="text" id="Editbox13"  name="admin_fname" value="<%=admin_fname%>" tabindex="13" title="Enter first Name" readonly></td></tr>
             <tr><td dir="<%=rtl%>"><%=resource.getString("lastname")%></td><td dir="<%=rtl%>"><input type="text" id="Editbox16"  name="admin_lname" value="<%=admin_lname%>" tabindex="14" title="Enter Last Name" readonly dir="<%=rtl%>"></td><td dir="<%=rtl%>"><%=resource.getString("userid")%></td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="userId"  name="userId" value="<%=user_id%>" tabindex="13" title="Enter UserId" readonly></td></tr>
              <tr><td dir="<%=rtl%>"><%=resource.getString("city")%></td><td>

                 <input type="text" dir="<%=rtl%>" id="Editbox4"   name="city" value="<%=city%>" tabindex="4" title="Enter City " readonly>


                 </td><td dir="<%=rtl%>"><%=resource.getString("designation")%></td><td><input type="text" id="Editbox15"  name="admin_designation" value="<%=admin_designation%>" tabindex="15" title="Enter Designation" dir="<%=rtl%>" readonly></td></tr>
             <tr><td dir="<%=rtl%>"><%=resource.getString("state")%></td><td dir="<%=rtl%>"><input type="text" id="Editbox5"   name="state" value="<%=state1%>" tabindex="5" title="Enter State" dir="<%=rtl%>" readonly></td>
                 <td dir="<%=rtl%>"><%=resource.getString("mobileno")%></td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="Editbox9"   name="mobile_no" value="<%=mobile_no%>" tabindex="9" title="Enter Mobile No with STD Code" readonly></td></tr>
             <tr><td dir="<%=rtl%>"><%=resource.getString("country")%></td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="Editbox6"   name="country" value="<%=country%>" tabindex="6" title="Enter Country" readonly></td>
             <td dir="<%=rtl%>"><%=resource.getString("emailid")%></td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>"  id="Editbox14" readonly name="admin_email" value="<%=admin_email%>" tabindex="16" title="Enter E-mail Id">
             </tr>
             <tr><td dir="<%=rtl%>"><%=resource.getString("pin")%></td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="Editbox7"  name="pin" value="<%=pin%>" tabindex="7" title="Enter PIN/ZIP Code" readonly></td>
             <td dir="<%=rtl%>"><%=resource.getString("gender")%></td><td dir="<%=rtl%>"> <select name="gender" dir="<%=rtl%>" size="1" disabled id="gender" style="width:148px"   tabindex="11" title="gender">
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

                    





            

             <tr><td dir="<%=rtl%>"><%=resource.getString("landlineno")%></td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="Editbox8"   name="land_line_no" value="<%=land_line_no%>" tabindex="8" title="Enter Land Line No" readonly></td>
            <%-- <td dir="<%=rtl%>"><%=resource.getString("password")%></td><td dir="<%=rtl%>"><input type="password" id="Editbox11" readonly  name="admin_password" dir="<%=rtl%>" value="<%=admin_password%>" tabindex="17" title="Enter Password" readonly></td>--%>
             </tr>

             <tr><td dir="<%=rtl%>"><%=resource.getString("typeofinstitute")%></td><td dir="<%=rtl%>"><select name="type_of_institute" disabled id="type_of_institute" style="width:148px" dir="<%=rtl%>" >



    <%if(type_of_institute!=null && type_of_institute.equals("govt")){%>
<option selected value="govt"><%=resource.getString("govt")%></option>
<option value="semi govt"><%=resource.getString("semigovt")%></option>
<option value="self financed"><%=resource.getString("selffinanced")%></option>
<option value="other"><%=resource.getString("other")%></option>
<option  value="Select"><%=resource.getString("select")%></option>
            <%}%>
            <%if(type_of_institute!=null && type_of_institute.equals("semi_govt")){%>
<option value="govt"><%=resource.getString("govt")%></option>
<option selected value="semi govt"><%=resource.getString("semigovt")%></option>
<option value="self financed"><%=resource.getString("selffinanced")%></option>
<option value="other"><%=resource.getString("other")%></option>
<option value="Select"><%=resource.getString("select")%></option>
            <%}%>
            <%if(type_of_institute!=null && type_of_institute.equals("self_financed")){%>
<option  value="govt"><%=resource.getString("govt")%></option>
<option value="semi govt"><%=resource.getString("semigovt")%></option>
<option selected value="self financed"><%=resource.getString("selffinanced")%></option>
<option value="other"><%=resource.getString("other")%></option>
<option  value="Select"><%=resource.getString("select")%></option>
            <%}%>
            <%if(type_of_institute!=null && type_of_institute.equals("other")){%>
<option  value="govt"><%=resource.getString("govt")%></option>
<option value="semi govt"><%=resource.getString("semigovt")%></option>
<option value="self financed"><%=resource.getString("selffinanced")%></option>
<option selected value="other"><%=resource.getString("other")%></option>
<option  value="Select"><%=resource.getString("select")%></option>
            <%}%>

</select></td> <tr><td><%=resource.getString("libraryname")%></td><td><input type="text"  id="Editbox14" readonly name="library_name" value="<%=library_name%>" tabindex="16" title="Enter Library Name"></td>
  
             </tr>

             <tr><td dir="<%=rtl%>"></td><td dir="<%=rtl%>"></td></tr>

            
             <tr><td dir="<%=rtl%>"><%=resource.getString("websitename")%></td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="Editbox12" readonly name="institute_website" value="<%=website%>" tabindex="12" title="Enter Institute Website"></td></tr>
             <tr><td dir="<%=rtl%>"><%=resource.getString("instituteid")%></td><td dir="<%=rtl%>"><input type="text"  id="Institute_id" dir="<%=rtl%>" name="Institute_id" value="<%=institute_Id%>"  readonly tabindex="17" title="Enter Institute Id"></td>
              
              </tr>
<tr><td dir="<%=rtl%>" colspan="4" align="center"><br>

      


        <input type="button" dir="<%=rtl%>" class="btn1"  name="cancel" value="<%=resource.getString("back")%>" onclick="quit();">
</td></tr
<%}%>
            </table>
                </td></tr>
        </table>
        
    </html:form>

</body>
<script language="javascript" type="text/javascript">
    function quit()
    {
        history.back(-2);
    }

</script>
</html>
