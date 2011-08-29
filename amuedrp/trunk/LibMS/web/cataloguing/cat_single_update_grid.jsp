<%-- 
    Document   : cat_single_update_grid
    Created on : Mar 14, 2011, 12:18:05 PM
    Author     : EdRP-04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
    <%@ page import="java.util.*"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
    <%@ page import="java.sql.*,com.myapp.struts.hbm.*"%>
    <%@ page import="java.io.*"   %>
    <%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
    <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
    <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
           <script>
            function send()
{
    top.location="<%=request.getContextPath()%>/cataloguing/cat_accession.jsp";
    return false;
}</script>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
    boolean page=true;
    String newbutton;
    String newbutton2;
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";page=true;}
    else{ rtl="RTL";align="right";page=false;}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
    %>
             <%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
String title=(String)session.getAttribute("title");
String doc_type=(String)session.getAttribute("doc_type");
String isbn10=(String)session.getAttribute("isbn10");
String button=(String)session.getAttribute("button");
 if(button.equals("Update"))
 {
   newbutton=resource.getString("cataloguing.cattitleeditgrid1.edit");
   newbutton2=resource.getString("cataloguing.catoldtitle.back");
}
 if(button.equals("View"))
 {
   newbutton=resource.getString("cataloguing.catoldtitle.view");
   newbutton2=resource.getString("cataloguing.catoldtitle.back");
}
 if(button.equals("New"))
 {
   newbutton=resource.getString("cataloguing.catownaccession.newitem");
   newbutton2=resource.getString("cataloguing.catoldtitle.back");
}
if(button.equals("Delete"))
 {
   newbutton=resource.getString("cataloguing.catoldtitle.delete");
   newbutton2=resource.getString("cataloguing.catoldtitle.back");
}
String msg1=(String) request.getAttribute("msg1");
String msg2=(String) request.getAttribute("msg2");
String path= request.getContextPath();
 pageContext.setAttribute("path", path);
%>
<script type="text/javascript" language="javascript">
    function submitNew()
{
    <% if(button.equalsIgnoreCase("New")||button.equalsIgnoreCase("View")){%>
    var buttonvalue="View";
    <% session.setAttribute("edit2", "View");%>
    <%}%>
    <% if(button.equalsIgnoreCase("Update")){%>
    var buttonvalue="Edit";
    <% session.setAttribute("edit2", "Edit");%>
    <%}%>
    <% if(button.equalsIgnoreCase("Delete")){%>
    var buttonvalue="Delete";
    <% session.setAttribute("edit2", "Delete");%>
    <%}%>
    document.getElementById("button1").setAttribute("value", buttonvalue);
    return true;
}
    function submitImport()
{
    <% if(button.equalsIgnoreCase("New")){%>
    var buttonvalue="New Item";
    <%}%>
    document.getElementById("button1").setAttribute("value", buttonvalue);
    return true;
}
function NewTitle()
{
    var buttonvalue="New";
    document.getElementById("button1").setAttribute("value", buttonvalue);
    return true;
}
             </script>

    <%!
int fromIndex,toIndex;
int pagesize=2,size;
int pageIndex;
int noofpages;
int modvalue;
String index;
List obj1;
%>
<%
int i=0;
 int j=0;
List<AccessionRegister> l11=(List<AccessionRegister>)session.getAttribute("opacList");
 index = request.getParameter("pageIndex");
 if(index!=null){
     pageIndex = Integer.parseInt(index);
  }
 else{
     pageIndex = 1;
     }
 if(l11!=null)
        size = l11.size();
 else
        size = 0;
 //for calculating no of pages required
 modvalue = size%pagesize;
 if(modvalue>0)
    noofpages = size/pagesize+1;
 else
     noofpages = size/pagesize;
 //to calculate the starting item and ending item index for the desired page
fromIndex = (pageIndex-1)*pagesize;
toIndex = fromIndex + pagesize;
if(toIndex>size)toIndex=size;
//fromIndex++;
%>

<link href="<%=request.getContextPath()%>/css/newformat.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <table style="position:absolute; left: 5%; top: 5%;" dir="<%=rtl %>">
            <tr bgcolor="#E0E888"><td colspan="8" align="center"><b><%= resource.getString("cataloguing.cataccessionentry.accessionheader")%></b></td></tr>
            <tr bgcolor="#E0E8F5"><td width="100"><%= resource.getString("cataloguing.catsingleviewgrid.recordno")%></td><td width="200"><%= resource.getString("cataloguing.cataccessionentry.accessionno")%></td><td width="100"><%= resource.getString("cataloguing.cataccessionentry.location")%></td><td width="100"><%= resource.getString("cataloguing.catviewownbibliogrid.action")%></td></tr>
        <logic:iterate id="AccessionRegister" name="opacList" offset="<%=String.valueOf(fromIndex)%>" length="<%= String.valueOf(pagesize) %>">
          <html:form action="/cataloguing/accessionedit">
                <html:hidden property="main_entry" name="BibliographicDetailEntryActionForm" value="Old"/>
                <html:hidden property="statement_responsibility" name="BibliographicDetailEntryActionForm" value="Old"/>
                <html:hidden property="call_no" name="BibliographicDetailEntryActionForm" value="Old"/>
                <html:hidden property="accession_type" name="BibliographicDetailEntryActionForm" value="Old"/>
                <html:hidden property="book_type" name="BibliographicDetailEntryActionForm" value="Old"/>
                <html:hidden property="document_type" name="BibliographicDetailEntryActionForm" value="<%=doc_type%>"/>
                <html:hidden property="title" name="BibliographicDetailEntryActionForm" value="<%=title%>"/>
                <html:hidden property="isbn10" name="BibliographicDetailEntryActionForm" value="<%=isbn10%>"/>
                <html:hidden property="no_of_copies" name="BibliographicDetailEntryActionForm"/>   <tr bgcolor="#98AFC7">
        <input type="hidden" name="library_id" name="BibliographicDetailEntryActionForm" value="<bean:write name="AccessionRegister" property="id.libraryId"/>"/>
        <input type="hidden" name="sublibrary_id" name="BibliographicDetailEntryActionForm" value="<bean:write name="AccessionRegister" property="id.sublibraryId"/>" />
        <input type="hidden" name="biblio_id" value='<bean:write name="AccessionRegister" property="bibliographicDetails.id.biblioId"/>'/>
        <input type="hidden" name="accession_no" value='<bean:write name="AccessionRegister" property="accessionNo"/>'/>
        <td><bean:write name="AccessionRegister" property="id.recordNo"/></td>
            <td><bean:write name="AccessionRegister" property="accessionNo"/></td>
            <td><bean:write name="AccessionRegister" property="location"/></td>
            <td><a><input type="submit" name="button1" value="<%=newbutton%>"  onclick="return submitNew()" style="border: hidden; cursor: pointer;"/></a></td>
        </tr>
         <input type="hidden" id="button1" name="button"/>
        </html:form>
        </logic:iterate>
 <tr><td colspan="7" align="center" height="20px;"></td></tr>
        <tr><td colspan="7" align="center"><input type="button" onclick="return send()"  value="<%=newbutton2%>" /></td></tr>
        <tr><td align="left" colspan="3">
                    <%  if(msg1!=null)
    {%>
   <span style="font-size:12px;font-weight:bold;color:blue;"><%=msg1%></span>
<%}%>
                    <%  if(msg2!=null)
    {%>
   <span style="font-size:12px;font-weight:bold;color:red;"><%=msg2%></span>
<%}%>
    </td></tr>
             </table>
    </body>
</html>
