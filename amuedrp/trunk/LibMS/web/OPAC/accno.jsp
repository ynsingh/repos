<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>
<html><head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Faraz Hasan" content="MCA,AMU">
<title>Search by Accession Number...</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
th a:link      { text-decoration: none; color: black }
     th a:visited   { text-decoration: none; color: black }
     .rows          { background-color: white;border: solid 1px blue; }
     .hiliterows    { background-color: pink; color: #000000; font-weight: bold;border: solid 1px blue; }
     .alternaterows { background-color: #efefef; }
     .header        { background-color: #c0003b; color: #FFFFFF;font-weight: bold;text-decoration: none;padding-left: 10px; }

     .datagrid      {  font-family: arial; font-size: 9pt;
	    font-weight: normal;}
     .item{ padding-left: 10px;}
</style>
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
function fun()
{
document.Form1.action="/accession.do";
document.Form1.method="post";
document.Form1.target="f1";
document.Form1.submit();
}
function funcSearch()
{
    document.Form1.action="accession.do";
   document.Form1.method="post";
    document.Form1.submit();
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



    </head><body onload="search();">
  <%--  <%if(page.equals(true)){%>--%>


    <html:form  method="post" action="/OPAC/accession" target="f1" styleId="Form1" >
        <table align="<%=align%>" dir="<%=rtl%>" width="1200x" class="datagrid" height="400px"  style="border:solid 1px #e0e8f5;" >


  <tr class="header"><td  width="800px" dir="<%=rtl%>"  height="28px" align="center" colspan="2">


		<%=resource.getString("opac.accessionno.accessionno")%>




        </td></tr>
   <tr style="background-color:#e0e8f5;"><td width="800px" rowspan="2" dir="<%=rtl%>">
          <table>
              <tr><td dir="<%=rtl%>"><%=resource.getString("opac.accessionno.enteraccessionno")%></td><td>
                     <input id="TXTKEY" name="TXTKEY" dir="<%=rtl%>" type="text">
<input id="TXTPAGE" value="accessionno" name="TXTPAGE" type="hidden">

<td align="<%=align%>" dir="<%=rtl%>">

                  


                  </td></tr>




          </table>
       </td><td class="header" dir="<%=rtl%>">
           <%=resource.getString("opac.simplesearch.restrictedby")%>

       </td>

    </tr>
    <tr style="background-color:#e0e8f5;" dir="<%=rtl%>">
          <td    align="<%=align%>" dir="<%=rtl%>">
          <table>
              <tr><td dir="<%=rtl%>"><%=resource.getString("opac.accessionno.library")%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<html:select property="CMBLib" dir="<%=rtl%>" tabindex="3"  value="<%=lib_id%>" styleId="CMBLib" onchange="search()">
    <html:option value="all">All</html:option>
    <html:options collection="libRs" property="libraryId" labelProperty="libraryName"/>
 </html:select>

     </td></tr><tr><td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.sublibrary")%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      <html:select property="CMBSUBLib"  dir="<%=rtl%>" value="<%=sublib_id%>"  styleId="SubLibrary" >
                      <%--     <html:option value="all">All</html:option>
                              <html:options collection="sublib" property="id.sublibraryId" labelProperty="sublibName" />
                --%>       </html:select></td>


              </tr></table></td>

    </tr>

    <tr><td>


<input type="submit" id="Button1" name="go" dir="<%=rtl%>" value="<%=resource.getString("opac.accessionno.go")%>" class="btn" />

<input type="reset" id="Button2" name="" value="<%=resource.getString("opac.browse.clear")%>" class="btn">


<script>
    function back()
    {
        window.location="<%=request.getContextPath()%>/OPAC/OPACmain.jsp";

    }
    </script>
      </td></tr>
 <tr style="background-color:#e0e8f5;"><td dir="<%=rtl%>"  height="400px" valign="top" colspan="2" >

             <IFRAME  name="f1" style="background-color:#e0e8f5;" src="#" frameborder=0 height="400px" width="1200px" scrolling="no"  id="f1"></IFRAME>


      </td></tr>


        </table>
    </html:form>

<%--
<%}else{%>

    <form  method="post" action="accession.do" target="f1" name="Form1" >
          <table align="left" width="1200x" class="datagrid" height="400px" class="datagrid"  style="border:solid 1px #e0e8f5;">


  <tr class="header"><td  width="800px"  height="28px" align="center" colspan="2">


		Accession No Search




        </td></tr>
   <tr style="background-color:#e0e8f5;"><td class="header">
           Restricted By

       </td><td width="800px" rowspan="2" align="right">
          <table class="datagrid">
              <tr><td>
                     <input id="TXTKEY" name="TXTKEY" type="text">
<input id="TXTPAGE" value="accessionno" name="TXTPAGE" type="hidden">




                  </td><td><%=resource.getString("opac.accessionno.enteraccessionno")%></td></tr>




          </table>
       </td>

    </tr>
    <tr style="background-color:#e0e8f5;" >
          <td    align="left">
          <table class="datagrid">
              <tr><td><%=resource.getString("opac.accessionno.library")%></td><td  valign="top">

 
        

     </td>

              </tr></table></td>

    </tr>

    <tr><td>


<input type="submit" id="Button1" name="go"  value="<%=resource.getString("opac.accessionno.go")%>" />

<input type="reset" id="Button2" name="" value="<%=resource.getString("opac.simplesearch.clear")%>">


<script>
    function back()
    {
        window.location="/OPAC/OPACmain.jsp";

    }
    </script>
      </td></tr>
 <tr style="background-color:#e0e8f5;"><td  height="400px" valign="top" colspan="2" >

             <IFRAME  name="f1" style="background-color:#e0e8f5;" src="#" frameborder=0 height="400px" width="1200px" scrolling="no"  id="f1"></IFRAME>


      </td></tr>

        </table>
    </form>



<%}%>--%>

    </body></html>