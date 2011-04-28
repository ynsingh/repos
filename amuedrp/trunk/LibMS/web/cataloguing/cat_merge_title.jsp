<%-- 
    Document   : cat_merge_title
    Created on : Mar 4, 2011, 7:34:15 PM
    Author     : EdRP-04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
    <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
    <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
    <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
 <jsp:include page="/admin/header.jsp"/>
    <html>
    <head>
             <%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <link href="<%=request.getContextPath()%>/css/newformat.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <table width="100%" style="position: absolute; top: 20%; left: 0%">
            <tr><td><IFRAME name="f1" src="<%=request.getContextPath()%>/cataloguing/cat_viewall_biblio.jsp" frameborder="0" id="f1" width="100%" height="300px;"></IFRAME></td></tr>
            <html:form action="/merge" method="post">
          <html:hidden property="library_id" name="BibliographicDetailEntryActionForm" value="<%=library_id%>"/>
                <html:hidden property="sublibrary_id" name="BibliographicDetailEntryActionForm" value="<%=sub_library_id%>" />
                <html:hidden property="main_entry" name="BibliographicDetailEntryActionForm" value="Old"/>
                <html:hidden property="statement_responsibility" name="BibliographicDetailEntryActionForm" value="Old"/>
                <html:hidden property="call_no" name="BibliographicDetailEntryActionForm" value="Old"/>
                <html:hidden property="accession_type" name="BibliographicDetailEntryActionForm" value="Old"/>
                <html:hidden property="book_type" name="BibliographicDetailEntryActionForm" value="Old"/>
                <html:hidden property="document_type" name="BibliographicDetailEntryActionForm"/>
                <html:hidden property="title" name="BibliographicDetailEntryActionForm"/>
                 <html:hidden property="isbn10" name="BibliographicDetailEntryActionForm"/>
                 <html:hidden property="no_of_copies" name="BibliographicDetailEntryActionForm"/>

                     <tr><td height="30px;"></td></tr>
                     <tr> <td align="center"> <input type="submit" name="button" value="Merge Title" Class="txt1"/>
                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit" name="button" value="Cancel" Class="txt1"/>
                 </td></tr>

</html:form>
  </table>  </body>
</html>
