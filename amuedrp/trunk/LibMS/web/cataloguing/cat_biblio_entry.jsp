<%--
    Document   : cat_biblio_entry
    Created on : Jan 13, 2011, 12:02:47 PM
    Author     : Asif Iqubal
--%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
    String newbutton;
    String newbutton1;
    boolean read;
    boolean disable;
%>
<%
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="right";}
    else{ rtl="RTL";align="left";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
    %>
<%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
String button1=(String)session.getAttribute("button");
String msg1=(String) request.getAttribute("msg1");
request.setCharacterEncoding("UTF-8");
request.getCharacterEncoding();
 if(button1.equals("Update"))
 {
   newbutton=resource.getString("cataloguing.catoldtitle.update");
   read=false;
   disable=false;
 }
 if(button1.equals("Delete")||button1.equals("View"))
 {
    newbutton=resource.getString("cataloguing.catoldtitle.delete");
    disable=true;
    read=true;
 }
 if(button1.equals("New"))
 {
   newbutton=resource.getString("cataloguing.catoldtitleentry1.save");
   newbutton1=resource.getString("cataloguing.catoldtitleentry1.saveaccession");
   disable=false;
   read=false;
 }
 %>
<script type="text/javascript">
function send()
{
    window.location="<%=request.getContextPath()%>/cataloguing/cat_biblio.jsp";
    return false;
}
function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
         if (charCode > 31 && (charCode < 48 || charCode > 57))
         return false;
         return true;
      }
</script>
<html>
    <head>
         <script type="text/javascript" language="javascript">
   function submitSave()
{
    var buttonvalue="Save";
    document.getElementById("button1").setAttribute("value", buttonvalue);
    return true;
}
function submitSaveAccession()
{
    var buttonvalue="Save and go for accessioning";
    document.getElementById("button1").setAttribute("value", buttonvalue);
    return true;
}
function submitUpdate()
{
    var buttonvalue="Update";
    document.getElementById("button1").setAttribute("value", buttonvalue);
    return true;
}
function submitDelete()
{
    var buttonvalue="Delete";
    document.getElementById("button1").setAttribute("value", buttonvalue);
    return true;
}
function copysubtitle(){
    var c=document.getElementById("subtitle").value;
    document.getElementById("subtitle1").value=c;
}
</script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bibliographic Detail Entry Form</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/animatedcollapse.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/keyboard/keyboard.js" charset="UTF-8"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/keyboard/keyboard_002.js" charset="UTF-8"></script>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/keyboard/keyboard.css"/>
        <script type="text/javascript">
animatedcollapse.addDiv('1', 'fade=1,height=20px')
animatedcollapse.addDiv('2', 'fade=1,height=20px')
animatedcollapse.addDiv('3', 'fade=1,height=20px')
animatedcollapse.addDiv('4', 'fade=1,height=20px')
animatedcollapse.addDiv('5', 'fade=1,height=20px')
animatedcollapse.addDiv('6', 'fade=1,height=20px')
animatedcollapse.ontoggle=function($, divobj, state){ //fires each time a DIV is expanded/contracted
}
animatedcollapse.init()

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
        var ids = [ "subtitle1","mainentry","publishername","publishingyear","isbn10","edition","alttitle","sernote","thesisabstract","title","statementresponsibility","addedentry","addedentry0","addedentry1","addedentry2","publicationplace","lccno","isbn13","callno","subject","notes"];
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
  <script type="text/javascript">
