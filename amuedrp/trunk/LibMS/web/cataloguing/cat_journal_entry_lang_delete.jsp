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
System.out.println("aaaaaaaaaaaadddddddddddAAAAAAAAAAAAAAAAAAAADDDDDDDDDDD"+button1);
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
   if(checkdata()==true){ var buttonvalue="Save";
    document.getElementById("button1").setAttribute("value", buttonvalue);
    return true;
    }else
        return false;
}
function submitSaveAccession()
{
    if(checkdata()==true){  var buttonvalue="Save and go for accessioning";
    document.getElementById("button1").setAttribute("value", buttonvalue);
    return true;
    }else
        return false;
}
function submitUpdate()
{
     if(checkdata()==true){ var buttonvalue="Update";
    document.getElementById("button1").setAttribute("value", buttonvalue);
    return true;
     }
     else
         return false;
}
 function showdiv(){

        var ele = document.getElementById("image1");


	if(ele.style.display == "block") {
    		ele.style.display = "none";

  	}
	else {
		ele.style.display = "block";

	}


    }
    function submit()
    {

        document.getElementsById("filename").value=document.getElementById("img").value;
       // alert(document.getElementsById("filename").value);
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

function checkdata()
{

        if(document.getElementById("title1").value=='' || document.getElementById("guide_name2").value=='' || document.getElementById("submited_by1").value=='' || document.getElementById("call1").value==''|| document.getElementById("degree1").value=='')
        {alert("Please Enter Reocords Mark with *");
         return false;}
     return true;

}
</script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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
animatedcollapse.addDiv('8', 'fade=1,height=20px')
animatedcollapse.addDiv('9', 'fade=1,height=20px')
animatedcollapse.addDiv('10', 'fade=1,height=20px')
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
        var ids = ["title","callno","subject","notes","latest","publisher_name","publication_place","website","clas","history","issn","frequency"];
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
       function systemlanguage() {
          var keyValue = document.getElementById('slanguage').options[document.getElementById('slanguage').selectedIndex].value;
              document.getElementById("language").value=keyValue;

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

    document.getElementById('stitle').style.display='none';
    document.getElementById('ptitle').style.display='block';
    document.getElementById('scallno').style.display='none';
    document.getElementById('pcallno').style.display='block';
    document.getElementById('ssubject').style.display='none';
    document.getElementById('psubject').style.display='block';
    document.getElementById('snotes').style.display='none';
    document.getElementById('pnotes').style.display='block';
    document.getElementById('slatest').style.display='none';
    document.getElementById('platest').style.display='none';
    document.getElementById('spublisher_name').style.display='none';
    document.getElementById('ppublisher_name').style.display='block';
    document.getElementById('spublication_place').style.display='none';
    document.getElementById('ppublication_place').style.display='block';
    document.getElementById('swebsite').style.display='none';
    document.getElementById('pwebsite').style.display='block';
    document.getElementById('sclas').style.display='none';
    document.getElementById('pclas').style.display='block';
    document.getElementById('shistory').style.display='none';
    document.getElementById('phistory').style.display='block';
    document.getElementById('sissn').style.display='none';
    document.getElementById('pissn').style.display='none';
    document.getElementById('sfrequency').style.display='none';
    document.getElementById('pfrequency').style.display='none';



    document.getElementById("checkbox").value="Checked";
    document.getElementById('title').disabled=false;
    document.getElementById('callno').disabled=false;
    document.getElementById('subject').disabled=false;
    document.getElementById('notes').disabled=false;
    document.getElementById('latest').disabled=false;
    document.getElementById('publisher_name').disabled=false;
    document.getElementById('publication_place').disabled=false;
    document.getElementById('website').disabled=false;
    document.getElementById('clas').disabled=false;
    document.getElementById('history').disabled=false;
    document.getElementById('issn').disabled=false;
    document.getElementById('frequency').disabled=false;

//document.getElementById('callno').style.backgroundColor='black';


}
else if(document.getElementById('check1').checked)
{

    document.getElementById('stitle').style.display='none';
    document.getElementById('ptitle').style.display='block';
    document.getElementById('scallno').style.display='none';
    document.getElementById('pcallno').style.display='block';
    document.getElementById('ssubject').style.display='none';
    document.getElementById('psubject').style.display='block';
    document.getElementById('snotes').style.display='none';
    document.getElementById('pnotes').style.display='block';
    document.getElementById('slatest').style.display='none';
    document.getElementById('platest').style.display='none';
    document.getElementById('spublisher_name').style.display='none';
    document.getElementById('ppublisher_name').style.display='block';
    document.getElementById('spublication_place').style.display='none';
    document.getElementById('ppublication_place').style.display='block';
    document.getElementById('swebsite').style.display='none';
    document.getElementById('pwebsite').style.display='block';
    document.getElementById('sclas').style.display='none';
    document.getElementById('pclas').style.display='block';
    document.getElementById('shistory').style.display='none';
    document.getElementById('phistory').style.display='block';
    document.getElementById('sissn').style.display='none';
    document.getElementById('pissn').style.display='none';
    document.getElementById('sfrequency').style.display='none';
    document.getElementById('pfrequency').style.display='none';

    document.getElementById("checkbox").value="Checked";
    document.getElementById('title').disabled=false;
    document.getElementById('callno').disabled=false;
    document.getElementById('subject').disabled=false;
    document.getElementById('notes').disabled=false;
    document.getElementById('latest').disabled=false;
    document.getElementById('publisher_name').disabled=false;
    document.getElementById('publication_place').disabled=false;
    document.getElementById('website').disabled=false;
    document.getElementById('clas').disabled=false;
    document.getElementById('history').disabled=false;
    document.getElementById('issn').disabled=false;
    document.getElementById('frequency').disabled=false;

document.getElementById('check1').checked=true;

   var keyValue = document.getElementById('slanguage').options[document.getElementById('slanguage').selectedIndex].value;
              document.getElementById("language").value=keyValue;


}else{
    //textbox colour change


    document.getElementById("checkbox").value="UnChecked";
    document.getElementById("language").value='';
    document.getElementById('stitle').style.display='none';
    document.getElementById('ptitle').style.display='none';
    document.getElementById('scallno').style.display='none';
    document.getElementById('pcallno').style.display='none';
    document.getElementById('ssubject').style.display='none';
    document.getElementById('psubject').style.display='none';
    document.getElementById('snotes').style.display='none';
    document.getElementById('pnotes').style.display='none';
    document.getElementById('slatest').style.display='none';
    document.getElementById('platest').style.display='none';
    document.getElementById('spublisher_name').style.display='none';
    document.getElementById('ppublisher_name').style.display='none';


    document.getElementById('spublication_place').style.display='none';
    document.getElementById('ppublication_place').style.display='none';
    document.getElementById('swebsite').style.display='none';
    document.getElementById('pwebsite').style.display='none';
    document.getElementById('sclas').style.display='none';
    document.getElementById('pclas').style.display='none';
    document.getElementById('shistory').style.display='none';
    document.getElementById('phistory').style.display='none';
    document.getElementById('sissn').style.display='none';
    document.getElementById('pissn').style.display='none';

    document.getElementById('sfrequency').style.display='none';
    document.getElementById('pfrequency').style.display='none';


}
}
  function show() {
      if(document.getElementById('mli').checked){
      id='key';




                document.getElementById(id).style.display = 'block';

      }
      else{
                document.getElementById(id).style.display = 'none';
                //textbox colour change


            document.getElementById("checkbox").value="UnChecked";
            document.getElementById("language").value='';


    document.getElementById("checkbox").value="UnChecked";
    document.getElementById("language").value='';
    document.getElementById('stitle').style.display='none';
    document.getElementById('ptitle').style.display='none';
    document.getElementById('scallno').style.display='none';
    document.getElementById('pcallno').style.display='none';
    document.getElementById('ssubject').style.display='none';
    document.getElementById('psubject').style.display='none';
    document.getElementById('snotes').style.display='none';
    document.getElementById('pnotes').style.display='none';
    document.getElementById('slatest').style.display='none';
    document.getElementById('platest').style.display='none';
    document.getElementById('spublisher_name').style.display='none';
    document.getElementById('ppublisher_name').style.display='none';


    document.getElementById('spublication_place').style.display='none';
    document.getElementById('ppublication_place').style.display='none';
    document.getElementById('swebsite').style.display='none';
    document.getElementById('pwebsite').style.display='none';
    document.getElementById('sclas').style.display='none';
    document.getElementById('pclas').style.display='none';
    document.getElementById('shistory').style.display='none';
    document.getElementById('phistory').style.display='none';
    document.getElementById('sissn').style.display='none';
    document.getElementById('pissn').style.display='none';

    document.getElementById('sfrequency').style.display='none';
    document.getElementById('pfrequency').style.display='none';


      }

        cat_biblio_entry_lang_delete.jsp
        }

        function hide(){


        }

