<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

 <%@page contentType="text/html" import="java.util.*,java.sql.*,java.io.*,com.myapp.struts.hbm.SubLibrary,com.myapp.struts.AdminDAO.*,org.apache.struts.upload.FormFile"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<link type="text/css" rel="stylesheet" href="/LibMS/css/page.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/helpdemo.js"></script>




 <%
    
        List libRs = (List)session.getAttribute("libRs");
        List sublib = (List)session.getAttribute("sublib");



         List list1=(List)session.getAttribute("list1");
      
      
     
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

String image_path=request.getContextPath()+"/images/no-image.jpg";
%>




<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Member Registration Page</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/newformat.css"/>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;align="left";}
    else{ rtl="RTL";page=false;align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
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
               document.getElementById('emptype_id').options.length = 0;
               document.getElementById('TXTFACULTY').options.length = 0;
newOpt = document.getElementById('SubLibrary').appendChild(document.createElement('option'));
newOpt.value = "Select";
newOpt.text = "Select";
newOpt = document.getElementById('emptype_id').appendChild(document.createElement('option'));
newOpt.value = "Select";
newOpt.text = "Select";
newOpt = document.getElementById('TXTFACULTY').appendChild(document.createElement('option'));
newOpt.value = "Select";
newOpt.text = "Select";
search1();
search();




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
search_faculty();
        search_member();
        search1();search();
return true;
}
}
function search_faculty() {

    var keyValue = document.getElementById('CMBLib').options[document.getElementById('CMBLib').selectedIndex].value;


if (keyValue=="Select")
    {


               document.getElementById('CMBLib').focus();
               document.getElementById('TXTFACULTY').options.length = 0;

newOpt = document.getElementById('TXTFACULTY').appendChild(document.createElement('option'));
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
newOpt = document.getElementById('TXTFACULTY').appendChild(document.createElement('option'));
newOpt.value = "Select";
newOpt.text = "Select";

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
newOpt = document.getElementById('emptype_id').appendChild(document.createElement('option'));
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
newOpt = document.getElementById('emptype_id').appendChild(document.createElement('option'));
newOpt.value = "Select";
newOpt.text = "Select";
for (var i = 0; i < em.length ; i++)
{
var ndValue = em[i].firstChild.nodeValue;
var ndValue1=em1[i].firstChild.nodeValue;
newOpt = document.getElementById('emptype_id').appendChild(document.createElement('option'));
newOpt.value = ndValue;
newOpt.text = ndValue1;


}

//search();
}

function update_sub(cartXML)
{
var depts = cartXML.getElementsByTagName("sublibrary_ids")[0];
var em = depts.getElementsByTagName("sublibrary_id");
var em1 = depts.getElementsByTagName("sublibrary_name");

        var newOpt =document.getElementById('SubLibrary').appendChild(document.createElement('option'));
        document.getElementById('SubLibrary').options.length = 0;
        newOpt = document.getElementById('SubLibrary').appendChild(document.createElement('option'));
        newOpt.value = "Select";
        newOpt.text = "Select";

        
      //  newOpt = document.getElementById('emptype_id').appendChild(document.createElement('option'));
       // newOpt.value = "Select";
      //  newOpt.text = "Select";
     //   newOpt = document.getElementById('TXTFACULTY').appendChild(document.createElement('option'));
      //  newOpt.value = "Select";
      //  newOpt.text = "Select";

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

search();
search1();
}

function search() {

    var keyValue = document.getElementById('emptype_id').options[document.getElementById('emptype_id').selectedIndex].value;
 var keyValue1 = document.getElementById('CMBLib').options[document.getElementById('CMBLib').selectedIndex].value;

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
newOpt = document.getElementById('subemptype_id').appendChild(document.createElement('option'));
newOpt.value = "Select";
newOpt.text = "Select";
<%
if(submemcat!=null)
if(submemcat.equalsIgnoreCase("Select")){%>
        document.getElementById('subemptype_id').value="Select";
        <%}%>

}

function search1() {

    var keyValue = document.getElementById('TXTFACULTY').options[document.getElementById('TXTFACULTY').selectedIndex].value;
 var keyValue1 = document.getElementById('CMBLib').options[document.getElementById('CMBLib').selectedIndex].value;


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

newOpt = document.getElementById('TXTCOURSE').appendChild(document.createElement('option'));
newOpt.value = "Select";
newOpt.text = "Select";


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
        alert("<%=resource.getString("circulation.cir_newmember.selectlib")%>");

        document.getElementById('CMBLib').focus();

        return false;
    }
   if(keyValue1=="Select")
    {
        alert("<%=resource.getString("circulation.cir_newmember.entersublib")%>");

        document.getElementById('SubLibrary').focus();

        return false;
    }
    if(document.getElementById('fname2').value=="")
    {
        alert("<%=resource.getString("circulation.cir_newmember.enterfname")%>");

        document.getElementById('fname2').focus();

        return false;
    }
     if(document.getElementById('lname2').value=="")
    {
        alert("<%=resource.getString("circulation.cir_newmember.enterlname")%>");


        document.getElementById('lname2').focus();

        return false;
    }
     if(document.getElementById('mail2').value=="")
    {
        alert("<%=resource.getString("circulation.cir_newmember.enteremailid")%>");

        document.getElementById('mail2').focus();

        return false;
    }
     if(document.getElementById('add11').value=="")
    {
        alert("<%=resource.getString("circulation.cir_newmember.enterlocaladd")%>");

        document.getElementById('add11').focus();

        return false;
    }
     if(document.getElementById('city11').value=="")
    {
        alert("<%=resource.getString("circulation.cir_newmember.entercity")%>");

        document.getElementById('city11').focus();

        return false;
    }
     if(document.getElementById('state11').value=="")
    {
        alert("<%=resource.getString("circulation.cir_newmember.enterstate")%>");

        document.getElementById('state11').focus();

        return false;
    }
     if(document.getElementById('country11').value=="")
    {
        alert("<%=resource.getString("circulation.cir_newmember.entercountry")%>");

        document.getElementById('country11').focus();

        return false;
    }
     if(document.getElementById('ph11').value=="")
    {
        alert("<%=resource.getString("circulation.cir_newmember.enterphno")%>");

        document.getElementById('ph11').focus();

        return false;
    }
     if(document.getElementById('emptype_id').value=="")
    {
        alert("<%=resource.getString("circulation.cir_newmember.entermemcategory")%>");

        document.getElementById('emptype_id').focus();

        return false;
    }
     if(document.getElementById('subemptype_id').value=="")
    {
        alert("<%=resource.getString("circulation.cir_newmember.entersubmemcategory")%>");

        document.getElementById('subemptype_id').focus();

        return false;
    }
return true;
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
function loadHelp()
{
    window.status="Press F1 for Help";
}
</script>

</head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<style type="text/css">
a:active
{
   color: #0000FF;
}
</style>
</head>
<jsp:include page="opacheader.jsp"></jsp:include>
<body  onload="search_sub();search_faculty();search1();search();loadHelp();"style="margin: 0px 0px 0px 0px;">

     <div id="image1"
   style="  top:50%;background: cyan;
   left:40%;
   overflow: hidden;
      position: absolute;
      border: double 1px black;
      display: none;"

      >
    <%
    if(msg!=null){%><script>
    alert("<%=msg%>");</script>
    <%}%>



    <html:form action="/imageupload1" method="post" styleId="form1" enctype="multipart/form-data">
        <table class="datagrid"  style="border:dashed 1px cyan;" align="center" height="50%" width="30%">
            <tr><td>
        <%=resource.getString("opac.simplesearch.imageupload")%> <html:file  property="img" name="OpacNewMemberActionForm" styleClass="btnapp" styleId="img" onchange="submit()"  onclick="copy()" />
          <input type="hidden" name="filename" id="filename" />
          <input type="button" onclick="showdiv();" class="btnapp" value="Cancel" />
</td></tr></table>
          
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

        <table dir="<%=rtl%>" align="center" class="datagrid" width="60%" height="100%" style="border: dashed 1px cyan;" >



            <tr><td  width="100%" style="border-bottom: dashed 1px cyan;"  class="header1"  align="center">

<%=resource.getString("opac.newmemberentry.text")%>
		



        </td></tr>

  <tr><td valign="center" align="<%=align%>" width="100%" >


          <table dir="<%=rtl%>" class="table_text" width="100%" >
              <tr><td dir="<%=rtl%>" width="25%"   align="right"><%=resource.getString("opac.simplesearch.library")%>*</td>
                  <td  align="<%=align%>"  width="25%" >
           
                      <% if(lib_id!=null){%>
                      <html:select property="CMBLib" disabled="true" styleClass="btnapp"  tabindex="3"  styleId="CMBLib" onchange="search_sub()" value="<%=lib_id%>">
               <html:option value="Select">Select</html:option>
               <html:options collection="libRs" property="libraryId" labelProperty="libraryName"/>
 </html:select>
               <html:hidden property="CMBLib"    styleId="CMBLib" onchange="search_sub()" value="<%=lib_id%>"></html:hidden>
                     

               <%}else{
%>
                    <html:select property="CMBLib"  tabindex="3" styleClass="btnapp"  styleId="CMBLib" onchange="search_sub()" value="<%=lib_id%>">
               <html:option value="Select">Select</html:option>
               <html:options collection="libRs" property="libraryId" labelProperty="libraryName"/>
                  </html:select>


<%}%>
                  </td><td rowspan="3" align="right" colspan="2" class="table_textbox" valign="bottom">

                       <%--<%if(session.getAttribute("image")!=null){%>
                       <html:img src="<%=path%>"   alt="" width="100" height="100"/>
                        <%}else{%>

                        <html:img src="<%=image_path%>"  alt="" width="100" height="100"/>
                           <%}%>

                           <br/> <a href="#" onclick="javascript:showdiv();"><%=resource.getString("circulation.cir_newmember.imageupload")%></a>--%></td>
 
</tr>
<tr><td dir="<%=rtl%>" align="right"><%=resource.getString("opac.simplesearch.sublibrary")%>*</td><td  align="<%=align%>">

           <% if(sublib_id!=null){%>
           <html:select property="cmdSubLibrary" disabled="true" styleClass="btnapp" styleId="SubLibrary" value="<%=sublib_id%>" >
             <html:option value="Select">Select</html:option>

             <%--<%!--<html:options collection="sublib" property="id.sublibraryId" labelProperty="sublibName" />--%>--%>
            </html:select>
           <html:hidden property="cmdSubLibrary"  styleId="SubLibrary" value="<%=sublib_id%>" ></html:hidden>
             


               <%}else{%>

                <html:select property="cmdSubLibrary"  styleId="SubLibrary" styleClass="btnapp" value="<%=sublib_id%>" >
             <html:option value="Select">Select</html:option>

             <%--<%!--<html:options collection="sublib" property="id.sublibraryId" labelProperty="sublibName" />--%>--%>
                       </html:select>


               <%}%>
    </td>

</tr>


<tr><td >&nbsp;<%=resource.getString("opac.myaccount.memberid")%>*</td><td class="table_textbox"><html:text    property="TXTMEMID" value="<%=mem_id1%>" styleId="mem_id2"  style="width:160px" onfocus="statwords('Enter Member Id such En number for Student')" onblur="loadHelp()" />
                    <html:messages id="err_name" property="TXTMEMID">
				<bean:write name="err_name" />

			</html:messages>
 
                    </td>
                   
                    
                   </tr>
            <tr><td ><%=resource.getString("opac.newmemberentry.firstname")%>*</td><td class="table_textbox"><html:text    property="TXTFNAME" styleId="fname2" style="width:160px" value="<%=fname%>" onfocus="statwords('Enter First Name')" onblur="loadHelp()" />
                 <html:messages id="err_name" property="TXTFNAME">
				<bean:write name="err_name" />

			</html:messages>

                </td><td><%=resource.getString("opac.newmemberentry.fax")%></td><td class="table_textbox"><html:text  property="TXTFAX" styleId="fax2" style="width:160px" value="<%=fax%>"onfocus="statwords('Enter FAX Number eg. 0571207124')" onblur="loadHelp()" /></td>

                </tr>





                 

           
             


            
             
            
             







            <tr><td>&nbsp;<%=resource.getString("opac.newmemberentry.middlename")%></td><td class="table_textbox"><html:text  property="TXTMNAME" styleId="mname2" style="width:160px" value="<%=mname%>" onfocus="statwords('Enter Middle Name')" onblur="loadHelp()" /></td>
            <%--<td><%=resource.getString("opac.newmemberentry.semister")%>
                 </td>--%>
                 <td><%=resource.getString("opac.newmemberentry.paddress")%></td><td class="table_textbox"><html:text property="TXTADD2" value="<%=add2%>" styleId="add22"  style="width:160px" onfocus="statwords('Enter House number/Street/Colony Name')" onblur="loadHelp()" /></td>
                 <%--<td class="table_textbox"><html:text  property="TXTSEM"  styleId="sem2"  value="<%=sem%>" styleClass="textBoxWidth" style="width:160px" onfocus="statwords('Enter Semester or year of the course eg. First Year/Fist Semester')" onblur="loadHelp()" />

                  </td>--%></tr>
            <tr><td>&nbsp;<%=resource.getString("opac.newmemberentry.lastname")%>*</td><td class="table_textbox"><html:text  property="TXTLNAME" styleId="lname2" style="width:160px" value="<%=lname%>" onfocus="statwords('Enter Last Name')" onblur="loadHelp()" />
                
                 <html:messages id="err_name" property="TXTLNAME">
				<bean:write name="err_name" />

			</html:messages>
                </td>
                <td ><%=resource.getString("opac.newmemberentry.city")%></td><td class="table_textbox"><html:text  property="TXTCITY2" value="<%=city2%>" styleId="city22" style="width:160px"  onfocus="statwords('Enter City Name)" onblur="loadHelp()" /></td>
                


                </tr>
            <tr>  <td>&nbsp;<%=resource.getString("opac.newmemberentry.emailid")%>*</td><td class="table_textbox"><html:text  property="TXTEMAILID" styleId="mail2" style="width:160px" value="<%=mail_id%>" onfocus="statwords('Enter Email Id you will get user name and password through thiss email Id')" onblur="loadHelp()" />
                
                 <html:messages id="err_name" property="TXTEMAILID">
				<bean:write name="err_name" />

			</html:messages>
                </td>
                 <td><%=resource.getString("opac.newmemberentry.typemem")%> *</td><td class="table_textbox">
                 <html:select  property="MEMCAT" styleClass="btnapp" style="width:160px"  styleId="emptype_id" tabindex="3" value="Select" onchange="return search();">
                 
                 
                         <html:option value="Select" >Select</html:option>
                 </html:select>
                     
                


                  </td>


            </tr>
             <tr><td><%=resource.getString("opac.newmemberentry.localadd")%>*</td><td class="table_textbox"> <html:text property="TXTADD1" style="width:160px" styleId="add11" value="<%=add1%>" onfocus="statwords('Enter House number/Street/Colony Name')" onblur="loadHelp()" />
                 
                 <html:messages id="err_name" property="TXTADD1">
				<bean:write name="err_name" />

			</html:messages>

                 </td>
              <td><%=resource.getString("opac.newmemberentry.memsubcat")%>*
                  </td><td class="table_textbox">
                     <html:select  property="MEMSUBCAT" styleClass="btnapp" styleId="subemptype_id" value="<%=submemcat%>">
                       <%--  <html:options  collection="list2" labelProperty="subEmptypeFullName" property="id.subEmptypeId"></html:options>--%>
                     </html:select>
                        
              
                      </td>

             </tr>
             <tr><td><%=resource.getString("opac.newmemberentry.city")%>*</td><td class="table_textbox"><html:text  property="TXTCITY1" value="<%=city1%>" styleId="city11" style="width:160px" onfocus="statwords('Enter City Name')" onblur="loadHelp()" />
                 
                 <html:messages id="err_name" property="TXTCITY1">
				<bean:write name="err_name" />

			</html:messages>

                 </td><td><%=resource.getString("opac.newmemberentry.empdesign")%></td><td class="table_textbox"><html:text  property="TXTDESG1" style="width:160px" styleId="desg2" value="<%=desg%>" onfocus="statwords('Enter Designation studuch as  staff/student/Doctor/professor')" onblur="loadHelp()" /></td></tr>
             <tr><td ><%=resource.getString("opac.newmemberentry.state")%>*</td><td class="table_textbox"><html:text  property="TXTSTATE1" style="width:160px" styleId="state11" value="<%=state1%>" onfocus="statwords('Enter State Name')" onblur="loadHelp()" />
                 
                 <html:messages id="err_name" property="TXTSTATE1">
				<bean:write name="err_name" />

			</html:messages>

                 </td><td><%=resource.getString("opac.newmemberentry.offname")%></td><td class="table_textbox"><html:text  property="TXTOFFICE" style="width:160px" styleId="office2" value="<%=office%>" onfocus="statwords('Enter Office Name')" onblur="loadHelp()" /></td></tr>
             <tr><td><%=resource.getString("opac.newmemberentry.country")%>*</td><td class="table_textbox"><html:text  property="TXTCOUNTRY1" value="<%=country1%>" styleId="country11" style="width:160px" onfocus="statwords('Enter Country Name')" onblur="loadHelp()" />
                 
                 <html:messages id="err_name" property="TXTCOUNTRY1">
				<bean:write name="err_name" />

			</html:messages>

                 </td><td> <%=resource.getString("opac.newmemberentry.facultyof")%>
                 </td><td class="table_textbox">
                      <html:select  property="TXTFACULTY" styleClass="btnapp" styleId="TXTFACULTY" style="width:160px" value="Select" onchange="return search1()" tabindex="3">
                         
                           <%--<html:options  collection="list3"  labelProperty="facultyName" property="id.facultyId"></html:options>--%>
                            
                         
                     </html:select>
                      </td></tr>
             <tr><td><%=resource.getString("opac.newmemberentry.mobile")%>*</td><td class="table_textbox"><html:text  property="TXTPH1" value="<%=ph1%>" styleId="ph11" style="width:160px" onfocus="statwords('Enter Mobile Number')" onblur="loadHelp()" />
                 
                 <html:messages id="err_name" property="TXTPH1">
				<bean:write name="err_name" />

			</html:messages>

                 </td> <td><%=resource.getString("opac.newmemberentry.dept")%> </td><td class="table_textbox">
                     <html:select  property="TXTDEPT" styleClass="btnapp" styleId="TXTDEPT" style="width:160px" value="<%=dept%>" onchange="return search_dept();" tabindex="3">
                        <%-- <html:options  collection="list4" property="id.deptId" labelProperty="deptName"></html:options>--%>
                     </html:select>

                 </td></tr>
             <tr><td><%=resource.getString("opac.newmemberentry.landlineno")%>.</td><td class="table_textbox"><html:text  property="TXTPH2" styleId="ph22" value="<%=ph2%>" style="width:160px"onfocus="statwords('Enter Land Line Number like 0571 207124')" onblur="loadHelp()" /></td>
                 <td><%=resource.getString("opac.newmemberentry.course")%></td><td class="table_textbox">

                    <html:select  property="TXTCOURSE" styleId="TXTCOURSE" styleClass="btnapp" style="width:160px" value="<%=course%>"  tabindex="3">
                      <%--  <html:options  collection="list5" property="id.courseId"  labelProperty="courseName"></html:options>--%>
                     </html:select>







</td></tr>
             <tr><td  align="center" colspan="4" class="txt2"> <html:submit property="button" styleClass="buttonhome" ><%=resource.getString("opac.newmemberentry.submit")%></html:submit>&nbsp;&nbsp;<html:reset styleClass="buttonhome" ><%=resource.getString("opac.newmemberentry.reset")%></html:reset>
                      </td></tr>


     </table>
      </td></tr>
  
     </table>


 </html:form>
     


</body>


<jsp:include page="opacfooter.jsp"></jsp:include>
   
</html>
