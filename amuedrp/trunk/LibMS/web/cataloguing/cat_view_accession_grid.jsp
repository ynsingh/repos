<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@page import="java.util.*"%>
    <%@page import="com.myapp.struts.hbm.*,java.util.*"%>
    <%@page import="java.util.ResourceBundle"%>
    <%@page import="java.util.Locale"%>
    <%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
    <%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
    <%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
    <jsp:include page="/admin/header.jsp"/>
<html>
    <head>
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
           %>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Asif Iqubal" content="MCA,AMU">
      <title></title>
       <link href="<%=request.getContextPath()%>/css/newformat.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function send()
{
    window.location="<%=request.getContextPath()%>/cataloguing/cat_accession.jsp";
    return false;
}
</script>
<script type="text/javascript" language="javascript">
    function submitNew()
{
    <% if(button.equalsIgnoreCase("View")){%>
    var buttonvalue="View";
    <% session.setAttribute("edit1", "View");%>
    <%}%>
    <%if(button.equalsIgnoreCase("New")){%>
    var buttonvalue="New Item";
    <% session.setAttribute("edit1", "New Item");%>
    <%}%>
    <% if(button.equalsIgnoreCase("Update")){%>
    var buttonvalue="Edit";
     <% session.setAttribute("edit1", "Edit");%>
    <%}%>
    <% if(button.equalsIgnoreCase("Delete")){%>
    var buttonvalue="Delete";
    <% session.setAttribute("edit1", "Delete");%>
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
</head>
<body bgcolor="#FFFFFF">
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
List<BibliographicDetails> l11=(List<BibliographicDetails>)session.getAttribute("opacList");
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
            <%if(!l11.isEmpty()){%>
        <table style="position:absolute; left: 5%; top: 30%;" dir="<%=rtl %>">
            <tr bgcolor="#E0E888"><td colspan="8" align="center"><b><%= resource.getString("cataloguing.cataccessionentry.bibliodetail")%></b></td></tr>
            <tr bgcolor="#E0E8F5"><td width="100"><%= resource.getString("cataloguing.catviewownbibliogrid.biblioid")%></td><td width="200"><%= resource.getString("cataloguing.catoldtitle.documenttype")%></td><td width="200"><%= resource.getString("cataloguing.catoldtitleentry1.title")%></td><td width="100"><%= resource.getString("cataloguing.catoldtitleentry1.mainentry")%></td><td width="100"><%= resource.getString("cataloguing.catoldtitleentry1.publishername")%></td><td width="100"><%= resource.getString("cataloguing.catoldtitleentry1.edition")%></td><td width="100"><%= resource.getString("cataloguing.catviewownbibliogrid.action")%></td></tr>
        <logic:iterate id="BibliographicDetails" name="opacList" offset="<%=String.valueOf(fromIndex)%>" length="30">
         <html:form action="/cataloguing/accessiongrid">
                <html:hidden property="main_entry" name="BibliographicDetailEntryActionForm" value="Old"/>
                <html:hidden property="statement_responsibility" name="BibliographicDetailEntryActionForm" value="Old"/>
                <html:hidden property="call_no" name="BibliographicDetailEntryActionForm" value="Old"/>
                <html:hidden property="accession_type" name="BibliographicDetailEntryActionForm" value="Old"/>
                <html:hidden property="book_type" name="BibliographicDetailEntryActionForm"/>
                <html:hidden property="document_type" name="BibliographicDetailEntryActionForm" value="<%=doc_type%>"/>
                <html:hidden property="title" name="BibliographicDetailEntryActionForm" value="<%=title%>"/>
                <html:hidden property="isbn10" name="BibliographicDetailEntryActionForm" value="<%=isbn10%>"/>
                <html:hidden property="no_of_copies" name="BibliographicDetailEntryActionForm"/>
            <tr bgcolor="#98AFC7">
        <input type="hidden" name="library_id" name="BibliographicDetailEntryActionForm" value="<bean:write name="BibliographicDetails" property="id.libraryId"/>"/>
        <input type="hidden" name="sublibrary_id" name="BibliographicDetailEntryActionForm" value="<bean:write name="BibliographicDetails" property="id.sublibraryId"/>" />
        <input type="hidden" name="biblio_id" value='<bean:write name="BibliographicDetails" property="id.biblioId"/>'/>
            <td><bean:write name="BibliographicDetails" property="id.biblioId"/></td>
            <td><bean:write name="BibliographicDetails" property="documentType"/></td>
            <td><bean:write name="BibliographicDetails" property="title"/></td>
            <td><bean:write name="BibliographicDetails" property="mainEntry"/></td>
            <td><bean:write name="BibliographicDetails" property="publisherName"/></td>
            <td><bean:write name="BibliographicDetails" property="edition"/></td>
            <td><a><input type="submit" name="button1" value="<%=newbutton%>" onclick="return submitNew()" style="border: hidden; cursor: pointer;"/></a></td>
        </tr>
         <input type="hidden" id="button1" name="button"/>
        </html:form>
        </logic:iterate>
 <tr><td colspan="7" align="center" height="20px;"></td></tr>
        <tr><td colspan="7" align="center"><input type="button" onclick="return send()"  value="<%=newbutton2%>" /></td></tr>
                     </table>
                     <%}%>
 </body>
</html>
