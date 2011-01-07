<%--
    Document   : admin_view
    Created on : Jun 13, 2010, 9:19:07 AM
    Author     : Dushyant
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>
 <jsp:include page="header.jsp" flush="true" />
<%@page pageEncoding="UTF-8"%>
<%@page import="java.sql.*,com.myapp.struts.opac.MyQueryResult" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%

String id1=request.getParameter("id");
ResultSet rst;


        // rst=MyQueryResult.getMyExecuteQuery("select * from staff_detail where staff_id="+id1);
rst = (ResultSet)request.getAttribute("simple_resultset");
rst.next();

String staff_id=rst.getString("staff_id");
String title=rst.getString("title");
String first_name=rst.getString("first_name");
String last_name=rst.getString("last_name");
String contact_no=rst.getString("contact_no");
String mobile_no=rst.getString("mobile_no");
String email_id=rst.getString("emai_id");
String date_joining=rst.getString("date_joining");
String date_releaving=rst.getString("date_releaving");
String father_name=rst.getString("father_name");
String date_of_birth=rst.getString("date_of_birth");
String gender=rst.getString("gender");
String address1=rst.getString("address1");
String city1=rst.getString("city1");
String state1=rst.getString("state1");
String country1=rst.getString("country1");
String zip1=rst.getString("zip1");
String address2=rst.getString("address2");
String city2=rst.getString("city2");
String state2=rst.getString("state2");
String country2=rst.getString("country2");
String zip2=rst.getString("zip2");
String library_id=rst.getString("library_id");

if(staff_id==null)
    staff_id="";
if( title== null)
    title="";
if( first_name ==null)
    first_name= "";
if( last_name== null) last_name="";

if( contact_no== null) contact_no="";
if( mobile_no== null) mobile_no="";
if( email_id== null) email_id="";
if( date_joining== null) date_joining="";
if( date_releaving== null) date_releaving="";
if( father_name== null) father_name="";
if( date_of_birth== null) date_of_birth="";
if( gender== null) gender="";
if( address1== null) address1="";
if( city1== null) city1="";
if( state1== null) state1="";
if( country1== null) country1="";
if( zip1== null) zip1="";
if( address2== null) address2="";
if( city2== null) city2="";
if( state2== null) state2="";
if( country2== null) country2="";
if( zip2== null) zip2="";
if( library_id== null) library_id="";
%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LibMS(Library Management System) Staff View</title>

<link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>

</head>
<body >


 

      
<div
   style="  top:150px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
    <table width="800px" height="800px"  valign="top" align="center">

       
<tr><td valign="top" height="800px" width="800px" align="center">

<fieldset style="border:solid 1px brown;padding-left: 20px;padding-right: 20px" ><legend><img src="/LibMS-Struts/images/staff.png"></legend>

