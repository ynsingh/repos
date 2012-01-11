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
<jsp:include page="/admin/header.jsp"/>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
    String newbutton;
    String newbutton1;
    boolean read;
    boolean disable;
    String path;
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
String fromjsp=(String)request.getAttribute("fromjsp");
if(fromjsp!=null && fromjsp.equals("cat_old_entry1")){
path="/cataloguing/cat_old_title.jsp";
}
else if(fromjsp!=null && fromjsp.equals("cat_biblio_entry"))
{
path="/cataloguing/cat_biblio.jsp";
}
else
path="/cataloguing/cat_accession.jsp";
System.out.println("Path         :"+path);
%>
<html>    
    <head>
        <script>
            function send()
{
    window.location="<%=request.getContextPath()%><%=path%>";
    return false;
}
        function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
         if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

         return true;
      }
        </script>
<%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
String button1=(String)session.getAttribute("button");
//Integer biblio_id=(Integer)session.getAttribute("biblio_id");
//String ii=biblio_id.toString();
String msg1=(String) request.getAttribute("msg1");
String msg2=(String) request.getAttribute("msg2");
%>
<%
 if(button1.equals("Update"))
 {
   newbutton=resource.getString("cataloguing.catoldtitle.update");
   read=false;
   disable=false;
 }
 if(button1.equals("Delete")||button1.equals("View"))
 {
    newbutton=resource.getString("cataloguing.catoldtitle.delete");
    disable=true;
    read=true;
 }
 if(button1.equals("New"))
 {
   newbutton=resource.getString("cataloguing.cataccessionentry.additem");
   disable=false;
   read=false;
 }
 %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
               <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
  <script type="text/javascript" language="javascript">
    function submitAdditem()
{
    var buttonvalue="Add Item";
    document.getElementById("button1").setAttribute("value", buttonvalue);
    return true;
}
function submitUpdate()
{
    var buttonvalue="Update";
    document.getElementById("button1").setAttribute("value", buttonvalue);
    return true;
}
function submitDelete()
{
    var buttonvalue="Delete";
    document.getElementById("button1").setAttribute("value", buttonvalue);
    return true;
}
</script>
    </head>
    <body>
        <html:form action="/accessionEntry" method="post">
            <html:hidden property="library_id" name="BibliographicDetailEntryActionForm" value="<%=library_id%>"/>
            <html:hidden property="sublibrary_id" name="BibliographicDetailEntryActionForm" value="<%=sub_library_id%>" />
            <html:hidden property="biblio_id" name="BibliographicDetailEntryActionForm"/>
            <html:hidden property="language" name="BibliographicDetailEntryActionForm"/>
            <html:hidden property="document_type" name="BibliographicDetailEntryActionForm"/>
            <html:hidden property="date_acquired1" name="BibliographicDetailEntryActionForm"/>
            <table width="100%" border="0" style="position: absolute; top: 20%" dir="<%=rtl%>">
            <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" colspan="8"><%=resource.getString("cataloguing.cataccessionentry.bibliodetail")%></td></tr>
            <tr><td colspan="8" height="5px;"></td></tr>
            <tr><td bgcolor="#AFC7C7" colspan="4" align="center"><strong><%=resource.getString("cataloguing.cataccessionentry.titleinfo")%></strong></td><td bgcolor="#CFECEC" colspan="4" align="center"><strong><%=resource.getString("cataloguing.cataccessionentry.docinfo")%></strong></td></tr>
            <tr><td align="<%=align%>"><%=resource.getString("cataloguing.catoldtitleentry1.title")%>:</td><td><html:text property="title" name="BibliographicDetailEntryActionForm" readonly="true" /></td><td align="<%=align%>"><%=resource.getString("cataloguing.catoldtitleentry1.subtitle")%>:</td><td><html:text property="subtitle" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="<%=align%>"><%=resource.getString("cataloguing.catoldtitleentry1.documentcategory")%>:</td><td><html:select property="book_type"  name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth">
            <html:options collection="DocumentCategory"  labelProperty="documentCategoryName" property="id.documentCategoryId" name="DocumentCategory"></html:options></html:select></td><td align="<%=align %>"><%=resource.getString("cataloguing.catoldtitleentry1.subject")%>:</td><td><html:text property="subject" name="BibliographicDetailEntryActionForm" readonly="true"/></td></tr>
            <tr><td align="<%=align%>"><%=resource.getString("cataloguing.catoldtitleentry1.alternatetitle")%>:</td><td><html:text property="alt_title" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td></td><td></td><td align="<%=align%>"><%=resource.getString("cataloguing.catoldtitleentry1.edition")%>:</td><td><html:text property="edition" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="<%=align %>"><%=resource.getString("cataloguing.cataccessionentry.nocopy")%>:</td><td><html:text property="no_of_copies" name="BibliographicDetailEntryActionForm" readonly="true"/></td></tr>
            <tr><td></td><td></td><td></td><td></td><td align="<%=align%>"><%=resource.getString("cataloguing.catoldtitleentry1.series")%>:</td><td colspan="2"><html:text property="ser_note" name="BibliographicDetailEntryActionForm" readonly="true" style="width:300px;"/></td><td></td></tr>
            <tr><td bgcolor="#AFC7C7" colspan="4" align="center"><strong><%=resource.getString("cataloguing.catoldtitleentry1.statementresponsibility")%></strong></td><td bgcolor="#CFECEC" colspan="4" align="center"><strong><%=resource.getString("cataloguing.cataccessionentry.pubinfo")%></strong></td></tr>
            <tr><td align="<%=align %>"><%=resource.getString("cataloguing.catoldtitleentry1.statementresponsibility")%>:</td><td><html:text property="statement_responsibility" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="<%=align %>"><%=resource.getString("cataloguing.catoldtitleentry1.mainentry")%>:</td><td><html:text property="main_entry" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="<%=align %>"><%=resource.getString("cataloguing.catoldtitleentry1.publishername")%>:</td><td><html:text property="publisher_name" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="<%=align %>"><%=resource.getString("cataloguing.catoldtitleentry1.publicationplace")%>:</td><td><html:text property="publication_place" name="BibliographicDetailEntryActionForm" readonly="true"/></td></tr>
            <tr><td align="<%=align %>"><%=resource.getString("cataloguing.catoldtitleentry1.addedentry")%>:</td><td><html:text property="added_entry" name="BibliographicDetailEntryActionForm" readonly="true"/></td>&nbsp;<td><html:text property="added_entry0" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td></td><td align="<%=align %>"><%=resource.getString("cataloguing.catoldtitleentry1.publishingyear")%>:</td><td><html:text property="publishing_year" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td></td><td></td></tr>
            <tr><td></td><td><html:text property="added_entry1" name="BibliographicDetailEntryActionForm" readonly="true"/></td>&nbsp;<td><html:text property="added_entry2" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td></td><td></td><td></td><td></td><td></td></tr>
            <tr><td bgcolor="#AFC7C7" colspan="8" align="center"><%=resource.getString("cataloguing.cataccessionentry.titleid")%></td></tr>
            <tr><td align="<%=align%>"><%=resource.getString("cataloguing.catoldtitleentry1.lcc")%>:</td><td><html:text property="LCC_no" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="<%=align %>"><%=resource.getString("cataloguing.catoldtitleentry1.callno")%>:</td><td><html:text property="call_no" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="<%=align%>"><%=resource.getString("cataloguing.catoldtitleentry1.isbn10")%>:</td><td><html:text property="isbn10" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="<%=align%>"><%=resource.getString("cataloguing.catoldtitleentry1.isbn13")%>:</td><td><html:text property="isbn13" name="BibliographicDetailEntryActionForm" readonly="true"/></td></tr>
            <tr><td height="5px;" colspan="8"></td></tr>
            <tr><td colspan="8" bgcolor="#E0E8F5" align="center" height="25px;" class="headerStyle"><%=resource.getString("cataloguing.cataccessionentry.accessionheader")%></td></tr>
            <tr><td align="<%=align %>"><%=resource.getString("cataloguing.cataccessionentry.volumeno")%>:</td><td><html:text property="volume_no" name="BibliographicDetailEntryActionForm" readonly="<%=read%>"/></td><td align="<%=align %>"><%=resource.getString("cataloguing.cataccessionentry.accessionno")%><a class="star">*</a>:</td><td><bean:write name="BibliographicDetailEntryActionForm" property="language"/><html:text property="accession_no" name="BibliographicDetailEntryActionForm" onkeypress="return isNumberKey(event)" readonly="<%=read%>"/></td>
                <td align="<%=align %>"><%=resource.getString("cataloguing.cataccessionentry.location")%>:</td><td>
                    <html:select disabled="<%=disable%>" property="location" name="BibliographicDetailEntryActionForm">
            <html:options collection="mixlist"  labelProperty="locationName" property="locationId"></html:options>
  </html:select>
                </td>
                <td align="<%=align %>"><%=resource.getString("cataloguing.cataccessionentry.shelflocation")%>:</td><td><html:text property="shelving_location" name="BibliographicDetailEntryActionForm" readonly="<%=read%>"/></td></tr>
            <tr><td align="<%=align %>"><%=resource.getString("cataloguing.cataccessionentry.index")%>:</td><td><html:text property="index_no" name="BibliographicDetailEntryActionForm" readonly="<%=read%>"/></td><td align="<%=align %>"><%=resource.getString("cataloguing.cataccessionentry.noofpage")%>:</td><td><html:text property="no_of_pages" name="BibliographicDetailEntryActionForm" readonly="<%=read%>"/></td><td align="<%=align %>"><%=resource.getString("cataloguing.cataccessionentry.physicalwidth")%>:</td><td><html:text property="physical_width" name="BibliographicDetailEntryActionForm" readonly="<%=read%>"/></td><td align="<%=align %>"><%=resource.getString("cataloguing.cataccessionentry.bindtype")%>:</td><td><html:text property="bind_type" name="BibliographicDetailEntryActionForm" readonly="<%=read%>"/></td></tr>
            <tr><td height="30px;" colspan="8"></td></tr>
            <tr>
                <td></td><td></td>
                <td colspan="4" align="center">
                <%if(button1.equals("Update")){%>
    <input  name="button1" type="submit" value="<%=newbutton%>" onclick="return submitUpdate();"/>
    &nbsp;&nbsp;&nbsp;<input name="button1" type="submit" value="<%=resource.getString("cataloguing.catoldtitleentry1.cancel")%>" onclick="return send()" />
    <%}else if(button1.equals("Delete")){%>
    <input  name="button1" type="submit" value="<%=newbutton%>" onClick="return submitDelete()"/>
    &nbsp;&nbsp;&nbsp;<input name="button1" type="submit" value="<%=resource.getString("cataloguing.catoldtitleentry1.cancel")%>" onclick="return send()"/>
   <%}else if(button1.equals("New")){%>
   <input name="button1" type="submit" value="<%=newbutton%>" onclick="return submitAdditem()"/>
    &nbsp;&nbsp;&nbsp;&nbsp;<input name="button1" type="submit" onclick="return send()" value="<%=resource.getString("cataloguing.catoldtitleentry1.cancel")%>"/>
    <%}else{%>
    <input  name="button1" type="button" value="<%=resource.getString("cataloguing.catoldtitle.back")%>" onclick="return send()"/><%}%>
     <input type="hidden" id="button1" name="button"/>
 </td>
                <td></td><td height="5px" colspan="2" class="mandatory" align="center"><a class="star">*</a><%=resource.getString("cataloguing.catoldtitle.mandatory")%></td>
            </tr>
            <tr><td colspan="8" height="30px;"></td></tr>
            <tr><td colspan="8" align="center">
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
