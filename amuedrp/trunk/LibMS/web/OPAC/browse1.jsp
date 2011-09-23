
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>
<title>Browsing.... </title>
<%!
    static Integer count=0;
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
%>
<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
  .rows          { background-color: white;border: solid 1px blue; }
     .hiliterows    { background-color: pink; color: #000000; font-weight: bold;border: solid 1px blue; }
     .alternaterows { background-color: #efefef; }
     .header        { background-color: #c0003b; color: #FFFFFF;font-family:Tahoma;font-size: 12px;font-weight: bold;text-decoration: none;padding-left: 10px; }

     .datagrid      {  font-family: arial; font-size: 9pt;
	    font-weight: normal;}
     .item{ padding-left: 10px;}

</style>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/helpdemo.js"></script>
<%
String library_id = (String)session.getAttribute("library_id");
String sublibrary_id = (String)session.getAttribute("memsublib");
     if(sublibrary_id==null)sublibrary_id=   (String)session.getAttribute("sublibrary_id");
if(sublibrary_id==null)sublibrary_id="all";
try{
    List libRs = (List)session.getAttribute("libRs");
    
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

 if(keyValue=="all"){

           document.getElementById('CMBLib').focus();
               document.getElementById('SubLibary').options.length = 0;
               newOpt = document.getElementById('SubLibary').appendChild(document.createElement('option'));
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

        var newOpt =document.getElementById('SubLibary').appendChild(document.createElement('option'));
        document.getElementById('SubLibary').options.length = 0;
   newOpt = document.getElementById('SubLibary').appendChild(document.createElement('option'));
                newOpt.value = "all";
                newOpt.text = "All";
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

document.getElementById("Form1").action="browse.do";
document.getElementById("Form1").method="post";
document.getElementById("Form1").target="f1";
document.getElementById("Form1").submit();
}


</script>
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
        var ids = ["TXTTITLE"];
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

    </script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/keyboard/keyboard.js" charset="UTF-8"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/keyboard/keyboard_002.js" charset="UTF-8"></script>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/keyboard/keyboard.css"/>
    </head>
<body onload="checkboxClickHandler();search();">
    
    <html:form action="/OPAC/browse"  styleId="Form1" target="f1" acceptCharset="utf-8">
   <table  align="<%=align%>" dir="<%=rtl%>" width="100%" class="datagrid" style="border:solid 1px #e0e8f5;">



  <tr class="header"><td  width="1000px" dir="<%=rtl%>"  height="28px" align="center" colspan="2">


	<%=resource.getString("opac.browse.browsesearch")%>	




        </td></tr>
    <tr dir="<%=rtl%>"><td>
    <div id='translControl'>
      <input type="checkbox" id="checkboxId" onclick="javascript:checkboxClickHandler();javascript:DisBox();javascript:languageChangeHandler();javascript:fun1()">
      <html:hidden property="checkbox" styleId="checkbox" name="browseSearchActionForm"/>
      <%=resource.getString("cataloguing.catbiblioentry.selectlang")%><select id="languageDropDown" onchange="javascript:languageChangeHandler()"></select>
    <html:hidden property="language" styleId="language" name="browseSearchActionForm"/>
    </div>
      </td></tr>
  <tr style="background-color:#e0e8f5;"><td width="800px" dir="<%=rtl%>" >
          <table>
              <tr><td dir="<%=rtl%>"><%=resource.getString("opac.browse.enterstartingkeyword")%></td><td><input  name="TXTTITLE" id="TXTTITLE" class="keyboardInput" type="text" dir="<%=rtl%>" onkeyup="fun()" onfocus="statwords('Enter Search Keyword')"></td></tr>
             

          </table>
      </td>
      <td dir="<%=rtl%>"   align="<%=align%>" valign="top">
          <table >
              <tr><td dir="<%=rtl%>"> <%=resource.getString("opac.simplesearch.field")%> </td><td rowspan="2" dir="<%=rtl%>" valign="top">


<select dir="<%=rtl%>" name="CMBFIELD" onChange="fun()" size="1">
<option value="any field" selected dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.anyfld")%></option>
<option value="mainEntry" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.auth")%></option>
<option value="title" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.tit")%></option>
<option value="publisherName" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.pub")%></option>

</select>
     </td>

              </tr></table></td></tr>
  <tr class="header"><td width="1000px" dir="<%=rtl%>"  align="<%=align%>" ><%=resource.getString("opac.simplesearch.restrictedby")%></td><td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.sortby")%></td></tr>
   <tr style="background-color:#e0e8f5;" dir="<%=rtl%>"> <td width="800px" dir="<%=rtl%>"  align="<%=align%>">
           <table  width="800px" dir="<%=rtl%>"><tr><td align="<%=align%>" dir="<%=rtl%>">
          <table>
              <tr><td dir="<%=rtl%>"><%=resource.getString("opac.browse.database")%></td><td>
                    <select name="CMBDB" dir="<%=rtl%>" onChange="fun1()" size="1">
<option selected value="combined" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.combnd")%></option>
    <option value="book" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.books")%></option>
     <option value="cd" dir="<%=rtl%>">CDs</option>
  
  
</select>
                  </td><td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.library")%></td>
                  <td>
                      <html:select property="CMBLib" dir="<%=rtl%>"  tabindex="3" value="<%=library_id%>"  styleId="CMBLib" onchange="search();fun()">
                           <html:option value="all">All</html:option>
                            <html:options collection="libRs" property="libraryId" labelProperty="libraryName"/>
                      </html:select>
                  </td>
                  <td align="left" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.sublibrary")%><html:select property="CMBSUBLib" dir="<%=rtl%>" value="<%=sublibrary_id%>" styleId="SubLibary" onchange="fun1()">
                          <%--<html:option value="all">All</html:option>--%>
                          <%--<html:options collection="sublib" property="id.sublibraryId" labelProperty="sublibName" />--%>
                       </html:select>



</tr>
            
          </table>
                   </td><td align="<%=align%>" dir="<%=rtl%>">
                      




                   </td></tr></table>
      </td>
      <td align="<%=align%>" dir="<%=rtl%>">
           <table>
                           <tr><td dir="<%=rtl%>"><%=resource.getString("opac.browse.title")%></td><td dir="<%=rtl%>"> <select name="CMBSORT" dir="<%=rtl%>" size="1" onChange="fun()" id="CMBSORT">
<option value="title" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.auth")%></option>
<option  value="mainEntry" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.field1")%></option>
<option value="isbn10" dir="<%=rtl%>">ISBN</option>
<option value="publisherName" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.pub")%></option>
</select>
</td>
                           </tr></table>


      </td>

  </tr>
  <tr><td>


<input type="submit" id="Button1" name="" dir="<%=rtl%>" class="btn" value="<%=resource.getString("opac.simplesearch.find")%>">
<input type="reset" id="Button2" name="" dir="<%=rtl%>" class="btn" value="<%=resource.getString("opac.browse.clear")%>">


      </td></tr>
 <tr><td  height="400px" valign="top" colspan="2"  dir="<%=rtl%>">

             <IFRAME  name="f1"  src="#" frameborder=0 height="400px" width="1200px" scrolling="no"  id="f1"></IFRAME>


      </td></tr>
      

       </table>
   

        

    


 </html:form>


 
</body>
</html>