function disablecheck()
{
    document.getElementById('checkboxId').checked=false;
  document.getElementById('mli').checked=false;
    DisBox();
}
    </script>cat_biblio_entry_lang_delete.jsp
    </head>
    <body onload="disablecheck();hide();">
        <div
   style="  top:100px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
       <%if(msg1!=null){%>   <span style="font-size:12px;font-weight:bold;color:red;" ><%=msg1%></span>  <%}%>
       <html:form method="post" styleId="form1" action="/catMultiLang3" acceptCharset="utf-8">
         <html:hidden property="library_id" name="BibliographicDetailEntryActionForm" value="<%=library_id%>" />
                        <html:hidden property="sublibrary_id" name="BibliographicDetailEntryActionForm" value="<%=sub_library_id%>" /><td></td>
                        <html:hidden property="accession_type" name="BibliographicDetailEntryActionForm"/>
                        <html:hidden property="document_type" name="BibliographicDetailEntryActionForm"/>
                        <html:hidden property="no_of_copies" name="BibliographicDetailEntryActionForm"/>
                        <html:hidden property="biblio_id" name="BibliographicDetailEntryActionForm" />
                          <html:hidden property="language" styleId="language" name="BibliographicDetailEntryActionForm"/>
                          <html:hidden property="date_acquired1" styleId="language" name="BibliographicDetailEntryActionForm"/>
                          <html:hidden property="checkbox" styleId="checkbox"  name="BibliographicDetailEntryActionForm"/>


           <table style="border:solid 1px black" class="datagrid" width="60%" align="center">
        <tr><td align="center"  bgcolor="#E0E8F5" height="25px;" colspan="4" ><b><%=resource.getString("cataloguing.catoldtitleentry1.header")%></b></td></tr>

        <tr>
                            <td align="left" class="datagrid" height="25px;" colspan="4" >

                                <input type="checkbox"  id="mli" onclick="show();">Click here for MLI Entry
                                <div id="key" style="display: none;text-align: center;margin: 5px 5px 5px 5px;border: dashed 2px cyan;">
        <div id='translControl1' style="display:none;">
      <input type="button" class="btnapp"  onclick="javascript:document.getElementById('translControl1').style.display='none';javascript:document.getElementById('translControl').style.display='inline';javascript:document.getElementById('check1').checked=false;javascript:document.getElementById('checkboxId').checked=false;DisBox();" value="Want Phonetic Keyword">
            <a class="star">Check For System Keyword Support*</a>  <input type="checkbox" id="check1" onclick="DisBox();" name="checkbox">

      <span class="datagrid"> <%=resource.getString("cataloguing.catoldtitleentry1.typein")%></span>&nbsp;<select class="btnapp" id="slanguage" onchange="systemlanguage()">
        <option value="HI">Hindi</option>
        <option value="BE">Bengali</option>
        <option value="TE">Telugu</option>
        <option value="MA">Marathi</option>
        <option value="TA">Tamil 	</option>
        <option value="UR">Urdu 	</option>
        <option value="GU">Gujarati</option>
        <option value="KA">Kannada </option>
        <option value="MA">Malayalam</option>
        <option value="OR">Oriya</option>
        <option value="PU">Punjabi</option>
        <option value="AS">Assamese</option>
        <option value="MI">Maithili</option>
        <option value="KA">Kashmiri</option>
        <option value="SA">Sanskrit</option>
      </select>

        </div>


        <div id='translControl'>
             <input type="button" class="btnapp"  onclick="javascript:document.getElementById('translControl').style.display='none';javascript:document.getElementById('translControl1').style.display='inline';javascript:document.getElementById('checkboxId').checked=false;javascript:document.getElementById('check1').checked=false;DisBox();" value="Want System Keyword">
            <a class="star">Check For Phontic Keyword Support*</a> <input type="checkbox"  name="checkbox" id="checkboxId" onclick="javascript:checkboxClickHandler();javascript:DisBox();javascript:languageChangeHandler();">

      <span class="datagrid">  <%=resource.getString("cataloguing.catoldtitleentry1.typein")%></span>&nbsp;<select class="btnapp" id="languageDropDown" onchange="javascript:languageChangeHandler()"></select>

        </div>
                                </div>
    </td>
