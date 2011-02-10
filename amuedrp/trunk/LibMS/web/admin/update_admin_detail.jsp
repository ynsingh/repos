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
ResultSet rst;
//out.println(id2);

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
       <table align="center"  class="txt" width="800px" style="font-family: arial;font-weight: bold;color:brown;font-size:13px">


     <tr><td  align="left" colspan="2" ><br><br> <span class="txt"><img src="/LibMS-Struts/images/Institutereg.png">
</span><br>
             <br>


         </td></tr>

           <%
           String reg=(String)request.getAttribute("reg");
           String reg1=(String)request.getAttribute("reg1");

            
           %>


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
               <tr><td width="150px">Institute Name</td><td><input type="text" id="institute_name"      name="institute_name" value="<%=institute_name%>" tabindex="1" title="Enter Instutute Name" ></td><td>Registration_id</td><td><input type="text" id="registration_request_id"  name="registration_request_id" value="<%=rst.getInt(1)%>"  readonly></td></tr>

             <tr><td>Institute Abbrivation</td><td><input type="text" id="abbreviated_name"   name="abbreviated_name" value="<%=abbreviated_name%>" tabindex="2"  title="Abbrivated name e.g. AMU(aligarh muslim University)"></td><td>Courtesy</td><td> <select name="courtesy"   size="1" id="courtesy"   tabindex="13" title="courtesy" style="width:148px">
    <%if(courtesy.equals("mr")){%>
<option selected value="mr">Mr</option>
<option value="mrs">Mrs</option>
<option value="ms">Ms.</option>
 <option  value="Select">Select</option>

            <%}else if(courtesy.equals("mrs")){%>
<option  value="mr">Mr</option>
<option selected value="mrs">Mrs</option>
<option value="ms">Ms.</option>
<option  value="Select">Select</option>
            <%}
    else if(courtesy.equals("ms")){%>
<option value="mr">Mr</option>
<option value="mrs">Mrs</option>
<option  selected value="ms">Ms.</option>
 <option  value="Select">Select</option>
            <%}
else
    {
        %>
<option  selected value="">Select</option>
<option value="mr">Mr</option>
<option value="mrs">Mrs</option>
<option  selected value="ms">Ms.</option>
 <option  value="Select">Select</option>
<%
}
%>

</select>
                     <input type="hidden" id="courtesy" name="courtesy" value="<%=courtesy%>"/>
</td></tr>

             <tr><td>Institute Address</td><td><input type="text" id="institute_address"   name="institute_address" value="<%=institute_address%>" tabindex="3"  title="Enter Address of Institute"></td><td>First Name</td><td><input type="text" id="admin_fname"  name="admin_fname" value="<%=admin_fname%>" tabindex="14" title="Enter first Name" ></td></tr>
             <tr><td>Library Name</td><td><input type="text"  id="library_name"  name="library_name" value="<%=library_name%>" tabindex="4" title="Enter Library Name"></td><td>Last Name</td><td><input type="text" id="admin_lname"  name="admin_lname" value="<%=admin_lname%>" tabindex="15" title="Enter Last Name" ></td></tr>
              <tr><td>City</td><td>

                 <input type="text" id="city"   name="city" value="<%=city%>" tabindex="5" title="Enter City " >


                 </td><td>Designation</td><td><input type="text" id="admin_designation"  name="admin_designation" value="<%=admin_designation%>" tabindex="16" title="Enter Designation" ></td></tr>
             <tr><td>State</td><td><input type="text" id="state"   name="state" value="<%=state1%>" tabindex="6" title="Enter State" ></td><td>Mobile No
                 </td><td><input type="text" id="mobile_no"   name="mobile_no" value="<%=mobile_no%>" tabindex="17" title="Enter Mobile No with STD Code" ></td></tr>
             <tr><td>Country</td><td><input type="text" id="country"   name="country" value="<%=country%>" tabindex="7" title="Enter Country" ></td>
             <td>Email ID</td><td><input type="text"  id="admin_email"  name="admin_email" value="<%=admin_email%>" onBlur="echeck(admin_email.value);" tabindex="18" title="Enter E-mail Id">
                 <br/>
                     <div align="left" id="searchResult" class="err" style="border:#000000; "></div>
                     <%
                      if(reg!=null)
                  out.println("*"+reg);
                 if(reg1!=null)
                  out.println("*"+reg1);
                         %>
              </tr>
             <tr><td>PIN</td><td><input type="text" id="pin"  name="pin" value="<%=pin%>" tabindex="8" title="Enter PIN/ZIP Code" ></td>
             <td>Gender</td><td> <select name="gender" size="1"  id="gender" style="width:148px"   tabindex="19" title="gender">
            <%if(gender.equals("male")){%>
            <option selected value="male">Male</option>
            <option value="female">Female</option>
             <option  value="Select">Select</option>


            <%}
            else if(gender.equals("female")){%>
            <option  value="male">Male</option>
            <option selected value="female">Female</option>
             <option  value="Select">Select</option>

            <%}
               else
                   {%>
                <option selected value="">Select</option>
               <option  value="male">Male</option>
            <option  value="female">Female</option>
<%
               }
                %>

            </select>
                 <input type="hidden" id="gender" name="gender" value="<%=gender%>"/>
            </td>
            </tr>









             <tr><td>Landline no</td><td><input type="text" id="land_line_no"   name="land_line_no" value="<%=land_line_no%>" tabindex="9" title="Enter Land Line No" ></td>
             <td>Password</td><td><input type="password" id="admin_password"   name="admin_password" value="<%=admin_password%>"  title="Enter Password"/></td>
             </tr>

             <tr><td>Type of Institute</td><td><select name="type_of_institute" tabindex="10" id="type_of_institute" style="width:148px" >



    <%if(type_of_institute.equals("govt")){%>
<option selected value="govt">Govt</option>
<option value="semi_govt">Semi Govt</option>
<option value="self_financed">Self Financed</option>
<option value="other">Other</option>
<option  value="Select">Select</option>
            <%}%>
            <%if(type_of_institute.equals("semi_govt")){%>
<option value="govt">Govt</option>
<option selected value="semi_govt">Semi Govt</option>
<option value="self_financed">Self Financed</option>
<option value="other">Other</option>
<option value="Select">Select</option>
            <%}%>
            <%if(type_of_institute.equals("self_financed")){%>
<option  value="govt">Govt</option>
<option value="semi_govt">Semi Govt</option>
<option selected value="self_financed">Self Financed</option>
<option value="other">Other</option>
<option  value="Select">Select</option>
            <%}%>
            <%if(type_of_institute.equals("other")){%>
<option  value="govt">Govt</option>
<option value="semi_govt">Semi Govt</option>
<option value="self_financed">Self Financed</option>
<option selected value="other">Other</option>
<option  value="Select">Select</option>
            <%}%>

