<%--
    Document   : cat_biblio_entry
    Created on : Jan 13, 2011, 12:02:47 PM
    Author     : Asif Iqubal
--%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
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
String ac=(String)request.getParameter("ac");
%>
<script type="text/javascript">

function send()
{
    window.location="<%=request.getContextPath()%>/cataloguing/cat_viewAll_acc.jsp";
    return false;
}
</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bibliographic Detail</title>
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
        <table border="0" class="table" width="70%" align="center">
            <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" colspan="2" ><b><%= resource.getString("cataloguing.cataccessionentry.bibliodetail")%></b></td></tr>
            <tr><td>
                    <table width="100%" dir="<%= rtl %>">
                        <tr><td>    <html:form method="post" action="/updateaccno">
        <table width="100%" border="0" cellspacing="4" cellpadding="1" dir="<%= rtl %>">
                        <tr>
                        <html:hidden property="library_id" name="BibliographicDetailEntryActionForm1" value="<%=library_id%>" />
                        <html:hidden property="sublibrary_id" name="BibliographicDetailEntryActionForm1" value="<%=sub_library_id%>" /><td></td>
                        <html:hidden property="accession_type" name="BibliographicDetailEntryActionForm1" value="Old"/>
                         
                         <html:hidden property="document_type" name="BibliographicDetailEntryActionForm1" value="Book"/>
                         <html:hidden property="no_of_copies" name="BibliographicDetailEntryActionForm1"/>
                        </tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr>
    <td width="150" align="<%=align%>" class="txtStyle"><strong>BibioId:</strong> </td>
    <td>
        <html:text property="biblio_id" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" readonly="true"/>
    </td>

</tr>
<tr>
    <td width="150" align="<%=align%>" class="txtStyle"><strong>Record No:</strong> </td>
    <td>
        <html:text property="record_no" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" readonly="true"/>
    </td>

</tr>
<tr>
    <td width="150" align="<%=align%>" class="txtStyle"><strong><%=resource.getString("cataloguing.catoldtitleentry1.documentcategory")%>:</strong> </td>
    <td>
        <html:text property="book_type" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" readonly="true"/>
    </td>

</tr>
<tr><td height="2px"></td>
</tr>
<tr>
       <td align="<%=align%>" class="txtStyle"><strong><%=resource.getString("cataloguing.catoldtitleentry1.subtitle")%>:</strong></td>
    <td><html:text property="subtitle" readonly="true" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" /></td>

  </tr>
<tr><td height="2px"></td>
</tr>
  <tr>
      <td align="<%=align%>" class="txtStyle"><strong><%=resource.getString("cataloguing.catoldtitleentry1.mainentry")%>:</strong></td>
  <td><html:text property="main_entry" readonly="true" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" />

  </td>
   </tr>
<tr><td height="2px"></td>
</tr>
   <tr>
 <td align="<%=align%>" class="txtStyle"><strong><%=resource.getString("cataloguing.catoldtitleentry1.publishername")%>:</strong></td>
 <td><html:text property="publisher_name" readonly="true" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" /></td>
     </tr>
<tr><td height="2px"></td>
</tr>
     <tr>
  <td align="<%=align%>" class="txtStyle"><strong><%=resource.getString("cataloguing.catoldtitleentry1.publishingyear")%>:</strong></td>
  <td><html:text property="publishing_year" readonly="true" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" />
  </td>
  </tr>
<tr><td height="2px"></td>
</tr>
  <tr>
    <td align="<%=align%>" class="txtStyle"><strong><%=resource.getString("cataloguing.catoldtitleentry1.isbn10")%>:</strong></td>
    <td><html:text  property="isbn10" readonly="true" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" /></td>
   </tr>
<tr><td height="2px"></td>
</tr>
   <tr>
    <td align="<%=align%>" class="txtStyle"><strong><%=resource.getString("cataloguing.catoldtitleentry1.edition")%>:</strong></td>
  <td><html:text property="edition" readonly="true" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" /></td>

  </tr>
<tr><td height="2px"></td>
</tr>
  <tr>
   <td align="<%=align%>" class="txtStyle"><strong><%=resource.getString("cataloguing.catoldtitleentry1.alternatetitle")%>:</strong></td>
  <td><html:text property="alt_title" readonly="true" name="BibliographicDetailEntryActionForm1"  styleClass="textBoxWidth" /></td>
  </tr>
<tr><td height="2px"></td>
</tr>
  <tr>
      <td align="<%=align%>" class="txtStyle"><strong><%=resource.getString("cataloguing.catoldtitleentry1.series")%>:</strong></td>
      <td><html:text property="ser_note" styleClass="textBoxWidth" name="BibliographicDetailEntryActionForm1" readonly="true"/></td>
  </tr>
