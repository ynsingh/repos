<%--
    Document   : cat_biblio_entry
    Created on : Jan 13, 2011, 12:02:47 PM
    Author     : Asif Iqubal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.List"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>

<%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
String msg1=(String) request.getAttribute("msg1");
//List bookList=(List)session.getAttribute("DocumentCategory");

%>
<script type="text/javascript">
function send()
{
    window.location="<%=request.getContextPath()%>/cataloguing/cat_old_title.jsp";
    return false;
}
function generateRow() {
       var d=document.getElementById("my_div");
       var no = document.getElementById("my_div").childNodes;
        if (no.length<5){
       d.innerHTML+="<input type='text' name='added_entry"+(no.length/2)+"' Class='textBoxWidth'/><br>";
    }else{
       // d.innerHTML+="</td>";
   }

        }
function changeIt()
{
var i = 1;
for(i=1;i>=3;i++)
{
    my_div.innerHTML = my_div.innerHTML +"<input type='text' name='mytext'+ i>"}
}
</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bibliographic Detail Entry Form</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
         <script src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
         <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
         <script type="text/javascript" src="<%=request.getContextPath()%>/js/animatedcollapse.js"></script>
<script type="text/javascript">
   // var i=getElementById(id);
  //  for(i=1;i<=3;i++)
//{animatedcollapse.addDiv(i, 'fade=1,height=20px')}
animatedcollapse.addDiv('1', 'fade=1,height=20px')
animatedcollapse.addDiv('2', 'fade=1,height=20px')
animatedcollapse.addDiv('3', 'fade=1,height=20px')
animatedcollapse.ontoggle=function($, divobj, state){ //fires each time a DIV is expanded/contracted
	//$: Access to jQuery
	//divobj: DOM reference to DIV being expanded/ collapsed. Use "divobj.id" to get its ID
	//state: "block" or "none", depending on state
}
animatedcollapse.init()

</script>
    </head>
    <body>
    <%if(msg1!=null){%>   <span style=" position:absolute; top: 90px; font-size:12px;font-weight:bold;color:red;" ><%=msg1%></span>  <%}%>
      
    <table border="1" class="table" width="80%" style="position: absolute; top: 20%; left: 10%">
        <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" colspan="2" ><b>Bibliographic Detail Entry</b></td></tr>      
            <tr><td>
                    <table width="100%">
                        <tr><td>    <html:form method="post" action="/catOldBiblio">
        <table width="100%" border="0" cellspacing="4" cellpadding="1" align="left">
                        <tr>
                        <html:hidden property="library_id" name="BibliographicDetailEntryActionForm" value="<%=library_id%>" />
                        <html:hidden property="sublibrary_id" name="BibliographicDetailEntryActionForm" value="<%=sub_library_id%>" /><td></td>
                        <html:hidden property="accession_type" name="BibliographicDetailEntryActionForm" value="Old"/>
                         <html:hidden property="document_type" name="BibliographicDetailEntryActionForm" value="Book"/>
                         <html:hidden property="no_of_copies" name="BibliographicDetailEntryActionForm"/>
                        </tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr>
    <td width="150" align="right" class="txtStyle"><strong>Document Catagory<a class="star">*</a>:</strong> </td>
    <td>
        <html:select property="book_type" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth"  tabindex="1">
            <html:options collection="DocumentCategory"  labelProperty="documentCategoryName" property="id.documentCategoryId" name="DocumentCategory"></html:options>
  </html:select>
     <br><span class="err">   <html:messages id="err_name" property="book_type">
        <bean:write name="err_name" />
    </html:messages></span></td>

</tr>
<tr><td height="2px"></td>
</tr>
<tr>
       <td align="right" class="txtStyle"><strong>Subtitle:</strong></td>
       <td><html:text property="subtitle" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="3" /></td>

  </tr>

  <tr><td height="2px"></td>
</tr>
<tr>
      <td align="right" class="txtStyle"><strong>Main Entry<a class="star">*</a>:</strong></td>
      <td><html:text property="main_entry" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="5" />
      <br><span class="err">   <html:messages id="err_name" property="main_entry">
        <bean:write name="err_name" />
    </html:messages></span>
  </td>
   </tr>
 <tr><td height="2px"></td>
</tr>
   <tr>
 <td align="right" class="txtStyle"><strong>Publisher Name:</strong></td>
 <td><html:text property="publisher_name" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="10" /></td>
     </tr>
  <tr><td height="2px"></td>
</tr>
     <tr>
  <td align="right" class="txtStyle"><strong>Publishing Year:</strong></td>
  <td><html:text property="publishing_year" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="12" />
  </td>
  </tr>
  <tr><td height="2px"></td>
</tr>
  <tr>
    <td align="right" class="txtStyle"><strong>ISBN-10: </strong></td>
    <td><html:text  property="isbn10" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="14" /></td>
   </tr>
  <tr><td height="2px"></td>
</tr>
   <tr>
    <td align="right" class="txtStyle"><strong>Edition:</strong></td>
    <td><html:text property="edition" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="16" /></td>
 
  </tr>
  <tr><td height="2px"></td>
