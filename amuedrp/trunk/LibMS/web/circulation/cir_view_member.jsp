<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%--
     Design by Iqubal Ahmad
     Modified on 2011-02-02
     This jsp page is meant for Dynamically change button value as View,Update,Deleate this is only page from where
     View, update, Deleate is done also used for   Ajax for
     Dept,Fac,course & image upload can be done.
     This jsp page is third page  for one Complete Process  of member Registration.
--%>


<%@page  pageEncoding="UTF-8" contentType="text/html" import="java.util.*,java.io.*,java.sql.*,com.myapp.struts.hbm.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<jsp:include page="/admin/header.jsp"/>

<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
%>
<%
try{
locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
<script language="javascript" type="text/javascript">

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

    var keyValue = document.getElementById('emptype_id').options[document.getElementById('emptype_id').selectedIndex].value;

    if (keyValue=="Select")
    {


               document.getElementById('emptype_id').focus();
               document.getElementById('subemptype_id').options.length = 0;
                newOpt = document.getElementById('subemptype_id').appendChild(document.createElement('option'));
                newOpt.value = "Select";
                newOpt.text = "Select";


		return false;
	}
else
    {
    keyValue = keyValue.replace(/^\s*|\s*$/g,"");
if (keyValue.length >= 1)
{

var req = newXMLHttpRequest();

req.onreadystatechange = getReadyStateHandler(req, update1);

req.open("POST","<%=request.getContextPath()%>/searchsubemp.do", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send("getEmpType_Id="+keyValue);


}
return true;
}
}

function update1(cartXML)
{

var depts = cartXML.getElementsByTagName("emp_ids")[0];
var em = depts.getElementsByTagName("emp_id");
var em1 = depts.getElementsByTagName("emp_name");

        var newOpt =document.getElementById('subemptype_id').appendChild(document.createElement('option'));
        document.getElementById('subemptype_id').options.length = 0;

for (var i = 0; i < em.length ; i++)
{
var ndValue = em[i].firstChild.nodeValue;
var ndValue1=em1[i].firstChild.nodeValue;
newOpt = document.getElementById('subemptype_id').appendChild(document.createElement('option'));
newOpt.value = ndValue;
newOpt.text = ndValue1;


}
newOpt = document.getElementById('subemptype_id').appendChild(document.createElement('option'));
newOpt.value = "Select";
newOpt.text = "Select";
<%
String submemcat=(String)request.getAttribute("submemcat");
if(submemcat!=null)
if(submemcat.equalsIgnoreCase("Select")){%>
        document.getElementById('subemptype_id').value="Select";
        <%}%>
}

function search1() {

    var keyValue = document.getElementById('TXTFACULTY').options[document.getElementById('TXTFACULTY').selectedIndex].value;

if (keyValue=="Select")
    {


               document.getElementById('TXTFACULTY').focus();
               document.getElementById('TXTDEPT').options.length = 0;
                document.getElementById('TXTCOURSE').options.length = 0;
                newOpt = document.getElementById('TXTDEPT').appendChild(document.createElement('option'));
newOpt.value = "Select";
newOpt.text = "Select";
newOpt = document.getElementById('TXTCOURSE').appendChild(document.createElement('option'));
newOpt.value = "Select";
newOpt.text = "Select";


		return false;
	}
else
    {
    keyValue = keyValue.replace(/^\s*|\s*$/g,"");
if (keyValue.length >= 1)
{

var req = newXMLHttpRequest();

req.onreadystatechange = getReadyStateHandler(req, update);

req.open("POST","<%=request.getContextPath()%>/dept.do", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send("getFaculty_Id="+keyValue);


}
return true;
}
}

function search_dept() {

    var keyValue = document.getElementById('TXTDEPT').options[document.getElementById('TXTDEPT').selectedIndex].value;
var keyValue1 = document.getElementById('TXTFACULTY').options[document.getElementById('TXTFACULTY').selectedIndex].value;


if (keyValue=="Select")
    {


               document.getElementById('TXTDEPT').focus();
               document.getElementById('TXTCOURSE').options.length = 0;

newOpt = document.getElementById('TXTCOURSE').appendChild(document.createElement('option'));
newOpt.value = "Select";
newOpt.text = "Select";
		return false;
	}
else
    {
    keyValue = keyValue.replace(/^\s*|\s*$/g,"");
if (keyValue.length >= 1)
{

var req = newXMLHttpRequest();

req.onreadystatechange = getReadyStateHandler(req, update_course);

req.open("POST","<%=request.getContextPath()%>/course.do", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send("getDept_Id="+keyValue+"&getFacultyID="+keyValue1);




}

return true;
}
}
function update_course(cartXML)
{
var courses = cartXML.getElementsByTagName("course_ids")[0];
var em = courses.getElementsByTagName("course_id");
var em1 = courses.getElementsByTagName("course_name");
        var newOpt =document.getElementById('TXTCOURSE').appendChild(document.createElement('option'));
        document.getElementById('TXTCOURSE').options.length = 0;

//alert(em.length);

for (var i = 0; i < em.length ; i++)
{
var ndValue = em[i].firstChild.nodeValue;
var ndValue1=em1[i].firstChild.nodeValue;
newOpt = document.getElementById('TXTCOURSE').appendChild(document.createElement('option'));
newOpt.value = ndValue;
newOpt.text = ndValue1;
}
newOpt = document.getElementById('TXTCOURSE').appendChild(document.createElement('option'));
newOpt.value = "Select";
newOpt.text = "Select";
<%
String course=(String)request.getAttribute("course");
if(course!=null)
if(course.equalsIgnoreCase("Select")){%>
        document.getElementById('TXTCOURSE').value="Select";
        <%}%>
}



function update(cartXML)
{
var depts = cartXML.getElementsByTagName("dept_ids")[0];
var em = depts.getElementsByTagName("dept_id");
var em1 = depts.getElementsByTagName("dept_name");

        var newOpt =document.getElementById('TXTDEPT').appendChild(document.createElement('option'));
        document.getElementById('TXTDEPT').options.length = 0;

for (var i = 0; i < em.length ; i++)
{
var ndValue = em[i].firstChild.nodeValue;
var ndValue1=em1[i].firstChild.nodeValue;
newOpt = document.getElementById('TXTDEPT').appendChild(document.createElement('option'));
newOpt.value = ndValue;
newOpt.text = ndValue1;


}
newOpt = document.getElementById('TXTDEPT').appendChild(document.createElement('option'));
newOpt.value = "Select";
newOpt.text = "Select";


<%
String dept;
dept=(String)request.getAttribute("dept");
if(dept!=null){
if(!dept.equalsIgnoreCase("Select")){%>

 search_dept();
 <%}else{%>
     document.getElementById('TXTDEPT').value="Select";
     <%}}else{%>
         search_dept();
         <%}%>
}

var availableSelectList1;
var availableSelectList2;
var availableSelectList3;
var str1;
function dcheck() {

availableSelectList1 = document.getElementById("searchResult1");
var TXTREG_DATE=document.getElementById('TXTREG_DATE');


    if (isValidDate(TXTREG_DATE.value)==false)
    {

        availableSelectList1.innerHTML=str1;
		TXTREG_DATE.value="";

                TXTREG_DATE.focus();
		return false;
	}
        else
            {
              availableSelectList1.innerHTML="";
            }
}

function dcheck1() {

availableSelectList1 = document.getElementById("searchResult2");
var TXTEXP_DATE=document.getElementById('TXTEXP_DATE');

    if (isValidDate(TXTEXP_DATE.value)==false)
    {

        availableSelectList1.innerHTML="Invalid Date";
		TXTEXP_DATE.value="";

                TXTEXP_DATE.focus();
		return false;
	}
        else
            {
              availableSelectList1.innerHTML="";
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




    function validation()
    {
   var email_id=document.getElementById('mail2');
    var first_name=document.getElementById('fname2');

    var last_name=document.getElementById('lname2');
    var local=document.getElementById('add11');
    var phone=document.getElementById('ph11');
     var emptype_id = document.getElementById('emptype_id').options[document.getElementById('emptype_id').selectedIndex].value;
    var subemptype_id = document.getElementById('subemptype_id').options[document.getElementById('subemptype_id').selectedIndex].value;


    var city1=document.getElementById('city11');
    var state1=document.getElementById('state11');
    var country1=document.getElementById('country11');




var str= "<%=resource.getString("circulation.cir_newmember.enterfollowingvalues")%>:-";


   if(email_id.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.enteremailid")%> ";
            alert(str);
            document.getElementById('mail2').focus();
            return false;
        }



   if(first_name.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.enterfname")%>";
            alert(str);
            document.getElementById('fname2').focus();
            return false;
        }
if(last_name.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.enterlname")%>";
            alert(str);
            document.getElementById('lname2').focus();
            return false;
        }

  if(local.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.enterlocaladd")%>";
            alert(str);
            document.getElementById('add11').focus();
            return false;
        }

 if(phone.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.enterphno")%> ";
            alert(str);
            document.getElementById('ph11').focus();
            return false;
        }

if(emptype_id=="Select")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.entermemcategory")%> ";
            alert(str);
            document.getElementById('emptype_id').focus();
            return false;
        }
    if(subemptype_id=="Select")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.entersubmemtype")%>";
            alert(str);
            document.getElementById('subemptype_id').focus();
            return false;
        }


  if(city1.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.entercity")%> ";
            alert(str);
            document.getElementById('city11').focus();
            return false;
        }
if(state1.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.enterstate")%> ";
            alert(str);
            document.getElementById('state11').focus();
            return false;
        }

        if(country1.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.entercountry")%> ";
            alert(str);
            document.getElementById('country11').focus();
            return false;
        }

    var TXTREG_DATE=document.getElementById('TXTREG_DATE');
    var TXTEXP_DATE=document.getElementById('TXTEXP_DATE');





var str="<%=resource.getString("circulation.cir_newmember.enterfollowingvalues")%>:-";




    if(TXTREG_DATE.value=="")
       { str+="\n <%=resource.getString("circulation.cir_newmember.enterdateofreg")%>";
            alert(str);
            document.getElementById('TXTREG_DATE').focus();
            return false;

       }

    if(TXTEXP_DATE.value=="")
      {  str+="\n <%=resource.getString("circulation.cir_newmember.enterdateofexp")%>";
           alert(str);
           document.getElementById('TXTEXP_DATE').focus();
            return false;

      }
if(TXTREG_DATE.value!="")
      {

          if(dcheck()==false)
           return false;

     }
     if(TXTEXP_DATE.value!="")
      {

          if(dcheck1()==false)
           return false;

     }
if(IsDateGreater(TXTREG_DATE.value,TXTEXP_DATE.value)==true)
    {

       str+="\n <%=resource.getString("circulation.cir_newmember.dateofexpgreater")%>";
       alert(str);
         document.getElementById('TXTEXP_DATE').focus();
         return false;
    }

    if(echeck(email_id.value)==false){

     str+="\n Invalid E-mail ID ";
            alert(str);
              document.getElementById('mail2').value="";
            document.getElementById('mail2').focus();
            return false;

 }
 if(isNaN(phone.value)){

 str+="\n Invalid Mobile No ";
            alert(str);
            document.getElementById('ph11').focus();
            return false;

 }


if(str=="<%=resource.getString("circulation.cir_newmember.enterfollowingvalues")%>:-")
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


function IsDateGreater(DateValue1, DateValue2)
{

var DaysDiff;
Date1 = new Date(DateValue1);
Date2 = new Date(DateValue2);
DaysDiff = Math.floor((Date1.getTime() - Date2.getTime())/(1000*60*60*24));
if(DaysDiff >= 0)
{

  return true;
}
else
{

return false;
}
}




function copy(){
  
  var i=document.getElementById("lname1");
  var j=document.getElementById("lname2");
  i.value=j.value;
  var fname1=document.getElementById("fname1");
  var fname2=document.getElementById("fname2");
  fname1.value=fname2.value;
   var mname1=document.getElementById("mname1");
  var mname2=document.getElementById("mname2");
  mname1.value=mname2.value;
   var city1=document.getElementById("city1");
  var city11=document.getElementById("city11");
  city1.value=city11.value;
  var city2=document.getElementById("city2");
  var city22=document.getElementById("city22");
  city2.value=city22.value;
   var state1=document.getElementById("state1");
  var state11=document.getElementById("state11");
  state1.value=state11.value;
   var state2=document.getElementById("state2");
  var state22=document.getElementById("state22");
  state2.value=state22.value;
  var country1=document.getElementById("country1");
  var country11=document.getElementById("country11");
  country1.value=country11.value;
   var country2=document.getElementById("country2");
  var country22=document.getElementById("country22");
  country2.value=country22.value;
   var ph1=document.getElementById("ph1");
  var ph11=document.getElementById("ph11");
  ph1.value=ph11.value;
   var ph2=document.getElementById("ph2");
  var ph22=document.getElementById("ph22");
  ph2.value=ph22.value;
   var add1=document.getElementById("add1");
  var add11=document.getElementById("add11");
  add1.value=add11.value;
   var add2=document.getElementById("add2");
  var add22=document.getElementById("add22");
  add2.value=add22.value;
   var mail1=document.getElementById("mail1");
  var mail2=document.getElementById("mail2");
  mail1.value=mail2.value;
   var fax1=document.getElementById("fax1");
  var fax2=document.getElementById("fax2");
  fax1.value=fax2.value;
   var emptype_id1=document.getElementById("emptype_id1");
  var emptype_id=document.getElementById("emptype_id");
  emptype_id1.value=emptype_id.value;
   var subemptype_id1=document.getElementById("subemptype_id1");
  var subemptype_id=document.getElementById("subemptype_id");
  subemptype_id1.value=subemptype_id.value;
   var desg1=document.getElementById("desg1");
  var desg2=document.getElementById("desg2");
  desg1.value=desg2.value;
   var office1=document.getElementById("office1");
  var office2=document.getElementById("office2");
  office1.value=office2.value;
   var TXTFACULTY1=document.getElementById("TXTFACULTY1");
  var TXTFACULTY=document.getElementById("TXTFACULTY");
  TXTFACULTY1.value=TXTFACULTY.value;
  var TXTDEPT1=document.getElementById("TXTDEPT1");
  var TXTDEPT=document.getElementById("TXTDEPT");
  TXTDEPT1.value=TXTDEPT.value;
  var TXTCOURSE1=document.getElementById("TXTCOURSE1");
  var TXTCOURSE=document.getElementById("TXTCOURSE");
  TXTCOURSE1.value=TXTCOURSE.value;
  var TXTREG_DATE1=document.getElementById("TXTREG_DATE1");
  var TXTREG_DATE=document.getElementById("TXTREG_DATE");
  TXTREG_DATE1.value=TXTREG_DATE.value;
   var TXTEXP_DATE1=document.getElementById("TXTEXP_DATE1");
  var TXTEXP_DATE=document.getElementById("TXTEXP_DATE");
  TXTEXP_DATE1.value=TXTEXP_DATE.value;
   var sem1=document.getElementById("sem1");
  var sem2=document.getElementById("sem2");
  sem1.value=sem2.value;
   var mem_id1=document.getElementById("mem_id1");
   var mem_id2=document.getElementById("mem_id2");
   mem_id1.value=mem_id2.value;
  
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
   $("#TXTREG_DATE").datepicker(jQueryDatePicker1Opts);
   $("#TXTEXP_DATE").datepicker(jQueryDatePicker1Opts);
    $("#date_of_birth").datepicker(jQueryDatePicker1Opts);



});


</script>
<%! String button1;%>


 <%

     CirMemberDetail     cirmemdetail  =(CirMemberDetail)session.getAttribute("cirmemdetail");
     CirMemberAccount     cirmemacct  =(CirMemberAccount)session.getAttribute("cirmemaccountdetail");
     System.out.println(cirmemdetail+" ......... "+cirmemacct);
     String button=(String)request.getAttribute("button");
        if (button==null) button=(String)session.getAttribute("page");
        session.setAttribute("page", button);
        boolean read=true;
        boolean button_visibility=true;


String lname=(String)request.getAttribute("lname");
if(lname==null)
    lname=cirmemdetail.getLname();
String fname=(String)request.getAttribute("fname");
if(fname==null)
    fname=cirmemdetail.getFname();
String mname=(String)request.getAttribute("mname");
if(mname==null)
    mname=cirmemdetail.getMname();
String memcat=(String)request.getAttribute("memcat");
if(memcat==null)
    memcat=cirmemacct.getMemType();
 submemcat=(String)request.getAttribute("submemcat");
if(submemcat==null)
    submemcat=cirmemacct.getSubMemberType();
String add1=(String)request.getAttribute("add1");
if(add1==null)
    add1=cirmemdetail.getAddress1();
String add2=(String)request.getAttribute("add2");
if(add2==null)
    add2=cirmemdetail.getAddress2();
String city1=(String)request.getAttribute("city1");
if(city1==null)
    city1=cirmemdetail.getCity1();
String city2=(String)request.getAttribute("city2");
if(city2==null)
    city2=cirmemdetail.getCity2();
String state1=(String)request.getAttribute("state1");
if(state1==null)
    state1=cirmemdetail.getState1();
String state2=(String)request.getAttribute("state2");
if(state2==null)
    state2=cirmemdetail.getState2();
String country1=(String)request.getAttribute("country1");
if(country1==null)
    country1=cirmemdetail.getCountry1();
String country2=(String)request.getAttribute("country2");
if(country2==null)
    country2=cirmemdetail.getCountry2();
String pin1=(String)request.getAttribute("pin1");
if(pin1==null)
    pin1=cirmemdetail.getPin1();
String pin2=(String)request.getAttribute("pin2");
if(pin2==null)
    pin2= cirmemdetail.getPin2();
String ph1=(String)request.getAttribute("ph1");
if(ph1==null)
    ph1=cirmemdetail.getPhone1();
String ph2=(String)request.getAttribute("ph2");
if(ph2==null)
    ph2=cirmemdetail.getPhone2();
 course=(String)request.getAttribute("course");

if(course==null)
    course=cirmemacct.getCourseId();
 dept=(String)request.getAttribute("dept");

if(dept==null)
    dept=cirmemacct.getDeptId();
String mail_id=(String)request.getAttribute("mail_id");
if(mail_id==null)
    mail_id=cirmemdetail.getEmail();
String faculty=(String)request.getAttribute("faculty");
if(faculty==null)
    faculty=cirmemacct.getFacultyId();


String desg=(String)request.getAttribute("desg");

if(desg==null)
    desg=cirmemacct.getDesg();
String exp_date=(String)request.getAttribute("exp_date");
if(exp_date==null)
    exp_date=cirmemacct.getExpiryDate();
String fax=(String)request.getAttribute("fax");
if(fax==null)
    fax=cirmemdetail.getFax();
String office=(String)request.getAttribute("office");
if(office==null)
    office=cirmemacct.getOffice();
String reg_date=(String)request.getAttribute("reg_date");
if(reg_date==null)
    reg_date=cirmemacct.getReqDate();
String sem=(String)request.getAttribute("sem");
if(sem==null)
    sem=cirmemacct.getSemester();
String mem_id=(String)request.getAttribute("mem_id");
if(mem_id==null)
    mem_id=cirmemdetail.getId().getMemId();


     
    %>

    <%
 if(button.equals("View"))
 {
     button1=resource.getString("circulation.cir_member_reg.view");
     read=true;
    
   button_visibility=false;
 }
 if(button.equals("Update"))
 {
   button1=resource.getString("circulation.cir_member_reg.update");
     read=false;
      
   button_visibility=true;
 }
 if(button.equals("Delete"))
 {
   button1=resource.getString("circulation.cir_member_reg.delete");
   read=true;
    
   button_visibility=true;
 }
%>
<script>
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
function echeck(str) {


availableSelectList = document.getElementById("searchResult");
availableSelectList.innerHTML="";
		var at="@"
		var dot="."
		var lat=str.indexOf(at)
		var lstr=str.length
		var ldot=str.indexOf(dot)
		if (str.indexOf(at)==-1){
		   availableSelectList.innerHTML="Invalid E-mail ID";
                   document.getElementById('mail2').value="";
		   return false
		}

		if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
		    availableSelectList.innerHTML="Invalid E-mail ID";
                    document.getElementById('mail2').value="";
		   return false
		}

		if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
		     availableSelectList.innerHTML="Invalid E-mail ID";
                     document.getElementById('mail2').value="";
		    return false
		}

		 if (str.indexOf(at,(lat+1))!=-1){
		     availableSelectList.innerHTML="Invalid E-mail ID";
                     document.getElementById('mail2').value="";
		    return false
		 }

		 if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
		     availableSelectList.innerHTML="Invalid E-mail ID";
                     document.getElementById('mail2').value="";
		    return false
		 }

		 if (str.indexOf(dot,(lat+2))==-1){
		     availableSelectList.innerHTML="Invalid E-mail ID";
                     document.getElementById('mail2').value="";
		    return false
		 }

		 if (str.indexOf(" ")!=-1){
		     availableSelectList.innerHTML="Invalid E-mail ID";
                     document.getElementById('mail2').value="";
		    return false
		 }

 		 return true
	}
