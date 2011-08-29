<%--
    Document   : cat_accession_entry
    Created on : Feb 28, 2011, 5:15:03 PM
    Author     : Asif Iqubal
--%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
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
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
    String newbutton;
    String newbutton2;
    boolean read;
    boolean disable;
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
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);%>
        <%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
String msg1=(String) request.getAttribute("msg1");
String msg2=(String) request.getAttribute("msg2");
String button=(String)session.getAttribute("button");
 if(button.equals("Update"))
 {
   newbutton=resource.getString("cataloguing.catoldtitleupdate1.update");
   read=false;
   disable=false;
}
 if(button.equals("View"))
 {
   newbutton=resource.getString("cataloguing.catoldtitle.view");
   read=true;
   disable=true;
}
if(button.equals("Delete"))
 {
   newbutton=resource.getString("cataloguing.catoldtitle.delete");
   read=true;
   disable=true;
}
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <link href="<%=request.getContextPath()%>/css/newformat.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />
         <script type="text/javascript" language="javascript">
        function submitNew()
{
    <% if(button.equalsIgnoreCase("New")||button.equalsIgnoreCase("View")){%>
    var buttonvalue="View";
    <%}%>
    <% if(button.equalsIgnoreCase("Update")){%>
    var buttonvalue="Update";
    <%}%>
    <% if(button.equalsIgnoreCase("Delete")){%>
    var buttonvalue="Delete";
    <%}%>
    document.getElementById("button1").setAttribute("value", buttonvalue);
    return true;
}
         </script>
    </head>
    <body>
        <html:form action="/deleteAccess" method="post">
            <html:hidden property="library_id" name="BibliographicDetailEntryActionForm" value="<%=library_id%>"/>
            <html:hidden property="sublibrary_id" name="BibliographicDetailEntryActionForm" value="<%=sub_library_id%>" />
            <html:hidden property="record_no" name="BibliographicDetailEntryActionForm"/>
            <html:hidden property="call_no" name="BibliographicDetailEntryActionForm"/>
            <html:hidden property="biblio_id" name="BibliographicDetailEntryActionForm"/>
            <html:hidden property="document_type" name="BibliographicDetailEntryActionForm" value="Book"/>
            <html:hidden property="title" name="BibliographicDetailEntryActionForm"/>
            <html:hidden property="subtitle" name="BibliographicDetailEntryActionForm"/>
            <html:hidden property="book_type" name="BibliographicDetailEntryActionForm"/>
            <html:hidden property="subject" name="BibliographicDetailEntryActionForm"/>
            <html:hidden property="alt_title" name="BibliographicDetailEntryActionForm"/>
            <html:hidden property="edition" name="BibliographicDetailEntryActionForm"/>
            <html:hidden property="no_of_copies" name="BibliographicDetailEntryActionForm"/>
            <html:hidden property="ser_note" name="BibliographicDetailEntryActionForm"/>
            <html:hidden property="statement_responsibility" name="BibliographicDetailEntryActionForm"/>
            <html:hidden property="main_entry" name="BibliographicDetailEntryActionForm" />
            <html:hidden property="publisher_name" name="BibliographicDetailEntryActionForm"/>
            <html:hidden property="publication_place" name="BibliographicDetailEntryActionForm"/>
            <html:hidden property="added_entry" name="BibliographicDetailEntryActionForm"/>
            <html:hidden property="added_entry0" name="BibliographicDetailEntryActionForm"/>
            <html:hidden property="publishing_year" name="BibliographicDetailEntryActionForm"/>
            <html:hidden property="added_entry1" name="BibliographicDetailEntryActionForm"/>
            <html:hidden property="added_entry2" name="BibliographicDetailEntryActionForm"/>
            <html:hidden property="LCC_no" name="BibliographicDetailEntryActionForm"/>
            <html:hidden property="isbn13" name="BibliographicDetailEntryActionForm"/>
            <html:hidden property="isbn10" name="BibliographicDetailEntryActionForm"/>
            <html:hidden property="language" name="BibliographicDetailEntryActionForm"/>
            <table width="100%" border="0" dir="<%= rtl %>">
          <%--  <tr><td colspan="8" bgcolor="#E0E8F5" align="center" height="25px;" class="headerStyle"><%=resource.getString("cataloguing.cataccessionentry.accessionheader")%></td></tr>--%>
             <tr><td height="5px;" colspan="8"></td></tr>
                <tr><td colspan="8" bgcolor="#E0E8F5" align="center" height="25px;" class="headerStyle"><strong><%=resource.getString("cataloguing.cataccessionentry.accessionheader")%></strong></td></tr>
                <tr>
                    <td align="right"><%=resource.getString("cataloguing.cataccessionentry.accessionno")%>:</td><td><bean:write name="BibliographicDetailEntryActionForm" property="language"/><html:text property="accession_no" name="BibliographicDetailEntryActionForm"/></td>
<td align="right">Physical Form:</td><td><html:text property="physical_form" name="BibliographicDetailEntryActionForm"/></td><td align="right">Colour:</td><td><html:text property="colour" name="BibliographicDetailEntryActionForm"/></td><td align="right">Physical Desc:</td><td><html:text property="physical_desc" name="BibliographicDetailEntryActionForm"/></td></tr>
                <tr>                    <td align="<%=align %>"><%=resource.getString("cataloguing.cataccessionentry.location")%>:</td><td>
                    <html:select disabled="<%=disable%>" property="location" name="BibliographicDetailEntryActionForm">
            <html:options collection="mixlist"  labelProperty="locationName" property="locationId"></html:options>
  </html:select>
                </td></tr>
             
            <tr><td height="20px;" colspan="8"></td></tr>
            <tr><td></td><td></td><td colspan="2" align="center">
                    <%if(button.equals("Update")||button.equals("Delete")){%>
                    <input name="button1" type="submit" value="<%= newbutton%>" onclick="return submitNew()"/>
                    <%}%>
                </td><td align="<%= align %>"><input name="button1" type="submit" onclick="return send()" value="<%=resource.getString("cataloguing.catoldtitle.back")%>"/>
                </td><td></td><td></td><td></td></tr>
            <input type="hidden" id="button1" name="button"/>
            <tr><td colspan="8" height="30px;"></td></tr>
            <tr><td colspan="8" align="<%=align%>">
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
