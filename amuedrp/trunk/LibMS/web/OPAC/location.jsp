<%@ page import="java.util.*,com.myapp.struts.hbm.*,java.text.*,java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
List sublib=(List)session.getAttribute("sublib");
String library_id=(String)session.getAttribute("library_id");
 String sublib_id = (String)session.getAttribute("memsublib");
        if(sublib_id==null)sublib_id= (String)session.getAttribute("sublibrary_id");
 List lib=(List)session.getAttribute("lib");
 
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

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
function search() {

    var keyValue = document.getElementById('CMBLib').options[document.getElementById('CMBLib').selectedIndex].value;

if (keyValue=="sel")
    {


               document.getElementById('CMBLib').focus();
               document.getElementById('SubLibary').options.length = 0;
newOpt = document.getElementById('SubLibary').appendChild(document.createElement('option'));
newOpt.value ="sel";
newOpt.text = "Select";

                fun();
		return false;
	}
else
    {
    keyValue = keyValue.replace(/^\s*|\s*$/g,"");
if (keyValue.length >= 1)
{

var req = newXMLHttpRequest();

req.onreadystatechange = getReadyStateHandler(req, update);

req.open("POST","<%=request.getContextPath()%>/sublibrary.do", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send("getSubLibrary_Id="+keyValue);


}
return true;
}
}

function update(cartXML)
{
var depts = cartXML.getElementsByTagName("sublibrary_ids")[0];
var em = depts.getElementsByTagName("sublibrary_id");
var em1 = depts.getElementsByTagName("sublibrary_name");

        var newOpt =document.getElementById('SubLibary').appendChild(document.createElement('option'));
        document.getElementById('SubLibary').options.length = 0;

for (var i = 0; i < em.length ; i++)
{
var ndValue = em[i].firstChild.nodeValue;
var ndValue1=em1[i].firstChild.nodeValue;
newOpt = document.getElementById('SubLibary').appendChild(document.createElement('option'));
newOpt.value = ndValue;
newOpt.text = ndValue1;


}
 fun();
}

</script>


<script language="javascript">
function fun()
{
    document.getElementById("form1").action = "<%= request.getContextPath()%>/Locationview.do";
    document.getElementById("form1").method="post";
    document.getElementById("form1").target="f4";
    document.getElementById("form1").submit();
}

</script>
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>

    </head>
    <body onload="fun()" style="background-color:#e0e8f5;" >
       <html:form method="post" action="/Locationview" target="f4" styleId="form1">
           <table dir="<%=rtl%>" align="center" width="80%" class="datagrid" style="border: solid 1px black">
               <tr><td class="header" align="center">Location</td></tr>
               <tr><td>
                       <table>
        <tr><td dir="<%=rtl%>"  align="<%=align%>"><%=resource.getString("opac.simplesearch.library")%></td>
            <td  dir="<%=rtl%>" align="<%=align%>">
                <html:select property="CMBLib" styleClass="selecthome" dir="<%=rtl%>" value="<%=library_id%>" tabindex="3"  styleId="CMBLib" onchange="search()">
                    <html:option value="sel">Select</html:option>
                    <html:options collection="lib" property="libraryId" labelProperty="libraryName"/>
             </html:select>

            </td>
            <td  align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.sublibrary")%></td>
             <td  dir="<%=rtl%>">
                 <html:select property="CMBSUBLib" styleClass="selecthome" dir="<%=rtl%>" value="<%=sublib_id%>"  styleId="SubLibary" onchange="fun()">
               <html:option value="sel">Select</html:option>
                     <html:options collection="sublib" property="id.sublibraryId" labelProperty="sublibName"  />
            </html:select>

             </td>
        </tr>
                       </table></td></tr>
        <tr><td></td></tr>
        <tr style="background-color:#e0e8f5;" dir="<%=rtl%>">


            <td  height="300px" valign="top" dir="<%=rtl%>">
             <IFRAME  src="<%=request.getContextPath()%>/OPAC/location_view.jsp" style="background-color:#e0e8f5;"  frameborder=0 height="300px" width="100%" scrolling="no" name="f4" id="f4"></IFRAME>
            

      </td>
     

</tr>
        </table>
       </html:form>



    </body>
</html>
