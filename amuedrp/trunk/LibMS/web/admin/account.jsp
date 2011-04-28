<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Staff Details
-->

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
 <jsp:include page="header.jsp" flush="true" />

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<%
String library_id=(String)session.getAttribute("library_id");
String msg1=(String)request.getAttribute("msg1");

%>

    <script language="javascript">
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
    var keyValue = document.getElementById("staff_id").value;


  //keyValue = keyValue.replace(/^\s*|\s*$/g,"");
if (keyValue.length >= 1)
{
availableSelectList = document.getElementById("searchResult");
var req = newXMLHttpRequest();

req.onreadystatechange = getReadyStateHandler(req, update);

req.open("POST","<%=request.getContextPath()%>/staff_id.do", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send("getEmail_Id="+keyValue);


}

}




function update(cartXML)
{
var emails = cartXML.getElementsByTagName("email_ids")[0];
var em = emails.getElementsByTagName("email_id");
availableSelectList.innerHTML = '';
for (var i = 0; i < em.length ; i++)
{
var ndValue = em[i].firstChild.nodeValue;
availableSelectList.innerHTML += ndValue+"\n";
}
}
  function check1()
{
    if(document.getElementById('staff_id').value=="")
    {
        alert("Enter Staff Id...");

        document.getElementById('staff_id').focus();

        return false;
    }

  }





  function quit()
  {

      window.location="<%=request.getContextPath()%>/admin/main.jsp";
      return false;
  }


</script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LibMS : Manage Staff Account</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>

</head>
<body>
       <html:form method="post" onsubmit="return check1()" action="/account1">
       
<div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
     <table border="1" class="table" width="400px"  align="center">


                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Manage Staff</td></tr>
                <tr><td valign="top" align="center"> <br/>
                <table cellspacing="10px">

                    <tr><td rowspan="5" class="btn">Enter Staff ID<br><br>
                            <input type="text" id="staff_id" name="staff_id" onblur="search();" value=""/>
                        <br>
                        <div align="left" id="searchResult" class="err" style="border:#000000; "></div>
                        </td><td width="200px" align="center"> <input type="submit" class="btn" id="Button1" name="button" value="Create Account"  style="width:150px"/></td></tr>

                    
 <tr><td width="150px" align="center"><input type="submit" id="Button3" name="button" value="View Account" class="btn"  style="width:150px" /></td></tr>
 <tr><td width="150px" align="center"><input type="submit" id="Button4" name="button" value="Delete Account" class="btn"  style="width:150px"/></td></tr>
 <tr><td width="150px" align="center"><input type="submit" id="Button5" name="button" value="Update Account" class="btn"  style="width:150px"/></td></tr>
 <tr><td width="150px" align="center"><input type="submit" id="Button6" name="button" value="Back" class="btn" onclick="return quit()" style="width:150px"/></td></tr>
 

                </table>
       


    <input type="hidden" name="library_id" value="<%=library_id%>">
   








</td></tr>
                <tr><td class="err">
                         <%     if (msg1!=null){
 %>
 
 
 <%=msg1%>
 
 <%
}

%>

                    </td></tr>

     </table>
        </div>
   
</html:form>
    </body>

     

</html>
