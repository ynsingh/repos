<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--  Design by Iqubal Ahmad
      Modified on 2011-02-02
     This jsp page is meant for Register newMember,Ajax for Dept,Fac,course is used & image upload can be done.
     This jsp page is Second page During Process of member Registration.
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<jsp:include page="/admin/header.jsp"/>
 <%@page contentType="text/html" import="java.util.*,java.io.*,java.sql.*,org.apache.struts.upload.FormFile,com.myapp.struts.hbm.*"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>




 <%
       
        String memid=(String)request.getAttribute("mem_id");
     //  List<SubLibrary> lst = (List<SubLibrary>)session.getAttribute("list");
     //  String size = String.valueOf(lst.size());
       

%>

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


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Member Registration Page</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/newformat.css"/>
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

}

function search1() {

    var keyValue = document.getElementById('TXTFACULTY').options[document.getElementById('TXTFACULTY').selectedIndex].value;

if (keyValue=="Select")
    {


               document.getElementById('TXTFACULTY').focus();
               document.getElementById('TXTDEPT').options.length = 0;
                document.getElementById('TXTCOURSE').options.length = 0;


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
   



});

function loadfaculty(){
    search();
   search1();
}


</script>



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
<body onload="loadfaculty();">
    

    <html:form   action="/CirNewMemberAgain" method="post" onsubmit="return validation();" >
   
        <table  dir="<%=rtl%>" align="center" width="800px"   class="table">



  <tr><td dir="<%=rtl%>"  class="headerStyle"  align="center">


          <%=resource.getString("circulation.cir_newmember.newmemacc")%>



        </td></tr>

  <tr><td dir="<%=rtl%>" valign="center" align="<%=align%>">

      
            <table  dir="<%=rtl%>" class="table_text" width="880px" >



               
                   
            
            
            <tr> 
                 <td dir="<%=rtl%>" > <%=resource.getString("circulation.cir_newmember.typeofmem")%>*</td><td class="table_textbox">
                 
                   <html:select  property="MEMCAT" style="width:160px" styleId="emptype_id" onchange="return search();">
                       <html:options  collection="list1" property="id.emptypeId" labelProperty="emptypeFullName"></html:options>
                     </html:select>
                     <br/>
                 

                  </td>


            </tr>
             <tr>
              <td dir="<%=rtl%>" ><%=resource.getString("circulation.cir_newmember.desg")%>*
                  </td><td class="table_textbox">
                      <html:select  property="MEMSUBCAT" styleId="subemptype_id" style="width:160px"  tabindex="3">
                   <%--  <html:options  collection="list2" property="subEmptypeFullName"></html:options>--%>
                     </html:select>
                   

                      </td>

             </tr>
             <tr><td dir="<%=rtl%>" ><%=resource.getString("circulation.cir_newmember.empdegn")%></td><td class="table_textbox"><html:text  property="TXTDESG1" style="width:160px"/></td></tr>
             <tr><td dir="<%=rtl%>" ><%=resource.getString("circulation.cir_newmember.officename")%></td><td class="table_textbox"><html:text  property="TXTOFFICE" style="width:160px"/></td></tr>
             <tr><td dir="<%=rtl%>" > <%=resource.getString("circulation.cir_newmember.facof")%>
                 </td><td class="table_textbox">
              <html:select  property="TXTFACULTY" styleId="TXTFACULTY" style="width:160px"  onchange="return search1()" tabindex="3">
                  <html:options  collection="list2"  labelProperty="facultyName" property="id.facultyId"></html:options>
                     </html:select>
                    
                      </td></tr>
             <tr> <td dir="<%=rtl%>" > <%=resource.getString("circulation.cir_newmember.dept")%>  </td><td class="table_textbox">

                  <html:select  property="TXTDEPT" styleId="TXTDEPT" style="width:160px"  onchange="return search_dept();" tabindex="3">
                  <%--   <html:options  collection="list4" property="deptName"></html:options>--%>
                     </html:select>

                  

                 </td></tr>
             <tr> <td dir="<%=rtl%>" > <%=resource.getString("circulation.cir_newmember.course")%>
                  </td><td class="table_textbox">
                  <html:select  property="TXTCOURSE" styleId="TXTCOURSE" style="width:160px"   tabindex="3">
                  <%--   <html:options  collection="list5" property="courseName"></html:options>--%>
                     </html:select>

                  





