<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

 <%@page contentType="text/html" import="java.util.List,java.sql.*,java.io.*,com.myapp.struts.hbm.SubLibrary,com.myapp.struts.AdminDAO.*,org.apache.struts.upload.FormFile"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<link type="text/css" rel="stylesheet" href="/LibMS-Struts/css/page.css"/>




 <%
    
        List libRs = (List)session.getAttribute("libRs");
        List sublib = (List)session.getAttribute("sublib");

        List list3=(List)session.getAttribute("list3");
        List list4=(List)session.getAttribute("list4");
        List list5=(List)session.getAttribute("list5");


      
      
     
        String lib_id=(String)session.getAttribute("library_id");
       
        String msg=(String)request.getAttribute("msg");


    
  String path=request.getContextPath()+"/OPAC/upload.jsp";
   
//String memid=(String)session.getAttribute("memid");
//List<SubLibrary> lst = (List<SubLibrary>)session.getAttribute("list");
//String size = String.valueOf(lst.size());
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
String course=(String)request.getAttribute("course");
String dept=(String)request.getAttribute("dept");
String mail_id=(String)request.getAttribute("mail_id");
String faculty=(String)request.getAttribute("faculty");
String desg=(String)request.getAttribute("desg");
//String exp_date=(String)request.getAttribute("exp_date");
String fax=(String)request.getAttribute("fax");
String office=(String)request.getAttribute("office");
//String reg_date=(String)request.getAttribute("reg_date");
String sem=(String)request.getAttribute("sem");
String mem_id1=(String)request.getAttribute("mem_id");
String file=(String) request.getAttribute("filename");

String sublib_id = (String)session.getAttribute("memsublib");
        if(sublib_id==null)sublib_id= (String)session.getAttribute("sublibrary_id");
System.out.println("Sub library ID="+ sublib_id);
String image_path=request.getContextPath()+"/images/no-image.jpg";
%>




