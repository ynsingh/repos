<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--  Design by Iqubal Ahmad
      Modified on 2011-02-02
     This jsp page is meant for viewing Details of Member After Clicking on Grid From Request from OPAC Menu .
     This jsp page is Second page in Process of Request from OPAC Menu.
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<jsp:include page="/admin/header.jsp"/> 
 <%@page contentType="text/html" import="java.util.*,java.io.*,java.sql.*,com.myapp.struts.hbm.CirRequestfromOpac"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

<%!


   ArrayList opacList;
   CirRequestfromOpac opac;
%>


 <%
    String sublibrary_id=(String) session.getAttribute("sublibrary_id");
    String login_role=(String) session.getAttribute("login_role");
 
 List cirrequestfromopac =(List) session.getAttribute("cirrequestfromopac");
     Iterator it = cirrequestfromopac.iterator();
     
       
        String memid=(String)request.getParameter("memid");
        String fname="";
        
        int i=cirrequestfromopac.size();
        for(int ii=0;ii<i;ii++)
            {
            CirRequestfromOpac doc =(CirRequestfromOpac)cirrequestfromopac.get(ii);
            System.out.println("Memid "+ ii + "="+doc.getMemId());
            if (doc.getMemId().equals(memid))
                {
                   opac = new CirRequestfromOpac();
                    opac = doc;
                   session.setAttribute("opacimage", opac);
                }
            it.next();

            }
      //   ResultSet rst = (ResultSet)session.getAttribute("faculty_resultset");
      // if(rst!=null) rst.beforeFirst();

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

    <html:form action="/CirOpacViewNewMember" method="post" enctype="multipart/form-data" onsubmit="return validation();">

   <table  align="center" width="800px" height="400px"  class="table">



  <tr><td  width="900px"   class="headerStyle"  align="center">


		New Member Registration



        </td></tr>

  <tr><td valign="center" align="left" height="400px" >

          <br>
          <table  class="table_text" width="880px" height="400px" border="0" >



                <tr><td width="150px">&nbsp;Member ID</td><td class="table_textbox"><html:text    property="TXTMEMID" value="<%=opac.getMemId()%>" readonly="true" style="width:160px" /></td>
                    <td></td>  <td rowspan="3" class="table_textbox" valign="bottom">
                        <html:img src="./viewImagefromOpac.jsp" width="128" height="120" />
                    </td>

                   </tr>
                   <tr><td >First Name*</td><td class="table_textbox"><html:text    property="TXTFNAME" value="<%=opac.getFname()%>" style="width:160px" /><br/>
                 <html:messages id="err_name" property="TXTFNAME">
				<bean:write name="err_name" />

			</html:messages>

                </td>

                </tr>
                <tr><td>&nbsp;Middle Name</td><td class="table_textbox"><html:text  property="TXTMNAME" value="<%=opac.getMname()%>" style="width:160px" /></td></tr>
                <tr><td>&nbsp;Last Name*</td><td class="table_textbox"><html:text  property="TXTLNAME" value="<%=opac.getLname()%>" style="width:160px" />
                <br/>
                 <html:messages id="err_name" property="TXTLNAME">
				<bean:write name="err_name" />

			</html:messages>
                </td>
                <td class="table_textbox" valign="bottom">



    </td>


                </tr>
                <tr>  <td>&nbsp;Email ID*</td><td class="table_textbox"><html:text  property="TXTEMAILID" value="<%=opac.getEmail()%>" style="width:160px" />
                <br/>
                 <html:messages id="err_name" property="TXTEMAILID">
				<bean:write name="err_name" />

			</html:messages>
                </td>
                 <td> Type of Member*</td><td class="table_textbox">
                  <%--   <html:select   property="MEMCAT" value="<%=opac.getMemType()%>" style="width:132px" >

      <html:option value="">Select Any</html:option>
      <html:option value="Student">Student</html:option>
      <html:option value="Teaching_Employee">Teaching Employee</html:option>
      <html:option value="Non_Teaching_Employee">Non Teaching Employee</html:option>


                      </html:select>--%>

       <html:select  property="MEMCAT" style="width:160px" value="<%=opac.getMemType()%>" styleId="emptype_id" onchange="return search();">
                       <html:options  collection="list1" property="id.emptypeId" labelProperty="emptypeFullName"></html:options>
                     </html:select>
                     <br/>
                 <html:messages id="err_name" property="MEMCAT">
				<bean:write name="err_name" />

			</html:messages>


                  </td>


            </tr>
            <tr><td>Local Address*</td><td class="table_textbox"> <html:text property="TXTADD1" value="<%=opac.getAddress1()%>" style="width:160px" />
                 <br/>
                 <html:messages id="err_name" property="TXTADD1">
				<bean:write name="err_name" />

			</html:messages>

                 </td>
              <td>Designation/Student Category*
              </td><td class="table_textbox">
                   <html:select  property="MEMSUBCAT" styleId="subemptype_id" style="width:160px"  tabindex="3">
                   <%--  <html:options  collection="list2" property="subEmptypeFullName"></html:options>--%>
                     </html:select>
                  <%--<html:select  property="MEMSUBCAT" value="<%=opac.getSubMemberType()%>"  style="width:132px">

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
                        <br/>
                 <html:messages id="err_name" property="MEMSUBCAT">
				<bean:write name="err_name" />

			</html:messages>

                      </td>

             </tr>
             <tr><td>City*</td><td class="table_textbox"><html:text  property="TXTCITY1" value="<%=opac.getCity1()%>" style="width:160px"/>
                 <br/>
                 <html:messages id="err_name" property="TXTCITY1">
				<bean:write name="err_name" />

			</html:messages>

                 </td><td>Employee Designation</td><td class="table_textbox"><html:text  property="TXTDESG1" value="<%=opac.getDesg()%>" style="width:160px"/></td></tr>
             <tr><td >State*</td><td class="table_textbox"><html:text  property="TXTSTATE1" value="<%=opac.getState1()%>" style="width:160px"/>
                 <br/>
                 <html:messages id="err_name" property="TXTSTATE1">
				<bean:write name="err_name" />

			</html:messages>

                 </td><td>Office Name</td><td class="table_textbox"><html:text  property="TXTOFFICE" value="<%=opac.getOffice()%>" style="width:160px"/></td></tr>
             <tr><td>Country*</td><td class="table_textbox"><html:text  property="TXTCOUNTRY1" value="<%=opac.getCountry1()%>" style="width:160px"/>
                 <br/>
                 <html:messages id="err_name" property="TXTCOUNTRY1">
				<bean:write name="err_name" />

			</html:messages>

                 </td><td> Faculty of
                 </td><td class="table_textbox">
                   <%--  <html:select  styleId="TXTFACULTY" property="TXTFACULTY" onchange="return search1()">
      <html:option value="Select">Select Any</html:option><%if (rst!=null)while (rst.next()){%>
    <html:option value="<%=rst.getString(1)%>"><%=rst.getString(2).toUpperCase()%></html:option><% } %>
 </html:select>--%>
                   <html:select  property="TXTFACULTY" styleId="TXTFACULTY" style="width:160px" value="<%=opac.getFacultyId() %>"  onchange="return search1()" tabindex="3">
                  <html:options  collection="list2"  labelProperty="facultyName" property="id.facultyId"></html:options>
                     </html:select>
                      </td></tr>
             <tr><td>Mobile*</td><td class="table_textbox"><html:text  property="TXTPH1" value="<%=opac.getPhone1()%>" style="width:160px"/>
                 <br/>
                 <html:messages id="err_name" property="TXTPH1">
				<bean:write name="err_name" />

			</html:messages>

                 </td> <td> Department  </td><td class="table_textbox">
                   <%--  <select  id="TXTDEPT" name="TXTDEPT" style="width:148px" onChange="return search_dept();">
                           <option value="Select">Select Any</option>




                    </select>--%>
                      <html:select  property="TXTDEPT" styleId="TXTDEPT" style="width:160px" value="<%=opac.getDeptId() %>"   onchange="return search_dept();" tabindex="3">
                  <%--   <html:options  collection="list4" property="deptName"></html:options>--%>
                     </html:select>


                 </td></tr>
             <tr><td>Land Line No.</td><td class="table_textbox"><html:text  property="TXTPH2" value="<%=opac.getPhone2()%>" style="width:160px"/></td> <td> Course
                  </td><td class="table_textbox">


                      <%--<select id="TXTCOURSE" name="TXTCOURSE" style="width:148px" >
                             <option value="Select">Select Any</option>

                      </select>--%>
                      <html:select  property="TXTCOURSE" styleId="TXTCOURSE" style="width:160px" value="<%=opac.getCourse()%>"   tabindex="3">
                  <%--   <html:options  collection="list5" property="courseName"></html:options>--%>
                     </html:select>





