<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>
<html><head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Search by Accession Number...</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/helpdemo.js"></script>
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
    window.status="Press F1 for Help";

    var keyValue = document.getElementById('CMBLib').options[document.getElementById('CMBLib').selectedIndex].value;

if (keyValue=="all")
    {


               document.getElementById('CMBLib').focus();
               document.getElementById('SubLibrary').options.length = 0;
            newOpt = document.getElementById('SubLibrary').appendChild(document.createElement('option'));
                newOpt.value ="all";
                newOpt.text = "All";



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

        var newOpt =document.getElementById('SubLibrary').appendChild(document.createElement('option'));
        document.getElementById('SubLibrary').options.length = 0;
 newOpt = document.getElementById('SubLibrary').appendChild(document.createElement('option'));
                newOpt.value ="all";
                newOpt.text = "All";
for (var i = 0; i < em.length ; i++)
{
var ndValue = em[i].firstChild.nodeValue;
var ndValue1=em1[i].firstChild.nodeValue;
newOpt = document.getElementById('SubLibrary').appendChild(document.createElement('option'));
newOpt.value = ndValue;
newOpt.text = ndValue1;


}

}

</script>
<script language="javascript">

function funcSearch()
{
    document.Form1.action="accession.do";
   document.Form1.method="post";
    document.Form1.submit();
}
loadHelp()
{
    window.status="Press F1 for Help";
}

</script>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
%>
<%
 String lib_id = (String)session.getAttribute("library_id");
  String sublib_id = (String)session.getAttribute("memsublib");
        if(sublib_id==null)sublib_id= (String)session.getAttribute("sublibrary_id");
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
session.setAttribute("page_name", "accessionno");
    %>



    </head><body style="background-color:#e0e8f5;margin: 0px 0px 0px 0px" onload="search();">
  


    <html:form  method="post" action="/OPAC/accession" target="f1" styleId="Form1" >
        <table align="center" dir="<%=rtl%>" width="80%" class="datagrid" height="400px"  style="border:solid 1px black;" >


  <tr class="header"><td  width="80%" dir="<%=rtl%>"  height="28px" align="center" colspan="2">


		<%=resource.getString("opac.accessionno.accessionno")%>




        </td></tr>
   <tr style="background-color:#e0e8f5;"><td width="800px" rowspan="2" dir="<%=rtl%>">
          <table>
              <tr><td dir="<%=rtl%>" valign="top"><%=resource.getString("opac.accessionno.enteraccessionno")%><br/>
                </td><td valign="top">
                      <input id="TXTKEY" name="TXTKEY" dir="<%=rtl%>" type="text" onfocus="statwords('Enter Accession Number')" onblur="loadHelp()">
                     *Case Sensentive
<input id="TXTPAGE" value="accessionno" name="TXTPAGE" type="hidden">

<input type="submit" id="Button1" name="go" dir="<%=rtl%>" value="<%=resource.getString("opac.accessionno.go")%>" class="buttonhome" />

<input type="reset" id="Button2" name="" value="<%=resource.getString("opac.browse.clear")%>" class="buttonhome">

  
<td align="<%=align%>" dir="<%=rtl%>">

                  


                  </td></tr>




          </table>
       </td><td class="header" dir="<%=rtl%>">
           <%=resource.getString("opac.simplesearch.restrictedby")%>

       </td>

    </tr>
    <tr style="background-color:#e0e8f5;" dir="<%=rtl%>">
          <td    align="<%=align%>" dir="<%=rtl%>" style="border: solid 1px black;">
              <table >
              <tr><td dir="<%=rtl%>"><%=resource.getString("opac.accessionno.library")%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                      <html:select property="CMBLib" dir="<%=rtl%>" tabindex="3" styleClass="selecthome" value="<%=lib_id%>" styleId="CMBLib" onchange="search()">
    <html:option value="all">All</html:option>
    <html:options collection="libRs" property="libraryId" labelProperty="libraryName"/>
 </html:select>

     </td></tr><tr><td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.sublibrary")%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      <html:select property="CMBSUBLib" styleClass="selecthome" dir="<%=rtl%>" value="<%=sublib_id%>"  styleId="SubLibrary" >
                           </html:select></td>


              </tr></table></td>

    <tr ><td dir="<%=rtl%>"  height="500px" valign="top" colspan="2" >
<hr/>
             <IFRAME  name="f1" style="background-color:#e0e8f5;" src="#" frameborder=0 height="100%" width="100%" scrolling="no"  id="f1"></IFRAME>


      </td></tr>


        </table>
    </html:form>


    </body></html>