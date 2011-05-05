<%@page language="java" import="com.myapp.struts.hbm.*,com.myapp.struts.AdminDAO.*" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<jsp:include page="header.jsp"/>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%

StaffDetail rst=(StaffDetail)session.getAttribute("updateresultset");
String button=(String)request.getAttribute("button");
String mainlib=(String)session.getAttribute("mainsublibrary");
String login_staff_id=(String)session.getAttribute("staff_id");
String message1=null;
String message2=null;
String msg3=null;

 String change=(String)request.getParameter("id");
 if(change==null)
   {  change=(String)request.getAttribute("change");}

      
  System.out.println(change+".........................")        ;



String staff_id=rst.getId().getStaffId();
String title="";
if(rst.getTitle()!=null)
{
    title=rst.getTitle().toString();
}
String first_name=rst.getFirstName();
String last_name=rst.getLastName();
String contact_no=rst.getContactNo().toString();
String mobile_no=rst.getMobileNo();
String email_id=rst.getEmailId();
String date_joining="";

if(rst.getDateJoining()!=null)
date_joining=rst.getDateJoining().toString();
String date_releaving="";
if(rst.getDateReleaving()!=null)
date_releaving=rst.getDateReleaving().toString();

String father_name=rst.getFatherName();
String date_of_birth="";
if(rst.getDateOfBirth()!=null)
date_of_birth=rst.getDateOfBirth().toString();
String gender="";
if(rst.getGender()!=null)
{
    gender=rst.getGender();
}
String address1=rst.getAddress1();
String city1=rst.getCity1();
String state1=rst.getState1();
String country1=rst.getCountry1();
String zip1=rst.getZip1();
String address2=rst.getAddress2();
String city2=rst.getCity2();
String state2=rst.getState2();
String country2=rst.getCountry2();
String zip2=rst.getZip2();
String user_sublibrary=rst.getSublibraryId();
String library_id=rst.getId().getLibraryId();


String sublibrary_id=(String)session.getAttribute("sublibrary_id");
String sublibrary_name=(String)session.getAttribute("sublibrary_name");


 button=(String)request.getAttribute("button");
 String data=(String)request.getAttribute("showstaff");

        if (button==null) button=(String)session.getAttribute("page");
        session.setAttribute("page", button);


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































%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LibMS : UpdateStaff Section</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script language="javascript" type="text/javascript">
/*
* Returns an new XMLHttpRequest object, or false if the browser
* doesn't support it
*/
var availableSelectList;
function newXMLHttpRequest() {
var xmlreq = false;
// Create XMLHttpRequest object in non-Microsoft browsers
if (window.XMLHttpRequest) {
xmlreq = new XMLHttpRequest();
} else if (window.ActiveXObject) {
try {
// Try to create XMLHttpRequest in later versions
// of Internet Explorer
xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
} catch (e1) {
// Failed to create required ActiveXObject
try {
// Try version supported by older versions
// of Internet Explorer
xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
} catch (e2) {
// Unable to create an XMLHttpRequest by any means
xmlreq = false;
}
}
}
return xmlreq;
}
/*
* Returns a function that waits for the specified XMLHttpRequest
* to complete, then passes it XML response to the given handler function.
* req - The XMLHttpRequest whose state is changing
* responseXmlHandler - Function to pass the XML response to
*/
function getReadyStateHandler(req, responseXmlHandler) {
// Return an anonymous function that listens to the XMLHttpRequest instance
return function () {
// If the request's status is "complete"
if (req.readyState == 4) {
// Check that we received a successful response from the server
if (req.status == 200) {
// Pass the XML payload of the response to the handler function.
responseXmlHandler(req.responseXML);
} else {
// An HTTP problem has occurred
alert("HTTP error "+req.status+": "+req.statusText);
}
}
}
}