function showdiv(){

        var ele = document.getElementById("image1");


	if(ele.style.display == "block") {
    		ele.style.display = "none";

  	}
	else {
		ele.style.display = "block";

	}


    }
  function validation()
    {
   var email_id=document.getElementById('mail2');
    var first_name=document.getElementById('fname2');

    var last_name=document.getElementById('lname2');
    var local=document.getElementById('add11');
    var phone=document.getElementById('ph11');
     var emptype_id = document.getElementById('emptype_id').options[document.getElementById('emptype_id').selectedIndex].value;
    var subemptype_id = document.getElementById('subemptype_id').options[document.getElementById('subemptype_id').selectedIndex].value;


    var city1=document.getElementById('city11');
    var state1=document.getElementById('state11');
    var country1=document.getElementById('country11');




var str= "<%=resource.getString("circulation.cir_newmember.enterfollowingvalues")%>:-";


   if(email_id.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.enteremailid")%> ";
            alert(str);
            document.getElementById('mail2').focus();
            return false;
        }



   if(first_name.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.enterfname")%>";
            alert(str);
            document.getElementById('fname2').focus();
            return false;
        }
if(last_name.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.enterlname")%>";
            alert(str);
            document.getElementById('lname2').focus();
            return false;
        }

  if(local.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.enterlocaladd")%>";
            alert(str);
            document.getElementById('add11').focus();
            return false;
        }

 if(phone.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.enterphno")%> ";
            alert(str);
            document.getElementById('ph11').focus();
            return false;
        }

if(emptype_id=="Select")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.entermemcategory")%> ";
            alert(str);
            document.getElementById('emptype_id').focus();
            return false;
        }
    if(subemptype_id=="Select")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.entersubmemtype")%>";
            alert(str);
            document.getElementById('subemptype_id').focus();
            return false;
        }


  if(city1.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.entercity")%> ";
            alert(str);
            document.getElementById('city11').focus();
            return false;
        }
