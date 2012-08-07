<%-- 
    Document   : institute_admin_details
    Created on : Mar 12, 2011, 4:26:08 PM
    Author     : Edrp-04
--%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>

<%@page pageEncoding="UTF-8"%>
<%
if(session.isNew()){
%>
<script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>
<%}%>
<%@page import="java.sql.*,com.myapp.struts.admin.AdminReg_Institute,com.myapp.struts.hbm.*,java.util.*" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%
String role=(String)session.getAttribute("login_role");
if(role.equalsIgnoreCase("insti-admin")|| role.equalsIgnoreCase("insti-admin,voter"))
   {
%>
<jsp:include page="/institute_admin/adminheader.jsp"/>
<%}
%>



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
     <tr><td width="15%" dir="<%=rtl%>"><%=resource.getString("institutename")%></td><td width="15%" ><input type="text" id="institute_name"      name="institute_name" value="<%=institute_name%>" tabindex="1" title="Enter Instutute Name" readonly dir="<%=rtl%>" ></td><td dir="<%=rtl%>" width="15%"><%=resource.getString("registrationid")%></td><td dir="<%=rtl%>" width="15%" ><input type="text" dir="<%=rtl%>" id="registration_request_id"  name="registration_request_id" value="<%=registration_id%>"  readonly></td></tr>

               <tr><td dir="<%=rtl%>"><%=resource.getString("instituteabbrevation")%></td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="abbreviated_name"   name="abbreviated_name" value="<%=abbreviated_name%>" tabindex="2"  title="Abbrivated name e.g. AMU(aligarh muslim University)" readonly></td><td dir="<%=rtl%>"><%=resource.getString("courtesy")%></td><td> <select name="courtesy"  dir="<%=rtl%>"  size="1" id="courtesy"   tabindex="13" title="courtesy" style="width:148px" disabled>
    <%if(courtesy.equals("mr")){%>
<option selected value="mr"><%=resource.getString("Mr")%></option>
<option value="mrs"><%=resource.getString("Mrs")%></option>
<option value="ms"><%=resource.getString("Ms")%></option>
 <option  value="Select"><%=resource.getString("select")%></option>

            <%}else if(courtesy.equals("mrs")){%>
<option  value="mr"><%=resource.getString("Mr")%></option>
<option selected value="mrs"><%=resource.getString("Mrs")%></option>
<option value="ms"><%=resource.getString("Ms")%></option>
<option  value="Select"><%=resource.getString("select")%></option>
            <%}
    else if(courtesy.equals("ms")){%>
<option value="mr"><%=resource.getString("Mr")%></option>
<option value="mrs"><%=resource.getString("Mrs")%></option>
<option  selected value="ms"><%=resource.getString("Ms")%></option>
 <option  value="Select"><%=resource.getString("select")%></option>
            <%}
else
    {
        %>
<option  selected value="">Select</option>
<option value="mr"><%=resource.getString("Mr")%></option>
<option value="mrs"><%=resource.getString("Mrs")%></option>
<option  selected value="ms"><%=resource.getString("Ms")%></option>
 <option  value="Select"><%=resource.getString("select")%></option>
<%
}
%>

</select>
                     <input type="hidden" id="courtesy" name="courtesy" value="<%=courtesy%>"/>
