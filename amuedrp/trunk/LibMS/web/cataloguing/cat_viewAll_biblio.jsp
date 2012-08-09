<!-- VIEW ALL CATALOG OLD TITLE ENTRY RECORD GRID-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="java.util.*,com.myapp.struts.opac.*,com.myapp.struts.hbm.*"%>
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

    <%!
        Locale locale=null;
        String locale1="en";
        String rtl="ltr";
        String align="left";
        OpacDoc opacDoc;
        List opacList,result;
        int fromIndex, toIndex;
        int from,i=0;
%>
<script type="text/javascript">
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
     var loc="<%=request.getContextPath()%>/cataloguing/viewAllBiblio.do?page="+<%=nextPage%>;
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
        previousPage = (pageNumber -1) + "";
    else
        previousPage = 0 + "";
    %>
    var loc="<%=request.getContextPath()%>/cataloguing/viewAllBiblio.do?page="+<%=previousPage%>+"&flag=true";;
    location.href= loc;
}
</script>

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
    }
    catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";page=true;}
    else{ rtl="RTL";align="right";page=false;}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
    String biblio_id=resource.getString("cataloguing.catviewownbibliogrid.biblioid");
    pageContext.setAttribute("biblio_id", biblio_id);
    String document_type=resource.getString("cataloguing.catoldtitle.documenttype");
    pageContext.setAttribute("document_type", document_type);
    String title1=resource.getString("cataloguing.catoldtitleentry1.title");
    pageContext.setAttribute("title1",title1);
    String main_entry=resource.getString("cataloguing.catoldtitleentry1.mainentry");
    pageContext.setAttribute("main_entry",main_entry);
    String action=resource.getString("cataloguing.catviewownbibliogrid.action");
    pageContext.setAttribute("action",action);
    String view=resource.getString("cataloguing.catoldtitle.view");
    pageContext.setAttribute("view",view);
    String path= request.getContextPath();
    pageContext.setAttribute("path", path);
int pagesize=100;
  
    int tcount =0;
  

     opacList = new ArrayList ();
        result = new ArrayList ();
        result = (ArrayList)session.getAttribute("opacList");
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
            obj1.setEntry_lang(obj.getEntryLanguage());
            j++;
            i++;
        opacList.add(obj1);
        }
    tcount = opacList.size();
     if ((toIndex = fromIndex+100) >= opacList.size())
        toIndex = opacList.size();
        request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
        pageContext.setAttribute("tCount", tcount);
        pageContext.setAttribute("pagesize", pagesize);
        pageContext.setAttribute("project",request.getContextPath());
    pageContext.setAttribute("tCount", tcount);
%>

<html>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
</head>
<body>
<table  width="100%"  dir="<%=rtl %>">
<%
if(tcount==0)
{
%>
<p class="err"><%=resource.getString("global.norecordfound")%></p>
<%}
else
{%>
<tr><td colspan="2" align="center">
        <ui:dataGrid items="${opacList}"   var="doc" name="datagrid1" cellPadding="0"  cellSpacing="0" styleClass="datagrid">
  <columns>

<column width="5px">
      <header value="Sno" hAlign="left" styleClass="admingridheader"/>
      <item  styleClass="item"  value="${doc.rowno}"   hAlign="left"/>

    </column>
    <column width="20%">
      <header value="Title" hAlign="left" styleClass="admingridheader"/>
      <item  styleClass="item"  value="${doc.title}"   hAlign="left"/>

    </column>
      <column width="20%">
      <header value="MainEntry" hAlign="left" styleClass="admingridheader"/>
      <item  styleClass="item"  value="${doc.main_entry}"   hAlign="left"/>

    </column>
      <column width="20%">
      <header value="CallNo" hAlign="left" styleClass="admingridheader"/>
      <item  styleClass="item"  value="${doc.callno}"   hAlign="left"/>

    </column>
 <column width="20%">
      <header value="Publisher Name" hAlign="left" styleClass="admingridheader"/>
      <item  styleClass="item"  value="${doc.publisher}"   hAlign="left"/>

    </column>
       <column width="20%">
      <header value="Publishing Place" hAlign="left" styleClass="admingridheader"/>
      <item  styleClass="item"  value="${doc.pubplace}"   hAlign="left"/>

    </column>
      <column width="20%">
      <header value="EntryLang" hAlign="left" styleClass="admingridheader"/>
      <item  styleClass="item"  value="${doc.entry_lang}"   hAlign="left"/>

    </column>
          <column width="10%">
      <header value="${action}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${view}"  hAlign="left" hyperLink="${path}/cataloguing/titleShow.do?id=${doc.biblioid}"
	      styleClass="item"/>
    </column>
       <column width="10%">
      <header value="${action}" hAlign="left" styleClass="admingridheader"/>
      <item   value="Upload Book Image"  hAlign="left" hyperLink="${path}/cataloguing/changelogo.jsp?id=${doc.biblioid}"
	      styleClass="item"/>
    </column>
<column width="10%">
      <header value="${action}" hAlign="left" styleClass="admingridheader"/>
      <item   value="Upload Digital Resource"  hAlign="left" hyperLink="${path}/cataloguing/adddigitalcontent.jsp?id=${doc.biblioid}"
	      styleClass="item"/>
    </column>

  </columns>
 <footer        styleClass="footer" show="true"/>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows" />

  <paging size="${pagesize}" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>

</ui:dataGrid>
</td></tr>
<tr><td>
        <%
String msg=(String)request.getAttribute("msg")        ;
if(msg!=null) out.println(msg);
%>
          <% if(pageNumber>0){
           %>
  <input type="button" onclick="previous()" value="previous" class="datagrid"/>
  <%}%>
           <% if(opacList.size()<pagesize){

    }else{%>
    <input type="button" onclick="next()" value="next" class="datagrid"/>
    <%}%>
<%}%>

</table>
</body>
</html>