if(state1.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.enterstate")%> ";
            alert(str);
            document.getElementById('state11').focus();
            return false;
        }

        if(country1.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.entercountry")%> ";
            alert(str);
            document.getElementById('country11').focus();
            return false;
        }

    var TXTREG_DATE=document.getElementById('TXTREG_DATE');
    var TXTEXP_DATE=document.getElementById('TXTEXP_DATE');





var str="<%=resource.getString("circulation.cir_newmember.enterfollowingvalues")%>:-";




    if(TXTREG_DATE.value=="")
       { str+="\n <%=resource.getString("circulation.cir_newmember.enterdateofreg")%>";
            alert(str);
            document.getElementById('TXTREG_DATE').focus();
            return false;

       }

    if(TXTEXP_DATE.value=="")
      {  str+="\n <%=resource.getString("circulation.cir_newmember.enterdateofexp")%>";
           alert(str);
           document.getElementById('TXTEXP_DATE').focus();
            return false;

      }
if(TXTREG_DATE.value!="")
      {

          if(dcheck()==false)
           return false;

     }
     if(TXTEXP_DATE.value!="")
      {

          if(dcheck1()==false)
           return false;

     }
if(IsDateGreater(TXTREG_DATE.value,TXTEXP_DATE.value)==true)
    {

       str+="\n <%=resource.getString("circulation.cir_newmember.dateofexpgreater")%>";
       alert(str);
         document.getElementById('TXTEXP_DATE').focus();
         return false;
    }

    if(echeck(email_id.value)==false){

     str+="\n Invalid E-mail ID ";
            alert(str);
              document.getElementById('mail2').value="";
            document.getElementById('mail2').focus();
            return false;

 }
 if(isNaN(phone.value)){

 str+="\n Invalid Mobile No ";
            alert(str);
            document.getElementById('ph11').focus();
            return false;

 }


