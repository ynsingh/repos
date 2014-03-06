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
<%
String request1=(String)request.getParameter("status");
if(request1!=null){

%>
<jsp:include page="adminheader.jsp"/>
<%}%>
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
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);%>

    <%
List<Election_Manager_StaffDetail> rst = (List<Election_Manager_StaffDetail>)request.getAttribute("resultset");

if(rst==null){System.out.println("ok"+rst); rst = (List)request.getAttribute("resultset");}
else{request.setAttribute("resultset", rst);
}
System.out.println("ok"+rst);

Election_Manager_StaffDetail ems=new Election_Manager_StaffDetail();
Login login=new Login();
//AdminRegistration adminReg = new AdminRegistration();
if(rst!=null)
if (!rst.isEmpty()){
    ems = (Election_Manager_StaffDetail)rst.get(0);
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update_Manager_Details</title>
    </head>
    <body onload="funload();">

        <html:form action="/confirm_update_manager" method="post" onsubmit="return validation();">
            <table width="100%" class="txt"  dir="<%=rtl%>">
             <br>
            <tr><td style="text-decoration: underline;" align="center"  dir="<%=rtl%>"><%=resource.getString("electionmanagerdetail")%></td></tr>

            <tr>
            <table width="75%" align="center"  dir="<%=rtl%>" class="txt" style="font-family: arial;font-weight: bold;color:brown;font-size:13px">

                

                <%if(rst!=null)
                if(!rst.isEmpty()){
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
                    <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("firstname")%>*</td>

                    <td align="<%=align%>" dir="<%=rtl%>"> <html:text  property="first_name" styleId="first_name" value="<%=first_name%>"/></td>
                 <td width="300px" class="err" dir="<%=rtl%>" align="<%=align%>"></td> </tr>
                <tr>
                    <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("lastname")%>*</td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text  styleId="last_name" property="last_name" value="<%=last_name%>"/></td>
               <td width="300px" class="err" dir="<%=rtl%>" align="<%=align%>"> </td> </tr>
                <tr>
                    <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("address")%></td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text  property="address1" value="<%=address%>" /></td>
                </tr>
                <tr>
                    <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("city")%></td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text property="city1" value="<%=city%>" /></td>
                </tr>
                <tr>
                    <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("state")%></td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text property="state1" value="<%=state%>" /></td>
                </tr>
                <tr>
                    <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("pin")%></td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text property="zip1" value="<%=pin%>" /></td>
                </tr>
                <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("country")%></td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text property="country1" value="<%=country%>"  /></td>
                </tr>

                <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("gender")%></td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text property="gender" value="<%=gender%>"/></td>
                </tr>

                <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("contactno")%>*</td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text  styleId="contact_no" property="contact_no" value="<%=contact_no%>" /></td>
                    <td width="300px" class="err" dir="<%=rtl%>" align="<%=align%>"></td>
                </tr>
                 <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("mobileno")%></td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text property="mobile_no" value="<%=mobile_no%>" /></td>
                </tr>

                 <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("emailid")%>*</td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text  styleId="email_id" property="email_id" value="<%=email_id%>"/></td>
                    <td width="300px" class="err" dir="<%=rtl%>" align="<%=align%>">

                     <div align="<%=align%>" dir="<%=rtl%>" id="searchResult" class="err" style="border:#000000; "></div>
                     <%
                      String reg=(String)request.getAttribute("reg");
           String reg1=(String)request.getAttribute("reg1");


                      if(reg!=null)
                  out.println("*"+reg);
                 if(reg1!=null)
                  out.println("*"+reg1);
                         %>

                    </td>
                </tr>


                <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("department")%></td>
                     <td align="<%=align%>" dir="<%=rtl%>"><html:text property="department" value="<%=department%>"  /></td>
                </tr>

             
             
             <html:hidden  property="staff_id" value="<%=staff_id%>"/>
                

                
                <html:hidden property="manager_id" value="<%=manager_id%>" />

                

                <html:hidden property="institute_id" value="<%=institute_id%>"  />

                

                <html:hidden property="user_id" value="<%=user_id%>"/>
                    
                <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("password")%>*</td>
                     <td align="<%=align%>" dir="<%=rtl%>"><html:password property="password" value="<%=password%>" styleId="pass" readonly="true" name="Election_Manager_RegistrationActionForm" /></td>
                     <td width="300px" class="err" dir="<%=rtl%>" align="<%=align%>"> </td>
                </tr>

                





                <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><br><br><br><br><br><br></td>
                     <td align="<%=align%>" dir="<%=rtl%>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" class="txt2" name="update" value="<%=resource.getString("update")%>"  />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="back" class="txt2" value="<%=resource.getString("back")%>" onclick="quit()"/></td>
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
     //location.href="<%=request.getContextPath()%>/institute_admin/update_election_managergrid_details.jsp";

   //  window.top.document.getElementById("pagetab").height = prevheight;
     location.href="<%=request.getContextPath()%>/instituteadmin.do";
     
}



function funload()
{
  // alert("yes its working");
   var parloc = window.top.document.getElementById("pagetab");
    var pagheight = document.height!=undefined?document.height:(document.body!=undefined?document.body.height:document.scrollHeight);
   prevheight = parloc.height;
   if(pagheight!=undefined)
   pagheight+=30;
    parloc.height = pagheight!=undefined?pagheight:650;
    //alert("yes its working");
}



    function validation()
    {

    var email_id=document.getElementById('email_id');
   var admin_fname=document.getElementById('first_name');
    var admin_lname=document.getElementById('last_name');

  
    var mobile_no=document.getElementById('contact_no');



  if (echeck(email_id.value)==false)
    {
		email_id.value="";

                email_id.focus();
		return false;
	}


var str="Enter Following Values:-";

    if(admin_fname.value=="")
       { str+="\n Enter  first name"; alert(str);
            document.getElementById('first_name').focus();
            return false;
       }

 if(admin_lname.value=="")
       { str+="\n Enter  last name"; alert(str);
            document.getElementById('last_name').focus();
            return false;
       }
       if(mobile_no.value=="")
       { str+="\n Enter contact no" ; alert(str);
            document.getElementById('contact_no').focus();
            return false;
       }
       if (isNaN(mobile_no.value))
        {
            str+="\n Enter Valid Contact No";
            alert(str);
            document.getElementById('contact_no').focus();
            return false;
        }
       
    if(email_id.value=="")
       { str+="\n Enter Email ID";
           alert(str);
            document.getElementById('email_id').focus();
            return false;
       }
  
if(str=="Enter Following Values:-")
    return true;
else
    {
        alert(str);
        document.getElementById('first_name').focus();
        return false;
    }




    }


function echeck(str) {
var email_id=document.getElementById('email_id');

//alert(str);
availableSelectList = document.getElementById("searchResult");
 availableSelectList.innerHTML = "";
		var at="@"
		var dot="."
		var lat=str.indexOf(at)
		var lstr=str.length
		var ldot=str.indexOf(dot)
		if (str.indexOf(at)==-1){
                    availableSelectList.innerHTML += "<%=resource.getString("invalid_emailid")%>"+"\n";
		   return false
		}

		if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
		  availableSelectList.innerHTML += "<%=resource.getString("invalid_emailid")%>"+"\n";
		   return false
		}

		if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
		    availableSelectList.innerHTML += "<%=resource.getString("invalid_emailid")%>"+"\n";
		    return false
		}

		 if (str.indexOf(at,(lat+1))!=-1){
		    availableSelectList.innerHTML += "<%=resource.getString("invalid_emailid")%>"+"\n";
		    return false
		 }

		 if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
		    availableSelectList.innerHTML += "<%=resource.getString("invalid_emailid")%>"+"\n";
		    return false
		 }

		 if (str.indexOf(dot,(lat+2))==-1){
		    availableSelectList.innerHTML += "<%=resource.getString("invalid_emailid")%>"+"\n";
		    return false
		 }

		 if (str.indexOf(" ")!=-1){
		    availableSelectList.innerHTML += "<%=resource.getString("invalid_emailid")%>"+"\n";
		    return false
		 }


 		 return true
	}






</script>

