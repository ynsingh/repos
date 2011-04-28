<%--
    Document   : cat_biblio_entry
    Created on : Jan 13, 2011, 12:02:47 PM
    Author     : Asif Iqubal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>

<%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
%>
<script type="text/javascript">

function send()
{
    window.location="<%=request.getContextPath()%>/cataloguing/cat_title_delete_grid.jsp";
    return false;
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
    </head>
    <body>


    <table border="1" class="table" width="80%" style="position: absolute; top: 20%; left: 10%">
        <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" colspan="2" ><b>Bibliographic Detail Entry</b></td></tr>
            <tr><td>
                    <table width="100%">
                        <tr><td>    <html:form method="post" action="/catOldBiblio" target="_blank">
        <table width="100%" border="0" cellspacing="4" cellpadding="1" align="left">
                        <tr>
                        <html:hidden property="library_id" name="BibliographicDetailEntryActionForm1" value="<%=library_id%>" />
                        <html:hidden property="sublibrary_id" name="BibliographicDetailEntryActionForm1" value="<%=sub_library_id%>" /><td></td>
                        <html:hidden property="accession_type" name="BibliographicDetailEntryActionForm1" value="Old"/>
                         <html:hidden property="document_type" name="BibliographicDetailEntryActionForm1" value="Book"/>
                         <html:hidden property="no_of_copies" name="BibliographicDetailEntryActionForm1"/>
                        </tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr>
    <td width="150" align="right" class="txtStyle"><strong>Document Catagory:</strong> </td>
    <td>
        <html:text property="book_type" readonly="true" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth"/>
</td>

</tr>
<tr><td height="2px"></td>
</tr>
<tr>
       <td align="right" class="txtStyle"><strong>Subtitle:</strong></td>
    <td><html:text property="subtitle" readonly="true" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" /></td>

  </tr>
<tr><td height="2px"></td>
</tr>
  <tr>
      <td align="right" class="txtStyle"><strong>Main Entry:</strong></td>
  <td><html:text property="main_entry" readonly="true" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" />

  </td>
   </tr>
<tr><td height="2px"></td>
</tr>
   <tr>
 <td align="right" class="txtStyle"><strong>Publisher Name:</strong></td>
 <td><html:text property="publisher_name" readonly="true" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" /></td>
     </tr>
<tr><td height="2px"></td>
</tr>
     <tr>
  <td align="right" class="txtStyle"><strong>Publishing Year:</strong></td>
  <td><html:text property="publishing_year" readonly="true" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" />
  </td>
  </tr>
<tr><td height="2px"></td>
</tr>
  <tr>
    <td align="right" class="txtStyle"><strong>ISBN-10: </strong></td>
    <td><html:text  property="isbn10" readonly="true" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" /></td>
   </tr>
<tr><td height="2px"></td>
</tr>
   <tr>
    <td align="right" class="txtStyle"><strong>Edition:</strong></td>
  <td><html:text property="edition" readonly="true" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" /></td>

  </tr>
<tr><td height="2px"></td>
</tr>
  <tr>
   <td align="right" class="txtStyle"><strong>Alternate Title:</strong></td>
  <td><html:text property="alt_title" readonly="true" name="BibliographicDetailEntryActionForm1"  styleClass="textBoxWidth" /></td>
  </tr>
<tr><td height="2px"></td>
</tr>
  <tr>
      <td align="right" class="txtStyle"><strong>Series:</strong></td>
      <td><html:text property="ser_note" styleClass="textBoxWidth" name="BibliographicDetailEntryActionForm"/></td>
  </tr>
<tr><td height="2px"></td>
</tr>
  <tr>
   <td align="right" class="txtStyle"><strong>Abstract:</strong></td>
   <td><html:textarea rows="5" cols="20" property="thesis_abstract" readonly="true" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" /></td>
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
    <td><html:text readonly="true" property="title" readonly="true" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" />
    </td>
         </tr>
         <tr><td height="2px"></td>
</tr>
         <tr>
  <td align="right" class="txtStyle"><strong>Statement Responsiblity:</strong></td>
  <td><html:text property="statement_responsibility" readonly="true" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" />

  </td>
         </tr>
         <tr><td height="2px"></td>
</tr>
         <tr>
        <td align="right" class="txtStyle"><strong>Added Entry:</strong></td>
        <td><html:text property="added_entry" readonly="true" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" />
            <input type="button" onclick="javascript:animatedcollapse.show(['1','2','3'])" value="+"/>
     <input type="button" onclick="javascript:animatedcollapse.hide(['1','2','3'])" value="-"/></td></tr>
       <tr>
                <td></td>
                <td>
                    <div id="1" style="display: none;">
                        <html:text property="added_entry0" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" readonly="true"/>
</div>
<div id="2" style="display: none;">
    <html:text property="added_entry1" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" readonly="true"/>
</div>
<div id="3" style="display: none;">
    <html:text property="added_entry2" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" readonly="true"/>
</div>
 </td>
            </tr>
     <tr><td height="2px"></td>
</tr>
            <tr><td align="right" class="txtStyle"><strong>Publication Place :</strong></td>
  <td><html:text property="publication_place" readonly="true" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" /></td>
</tr>
<tr><td height="2px"></td>
</tr>
<tr> <td align="right" class="txtStyle"><strong>LCC No:</strong></td>
  <td><html:text property="LCC_no" readonly="true" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" />
         </td></tr>
<tr><td height="2px"></td>
</tr>
<tr> <td align="right" class="txtStyle"><strong>ISBN-13: </strong></td>
  <td><html:text  property="isbn13" readonly="true"  name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" /></td>
</tr>
<tr><td height="2px"></td>
</tr>
<tr><td align="right" class="txtStyle"><strong>Call No:</strong></td>
  <td><html:text property="call_no" readonly="true" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" />

  </td></tr>
<tr><td height="2px"></td>
</tr>
<tr><td align="right" class="txtStyle"><strong>Subjects:</strong></td>
  <td><html:text property="subject" readonly="true" name="BibliographicDetailEntryActionForm1"  styleClass="textBoxWidth" /></td>
</tr>
<tr><td height="2px"></td>
</tr>
<tr> <td align="right" class="txtStyle"><strong>Notes:</strong></td>
  <td><html:textarea rows="5" cols="20" readonly="true" property="notes" name="BibliographicDetailEntryActionForm1"  styleClass="textBoxWidth" /></td>
</tr>
     </table></td></tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr>
    <td align="center" colspan="4">
    &nbsp;&nbsp;&nbsp;&nbsp;<input name="button" type="submit" onclick="return send()" value="Back" class="txt1"/>
    </td>
           </html:form>
</tr><tr><td colspan="5" height="5px"></td>
</tr>
                    </table> </td></tr>    </table>
    </body>
</html>