</td></tr>

               <tr><td dir="<%=rtl%>"><%=resource.getString("instituteAddress")%></td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="institute_address"   name="institute_address" value="<%=institute_address%>" tabindex="3"  title="Enter Address of Institute" readonly></td><td dir="<%=rtl%>"><%=resource.getString("firstname")%></td><td dir="<%=rtl%>"><input type="text" id="admin_fname"  name="admin_fname" value="<%=admin_fname%>" tabindex="14" title="Enter first Name" readonly></td></tr>
               <tr><td dir="<%=rtl%>"><%=resource.getString("lastname")%></td><td dir="<%=rtl%>"> <input type="text" id="admin_lname" dir="<%=rtl%>"  name="admin_lname" value="<%=admin_lname%>" tabindex="15" title="Enter Last Name" readonly></td><td><%=resource.getString("userid")%></td><td dir="<%=rtl%>"><input type="text" readonly id="userId" dir="<%=rtl%>"  name="user_id" value="<%=user_id%>" tabindex="13" title="Enter UserId"></td></tr>
              <tr><td dir="<%=rtl%>"><%=resource.getString("city")%></td><td>

                      <input type="text" id="city" dir="<%=rtl%>"  name="city" value="<%=city%>" tabindex="5" title="Enter City " readonly >


                  </td><td dir="<%=rtl%>"><%=resource.getString("designation")%></td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="admin_designation"  name="admin_designation" value="<%=admin_designation%>" tabindex="16" title="Enter Designation" readonly></td></tr>
              <tr><td dir="<%=rtl%>"><%=resource.getString("state")%></td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="state"   name="state" value="<%=state1%>" tabindex="6" title="Enter State" readonly></td><td><%=resource.getString("mobileno")%>
                  </td><td dir="<%=rtl%>"><input type="text" id="mobile_no" dir="<%=rtl%>"  name="mobile_no" value="<%=mobile_no%>" tabindex="17" title="Enter Mobile No with STD Code" readonly></td></tr>
              <tr><td dir="<%=rtl%>"><%=resource.getString("country")%></td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="country"   name="country" value="<%=country%>" tabindex="7" title="Enter Country" readonly></td>
                  <td dir="<%=rtl%>"><%=resource.getString("emailid")%></td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="admin_email"  name="admin_email" value="<%=admin_email%>" onBlur="echeck(admin_email.value);" tabindex="18" title="Enter E-mail Id" readonly>
                 <br/>
                     <div align="<%=align%>" dir="<%=rtl%>" id="searchResult" class="err" style="border:#000000; "></div>
                     <%
                      if(reg!=null)
                  out.println("*"+reg);
                 if(reg1!=null)
                  out.println("*"+reg1);
                         %>
              </tr>
              <tr><td dir="<%=rtl%>"><%=resource.getString("pin")%></td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="pin"  name="pin" value="<%=pin%>" tabindex="8" title="Enter PIN/ZIP Code" readonly></td>
                 <td dir="<%=rtl%>"><%=resource.getString("gender")%></td><td> <select name="gender" size="1" dir="<%=rtl%>"  id="gender" style="width:148px"   tabindex="19" title="gender" disabled>
            <%if(gender.equals("male")){%>
            <option selected value="male"><%=resource.getString("male")%></option>
            <option value="female"><%=resource.getString("female")%></option>
             <option  value="Select"><%=resource.getString("select")%></option>


            <%}
            else if(gender.equals("female")){%>
            <option  value="male"><%=resource.getString("male")%></option>
            <option selected value="female"><%=resource.getString("female")%></option>
             <option  value="Select"><%=resource.getString("select")%></option>

            <%}
               else
                   {%>
                <option selected value=""><%=resource.getString("select")%></option>
               <option  value="male"><%=resource.getString("male")%></option>
            <option  value="female"><%=resource.getString("female")%></option>
<%
               }
                %>

            </select>
                 <input type="hidden" id="gender" name="gender" value="<%=gender%>"/>
            </td>
            </tr>









            <tr><td dir="<%=rtl%>"><%=resource.getString("landlineno")%></td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="land_line_no"   name="land_line_no" value="<%=land_line_no%>" tabindex="9" title="Enter Land Line No" readonly></td>
                <td dir="<%=rtl%>"><%=resource.getString("password")%></td><td dir="<%=rtl%>"><input type="password" id="admin_password" dir="<%=rtl%>"  name="admin_password" value="<%=admin_password%>"  title="Enter Password" readonly/></td>
             </tr>

             <tr><td dir="<%=rtl%>"><%=resource.getString("typeofinstitute")%></td><td><select name="type_of_institute" dir="<%=rtl%>" tabindex="10" id="type_of_institute" style="width:148px" disabled >



    <%if(type_of_institute.equals("govt")){%>
<option selected value="govt"><%=resource.getString("govt")%></option>
<option value="semi_govt"><%=resource.getString("semigovt")%></option>
<option value="self_financed"><%=resource.getString("selffinanced")%></option>
<option value="other"><%=resource.getString("other")%></option>
<option  value="Select"><%=resource.getString("select")%></option>
            <%}%>
            <%if(type_of_institute.equals("semi_govt")){%>
<option value="govt"><%=resource.getString("govt")%></option>
<option selected value="semi_govt"><%=resource.getString("semigovt")%></option>
<option value="self_financed"><%=resource.getString("selffinanced")%></option>
<option value="other"><%=resource.getString("other")%></option>
<option value="Select"><%=resource.getString("select")%></option>
            <%}%>
            <%if(type_of_institute.equals("self_financed")){%>
<option  value="govt"><%=resource.getString("govt")%></option>
<option value="semi_govt"><%=resource.getString("semigovt")%></option>
<option selected value="self_financed"><%=resource.getString("selffinanced")%></option>
<option value="other"><%=resource.getString("other")%></option>
<option  value="Select"><%=resource.getString("select")%></option>
            <%}%>
            <%if(type_of_institute.equals("other")){%>
<option  value="govt"><%=resource.getString("govt")%></option>
<option value="semi_govt"><%=resource.getString("semigovt")%></option>
<option value="self_financed"><%=resource.getString("selffinanced")%></option>
<option selected value="other"><%=resource.getString("other")%></option>
<option  value="Select"><%=resource.getString("select")%></option>
            <%}%>

