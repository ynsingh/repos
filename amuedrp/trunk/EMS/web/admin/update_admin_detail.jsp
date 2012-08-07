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

<%
String id1=request.getParameter("id");
int id2=0;
if(id1==null)
 {
      id2=Integer.parseInt((String)session.getAttribute("reg"));
}
else
    {
    id2=Integer.parseInt(id1);
    }
List rst;
//out.println(id2);
AdminRegistrationDAO admindao = new AdminRegistrationDAO();
AdminReg_Institute adminInstituteReg = new AdminReg_Institute();
AdminRegistration adminReg = new AdminRegistration();
Institute institute = new Institute();
rst = (List)admindao.getAdminInstituteDetailsById(id2);


%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Untitled Page</title>

<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";
%>
<%
if(session.isNew()){
%>
<script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>
<%}%>

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
<link rel="stylesheet" href="/EMS/css/page.css"/>
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
    <html:form  method="post" action="/adminupdate" onsubmit="return validation();" >
        <table align="center" dir="<%=rtl%>"  class="txt" width="80%" style="font-family: arial;font-weight: bold;color:brown;font-size:13px">


            <tr><td  align="<%=align%>" dir="<%=rtl%>" colspan="2" ><br><br> <span class="txt">
</span><br>
             <br>


         </td></tr>


           <%
           String reg=(String)request.getAttribute("reg");
           String reg1=(String)request.getAttribute("reg1");

            
           %>


            <%
            if (rst!=null)
    {
    adminInstituteReg = (AdminReg_Institute)rst.get(0);
    adminReg = adminInstituteReg.getAdminRegistration();
    institute = adminInstituteReg.getInstitute();

       Iterator it = rst.iterator();
            if(it.hasNext()){
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
               <tr><td width="15%" dir="<%=rtl%>"><%=resource.getString("institutename")%>*</td><td width="15%" ><input type="text" id="institute_name"      name="institute_name" value="<%=institute_name%>" tabindex="1" title="Enter Instutute Name" dir="<%=rtl%>" ></td><td dir="<%=rtl%>" width="15%" ><%=resource.getString("registrationid")%></td><td dir="<%=rtl%>" width="15%" ><input type="text" dir="<%=rtl%>" id="registration_request_id"  name="registration_request_id" value="<%=registration_id%>"  readonly></td></tr>

             <tr><td dir="<%=rtl%>"><%=resource.getString("instituteabbrevation")%></td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="abbreviated_name"   name="abbreviated_name" value="<%=abbreviated_name%>" tabindex="2"  title="Abbrivated name e.g. AMU(aligarh muslim University)"></td><td dir="<%=rtl%>"><%=resource.getString("courtesy")%></td><td>
                <input type="text" name="courtesy"  dir="<%=rtl%>" value="<%=courtesy%>" size="1" id="courtesy"   tabindex="11" title="courtesy" style="width:148px"/>

              <%--       <select name="courtesy"  dir="<%=rtl%>"  size="1" id="courtesy"   tabindex="13" title="courtesy" style="width:148px">
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

</select>--%>
                     <input type="hidden" id="courtesy" name="courtesy" value="<%=courtesy%>"/>
</td></tr>

             <tr><td dir="<%=rtl%>"><%=resource.getString("instituteAddress")%>*</td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="institute_address"   name="institute_address" value="<%=institute_address%>" tabindex="3"  title="Enter Address of Institute"></td><td dir="<%=rtl%>"><%=resource.getString("firstname")%>*</td><td dir="<%=rtl%>"><input type="text" id="admin_fname"  name="admin_fname" value="<%=admin_fname%>" tabindex="14" title="Enter first Name" ></td></tr>
             <tr><td dir="<%=rtl%>"><%=resource.getString("lastname")%>*</td><td dir="<%=rtl%>"> <input type="text" id="admin_lname" dir="<%=rtl%>"  name="admin_lname" value="<%=admin_lname%>" tabindex="15" title="Enter Last Name" ></td><td><%=resource.getString("userid")%></td><td dir="<%=rtl%>"><input type="text" readonly id="userId" dir="<%=rtl%>"  name="user_id" value="<%=user_id%>" tabindex="13" title="Enter UserId" F></td></tr>
              <tr><td dir="<%=rtl%>"><%=resource.getString("city")%>*</td><td>

                 <input type="text" id="city" dir="<%=rtl%>"  name="city" value="<%=city%>" tabindex="5" title="Enter City " >


                 </td><td dir="<%=rtl%>"><%=resource.getString("designation")%></td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="admin_designation"  name="admin_designation" value="<%=admin_designation%>" tabindex="16" title="Enter Designation" ></td></tr>
             <tr><td dir="<%=rtl%>"><%=resource.getString("state")%>*</td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="state"   name="state" value="<%=state1%>" tabindex="6" title="Enter State" ></td><td><%=resource.getString("mobileno")%>*
                 </td><td dir="<%=rtl%>"><input type="text" id="mobile_no" dir="<%=rtl%>"  name="mobile_no" value="<%=mobile_no%>" tabindex="17" title="Enter Mobile No with STD Code" ></td></tr>
             <tr><td dir="<%=rtl%>"><%=resource.getString("country")%>*</td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="country"   name="country" value="<%=country%>" tabindex="7" title="Enter Country" ></td>
             <td dir="<%=rtl%>"><%=resource.getString("emailid")%>*</td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="admin_email"  name="admin_email" value="<%=admin_email%>" onBlur="echeck(admin_email.value);" tabindex="18" title="Enter E-mail Id">
                 <br/>
                     <div align="<%=align%>" dir="<%=rtl%>" id="searchResult" class="err" style="border:#000000; "></div>
                     <%
                      if(reg!=null)
                  out.println("*"+reg);
                 if(reg1!=null)
                  out.println("*"+reg1);
                         %>
              </tr>
             <tr><td dir="<%=rtl%>"><%=resource.getString("pin")%></td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="pin"  name="pin" value="<%=pin%>" tabindex="8" title="Enter PIN/ZIP Code" ></td>
             <td dir="<%=rtl%>"><%=resource.getString("gender")%>*</td><td> <select name="gender" size="1" dir="<%=rtl%>"  id="gender" style="width:148px"   tabindex="19" title="gender">
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









             <tr><td dir="<%=rtl%>"><%=resource.getString("landlineno")%></td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="land_line_no"   name="land_line_no" value="<%=land_line_no%>" tabindex="9" title="Enter Land Line No" ></td>
             <input type="hidden" id="admin_password" dir="<%=rtl%>"  name="admin_password" value="<%=admin_password%>"  title="Enter Password" readonly/>
             </tr>

             <tr><td dir="<%=rtl%>"><%=resource.getString("typeofinstitute")%>*</td><td><select name="type_of_institute" dir="<%=rtl%>" tabindex="10" id="type_of_institute" style="width:148px" >



    <%if(type_of_institute.equals("govt")){%>
<option selected value="govt"><%=resource.getString("govt")%></option>
<option value="semi_govt"><%=resource.getString("semigovt")%></option>
<option value="self_financed"><%=resource.getString("selffinanced")%></option>
<option value="other"><%=resource.getString("other")%></option>
<option  value="Select"><%=resource.getString("select")%></option>
            <%}
            if(type_of_institute.equals("semi_govt")){%>
<option value="govt"><%=resource.getString("govt")%></option>
<option selected value="semi_govt"><%=resource.getString("semigovt")%></option>
<option value="self_financed"><%=resource.getString("selffinanced")%></option>
<option value="other"><%=resource.getString("other")%></option>
<option value="Select"><%=resource.getString("select")%></option>
            <%}
      if(type_of_institute.equals("self_financed")){%>
<option  value="govt"><%=resource.getString("govt")%></option>
<option value="semi_govt"><%=resource.getString("semigovt")%></option>
<option selected value="self_financed"><%=resource.getString("selffinanced")%></option>
<option value="other"><%=resource.getString("other")%></option>
<option  value="Select"><%=resource.getString("select")%></option>
            <%} %>
            <% if(type_of_institute.equals("other")){%>
<option  value="govt"><%=resource.getString("govt")%></option>
<option value="semi_govt"><%=resource.getString("semigovt")%></option>
<option value="self_financed"><%=resource.getString("selffinanced")%></option>
<option selected value="other"><%=resource.getString("other")%></option>
<option  value="Select"><%=resource.getString("select")%></option>
            <%}
     if(type_of_institute.equals("Select"))
        {%>
            <option  value="govt"><%=resource.getString("govt")%></option>
<option value="semi_govt"><%=resource.getString("semigovt")%></option>
<option value="self_financed"><%=resource.getString("selffinanced")%></option>
<option  value="other"><%=resource.getString("other")%></option>
<option selected value="Select"><%=resource.getString("select")%></option>
            <%}%>

</select></td>

             </tr>
             <tr><td dir="<%=rtl%>"><%=resource.getString("institutedomain")%></td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="institute_domain" name="institute_domain" value="<%=domain%>" tabindex="11" title="Enter Institute Domain e.g amu.com" ></td></tr>
             <tr><td dir="<%=rtl%>"></td><td dir="<%=rtl%>"></td></tr>


             <tr><td dir="<%=rtl%>"><%=resource.getString("websitename")%></td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="Editbox12"  name="institute_website" value="<%=website%>" tabindex="12" title="Enter Institute Website"></td></tr>
             <tr><td dir="<%=rtl%>"><%=resource.getString("instituteid")%></td><td dir="<%=rtl%>"><input type="text" dir="<%=rtl%>" id="Institute_id" name="institute_id" value="<%=institute_id%>"  readonly title="Enter Institute Id"></td>

              </tr>
<tr><td colspan="4" align="center" dir="<%=rtl%>" class="txt2"><br><br>
         
                          
        <input type="submit" class="txt2" dir="<%=rtl%>" id="Button1" name="submit" value="<%=resource.getString("update")%>" onclick="return validation();">&nbsp;&nbsp;<input type="button" dir="<%=rtl%>"  name="cancel" class="txt2" value="<%=resource.getString("cancel")%>" onclick="quit();"></td></tr>
        </table>





<%}}%>
    </html:form>

