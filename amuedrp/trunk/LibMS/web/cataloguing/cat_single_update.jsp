<%--
    Document   : cat_accession_entry
    Created on : Feb 28, 2011, 5:15:03 PM
    Author     : Asif Iqubal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<html>
    <head>
<script>
            function send()
{
    window.location="<%=request.getContextPath()%>/cataloguing/cat_single_update_grid.jsp";
    return false;
}</script>
<%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
String msg1=(String) request.getAttribute("msg1");
String msg2=(String) request.getAttribute("msg2");
%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
            <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
    </head>
    <body>
        <html:form action="/updateSingle" method="post">
            <html:hidden property="library_id" name="BibliographicDetailEntryActionForm1" value="<%=library_id%>"/>
            <html:hidden property="sublibrary_id" name="BibliographicDetailEntryActionForm1" value="<%=sub_library_id%>" />
            <html:hidden property="record_no" name="BibliographicDetailEntryActionForm1"/>
            <html:hidden property="biblio_id" name="BibliographicDetailEntryActionForm1"/>
            <html:hidden property="call_no" name="BibliographicDetailEntryActionForm1"/>
            <html:hidden property="document_type" name="BibliographicDetailEntryActionForm1"/>
            <html:hidden property="title" name="BibliographicDetailEntryActionForm1"/>
            <html:hidden property="subtitle" name="BibliographicDetailEntryActionForm1"/>
            <html:hidden property="book_type" name="BibliographicDetailEntryActionForm1"/>
            <html:hidden property="subject" name="BibliographicDetailEntryActionForm1"/>
            <html:hidden property="alt_title" name="BibliographicDetailEntryActionForm1"/>
            <html:hidden property="edition" name="BibliographicDetailEntryActionForm1"/>
            <html:hidden property="no_of_copies" name="BibliographicDetailEntryActionForm1"/>
            <html:hidden property="ser_note" name="BibliographicDetailEntryActionForm1"/>
            <html:hidden property="statement_responsibility" name="BibliographicDetailEntryActionForm1"/>
            <html:hidden property="main_entry" name="BibliographicDetailEntryActionForm1" />
            <html:hidden property="publisher_name" name="BibliographicDetailEntryActionForm1"/>
            <html:hidden property="publication_place" name="BibliographicDetailEntryActionForm1"/>
            <html:hidden property="added_entry" name="BibliographicDetailEntryActionForm1"/>
            <html:hidden property="added_entry0" name="BibliographicDetailEntryActionForm1"/>
            <html:hidden property="publishing_year" name="BibliographicDetailEntryActionForm1"/>
            <html:hidden property="added_entry1" name="BibliographicDetailEntryActionForm1"/>
            <html:hidden property="added_entry2" name="BibliographicDetailEntryActionForm1"/>
            <html:hidden property="LCC_no" name="BibliographicDetailEntryActionForm1"/>
            <html:hidden property="isbn13" name="BibliographicDetailEntryActionForm1"/>
            <html:hidden property="isbn10" name="BibliographicDetailEntryActionForm1"/>
            <table width="100%" border="0">
            <tr><td colspan="8" bgcolor="#E0E8F5" align="center" height="25px;" class="headerStyle">Accessioning of Item</td></tr>
            <tr><td align="right">Volume No:</td><td><html:text property="volume_no" name="BibliographicDetailEntryActionForm1"/></td><td align="right">Accession No<a class="star">*</a>:</td><td><html:text property="accession_no" name="BibliographicDetailEntryActionForm1"/></td>
                <td align="right">Location:</td>
                <td>
                               <html:select property="location" name="BibliographicDetailEntryActionForm1">
            <html:options collection="mixlist"  labelProperty="locationName" property="locationId"></html:options>
            </html:select>
                </td><td align="right">Shelving Location:</td><td><html:text property="shelving_location" name="BibliographicDetailEntryActionForm1"/></td></tr>
            <tr><td align="right">Index:</td><td><html:text property="index_no" name="BibliographicDetailEntryActionForm1"/></td><td align="right">No of Pages:</td><td><html:text property="no_of_pages" name="BibliographicDetailEntryActionForm1"/></td><td align="right">Physical Width:</td><td><html:text property="physical_width" name="BibliographicDetailEntryActionForm1"/></td><td align="right">Bind Type:</td><td><html:text property="bind_type" name="BibliographicDetailEntryActionForm1"/></td></tr>
            
            <tr><td height="20px;" colspan="8"></td></tr>
            <tr><td></td><td></td><td colspan="2" align="center"><input name="button" type="submit" value="Update" class="txt1"/></td><td align="right"><input name="button" type="submit" onclick="return send()" value="Back" class="txt1"/></td><td></td><td height="5px" colspan="2" class="mandatory" align="center"><a class="star">*</a>indicated field is mandatory</td></tr>
            <tr><td colspan="8" height="30px;"></td></tr>
            <tr><td colspan="8" align="left">
                    <%  if(msg1!=null)
    {%>
   <span style="font-size:12px;font-weight:bold;color:blue;"><%=msg1%></span>
<%}%>
                                    <%  if(msg2!=null)
    {%>
   <span style="font-size:12px;font-weight:bold;color:red;"><%=msg2%></span>
<%}%></td></tr>
            </table>
   </html:form>
    </body>
</html>