function DisBox()
{
if(document.getElementById('checkboxId').checked)
{
document.getElementById("checkbox").value="Checked";
document.getElementById('subtitle1').disabled=false;
document.getElementById('mainentry').disabled=false;
document.getElementById('publishername').disabled=false;
document.getElementById('publishingyear').disabled=false;
document.getElementById('isbn10').disabled=false;
document.getElementById('edition').disabled=false;
document.getElementById('alttitle').disabled=false;
document.getElementById('sernote').disabled=false;
document.getElementById('thesisabstract').disabled=false;
document.getElementById('title').disabled=false;
document.getElementById('statementresponsibility').disabled=false;
document.getElementById('addedentry').disabled=false;
document.getElementById('addedentry0').disabled=false;
document.getElementById('addedentry1').disabled=false;
document.getElementById('addedentry2').disabled=false;
document.getElementById('publicationplace').disabled=false;
document.getElementById('lccno').disabled=false;
document.getElementById('isbn13').disabled=false;
document.getElementById('callno').disabled=false;
document.getElementById('subject').disabled=false;
document.getElementById('notes').disabled=false;

}
else
{
document.getElementById('subtitle1').blur();
document.getElementById('subtitle1').disabled=true;
document.getElementById('mainentry').blur();
document.getElementById('mainentry').disabled=true;
document.getElementById('publishername').blur();
document.getElementById('publishername').disabled=true;
document.getElementById('publishingyear').blur();
document.getElementById('publishingyear').disabled=true;
document.getElementById('isbn10').blur();
document.getElementById('isbn10').disabled=true;
document.getElementById('edition').blur();
document.getElementById('edition').disabled=true;
document.getElementById('alttitle').blur();
document.getElementById('alttitle').disabled=true;
document.getElementById('sernote').blur();
document.getElementById('sernote').disabled=true;
document.getElementById('thesisabstract').blur();
document.getElementById('thesisabstract').disabled=true;
document.getElementById('title').blur();
document.getElementById('title').disabled=true;
document.getElementById('statementresponsibility').blur();
document.getElementById('statementresponsibility').disabled=true;
document.getElementById('addedentry').blur();
document.getElementById('addedentry').disabled=true;
document.getElementById('addedentry0').blur();
document.getElementById('addedentry0').disabled=true;
document.getElementById('addedentry1').blur();
document.getElementById('addedentry1').disabled=true;
document.getElementById('addedentry2').blur();
document.getElementById('addedentry2').disabled=true;
document.getElementById('publicationplace').blur();
document.getElementById('publicationplace').disabled=true;
document.getElementById('lccno').blur();
document.getElementById('lccno').disabled=true;
document.getElementById('isbn13').blur();
document.getElementById('isbn13').disabled=true;
document.getElementById('callno').blur();
document.getElementById('callno').disabled=true;
document.getElementById('subject').blur();
document.getElementById('subject').disabled=true;
document.getElementById('notes').blur();
document.getElementById('notes').disabled=true;
  document.getElementById("checkbox").value="UnChecked";
document.getElementById("language").value="";
}
}
function disablecheck(){
    document.getElementById('checkboxId').checked=false;
    

    DisBox();
}
    </script>
    </head>
<body onload="return disablecheck()">
       <%if(msg1!=null){%>   <span style="position:absolute; top: 90px; font-size:12px;font-weight:bold;color:red;" ><%=msg1%></span>  <%}%>
      <table border="1" class="table" width="96%" style="position: absolute; top: 20%; left: 3%">
        <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" colspan="2" ><b><%=resource.getString("cataloguing.catoldtitleentry1.header")%></b></td></tr>
            <tr><td>
                    <table width="100%" border="0" dir="<%=rtl%>">
                        <tr>
                            <td width="45%" align="<%= align %>">
                          <html:form method="post" styleId="form1" action="/catMultiLang" acceptCharset="utf-8">
                        <table width="100%" border="0"  dir="<%=rtl%>">
                        <html:hidden property="library_id" name="BibliographicDetailEntryActionForm" value="<%=library_id%>" />
                        <html:hidden property="sublibrary_id" name="BibliographicDetailEntryActionForm" value="<%=sub_library_id%>" /><td></td>
                        <html:hidden property="accession_type" name="BibliographicDetailEntryActionForm"/>
                        <html:hidden property="document_type" name="BibliographicDetailEntryActionForm"/>
                        <html:hidden property="no_of_copies" name="BibliographicDetailEntryActionForm"/>
                        <html:hidden property="biblio_id" name="BibliographicDetailEntryActionForm" />
                          <html:hidden property="language" styleId="language" name="BibliographicDetailEntryActionForm"/>
                          <html:hidden property="date_acquired1" styleId="language" name="BibliographicDetailEntryActionForm"/>
                
