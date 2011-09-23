<%@page import="com.myapp.struts.hbm.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Simple Search...</title>
<%



        List libRs = (List)session.getAttribute("libRs");

        System.out.println(libRs.size());
        List sublib = (List)session.getAttribute("sublib");
        String lib_id = (String)session.getAttribute("library_id");
        String sublib_id =  (String)session.getAttribute("memsublib");
        if(sublib_id==null ||sublib_id.equals(""))
            sublib_id = (String)session.getAttribute("sublibrary_id");
        System.out.println("sublibId="+ sublib_id);
       // rs.beforeFirst();


    %>
<style type="text/css">

a:active
{
   color: #0000FF;
}
 .rows          { background-color: white;border: solid 1px blue; }
     .hiliterows    { background-color: pink; color: #000000; font-weight: bold;border: solid 1px blue; }
     .alternaterows { background-color: #efefef; }
     .header        { background-color: #c0003b; color: #FFFFFF;font-family:Tahoma;font-size: 12px;font-weight: bold;text-decoration: none;padding-left: 10px; }

     .datagrid      {  font-family: arial; font-size: 9pt;
	    font-weight: normal;}
     .item{ padding-left: 10px;}

</style>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;align="left";}
    else{ rtl="RTL";page=false;align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
<link rel="stylesheet" href="/LibMS/css/page.css"/>
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

}

</script>
<SCRIPT language="JAVASCRIPT">
    function f()
    {
        if(document.getElementById('CMBYR').value=="upto" || document.getElementById('CMBYR').value=="after")
         {
            document.getElementById('TXTYR1').disabled=false;
            document.getElementById('TXTYR1').style.backgroundColor = "#ffffff";
            document.getElementById('TXTYR2').disabled=true;
            document.getElementById('TXTYR2').style.backgroundColor = "#eeeeee";

	   }
           if(document.getElementById('CMBYR').value=="all")
         {
             document.getElementById('TXTYR1').disabled=true;
            document.getElementById('TXTYR1').style.backgroundColor = "#eeeeee";
            document.getElementById('TXTYR2').disabled=true;
            document.getElementById('TXTYR2').style.backgroundColor = "#eeeeee";

	   }
        if(document.getElementById('CMBYR').value=="between")
         {
            document.getElementById('TXTYR1').disabled=false;
            document.getElementById('TXTYR1').style.backgroundColor = "#ffffff";
            document.getElementById('TXTYR2').disabled=false;
            document.getElementById('TXTYR2').style.backgroundColor = "#ffffff";

	   }


    }
    function validate()
    {
        if(document.getElementById('CMBYR').value=="between")
            {
                if((document.getElementById('TXTYR1').value=="") || (document.getElementById('TXTYR2').value==""))
                {
                    alert("Please specify year");
                    return false;
                }
                
            }
          if((document.getElementById('CMBYR').value=="upto")||(document.getElementById('CMBYR').value=="after"))
          {
              if(document.getElementById('TXTYR1').value==""){
                  alert("Please specify year");
                  return false;}
          }
               return true;
    }
</SCRIPT>
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
        var ids = [ "TXTPHRASE","TXTYR1","TXTYR2"];
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
</head>
<body onload="checkboxClickHandler();search();">
    <html:form  method="post" action="/simple_search"  onsubmit="return validate();">
        <table  align="<%=align%>" dir="<%=rtl%>" width="100%" class="datagrid"  style="border:solid 1px #e0e8f5;">
  <tr class="header"><td  width="90%" dir="<%=rtl%>"  align="center" colspan="2">
		<%=resource.getString("opac.simplesearch.smpsearch")%>
</td></tr>
    <tr dir="<%=rtl%>"><td>
   <div id='translControl'>
     
      <html:hidden property="checkbox" styleId="checkbox" name="SimpleSearchActionForm"/>
      <input type="checkbox" id="checkboxId" onclick="javascript:checkboxClickHandler();javascript:DisBox();javascript:languageChangeHandler()">
       
      <%=resource.getString("cataloguing.catbiblioentry.selectlang")%>

    
      <select id="languageDropDown" onchange="javascript:languageChangeHandler()"></select>
    
      <html:hidden property="language" styleId="language" name="SimpleSearchActionForm"/>
    </div>
      </td></tr>
  <tr style="background-color:#e0e8f5;"><td  dir="<%=rtl%>" >
          <table dir="<%=rtl%>">
              <tr><td dir="<%=rtl%>" ><%=resource.getString("opac.simplesearch.enterwordorphrase")%></td><td><input type="text" dir="<%=rtl%>" id="TXTPHRASE" name="TXTPHRASE" class="keyboardInput" onfocus="statwords('Enter Author/Title/Publisher of the book')" onblur="loadHelp()" ></td></tr>
              <tr>   <td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.connectwordas")%></td><td><select name="CMBCONN" dir="<%=rtl%>" size="1" id="CMBCONN">
<option selected dir="<%=rtl%>" value="or"><%=resource.getString("opac.simplesearch.or")%></option>
<option dir="<%=rtl%>" value="and"><%=resource.getString("opac.simplesearch.and")%></option>
</select></td>
      </tr>
          </table>
      </td>
      <td    align="<%=align%>" dir="<%=rtl%>" valign="top">
          <table >
              <tr><td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.field")%> </td><td rowspan="2" dir="<%=rtl%>" valign="top">
          <select name="CMBFIELD" size="1" id="CMBFIELD" dir="<%=rtl%>">
<option value="any field" selected dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.anyfld")%></option>
<option  value="mainEntry" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.auth")%></option>
<option value="title" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.tit")%></option>
<option value="publisherName" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.pub")%></option>
</select>
     </td>
              </tr></table></td></tr>
  <tr class="header"><td width="1000px" dir="<%=rtl%>"  align="<%=align%>" >&nbsp;<%=resource.getString("opac.simplesearch.restrictedby")%></td><td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.sortby")%></td></tr>
   <tr style="background-color:#e0e8f5;"><td   dir="<%=rtl%>" align="<%=align%>">
           <table   ><tr><td dir="<%=rtl%>" align="<%=align%>">
          <table>
             
              <tr>   <td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.library")%></td><td>
    
                      <html:select property="CMBLib" dir="<%=rtl%>"  value="<%=lib_id%>"   styleId="CMBLib" onchange="search()">
                          <html:option value="all">All</html:option>
     <html:options collection="libRs" property="libraryId"  labelProperty="libraryName"/>
 </html:select>
</td>
</tr>
 <tr><td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.database")%></td><td>
                      <select name="CMBDB" dir="<%=rtl%>" size="1" id="CMBDB">
<option value="combined" dir="<%=rtl%>" selected><%=resource.getString("opac.simplesearch.combnd")%></option>
    <option value="book" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.books")%></option>
    <option value="cd" dir="<%=rtl%>">CDs</option>

</select>
                  </td></tr>
          </table>
                   </td><td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.sublibrary")%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                       <html:select property="CMBSUBLib" dir="<%=rtl%>"  styleId="SubLibary" value="<%=sublib_id%>">
                         
                         <%--  <html:options collection="sublib" property="id.sublibraryId" labelProperty="sublibName" />--%>
                       </html:select>
                       <table>
                           <tr><td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.pubyear")%></td><td rowspan="4" dir="<%=rtl%>"></td>
                               <td><select name="CMBYR" dir="<%=rtl%>" onChange="f()" size="1" id="CMBYR" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
                                    <option selected value="all" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.allyear")%></option>
                                    <option value="between" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.between")%></option>
                                    <option value="upto" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.upto")%></option>
                                    <option value="after" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.after")%></option>
                                </select>
                               </td>
                               <td>
                                   <input type="text" dir="<%=rtl%>" id="TXTYR1"  name="TXTYR1" align="<%=align%>" disabled="true" style="width:50px" onfocus="statwords('Enter Year')" onblur="loadHelp()" >
                               </td>
<td>
    <input type="text" id="TXTYR2"  align="<%=align%>" name="TXTYR2" dir="<%=rtl%>" disabled="true" style="width:50px" onfocus="statwords('Enter Year')"  onblur="loadHelp()" >
</td></tr>
 </table>
 </td></tr></table>
      </td>
      <td align="<%=align%>" dir="<%=rtl%>">
           <table>
                           <tr><td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.field1")%></td><td> <select name="CMBSORT" size="1" dir="<%=rtl%>" id="CMBSORT">
<option  value="mainEntry" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.auth")%></option>
<option value="title" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.tit")%></option>
<option value="isbn10" dir="<%=rtl%>">ISBN</option>
<option value="publisher_name" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.pub")%></option>
</select></td>
                           </tr></table>
      </td>     
  </tr>
  <tr><td>
          <input type="submit" dir="<%=rtl%>" id="Button1" class="btn" name="" value="<%=resource.getString("opac.simplesearch.find")%>" >
<input type="reset" dir="<%=rtl%>" id="Button2" class="btn" name="" value="<%=resource.getString("opac.browse.clear")%>">
      </td></tr>
       <tr class="header" dir="<%=rtl%>">
                               <td colspan="2" dir="<%=rtl%>">
                            <a dir="<%=rtl%>" name="tips">&nbsp;<%=resource.getString("opac.simplesearch.searchtip")%></a>
                            <table class="datagrid" dir="<%=rtl%>" style="background-color:#e0e8f5;color:black" halign="right" border="0" cellpadding="2" cellspacing="0" width="100%" frame="hspaces" height="38" rules="rows">
    <colgroup width="15%"></colgroup><colgroup width="1%"></colgroup><colgroup width="90%"></colgroup>
    <tbody><tr>
    <th colspan="3" class="tipstext" dir="<%=rtl%>">
        <%=resource.getString("opac.simplesearch.t1")%>    	
    </th>
    </tr>
    <tr>
        <td class="txt2" dir="<%=rtl%>">
    		 <%=resource.getString("opac.simplesearch.t2")%>
    </td>
    <td class="tipsheading" dir="<%=rtl%>">:</td>
    <td class="tipstext" dir="<%=rtl%>">
    	<%=resource.getString("opac.simplesearch.t3")%>	 
    </td>
    </tr>
    <tr valign="top" dir="<%=rtl%>">
    	<td class="txt2" dir="<%=rtl%>">
    		<%=resource.getString("opac.simplesearch.t4")%>
   	</td>
   	<td class="tipsheading" dir="<%=rtl%>">:</td>
   	<td class="tipstext" dir="<%=rtl%>">
          <%=resource.getString("opac.simplesearch.t5")%>
    	</td>
    </tr>
    <tr valign="top" dir="<%=rtl%>">
    	<td class="txt2" dir="<%=rtl%>">
    	  <%=resource.getString("opac.simplesearch.t6")%>
    	</td>
    	<td class="tipsheading" dir="<%=rtl%>">:</td>
    	<td class="tipstext" dir="<%=rtl%>">
    	 <%=resource.getString("opac.simplesearch.t7")%>	
    	</td>
    </tr>
    <tr valign="top" dir="<%=rtl%>">
    	<td class="txt2" dir="<%=rtl%>">
    	 <%=resource.getString("opac.simplesearch.t8")%>
    	</td>
    	<td class="tipsheading" dir="<%=rtl%>">:</td>
    	<td class="tipstext" dir="<%=rtl%>">
    	 <%=resource.getString("opac.simplesearch.t9")%>	
    	</td>
    </tr>
     <tr valign="top" dir="<%=rtl%>">
    	<td class="txt2" nowrap1="" dir="<%=rtl%>">
    		<%=resource.getString("opac.simplesearch.t10")%>
    	</td>
    	<td class="tipsheading" dir="<%=rtl%>">:</td>
    	<td class="tipstext" dir="<%=rtl%>">
    	 <%=resource.getString("opac.simplesearch.t11")%> 
    	</td>
    </tr>
    <tr valign="top" dir="<%=rtl%>">
    	<td class="txt2" nowrap1="" dir="<%=rtl%>">
    	 <%=resource.getString("opac.simplesearch.t12")%>
    	</td>
    	<td class="tipsheading" dir="<%=rtl%>">:</td>
    	<td class="tipstext" dir="<%=rtl%>">
    	 <%=resource.getString("opac.simplesearch.t13")%>
    	</td>
    </tr>
    <tr valign="top" dir="<%=rtl%>">
    	<td class="txt2" nowrap1="" dir="<%=rtl%>">
    	  <%=resource.getString("opac.simplesearch.t14")%>
    	</td>
    	<td class="tipsheading" dir="<%=rtl%>">:</td>
    	<td class="tipstext" dir="<%=rtl%>">
    	  <%=resource.getString("opac.simplesearch.t15")%>
    	</td>
    </tr>
   <tr valign="top" dir="<%=rtl%>">
   	<td class="txt2" align="right" dir="<%=rtl%>">
   	  <%=resource.getString("opac.simplesearch.t16")%>
    	</td>
    	<td colspan="2" class="txt2" dir="<%=rtl%>">
    	  <%=resource.getString("opac.simplesearch.t17")%> 	
    	</td>
   </tr></tbody></table>
                               </td></tr>
       </table>    
    </html:form>
</body>
</html>
