    <%@page import="com.myapp.struts.hbm.DocumentDetails,com.myapp.struts.opac.OpacDoc,com.myapp.struts.opac.MixAccessionRecord"%>
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


</script>
</head>
<body onload="parent.setIframeHeight();">
<%!
  int i=1,from=1;
   Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
   
   ArrayList<DocumentDetails> opacList;
    ArrayList<MixAccessionRecord> opacList1;
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

   opacList = new ArrayList<DocumentDetails> ();
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
<table align="left" width="100%" class="datagrid" style="border:dashed 1px cyan;">
   <tr ><td    height="18px" align="left" colspan="2">
		<%--<%=resource.getString("opac.browse.browsesearchresult")%>--%>
                <i>AccessionNo Search Result>><%=tcount%> records Found&nbsp;&nbsp;&nbsp;  No of Records per page >> 100 </i>
        </td></tr>
  
     <tr >
         <td  align="center" valign="top" height="300px">
        <!--     <input type="text" id="pagesize" onblur="showcount()"/>-->
<%
if(tcount==0)
{
%>
<%}
else
{ %> &nbsp;&nbsp;&nbsp;Record No : <%=from%> to <%=i%>
<table  width="100%"><tr><td valign="top">

<table class="datagrid" width="100%" style="border:dashed 1px cyan;">
            <tr class="opacgrid"><td width="5px"  >
                         <i>Sno</i></td>
                     <td width="10%">
                         <i>Book Cover Page</i></td>
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
            <td width="5px"   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioId()%>&library_id=<%=opacList.get(i).getId().getLibraryId() %>&sublibrary_id=<%=opacList.get(i).getId().getSublibraryId() %>"><%=i+1 %></a></td>
            <td width="10%"   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioId()%>&library_id=<%=opacList.get(i).getId().getLibraryId() %>&sublibrary_id=<%=opacList.get(i).getId().getSublibraryId() %>"><img src="<%=request.getContextPath()%>/images/no-image.jpg" height="80px" width="80px"></a></td>
            <td align="left" width="10%"   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioId()%>&library_id=<%=opacList.get(i).getId().getLibraryId() %>&sublibrary_id=<%=opacList.get(i).getId().getSublibraryId() %>"><%=opacList.get(i).getTitle() %></a>
            </td>
            <td width="20%"   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioId() %>&library_id=<%=opacList.get(i).getId().getLibraryId() %>&sublibrary_id=<%=opacList.get(i).getId().getSublibraryId() %>"><%=opacList.get(i).getMainEntry() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioId()%>&library_id=<%=opacList.get(i).getId().getLibraryId() %>&sublibrary_id=<%=opacList.get(i).getId().getSublibraryId() %>"><%=opacList.get(i).getCallNo() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioId()%>&library_id=<%=opacList.get(i).getId().getLibraryId() %>&sublibrary_id=<%=opacList.get(i).getId().getSublibraryId() %>"><%=opacList.get(i).getPublisherName() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioId()%>&library_id=<%=opacList.get(i).getId().getLibraryId() %>&sublibrary_id=<%=opacList.get(i).getId().getSublibraryId() %>"><%=opacList.get(i).getPublishingYear() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioId()%>&library_id=<%=opacList.get(i).getId().getLibraryId() %>&sublibrary_id=<%=opacList.get(i).getId().getSublibraryId() %>"><%=opacList.get(i).getId().getLibraryId() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioId()%>&library_id=<%=opacList.get(i).getId().getLibraryId() %>&sublibrary_id=<%=opacList.get(i).getId().getSublibraryId() %>"><%=opacList.get(i).getId().getSublibraryId() %></a>
            </td>
        </tr>
        <%}%>
    </table>
<%--<ui:dataGrid items="${opacList}"   var="doc" name="datagrid1" cellPadding="0"  cellSpacing="0" styleClass="datagrid">
  <columns>
    <column width="20%">
      <header value="${Title}" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.title}" hyperLink="${project}/OPAC/AccessionRecord.do?doc_id=${doc.accessionNo}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  hAlign="left"/>
    </column>

    <column width="20%">
      <header value="${MainEntry}" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.mainEntry}" hyperLink="${project}/OPAC/AccessionRecord.do?doc_id=${doc.accessionNo}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  hAlign="left"  />
    </column>

   

 <column width="15%">
      <header value="${AccessionNo}" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.accessionNo}" hyperLink="${project}/OPAC/AccessionRecord.do?doc_id=${doc.accessionNo}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"   hAlign="left" />
    </column>

      <column width="15%">
      <header value="${LibraryID}" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.id.libraryId}" hyperLink="${project}/OPAC/AccessionRecord.do?doc_id=${doc.accessionNo}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  hAlign="left" />
    </column>
        <column width="15%">
      <header value="SubLibrary" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.id.sublibraryId}" hyperLink="${project}/OPAC/AccessionRecord.do?doc_id=${doc.accessionNo}&amp;library_id=${doc.id.libraryId}&amp;sublibrary_id=${doc.id.sublibraryId}"  hAlign="left" />
    </column>
 </columns>