</tr>
        <tr><td>
                    <table width="100%"  dir="<%=rtl%>">

<tr>
    <td width="40%" class="txtStyle" align="<%=align%>" height="10px;"><strong><%=resource.getString("cataloguing.catoldtitleentry1.documentcategory")%><a class="star">*</a>:</strong> </td>
    <td colspan="5">  <html:select property="book_type"  name="BibliographicDetailEntryActionForm" styleClass="btnapp">
            <html:options collection="DocumentCategory"  labelProperty="documentCategoryName" property="id.documentCategoryId" name="DocumentCategory"></html:options>
  </html:select>
    </td>
</tr>

<tr>
    <td class="txtStyle" width="15%" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.title")%>:</strong> </td>
    <td width="15%"><html:text property="title" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="1" />
    </td>
    <td width="15%">
        <div id="ptitle" style="display:none;">
            <html:text property="title1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleId="title" styleClass="keyboardInput" tabindex="2"/>
        </div>
        <div id="stitle" style="display:none;">
            <html:text property="title1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm"  tabindex="2"/>
        </div>


    </td>
    <td class="txtStyle" width="15%" align="<%=align%>"><strong>Latest:</strong> </td>
    <td width="15%"><html:text property="latest" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="1" />
    </td>
    <td width="15%">
        <div id="platest" style="display:none;">
            <html:text property="latest1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleId="latest" styleClass="keyboardInput" tabindex="2"/>
        </div>
        <div id="slatest" style="display:none;">
            <html:text property="latest1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm"  tabindex="2"/>
        </div>


    </td>

  </tr>

