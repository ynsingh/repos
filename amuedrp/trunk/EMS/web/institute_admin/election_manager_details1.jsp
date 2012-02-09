<%-- 
    Document   : election_manager_details
    Created on : Mar 21, 2011, 12:57:10 PM
    Author     : Edrp-04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*,com.myapp.struts.hbm.*" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<link rel="stylesheet" href="/EMS-Struts/css/page.css"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.*,java.io.*,java.net.*"%>
<jsp:include page="/election_manager/login.jsp"/>


<%
try{
if(session.getAttribute("institute_id")!=null){
System.out.println("institute_id"+session.getAttribute("institute_id"));
}
else{
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/login.jsp?session=\"expired\"";</script><%
    }
}catch(Exception e){
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %>sessionout();<%
    }

%>



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
List<Election_Manager_StaffDetail> rst = (List<Election_Manager_StaffDetail>)request.getAttribute("resultset");

if(rst==null){System.out.println("ok"+rst); rst = (List)request.getAttribute("resultset");}
else{request.setAttribute("resultset", rst);}
System.out.println("ok"+rst);

Election_Manager_StaffDetail ems=new Election_Manager_StaffDetail();
Login login=new Login();
//AdminRegistration adminReg = new AdminRegistration();
if (!rst.isEmpty()){
    ems = (Election_Manager_StaffDetail)rst.get(0);
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Election_Manager_Details</title>
    </head>
    <body onload="funload();">

        <html:form action="/manager_registration" method="post">
        <table width="100%" dir="<%=rtl%>">
             <br>
             <tr><td style="text-decoration: underline;" align="center" class="txt" dir="<%=rtl%>"><%=resource.getString("electionmanagerdetail")%></td></tr>

            <tr>
            <table width="45%" align="center" dir="<%=rtl%>" class="txt" style="font-family: arial;font-weight: bold;color:brown;font-size:13px">

                <tr><td></td></tr>

                <%if(!rst.isEmpty()){
                
                String first_name=ems.getStaffDetail().getFirstName();
                String last_name=ems.getStaffDetail().getLastName();
                String address=ems.getStaffDetail().getAddress1();
                String city=ems.getStaffDetail().getCity1();
                String state=ems.getStaffDetail().getState1();
                String country=ems.getStaffDetail().getCountry1();
                String pin=ems.getStaffDetail().getZip1();
                String gender=ems.getStaffDetail().getGender();
                String contact_no=ems.getStaffDetail().getContactNo();
                String mobile_no=ems.getStaffDetail().getMobileNo();
                String email_id=ems.getStaffDetail().getEmailId();
                String department=ems.getElectionManager().getDepartment();
                String staff_id=ems.getElectionManager().getStaffId();
                String manager_id=ems.getElectionManager().getId().getManagerId();
                String institute_id=ems.getElectionManager().getId().getInstituteId();
                String user_id=ems.getElectionManager().getUserId();
                String password=ems.getLogin().getPassword();
                %>






                <tr>
                    <td align="<%=align%>" dir="<%=rtl%>"> <%=resource.getString("firstname")%></td>

                    <td align="<%=align%>" dir="<%=rtl%>"> <html:text property="first_name" value="<%=first_name%>" readonly="true"/></td>
                </tr>
                <tr>
                    <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("lastname")%></td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text property="last_name"value="<%=last_name%>" readonly="true"/></td>
                </tr>
                <tr>
                    <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("address")%></td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text  property="address1" value="<%=address%>" readonly="true"/></td>
                </tr>
                <tr>
                    <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("city")%></td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text property="city1" value="<%=city%>" readonly="true"/></td>
                </tr>
                <tr>
                    <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("state")%></td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text property="state1" value="<%=state%>" readonly="true"/></td>
                </tr>
                <tr>
                    <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("pin")%></td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text property="zip1" value="<%=pin%>" readonly="true"/></td>
                </tr>
                <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("country")%></td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text property="country1" value="<%=country%>" readonly="true" /></td>
                </tr>

                <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("gender")%></td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text property="gender" value="<%=gender%>" readonly="true"/></td>
                </tr>

                <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("contactno")%></td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text property="contact_no" value="<%=contact_no%>" readonly="true"/></td>
                </tr>
                 <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("mobileno")%></td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text property="mobile_no" value="<%=mobile_no%>" readonly="true"/></td>
                </tr>

                 <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("emailid")%></td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text property="email_id" value="<%=email_id%>" readonly="true"/></td>
                </tr>


                <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("department")%></td>
                     <td align="<%=align%>" dir="<%=rtl%>"><html:text property="department" value="<%=department%>" readonly="true" /></td>
                </tr>

                <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("staffid")%></td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text property="staff_id" value="<%=staff_id%>" readonly="true"/></td>
                </tr>

                <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("managerid")%></td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text property="manager_id" value="<%=manager_id%>" readonly="true"/></td>
                    
                </tr>

                <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("instituteid")%></td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text property="institute_id" value="<%=institute_id%>" readonly="true" /></td>
                    
                </tr>

                <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("userid")%></td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text property="user_id" value="<%=user_id%>" readonly="true" /></td>
                </tr>


                 <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("password")%></td>
                     <td align="<%=align%>" dir="<%=rtl%>"><html:password property="password" value="<%=password%>" styleId="pass" readonly="true" name="Election_Manager_RegistrationActionForm" /></td>
                     
                <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><br><br><br><br><br><br></td>
                    <%-- <td align="<%=align%>" dir="<%=rtl%>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="back" class="txt2" value="<%=resource.getString("back")%>" onclick="quit()"/></td>--%>
                </tr>

            </table>
            </tr>
       <%}%> </table></html:form>


    </body>
</html>
<script>
    var prevheight;
    function quit()
{
    //top.location="/EMS-Struts/institute_admin/institute_admin_home.jsp";
   // parent.document.getElementById("pagetab").height = prevheight;
    window.location.href="<%=request.getContextPath()%>/manager_details.do";
}



function funload()
{
  // alert("yes its working");
   var parloc = parent.document.getElementById("pagetab");
    var pagheight = document.height!=undefined?document.height:(document.body!=undefined?document.body.height:document.scrollHeight);
   prevheight = parloc.height;
   //alert(pagheight);
   if(pagheight!=undefined)pagheight+=30;
    parloc.height = pagheight!=undefined?pagheight:650;
    //alert("yes its working");
}
</script>
