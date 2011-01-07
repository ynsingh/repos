<%@ page language="java" import="java.sql.*"  %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="com.myapp.struts.*"%>
<jsp:include page="header.jsp" flush="true" />

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
<script language="javascript">
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

function search() {
  
    var keyValue = document.getElementById("email_id").value;
availableSelectList = document.getElementById("searchResult");
  availableSelectList.innerHTML = "";
    if (echeck(email_id.value)==false)
    {
		email_id.value="";
		
                email_id.focus();
		return false;
	}
else
    {
    keyValue = keyValue.replace(/^\s*|\s*$/g,"");
if (keyValue.length >= 1)
{
availableSelectList = document.getElementById("searchResult");
var req = newXMLHttpRequest();

req.onreadystatechange = getReadyStateHandler(req, update);

req.open("POST","<%=request.getContextPath()%>/email.do", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send("getEmail_Id="+keyValue);


}


    
return true;
}
}





function update(cartXML)
{
var emails = cartXML.getElementsByTagName("email_ids")[0];
var em = emails.getElementsByTagName("email_id");
availableSelectList.innerHTML = '';
for (var i = 0; i < em.length ; i++)
{
var ndValue = em[i].firstChild.nodeValue;
availableSelectList.innerHTML += ndValue+"\n";


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



</script>
<link rel="stylesheet" href="/LibMS-Struts/admin/cupertino/jquery.ui.all.css" type="text/css">
<script type="text/javascript" src="/LibMS-Struts/admin/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="/LibMS-Struts/admin/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="/LibMS-Struts/admin/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="/LibMS-Struts/admin/jquery.ui.datepicker.min.js"></script>
<style type="text/css">
.ui-datepicker
{
   font-family: Arial;
   font-size: 13px;
}
</style>
<script type="text/javascript">
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




</script>

  
<script language = "Javascript">
/**
 * DHTML email validation script. Courtesy of SmartWebby.com (http://www.smartwebby.com/dhtml/)
 */

function echeck(str) {
availableSelectList = document.getElementById("searchResult");
		var at="@"
		var dot="."
		var lat=str.indexOf(at)
		var lstr=str.length
		var ldot=str.indexOf(dot)
		if (str.indexOf(at)==-1){
		   availableSelectList.innerHTML="Invalid E-mail ID";
		   return false
		}

		if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
		    availableSelectList.innerHTML="Invalid E-mail ID";
		   return false
		}

		if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
		     availableSelectList.innerHTML="Invalid E-mail ID";
		    return false
		}

		 if (str.indexOf(at,(lat+1))!=-1){
		     availableSelectList.innerHTML="Invalid E-mail ID";
		    return false
		 }

		 if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
		     availableSelectList.innerHTML="Invalid E-mail ID";
		    return false
		 }

		 if (str.indexOf(dot,(lat+2))==-1){
		     availableSelectList.innerHTML="Invalid E-mail ID";
		    return false
		 }

		 if (str.indexOf(" ")!=-1){
		     availableSelectList.innerHTML="Invalid E-mail ID";
		    return false
		 }

 		 return true
	}


</script>



<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Untitled Page</title>

<link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>

</head>
<body>


    <html:form  method="post"    action="/registerstaff"  onsubmit="return validation();">

      
<div
   style="  top:150px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
    <table width="800px" height="800px"  valign="top" align="center">

        <tr><td valign="top" height="800px" width="800px" align="center">
                
            
