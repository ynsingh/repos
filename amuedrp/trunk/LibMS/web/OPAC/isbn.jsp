<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>
<html><head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Faraz Hasan" content="MCA,AMU">
<title>Search by ISBN Number...</title>
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
<script type="text/javascript">
   function DisBox()
{
if(document.getElementById('checkboxId').checked)
{
document.getElementById("checkbox").value="Checked";
}
else{
    document.getElementById("checkbox").value="Unchecked";
}
}
</script>
<script type="text/javascript" src="https://www.google.com/jsapi?key=ABQIAAAApEiKekYWqFpDa_PStAFTMBRxcC-Fn9tK14QS9YKtPPoXy5_dfhQr8n6mPjQbdLIjMkUpUDQ7khVrfQ">
        </script>
        <script type="text/javascript">
      // Load the Google Transliterate API
      google.load("elements", "1", {
            packages: "transliteration"
          });

      var transliterationControl;
      function onLoad() {
        var options = {
            sourceLanguage: 'en',
            destinationLanguage: ['ar','hi','kn','ml','ta','te'],
            transliterationEnabled: true,
            shortcutKey: 'ctrl+g'
        };
        // Create an instance on TransliterationControl with the required
        // options.
        transliterationControl =
          new google.elements.transliteration.TransliterationControl(options);

        // Enable transliteration in the textfields with the given ids.
        var ids = [];
        transliterationControl.makeTransliteratable(ids);

        // Add the STATE_CHANGED event handler to correcly maintain the state
        // of the checkbox.
        transliterationControl.addEventListener(
            google.elements.transliteration.TransliterationControl.EventType.STATE_CHANGED,
            transliterateStateChangeHandler);

        // Add the SERVER_UNREACHABLE event handler to display an error message
        // if unable to reach the server.
        transliterationControl.addEventListener(
            google.elements.transliteration.TransliterationControl.EventType.SERVER_UNREACHABLE,
            serverUnreachableHandler);

        // Add the SERVER_REACHABLE event handler to remove the error message
        // once the server becomes reachable.
        transliterationControl.addEventListener(
            google.elements.transliteration.TransliterationControl.EventType.SERVER_REACHABLE,
            serverReachableHandler);

        // Set the checkbox to the correct state.
        document.getElementById('checkboxId').checked =
          transliterationControl.isTransliterationEnabled();

        // Populate the language dropdown
        var destinationLanguage =
          transliterationControl.getLanguagePair().destinationLanguage;
        var languageSelect = document.getElementById('languageDropDown');
        var supportedDestinationLanguages =
          google.elements.transliteration.getDestinationLanguages(
            google.elements.transliteration.LanguageCode.ENGLISH);
        for (var lang in supportedDestinationLanguages) {
          var opt = document.createElement('option');
          opt.text = lang;
          opt.value = supportedDestinationLanguages[lang];
          if (destinationLanguage == opt.value) {
            opt.selected = true;
          }
          try {
            languageSelect.add(opt, null);
          } catch (ex) {
            languageSelect.add(opt);
          }
        }
      }

      // Handler for STATE_CHANGED event which makes sure checkbox status
      // reflects the transliteration enabled or disabled status.
      function transliterateStateChangeHandler(e) {
        document.getElementById('checkboxId').checked = e.transliterationEnabled;
      }

      // Handler for checkbox's click event.  Calls toggleTransliteration to toggle
      // the transliteration state.
      function checkboxClickHandler() {
          window.status="Press F1 for Help";
        transliterationControl.toggleTransliteration();
      }

      // Handler for dropdown option change event.  Calls setLanguagePair to
      // set the new language.
      function languageChangeHandler() {
                  var keyValue = document.getElementById('languageDropDown').options[document.getElementById('languageDropDown').selectedIndex].value;
              document.getElementById("language").value=keyValue;
        var dropdown = document.getElementById('languageDropDown');
        transliterationControl.setLanguagePair(
            google.elements.transliteration.LanguageCode.ENGLISH,
            dropdown.options[dropdown.selectedIndex].value);
      }

      // SERVER_UNREACHABLE event handler which displays the error message.
      function serverUnreachableHandler(e) {
        document.getElementById("errorDiv").innerHTML =
            "Transliteration Server unreachable";
      }

      // SERVER_UNREACHABLE event handler which clears the error message.
      function serverReachableHandler(e) {
        document.getElementById("errorDiv").innerHTML = "";
      }
      google.setOnLoadCallback(onLoad);
      function loadHelp()
      {
          window.status="Press F1 for Help";
      }

    </script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/keyboard/keyboard.js" charset="UTF-8"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/keyboard/keyboard_002.js" charset="UTF-8"></script>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/keyboard/keyboard.css"/>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/helpdemo.js"></script>
    </head>
<body onload="checkboxClickHandler();search();funcSearch();">

    <html:form  method="post" action="/OPAC/SearchByIsbn" target="f1" styleId="Form1" onsubmit="return validate();" acceptCharset="utf-8">
           <table align="<%=align%>" dir="<%=rtl%>" width="1200x" class="datagrid" height="400px"  style="border:solid 1px #e0e8f5;" >
  <tr class="header"><td  width="800px" dir="<%=rtl%>"  height="28px" align="center" colspan="2">


	<%=resource.getString("opac.isbn.isbnsearch")%>




        </td></tr>
  <tr dir="<%=rtl%>"><td>
    <div id='translControl'>
      <input type="checkbox" id="checkboxId" onclick="javascript:checkboxClickHandler();javascript:DisBox();javascript:languageChangeHandler();">
      <html:hidden property="checkbox" styleId="checkbox" name="SearchByIsbnActionForm"/>
      <%=resource.getString("cataloguing.catbiblioentry.selectlang")%>

      <select id="languageDropDown" onchange="javascript:languageChangeHandler()">
          
      </select>
   <html:hidden property="language" styleId="language" name="SearchByIsbnActionForm"/>
    </div>
      </td></tr>


   



   
      


  
   <tr style="background-color:#e0e8f5;"><td width="800px" rowspan="2" dir="<%=rtl%>">
          <table>
              <tr><td dir="<%=rtl%>"><%=resource.getString("opac.isbn.isbnsearch")%></td><td>
                      <input id="TXTKEY" name="TXTKEY" dir="<%=rtl%>" type="text"onfocus="statwords('Enter ISBN Number of the Book/Document')" onblur="loadHelp()">
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


    </body></html>