</select></td>

             </tr>
             <tr><td dir="<%=rtl%>"><%=resource.getString("institutedomain")%></td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="institute_domain" name="institute_domain" value="<%=domain%>" tabindex="11" title="Enter Institute Domain e.g amu.com" readonly></td></tr>
             <tr><td dir="<%=rtl%>"></td><td dir="<%=rtl%>"></td></tr>


             <tr><td dir="<%=rtl%>"><%=resource.getString("websitename")%></td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="Editbox12"  name="institute_website" value="<%=website%>" tabindex="12" title="Enter Institute Website" readonly></td></tr>
             <tr><td dir="<%=rtl%>"><%=resource.getString("instituteid")%></td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="Institute_id" name="institute_id" value="<%=institute_id%>"  readonly title="Enter Institute Id"></td>

             </tr><%--<tr><td>&nbsp;</td></tr>--%>
             <%--<tr><td></td><td></td><td><a href="#" onclick="switchMain()" class="txt" style="font-size:13px"><%=resource.getString("login.managesuperadminaccount.changepassword")%></a></td></tr>--%>
              <%-- <tr>
                  <td width="100%">
                      <div id="main" style="visibility: hidden"><table>
                          <tr>
                              <td>New Password:</td><td><input type="text"></td>
                          </tr>
                          <tr><td>Re-Enter Password</td><td><input type="text"></td>
                          </tr>
                          <tr>
                              <td><input type="button" value="Cancel" onclick="window.location.reload();"></td>
                          </tr>
                          </table>
                      </div>
                  </td>
              </tr>--%>





<tr><td colspan="4" align="center" dir="<%=rtl%>" class="txt2"><br><br>


        <%--<input type="submit" class="txt2" dir="<%=rtl%>" id="Button1" name="submit" value="<%=resource.getString("update")%>">&nbsp;&nbsp;<input type="button" dir="<%=rtl%>"  name="cancel" class="txt2" value="<%=resource.getString("cancel")%>" onclick="quit();">--%>
        </td></tr>
        </table><div id="main" style="visibility: hidden; top:-50; overflow: auto" >
            <table width="70%" class="txt" align="center" style="font-size:13px" dir="<%=rtl%>">
                
                <tr>
                    <td dir="<%=rtl%>">
                    <%=resource.getString("login.managesuperadminaccount.newpassword")%>
                    </td>
                    <%-- <td dir="<%=rtl%>"><input type="password" name="password"> </td>--%>
                    <td align="<%=align%>" dir="<%=rtl%>"><html:password property="password" styleId="pass" name="AdminViewActionForm"/></td>
                     <td width="300px" class="err" dir="<%=rtl%>" align="<%=align%>">  <div id="repasswordErr"></div>
             </td>
                </tr>
                <tr>
                    <td dir="<%=rtl%>">
                       <%=resource.getString("login.managesuperadminaccount.repassword")%> 
                    </td>
                    <td dir="<%=rtl%>">
                        <input type="password" name="repassword" id="pass1">
                    </td>
                </tr>
                <tr>
                     <td ><%--<input type="submit" value="Change Password" class="txt2"> <input type="button" onclick="window.location.reload();" class="txt2" value="Cancel" >--%></td>
                     <td><input type="submit" value="<%=resource.getString("login.managesuperadminaccount.changepassword")%>" class="txt2" onclick="return checkPassword();"><input type="button" onclick="window.location.reload();" class="txt2" value="<%=resource.getString("cancel")%>" ></td>

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
            
            document.getElementById("repasswordErr").innerHTML="<p>Password Mismatched!</p>";
            document.getElementById("pass1").value="";
            document.getElementById("pass1").focus();
            return false;
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
      {  str+="\n Password is required ";
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
<script>
    var msg = "Your Password Successfully Changed! Please Login Again.";
    top.location = "<%=request.getContextPath()%>"+"/login.jsp?msg="+msg;</script><%
    }
}catch(Exception e){
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %>sessionout();<%
    }

%>