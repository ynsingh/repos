    <%@page import="com.myapp.struts.opac.OpacDoc"%>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridTag"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="java.io.*"   %>
    <%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script language="javascript" >

function showcount()
{
    var page;
    page = document.getElementById("pagesize").value;
    if (page>0){
    location.href="/LibMS/OPAC/simple_search.jsp?pagesize="+page;}
}
</script>
</head>
<body style="background-color:#e0e8f5;">
<%!
  
   Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
   OpacDoc Ob;
   ArrayList opacList;
   int fromIndex, toIndex;
%>

<%
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

   opacList = new ArrayList ();
   int tcount =0;
   int pagesize=10;
   


if(session.getAttribute("documentDetail")!=null){
 opacList = new ArrayList ();
 opacList = (ArrayList)session.getAttribute("documentDetail");
 if(opacList!=null)tcount =opacList.size();



  //   String page1 = (String)request.getParameter("pagesize");
     //System.out.println("page1="+page1);
  // if (page1!=null) pagesize = Integer.parseInt(page1);
   fromIndex = (int)DataGridParameters.getDataGridPageIndex(request, "datagrid1");

   if ((toIndex = fromIndex+(int)pagesize) >= tcount)
   toIndex = tcount;
   if(opacList!=null)request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
   pageContext.setAttribute("pagesize", pagesize);
   pageContext.setAttribute("fromIndex", fromIndex);
   pageContext.setAttribute("fromIndex1", fromIndex+1);
pageContext.setAttribute("toIndex1", toIndex);
pageContext.setAttribute("project", request.getContextPath());
%>
<%
  String Title=resource.getString("opac.simplesearch.title");
  pageContext.setAttribute("Title", Title);
  String MainEntry=resource.getString("opac.simplesearch.mainentry");
  pageContext.setAttribute("MainEntry", MainEntry);
  String CallNo=resource.getString("opac.simplesearch.callno.");
  pageContext.setAttribute("CallNo",CallNo);
  String LibraryID=resource.getString("opac.browse.table.Libraryid");
  pageContext.setAttribute("LibraryID",LibraryID);
  String acc="AccessionNo";
  pageContext.setAttribute("AccessionNo",acc); 

%>
<table align="left" width="100%" height="400px" class="datagrid" style="border:solid 1px #e0e8f5;">
   <tr style="background-color:#e0e8f5;"><td    height="18px" align="left" colspan="2">
		<%--<%=resource.getString("opac.browse.browsesearchresult")%>--%>
                <i>AccessionNo Search Result>><%=tcount%> records Found</i>
        </td></tr>
  
     <tr style="background-color:#e0e8f5;">
         <td  align="center" valign="top" height="300px">
        <!--     <input type="text" id="pagesize" onblur="showcount()"/>-->
<%
if(tcount==0)
{
%>
<%--<p class="err"> <%=resource.getString("global.norecordfound")%>--%>

<%
//String msg1=(String)request.getAttribute("msg1");
//if(msg1!=null)
//out.println(msg1);
%>
<%}
else
{%>
<table height="300px" width="100%"><tr><td valign="top">
<ui:dataGrid items="${opacList}"   var="doc" name="datagrid1" cellPadding="0"  cellSpacing="0" styleClass="datagrid">
  <columns>
    <column width="20%">
      <header value="${Title}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.title}" hyperLink="${project}/OPAC/AccessionRecord.do?doc_id=${doc.accessionNo}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  hAlign="left"/>
    </column>

    <column width="20%">
      <header value="${MainEntry}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.mainEntry}" hyperLink="${project}/OPAC/AccessionRecord.do?doc_id=${doc.accessionNo}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  hAlign="left"  />
    </column>

   

 <column width="15%">
      <header value="${AccessionNo}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.accessionNo}" hyperLink="${project}/OPAC/AccessionRecord.do?doc_id=${doc.accessionNo}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"   hAlign="left" />
    </column>
      <column width="15%">
      <header value="${LibraryID}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.id.libraryId}" hyperLink="${project}/OPAC/AccessionRecord.do?doc_id=${doc.accessionNo}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  hAlign="left" />
    </column>
        <column width="15%">
      <header value="SubLibrary" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.id.sublibraryId}" hyperLink="${project}/OPAC/AccessionRecord.do?doc_id=${doc.accessionNo}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  hAlign="left" />
    </column>
 </columns>
<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="${pagesize}" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>

</ui:dataGrid>
</td></tr>
</table>
  <%}%>


  </td></tr></table>
  <%}else if(session.getAttribute("documentDetail2")!=null){
    
       opacList = new ArrayList ();
 opacList = (ArrayList)session.getAttribute("documentDetail2");
// System.out.println(opacList.size());
  
   if(opacList!=null)tcount=opacList.size();
  
  
     String page1 = (String)request.getParameter("pagesize");
     //System.out.println("page1="+page1);
   if (page1!=null) pagesize = Integer.parseInt(page1);
  
   fromIndex = (int)DataGridParameters.getDataGridPageIndex(request, "datagrid1");
   if ((toIndex = fromIndex+(int)pagesize) >= tcount)
   toIndex = tcount;
  // System.out.println("opacList="+opacList.size()+" tcount="+tcount);
   if(opacList!=null)request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
   pageContext.setAttribute("pagesize", pagesize);
%>

<%
  String Title=resource.getString("opac.simplesearch.title");
  pageContext.setAttribute("Title", Title);
  String MainEntry=resource.getString("opac.simplesearch.mainentry");
  pageContext.setAttribute("MainEntry", MainEntry);
  String CallNo=resource.getString("opac.simplesearch.callno.");
  pageContext.setAttribute("CallNo",CallNo);
  String LibraryID=resource.getString("opac.browse.table.Libraryid");
  pageContext.setAttribute("LibraryID",LibraryID);
  String acc="AccessionNo";
  pageContext.setAttribute("AccessionNo",acc);
pageContext.setAttribute("project",request.getContextPath());
%>

<table align="left" width="100%" height="400px" class="datagrid" style="border:solid 1px #e0e8f5;">
   <tr style="background-color:#e0e8f5;"><td    height="18px" align="left" colspan="2">
		<%--<%=resource.getString("opac.browse.browsesearchresult")%>--%>
                <i>AccessionNo Search Result>><%=tcount%> records Found</i>
        </td></tr>

     <tr style="background-color:#e0e8f5;">
         <td  align="center" valign="top" height="300px" width="100%">
        <!--     <input type="text" id="pagesize" onblur="showcount()"/>-->
<%
if(tcount==0)
{
%>
<%--<p class="err"> <%=resource.getString("global.norecordfound")%>--%>

<%
//String msg1=(String)request.getAttribute("msg1");
//if(msg1!=null)
//out.println(msg1);
%>
<%}
else
{%>
<table height="300px"  width="100%"><tr><td valign="top" width="100%">
<ui:dataGrid items="${opacList}"   var="doc" name="datagrid1" cellPadding="0"  cellSpacing="0" styleClass="datagrid">
  <columns>
    <column width="20%">
      <header value="${Title}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.bibliographicDetailsLang.title}" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.bibliographicDetailsLang.id.biblioId}&amp;library_id=${doc.bibliographicDetailsLang.id.libraryId}&amp;sublibrary_id=${doc.bibliographicDetailsLang.id.sublibraryId}"  hAlign="left"/>
    </column>

    <column width="20%">
      <header value="${MainEntry}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.bibliographicDetailsLang.mainEntry}" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.bibliographicDetailsLang.id.biblioId}&amp;library_id=${doc.bibliographicDetailsLang.id.libraryId}&amp;sublibrary_id=${doc.bibliographicDetailsLang.id.sublibraryId}"  hAlign="left"  />
    </column>

   
 <column width="15%">
      <header value="${AccessionNo}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.accessionRegister.accessionNo}" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.bibliographicDetailsLang.id.biblioId}&amp;library_id=${doc.bibliographicDetailsLang.id.libraryId}&amp;sublibrary_id=${doc.bibliographicDetailsLang.id.sublibraryId}" hAlign="left" />
    </column>
      <column width="15%">
      <header value="${LibraryID}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.bibliographicDetailsLang.id.libraryId}" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.bibliographicDetailsLang.id.biblioId}&amp;library_id=${doc.bibliographicDetailsLang.id.libraryId}&amp;sublibrary_id=${doc.bibliographicDetailsLang.id.sublibraryId}"   hAlign="left" />
    </column>
<column width="15%">
      <header value="SubLibraryID" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.bibliographicDetailsLang.id.sublibraryId}" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.bibliographicDetailsLang.id.biblioId}&amp;library_id=${doc.bibliographicDetailsLang.id.libraryId}&amp;sublibrary_id=${doc.bibliographicDetailsLang.id.sublibraryId}"   hAlign="left" />
    </column>
  </columns>
<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="${pagesize}" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>

</ui:dataGrid>
</td></tr>
</table>
  <%}%>


  </td></tr></table>
 <%}%>

    </body>

</html>


