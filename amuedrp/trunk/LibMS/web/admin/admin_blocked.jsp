<%--
    Document   : admin_view
    Created on : Jun 13, 2010, 9:19:07 AM
    Author     : Dushyant
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.sql.*,com.myapp.struts.MyQueryResult" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
<%
String id1=request.getParameter("id");
int id2=Integer.parseInt(id1);
ResultSet rst;
rst=(ResultSet)session.getAttribute("blocked_resultset");
rst.beforeFirst();
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Untitled Page</title>
<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
</head>
<body>
    <html:form  action="/changeWorkingStatus" method="post"  onsubmit="return validation();">
         <table align="center"  class="txt" width="800px" style="font-family: arial;font-weight: bold;color:brown;font-size:13px">


     <tr><td  align="left" colspan="2" ><br><br> <span class="txt"><img src="/LibMS-Struts/images/Institutereg.png">
</span><br>
             <br>


         </td></tr>

            <%if(rst.next()){%>
     <%
     String registration_request_id=rst.getString("registration_id");
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
     String working_status=rst.getString("working_status");
     String library_name=rst.getString("library_name");
     String courtesy=rst.getString("courtesy");
     String gender=rst.getString("gender");

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
              <tr><td width="150px">Institute Name</td><td><input type="text" id="Editbox1"   name="institute_name" value="<%=institute_name%>" tabindex="1" title="Enter Instutute Name" readonly></td><td>Registration_id</td><td><input type="text" id="Editbox18"  name="registration_request_id" value="<%=registration_request_id%>" tabindex="18" readonly></td></tr>

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
                     <input type="hidden" id="courtesy" name="courtesy" value="<%=courtesy%>"/>
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




<input type="hidden" id="gender" name="gender" value="<%=gender%>"/>




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


             <tr><td>website name</td><td><input type="text" id="Editbox12" readonly name="institute_website" value="<%=website%>" tabindex="12" title="Enter Institute Website"></td></td></tr>
            
              <tr> <td >Library Id</td><td><input type="text"  id="library_id" name="library_id" value="<%=library_id%>" tabindex="17" title="Enter Library Id" readonly></td>

              </tr>

               <tr> <td style="color:blue">Working Status</td><td><select  id="working_status" name="working_status"  tabindex="18" title="Select Working Status">
                         <%
                         if(working_status.equals("OK"))
                             {
                            %>
                            <option selected value="OK">OK</option>
                            <option value="Blocked">Blocked</option>
                 <%
                            }
                            else
                                {
                    %>
                    <option selected value="Blocked">Blocked</option>
                    <option value="OK">OK</option>
                    <%
                    }
                        %>
                         </select>
                 </td>

              </tr>
<tr><td colspan="4" align="center"><br><br>
        <input type="submit" class="txt2"  id="submit" name="submit" value="Accept"><input type="button" class="txt2"    name="cancel" value="Back" onclick="quit();">
</td></tr>
<%}%>
        </table>


 </html:form>

</body>

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
    parent.location="/LibMS-Struts/admin/admin_home.jsp";
}
</script>

</html>
