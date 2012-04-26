<!-- SIMPLE SEARCH GRID-->
    <%@page import="com.myapp.struts.opac.*,com.myapp.struts.hbm.*"%>
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
    <link rel="stylesheet" href="/LibMS/css/page.css"/>
    <script language="javascript" >
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
     var loc="<%=request.getContextPath()%>/simple_search.do?page="+<%=nextPage%>;
     location.href= loc;
    }
    <%
    //CHECK IF CLICK ON PREVIOUS BUTTON
    if(request.getParameter("flag")!=null)
    {
     i=i-200;
     }
     //CHECK IF CLICK ON RESTART SEARCH
     if(pageNumber==0)
         i=0;
    %>
    function previous()
    {
       
     <%
     String previousPage ="";
      
     if(pageNumber>=1)
       {
          
         previousPage = (pageNumber -1) + "";
         
         }
    else
     {   previousPage = 0 + "";}
     
    %>
    var loc="<%=request.getContextPath()%>/simple_search.do?page="+<%=previousPage%>+"&flag=true";
    location.href= loc;
    }

    </script>
    </head>
    <body style="background-color:#e0e8f5;margin: 2px 2px 2px 2px ">
    <%!
    OpacDoc Ob;
     int i=0;
     int flag=0;
    ArrayList opacList,result;
    int fromIndex, toIndex;
    static Integer count=0;
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
    %>
    <%
     try
        {
            locale1=(String)session.getAttribute("locale");
            if(session.getAttribute("locale")!=null)
            {
                locale1 = (String)session.getAttribute("locale");
            }
            else locale1="en";
        }
        catch(Exception e){locale1="en";}
        locale = new Locale(locale1);
        if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";}
        else{ rtl="RTL";align="right";}
        ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
        String Title=resource.getString("opac.simplesearch.title");
        pageContext.setAttribute("Title", Title);
        String MainEntry=resource.getString("opac.simplesearch.mainentry");
        pageContext.setAttribute("MainEntry", MainEntry);
        String CallNo=resource.getString("opac.simplesearch.callno.");
        pageContext.setAttribute("CallNo",CallNo);
        String LibraryID=resource.getString("opac.browse.table.Libraryid");
        pageContext.setAttribute("LibraryID",LibraryID);
        pageContext.setAttribute("project",request.getContextPath());
        System.out.println("I am coming");
        // IF SEARCH IS IN ENGLISH
        if(session.getAttribute("simple_search_list")!=null)
        {
        String path= request.getContextPath();
        pageContext.setAttribute("path", path);
        opacList = new ArrayList ();
        result = new ArrayList ();
        result = (ArrayList)session.getAttribute("simple_search_list");
     
        int j=0;
        while(j<result.size())
        {
            BibliographicDetails obj=(BibliographicDetails)result.get(j);
            OpacDoc obj1=new OpacDoc();
            obj1.setBiblioid(obj.getId().getBiblioId());
            obj1.setTitle(obj.getTitle());
            obj1.setCallno(obj.getCallNo());
            obj1.setRowno(i+1);
            obj1.setLibrary_id(obj.getId().getLibraryId().toUpperCase());
            obj1.setSublibrary_id(obj.getId().getSublibraryId().toUpperCase());
            obj1.setMain_entry(obj.getMainEntry());
            obj1.setPublisher(obj.getPublisherName());
            obj1.setPubplace(obj.getPublicationPlace());
            obj1.setPub_yr(obj.getPublishingYear());
            j++;
            i++;
        opacList.add(obj1);    
        }


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
<table align="center" dir="<%=rtl%>" width="100%" height="400px" class="datagrid"  >
  
     <tr  dir="<%=rtl%>">
         <td  align="center" width="100%"  valign="top" dir="<%=rtl%>" height="300px">

<%
if(tcount==0)
{
%>
<i>    <%=resource.getString("opac.simplesearch.smpsearch")%> Results>><span class="err"> <%=tcount%> Record Found</span></i>
<%}
else
{%>
<table height="300px" width="100%" ><tr><td valign="top" align="left">
           <i>    <%=resource.getString("opac.simplesearch.smpsearch")%> Results>> <%=tcount%> Record Found&nbsp;&nbsp;&nbsp;  Records In Current Page >><%=opacList.size()%> &nbsp;&nbsp;&nbsp;Current Page Size >> 100</i>
          <% if(pageNumber>0){
           %>
  <input type="button" onclick="previous()" value="previous" class="datagrid"/>
  <%}%>
           <% if(opacList.size()<pagesize){

    }else{%>
    <input type="button" onclick="next()" value="next" class="datagrid"/>
    <%}%>
  
<ui:dataGrid items="${opacList}"   var="doc" name="datagrid1" cellPadding="0"  cellSpacing="0" styleClass="datagrid">
  <columns>
     
<column width="5px">
      <header value="Sno" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.rowno}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}"  hAlign="left"/>

    </column>
    <column width="20%">
      <header value="${Title}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.title}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}"  hAlign="left"/>
    
    </column>
      <column width="20%">
      <header value="MainEntry" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.main_entry}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}"  hAlign="left"/>

    </column>
      <column width="20%">
      <header value="CallNo" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.callno}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}"  hAlign="left"/>

    </column>
 <column width="20%">
      <header value="Publisher Name" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.publisher}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}"  hAlign="left"/>

    </column>
       <column width="20%">
      <header value="Publishing Place" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.pubplace}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}"  hAlign="left"/>

    </column>
 <column width="10%">
      <header value="Publishing Year" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.pub_yr}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}"  hAlign="left"/>

    </column>
      <column width="20%">
      <header value="library_id" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.library_id}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}"  hAlign="left"/>

    </column>
       <column width="20%">
      <header value="sublibrary_id" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.sublibrary_id}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}"  hAlign="left"/>

    </column>

  </columns>
 <footer        styleClass="footer" show="true"/>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows" />

  <paging size="${pagesize}" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>