if(str=="<%=resource.getString("circulation.cir_newmember.enterfollowingvalues")%>:-")
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
function loadfaculty(){
    window.status="Press F1 for Help";
    search();
   
   search1();
   
}
</script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/newformat.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/helpdemo.js"></script>
</head>
<div
   style="  top:130px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">




<style type="text/css">
body
{

}
</style>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<style type="text/css">
a:active
{
   color: #0000FF;
}
</style>


<script language="javascript" type="text/javascript">
    function loadHelp()
    {
        window.status="Press F1 for Help";
    }
</script>



</head>
<body onload="return loadfaculty();loadHelp()">
    <%if (button.equals("Update")==true)
                        {%>

   <div id="image1"
   style="  top:130px;background: red;
   left:30%;
   overflow: hidden;
      position: absolute;
      display: none;"

      >

   <html:form action="/cirimageupload" method="post" styleId="form1" enctype="multipart/form-data">


      <table class="table" style="border:5px solid blue;" align="center" height="100px" width="400px">
           <tr><td>Select Image:
       <html:file  property="img" name="CirculationMemberActionForm" styleId="img" onchange="submit()"   onclick="copy()" />
      <input type="hidden" name="filename" tabindex="16" id="filename" />
      <input type="button" value="Cancel" onclick="showdiv();"/>         </td></tr></table>
      

           <html:hidden property="TXTLNAME" name="CirculationMemberActionForm" styleId="lname1"/>
          <html:hidden property="TXTFNAME" name="CirculationMemberActionForm" styleId="fname1"/>
          <html:hidden property="TXTMNAME" name="CirculationMemberActionForm" styleId="mname1"/>
          <html:hidden property="TXTADD1" name="CirculationMemberActionForm" styleId="add1"/>
          <html:hidden property="TXTADD2" name="CirculationMemberActionForm" styleId="add2"/>
          <html:hidden property="TXTCITY1" name="CirculationMemberActionForm" styleId="city1"/>
          <html:hidden property="TXTCITY2" name="CirculationMemberActionForm" styleId="city2"/>
          <html:hidden property="TXTSTATE1" name="CirculationMemberActionForm" styleId="state1"/>
          <html:hidden property="TXTSTATE2" name="CirculationMemberActionForm" styleId="state2"/>
          <html:hidden property="TXTCOUNTRY1" name="CirculationMemberActionForm" styleId="country1"/>
          <html:hidden property="TXTCOUNTRY2" name="CirculationMemberActionForm" styleId="country2"/>
          <html:hidden property="TXTPH1" name="CirculationMemberActionForm" styleId="ph1"/>
          <html:hidden property="TXTPH2" name="CirculationMemberActionForm" styleId="ph2"/>
          <html:hidden property="TXTEMAILID" name="CirculationMemberActionForm" styleId="mail1"/>
          <html:hidden property="TXTFAX" name="CirculationMemberActionForm" styleId="fax1"/>
          <html:hidden property="MEMCAT" name="CirculationMemberActionForm" styleId="emptype_id1"/>
          <html:hidden property="MEMSUBCAT" name="CirculationMemberActionForm" styleId="subemptype_id1"/>
          <html:hidden property="TXTDESG1" name="CirculationMemberActionForm" styleId="desg1"/>
          <html:hidden property="TXTOFFICE" name="CirculationMemberActionForm" styleId="office1"/>
          <html:hidden property="TXTFACULTY" name="CirculationMemberActionForm" styleId="TXTFACULTY1"/>
          <html:hidden property="TXTDEPT" name="CirculationMemberActionForm" styleId="TXTDEPT1"/>
          <html:hidden property="TXTCOURSE" name="CirculationMemberActionForm" styleId="TXTCOURSE1"/>
          <html:hidden property="TXTSEM" name="CirculationMemberActionForm" styleId="sem1"/>
          <html:hidden property="TXTREG_DATE" name="CirculationMemberActionForm" styleId="TXTREG_DATE1"/>
          <html:hidden property="TXTEXP_DATE" name="CirculationMemberActionForm" styleId="TXTEXP_DATE1"/>
          <html:hidden property="TXTMEMID" name="CirculationMemberActionForm" styleId="mem_id1"/>

    </html:form>

    </div>
 <%}%>
 <html:form action="/cirviewmember" method="post" onsubmit="return validation();">


   <table dir="<%=rtl%>"  align="center" width="80%"   class="table">



  <tr><td dir="<%=rtl%>" width="80%" height="25px"  class="headerStyle"  align="center">


		<%=resource.getString("circulation.cir_newmember.newmemberregistration")%>



        </td></tr>

  <tr><td  align="center">

          <br>
            <table dir="<%=rtl%>" width="80%" >



                <tr><td dir="<%=rtl%>" class="txtStyle" width="25%"><%=resource.getString("circulation.cir_newmember.memberid")%></td><td dir="<%=rtl%>" width="25%" class="table_textbox">
                        <html:text    property="TXTMEMID" readonly="true"  styleId="mem_id2" name="CirculationMemberActionForm" value="<%=mem_id%>" style="width:160px" onfocus="statwords('Member Id');" onblur="loadHelp()"/></td>
                    <td dir="<%=rtl%>" width="25%" rowspan="6" width="150px" valign="bottom">

                       <%=resource.getString("circulation.cir_newmember.imageupload")%>
                        
                    </td><td dir="<%=rtl%>" rowspan="6" width="25%" class="table_textbox" valign="bottom">

                        <%if (request.getAttribute("imagechange")!=null){%>
                        <html:img src="./circulation/upload.jsp"  alt="no Image Selected" width="120" height="120"/>
                        <%}else{%>
                        <html:img src="./circulation/viewimage.jsp" alt="no image selected" width="120" height="120" />
                        <%}%> <br/> <%if(button.equals("Update")==true)
                        {%>
                        <a href="#" onclick="javascript:showdiv();"> <%=resource.getString("circulation.cir_newmember.imageupload")%></a>
                        <%}%>
                    </td>
                   </tr>
                   <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.fname")%>*</td><td dir="<%=rtl%>" class="table_textbox"><html:text  readonly="<%=read%>"  property="TXTFNAME" styleId="fname2" value="<%=fname%>" name="CirculationMemberActionForm" style="width:160px" onfocus="statwords('Edit First Name');" onblur="loadHelp()"/><br/>
                 <html:messages id="err_name" property="TXTFNAME">
				<bean:write name="err_name" />

			</html:messages>

                </td>

                </tr>
                <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.mname")%></td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTMNAME" readonly="<%=read%>" styleId="mname2"   value="<%=mname%>" name="CirculationMemberActionForm" style="width:160px" onfocus="statwords('Edit Middle Name');" onblur="loadHelp()"/></td></tr>
            <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.lname")%>*</td><td dir="<%=rtl%>" class="table_textbox"><html:text  readonly="<%=read%>" property="TXTLNAME" styleId="lname2"  value="<%=lname%>" name="CirculationMemberActionForm" style="width:160px" onfocus="statwords('Edit Last Name');" onblur="loadHelp()" />
                <br/>
                 <html:messages id="err_name" property="TXTLNAME">
				<bean:write name="err_name" />

			</html:messages>
                </td>



                </tr>
            <tr>  <td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.email")%>*</td><td dir="<%=rtl%>" class="table_textbox"><html:text  readonly="<%=read%>" property="TXTEMAILID"  styleId="mail2" value="<%=mail_id%>" onblur="return echeck(mail2.value)" name="CirculationMemberActionForm" style="width:160px" onfocus="statwords('Edit email Id');" onblur="loadHelp()" />
                <br/><div align="left" class="err" id="searchResult" style="border:#000000; "></div>
                 <html:messages id="err_name" property="TXTEMAILID">
				<bean:write name="err_name" />

			</html:messages>
                </td>
                

            </tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.localadd")%>*</td><td dir="<%=rtl%>" class="table_textbox"> <html:text property="TXTADD1" styleId="add11" style="width:160px" value="<%=add1%>"  readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px" onfocus="statwords('Edit street/colony/moh Name');" onblur="loadHelp()"/>
                 <br/>
                 <html:messages id="err_name" property="TXTADD1">
		<bean:write name="err_name" />

		</html:messages>

                 </td>
             

             </tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.city")%>*</td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTCITY1" value="<%=city1%>" styleId="city11" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px" onfocus="statwords('Edit City Name');" onblur="loadHelp()"/>
                 <br/>


                 </td>
                  <td dir="<%=rtl%>"> <%=resource.getString("circulation.cir_newmember.typeofmem")%>*</td><td dir="<%=rtl%>" class="table_textbox">
                       <%if(button.equals("View")||button.equals("Delete")){%>
                        <html:select disabled="true"  property="MEMCAT" style="width:160px" styleId="emptype_id" value="<%=memcat%>" styleId="emptype_id">
                       <html:options  collection="list1" property="id.emptypeId" labelProperty="emptypeFullName"></html:options>
                     </html:select>

                   <%}else{%>
                   <html:select  property="MEMCAT" style="width:160px" value="<%=memcat%>" styleId="emptype_id" onchange="return search();" >
                       <html:options  collection="list1" property="id.emptypeId" labelProperty="emptypeFullName"></html:options>
                     </html:select>

              <%}%>
                     <br/>


                  </td>



               </tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.state")%>*</td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTSTATE1" styleId="state11" value="<%=state1%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"/>
                 <br/>


                 </td>
                  <td dir="<%=rtl%>"><%=resource.getString("circulation.cir_newmember.desg")%>*
                  </td><td dir="<%=rtl%>" class="table_textbox">
                       <%if(button.equals("View")||button.equals("Delete")){%>



                        <html:select disabled="true" property="MEMSUBCAT" styleId="subemptype_id" value="<%=submemcat%>" style="width:160px"  tabindex="3">

                     </html:select>
          <%}else{%>

         <html:select  property="MEMSUBCAT" styleId="subemptype_id" value="<%=submemcat%>" style="width:160px"  tabindex="3">
                     </html:select>
         <%}%>
                        <br/>


                      </td>

                </tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.country")%>*</td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTCOUNTRY1" styleId="country11" value="<%=country1%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px" onfocus="statwords('Edit Country Name');" onblur="loadHelp()"/>
                 <br/>


                 </td>
                   <td dir="<%=rtl%>"><%=resource.getString("circulation.cir_newmember.empdegn")%></td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTDESG1" value="<%=desg%>" styleId="desg2" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"onfocus="statwords('Edit Designation of the member');" onblur="loadHelp()"/></td>
                 </tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.mobile")%>*</td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTPH1"  styleId="ph11" value="<%=ph1 %>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px" onfocus="statwords('Edit 10 digit mobile Number');" onblur="loadHelp()"/>
                 <br/>


                 </td>
                  <td dir="<%=rtl%>"><%=resource.getString("circulation.cir_newmember.officename")%></td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTOFFICE" styleId="office2" value="<%=office%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px" onfocus="statwords('Edit Office Name');" onblur="loadHelp()"/></td>
               </tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.landlineno")%>.</td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTPH2" styleId="ph22" value="<%=ph2%>" readonly="<%=read%>"  name="CirculationMemberActionForm" style="width:160px" onfocus="statwords('Edit Land line Number if Any');" onblur="loadHelp()" /></td>
                <td dir="<%=rtl%>"> <%=resource.getString("circulation.cir_newmember.facof")%>
                  </td><td dir="<%=rtl%>" class="table_textbox">
            <%if(button.equals("View")||button.equals("Delete")){%>
            <html:select disabled="true"  property="TXTFACULTY" styleId="TXTFACULTY" style="width:160px" value="<%=faculty%>"  onclick="return search1()" tabindex="3">
                  <html:options  collection="list2"  labelProperty="facultyName" property="id.facultyId"></html:options>
                     </html:select>
                      <%}
         else
            {%>
            <html:select  property="TXTFACULTY" styleId="TXTFACULTY" value="<%=faculty%>" style="width:160px"  onclick="return search1()" tabindex="3">
                <html:option value="Select"><%=resource.getString("circulation.cir_newmember.select")%></html:option>
                <html:options  collection="list2"  labelProperty="facultyName" property="id.facultyId"></html:options>
                     </html:select>
                     <%

            } %>

                     

                      </td>
                </tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.fax")%></td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTFAX" styleId="fax2" value="<%=fax%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px" onfocus="statwords('Enter FAX Number if Any');" onblur="loadHelp()"/></td>
                   <td dir="<%=rtl%>"> <%=resource.getString("circulation.cir_newmember.dept")%>  </td><td dir="<%=rtl%>" class="table_textbox">
                     <%if(button.equals("View")||button.equals("Delete")){%>
                     <html:select  property="TXTDEPT" styleId="TXTDEPT" style="width:160px" disabled="true" value="<%=dept %>"  onchange="return search_dept();" tabindex="3">
                     <html:option value="Select"><%=resource.getString("circulation.cir_newmember.select")%></html:option>
                     </html:select>

   <%}
         else if(button.equals("Update"))
            {%>
            <html:select  property="TXTDEPT" styleId="TXTDEPT" style="width:160px"  value="<%=dept%>"  onchange="return search_dept();" tabindex="3">
               <html:option value="Select"><%=resource.getString("circulation.cir_newmember.select")%></html:option>
            </html:select>
     <%}else{%>
            <html:select  property="TXTDEPT" styleId="TXTDEPT" style="width:160px"  value="<%=dept %>"  onchange="return search_dept();" tabindex="3">
               <html:option value="Select"><%=resource.getString("circulation.cir_newmember.select")%></html:option>
            </html:select>




                <%

            } %>



                 </td>
                

             </tr>

            <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.permadd")%></td><td dir="<%=rtl%>" class="table_textbox"><html:text property="TXTADD2"  style="width:160px" styleId="add22" value="<%=add2%>"  readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"/></td>
                 <td dir="<%=rtl%>"> <%=resource.getString("circulation.cir_newmember.course")%> </td>

                  <td dir="<%=rtl%>" class="table_textbox">

                  <%if(button.equals("View")||button.equals("Delete")){%>
                  <html:select disabled="true"  property="TXTCOURSE" styleId="TXTCOURSE" style="width:160px" value="<%=course%>"   tabindex="3">
                     <html:option value="Select"><%=resource.getString("circulation.cir_newmember.select")%></html:option>
                  </html:select>

   <%}
          else if(button.equals("Update"))
            {%>
            <html:select  property="TXTCOURSE" styleId="TXTCOURSE" style="width:160px" value="<%=course%>"   tabindex="3">
                   <html:option value="Select"><%=resource.getString("circulation.cir_newmember.select")%></html:option>
                     </html:select>
    <%}else{%>
              <html:select   property="TXTCOURSE" styleId="TXTCOURSE" style="width:160px" value="<%=course %>"   tabindex="3">
                    <html:option value="Select"><%=resource.getString("circulation.cir_newmember.select")%></html:option>
                     </html:select>
<%} %>








