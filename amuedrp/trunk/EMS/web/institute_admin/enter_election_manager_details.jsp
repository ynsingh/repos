<%-- 
    Document   : enter_election_manager_details
    Created on : Mar 14, 2011, 11:01:24 AM
    Author     : Edrp-04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
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
String contextPath = request.getContextPath();
%>


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
    regid = resource.getString("registrationid");
    System.out.println("Reg id="+regid);
    %>
    <% String institute_id=(String)session.getAttribute("institute_id");%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=contextPath%>/css/page.css"/>
        <title></title>
    </head>
    <jsp:include page="adminheader.jsp"/>
    <body>
        <html:form action="/manager_registration" method="post" onsubmit="return checkPassLog();" >
            <table width="100%" class="txt" dir="<%=rtl%>">
             <br> 
            <tr><td style="text-decoration: underline;" align="center" dir="<%=rtl%>"><%=resource.getString("electionmanagerdetail")%></td></tr>
           
            <tr>
            <table width="75%" align="center" class="txt" dir="<%=rtl%>" style="font-family: arial;font-weight: bold;color:brown;font-size:13px">
                
                <tr><td></td></tr>
                <tr>
                    <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("firstname")%>*</td>
                
                    <td align="<%=align%>" dir="<%=rtl%>"> <html:text property="first_name" styleId="first_name" /></td>
                    <td width="300px" class="err" dir="<%=rtl%>" align="<%=align%>"> <html:messages id="err_name"  property="first_name">
            <%=resource.getString("firstname_cannotbe_blank")%>
                        </html:messages></td>
                </tr>
                <tr>
                    <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("lastname")%>*</td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text property="last_name" styleId="last_name" name="Election_Manager_RegistrationActionForm"/></td>
                    <td width="300px" class="err" dir="<%=rtl%>" align="<%=align%>"> <html:messages id="err_name"  property="last_name">
            <%=resource.getString("lastname_cannotbe_blank")%>
                        </html:messages></td>
                </tr>
                <tr>
                    <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("address")%></td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text  property="address1" styleId="address"/></td>
                </tr>
                <tr>
                    <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("city")%></td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text property="city1"  styleId="city" name="Election_Manager_RegistrationActionForm"/></td>
                </tr>
                <tr>
                    <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("state")%></td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text property="state1" styleId="state" name="Election_Manager_RegistrationActionForm"/></td>
                </tr>
                <tr>
                    <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("pin")%></td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text property="zip1" styleId="pin" name="Election_Manager_RegistrationActionForm"/></td>
                </tr>
                <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("country")%></td>
                     <td align="<%=align%>" dir="<%=rtl%>"><html:text property="country1" styleId="country" name="Election_Manager_RegistrationActionForm"/></td>
                </tr>

                <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("gender")%></td>
                     <td align="<%=align%>" dir="<%=rtl%>"><html:text property="gender" styleId="gender" name="Election_Manager_RegistrationActionForm"/></td>
                </tr>

                <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("contactno")%>*</td>
                     <td align="<%=align%>" dir="<%=rtl%>"><html:text property="contact_no" styleId="contact" name="Election_Manager_RegistrationActionForm"/></td>
                    <td width="300px" class="err" dir="<%=rtl%>" align="<%=align%>"> <html:messages id="err_name"  property="contact_no">
           <%=resource.getString("contactno_cannotbe_blank")%>
                        </html:messages></td>
                </tr>
                 <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("mobileno")%></td>
                     <td align="<%=align%>" dir="<%=rtl%>"><html:text property="mobile_no" styleId="mobile" name="Election_Manager_RegistrationActionForm"/></td>
                </tr>

                 <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("emailid")%>*</td>
                     <td align="<%=align%>" dir="<%=rtl%>"><html:text property="email_id" styleId="email" name="Election_Manager_RegistrationActionForm"/></td>
                    <td width="300px" class="err" dir="<%=rtl%>" align="<%=align%>"> <html:messages id="err_name"  property="email_id">
            <%=resource.getString("emailid_cannotbe_blank")%>
                        </html:messages><div align="<%=align%>" dir="<%=rtl%>" id="searchResult" class="err" style="border:#000000; "></div></td>
                </tr>


                <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("department")%></td>
                     <td align="<%=align%>" dir="<%=rtl%>"><html:text property="department" styleId="department" name="Election_Manager_RegistrationActionForm"/></td>
                </tr>

               <%-- <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("staffid")%>*</td>
                     <td align="<%=align%>" dir="<%=rtl%>"><html:text property="staff_id" styleId="staff_id" name="Election_Manager_RegistrationActionForm"/></td>
                    <td width="300px" class="err" dir="<%=rtl%>" align="<%=align%>"> <html:messages id="err_name"  property="staff_id">
            <%=resource.getString("staffid_cannotbe_blank")%>
                        </html:messages></td>
                </tr>--%>

                <%--<tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("managerid")%>*</td>
                     <td align="<%=align%>" dir="<%=rtl%>"><html:text property="manager_id" styleId="manager_id" name="Election_Manager_RegistrationActionForm"/></td>
                    <td width="300px" class="err" dir="<%=rtl%>" align="<%=align%>"> <html:messages id="err_name"  property="manager_id">
            <%=resource.getString("managerid_cannotbe_blank")%>
                        </html:messages></td>
                </tr>--%>

               <%-- <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("instituteid")%></td>
                     <td align="<%=align%>" dir="<%=rtl%>"><html:text property="institute_id" value="<%=institute_id%>" styleId="institute_id" name="Election_Manager_RegistrationActionForm" readonly="true"/></td>
                    <td width="300px" class="err" dir="<%=rtl%>" align="<%=align%>"> <html:messages id="err_name"  property="institute_id">
                            <bean:write name="err_name" />
             </html:messages></td>
                </tr>--%>

             <%--   <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("userid")%>*</td>
                     <td align="<%=align%>" dir="<%=rtl%>"><html:text property="user_id" styleId="user_id" name="Election_Manager_RegistrationActionForm"/></td>
                    <td width="300px" class="err" dir="<%=rtl%>" align="<%=align%>"> <html:messages id="err_name"  property="user_id">
                            <%=resource.getString("userid_cannotbe_blank")%><bean:write name="err_name" />
             </html:messages></td>
                </tr>--%>

              <%--  <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("password")%>*</td>
                     <td align="<%=align%>" dir="<%=rtl%>"><html:password property="password" styleId="pass" name="Election_Manager_RegistrationActionForm"/></td>
                     <td width="300px" class="err" dir="<%=rtl%>" align="<%=align%>"> <html:messages id="err_name"  property="password">
                            <bean:write name="err_name" />
             </html:messages><div id="repasswordErr1"></div></td>
                </tr>

                 <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("login.managesuperadminaccount.repassword")%>*</td>
                     <td align="<%=align%>" dir="<%=rtl%>"><html:password property="repassword" styleId="pass1" name="Election_Manager_RegistrationActionForm"/></td>
                     <td width="300px" class="err" dir="<%=rtl%>" align="<%=align%>"> <div id="repasswordErr"></div></td>
                </tr>
