<%--
    Document   : acq_new_title_entry
    Created on : Sep 28, 2010, 3:13:10 PM
    Author     : maqbool
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
String msg1=(String)request.getAttribute("msg1");
String msg3=(String)request.getAttribute("msg3");
String button=(String)request.getAttribute("button");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Title Entry Form</title>
        <link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
         <link rel="stylesheet" href="/LibMS-Struts/css/formstyle.css"/>
    </head>
    <body><%  if(msg1!=null)
    {%>
    &nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:12px;font-weight:bold;color:red; position: absolute; top: 130px;" ><%=msg1%></span>
<%}%>
    <%  if(msg3!=null)
    {%>
    &nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:16px;font-weight:bold;color:blue; position: absolute; top: 150px;" ><%=msg3%></span>
<%}%>
        <html:form method="post" action="/catOldTitleEntryAction" style="position:absolute; left:200px; top:150px;"  >

<table width="600" border="0" cellspacing="4" cellpadding="1">
<tr>
    <html:hidden property="library_id" name="OldTitleEntryActionForm" value="<%=library_id%>" /><td></td>
<td colspan="2" align="left" class="headerStyle">&nbsp;&nbsp;&nbsp;&nbsp;<b>Title Entry Form</b></td>
</tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr><td colspan="5" height="10px"></td>
</tr>
  <tr>
    <td width="150" align="right" class="txtStyle"><strong>Document Type:</strong> </td>
    <td><html:select property="document_type" name="OldTitleEntryActionForm" styleClass="textBoxWidth" >
       <html:option value="">Select</html:option>
            <html:option value="Book">Book</html:option>
            <html:option value="CDs">CDs</html:option>
  </html:select></td><td width="300px" class="txtStyle"> <span class="err"> <html:messages id="err_name" property="document_type">
              <bean:write   name="err_name" />
          </html:messages></span></td>
  </tr>
<tr><td colspan="5" height="10px"></td>
</tr>
  <tr>
    <td width="150" align="right" class="txtStyle"><strong>Title:</strong> </td>
    <td><html:text property="title" name="OldTitleEntryActionForm" styleClass="textBoxWidth" /></td>
  <td width="300px"class="txtStyle" > <span class="err">  <html:messages id="err_name" property="document_entry_id">
              <bean:write name="err_name" /></span>

			</html:messages>
         </td>
  </tr>
  <tr><td colspan="5" height="5px"></td>
</tr>
  <tr>
    <td align="right" class="txtStyle"><strong> Subtitle:</strong></td>
    <td><html:text property="subtitle" name="OldTitleEntryActionForm" styleClass="textBoxWidth" /></td>
    <td width="300px"class="txtStyle" > <span class="err">  <html:messages id="err_name" property="title">
				<bean:write name="err_name" />

			</html:messages></span>
         </td>
  </tr>
  <tr><td colspan="5" height="5px"></td>
</tr>
  <tr>
    <td align="right" class="txtStyle"><strong>Author:</strong></td>
  <td><html:text property="author" name="OldTitleEntryActionForm" styleClass="textBoxWidth" /></td>
 <td width="300px"class="txtStyle" ><span class="err">   <html:messages id="err_name" property="author">
             <bean:write name="err_name" /></span>

			</html:messages>
         </td>
  </tr>
  <tr><td colspan="5" height="5px"></td>
</tr>
  <tr>
    <td align="right" class="txtStyle"><strong>ISBN : </strong></td>
  <td><html:text property="isbn" name="OldTitleEntryActionForm" styleClass="textBoxWidth" /></td>
 <td width="300px"class="txtStyle" ><span class="err">   <html:messages id="err_name" property="isbn">
				<bean:write name="err_name" />

         </html:messages> </span>
         </td>
  </tr>
  <tr><td colspan="5" height="5px"></td>
</tr>
<tr>
    <td align="right" class="txtStyle"><strong>Volume No:</strong></td>
  <td><html:text property="volumeno" name="OldTitleEntryActionForm" styleClass="textBoxWidth" /></td>
 <td width="300px"class="txtStyle" > <span class="err">  <html:messages id="err_name" property="publication">
             <bean:write name="err_name" /></span>

			</html:messages>
         </td>
  </tr>
 <tr><td colspan="5" height="10px"></td>
