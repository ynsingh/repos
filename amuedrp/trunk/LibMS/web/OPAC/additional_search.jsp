    <%@ page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="java.util.*,com.myapp.struts.hbm.*,com.myapp.struts.opac.*"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="java.io.*"%>
    <%@ page import="java.util.ResourceBundle"%>
    <%@ page import="java.util.Locale"%>
    <%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
    <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
    <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

    <script>
          function next(){
         <%
     int pageNumber=0;
     if(request.getParameter("page") != null) {
       session.setAttribute("page", request.getParameter("page"));
       pageNumber = Integer.parseInt(request.getParameter("page"));
     } else {
       session.setAttribute("page", "1");
     }
     String nextPage = (pageNumber +1) + "";%>
     var loc="<%=request.getContextPath()%>/OPAC/additional.do?page="+<%=nextPage%>;
     location.href= loc;
    }
<%
    //CHECK IF CLICK ON PREVIOUS BUTTON
    if(request.getParameter("flag")!=null)
    {
       if(opacList.size()<100)
            row=row-(100+opacList.size());
         else
           row=row-200;
     }
     //CHECK IF CLICK ON RESTART SEARCH
     if(pageNumber==0)
         row=0;
    %>
    function previous()
    {
     <%
     String previousPage ="";
     if(pageNumber>=1)
        previousPage = (pageNumber -1) + "";
    else
        previousPage = 0 + "";
    %>
    var loc="<%=request.getContextPath()%>/OPAC/additional.do?page="+<%=previousPage%>+"&flag=true";
    location.href= loc;
    }

        </script>
</head>
<jsp:include page="opacheader.jsp"></jsp:include>
<body style="" >
<%!
ArrayList<OpacDoc> opacList;
   ArrayList result;
   int fromIndex=0, toIndex;
   int tcount =0;
   int row=0,from=0;
   int tpage=0;
   Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";
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