<tr><td height="2px"></td>
</tr>
  <tr>
   <td align="<%=align%>" class="txtStyle"><strong><%=resource.getString("cataloguing.catoldtitleentry1.abstract")%>:</strong></td>
   <td><html:textarea rows="5" cols="20" property="thesis_abstract" readonly="true" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" /></td>
   </tr>
   <tr>
   <td align="<%=align%>" class="txtStyle"><strong>No of Pages:</strong></td>
   <td><html:text  property="no_of_pages"  name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" /></td>
   </tr>
   <tr>
   <td align="<%=align%>" class="txtStyle"><strong>Volumn No:</strong></td>
   <td><html:text property="volume_no"  name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" /></td>
   </tr>
   <tr>
   <td align="<%=align%>" class="txtStyle"><strong>Location:</strong></td>
   <td>
       <html:select property="location" name="BibliographicDetailEntryActionForm1">
            <html:options collection="mixlist"  labelProperty="locationName" property="locationId"></html:options>
  </html:select>

       </td>
   </tr>
   <tr>
   <td align="<%=align%>" class="txtStyle"><strong>Physical Description/Collation:</strong></td>
   <td><html:text property="collation"  name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" /></td>
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
    <td width="150" align="<%=align%>" class="txtStyle"><strong><%=resource.getString("cataloguing.catoldtitleentry1.title")%>:</strong> </td>
    <td><html:text readonly="true" property="title" readonly="true" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" />
    </td>
         </tr>
<tr><td height="2px"></td>
</tr>
         <tr>
  <td align="<%=align%>" class="txtStyle"><strong><%=resource.getString("cataloguing.catoldtitleentry1.statementresponsibility")%>:</strong></td>
  <td><html:text property="statement_responsibility" readonly="true" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" />

  </td>
         </tr>
<tr><td height="2px"></td>
</tr>
         <tr>
        <td align="<%=align%>" class="txtStyle"><strong><%=resource.getString("cataloguing.catoldtitleentry1.addedentry")%>:</strong></td>
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
       <tr><td colspan="5" height="5px"></td>
</tr>
            <tr><td align="<%=align%>" class="txtStyle"><strong><%=resource.getString("cataloguing.catoldtitleentry1.publicationplace")%>:</strong></td>
  <td><html:text property="publication_place" readonly="true" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" /></td>
</tr>
<tr><td height="2px"></td>
</tr>
<tr> <td align="<%=align%>" class="txtStyle"><strong><%=resource.getString("cataloguing.catoldtitleentry1.lcc")%>:</strong></td>
  <td><html:text property="LCC_no" readonly="true" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" />
         </td></tr>
<tr><td height="2px"></td>
</tr>
<tr> <td align="<%=align%>" class="txtStyle"><strong><%=resource.getString("cataloguing.catoldtitleentry1.isbn13")%>:</strong></td>
  <td><html:text  property="isbn13" readonly="true"  name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" /></td>
</tr>
<tr><td height="2px"></td>
</tr>
<tr><td align="<%=align%>" class="txtStyle"><strong><%=resource.getString("cataloguing.catoldtitleentry1.callno")%>:</strong></td>
  <td><html:text property="call_no" readonly="true" name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" />

  </td></tr>
<tr><td height="2px"></td>
</tr>
<tr><td align="<%=align%>" class="txtStyle"><strong><%=resource.getString("cataloguing.catoldtitleentry1.subject")%>:</strong></td>
  <td><html:text property="subject" readonly="true" name="BibliographicDetailEntryActionForm1"  styleClass="textBoxWidth" /></td>
</tr>
<tr><td height="2px"></td>
</tr>
<tr> <td align="<%=align%>" class="txtStyle"><strong><%=resource.getString("cataloguing.catoldtitleentry1.note")%>:</strong></td>
  <td><html:textarea rows="5" cols="20" readonly="true" property="notes" name="BibliographicDetailEntryActionForm1"  styleClass="textBoxWidth" /></td>
</tr>
<tr>
   <td align="<%=align%>" class="txtStyle"><strong>Index:</strong></td>
   <td><html:text property="index_no"  name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" /></td>
   </tr>
   <tr>
   <td align="<%=align%>" class="txtStyle"><strong>Accession No:</strong></td>
   <td><html:text property="accession_no"  name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" /></td>
   </tr>
    <tr>
   <td align="<%=align%>" class="txtStyle"><strong>Shelving Location:</strong></td>
   <td><html:text property="shelving_location"  name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" /></td>
   </tr>
   <tr>
   <td align="<%=align%>" class="txtStyle"><strong>Bind Type:</strong></td>
   <td><html:text property="bind_type"  name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" /></td>
   </tr>

   <html:hidden property="acc_no1"   name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" />
  <html:hidden property="date_acquired"   name="BibliographicDetailEntryActionForm1" styleClass="textBoxWidth" />
     </table></td></tr>
<tr><td height="2px"></td>
</tr>
<tr>
    <td align="center" colspan="4">
        <% if(ac.equalsIgnoreCase("up")){%>
    <input name="button" type="submit"  value="Update" class="txt1"/>
    <%}else{%>
    <input name="button" type="submit"  value="Delete" class="txt1"/>
    <%}%>
    &nbsp;<input name="button" type="button" onclick="return send()" value="<%=resource.getString("cataloguing.catoldtitle.back")%>" class="txt1"/>
    </td>
           </html:form>
</tr><tr><td colspan="5" height="5px"></td>
</tr>
                    </table> </td></tr>    </table>
    </body>
</html>

