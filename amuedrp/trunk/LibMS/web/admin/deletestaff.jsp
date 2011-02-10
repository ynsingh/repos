<%@ page language="java" import="java.sql.*"  %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%
String library_id=(String)session.getAttribute("library_id");
String staff_id=(String)request.getAttribute("staff_id");
String message1=request.getParameter("msg1");
String message2=request.getParameter("msg2");
String msg3=request.getParameter("email_id");

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Untitled Page</title>

<link rel="stylesheet" href="css/page.css"/>

</head>
<body>


 <html:form  method="post"  action="/registerstaff" onsubmit="return check()">

      <jsp:include page="header.jsp" flush="true" />
<div
   style="  top:150px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
    <table width="800px" height="800px"  valign="top" align="center">

        <tr><td valign="top" height="800px" width="800px" align="center">
                
            
<fieldset style="border:solid 1px brown;padding-left: 20px;padding-right: 20px" ><legend><img src="images/staff.png" height="45px" width="23px"/>&nbsp;<span style="font-family:arial;font-size:17px;font-weight: bold">Registration Form</span></legend>
                    <br>
                    <br>
                    <span class="txt">Professional Details</span>
                    <hr>
                    <br>
                    <table  style="font-family: arial;font-weight: bold;color:brown;font-size:13px">
                                           
                                <tr><td width="250px">Employee Id</td><td  colspan="1"><input type="text" id="Editbox1" tabindex="1" disabled="true" name="" value="<%=staff_id%>"></td><td></td><td><img src="images/no.png" height="150px" width="150px" border="1px"/><br>
                                    <input type="file" id="file1"/>
                                    </td></tr>
                                <!--<tr><td width="250px">Employee Id</td><td  colspan="3"><input type="text" id="Editbox1" tabindex="1" disabled="true" name="" value="<%=staff_id%>"></td></tr>-->
                                <tr><td colspan="4" height="5px"></td></tr>
                                <tr><td width="250px">First Name
                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <select name="courtesy" size="1" id="Combobox1" tabindex="2">
                                    <option selected value="Mr.">Mr.</option>
                                    <option value="Mrs.">Mrs.</option>
                                    <option value="Ms.">Ms.</option>
                                    </select>



                                    </td><td width="250px"><input type="text" id="Editbox2"  tabindex="3" name="first_name" value=""></td>
                                  <td width="250px">Last Name</td><td width="250px"><input type="text" id="Editbox4"  tabindex="4"  name="last_name" value=""></td>
                                </tr>
                                    <tr><td colspan="4" height="5px"></td></tr>


                         
                              