</td>
                  </tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.city")%></td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTCITY2"styleId="city22" value="<%=city2%>" style="width:160px" readonly="<%=read%>"onfocus="statwords('Enter City Name');" onblur="loadHelp()"/></td>

                  <td> <%=resource.getString("circulation.cir_newmember.sem")%>
                  </td>
                  <td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTSEM" styleId="sem2"   styleClass="textBoxWidth" value="<%=sem%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px" onfocus="statwords('Enter Semester or Year of the course');" onblur="loadHelp()" />

                  </td>

              </tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.state")%></td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTSTATE2" styleId="state22" value="<%=state2%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"onfocus="statwords('Enter State Name');" onblur="loadHelp()"/></td>
                    <td dir="<%=rtl%>"> <%=resource.getString("circulation.cir_newmember.reg")%>*<br>(YYYY-MM-DD)
                 </td><td class="table_textbox">
                     
                       <%if(button.equals("View")||button.equals("Delete")){%>  
                    
                       <html:text  property="TXTREG_DATE" disabled="true"  styleId="TXTREG_DATE"  styleClass="textBoxWidth" value="<%=reg_date%>"  readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px" />
                     <%}else{%>
                     <html:text  property="TXTREG_DATE"  styleId="TXTREG_DATE"   styleClass="textBoxWidth" value="<%=reg_date%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px" />
                     <%}%>
                   
                       <br/> <div align="<%=align%>" class="err" id="searchResult1" style="border:#000000; "></div>

                  </td>
               </tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.country")%></td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTCOUNTRY2"  styleId="country22" value="<%=country2%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"onfocus="statwords('Enter Country Name');" onblur="loadHelp()"/></td>
               <td dir="<%=rtl%>" valign="top"><%=resource.getString("circulation.cir_newmember.exp")%>*<br>(YYYY-MM-DD)
                  </td>
                  <td dir="<%=rtl%>" class="table_textbox" valign="top">

                      <%if(button.equals("View")||button.equals("Delete")){%>
                      <html:text  property="TXTEXP_DATE" disabled="true" styleId="TXTEXP_DATE" value="<%=exp_date%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"/>

                         <%} else {%>
                         <html:text  property="TXTEXP_DATE"  styleId="TXTEXP_DATE" value="<%=exp_date%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"/>

                         <%}%>

                       <br/> <div align="<%=align%>" class="err" id="searchResult2" style="border:#000000;"></div>
                       </td>
             </tr>



     </table>
      </td></tr>
 <tr><td colspan="4" align="center" class="txt2">
         <%if(button_visibility){%>
         <input type="Submit" value="<%=button1%>"  onclick="return del()"/>
         <%}%>
         &nbsp;&nbsp;

         <input type="button" onclick="return quit()" value="<%=resource.getString("circulation.cir_newmember.back")%>"/>
         <br/>        </td>

          </tr>
     </table>

         <input type="hidden" id="btn" name="button" value="" />



 </html:form>


