<%--
    Document   : cat_update_titles
    Created on : Jan 12, 2011, 10:49:28 AM
    Author     : Asif Iqubal
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
String msg1=(String)request.getAttribute("msg1");
String msg2=(String)request.getAttribute("msg2");
%>
<html>
    <head>
 <script>
            function back()
            {
                location.href="<%=request.getContextPath()%>/admin/main.jsp";
            }
 </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
             <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
    </head>
   <jsp:include page="/admin/header.jsp"/>
    <body>
        <html:form method="post" action="/catOldBiblioAction" style="position:absolute; left:30%; top:30%">
            <table border="1" class="table" width="400">
                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" ><b>Manage Old Bibliographic Details</b></td></tr>
                <html:hidden property="library_id" name="BibliographicDetailEntryActionForm" value="<%=library_id%>"/>
                <html:hidden property="sublibrary_id" name="BibliographicDetailEntryActionForm" value="<%=sub_library_id%>" />
                <html:hidden property="main_entry" name="BibliographicDetailEntryActionForm" value="Old"/>
                <html:hidden property="statement_responsibility" name="BibliographicDetailEntryActionForm" value="Old"/>
                <html:hidden property="call_no" name="BibliographicDetailEntryActionForm" value="Old"/>
                <html:hidden property="accession_type" name="BibliographicDetailEntryActionForm" value="Old"/>
                <html:hidden property="book_type" name="BibliographicDetailEntryActionForm" value="Old"/>
                <tr><td>
                <table border="0" cellspacing="4" cellpadding="1" align="center">
                    <tr><td><br><br></td></tr>
                    <tr><td rowspan="7" width="100">
               <strong>Document Type:<a class="star">*</a></strong><br>
  <html:select property="document_type" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" >
       <html:option value="">Select</html:option>
            <html:option value="Book">Book</html:option>
            <html:option value="CD">CD</html:option>
  </html:select>
            <br><span class="err">   <html:messages id="err_name" property="document_type">
        <bean:write name="err_name" />
    </html:messages></span><br>
                           <strong>Enter Title:<a class="star">*</a></strong>   <br>
                            <html:text property="title" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth"/><br>
                                <span class="err"><html:messages id="err_name" property="title">
                          <bean:write name="err_name" />
                            </html:messages></span><br>
                                 <strong>Enter ISBN:</strong><br>
                            <html:text property="isbn10" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth"/><br><br>
                            <div class="mandatory">   <a class="star">*</a>indicated fields are mandatory</div>
                        </td></tr>
                    <tr><td width="40"></td><td><input type="Submit" name="button" value="New" Class="txt1"/></td></tr>
                    <tr><td width="40"></td><td><input type="Submit" name="button" value="Update" Class="txt1"/></td></tr>
                    <tr><td width="40"></td><td><input type="Submit" name="button" value="View" Class="txt1"/></td></tr>
                    <tr><td width="40"></td><td><input type="Submit" name="button" value="Delete" Class="txt1"/></td></tr>
                    <tr><td width="40"></td><td><input type="button" name="button" value="Back" Class="txt1" onclick="back();"/></td></tr>
                        <tr><td height="20px;"></td></tr>
                    <tr><td colspan="2" align="center"><br><br></td></tr>
                    <tr><td colspan="2">
                        <%  if(msg1!=null)
    {%>
   <span style="font-size:12px;font-weight:bold;color:red;" ><%=msg1%></span>
<%}%>
    <%  if(msg2!=null)
    {%>
    <span style="font-size:12px;font-weight:bold;color:blue;" ><%=msg2%></span>
<%}%>
                        </td> </tr>
  </table>
                    </td></tr>

            </table>
    </html:form>
 </body>
</html>
