
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

    %>
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

                fun1();

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
<script language="javascript">
function fun()
{
search();
document.getElementById("Form1").action="browse.do";
document.getElementById("Form1").method="post";
document.getElementById("Form1").target="f1";
document.getElementById("Form1").submit();
}
function fun1()
{
    document.getElementById("Form1").action="browse.do";
document.getElementById("Form1").method="post";
document.getElementById("Form1").target="f1";
document.getElementById("Form1").submit();
}

</script>
</head>
<body onload="fun1()">
    
    <html:form action="/OPAC/browse"  styleId="Form1" target="f1">
   <table  align="<%=align%>" dir="<%=rtl%>" width="1200x" class="datagrid" style="border:solid 1px #e0e8f5;">



  <tr class="header"><td  width="1000px" dir="<%=rtl%>"  height="28px" align="center" colspan="2">


	<%=resource.getString("opac.browse.browsesearch")%>	




        </td></tr>
  <tr style="background-color:#e0e8f5;"><td width="800px" dir="<%=rtl%>" >
          <table>
              <tr><td dir="<%=rtl%>"><%=resource.getString("opac.browse.enterstartingkeyword")%></td><td><input  name="TXTTITLE" type="text" dir="<%=rtl%>" onkeyup="fun()"></td></tr>
             

          </table>
      </td>
      <td dir="<%=rtl%>"   align="<%=align%>" valign="top">
          <table >
              <tr><td dir="<%=rtl%>"> <%=resource.getString("opac.simplesearch.field")%> </td><td rowspan="2" dir="<%=rtl%>" valign="top">


<select dir="<%=rtl%>" name="CMBFIELD" onChange="fun()" size="1">
<option value="any field" selected dir="<%=rtl%>">ANY FIELD</option>
<option value="authorName" dir="<%=rtl%>">AUTHOR</option>
<option value="title" dir="<%=rtl%>">TITLE</option>
<option value="publisherName" dir="<%=rtl%>">PUBLISHER</option>

</select>
     </td>

              </tr></table></td></tr>
  <tr class="header"><td width="1000px" dir="<%=rtl%>"  align="<%=align%>" ><%=resource.getString("opac.simplesearch.restrictedby")%></td><td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.sortby")%></td></tr>
   <tr style="background-color:#e0e8f5;" dir="<%=rtl%>"> <td width="800px" dir="<%=rtl%>"  align="<%=align%>">
           <table  width="800px" dir="<%=rtl%>"><tr><td align="<%=align%>" dir="<%=rtl%>">
          <table>
              <tr><td dir="<%=rtl%>"><%=resource.getString("opac.browse.database")%></td><td>
                    <select name="CMBDB" dir="<%=rtl%>" onChange="fun1()" size="1">
<option selected value="combined" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.combnd")%></option>
    <option value="book" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.books")%></option>
     <option value="cd" dir="<%=rtl%>">CDs</option>
  
  
</select>
                  </td><td dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.library")%></td>
                  <td>
                      <html:select property="CMBLib" dir="<%=rtl%>"  tabindex="3" value="<%=library_id%>"  styleId="CMBLib" onchange="fun()">
                           <html:option value="all">All</html:option>
                            <html:options collection="libRs" property="libraryId" labelProperty="libraryName"/>
                      </html:select>
                  </td>
                  <td align="left" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.sublibrary")%><html:select property="CMBSUBLib" dir="<%=rtl%>" value="<%=sublibrary_id%>" styleId="SubLibary" onchange="fun1()">
                          <html:option value="all">All</html:option>
                          <html:options collection="sublib" property="id.sublibraryId" labelProperty="sublibName" />
                       </html:select>



</tr>
            
          </table>
                   </td><td align="<%=align%>" dir="<%=rtl%>">
                      




                   </td></tr></table>
      </td>
      <td align="<%=align%>" dir="<%=rtl%>">
           <table>
                           <tr><td dir="<%=rtl%>"><%=resource.getString("opac.browse.title")%></td><td dir="<%=rtl%>"> <select name="CMBSORT" dir="<%=rtl%>" size="1" onChange="fun()" id="CMBSORT">
