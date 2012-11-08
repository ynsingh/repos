     <%@page import="com.myapp.struts.opac.OpacDoc,com.myapp.struts.hbm.*"%>
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
    parent.document.getElementById("form1").action="<%=request.getContextPath()%>/NewArrival.do?page=<%=nextPage%>";
    parent.document.getElementById("form1").method="post";
    parent.document.getElementById("form1").target="f1";
    parent.document.getElementById("form1").submit();

    }
    <%
    //CHECK IF CLICK ON PREVIOUS BUTTON
    if(request.getParameter("flag")!=null)
    {
     i=i-100;
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

    parent.document.getElementById("form1").action="<%=request.getContextPath()%>/NewArrival.do?page=<%=previousPage%>"+"&flag=true";
    parent.document.getElementById("form1").method="post";
    parent.document.getElementById("form1").target="f1";
    parent.document.getElementById("form1").submit();
    }

    </script>
   


<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
    int i=0,from=0;
%>
<%
String lib_id=(String)session.getAttribute("library_id");
String sublib_id = (String)session.getAttribute("memsublib");
        if(sublib_id==null)sublib_id= (String)session.getAttribute("sublibrary_id");
try{
locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
<%
  String Title=resource.getString("opac.simplesearch.title");
  pageContext.setAttribute("Title", Title);
  String MainEntry=resource.getString("opac.simplesearch.mainentry");
  pageContext.setAttribute("MainEntry", MainEntry);
  String LibraryID=resource.getString("opac.browse.table.Libraryid");
  pageContext.setAttribute("LibraryID",LibraryID);
String pub_name=resource.getString("cataloguing.catoldtitleentry1.publishername");
  pageContext.setAttribute("pub_name", pub_name);
%>
<%!
   ArrayList result;
   ArrayList<OpacDoc> opacList;
   int fromIndex, toIndex,pagesize=100;
%>

<%
   String msg=(String)request.getAttribute("msg");
   int tcount =0;
   int currentrec=0;

   if(session.getAttribute("newarrival")!=null){

       System.out.println("OKKKKKKKKKKKKKKKKKKKKKKK");
        opacList = new ArrayList ();
        result = new ArrayList ();
        result = (ArrayList)session.getAttribute("newarrival");
        from=i;
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
            obj1.setDateacq(obj.getDateAcquired());
                 obj1.setImage(obj.getImage());
            obj1.setDigitaldata(obj.getDigitalData());
            obj1.setComment(obj.getDigitalComment());

            j++;
            i++;
        opacList.add(obj1);
        }
        tcount =j;
        if ((toIndex = fromIndex+100) >= opacList.size())
        toIndex = opacList.size();
        request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
        pageContext.setAttribute("tCount", tcount);
        pageContext.setAttribute("pagesize", pagesize);
        pageContext.setAttribute("project",request.getContextPath());
%>
</head>
<body onload="parent.setIframeHeight();">
 

<%
if(tcount==0)
{
%>
<i>    New Arrials Results>><span class="err"> <%=tcount%> Record Found</span></i>
<%}
else
{%>
<table height="300px" width="100%" class="datagrid"><tr><td valign="top" align="left">
           <i>   New Arrivals Results>> <%=tcount%> Record Found&nbsp;&nbsp;&nbsp;  Total Number of Record >><%=opacList.size()%> &nbsp;&nbsp;&nbsp;Current Page Size >> 100 &nbsp;&nbsp;&nbsp;Record No : <%=from+1%> to <%=i%></i>
          <% if(pageNumber>0){
           %>
  <input type="button" onclick="previous()" value="previous" class="btnapp"/>
  <%}%>
           <% if(opacList.size()<pagesize){

    }else{%>
    <input type="button" onclick="next()" value="next" class="btnapp"/>
    <%}%>
  <table class="datagrid" width="100%" style="border:dashed 1px cyan;">
            <tr class="opacgrid"><td width="5px"  >
                         <i>Sno</i></td>
                     <td width="10%">
                         <i>Book Cover Page</i></td>.  <td width="10%">
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
                         <i>Date Acquired</i></td>
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
            <td width="5px"   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>"><%=opacList.get(i).getRowno() %></a></td>
            <td width="10%"   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>"><img src="<%=request.getContextPath()%>/admin/logo1.jsp?x=<%=opacList.get(i).getImage() %>" height="80px" width="80px"></a>

            </td>
            <td width="10%"   style="border-top:dashed 1px cyan;">
                <% if(opacList.get(i).getDigitaldata()!=null){%>
                <a href="<%=request.getContextPath()%>/admin/logo1.jsp?x=<%=opacList.get(i).getDigitaldata() %>" target="_blank"><img src="<%=request.getContextPath()%>/images/econtent.GIF" height="30px" width="100px"/> <br>Comment :<%=opacList.get(i).getComment() %></a>
            <%}else{%>
            Not-available
                <%}%>
            </td>
            <td align="left" width="10%"   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>"><%=opacList.get(i).getTitle() %></a>
            </td>
            <td width="20%"   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>"><%=opacList.get(i).getMain_entry() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>"><%=opacList.get(i).getCallno() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>"><%=opacList.get(i).getPublisher() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>"><%=opacList.get(i).getDateacq() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>"><%=opacList.get(i).getLibrary_id() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>"><%=opacList.get(i).getSublibrary_id() %></a>
            </td>
        </tr>
        <%}%>
    </table>
<%--<ui:dataGrid items="${opacList}"   var="doc" name="datagrid1" cellPadding="0"  cellSpacing="0" styleClass="datagrid">
  <columns>

<column width="5px">
      <header value="Sno" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.rowno}"   hAlign="left"/>

    </column>
    <column width="20%">
      <header value="${Title}" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.title}"   hAlign="left"/>

    </column>
      <column width="20%">
      <header value="MainEntry" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.main_entry}"   hAlign="left"/>

    </column>
      <column width="20%">
      <header value="CallNo" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.callno}"  hAlign="left"/>

    </column>
 <column width="20%">
      <header value="Publisher Name" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.publisher}"   hAlign="left"/>

    </column>
      <column width="20%">
      <header value="Date Acquired" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.dateacq}"   hAlign="left"/>

    </column>
       <column width="20%">
      <header value="Publishing Place" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.pubplace}"   hAlign="left"/>

    </column>
       <column width="20%">
      <header value="library_id" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.library_id}"   hAlign="left"/>

    </column>
       <column width="20%">
      <header value="sublibrary_id" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.sublibrary_id}"   hAlign="left"/>

    </column>

  </columns>
 <footer        styleClass="footer" show="true"/>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows" />

  <paging size="${pagesize}" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>

</ui:dataGrid>--%>
</td></tr>
<tr>
<td align="right" width="10%" class="header1">&nbsp
    <% if(pageNumber>0){
      %>
  <input type="button" onclick="previous()" value="previous" class="btnapp"/>
  <%}%>
    <% if(opacList.size()<pagesize){

    }else{%>
    <input type="button" onclick="next()" value="next" class="btnapp"/>
    <%}%>

</td>
</tr>
    </table>
<%}}%>

    </body>


</html>