<tr><td width="250px">Contact No</td><td width="250px"><input type="text" id="Editbox5"  tabindex="5"  name="contact_no" value=""></td>
                                <td width="250px">Mobile No</td><td width="250px"><input type="text" id="Editbox6"  tabindex="6"  name="mobile_no" value=""></td>
                                </tr>
                                <tr><td colspan="4" height="5px"></td></tr>
                                <tr><td width="250px" >Email Id</td><td colspan="3"><input type="text" id="Editbox7"  tabindex="7"  name="email_id" value=""></td>
                                
                                </tr>
                                <tr><td colspan="4" height="5px"></td></tr>
                            <tr><td width="250px">Date Of Joining</td><td width="250px"><input type="text" id="Editbox8"  tabindex="8"  name="do_joining" value=""></td>
                           <td width="250px">Date Of Releaving</td><td width="250px"><input type="text" id="Editbox9"  tabindex="9"  name="do_releaving" value=""></td>
                            </tr>
                    </table>
                            <br><br>
                            <br>
                    <span class="txt">Professional Details</span>
                    <hr>
                    <br><br>
                            <table  style="font-family: arial;font-weight: bold;color:brown;font-size:13px">

                                <tr><td width="250px">Father Name</td><td width="250px"><input type="text" id="Editbox19"  tabindex="10"  name="father_name" value=""></td>
                                
                                <td width="250px">Gender</td><td width="250px">
                                <select name="gender" size="1" id="Combobox2" tabindex="12" >
                                <option selected value="Male">Male</option>
                                <option value="Female">Female</option>
                                </select>


                                </td>
                                </tr>
                                <tr><td colspan="4" height="5px"></td></tr>
                                <tr>
                                <td width="250px">Mailing Address</td><td width="250px"><textarea name="address1" id="TextArea1"  rows="5" cols="17"></textarea></td>
                                <td width="250px" colspan="2">
                                    <table width="">
                                        <tr><td width="165px">City</td><td width="100px"><input type="text" id="Editbox11"  name="city1" value=""></td></tr>
                                        <tr><td colspan="2" height="7px"></td></tr>
                                          <tr><td>State</td><td><input type="text" id="Editbox12"  name="state1" value=""></td></tr>
                                          <tr><td colspan="2" height="7px"></td></tr>
                                        <tr><td>Country</td><td><input type="text" id="Editbox13"  name="country1" value=""></td></tr>
                                        <tr><td colspan="2" height="7px"></td></tr>


                                    </table>



                                </td>
                                </tr>
                               
                                <tr><td colspan="4" height="5px"></td></tr>
                               
                            
                            
                             <tr><td width="250px">ZIP Code</td><td width="250px"><input type="text" id="Editbox14"  name="zip1" value=""></td>
                           <td width="250px">Date of Birth</td><td width="250px"><input type="text" id="Editbox10"  tabindex="11"  name="date_of_birth" value=""></td>
                            </tr>
                            <tr><td colspan="4" height="5px"></td></tr>
                            <tr><td width="250px" colspan="4"><input type="checkbox" id="Checkbox1" name="checkbox1" value="on" onclick="return copy(address1,address2,city1,city2,state1,state2,country1,country2,zip1,zip2)" >&nbsp;&nbsp;<b>Click Me</b>(if permanent adress is same as mailing address)</td>

                            </tr>
                            <tr><td colspan="4" height="5px"></td></tr>
                            <tr>
                                <td width="250px">Permanent Address</td><td width="250px"><textarea name="address2" id="TextArea2"  rows="5" cols="17"></textarea></td>
                                <td width="250px" colspan="2">
                                    <table>
                                        <tr><td width="165px">City</td><td><input type="text" id="Editbox15"  name="city2" value=""></td></tr>
                                        <tr><td colspan="4" height="7px"></td></tr>
                                          <tr><td>State</td><td><input type="text" id="Editbox16"  name="state2" value=""></td></tr>
                                          <tr><td colspan="4" height="7px"></td></tr>
                                        <tr><td>Country</td><td><input type="text" id="Editbox17"  name="country2" value=""></td></tr>
                                        <tr><td colspan="4" height="7px"></td></tr>
                                        <tr><td width="250px">ZIP Code</td><td width="250px" COLSPAN="3"><input type="text" id="Editbox18"  name="zip2" value=""></td>
                                        <tr><td colspan="4" height="7px"></td></tr>
                                        <tr><td colspan="4" height="7px"></td></tr>
                            
                                    </table>



                                </td>
                                </tr>
                            </table>
                             
                            <br><br>
                            <input type="submit" id="Button1" name="" value="Register" class="btn" >
                            <input type="reset" id="Button2" name="submit" value="Reset" class="btn">
                            <input type="button" id="Button3" name="" value="Back" onclick="return send()" class="btn">
                            <br><br>
</fieldset>
            </td></tr>
                
    </table>

                        




<input type="hidden" id="Editbox" tabindex="1"  name="employee_id" value="<%=staff_id%>">















</div>



























  
</html:form>

<script type="text/javascript">
    document.form1.employee_id.focus();
    function check()
    {
        if(document.form1.employee_id.value=="")
            {
              alert("Enter staff id..");
              document.form1.employee_id.focus();
              document.form1.employee_id.select();
              return false;
            }
            if(document.form1.first_name.value=="")
            {
              alert("Enter First name");
              document.form1.first_name.focus();
              document.form1.first_name.select();
              return false;
            }
            if(document.form1.Editbox4.value=="")
            {
              alert("Enter last name");
              document.form1.Editbox4.focus();
              document.form1.Editbox4.select();
              return false;
            }

           if(document.form1.Editbox5.value=="")
            {
              alert("Enter Contact No");
              document.form1.Editbox5.focus();
              document.form1.Editbox5.select();
              return false;
            }

    }

function send()
{
    window.location="/LibMS-Struts/admin/main.jsp";
    return false;
}

function confirm1()
{
var answer = confirm ("Do you want to add data?")
if (answer!=true)
    {
        document.form1.focus();
        return false;
    }
}

function copy(address1,address2,city1,city2,state1,state2,country1,country2,zip1,zip2)
{

    if(document.form1.checkbox1.checked==true)
        {

        //alert("aaaa");
        address2.value=address1.value;
        city2.value=city1.value;
        state2.value=state1.value;
        country2.value=country1.value;
        zip2.value=zip1.value;



    }
}



</script>


   <%     if (msg3!=null){
 %>
 <script language="javascript">
 window.location="/LibMS-Struts/admin/acq_registerstaff.jsp";
 alert("<%=msg3%>");
 </script>
 <%
}
%>

<%
if(message1!=null||message2!=null)
    {
    %>
 <script language="javascript">
 window.location="/LibMS-Struts/admin/acq_registerstaff.jsp";
 string s=<%=message1%>;
 s=s+"<br>"+<%=message2%>;
 alert(s);
 </script>
<%
}
%>




</html>
