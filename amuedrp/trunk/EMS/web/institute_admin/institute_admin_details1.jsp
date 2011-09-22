<%-- 
    Document   : institute_admin_details
    Created on : Mar 12, 2011, 4:26:08 PM
    Author     : Edrp-04
--%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>

<%@page pageEncoding="UTF-8"%>
<%@page import="java.sql.*,com.myapp.struts.admin.AdminReg_Institute,com.myapp.struts.hbm.*,java.util.*" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>



<%
try{
if(session.getAttribute("institute_id")!=null){
System.out.println("institute_id"+session.getAttribute("institute_id"));

%>


<%
//String id1=request.getParameter("id");
//int id2=0;
//if(id1==null)
 //{
   //   id2=Integer.parseInt((String)request.getAttribute("reg"));
//}
//else
  //  {
  //  id2=Integer.parseInt(id1);
    //}
//List rst;
List rst = (List)request.getAttribute("resultset");

if(rst==null){System.out.println("ok"+rst); rst = (List)session.getAttribute("resultset");}
else{session.setAttribute("resultset", rst);}
System.out.println("ok"+rst);

//out.println(id2);
AdminRegistrationDAO admindao = new AdminRegistrationDAO();
AdminReg_Institute adminInstituteReg = new AdminReg_Institute();
AdminRegistration adminReg = new AdminRegistration();
Institute institute = new Institute();
//rst = (List)admindao.getAdminInstituteDetailsById(id2);

if (!rst.isEmpty()){
    adminInstituteReg = (AdminReg_Institute)rst.get(0);
    }


%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Institute_Admin_Account</title>

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
<link rel="stylesheet" href="/EMS-Struts/css/page.css"/>
<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
 .btn
    {
font-family:Tahoma;
    font-size:13px;
    letter-spacing:1px;
     padding-left:10px;
     color:brown;
     text-align:center;
     width:100px;


     font-weight:bold;
    }
.txt
{
font-family: Arial,Helvetica,sans-serif;
font-size:15px;
letter-spacing:1px;
color:brown;
font-weight:bold;
}
</style>
</head>
<body>
    <html:form  method="post" action="/changeadminpassword" onsubmit="return validation();"  >
        <table align="center" dir="<%=rtl%>"  class="txt" width="80%" style="font-family: arial;font-weight: bold;color:brown;font-size:13px">


            <tr><td  align="<%=align%>" dir="<%=rtl%>" colspan="2" ><br><br> <span class="txt" ><%--<img src="/EMS-Struts/images/Institutereg.png">--%>
</span><br>
             <br>


         </td></tr>


           <%
           String reg=(String)request.getAttribute("reg");
           String reg1=(String)request.getAttribute("reg1");
String msg=(String)request.getAttribute("msg");

           %>


            
            <%if(!rst.isEmpty())
    {
   //adminInstituteReg = (AdminReg_Institute)rst.get(0);
    adminReg = adminInstituteReg.getAdminRegistration();
    institute = adminInstituteReg.getInstitute();

      // Iterator it = rst.iterator();
        //    if(it.hasNext()){
    String registration_id=String.valueOf(adminReg.getRegistrationId());
     String institute_name=adminReg.getInstituteName();
     String abbreviated_name=adminReg.getAbbreviatedName();
     String institute_address=adminReg.getInstituteAddress();
     String city=adminReg.getCity();
     String state1=adminReg.getState();
     String country=adminReg.getCountry();
     String pin=adminReg.getPin();
     String land_line_no=adminReg.getLandLineNo();
     String mobile_no=adminReg.getMobileNo();
     String domain=adminReg.getDomain();
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
     String institute_id="";
     String user_id = adminReg.getUserId();
     if (institute!=null){
     institute_id = institute.getInstituteId();}
     //String institute_Id =
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
         type_of_institute="";
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
     if(user_id==null)
         user_id="";
     %>
     <input type="hidden" id="institute_name"   name="institute_name" value="<%=institute_name%>" tabindex="1" title="Enter Instutute Name" readonly dir="<%=rtl%>" >
     <input type="hidden" dir="<%=rtl%>" id="registration_request_id"  name="registration_request_id" value="<%=registration_id%>"  readonly>

     <input type="hidden" dir="<%=rtl%>" id="abbreviated_name"   name="abbreviated_name" value="<%=abbreviated_name%>" tabindex="2"  title="Abbrivated name e.g. AMU(aligarh muslim University)" readonly>
      <input type="hidden" id="courtesy" name="courtesy" value="<%=courtesy%>"/>


      <input type="hidden" dir="<%=rtl%>" id="institute_address"   name="institute_address" value="<%=institute_address%>" tabindex="3"  title="Enter Address of Institute" readonly>
      <input type="hidden" id="admin_fname"  name="admin_fname" value="<%=admin_fname%>" tabindex="14" title="Enter first Name" readonly/>
      <input type="hidden" id="admin_lname" dir="<%=rtl%>"  name="admin_lname" value="<%=admin_lname%>" tabindex="15" title="Enter Last Name" readonly>
      <input type="hidden" readonly id="userId" dir="<%=rtl%>"  name="user_id" value="<%=user_id%>" tabindex="13" title="Enter UserId">
      <input type="hidden" id="city" dir="<%=rtl%>"  name="city" value="<%=city%>" tabindex="5" title="Enter City " readonly >
<input type="hidden" dir="<%=rtl%>" id="admin_designation"  name="admin_designation" value="<%=admin_designation%>" tabindex="16" title="Enter Designation" readonly>
   <input type="hidden" dir="<%=rtl%>" id="state"   name="state" value="<%=state1%>" tabindex="6" title="Enter State" readonly>
        <input type="hidden" id="mobile_no" dir="<%=rtl%>"  name="mobile_no" value="<%=mobile_no%>" tabindex="17" title="Enter Mobile No with STD Code" readonly>
     <input type="hidden" dir="<%=rtl%>" id="country"   name="country" value="<%=country%>" tabindex="7" title="Enter Country" readonly>
     <input type="hidden" dir="<%=rtl%>" id="admin_email"  name="admin_email" value="<%=admin_email%>" onBlur="echeck(admin_email.value);" tabindex="18" title="Enter E-mail Id" readonly>
         
        <input type="hidden" dir="<%=rtl%>" id="pin"  name="pin" value="<%=pin%>" tabindex="8" title="Enter PIN/ZIP Code" readonly>
       
                 <input type="hidden" id="gender" name="gender" value="<%=gender%>"/>
            

<% if(msg!=null){%>
<p class="mess"><%=msg%></p>
<%}%>






           <input type="hidden" dir="<%=rtl%>" id="land_line_no"   name="land_line_no" value="<%=land_line_no%>" tabindex="9" title="Enter Land Line No" readonly>
        <input type="hidden" id="admin_password" dir="<%=rtl%>"  name="admin_password" value="<%=admin_password%>"  title="Enter Password" readonly/>
        <input type="hidden" name="type_of_institute" value="<%=type_of_institute%>" dir="<%=rtl%>" tabindex="10" id="type_of_institute" style="width:148px"/>
    
            <input type="hidden" dir="<%=rtl%>" id="institute_domain" name="institute_domain" value="<%=domain%>" tabindex="11" title="Enter Institute Domain e.g amu.com" readonly>
           <input type="hidden" dir="<%=rtl%>" id="Editbox12"  name="institute_website" value="<%=website%>" tabindex="12" title="Enter Institute Website" readonly>
           <input type="hidden" dir="<%=rtl%>" id="Institute_id" name="institute_id" value="<%=institute_id%>"  readonly title="Enter Institute Id">

       


<tr><td colspan="4" align="center" dir="<%=rtl%>" class="txt2"><br><br>


        </td></tr>
        </table><div id="main" style="visibility: visible; top:-50; overflow: auto" >
            <table width="70%" class="txt" align="center" style="font-size:13px" dir="<%=rtl%>">
                <tr>
                     <td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("userid")%></td>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:text property="user_id" value="<%=user_id%>" readonly="true" /></td>
                </tr>
                <tr>

                    <td dir="<%=rtl%>">
                    <%=resource.getString("login.managesuperadminaccount.newpassword")%>
                    </td>
                    <%-- <td dir="<%=rtl%>"><input type="password" name="password"> </td>--%>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:password property="password" styleId="pass" name="AdminViewActionForm"/></td>
                     
                </tr>
                <tr>
                    <td dir="<%=rtl%>">
                       <%=resource.getString("login.managesuperadminaccount.repassword")%> 
                    </td>
                    <td dir="<%=rtl%>">
                        <input type="password" name="repassword" id="pass1">
                    </td>
                    <td width="300px" class="err" dir="<%=rtl%>" align="<%=align%>">  <div id="repasswordErr"></div>
             </td>
                </tr>
                <tr>
                     <td ></td>
                     <td><input type="submit" value="<%=resource.getString("login.managesuperadminaccount.changepassword")%>" class="txt2" onclick="return checkPassword();"><input type="button" onclick="quit();" class="txt2" value="<%=resource.getString("cancel")%>" ></td>

                </tr>
            </table>
        </div>





<%}%>
    </html:form>

</body>


 <script>
function getStyle()
   {
      var temp = document.getElementById("main").style.visibility;

      return temp;
   }
function quit()
   {
      top.location="/EMS/instituteadmin.do"


   }
 function switchMain()
  {

      var current = getStyle();

      if( current == "hidden" )
       {
         document.getElementById("main").style.visibility = "visible";
         document.getElementById("main").style.top = "250px";
       }
       else
       {

         document.getElementById("main").style.visibility = "hidden";
       }
  }
<%--
  function back()
  {
      var current=getStyle();
      if (current=="visible")
          {
              document.getElementById("main").style.visiblity="hidden";
          }
  }--%>


function  checkPassword()
    {
       
       var pass = document.getElementById("pass").value;
       
       var pass1=document.getElementById("pass1").value;
      // if(pass=="")
          // alert("Please enter pasword");
        if(pass==pass1){
       
            return true;}
        else{
            if(pass1==" " || pass1== "")
             {
                 document.getElementById("repasswordErr").innerHTML="<p><%=resource.getString("please_reenter_password")%></p>";
            document.getElementById("pass1").value="";
            document.getElementById("pass1").focus();
            return false;}
        else{
        document.getElementById("repasswordErr").innerHTML="<p><%=resource.getString("password_mismatch")%></p>";
            document.getElementById("pass1").value="";
            document.getElementById("pass1").focus();
            return false;}
        }
    }

</script>




<script>
    function quit()
    {
        top.location="<%=request.getContextPath()%>/institute_admin/institute_admin_home.jsp";
    }

</script>
<script type="text/javascript">



    function validation()
    {

   
  var password=document.getElementById('pass');

 

var str="";




       if(password.value=="")
       {  str+="\n <%=resource.getString("password_required")%> ";
       alert(str);
            document.getElementById('pass').focus();
            return false;
       }

   
    
if(str=="")
    return true;





    }


function echeck(str) {
var email_id=document.getElementById('admin_email');

//alert(str);
availableSelectList = document.getElementById("searchResult");
 availableSelectList.innerHTML = "";
		var at="@"
		var dot="."
		var lat=str.indexOf(at)
		var lstr=str.length
		var ldot=str.indexOf(dot)
		if (str.indexOf(at)==-1){
		 availableSelectList.innerHTML += "Invalid E-mail ID"+"\n";
		   return false
		}

		if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
		  availableSelectList.innerHTML += "Invalid E-mail ID"+"\n";
		   return false
		}

		if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
		    availableSelectList.innerHTML += "Invalid E-mail ID"+"\n";
		    return false
		}

		 if (str.indexOf(at,(lat+1))!=-1){
		    availableSelectList.innerHTML += "Invalid E-mail ID"+"\n";
		    return false
		 }

		 if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
		    availableSelectList.innerHTML += "Invalid E-mail ID"+"\n";
		    return false
		 }

		 if (str.indexOf(dot,(lat+2))==-1){
		    availableSelectList.innerHTML += "Invalid E-mail ID"+"\n";
		    return false
		 }

		 if (str.indexOf(" ")!=-1){
		    availableSelectList.innerHTML += "Invalid E-mail ID"+"\n";
		    return false
		 }


 		 return true
	}




</script>

</html>






<%--   <script>
            function getDocHeight() {
    var D = document;
    return Math.max(
        Math.max(D.body.scrollHeight, D.documentElement.scrollHeight),
        Math.max(D.body.offsetHeight, D.documentElement.offsetHeight),
        Math.max(D.body.clientHeight, D.documentElement.clientHeight)
    );
}
           function load1(){
            alert(getDocHeight()+"px");
            var currentheight=getDocHeight()+"px";
            parent.getElementById("pageload").style.height = currentheight;


            alert(getDocHeight()+"px");
           }
</script>--%>
    
<%
}
else{%>
<%
String msg1=(String)request.getAttribute("msg");
if(msg1==null){msg1=(String)request.getAttribute("msg");}
if(msg1!=null)
    {
    %>
    <script>
        alert("<%=msg1%>");
        top.location = "<%=request.getContextPath()%>"+"/login.jsp"
    </script>
<%}%><%
    }
}catch(Exception e){
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %>sessionout();<%
    }

%>