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
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/ajax.js"></script>




    <script type="text/javascript">
        function search(doc_id,lib,sublib,rate)
{




     location.href="<%=request.getContextPath()%>/OPAC/ratetitle1.do?doc_id="+doc_id+"&library_id="+lib+"&sublibrary_id="+sublib+"&rate="+rate;
    
     
    return true;
   
    
}
 function searchmli(doc_id,lib,sublib,rate,mli)
{




     location.href="<%=request.getContextPath()%>/OPAC/ratetitle1.do?doc_id="+doc_id+"&library_id="+lib+"&sublibrary_id="+sublib+"&rate="+rate+"&mli="+mli;


    return true;


}



        <%
    String library_id = (String)session.getAttribute("library_id");
    String sublibrary_id = (String)session.getAttribute("memsublib");
     if(sublibrary_id==null)sublibrary_id=   (String)session.getAttribute("sublibrary_id");
if(sublibrary_id==null)sublibrary_id="all";
%>

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
       {
          
         previousPage = (pageNumber -1) + "";
         
         }
    else
     {   previousPage = 0 + "";}
     
    %>
    var loc="<%=request.getContextPath()%>/simple_search.do?page="+<%=previousPage%>+"&flag=true";
    location.href= loc;
    }
    function clear1(x)
    {



        document.getElementById(x+'a').src= "<%=request.getContextPath()%>/images/star.png";
        document.getElementById(x+'b').src= "<%=request.getContextPath()%>/images/star.png";
        document.getElementById(x+'c').src= "<%=request.getContextPath()%>/images/star.png";
        document.getElementById(x+'d').src= "<%=request.getContextPath()%>/images/star.png";
        document.getElementById(x+'e').src= "<%=request.getContextPath()%>/images/star.png";
            

    }

    function rate(id,x)
    {

        
        if(x==1)
        {document.getElementById(id+'a').src= "<%=request.getContextPath()%>/images/white.png";
            document.getElementById(id+'a').title= "You Rate 1";}
    else if(x==2)
        {
            document.getElementById(id+'a').src= "<%=request.getContextPath()%>/images/white.png";
            document.getElementById(id+'b').src= "<%=request.getContextPath()%>/images/white.png";
            document.getElementById(id+'a').title= "You Rate 1";
            document.getElementById(id+'b').title= "You Rate 2";
        }
    else if(x==3)
        {document.getElementById(id+'a').src= "<%=request.getContextPath()%>/images/white.png";
            document.getElementById(id+'b').src= "<%=request.getContextPath()%>/images/white.png";
            document.getElementById(id+'c').src= "<%=request.getContextPath()%>/images/white.png";
            document.getElementById(id+'a').title= "You Rate 1";
            document.getElementById(id+'b').title= "You Rate 2";
            document.getElementById(id+'c').title= "You Rate 3";}
    else if(x==4)
        {document.getElementById(id+'a').src= "<%=request.getContextPath()%>/images/white.png";
            document.getElementById(id+'b').src= "<%=request.getContextPath()%>/images/white.png";
            document.getElementById(id+'c').src= "<%=request.getContextPath()%>/images/white.png";

            document.getElementById(id+'d').src= "<%=request.getContextPath()%>/images/white.png";
            document.getElementById(id+'a').title= "You Rate 1";
            document.getElementById(id+'b').title= "You Rate 2";
            document.getElementById(id+'c').title= "You Rate 3";
            document.getElementById(id+'d').title= "You Rate 4";}
    else if(x==5)
        {document.getElementById(id+'a').src= "<%=request.getContextPath()%>/images/white.png";
            document.getElementById(id+'b').src= "<%=request.getContextPath()%>/images/white.png";
            document.getElementById(id+'c').src= "<%=request.getContextPath()%>/images/white.png";
         
            document.getElementById(id+'d').src= "<%=request.getContextPath()%>/images/white.png";
         
            document.getElementById(id+'e').src= "<%=request.getContextPath()%>/images/white.png";
            document.getElementById(id+'a').title= "You Rate 1";
            document.getElementById(id+'b').title= "You Rate 2";
            document.getElementById(id+'c').title= "You Rate 3";
            document.getElementById(id+'d').title= "You Rate 4";
            document.getElementById(id).title= "You Rate 5";}

    }

    </script>
    </head>
    
    <body style="margin: 0px 0px 0px 0px " onload="parent.setIframeHeight();">

    <%!
    OpacDoc Ob;
     int row=0,from=0;
     int flag=0;
     List<BibliographicDetails> result;
     ArrayList result1;
    ArrayList<OpacDoc>  opacList;
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
        
        result =  (List<BibliographicDetails>)session.getAttribute("simple_search_list");
     
        int j=0;
        from=(Integer)request.getAttribute("from");
        System.out.println(from+"............."+result.size());
        while(j<result.size())
        {
            BibliographicDetails obj=(BibliographicDetails)result.get(j);
            OpacDoc obj1=new OpacDoc();
            obj1.setBiblioid(obj.getId().getBiblioId());
            obj1.setTitle(obj.getTitle());
            obj1.setCallno(obj.getCallNo());
            obj1.setRowno(row+1);
            obj1.setLibrary_id(obj.getId().getLibraryId().toUpperCase());
            if(obj.getId().getLibraryId().toUpperCase().equalsIgnoreCase(obj.getId().getSublibraryId().toUpperCase()))
            obj1.setLocation("CL");
            else
                obj1.setLocation(obj.getId().getSublibraryId().toUpperCase());


                            obj1.setSublibrary_id(obj.getId().getSublibraryId().toUpperCase());
            obj1.setMain_entry(obj.getMainEntry());
            obj1.setPublisher(obj.getPublisherName());
            obj1.setPubplace(obj.getPublicationPlace());
            obj1.setPub_yr(String.valueOf(obj.getPublishingYear()));
              obj1.setImage(obj.getImage());
             obj1.setDigitaldata(obj.getDigitalData());
            obj1.setComment(obj.getDigitalComment());
            obj1.setRate(obj.getRating());

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
 <table align="center" class="datagrid" dir="<%=rtl%>" width="100%" bgcolor="white" height="100%">
 
           
           <tr><td colspan="3">
<table align="center" dir="<%=rtl%>" width="100%"  class="datagrid"  >
  
     <tr  dir="<%=rtl%>">
         <td  align="center" width="100%"  valign="top" dir="<%=rtl%>" height="300px">

<%
if(tcount==0)
{
%>
<i>    <%=resource.getString("opac.simplesearch.smpsearch")%> Results>><span class="err"> <%=row%> Record Found</span></i>
<%}
else
{%>
<table height="300px" width="100%" ><tr><td valign="top" align="left" >
            <table class="txt2" width="100%">
                <tr><td><i>    <%=resource.getString("opac.simplesearch.smpsearch")%> Results>> <%=tcount%> Record Found&nbsp;&nbsp;&nbsp;  No of Records per page >><%=pagesize%> &nbsp;&nbsp;&nbsp;Record No : <%=from+1%> to <%=row%></i></td>
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
                         <i><img src="<%=request.getContextPath()%>/images/ebook2.jpg" height="30px" width="50px"/></i></td>
                     <td width="10%" >
                         <i>Title</i></td>
              <td   >
                  <i>Main ENtry</i></td>
              <td   >
                         <i>Call No</i></td>
              <td   >
                         <i>Publisher Name</i></td>
              <td   >
                         <i>Publishing Year</i></td>
              <td   >
                         <i>Institute</i></td>
              <td   >
                         <i>Institute Location</i></td>
              <td   >
                         <i>My Rating</i></td>


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
            <td width="10%"   style="border-top:dashed 1px cyan;"><img src="<%=request.getContextPath()%>/images/ebook3.jpg" height="30px" width="50px"/><br>
                <% if(opacList.get(i).getDigitaldata()!=null){%>
                <a href="<%=request.getContextPath()%>/admin/logo1.jsp?x=<%=opacList.get(i).getDigitaldata() %>" target="_blank">Click here<br>Comment :<%=opacList.get(i).getComment() %></a>
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
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>"><%=opacList.get(i).getPub_yr() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>"><%=opacList.get(i).getLibrary_id() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>"><%=opacList.get(i).getLocation() %></a>
            </td>
            <td   style="border-top:dashed 1px cyan;"><img onclick="search('<%=opacList.get(i).getBiblioid()%>','<%=opacList.get(i).getLibrary_id() %>','<%=opacList.get(i).getSublibrary_id() %>','1')" width="20px" height="20px" id="<%=opacList.get(i).getRowno() %>a" onmouseout="clear1('<%=opacList.get(i).getRowno() %>')" onmouseover="rate('<%=opacList.get(i).getRowno() %>',1)" src="<%=request.getContextPath()%>/images/star.png"/><img id="<%=opacList.get(i).getRowno() %>b" onmouseover="rate('<%=opacList.get(i).getRowno() %>',2)" width="20px" height="20px" src="<%=request.getContextPath()%>/images/star.png" onclick="search('<%=opacList.get(i).getBiblioid()%>','<%=opacList.get(i).getLibrary_id() %>','<%=opacList.get(i).getSublibrary_id() %>','2')" onmouseout="clear1('<%=opacList.get(i).getRowno() %>')"/>
                <img onclick="search('<%=opacList.get(i).getBiblioid()%>','<%=opacList.get(i).getLibrary_id() %>','<%=opacList.get(i).getSublibrary_id() %>','3')" onmouseout="clear1('<%=opacList.get(i).getRowno() %>')"  onmouseover="rate('<%=opacList.get(i).getRowno() %>',3)" id="<%=opacList.get(i).getRowno() %>c" width="20px" height="20px" src="<%=request.getContextPath()%>/images/star.png"/>
                <img onclick="search('<%=opacList.get(i).getBiblioid()%>','<%=opacList.get(i).getLibrary_id() %>','<%=opacList.get(i).getSublibrary_id() %>','4')" onmouseout="clear1('<%=opacList.get(i).getRowno() %>')" onmouseover="rate('<%=opacList.get(i).getRowno() %>',4)"  id="<%=opacList.get(i).getRowno() %>d" width="20px" height="20px" src="<%=request.getContextPath()%>/images/star.png"/>
                <img onclick="search('<%=opacList.get(i).getBiblioid()%>','<%=opacList.get(i).getLibrary_id() %>','<%=opacList.get(i).getSublibrary_id() %>','5')" onmouseout="clear1('<%=opacList.get(i).getRowno() %>')" onmouseover="rate('<%=opacList.get(i).getRowno() %>',5)" id="<%=opacList.get(i).getRowno() %>e" width="20px" height="20px" src="<%=request.getContextPath()%>/images/star.png"/>
                <br>Viewer Rating:<i> <%=opacList.get(i).getRate()!=null?opacList.get(i).getRate():"Not Given"%></i>
            </td>
        </tr>
        <%}%>
    </table>
<%--<ui:dataGrid items="${opacList}"   var="doc" name="datagrid1" cellPadding="0"  cellSpacing="0" styleClass="datagrid">
  <columns>
     
<column width="5px">
      <header value="Sno" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.rowno}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h='t'"  hAlign="left"/>

    </column>
    <column width="20%">
      <header value="${Title}" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.title}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>
    
    </column>
      <column width="20%">
      <header value="MainEntry" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.main_entry}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>

    </column>
      <column width="10%">
      <header value="CallNo" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.callno}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>

    </column>
 <column width="20%">
      <header value="Publisher Name" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.publisher}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>

    </column>
       <column width="10%">
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
<tr>
<td align="right" width="10%" style="border-top:dashed 1px cyan;border-bottom:dashed 1px cyan;">&nbsp
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
    </table></td></tr>
