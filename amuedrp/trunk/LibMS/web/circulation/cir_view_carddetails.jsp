 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
//alert("HTTP error "+req.status+": "+req.statusText);
}
}
}
}

    function quit()
    {
        location.href="<%=request.getContextPath()%>/circulation/cir_generate_card.jsp";
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
alert(keyValue);
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
System.out.println(faculty);

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

   
<script>
  


 
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
 <html:form action="/card_inf" method="post">


   <table dir="<%=rtl%>"  align="center" width="80%"   class="table">



  <tr><td dir="<%=rtl%>" width="80%" height="25px"  class="headerStyle"  align="center">


	Registered Member Details



        </td></tr>

  <tr><td  align="center">

          <br>
            <table dir="<%=rtl%>" width="80%" >



                <tr><td dir="<%=rtl%>" class="txtStyle" width="25%"><%=resource.getString("circulation.cir_newmember.memberid")%></td><td dir="<%=rtl%>" width="25%" class="table_textbox">
                        <html:text    property="TXTMEMID" readonly="true"  styleId="mem_id2"   style="width:160px" onfocus="statwords('Member Id');" onblur="loadHelp()"/></td>
                    <td dir="<%=rtl%>" width="25%" rowspan="6" width="150px" valign="bottom">

                       <%=resource.getString("circulation.cir_newmember.imageupload")%>

                    </td><td dir="<%=rtl%>" rowspan="6" width="25%" class="table_textbox" valign="bottom">

                       
                        <html:img src="./circulation/viewimage.jsp" alt="no image selected" width="120" height="120" />
                     
                    </td>
                   </tr>
                   <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.fname")%>*</td><td dir="<%=rtl%>" class="table_textbox"><html:text  readonly="<%=read%>"  property="TXTFNAME" styleId="fname2"  name="GenerateCardActionForm" style="width:160px" onfocus="statwords('Edit First Name');" onblur="loadHelp()"/><br/>
                 <html:messages id="err_name" property="TXTFNAME">
				<bean:write name="err_name" />

			</html:messages>

                </td>

                </tr>
                <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.mname")%></td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTMNAME" readonly="<%=read%>" styleId="mname2"    name="GenerateCardActionForm" style="width:160px" onfocus="statwords('Edit Middle Name');" onblur="loadHelp()"/></td></tr>
            <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.lname")%>*</td><td dir="<%=rtl%>" class="table_textbox"><html:text  readonly="<%=read%>" property="TXTLNAME" styleId="lname2"   name="GenerateCardActionForm" style="width:160px" onfocus="statwords('Edit Last Name');" onblur="loadHelp()" />
                <br/>
                 <html:messages id="err_name" property="TXTLNAME">
				<bean:write name="err_name" />

			</html:messages>
                </td>



                </tr>
            <tr>  <td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.email")%>*</td><td dir="<%=rtl%>" class="table_textbox"><html:text  readonly="<%=read%>" property="TXTEMAILID"  styleId="mail2"  onblur="return echeck(mail2.value)" name="GenerateCardActionForm" style="width:160px" onfocus="statwords('Edit email Id');" onblur="loadHelp()" />
                <br/><div align="left" class="err" id="searchResult" style="border:#000000; "></div>
                 <html:messages id="err_name" property="TXTEMAILID">
				<bean:write name="err_name" />

			</html:messages>
                </td>


            </tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.localadd")%>*</td><td dir="<%=rtl%>" class="table_textbox"> <html:text property="TXTADD1" styleId="add11" style="width:160px" readonly="<%=read%>" name="GenerateCardActionForm" style="width:160px" onfocus="statwords('Edit street/colony/moh Name');" onblur="loadHelp()"/>
                 <br/>
                 <html:messages id="err_name" property="TXTADD1">
		<bean:write name="err_name" />

		</html:messages>

                 </td>


             </tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.city")%>*</td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTCITY1"  styleId="city11" readonly="<%=read%>" name="GenerateCardActionForm" style="width:160px" onfocus="statwords('Edit City Name');" onblur="loadHelp()"/>
                 <br/>


                 </td>
                  <td dir="<%=rtl%>"> <%=resource.getString("circulation.cir_newmember.typeofmem")%>*</td><td dir="<%=rtl%>" class="table_textbox">
                     
                        <html:select disabled="true"  property="MEMCAT" style="width:160px" styleId="emptype_id" styleId="emptype_id">
                       <html:options  collection="list1" property="id.emptypeId" labelProperty="emptypeFullName"></html:options>
                     </html:select>

                  
                     <br/>


                  </td>



               </tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.state")%>*</td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTSTATE1" styleId="state11"  readonly="<%=read%>" name="GenerateCardActionForm" style="width:160px"/>
                 <br/>


                 </td>
                  <td dir="<%=rtl%>"><%=resource.getString("circulation.cir_newmember.desg")%>*
                  </td><td dir="<%=rtl%>" class="table_textbox">
                     



                        <html:select disabled="true" property="MEMSUBCAT" styleId="subemptype_id"  style="width:160px"  tabindex="3">

                     </html:select>
       
                        <br/>


                      </td>

                </tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.country")%>*</td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTCOUNTRY1" styleId="country11"  readonly="<%=read%>" name="GenerateCardActionForm" style="width:160px" onfocus="statwords('Edit Country Name');" onblur="loadHelp()"/>
                 <br/>


                 </td>
                   <td dir="<%=rtl%>"><%=resource.getString("circulation.cir_newmember.empdegn")%></td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTDESG1"  styleId="desg2" readonly="<%=read%>" name="GenerateCardActionForm" style="width:160px"onfocus="statwords('Edit Designation of the member');" onblur="loadHelp()"/></td>
                 </tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.mobile")%>*</td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTPH1"  styleId="ph11" readonly="<%=read%>" name="GenerateCardActionForm" style="width:160px" onfocus="statwords('Edit 10 digit mobile Number');" onblur="loadHelp()"/>
                 <br/>


                 </td>
                  <td dir="<%=rtl%>"><%=resource.getString("circulation.cir_newmember.officename")%></td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTOFFICE" styleId="office2"  readonly="<%=read%>" name="GenerateCardActionForm" style="width:160px" onfocus="statwords('Edit Office Name');" onblur="loadHelp()"/></td>
               </tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.landlineno")%>.</td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTPH2" styleId="ph22"  readonly="<%=read%>"  name="GenerateCardActionForm" style="width:160px" onfocus="statwords('Edit Land line Number if Any');" onblur="loadHelp()" /></td>
                <td dir="<%=rtl%>"> <%=resource.getString("circulation.cir_newmember.facof")%>
                  </td><td dir="<%=rtl%>" class="table_textbox">
        
            <html:select disabled="true"  property="TXTFACULTY" styleId="TXTFACULTY" style="width:160px"   onclick="return search1()" tabindex="3">
                  <html:options  collection="list2"  labelProperty="facultyName" property="id.facultyId"></html:options>
                     </html:select>
                     



                      </td>
                </tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.fax")%></td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTFAX" styleId="fax2" readonly="<%=read%>" name="GenerateCardActionForm" style="width:160px" onfocus="statwords('Enter FAX Number if Any');" onblur="loadHelp()"/></td>
                   <td dir="<%=rtl%>"> <%=resource.getString("circulation.cir_newmember.dept")%>  </td><td dir="<%=rtl%>" class="table_textbox">
                     
                     <html:select  property="TXTDEPT" styleId="TXTDEPT" style="width:160px" disabled="true"   onchange="return search_dept();" tabindex="3">
                     <html:option value="Select"><%=resource.getString("circulation.cir_newmember.select")%></html:option>
                     </html:select>

  



                 </td>


             </tr>

            <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.permadd")%></td><td dir="<%=rtl%>" class="table_textbox"><html:text property="TXTADD2"  style="width:160px" styleId="add22"  readonly="<%=read%>" name="GenerateCardActionForm" style="width:160px"/></td>
                 <td dir="<%=rtl%>"> <%=resource.getString("circulation.cir_newmember.course")%> </td>

                  <td dir="<%=rtl%>" class="table_textbox">

                
                  <html:select disabled="true"  property="TXTCOURSE" styleId="TXTCOURSE" style="width:160px"  tabindex="3">
                     <html:option value="Select"><%=resource.getString("circulation.cir_newmember.select")%></html:option>
                  </html:select>

 








</td>
                  </tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.city")%></td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTCITY2"styleId="city22"  style="width:160px" readonly="<%=read%>"onfocus="statwords('Enter City Name');" onblur="loadHelp()"/></td>

                  <td> <%=resource.getString("circulation.cir_newmember.sem")%>
                  </td>
                  <td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTSEM" styleId="sem2"   styleClass="textBoxWidth"  readonly="<%=read%>" name="GenerateCardActionForm" style="width:160px" onfocus="statwords('Enter Semester or Year of the course');" onblur="loadHelp()" />

                  </td>

              </tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.state")%></td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTSTATE2" styleId="state22"  readonly="<%=read%>" name="GenerateCardActionForm" style="width:160px"onfocus="statwords('Enter State Name');" onblur="loadHelp()"/></td>
                    <td dir="<%=rtl%>"> <%=resource.getString("circulation.cir_newmember.reg")%>*<br>(YYYY-MM-DD)
                 </td><td class="table_textbox">

                     

                       <html:text  property="TXTREG_DATE" disabled="true"  styleId="TXTREG_DATE"  styleClass="textBoxWidth"  readonly="<%=read%>" name="GenerateCardActionForm" style="width:160px" />
                    
                      

                  </td>
               </tr>
             <tr><td dir="<%=rtl%>" class="txtStyle"><%=resource.getString("circulation.cir_newmember.country")%></td><td dir="<%=rtl%>" class="table_textbox"><html:text  property="TXTCOUNTRY2"  styleId="country22"  readonly="<%=read%>" name="GenerateCardActionForm" style="width:160px"onfocus="statwords('Enter Country Name');" onblur="loadHelp()"/></td>
               <td dir="<%=rtl%>" valign="top"><%=resource.getString("circulation.cir_newmember.exp")%>*<br>(YYYY-MM-DD)
                  </td>
                  <td dir="<%=rtl%>" class="table_textbox" valign="top">

                     
                      <html:text  property="TXTEXP_DATE" disabled="true" styleId="TXTEXP_DATE" readonly="<%=read%>" name="GenerateCardActionForm" style="width:160px"/>

                      
                     
                       </td>
             </tr>



     </table>
      </td></tr>
 <tr><td colspan="4" align="center" class="txt2">
        
         <html:submit property="button" value="<%=button%>"  onclick="return del()"/>
     
         &nbsp;&nbsp;

         <input type="button" onclick="return quit()" value="<%=resource.getString("circulation.cir_newmember.back")%>"/>
         <br/>        </td>

          </tr>
     </table>

         <input type="hidden" id="btn" name="button" value="" />



 </html:form>


</body>


</div>


   
