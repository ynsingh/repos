<!-- BROWSE SEARCH JSP-->
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

String net=null;
try
{
      URL url = new URL("http://www.google.com");
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
    System.out.println("No WOrking");
}

%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/helpdemo.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi?key=ABQIAAAApEiKekYWqFpDa_PStAFTMBRxcC-Fn9tK14QS9YKtPPoXy5_dfhQr8n6mPjQbdLIjMkUpUDQ7khVrfQ">
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajax.js"></script>
<script language="javascript" type="text/javascript">
var availableSelectList;
function search()
{

    var keyValue = document.getElementById('CMBLib').options[document.getElementById('CMBLib').selectedIndex].value;
     if(keyValue=="all")
     {
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
    
}

function fun()
{
   
    document.getElementById("Form1").action="browse_search.do";
    document.getElementById("Form1").method="post";
    document.getElementById("Form1").target="f1";
    document.getElementById("Form1").submit();
   
}
<% if(net.equalsIgnoreCase("false")==false){%>
      //********************************* Load the Google Transliterate API************************************

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
        var ids = [ "TXTTITLE"];
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
        document.getElementById('checkboxId1').checked = e.transliterationEnabled;
      }

      // Handler for checkbox's click event.  Calls toggleTransliteration to toggle
      // the transliteration state.
      function checkboxClickHandler() {
        window.status="Press F1 for Help";
        if(document.getElementById('checkboxId1').checked)
        {
        document.getElementById("checkbox").value="Checked";
        }
        else
        {
        document.getElementById("checkbox").value="Unchecked";
        }
        if(document.getElementById('checkboxId1').checked==true)
        {
        document.getElementById('MLI').style.display = 'block';
        }
        else{
            document.getElementById('MLI').style.display = 'none';
            document.getElementById('TXTTITLE').style.textAlign = "left";
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
      //****************************************END OF GOOGLE API JS CODE******************************************
      //<%}%>
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

</script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/keyboard/keyboard.js" charset="UTF-8"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/keyboard/keyboard_002.js" charset="UTF-8"></script>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/keyboard/keyboard.css"/>
    </head>
    <jsp:include page="opacheader.jsp"></jsp:include>
    <body onload="checkboxClickHandler();search();fun(); setIframeHeight();" bgcolor="cyan" style="margin:0px 0px 0px 0px;">
        <html:form action="/OPAC/browse_search"  styleId="Form1" target="f1" acceptCharset="utf-8">
        <html:hidden property="checkbox" styleId="checkbox" name="browseSearchActionForm"/>
        <html:hidden property="language" styleId="language" name="browseSearchActionForm"/>
        <table class="header1" width="100%"><tr><td align="left" class="datagrid" dir="<%=rtl%>" width="100%" bgcolor="white" height="100%">
        <table align="center" class="datagrid" dir="<%=rtl%>" width="100%" bgcolor="white" height="100%">
          
               

            <tr><td style="color:red;height: 20px;border-bottom:  dotted 1px cyan;font-weight: bold;margin: 0px 0px 0px 0px"  colspan="2" align="center"><p><font color="red" ><b><%=resource.getString("opac.browse.browsesearch")%></b></font></p></td></tr>
            <tr><td valign="top">
        <table  align="<%=align%>" dir="<%=rtl%>" width="100%">
     
      
   <% if(net.equalsIgnoreCase("true")){%>
        <tr dir="<%=rtl%>"><td width="100%"> <%=resource.getString("cataloguing.catbiblioentry.selectlang1")%></td><td>
        <table><tr><td>
        <div id='translControl'>

         <input type="checkbox" id="checkboxId1"  onclick="javascript:checkboxClickHandler();javascript:languageChangeHandler()">
        </div></td><td>
        <div id="MLI" style="visibility: block" >
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
  <tr ><td  dir="<%=rtl%>" >
         <%=resource.getString("opac.browse.enterstartingkeyword")%></td><td><input  name="TXTTITLE" id="TXTTITLE" class="keyboardInput" type="text" dir="<%=rtl%>"  onfocus="statwords('Enter Search Keyword')"></td> </tr>
  <tr>
      <td dir="<%=rtl%>"   align="<%=align%>" valign="top">

         <%=resource.getString("opac.simplesearch.field")%> </td>
      <td  dir="<%=rtl%>" valign="top">
<select dir="<%=rtl%>" name="CMBFIELD" class="selecthome"  size="1">
<option value="any field" selected dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.anyfld")%></option>
<option value="mainEntry" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.auth")%></option>
<option value="title" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.tit")%></option>
<option value="publisherName" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.pub")%></option>
</select>
     </td>
              </tr>
              <tr ><td style="color:red;border-bottom:  dashed 1px cyan;font-weight: bold;" colspan="2" dir="<%=rtl%>"  align="<%=align%>" ><%=resource.getString("opac.simplesearch.restrictedby")%></td></tr>

              <tr><td dir="<%=rtl%>"><%=resource.getString("opac.browse.database")%></td><td>
                    <select name="CMBDB" class="selecthome" dir="<%=rtl%>"  size="1">
<option selected value="combined" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.combnd")%></option>
    <option value="book" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.books")%></option>
     <option value="cd" dir="<%=rtl%>">CD/DVD ROM</option>
     <option value="vd" dir="<%=rtl%>">Electronic Document(Video/Motion Pictures)</option>
     <option value="ppt" dir="<%=rtl%>">Electronic Document(Presenatation)</option>
     <option value="au" dir="<%=rtl%>">Electronic Document(Sound Recording)</option>
     <option value="th" dir="<%=rtl%>">Thesis</option>
     <option value="ds" dir="<%=rtl%>">Dissertations</option>
</select>
                  </td></tr>
              <tr><td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.library")%></td>
                  <td>
                      <html:select property="CMBLib" styleClass="selecthome" dir="<%=rtl%>"  tabindex="3" value="<%=library_id%>"  styleId="CMBLib" onchange="search();">
                           <html:option value="all">All</html:option>
                            <html:options collection="libRs" property="libraryId" labelProperty="libraryName"/>
                      </html:select>
                  </td>
              </tr>
              <tr>
                  <td align="left" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.sublibrary")%></td><td><html:select styleClass="selecthome" property="CMBSUBLib" dir="<%=rtl%>" value="<%=sublibrary_id%>" styleId="SubLibary" >
       <html:option value="all">All</html:option>
                      </html:select>
</tr>
<tr><td dir="<%=rtl%>">Sort</td><td dir="<%=rtl%>"> <select class="selecthome" name="CMBSORT" dir="<%=rtl%>" size="1"  id="CMBSORT">
<option  value="callNo" dir="<%=rtl%>">Call No</option>
<option value="title" dir="<%=rtl%>">Title</option>
<option  value="mainEntry" dir="<%=rtl%>">Main Author</option>
<option value="isbn10" dir="<%=rtl%>">ISBN</option>
<option value="publisherName" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.pub")%></option>
</select>
</td>
                           </tr>
                           <tr><td colspan="2">
                                   <input type="submit"  class="buttonhome" id="Button1" name="" dir="<%=rtl%>"  value="<%=resource.getString("opac.simplesearch.find")%>">
                                    <input type="reset" id="Button2" class="buttonhome" name="" dir="<%=rtl%>"  value="<%=resource.getString("opac.browse.clear")%>">
      </td></tr>
       </table>
                </td><td valign="top"  style="text-align: justify;border-left: dashed 1px cyan;" width="60%">
                    
                    <b style="margin-left: 10px;"> Search Tips</b><br>

                    <b style="margin-left: 10px;">How can I get fewer results?</b>
<p style="margin-left: 10px;margin-right: 10px;text-align:justify;">If you use more than one keyword, our search engine will restrict the results to  match all the keywords you enter.</p>
<b style="margin-left: 10px;">How do I sort my results?</b>
<p style="margin-left: 10px;margin-right: 10px;text-align:justify;">Select Specific Field on which you want to sort the result from given combo box.</p>

           </tr>
           <tr><td    valign="top"   dir="<%=rtl%>" colspan="2">
                   <IFRAME    name="f1"  src="#" frameborder=0 style="margin: 0px 0px 0px 0px;"  scrolling="no" width="100%" id="f1"></IFRAME>
                   

               </td></tr>
           

        </table> </td></tr></table>
 </html:form>
</body>
 <jsp:include page="opacfooter.jsp"></jsp:include>
</html>