<tr>
      <td class="txtStyle" align="<%=align%>"><strong>Publisher<a class="star">*</a>:</strong></td>
      <td><html:text property="publisher_name" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="5" />
      <br>
  </td>
  <td>
        <div id="ppublisher_name"  style="display:none;">
      <html:text property="publisher_name1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleId="publisher_name" styleClass="keyboardInput" tabindex="6"/>
        </div>
        <div id="spublisher_name"   style="display:none;">
      <html:text property="publisher_name1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm"   tabindex="6"/>
        </div>
        </td>
         <td class="txtStyle" align="<%=align%>"><strong>Country:</strong></td>
      <td><html:text property="publication_place" readonly="<%=read%>" styleClass="textBoxWidth" name="BibliographicDetailEntryActionForm" tabindex="35"/></td>
      <td>
    <div id="ppublication_place" style="display:none;">
          <html:text property="publication_place1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleId="publication_place" styleClass="keyboardInput" tabindex="36"/>
    </div>
    <div id="spublication_place" style="display:none;">
          <html:text property="publication_place1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm"  tabindex="36"/>
    </div>
      </td>

</tr>


   <tr>
 <td class="txtStyle" align="<%=align%>"><strong>Website:</strong></td>
 <td><html:text property="website" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="9" /></td>
 <td>
       <div id="pwebsite"  style="display:none;">
     <html:text property="website1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleId="website" styleClass="keyboardInput" tabindex="10"/>
       </div>
 <div id="swebsite"  style="display:none;">
     <html:text property="website1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm"   tabindex="10"/>
       </div>
 </td>
 <td class="txtStyle" align="<%=align%>"><strong>Class</strong></td>
         <td><html:text property="clas" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="11" /></td>
         <td>
             <div id="pclas"  style="display:none;">
             <html:text property="clas1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleId="clas" styleClass="keyboardInput" tabindex="12" />
             </div>
             <div id="sclas"  style="display:none;">
             <html:text property="clas1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleId="clas"  tabindex="12" />
             </div>
             </td>
   </tr>

  <tr>
    <td class="txtStyle" align="<%=align%>"><strong>Frequency: </strong></td>
    <td><html:text  property="frequency" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="23" /></td>
    <td>
        <div id="pfrequency"  style="display:none;">
        <html:text property="frequency1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleId="frequency" styleClass="keyboardInput" tabindex="24"/>
        </div>
        <div id="sfrequency"  style="display:none;">
        <html:text property="frequency1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm"   tabindex="24"/>
        </div>
    </td>
    <td class="txtStyle" align="<%=align%>"><strong>History:</strong></td>
         <td><html:text  property="history" readonly="<%=read%>"   name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="25" /></td>
   <td>
       <div id="phistory"  style="display:none;">
       <html:text  property="history1" readonly="<%=read%>"  name="BibliographicDetailEntryActionForm" styleId="history" styleClass="keyboardInput" tabindex="26"/>
       </div>
       <div id="shistory"  style="display:none;">
       <html:text  property="history1" readonly="<%=read%>"  name="BibliographicDetailEntryActionForm"   tabindex="26"/>
       </div>
       </td>

  </tr>



  <tr>
   <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.callno")%><a class="star">*</a>:</strong></td>
         <td>
             <html:text property="call_no" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="33" />
        <br><span class="err">   <html:messages id="err_name" property="call_no">
        <%=resource.getString("cataloguing.catoldtitleentry1.err2")%>
    </html:messages></span>
  </td>
  <td>
      <div class="pcall" style="display:none;">
      <html:text property="call_no1"  readonly="true" name="BibliographicDetailEntryActionForm" styleId="callno" styleClass="keyboardInput" tabindex="34" />
  </div>
  </td>
   <td class="txtStyle" align="<%=align%>"><strong>ISSN:</strong></td>
         <td><html:text  property="issn" readonly="<%=read%>"   name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="25" /></td>
   <td>
       <div id="pissn"  style="display:none;">
       <html:text  property="issn1" readonly="<%=read%>"  name="BibliographicDetailEntryActionForm" styleId="issn" styleClass="keyboardInput" tabindex="26"/>
       </div>
       <div id="sissn"  style="display:none;">
       <html:text  property="issn1" readonly="<%=read%>"  name="BibliographicDetailEntryActionForm"   tabindex="26"/>
       </div>
       </td>
  </tr>

  <tr>

      <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.subject")%>:</strong></td>
         <td><html:text property="subject" readonly="<%=read%>" name="BibliographicDetailEntryActionForm"  styleClass="textBoxWidth" tabindex="37" /></td>