<fieldset style="border:solid 1px brown;padding-left: 20px;padding-right: 20px" ><legend><img src="/LibMS-Struts/images/staff.png"></legend>
                   
                    <br>
                   <br/>
                    <span class="txt">Professional Details</span>
                    <hr>
                    <br>
                    <table  style="font-family: arial;font-weight: bold;color:brown;font-size:13px" >
                                           
                        <tr><td width="250px">Employee Id</td><td  width="250px" ><input type="text" id="staff_id" style="width:180px" disabled="true" name="" value="<%=staff_id%>"></td><td width="250px" >Email Id*</td><td ><input type="text" id="email_id" onBlur="return search();" tabindex="1"  name="email_id" value="">
                                <br/> <div align="left" id="searchResult" style="border:#000000; "></div>
                                    </td></tr>
                             
                                <tr><td colspan="4" height="8px"></td></tr>
                                <tr><td width="250px">First Name*
                                 



                                    </td>
                                    <td width="250px">
                                        <table cellspacing="5px"><tr><td>
                                        <select name="courtesy" size="1" id="courtesy" tabindex="2" style="width:59px;align:right">
                                    <option selected value="Select">Select</option>
                                    <option  value="mr">Mr.</option>
                                    <option value="mrs">Mrs.</option>
                                     <option  value="ms">Ms.</option>
                                    </select></td>
                                    <td>&nbsp;<input type="text" id="first_name" style="width:120px"  tabindex="3" name="first_name" value=""></td>
                                        </table>
                                    </td>
                                  <td width="250px">Last Name*</td><td width="150px"><input type="text" id="last_name"  tabindex="4"  name="last_name" value=""></td>
                                </tr>
                                    <tr><td colspan="4" height="8px"></td></tr>


                         
                              
                                    <tr><td width="250px">Contact No<br>
                                        With STD/ISD Code</td><td width="250px"><input type="text" style="width:180px" id="contact_no"  tabindex="5"  title="(STD)-(NUMBER)" name="contact_no" value=""></td>
                                <td width="250px">Mobile No</td><td width="250px"><input type="text"  id="mobile_no"  tabindex="6"  name="mobile_no" value=""></td>
                                </tr>
                                <tr><td colspan="4" height="8px"></td></tr>
                                                            
                                
                                
                            <tr><td width="250px">Date Of Joining*<br>(YYYY-MM-DD)</td><td width="250px">
                   <input type="text" id="do_joining"  style="width:180px;" name="do_joining"   tabindex="7"/>
              <br/> <div align="left" id="searchResult1" style="border:#000000; "></div>
                
                                    </td>
                           <td width="250px">Date Of Releaving*<br>(YYYY-MM-DD)</td><td width="250px">
                 <input type="text" id="do_releaving" style="" name="do_releaving"    tabindex="8"/>
                 <br/> <div align="left" id="searchResult2" style="border:#000000; "></div>

                               </td>
                            </tr>
                    </table>
                            <br><br>
                            <br>
                    <span class="txt">Personal Details</span>
                    <hr>
                    <br><br>
                            <table  style="font-family: arial;font-weight: bold;color:brown;font-size:13px" >

                                <tr><td width="250px">Father Name</td><td width="250px"><input type="text" id="father_name"  tabindex="9"  name="father_name" value=""></td>
                                
                                <td width="250px">Gender</td><td width="250px">
                                <select name="gender" size="1" id="gender" tabindex="10">
                                <option selected value="Select">Select</option>
                                <option  value="male">Male</option>
                                <option value="female">Female</option>
                                </select>


                                </td>
                                </tr>
                                <tr><td colspan="4" height="5px"></td></tr>
                                <tr>
                                <td width="250px">Mailing Address*</td><td width="250px"><textarea name="address1" id="address1" tabindex="11"  rows="5" cols="17"></textarea></td>
                                <td width="250px" colspan="2">
                                    <table style="font-family: arial;font-weight: bold;color:brown;font-size:13px">
                                        <tr><td width="165px">City</td><td width="100px"><input type="text" id="city1" tabindex="12" name="city1" value=""></td></tr>
                                        <tr><td colspan="2" height="7px"></td></tr>
                                          <tr><td>State</td><td><input type="text" id="state1"  name="state1" tabindex="13" value=""/></td></tr>
                                          <tr><td colspan="2" height="7px"></td></tr>
                                          <tr><td>Country</td><td><input type="text" id="country1"  name="country1" tabindex="14" value=""></td></tr>
                                        <tr><td colspan="2" height="7px"></td></tr>


                                    </table>



                                </td>
                                </tr>
                               
                                <tr><td colspan="4" height="5px"></td></tr>
                               
                            
                            
                             <tr><td width="250px">ZIP Code</td><td width="250px"><input type="text" id="zip1" tabindex="15" name="zip1" value=""></td>
                           <td width="250px">Date of Birth*<br>(YYYY-MM-DD)</td><td width="250px">
                                 <input type="text" id="date_of_birth" style=""  tabindex ="16"   name="date_of_birth" />
                               <br/> <div align="left" id="searchResult3" style="border:#000000; "></div>


                               </td>
                            </tr>
                            <tr><td colspan="4" height="5px"></td></tr>
                            <tr><td width="250px" colspan="4"><input type="checkbox" id="Checkbox1" name="check" value="on" tabindex="17" onclick="return copy()" >&nbsp;&nbsp;<b>Click Here</b>&nbsp;(If permanent address is same as mailing address)</td>

                            </tr>
                            <tr><td colspan="4" height="5px"></td></tr>
                            <tr>
                                <td width="250px">Permanent Address</td><td width="250px"><textarea name="address2" id="address2" tabindex="18" rows="5" cols="17"></textarea></td>
                                <td width="250px" colspan="2">
                                    <table style="font-family: arial;font-weight: bold;color:brown;font-size:13px">
                                        <tr><td width="165px">City</td><td width="100px"><input type="text" id="city2" tabindex="19"  name="city2" value=""></td></tr>
                                        <tr><td colspan="4" height="7px"></td></tr>
                                          <tr><td>State</td><td><input type="text" id="state2"  name="state2" tabindex="20" value=""></td></tr>
                                          <tr><td colspan="4" height="7px"></td></tr>
                                        <tr><td>Country</td><td><input type="text" id="country2"  name="country2" tabindex="21" value=""></td></tr>
                                        <tr><td colspan="4" height="7px"></td></tr>
                                        <tr><td width="250px">ZIP Code</td><td width="250px" COLSPAN="3"><input type="text" tabindex="22" id="zip2"  name="zip2" value=""></td>
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
<input type="hidden" id="email" tabindex="1"  name="email" value="">














