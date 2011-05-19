<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>

<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Faraz Hasan" content="MCA,AMU">
<title>Advance Search...</title>

<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
.rows          { background-color: white;border: solid 1px blue; }
     .hiliterows    { background-color: pink; color: #000000; font-weight: bold;border: solid 1px blue; }
     .alternaterows { background-color: #efefef; }
     .header        { background-color: #c0003b; color: #FFFFFF;font-family:Tahoma;font-size: 12px;font-weight: bold;text-decoration: none;padding-left: 10px; }

     .datagrid      {  font-family: arial; font-size: 9pt;
	    font-weight: normal;}
     .item{ padding-left: 10px;}




</style>
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

for (var i = 0; i < em.length ; i++)
{
var ndValue = em[i].firstChild.nodeValue;
var ndValue1=em1[i].firstChild.nodeValue;
newOpt = document.getElementById('SubLibrary').appendChild(document.createElement('option'));
newOpt.value = ndValue;
newOpt.text = ndValue1;


}

}

</script>
<SCRIPT language="JAVASCRIPT">
     function f()
    {
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

    %>


</head>

<body>
    <%-- <%if(page.equals(true)){%>--%>

     <html:form  method="post" action="/OPAC/advance_search" onsubmit="return validate();">



            <table  align="<%=align%>" dir="<%=rtl%>" width="1200x" class="datagrid"  style="border:solid 1px #e0e8f5;">



  <tr class="header" dir="<%=rtl%>"><td  width="1000px" dir="<%=rtl%>"  height="28px" align="center" colspan="2">


		  <%=resource.getString("opac.advance.advancesearchtext")%>




        </td></tr>
  <tr style="background-color:#e0e8f5;"><td width="800px" dir="<%=rtl%>">
          <table width="800px" dir="<%=rtl%>">
              <tr><td width="130px" dir="<%=rtl%>"><%=resource.getString("opac.advance.seachkeyword")%></td><td><input name="TXTPHRASE1" type="text" dir="<%=rtl%>">
</td></tr>
              <tr>   <td><%=resource.getString("opac.simplesearch.connectwordas")%></td><td> <select name="CMB1" size="1" dir="<%=rtl%>">
    <option value="or" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.or")%></option>
    <option value="and" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.and")%></option>
  </select>

               

                  </td><td align="<%=align%>" dir="<%=rtl%>">  <select name="CMBF1" size="1">
<option value="or" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.or")%></option>
<option value="and" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.and")%></option>
</select></td>

      </tr>

          </table>
      </td>
      <td    align="<%=align%>" valign="top" dir="<%=rtl%>">
          <table >
              <tr><td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.field")%> </td><td rowspan="2" valign="top" dir="<%=rtl%>">

          <select name="CMBFIELD1" size="1" dir="<%=rtl%>" >
<option selected value="title" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.anyfld")%></option>
<option value="author" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.auth")%></option>
<option value="title" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.tit")%></option>
<option value="isbn10" dir="<%=rtl%>">ISBN</option>
<option value="subject" dir="<%=rtl%>"><%=resource.getString("opac.advance.subject")%></option>

</select>

     </td>

              </tr></table></td></tr>
   <tr style="background-color:#e0e8f5;" dir="<%=rtl%>"><td width="800px" dir="<%=rtl%>" >
          <table width="800px" dir="<%=rtl%>">
              <tr><td width="130px" dir="<%=rtl%>"><%=resource.getString("opac.advance.seachkeyword")%></td><td dir="<%=rtl%>"> <input name="TXTPHRASE2" dir="<%=rtl%>"  type="text">
</td></tr>
              <tr>   <td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.connectwordas")%></td><td> <select name="CMB2" size="1" dir="<%=rtl%>">
    <option value="or" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.or")%></option>
    <option value="and" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.and")%></option>
                      </select></td><td align="<%=align%>" dir="<%=rtl%>">      <select name="CMBF2" size="1" dir="<%=rtl%>">
<option value="or" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.or")%></option>
<option value="and" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.and")%></option>
</select>
</td>

      </tr>

          </table>
      </td>
      <td    align="<%=align%>" valign="top" dir="<%=rtl%>">
          <table dir="<%=rtl%>">
              <tr><td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.anyfld")%> </td><td rowspan="2" dir="<%=rtl%>" valign="top">

         <select name="CMBFIELD2" size="1"  dir="<%=rtl%>">
<option selected value="author" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.anyfld")%></option>
<option value="author" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.auth")%></option>
<option value="title" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.tit")%></option>
<option value="isbn10" dir="<%=rtl%>">ISBN</option>
<option value="subject" dir="<%=rtl%>"><%=resource.getString("opac.advance.subject")%></option>

</select>


     </td>

              </tr></table></td></tr>
    <tr style="background-color:#e0e8f5;" dir="<%=rtl%>"><td width="800px"  dir="<%=rtl%>">
          <table width="800px" dir="<%=rtl%>">
              <tr><td width="130px" dir="<%=rtl%>"><%=resource.getString("opac.advance.seachkeyword")%></td><td dir="<%=rtl%>"><input name="TXTPHRASE3" type="text" dir="<%=rtl%>">

</td></tr>
              <tr>   <td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.connectwordas")%></td><td dir="<%=rtl%>"> <select name="CMB3" size="1" dir="<%=rtl%>">
    <option value="or" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.or")%></option>
    <option value="and" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.and")%></option>
  </select></td><td>     <select name="CMBF3" size="1" dir="<%=rtl%>">
<option value="or" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.or")%></option>
<option value="and" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.and")%></option>
</select>
</td>

      </tr>

          </table>
      </td>
      <td dir="<%=rtl%>"   align="<%=align%>" valign="top">
          <table dir="<%=rtl%>">
              <tr><td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.anyfld")%> </td><td dir="<%=rtl%>" rowspan="2" valign="top">

        <select name="CMBFIELD3" size="1" dir="<%=rtl%>">
<option selected value="subject" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.anyfld")%></option>
<option value="author" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.auth")%></option>
<option value="title" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.tit")%></option>
<option value="isbn10" dir="<%=rtl%>">ISBN</option>
<option value="subject" dir="<%=rtl%>"><%=resource.getString("opac.advance.subject")%></option>

</select>


     </td>

              </tr></table></td></tr>
  <tr class="header" dir="<%=rtl%>"><td width="1000px" dir="<%=rtl%>"  align="<%=align%>" ><%=resource.getString("opac.simplesearch.restrictedby")%></td><td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.sortby")%></td></tr>
   <tr style="background-color:#e0e8f5;" dir="<%=rtl%>"><td width="800px" dir="<%=rtl%>"  align="<%=align%>">
           <table  width="800px" dir="<%=rtl%>"><tr><td align="<%=align%>" dir="<%=rtl%>">
          <table>
              <tr><td ><%=resource.getString("opac.simplesearch.database")%></td><td>
                      <select name="CMBDB" size="1" id="CMBDB" dir="<%=rtl%>">
<option value="combined" selected dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.combnd")%></option>
    <option value="book" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.books")%></option>
    <option value="cd" dir="<%=rtl%>">CDs</option>

</select>
                  </td></tr>
              <tr>   <td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.library")%></td><td>

                      <html:select property="CMBLib" dir="<%=rtl%>" value="<%=lib_id%>"  tabindex="3"  styleId="CMBLib" onchange="search()">
                          <html:option value="all">All</html:option>
     <html:options collection="libRs" property="libraryId" labelProperty="libraryName"/>
 </html:select>
</td>
<td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.sublibrary")%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                       <html:select property="CMBSUBLib" dir="<%=rtl%>" value="<%=sublib_id%>"  styleId="SubLibrary" >
                           <html:option value="all">All</html:option>
                           <html:options collection="sublib" property="id.sublibraryId" labelProperty="sublibName" />
                       </html:select>
      </tr>

          </table>
                   </td><td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.pubyear")%>
                                   <br/>
                       <table>
                           <tr><td rowspan="4" dir="<%=rtl%>"></td><td><select name="CMBYR" dir="<%=rtl%>" onChange=f() size="1" id="CMBYR" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">

<option value="all" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.allyear")%></option>
<option value="between" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.between")%></option>
<option value="upto" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.upto")%></option>
<option value="after" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.after")%></option>
</select></td><td dir="<%=rtl%>">
<input type="text" dir="<%=rtl%>" id="TXTYR1"  name="TXTYR1" style="width:50px"></td><td>
<input type="text" id="TXTYR2" dir="<%=rtl%>" name="TXTYR2" style="width:50px">
</td></tr>



                       </table>




                   </td></tr></table>
      </td>
      <td align="<%=align%>" dir="<%=rtl%>">
           <table dir="<%=rtl%>">
                           <tr dir="<%=rtl%>"><td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.field1")%></td><td> <select name="CMBSORT" dir="<%=rtl%>" size="1" id="CMBSORT">
<option  value="author_main" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.auth")%></option>
<option value="title" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.tit")%></option>
<option value="isbn10" dir="<%=rtl%>">ISBN</option>
<option value="publisher_name" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.pub")%></option>
</select></td>
                           </tr></table>


      </td>

  </tr>
  <tr dir="<%=rtl%>"><td dir="<%=rtl%>">

<input id="Button2" class="btn" name="" dir="<%=rtl%>" value="<%=resource.getString("opac.simplesearch.find")%>"  type="submit">
<input id="Button1" name="" class="btn" dir="<%=rtl%>" value="<%=resource.getString("opac.browse.clear")%>" type="reset">


      </td></tr>
           <tr class="header" dir="<%=rtl%>">
                               <td colspan="2" dir="<%=rtl%>">


                            <a name="tips" dir="<%=rtl%>">&nbsp;<%=resource.getString("opac.simplesearch.searchtip")%></a>




                            <table class="datagrid" dir="<%=rtl%>" style="background-color:#e0e8f5;color:black;" halign="right" border="0" cellpadding="2" cellspacing="0" width="100%" frame="hspaces" height="38" rules="rows">
    <colgroup width="15%"></colgroup><colgroup width="1%" dir="<%=rtl%>"></colgroup><colgroup width="90%" dir="<%=rtl%>"></colgroup>
    <tbody><tr>
    <th colspan="3" class="tipstext" dir="<%=rtl%>">
    	<%=resource.getString("opac.advance.t1")%>
    </th>

    </tr>

    <tr>
        <td class="txt2" dir="<%=rtl%>">
    		<%=resource.getString("opac.simplesearch.library")%>
    </td>
    <td class="tipsheading" dir="<%=rtl%>">:</td>
    <td class="tipstext" dir="<%=rtl%>">
    		 <%=resource.getString("opac.simplesearch.t11")%>
    </td>

    </tr>
      <tr>
        <td class="txt2" dir="<%=rtl%>">
    		<%=resource.getString("opac.simplesearch.database")%>
    </td>
    <td class="tipsheading" dir="<%=rtl%>">:</td>
    <td class="tipstext" dir="<%=rtl%>">
    		<%=resource.getString("opac.simplesearch.t3")%>
    </td>

    </tr>

    <tr valign="top" dir="<%=rtl%>">
    	<td class="txt2" dir="<%=rtl%>">
    		<%=resource.getString("opac.simplesearch.field1")%>
   	</td>
   	<td class="tipsheading" dir="<%=rtl%>">:</td>
   	<td class="tipstext" dir="<%=rtl%>">
    		<%=resource.getString("opac.simplesearch.t5")%>
    	</td>

    </tr>

    <tr valign="top" dir="<%=rtl%>">

    	<td class="txt2" dir="<%=rtl%>">
    		<%=resource.getString("opac.advance.seachkeyword")%>
    	</td>
    	<td class="tipsheading" dir="<%=rtl%>">:</td>
    	<td class="tipstext" dir="<%=rtl%>">
    		 <%=resource.getString("opac.simplesearch.t7")%>
    	</td>

    </tr>

    <tr valign="top" dir="<%=rtl%>">
    	<td class="txt2" dir="<%=rtl%>">
    		<%=resource.getString("opac.simplesearch.connectwordas")%>
    	</td>
    	<td class="tipsheading" dir="<%=rtl%>">:</td>
    	<td class="tipstext" dir="<%=rtl%>">
    		 <%=resource.getString("opac.simplesearch.t9")%>
    	</td>

    </tr>
     <tr valign="top" dir="<%=rtl%>">
    	<td class="txt2" dir="<%=rtl%>">
    		<%=resource.getString("opac.simplesearch.field1")%>
    	</td>
    	<td class="tipsheading" dir="<%=rtl%>">:</td>
    	<td class="tipstext" dir="<%=rtl%>">
    		 <%=resource.getString("opac.simplesearch.t15")%>
    	</td>

    </tr>
    <tr valign="top" dir="<%=rtl%>">
    	<td class="txt2" nowrap1="" dir="<%=rtl%>">
    		<%=resource.getString("opac.simplesearch.pubyear")%>
    	</td>
    	<td class="tipsheading" dir="<%=rtl%>">:</td>
    	<td class="tipstext" dir="<%=rtl%>">
    		 <%=resource.getString("opac.simplesearch.t13")%>
    	</td>

    </tr>

   <tr valign="top" dir="<%=rtl%>">
   	<td class="txt2" align="right" dir="<%=rtl%>">
   	     <%=resource.getString("opac.simplesearch.t16")%>
    	</td>
    	<td colspan="2" class="txt2" dir="<%=rtl%>">
    		<%=resource.getString("opac.advance.t2")%>
    	</td>

   </tr></tbody></table>



                               </td></tr>

       </table>


   </html:form>
  



<%--
     <%}else{%>

   
<html:form  method="post" action="/OPAC/advance_search" onsubmit="return validate();">
    
    
     <table  align="left" width="1200x" class="datagrid"  style="border:solid 1px #e0e8f5;">



  <tr class="header"><td  width="1000px"  height="28px" align="center" colspan="2">


		  <%=resource.getString("opac.advance.advancesearchtext")%>




        </td></tr>
  <tr style="background-color:#e0e8f5;">

      <td    align="left" width="200px" valign="top">
          <table >
              <tr>
                  <td>in Field </td>
                  <td rowspan="2" valign="top">

          <select name="CMBFIELD1" size="1" >
<option selected value="title">ANY FIELD</option>
<option value="author">AUTHOR</option>
<option value="title">TITLE</option>
<option value="isbn10">ISBN</option>
<option value="subject">SUBJECT</option>


</select>

     </td>

              </tr></table></td>
              <td width="800px" align="right">
          <table width="800px" align="right">
              <tr>
                  <td align="right" colspan="2"><input name="TXTPHRASE1" type="text">
</td><td align="left">Search Keyword as&nbsp;</td></tr>
              <tr> <td align="center" width="500px"><select name="CMBF1" size="1">
<option value="or">OR</option>
<option value="and">AND</option>
</select></td>
<td align="right"> <select name="CMB1" size="1">
    <option value="or">OR</option>
    <option value="and">AND</option>
  </select>



                  </td> <td align="left">Connected Word As</td>
      </tr>

          </table>
      </td>
  </tr>
  <tr style="background-color:#e0e8f5;">

      <td    align="left" width="200px" valign="top">
          <table >
              <tr>
                  <td>in Field </td>
                  <td rowspan="2" valign="top">

          <select name="CMBFIELD2" size="1" >
<option selected value="title">ANY FIELD</option>
<option value="author">AUTHOR</option>
<option value="title">TITLE</option>
<option value="isbn10">ISBN</option>
<option value="subject">SUBJECT</option>


</select>

     </td>

              </tr></table></td>
              <td width="800px" align="right">
          <table width="800px" align="right">
              <tr>
                  <td align="right" colspan="2"><input name="TXTPHRASE2" type="text">
</td><td align="left">Search Keyword as&nbsp;</td></tr>
              <tr> <td align="center" width="500px"><select name="CMBF2" size="1">
<option value="or">OR</option>
<option value="and">AND</option>
</select></td>
<td align="right"> <select name="CMB2" size="1">
    <option value="or">OR</option>
    <option value="and">AND</option>
  </select>



                  </td> <td align="left">Connected Word As</td>
      </tr>

          </table>
      </td>
  </tr>
  <tr style="background-color:#e0e8f5;">

      <td    align="left" width="200px" valign="top">
          <table >
              <tr>
                  <td>in Field </td>
                  <td rowspan="2" valign="top">

          <select name="CMBFIELD3" size="1" >
<option selected value="title">ANY FIELD</option>
<option value="author">AUTHOR</option>
<option value="title">TITLE</option>
<option value="isbn10">ISBN</option>
<option value="subject">SUBJECT</option>

</select>

     </td>

              </tr></table></td>
              <td width="800px" align="right">
          <table width="800px" align="right">
              <tr>
                  <td align="right" colspan="2"><input name="TXTPHRASE3" type="text">
</td><td align="left">Search Keyword as&nbsp;</td></tr>
              <tr> <td align="center" width="500px"><select name="CMBF3" size="1">
<option value="or">OR</option>
<option value="and">AND</option>
</select></td>
<td align="right"> <select name="CMB3" size="1">
    <option value="or">OR</option>
    <option value="and">AND</option>
  </select>



                  </td> <td align="left">Connected Word As</td>
      </tr>

          </table>
      </td>
  </tr>
 

    <tr class="header">
      <td align="right">Sort By</td><td width="1000px"   align="right" >&nbsp;Restricted By</td></tr>
   <tr style="background-color:#e0e8f5;">

      <td align="left">
           <table>
                           <tr><td>Field</td><td> <select name="CMBSORT" size="1" id="CMBSORT">
<option  value="author_main">AUTHOR</option>
<option value="title">TITLE</option>
<option value="isbn10">ISBN</option>
<option value="publisher_name">PUBLISHER</option>
</select></td>
                           </tr></table>

<td align="left">Sub Library&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                       <html:select property="CMBSUBLib"  styleId="SubLibrary" >
                           <html:options collection="sublib" property="id.sublibraryId" labelProperty="sublibName" />
                       </html:select>
      </td>
  <td width="800px"   align="right">
           <table  width="800px" ><tr>
                  <td align="left"><%=resource.getString("opac.additional.publicationyear")%>
                                   <br/>
                       <table >
                           <tr><td rowspan="4"></td><td><select name="CMBYR" onChange=f() size="1" id="CMBYR" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected value="all">Select Year</option>
<option value="all">ALL YEARS</option>
<option value="between">BETWEEN</option>
<option value="upto">UPTO</option>
<option value="after">AFTER</option>
                                   </select></td><td align="right">
<input type="text" id="TXTYR1"  name="TXTYR1" style="width:50px"></td><td align="right">
<input type="text" id="TXTYR2"  name="TXTYR2" style="width:50px">
</td></tr>



                       </table>




                   </td>
                <td align="500px" align="right">
          <table>
              <tr><td ><%=resource.getString("opac.simplesearch.database")%></td><td>
                      <select name="CMBDB" size="1" id="CMBDB">
<option value="combined" selected>COMBINED</option>
    <option value="book">BOOKS</option>
    <option value="cd">CDs</option>

</select>
                  </td></tr>
                   <tr>   <td ><%=resource.getString("opac.simplesearch.library")%></td><td>
                  <select name="CMBLib" size="1" id="CMBLib">
    
</select>


                  </td>

      </tr>

          </table>
                   </td>
               </tr></table>
      </td>
  </tr>




 
  <tr><td  colspan="2" align="right">

<input id="Button2" name="" class="btn" value="<%=resource.getString("opac.advance.find")%>"  type="submit">
<input id="Button1" name="" class="btn" value="<%=resource.getString("opac.advance.clear")%>" type="reset">


      </td></tr>
    <tr class="header">
       <td colspan="2" align="right">


                            <a name="tips">&nbsp;Search Tips</a>




                            <table class="datagrid" style="background-color:#e0e8f5;color:black" halign="left" border="0" cellpadding="2" cellspacing="0" width="100%" frame="hspaces" height="38" rules="rows">
    <colgroup width="79%"></colgroup><colgroup width="1%"></colgroup><colgroup width="20%"></colgroup>
    <tbody><tr>
    <th colspan="3" class="tipstext">
    	The user can make a advanced search using this option. The fields to be entered are:
    </th>

    </tr>

    <tr>
        <td class="tipstext" align="right">
    		 Select from the combo box the database on which the search is to be made.
    </td>


    <td class="tipsheading">:</td>
     <td class="txt2" align="right">
    		Database
    </td>
    </tr>
    <tr>
        <td class="tipstext" align="right">
    		 Select from the combo box the Library on which the search is to be made.
    </td>


    <td class="tipsheading">:</td>
     <td class="txt2" align="right">
    		Library
    </td>
    </tr>

    <tr valign="top">


   	<td class="tipstext" align="right">
    		Select from the combo box the field on which the Sorting is to be made.
    	</td>
        <td class="tipsheading">:</td>
<td class="txt2" align="right">
    		Field
   	</td>
    </tr>

    <tr valign="top">



    	<td class="tipstext" align="right">
    		 Give the word(s) or phrase on the basis of which the search is to be made.
    	</td>
        <td class="tipsheading">:</td>
<td class="txt2" align="right">
    		Search Keyword As
    	</td>
    </tr>

    <tr valign="top">


    	<td class="tipstext" align="right">
    		 Select from the combo box the connector required between the search words.
    	</td>
        <td class="tipsheading">:</td>
<td class="txt2" align="right">
    		Connected Word As
    	</td>
    </tr>
    <tr valign="top">


    	<td class="tipstext" align="right">
    		 Give the publishing year(s) within which a search has to be made.
    	</td>
        <td class="tipsheading">:</td>
<td class="txt2"  align="right">
    		Publication Year
    	</td>
    </tr>
    <tr valign="top">


    	<td class="tipstext" align="right">
    		  Select from the combo box the Field required on which searching has to made.
    	</td>
        <td class="tipsheading">:</td>
<td class="txt2"  align="right">
    		In Field
    	</td>
    </tr>

   <tr valign="top">

    	<td colspan="2" class="txt2" align="right">
    		Click on Find and the result is displayed. Thus , a advanced search can be made on  title-wise, author-wise or subject-wise etc.
    	</td>

   </tr></tbody></table>


       

       </table>



</html:form>


     <%}%>--%>
</body>
</html>