</td></tr>
             <tr><td dir="<%=rtl%>" > <%=resource.getString("circulation.cir_newmember.sem")%>
                  </td><td class="table_textbox"><html:text  property="TXTSEM"   value="" styleClass="textBoxWidth" style="width:160px"  />

                  </td></tr>

           <%-- <tr>
                <td colspan="2" align="center">Accessable Library List<html:select  property="library" styleId="Library" size="5" multiple="true"  onchange="return search_lib()" style="width: 160px">
                  <html:options  collection="list"  labelProperty="sublibName" property="id.sublibraryId"></html:options>
                     </html:select>

                </td>

            </tr>--%>
             <tr><td dir="<%=rtl%>" > <%=resource.getString("circulation.cir_newmember.reg")%>*<br>(YYYY-MM-DD)
                 </td><td class="table_textbox"><html:text  property="TXTREG_DATE" styleId="TXTREG_DATE"  style="width:160px"  value="" styleClass="textBoxWidth"  />
                     <div align="<%=align%>" id="searchResult1" style="border:#000000; "></div>

                  </td></tr>
             <tr>
                 <td dir="<%=rtl%>" valign="top"><%=resource.getString("circulation.cir_newmember.exp")%>*<br>(YYYY-MM-DD)
                  </td>
                  <td dir="<%=rtl%>" class="table_textbox" valign="top"><html:text  property="TXTEXP_DATE" styleId="TXTEXP_DATE" style="width:160px"/>
                   <div align="<%=align%>" id="searchResult2" style="border:#000000;"></div>
                       </td></tr>
             
            <tr><td dir="<%=rtl%>" ><%=resource.getString("opac.myaccount.reservationrequest.cardid")%>
                  </td><td class="table_textbox"><html:text  property="card_id"   value="" styleClass="textBoxWidth" style="width:160px"  />

                  </td></tr>

            <tr><td dir="<%=rtl%>" ><%=resource.getString("circulation.cir_newmember.pass")%>
                  </td><td class="table_textbox"><input type="password"   name="password"  id="password"  value=""/>

                  </td> </tr>

            <tr><td> <input type="hidden" name="memid"  id="memid" value="<%=memid%>"></td></tr>
         
     </table>
      </td></tr>
  <tr><td colspan="4" align="center" class="txt2">&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit"  value="<%=resource.getString("circulation.cir_newmember.submit")%> " onclick="return Submit();"/>&nbsp;&nbsp;<input type="reset" value="<%=resource.getString("circulation.cir_newmember.reset")%>"/>&nbsp;&nbsp;<input type="button"  value="<%=resource.getString("circulation.cir_newmember.back")%>" onclick="return back();"/>
                      </td>

          </tr>
     </table>

     <input type="hidden" id="button" name="button" value=""/>
 </html:form>


</body>


</div>
  <script language="javascript" type="text/javascript">
    function Submit()
{
    var buttonvalue="Submit";
    document.getElementById("button").setAttribute("value", buttonvalue);
    return true;
}

   function back()
    {
        
        location.href="<%=request.getContextPath()%>/circulation/cir_member_reg.jsp";
    }

 </script>
 <script language="javascript" type="text/javascript">


  
    function validation()
    {

 

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
    
if(IsDateGreater(TXTREG_DATE.value,TXTEXP_DATE.value)==true)
    {
       
       str+="\n <%=resource.getString("circulation.cir_newmember.dateofexpgreater")%>";
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

</script>



 
</html>