</div>
    <div
   style="
      top: 900px;

      position: absolute;

      visibility: show;">
        <jsp:include page="footer.jsp" />

</div>
</html:form>



</html>
<script type="text/javascript">
   
    
     var gender;
    function validation()
    {

 //   availableSelectList1.innerHTML="";
  //  availableSelectList2.innerHTML="";
 //   availableSelectList3.innerHTML="";
 
    
    var email_id=document.getElementById('email_id');

   
	

    var gender=document.getElementById('gender').options[document.getElementById('gender').selectedIndex].text;
    var courtesy=document.getElementById('courtesy').options[document.getElementById('courtesy').selectedIndex].text;

    var first_name=document.getElementById('first_name');
    var last_name=document.getElementById('last_name');
    var do_joining=document.getElementById('do_joining');
    var do_releaving=document.getElementById('do_releaving');
    var father_name=document.getElementById('father_name');
   var contact_no=document.getElementById('contact_no');
   var mobile_no=document.getElementById('mobile_no');
    var address1=document.getElementById('address1');
    var city1=document.getElementById('city1');
    var state1=document.getElementById('state1');
    var country1=document.getElementById('country1');
    var zip1=document.getElementById('zip1');
    var dob=document.getElementById('date_of_birth');
    var address2=document.getElementById('address2');
    var city2=document.getElementById('city2');
    var state2=document.getElementById('state2');
    var country2=document.getElementById('country2');
    var zip2=document.getElementById('zip2');

 



var str="Enter Following Values:-";

    if(email_id.value=="")
        {
            str+="\n Enter Email ID";
            alert(str);
            document.getElementById('email_id').focus();
            return false;
        }
    if(courtesy=="Select")
   { str+="\n Select Courtesy";
        alert(str);
        document.getElementById('courtesy').focus();
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
      if(contact_no.value=="")
       { str+="\n Enter Contact No";
            alert(str);
            document.getElementById('contact_no').focus();
            return false;

       }
        if (isNaN(contact_no.value))
        {
            str+="\n Enter Valid Contact No";
            alert(str);
            document.getElementById('contact_no').focus();
            return false;
        }

       if(mobile_no.value=="")
       { str+="\n Enter mobile No";
            alert(str);
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

    if(do_joining.value=="")
       { str+="\n Enter Date of Joining";
            alert(str);
            document.getElementById('do_joining').focus();
            return false;

       }
        if(dcheck()==false)
     return false;
    if(do_releaving.value=="")
      {  str+="\n Enter Releaving date";
           alert(str);
           document.getElementById('do_releaving').focus();
            return false;

      }
       if(dcheck_releaving()==false)
     return false;

    if(father_name.value=="")
       { str+="\n Enter Father_name";
            alert(str);
            document.getElementById('father_name').focus();
            return false;

       }
    if(gender=="Select")
      {  str+="\n Select Gender";
           alert(str);
           document.getElementById('gender').focus();
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
    if(zip1.value=="")
     {   str+="\n Enter Zip Code";
          alert(str);
          document.getElementById('zip1').focus();
            return false;

     }
    if(dob.value=="")
       { str+="\n Enter date of Birth";
            alert(str);
            document.getElementById('date_of_birth').focus();
            return false;

       }


 if(dcheck_dob()==false)
     return false;


    if(address2.value=="")
       { str+="\n Enter Address 2";
            alert(str);
            document.getElementById('address2').focus();
            return false;

       }
    if(city2.value=="")
      {  str+="\n Enter city 2" ;
           alert(str);
           document.getElementById('city2').focus();
            return false;

      }
    if(state2.value=="")
       { str+="\n Enter State 2";
            alert(str);
            document.getElementById('state2').focus();
            return false;

       }
    if(country2.value=="")
       { str+="\n Enter Country 2";
            alert(str);
            document.getElementById('country2').focus();
            return false;

       }
    if(zip2.value=="")
      {  str+="\n Enter Zip Code 2";
           alert(str);
           document.getElementById('zip2').focus();
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
    


function send()
{
    window.location="/LibMS-Struts/admin/acq_register.jsp";
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



</script>


   <%     if (msg3!=null){
 %>
 <script language="javascript">
 window.location="/LibMS-Struts/admin/acq_registerstaff.jsp";
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
 window.location="/LibMS-Struts/admin/acq_registerstaff.jsp";
 string s=<%=message1%>;
 s=s+"<br>"+<%=message2%>;
 alert(s);
 </script>
<%
}
%>