<span class="txt">Professional Details</span>
                    <hr>
                    <br>
                    <table  style="font-family: arial;font-weight: bold;color:brown;font-size:13px" >

                        <tr><td width="250px">Employee Id</td><td  width="250px" ><input type="text" id="staff_id" style="width:180px;" tabindex="1" disabled name="staff_id" value="<%=staff_id%>">
                            <input type="hidden" id="staff_id" value="<%=rst.getString(1)%>" disabled/>
                            </td><td width="250px" >Email Id*</td><td >
                                <input type="text" id="email_id"  tabindex="2" onBlur="return search();" disabled name="email_id" value="<%=email_id%>">

                                <br/> <div align="left" id="searchResult" style="border:#000000; "></div>
                                    </td></tr>

                                <tr><td colspan="4" height="8px"></td></tr>
                                <tr><td width="250px">First Name*





                                    </td>
                                    <td width="250px">
                                        <table cellspacing="5px">
                                            <tr><td>
                                        <select name="courtesy" size="1" id="courtesy" tabindex="3"  onchange="court(this.options[this.selectedIndex].value)" style="width:57px;align:right" disabled>
                                    <option value="Select">Select</option>
                                       <%if(title.equals("mr"))
                                       {%>
                                    <option selected value="mr">Mr.</option>
                                    <option value="mrs">Mrs.</option>
                                    <option value="ms">Ms.</option>
                                    <%}
                                        else if(title.equals("Mrs.")){%>
                                    <option  value="mr">Mr.</option>
                                    <option selected value="mrs">Mrs.</option>
                                    <option value="ms">Ms.</option>
                                        <%}else{%>
                                    <option  value="mr">Mr.</option>
                                    <option  value="mrs">Mrs.</option>
                                    <option selected value="ms">Ms.</option>
                                    <%}%>
                                    </select></td>
                                    <td>&nbsp;<input type="text" id="first_name"  tabindex="4" style="width:120px;" name="first_name" value="<%=first_name%>" disabled></td></td>
                                        </table>

                                      </td>
                                  <td width="250px">Last Name*</td><td width="150px"><input type="text" id="last_name"  tabindex="5"  name="last_name" value="<%=last_name%>" disabled></td>
                                </tr>
                                    <tr><td colspan="4" height="8px"></td></tr>




                                    <tr><td width="250px">Contact No</td><td width="250px"><input type="text" style="width:180px;" id="contact_no"  tabindex="6"  title="(STD)-(NUMBER)" name="contact_no" value="<%=contact_no%>" disabled></td>
                                <td width="250px">Mobile No</td><td width="250px"><input type="text" id="mobile_no"  tabindex="7"  name="mobile_no" value="<%=mobile_no%>" disabled></td>
                                </tr>
                                <tr><td colspan="4" height="8px"></td></tr>



                            <tr><td width="250px">Date Of Joining*<br>(YYYY-MM-DD)</td><td width="250px">
                   <input type="text" id="do_joining" style="width:180px;" name="do_joining" value="<%=date_joining%>" tabindex="8" disabled/>


                                    </td>
                           <td width="250px">Date Of Releaving*<br>(YYYY-MM-DD)</td><td width="250px">
                 <input type="text" id="do_releaving" style="" name="do_releaving" value="<%=date_releaving%>" tabindex="9" disabled/>

                               </td>
                            </tr>
                    </table>



                            <br><br>
                            <br>
                    <span class="txt">Personal Details</span>
                    <hr>
                    <br><br>
                            <table  style="font-family: arial;font-weight: bold;color:brown;font-size:13px">

                                <tr><td width="250px">Father Name</td><td width="250px"><input type="text" id="father_name" disabled tabindex="10"  name="father_name" value="<%=father_name%>"></td>

                                <td width="250px">Gender</td><td width="250px">

                                <select name="gender" size="1" id="gender" tabindex="11" style="width:148px;" disabled>

                                    <%if(gender.equals("male")){%>
                                <option selected value="male">Male</option>
                                <option value="female">Female</option>
                                 <option  value="Select">Select</option>
                                <%}
                                else
                                { %>
                                 <option value="male">Male</option>
                                <option selected value="female">Female</option>
                                 <option  value="Select">Select</option>
                                <%}%>
                                </select>


                                </td>
                                </tr>
                                <tr><td colspan="4" height="5px"></td></tr>
                                <tr>
                                    <td width="250px">Mailing Address</td><td width="250px"><textarea name="address1" disabled id="address1" tabindex="12"  rows="5" cols="17"><%=address1%></textarea></td>
                                <td width="250px" colspan="2">
                                    <table width="">
                                        <tr><td width="165px">City</td><td width="100px"><input type="text" id="city1" disabled  name="city1" tabindex="13" value="<%=city1%>"></td></tr>
                                        <tr><td colspan="2" height="7px"></td></tr>
                                          <tr><td>State</td><td><input type="text" id="state1" disabled  name="state1" tabindex="14" value="<%=state1%>"></td></tr>
                                          <tr><td colspan="2" height="7px"></td></tr>
                                          <tr><td>Country</td><td><input type="text" id="country1" disabled  name="country1" tabindex="15" value="<%=country1%>"/></td></tr>
                                        <tr><td colspan="2" height="7px"></td></tr>


                                    </table>



                                </td>
                                </tr>

                                <tr><td colspan="4" height="5px"></td></tr>



                                <tr><td width="250px">ZIP Code</td><td width="250px"><input type="text" disabled id="zip1" tabindex="16"  name="zip1" value="<%=zip1%>"></td>
                           <td width="250px">Date of Birth</td><td width="250px"><input type="text" disabled id="date_of_birth"   tabindex="17"  name="date_of_birth" value="<%=date_of_birth%>"></td>
                            </tr>
                            <tr><td colspan="4" height="5px"></td></tr>
                            <tr><td width="250px" colspan="4"><input type="checkbox" id="Checkbox1" disabled name="check" value="on" tabindex="18" onclick="return copy()" >&nbsp;&nbsp;<b>Click Me</b>(if permanent adress is same as mailing address)</td>

                            </tr>
                            <tr><td colspan="4" height="5px"></td></tr>
                            <tr>
                                <td width="250px">Permanent Address</td><td width="250px"><textarea name="address2" disabled id="address2" tabindex="19"  rows="5" cols="17">
