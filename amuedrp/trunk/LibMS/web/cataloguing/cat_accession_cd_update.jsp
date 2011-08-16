<%--
    Document   : cat_accession_entry
    Created on : Feb 28, 2011, 5:15:03 PM
    Author     : Asif Iqubal
--%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="java.io.*"   %>
    <%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
    <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
    <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>
<html>
    <head>
        <script>
            function send()
{
    window.location="<%=request.getContextPath()%>/cataloguing/cat_accession.jsp";
    return false;
}</script>
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
String msg1=(String) request.getAttribute("msg1");
%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
             <style>
    th a:link      { text-decoration: none; color: black }
     th a:visited   { text-decoration: none; color: black }
     .rows          { background-color: white }
     .hiliterows    { background-color: pink; color: #000000; font-weight: bold }
     .alternaterows { background-color: #efefef }
     .header        { background-color: #c0003b; color: #FFFFFF;font-weight: bold }

     .datagrid      { border: 1px solid #C7C5B2; font-family: arial; font-size: 9pt;
	    font-weight: normal }

</style>
    <link href="<%=request.getContextPath()%>/css/newformat.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />
    </head>

    <body>
        <html:form action="/ownaccessionEntry1" method="post">
            <html:hidden property="library_id" name="BibliographicDetailEntryActionForm" value="<%=library_id%>"/>
            <html:hidden property="sublibrary_id" name="BibliographicDetailEntryActionForm" value="<%=sub_library_id%>" />
            <html:hidden property="biblio_id" name="BibliographicDetailEntryActionForm"/>
            <html:hidden property="document_type" name="BibliographicDetailEntryActionForm" value="CD"/>
            <table width="100%" border="0" style="position: absolute; top: 20%">
            <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" colspan="8" ><%=resource.getString("cataloguing.cataccessionentry.bibliodetail")%></td></tr>
            <tr><td colspan="8" height="5px;"></td></tr>
             <tr><td bgcolor="#AFC7C7" colspan="4" align="center"><strong><%=resource.getString("cataloguing.cataccessionentry.titleinfo")%></strong></td><td bgcolor="#CFECEC" colspan="4" align="center"><strong>Disc Information:</strong></td></tr>
                <tr><td align="right"><%=resource.getString("cataloguing.catoldtitleentry1.title")%>:</td><td><html:text property="title" name="BibliographicDetailEntryActionForm" readonly="true" /></td><td align="right"><%=resource.getString("cataloguing.catoldtitleentry1.subtitle")%>:</td><td><html:text property="subtitle" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="right">Disk Type:</td><td><html:text property="type_of_disc" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="right"><%=resource.getString("cataloguing.catoldtitleentry1.subject")%>:</td><td><html:text property="subject" name="BibliographicDetailEntryActionForm" readonly="true"/></td></tr>
                <tr><td align="right"><%=resource.getString("cataloguing.catoldtitleentry1.alternatetitle")%>:</td><td><html:text property="alt_title" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="<%=align%>"><%=resource.getString("cataloguing.catoldtitleentry1.documentcategory")%>:</td><td><html:text property="book_type" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="right"><%=resource.getString("cataloguing.catoldtitleentry1.edition")%>:</td><td><html:text property="edition" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="right"><%=resource.getString("cataloguing.cataccessionentry.nocopy")%>:</td><td><html:text property="no_of_copies" name="BibliographicDetailEntryActionForm" readonly="true"/></td></tr>
                <tr><td bgcolor="#AFC7C7" colspan="4" align="center"><strong><%=resource.getString("cataloguing.catoldtitleentry1.statementresponsibility")%></strong></td><td bgcolor="#CFECEC" colspan="4" align="center"><strong><%=resource.getString("cataloguing.cataccessionentry.pubinfo")%></strong></td></tr>
                <tr><td align="right"><%=resource.getString("cataloguing.catoldtitleentry1.statementresponsibility")%>:</td><td><html:text property="statement_responsibility" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="right"><%=resource.getString("cataloguing.catoldtitleentry1.mainentry")%>:</td><td><html:text property="main_entry" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="right"><%=resource.getString("cataloguing.catoldtitleentry1.publishername")%>:</td><td><html:text property="publisher_name" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="right"><%=resource.getString("cataloguing.catoldtitleentry1.publicationplace")%>:</td><td><html:text property="publication_place" name="BibliographicDetailEntryActionForm" readonly="true"/></td></tr>
                <tr><td align="right"><%=resource.getString("cataloguing.catoldtitleentry1.addedentry")%>:</td><td><html:text property="added_entry" name="BibliographicDetailEntryActionForm" readonly="true"/></td>&nbsp;<td><html:text property="added_entry0" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td></td>
                <tr><td></td><td><html:text property="added_entry1" name="BibliographicDetailEntryActionForm" readonly="true"/></td>&nbsp;<td><html:text property="added_entry2" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td></td><td></td><td></td><td></td><td></td></tr>
                <tr><td bgcolor="#AFC7C7" colspan="8" align="center">Title Identification</td></tr>
                <tr><td align="right"><%=resource.getString("cataloguing.catoldtitleentry1.callno")%>:</td><td><html:text property="call_no" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="right"><%=resource.getString("cataloguing.catoldtitleentry1.isbn10")%>:</td><td><html:text property="isbn10" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="right"><%=resource.getString("cataloguing.catoldtitleentry1.isbn13")%>:</td><td><html:text property="isbn13" name="BibliographicDetailEntryActionForm" readonly="true"/></td></tr><tr><td colspan="8" height="30px;"></td></tr>
            <tr><td colspan="8">
                    <iframe src="<%=request.getContextPath()%>/cataloguing/cat_single_update_grid.jsp" width="100%" height="200px;" scrolling="no" frameborder="0"></iframe>
                </td></tr>
            </table>    </html:form>
    </body>
</html>
