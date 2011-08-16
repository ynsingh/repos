

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%@page  import="java.util.*,java.io.*,java.net.*"%>

<html><head>
   <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Faraz Hasan" content="MCA,AMU">
<title>Search by ISBN...</title>
<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
.rows          { background-color: white;border: solid 1px blue; }
     .hiliterows    { background-color: pink; color: #000000; border: solid 1px blue; }
     .alternaterows { background-color: #efefef; }
     .header        { background-color: #c0003b; color: #FFFFFF;font-family:Tahoma;font-size: 12px;text-decoration: none;padding-left: 10px; }
  .header1        { font-family:Tahoma;font-size: 12px;text-decoration: none;padding-left: 10px; }
      .datagrid      {  font-family: arial; font-size: 9pt;
	    font-weight: normal;}
     .item{ padding-left: 10px;}
</style>
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

if (keyValue=="all")
    {


               document.getElementById('CMBLib').focus();
               document.getElementById('SubLibrary').options.length = 0;
                newOpt = document.getElementById('SubLibrary').appendChild(document.createElement('option'));
                newOpt.value = "all";
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
                newOpt.value = "all";
                newOpt.text = "All";
for (var i = 0; i < em.length ; i++)
{
var ndValue = em[i].firstChild.nodeValue;
var ndValue1=em1[i].firstChild.nodeValue;
newOpt = document.getElementById('SubLibrary').appendChild(document.createElement('option'));
newOpt.value = ndValue;
newOpt.text = ndValue1;


}
funcSearch();
}

</script>
<script language="javascript">

function funcSearch()
{
    document.getElementById("FORM1").action="/OPAC/SearchByIsbn.do";
    document.getElementById("Form1").method="post";
    
    document.getElementById("Form1").submit();
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
    List libRs = (List)session.getAttribute("libRs");
    List sublib = (List)session.getAttribute("sublib");
      
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


    </head><body onload="search();funcSearch();">
   <%-- <%if(page.equals(true)){%>--%>
    <html:form method="post" action="/OPAC/SearchByIsbn" target="f1" styleId="Form1">
        <table align="<%=align%>" dir="<%=rtl%>" width="1200x" height="400px" class="datagrid"  style="border:solid 1px #e0e8f5;">


  <tr class="header" dir="<%=rtl%>"><td dir="<%=rtl%>" width="800px"  height="28px" align="center" colspan="2">


		<%=resource.getString("opac.isbn.isbnsearch")%>




        </td></tr>
   <tr style="background-color:#e0e8f5;" dir="<%=rtl%>">
       <td width="800px" rowspan="2" dir="<%=rtl%>">
          <table class="datagrid" dir="<%=rtl%>">
              <tr><td ><%=resource.getString("opac.isbn.enterisbn")%></td><td>
                      <input id="TXTKEY" dir="<%=rtl%>" name="TXTKEY" type="text">
<input id="TXTPAGE" value="isbn"  name="TXTPAGE" dir="<%=rtl%>" type="hidden">


              
              </tr>




          </table>
       </td>
       <td class="header" dir="<%=rtl%>">
           <%=resource.getString("opac.simplesearch.restrictedby")%>

       </td>
        
    </tr>
    <tr style="background-color:#e0e8f5;" dir="<%=rtl%>">
        <td    align="left" colspan="1" dir="<%=rtl%>">
          <table class="datagrid" dir="<%=rtl%>">
              <tr >
                  <td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("opac.simplesearch.library")%> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<html:select property="CMBLib" dir="<%=rtl%>"  tabindex="3"  styleId="CMBLib" value="<%=lib_id%>" onchange="search()">
    <html:option value="all">All</html:option>
    <html:options collection="libRs" property="libraryId" labelProperty="libraryName"/>
 </html:select>

     </td></tr><tr><td  dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.sublibrary")%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      <html:select property="CMBSUBLib" value="<%=sublib_id%>" styleId="SubLibrary" onchange="funcSearch();">
                          <%--    <html:option value="all">All</html:option>
                              <html:options collection="sublib" property="id.sublibraryId" labelProperty="sublibName" />--%>
                       </html:select></td>
              </tr></table></td>

    </tr>

    <tr><td>


<input type="submit" id="Button1" dir="<%=rtl%>" class="btn" name="" value="<%=resource.getString("opac.simplesearch.find")%>">
<input type="reset" id="Button2" name="" dir="<%=rtl%>" class="btn" value="<%=resource.getString("opac.browse.clear")%>">



      </td></tr>
    <tr style="background-color:#e0e8f5;" dir="<%=rtl%>"><td  dir="<%=rtl%>" height="400px" valign="top" colspan="2" >

             <IFRAME  name="f1" style="background-color:#e0e8f5;" src="#" frameborder=0 height="400px" width="1200px" scrolling="no"  id="f1"></IFRAME>


      </td></tr>
      



        </table>



    </html:form>


    </body>

</html>