</body>


</div>
<script language="javascript" type="text/javascript">
    function quit()
    {
        location.href="<%=request.getContextPath()%>/circulation/cir_member_reg.jsp";
    }

    function del()
    {
       
      
       
     document.getElementById('btn').value="<%=button%>" ;
    var option=document.getElementById('btn').value;
    if(option=="Delete"){
        var a=confirm("<%=resource.getString("circulation.cir_newmember.douwanttodelrec")%> ");
       // alert(a);
        if(a!=true)
            {
                document.getElementById('btn').focus();
               return false;

        }
        else
            return true;
    }else{
         return validation();

    }
    }

</script>
 <script language="javascript" type="text/javascript">



  



   function validation()
    {
   var email_id=document.getElementById('mail2');
    var first_name=document.getElementById('fname2');

    var last_name=document.getElementById('lname2');
    var local=document.getElementById('add11');
    var phone=document.getElementById('ph11');
     var emptype_id = document.getElementById('emptype_id').options[document.getElementById('emptype_id').selectedIndex].value;
    var subemptype_id = document.getElementById('subemptype_id').options[document.getElementById('subemptype_id').selectedIndex].value;


    var city1=document.getElementById('city11');
    var state1=document.getElementById('state11');
    var country1=document.getElementById('country11');




var str= "<%=resource.getString("circulation.cir_newmember.enterfollowingvalues")%>:-";


   if(email_id.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.enteremailid")%> ";
            alert(str);
            document.getElementById('mail2').focus();
            return false;
        }



   if(first_name.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.enterfname")%>";
            alert(str);
            document.getElementById('fname2').focus();
            return false;
        }
if(last_name.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.enterlname")%>";
            alert(str);
            document.getElementById('lname2').focus();
            return false;
        }

  if(local.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.enterlocaladd")%>";
            alert(str);
            document.getElementById('add11').focus();
            return false;
        }

 if(phone.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.enterphno")%> ";
            alert(str);
            document.getElementById('ph11').focus();
            return false;
        }

if(emptype_id=="Select")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.entermemcategory")%> ";
            alert(str);
            document.getElementById('emptype_id').focus();
            return false;
        }
    if(subemptype_id=="Select")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.entersubmemtype")%>";
            alert(str);
            document.getElementById('subemptype_id').focus();
            return false;
        }


  if(city1.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.entercity")%> ";
            alert(str);
            document.getElementById('city11').focus();
            return false;
        }