<td>
    <div id="psubject" style="display:none;">
    <html:text property="subject1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleId="subject" styleClass="keyboardInput" tabindex="38" />
    </div>
    <div id="ssubject" style="display:none;">
    <html:text property="subject1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm"   tabindex="38" />
    </div>
    </td>

  </tr>

  <tr>
   <td class="txtStyle" align="<%=align%>"><strong>Remarks:</strong></td>
   <td><html:textarea rows="4" cols="20" property="notes" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="41" /></td>
   <td>
        <div id="pnotes" style="display:none;" >
       <html:textarea rows="4" cols="18" property="notes1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleId="notes" styleClass="keyboardInput" tabindex="42"/>
        </div>
        <div id="snotes" style="display:none;" >
       <html:textarea rows="4" cols="18" property="notes1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm"   tabindex="42"/>
        </div>
   </td>
  </tr>





</table>
</td>

                        <tr><td colspan="5" height="5px" class="mandatory" dir="<%=rtl%>"><a class="star">*</a><%=resource.getString("cataloguing.catoldtitle.mandatory")%></td></tr>
<%--<tr><td colspan="5" height="5px" class="mandatory" dir="<%=rtl%>"><a class="star">*</a><%=resource.getString("cataloguing.catoldtitle.selectlanguage")%></td></tr>--%>
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
   <%}else{%>
    <input  name="button1" type="button" value="<%=resource.getString("cataloguing.catoldtitle.back")%>" onclick="return send()"/><%}%>
     <input type="hidden" id="button1" name="button"/>
    </td>

</tr><tr><td colspan="5" height="5px"></td>
</tr>
                    </table>
      </html:form>
        </div>


    </body>
</html>