</body>
<script>
    function quit()
    {
        location.href="<%=request.getContextPath()%>/admin/update_admin.jsp";
    }

</script>
<script type="text/javascript">



    function validation()
    {

    var email_id=document.getElementById('admin_email');
    var type_of_institute=document.getElementById('type_of_institute').options[document.getElementById('type_of_institute').selectedIndex].text;
    var gender=document.getElementById('gender').options[document.getElementById('gender').selectedIndex].text;
    
    var institute_name=document.getElementById('institute_name');
  
    var institute_address=document.getElementById('institute_address');
    var admin_fname=document.getElementById('admin_fname');
    var admin_lname=document.getElementById('admin_lname');
 
    var city=document.getElementById('city');
    var state=document.getElementById('state');
    var country=document.getElementById('country');
  
    var mobile_no=document.getElementById('mobile_no');
 
  var library_name=document.getElementById('institute_name');

  if (echeck(email_id.value)==false)
    {
		email_id.value="";

                email_id.focus();
		return false;
	}


var str="<%=resource.getString("enter_follwing")%>";
if(institute_name.value=="")
      {  str+="\n <%=resource.getString("institutename")%>";
       alert(str);
            document.getElementById('institute_name').focus();
            return false;
       }

   // if(abbreviated_name.value=="")
   //   {  str+="\n Enter Abbreviated name";
   ///       alert(str);
   //        document.getElementById('abbreviated_name').focus();
   //         return false;
  //     }

   //     if(courtesy=="Select")
   // {str+="\n Select Courtesy";

  //   alert(str);
   //         document.getElementById('courtesy').focus();
   //         return false;
   //    }
      if(institute_address.value=="")
       { str+="\n <%=resource.getString("instituteAddress")%>"; alert(str);
            document.getElementById('institute_address').focus();
            return false;
       }
    if(admin_fname.value=="")
       { str+="\n <%=resource.getString("firstname")%>"; alert(str);
            document.getElementById('admin_fname').focus();
            return false;
       }

       
 if(admin_lname.value=="")
       { str+="\n <%=resource.getString("lastname")%>"; alert(str);
            document.getElementById('admin_lname').focus();
            return false;
       }
        if(city.value=="")
       { str+="\n <%=resource.getString("city")%>";
           alert(str);
            document.getElementById('city').focus();
            return false;
       }
    //   if(admin_designation.value=="")
    //    {str+="\n Enter admin designation"; alert(str);
     //       document.getElementById('admin_designation').focus();
    //        return false;
    //   }
       if(state.value=="")
        {str+="\n <%=resource.getString("state")%>"; alert(str);
            document.getElementById('state').focus();
            return false;
       }
       if(mobile_no.value=="")
       { str+="\n <%=resource.getString("mobileno")%>" ; alert(str);
            document.getElementById('mobile_no').focus();
            return false;
       }
       if (isNaN(mobile_no.value))
        {
            str+="\n <%=resource.getString("valid_mobileno")%>";
            alert(str);
            document.getElementById('mobile_no').focus();
            return false;
        }
        if(country.value=="")
       { str+="\n <%=resource.getString("country")%>"; alert(str);
            document.getElementById('country').focus();
            return false;
       }
    if(email_id.value=="")
       { str+="\n <%=resource.getString("emailid")%>";
           alert(str);
            document.getElementById('admin_email').focus();
            return false;
       }
   //    if(pin.value=="")
    //   { str+="\n Enter pin Code"; alert(str);
    //        document.getElementById('pin').focus();
    //        return false;
    //   }

   if(gender=="<%=resource.getString("select")%>")
       { str+="\n <%=resource.getString("gender")%>"; alert(str);
            document.getElementById('gender').focus();
            return false;
       }


      


//if(land_line_no.value=="")
   //    { str+="\n Enter land line no"; alert(str);
   //         document.getElementById('land_line_no').focus();
   //         return false;
   //    }
   //    if (isNaN(land_line_no.value))
  //      {
    //        str+="\n Enter Valid Contact No";
   //         alert(str);
   //         document.getElementById('land_line_no').focus();
   //         return false;
   //     }
    if(type_of_institute=="<%=resource.getString("select")%>")
    {str+="\n <%=resource.getString("typeofinstitute")%>";
         alert(str);
            document.getElementById('type_of_institute').focus();
            return false;
       }


  //  if(institute_domain.value=="")
    //  {  str+="\n Enter institute domain"; alert(str);
 //           document.getElementById('institute_domain').focus();
  //          return false;
   //    }

  //   if(institute_website.value=="")
  //      {str+="\n Enter website"; alert(str);
  //          document.getElementById('institute_website').focus();
  //          return false;
   //    }

if(str=="<%=resource.getString("enter_follwing")%>")
    return true;
else
    {
        alert(str);
        document.getElementById('institute_name').focus();
        return false;
    }




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