<option value="title" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.auth")%></option>
<option  value="authorName" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.field1")%></option>
<option value="isbn10" dir="<%=rtl%>">ISBN</option>
<option value="publisherName" dir="<%=rtl%>"><%=resource.getString("opac.simplesearch.pub")%></option>
</select>
</td>
                           </tr></table>


      </td>

  </tr>
  <tr><td>


<input type="submit" id="Button1" name="" dir="<%=rtl%>" class="btn" value="<%=resource.getString("opac.simplesearch.find")%>">
<input type="reset" id="Button2" name="" dir="<%=rtl%>" class="btn" value="<%=resource.getString("opac.browse.clear")%>">


      </td></tr>
 <tr><td  height="400px" valign="top" colspan="2"  dir="<%=rtl%>">

             <IFRAME  name="f1"  src="#" frameborder=0 height="400px" width="1200px" scrolling="no"  id="f1"></IFRAME>


      </td></tr>
      

       </table>
   

        

    


 </html:form>


 <%--<%}else{%>
 <html:form action="/OPAC/browse"  styleId="Form1">

        <table  align="left" width="1200x" class="datagrid" style="border:solid 1px #e0e8f5;">



  <tr class="header"><td  width="1000px"   align="center" colspan="2">


		Browse Search




        </td></tr>
  <tr style="background-color:#e0e8f5;">
      <td    align="left" width="200px" valign="top">
          <table width="200px">
              <tr><td>in Field </td><td rowspan="2" valign="top">
<select name="CMBFIELD" onChange="fun()" size="1">
<option value="any field" selected>ANY FIELD</option>
<option value="authorName">AUTHOR</option>
<option value="title">TITLE</option>
<option value="publisherName">PUBLISHER</option>

</select>
        
     </td>

              </tr></table></td>
  <td align="right">
          <table>
              <tr><td >Keyword</td><td><input  name="TXTTITLE" type="text" onkeyup="fun()"></td></tr>
             

          </table>
      </td>
  </tr>
  <tr class="header"><td align="right">Sort By</td><td width="1000px"   align="right" >&nbsp;Restricted By</td></tr>
    <tr style="background-color:#e0e8f5;">

      <td align="left">
           <table>
                           <tr><td>Field</td><td> <select name="CMBSORT" size="1" id="CMBSORT">
<option  value="authorName">AUTHOR</option>
<option value="title">TITLE</option>
<option value="isbn10">ISBN</option>
<option value="publisherName">PUBLISHER</option>
</select></td>
                           </tr></table>


      </td>
  
                <td align="500px" align="right">
          <table>
              <tr><td ><%=resource.getString("opac.simplesearch.database")%></td><td>
                      <select name="CMBDB" size="1" id="CMBDB">
<option value="combined" selected>COMBINED</option>
    <option value="book">BOOKS</option>
    <option value="cd">CDs</option>

</select>
                  </td><td ><%=resource.getString("opac.simplesearch.library")%></td><td>
                  <html:select property="CMBLib"  tabindex="3"  styleId="CMBLib" onchange="search()">
     <html:options collection="libRs" property="libraryId" labelProperty="libraryName"/>
 </html:select>

</td>

<td align="left">SubLib <select  name="CMBSUBLib"size="1" id="SUBLIB" onChange="fun()">
                        <option selected value="all">All</option>
                       <option value="amu">Main Library</option>
                       <option  value="csl">Computer Science Libray</option>
                       <option  value="phl">Physics Seminar Libray</option>
                       <option  value="chl">Chemistry Seminar Library</option>
                       </select>
    </td>          
</tr>
                   
          </table>
                   </td>
               </tr></table>
     

  <tr><td colspan="2" align="right">



<input type="reset" id="Button2" class="btn" name="" value="CLEAR">
<input type="submit" id="Button1" class="btn" name="" value="FIND">

      </td>

 
  
<tr><td  height="400px" valign="top" colspan="2" >

             <IFRAME  name="f1"  src="#" frameborder=0 height="400px" width="1200px" scrolling="no"  id="f1"></IFRAME>


      </td></tr>

       







 </html:form>
 <%}%>--%>
 
</body>
</html>