<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="${pagesize}" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>

</ui:dataGrid>--%>
</td></tr>
</table>
  <%}%>


  </td></tr></table>
  <%}else if(session.getAttribute("documentDetail2")!=null){

   ArrayList<OpacDoc>  opacList1 = new ArrayList<OpacDoc> ();
   ArrayList  result = new ArrayList ();
         result = (ArrayList)session.getAttribute("documentDetail2");
        from=i;
            int j=0;
            System.out.println(result.size()+".................");
        while(j<result.size())
        {
            MixAccessionRecord obj=(MixAccessionRecord)result.get(j);
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
            j++;
            i++;
        opacList1.add(obj1);
        }

         tcount=opacList1.size();
        System.out.println(opacList1.size());
        i=0;

        /*if ((toIndex = fromIndex+100) >= opacList1.size())
        toIndex = opacList1.size();
        request.setAttribute ("opacList1", opacList1.subList(fromIndex, toIndex));
        pageContext.setAttribute("tCount", tcount);
        pageContext.setAttribute("pagesize", pagesize);*/

        pageContext.setAttribute("project",request.getContextPath());
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

<table align="left" width="100%" class="datagrid" >
   <tr style="background-color:#e0e8f5;"><td    height="18px" align="left" colspan="2">
		<%--<%=resource.getString("opac.browse.browsesearchresult")%>--%>
                <i>AccessionNo Search Result>><%=tcount%> records Found&nbsp;&nbsp;&nbsp;  No of Records per page >> 100 &nbsp;&nbsp;&nbsp;</i>
        </td></tr>

     <tr >
         <td  align="center" valign="top"  width="100%">
        <!--     <input type="text" id="pagesize" onblur="showcount()"/>-->
<%
if(tcount==0)
{

}else
{%>
<table   width="100%"><tr><td valign="top" width="100%">
            <table  width="100%"><tr><td valign="top">

<table class="datagrid" width="100%" style="border:dashed 1px cyan;">
            <tr class="opacgrid"><td width="5px"  >
                         <i>Sno</i></td>
                     <td width="10%">
                         <i>Book Cover Page</i></td>
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
                <tr>
            <td width="5px"   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList1.get(i).getBiblioid() %>&library_id=<%=opacList1.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList1.get(i).getSublibrary_id() %>"><%=i+1 %></a></td>
            <td width="10%"   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList1.get(i).getBiblioid() %>&library_id=<%=opacList1.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList1.get(i).getSublibrary_id() %>"><img src="<%=request.getContextPath()%>/images/no-image.jpg" height="80px" width="80px"></a></td>
            <td align="left" width="10%"   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList1.get(i).getBiblioid() %>&library_id=<%=opacList1.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList1.get(i).getSublibrary_id() %>"><%=opacList1.get(i).getTitle() %></a>
            </td>
            <td width="20%"   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList1.get(i).getBiblioid() %>&library_id=<%=opacList1.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList1.get(i).getSublibrary_id() %>"><%=opacList1.get(i).getMain_entry() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList1.get(i).getBiblioid() %>&library_id=<%=opacList1.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList1.get(i).getSublibrary_id() %>"><%=opacList1.get(i).getCallno() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList1.get(i).getBiblioid() %>&library_id=<%=opacList1.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList1.get(i).getSublibrary_id() %>"><%=opacList1.get(i).getPublisher() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList1.get(i).getBiblioid() %>&library_id=<%=opacList1.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList1.get(i).getSublibrary_id() %>"><%=opacList1.get(i).getPub_yr() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList1.get(i).getBiblioid() %>&library_id=<%=opacList1.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList1.get(i).getSublibrary_id() %>"><%=opacList1.get(i).getLibrary_id() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList1.get(i).getBiblioid() %>&library_id=<%=opacList1.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList1.get(i).getSublibrary_id() %>"><%=opacList1.get(i).getSublibrary_id() %></a>
            </td>
        </tr>
    </table>
</td></tr>
</table>
</td></tr>
</table>
  <%}%>


  </td></tr></table>
 <%}%>

    </body>

</html>