</tr>
  <tr>
    <td align="right" class="txtStyle"><strong>Edition:</strong></td>
  <td><html:text property="edition" name="OldTitleEntryActionForm" styleClass="textBoxWidth" /></td>
 <td width="300px"class="txtStyle" > <span class="err">  <html:messages id="err_name" property="publication">
             <bean:write name="err_name" /></span>

			</html:messages>
         </td>
  </tr>
<tr><td colspan="5" height="10px"></td>
</tr>
  <tr>
    <td align="right" class="txtStyle"><strong>Publisher Name:</strong></td>
  <td><html:text property="publishername" name="OldTitleEntryActionForm" styleClass="textBoxWidth" /></td>
 <td width="300px"class="txtStyle" > <span class="err">  <html:messages id="err_name" property="publication">
             <bean:write name="err_name" /></span>

			</html:messages>
         </td>
  </tr>
  <tr><td colspan="5" height="5px"></td>
</tr>
<tr>
   <td align="right" class="txtStyle"><strong>Publication Place :</strong></td>
  <td><html:text property="publicationplace" name="OldTitleEntryActionForm" styleClass="textBoxWidth" /></td>
 <td width="300px"class="txtStyle" >   <html:messages id="err_name" property="price">
				<bean:write name="err_name" />

			</html:messages>
         </td>
  </tr>
  <tr><td colspan="5" height="10px"></td>
</tr>
  <tr>
   <td align="right" class="txtStyle"><strong>Publishing Year:</strong></td>
  <td><html:text property="publishingyear" name="OldTitleEntryActionForm" styleClass="textBoxWidth" /></td>
 <td width="300px"class="txtStyle" >   <html:messages id="err_name" property="price">
				<bean:write name="err_name" />

			</html:messages>
         </td>
  </tr>
 <tr><td colspan="5" height="10px"></td>
</tr>
  <tr>
   <td align="right" class="txtStyle"><strong>Call No:</strong></td>
  <td><html:text property="callno" name="OldTitleEntryActionForm" styleClass="textBoxWidth" /></td>
 <td width="300px"class="txtStyle" >   <html:messages id="err_name" property="price">
				<bean:write name="err_name" />

			</html:messages>
         </td>
  </tr>
  <tr><td colspan="5" height="10px"></td>
</tr>
  <tr>
   <td align="right" class="txtStyle"><strong>Accession No:</strong></td>
  <td><html:text property="accessionno" name="OldTitleEntryActionForm"  styleClass="textBoxWidth" /></td>
 <td width="300px"class="txtStyle" >   <html:messages id="err_name" property="price">
				<bean:write name="err_name" />

			</html:messages>
         </td>
  </tr>
<tr><td colspan="5" height="10px"></td>
</tr>
       <tr>
   <td align="right" class="txtStyle"><strong>Location:</strong></td>
  <td><html:text property="location" name="OldTitleEntryActionForm"  styleClass="textBoxWidth" /></td>
 <td width="300px"class="txtStyle" >   <html:messages id="err_name" property="price">
				<bean:write name="err_name" />

			</html:messages>
         </td>
  </tr>
<tr><td colspan="5" height="10px"></td>
</tr>
  <tr>
   <td align="right" class="txtStyle"><strong>No of Copies:</strong></td>
  <td><html:text property="noofcopies" name="OldTitleEntryActionForm" styleClass="textBoxWidth" /></td>
 <td width="300px"class="txtStyle" >   <html:messages id="err_name" property="price">
				<bean:write name="err_name" />

			</html:messages>
         </td>
  </tr>
    <tr><td colspan="5" height="10px"></td>
</tr>
  <tr>
   <td align="right" class="txtStyle"><strong>Price:</strong></td>
  <td><html:text property="price" name="OldTitleEntryActionForm"  styleClass="textBoxWidth" /></td>
 <td width="300px"class="txtStyle" >   <html:messages id="err_name" property="price">
				<bean:write name="err_name" />

			</html:messages>
         </td>
  </tr>
  <tr><td colspan="5" height="5px"></td>
</tr>


<tr><td></td><td></td></tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr><td colspan="5" height="10px"></td>
</tr>
<tr>
<td align="center" colspan="3">
    <input name="button" type="submit" value="<%=button%>" class="txt1" />
	&nbsp;&nbsp;&nbsp;<input name="button" type="submit" value="Cancel" class="txt1"/></td>
</tr><tr><td colspan="5" height="5px"></td>
</tr>
</table>

  </html:form>
    </body>
</html>