if(session.getAttribute("additional_search_list")!=null){

        String path= request.getContextPath();
        pageContext.setAttribute("path", path);
        opacList = new ArrayList ();
        result = new ArrayList ();
        result = (ArrayList)session.getAttribute("additional_search_list");
         from=(Integer)request.getAttribute("from");
        row=from;
        int j=0;
        while(j<result.size())
        {
            BibliographicDetails obj=(BibliographicDetails)result.get(j);
            OpacDoc obj1=new OpacDoc();
            obj1.setBiblioid(obj.getId().getBiblioId());
            obj1.setTitle(obj.getTitle());
            obj1.setCallno(obj.getCallNo());
            obj1.setRowno(row+1);
            obj1.setLibrary_id(obj.getId().getLibraryId().toUpperCase());
            obj1.setSublibrary_id(obj.getId().getSublibraryId().toUpperCase());
            obj1.setMain_entry(obj.getMainEntry());
            obj1.setPublisher(obj.getPublisherName());
             obj1.setSubject(obj.getSubject());
            obj1.setPubplace(obj.getPublicationPlace());
            obj1.setPub_yr(String.valueOf(obj.getPublishingYear()));
            obj1.setImage(obj.getImage());
            obj1.setDigitaldata(obj.getDigitalData());
            obj1.setComment(obj.getDigitalComment());

            j++;
            row++;
        opacList.add(obj1);
        }

            row=(Integer)request.getAttribute("to");
        int tcount=0;
        tcount =(Integer)session.getAttribute("simple_search_nor");


        int pagesize = 100;
        if ((toIndex = fromIndex+100) >= opacList.size())
        toIndex = opacList.size();
        request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
        pageContext.setAttribute("tCount", tcount);
        pageContext.setAttribute("pagesize", pagesize);
        path= request.getContextPath();
        pageContext.setAttribute("path", path);
        pageContext.setAttribute("project",request.getContextPath());

  %>
 <table align="center" dir="<%=rtl%>" width="100%" height="400px" class="datagrid" style="">
 
     <tr  dir="<%=rtl%>">
         <td   align="<%=align%>" valign="top" dir="<%=rtl%>" height="300px" style="border-top: dashed 1px cyan;border-bottom: dashed 1px cyan;">
<%
if(tcount==0)
{
%>
<i>    Additional Search Results>> <%=tcount%> Record Found</i>
<%}
else
{%>
<table height="300px" width="100%" dir="<%=rtl%>"><tr><td valign="top" align="left" >
            <table class="txt2" width="100%">
                <tr><td><i>   Additional Search Results>> <%=tcount%> Record Found&nbsp;&nbsp;&nbsp;  No of Records per page >>100 &nbsp;&nbsp;&nbsp;Record No : <%=from+1%> to <%=row%></i></td>
              <td align="right">      <% if(pageNumber>0){
           %>
  <input type="button" onclick="previous()" value="previous" class="btnapp"/>
  <%}%>
           <% if(opacList.size()<pagesize){

    }else{%>
    <input type="button" onclick="next()" value="next" class="btnapp"/>
    <%}%>

                    </td></tr>
            </table>
      <table class="datagrid" width="100%" style="border:dashed 1px cyan;">
            <tr class="opacgrid" ><td width="5px"  >
                         <i>Sno</i></td>
                     <td width="10%">
                         <i>Book Cover Page</i></td>
                     <td width="10%">
                         <i>E-Content</i></td>
                     <td width="10%" >
                         <i>Title</i></td>
              <td   >
                  <i>Main Entry</i></td>
              <td   >
                         <i>Call No</i></td>
              <td   >
                         <i>Publisher Name</i></td>
              <td   >
                         <i>Publishing Year</i></td>
              <td   >
                         <i>Library</i></td>
              <td   >
                         <i>SubLibrary</i></td>


                 </tr>
        <% for(int i=0;i<opacList.size();i++){
   if((i%2)==0){
    %>

        <tr>
            <%}else{%>
        <tr class="alternaterows">
            <%}%>
            <td width="5px"   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>&h=t"><%=opacList.get(i).getRowno() %></a></td>
             <td width="10%"   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>&h=t"><img src="<%=request.getContextPath()%>/admin/logo1.jsp?x=<%=opacList.get(i).getImage() %>" height="80px" width="80px"></a>

            </td>
            <td width="10%"   style="border-top:dashed 1px cyan;">
                <% if(opacList.get(i).getDigitaldata()!=null){%>
                <a href="<%=request.getContextPath()%>/admin/logo1.jsp?x=<%=opacList.get(i).getDigitaldata() %>" target="_blank"><img src="<%=request.getContextPath()%>/images/econtent.GIF" height="30px" width="100px"/> <br>Comment :<%=opacList.get(i).getComment() %></a>
            <%}else{%>
            Not-available
                <%}%>
            </td>
            <td align="left" width="10%"   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>&h=t"><%=opacList.get(i).getTitle() %></a>
            </td>
            <td width="20%"   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>&h=t"><%=opacList.get(i).getMain_entry() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>&h=t"><%=opacList.get(i).getCallno() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>&h=t"><%=opacList.get(i).getPublisher() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>&h=t"><%=opacList.get(i).getPub_yr() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>&h=t"><%=opacList.get(i).getLibrary_id() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>&h=t"><%=opacList.get(i).getSublibrary_id() %></a>
            </td>
        </tr>
        <%}%>
    </table>
<%--<ui:dataGrid items="${opacList}"   var="doc" name="datagrid1" cellPadding="0"  cellSpacing="0" styleClass="datagrid">
  <columns>

<column width="5px">
      <header value="Sno" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.rowno}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>

    </column>
    <column width="20%">
      <header value="${Title}" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.title}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>

    </column>
      <column width="15%">
      <header value="MainEntry" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.main_entry}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>

    </column>
 <column width="20%">
      <header value="Subject" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.subject}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>

    </column>
      <column width="10%">
      <header value="CallNo" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.callno}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>

    </column>
 <column width="15%">
      <header value="Publisher Name" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.publisher}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>

    </column>
       <column width="15%">
      <header value="Publishing Place" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.pubplace}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>

    </column>
<column width="10%">
      <header value="Publishing Year" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.pub_yr}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>

    </column>
      <column width="20%">
      <header value="library_id" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.library_id}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>

    </column>
       <column width="20%">
      <header value="sublibrary_id" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.sublibrary_id}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>

    </column>

  </columns>
 <footer        styleClass="footer" show="true"/>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows" />

  <paging size="${pagesize}" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>