if(state1.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.enterstate")%> ";
            alert(str);
            document.getElementById('state11').focus();
            return false;
        }

        if(country1.value=="")
        {
            str+="\n <%=resource.getString("circulation.cir_newmember.entercountry")%> ";
            alert(str);
            document.getElementById('country11').focus();
            return false;
        }

    var TXTREG_DATE=document.getElementById('TXTREG_DATE');
    var TXTEXP_DATE=document.getElementById('TXTEXP_DATE');





var str="<%=resource.getString("circulation.cir_newmember.enterfollowingvalues")%>:-";




    if(TXTREG_DATE.value=="")
       { str+="\n <%=resource.getString("circulation.cir_newmember.enterdateofreg")%>";
            alert(str);
            document.getElementById('TXTREG_DATE').focus();
            return false;

       }

    if(TXTEXP_DATE.value=="")
      {  str+="\n <%=resource.getString("circulation.cir_newmember.enterdateofexp")%>";
           alert(str);
           document.getElementById('TXTEXP_DATE').focus();
            return false;

      }
if(TXTREG_DATE.value!="")
      {

          if(dcheck()==false)
           return false;

     }
     if(TXTEXP_DATE.value!="")
      {

          if(dcheck1()==false)
           return false;

     }
if(IsDateGreater(TXTREG_DATE.value,TXTEXP_DATE.value)==true)
    {

       str+="\n <%=resource.getString("circulation.cir_newmember.dateofexpgreater")%>";
       alert(str);
         document.getElementById('TXTEXP_DATE').focus();
         return false;
    }

    if(echeck(email_id.value)==false){

     str+="\n Invalid E-mail ID ";
            alert(str);
              document.getElementById('mail2').value="";
            document.getElementById('mail2').focus();
            return false;

 }
 if(isNaN(phone.value)){

 str+="\n Invalid Mobile No ";
            alert(str);
            document.getElementById('ph11').focus();
            return false;

 }


if(str=="<%=resource.getString("circulation.cir_newmember.enterfollowingvalues")%>:-")
   {
       return true;

   }
else
    {

        alert(str);
        document.getElementById('mail2').focus();
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

</script>




  

</html>
