<!-- ADDITIONAL SEARCH JSP-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="java.sql.ResultSet"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/helpdemo.js"></script>
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
    List rs = (List)session.getAttribute("libRs");
       

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
          <%--    var x=document.getElementById('TXTAUTHOR').value;
             var y=document.getElementById('TXTTITLE').value;
              var z=document.getElementById('TXTSUBJECT').value;
               var a=document.getElementById('TXTOTHER').value;

        if(x=='' && y=='' && z=='' && a=='')
            {
                alert("Please Enter KeyWord No to Search Title");
                return false;

            }--%>
      }
      function f()
      {
        search();
        if(document.getElementById("F1").CMBYR.value=="upto" || document.getElementById("F1").CMBYR.value=="after")
         {
            document.getElementById("F1").TXTYR1.disabled=false;
            document.getElementById("F1").TXTYR1.style.backgroundColor = "#ffffff";
            document.getElementById("F1").TXTYR2.disabled=true;
            document.getElementById("F1").TXTYR2.style.backgroundColor = "#eeeeee";

	   }
	 if(document.getElementById("F1").CMBYR.value=="all")
         {
            document.getElementById("F1").TXTYR1.disabled=true;
            document.getElementById("F1").TXTYR1.style.backgroundColor = "#eeeeee";
            document.getElementById("F1").TXTYR2.disabled=true;
            document.getElementById("F1").TXTYR2.style.backgroundColor = "#eeeeee";

	   }
        if(document.getElementById("F1").CMBYR.value=="between")
         {
            document.getElementById("F1").TXTYR1.disabled=false;
            document.getElementById("F1").TXTYR1.style.backgroundColor = "#ffffff";
            document.getElementById("F1").TXTYR2.disabled=false;
            document.getElementById("F1").TXTYR2.style.backgroundColor = "#ffffff";

	   }

    }
    function validate()
    {
           var x=document.getElementById('TXTAUTHOR').value;
             var y=document.getElementById('TXTTITLE').value;
              var z=document.getElementById('TXTSUBJECT').value;
//               var a=document.getElementById('TXTOTHER').value;

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
        if(document.getElementById('checkboxId2').checked)
        {
            
           document.getElementById("checkbox").value="Checked";
           
        }
        else
        {
           // window.location.reload();
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
      
        var ids = [ "TXTAUTHOR","TXTTITLE","TXTSUBJECT","TXTYR1","TXTYR2"];
      
             
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

      // Handler for STATE_CHANGED event which makes sure checkbox status
      // reflects the transliteration enabled or disabled status.
      function transliterateStateChangeHandler(e) {
        document.getElementById('checkboxId2').checked = e.transliterationEnabled;
      }
        // Handler for checkbox's click event.  Calls toggleTransliteration to toggle
        // the transliteration state.
        //GOOGLE API
        function checkboxClickHandler() {
        window.status="Press F1 for Help";
        if(document.getElementById('checkboxId2').checked==true)
        {
        document.getElementById('MLI').style.display = 'block';
        }
        else{
            document.getElementById('MLI').style.display = 'none';
            document.getElementById('TXTAUTHOR').style.textAlign = "left";
             document.getElementById('TXTTITLE').style.textAlign = "left";
              document.getElementById('TXTSUBJECT').style.textAlign = "left";
               document.getElementById('TXTOTHER').style.textAlign = "left";
        }
        transliterationControl.toggleTransliteration();
      }

    </script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/keyboard/keyboard.js" charset="UTF-8"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/keyboard/keyboard_002.js" charset="UTF-8"></script>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/keyboard/keyboard.css"/>
</head>
<jsp:include page="opacheader.jsp"/>
<body onload="search();checkboxClickHandler();"  style="margin:0px 0px 0px 0px 0px;">


    <html:form styleId="F1" method="post" action="/OPAC/additional" onsubmit="return validate();" acceptCharset="utf-8">
        <table   align="center" dir="<%=rtl%>" width="50%" class="datagrid" style="border:dashed 1px cyan">
  <tr class="header1"><td style="border-bottom: dashed 1px cyan;"  width="100%"align="center" dir="<%=rtl%>"  height="28px" align="center" colspan="3">
          <font color="red"><%=resource.getString("opac.additional.additionalsearchtext")%></font>
        </td></tr>
  
           <html:hidden property="checkbox" styleId="checkbox" name="AdditionalSearchActionForm"/>
           <html:hidden property="language" styleId="language" name="AdditionalSearchActionForm"/>
      <% if(net.equalsIgnoreCase("true")){%>
        <tr dir="<%=rtl%>"><td >
        <table><tr><td>
      <%=resource.getString("cataloguing.catbiblioentry.selectlang1")%> </td><td> <div id='translControl'>

         <input type="checkbox" id="checkboxId2"  onclick="javascript:DisBox();javascript:checkboxClickHandler();javascript:languageChangeHandler()">
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
      
  <tr><td  dir="<%=rtl%>" width="50%" valign="top">
          <table width="100%">
              <tr><td dir="<%=rtl%>"><%=resource.getString("opac.additional.author")%></td><td><input type="text" dir="<%=rtl%>" id="TXTAUTHOR"  name="TXTAUTHOR" class="keyboardInput" onfocus="statwords('Enter Author Name Keyword')" onblur="loadHelp()" ></td></tr>
              <tr><td dir="<%=rtl%>"><%=resource.getString("opac.additional.title")%></td><td><input dir="<%=rtl%>" type="text" id="TXTTITLE" name="TXTTITLE" class="keyboardInput" onfocus="statwords('Enter Title  Keyword')" onblur="loadHelp()" ></td></tr>
              <tr><td dir="<%=rtl%>"><%=resource.getString("opac.additional.subject")%></td><td><input type="text" dir="<%=rtl%>" id="TXTSUBJECT"  name="TXTSUBJECT" class="keyboardInput"  onfocus="statwords('Enter Subject Keyword')" onblur="loadHelp()" ></td></tr>
              <%--<tr><td dir="<%=rtl%>"><%=resource.getString("opac.additional.otherfield")%></td><td><input dir="<%=rtl%>" type="text" id="TXTOTHER"  name="TXTOTHER" class="keyboardInput" onfocus="statwords('Enter Other Keyword')" onblur="loadHelp()" ></td></tr>--%>
          </table>
      </td>
      <td  dir="<%=rtl%>"  align="<%=align%>" valign="top" >
          <table   width="100%">
              <tr><td  dir="<%=rtl%>"> <%=resource.getString("opac.additional.connectas1")%> </td><td  valign="top" dir="<%=rtl%>">

         <select name="CMBCONN1" class="selecthome" size="1" dir="<%=rtl%>" id="CMBCONN1" >
        <option selected value="or" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.or")%></option>
        <option value="and" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.and")%></option>
        <%--<option value="phrase" dir="<%=rtl%>"><%=resource.getString("opac.additional.phrase")%></option>--%>
        </select>
     </td>
              </tr>
               <tr><td dir="<%=rtl%>"><%=resource.getString("opac.additional.connectas2")%> </td><td dir="<%=rtl%>"  valign="top">
         <select name="CMBCONN2" class="selecthome" size="1" id="CMBCONN2" dir="<%=rtl%>" >
        <option selected value="or" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.or")%></option>
        <option value="and" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.and")%></option>
        <%--<option value="phrase" dir="<%=rtl%>"><%=resource.getString("opac.additional.phrase")%></option>--%>
        </select>
     </td>
              </tr>
           <tr><td dir="<%=rtl%>"><%=resource.getString("opac.additional.connectas3")%></td><td  valign="top">
          <select name="CMBCONN3" class="selecthome" size="1" id="CMBCONN3" dir="<%=rtl%>">
            <option selected value="or" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.or")%></option>
            <option value="and" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.and")%></option>
            <%--<option value="phrase" dir="<%=rtl%>"><%=resource.getString("opac.additional.phrase")%></option>--%>
            </select>
     </td>
              </tr>
           <%--<tr><td dir="<%=rtl%>"><%=resource.getString("opac.additional.connectas4")%> </td><td  valign="top">
        <select name="CMBCONN4" class="selecthome" dir="<%=rtl%>" size="1" id="CMBCONN4">
            <option selected value="or" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.or")%></option>
            <option value="and" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.and")%></option>
            <option value="phrase" dir="<%=rtl%>"><%=resource.getString("opac.additional.phrase")%></option>
        </select>
     </td>
              </tr>--%>
          </table></td></tr>
        <tr dir="<%=rtl%>"><td colspan="2" align="<%=align%>" dir="<%=rtl%>" style="color:red;font-weight: bold;"><%=resource.getString("opac.simplesearch.restrictedby")%></td><td align="<%=align%>"  dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.sortby")%></td></tr>
        <tr style="">
        <td   colspan="2" align="<%=align%>" dir="<%=rtl%>">
           <table   align="<%=align%>" dir="<%=rtl%>">
               <tr>
                   <td  dir="<%=rtl%>">
                    <table align="<%=align%>" dir="<%=rtl%>">
                        <tr>   
                            <td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.library")%></td>
                            <td dir="<%=rtl%>" align="<%=align%>">
                                <html:select styleClass="selecthome" property="CMBLib" value="<%=lib_id%>"  dir="<%=rtl%>"  tabindex="3"  styleId="CMBLib" onchange="search()">
                                     <html:option value="all">All</html:option>
                                        <html:options collection="libRs" property="libraryId" labelProperty="libraryName"/>
                                  </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td dir="<%=rtl%>"><%=resource.getString("opac.additional.database")%></td>
                            <td>
                                <select name="CMBDB" class="selecthome" dir="<%=rtl%>" size="1" id="CMBDB">
                                     <option selected value="combined" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.combnd")%></option>
                                     <option value="book" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.books")%></option>
                                  <option value="cd" dir="<%=rtl%>">CD/DVD ROM</option>
     <option value="vd" dir="<%=rtl%>">Electronic Document(Video/Motion Pictures)</option>
     <option value="ppt" dir="<%=rtl%>">Electronic Document(Presenatation)</option>
     <option value="au" dir="<%=rtl%>">Electronic Document(Sound Recording)</option>
     <option value="th" dir="<%=rtl%>">Thesis</option>
     <option value="ds" dir="<%=rtl%>">Dissertations</option>
                                </select>
                            </td>
                        </tr>
                        </table>
                   </td>
                   <td>              
                       <table align="<%=align%>" dir="<%=rtl%>">
                           <tr>
                               <td align="<%=align%>" colspan="3" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.sublibrary")%>
                       <html:select property="CMBSUBLib" styleClass="selecthome" dir="<%=rtl%>"  value="<%=sublib_id%>" styleId="SubLibary" >
                         <html:option value="all">All</html:option>
                       </html:select>
                        </td></tr>
                           <table>
                           <tr>
                               <td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.pubyear")%></td>
                               <td rowspan="4" dir="<%=rtl%>"></td>
                               <td>
                                   <select name="CMBYR" class="selecthome" onChange="f()" dir="<%=rtl%>" size="1" id="CMBYR">
                                    <option selected value="all" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.allyear")%></option>
                                    <option value="between" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.between")%></option>
                                    <option value="upto" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.upto")%></option>
                                    <option value="after" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.after")%></option>
                                </select>
                               </td>
                               <td>
               <input type="text" id="TXTYR1"  name="TXTYR1" dir="<%=rtl%>" disabled="true" style="width:50px">
                               </td>
<td>
    <input type="text" id="TXTYR2"  name="TXTYR2" disabled="true" dir="<%=rtl%>" style="width:50px">
</td></tr>
 </table>
                       </table>
                   </td>
                   <td valign="top" align="middle">
                             <table align="<%=align%>" dir="<%=rtl%>">
                           <tr>
                               <td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.field1")%></td><td> <select class="selecthome" name="CMBSORT" size="1" dir="<%=rtl%>" id="CMBSORT">
        <option  value="mainEntry" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.auth")%></option>
        <option value="title" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.tit")%></option>
        <option value="isbn10" dir="<%=rtl%>">ISBN</option>
        <option value="publisherName" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.pub")%></option>
        </select></td>
                           </tr></table>
       </td>
               </tr>

               <tr  dir="<%=rtl%>"><td  align="<%=align%>" dir="<%=rtl%>" colspan="3">
                   <input type="submit" class="buttonhome" id="Button1"  dir="<%=rtl%>" name="" value="<%=resource.getString("opac.simplesearch.find")%>">
                    <input type="reset" id="Button2"  class="buttonhome" dir="<%=rtl%>" name="" value="<%=resource.getString("opac.browse.clear")%>">
                   </td></tr>


           </table>
       </td>
   </tr>
       </table>
</html:form>
    <jsp:include page="opacfooter.jsp"/>
 
</body>
</html>
