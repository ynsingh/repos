<%--
    Document   : cat_biblio_entry
    Created on : Jan 13, 2011, 12:02:47 PM
    Author     : Asif Iqubal
--%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.List"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
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
%>
<%
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
String button1=(String)session.getAttribute("button");
String msg1=(String) request.getAttribute("msg1");
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
   newbutton=resource.getString("cataloguing.catoldtitleentry1.save");
   newbutton1=resource.getString("cataloguing.catoldtitleentry1.saveaccession");
   disable=false;
   read=false;
 }
 %>
<script type="text/javascript">
function send()
{
    window.location="<%=request.getContextPath()%>/cataloguing/cat_old_title.jsp";
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
<html>
    <head>
<script type="text/javascript" language="javascript">
    function submitSave()
{
    var buttonvalue="Save";
    document.getElementById("button1").setAttribute("value", buttonvalue);
    return true;
}
function submitSaveAccession()
{
    var buttonvalue="Save and go for accession";
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bibliographic Detail Entry Form</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
         <script src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
         <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
         <script type="text/javascript" src="<%=request.getContextPath()%>/js/animatedcollapse.js"></script>
<script type="text/javascript">
animatedcollapse.addDiv('1', 'fade=1,height=20px')
animatedcollapse.addDiv('2', 'fade=1,height=20px')
animatedcollapse.addDiv('3', 'fade=1,height=20px')
animatedcollapse.ontoggle=function($, divobj, state){ //fires each time a DIV is expanded/contracted
}
animatedcollapse.init()
</script>
    </head>
    <body>
    <%if(msg1!=null){%>   <span style=" position:absolute; top: 90px; font-size:12px;font-weight:bold;color:red;" ><%=msg1%></span>  <%}%>

    <table border="1" class="table" width="80%" style="position: absolute; top: 20%; left: 10%">
        <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" colspan="2" ><b><%=resource.getString("cataloguing.catoldtitleentry1.header")%></b></td></tr>
            <tr><td>
                    <table width="100%" dir="<%=rtl%>">
                        <tr><td>    <html:form method="post" action="/catOldBiblio">
        <table width="100%" border="0" cellspacing="4" cellpadding="1" dir="<%=rtl%>">
                        <tr>
                        <html:hidden property="library_id" name="BibliographicDetailEntryActionForm" value="<%=library_id%>" />
                        <html:hidden property="sublibrary_id" name="BibliographicDetailEntryActionForm" value="<%=sub_library_id%>" /><td></td>
                        <html:hidden property="accession_type" name="BibliographicDetailEntryActionForm" value="Old"/>
                         <html:hidden property="biblio_id" name="BibliographicDetailEntryActionForm" />
                        <html:hidden property="document_type" name="BibliographicDetailEntryActionForm" value="Book"/>
                         <html:hidden property="no_of_copies" name="BibliographicDetailEntryActionForm"/>
                         <html:hidden property="date_acquired1" name="BibliographicDetailEntryActionForm"/>
                        </tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr>
    <td width="150" class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.documentcategory")%><a class="star">*</a>:</strong> </td>
    <td>
        <html:select property="book_type"  name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth"  tabindex="1">
            <html:options collection="DocumentCategory"  labelProperty="documentCategoryName" property="id.documentCategoryId" name="DocumentCategory"></html:options>
  </html:select>
     <br><span class="err">   <html:messages id="err_name" property="book_type">
       <%=resource.getString("cataloguing.catoldtitle.err1")%>
    </html:messages></span></td>

</tr>
<tr><td height="2px"></td>
</tr>
<tr>
       <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.subtitle")%>:</strong></td>
       <td><html:text property="subtitle" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="3" /></td>

  </tr>

  <tr><td height="2px"></td>
</tr>
<tr>
      <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.mainentry")%><a class="star">*</a>:</strong></td>
      <td><html:text property="main_entry" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="5" />
      <br><span class="err">   <html:messages id="err_name" property="main_entry">
       <%=resource.getString("cataloguing.catoldtitleentry1.err1")%>
    </html:messages></span>
  </td>
   </tr>
 <tr><td height="2px"></td>
</tr>
   <tr>
 <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.publishername")%>:</strong></td>
 <td><html:text property="publisher_name" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="10" /></td>
     </tr>
  <tr><td height="2px"></td>
</tr>
     <tr>
  <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.publishingyear")%>:</strong></td>
  <td><html:text property="publishing_year" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="12" onkeypress="return isNumberKey(event)" />
  </td>
  </tr>
  <tr><td height="2px"></td>
</tr>
  <tr>
    <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.isbn10")%>:</strong></td>
    <td><html:text  property="isbn10" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="14" /></td>
   </tr>
  <tr><td height="2px"></td>
</tr>
   <tr>
    <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.edition")%>:</strong></td>
    <td><html:text property="edition" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="16" /></td>

  </tr>
  <tr><td height="2px"></td>
</tr>
  <tr>
   <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.alternatetitle")%>:</strong></td>
   <td><html:text property="alt_title" readonly="<%=read%>" name="BibliographicDetailEntryActionForm"  styleClass="textBoxWidth" tabindex="18" /></td>
  </tr>
  <tr><td height="2px"></td>
</tr>
  <tr>
      <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.series")%>:</strong></td>
      <td><html:text property="ser_note" readonly="<%=read%>" styleClass="textBoxWidth" name="BibliographicDetailEntryActionForm" tabindex="20"/></td>
  </tr>
  <tr><td height="2px"></td>
</tr>
  <tr>
   <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.abstract")%>:</strong></td>
   <td><html:textarea rows="5" cols="20" readonly="<%=read%>" property="thesis_abstract" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="22" /></td>
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
    <td width="150" class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.title")%>:</strong> </td>
    <td><html:text readonly="true" property="title"  name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="2" />
    </td>
         </tr>
         <tr><td height="2px"></td>
</tr>
         <tr>
  <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.statementresponsibility")%><a class="star">*</a>:</strong></td>
  <td><html:text property="statement_responsibility" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="4" />
      <br><span class="err">   <html:messages id="err_name" property="statement_responsibility">
        <%=resource.getString("cataloguing.catoldtitleentry1.err3")%>
    </html:messages></span>
  </td>
         </tr>
         <tr><td height="2px"></td>
</tr>
         <tr>
        <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.addedentry")%>:</strong></td>
        <td><html:text property="added_entry" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="6" />

     <input type="button" onclick="javascript:animatedcollapse.show(['1','2','3'])" value="+"/>
     <input type="button" onclick="javascript:animatedcollapse.hide(['1','2','3'])" value="-"/></td>
         </tr>
            <tr>
                <td></td>
                <td>
                    <div id="1" style="display: none;">
                        <html:text property="added_entry0" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="7"/>
</div>
<div id="2" style="display: none;">
    <html:text property="added_entry1" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="8"/>
</div>
<div id="3" style="display: none;">
    <html:text property="added_entry2" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="9"/>
</div>
 </td>
            </tr>
  <tr><td height="2px"></td>
</tr>
     <tr><td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.publicationplace")%>:</strong></td>
         <td><html:text property="publication_place" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="11" /></td>
</tr>
<tr><td height="2px"></td>
</tr>
<tr> <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.lcc")%>:</strong></td>
         <td><html:text property="LCC_no" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="13" />
         </td></tr>
<tr><td height="2px"></td>
</tr>
<tr> <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.isbn13")%>:</strong></td>
         <td><html:text  property="isbn13" readonly="<%=read%>"  name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="15" /></td>
</tr>
<tr><td height="2px"></td>
</tr>
<tr><td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.callno")%><a class="star">*</a>:</strong></td>
         <td><html:text property="call_no" readonly="<%=read%>" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="17" />
        <br><span class="err">   <html:messages id="err_name" property="call_no">
        <%=resource.getString("cataloguing.catoldtitleentry1.err2")%>
    </html:messages></span>
  </td></tr>
<tr><td height="2px"></td>
</tr>
<tr><td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.subject")%>:</strong></td>
         <td><html:text property="subject" readonly="<%=read%>" name="BibliographicDetailEntryActionForm"  styleClass="textBoxWidth" tabindex="19" /></td>
</tr>
<tr><td height="2px"></td>
</tr>
<tr> <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.note")%>:</strong></td>
          <td><html:textarea rows="5" cols="20" property="notes" readonly="<%=read%>" name="BibliographicDetailEntryActionForm"  styleClass="textBoxWidth" tabindex="21" /></td>
</tr>
     </table></td></tr>

                        <tr><td colspan="5" height="5px" class="mandatory" dir="<%=rtl%>"><a class="star">*</a><%=resource.getString("cataloguing.catoldtitle.mandatory")%></td></tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr>
    <td align="center" colspan="4" dir="<%=rtl%>">
    <%if(button1.equals("Update")){%>
    <input  name="button1" type="submit" value="<%=newbutton%>" onclick="return submitUpdate();"/>
    &nbsp;&nbsp;&nbsp;<input name="button1" type="submit" value="<%=resource.getString("cataloguing.catoldtitleentry1.cancel")%>" onclick="return send()" />
    <%}else if(button1.equals("Delete")){%>
    <input  name="button1" type="submit" value="<%=newbutton%>" onClick="return submitDelete()"/>
    &nbsp;&nbsp;&nbsp;<input name="button1" type="submit" value="<%=resource.getString("cataloguing.catoldtitleentry1.cancel")%>" onclick="return send()"/>
   <%}else if(button1.equals("New")){%>
   <input name="button1" type="submit" value="<%=newbutton%>" onclick="return submitSave()"/>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="button1" type="submit" value="<%=newbutton1%>" onclick="return submitSaveAccession()" style="font-family: Arial,Helvetica,sans-serif; font-size:12px; width: auto;"/>
    &nbsp;&nbsp;&nbsp;&nbsp;<input name="button1" type="submit" onclick="return send()" value="<%=resource.getString("cataloguing.catoldtitleentry1.cancel")%>"/>
    <%}else{%>
    <input  name="button1" type="submit" value="<%=resource.getString("cataloguing.catoldtitle.back")%>" onclick="return send()"/><%}%>
 <input id="button1"  name="button" type="hidden"/>
    </td>
           </html:form>
</tr><tr><td colspan="5" height="5px"></td>
</tr>
                    </table> </td></tr>

    </table>
    </body>
</html>