</tr>
  <tr>
   <td align="right" class="txtStyle"><strong>Alternate Title:</strong></td>
   <td><html:text property="alt_title" name="BibliographicDetailEntryActionForm"  styleClass="textBoxWidth" tabindex="18" /></td>
  </tr>
  <tr><td height="2px"></td>
</tr>
  <tr>
      <td align="right" class="txtStyle"><strong>Series:</strong></td>
      <td><html:text property="ser_note" styleClass="textBoxWidth" name="BibliographicDetailEntryActionForm" tabindex="20"/></td>
  </tr>
  <tr><td height="2px"></td>
</tr>
  <tr>
   <td align="right" class="txtStyle"><strong>Abstract:</strong></td>
   <td><html:textarea rows="5" cols="20" property="thesis_abstract" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="22" /></td>
   </tr>
<tr><td colspan="5" height="5px"></td>
</tr>
<tr><td colspan="5" height="5px"></td>
</tr>
<tr><td></td><td></td></tr>
<tr><td colspan="5" height="10px"></td>
</tr>
</table>
</td>
 <td>
     <table>
         <tr>
    <td width="150" align="right" class="txtStyle"><strong>Title:</strong> </td>
    <td><html:text readonly="true" property="title" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="2" />
    </td>
         </tr>
         <tr><td height="2px"></td>
</tr>
         <tr>
  <td align="right" class="txtStyle"><strong>Statement Responsiblity<a class="star">*</a>:</strong></td>
  <td><html:text property="statement_responsibility" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="4" />
      <br><span class="err">   <html:messages id="err_name" property="statement_responsibility">
        <bean:write name="err_name" />
    </html:messages></span>
  </td>
         </tr>
         <tr><td height="2px"></td>
</tr>
         <tr>
        <td align="right" class="txtStyle"><strong>Added Entry:</strong></td>
        <td><html:text property="added_entry" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="6" />
                
     <input type="button" onclick="javascript:animatedcollapse.show(['1','2','3'])" value="+"/>
     <input type="button" onclick="javascript:animatedcollapse.hide(['1','2','3'])" value="-"/></td></tr>
            <!--<input type="button" onclick="generateRow()" value="+"/><div id="my_div"></div></td>-->
            <tr>
                <td></td>
                <td>
                    <div id="1" style="display: none;">
                        <html:text property="added_entry0" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="7"/>
</div>
<div id="2" style="display: none;">
    <html:text property="added_entry1" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="8"/>
</div>
<div id="3" style="display: none;">
    <html:text property="added_entry2" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="9"/>
</div>
 </td>
            </tr>
  <tr><td height="2px"></td>
</tr>
     <tr><td align="right" class="txtStyle"><strong>Publication Place :</strong></td>
         <td><html:text property="publication_place" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="11" /></td>
</tr>
<tr><td height="2px"></td>
</tr>
<tr> <td align="right" class="txtStyle"><strong>LCC No:</strong></td>
         <td><html:text property="LCC_no" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="13" />
         </td></tr>
<tr><td height="2px"></td>
</tr>
<tr> <td align="right" class="txtStyle"><strong>ISBN-13: </strong></td>
         <td><html:text  property="isbn13"  name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="15" /></td>
</tr>
<tr><td height="2px"></td>
</tr>
<tr><td align="right" class="txtStyle"><strong>Call No<a class="star">*</a>:</strong></td>
         <td><html:text property="call_no" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="17" />
        <br><span class="err">   <html:messages id="err_name" property="call_no">
        <bean:write name="err_name" />
    </html:messages></span>
  </td></tr>
<tr><td height="2px"></td>
</tr>
<tr><td align="right" class="txtStyle"><strong>Subjects:</strong></td>
         <td><html:text property="subject" name="BibliographicDetailEntryActionForm"  styleClass="textBoxWidth" tabindex="19" /></td>
</tr>
<tr><td height="2px"></td>
</tr>
<tr> <td align="right" class="txtStyle"><strong>Notes:</strong></td>
          <td><html:textarea rows="5" cols="20" property="notes" name="BibliographicDetailEntryActionForm"  styleClass="textBoxWidth" tabindex="21" /></td>
</tr>
     </table></td></tr>
           
              <tr><td colspan="5" height="5px" class="mandatory" align="right"><a class="star">*</a>indicated fields are mandatory</td></tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr>
    <td align="center" colspan="4">
    <input name="button" type="submit" value="Save" class="txt1"/>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="button" type="submit" value="Save and go for accessioning" style="font-family: Arial,Helvetica,sans-serif; font-size:12px; color:brown; width: auto;"/>
    &nbsp;&nbsp;&nbsp;&nbsp;<input name="button" type="submit" onclick="return send()" value="Cancel" class="txt1"/>
    </td>
           </html:form>
</tr><tr><td colspan="5" height="5px"></td>
</tr>
                    </table> </td></tr>

    </table>
    </body>
</html>

