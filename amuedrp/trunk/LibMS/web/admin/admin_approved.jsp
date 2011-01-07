<%-- 
    Document   : admin_view
    Created on : Jun 13, 2010, 9:19:07 AM
    Author     : Dushyant
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>

<%@page pageEncoding="UTF-8"%>
<%@page import="java.sql.*,com.myapp.struts.opac.MyQueryResult" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%
String id1=request.getParameter("id");
int id2=Integer.parseInt(id1);
ResultSet rst;


         rst=MyQueryResult.getMyExecuteQuery("select * from admin_registration where registration_id="+id2);




%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Untitled Page</title>
<link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
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
  <table align="center"  class="txt" width="800px" style="font-family: arial;font-weight: bold;color:brown;font-size:13px">


     <tr><td  align="left" colspan="2" ><br><br> <span class="txt"><img src="/LibMS-Struts/images/Institutereg.png">
</span><br>
             <br>


         </td></tr>
            <%if(rst.next()){%>
     <%
     String registration_id=rst.getString("registration_id");
     String institute_name=rst.getString("institute_name");
     String abbreviated_name=rst.getString("abbreviated_name");
     String institute_address=rst.getString("Institute_address");;
     String city=rst.getString("city");
     String state1=rst.getString("state");
     String country=rst.getString("country");
     String pin=rst.getString("pin");
     String land_line_no=rst.getString("land_line_no");
     String mobile_no=rst.getString("mobile_no");
     String domain=rst.getString("domain");
     String type_of_institute=rst.getString("type_of_institute");
     String website=rst.getString("website");
     String admin_fname=rst.getString("admin_fname");
     String admin_lname=rst.getString("admin_lname");
     String admin_designation=rst.getString("admin_designation");
     String admin_email=rst.getString("admin_email");
     String admin_password=rst.getString("admin_password");
     String status=rst.getString("status");
     String library_id=rst.getString("library_id");
     String library_name=rst.getString("library_name");
     String courtesy=rst.getString("courtesy");
     String gender=rst.getString("gender");

     if(registration_id==null)
         registration_id="";
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
     if(library_id==null)
         library_id="";
     if(library_name==null)
         library_name="";
     if(courtesy==null)
         courtesy="";
     if(gender==null)
         gender="";
     %>
              <tr><td width="150px">Institute Name</td><td><input type="text" id="Editbox1"   name="institute_name" value="<%=institute_name%>" tabindex="1" title="Enter Instutute Name" readonly></td><td>Registration_id</td><td><input type="text" id="Editbox18"  name="registration_request_id" value="<%=registration_id%>" tabindex="18" readonly></td></tr>

             <tr><td>Institute Abbrivation</td><td><input type="text" id="Editbox2"   name="abbreviated_name" value="<%=abbreviated_name%>" tabindex="2" readonly title="Abbrivated name e.g. AMU(aligarh muslim University)"></td><td>Courtesy</td><td> <select name="courtesy"  disabled size="1" id="courtesy"   tabindex="11" title="courtesy" style="width:148px">
    <%if(courtesy.equals("mr")){%>
<option selected value="mr">Mr</option>
<option value="mrs">Mrs</option>
<option value="ms">Ms.</option>

            <%}%>
            <%if(courtesy.equals("mrs")){%>
<option  value="mr">Mr</option>
<option selected value="mrs">Mrs</option>
<option value="ms">Ms.</option>
            <%}%>
            <%if(courtesy.equals("ms")){%>
<option value="mr">Mr</option>
<option value="mrs">Mrs</option>
<option  selected value="ms">Ms.</option>
            <%}%>

</select>
</td></tr>

             <tr><td>Institute Address</td><td><input type="text" id="Editbox3"   name="institute_address" value="<%=institute_address%>" tabindex="3" readonly title="Enter Address of Institute"></td><td>First Name</td><td><input type="text" id="Editbox13"  name="admin_fname" value="<%=admin_fname%>" tabindex="13" title="Enter first Name" readonly></td></tr>
             <tr><td>Library Name</td><td><input type="text"  id="Editbox14" readonly name="library_name" value="<%=library_name%>" tabindex="16" title="Enter Library Name"></td><td>Last Name</td><td><input type="text" id="Editbox16"  name="admin_lname" value="<%=admin_lname%>" tabindex="14" title="Enter Last Name" readonly></td></tr>
              <tr><td>City</td><td>

                 <input type="text" id="Editbox4"   name="city" value="<%=city%>" tabindex="4" title="Enter City " readonly>


                 </td><td>Designation</td><td><input type="text" id="Editbox15"  name="admin_designation" value="<%=admin_designation%>" tabindex="15" title="Enter Designation" readonly></td></tr>
             <tr><td>State</td><td><input type="text" id="Editbox5"   name="state" value="<%=state1%>" tabindex="5" title="Enter State" readonly></td><td>Mobile No
                 </td><td><input type="text" id="Editbox9"   name="mobile_no" value="<%=mobile_no%>" tabindex="9" title="Enter Mobile No with STD Code" readonly></td></tr>
             <tr><td>Country</td><td><input type="text" id="Editbox6"   name="country" value="<%=country%>" tabindex="6" title="Enter Country" readonly></td>
             <td>Email ID</td><td><input type="text"  id="Editbox14" readonly name="admin_email" value="<%=admin_email%>" tabindex="16" title="Enter E-mail Id">
             </tr>
             <tr><td>PIN</td><td><input type="text" id="Editbox7"  name="pin" value="<%=pin%>" tabindex="7" title="Enter PIN/ZIP Code" readonly></td>
             <td>Gender</td><td> <select name="gender" size="1" disabled id="gender" style="width:148px"   tabindex="11" title="gender">
            <%if(gender.equals("male")){%>
            <option selected value="male">Male</option>
            <option value="female">Female</option>


            <%}%>
            <%if(gender.equals("female")){%>
            <option  value="male">Male</option>
            <option selected value="female">Female</option>

            <%}%>

            </select>
            </td>
            </tr>

                    





            

             <tr><td>Landline no</td><td><input type="text" id="Editbox8"   name="land_line_no" value="<%=land_line_no%>" tabindex="8" title="Enter Land Line No" readonly></td>
             <td>Password</td><td><input type="password" id="Editbox11" readonly  name="admin_password" value="<%=admin_password%>" tabindex="17" title="Enter Password" readonly></td>
             </tr>

             <tr><td>Type of Institute</td><td><select name="type_of_institute" disabled id="type_of_institute" style="width:148px" >



    <%if(type_of_institute.equals("govt")){%>
<option selected value="govt">Govt</option>
<option value="semi govt">Semi Govt</option>
<option value="self financed">Self Financed</option>
<option value="other">Other</option>
<option  value="Select">Select</option>
            <%}%>
            <%if(type_of_institute.equals("semi_govt")){%>
<option value="govt">Govt</option>
<option selected value="semi govt">Semi Govt</option>
<option value="self financed">Self Financed</option>
<option value="other">Other</option>
<option value="Select">Select</option>
            <%}%>
            <%if(type_of_institute.equals("self_financed")){%>
<option  value="govt">Govt</option>
<option value="semi govt">Semi Govt</option>
<option selected value="self financed">Self Financed</option>
<option value="other">Other</option>
<option  value="Select">Select</option>
            <%}%>
            <%if(type_of_institute.equals("other")){%>
<option  value="govt">Govt</option>
<option value="semi govt">Semi Govt</option>
<option value="self financed">Self Financed</option>
<option selected value="other">Other</option>
<option  value="Select">Select</option>
            <%}%>

</select></td>
  
             </tr>
             <tr><td>Institute Domain</td><td><input type="text" id="Editbox10" name="institute_domain" value="<%=domain%>" tabindex="10" title="Enter Institute Domain e.g amu.com" readonly></td></tr>
             <tr><td></td><td></td></tr>

            
             <tr><td>website name</td><td><input type="text" id="Editbox12" readonly name="institute_website" value="<%=website%>" tabindex="12" title="Enter Institute Website"></td></tr>
             <tr><td>Library ID</td><td><input type="text"  id="library_id" name="library_id" value="<%=library_id%>"  readonly tabindex="17" title="Enter Library Id"></td>
              
              </tr>
<tr><td colspan="4" align="center"><br><br>
        <input type="button" class="txt2"   name="cancel" value="Back" onclick="quit();">
</td></tr
<%}%>
        </table>
        <%--