</table>
               </td></tr>
 </table>
  <%}%>
 
  <%}
        // IF SEARCH IS MLI
        if(session.getAttribute("simple_search_list1")!=null)
        {
        opacList = new ArrayList();
        result1 = new ArrayList();
        result1 = (ArrayList<SearchPOJO>)session.getAttribute("simple_search_list1");
            int j=0;
            from=(Integer)request.getAttribute("from");
        while(j<result1.size())
        {
            BibliographicDetailsLang obj=(BibliographicDetailsLang)result1.get(j);
            OpacDoc obj1=new OpacDoc();
            obj1.setBiblioid(obj.getId().getBiblioId());
            obj1.setTitle(obj.getTitle());
            obj1.setCallno(obj.getCallNo());
            obj1.setRowno(row+1);
            obj1.setLibrary_id(obj.getId().getLibraryId().toUpperCase());
            if(obj.getId().getLibraryId().toUpperCase().equalsIgnoreCase(obj.getId().getSublibraryId().toUpperCase()))
            obj1.setLocation("CL");
            else
                obj1.setLocation(obj.getId().getSublibraryId().toUpperCase());

            obj1.setSublibrary_id(obj.getId().getSublibraryId().toUpperCase());
            obj1.setMain_entry(obj.getMainEntry());
            obj1.setPublisher(obj.getPublisherName());
            obj1.setPubplace(obj.getPublicationPlace());
              obj1.setPub_yr(obj.getPublishingYear().toString());
               obj1.setImage(obj.getImage());
            obj1.setDigitaldata(obj.getDigitalData());
            obj1.setComment(obj.getDigitalComment());
            obj1.setRate(obj.getRating());

            j++;
            row++;
        opacList.add(obj1);
        }
            row=(Integer)request.getAttribute("to");
System.out.println(opacList.size()+".....................");
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
<table height="300px" width="100%" ><tr><td valign="top" align="left" >
            <table class="txt2" width="100%">
                <tr><td><i>    <%=resource.getString("opac.simplesearch.smpsearch")%> Results>> <%=tcount%> Record Found&nbsp;&nbsp;&nbsp;  No of Records per page >><%=opacList.size()%> &nbsp;&nbsp;&nbsp;Record No : <%=from+1%> to <%=row%></i></td>
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
                         <i><img src="<%=request.getContextPath()%>/images/ebook2.jpg" height="30px" width="50px"/></i></td>
                     <td width="10%" >
                         <i>Title</i></td>
              <td   >
                  <i>Main ENtry</i></td>
              <td   >
                         <i>Call No</i></td>
              <td   >
                         <i>Publisher Name</i></td>
              <td   >
                         <i>Publishing Year</i></td>
              <td   >
                         <i>Library</i></td>
              <td   >
                         <i>SubLibrary</i></td>  <td   >
                         <i>My Rating</i></td>


                 </tr>
        <% for(int i=0;i<opacList.size();i++){
   if((i%2)==0){
    %>

        <tr>
            <%}else{%>
        <tr class="alternaterows">
            <%}%>
            <td width="5px"   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails1.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>&h=t"><%=opacList.get(i).getRowno() %></a></td>
            <td width="10%"   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails1.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>"><img src="<%=request.getContextPath()%>/admin/logo1.jsp?x=<%=opacList.get(i).getImage() %>" height="80px" width="80px"></a>

            </td>
            <td width="10%"   style="border-top:dashed 1px cyan;"><img src="<%=request.getContextPath()%>/images/ebook3.jpg" height="30px" width="50px"/><br>
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
            <td   style="border-top:dashed 1px cyan;"><a href="<%=request.getContextPath()%>/OPAC/viewDetails1.do?doc_id=<%=opacList.get(i).getBiblioid()%>&library_id=<%=opacList.get(i).getLibrary_id() %>&sublibrary_id=<%=opacList.get(i).getSublibrary_id() %>&h=t"><%=opacList.get(i).getLocation() %></a>
            </td>
             <td   style="border-top:dashed 1px cyan;"><img onclick="searchmli('<%=opacList.get(i).getBiblioid()%>','<%=opacList.get(i).getLibrary_id() %>','<%=opacList.get(i).getSublibrary_id() %>','1','t')" width="20px" height="20px" id="<%=opacList.get(i).getRowno() %>a" onmouseout="clear1('<%=opacList.get(i).getRowno() %>')" onmouseover="rate('<%=opacList.get(i).getRowno() %>',1)" src="<%=request.getContextPath()%>/images/star.png"/><img id="<%=opacList.get(i).getRowno() %>b" onmouseover="rate('<%=opacList.get(i).getRowno() %>',2)" width="20px" height="20px" src="<%=request.getContextPath()%>/images/star.png" onclick="searchmli('<%=opacList.get(i).getBiblioid()%>','<%=opacList.get(i).getLibrary_id() %>','<%=opacList.get(i).getSublibrary_id() %>','2','t')" onmouseout="clear1('<%=opacList.get(i).getRowno() %>')"/>
                <img onclick="searchmli('<%=opacList.get(i).getBiblioid()%>','<%=opacList.get(i).getLibrary_id() %>','<%=opacList.get(i).getSublibrary_id() %>','3','t')" onmouseout="clear1('<%=opacList.get(i).getRowno() %>')"  onmouseover="rate('<%=opacList.get(i).getRowno() %>',3)" id="<%=opacList.get(i).getRowno() %>c" width="20px" height="20px" src="<%=request.getContextPath()%>/images/star.png"/>
                <img onclick="searchmli('<%=opacList.get(i).getBiblioid()%>','<%=opacList.get(i).getLibrary_id() %>','<%=opacList.get(i).getSublibrary_id() %>','4','t')" onmouseout="clear1('<%=opacList.get(i).getRowno() %>')" onmouseover="rate('<%=opacList.get(i).getRowno() %>',4)"  id="<%=opacList.get(i).getRowno() %>d" width="20px" height="20px" src="<%=request.getContextPath()%>/images/star.png"/>
                <img onclick="searchmli('<%=opacList.get(i).getBiblioid()%>','<%=opacList.get(i).getLibrary_id() %>','<%=opacList.get(i).getSublibrary_id() %>','5','t')" onmouseout="clear1('<%=opacList.get(i).getRowno() %>')" onmouseover="rate('<%=opacList.get(i).getRowno() %>',5)" id="<%=opacList.get(i).getRowno() %>e" width="20px" height="20px" src="<%=request.getContextPath()%>/images/star.png"/>
                <br>Viewer Rating:<i> <%=opacList.get(i).getRate()!=null?opacList.get(i).getRate():"Not Given"%></i>
            </td>
        </tr>
        <%}%>
    </table>
<%--<ui:dataGrid items="${opacList}"   var="doc" name="datagrid1" cellPadding="0"  cellSpacing="0" styleClass="datagrid">
  <columns>

<column width="5px">
      <header value="Sno" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.rowno}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h='t'"  hAlign="left"/>

    </column>
    <column width="20%">
      <header value="${Title}" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.title}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>

    </column>
      <column width="20%">
      <header value="MainEntry" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.main_entry}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>

    </column>
      <column width="10%">
      <header value="CallNo" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.callno}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>

    </column>
 <column width="20%">
      <header value="Publisher Name" hAlign="left" styleClass="header1"/>
      <item  styleClass="item"  value="${doc.publisher}" hyperLink="${project}/OPAC/viewDetails.do?doc_id=${doc.biblioid}&amp;library_id=${doc.library_id}&amp;sublibrary_id=${doc.sublibrary_id}&amp;h=t"  hAlign="left"/>

    </column>
       <column width="10%">
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
<tr>
<td align="right" width="10%" class="header1">&nbsp
  
    
    
  <% if(pageNumber>0){%>
  <input type="button" onclick="previous()" value="previous" class="btnapp"/>
  <%}%>
    
    <% if(opacList.size()<pagesize){

    }else{%>
    <input type="button" onclick="next()" value="next" class="btnapp"/>
    <%}%>

</td>
</tr>
    </table></td></tr>
</table>
    <%}%>
  

  <%}%>
    </body>
</html>