--%>
                <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><br><br><br><br><br><br></td>
                     <td align="<%=align%>" dir="<%=rtl%>"><input type="submit" name="submit" value="<%=resource.getString("submit")%> " class="txt2" onclick="return echeck(email_id.value);"/>&nbsp;&nbsp;<input type="button" name="cancel" value="<%=resource.getString("cancel")%>" class="txt2" onclick="quit()"/>&nbsp;&nbsp; <input type="button" name="reset" value="<%=resource.getString("reset")%>" onclick="return clearme()" class="txt2"/></td>
                </tr>

            </table>
            </tr>
        </table></html:form>
    </body>
    <script>
        function quit()
    {
        top.location="<%=contextPath%>/institute_admin/institute_admin_home.jsp";
    }

    function  checkPassword()
    {
        return echeck(email_id.value);
       // alert("hello");
        var pass = document.getElementById("pass").value;
        var pass1=document.getElementById("pass1").value;
        //alert(pass+" "+pass1);
        if(pass==pass1){
          //  alert("successfull");
            return true;}
        else{
            //alert("fail");
            document.getElementById("repasswordErr").innerHTML="<p>Password Mismatched!</p>";
            document.getElementById("pass1").value="";
            document.getElementById("pass1").focus();
            return false;
        }
    }


     function  checkPassLog()
    {
       // alert("hello");
        var pass = document.getElementById("pass").value;
        var id=document.getElementById("user_id").value;
        //alert(pass+" "+pass1);
        if(pass!=id){
          //  alert("successfull");
            return true;}
        else{
            //alert("fail");
            document.getElementById("repasswordErr1").innerHTML="<p>UserId & Password should be different</p>";
            document.getElementById("pass").value="";
            document.getElementById("pass").focus();
            return false;
        }
    }

    function clearme()
{


    document.getElementById("first_name").value = "";
    document.getElementById("last_name").value = "";
    document.getElementById("address").value = "";
    document.getElementById("city").value = "";
    document.getElementById("state").value="";
    document.getElementById("pin").value="";
    document.getElementById("country").value="";
    document.getElementById("gender").value="";
    document.getElementById("contact").value="";
    document.getElementById("mobile").value="";
    document.getElementById("email").value="";
    document.getElementById("department").value="";
    document.getElementById("staff_id").value="";
    document.getElementById("manager_id").value="";
   // document.getElementById("institute_id").value="";
    document.getElementById("user_id").value="";
    document.getElementById("pass").value="";
    document.getElementById("pass1").value="";

    return true;
}

