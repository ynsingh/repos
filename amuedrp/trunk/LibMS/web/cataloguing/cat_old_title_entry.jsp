<%--
    Document   : cat_biblio_entry
    Created on : Jan 13, 2011, 12:02:47 PM
    Author     : Client
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>
<%! boolean read=false;%>
<%
String library_id=(String)session.getAttribute("library_id");
String button=(String)request.getAttribute("button");
if (button.equals("View")||button.equals("Delete"))
read=true;

else
    read=false;
System.out.println("<<<<<<"+read+button);
%>
<script type="text/javascript">
function del()
{
   var answer = confirm ("Do you want to Delete Record?")
if (answer!=true)
    {
        document.getElementById('button1').focus();
        return false;
    }
    else
        {
       document.Form1.action="/LibMS-Struts/cataloguing/cat_old_title.jsp";
       document.Form1.method="post";
   //document.Form1.target="_blank";
        document.Form1.submit();
return true;

        }


}
</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bibliographic Detail Entry Form</title>
        <link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
         <link rel="stylesheet" href="/LibMS-Struts/css/formstyle.css"/>
    </head>
    <body>
        <html:form method="post" action="/catOldBiblioDetailAction" style="position:absolute; left:200px; top:150px;"  >
 <table border="1" class="table" width="730">
                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" ><b>Bibliographic Detail Entry</b></td></tr>
                <tr><td>
                        <table width="700" border="0" cellspacing="4" cellpadding="1" align="center">
                        <tr>
                        <html:hidden property="library_id" name="BibliographicDetailEntryActionForm" value="<%=library_id%>" /><td></td>
                        <html:hidden property="accession_type" name="BibliographicDetailEntryActionForm"/>
                        </tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr><td colspan="5" height="10px"></td>
</tr>
  <tr>
    <td width="150" align="right" class="txtStyle"><strong>Biblio ID:</strong> </td>
    <td><html:text readonly="true"  property="biblio_id" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" /></td>
    <td width="150" align="right" class="txtStyle"><strong>Document Type:</strong> </td>
    <td>
      <html:text readonly="true"  property="document_type" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" />
    </td>
  </tr>
  <tr><td colspan="5" height="10px"></td>
</tr>
  <tr>
    <td width="150" align="right" class="txtStyle"><strong>Title<a class="star">*</a>:</strong> </td>
    <td><html:text readonly="<%=read%>" property="title" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" />
            <br><span class="err">   <html:messages id="err_name" property="title">
        <bean:write name="err_name" />
    </html:messages></span>
    </td>
 <td align="right" class="txtStyle"><strong> Subtitle:</strong></td>
    <td><html:text readonly="<%=read%>" property="subtitle" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" /></td>
  </tr>
  <tr><td colspan="5" height="5px"></td>
</tr>
  <tr>
    <td align="right" class="txtStyle"><strong>Main Author<a class="star">*</a>:</strong></td>
  <td><html:text readonly="<%=read%>" property="author_main" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" />
        <br><span class="err">   <html:messages id="err_name" property="author_main">
        <bean:write name="err_name" />
    </html:messages></span>
  </td>
 <td align="right" class="txtStyle"><strong>Sub Authors:</strong></td>
  <td><html:text readonly="<%=read%>" property="author_sub" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" /></td>
  </tr>
  <tr><td colspan="5" height="5px"></td>
</tr>
  <tr>
    <td align="right" class="txtStyle"><strong>Publisher Name:</strong></td>
  <td><html:text readonly="<%=read%>" property="publisher_name" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" /></td>
 <td align="right" class="txtStyle"><strong>Publication Place :</strong></td>
  <td><html:text readonly="<%=read%>" property="publication_place" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" /></td>
  </tr>
  <tr><td colspan="5" height="5px"></td>