<tr>
      <td width="25%">
    </td>
    <td width="30%"></td>
    <td height="20px;">
        <div id='translControl'>
     <a class="star">*</a> <input type="checkbox" id="checkboxId" onclick="javascript:checkboxClickHandler();javascript:languageChangeHandler();javascript:DisBox();">
      <html:hidden property="checkbox" styleId="checkbox" name="BibliographicDetailEntryActionForm"/>
      <%=resource.getString("cataloguing.catoldtitleentry1.typein")%><select id="languageDropDown" onchange="javascript:languageChangeHandler()"></select>
    </div>
    </td>
</tr>
<tr>
    <td class="txtStyle" align="<%=align%>" height="10px;"><strong><%=resource.getString("cataloguing.catoldtitleentry1.documentcategory")%><a class="star">*</a>:</strong> </td>
    <td>  <html:select property="book_type"  name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth">
            <html:options collection="DocumentCategory"  labelProperty="documentCategoryName" property="id.documentCategoryId" name="DocumentCategory"></html:options>
  </html:select>
    </td>
</tr>
<tr><td height="2px"></td>
</tr>
<tr>
    <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.title")%>:</strong> </td>
    <td><html:text property="title" readonly="true" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="1" />
    </td>
    <td><html:text property="title1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleId="title" styleClass="keyboardInput" tabindex="2"/></td>
  </tr>
  <tr><td height="2px"></td>
</tr>
<tr>
      <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.mainentry")%><a class="star">*</a>:</strong></td>
      <td><html:text property="main_entry" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="5" />
      <br><span class="err">   <html:messages id="err_name" property="main_entry">
       <%=resource.getString("cataloguing.catoldtitleentry1.err1")%>
    </html:messages></span>
  </td>
  <td><html:text property="main_entry1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleId="mainentry" styleClass="keyboardInput" tabindex="6"/></td>
</tr>
 <tr><td height="2px"></td>
</tr>
   <tr>
 <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.publishername")%>:</strong></td>
 <td><html:text property="publisher_name" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="9" /></td>
 <td><html:text property="publisher_name1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleId="publishername" styleClass="keyboardInput" tabindex="10"/></td>
   </tr>
  <tr><td height="2px"></td>
</tr>
     <tr>
  <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.publishingyear")%>:</strong></td>
  <td><html:text property="publishing_year" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="13" onkeypress="return isNumberKey(event)" />
  </td>
  <td><html:text property="publishing_year1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleId="publishingyear" styleClass="keyboardInput" tabindex="14"/></td>
     </tr>
  <tr><td height="2px"></td>
</tr>
  <tr>
    <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.isbn10")%>: </strong></td>
    <td><html:text  property="isbn10" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="23" /></td>
    <td><html:text property="isbn101" readonly="true" name="BibliographicDetailEntryActionForm" styleId="isbn10" styleClass="keyboardInput" tabindex="24"/></td>
  </tr>
  <tr><td height="2px"></td>
</tr>
   <tr>
    <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.edition")%>:</strong></td>
    <td><html:text property="edition" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="27" /></td>
    <td><html:text property="edition1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleId="edition" styleClass="keyboardInput" tabindex="28"/></td>
  </tr>
  <tr><td height="2px"></td>
</tr>
  <tr>
   <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.alternatetitle")%>:</strong></td>
   <td><html:text property="alt_title" readonly="<%=read%>" name="BibliographicDetailEntryActionForm"  styleClass="textBoxWidth" tabindex="31" /></td>
   <td><html:text property="alt_title1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleId="alttitle" styleClass="keyboardInput" tabindex="32"/></td>
  </tr>
  <tr><td height="2px"></td>
</tr>
  <tr>
      <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.series")%>:</strong></td>
      <td><html:text property="ser_note" readonly="<%=read%>" styleClass="textBoxWidth" name="BibliographicDetailEntryActionForm" tabindex="35"/></td>
      <td><html:text property="ser_note1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleId="sernote" styleClass="keyboardInput" tabindex="36"/></td>
  </tr>
  <tr><td height="2px"></td>
</tr>
  <tr>
   <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.abstract")%>:</strong></td>
   <td><html:textarea rows="5" cols="20" property="thesis_abstract" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="39" /></td>
   <td><html:textarea rows="4" cols="18" property="thesis_abstract1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleId="thesisabstract" styleClass="keyboardInput" tabindex="40"/></td>
  </tr>
