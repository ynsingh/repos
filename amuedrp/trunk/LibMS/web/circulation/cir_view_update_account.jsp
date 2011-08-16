<%--  Design by Iqubal Ahmad
      Modified on 2011-02-02
      This jsp page is used for  Create Account By Accepting password for OPAC NewMember Entry For Particular Member
      This jsp page is Fifth page  for one Complete Process  of member Registration.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="com.myapp.struts.hbm.*,java.util.*"%>
<jsp:include page="/admin/header.jsp" flush="true" />
 <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
CirMemberAccount cirmemberaccount=(CirMemberAccount)session.getAttribute("cirmemberaccount");
CirMemberDetail cirmemberdetail=(CirMemberDetail)session.getAttribute("cirmemberdetail");
String mem_id="";
String mem_name="";
String last_name = "";
String mail_id = "";
String button = "";
    if(cirmemberaccount!=null)
mem_id=cirmemberaccount.getId().getMemid();

if(cirmemberdetail!=null){
mem_name=cirmemberdetail.getFname();
last_name=cirmemberdetail.getLname();
mail_id=cirmemberdetail.getEmail();}
button=(String)request.getAttribute("button");
if(button==null) button=(String)request.getParameter("button");
boolean read=true;
boolean button_visibility=true;

%>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
    String button1;
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
<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
        <title>LibMS</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/cupertino/jquery.ui.all.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.datepicker.min.js"></script>

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
var availableSelectList1;
var availableSelectList2;
var availableSelectList3;
var str1;
function dcheck() {

availableSelectList1 = document.getElementById("searchResult1");
 availableSelectList1.innerHTML="";
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
 availableSelectList1.innerHTML="";
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

function checkLogin()
{

    KeyValue=document.getElementById('mem_id').value;
        KeyValue1=document.getElementById('password').value;

        if(KeyValue==KeyValue1)
            {
               availableSelectList = document.getElementById("searchResult");

               availableSelectList.innerHTML="<%=resource.getString("circulation.cir_newmember.loginandpascannotsame")%>";
                document.getElementById('password').value="";

            }
else{
 availableSelectList = document.getElementById("searchResult");

               availableSelectList.innerHTML="";

}

}

function checkDupli()
    {







      var x1=document.getElementById('password');
        var x2=document.getElementById('password1');
        if(x1.value!=x2.value)
            {
                alert("password mismatch");
                document.getElementById('password').value="";
                document.getElementById('password1').value="";
                document.getElementById('password').focus();
                return false;
            }
            else
                return true;




                return true;


    }
    function quit()
    {
       window.location="<%=request.getContextPath()%>/circulation/manage_member_account.jsp";
       return false;

    }

     function confirm1()
{

document.getElementById('btn').value="<%=button%>" ;

var answer = confirm ("<%=resource.getString("circulation.cir_newmember.douwanttodelrec")%>?")
if (answer!=true)
    {
         
        document.getElementById('button1').focus();
        return false;
    }
    return true;
}



function check1()
{
 document.getElementById('btn').value="<%=button%>" ;
var x=document.getElementById('card_id');
        if(x.value=="")
            {
                alert("<%=resource.getString("circulation.cir_createaccount.cardidcanotbeblank")%>");
                 document.getElementById('card_id').focus();
                return false;
            }

 var emptype_id = document.getElementById('emptype_id').options[document.getElementById('emptype_id').selectedIndex].value;

   if(emptype_id=="Select")
        {

            alert("<%=resource.getString("circulation.cir_newmember.entermemcategory")%>");
            document.getElementById('emptype_id').focus();
            return false;
        }

var subemptype_id = document.getElementById('subemptype_id').options[document.getElementById('subemptype_id').selectedIndex].value;

   if(subemptype_id=="Select")
        {

            alert("<%=resource.getString("circulation.cir_newmember.entersubmemcategory")%>");
            document.getElementById('subemptype_id').focus();
            return false;
        }



    var reg=document.getElementById('TXTREG_DATE');
    var exp=document.getElementById('TXTEXP_DATE');







var str="<%=resource.getString("circulation.cir_newmember.enterfollowingvalues")%>:-";



        if(reg.value==""){
             alert("<%=resource.getString("circulation.cir_newmember.enterdateofreg")%>");
            document.getElementById('TXTREG_DATE').focus();
            return false;



        }
  if(exp.value==""){
             alert("<%=resource.getString("circulation.cir_newmember.enterdateofexp")%>");
            document.getElementById('TXTEXP_DATE').focus();
            return false;



        }


if(IsDateGreater(reg.value,exp.value)==true)
    {
       str+="\n<%=resource.getString("circulation.cir_newmember.dateofexpgreater")%>";
       alert(str);
         document.getElementById('TXTEXP_DATE').focus();
         return false;
    }





   
 if(reg.value!="")
      {

          if(dcheck()==false)
           return false;

     }
     if(exp.value!="")
      {

          if(dcheck1()==false)
           return false;

     }

if(str=="<%=resource.getString("circulation.cir_newmember.enterfollowingvalues")%>:-")
   {
       return true;

   }
else
    {

        alert(str);
        document.getElementById('card_id').focus();
        return false;
    }







  }
  function loaddata(){
      search();
      search1();
search_dept();
  }
    </script>
       <style type="text/css">
.ui-datepicker
{
   font-family: Arial;
   font-size: 13px;
}
</style>
<script>


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




});
</script>

    </head>

    <body onload="loaddata()" >


  <html:form  action="/cir_account_updateview" method="post" >
      <table dir="<%=rtl%>" width="400px"  valign="top" align="center" class="table">
          <tr><td   width="400px" valign="top" style="" align="center" class="headerStyle"><%=resource.getString("circulation.cir_createaccount1.mangmemacc")%></td></tr>
          <tr><td align="center"><br/><br/>
                
                 

                       <table dir="<%=rtl%>" width="400px" align="center">
                        <tr><td dir="<%=rtl%>" class="txtstyle" width="250px" align="<%=align%>" colspan="2" height="5px"><%=resource.getString("circulation.cir_createaccount1.memberid/loginid")%></td><td dir="<%=rtl%>" width="250px"><input type="text" id="mem_id"  name="mem_id" style="width: 160px" readonly  value="<%=mem_id%>"></td></tr>
                        <tr><td dir="<%=rtl%>" colspan="2" height="5px"></td></tr>
                        <tr><td dir="<%=rtl%>" class="txtstyle" colspan="2" align="<%=align%>" height="5px"><%=resource.getString("circulation.cir_createaccount1.memname")%></td><td dir="<%=rtl%>" ><input type="text"  name="mem_name" id="mem_name" readonly    value="<%=mem_name%>&nbsp;<%=last_name%>" style="width: 160px"></td></tr>
                        <tr><td dir="<%=rtl%>" colspan="2" height="5px"></td></tr>
                        <tr><td dir="<%=rtl%>" class="txtstyle" colspan="2" height="5px" align="<%=align%>" ><%=resource.getString("circulation.cir_newmember.email")%></td><td dir="<%=rtl%>" ><input type="text" readonly name="mail_id" id="mail_id"   name="Editbox2" value="<%=mail_id%>" style="width: 160px"/> </td></tr>
                        <tr><td dir="<%=rtl%>" colspan="2" height="5px"></td></tr>
                        <tr><td dir="<%=rtl%>" class="txtstyle" colspan="2" height="5px" align="<%=align%>"><%=resource.getString("circulation.cir_newmember.pass")%><a class="star">*</a></td><td dir="<%=rtl%>" ><html:password   property="password"   styleId="password" readonly="true" onblur="return checkLogin();" style="width: 160px" value="<%=cirmemberaccount.getPassword()%>"/>
                            <div align="left" id="searchResult" class="err" style="border:#000000; "></div>
                            </td></tr>
                        <%--<tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="txtstyle" colspan="2" height="5px" align="left">Confirm <a class="star">*</a></td><td><html:password   property="password"  styleId="password1" onblur="return checkDupli();"  readonly="<%=read%>" style="width: 160px" value="" /></td></tr>--%>
                        <tr><td dir="<%=rtl%>" colspan="2" height="5px"></td></tr>
                        <tr><td dir="<%=rtl%>" class="txtstyle" colspan="2" height="5px"><%=resource.getString("circulation.cir_newmember.cardid")%><a class="star">*</a></td><td dir="<%=rtl%>" ><html:text  property="card_id" styleId="card_id" readonly="<%=read%>"  value="<%=cirmemberaccount.getCardId()%>" style="width: 160px"/></td></tr>
                        <tr><td dir="<%=rtl%>" class="txtstyle" valign="top" colspan="2"  align="<%=align%>"> <%=resource.getString("circulation.cir_newmember.typeofmem")%><a class="star">*</a></td><td class="table_textbox">
                                <%if(session.getAttribute("list1")!=null)
                                {%>
                                <%if(button.equals("Update"))
                                {%>
                                <html:select  property="MEMCAT" style="width:160px"  styleId="emptype_id"  value="<%=cirmemberaccount.getMemType()%>" onchange="return search();">
                                    <html:option value="Select">Select</html:option>
                                    <html:options  collection="list1" labelProperty="emptypeFullName" property="id.emptypeId"  ></html:options></html:select>
                                <%}else{%>
                                <html:select  property="MEMCAT" style="width:160px"  styleId="emptype_id" disabled="true" value="<%=cirmemberaccount.getMemType()%>" onchange="return search();">
                                <html:option value="Select">Select</html:option>
                                    <html:options  collection="list1" labelProperty="emptypeFullName" property="id.emptypeId"  ></html:options> </html:select>
                                    <%}
                                }else{%>
                               <html:select  property="MEMCAT" style="width:160px"  styleId="emptype_id"  value="<%=cirmemberaccount.getMemType()%>" onchange="return search();">
                                 <html:option value="Select">Select</html:option>
                                   <html:option value="<%=cirmemberaccount.getMemType()%>"><%=cirmemberaccount.getMemType()%></html:option></html:select>
                                 <%}%>

                    
                     <br/>
                 <html:messages id="err_name" property="MEMCAT">
				<bean:write name="err_name" />

			</html:messages>


                  </td></tr>
                        <tr><td dir="<%=rtl%>" class="txtstyle" colspan="2" height="5px"><%=resource.getString("circulation.cir_newmember.desg")%><a class="star">*</a>
                  </td><td dir="<%=rtl%>" class="table_textbox">
                      <%if(session.getAttribute("list2")!=null){%>
                      <%if(button.equals("Update")){%>
                      <html:select  property="MEMSUBCAT" styleId="subemptype_id"  style="width:160px" value="<%=cirmemberaccount.getSubMemberType()%>" tabindex="3">
                       <html:option value="Select">Select</html:option>
                          <html:options  collection="list2" labelProperty="subEmptypeFullName" property="id.subEmptypeId"></html:options></html:select>
                          <%}else{%>
                         <html:select  property="MEMSUBCAT" styleId="subemptype_id" disabled="true"  style="width:160px" value="<%=cirmemberaccount.getSubMemberType()%>" tabindex="3">
                         <html:option value="Select">Select</html:option>
                             <html:options  collection="list2" labelProperty="subEmptypeFullName" property="id.subEmptypeId"></html:options></html:select>
                      <%}}else{%>
                      <html:select  property="MEMSUBCAT" styleId="subemptype_id"  style="width:160px" value="<%=cirmemberaccount.getSubMemberType()%>" tabindex="3">
                    <html:option value="Select">Select</html:option>
                          <html:option value="<%=cirmemberaccount.getSubMemberType()%>"><%=cirmemberaccount.getSubMemberType()%></html:option></html:select>
                  <%}%>
                     
                        <br/>
                 <html:messages id="err_name" property="MEMSUBCAT">
				<bean:write name="err_name" />

			</html:messages>

                      </td></tr>
                        <tr><td dir="<%=rtl%>" colspan="2" class="txtstyle" height="5px"><%=resource.getString("circulation.cir_newmember.empdegn")%></td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTDESG1" style="width:160px" value="<%=cirmemberaccount.getDesg()%>" styleId="desg2" readonly="<%=read%>"/></td></tr>
                        <tr><td dir="<%=rtl%>" colspan="2" height="5px" class="txtstyle"><%=resource.getString("circulation.cir_newmember.officename")%></td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTOFFICE" styleId="office2" value="<%=cirmemberaccount.getOffice()%>" style="width:160px" readonly="<%=read%>"/></td></tr>
                        <tr><td dir="<%=rtl%>" colspan="2" height="5px" class="txtstyle"><%=resource.getString("circulation.cir_newmember.facof")%>
                 </td><td dir="<%=rtl%>" class="table_textbox">

                  <%if(session.getAttribute("list3")!=null){%>
                  <%if(button.equals("Update")){%>
                      <html:select  property="TXTFACULTY" styleId="TXTFACULTY"  style="width:160px" value="<%=cirmemberaccount.getFacultyId()%>"  onchange="return search1()" tabindex="3">
                  <html:option value="Select">Select</html:option>
                         <html:options  collection="list3"  labelProperty="facultyName" property="id.facultyId"></html:options></html:select><%}else{%>
                  <html:select  property="TXTFACULTY" styleId="TXTFACULTY" disabled="true" style="width:160px" value="<%=cirmemberaccount.getFacultyId()%>"  onchange="return search1()" tabindex="3">
                  <html:option value="Select">Select</html:option>
                      <html:options  collection="list3"  labelProperty="facultyName" property="id.facultyId"></html:options></html:select>
                  <%}}else{%>
                  <html:select  property="TXTFACULTY" styleId="TXTFACULTY" style="width:160px" value="<%=cirmemberaccount.getFacultyId()%>" disabled="<%=read%>" onchange="return search1()" tabindex="3">
                 <html:option value="Select">Select</html:option>
                      <html:option value="<%=cirmemberaccount.getFacultyId()%>"><%=cirmemberaccount.getFacultyId()%></html:option></html:select>
                  <%}%>
                     

                      </td></tr>
                        <tr><td dir="<%=rtl%>" colspan="2" height="5px" class="txtstyle"><%=resource.getString("circulation.cir_newmember.dept")%>  </td><td dir="<%=rtl%>" class="table_textbox">

                  <html:select  property="TXTDEPT" styleId="TXTDEPT" style="width:160px" disabled="<%=read%>"  onchange="return search_dept();" value="<%=cirmemberaccount.getDeptId()%>" tabindex="3">
                  <%--   <html:options  collection="list4" property="deptName"></html:options>--%>
                  <html:option value="Select">Select</html:option>


                     </html:select>



                 </td></tr>
                        <tr><td dir="<%=rtl%>" colspan="2" height="5px" class="txtstyle"><%=resource.getString("circulation.cir_newmember.course")%>  </td><td dir="<%=rtl%>" class="table_textbox">

                  <html:select  property="TXTCOURSE" styleId="TXTCOURSE" style="width:160px" disabled="<%=read%>"  value="<%=cirmemberaccount.getCourseId() %>" tabindex="3">
                  <%--   <html:options  collection="list4" property="deptName"></html:options>--%>
                  <html:option value="Select">Select</html:option>


                     </html:select>



                 </td></tr>

                        <tr>
                            <td dir="<%=rtl%>" colspan="2" height="5px" class="txtstyle"><%=resource.getString("circulation.cir_newmember.sem")%>
                            </td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTSEM" styleId="sem2" readonly="<%=read%>" value="<%=cirmemberaccount.getSemester()%>" styleClass="textBoxWidth" style="width:160px"  />

                  </td>
                        </tr>
                        <tr><td dir="<%=rtl%>" colspan="2" align="<%=align%>" class="txtstyle"><%=resource.getString("circulation.cir_newmember.access")%></td><td>
                                <%if(session.getAttribute("list")!=null){%>
                                <%if(button.equals("Update")){%>
                                <html:select  property="library" styleId="Library"  onchange="return search_lib()" style="width: 160px" value="<%=cirmemberaccount.getId().getLibraryId()%>">
                                    <html:option value="Select">Select</html:option>
                                    <html:options  collection="list"  labelProperty="sublibName" property="id.sublibraryId"></html:options></html:select><%}else{%>

                                    <html:select  property="library" styleId="Library" disabled="true" onchange="return search_lib()" style="width: 160px" value="<%=cirmemberaccount.getId().getLibraryId()%>">
                                    <html:option value="Select">Select</html:option>
                                        <html:options  collection="list"  labelProperty="sublibName" property="id.sublibraryId"></html:options></html:select>
                                    <%}}else{%>
                                    <html:select  property="library" styleId="Library"  onchange="return search_lib()" style="width: 160px" value="<%=cirmemberaccount.getId().getLibraryId()%>">
                                   <html:option value="Select">Select</html:option>
                                        <html:option value="<%=cirmemberaccount.getId().getLibraryId()%>"><%=cirmemberaccount.getId().getLibraryId()%></html:option></html:select>
                                    <%}%>
                     

                </td></tr>


                        <tr><td dir="<%=rtl%>" colspan="2" height="5px" class="txtstyle"><%=resource.getString("circulation.cir_newmember.reg")%><a class="star">*</a><br>(YYYY-MM-DD)
                            </td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTREG_DATE" readonly="<%=read%>" value="<%=cirmemberaccount.getReqDate()%>" styleId="TXTREG_DATE"  style="width:160px"  styleClass="textBoxWidth"  />
                    <html:messages id="err_name" property="TXTREG_DATE">
				<bean:write name="err_name" />

			</html:messages>
                                <br/> <div align="<%=align%>" id="searchResult1" class="err" style="border:#000000; "></div>

                  </td></tr>
             <tr>
                 <td dir="<%=rtl%>" valign="top" colspan="2" height="5px" class="txtstyle"><%=resource.getString("circulation.cir_newmember.exp")%><a class="star">*</a><br>(YYYY-MM-DD)
                  </td>
                  <td dir="<%=rtl%>" class="table_textbox" valign="top"><html:text  property="TXTEXP_DATE" readonly="<%=read%>"  value="<%=cirmemberaccount.getExpiryDate()%>" styleId="TXTEXP_DATE" style="width:160px"/>
                  <html:messages id="err_name" property="TXTEXP_DATE">
				<bean:write name="err_name" />

			</html:messages>

                      <br/> <div align="<%=align%>" id="searchResult2" class="err" style="border:#000000;"></div>
                       </td></tr>
             <tr>
                 <td></td>
                 <td></td></tr>
                        <tr><td dir="<%=rtl%>" colspan="4" align="center">
                                <br>
                                <br>
                        
<br/><br/>
                            </td></tr>
<input type="hidden" value="<%=mem_id%>" name="mem_id"/>
<input type="hidden" value="<%=mem_name%>" name="mem_name"/>
<input type="hidden" value="<%=last_name%>" name="last_name"/>
                    </table>

              </td></tr>
                        <tr>
    <td dir="<%=rtl%>" colspan="4" align="center">
    <%if(button_visibility){
    if(button.equals("Delete")){%>
    <input type="submit"    value="<%=button1%>" onclick="return confirm1();"  class="btn" style="left:80px"  />
    <%}
    else{%>
    <input type="submit"   value="<%=button1%>" class="btn" onclick="return check1();"   style="left:80px"  />
     <%}%><%}%>
    <input type="button" onclick="return quit();" class="btn" style="left:150px" value="<%=resource.getString("circulation.cir_newmember.back")%>"/>
    <br/><br/>
    </td>
</tr>
<input type="hidden" value="<%=mem_id%>" name="mem_id"/>
<input type="hidden" value="<%=mem_name%>" name="mem_name"/>
<input type="hidden" value="<%=last_name%>" name="last_name"/>
                    </table>


 <input type="hidden" id="btn" name="button" value="" />















                        </html:form>
        </div>



    </body>
    




</html>