var availableSelectList1;
var availableSelectList2;
var availableSelectList3;
var str1;
function dcheck() {

availableSelectList1 = document.getElementById("searchResult1");

    if (isValidDate(do_joining.value)==false)
    {

        availableSelectList1.innerHTML=str1;
		do_joining.value="";

                do_joining.focus();
		return false;
	}
        else
            {
              availableSelectList1.innerHTML="";
            }
}
function dcheck_releaving() {

availableSelectList2 = document.getElementById("searchResult2");

    if (isValidDate(do_releaving.value)==false)
    {
         availableSelectList2.innerHTML=str1;
		do_releaving.value="";

                do_releaving.focus();
		return false;
	}
        else
    {
    availableSelectList2.innerHTML="";
    }
}
function dcheck_dob() {

availableSelectList3 = document.getElementById("searchResult3");

    if (isValidDate(date_of_birth.value)==false)
    {
        availableSelectList3.innerHTML=str1;
		date_of_birth.value="";

                date_of_birth.focus();
		return false;
	}else{
          availableSelectList3.innerHTML="";
        }
}
function isValidDate(dateStr) {
// Checks for the following valid date formats:
// YYYY-mm-dd
// Also separates date into month, day, and year variables

var datePat = /^(\d{4})(\-)(\d{1,2})\2(\d{1,2})$/;

// To require a 4 digit year entry, use this line instead:
// var datePat = /^(\d{4})(\-)(\d{1,2})\2(\d{1,2})$/;

var matchArray = dateStr.match(datePat); // is the format ok?
if (matchArray == null) {
str1="Date is not in a valid format.";
return false;
}
month = matchArray[3]; // parse date into variables
day = matchArray[4];
year = matchArray[1];
if (month < 1 || month > 12) { // check month range
str1="Month must be between 1 and 12.";
return false;
}
if (day < 1 || day > 31) {
str1="Day must be between 1 and 31.";

return false;
}
if ((month==4 || month==6 || month==9 || month==11) && day==31) {
str1="Month "+month+" doesn't have 31 days!";

return false
}
if (month == 2) { // check for february 29th
var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
if (day>29 || (day==29 && !isleap)) {
str1="February " + year + " doesn't have " + day + " days!";

return false;
   }
}
return true;  // date is valid
}
//  End -->


function IsDateGreater(DateValue1, DateValue2)
{

var DaysDiff;
Date1 = new Date(DateValue1);
Date2 = new Date(DateValue2);
DaysDiff = Math.floor((Date1.getTime() - Date2.getTime())/(1000*60*60*24));
if(DaysDiff > 0)
{

return true;
}
else
{

return false;
}
}

function del()
{
   var answer = confirm ("Do you want to Delete Record?")
if (answer!=true)
    {
        document.getElementById('Button1').focus();
        return false;
    }
    else
        {
       document.Form1.action="<%=request.getContextPath()%>/admin/duplicate_message.jsp";
       document.Form1.method="post";
   //document.Form1.target="_blank";
        document.Form1.submit();
return true;

        }


}


function copy()
{

        if(document.getElementById('Checkbox1').checked==true)
        {


        var a1=document.getElementById('address1');
        var a2=document.getElementById('city1');
        var a3=document.getElementById('state1');
        var a4=document.getElementById('country1');
        var a5=document.getElementById('zip1');

        document.getElementById('address2').value=a1.value;
        document.getElementById('city2').value=a2.value;
        document.getElementById('state2').value=a3.value;
        document.getElementById('country2').value=a4.value;
        document.getElementById('zip2').value=a5.value;




    }
}


function send(){


<%
if(change.equals("admin"))
{%>
       location.href="<%=request.getContextPath()%>/admin/main.jsp";
     <%}else if(change.equals("1")){%>
          location.href="<%=request.getContextPath()%>/admin/acq_register.jsp";
         
<%}else{%>
      location.href="<%=request.getContextPath()%>/admin/viewstaff.jsp";
    <%}%>


}





</script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/cupertino/jquery.ui.all.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.datepicker.min.js"></script>
<style type="text/css">
.ui-datepicker
{
   font-family: Arial;
   font-size: 13px;
}
</style>





<script language = "Javascript">
/**
 * DHTML email validation script. Courtesy of SmartWebby.com (http://www.smartwebby.com/dhtml/)
 */

$(document).ready(function()
{
   var jQueryDatePicker1Opts =
   {
      dateFormat: 'yy-mm-dd',
      changeMonth: false,
      changeYear: false,
      showButtonPanel: false,
      showAnim: 'show'
   };
   $("#do_joining").datepicker(jQueryDatePicker1Opts);
   $("#do_releaving").datepicker(jQueryDatePicker1Opts);
    $("#date_of_birth").datepicker(jQueryDatePicker1Opts);



});

