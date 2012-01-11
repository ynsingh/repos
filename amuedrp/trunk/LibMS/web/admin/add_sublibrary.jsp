<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Library Details
-->
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
 <jsp:include page="header.jsp" flush="true" />

<%
String sublib_id=(String)request.getAttribute("new_sublib_id");
boolean button=false;
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>LibMS : Manage Library </title>
<link href="common" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/newformat.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/acquisition/dhtmlgoodies_calendar/dhtmlgoodies_calendar.css" media="screen"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/acquisition/dhtmlgoodies_calendar/dhtmlgoodies_calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/helpdemo.js"></script>


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

window.status="Press F1 for Help";
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

function hideTextbox(){
    var vale=document.getElementById('TXTDEPT').options[document.getElementById('TXTDEPT').selectedIndex].value;

    if(vale=="Select")
        {
           
            document.getElementById("sublib_name").style.visibility = "visible";
        }
        else
            {
                document.getElementById("sublib_name").style.visibility = "hidden";
            }
}
function loadHelp()
{
    window.status="Press F1 for Help";
}
</script>

</head>
<body onload="search1();">
 <div
   style="  top:120px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
    
     <html:form action="/add_sub_lib" method="post" >
 <table dir="<%=rtl%>" class="table" width="400px" height="200px" align="center">


                <tr><td dir="<%=rtl%>" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;"><%=resource.getString("systemsetup.manage_library.managesublib")%></td></tr>
                <tr><td dir="<%=rtl%>" valign="top" align="center"> <br/>
                <table dir="<%=rtl%>" >

 <tr>

    <td dir="<%=rtl%>" align="<%=align%>"><strong><%=resource.getString("systemsetup.add_sublibrary.sublibraryid")%></strong></td>
    <html:hidden property="sublibrary_id" styleId="sublibrary_id"  name="AddSubLibraryActionForm" value="<%=sublib_id%>" styleClass="textBoxWidth" />
    <td dir="<%=rtl%>"><html:text property="sublibrary_id" styleId="sublibrary_id" disabled="true" name="AddSubLibraryActionForm" value="<%=sublib_id%>" styleClass="textBoxWidth" />

    </td>
  </tr>
   <tr>
    <td dir="<%=rtl%>" width="150" align="<%=align%>"><strong><%=resource.getString("systemsetup.add_sublib.faculty")%></strong> </td>
    <td dir="<%=rtl%>"><html:select  property="faculty1" styleId="TXTFACULTY" style="width:160px" value="Select" onchange="return search1()">
             <html:option    value="Select">Select</html:option>
            <html:options  collection="list2"  labelProperty="facultyName" property="id.facultyId"></html:options>
                 
                     </html:select>

    
    
    </td>
   </tr>
 
  <tr>
    
    <td dir="<%=rtl%>" align="<%=align%>"><strong><%=resource.getString("systemsetup.add_dept.deptname")%><a class="star">*</a></strong></td>
    <td>
        <html:select  property="sublib_name1" styleId="TXTDEPT" style="width:160px"   value="Select" tabindex="3" onchange="hideTextbox()">
                <html:option    value="Select">Select</html:option>
                     </html:select>
      

    </td>
    
  </tr>
  <tr><td dir="<%=rtl%>" align="<%=align%>" > <%=resource.getString("systemsetup.add_dept.or")%>&nbsp;</td><td>


          
          <html:text property="sublib_name" styleId="sublib_name"  name="AddSubLibraryActionForm" value="" styleClass="textBoxWidth" onfocus="statwords('Enter Department Name')" onblur="loadHelp()" /></td></tr>
       

          
             
  
 
 
  <tr>
   
    <td dir="<%=rtl%>" align="<%=align%>"><strong><%=resource.getString("systemsetup.add_sublib.deptadd")%><a class="star">*</a></strong></td>
    <td dir="<%=rtl%>"><html:text property="department_address" styleId="department_address" name="AddSubLibraryActionForm" styleClass="textBoxWidth"  onfocus="statwords('Enter Department Address')" onblur="loadHelp()" />
        
     </td>
    
  </tr>
 
<tr>
    <td dir="<%=rtl%>" colspan="4" align="center">
        <br/>
        <input type="submit"  value="<%=resource.getString("circulation.cir_newmember.submit")%>"  onClick="return validation();"/>

        <input type="button"  value="<%=resource.getString("circulation.cir_member_reg.back")%>" onclick="return quit();" />
 </td>
</tr>
</table>
                    </td></tr></table>
</html:form>>
 
</div>
</body>

 <script language="javascript" type="text/javascript">
  function quit()
  {

      window.location="<%=request.getContextPath()%>/admin/manage_library.jsp";
      return false;
  }
   function validation()
    {




var keyValue = document.getElementById('TXTDEPT').options[document.getElementById('TXTDEPT').selectedIndex].value;


 
    var sublib_name=document.getElementById('sublib_name');

  
    var department_address=document.getElementById('department_address');







var str="<%=resource.getString("circulation.cir_newmember.enterfollowingvalues")%>:-";


   
    if(sublib_name.value=="")
        {
            if(keyValue=="Select")
            {str+="\n <%=resource.getString("systemsetup.add_sublib.enterdeptname")%> ";
             alert(str);
             document.getElementById('sublib_name').focus();
            return false;
            }
        }
   




  if(department_address.value=="")
      {
             str+="\n <%=resource.getString("systemsetup.add_sublib.enteradd")%>";
           alert(str);
           document.getElementById('department_address').focus();
            return false;
         

     }


if(str=="<%=resource.getString("circulation.cir_newmember.enterfollowingvalues")%>:-")
   {
       return true;

   }
else
    {

        alert(str);
        document.getElementById('faculty').focus();
        return false;
    }




    }
 </script>

  <%
 String msg=(String)request.getAttribute("msg");
           if (msg!=null){
%>
 <script language="javascript">
 window.location="<%=request.getContextPath()%>/admin/add_sublibrary.jsp";
 alert("<%=msg%>");
 </script>
 <%
}

%>
</html>

 