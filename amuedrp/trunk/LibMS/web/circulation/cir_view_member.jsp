<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%--
     Design by Iqubal Ahmad
     Modified on 2011-02-02
     This jsp page is meant for Dynamically change button value as View,Update,Deleate this is only page from where
     View, update, Deleate is done also used for   Ajax for
     Dept,Fac,course & image upload can be done.
     This jsp page is third page  for one Complete Process  of member Registration.
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<jsp:include page="/admin/header.jsp"/>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.sql.*,com.myapp.struts.hbm.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>



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
}

function search1() {

    var keyValue = document.getElementById('TXTFACULTY').options[document.getElementById('TXTFACULTY').selectedIndex].value;

if (keyValue=="Select")
    {


               document.getElementById('TXTFACULTY').focus();
               document.getElementById('TXTDEPT').options.length = 0;
                newOpt = document.getElementById('TXTDEPT').appendChild(document.createElement('option'));
                newOpt.value = "Select";
                newOpt.text = "Select";
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
newOpt = document.getElementById('TXTDEPT').appendChild(document.createElement('option'));
newOpt.value = "Select";
newOpt.text = "Select";
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
return true;
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
 search_dept();
}


function dcheck() {

availableSelectList1 = document.getElementById("searchResult1");

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
function dcheck_releaving() {

availableSelectList2 = document.getElementById("searchResult2");

    if (isValidDate(TXTEXP_DATE.value)==false)
    {
         availableSelectList2.innerHTML=str1;
		TXTEXP_DATE.value="";

                TXTEXP_DATE.focus();
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
function copy(){
   alert("HH"+j);
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
   // document.getElementById("1").value=document.getElementById("2").value;
  // document.CirculationMemberActionForm.getElementById("1").value=j;
  //form f = document.getElementsByName("CirculationMemberActionForm");

  // document.forms.getElementsByName("CirculationMemberActionForm").getElementsByName("TXTLNAME").value=j;

   // var j=form2.getElementById("2");
   // j.valueOf()=i.valueOf();
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

function loadfaculty(){
    search();
   search1();
}

</script>


 <%
/*
String memid=(String)session.getAttribute("memid");
List<SubLibrary> lst = (List<SubLibrary>)session.getAttribute("list");
String size = String.valueOf(lst.size());
String lname=(String)request.getAttribute("lname");
String fname=(String)request.getAttribute("fname");
String mname=(String)request.getAttribute("mname");
String memcat=(String)request.getAttribute("memcat");
String submemcat=(String)request.getAttribute("submemcat");
String add1=(String)request.getAttribute("add1");
String add2=(String)request.getAttribute("add2");
String city1=(String)request.getAttribute("city1");
String city2=(String)request.getAttribute("city2");
String state1=(String)request.getAttribute("state1");
String state2=(String)request.getAttribute("state2");
String country1=(String)request.getAttribute("country1");
String country2=(String)request.getAttribute("country2");
String pin1=(String)request.getAttribute("pin1");
String pin2=(String)request.getAttribute("pin2");
String ph1=(String)request.getAttribute("ph1");
String ph2=(String)request.getAttribute("ph2");
String course1=(String)request.getAttribute("course");
String dept=(String)request.getAttribute("dept");
String mail_id=(String)request.getAttribute("mail_id");
String faculty=(String)request.getAttribute("faculty");
String desg=(String)request.getAttribute("desg");
String exp_date=(String)request.getAttribute("exp_date");
String fax=(String)request.getAttribute("fax");
String office=(String)request.getAttribute("office");
String reg_date=(String)request.getAttribute("reg_date");
String sem=(String)request.getAttribute("sem");
String mem_id=(String)request.getAttribute("mem_id");
String file=(String) request.getAttribute("filename");*/
%>

 <%

     CirMemberDetail     cirmemdetail  =(CirMemberDetail)request.getAttribute("cirmemdetail");
     CirMemberAccount     cirmemacct  =(CirMemberAccount)request.getAttribute("cirmemaccountdetail");
     List<Department> dep=(List<Department>)session.getAttribute("departmentname");
    List<Faculty> fac =(List<Faculty>)session.getAttribute("facultyname");
   List<Courses> course =(List<Courses>)session.getAttribute("coursename");
   System.out.println(cirmemdetail+" ......... "+cirmemacct);

  

    // session.setAttribute("cirmemdetail", cirmemdetail);
   //  ResultSet rst = (ResultSet)session.getAttribute("faculty_resultset");
      
        String button=(String)request.getAttribute("button");
        if (button==null) button=(String)session.getAttribute("page");
        session.setAttribute("page", button);
        boolean read=true;
         boolean button_visibility=true;
      // if(rst!=null) rst.beforeFirst();

    %>

    <%
 if(button.equals("View"))
 {
   read=true;
   button_visibility=false;
 }
 if(button.equals("Update"))
 {
   read=false;
   button_visibility=true;
 }
 if(button.equals("Delete"))
 {
   read=true;
   button_visibility=true;
 }
%>




<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/newformat.css"/>
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






</head>
<body onload="return loadfaculty();">


    <html:form action="/cirviewmember" method="post" onsubmit="return validation();">


   <table  align="center" width="800px" height="400px"  class="table">



  <tr><td    class="headerStyle"  align="center">


		New Member Registration



        </td></tr>

  <tr><td valign="center" align="center" height="400px" >

          <br>
            <table width="800px" >



                <tr><td class="txtStyle" width="150px">Member ID</td><td class="table_textbox">
                        <html:text    property="TXTMEMID" readonly="true" value="<%= cirmemacct.getId().getMemid() %>" name="CirculationMemberActionForm" style="width:160px" /></td>
                    <td rowspan="4" width="150px" valign="bottom">

                        <%if (button.equals("Update")==false)
                        {%>
                        Image Upload
                        <%}%>
                        
                    </td><td rowspan="4" class="table_textbox" valign="bottom">

                        <%if (request.getAttribute("imagechange")!=null){%>
                        <html:img src="./circulation/upload.jsp"  alt="no Image Selected" width="120" height="120"/>
                        <%}else{%>
                        <html:img src="./circulation/viewimage.jsp" width="128" height="120" />
                        <%}%>
                        <%--       <html:file property="uploadedFile"  styleId="uploadedFile" styleClass="table_text" value="<%=s%>"  name="CirculationMemberActionForm"/></td> --%>

                   </tr>
                   <tr><td class="txtStyle">First Name*</td><td class="table_textbox"><html:text  readonly="<%=read%>"  property="TXTFNAME" styleId="fname2" value="<%=cirmemdetail.getFname()%>" name="CirculationMemberActionForm" style="width:160px" /><br/>
                 <html:messages id="err_name" property="TXTFNAME">
				<bean:write name="err_name" />

			</html:messages>

                </td>

                </tr>
            <tr><td class="txtStyle">Middle Name</td><td class="table_textbox"><html:text  property="TXTMNAME" readonly="<%=read%>"  value="<%=cirmemdetail.getMname()%>" name="CirculationMemberActionForm" style="width:160px" /></td></tr>
            <tr><td class="txtStyle">Last Name*</td><td class="table_textbox"><html:text  readonly="<%=read%>" property="TXTLNAME" styleId="lname2"  value="<%=cirmemdetail.getLname()%>" name="CirculationMemberActionForm" style="width:160px" />
                <br/>
                 <html:messages id="err_name" property="TXTLNAME">
				<bean:write name="err_name" />

			</html:messages>
                </td>



                </tr>
            <tr>  <td class="txtStyle">Email ID*</td><td class="table_textbox"><html:text  readonly="<%=read%>" property="TXTEMAILID"  styleId="mail2" value="<%=cirmemdetail.getEmail()%>" name="CirculationMemberActionForm" style="width:160px" />
                <br/>
                 <html:messages id="err_name" property="TXTEMAILID">
				<bean:write name="err_name" />

			</html:messages>
                </td>
                 <td> Type of Member*</td><td class="table_textbox">
                       <%if(button.equals("View")||button.equals("Delete")){%>
                        <html:select disabled="true"  property="MEMCAT" style="width:160px" styleId="emptype_id" value="<%=cirmemacct.getMemType() %>" styleId="emptype_id">
                       <html:options  collection="list1" property="id.emptypeId" labelProperty="emptypeFullName"></html:options>
                     </html:select>
                      <%-- <html:select      property="MEMCAT"   style="width:132px"   name="CirculationMemberActionForm" >
                           <html:option value="">Select Any</html:option>
                            <html:option value="Student">Student</html:option>
                          <html:option value="Teaching_Employee">Teaching Employee</html:option>
                          <html:option value="Non_Teaching_Employee">Non Teaching Employee</html:option>
                       </html:select>--%>
                   <%}else{%>
                   <html:select  property="MEMCAT" style="width:160px" value="<%=cirmemacct.getMemType() %>" styleId="emptype_id" onchange="return search();" >
                       <html:options  collection="list1" property="id.emptypeId" labelProperty="emptypeFullName"></html:options>
                     </html:select>
         <%--   <html:select   property="MEMCAT"  style="width:132px" value="<%=cirmemacct.getMemType()%>" name="CirculationMemberActionForm" >
      <html:option value="">Select Any</html:option>
      <html:option value="Student">Student</html:option>
      <html:option value="Teaching_Employee">Teaching Employee</html:option>
      <html:option value="Non_Teaching_Employee">Non Teaching Employee</html:option>
           </html:select>--%>
              <%}%>
                     <br/>


                  </td>


            </tr>
             <tr><td class="txtStyle">Local Address*</td><td class="table_textbox"> <html:text property="TXTADD1" styleId="add11" style="width:160px" value="<%=cirmemdetail.getAddress1()%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px" />
                 <br/>
                 <html:messages id="err_name" property="TXTADD1">
		<bean:write name="err_name" />

		</html:messages>

                 </td>
              <td>Designation/Student Category
                  </td><td class="table_textbox">
                       <%if(button.equals("View")||button.equals("Delete")){%>

                       <%--
                       <html:select    property="MEMSUBCAT" style="width:132px" value="<%=cirmemacct.getSubMemberType() %>" name="CirculationMemberActionForm">

      <html:option value="">Select Any</html:option>
       <html:option value="UG">UnderGraduate</html:option>
         <html:option value="PG">Post Graduate</html:option>
         <html:option value="Scholar">Scholar</html:option>
         <html:option value="Proffessor">Proffessor</html:option>
         <html:option value="Leacturer">Leacturer</html:option>
         <html:option value="Reader">Reader</html:option>
         <html:option value="Clerk">Clerk</html:option>
         <html:option value="others">Others</html:option>
                      </html:select>--%>

                        <html:select disabled="true" property="MEMSUBCAT" styleId="subemptype_id" value="<%=cirmemacct.getSubMemberType() %>" style="width:160px"  tabindex="3">
                   <%--  <html:options  collection="list2" property="subEmptypeFullName"></html:options>--%>
                     </html:select>
          <%}else{%>
         <%-- <html:select   property="MEMSUBCAT" style="width:132px" value="<%=cirmemacct.getSubMemberType()%>" name="CirculationMemberActionForm">

      <html:option value="">Select Any</html:option>
       <html:option value="UG">UnderGraduate</html:option>
         <html:option value="PG">Post Graduate</html:option>
         <html:option value="Scholar">Scholar</html:option>
         <html:option value="Proffessor">Proffessor</html:option>
         <html:option value="Leacturer">Leacturer</html:option>
         <html:option value="Reader">Reader</html:option>
         <html:option value="Clerk">Clerk</html:option>
         <html:option value="others">Others</html:option>
                      </html:select>--%>
         <html:select  property="MEMSUBCAT" styleId="subemptype_id" value="<%=cirmemacct.getSubMemberType() %>" style="width:160px"  tabindex="3">
                   <%--  <html:options  collection="list2" property="subEmptypeFullName"></html:options>--%>
                     </html:select>
         <%}%>
                        <br/>


                      </td>

             </tr>
             <tr><td class="txtStyle">City*</td><td class="table_textbox"><html:text  property="TXTCITY1" value="<%=cirmemdetail.getCity1()%>" styleId="city11" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"/>
                 <br/>


                 </td><td>Employee Designation</td><td class="table_textbox"><html:text  property="TXTDESG1" value="<%=cirmemacct.getDesg()%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"/></td></tr>
             <tr><td class="txtStyle">State*</td><td class="table_textbox"><html:text  property="TXTSTATE1" styleId="state11" value="<%=cirmemdetail.getState1()%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"/>
                 <br/>


                 </td><td>Office Name</td><td class="table_textbox"><html:text  property="TXTOFFICE" value="<%=cirmemacct.getOffice()%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"/></td></tr>
             <tr><td class="txtStyle">Country*</td><td class="table_textbox"><html:text  property="TXTCOUNTRY1" styleId="country11" value="<%=cirmemdetail.getCountry1()%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"/>
                 <br/>


                 </td><td> Faculty of
                  </td><td class="table_textbox">
            <%if(button.equals("View")||button.equals("Delete")){%>
            <html:select disabled="true"  property="TXTFACULTY" styleId="TXTFACULTY" style="width:160px" value="<%=cirmemacct.getFacultyId() %>"  onchange="return search1()" tabindex="3">
                  <html:options  collection="list2"  labelProperty="facultyName" property="id.facultyId"></html:options>
                     </html:select>
                      <%}
         else
            {%>
                 <html:select  property="TXTFACULTY" styleId="TXTFACULTY" style="width:160px"  onchange="return search1()" tabindex="3">
                  <html:options  collection="list2"  labelProperty="facultyName" property="id.facultyId"></html:options>
                     </html:select>
                     <%

            } %>

                     

                      </td></tr>
             <tr><td class="txtStyle">Mobile*</td><td class="table_textbox"><html:text  property="TXTPH1" styleId="ph11" value="<%=cirmemdetail.getPhone1()%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"/>
                 <br/>


                 </td> <td> Department  </td><td class="table_textbox">
                     <%if(button.equals("View")||button.equals("Delete")){%>
                     <html:select  property="TXTDEPT" styleId="TXTDEPT" style="width:160px" disabled="true" value="<%=cirmemacct.getDeptId() %>"  onchange="return search_dept();" tabindex="3">
                  <%--   <html:options  collection="list4" property="deptName"></html:options>--%>
                     </html:select>

   <%}
         else if(button.equals("Update"))
            {%>
            <html:select  property="TXTDEPT" styleId="TXTDEPT" style="width:160px"  value="<%=cirmemacct.getDeptId() %>"  onchange="return search_dept();" tabindex="3">
                  <%--   <html:options  collection="list4" property="deptName"></html:options>--%>
                     </html:select>
     <%}else{%>
            <html:select  property="TXTDEPT" styleId="TXTDEPT" style="width:160px"  value="<%=cirmemacct.getDeptId() %>"  onchange="return search_dept();" tabindex="3">
                  <%--   <html:options  collection="list4" property="deptName"></html:options>--%>
                     </html:select>




                <%

            } %>

                     

                 </td></tr>
             <tr><td class="txtStyle">Land Line No.</td><td class="table_textbox"><html:text  property="TXTPH2" value="<%=cirmemdetail.getPhone2()%>" readonly="<%=read%>"  name="CirculationMemberActionForm" style="width:160px" /></td>
                 <td> Course </td>

                  <td class="table_textbox">

                  <%if(button.equals("View")||button.equals("Delete")){%>
                  <html:select disabled="true"  property="TXTCOURSE" styleId="TXTCOURSE" style="width:160px" value="<%=cirmemacct.getCourseId()%>"   tabindex="3">
                  <%--   <html:options  collection="list5" property="courseName"></html:options>--%>
                     </html:select>

   <%}
          else if(button.equals("Update"))
            {%>
            <html:select  property="TXTCOURSE" styleId="TXTCOURSE" style="width:160px" value="<%=cirmemacct.getCourseId()%>"   tabindex="3">
                  <%--   <html:options  collection="list5" property="courseName"></html:options>--%>
                     </html:select>
    <%}else{%>
              <html:select   property="TXTCOURSE" styleId="TXTCOURSE" style="width:160px" value="<%=cirmemacct.getCourseId()%>"   tabindex="3">
                  <%--   <html:options  collection="list5" property="courseName"></html:options>--%>
                     </html:select>
<%} %>








</td></tr>
             <tr><td class="txtStyle">Fax</td><td class="table_textbox"><html:text  property="TXTFAX" value="<%=cirmemdetail.getFax()%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"/></td><td> Semester/Year
                  </td><td class="table_textbox"><html:text  property="TXTSEM"   value="" styleClass="textBoxWidth" value="<%=cirmemacct.getSemester()%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"  />

                  </td></tr>

            <tr><td class="txtStyle">Permanent Address</td><td class="table_textbox"><html:text property="TXTADD2" style="width:160px" value="<%=cirmemdetail.getAddress2()%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"/></td>
                <%--   <td> Request Date
            </td><td class="table_textbox"><html:text  property="TXTREQ_DATE"   value="" styleClass="textBoxWidth" value="<%=cirmemdetail.getRequestdate()%>" disabled="true"   style="width:160px" /> --%>
            </tr>
             <tr><td class="txtStyle">City</td><td class="table_textbox"><html:text  property="TXTCITY2" value="<%=cirmemdetail.getCity2()%>" style="width:160px" readonly="<%=read%>"/></td><td> Registration Date*<br>(YYYY-MM-DD)
                 </td><td class="table_textbox">
                     
                       <%if(button.equals("View")||button.equals("Delete")){%>  
                    
                       <html:text  property="TXTREG_DATE" disabled="true"  styleId="TXTREG_DATE"  value="" styleClass="textBoxWidth" value="<%=cirmemacct.getReqDate() %>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px" />
                     <%}else{%>
                     <html:text  property="TXTREG_DATE"  styleId="TXTREG_DATE"  value="" styleClass="textBoxWidth" value="<%=cirmemacct.getReqDate() %>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px" />
                     <%}%>
                      <%--   <img src="/LibMS-Struts/images/img.gif"  onclick="displayCalendar(document.forms[0].TXTREG_DATE,'yyyy/mm/dd',this)"/> --%>   <br/>

                       <br/> <div align="left" id="searchResult1" style="border:#000000; "></div>

                  </td></tr>
             <tr><td class="txtStyle">State</td><td class="table_textbox"><html:text  property="TXTSTATE2" value="<%=cirmemdetail.getState2()%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"/></td>
                 <td valign="top">Expire Date*<br>(YYYY-MM-DD)
                  </td>
                  <td class="table_textbox" valign="top">
                      
                      <%if(button.equals("View")||button.equals("Delete")){%>  
                      <html:text  property="TXTEXP_DATE" disabled="true" styleId="TXTEXP_DATE" value="<%=cirmemacct.getExpiryDate() %>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"/>

                         <%} else {%>
                         <html:text  property="TXTEXP_DATE"  styleId="TXTEXP_DATE" value="<%=cirmemacct.getExpiryDate() %>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"/>

                         <%}%>

                     <%-- <img src="/LibMS-Struts/images/img.gif"  onclick="displayCalendar(document.forms[0].TXTEXP_DATE,'yyyy/mm/dd',this)"/> --%>   <br/>
                       <br/> <div align="left" id="searchResult2" style="border:#000000;"></div>
                       </td></tr>
             <tr><td class="txtStyle">Country</td><td class="table_textbox"><html:text  property="TXTCOUNTRY2" value="<%=cirmemdetail.getCountry2()%>" readonly="<%=read%>" name="CirculationMemberActionForm" style="width:160px"/></td></tr>



     </table>
      </td></tr>
 <tr><td colspan="4" align="center" class="txt2">
         <%if(button_visibility){%>
         <html:submit value="<%=button%>" styleId="button" onclick="return del()" property="button"  ></html:submit>
         <%}%>
         &nbsp;&nbsp;

         <html:button value="Back" property="button" onclick="return quit()"></html:button>
         <br/>        </td>

          </tr>
     </table>


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

    var option=document.getElementById('button').value;
    if(option=="Delete"){
        var a=confirm("Do You Want To Delete Record ");
       // alert(a);
        if(a!=true)
            {
                document.getElementById('button').focus();
               return false;

        }
        else
            return true;
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


    var city1=document.getElementById('city11');
    var state1=document.getElementById('state11');
    var country1=document.getElementById('country11');




var str="Enter Following Values:-";


   if(email_id.value=="")
        {
            str+="\n Enter Email ID";
            alert(str);
            document.getElementById('mail2').focus();
            return false;
        }



   if(first_name.value=="")
        {
            str+="\n Enter First Name";
            alert(str);
            document.getElementById('fname2').focus();
            return false;
        }
if(last_name.value=="")
        {
            str+="\n Enter Last Name";
            alert(str);
            document.getElementById('lname2').focus();
            return false;
        }

  if(local.value=="")
        {
            str+="\n Enter Local Address";
            alert(str);
            document.getElementById('add11').focus();
            return false;
        }

 if(phone.value=="")
        {
            str+="\n Enter PhoneNo ";
            alert(str);
            document.getElementById('ph11').focus();
            return false;
        }

if(emptype_id=="Select")
        {
            str+="\n Enter Member Category ";
            alert(str);
            document.getElementById('emptype_id').focus();
            return false;
        }



  if(city1.value=="")
        {
            str+="\n Enter city ";
            alert(str);
            document.getElementById('city11').focus();
            return false;
        }
if(state1.value=="")
        {
            str+="\n Enter state ";
            alert(str);
            document.getElementById('state11').focus();
            return false;
        }

        if(country1.value=="")
        {
            str+="\n Enter country ";
            alert(str);
            document.getElementById('country11').focus();
            return false;
        }

    var TXTREG_DATE=document.getElementById('TXTREG_DATE');
    var TXTEXP_DATE=document.getElementById('TXTEXP_DATE');





var str="Enter Following Values:-";




    if(TXTREG_DATE.value=="")
       { str+="\n Enter Date of Registration";
            alert(str);
            document.getElementById('TXTREG_DATE').focus();
            return false;

       }

    if(TXTEXP_DATE.value=="")
      {  str+="\n Enter Expiry date";
           alert(str);
           document.getElementById('TXTEXP_DATE').focus();
            return false;

      }

if(IsDateGreater(TXTREG_DATE.value,TXTEXP_DATE.value)==true)
    {

       str+="\nDate of Expiry Should be greater than Date of Registration";
       alert(str);
         document.getElementById('TXTEXP_DATE').focus();
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
