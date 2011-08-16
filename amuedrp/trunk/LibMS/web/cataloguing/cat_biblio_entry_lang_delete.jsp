<%--
    Document   : cat_biblio_entry_lang_delete
    Created on : Jan 13, 2011, 12:02:47 PM
    Author     : Asif Iqubal
--%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List"%>
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
%>
<script type="text/javascript">
function send()
{
    window.location="<%=request.getContextPath()%>/cataloguing/cat_view_own_biblio_grid.jsp";
    return false;
}
function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
         if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

         return true;
      }
function generateRow() {
       var d=document.getElementById("my_div");
       var no = document.getElementById("my_div").childNodes;
        if (no.length<5){
       d.innerHTML+="<input type='text' name='added_entry"+(no.length/2)+"' Class='textBoxWidth'/><br>";
    }else{
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
         <script type="text/javascript" language="javascript">
    function submitSave()
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
   // var i=getElementById(id);
  //  for(i=1;i<=3;i++)
//{animatedcollapse.addDiv(i, 'fade=1,height=20px')}
animatedcollapse.addDiv('1', 'fade=1,height=20px')
animatedcollapse.addDiv('2', 'fade=1,height=20px')
animatedcollapse.addDiv('3', 'fade=1,height=20px')
animatedcollapse.addDiv('4', 'fade=1,height=20px')
animatedcollapse.addDiv('5', 'fade=1,height=20px')
animatedcollapse.addDiv('6', 'fade=1,height=20px')
animatedcollapse.ontoggle=function($, divobj, state){ //fires each time a DIV is expanded/contracted
	//$: Access to jQuery
	//divobj: DOM reference to DIV being expanded/ collapsed. Use "divobj.id" to get its ID
	//state: "block" or "none", depending on state
}
animatedcollapse.init()
</script>
    </head>
<%request.setCharacterEncoding("UTF-8");
request.getCharacterEncoding();
%>
    <body>
      <table border="1" class="table" width="90%" style="position: absolute; top: 20%; left: 3%">
        <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" colspan="2" ><b><%=resource.getString("cataloguing.catoldtitleentry1.header")%></b></td></tr>
            <tr><td>
                    <table width="100%" border="0" dir="<%=rtl%>">
                        <tr><td width="550">    <html:form method="post" styleId="form1" action="/catMultiLang" acceptCharset="utf-8">
        <table width="100%" border="0"  dir="<%=rtl%>">
                        <html:hidden property="library_id" name="BibliographicDetailEntryActionForm" value="<%=library_id%>" />
                        <html:hidden property="sublibrary_id" name="BibliographicDetailEntryActionForm" value="<%=sub_library_id%>" /><td></td>
                        <html:hidden property="accession_type" name="BibliographicDetailEntryActionForm" value="Old"/>
                         <html:hidden property="document_type" name="BibliographicDetailEntryActionForm" value="Book"/>
                         <html:hidden property="no_of_copies" name="BibliographicDetailEntryActionForm"/>
                         <html:hidden property="biblio_id" name="BibliographicDetailEntryActionForm"/>
<tr>
      <td>
    </td>
    <td></td>
    <td height="20px;">
    </td>
        </tr>
<tr>
    <td class="txtStyle" align="<%=align%>" height="10px;"><strong><%=resource.getString("cataloguing.catoldtitleentry1.documentcategory")%><a class="star">*</a>:</strong> </td>
    <td>  <html:select property="book_type" disabled="true"  name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth">
            <html:options collection="DocumentCategory"  labelProperty="documentCategoryName" property="id.documentCategoryId" name="DocumentCategory"></html:options>
  </html:select>
    
    </td>
</tr>
<tr><td height="2px"></td>
</tr>
<tr>
    <td width="150" class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.title")%>:</strong> </td>
    <td><html:text property="title" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" readonly="true" tabindex="1" />
    </td>
     <td><html:text property="title1" name="BibliographicDetailEntryActionForm" styleId="title" readonly="true" tabindex="2"/></td>
  </tr>

  <tr><td height="2px"></td>
</tr>
<tr>
      <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.mainentry")%><a class="star">*</a>:</strong></td>
      <td><html:text property="main_entry" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" readonly="true" tabindex="5" />
      <br><span class="err">   <html:messages id="err_name" property="main_entry">
       <%=resource.getString("cataloguing.catoldtitleentry1.err1")%>
    </html:messages></span>
  </td>
  <td><html:text property="main_entry1" name="BibliographicDetailEntryActionForm" styleId="mainentry" readonly="true" tabindex="6"/></td>
</tr>
 <tr><td height="2px"></td>
</tr>
   <tr>
 <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.publishername")%>:</strong></td>
 <td><html:text property="publisher_name" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" readonly="true" tabindex="9" /></td>
 <td><html:text property="publisher_name1" name="BibliographicDetailEntryActionForm" styleId="publishername" readonly="true" tabindex="10"/></td>
   </tr>
  <tr><td height="2px"></td>
</tr>
     <tr>
  <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.publishingyear")%>:</strong></td>
  <td><html:text property="publishing_year" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" readonly="true" tabindex="13" onkeypress="return isNumberKey(event)" />
  </td>
  <td><html:text property="publishing_year1" name="BibliographicDetailEntryActionForm" styleId="publishingyear" readonly="true" tabindex="14"/></td>
     </tr>
  <tr><td height="2px"></td>
</tr>
  <tr>
    <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.isbn10")%>: </strong></td>
    <td><html:text  property="isbn10" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" readonly="true" tabindex="23" /></td>
    <td><html:text property="isbn101" name="BibliographicDetailEntryActionForm" styleId="isbn10" readonly="true" tabindex="24"/></td>
  </tr>
  <tr><td height="2px"></td>
</tr>
   <tr>
    <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.edition")%>:</strong></td>
    <td><html:text property="edition" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" readonly="true" tabindex="27" /></td>
    <td><html:text property="edition1" name="BibliographicDetailEntryActionForm" styleId="edition" readonly="true" tabindex="28"/></td>
  </tr>
  <tr><td height="2px"></td>
</tr>
  <tr>
   <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.alternatetitle")%>:</strong></td>
   <td><html:text property="alt_title" name="BibliographicDetailEntryActionForm"  styleClass="textBoxWidth" readonly="true" tabindex="31" /></td>
   <td><html:text property="alt_title1" name="BibliographicDetailEntryActionForm" styleId="alttitle" readonly="true" tabindex="32"/></td>
  </tr>
  <tr><td height="2px"></td>
</tr>
  <tr>
      <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.series")%>:</strong></td>
      <td><html:text property="ser_note" styleClass="textBoxWidth" name="BibliographicDetailEntryActionForm" readonly="true" tabindex="35"/></td>
      <td><html:text property="ser_note1" name="BibliographicDetailEntryActionForm" styleId="sernote" readonly="true" tabindex="36"/></td>
  </tr>
  <tr><td height="2px"></td>
</tr>
  <tr>
   <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.abstract")%>:</strong></td>
   <td><html:textarea rows="5" cols="20" property="thesis_abstract" name="BibliographicDetailEntryActionForm" readonly="true" styleClass="textBoxWidth" tabindex="39" /></td>
   <td><html:textarea rows="4" cols="18" property="thesis_abstract1" name="BibliographicDetailEntryActionForm" readonly="true" styleId="thesisabstract" tabindex="40"/></td>
  </tr>
<%--<tr><td colspan="5" height="5px"></td>
</tr>
<tr><td colspan="5" height="5px"></td>
</tr>
<tr><td></td><td></td></tr>
<tr><td colspan="5" height="10px"></td>
</tr>--%>
</table>
</td>
 <td width="650">
     <table border="0" height="100%">
         <tr><td><br><br><br><br><br></td></tr>
         <tr>
                 <td class="txtStyle" align="<%=align%>" width="50"><strong><%=resource.getString("cataloguing.catoldtitleentry1.subtitle")%>:</strong></td>
    <td><html:text property="subtitle" onchange="return copysubtitle();" name="BibliographicDetailEntryActionForm" readonly="true" styleId="subtitle" styleClass="textBoxWidth" tabindex="3" /></td>
         </tr>
         <tr><td height="2px"></td>
</tr>
         <tr>
  <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.statementresponsibility")%><a class="star">*</a>:</strong></td>
  <td><html:text property="statement_responsibility" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" readonly="true" tabindex="7" />
      <br><span class="err">   <html:messages id="err_name" property="statement_responsibility">
        <%=resource.getString("cataloguing.catoldtitleentry1.err3")%>
    </html:messages></span>
  </td>
         </tr>
         <tr><td height="2px"></td>
</tr>
     <tr><td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.publicationplace")%>:</strong></td>
         <td><html:text property="publication_place" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" tabindex="11" readonly="true" /></td>
     </tr>
<tr>
        <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.addedentry")%>:</strong></td>
        <td><html:text property="added_entry" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" readonly="true" tabindex="15" />

     <input type="button" onclick="javascript:animatedcollapse.show(['1','2','3'])" value="+"/>
     <input type="button" onclick="javascript:animatedcollapse.hide(['1','2','3'])" value="-"/></td>
         </tr>
            <!--<input type="button" onclick="generateRow()" value="+"/><div id="my_div"></div></td>-->
            <tr>
                <td></td>
                <td>
                    <div id="1" style="display: none;">
                        <html:text property="added_entry0" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" readonly="true" tabindex="16"/>
</div>
<div id="2" style="display: none;">
    <html:text property="added_entry1" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" readonly="true" tabindex="17"/>
</div>
<div id="3" style="display: none;">
    <html:text property="added_entry2" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" readonly="true" tabindex="18"/>
</div>
 </td>
            </tr>
  <tr><td height="2px"></td>
</tr>

<tr> <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.isbn13")%>:</strong></td>
         <td><html:text  property="isbn13"  name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" readonly="true" tabindex="25" /></td>
</tr>
 <tr><td height="2px"></td>
</tr>
<tr> <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.lcc")%>:</strong></td>
         <td><html:text property="LCC_no" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" readonly="true" tabindex="29" />
         </td>
</tr>
<tr><td height="2px"></td>
</tr>
<tr><td height="2px"></td>
</tr>
<tr><td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.callno")%><a class="star">*</a>:</strong></td>
         <td><html:text property="call_no" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" readonly="true" tabindex="33" />
        <br><span class="err">   <html:messages id="err_name" property="call_no">
        <%=resource.getString("cataloguing.catoldtitleentry1.err2")%>
    </html:messages></span>
  </td>

</tr>
<tr><td height="2px"></td>
</tr>
<tr><td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.subject")%>:</strong></td>
         <td><html:text property="subject" name="BibliographicDetailEntryActionForm"  styleClass="textBoxWidth" readonly="true" tabindex="37" /></td>

</tr>
<tr><td height="2px"></td>
</tr>
<tr> <td class="txtStyle" align="<%=align%>"><strong><%=resource.getString("cataloguing.catoldtitleentry1.note")%>:</strong></td>
          <td><html:textarea rows="5" cols="20" property="notes" name="BibliographicDetailEntryActionForm" readonly="true"  styleClass="textBoxWidth" tabindex="41" /></td>

</tr>
     </table>
 </td>
 <td width="350" align="left" valign="top">
         <table border="0" height="100%" >
             <tr><td><br><br><br><br><br></td></tr>
              <tr><td height="5px;"></td></tr>
             <tr>
                 <td><html:text property="subtitle1" name="BibliographicDetailEntryActionForm" styleId="subtitle1" readonly="true" tabindex="4"/></td>
             </tr>
               <tr><td height="2px"></td>
</tr>
             <tr>
                 <td><html:text property="statement_responsibility1" name="BibliographicDetailEntryActionForm" readonly="true" styleId="statementresponsibility" tabindex="8"/></td>
             </tr>
    <tr><td height="2px"></td>
</tr>
             <tr>
                              <td><html:text property="publication_place1" name="BibliographicDetailEntryActionForm" readonly="true" styleId="publicationplace" tabindex="12" /></td>
             </tr>
       <tr><td height="2px"></td>
</tr>
             <tr>
                 <td><html:text property="added_entryl" name="BibliographicDetailEntryActionForm" styleId="addedentry" readonly="true" tabindex="19" />

     <input type="button" onclick="javascript:animatedcollapse.show(['4','5','6'])" value="+"/>
     <input type="button" onclick="javascript:animatedcollapse.hide(['4','5','6'])" value="-"/></td>
             </tr>
       <tr><td height="2px"></td>
</tr>
             <tr>
                  <td>
                    <div id="4" style="display: none;">
                        <html:text property="added_entry01" name="BibliographicDetailEntryActionForm" styleId="addedentry0" readonly="true" tabindex="42" />
</div>
<div id="5" style="display: none;">
    <html:text property="added_entry11" name="BibliographicDetailEntryActionForm" styleId="addedentry1" readonly="true" tabindex="21"/>
</div>
<div id="6" style="display: none;">
    <html:text property="added_entry21" styleId="addedentry2" name="BibliographicDetailEntryActionForm" readonly="true" tabindex="22"/>
</div>
 </td>
             </tr>
       <tr>
                 <td><html:text  property="isbn131"  name="BibliographicDetailEntryActionForm" styleId="isbn13" readonly="true" tabindex="26"/></td>
             </tr>
               <tr><td height="2px"></td>
</tr>
             <tr>
                 <td><html:text property="LCC_no1" name="BibliographicDetailEntryActionForm" styleId="lccno" readonly="true" tabindex="30"/>
         </td>
             </tr>
  <tr><td height="2px"></td>
</tr>
             <tr>
                 <td><html:text property="call_no1" name="BibliographicDetailEntryActionForm" styleId="callno" readonly="true" tabindex="34" /></td>
             </tr>
              <tr><td height="2px"></td>
</tr>
             <tr>
                 <td><html:text property="subject1" name="BibliographicDetailEntryActionForm" styleId="subject" readonly="true" tabindex="38" /></td>
             </tr>
              <tr><td height="2px"></td>
</tr>
             <tr>
                 <td><html:textarea rows="4" cols="18" property="notes1" name="BibliographicDetailEntryActionForm" styleId="notes" readonly="true" tabindex="41" /></td>
             </tr>
         </table>
     </td>
                        </tr>

                        <tr><td colspan="5" height="5px" class="mandatory" dir="<%=rtl%>"><a class="star">*</a><%=resource.getString("cataloguing.catoldtitle.mandatory")%></td></tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr>
    <td align="center" colspan="4" dir="<%=rtl%>">
    <input  name="button1" type="submit" value="<%=resource.getString("cataloguing.catoldtitle.back")%>" onclick="return send()"/>
    </td>
           </html:form>
</tr><tr><td colspan="5" height="5px"></td>
</tr>
                    </table> </td></tr>
    </table>
    </body>
</html>