<%=address2%>
                                    </textarea></td>
                                <td width="250px" colspan="2">
                                    <table>
                                        <tr><td width="165px">City</td><td width="100px"><input type="text" disabled id="city2" tabindex="20"  name="city2" value="<%=city2%>"></td></tr>
                                        <tr><td colspan="4" height="7px"></td></tr>
                                          <tr><td>State</td><td><input type="text" id="state2"  name="state2" disabled tabindex="21" value="<%=state2%>"></td></tr>
                                          <tr><td colspan="4" height="7px"></td></tr>
                                        <tr><td>Country</td><td><input type="text" id="country2"  name="country2" disabled tabindex="22" value="<%=country2%>"></td></tr>
                                        <tr><td colspan="4" height="7px"></td></tr>
                                        <tr><td width="250px">ZIP Code</td><td width="250px" COLSPAN="3"><input type="text" disabled id="zip2" tabindex="23"  name="zip2" value="<%=zip2%>"></td>
                                        <tr><td colspan="4" height="7px"></td></tr>
                                        <tr><td colspan="4" height="7px"></td></tr>

                                    </table>



                                </td>
                                </tr>
                            </table>

                            <br><br>


                           
                                <input type="button" id="Button3" name="button" value="Back" onclick="return send()" class="btn" />
                                <br/>
                                <br/>
         
</fieldset>
 </td></tr>
        </table>

   <%-- <table width="800px" height="800px"  valign="top" align="center" >

        <tr><td valign="top" height="800px" width="800px" align="center">
                
            
<fieldset style="border:solid 1px brown;padding-left: 20px;padding-right: 20px" ><legend><img src="images/staff.png" height="45px" width="23px"/></legend>
                    <br>
                    <br>
                    <span class="txt">Professional Details</span>
                    <hr>
                    <br>
                    <table  style="font-family: arial;font-weight: bold;color:brown;font-size:13px">
                                           
                                <tr><td width="250px">Employee Id</td><td  colspan="1"><input type="text" id="staff_id" tabindex="1" disabled="true" name="staff_id" value="<%=rst.getString(1)%>"></td>
                                    <td width="250px" >Email Id</td><td colspan="3"><input type="text" id="email_id"  tabindex="2"  name="email_id" value="<%=rst.getString(7)%>"></td>
                                    </tr>
                                  <tr><td colspan="4" height="5px"></td></tr>
                                <tr><td width="250px">First Name
                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <select name="courtesy" size="1" id="courtesy" tabindex="3"  onchange="court(this.options[this.selectedIndex].value)">
                                    <option value="Select">Select</option>
                                       <%if(rst.getString(2).equals("mr"))
                                       {%>
                                    <option selected value="mr">Mr.</option>
                                    <option value="mrs">Mrs.</option>
                                    <option value="ms">Ms.</option>
                                    <%}
                                        else if(rst.getString(2).equals("Mrs.")){%>
                                    <option  value="mr">Mr.</option>
                                    <option selected value="mrs">Mrs.</option>
                                    <option value="ms">Ms.</option>
                                        <%}else{%>
                                    <option  value="mr">Mr.</option>
                                    <option  value="mrs">Mrs.</option>
                                    <option selected value="ms">Ms.</option>
                                    <%}%>
                                    </select>



                                    </td><td width="250px"><input type="text" id="first_name"  tabindex="4" name="first_name" value="<%=rst.getString(3)%>"></td>
                                  <td width="250px">Last Name</td><td width="250px"><input type="text" id="last_name"  tabindex="5"  name="last_name" value="<%=rst.getString(4)%>"></td>
                                </tr>
                                    <tr><td colspan="4" height="5px"></td></tr>


                         
                              
