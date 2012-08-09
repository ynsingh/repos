<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.myapp.struts.hbm.*,java.util.*,com.myapp.struts.systemsetupDAO.DocumentCategoryDAO,com.myapp.struts.CirDAO.*,com.myapp.struts.hbm.*" %>
<%@page import="java.util.*"%>
    <%@page import="com.myapp.struts.hbm.*,java.util.*"%>
    <%@page import="java.util.ResourceBundle"%>
    <%@page import="java.util.Locale"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
    <%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
    <%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
    <%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
    <html>
   <%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
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
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <title></title>
       <link href="<%=request.getContextPath()%>/css/newformat.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />
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

     String nextPage = (pageNumber +1) + "";
    if(pageNumber==0)
    {
    nextPage = (pageNumber +2) + "";
    }
%>

     //var loc="<%=request.getContextPath()%>/OPAC/additional.do?page="+<%=nextPage%>;
     //location.href= loc;
    }
<%
    //CHECK IF CLICK ON PREVIOUS BUTTON
    if(request.getParameter("flag")!=null)
    {
       if(size<10)
            row=row-(10+size);
         else
           row=row-20;
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
    
    }

    </script>
    <head>
 <%!
 int row=0;
int fromIndex,toIndex;
int pagesize=10,size;
int pageIndex=0;
int noofpages;
int modvalue;
String index;
List obj1;
%>
<%

 int j=0;
List<DocumentDetails> l12=(List<DocumentDetails>)session.getAttribute("documentDetail");
 //index = request.getParameter("pageIndex");
 //if(index!=null){
   //  pageIndex = Integer.parseInt(index);
  //}
 //else{
   //  pageIndex = 1;
    // }
 //if(pageIndex==0)
   //  pageIndex=1;
if(l12!=null)
        size = l12.size();
 else
        size = 0;
 //for calculating no of pages required
 //modvalue = size%pagesize;
 //if(modvalue>0)
   // noofpages = size/pagesize+1;
 //else
   //  noofpages = size/pagesize;
 //to calculate the starting item and ending item index for the desired page
if(pageNumber==0)
    fromIndex = 0;
else
 fromIndex = (pageNumber-1)*pagesize;
toIndex = fromIndex + pagesize;
System.out.println(fromIndex+"   "+toIndex);
if(toIndex>size)
    toIndex=size;
//fromIndex++;
%>
<%! String title,author,publ_pl,pub_name,pub_yr,pages,callno,phy_width,loc,pubyr,copy,vol,ed,publ,place,isbn,accno,subtitle,subject,id,lib_id,status,location,booktype;
 List<DocumentDetails> dd=new ArrayList<DocumentDetails>();

%>
<%
List<BibliographicDetails> dd1 = (List<BibliographicDetails>)session.getAttribute("MLIdocumentDetail");









CirCheckout obj=null;
String sublib_id1=null;
dd = (List<DocumentDetails>)session.getAttribute("documentDetail");
        if(dd!=null){
            lib_id=(String)dd.get(0).getId().getLibraryId();
            sublib_id1 = (String)dd.get(0).getId().getSublibraryId();
            booktype=dd.get(0).getBookType();
             obj=CirculationDAO.searchCheckOutDetails(lib_id, sublib_id1, String.valueOf(dd.get(0).getId().getDocumentId()));
}
 DocumentCategory docc = (DocumentCategory)DocumentCategoryDAO.searchDocumentCategory(lib_id, sublib_id1, booktype);
            String issuetype ="";
            if(docc!=null)
                issuetype=docc.getIssueCheck();
            %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script>
            function resetsize(x){
                
                parent.setIframeHeight1();
            }
        </script>
    </head>
<%--//<body style="margin:0px 0px 0px 0px;" onload="parent.setIframeHeight1();">--%>
<body onload="parent.setIframeHeight1();" style="margin:0px 0px 0px 0px;font: arial;font-size: 11px;text-align: center;height:100%">
    <i>Total Records : <%=size%> No of Records per page >>10 &nbsp;&nbsp;&nbsp;Record No : <%=fromIndex+1%> to <%=toIndex %></i><% if(toIndex>10){
           %>
    <a dir="" target="f1" href="<%=request.getContextPath()%>/OPAC/allitems.jsp?page=<%=previousPage%>&flag=true">Previous</a>&nbsp;&nbsp;
  <%}%>
           <% if(toIndex==size){

    }else{
     
     %>
    <a dir="" target="f1" href="<%=request.getContextPath()%>/OPAC/allitems.jsp?page=<%=nextPage%>">Next</a>&nbsp;&nbsp;
    <%}%>
        <table id="t"  dir="<%=rtl %>"  class="datagrid" style="border:dashed 1px cyan;">
        <tr  class="opacgrid"><td width="150" height="25px" style="border:dashed 1px cyan;"   align="center">Accession No</td><td width="200" style="border:dashed 1px cyan;" align="center"><%= resource.getString("cataloguing.catoldtitleentry1.title")%></td><td style="border:dashed 1px cyan;" width="200" align="center"><%= resource.getString("cataloguing.catoldtitleentry1.mainentry")%></td><td width="100" style="border:dashed 1px cyan;" align="center">Location</td><td width="100" style="border:dashed 1px cyan;" align="center">Call No</td><td width="100" style="border:dashed 1px cyan;"   align="center">VolumeNo</td><td width="100" style="border:dashed 1px cyan;" align="center">Status</td><td width="200" style="border:dashed 1px cyan;" align="center">Issue Type</td><td width="200" style="border:dashed 1px cyan;" align="center"><%= resource.getString("cataloguing.catviewownbibliogrid.action")%></td></tr>
<% for(int t=fromIndex,c=1;t<dd.size();t++,c++){
if(c>pagesize)
  break;
    %>
<tr class="row"><td align="center" width="10%"><%= dd.get(t).getAccessionNo() %></td>
    <% if(dd1!=null){%>
<td align="center"><%= dd1.get(0).getTitle()!=null ?dd1.get(0).getTitle():""%></td>
<td align="center"><%= dd1.get(0).getMainEntry()!=null?dd1.get(0).getMainEntry():""%></td>
<%}else{%>
<td align="center"><%= dd.get(t).getTitle() %></td>
<td align="center"><%= dd.get(t).getMainEntry() %></td>

<%}%>
<td align="center"><%= dd.get(t).getLocation() %></td>
<td align="center"><%= dd.get(t).getCallNo() %></td>
<td align="center"><%= dd.get(t).getVolumeNo()==null?"No Given":dd.get(t).getVolumeNo() %></td>
<td align="center"><%= dd.get(t).getStatus() %></td>
<td align="center"><%= issuetype %></td>
 <%if(dd.get(t).getStatus().equalsIgnoreCase("available")&& issuetype.equals("Issuable")){%>
 <td align="center"><a href="<%=request.getContextPath()%>/OPAC/checkoutRequest.do?docId=<%= dd.get(t).getId().getDocumentId()%>&libId=<%= dd.get(t).getId().getLibraryId()%>&sublibId=<%= dd.get(t).getId().getSublibraryId() %>">Request for Check Out</a></td><%}
 else if(dd.get(t).getStatus().equalsIgnoreCase("available")==false && issuetype.equals("Issuable")){%>
 <td align="center">Issued Expected Arrival Date <%=obj.getDueDate()%><br>Request for Reservation</td>
 <%}%>

</tr>
 <%}%>
       
       
       
</table>
    </body>
</html>