</table>
</td>
 <td width="33%">
     <table border="0" height="100%">
         <tr><td width="40%"><br><br><br><br><br></td></tr>
         <tr>
                 <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.subtitle")%>:</strong></td>
    <td><html:text property="subtitle" onchange="return copysubtitle();" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleId="subtitle" styleClass="textBoxWidth" tabindex="3" /></td>
         </tr>
         <tr><td height="2px"></td>
</tr>
         <tr>
  <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.statementresponsibility")%><a class="star">*</a>:</strong></td>
  <td><html:text property="statement_responsibility" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="7" />
      <br><span class="err">   <html:messages id="err_name" property="statement_responsibility">
        <%=resource.getString("cataloguing.catoldtitleentry1.err3")%>
    </html:messages></span>
  </td>
         </tr>
         <tr><td height="2px"></td>
</tr>
     <tr><td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.publicationplace")%>:</strong></td>
         <td><html:text property="publication_place" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="11" /></td>
     </tr>
<tr>
        <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.addedentry")%>:</strong></td>
        <td><html:text property="added_entry" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="15" />
     <input type="button" onclick="javascript:animatedcollapse.show(['1','2','3'])" value="+"/>
     <input type="button" onclick="javascript:animatedcollapse.hide(['1','2','3'])" value="-"/></td>
         </tr>
            <tr>
                <td></td>
                <td>
                    <div id="1" style="display: none;">
                        <html:text property="added_entry0" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="16"/>
</div>
<div id="2" style="display: none;">
    <html:text property="added_entry1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="17"/>
</div>
<div id="3" style="display: none;">
    <html:text property="added_entry2" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="18"/>
</div>
 </td>
            </tr>
  <tr><td height="2px"></td>
</tr>

<tr> <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.isbn13")%>:</strong></td>
         <td><html:text  property="isbn13" readonly="<%=read%>"  name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="25" /></td>
</tr>
 <tr><td height="2px"></td>
</tr>
<tr> <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.lcc")%>:</strong></td>
         <td><html:text property="LCC_no" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="29" />
         </td>
</tr>
<tr><td height="2px"></td>
</tr>
<tr><td height="2px"></td>
</tr>
<tr><td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.callno")%><a class="star">*</a>:</strong></td>
         <td><html:text property="call_no" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="33" />
        <br><span class="err">   <html:messages id="err_name" property="call_no">
        <%=resource.getString("cataloguing.catoldtitleentry1.err2")%>
    </html:messages></span>
  </td>

</tr>
<tr><td height="2px"></td>
</tr>
<tr><td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.subject")%>:</strong></td>
         <td><html:text property="subject" readonly="<%=read%>" name="BibliographicDetailEntryActionForm"  styleClass="textBoxWidth" tabindex="37" /></td>

</tr>
<tr><td height="2px"></td>
</tr>
<tr> <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.note")%>:</strong></td>
          <td><html:textarea rows="5" cols="20" property="notes" readonly="<%=read%>" name="BibliographicDetailEntryActionForm"  styleClass="textBoxWidth" tabindex="41" /></td>

</tr>
     </table>
 </td>
 <td align="left" valign="top">
         <table border="0">
             <tr><td><br><br><br><br><br></td></tr>
              <tr><td height="5px;"></td></tr>
             <tr>
                 <td><html:text property="subtitle1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleId="subtitle1" styleClass="keyboardInput" tabindex="4"/></td>
             </tr>
               <tr><td height="2px"></td>
</tr>
             <tr>
                 <td><html:text property="statement_responsibility1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleId="statementresponsibility" styleClass="keyboardInput" tabindex="8"/></td>
             </tr>
    <tr><td height="2px"></td>
</tr>
             <tr>
                              <td><html:text property="publication_place1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleId="publicationplace" styleClass="keyboardInput" tabindex="12" /></td>
             </tr>
       <tr><td height="2px"></td>
</tr>
             <tr>
                 <td><html:text property="added_entryl" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleId="addedentry" styleClass="keyboardInput" tabindex="19" />

     <input type="button" onclick="javascript:animatedcollapse.show(['4','5','6'])" value="+"/>
     <input type="button" onclick="javascript:animatedcollapse.hide(['4','5','6'])" value="-"/></td>
             </tr>
       <tr><td height="2px"></td>