<tr><td width="250px">Contact No</td><td width="250px"><input type="text" id="contact_no"  tabindex="6"  name="contact_no" value="<%=rst.getString(5)%>"></td>
                                <td width="250px">Mobile No</td><td width="250px"><input type="text" id="mobile_no"  tabindex="7"  name="mobile_no" value="<%=rst.getString(6)%>"></td>
                                </tr>
                                <tr><td colspan="4" height="5px"></td></tr>
                                
                                
                                
                                <tr><td colspan="4" height="5px"></td></tr>
                            <tr><td width="250px">Date Of Joining</td><td width="250px"><input type="text" id="do_joining"  tabindex="8"  name="do_joining" value="<%=rst.getString(8)%>"></td>
                           <td width="250px">Date Of Releaving</td><td width="250px"><input type="text" id="do_releaving"  tabindex="9"  name="do_releaving" value="<%=rst.getString(9)%>"></td>
                            </tr>
                    </table>
                            <br><br>
                            <br>
                    <span class="txt">Professional Details</span>
                    <hr>
                    <br><br>
                            <table  style="font-family: arial;font-weight: bold;color:brown;font-size:13px">

                                <tr><td width="250px">Father Name</td><td width="250px"><input type="text" id="father_name"  tabindex="10"  name="father_name" value="<%=rst.getString(10)%>"></td>
                                
                                <td width="250px">Gender</td><td width="250px">

                                <select name="gender" size="1" id="gender" tabindex="11">
                                <option  value="Select">Select</option>
                                    <%if(rst.getString(12).equals("male")){%>
                                <option  value="Male">Male</option>
                                <option value="Female">Female</option>
                                <%}
                                else
                                { %>
                                 <option value="Male">Male</option>
                                <option selected value="Female">Female</option>
                                <%}%>
                                </select>


                                </td>
                                </tr>
                                <tr><td colspan="4" height="5px"></td></tr>
                                <tr>
                                    <td width="250px">Mailing Address</td><td width="250px"><textarea name="address1" id="address1" tabindex="12"  rows="5" cols="17"><%=rst.getString(13)%></textarea></td>
                                <td width="250px" colspan="2">
                                    <table width="">
                                        <tr><td width="165px">City</td><td width="100px"><input type="text" id="city1"  name="city1" tabindex="13" value="<%=rst.getString(14)%>"></td></tr>
                                        <tr><td colspan="2" height="7px"></td></tr>
                                          <tr><td>State</td><td><input type="text" id="state1"  name="state1" tabindex="14" value="<%=rst.getString(15)%>"></td></tr>
                                          <tr><td colspan="2" height="7px"></td></tr>
                                          <tr><td>Country</td><td><input type="text" id="country1"  name="country1" tabindex="15" value="<%=rst.getString(16)%>"/></td></tr>
                                        <tr><td colspan="2" height="7px"></td></tr>


                                    </table>



                                </td>
                                </tr>
                               
                                <tr><td colspan="4" height="5px"></td></tr>
                               
                            
                            
                                <tr><td width="250px">ZIP Code</td><td width="250px"><input type="text" id="zip1" tabindex="16"  name="zip1" value="<%=rst.getString(17)%>"></td>
                           <td width="250px">Date of Birth</td><td width="250px"><input type="text" id="date_of_birth"   tabindex="17"  name="date_of_birth" value="<%=rst.getString(11)%>"></td>
                            </tr>
                            <tr><td colspan="4" height="5px"></td></tr>
                            <tr><td width="250px" colspan="4"><input type="checkbox" id="Checkbox1" name="check" value="on" tabindex="18" onclick="return copy()" >&nbsp;&nbsp;<b>Click Me</b>(if permanent adress is same as mailing address)</td>

                            </tr>
                            <tr><td colspan="4" height="5px"></td></tr>
                            <tr>
                                <td width="250px">Permanent Address</td><td width="250px"><textarea name="address2" id="address2" tabindex="19"  rows="5" cols="17">
<%=rst.getString(18)%>
                                    </textarea></td>
                                <td width="250px" colspan="2">
                                    <table>
                                        <tr><td width="165px">City</td><td width="100px"><input type="text" id="city2" tabindex="20"  name="city2" value="<%=rst.getString(19)%>"></td></tr>
                                        <tr><td colspan="4" height="7px"></td></tr>
                                          <tr><td>State</td><td><input type="text" id="state2"  name="state2" tabindex="21" value="<%=rst.getString(20)%>"></td></tr>
                                          <tr><td colspan="4" height="7px"></td></tr>
                                        <tr><td>Country</td><td><input type="text" id="country2"  name="country2" tabindex="22" value="<%=rst.getString(21)%>"></td></tr>
                                        <tr><td colspan="4" height="7px"></td></tr>
                                        <tr><td width="250px">ZIP Code</td><td width="250px" COLSPAN="3"><input type="text" id="zip2" tabindex="23"  name="zip2" value="<%=rst.getString(22)%>"></td>
                                        <tr><td colspan="4" height="7px"></td></tr>
                                        <tr><td colspan="4" height="7px"></td></tr>
                            
                                    </table>



                                </td>
                                </tr>
                            </table>
                             
                            <br><br>

                           
                                <input type="button" id="Button3" name="button" value="Quit" onclick="history.back(-2);" class="btn" />
                               
                               
                              
     
                             
                               

                            <br><br>
</fieldset>
            </td></tr>
                
    </table>--%>

                        




<input type="hidden" id="Editbox" tabindex="1"  name="employee_id" value="<%=rst.getString(1)%>">















</div>















<script>
    function send()
    {
        window.location="/LibMS-Struts/admin/viewstaff.jsp";
    }
    </script>











    <div
   style="
      top: 850px;

      position: absolute;

      visibility: show;">
        <jsp:include page="footer.jsp" />

</div>




</html>

