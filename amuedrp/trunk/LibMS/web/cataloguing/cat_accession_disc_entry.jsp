<%-- 
    Document   : cat_accession_disc_entry
    Created on : Mar 23, 2011, 1:12:52 PM
    Author     : khushnood
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
<html>
    <head>
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
    %>
        <%
                    String library_id = (String) session.getAttribute("library_id");
                    String sub_library_id = (String) session.getAttribute("sublibrary_id");
                  //  Integer biblio_id = (Integer) session.getAttribute("biblio_id");
                  //  String ii = biblio_id.toString();
                    String msg1 = (String) request.getAttribute("msg1");
                    String msg2 = (String) request.getAttribute("msg2");
        String fromjsp=(String)request.getAttribute("fromjsp");
if(fromjsp!=null){
path="/cataloguing/cat_old_title.jsp";
}
else
path="/cataloguing/cat_accession.jsp";
System.out.println("Path         :"+path);
            %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="/LibMS-Struts/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="/LibMS-Struts/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
        <script type="text/javascript" src="/LibMS-Struts/js/animatedcollapse.js"></script>
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
            <script>
            function send()
{
    window.location="<%=request.getContextPath()%>/cataloguing/cat_accession.jsp";
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
String button1=(String)session.getAttribute("button");
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
        <html:form method="post" action="/DiscAccessionEntry">
            <html:hidden property="library_id" name="BibliographicDetailEntryActionForm" value="<%=library_id%>" />
            <html:hidden property="sublibrary_id" name="BibliographicDetailEntryActionForm" value="<%=sub_library_id%>" />
            <html:hidden property="biblio_id" name="BibliographicDetailEntryActionForm"/>
            <html:hidden property="accession_type" name="BibliographicDetailEntryActionForm" value="Old"/>
            <html:hidden property="document_type" name="BibliographicDetailEntryActionForm" value="CD"/>
            <html:hidden property="book_type" name="BibliographicDetailEntryActionForm"/>
            <table width="100%" border="0" style="position: absolute; top: 20%">
                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" colspan="8" ><strong><%=resource.getString("cataloguing.cataccessionentry.bibliodetail")%></strong></td></tr>
                <tr><td colspan="8" height="5px;"></td></tr>
                <tr><td bgcolor="#AFC7C7" colspan="4" align="center"><strong><%=resource.getString("cataloguing.cataccessionentry.titleinfo")%></strong></td><td bgcolor="#CFECEC" colspan="4" align="center"><strong>Disc Information:</strong></td></tr>
                <tr><td align="right"><%=resource.getString("cataloguing.catoldtitleentry1.title")%>:</td><td><html:text property="title" name="BibliographicDetailEntryActionForm" readonly="true" /></td><td align="right"><%=resource.getString("cataloguing.catoldtitleentry1.subtitle")%>:</td><td><html:text property="subtitle" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="right">Disk Type:</td><td><html:text property="type_of_disc" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="right"><%=resource.getString("cataloguing.catoldtitleentry1.subject")%>:</td><td><html:text property="subject" name="BibliographicDetailEntryActionForm" readonly="true"/></td></tr>
                <tr><td align="right"><%=resource.getString("cataloguing.catoldtitleentry1.alternatetitle")%>:</td><td><html:text property="alt_title" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="<%=align%>"><%=resource.getString("cataloguing.catoldtitleentry1.documentcategory")%>:</td><td><html:text property="book_type" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="right"><%=resource.getString("cataloguing.catoldtitleentry1.edition")%>:</td><td><html:text property="edition" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="right"><%=resource.getString("cataloguing.cataccessionentry.nocopy")%>:</td><td><html:text property="no_of_copies" name="BibliographicDetailEntryActionForm" readonly="true"/></td></tr>
                <tr><td bgcolor="#AFC7C7" colspan="4" align="center"><strong><%=resource.getString("cataloguing.catoldtitleentry1.statementresponsibility")%></strong></td><td bgcolor="#CFECEC" colspan="4" align="center"><strong><%=resource.getString("cataloguing.cataccessionentry.pubinfo")%></strong></td></tr>
                <tr><td align="right"><%=resource.getString("cataloguing.catoldtitleentry1.statementresponsibility")%>:</td><td><html:text property="statement_responsibility" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="right"><%=resource.getString("cataloguing.catoldtitleentry1.mainentry")%>:</td><td><html:text property="main_entry" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="right"><%=resource.getString("cataloguing.catoldtitleentry1.publishername")%>:</td><td><html:text property="publisher_name" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="right"><%=resource.getString("cataloguing.catoldtitleentry1.publicationplace")%>:</td><td><html:text property="publication_place" name="BibliographicDetailEntryActionForm" readonly="true"/></td></tr>
                <tr><td align="right"><%=resource.getString("cataloguing.catoldtitleentry1.addedentry")%>:</td><td><html:text property="added_entry" name="BibliographicDetailEntryActionForm" readonly="true"/></td>&nbsp;<td><html:text property="added_entry0" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td></td>
                <tr><td></td><td><html:text property="added_entry1" name="BibliographicDetailEntryActionForm" readonly="true"/></td>&nbsp;<td><html:text property="added_entry2" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td></td><td></td><td></td><td></td><td></td></tr>
                <tr><td bgcolor="#AFC7C7" colspan="8" align="center">Title Identification</td></tr>
                <tr><td align="right"><%=resource.getString("cataloguing.catoldtitleentry1.callno")%>:</td><td><html:text property="call_no" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="right"><%=resource.getString("cataloguing.catoldtitleentry1.isbn10")%>:</td><td><html:text property="isbn10" name="BibliographicDetailEntryActionForm" readonly="true"/></td><td align="right"><%=resource.getString("cataloguing.catoldtitleentry1.isbn13")%>:</td><td><html:text property="isbn13" name="BibliographicDetailEntryActionForm" readonly="true"/></td></tr>
                <tr><td height="5px;" colspan="8"></td></tr>
                  <tr><td colspan="8" bgcolor="#E0E8F5" align="center" height="25px;" class="headerStyle"><%=resource.getString("cataloguing.cataccessionentry.accessionheader")%></td></tr>
           <tr><td align="right"><%=resource.getString("cataloguing.cataccessionentry.accessionno")%>:</td><td><bean:write name="BibliographicDetailEntryActionForm" property="language"/><html:text property="accession_no" name="BibliographicDetailEntryActionForm"/></td><td align="right">Physical Form:</td><td><html:text property="physical_form" name="BibliographicDetailEntryActionForm"/></td><td align="right">Colour:</td><td><html:text property="colour" name="BibliographicDetailEntryActionForm"/></td><td align="right">Physical Desc:</td><td><html:text property="physical_desc" name="BibliographicDetailEntryActionForm"/></td></tr>
                <tr>                <td align="<%=align %>"><%=resource.getString("cataloguing.cataccessionentry.location")%>:</td><td>
                    <html:select disabled="<%=disable%>" property="location" name="BibliographicDetailEntryActionForm">
            <html:options collection="mixlist"  labelProperty="locationName" property="locationId"></html:options>
  </html:select>
                </td></tr>
<tr></tr>
                <tr><td height="10px;" colspan="8"></td></tr>
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
   <input name="button1"  type="submit" value="<%=newbutton%>" onclick="return submitAdditem()"/>
    &nbsp;&nbsp;&nbsp;&nbsp;<input name="button1" type="submit" onclick="return send()" value="<%=resource.getString("cataloguing.catoldtitleentry1.cancel")%>"/>
    <%}else{%>
    <input  name="button1" type="button" value="<%=resource.getString("cataloguing.catoldtitle.back")%>" onclick="return send()"/><%}%>
     <input type="hidden" id="button1" name="button"/>
 </td>
                <td></td><td height="5px" colspan="2" class="mandatory" align="center"><a class="star">*</a><%=resource.getString("cataloguing.catoldtitle.mandatory")%></td>
            </tr>
                <tr><td colspan="8" height="30px;"></td></tr>
                <tr><td colspan="8" align="center">
                        <% if (msg1 != null) {%>
                        <span style="font-size:12px;font-weight:bold;color:blue;"><%=msg1%></span>
                        <%}%>
                        <%  if (msg2 != null) {%>
                        <span style="font-size:12px;font-weight:bold;color:red;"><%=msg2%></span>
                        <%}%></td></tr>              
            </table>
        </html:form>
    </body>
</html>