<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Member Registration Page</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/newformat.css"/>
<script language="javascript" type="text/javascript">
 function showPic()
    {
        alert("Src="+window.document.getElementById("uploadedFile").value);
        window.document.getElementById("photo").src = window.document.getElementById("uploadedFile").value;
        alert("Src="+window.document.getElementById("photo").src);
        window.reload;
    }

   
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
function search_sub() {

    var keyValue = document.getElementById('CMBLib').options[document.getElementById('CMBLib').selectedIndex].value;
    document.getElementById('SubLibrary').options.length = 0;
    document.getElementById('TXTFACULTY').options.length = 0;
    document.getElementById('TXTDEPT').options.length = 0;
    document.getElementById('TXTCOURSE').options.length = 0;
    


if (keyValue=="Select")
    {


               document.getElementById('CMBLib').focus();
               document.getElementById('SubLibrary').options.length = 0;
newOpt = document.getElementById('SubLibrary').appendChild(document.createElement('option'));
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

req.onreadystatechange = getReadyStateHandler(req, update_sub);

req.open("POST","<%=request.getContextPath()%>/sublibrary.do", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send("getSubLibrary_Id="+keyValue);


}
return true;
}
}
function search_faculty() {

    var keyValue = document.getElementById('CMBLib').options[document.getElementById('CMBLib').selectedIndex].value;


if (keyValue=="Select")
    {


               document.getElementById('CMBLib').focus();
               document.getElementById('TXTFACULTY').options.length = 0;



		return false;
	}
else
    {
    keyValue = keyValue.replace(/^\s*|\s*$/g,"");
if (keyValue.length >= 1)
{

var req = newXMLHttpRequest();

req.onreadystatechange = getReadyStateHandler(req, update_fac);

req.open("POST","<%=request.getContextPath()%>/faculty.do", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send("getLibrary_Id="+keyValue);


}
return true;
}
}
function update_fac(cartXML)
{


var depts = cartXML.getElementsByTagName("faculty_ids")[0];
var em = depts.getElementsByTagName("faculty_id");
var em1 = depts.getElementsByTagName("faculty_name");

        var newOpt =document.getElementById('TXTFACULTY').appendChild(document.createElement('option'));
        document.getElementById('TXTFACULTY').options.length = 0;

for (var i = 0; i < em.length ; i++)
{
var ndValue = em[i].firstChild.nodeValue;
var ndValue1=em1[i].firstChild.nodeValue;
newOpt = document.getElementById('TXTFACULTY').appendChild(document.createElement('option'));
newOpt.value = ndValue;
newOpt.text = ndValue1;


}
search1();
}

function search_member() {

    var keyValue = document.getElementById('CMBLib').options[document.getElementById('CMBLib').selectedIndex].value;


if (keyValue=="Select")
    {


               document.getElementById('CMBLib').focus();
               document.getElementById('emptype_id').options.length = 0;



		return false;
	}
else
    {
    keyValue = keyValue.replace(/^\s*|\s*$/g,"");
if (keyValue.length >= 1)
{

var req = newXMLHttpRequest();

req.onreadystatechange = getReadyStateHandler(req, update_member);

req.open("POST","<%=request.getContextPath()%>/member.do", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send("getLibrary_Id="+keyValue);


}
return true;
}
}
function update_member(cartXML)
{
var depts = cartXML.getElementsByTagName("emp_ids")[0];
var em = depts.getElementsByTagName("emp_id");
var em1 = depts.getElementsByTagName("emp_name");

        var newOpt =document.getElementById('emptype_id').appendChild(document.createElement('option'));
        document.getElementById('emptype_id').options.length = 0;

for (var i = 0; i < em.length ; i++)
{
var ndValue = em[i].firstChild.nodeValue;
var ndValue1=em1[i].firstChild.nodeValue;
newOpt = document.getElementById('emptype_id').appendChild(document.createElement('option'));
newOpt.value = ndValue;
newOpt.text = ndValue1;


}
search();
}

function update_sub(cartXML)
{
var depts = cartXML.getElementsByTagName("sublibrary_ids")[0];
var em = depts.getElementsByTagName("sublibrary_id");
var em1 = depts.getElementsByTagName("sublibrary_name");

        var newOpt =document.getElementById('SubLibrary').appendChild(document.createElement('option'));
        document.getElementById('SubLibrary').options.length = 0;

for (var i = 0; i < em.length ; i++)
{
var ndValue = em[i].firstChild.nodeValue;
var ndValue1=em1[i].firstChild.nodeValue;
newOpt = document.getElementById('SubLibrary').appendChild(document.createElement('option'));
newOpt.value = ndValue;
newOpt.text = ndValue1;
if(ndValue=="<%=sublib_id%>")
{
    newOpt.selected = true;
}

}
search_faculty();
search_member();


}

function search() {

    var keyValue = document.getElementById('emptype_id').options[document.getElementById('emptype_id').selectedIndex].value;
 var keyValue1 = document.getElementById('CMBLib').options[document.getElementById('CMBLib').selectedIndex].value;

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
req.send("getEmpType_Id="+keyValue+"&getLibraryId="+keyValue1);


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
 var keyValue1 = document.getElementById('CMBLib').options[document.getElementById('CMBLib').selectedIndex].value;


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
req.send("getFaculty_Id="+keyValue+"&getLibrary_Id="+keyValue1);


}
return true;
}
}