</ui:dataGrid>--%>
</td></tr>
    <tr><td style="border-top: dashed 1px cyan;border-bottom: dashed 1px cyan;" align="right">
            
          <% if(pageNumber>0){%>
  <input type="button" onclick="previous()" value="previous" class="btnapp"/>
  <%}%>
           <% if(opacList.size()<pagesize){

    }else{%>
    <input type="button" onclick="next()" value="next" class="btnapp"/>
    <%}%>
  

        </td></tr>
</table>
  <%}%>
  </td>
  </tr></table>
  <%}else if(session.getAttribute("additional_search_list1")!=null){

        opacList = new ArrayList ();
        result = new ArrayList ();
        result = (ArrayList<SearchPOJO>)session.getAttribute("additional_search_list1");
          from=(Integer)request.getAttribute("from");
        row=from;

            int j=0;
        while(j<result.size())
        {
            SearchPOJO obj=(SearchPOJO)result.get(j);
            OpacDoc obj1=new OpacDoc();
            obj1.setBiblioid(obj.getBibliographicDetailsLang().getId().getBiblioId());
            obj1.setTitle(obj.getBibliographicDetailsLang().getTitle());
            obj1.setCallno(obj.getBibliographicDetailsLang().getCallNo());
            obj1.setRowno(row+1);
            obj1.setLibrary_id(obj.getBibliographicDetailsLang().getId().getLibraryId().toUpperCase());
            obj1.setSublibrary_id(obj.getBibliographicDetailsLang().getId().getSublibraryId().toUpperCase());
            obj1.setMain_entry(obj.getBibliographicDetailsLang().getMainEntry());
            obj1.setPublisher(obj.getBibliographicDetailsLang().getPublisherName());
            obj1.setPubplace(obj.getBibliographicDetailsLang().getPublicationPlace());
              obj1.setSubject(obj.getBibliographicDetailsLang().getSubject());
               obj1.setPub_yr(obj.getBibliographicDetailsLang().getPublishingYear());
                obj1.setImage(obj.getBibliographicDetailsLang().getImage());
            obj1.setDigitaldata(obj.getBibliographicDetailsLang().getDigitalData());
            obj1.setComment(obj.getBibliographicDetailsLang().getDigitalComment());
            j++;
            row++;
        opacList.add(obj1);
        }
       row=(Integer)request.getAttribute("to");



        int tcount=0;
        tcount =(Integer)session.getAttribute("simple_search_nor");


        int pagesize = 100;
        if ((toIndex = fromIndex+100) >= opacList.size())
        toIndex = opacList.size();
        request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
        pageContext.setAttribute("tCount", tcount);
        pageContext.setAttribute("pagesize", pagesize);
        pageContext.setAttribute("project",request.getContextPath());
%>
<%

%>
<table align="center" dir="<%=rtl%>" width="100%" height="400px" class="datagrid"  >

     <tr  dir="<%=rtl%>">
         <td  align="center" width="100%"  valign="top" dir="<%=rtl%>" height="300px">
<%
if(tcount==0)
{
%>
<i>    Additional Search Results>><span class="err"> <%=tcount%> Record Found</span></i>
<%}
else
{%>
<table height="300px" width="100%" dir="<%=rtl%>"><tr><td valign="top" align="left" >
            <table class="txt2" width="100%">
                <tr><td><i>   Additional Search Results>> <%=tcount%> Record Found&nbsp;&nbsp;&nbsp;  No of Records per page >>100 &nbsp;&nbsp;&nbsp;Record No : <%=from+1%> to <%=row%></i></td>
              <td align="right">      <% if(pageNumber>0){
           %>
  <input type="button" onclick="previous()" value="previous" class="btnapp"/>
  <%}%>
           <% if(opacList.size()<pagesize){

    }else{%>
    <input type="button" onclick="next()" value="next" class="btnapp"/>
    <%}%>

                    </td></tr>
            </table>
      <table class="datagrid" width="100%" style="border:dashed 1px cyan;">
            <tr class="opacgrid"><td width="5px"  >
                         <i>Sno</i></td>
                     <td width="10%">
                         <i>Book Cover Page</i></td>
                      <td width="10%">
                         <i>E-Content</i></td>
                     <td width="10%" >
                         <i>Title</i></td>
              <td   >
                  <i>Main Entry</i></td>
              <td   >
                         <i>Call No</i></td>
              <td   >
                         <i>Publisher Name</i></td>
              <td   >
                         <i>Publishing Year</i></td>
              <td   >
                         <i>Library</i></td>
              <td   >
                         <i>SubLibrary</i></td>


                 </tr>
        <% for(int i=0;i<opacList.size();i++){
   if((i%2)==0){
    %>

        <tr>
            <%}else{%>
        <tr class="alternaterows">
            <%}%>
            <td width="5px"   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails1.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>&h=t"><%=opacList.get(i).getRowno() %></a></td>
            <td width="10%"   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails1.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>&h=t"><img src="<%=request.getContextPath()%>/admin/logo1.jsp?x=<%=opacList.get(i).getImage() %>" height="80px" width="80px"></a>

            </td>
            <td width="10%"   style="border-top:dashed 1px cyan;">
                <% if(opacList.get(i).getDigitaldata()!=null){%>
               <a href="<%=request.getContextPath()%>/admin/logo1.jsp?x=<%=opacList.get(i).getDigitaldata() %>" target="_blank"><img src="<%=request.getContextPath()%>/images/econtent.GIF" height="30px" width="100px"/> <br>Comment :<%=opacList.get(i).getComment() %></a>
            <%}else{%>
            Not-available
                <%}%>
            </td>
            <td align="left" width="10%"   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails1.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>&h=t"><%=opacList.get(i).getTitle() %></a>
            </td>
            <td width="20%"   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails1.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>&h=t"><%=opacList.get(i).getMain_entry() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails1.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>&h=t"><%=opacList.get(i).getCallno() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails1.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>&h=t"><%=opacList.get(i).getPublisher() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails1.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>&h=t"><%=opacList.get(i).getPub_yr() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails1.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>&h=t"><%=opacList.get(i).getLibrary_id() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails1.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>&h=t"><%=opacList.get(i).getSublibrary_id() %></a>
            </td>
        </tr>
        <%}%>
    </table>
<%--<ui:dataGrid items="${opacList}"   var="doc" name="datagrid1" cellPadding="0"  cellSpacing="0" styleClass="datagrid">
  <columns>

<column width="5px">
      <header value="Sno" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.rowno}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>

    </column>
    <column width="20%">
      <header value="${Title}" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.title}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>

    </column>
      <column width="15%">
      <header value="MainEntry" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.main_entry}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>

    </column>
 <column width="20%">
      <header value="Subject" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.subject}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>

    </column>
      <column width="10%">
      <header value="CallNo" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.callno}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>

    </column>
 <column width="15%">
      <header value="Publisher Name" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.publisher}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>

    </column>
       <column width="15%">
      <header value="Publishing Place" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.pubplace}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>

    </column>
<column width="10%">
      <header value="Publishing Year" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.pub_yr}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>

    </column>
      <column width="20%">
      <header value="library_id" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.library_id}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>

    </column>
       <column width="20%">
      <header value="sublibrary_id" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.sublibrary_id}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>

    </column>

  </columns>
 <footer        styleClass="footer" show="true"/>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows" />

  <paging size="${pagesize}" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>

</ui:dataGrid>--%>
</td></tr>
    <tr><td style="border-top: dashed 1px cyan;border-bottom: dashed 1px cyan;" align="right">

          <% if(pageNumber>0){%>
  <input type="button" onclick="previous()" value="previous" class="btnapp"/>
  <%}%>
           <% if(opacList.size()<pagesize){

    }else{%>
    <input type="button" onclick="next()" value="next" class="btnapp"/>
    <%}%>


        </td></tr>
</table>
</td></tr>
</table>
    <%}%>


  <%}%>
    </body>
    <jsp:include page="opacfooter.jsp"></jsp:include>
</html>