</select></td>

             </tr>
             <tr><td>Institute Domain</td><td><input type="text" id="institute_domain" name="institute_domain" value="<%=domain%>" tabindex="11" title="Enter Institute Domain e.g amu.com" ></td></tr>
             <tr><td></td><td></td></tr>


             <tr><td>website name</td><td><input type="text" id="Editbox12"  name="institute_website" value="<%=website%>" tabindex="12" title="Enter Institute Website"></td></td></tr>
             <tr><td>Library ID</td><td><input type="text"  id="library_id" name="library_id" value="<%=library_id%>"  readonly title="Enter Library Id"></td>

              </tr>
<tr><td colspan="4" align="center" class="txt2"><br><br>
         
                          
        <input type="submit" class="txt2"  id="Button1" name="submit" value="Update">&nbsp;&nbsp;<input type="button"   name="cancel" class="txt2" value="Cancel" onclick="quit();"></td></tr>
        </table>





<%}%>
    </html:form>

</body>
<script>
    function quit()
    {
        history.back(-2);
    }

</script>
<script type="text/javascript">



    function validation()
    {

    var email_id=document.getElementById('admin_email');
    var type_of_institute=document.getElementById('type_of_institute').options[document.getElementById('type_of_institute').selectedIndex].text;
    var gender=document.getElementById('gender').options[document.getElementById('gender').selectedIndex].text;
   // var courtesy=document.getElementById('courtesy').options[document.getElementById('courtesy').selectedIndex].text;

    var institute_name=document.getElementById('institute_name');
  //  var abbreviated_name=document.getElementById('abbreviated_name');
    var institute_address=document.getElementById('institute_address');
    var admin_fname=document.getElementById('admin_fname');
    var admin_lname=document.getElementById('admin_lname');
 //   var institute_website=document.getElementById('institute_website');
 //   var admin_designation=document.getElementById('admin_designation');
    var city=document.getElementById('city');
    var state=document.getElementById('state');
    var country=document.getElementById('country');
  //  var pin=document.getElementById('pin');
  //  var land_line_no=document.getElementById('land_line_no');
    var mobile_no=document.getElementById('mobile_no');
  //  var institute_domain=document.getElementById('institute_domain');
  var library_name=document.getElementById('library_name');

  if (echeck(email_id.value)==false)
    {
		email_id.value="";

                email_id.focus();
		return false;
	}


var str="Enter Following Values:-";
if(institute_name.value=="")
      {  str+="\n Enter Institute Name ";
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
       { str+="\n Enter institute Address"; alert(str);
            document.getElementById('institute_address').focus();
            return false;
       }
    if(admin_fname.value=="")
       { str+="\n Enter admin first name"; alert(str);
            document.getElementById('admin_fname').focus();
            return false;
       }

       if(library_name.value=="")
       { str+="\n Enter Library name"; alert(str);
            document.getElementById('library_name').focus();
            return false;
       }
 if(admin_lname.value=="")
       { str+="\n Enter admin last name"; alert(str);
            document.getElementById('admin_lname').focus();
            return false;
       }
        if(city.value=="")
       { str+="\n Enter city";
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
        {str+="\n Enter State"; alert(str);
            document.getElementById('state').focus();
            return false;
       }
       if(mobile_no.value=="")
       { str+="\n Enter mobile no" ; alert(str);
            document.getElementById('mobile_no').focus();
            return false;
       }
       if (isNaN(mobile_no.value))
        {
            str+="\n Enter Valid Mobile No";
            alert(str);
            document.getElementById('mobile_no').focus();
            return false;
        }
        if(country.value=="")
       { str+="\n Enter Country"; alert(str);
            document.getElementById('country').focus();
            return false;
       }
    if(email_id.value=="")
       { str+="\n Enter Email ID";
           alert(str);
            document.getElementById('admin_email').focus();
            return false;
       }
   //    if(pin.value=="")
    //   { str+="\n Enter pin Code"; alert(str);
    //        document.getElementById('pin').focus();
    //        return false;
    //   }

   if(gender=="Select")
       { str+="\n Select Gender"; alert(str);
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
    if(type_of_institute=="Select")
    {str+="\n Select Type of Institute";
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

if(str=="Enter Following Values:-")
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
