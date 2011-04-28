<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Staff ID
-->

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.List"%>

<jsp:include page="../admin/header.jsp" flush="true" />

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<%
String library_id=(String)session.getAttribute("library_id");
String msg=(String)request.getAttribute("msg");
String msg1=(String)request.getAttribute("msg1");
List list1=(List)session.getAttribute("list1");
List list2=(List)session.getAttribute("list2");
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
function search1() {

    var keyValue = document.getElementById('emptype_id').options[document.getElementById('emptype_id').selectedIndex].value;

if (keyValue=="Select")
    {


               document.getElementById('emptype_id').focus();
               document.getElementById('subemptype_id').options.length = 0;
             newOpt = document.getElementById('subemptype_id').appendChild(document.createElement('option'));
            newOpt.value ="Select";
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

req.open("POST","<%=request.getContextPath()%>/searchsubemp.do", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send("getEmpType_Id="+keyValue);


}
return true;
}
}

function update(cartXML)
{
var depts = cartXML.getElementsByTagName("emp_ids")[0];
var em = depts.getElementsByTagName("emp_id");
var em1 = depts.getElementsByTagName("emp_name");

        var newOpt =document.getElementById('subemptype_id').appendChild(document.createElement('option'));
        document.getElementById('subemptype_id').options.length = 0;
            newOpt = document.getElementById('subemptype_id').appendChild(document.createElement('option'));
            newOpt.value ="Select";
                newOpt.text = "Select";

for (var i = 0; i < em.length ; i++)
{
var ndValue = em[i].firstChild.nodeValue;
var ndValue1=em1[i].firstChild.nodeValue;
newOpt = document.getElementById('subemptype_id').appendChild(document.createElement('option'));
newOpt.value = ndValue;
newOpt.text = ndValue1;


}

}

</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LibMS : Manage Book Category </title>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
 <script language="javascript" type="text/javascript">


function check1()
{

    var emptype = document.getElementById('emptype_id').options[document.getElementById('emptype_id').selectedIndex].value;
   
var subemp = document.getElementById('subemptype_id').options[document.getElementById('subemptype_id').selectedIndex].value;
var book_type = document.getElementById('book_type').options[document.getElementById('book_type').selectedIndex].value;

    if(emptype=="Select")
    {
        alert("Select Emp Type");

        document.getElementById('emptype_id').focus();

        return false;
    }

    if(subemp=="Select")
    {
        alert("Select SubEmp Type");

        document.getElementById('subemptype_id').focus();

        return false;
    }

  if(book_type=="Select")
    {
        alert("Select Document Category Type");

        document.getElementById('book_type').focus();

        return false;
    }


return true;
  }





  function quit()
  {

      window.location="<%=request.getContextPath()%>/admin/main.jsp";
      return false;
  }


    </script>
</head>
<body onload="search1();">

    <html:form method="post" onsubmit="return check1()" action="/bookcategorydecide">

<div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

    <table border="1" class="table" width="400px" height="200px" align="center">


                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Configure Fine Detail</td></tr>
                <tr><td valign="top" align="center"> <br/>
                        <table >
                            <tr><td width="150px" align="center" colspan="2">Select MemberType </td>
                                <td width="150px" align="center"> <input type="submit" class="btn" id="Button1" name="button" value="Add" />
                                </td>
                            </tr>
                            <tr><td align="center" colspan="2" >
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      <html:select  property="emptype_id" style="width:160px" styleId="emptype_id" tabindex="3"  onchange="return search1();">
                          <html:option value="Select">Select</html:option>
                          <html:options  collection="list1" property="id.emptypeId" labelProperty="emptypeFullName"></html:options>
                     </html:select>


               </td><td width="150px" align="center"><input type="submit" id="Button2" class="btn" name="button" value="Update"  /></td> </tr>
                            <tr><td width="150px" align="center" colspan="2">
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    Select SubMember Type </td><td width="150px" align="center"><input type="submit" id="Button4" name="button" value="Delete" class="btn" /></td><tr><td align="center" colspan="2">
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                                                                        <html:select  property="sub_emptype_id" styleId="subemptype_id" style="width:160px"  tabindex="3">
                                                                            
                     </html:select>


                                </td> <td width="150px" align="center"><input type="submit" id="Button3" name="button" value="View" class="btn"  onclick="return check1();" /></td> </tr>
                            <tr><td  class="txt2" align="center" colspan="2">
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    Select Document Category
                    <br/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                     <html:select  property="book_type" style="width:160px" styleId="book_type" tabindex="3"  >
                          <html:option value="Select">Select</html:option>
                          <html:options  collection="list3" property="id.documentCategoryId" labelProperty="documentCategoryName"></html:options>
                     </html:select>
                        </td>
                        <td align="center"><input type="button" id="Button5" name="button" value="Back" class="btn" onclick="return quit()"/></td>
                    </tr>
                   
                   


                </table>


    <input type="hidden" name="library_id" value="<%=library_id%>">


</td></tr>
                <tr><td>

    <br>
     <%
          if (msg!=null)
          {
        %>


        <p class="mess">  <%=msg%></p>

        <%
        }
        %>
         <%if (msg1!=null)
          {
        %>


        <p class="err">  <%=msg1%></p>

        <%
        }
        %>






                    </td></tr></table>
        </div>

</html:form>

</body>

      
</html>
