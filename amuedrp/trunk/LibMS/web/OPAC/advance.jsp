<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>

<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Advance Search...</title>
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
   function setIframeHeight2(x) {
       iframe=document.getElementById('f1');
       iframe.height=x;


};

function funcSearch()
{
    
    document.getElementById("Form1").action="advance_search.do";
    document.getElementById("Form1").method="post";
    document.getElementById("Form1").target="f1";
    document.getElementById("Form1").submit();
}
function search() {

      
    var keyValue = document.getElementById('CMBLib').options[document.getElementById('CMBLib').selectedIndex].value;
if(keyValue=="all"){
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
}
     function f()
    {
        var x=document.getElementById('TXTPHRASE1').value;
             var y=document.getElementById('TXTPHRASE2').value;
              var z=document.getElementById('TXTPHRASE3').value;


        <%--if(x=='' && y=='' && z=='' )
            {
                alert("Please Enter KeyWord No to Search Title");
                return false;

            }--%>


        search();
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
          var x=document.getElementById('TXTPHRASE1').value;
             var y=document.getElementById('TXTPHRASE2').value;
              var z=document.getElementById('TXTPHRASE3').value;


        if(x=='' && y=='' && z=='' )
            {
                alert("Please Enter KeyWord No to Search Title");
                return false;

            }

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
   function DisBox()
{
if(document.getElementById('checkboxId3').checked)
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
        var ids = [ "TXTPHRASE1","TXTPHRASE2","TXTPHRASE3","TXTYR1","TXTYR2"];
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
        document.getElementById('checkboxId3').checked = e.transliterationEnabled;
      }

      // Handler for checkbox's click event.  Calls toggleTransliteration to toggle
      // the transliteration state.
      function checkboxClickHandler() {
          window.status="Press F1 for Help";
            if(document.getElementById('checkboxId3').checked==true)
        {
        document.getElementById('MLI').style.display = 'block';
        }
        else{
            document.getElementById('MLI').style.display = 'none';
            document.getElementById('TXTPHRASE1').style.textAlign = "left";
             document.getElementById('TXTPHRASE2').style.textAlign = "left";
              document.getElementById('TXTPHRASE3').style.textAlign = "left";
               document.getElementById('TXTYR1').style.textAlign = "left";
               document.getElementById('TXTYR2').style.textAlign = "left";
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
     
    </script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/keyboard/keyboard.js" charset="UTF-8"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/keyboard/keyboard_002.js" charset="UTF-8"></script>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/keyboard/keyboard.css"/>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/helpdemo.js"></script>
    </head>
    <jsp:include page="opacheader.jsp"></jsp:include>
<body onload="search();checkboxClickHandler();setIframeHeight();" style="margin: 0px 0px 0px 0px " >
    <html:form  method="post" action="/OPAC/advance_search" target="f1" onsubmit="return validate();" styleId="Form1"  acceptCharset="utf-8">
     <table  align="center" dir="<%=rtl%>" width="80%" class="datagrid"  style="border:dashed 1px cyan;">
  <tr class="header1" dir="<%=rtl%>"><td  width="100%" dir="<%=rtl%>" style="border-bottom: dashed 1px cyan " height="28px" align="center" colspan="2">
          <font color="red">  <%=resource.getString("opac.advance.advancesearchtext")%></font>
        </td></tr>
  
   <html:hidden property="language" styleId="language" name="AdvanceSearchActionForm"/>
  <html:hidden property="checkbox" styleId="checkbox" name="AdvanceSearchActionForm"/>
  <% if(net.equalsIgnoreCase("true")){%>
        <tr dir="<%=rtl%>"><td >
        <table><tr><td>
      <%=resource.getString("cataloguing.catbiblioentry.selectlang1")%> </td><td> <div id='translControl'>

         <input type="checkbox" id="checkboxId3"  onclick="javascript:DisBox();javascript:checkboxClickHandler();javascript:languageChangeHandler()">
        </div></td><td>
        <div id="MLI" style="visibility: block;" >
          <select id="languageDropDown" class="selecthome" onchange="javascript:languageChangeHandler()">
          </select>
        </div>
        </td></tr></table>

        </td></tr>
        <%}else{%>
         <tr><td  dir="<%=rtl%>" colspan="3">
                 Sorry Cannot Support MLI Entry because of unavaliblibility of Net
         </td>
        </tr>
        <%}%>
  <tr ><td  dir="<%=rtl%>" width="50%">
          <table   width="100%" dir="<%=rtl%>">
              <tr><td  dir="<%=rtl%>" with="20%"><%=resource.getString("opac.advance.seachkeyword")%></td><td colspan="2"><input name="TXTPHRASE1" id="TXTPHRASE1" class="keyboardInput"  type="text" dir="<%=rtl%>" onfocus="statwords('Enter Keyword')" onblur="loadHelp()">
</td></tr>
              <tr>   <td><%=resource.getString("opac.simplesearch.connectwordas")%></td><td> <select class="selecthome" name="CMB1" size="1" dir="<%=rtl%>">
    <option value="or" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.or")%></option>
    <option value="and" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.and")%></option>
  </select>        
                  </td><td align="<%=align%>" dir="<%=rtl%>">  <select class="selecthome" name="CMBF1" size="1">
<option value="or" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.or")%></option>
<option value="and" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.and")%></option>
</select></td>
     </tr>
          </table>
      </td>
      <td    align="<%=align%>" valign="top" dir="<%=rtl%>">
          <table  width="100%" >
              <tr><td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.field")%> </td><td align="<%=align%>" valign="top" dir="<%=rtl%>">
          <select name="CMBFIELD1" class="selecthome" size="1" dir="<%=rtl%>" >
<%--<option selected value="title" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.anyfld")%></option>--%>
<option value="author" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.auth")%></option>
<option value="title" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.tit")%></option>
<option value="isbn10" dir="<%=rtl%>">ISBN</option>
<option value="subject" dir="<%=rtl%>"><%=resource.getString("opac.advance.subject")%></option>
</select>
     </td>
              </tr></table></td></tr>
  <tr  dir="<%=rtl%>"><td  dir="<%=rtl%>" width="50%">
           <table  dir="<%=rtl%>" width="100%"  >
               <tr><td dir="<%=rtl%>"><%=resource.getString("opac.advance.seachkeyword")%></td><td dir="<%=rtl%>" colspan="2"> <input name="TXTPHRASE2" id="TXTPHRASE2" class="keyboardInput" dir="<%=rtl%>"  type="text" onfocus="statwords('Enter Keyword')" onblur="loadHelp()" >
</td></tr>
              <tr>   <td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.connectwordas")%></td><td> <select class="selecthome" name="CMB2" size="1" dir="<%=rtl%>">
    <option value="or" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.or")%></option>
    <option value="and" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.and")%></option>
                      </select></td><td align="<%=align%>" dir="<%=rtl%>">      <select name="CMBF2" class="selecthome" size="1" dir="<%=rtl%>">
<option value="or" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.or")%></option>
<option value="and" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.and")%></option>
</select>
</td>
      </tr>
          </table>
      </td>
      <td    align="<%=align%>" width="100%" valign="top" dir="<%=rtl%>">
          <table dir="<%=rtl%>" width="100%" >
              <tr><td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.anyfld")%> </td><td dir="<%=rtl%>" valign="top">
         <select name="CMBFIELD2" class="selecthome" size="1"  dir="<%=rtl%>">
        <%--<option selected value="author" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.anyfld")%></option>--%>
        <option value="author" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.auth")%></option>
        <option value="title" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.tit")%></option>
        <option value="isbn10" dir="<%=rtl%>">ISBN</option>
        <option value="subject" dir="<%=rtl%>"><%=resource.getString("opac.advance.subject")%></option>
        </select>
     </td>
              </tr></table></td></tr>
   <tr dir="<%=rtl%>"><td   dir="<%=rtl%>" width="50%" >
            <table  dir="<%=rtl%>" width="100%" >
                <tr><td  dir="<%=rtl%>"><%=resource.getString("opac.advance.seachkeyword")%></td><td dir="<%=rtl%>" colspan="2"><input name="TXTPHRASE3" id="TXTPHRASE3" class="keyboardInput" type="text" dir="<%=rtl%>"onfocus="statwords('Enter Keyword')" onblur="loadHelp()" >
</td></tr>
              <tr>   <td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.connectwordas")%></td><td dir="<%=rtl%>"> <select class="selecthome" name="CMB3" size="1" dir="<%=rtl%>">
         <option value="or" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.or")%></option>
        <option value="and" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.and")%></option>
        </select></td><td>    <select name="CMBF3" class="selecthome" size="1" dir="<%=rtl%>">
        <option value="or" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.or")%></option>
        <option value="and" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.and")%></option>
        </select>
        </td>
      </tr>
          </table>
      </td>
      <td dir="<%=rtl%>"   align="<%=align%>" valign="top" width="50%">
          <table dir="<%=rtl%>" width="100%" >
              <tr><td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.anyfld")%> </td><td dir="<%=rtl%>"  valign="top">
        <select name="CMBFIELD3" size="1" dir="<%=rtl%>" class="selecthome">
        <%--<option selected value="subject" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.anyfld")%></option>--%>
        <option value="author" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.auth")%></option>
        <option value="title" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.tit")%></option>
        <option value="isbn10" dir="<%=rtl%>">ISBN</option>
        <option value="subject" dir="<%=rtl%>"><%=resource.getString("opac.advance.subject")%></option>
        </select>
     </td>
              </tr></table></td></tr>
   <tr class="header1" dir="<%=rtl%>"><td  dir="<%=rtl%>"  align="<%=align%>" style="border-bottom: dashed 1px cyan " ><font color="red"> <%=resource.getString("opac.simplesearch.restrictedby")%></font></td><td align="<%=align%>"  style="border-bottom: dashed 1px cyan;color:red; " dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.sortby")%></td></tr>
   <tr dir="<%=rtl%>"><td  width="80%" dir="<%=rtl%>"  align="<%=align%>">
           <table width="100%"  dir="<%=rtl%>"><tr><td align="<%=align%>" dir="<%=rtl%>">
                       <table width="100%">
              <tr><td ><%=resource.getString("opac.simplesearch.database")%></td><td>
                      <select name="CMBDB" size="1" class="selecthome" id="CMBDB" dir="<%=rtl%>">
        <option value="combined" selected dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.combnd")%></option>
        <option value="book" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.books")%></option>
        <option value="cd" dir="<%=rtl%>">CD/DVD ROM</option>
     <option value="vd" dir="<%=rtl%>">Electronic Document(Video/Motion Pictures)</option>
     <option value="ppt" dir="<%=rtl%>">Electronic Document(Presenatation)</option>
     <option value="au" dir="<%=rtl%>">Electronic Document(Sound Recording)</option>
     <option value="th" dir="<%=rtl%>">Thesis</option>
     <option value="ds" dir="<%=rtl%>">Dissertations</option>
        </select>
                  </td></tr>
              <tr>   <td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.library")%></td><td>
                      <html:select property="CMBLib" dir="<%=rtl%>" styleClass="selecthome" value="<%=lib_id%>"  tabindex="3"  styleId="CMBLib" onchange="search()">
                          <html:option value="all">All</html:option>
     <html:options collection="libRs" property="libraryId" labelProperty="libraryName"/>
 </html:select>
</td>
<td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.sublibrary")%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                       <html:select property="CMBSUBLib" styleClass="selecthome" dir="<%=rtl%>" value="<%=sublib_id%>"  styleId="SubLibrary" >
                      <html:option value="all">All</html:option>
                       </html:select>
      </tr>
          </table>
                   </td><td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.pubyear")%>
                                   <br/>
                       <table>
                           <tr><td rowspan="4" dir="<%=rtl%>"></td><td><select class="selecthome" name="CMBYR" dir="<%=rtl%>" onChange=f() size="1" id="CMBYR" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">

<option value="all" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.allyear")%></option>
<option value="between" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.between")%></option>
<option value="upto" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.upto")%></option>
<option value="after" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.after")%></option>
</select></td><td dir="<%=rtl%>">
<input type="text" dir="<%=rtl%>" id="TXTYR1"  name="TXTYR1" style="width:50px" onfocus="statwords('Enter Year')" onblur="loadHelp()" ></td><td>
<input type="text" id="TXTYR2" dir="<%=rtl%>" name="TXTYR2" style="width:50px" onfocus="statwords('Enter Year')" onblur="loadHelp()" >
</td></tr>
</table>
</td></tr></table>
      </td>
      <td align="<%=align%>" dir="<%=rtl%>">
           <table dir="<%=rtl%>">
                           <tr dir="<%=rtl%>"><td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.field1")%></td><td> <select class="selecthome" name="CMBSORT" dir="<%=rtl%>" size="1" id="CMBSORT">
<option  value="author" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.auth")%></option>
<option value="title" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.tit")%></option>
<option value="subject" dir="<%=rtl%>">Subject</option>
<option value="isbn10" dir="<%=rtl%>">ISBN10</option>
</select></td>
                           </tr></table>
      </td>
  </tr>
  <tr dir="<%=rtl%>"><td dir="<%=rtl%>">
<input id="Button2" class="buttonhome" name="" dir="<%=rtl%>" value="<%=resource.getString("opac.simplesearch.find")%>"  type="submit">
<input id="Button1" name="" class="buttonhome" dir="<%=rtl%>" value="<%=resource.getString("opac.browse.clear")%>" type="reset">
      </td></tr>
  <tr ><td dir="<%=rtl%>"    valign="top" colspan="2" >

          <IFRAME  name="f1" height="0px" src="#" frameborder=0  width="100%" scrolling="no"  id="f1"></IFRAME>


      </td></tr>

        
       </table>
   </html:form>

</body>
<jsp:include page="opacfooter.jsp"></jsp:include>
</html>
