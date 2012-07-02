<!--ISBN SEARCH JSP-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="java.sql.ResultSet"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Search by ISBN Number...</title>
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
String net=null;
try
{
      URL url = new URL("http://www.gmail.com");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      // just want to do an HTTP GET here
      connection.setRequestMethod("GET");
      // give it 15 seconds to respond
      connection.setReadTimeout(5*1000);
      connection.connect();
      net="true";
}
catch(Exception e)
{
    net="false";
}

    %>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
   <script type="text/javascript" src="https://www.google.com/jsapi?key=ABQIAAAApEiKekYWqFpDa_PStAFTMBRxcC-Fn9tK14QS9YKtPPoXy5_dfhQr8n6mPjQbdLIjMkUpUDQ7khVrfQ">
        </script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/ajax.js"></script>
<script language="javascript" type="text/javascript">
    //   reSize Iframe when ever child  calls  it
   function setIframeHeight() {
       iframe=document.getElementById('f1');

    if (iframe) {

        var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
        if (iframeWin.document.body) {
            iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
        }
    }
};
var availableSelectList;
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

function funcSearch()
{
     var acc=document.getElementById('TXTKEY').value;
        if(acc=="")
            {
                alert("Please Enter ISBN10 No to Search Title");
                return false;

            }
    document.getElementById("Form1").action="SearchByIsbn.do";
document.getElementById("Form1").method="post";
document.getElementById("Form1").target="f1";
document.getElementById("Form1").submit();
return true;
}

   function DisBox()
{
if(document.getElementById('checkboxId4').checked)
{
document.getElementById("checkbox").value="Checked";
}
else{
    document.getElementById("checkbox").value="Unchecked";
}
}
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
     //   var ids = ["TXTKEY"];
      //  transliterationControl.makeTransliteratable(ids);

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
       // document.getElementById('checkboxId').checked =
         // transliterationControl.isTransliterationEnabled();

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
        document.getElementById('checkboxId4').checked = e.transliterationEnabled;
      }

      // Handler for checkbox's click event.  Calls toggleTransliteration to toggle
      // the transliteration state.
      function checkboxClickHandler() {
          window.status="Press F1 for Help";

           if(document.getElementById('checkboxId4').checked==true)
        {
        document.getElementById('MLI').style.display = 'block';
        }
        else{
            document.getElementById('MLI').style.display = 'none';
            document.getElementById('TXTKEY').style.textAlign = "left";
        }

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
    <jsp:include page="opacheader.jsp"></jsp:include>
<body onload="search();checkboxClickHandler();setIframeHeight();" style="margin: 0px 0px 0px 0px;">

    <html:form  method="post" action="/OPAC/SearchByIsbn" target="f1" styleId="Form1" onsubmit="return search();" acceptCharset="utf-8">
       
        <table align="center" dir="<%=rtl%>" width="90%" class="datagrid" height="400px"  style="border:dashed 1px cyan;" >


  <tr class="header1"><td  width="100%" dir="<%=rtl%>"   align="center" colspan="2">


		<%=resource.getString("opac.isbn.isbnsearch")%>




        </td></tr>
  <tr ><td  rowspan="2" valign="top" dir="<%=rtl%>">
          <table>
              
               <html:hidden property="checkbox" styleId="checkbox" name="SearchByIsbnActionForm"/>
              <html:hidden property="language" styleId="language" name="SearchByIsbnActionForm"/>
              <tr><td dir="<%=rtl%>" valign="top"> <%=resource.getString("opac.isbn.isbnsearch")%><br/>
                </td><td valign="top">
                     <input id="TXTKEY" name="TXTKEY" dir="<%=rtl%>" type="text" onfocus="statwords('Enter ISBN Number of the Book/Document')" onblur="loadHelp()">
<input id="TXTPAGE" value="accessionno" name="TXTPAGE" type="hidden">

<input type="button" onclick="return funcSearch();" id="Button1" name="go" dir="<%=rtl%>" value="<%=resource.getString("opac.accessionno.go")%>" class="buttonhome" />

<input type="reset" id="Button2" name="" value="<%=resource.getString("opac.browse.clear")%>" class="buttonhome">


<td align="<%=align%>" dir="<%=rtl%>">




                  </td></tr>




          </table>
      </td><td class="header1" dir="<%=rtl%>" valign="top">
           <%=resource.getString("opac.simplesearch.restrictedby")%>

       </td>

    </tr>
    <tr  dir="<%=rtl%>">
          <td    align="<%=align%>" dir="<%=rtl%>" style="border: dashed 1px cyan;">
              <table >
              <tr><td dir="<%=rtl%>"><%=resource.getString("opac.accessionno.library")%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                      <html:select property="CMBLib" dir="<%=rtl%>" tabindex="3" styleClass="selecthome" value="<%=lib_id%>" styleId="CMBLib" onchange="return search()">
    <html:option value="all">All</html:option>
    <html:options collection="libRs" property="libraryId" labelProperty="libraryName"/>
 </html:select>

     </td></tr><tr><td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.sublibrary")%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      <html:select property="CMBSUBLib" styleClass="selecthome" dir="<%=rtl%>" onchange="return search();" value="<%=sublib_id%>"  styleId="SubLibrary" >
                          <html:option value="all">All</html:option>
                          </html:select></td>


              </tr></table></td>

    <tr ><td dir="<%=rtl%>"   valign="top" colspan="2" >

          <IFRAME  name="f1"  src="#" frameborder=0 height="0px" width="100%" scrolling="no"  id="f1"></IFRAME>


      </td></tr>


        </table>
    </html:form>


    </body>
<jsp:include page="opacfooter.jsp"></jsp:include>
</html>