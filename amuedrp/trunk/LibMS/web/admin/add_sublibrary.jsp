<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Library Details
-->

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
 <jsp:include page="header.jsp" flush="true" />

<%
String sublib_id=(String)request.getAttribute("new_sublib_id");
boolean button=false;
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
 <table class="table" width="400px" height="200px" align="center">


                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Manage SubLibrary</td></tr>
                <tr><td valign="top" align="center"> <br/>
                <table >

 <tr>

    <td align="left"><strong>SubLibrary Id</strong></td>
    <html:hidden property="sublibrary_id" styleId="sublibrary_id"  name="AddSubLibraryActionForm" value="<%=sublib_id%>" styleClass="textBoxWidth" />
    <td><html:text property="sublibrary_id" styleId="sublibrary_id" disabled="true" name="AddSubLibraryActionForm" value="<%=sublib_id%>" styleClass="textBoxWidth" />

    </td>
  </tr>
   <tr>
    <td width="150" align="left"><strong>Faculty</strong> </td>
    <td><html:select  property="faculty1" styleId="TXTFACULTY" style="width:160px" value="Select" onchange="return search1()">
             <html:option    value="Select">Select</html:option>
            <html:options  collection="list2"  labelProperty="facultyName" property="id.facultyId"></html:options>
                 
                     </html:select>

    
    
    </td>
   </tr>
 
  <tr>
    
    <td align="left"><strong>Department Name<a class="star">*</a></strong></td>
    <td>
        <html:select  property="sublib_name1" styleId="TXTDEPT" style="width:160px"   value="Select" tabindex="3" onchange="hideTextbox()">
                <html:option    value="Select">Select</html:option>
                     </html:select>
      

    </td>
    
  </tr>
  <tr><td align="right" > OR&nbsp;</td><td>


          
       <html:text property="sublib_name" styleId="sublib_name"  name="AddSubLibraryActionForm" value="" styleClass="textBoxWidth"/></td></tr>
       

          
             
  
 
 
  <tr>
   
    <td align="left"><strong>Department Address<a class="star">*</a></strong></td>
    <td><html:text property="department_address" styleId="department_address" name="AddSubLibraryActionForm" styleClass="textBoxWidth"/>
        
     </td>
    
  </tr>
 
<tr>
    <td colspan="4" align="center">
        <br/>
        <input type="submit"  value="Submit"  onClick="return validation();"/>

        <input type="button"  value="Back" onclick="return quit();" />
 </td>
</tr>
</table>
                    </td></tr></table>
</html:form>>
 
</div>
</body>
<div
   style="
      top: 680px;

      position: absolute;

      visibility: show;">
        <jsp:include page="/admin/footer.jsp" />

</div>

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







var str="Enter Following Values:-";


   
    if(sublib_name.value=="")
        {
            if(keyValue=="Select")
            {str+="\n Enter Department Name ";
             alert(str);
             document.getElementById('sublib_name').focus();
            return false;
            }
        }
   




  if(department_address.value=="")
      {
             str+="\n Enter Address";
           alert(str);
           document.getElementById('department_address').focus();
            return false;
         

     }


if(str=="Enter Following Values:-")
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

 