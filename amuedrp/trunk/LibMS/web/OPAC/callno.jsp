<!-- CALL NO SEARCH JSP-->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
 <%@page import="java.sql.ResultSet"%>
 <%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>

<html><head>
<title>Search by Call Number...</title>
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

   request.setCharacterEncoding("UTF-8");

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
var availableSelectList;
function search() {

     var acc=document.getElementById('TXTKEY').value;
        if(acc=="")
            {
                alert("Please Enter CALL No to Search Title");
                return false;

            }


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
fun();
}
function fun()
{
 var acc=document.getElementById('TXTKEY').value;
        if(acc=="")
            {
                alert("Please Enter CALL No to Search Title");
                return false;

            }

document.getElementById("Form1").action="SearchByCallNo.do";
document.getElementById("Form1").method="post";
document.getElementById("Form1").target="f1";
document.getElementById("Form1").submit();
}


   function DisBox()
{
if(document.getElementById('checkboxId5').checked)
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
        var ids = ["TXTKEY"];
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
        document.getElementById('checkboxId5').checked = e.transliterationEnabled;
      }

      // Handler for checkbox's click event.  Calls toggleTransliteration to toggle
      // the transliteration state.
      function checkboxClickHandler() {
          window.status="Press F1 for Help";
             if(document.getElementById('checkboxId5').checked==true)
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
</head>
<body onload="checkboxClickHandler();" style="background-color:#e0e8f5;margin: 0px 0px 0px 0px">
    <html:form  method="post" action="/OPAC/SearchByCallNo" onsubmit="return search();" target="f1" styleId="Form1">
    </head><body >
  
            <table align="center" dir="<%=rtl%>" width="100%" class="datagrid" height="400px"  style="border:solid 1px black;">


  <tr class="header" dir="<%=rtl%>"><td   dir="<%=rtl%>"  height="28px" align="center" colspan="3">


          <b dir="<%=rtl%>"><%=resource.getString("opac.callno.callno")%></b>




        </td></tr>

 <html:hidden property="checkbox" styleId="checkbox" name="CallNoSearchActionForm"/>
   <html:hidden property="language" styleId="language" name="CallNoSearchActionForm"/>
         <tr><td dir="<%=rtl%>" width="100%" >
                 <table>
                     <tr><td>

                      <%=resource.getString("opac.callno.entercallno")%></td><td>
        <input id="TXTKEY" dir="<%=rtl%>"  name="TXTKEY" type="text" onfocus="statwords('Enter Call Number')">
<input id="TXTPAGE" value="callno" dir="<%=rtl%>" name="TXTPAGE" type="hidden">
   <input type="button" onclick="return fun();" class="buttonhome" dir="<%=rtl%>" id="Button1" name="go" value="<%=resource.getString("opac.callno.go")%>" >

<input type="reset" class="buttonhome" id="Button2" dir="<%=rtl%>" name="" value="<%=resource.getString("opac.browse.clear")%>">

                  </td>

</tr>
        </table>
             </td><td>
                 <table>
                          <tr>
                  <td class="header" dir="<%=rtl%>" colspan="2">
           <%=resource.getString("opac.simplesearch.restrictedby")%>

       </td>

                          </tr>
                           <tr><td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.library")%> </td><td>

                      <html:select property="CMBLib" dir="<%=rtl%>"  styleClass="selecthome" tabindex="3"  value="<%=lib_id%>" styleId="CMBLib" onchange="search()">
     <html:option value="all">All</html:option>
    <html:options collection="libRs" property="libraryId" labelProperty="libraryName"/>
 </html:select>

     </td></tr>
              <tr><td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.sublibrary")%></td><td>
                      <html:select property="CMBSUBLib" styleClass="selecthome"  styleId="SubLibrary" value="<%=sublib_id%>" onchange="search();">
                          <html:option value="all">All</html:option>
                       </html:select></td>




     </tr>
                      </table>
             </td></tr>
        <tr>
            <td  dir="<%=rtl%>" height="700px" valign="top"  style="border:Solid 1px black;" colspan="3" >

             <IFRAME  name="f1" style="background-color:#e0e8f5;" src="#" frameborder=0 height="100%" width="100%" scrolling="yes"  id="f1"></IFRAME>


      </td>

        </tr>
    </table>
        
</html:form>



    </body></html>