</ui:dataGrid>
</td></tr>
<tr>
<td align="right" width="10%" class="header">&nbsp
    <% if(pageNumber>0){
      %>
  <input type="button" onclick="previous()" value="previous" class="datagrid"/>
  <%}%>
    <% if(opacList.size()<pagesize){
        
    }else{%>
    <input type="button" onclick="next()" value="next" class="datagrid"/>
    <%}%>

</td>
</tr>
    </table></td></tr>
</table>
  <%}%>
 
  <%}
        // IF SEARCH IS MLI
        if(session.getAttribute("simple_search_list1")!=null)
        {
        opacList = new ArrayList();
        result = new ArrayList();
        result = (ArrayList<SearchPOJO>)session.getAttribute("simple_search_list1");
            int j=0;
        while(j<result.size())
        {
            SearchPOJO obj=(SearchPOJO)result.get(j);
            OpacDoc obj1=new OpacDoc();
            obj1.setBiblioid(obj.getBibliographicDetailsLang().getId().getBiblioId());
            obj1.setTitle(obj.getBibliographicDetailsLang().getTitle());
            obj1.setCallno(obj.getBibliographicDetailsLang().getCallNo());
            obj1.setRowno(i+1);
            obj1.setLibrary_id(obj.getBibliographicDetailsLang().getId().getLibraryId().toUpperCase());
            obj1.setSublibrary_id(obj.getBibliographicDetailsLang().getId().getSublibraryId().toUpperCase());
            obj1.setMain_entry(obj.getBibliographicDetailsLang().getMainEntry());
            obj1.setPublisher(obj.getBibliographicDetailsLang().getPublisherName());
            obj1.setPubplace(obj.getBibliographicDetailsLang().getPublicationPlace());
              obj1.setPub_yr(obj.getBibliographicDetailsLang().getPublishingYear());
            j++;
            i++;
        opacList.add(obj1);
        }


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
<i>    <%=resource.getString("opac.simplesearch.smpsearch")%> Results>><span class="err"> <%=tcount%> Record Found</span></i>
<%}
else
{%>
<table height="300px" width="100%" ><tr><td valign="top" align="left">
           <i>    <%=resource.getString("opac.simplesearch.smpsearch")%> Results>> <%=tcount%> Record Found&nbsp;&nbsp;&nbsp;  Records In Current Page >><%=opacList.size()%> &nbsp;&nbsp;&nbsp;Current Page Size >> 100</i>
          <% if(pageNumber>0){%>
  <input type="button" onclick="previous()" value="previous" class="datagrid"/>
  <%}%>
           <% if(opacList.size()<pagesize){

    }else{%>
    <input type="button" onclick="next()" value="next" class="datagrid"/>
    <%}%>

<ui:dataGrid items="${opacList}"   var="doc" name="datagrid1" cellPadding="0"  cellSpacing="0" styleClass="datagrid">
  <columns>
<column width="5px">
      <header value="Sno" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.rowno}" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}"  hAlign="left"/>

    </column>

    <column width="20%">
      <header value="${Title}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.title}" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}"  hAlign="left"/>

    </column>

    <column width="30%">
      <header value="${MainEntry}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.main_entry}" hAlign="left" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}"  />
    </column>
 <column width="20%">
      <header value="PublisherName" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.publisher}" hAlign="left" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}"  />
    </column>
 <column width="15%">
      <header value="PublicationPlace" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.pubplace}" hAlign="left" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}"   />
    </column>
<column width="10%">
      <header value="Publishing Year" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.pub_yr}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}"  hAlign="left"/>

    </column>
      <column width="10%">
      <header value="${CallNo}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.callno}" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}"  hAlign="left" />
    </column>


      <column width="15%">
      <header value="${LibraryID}" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.library_id}" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}"  hAlign="left" />
    </column>
       <column width="15%">
      <header value="SubLibrary" hAlign="left" styleClass="header"/>
      <item  styleClass="item"  value="${doc.sublibrary_id}" hyperLink="${project}/OPAC/viewDetails1.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}"  hAlign="left" />
    </column>
 </columns>
 <footer        styleClass="footer" show="true"/>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows" />

  <paging size="${pagesize}" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>

</ui:dataGrid>
</td></tr>
<tr>
<td align="right" width="10%" class="header">&nbsp
  
    
    
  <% if(pageNumber>0){%>
  <input type="button" onclick="previous()" value="previous" class="datagrid"/>
  <%}%>
    
    <% if(opacList.size()<pagesize){

    }else{%>
    <input type="button" onclick="next()" value="next" class="datagrid"/>
    <%}%>

</td>
</tr>
    </table></td></tr>
</table>
    <%}%>
  

  <%}%>
    </body>
</html>