</td></tr>
             <tr><td>Fax</td><td class="table_textbox"><html:text  property="TXTFAX" value="<%=opac.getFax()%>" style="width:160px"/></td><td> Semester/Year
                  </td><td class="table_textbox"><html:text  property="TXTSEM"   value="<%=opac.getSemester()%>" styleClass="textBoxWidth" style="width:160px"  />

                  </td></tr>

             <tr><td>Permanent Address</td><td class="table_textbox"><html:text property="TXTADD2" value="<%=opac.getAddress2()%>" style="width:160px"/></td>
             <td> Request Date<br>(YYYY-MM-DD)
                  </td><td class="table_textbox"><html:text  property="TXTREQ_DATE"  style="width:160px"  value="<%= opac.getRequestdate()%>" styleClass="textBoxWidth"  />
             </tr>
             <tr><td >City</td><td class="table_textbox"><html:text  property="TXTCITY2" value="<%=opac.getCity2()%>" style="width:160px"/></td><td> Registration Date*<br>(YYYY-MM-DD)
                 </td><td class="table_textbox"><html:text  property="TXTREG_DATE"  styleId="TXTREG_DATE" style="width:160px"  value="<%= opac.getRegDate() %>" styleClass="textBoxWidth"  />
                    <html:messages id="err_name" property="TXTREG_DATE">
				<bean:write name="err_name" />

			</html:messages>
                     <br/> <div align="left" id="searchResult1" style="border:#000000; "></div>

                  </td></tr>
             <tr><td >State</td><td class="table_textbox"><html:text  property="TXTSTATE2" value="<%=opac.getState2()%>" style="width:160px"/></td>
                 <td valign="top">Expire Date*<br>(YYYY-MM-DD)
                  </td>
                  <td class="table_textbox" valign="top"><html:text  property="TXTEXP_DATE" styleId="TXTEXP_DATE" value="<%= opac.getExpDate()%>" style="width:160px"/>
                  <html:messages id="err_name" property="TXTEXP_DATE">
				<bean:write name="err_name" />

			</html:messages>
                   <br/> <div align="left" id="searchResult2" style="border:#000000;"></div>
                       </td></tr>
             <tr><td >Country</td><td class="table_textbox"><html:text  property="TXTCOUNTRY2" value="<%=opac.getCountry2()%>" style="width:160px"/></td></tr>



     </table>
      </td></tr>
  <tr>
     
      <td colspan="4" align="center" class="txt2">
          <%if((sublibrary_id.equalsIgnoreCase(opac.getSublibraryId()) && login_role.equalsIgnoreCase("dept-admin"))||(sublibrary_id.equalsIgnoreCase(opac.getSublibraryId()) && login_role.equalsIgnoreCase("insti-admin"))||(sublibrary_id.equalsIgnoreCase(opac.getSublibraryId()) && login_role.equalsIgnoreCase("admin"))){%>
          <html:submit property="button" value="Approved">Submit</html:submit>
          <%}%>
          &nbsp;&nbsp;<html:button property="button" onclick="return quit();">Back</html:button>
                      </td>

          </tr>
     </table>


 </html:form>


</body>


</div>
            <script>
    function quit()
    {
        location.href="<%=request.getContextPath()%>/circulation/cir_requestfrom_opac.jsp";

    }
    function showPic()
    {
        alert("Src="+window.document.getElementById("uploadedFile").value);
        window.document.getElementById("photo").src = window.document.getElementById("uploadedFile").value;
        alert("Src="+window.document.getElementById("photo").src);
        window.reload;
    }
</script>
 <script language="javascript" type="text/javascript">



    function validation()
    {



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

</script>


   

</html>