function echeck(str) {
availableSelectList = document.getElementById("searchResult");
		var at="@"
		var dot="."
		var lat=str.indexOf(at)
		var lstr=str.length
		var ldot=str.indexOf(dot)
		if (str.indexOf(at)==-1){
		   availableSelectList.innerHTML="Invalid E-mail ID";
                   document.getElementById('email_id').value="";
		   return false
		}

		if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
		    availableSelectList.innerHTML="Invalid E-mail ID";
                    document.getElementById('email_id').value="";
		   return false
		}

		if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
		     availableSelectList.innerHTML="Invalid E-mail ID";
                     document.getElementById('email_id').value="";
		    return false
		}

		 if (str.indexOf(at,(lat+1))!=-1){
		     availableSelectList.innerHTML="Invalid E-mail ID";
                     document.getElementById('email_id').value="";
		    return false
		 }

		 if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
		     availableSelectList.innerHTML="Invalid E-mail ID";
                     document.getElementById('email_id').value="";
		    return false
		 }

		 if (str.indexOf(dot,(lat+2))==-1){
		     availableSelectList.innerHTML="Invalid E-mail ID";
                     document.getElementById('email_id').value="";
		    return false
		 }

		 if (str.indexOf(" ")!=-1){
		     availableSelectList.innerHTML="Invalid E-mail ID";
                     document.getElementById('email_id').value="";
		    return false
		 }

 		 return true
	}

availableSelectList1.innerHTML="";
    availableSelectList2.innerHTML="";
    availableSelectList3.innerHTML="";

    function validation()
    {




    var email_id=document.getElementById('email_id');
    var first_name=document.getElementById('first_name');

    var last_name=document.getElementById('last_name');
    var dob=document.getElementById('date_of_birth');
    var do_joining=document.getElementById('do_joining');
    var do_releaving=document.getElementById('do_releaving');
   var address1=document.getElementById('address1');
    var city1=document.getElementById('city1');
    var state1=document.getElementById('state1');
    var country1=document.getElementById('country1');






var str="Enter Following Values:-";


   if(email_id.value=="")
        {
            str+="\n Enter Email ID";
            alert(str);
            document.getElementById('email_id').focus();
            return false;
        }

    if(first_name.value=="")
        {str+="\n Enter First Name ";
             alert(str);
             document.getElementById('first_name').focus();
            return false;

        }
    if(last_name.value=="")
      {  str+="\n Enter Last_name";
           alert(str);
           document.getElementById('last_name').focus();
            return false;

      }




  if(do_joining.value!="")
      {

          if(dcheck()==false)
           return false;

     }

   if(do_releaving.value!="")
      {
       if(dcheck_releaving()==false)
        return false;
      }
 if(dob.value!="")
      {
       if(dcheck_dob()==false)
        return false;
      }





    if(address1.value=="")
        {str+="\n Enter Address";
             alert(str);
             document.getElementById('address1').focus();
            return false;

        }
    if(city1.value=="")
        {str+="\n Enter city";
             alert(str);
         document.getElementById('city1').focus();
            return false;
        }

    if(state1.value=="")
      {  str+="\n Enter State";
           alert(str);
           document.getElementById('state1').focus();
            return false;

      }
    if(country1.value=="")
      {  str+="\n Enter Country";
           alert(str);
           document.getElementById('country1').focus();
            return false;

      }


if(IsDateGreater(do_joining.value,do_releaving.value)==true)
    {
       str+="\nDate of Releaving Should be greater than Date of Joining";
       alert(str);
         document.getElementById('do_releaving').focus();
         return false;
    }

if(str=="Enter Following Values:-")
   {
       return true;

   }
else
    {

        alert(str);
        document.getElementById('email_id').focus();
        return false;
    }




    }




</script>



<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LibMS : Update Staff Section</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

</head>
<body>



 <html:form  method="post"  action="/updatestaff"  onsubmit="return validation();">

      