<hr id="Line1" style="color:#1191BB;background-color:#1191BB;border:0px;margin:0;padding:0;position:absolute;left:26px;top:137px;width:1205px;height:15px;z-index:2">
<div id="wb_Text1" style="position:absolute;left:400px;top:166px;width:363px;height:24px;z-index:1;" align="left">
<font style="FONT-SIZE: 21px" color="#000000" face="Arial"><b>Institute Administrator Registration</b></font></div>
<div id="wb_Text2" style="position:absolute;left:25px;top:200px;width:201px;height:16px;z-index:2;" align="right">
<font style="font-size:13px" color="#000000" face="Arial"><b>Institute Name </b></font><font style="font-size:13px" color="#FF0000" face="Arial"><b>*</b></font></div>
<div id="wb_Text4" style="position:absolute;left:25px;top:260px;width:201px;height:16px;z-index:3;" align="right">
<font style="font-size:13px" color="#000000" face="Arial"><b>Institute Address </b></font><font style="font-size:13px" color="#FF0000" face="Arial"><b>*</b></font></div>
<div id="wb_Text5" style="position:absolute;left:25px;top:290px;width:201px;height:16px;z-index:4;" align="right">
<font style="font-size:13px" color="#000000" face="Arial"><b>City </b></font><font style="font-size:13px" color="#FF0000" face="Arial"><b>*</b></font></div>
<div id="wb_Text6" style="position:absolute;left:25px;top:320px;width:201px;height:16px;z-index:5;" align="right">
<font style="font-size:13px" color="#000000" face="Arial"><b>State </b></font><font style="font-size:13px" color="#FF0000" face="Arial"><b>*</b></font></div>
<div id="wb_Text7" style="position:absolute;left:25px;top:350px;width:201px;height:16px;z-index:6;" align="right">
<font style="font-size:13px" color="#000000" face="Arial"><b>Country </b></font><font style="font-size:13px" color="#FF0000" face="Arial"><b>*</b></font></div>
<div id="wb_Text8" style="position:absolute;left:25px;top:380px;width:201px;height:16px;z-index:7;" align="right">
<font style="font-size:13px" color="#000000" face="Arial"><b>Pin Code/Zip Code&nbsp; </b></font><font style="font-size:13px" color="#FF0000" face="Arial"><b>*</b></font></div>
<div id="wb_Text12" style="position:absolute;left:654px;top:230px;width:192px;height:16px;z-index:8;" align="right">
<font style="font-size:13px" color="#000000" face="Arial"><b>Administrator's Last Name </b></font><font style="font-size:13px" color="#FF0000" face="Arial"><b>*</b></font></div>
<div id="wb_Text13" style="position:absolute;left:654px;top:260px;width:192px;height:16px;z-index:9;" align="right">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b>Administrator's Designation </b></font><font style="FONT-SIZE: 13px" color="#ff0000" face="Arial"><b>*</b></font></div>
<%if(rst.next()){%>
<input type="text" id="Editbox1" disabled="true" style="position:absolute;left:235px;top:200px;width:368px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:10" name="institute_name" value="<%=rst.getString(2)%>" tabindex="1" title="Enter Instutute Name">
<input type="hidden" name="institute_name" value="<%=rst.getString(2)%>">
<input type="text" id="Editbox2" disabled="true" style="position:absolute;left:235px;top:230px;width:368px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:11" name="abbreviated_name" value="<%=rst.getString(3)%>" tabindex="2" title="Abbrivated name e.g. AMU(aligarh muslim University)">
<input type="hidden" name="abbreviated_name" value="<%=rst.getString(3)%>">
<input type="text" id="Editbox3" disabled="true" style="position:absolute;left:235px;top:260px;width:368px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:12" name="institute_address" value="<%=rst.getString(4)%>" tabindex="3" title="Enter Address of Institute">
<input type="hidden" name="institute_address" value="<%=rst.getString(4)%>" tabindex="3">
<input type="text" id="Editbox4" disabled="true" style="position:absolute;left:235px;top:290px;width:368px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:13" name="city" value="<%=rst.getString(5)%>" tabindex="4" title="Enter City ">
<input type="hidden"  name="city" value="<%=rst.getString(5)%>">
<input type="text" id="Editbox5" disabled="true" style="position:absolute;left:235px;top:320px;width:368px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:14" name="state" value="<%=rst.getString(6)%>" tabindex="5" title="Enter State">
<input type="hidden" name="state" value="<%=rst.getString(6)%>">
<input type="text" id="Editbox6" disabled="true" style="position:absolute;left:235px;top:350px;width:368px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:15" name="country" value="<%=rst.getString(7)%>" tabindex="6" title="Enter Country">
<input type="hidden" name="country" value="<%=rst.getString(7)%>" >
<input type="text" id="Editbox7" disabled="true" style="position:absolute;left:235px;top:380px;width:368px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:16" name="pin" value="<%=rst.getString(8)%>" tabindex="7" title="Enter PIN/ZIP Code">
<input type="hidden" name="pin" value="<%=rst.getString(8)%>">
<input type="text" id="Editbox8" disabled="true" style="position:absolute;left:235px;top:410px;width:368px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:17" name="land_line_no" value="<%=rst.getString(9)%>" tabindex="8" title="Enter Land Line No">
<input type="hidden" name="land_line_no" value="<%=rst.getString(9)%>">
<input type="text" id="Editbox9" disabled="true" style="position:absolute;left:235px;top:440px;width:368px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:18" name="mobile_no" value="<%=rst.getString(10)%>" tabindex="9" title="Enter Mobile No with STD Code">
<input type="hidden"name="mobile_no" value="<%=rst.getString(10)%>" >
<input type="text" id="Editbox10" disabled="true" style="position:absolute;left:280px;top:470px;width:323px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:19" name="institute_domain" value="<%=rst.getString(11)%>" tabindex="10" title="Enter Institute Domain e.g amu.com">
<input type="hidden" name="institute_domain" value="<%=rst.getString(11)%>">
<input type="text" id="Editbox11" disabled="true" style="position:absolute;left:850px;top:320px;width:368px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:20" name="admin_password" value="<%=rst.getString(18)%>" tabindex="17" title="Enter Password">
<input type="hidden" name="admin_password" value="<%=rst.getString(18)%>">
<input type="text" id="Editbox18" disabled="true" style="position:absolute;left:850px;top:350px;width:368px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:20" name="registration_request_id" value="<%=rst.getInt(1)%>" tabindex="18" >
<input type="hidden" name="registration_request_id" value="<%=rst.getInt(1)%>">
<input type="text" id="Editbox19" style="position:absolute;left:850px;top:380px;width:368px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:20" name="library_id" value="<%=rst.getString("library_id")%>"  tabindex="19" title="Enter Library Id" disabled/>

<div style="position:absolute;left:235px;top:500px;width:135px;height:18px;border:1px #C0C0C0 solid;z-index:21">
<select name="type_of_institute" size="1" id="Combobox1" disabled="true" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;" tabindex="11" title="Select type of Institute">
    <%if(rst.getString(12).equals("govt")){%>
<option selected value="govt">Govt</option>
<option value="semi govt">Semi Govt</option>
<option value="self financed">Self Financed</option>
<option value="other">Other</option>
            <%}%>
            <%if(rst.getString(12).equals("Semi Govt")){%>
<option value="govt">Govt</option>
<option selected value="semi govt">Semi Govt</option>
<option value="self financed">Self Financed</option>
<option value="other">Other</option>
            <%}%>
            <%if(rst.getString(12).equals("self financed")){%>
<option  value="govt">Govt</option>
<option value="semi govt">Semi Govt</option>
<option selected value="self financed">Self Financed</option>
<option value="other">Other</option>
            <%}%>
            <%if(rst.getString(12).equals("other")){%>
<option  value="govt">Govt</option>
<option value="semi govt">Semi Govt</option>
<option value="self financed">Self Financed</option>
<option selected value="other">Other</option>
            <%}%>

</select>
</div>
            <input type="hidden" name="type_of_institute" value="<%=rst.getString(12)%>">


            <!--gender display and courtesy display-->


            <div style="position:absolute;left:235px;top:600px;width:135px;height:18px;border:1px #C0C0C0 solid;z-index:21">
<select name="courtesy" size="1" id="courtesy" disabled="true" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;" tabindex="11" title="courtesy">
    <%if(rst.getString(22).equals("mr")){%>
<option selected value="mr">Mr</option>
<option value="mrs">Mrs</option>
<option value="ms">Ms.</option>

            <%}%>
            <%if(rst.getString(22).equals("mrs")){%>
<option  value="mr">Mr</option>
<option selected value="mrs">Mrs</option>
<option value="ms">Ms.</option>
            <%}%>
            <%if(rst.getString(22).equals("ms")){%>
<option value="mr">Mr</option>
<option value="mrs">Mrs</option>
<option  selected value="ms">Ms.</option>
            <%}%>

</select>
</div>
            <input type="hidden" name="courtesy" value="<%=rst.getString(22)%>">



<div style="position:absolute;left:235px;top:650px;width:135px;height:18px;border:1px #C0C0C0 solid;z-index:21">
<select name="gender" size="1" id="gender" disabled="true" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;" tabindex="11" title="gender">
    <%if(rst.getString(23).equals("male")){%>
<option selected value="male">Male</option>
<option value="female">Female</option>


            <%}%>
            <%if(rst.getString(23).equals("female")){%>
<option  value="male">Male</option>
<option selected value="female">Female</option>

            <%}%>

</select>
</div>
            <input type="hidden" name="female" value="<%=rst.getString(23)%>">



















<div id="wb_Text14" style="position:absolute;left:238px;top:473px;width:19px;height:16px;z-index:22;" align="left">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b>@</b></font></div>
<div id="wb_Text15" style="position:absolute;left:232px;top:533px;width:41px;height:16px;z-index:23;" align="left">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b>http://</b></font></div>
<div id="wb_Text16" style="position:absolute;left:30px;top:181px;width:152px;height:16px;z-index:24;" align="left">
<font style="FONT-SIZE: 13px" color="#ff0000" face="Arial"><b>* Fields are mendatory</b></font></div>

<input type="button" id="Button2" name="cancel" value="Cancel" style="position:absolute;left:1000px;top:450px;width:96px;height:25px;font-family:Arial;font-size:13px;z-index:26">
<input type="text" id="Editbox12" disabled="true" style="position:absolute;left:276px;top:530px;width:323px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:27" name="institute_website" value="<%=rst.getString(13)%>" tabindex="12" title="Enter Institute Website">
<input type="hidden" name="institute_website" value="<%=rst.getString(13)%>">
<div id="wb_Text18" style="position:absolute;left:654px;top:320px;width:192px;height:16px;z-index:28;" align="right">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b>Administrator Password </b></font><font style="FONT-SIZE: 13px" color="#ff0000" face="Arial"><b>*</b></font></div>
<input type="text" id="Editbox13" disabled="true" style="position:absolute;left:850px;top:200px;width:371px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:29" name="admin_fname" value="<%=rst.getString(14)%>" tabindex="13" title="Enter first Name">
<input type="hidden" name="admin_fname" value="<%=rst.getString(14)%>">
<div id="wb_Text18" style="position:absolute;left:654px;top:350px;width:192px;height:16px;z-index:28;" align="right">
<font style="FONT-SIZE: 13px" color="" face="Arial"><b>Request Id for Registration </b></font><font style="FONT-SIZE: 13px" color="#ff0000" face="Arial"><b>*</b></font></div>
<div id="wb_Text18" style="position:absolute;left:654px;top:380px;width:192px;height:16px;z-index:28;" align="right">
<font style="FONT-SIZE: 13px" color="blue" face="Arial"><b>Library Id </b></font><font style="FONT-SIZE: 13px" color="#ff0000" face="Arial"><b>*</b></font></div>

<div id="wb_Text19" style="position:absolute;left:25px;top:440px;width:201px;height:16px;z-index:31;" align="right">
<font style="font-size:13px" color="#000000" face="Arial"><b>Mobile No </b></font><font style="font-size:13px" color="#FF0000" face="Arial"><b>*</b></font></div>
<div id="wb_Text20" style="position:absolute;left:25px;top:410px;width:201px;height:16px;z-index:32;" align="right">
<font style="font-size:13px" color="#000000" face="Arial"><b>Land Line No </b></font><font style="font-size:13px" color="#FF0000" face="Arial"><b>*</b></font></div>
<div id="wb_Text21" style="position:absolute;left:25px;top:470px;width:201px;height:16px;z-index:33;" align="right">
<font style="font-size:13px" color="#000000" face="Arial"><b>Institute Domain </b></font><font style="font-size:13px" color="#FF0000" face="Arial"><b>*</b></font></div>
<div id="wb_Text22" style="position:absolute;left:25px;top:500px;width:201px;height:16px;z-index:34;" align="right">
<font style="font-size:13px" color="#000000" face="Arial"><b>Type of Institute </b></font><font style="font-size:13px" color="#FF0000" face="Arial"><b>*</b></font></div>
<div id="wb_Text23" style="position:absolute;left:25px;top:530px;width:201px;height:16px;z-index:35;" align="right">
<font style="font-size:13px" color="#000000" face="Arial"><b>Institute Website </b></font><font style="font-size:13px" color="#FF0000" face="Arial"><b>*</b></font></div>
<div id="wb_Text24" style="position:absolute;left:653px;top:200px;width:192px;height:16px;z-index:36;" align="right">
<font style="FONT-SIZE: 13px" color="#000000" face="Arial"><b>Administrator's First Name </b></font><font style="FONT-SIZE: 13px" color="#ff0000" face="Arial"><b>*</b></font></div>
<div id="wb_Text27" style="position:absolute;left:654px;top:290px;width:192px;height:16px;z-index:37;" align="right">
<font style="font-size:13px" color="#000000" face="Arial"><b>Administrator's E-mail </b></font><font style="font-size:13px" color="#FF0000" face="Arial"><b>*</b></font></div>
<div id="wb_Text29" style="position:absolute;left:25px;top:230px;width:201px;height:16px;z-index:38;" align="right">
<font style="font-size:13px" color="#000000" face="Arial"><b>Abbreviated Name of Institute </b></font></div>

<input type="text" disabled="true" id="Editbox14"style="position:absolute;left:850px;top:290px;width:371px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:39" name="admin_email" value="<%=rst.getString(17)%>" tabindex="16" title="Enter E-mail Id">
<input type="hidden" name="admin_email" value="<%=rst.getString(17)%>">
<input type="text" id="Editbox15" disabled="true"style="position:absolute;left:850px;top:260px;width:371px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:40" name="admin_designation" value="<%=rst.getString(16)%>" tabindex="15" title="Enter Designation">
<input type="hidden" name="admin_designation" value="<%=rst.getString(16)%>">
<input type="text" id="Editbox16" disabled="true" style="position:absolute;left:850px;top:230px;width:371px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:41" name="admin_lname" value="<%=rst.getString(15)%>" tabindex="14" title="Enter Last Name">
<input type="hidden" name="admin_lname" name="admin_lname" value="<%=rst.getString(15)%>">
<%}%>--%>
    </html:form>

</body>

</html>
<script>
    function quit()
    {
        history.back(-2);
    }

</script>