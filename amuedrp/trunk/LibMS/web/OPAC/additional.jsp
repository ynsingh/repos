<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Faraz Hasan" content="MCA,AMU">
<title>Additional Search...</title>
<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
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

    %>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
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

</head>
<body >
    
    <html:form styleId="F1" method="post" action="/OPAC/additional" onsubmit="return validate();">
        <table  align="<%=align%>" dir="<%=rtl%>" width="1200x" class="datagrid" style="border:solid 1px #e0e8f5;">



  <tr class="header"><td  width="1000px" dir="<%=rtl%>"  height="28px" align="center" colspan="2">


	   <%=resource.getString("opac.additional.additionalsearchtext")%>




        </td></tr>
  <tr style="background-color:#e0e8f5;"><td width="800px" dir="<%=rtl%>" >
          <table>
              <tr><td dir="<%=rtl%>"><%=resource.getString("opac.additional.author")%></td><td><input type="text" dir="<%=rtl%>" id="TXTAUTHOR"  name="TXTAUTHOR"></td></tr>
              <tr><td dir="<%=rtl%>"><%=resource.getString("opac.additional.title")%></td><td><input dir="<%=rtl%>" type="text" id="TXTTITLE" name="TXTTITLE"></td></tr>
              <tr><td dir="<%=rtl%>"><%=resource.getString("opac.additional.subject")%></td><td><input type="text" dir="<%=rtl%>" id="TXTSUBJECT"  name="TXTSUBJECT"></td></tr>
              <tr><td dir="<%=rtl%>"><%=resource.getString("opac.additional.otherfield")%></td><td><input dir="<%=rtl%>" type="text" id="TXTOTHER"  name="TXTOTHER"></td></tr>


    

          </table>
      </td>
      <td  dir="<%=rtl%>"  align="<%=align%>">
          <table >
              <tr><td dir="<%=rtl%>"> <%=resource.getString("opac.additional.connectas1")%> </td><td  valign="top" dir="<%=rtl%>">

         <select name="CMBCONN1" size="1" dir="<%=rtl%>" id="CMBCONN1" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier;font-size:13px;">
<option selected value="or" dir="<%=rtl%>">OR</option>
<option value="and" dir="<%=rtl%>">AND</option>
<option value="phrase" dir="<%=rtl%>">PHRASE</option>
</select>
     </td>

              </tr>
               <tr><td dir="<%=rtl%>"><%=resource.getString("opac.additional.connectas2")%> </td><td dir="<%=rtl%>"  valign="top">

         <select name="CMBCONN2" size="1" id="CMBCONN2" dir="<%=rtl%>" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="or" dir="<%=rtl%>">OR</option>
<option value="and" dir="<%=rtl%>">AND</option>
<option value="phrase" dir="<%=rtl%>">PHRASE</option>
</select>
     </td>

              </tr>
           <tr><td dir="<%=rtl%>"><%=resource.getString("opac.additional.connectas3")%></td><td  valign="top">

          <select name="CMBCONN3" size="1" id="CMBCONN3" dir="<%=rtl%>">
<option selected value="or" dir="<%=rtl%>">OR</option>
<option value="and" dir="<%=rtl%>">AND</option>
<option value="phrase" dir="<%=rtl%>">PHRASE</option>
</select>
     </td>

              </tr>
           <tr><td dir="<%=rtl%>"><%=resource.getString("opac.additional.connectas4")%> </td><td  valign="top">

        <select name="CMBCONN4" dir="<%=rtl%>" size="1" id="CMBCONN4" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="or" dir="<%=rtl%>">OR</option>
