<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Staff ID 
-->

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>

 <jsp:include page="/admin/header.jsp" flush="true" />

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<%
String library_id=(String)session.getAttribute("library_id");
String msg=(String)request.getAttribute("msg");
String msg1=(String)request.getAttribute("msg1");

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

 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
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


function search1() {

    var keyValue = document.getElementById('TXTFACULTY').options[document.getElementById('TXTFACULTY').selectedIndex].value;

if (keyValue=="Select")
    {


               document.getElementById('TXTFACULTY').focus();
               document.getElementById('TXTDEPT').options.length = 0;
               newOpt = document.getElementById('TXTDEPT').appendChild(document.createElement('option'));
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




function update(cartXML)
{


var depts = cartXML.getElementsByTagName("dept_ids")[0];
var em = depts.getElementsByTagName("dept_id");
var em1 = depts.getElementsByTagName("dept_name");

        var newOpt =document.getElementById('TXTDEPT').appendChild(document.createElement('option'));
        document.getElementById('TXTDEPT').options.length = 1;
        newOpt.value = "Select";
        newOpt.text = "Select";

for (var i = 0; i < em.length ; i++)
{
var ndValue = em[i].firstChild.nodeValue;
var ndValue1=em1[i].firstChild.nodeValue;
newOpt = document.getElementById('TXTDEPT').appendChild(document.createElement('option'));
newOpt.value = ndValue;
newOpt.text = ndValue1;


}

}


    </script>
 <script language="javascript" type="text/javascript">


function Add()
{
    var buttonvalue="Add";
    document.getElementById("button").setAttribute("value", buttonvalue);
    return true;
}

function View()
{
    var buttonvalue="View";
    document.getElementById("button").setAttribute("value", buttonvalue);
    return true;
}
function Update()
{
    var buttonvalue="Update";
    document.getElementById("button").setAttribute("value", buttonvalue);
    return true;
}
function Delete()
{
    var buttonvalue="Delete";
    document.getElementById("button").setAttribute("value", buttonvalue);
    return true;
}

function check1()
{
     if(document.getElementById('TXTFACULTY').value=="Select")
    {
        alert("<%=resource.getString("systemsetup.manage_course.selectcourse")%>");

        document.getElementById('TXTFACULTY').focus();

        return false;
    }

     if(document.getElementById('TXTDEPT').value=="Select")
    {
        alert("<%=resource.getString("systemsetup.manage_course.selectdept")%>");

        document.getElementById('TXTDEPT').focus();

        return false;
    }
    if(document.getElementById('course_id').value=="")
    {
        alert("<%=resource.getString("systemsetup.manage_course.entercourseid")%>");

        document.getElementById('course_id').focus();

        return false;
    }

  }





  function quit()
  {

      window.location="<%=request.getContextPath()%>/admin/main.jsp";
      return false;
  }



    </script>
</head>
<body onload="search1();">
 
    <html:form method="post" onsubmit="return check1()" action="/courseregistration">
       
<div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

    <table dir="<%=rtl%>" border="1" class="table" width="400px" height="200px" align="center">

  
        <tr><td dir="<%=rtl%>" align="center" colspan="3" class="headerStyle" bgcolor="#E0E8F5" height="25px;"><%=resource.getString("systemsetup.manage_faculty.managecourse")%></td></tr>
        <tr><td>
                <table dir="<%=rtl%>">

               <tr><td dir="<%=rtl%>" align="<%=align%>">&nbsp;<%=resource.getString("systemsetup.add_faculty.facultyname")%> </td><td align="<%=align%>">
                       <html:select   property="faculty_id" styleId="TXTFACULTY" value="Select" onchange="search1()">
                          <html:option value="Select">Select</html:option>
                          <html:options collection="faculty" property="id.facultyId" labelProperty="facultyName"></html:options>

                     </html:select>
                  

               </td></tr>
                 
<tr><td dir="<%=rtl%>" align="center" width="150px"><%=resource.getString("systemsetup.add_dept.deptname")%></td><td align="<%=align%>">
        <html:select   property="dept_id" styleId="TXTDEPT" value="Select">
            
                          <html:option value="Select">Select</html:option>
 <html:options  collection="department" property="id.deptId" labelProperty="deptName"></html:options>

                     </html:select>


               </td></tr>
<tr><td dir="<%=rtl%>" class="txt2"><%=resource.getString("systemsetup.manage_course.entercourseid")%></td><td>
                        <input type="text" id="course_id" name="course_id" value=""/>
                        </td>
</tr></table>
            </td><td>
                <table dir="<%=rtl%>" cellspacing="10px">

                    <tr><td dir="<%=rtl%>" width="150px" align="center"> <input type="submit" class="btn" id="Button1"  value="<%=resource.getString("systemsetup.manage_notice.add")%>" onclick="return Add();" /></td></tr>
                    <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="submit" id="Button2" class="btn"  value="<%=resource.getString("circulation.cir_member_reg.update")%>" onclick="return Update();"  /></td></tr>
                    <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="submit" id="Button3" value="<%=resource.getString("circulation.cir_member_reg.view")%>" onclick="return View();" class="btn"  /></td></tr>
                    <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="submit" id="Button4"  value="<%=resource.getString("circulation.cir_member_reg.delete")%>" onclick="return Delete();" class="btn" /></td></tr>
                         <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="button" id="Button5"  value="<%=resource.getString("circulation.cir_member_reg.back")%>" class="btn" onclick="return quit()"/></td></tr>
 

                </table>
       

    <input type="hidden" name="library_id" value="<%=library_id%>">
   
  <input type="hidden" id="button" name="button" />







</td></tr>
        <tr><td class="mess" colspan="2">
               <%
          if (msg!=null)
          {
        %>


        <p class="mess" dir="<%=rtl%>" align="<%=align%>">  <%=msg%></p>

        <%
        }
        %>
         <%if (msg1!=null)
          {
        %>


        <p class="err" dir="<%=rtl%>" align="<%=align%>">  <%=msg1%></p>

        <%
        }
        %>


            </td></tr>
        <tr><td align="justify" colspan="2"><font color="blue" size="-1"><b><%=resource.getString("systemsetup.manage_notice.example")%>:</b> mca <%=resource.getString("systemsetup.manage_course.formca")%>, mba <%=resource.getString("systemsetup.manage_course.formba")%>, <%=resource.getString("systemsetup.manage_course.ifcourseexist")%>.</font></td></tr>
    </table>
        </div>
  
</html:form>

</body>

    
</html>