<div
   style="  top:100px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">





   <table width="80%"   valign="top" align="center">

        <tr><td valign="top" height="100%" width="80%" align="center">



                   
 
                            <%
                            if( button.equals("View"))
                                {%>

                               <table width="60%" class="table"  border="1" align="center">

         <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Manage Staff</td></tr>
                <tr><td valign="top" align="right" style=" padding-left: 5px;">

     <font color="blue">  <b>Fields marked with asterisk (*) are Compuslory</b></font>


     <br/>
            <table align="center">

            <tr>
                <td width="10%">      Library</td >
                <td width="15%">
                    


                    <html:select  property="sublibrary_id" tabindex="3"  value="<%=user_sublibrary%>" disabled="true">
                        <html:options name="SubLibrary" collection="sublib" property="id.sublibraryId" labelProperty="sublibName"></html:options>
                     </html:select>
                </td>
            </tr>
            <tr><td width="15%">Employee Id</td><td><input type="text" id="staff_id"  disabled="true"  value="<%=staff_id%>"></td>
            </tr>
            <tr>
                <td>Email Id*</td>
                <td>
                    <input type="text" id="email_id"  tabindex="1" disabled  name="email_id" value="<%=email_id%>">
                                       
                </td>
            </tr>
            <tr>
                <td>First Name*</td>
                <td>
                                        <table><tr><td>
                                       <select name="courtesy" size="1" id="courtesy" tabindex="2" disabled >                                  <option value="Select">Select</option>
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
                                    <%}else if(title.equals("ms")){%>
                                    <option  value="mr">Mr.</option>
                                    <option  value="mrs">Mrs.</option>
                                    <option selected value="ms">Ms.</option>
                                    <%}else {%>
                                    <option selected value="Select"></option>
                                    <option  value="mr">Mr.</option>
                                    <option  value="mrs">Mrs.</option>
                                    <option  value="ms">Ms.</option>

                                    <%}%>
                                    </select></td>
                                    <td>&nbsp;<input type="text" id="first_name" style="width:100px;"  tabindex="3" name="first_name" value="<%=first_name%>" readonly></td>
                                        </table>
                 </td>
            </tr>
            <tr><td>Last Name*</td>
                <td><input type="text" id="last_name"  tabindex="4"  name="last_name" value="<%=last_name%>" readonly></td>

            </tr>
                                <tr><td>Employee's Gender</td><td>
                           <select name="gender" size="1" id="gender" tabindex="10" disabled>
                                 <%if(gender.equals("male")){%>
                                <option selected value="male">Male</option>
                                <option value="female">Female</option>
                                 <option  value="Select">Select</option>
                                <%}
                                else if(gender.equals("female"))
                                { %>
                                 <option value="male">Male</option>
                                <option selected value="female">Female</option>
                                 <option  value="Select">Select</option>
                                <%}else{%>
                                  <option value="male">Male</option>
                                <option  value="female">Female</option>
                                 <option selected value="Select"></option>
                                <%}%>
                                </select>


                                </td><td width="15%">Date of Joining<br>(YYYY-MM-DD)</td><td width="15%">
                                    <input type="text" id="do_joining"   name="do_joining"  value="<%=date_joining%>" disabled  tabindex="7"/>
             
                                    </td></tr>
                                <tr>        <td>Date of Birth<br>(YYYY-MM-DD)</td><td width="250px">
                                        <input type="text" id="date_of_birth" style=""  tabindex ="16"   name="date_of_birth" disabled value="<%=date_of_birth %>" />
                              


                               </td><td >Date of Releaving<br>(YYYY-MM-DD)</td><td >
                                   <input type="text" id="do_releaving" style="" name="do_releaving"  disabled  value="<%=date_releaving %>"  tabindex="8"/>
                 
                               </td>

                            </tr>


                            <tr> <td>Father Name</td><td ><input type="text" id="father_name"  tabindex="9" disabled name="father_name" value="<%=father_name%>"></td>
                                  </tr>





                                    <tr><td >Contact No<br>
                                            With STD/ISD Code</td><td ><input type="text"  id="contact_no"  tabindex="5"  disabled title="(STD)-(NUMBER)" name="contact_no" value="<%=contact_no%>"></td></tr>
                                    <tr><td >Mobile No</td><td><input type="text"  id="mobile_no"  tabindex="6"  disabled name="mobile_no" value="<%=mobile_no%>"></td>
                                </tr>

                                <tr>    <td>Mailing Address*</td><td><input type="textbox" name="address1" disabled id="address1" tabindex="11" value="<%=address1%>"  /></td></tr>
                                <tr><td width="185px">City*</td><td width="100px"><input type="text" id="city1" disabled tabindex="12" name="city1" value="<%=city1%>"></tr>
                                <tr><td>State*</td><td><input type="text" id="state1"  name="state1" tabindex="13" disabled value="<%=state1%>"/></td></tr>
                                <tr><td>Country*</td><td><input type="text" id="country1"  name="country1" disabled tabindex="14" value="<%=country1%>"></tr>
                            <tr>
                            </tr>
                            <tr><td width="250px">ZIP Code</td><td width="250px"><input type="text" id="zip1" disabled tabindex="15" name="zip1" value="<%=zip1%>"></td></tr>

                            
                            <tr>    <td>Permanent Address</td><td><input type="textbox" name="address2" disabled id="address2" tabindex="18"  value="<%=address2%>"/></td></tr>
                            <tr>    <td>City</td><td><input type="text" id="city2" tabindex="19" disabled  name="city2" value="<%=city2%>"></td></tr>
                            <tr>    <td>State</td><td><input type="text" id="state2"  name="state2" disabled tabindex="20" value="<%=state2%>"></td></tr>
                            <tr><td>Country</td><td><input type="text" id="country2"  name="country2" disabled tabindex="21" value="<%=country2%>"></td></tr>
                <tr> <td>ZIP Code</td><td><input type="text" tabindex="22" id="zip2" disabled  name="zip2" value="<%=zip2%>"></td><td colspan="2">
                            
                          
                            <input type="button" id="Button3" name="button" value="Back" onclick="return send()" />
                              