<option value="and" dir="<%=rtl%>">AND</option>
<option value="phrase" dir="<%=rtl%>">PHRASE</option>
</select>
     </td>

              </tr>




          </table></td></tr>
  <tr class="header" dir="<%=rtl%>"><td width="1000px"   align="<%=align%>" dir="<%=rtl%>">Restricted By</td><td align="<%=align%>" dir="<%=rtl%>">Sort By</td></tr>
   <tr style="background-color:#e0e8f5;">
       <td width="800px"   align="<%=align%>" dir="<%=rtl%>">
           <table  width="700px" dir="<%=rtl%>">
               <tr>
                   <td align="500px" dir="<%=rtl%>">
                    <table>
                        <tr>   
                            <td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.library")%></td>
                            <td dir="<%=rtl%>" align="<%=align%>">
                                <html:select property="CMBLib" value="<%=lib_id%>"  dir="<%=rtl%>"  tabindex="3"  styleId="CMBLib" onchange="search()">
                                     <html:option value="all">All</html:option>
                                        <html:options collection="libRs" property="libraryId" labelProperty="libraryName"/>
                                  </html:select>
                            </td>



                        </tr>
                        <tr>
                            <td dir="<%=rtl%>"><%=resource.getString("opac.additional.database")%></td>
                            <td>
                                <select name="CMBDB"  dir="<%=rtl%>" size="1" id="CMBDB">
                                     <option selected value="combined" dir="<%=rtl%>">COMBINED</option>
                                     <option value="book" dir="<%=rtl%>">BOOKS</option>
                                     <option value="cd" dir="<%=rtl%>">CDs</option>
                                </select>
                            </td>
                        </tr>

                        </table>
                   </td>
                   <td>              
                       <table>
                           <tr>
                               <td align="<%=align%>" colspan="3" dir="<%=rtl%>">SubLib
                       <html:select property="CMBSUBLib" dir="<%=rtl%>"  value="<%=sublib_id%>" styleId="SubLibary" >
                           <html:option value="all">All</html:option>
                           <html:options collection="sublib" property="id.sublibraryId" labelProperty="sublibName" />
                       </html:select>
                        </td></tr>
                           <table>
                           <tr>
                               <td dir="<%=rtl%>">Publishing Year</td>
                               <td rowspan="4" dir="<%=rtl%>"></td>
                               <td>
                                   <select name="CMBYR" onChange="f()" dir="<%=rtl%>" size="1" id="CMBYR" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
                                    <option selected value="all" dir="<%=rtl%>">ALL YEARS</option>
                                    <option value="between" dir="<%=rtl%>">BETWEEN</option>
                                    <option value="upto" dir="<%=rtl%>">UPTO</option>
                                    <option value="after" dir="<%=rtl%>">AFTER</option>
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
                     <td>

                             <table>
                           <tr>
                               <td dir="<%=rtl%>">Field</td><td> <select name="CMBSORT" size="1" dir="<%=rtl%>" id="CMBSORT">
<option  value="authorName" dir="<%=rtl%>">AUTHOR</option>
<option value="title" dir="<%=rtl%>">TITLE</option>
<option value="isbn10" dir="<%=rtl%>">ISBN</option>
<option value="publisher_name" dir="<%=rtl%>">PUBLISHER</option>
</select></td>
                           </tr></table>
       </td>
               </tr></table>

       </td>
 
      


  </tr>
  <tr><td>
<input type="submit" id="Button1" class="btn" name="" value="FIND">


<input type="reset" id="Button2" class="btn" name="" value="CLEAR">


      </td></tr>
      <tr class="header" dir="<%=rtl%>">
                               <td colspan="2" dir="<%=rtl%>">


                            <a name="tips" dir="<%=rtl%>">&nbsp;Search Tips</a>




                            <table class="datagrid" dir="<%=rtl%>" style="background-color:#e0e8f5;color:black" halign="right" border="0" cellpadding="2" cellspacing="0" width="100%" frame="hspaces" height="38" rules="rows">
    <colgroup width="15%"></colgroup><colgroup width="1%"></colgroup><colgroup width="90%"></colgroup>
    <tbody><tr>
    <th colspan="3" class="tipstext" dir="<%=rtl%>">
    	The user can make a Additional Search using this option. The fields to be entered are:
    </th>

    </tr>

    <tr>
        <td class="txt2" dir="<%=rtl%>">
    		Database
    </td>
    <td class="tipsheading" dir="<%=rtl%>">:</td>
    <td class="tipstext" dir="<%=rtl%>">
    		 Select from the combo box the database on which the search is to be made.
    </td>

    </tr>