function search_dept() {

    var keyValue = document.getElementById('TXTDEPT').options[document.getElementById('TXTDEPT').selectedIndex].value;
var keyValue1 = document.getElementById('TXTFACULTY').options[document.getElementById('TXTFACULTY').selectedIndex].value;
var keyValue2 = document.getElementById('CMBLib').options[document.getElementById('CMBLib').selectedIndex].value;


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
req.send("getDept_Id="+keyValue+"&getFacultyID="+keyValue1+"&getLibraryId="+keyValue2);




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


function submit()
    {
       
        document.getElementsById("filename").value=document.getElementById("img").value;
       // alert(document.getElementsById("filename").value);
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
 // var TXTREG_DATE1=document.getElementById("TXTREG_DATE1");
  //var TXTREG_DATE=document.getElementById("TXTREG_DATE");
  //TXTREG_DATE1.value=TXTREG_DATE.value;
  // var TXTEXP_DATE1=document.getElementById("TXTEXP_DATE1");
 // var TXTEXP_DATE=document.getElementById("TXTEXP_DATE");
  //TXTEXP_DATE1.value=TXTEXP_DATE.value;
   var sem1=document.getElementById("sem1");
  var sem2=document.getElementById("sem2");
  sem1.value=sem2.value;
   var mem_id1=document.getElementById("mem_id1");
   var mem_id2=document.getElementById("mem_id2");
   mem_id1.value=mem_id2.value;
}


function check1()
{
   
   var keyValue = document.getElementById('CMBLib').options[document.getElementById('CMBLib').selectedIndex].value;
   var keyValue1 = document.getElementById('SubLibrary').options[document.getElementById('SubLibrary').selectedIndex].value;
   
   
   if(keyValue=="Select")
    {
        alert("Select Library");

        document.getElementById('CMBLib').focus();

        return false;
    }
   if(keyValue1=="Select")
    {
        alert("Enter SubLibrary");

        document.getElementById('SubLibrary').focus();

        return false;
    }
    if(document.getElementById('fname2').value=="")
    {
        alert("Enter firstName");

        document.getElementById('fname2').focus();

        return false;
    }
     if(document.getElementById('lname2').value=="")
    {
        alert("Enter LastName");


        document.getElementById('lname2').focus();

        return false;
    }
     if(document.getElementById('mail2').value=="")
    {
        alert("Enter MailID");

        document.getElementById('mail2').focus();

        return false;
    }
     if(document.getElementById('add11').value=="")
    {
        alert("Enter Address");

        document.getElementById('add11').focus();

        return false;
    }
     if(document.getElementById('city11').value=="")
    {
        alert("Enter City");

        document.getElementById('city11').focus();

        return false;
    }
     if(document.getElementById('state11').value=="")
    {
        alert("Enter State");

        document.getElementById('state11').focus();

        return false;
    }
     if(document.getElementById('country11').value=="")
    {
        alert("Enter country");

        document.getElementById('country11').focus();

        return false;
    }
     if(document.getElementById('ph11').value=="")
    {
        alert("Enter PhoneNo.");

        document.getElementById('ph11').focus();

        return false;
    }
     if(document.getElementById('emptype_id').value=="")
    {
        alert("Enter MemberType");

        document.getElementById('emptype_id').focus();

        return false;
    }
     if(document.getElementById('subemptype_id').value=="")
    {
        alert("Enter SubMemberType");

        document.getElementById('subemptype_id').focus();

        return false;
    }
return true;
  }

</script>

</head>




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
<body onload="search_sub();" style="margin: 0px 0px 0px 0px;">
    <%
    if(msg!=null){%><script>
    alert("<%=msg%>");</script>
    <%}%>


     <div style="position: absolute; top: 42%; left: 38%">
    <html:form action="/imageupload1" method="post" styleId="form1" enctype="multipart/form-data">
        Image Upload <html:file  property="img" name="OpacNewMemberActionForm" styleId="img" onchange="submit()"  onclick="copy()" />
          <input type="hidden" name="filename" id="filename" />
           <html:hidden property="TXTLNAME" name="OpacNewMemberActionForm" styleId="lname1"/>
          <html:hidden property="TXTFNAME" name="OpacNewMemberActionForm" styleId="fname1"/>
          <html:hidden property="TXTMNAME" name="OpacNewMemberActionForm" styleId="mname1"/>
          <html:hidden property="TXTADD1" name="OpacNewMemberActionForm" styleId="add1"/>
          <html:hidden property="TXTADD2" name="OpacNewMemberActionForm" styleId="add2"/>
          <html:hidden property="TXTCITY1" name="OpacNewMemberActionForm" styleId="city1"/>
          <html:hidden property="TXTCITY2" name="OpacNewMemberActionForm" styleId="city2"/>
          <html:hidden property="TXTSTATE1" name="OpacNewMemberActionForm" styleId="state1"/>
          <html:hidden property="TXTSTATE2" name="OpacNewMemberActionForm" styleId="state2"/>
          <html:hidden property="TXTCOUNTRY1" name="OpacNewMemberActionForm" styleId="country1"/>
          <html:hidden property="TXTCOUNTRY2" name="OpacNewMemberActionForm" styleId="country2"/>
          <html:hidden property="TXTPH1" name="OpacNewMemberActionForm" styleId="ph1"/>
          <html:hidden property="TXTPH2" name="OpacNewMemberActionForm" styleId="ph2"/>
          <html:hidden property="TXTEMAILID" name="OpacNewMemberActionForm" styleId="mail1"/>
          <html:hidden property="TXTFAX" name="OpacNewMemberActionForm" styleId="fax1"/>
          <html:hidden property="MEMCAT" name="OpacNewMemberActionForm" styleId="emptype_id1"/>
          <html:hidden property="MEMSUBCAT" name="OpacNewMemberActionForm" styleId="subemptype_id1"/>
          <html:hidden property="TXTDESG1" name="OpacNewMemberActionForm" styleId="desg1"/>
          <html:hidden property="TXTOFFICE" name="OpacNewMemberActionForm" styleId="office1"/>
          <html:hidden property="TXTFACULTY" name="OpacNewMemberActionForm" styleId="TXTFACULTY1"/>
          <html:hidden property="TXTDEPT" name="OpacNewMemberActionForm" styleId="TXTDEPT1"/>
          <html:hidden property="TXTCOURSE" name="OpacNewMemberActionForm" styleId="TXTCOURSE1"/>
          <html:hidden property="TXTSEM" name="OpacNewMemberActionForm" styleId="sem1"/>
          <%-- <html:hidden property="TXTREG_DATE" name="OpacNewMemberActionForm" styleId="TXTREG_DATE1"/>
          <html:hidden property="TXTEXP_DATE" name="OpacNewMemberActionForm" styleId="TXTEXP_DATE1"/>--%>
          <html:hidden property="TXTMEMID" name="OpacNewMemberActionForm" styleId="mem_id1"/>


    </html:form>
</div>

    <html:form action="/OpacNewMember" method="post" onsubmit="return check1();">

        <table  align="center" class="table" width="90%" height="100%" >



  <tr><td  width="90%"   class="header"  align="center">


		New Member Registration



        </td></tr>

  <tr><td valign="center" align="left" >


          <table  class="table_text"  border="0" >
              <tr><td align="right">Library Name*</td><td width="200px" align="left">
           <html:select property="CMBLib"  tabindex="3"  styleId="CMBLib" onchange="search_sub()" value="<%=lib_id%>">
               <html:option value="Select">Select</html:option>
               <html:options collection="libRs" property="libraryId" labelProperty="libraryName"/>
 </html:select>


</tr>
<tr><td  align="right"> SubLibrary Name*</td><td width="200px" align="left">
        <html:select property="cmdSubLibrary"  styleId="SubLibrary" value="<%=sublib_id%>" >
             <html:option value="Select">Select</html:option>
            <html:options collection="sublib" property="id.sublibraryId" labelProperty="sublibName" />
                       </html:select>

</tr>


                <tr><td width="150px">&nbsp;Member ID*</td><td class="table_textbox"><html:text    property="TXTMEMID" value="<%=mem_id1%>" styleId="mem_id2"  style="width:160px" /><br/>
                    <html:messages id="err_name" property="TXTMEMID">
				<bean:write name="err_name" />

			</html:messages>
 
                    </td>
                   
                  <td></td>  <td rowspan="3" class="table_textbox" valign="bottom">
                      
                       <%if(session.getAttribute("image")!=null){%>
                      <html:img src="<%=path%>"   alt="" width="120" height="120"/>
                        <%}else{%>

                        <html:img src="<%=image_path%>"  alt="" width="120" height="120"/>
                           <%}%>

                    
                   </tr>
            <tr><td >First Name*</td><td class="table_textbox"><html:text    property="TXTFNAME" styleId="fname2" style="width:160px" value="<%=fname%>" /><br/>
                 <html:messages id="err_name" property="TXTFNAME">
				<bean:write name="err_name" />

			</html:messages>

                </td>

                </tr>
            <tr><td>&nbsp;Middle Name</td><td class="table_textbox"><html:text  property="TXTMNAME" styleId="mname2" style="width:160px" value="<%=mname%>" /></td></tr>
            <tr><td>&nbsp;Last Name*</td><td class="table_textbox"><html:text  property="TXTLNAME" styleId="lname2" style="width:160px" value="<%=lname%>" />
                <br/>
                 <html:messages id="err_name" property="TXTLNAME">
				<bean:write name="err_name" />

			</html:messages>
                </td>
                <%--   <td width="150px" valign="bottom"> <a href="<%=request.getContextPath()%>/OPAC/upload1.jsp">Image Upload</a></td><td class="table_textbox" valign="bottom">




    </td>--%>


                </tr>
            <tr>  <td>&nbsp;Email ID*</td><td class="table_textbox"><html:text  property="TXTEMAILID" styleId="mail2" style="width:160px" value="<%=mail_id%>" />
                <br/>
                 <html:messages id="err_name" property="TXTEMAILID">
				<bean:write name="err_name" />

			</html:messages>
                </td>
                 <td> Type of Member*</td><td class="table_textbox">
                 <html:select  property="MEMCAT" style="width:160px" styleId="emptype_id" tabindex="3" value="<%=memcat%>" onchange="return search();">
                  <%--   <html:options  collection="list1" labelProperty="emptypeFullName" property="id.emptypeId"></html:options>--%>
                     </html:select>
                     <br/>
                


                  </td>


            </tr>
             <tr><td>Local Address*</td><td class="table_textbox"> <html:text property="TXTADD1" style="width:160px" styleId="add11" value="<%=add1%>" />
                 <br/>
                 <html:messages id="err_name" property="TXTADD1">
				<bean:write name="err_name" />

			</html:messages>

                 </td>
              <td>Member SubCategory*
                  </td><td class="table_textbox">
                     <html:select  property="MEMSUBCAT" styleId="subemptype_id" value="<%=submemcat%>">
                       <%--  <html:options  collection="list2" labelProperty="subEmptypeFullName" property="id.subEmptypeId"></html:options>--%>
                     </html:select>
                        <br/>
              
                      </td>

             </tr>
             <tr><td>City*</td><td class="table_textbox"><html:text  property="TXTCITY1" value="<%=city1%>" styleId="city11" style="width:160px"/>
                 <br/>
                 <html:messages id="err_name" property="TXTCITY1">
				<bean:write name="err_name" />

			</html:messages>

                 </td><td>Employee Designation</td><td class="table_textbox"><html:text  property="TXTDESG1" style="width:160px" styleId="desg2" value="<%=desg%>"/></td></tr>
             <tr><td >State*</td><td class="table_textbox"><html:text  property="TXTSTATE1" style="width:160px" styleId="state11" value="<%=state1%>"/>
                 <br/>
                 <html:messages id="err_name" property="TXTSTATE1">
				<bean:write name="err_name" />

			</html:messages>

                 </td><td>Office Name</td><td class="table_textbox"><html:text  property="TXTOFFICE" style="width:160px" styleId="office2" value="<%=office%>"/></td></tr>
             <tr><td>Country*</td><td class="table_textbox"><html:text  property="TXTCOUNTRY1" value="<%=country1%>" styleId="country11" style="width:160px"/>
                 <br/>
                 <html:messages id="err_name" property="TXTCOUNTRY1">
				<bean:write name="err_name" />

			</html:messages>

                 </td><td> Faculty of
                 </td><td class="table_textbox">
                      <html:select  property="TXTFACULTY" styleId="TXTFACULTY" style="width:160px" value="<%=faculty%>" onchange="return search1()" tabindex="3">
                       <%--   <html:options  collection="list3" property="id.facultyId" labelProperty="facultyName"></html:options>--%>
                     </html:select>
                      </td></tr>
             <tr><td>Mobile*</td><td class="table_textbox"><html:text  property="TXTPH1" value="<%=ph1%>" styleId="ph11" style="width:160px"/>
                 <br/>
                 <html:messages id="err_name" property="TXTPH1">
				<bean:write name="err_name" />

			</html:messages>

                 </td> <td> Department  </td><td class="table_textbox">
                     <html:select  property="TXTDEPT" styleId="TXTDEPT" style="width:160px" value="<%=dept%>" onchange="return search_dept();" tabindex="3">
                        <%-- <html:options  collection="list4" property="id.deptId" labelProperty="deptName"></html:options>--%>
                     </html:select>

                 </td></tr>
             <tr><td>Land Line No.</td><td class="table_textbox"><html:text  property="TXTPH2" styleId="ph22" value="<%=ph2%>" style="width:160px"/></td>
                 <td>Course</td><td class="table_textbox">

                    <html:select  property="TXTCOURSE" styleId="TXTCOURSE" style="width:160px" value="<%=course%>"  tabindex="3">
                      <%--  <html:options  collection="list5" property="id.courseId"  labelProperty="courseName"></html:options>--%>
                     </html:select>







</td></tr>
             <tr><td>Fax</td><td class="table_textbox"><html:text  property="TXTFAX" styleId="fax2" style="width:160px" value="<%=fax%>"/></td><td> Semester/Year
                 </td><td class="table_textbox"><html:text  property="TXTSEM"  styleId="sem2"  value="<%=sem%>" styleClass="textBoxWidth" style="width:160px"  />

                  </td></tr>

            <tr><td>Permanent Address</td><td class="table_textbox"><html:text property="TXTADD2" value="<%=add2%>" styleId="add22"  style="width:160px"/></td></tr>
             <tr><td >City</td><td class="table_textbox"><html:text  property="TXTCITY2" value="<%=city2%>" styleId="city22" style="width:160px"/></td>
              </tr>


             
             <tr><td >State</td><td class="table_textbox"><html:text  property="TXTSTATE2" value="<%=state2%>" styleId="state22" style="width:160px"/></td>
                </tr>
             <tr><td >Country</td><td class="table_textbox"><html:text  property="TXTCOUNTRY2" value="<%=country2%>" styleId="country22" style="width:160px"/></td>
             <td  align="center" class="txt2"> <html:submit property="button" value="Submit">Submit</html:submit>&nbsp;&nbsp;<html:reset>Reset</html:reset>
                      </td>
             </tr>



     </table>
      </td></tr>
  
     </table>


 </html:form>
     


</body>



   
</html>
