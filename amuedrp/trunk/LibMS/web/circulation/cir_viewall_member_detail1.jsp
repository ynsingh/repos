
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="java.sql.ResultSet"%>
 <jsp:include page="/admin/header.jsp" flush="true" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>

  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/cupertino/jquery.ui.all.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.datepicker.min.js"></script>

<title>View All Member Details </title>
<%!
    static Integer count=0;
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
%>
<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
  .rows          { background-color: white;border: solid 1px blue; }
     .hiliterows    { background-color: pink; color: #000000; font-weight: bold;border: solid 1px blue; }
     .alternaterows { background-color: #efefef; }
     .header        { background-color: #c0003b; color: #FFFFFF;font-family:Tahoma;font-size: 12px;font-weight: bold;text-decoration: none;padding-left: 10px; }

     .datagrid      {  font-family: arial; font-size: 9pt;
	    font-weight: normal;}
     .item{ padding-left: 10px;}

</style>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/acquisition/dhtmlgoodies_calendar/dhtmlgoodies_calendar.css" media="screen"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/acquisition/dhtmlgoodies_calendar/dhtmlgoodies_calendar.js"></script>
<%
String library_id = (String)session.getAttribute("library_id");
String sublibrary_id = (String)session.getAttribute("memsublib");



     if(sublibrary_id==null)sublibrary_id=   (String)session.getAttribute("sublibrary_id");
if(sublibrary_id==null)sublibrary_id="all";
try{
    List libRs = (List)session.getAttribute("libRs");

locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
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
function search() {

    var keyValue = document.getElementById('CMBLib').options[document.getElementById('CMBLib').selectedIndex].value;

 if(keyValue=="all"){

           document.getElementById('CMBLib').focus();
               document.getElementById('SubLibary').options.length = 0;
               newOpt = document.getElementById('SubLibary').appendChild(document.createElement('option'));
                newOpt.value = "all";
                newOpt.text = "All";

                fun1();

		return false;



        }
else
    {
    keyValue = keyValue.replace(/^\s*|\s*$/g,"");
if (keyValue.length >= 1)
{

var req = newXMLHttpRequest();

req.onreadystatechange = getReadyStateHandler(req, update);

req.open("POST","<%=request.getContextPath()%>/sublibrary.do", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send("getSubLibrary_Id="+keyValue);


}
return true;
}
}

function update(cartXML)
{
var depts = cartXML.getElementsByTagName("sublibrary_ids")[0];
var em = depts.getElementsByTagName("sublibrary_id");
var em1 = depts.getElementsByTagName("sublibrary_name");

        var newOpt =document.getElementById('SubLibary').appendChild(document.createElement('option'));
        document.getElementById('SubLibary').options.length = 0;

for (var i = 0; i < em.length ; i++)
{
var ndValue = em[i].firstChild.nodeValue;
var ndValue1=em1[i].firstChild.nodeValue;
newOpt = document.getElementById('SubLibary').appendChild(document.createElement('option'));
newOpt.value = ndValue;
newOpt.text = ndValue1;
}
}
</script>
  <script language="javascript" type="text/javascript">

var availableSelectList1;
var availableSelectList2;

var str1;
function dcheck() {

availableSelectList1 = document.getElementById("searchResult1");

    if (isValidDate(registrationdate.value)==false)
    {

        availableSelectList1.innerHTML=str1;
		registrationdate.value="";

                registrationdate.focus();
		return false;
	}
        else
            {
              availableSelectList1.innerHTML="";
            }
}
function dcheck1() {

availableSelectList2 = document.getElementById("searchResult2");

    if (isValidDate(expirydate.value)==false)
    {
         availableSelectList2.innerHTML=str1;
		expirydate.value="";

                expirydate.focus();
		return false;
	}
        else
    {
    availableSelectList2.innerHTML="";
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






/**
 * DHTML email validation script. Courtesy of SmartWebby.com (http://www.smartwebby.com/dhtml/)
 */









</script>


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
   $("#registrationdate").datepicker(jQueryDatePicker1Opts);
   $("#expirydate").datepicker(jQueryDatePicker1Opts);




});

    </script>

 <script language="javascript" type="text/javascript">

var availableSelectList1;
var availableSelectList2;

var str1;
function dcheck() {

availableSelectList1 = document.getElementById("searchResult3");

    if (isValidDate(requestdateto.value)==false)
    {

        availableSelectList1.innerHTML=str1;
		requestdateto.value="";

                requestdateto.focus();
		return false;
	}
        else
            {
              availableSelectList1.innerHTML="";
            }
}
function dcheck1() {

availableSelectList2 = document.getElementById("searchResult4");

    if (isValidDate(requestdatefrom.value)==false)
    {
         availableSelectList2.innerHTML=str1;
		requestdatefrom.value="";

                requestdatefrom.focus();
		return false;
	}
        else
    {
    availableSelectList2.innerHTML="";
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






/**
 * DHTML email validation script. Courtesy of SmartWebby.com (http://www.smartwebby.com/dhtml/)
 */









</script>


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
   $("#requestdateto").datepicker(jQueryDatePicker1Opts);
   $("#requestdatefrom").datepicker(jQueryDatePicker1Opts);




});

    </script>






<script language="javascript">
function fun()
{
search();
document.getElementById("Form1").action="<%=request.getContextPath()%>/cir_viewalldetail.do";
document.getElementById("Form1").method="post";
document.getElementById("Form1").target="f1";
document.getElementById("Form1").submit();
}
function disableStatus()
{


}
<%--function print()
{

    document.getElementById("Form1").action = "<%=request.getContextPath()%>/cir_viewalldetail.do"
    document.getElementById("Form1").method="post";
    document.getElementById("Form1").target="";
    document.getElementById("Form1").submit();
}--%>
</script>
    </head>
    <body >

    <html:form action="/cir_viewalldetail"  styleId="Form1" target="f1"  acceptCharset="utf-8">

        <div
   style="  top:100px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;"
      >
   <table  align="<%=align%>" dir="<%=rtl%>" width="800px" class="datagrid" style="border:solid 1px #e0e8f5;">



  <tr  class="header"><td  width="1000px" dir="<%=rtl%>"  height="28px" align="center" colspan="2">


	<%--<%=resource.getString("opac.browse.browsesearch")%>--%>Search Member Record




        </td></tr>
    <tr dir="<%=rtl%>"><td>
    <div id='translControl'>
      <%--<input type="checkbox" id="checkboxId" onclick="">--%>
      <html:checkbox property="checkbox" styleId="reqfromopac" name="CirViewAll1ActionForm" onclick="status.disabled=this.checked"/>
      <strong> Request From Opac</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     <%-- <input type="checkbox" id="checkboxId" onclick="">--%>
    <%-- <html:checkbox property="checkbox1" styleId="tempreg" name="CirViewAll1ActionForm"/>
      <strong>Temporary Registration</strong>
--%>


    </div>
      </td></tr>
  <tr style="background-color:#e0e8f5;"><td width="800px" dir="<%=rtl%>" >
          <table>
              <tr><td dir="<%=rtl%>">Member Id</td><td><input  name="TXTTITLE" onblur="fun()" id="TXTTITLE" class="keyboardInput" type="text" dir="<%=rtl%>" onkeyup=""></td>
                 <td dir="<%=rtl%>">Faculty</td><td>
                     <html:select  property="TXTFACULTY" styleId="TXTFACULTY" onchange="fun()" style="width:160px" value=""  onclick="return search1()" tabindex="21">
                  <html:option value="Select"><%=resource.getString("circulation.cir_newmember.select")%></html:option>
                  <html:options  collection="faculty"  labelProperty="facultyName" property="id.facultyId"></html:options>
                     </html:select>
                 </td>
                 <td dir="<%=rtl%>">Department</td>
                 <td>
                     <html:select  property="TXTDEPT" styleId="TXTDEPT" onchange="fun()" style="width:160px"  onchange="return search_dept();" value="" tabindex="22">
                  <html:option value="Select"><%=resource.getString("circulation.cir_newmember.select")%></html:option>
                         <html:options  collection="dept" labelProperty="deptName" property="id.deptId"></html:options>
                     </html:select>
                 </td>
                 <td dir="<%=rtl%>">Course</td>
                 <td>
                     <html:select  property="TXTCOURSE" onchange="fun()" styleId="TXTCOURSE" style="width:160px" value=""  tabindex="23">
                  <html:option value="Select"><%=resource.getString("circulation.cir_newmember.select")%></html:option>
                        <html:options  collection="courses" labelProperty="courseName" property="id.courseId"></html:options>
                     </html:select>

                 </td>

              </tr>
          </table>
      </td>
      <td dir="<%=rtl%>"   align="<%=align%>" valign="top">
          <table >
              <tr><td dir="<%=rtl%>"> Status </td><td rowspan="2" dir="<%=rtl%>" valign="top">


<select dir="<%=rtl%>" name="status" id="status" onChange="fun()" size="1">
<option value="Registered" selected dir="<%=rtl%>">Registered</option>
<option value="Active" dir="<%=rtl%>">Active</option>
<option value="Blocked" dir="<%=rtl%>">Blocked</option>
<option value="Expired" dir="<%=rtl%>">Expired</option>
</select>
     </td>

              </tr></table></td></tr>
  <tr><td class="header" width="1000px" dir="<%=rtl%>"  align="<%=align%>" ><%=resource.getString("opac.simplesearch.restrictedby")%></td><td class="header" align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.sortby")%></td></tr>
   <tr style="background-color:#e0e8f5;" dir="<%=rtl%>"> <td width="800px" dir="<%=rtl%>"  align="<%=align%>">
           <table  width="800px" dir="<%=rtl%>"><tr><td align="<%=align%>" dir="<%=rtl%>">
          <table>
              <tr>
                  <%--<td dir="<%=rtl%>"><%=resource.getString("opac.browse.database")%></td><td>
                    <select name="CMBDB" dir="<%=rtl%>" onChange="fun1()" size="1">
<option selected value="combined" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.combnd")%></option>
    <option value="book" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.books")%></option>
     <option value="cd" dir="<%=rtl%>">CDs</option>


</select>
                  </td>--%>

                  <%--<td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.library")%></td>--%>
                  <td>
                     <%-- <html:select property="CMBLib" dir="<%=rtl%>"  tabindex="3" value="<%=library_id%>"  styleId="CMBLib" onchange="fun()">
                           <html:option value="all">All</html:option>
                            <html:options collection="libRs" property="libraryId" labelProperty="libraryName"/>
                      </html:select>--%>
                  </td>
                  <td align="left" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.sublibrary")%>

                      <%

                        if(library_id.equalsIgnoreCase(sublibrary_id)==true){
                      %>
                        <html:select property="CMBSUBLib" dir="<%=rtl%>" value="<%=sublibrary_id%>" styleId="SubLibary" onchange="fun1()">
                          <html:option value="All">All</html:option>
                          <html:options collection="sublibrary" property="id.sublibraryId" labelProperty="sublibName" />
                       </html:select>


                        <%}else{ %>
                        <html:text property="CMBSUBLib" readonly="true" dir="<%=rtl%>" value="<%=sublibrary_id%>" styleId="SubLibary"></html:text>



                          <%}%>

               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <td dir="<%=rtl%>" align="<%=align%>"><strong>RegistrationDate&nbsp;&nbsp;&nbsp;&nbsp;From</strong></td>
         <td dir="<%=rtl%>" ><html:text property="registrationdate"  styleId="registrationdate"   onblur="fun()" styleClass="textBoxWidth"/>&nbsp;&nbsp;
          <div class="err" align="<%=align%>" id="searchResult1" ></div>

         <td dir="<%=rtl%>" align="<%=align%>"><strong>To</strong></td>
         <td dir="<%=rtl%>" ><html:text property="registrationdatefrom" styleId="expirydate"   onblur="fun()" styleClass="textBoxWidth"/>
           <div class="err" align="<%=align%>" id="searchResult2" ></div>
</tr>



              


          </table>
                   </td><td align="<%=align%>" dir="<%=rtl%>">





                   </td></tr></table>
      </td>
      <td align="<%=align%>" dir="<%=rtl%>">
           <table>
                           <tr><td dir="<%=rtl%>"><%=resource.getString("opac.browse.title")%></td><td dir="<%=rtl%>"> <select name="CMBSORT" dir="<%=rtl%>" size="1" onChange="fun()" id="CMBSORT">
<option value="memid" dir="<%=rtl%>">MemberId</option>
<option  value="firstname" dir="<%=rtl%>">FirstName</option>
<option value="registerdate" dir="<%=rtl%>">RegisterDate</option>
<option value="expirydate" dir="<%=rtl%>">ExpiryDate</option>
<option value="status" dir="<%=rtl%>">Status</option>

</select>
</td>
                           </tr></table>


      </td>

  </tr>
  <tr><td>


          <html:submit   dir="<%=rtl%>" styleClass="btn"  property="button" value="Find"></html:submit>
<input type="reset" id="Button2"  dir="<%=rtl%>" class="btn" value="<%=resource.getString("opac.browse.clear")%>">
<html:submit dir="<%=rtl%>" styleClass="btn"  property="button" value="Print"></html:submit>




      </td>
  </tr>

  <tr>
      <td>
          <table>
              <tr><td dir="<%=rtl%>" align="<%=align%>"><strong>RequestDate&nbsp;&nbsp;From</strong></td>
                  <td dir="<%=rtl%>" ><html:text property="requestdate"  styleId="requestdateto"   onblur="fun()" styleClass="textBoxWidth"/>&nbsp;&nbsp;
          <div class="err" align="<%=align%>" id="searchResult3" ></div>

         <td dir="<%=rtl%>" align="<%=align%>"><strong>To</strong></td>
         <td dir="<%=rtl%>" ><html:text property="requestdate1" styleId="requestdatefrom"   onblur="fun()" styleClass="textBoxWidth"/>
           <div class="err" align="<%=align%>" id="searchResult4" ></div>
        

      </td></tr></table></td></tr>
 <tr><td  height="400px" valign="top" colspan="2"  dir="<%=rtl%>">

             <IFRAME  name="f1"  src="#" frameborder=1 height="1200px" width="1400px" scrolling="no"  id="f1"></IFRAME>


      </td></tr>


       </table>




        </div>


 </html:form>


 
</body>
</html>