<tr valign="top" dir="<%=rtl%>">
    	<td class="txt2" nowrap1="" dir="<%=rtl%>">
    		Library
    	</td>
    	<td class="tipsheading" dir="<%=rtl%>">:</td>
    	<td class="tipstext" dir="<%=rtl%>">
    		 Give the Library within which a search has to be made.
    	</td>

    </tr>
    <tr valign="top" dir="<%=rtl%>">
    	<td class="txt2" dir="<%=rtl%>">
    		Author
   	</td>
   	<td class="tipsheading" dir="<%=rtl%>">:</td>
   	<td class="tipstext" dir="<%=rtl%>">
    		Give the word(s) or phrase on the basis of which the search is to be made on Author Field.
    	</td>

    </tr>
    <tr valign="top" dir="<%=rtl%>">
    	<td class="txt2" dir="<%=rtl%>">
    		Title
   	</td>
   	<td class="tipsheading" dir="<%=rtl%>">:</td>
   	<td class="tipstext" dir="<%=rtl%>">
    		Give the word(s) or phrase on the basis of which the search is to be made on Title Field.
    	</td>

    </tr>
    <tr valign="top" dir="<%=rtl%>">
    	<td class="txt2" dir="<%=rtl%>">
    		Subject
   	</td>
   	<td class="tipsheading" dir="<%=rtl%>">:</td>
   	<td class="tipstext" dir="<%=rtl%>">
    		Give the word(s) or phrase on the basis of which the search is to be made on Subject Field.
    	</td>

    </tr>

    <tr valign="top" dir="<%=rtl%>">

    	<td class="txt2" dir="<%=rtl%>">
    		other fields
    	</td>
    	<td class="tipsheading" dir="<%=rtl%>">:</td>
    	<td class="tipstext" dir="<%=rtl%>">
    		 Give the word(s) or phrase on the basis of which the search is to be made on Given Field.
    	</td>

    </tr>

    <tr valign="top" dir="<%=rtl%>">
    	<td class="txt2" dir="<%=rtl%>">
    		Connect As
    	</td>
    	<td class="tipsheading" dir="<%=rtl%>">:</td>
    	<td class="tipstext" dir="<%=rtl%>">
    		 Select from the combo box the connector required between the search words.
    	</td>

    </tr>
    <tr valign="top" dir="<%=rtl%>">
    	<td class="txt2" nowrap1="" dir="<%=rtl%>">
    		Publication Year
    	</td>
    	<td class="tipsheading" dir="<%=rtl%>">:</td>
    	<td class="tipstext" dir="<%=rtl%>">
    		 Give the publishing year(s) within which a search has to be made.
    	</td>

    </tr>
<tr valign="top" dir="<%=rtl%>">
    	<td class="txt2" dir="<%=rtl%>">
    		Field
   	</td>
   	<td class="tipsheading" dir="<%=rtl%>">:</td>
   	<td class="tipstext" dir="<%=rtl%>">
    		Select from the combo box the field on which the sorting has to be made.
    	</td>

    </tr>
   <tr valign="top" dir="<%=rtl%>">
   	<td class="txt2" align="right" dir="<%=rtl%>">
   		Click Find
    	</td>
    	<td colspan="2" class="txt2" dir="<%=rtl%>">
    		and the result is displayed. Thus , a additional search can be made on any field, title-wise, author-wise or subject-wise.
    	</td>

   </tr></tbody></table>



                               </td></tr>

       </table>



</html:form>


</body>
</html>
