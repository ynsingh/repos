<!-- JSP PAGE FOR SIMPLE SEARCH OPAC SECTION-->
<%@page import="com.myapp.struts.hbm.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
%>
<%
try
{
    locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
    }
    else locale1="en";
}
catch(Exception e)
{
    locale1="en";
}
        locale = new Locale(locale1);
        if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;align="left";}
        else{ rtl="RTL";page=false;align="right";}
        ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
        String lib_id = (String)session.getAttribute("library_id");
        String sublib_id =  (String)session.getAttribute("memsublib");
        if(sublib_id==null ||sublib_id.equals(""))
            sublib_id = (String)session.getAttribute("sublibrary_id");

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
<%
    String library_id = (String)session.getAttribute("library_id");
    String sublibrary_id = (String)session.getAttribute("memsublib");
     if(sublibrary_id==null)sublibrary_id=   (String)session.getAttribute("sublibrary_id");
if(sublibrary_id==null)sublibrary_id="all";
%>

<link rel="stylesheet" href="/LibMS/css/page.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/helpdemo.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi?key=ABQIAAAApEiKekYWqFpDa_PStAFTMBRxcC-Fn9tK14QS9YKtPPoXy5_dfhQr8n6mPjQbdLIjMkUpUDQ7khVrfQ">
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajax.js"></script>
<script language="javascript" type="text/javascript">


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
        if(document.getElementById('TXTPHRASE').value=="")
            {
                    alert("Please specify Keyword to Search");
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
        if(document.getElementById('checkboxId').checked)
        {
        document.getElementById("checkbox").value="Checked";
        }
        else
        {
        document.getElementById("checkbox").value="Unchecked";
        }
    }

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
        document.getElementById('checkboxId').checked = e.transliterationEnabled;
      }

      // Handler for checkbox's click event.  Calls toggleTransliteration to toggle
      // the transliteration state.
      function checkboxClickHandler() {
        window.status="Press F1 for Help";
        
        if(document.getElementById('checkboxId').checked==true)
        {
        document.getElementById('MLI').style.display = 'block';
        }
        else{
            document.getElementById('MLI').style.display = 'none';
            document.getElementById('TXTPHRASE').style.textAlign = "left";
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
</script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/keyboard/keyboard.js" charset="UTF-8"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/keyboard/keyboard_002.js" charset="UTF-8"></script>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/keyboard/keyboard.css"/>
</head>
<body onload="javascript:checkboxClickHandler();search();setIframeHeight();" style="margin:0px 0px 0px 0px 0px;">

    <html:form  method="post" styleId="Form1" target="f1" action="/simple_search"  onsubmit="return validate();" acceptCharset="utf-8">
       <table align="center" class="datagrid" dir="<%=rtl%>" width="100%" bgcolor="white" height="100%">
        <tr  ><td   valign="bottom" height="10%"   >





                                <img src="<%=request.getContextPath()%>/images/bp.PNG" alt="banner space"  border="0" align="<%=align%>" dir="<%=rtl%>" id="Image1" style="height:40px;width:150px;">
                                <br>


                            </td>
                            <td align="right" colspan="2" >
                                <img src="<%=request.getContextPath()%>/images/logo.png" alt=""  border="0" align="top" id="Image1" style="height:70px;width:160px;">
                </td>

            </tr>
            <tr>
                <td colspan="3">



       <a href="OpacLib.do?name=simple" dir="<%=rtl%>"   style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" ><%=resource.getString("opacmainframe.header.simple")%></a>&nbsp;|&nbsp;<a href="OpacLib.do?name=browse"  dir="<%=rtl%>"  style="font-weight: bold;text-decoration:none;font-family: Arial;color:blue;font-size: 11px" ><%=resource.getString("opacmainframe.header.browse")%></a>&nbsp;|&nbsp;<a href="OpacLib.do?name=additional" dir="<%=rtl%>" style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" ><%=resource.getString("opacmainframe.header.additional")%></a>&nbsp;|&nbsp;<a href="OpacLib.do?name=advance" dir="<%=rtl%>" style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" ><%=resource.getString("opacmainframe.header.advance")%></a>&nbsp;|&nbsp;<a href="OpacLib.do?name=isbn" dir="<%=rtl%>" style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" ><%=resource.getString("opacmainframe.header.isbn")%></a>&nbsp;|&nbsp;<a href="OpacLib.do?name=callno" dir="<%=rtl%>" style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" ><%=resource.getString("opacmainframe.header.callno")%></a>&nbsp;|&nbsp;<a href="<%=request.getContextPath()%>/OPAC/OpacLib.do?name=accno" dir="<%=rtl%>"  style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" ><%=resource.getString("opacmainframe.header.accessionno")%></a>&nbsp;|&nbsp;<a  href="./OpacLib.do?name=newarrival" dir="<%=rtl%>" style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px"><%=resource.getString("opacmainframe.mframe.newarrivals")%></a>&nbsp;|&nbsp;<a  href="../OPAC/Notice.do" style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" dir="<%=rtl%>"><%=resource.getString("opacmainframe.mframe.notices")%></a>&nbsp;|&nbsp;<a  href="../OPAC/Locations.do" style="text-decoration:none;font-family: Arial;font-size: 11px;color:blue" dir="<%=rtl%>"  ><%=resource.getString("opacmainframe.mframe.location")%></a>&nbsp;|&nbsp;<a  href="../OPAC/OpacLib.do?name=feedback" dir="<%=rtl%>" style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" ><%=resource.getString("opacmainframe.mframe.feedback")%></a>&nbsp;|&nbsp;
        <b style="color:blue;letter-spacing: 1px;text-decoration:none;font-family: Arial;font-size: 11px">
                  <a href="../OPAC/OpacLib.do?name=newmember" style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px"   dir="<%=rtl%>"  ><%=resource.getString("opacmainframe.mframe.memberregistration")%></a>&nbsp;|&nbsp;<a  href="../OPAC/OpacLib.do?name=myaccount&amp;p=t" style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px"   dir="<%=rtl%>"  >My Account</a>&nbsp;|&nbsp;<a href="../OPAC/OPACmain1.jsp" target="_top" style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" ><b style="color:blue" dir="<%=rtl%>" > <%=resource.getString("opacmainframe.header.home")%></b></a>&nbsp;|&nbsp;<a href="<%=request.getContextPath()%>/help.jsp"  dir="<%=rtl%>"  style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" ><b><%=resource.getString("opacmainframe.header.help")%></b></a>&nbsp;|&nbsp;
                    <%if(library_id==null){%>
      <a href="<%=request.getContextPath()%>/login.jsp" onclick=""  style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("opacmainframe.header.exit")%></a>
 <%}else{%><a href="<%=request.getContextPath()%>/admin/main.jsp" onclick=""  style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" ><%=resource.getString("opacmainframe.header.exit")%></a><%}%>

                    </b>



      </td>
            </tr>

            <tr   style="background-color: #BFDBFF;height: 50px;  background-image: url('<%=request.getContextPath()%>/images/banner_bg.jpg'); border:  solid 1px black;margin: 0px 0px 0px 0px"><td colspan="3" style="font-style: italic;font-size: 20px;color:white;" valign="middle" align="center">
           "Online Public Access Catalogue"</td>


            </tr>

           <tr ><td  style="color:red;border-bottom:  dotted 1px cyan;font-weight: bold;margin: 0px 0px 0px 0px" height="15px"  dir="<%=rtl%>"  align="center" colspan="3">
          <b color="white">	<%=resource.getString("opac.simplesearch.smpsearch")%></b>
        </td></tr>
           <html:hidden property="checkbox" styleId="checkbox" name="SimpleSearchActionForm"/>
          <html:hidden property="language" styleId="language" name="SimpleSearchActionForm"/>
        <% if(net.equalsIgnoreCase("true")){%>
        <tr dir="<%=rtl%>"><td width="12%">  <%=resource.getString("cataloguing.catbiblioentry.selectlang1")%></td><td width="25%">
        <table><tr><td>
        <div id='translControl'>
        
         <input type="checkbox" id="checkboxId"  onclick="javascript:checkboxClickHandler();javascript:DisBox();javascript:languageChangeHandler()">
        </div></td><td>
        <div id="MLI" style="visibility: block" >
          <select id="languageDropDown" class="selecthome" onchange="javascript:languageChangeHandler()">
          </select>
        </div>
        </td></tr></table>
      
        </td><td> <i>Please Select Desired Language In Which you want to Entered Search Keywords</i></td></tr>
        <%}else{%>
         <tr><td  dir="<%=rtl%>" colspan="3">
                 Sorry Cannot Support MLI Entry because of unavaliblibility of Net
         </td>
        </tr>
        <%}%>
        <tr><td  dir="<%=rtl%>" >
         <%=resource.getString("opac.simplesearch.field")%> </td><td  dir="<%=rtl%>" valign="top">
         <select name="CMBFIELD"  class="selecthome" size="1" id="CMBFIELD" dir="<%=rtl%>">
            <option value="any field" selected dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.anyfld")%></option>
            <option  value="mainEntry" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.auth")%></option>
            <option value="title" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.tit")%></option>
            <option value="publisherName" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.pub")%></option>
        </select>
        </td><td><i> Please Select Desired Field In Which you want to Search Keywords</i></td>
        </tr>
        <tr><td dir="<%=rtl%>" ><%=resource.getString("opac.simplesearch.enterwordorphrase")%></td><td><input type="text" dir="<%=rtl%>" id="TXTPHRASE" name="TXTPHRASE" class="keyboardInput" onfocus="statwords('Enter Author/Title/Publisher of the book')" onblur="loadHelp()" ></td>
        </tr>
        <tr>   <td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.connectwordas")%></td><td><select name="CMBCONN" dir="<%=rtl%>" size="1" id="CMBCONN" class="selecthome">
            <option selected dir="<%=rtl%>" value="or"><%=resource.getString("opac.simplesearch.or")%></option>
            <option dir="<%=rtl%>" value="and"><%=resource.getString("opac.simplesearch.and")%></option>
            </select></td>
        <td><i> Please Select Desired Connector to Connect Other Criteria with your Search Keywords</i></td>
        </tr>
        <tr ><td style="border-bottom: dashed 1px cyan;color:red;font-weight: bold" dir="<%=rtl%>"  align="<%=align%>" colspan="3" >&nbsp;<%=resource.getString("opac.simplesearch.restrictedby")%></td></tr>
         <tr>   <td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.library")%></td><td>
                      <html:select property="CMBLib" dir="<%=rtl%>"  styleClass="selecthome"  value="<%=lib_id%>"   styleId="CMBLib" onchange="search()">
                          <html:option value="all">All</html:option>
                         <html:options collection="libRs" property="libraryId"  labelProperty="libraryName"/>
                    </html:select>
        </td><td><i> Please Select Desired Library In Which You Want to Search Records</i></td>
        </tr>
        <tr><td><%=resource.getString("opac.simplesearch.sublibrary")%></td><td> <html:select styleClass="selecthome" property="CMBSUBLib" dir="<%=rtl%>"  styleId="SubLibary" value="<%=sublib_id%>">
                 <html:option value="all">All</html:option>
                </html:select></td>
        <td><i> Please Select Desired SubLibrary In Which You Want to Search Records</i></td></tr>
        <tr><td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.database")%></td><td>
             <select name="CMBDB" class="selecthome" dir="<%=rtl%>" size="1" id="CMBDB">
            <option value="combined" dir="<%=rtl%>" selected><%=resource.getString("opac.simplesearch.combnd")%></option>
            <option value="book" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.books")%></option>
            <option value="cd" dir="<%=rtl%>">CD/DVD ROM</option>
     <option value="vd" dir="<%=rtl%>">Electronic Document(Video/Motion Pictures)</option>
     <option value="ppt" dir="<%=rtl%>">Electronic Document(Presenatation)</option>
     <option value="au" dir="<%=rtl%>">Electronic Document(Sound Recording)</option>
     <option value="th" dir="<%=rtl%>">Thesis</option>
     <option value="ds" dir="<%=rtl%>">Dissertations</option>
            </select>
         </td><td><i> Please Select Desired Type of Documents for Which You Want to Search Records</i></td></tr>
         <tr><td><%=resource.getString("opac.simplesearch.pubyear")%></td><td>
         <table>
         <tr><td rowspan="4" dir="<%=rtl%>"></td>
         <td><select name="CMBYR" dir="<%=rtl%>" onChange="f()" size="1" id="CMBYR" class="selecthome">
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
         </td><td> <i>Please Specify Publishing Year Criteria If Applicable</i></td></tr>
        <tr><td colspan="3" class="header1" align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.sortby")%></td></tr>
        <tr><td><%=resource.getString("opac.simplesearch.field1")%></td><td><select class="selecthome" name="CMBSORT" size="1" dir="<%=rtl%>" id="CMBSORT">
                <option  value="mainEntry" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.auth")%></option>
                <option value="title" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.tit")%></option>
                <option value="publisherName" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.pub")%></option>
                </select></td><td><i> Please Select The Field on Which you want to Sort Records If Applicable</i></td></tr>
        <tr><td colspan="2" align="<%=align%>">
                <input type="submit" dir="<%=rtl%>" id="Button1" class="buttonhome" name="" value="<%=resource.getString("opac.simplesearch.find")%>" >
                <input type="reset" dir="<%=rtl%>" id="Button2" class="buttonhome" name="" value="<%=resource.getString("opac.browse.clear")%>">
        </td></tr>
        <tr><td    valign="top"   dir="<%=rtl%>" colspan="3">
                <IFRAME    name="f1" height="0px"  src="#" frameborder=0 style="margin: 0px 0px 0px 0px;"  scrolling="no" width="100%" id="f1"></IFRAME>


               </td></tr>
        <tr><td align="left" class="datagrid" valign="top" colspan="3">
                          <%=resource.getString("developedby")%>  &copy; <%=resource.getString("login.message.footer")%>
         &nbsp; follow us : <img src="<%=request.getContextPath()%>/images/blog.jpeg" height="16px" width="20px"/>
     <img src="<%=request.getContextPath()%>/images/facebook.jpeg" height="16px" width="20px"/>
     <img src="<%=request.getContextPath()%>/images/twitter.jpeg" height="16px" width="20px"/>
      <a href="http://www.youtube.com/user/DrAasimZafar?blend=15&ob=5#p/u/0/COwssqRU9Ao"><img src="<%=request.getContextPath()%>/images/youtube.jpeg" height="16px" width="40px"/></a>
            </td></tr>
        </table>
        
    </html:form>
</body>
</html>