</tr>
  <tr>
   <td align="right" class="txtStyle"><strong>Publishing Year:</strong></td>
  <td><html:text readonly="<%=read%>" property="publishing_year" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" />
            <br><span class="err">   <html:messages id="err_name" property="publishing_year">
        <bean:write name="err_name" />
    </html:messages></span>
  </td>
 <td align="right" class="txtStyle"><strong>LCC No:</strong></td>
  <td><html:text readonly="<%=read%>" property="control_no" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" />
           </td>
  </tr>
  <tr><td colspan="5" height="5px"></td>
</tr>
  <tr>
    <td align="right" class="txtStyle"><strong>ISBN: </strong></td>
    <td><html:text readonly="true"  property="isbn10" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" /></td>
   <td align="right" class="txtStyle"><strong>Second ISBN: </strong></td>
  <td><html:text readonly="<%=read%>"  property="isbn13" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" /></td>

  </tr>
  <tr><td colspan="5" height="5px"></td>
</tr>
  <tr>
    <td align="right" class="txtStyle"><strong>Edition:</strong></td>
  <td><html:text readonly="<%=read%>" property="edition" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" /></td>
 <td align="right" class="txtStyle"><strong>Call No<a class="star">*</a>:</strong></td>
  <td><html:text readonly="<%=read%>" property="call_no" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" />
          <br><span class="err">   <html:messages id="err_name" property="call_no">
        <bean:write name="err_name" />
    </html:messages></span>
  </td>
  </tr>
<tr><td colspan="5" height="10px"></td>
</tr>
  <tr>
   <td align="right" class="txtStyle"><strong>Index No:</strong></td>
  <td><html:text readonly="<%=read%>" property="index_no" name="BibliographicDetailEntryActionForm"  styleClass="textBoxWidth" /></td>
 <td align="right" class="txtStyle"><strong>No of pages:</strong></td>
  <td><html:text readonly="<%=read%>" property="no_of_pages" name="BibliographicDetailEntryActionForm"  styleClass="textBoxWidth" /></td>
  </tr>
  <tr><td colspan="5" height="5px"></td>
</tr>
  <tr>
   <td align="right" class="txtStyle"><strong>Physical Width:</strong></td>
  <td><html:text readonly="<%=read%>" property="physical_width" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" /></td>
 <td align="right" class="txtStyle"><strong>Price:</strong></td>
  <td><html:text readonly="<%=read%>" property="price" name="BibliographicDetailEntryActionForm"  styleClass="textBoxWidth" /></td>

  </tr>
<tr><td colspan="5" height="10px"></td>
</tr>
  <tr>
   <td align="right" class="txtStyle"><strong>Bind Type:</strong></td>
  <td><html:text readonly="<%=read%>" property="bind_type" name="BibliographicDetailEntryActionForm"  styleClass="textBoxWidth" /></td>
  <td align="right" class="txtStyle"><strong>No of copies:</strong></td>
  <td><html:text readonly="<%=read%>" property="no_of_copies" name="BibliographicDetailEntryActionForm"  styleClass="textBoxWidth" /></td>

  </tr>
    <tr><td colspan="5" height="5px"></td>
</tr>
 
<tr><td></td><td></td></tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr><td colspan="5" height="5px" class="mandatory" align="right"><a class="star">*</a>indicated fields are mandatory</td></tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr>
<td align="center" colspan="4">
    <%if(button.equals("Update")){%>
    <input id="button1"  name="button" type="submit" value="<%=button%>" class="txt1" />
    &nbsp;&nbsp;&nbsp;<input name="button" type="submit" value="Cancel" class="txt1"/>
    <%}else if(button.equals("Delete")){%>
    <input id="button1"  name="button" type="submit" onclick="return del()" value="<%=button%>" class="txt1" />
    &nbsp;&nbsp;&nbsp;<input name="button" type="submit" value="Cancel" class="txt1"/>
    <%}else{%>
    <input  name="button" type="submit" value="Back" class="txt1" /><%}%>
	</td>
</tr><tr><td colspan="5" height="5px"></td>
</tr>
</table>
</td></tr> </table>
  </html:form>
    </body>
</html>