function echeck(str) {
<%--var email_id=document.getElementById('email_id');

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

--%>
 		 return true
	}

    </script>


    <%
String msg=(String)request.getAttribute("msg");

if(msg==null){msg=(String)request.getAttribute("msg");}
if(msg!=null)
    {
    %>
    <script language="javascript" type="text/javascript">
        //window.location="/EMS-Struts/admin/admin_registration.jsp";
        //document.getElementById("staff_id").value = "";
        //document.getElementById("staffand The Mail of Confirmation Sent Successfully_id").focus();
        alert("<%=msg%>"+ " <%=resource.getString("mail_of_confirmation")%> ");
        top.location="/EMS/instituteadmin.do";
    </script>
    <%}%>



    <%
String msg1=(String)request.getAttribute("msg1");

if(msg1==null){msg1=(String)request.getAttribute("msg1");}
if(msg1!=null)
    {
    %>
    <script language="javascript" type="text/javascript">
        //window.location="/EMS-Struts/admin/admin_registration.jsp";
        //document.getElementById("staff_id").value = "";
        //document.getElementById("staff_id").focus();
        alert("<%=msg1%>");
    </script>
    <%}%>

    <%
String msg2=(String)request.getAttribute("msg2");

if(msg2==null){msg1=(String)request.getAttribute("msg2");}
if(msg2!=null)
    {
    %>
    <script language="javascript" type="text/javascript">
        //window.location="/EMS-Struts/admin/admin_registration.jsp";
        //document.getElementById("staff_id").value = "";
        //document.getElementById("staff_id").focus();
        alert("<%=msg2%>");
    </script>
    <%}%>


    <%
String msg3=(String)request.getAttribute("msg3");

if(msg3==null){msg1=(String)request.getAttribute("msg3");}
if(msg3!=null)
    {
    %>
    <script language="javascript" type="text/javascript">
        //window.location="/EMS-Struts/admin/admin_registration.jsp";
        //document.getElementById("staff_id").value = "";
        //document.getElementById("staff_id").focus();
        alert("<%=msg3%>");
    </script>
    <%}%>


</html>