</tr>
             <tr>
                  <td>
                    <div id="4" style="display: none;">
                        <html:text property="added_entry01" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleId="addedentry0" styleClass="keyboardInput" tabindex="42" />
</div>
<div id="5" style="display: none;">
    <html:text property="added_entry11" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleId="addedentry1" styleClass="keyboardInput" tabindex="21"/>
</div>
<div id="6" style="display: none;">
    <html:text property="added_entry21" readonly="<%=read%>" styleId="addedentry2" name="BibliographicDetailEntryActionForm" styleClass="keyboardInput" tabindex="22"/>
</div>
 </td>
             </tr>
       <tr>
                 <td><html:text  property="isbn131" readonly="true"  name="BibliographicDetailEntryActionForm" styleId="isbn13" styleClass="keyboardInput" tabindex="26"/></td>
             </tr>
               <tr><td height="2px"></td>
</tr>
             <tr>
                 <td><html:text property="LCC_no1" readonly="true" name="BibliographicDetailEntryActionForm" styleId="lccno" styleClass="keyboardInput" tabindex="30"/>
         </td>
             </tr>
  <tr><td height="2px"></td>
</tr>
             <tr>
                 <td><html:text property="call_no1" readonly="true" name="BibliographicDetailEntryActionForm" styleId="callno" styleClass="keyboardInput" tabindex="34" /></td>
             </tr>
              <tr><td height="2px"></td>
</tr>
             <tr>
                 <td><html:text property="subject1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleId="subject" styleClass="keyboardInput" tabindex="38" /></td>
             </tr>
              <tr><td height="2px"></td>
</tr>
             <tr>
                 <td><html:textarea rows="4" cols="18" property="notes1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="keyboardInput" styleId="notes" tabindex="41" /></td>
             </tr>
         </table>
     </td>
                        </tr>

                        <tr><td colspan="5" height="5px" class="mandatory" dir="<%=rtl%>"><a class="star">*</a><%=resource.getString("cataloguing.catoldtitle.mandatory")%></td></tr>
<tr><td colspan="5" height="5px" class="mandatory" dir="<%=rtl%>"><a class="star">*</a><%=resource.getString("cataloguing.catoldtitle.selectlanguage")%></td></tr>
                        <tr><td colspan="5" height="10px"></td>
</tr>
<tr>
    <td align="center" colspan="4" dir="<%=rtl%>">
    <%if(button1.equals("Update")){%>
    <input  name="button1" type="submit" value="<%=newbutton%>" onclick="return submitUpdate();"/>
    &nbsp;&nbsp;&nbsp;<input name="button1" type="submit" value="<%=resource.getString("cataloguing.catoldtitleentry1.cancel")%>" onclick="return send()" />
    <%}else if(button1.equals("Delete")){%>
    <input name="button1" type="submit" value="<%=newbutton%>" onClick="return submitDelete()"/>
    &nbsp;&nbsp;&nbsp;<input name="button1" type="submit" value="<%=resource.getString("cataloguing.catoldtitleentry1.cancel")%>" onclick="return send()"/>
   <%}else if(button1.equals("New")){%>
   <input name="button1" type="submit" value="<%=newbutton%>" onclick="return submitSave()"/>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="button1" type="submit" value="<%=newbutton1%>" onclick="return submitSaveAccession()" style="font-family: Arial,Helvetica,sans-serif; font-size:12px; width: auto;"/>
    &nbsp;&nbsp;&nbsp;&nbsp;<input name="button1" type="submit" onclick="return send()" value="<%=resource.getString("cataloguing.catoldtitleentry1.cancel")%>"/>
    <%}else{%>
    <input  name="button1" type="submit" value="<%=resource.getString("cataloguing.catoldtitle.back")%>" onclick="return send()"/><%}%>
     <input type="hidden" id="button1" name="button"/>
    </td>
           </html:form>
</tr><tr><td colspan="5" height="5px"></td>
</tr>
                    </table> </td></tr>
    </table>
    </body>
</html>