</td></tr>

                    </table>

                    </td></tr>   </table>
                                 <%}
                                else if(button.equals("Update"))
                                    {%>
                                          <table width="60%" class="table"  border="1" align="center">

         <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Manage Staff</td></tr>
                <tr><td valign="top" align="right" style=" padding-left: 5px;">

     <font color="blue">  <b>Fields marked with asterisk (*) are Compuslory</b></font>


     <br/>
            <table align="center">

            <tr>
                <td width="10%">      Library</td >
                <td width="15%">
                    <%if(sublibrary_id.equalsIgnoreCase(mainlib) && staff_id.equalsIgnoreCase(login_staff_id)==true){%>
                      <html:hidden property="sublibrary_id" value="<%=user_sublibrary%>"/>
                    <html:select  property="sublibrary_id" disabled="true" tabindex="3" value="<%=user_sublibrary%>">
                        <html:options name="SubLibrary" collection="sublib" property="id.sublibraryId" labelProperty="sublibName"></html:options>
                     </html:select>


                    <%}else if(sublibrary_id.equalsIgnoreCase(mainlib) && staff_id.equalsIgnoreCase("admin."+mainlib)==false){%>
                   
                    <html:select  property="sublibrary_id" tabindex="3" value="<%=user_sublibrary%>">
                        <html:options name="SubLibrary" collection="sublib" property="id.sublibraryId" labelProperty="sublibName"></html:options>
                     </html:select>
                    <%}else if(sublibrary_id.equalsIgnoreCase(mainlib))  {%>

                     <html:hidden property="sublibrary_id" value="<%=user_sublibrary%>"/>
                    <html:select  property="sublibrary_id" tabindex="3" disabled="true" value="<%=user_sublibrary%>">
                     <html:options name="SubLibrary" collection="sublib" property="id.sublibraryId" labelProperty="sublibName"></html:options>
                     </html:select>
                   
                    
                    <%}else{%>
                    <html:hidden property="sublibrary_id" value="<%=user_sublibrary%>"/>
                    <html:select  property="sublibrary_id" tabindex="3" disabled="true" value="<%=user_sublibrary%>">
                     <html:options name="SubLibrary" collection="sublib" property="id.sublibraryId" labelProperty="sublibName"></html:options>
                     </html:select>
                    <%}%>

                   
                </td>
            </tr>
            <tr><td width="15%">Employee Id</td><td><input type="text" id="staff_id"  disabled="true"  value="<%=staff_id%>"></td>
           </tr>
            <tr>
                <td>Email Id*</td>
                <td>
                   <input type="text" name="email_id" id="email_id" value="<%=email_id%>" onblur="return echeck(email_id.value)"/>

                                        <br/> <div align="left" class="err" id="searchResult" style="border:#000000; "></div>

                </td>
            </tr>
            <tr>
                <td>First Name*</td>
                <td>
                                        <table><tr><td>
                                       <select name="courtesy" size="1" id="courtesy" tabindex="2"  >                                  <option value="Select">Select</option>
                                       <%if(title.equals("mr"))
                                       {%>
                                    <option selected value="mr">Mr.</option>
                                    <option value="mrs">Mrs.</option>
                                    <option value="ms">Ms.</option>
                                     
                                    <%}
                                        else if(title.equals("mrs")){%>
                                    <option  value="mr">Mr.</option>
                                    <option selected value="mrs">Mrs.</option>
                                    <option value="ms">Ms.</option>
                                    
                                     <%}else if(title.equals("ms")){%>
                                    <option  value="mr">Mr.</option>
                                    <option  value="mrs">Mrs.</option>
                                    <option selected value="ms">Ms.</option>
                                   
                                    <%}else{%>
                                      <option  value="mr">Mr.</option>
                                    <option  value="mrs">Mrs.</option>
                                    <option value="ms">Ms.</option>
                                     <option selected  value="Select">Select</option>
                                    <%}%>
                                    </select></td>
                                    <td>&nbsp;<input type="text" id="first_name" style="width:100px;"  tabindex="3" name="first_name" value="<%=first_name%>"></td>
                                        </table>
                 </td>
            </tr>
            <tr><td>Last Name*</td>
                <td><input type="text" id="last_name"  tabindex="4"  name="last_name" value="<%=last_name%>"></td>

            </tr>
                                <tr><td>Employee's Gender</td><td>
                           <select name="gender" size="1" id="gender" tabindex="10" >
                                 <%if(gender.equals("male")){%>
                                <option selected value="male">Male</option>
                                <option value="female">Female</option>
                                 <option  value="Select">Select</option>
                                <%}
                                else if(gender.equals("female"))
                                { %>
                                 <option value="male">Male</option>
                                <option selected value="female">Female</option>
                                 <option  value="Select">Select</option>
                                <%}else{%>
                                <option value="male">Male</option>
                                <option  value="female">Female</option>
                                 <option selected value="Select">Select</option>
                                <%}%>
                                </select>


                                </td><td width="15%">Date Of Joining<br>(YYYY-MM-DD)</td><td width="15%">
                                            <input type="text" id="do_joining" value="<%=date_joining%>"   name="do_joining"   tabindex="7"/>
              <br/> <div class="err" align="left" id="searchResult1" ></div>

                                    </td></tr>
                                <tr>        <td>Date of Birth<br>(YYYY-MM-DD)</td><td width="250px">
                                         <input type="text" id="date_of_birth" style=""  tabindex ="16"  value="<%=date_of_birth%>" name="date_of_birth" />
                               <br/> <div align="left" class="err" id="searchResult3" style="border:#000000; "></div>



                               </td><td >Date Of Releaving<br>(YYYY-MM-DD)</td><td >
                                 <input type="text" id="do_releaving" style="" name="do_releaving" value="<%=date_releaving %>"    tabindex="8"/>
                 <br/> <div align="left" class="err" id="searchResult2" style="border:#000000; "></div>

                               </td>

                            </tr>


                            <tr> <td>Father Name</td><td ><input type="text" id="father_name"  tabindex="9"  name="father_name" value="<%=father_name%>"></td>
                                  </tr>





                                    <tr><td >Contact No<br>
                                            With STD/ISD Code</td><td ><input type="text"  id="contact_no"  tabindex="5"   title="(STD)-(NUMBER)" name="contact_no" value="<%=contact_no%>"></td></tr>
                                    <tr><td >Mobile No</td><td><input type="text"  id="mobile_no"  tabindex="6"   name="mobile_no" value="<%=mobile_no%>"></td>
                                </tr>

                                <tr>    <td>Mailing Address*</td><td><input type="textbox" name="address1"  id="address1" tabindex="11" value="<%=address1%>"  /></td></tr>
                                <tr><td width="185px">City*</td><td width="100px"><input type="text" id="city1"  tabindex="12" name="city1" value="<%=city1%>"></tr>
                                <tr><td>State*</td><td><input type="text" id="state1"  name="state1" tabindex="13"  value="<%=state1%>"/></td></tr>
                                <tr><td>Country*</td><td><input type="text" id="country1"  name="country1"  tabindex="14" value="<%=country1%>"></tr>
                            <tr>
                            </tr>
                            <tr><td width="250px">ZIP Code</td><td width="250px"><input type="text" id="zip1"  tabindex="15" name="zip1" value="<%=zip1%>"></td></tr>
 <tr>
                                <td colspan="2"><input type="checkbox" id="Checkbox1" name="check" value="on" tabindex="17" onclick="return copy()" >&nbsp;&nbsp;<b>Click Here</b>&nbsp;(If permanent address is same as mailing address)</td>

                            </tr>

                            <tr>    <td>Permanent Address</td><td><input type="textbox" name="address2"  id="address2" tabindex="18"  value="<%=address2%>"/></td></tr>
                            <tr>    <td>City</td><td><input type="text" id="city2" tabindex="19"   name="city2" value="<%=city2%>"></td></tr>
                            <tr>    <td>State</td><td><input type="text" id="state2"  name="state2"  tabindex="20" value="<%=state2%>"></td></tr>
                            <tr><td>Country</td><td><input type="text" id="country2"  name="country2"  tabindex="21" value="<%=country2%>"></td></tr>
                <tr> <td>ZIP Code</td><td><input type="text" tabindex="22" id="zip2"   name="zip2" value="<%=zip2%>"></td><td colspan="2">


                            <input type="submit" id="Button1" name="button" value="<%=button%>" class="btn" >
                           <input type="button" id="Button3" name="button" value="Back" onclick="return send()" class="btn" />


</td></tr>

                    </table>

                    </td></tr>   </table>
                                    
                                <%}
                                else if(button.equals("Delete"))
                                    {
                                %>
                    <table width="60%" class="table"  border="1" align="center">

         <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Manage Staff</td></tr>
                <tr><td valign="top" align="right" style=" padding-left: 5px;">

     <font color="blue">  <b>Fields marked with asterisk (*) are Compuslory</b></font>


     <br/>
            <table align="center">

            <tr>
                <td width="10%">      Library</td >
                <td width="15%">
                    <html:select  property="sublibrary_id" tabindex="3"  value="<%=user_sublibrary%>" disabled="true">
                     <html:options name="SubLibrary" collection="sublib" property="id.sublibraryId" labelProperty="sublibName"></html:options>
                     </html:select>
                    <input type="hidden" id="sublibrary_id"   name="sublibrary_id" value="<%=user_sublibrary%>">

                </td>
            </tr>
            <tr><td width="15%">Employee Id</td><td><input type="text" id="staff_id"  disabled="true"  value="<%=staff_id%>"></td>
             </tr>
            <tr>
                <td>Email Id*</td>
                <td>
                    <input type="text" id="email_id"  tabindex="1" disabled  name="email_id" value="<%=email_id%>">
                                       
                </td>
            </tr>
            <tr>
                <td>First Name*</td>
                <td>
                                        <table><tr><td>
                                       <select name="courtesy" size="1" id="courtesy" tabindex="2" disabled >                                  <option value="Select">Select</option>
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
                                    <td>&nbsp;<input type="text" id="first_name" style="width:100px;"  tabindex="3" name="first_name" value="<%=first_name%>" readonly></td>
                                        </table>
                 </td>
            </tr>
            <tr><td>Last Name*</td>
                <td><input type="text" id="last_name"  tabindex="4"  name="last_name" value="<%=last_name%>" readonly></td>

            </tr>
                                <tr><td>Employee's Gender</td><td>
                           <select name="gender" size="1" id="gender" tabindex="10" disabled>
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


                                </td><td width="15%">Date Of Joining<br>(YYYY-MM-DD)</td><td width="15%">
                                    <input type="text" id="do_joining"   name="do_joining"  value="<%=date_joining%>" disabled  tabindex="7"/>
             
                                    </td></tr>
                                <tr>        <td>Date of Birth<br>(YYYY-MM-DD)</td><td width="250px">
                                        <input type="text" id="date_of_birth" style=""  tabindex ="16"   name="date_of_birth" disabled value="<%=date_of_birth %>" />
                              


                               </td><td >Date Of Releaving<br>(YYYY-MM-DD)</td><td >
                                   <input type="text" id="do_releaving" style="" name="do_releaving"  disabled  value="<%=date_releaving %>"  tabindex="8"/>
                 
                               </td>

                            </tr>


                            <tr> <td>Father Name</td><td ><input type="text" id="father_name"  tabindex="9" disabled name="father_name" value="<%=father_name%>"></td>
                                  </tr>





                                    <tr><td >Contact No<br>
                                            With STD/ISD Code</td><td ><input type="text"  id="contact_no"  tabindex="5"  disabled title="(STD)-(NUMBER)" name="contact_no" value="<%=contact_no%>"></td></tr>
                                    <tr><td >Mobile No</td><td><input type="text"  id="mobile_no"  tabindex="6"  disabled name="mobile_no" value="<%=mobile_no%>"></td>
                                </tr>

                                <tr>    <td>Mailing Address*</td><td><input type="textbox" name="address1" disabled id="address1" tabindex="11" value="<%=address1%>"  /></td></tr>
                                <tr><td width="185px">City*</td><td width="100px"><input type="text" id="city1" disabled tabindex="12" name="city1" value="<%=city1%>"></tr>
                                <tr><td>State*</td><td><input type="text" id="state1"  name="state1" tabindex="13" disabled value="<%=state1%>"/></td></tr>
                                <tr><td>Country*</td><td><input type="text" id="country1"  name="country1" disabled tabindex="14" value="<%=country1%>"></tr>
                            <tr>
                            </tr>
                            <tr><td width="250px">ZIP Code</td><td width="250px"><input type="text" id="zip1" disabled tabindex="15" name="zip1" value="<%=zip1%>"></td></tr>

                            
                            <tr>    <td>Permanent Address</td><td><input type="textbox" name="address2" disabled id="address2" tabindex="18"  value="<%=address2%>"/></td></tr>
                            <tr>    <td>City</td><td><input type="text" id="city2" tabindex="19" disabled  name="city2" value="<%=city2%>"></td></tr>
                            <tr>    <td>State</td><td><input type="text" id="state2"  name="state2" disabled tabindex="20" value="<%=state2%>"></td></tr>
                            <tr><td>Country</td><td><input type="text" id="country2"  name="country2" disabled tabindex="21" value="<%=country2%>"></td></tr>
                <tr> <td>ZIP Code</td><td><input type="text" tabindex="22" id="zip2" disabled  name="zip2" value="<%=zip2%>"></td><td colspan="2">
                            
                          
                          <input type="submit" id="Button1" name="button" value="Confirm" class="txt2" onclick="return del()"/>
                              <input type="button" id="Button3" name="button" value="Back" onclick="return send()" class="txt2" />

                              
</td></tr>

                    </table>

                    </td></tr>   </table>

                           
                                <input type="hidden" name="button" value="<%=button%>"/>
                              <%}%>
                           

                            <br><br>

            </td></tr>
                
    </table>

                        




<input type="hidden" id="Editbox" tabindex="1"  name="employee_id" value="<%=staff_id %>">
</div>
</html:form>



   <%     if (msg3!=null){
 %>
 <script language="javascript">
 window.location="<%=request.getContextPath()%>/admin/acq_registerstaff.jsp";
 var x=document.getElementById("mess");
 x.value="You have Entered Duplicate Email-ID";
  </script>
 <%
}
%>

<%
if(message1!=null||message2!=null)
    {
    %>
 <script language="javascript">
 window.location="<%=request.getContextPath()%>/admin/acq_registerstaff.jsp";
 string s=<%=message1%>;
 s=s+"<br>"+<%=message2%>;
 alert(s);
 </script>
<%
}
%>




</html>
