<%@ page import="com.myapp.struts.hbm.*,java.util.*,com.myapp.struts.systemsetupDAO.DocumentCategoryDAO;" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <style>

    th a:link      { text-decoration: none; color: black }
     th a:visited   { text-decoration: none; color: black }
     .rows          { background-color: white;border: solid 1px blue; }
     .hiliterows    { background-color: pink; color: #000000; font-weight: bold;border: solid 1px blue; }
     .alternaterows { background-color: #efefef; }
     .header        { background-color: #c0003b; color: #FFFFFF;font-weight: bold;text-decoration: none;padding-left: 10px; }

     .datagrid      {  font-family: arial; font-size: 9pt;
	    font-weight: normal;}
     .item{ padding-left: 10px;}

</style>

<script language="javaScript" src="fulldetail.js"></script>
</head>
<body>





<%! String title,author,publ_pl,pub_name,pub_yr,pages,index,callno,phy_width,loc,pubyr,copy,vol,ed,publ,place,isbn,accno,subtitle,subject,id,lib_id,status,location,booktype;
   DocumentDetails dd;
    
%>
<%

dd = (DocumentDetails)request.getAttribute("documentDetail");
        if(dd!=null){
            
           booktype=dd.getBookType();
            title=dd.getTitle();
            subtitle=dd.getSubtitle();
            author=dd.getMainEntry();
            publ_pl=dd.getPublicationPlace();
            pub_name=dd.getPublisherName();
            pub_yr=dd.getPublishingYear();
            pages=dd.getNoOfPages();
            index=dd.getIndexNo();
            callno=dd.getCallNo();
            isbn=dd.getIsbn10();
            ed=dd.getEdition();
            location=dd.getLocation();
            lib_id = dd.getId().getLibraryId();
            phy_width=dd.getPhysicalWidth();
            status = dd.getStatus();
            accno = dd.getAccessionNo();
            String sublib_id = (String)dd.getId().getSublibraryId();
            if(title==null)title="";
            if(subtitle==null)subtitle="";
            if(author==null)author="";
            if(publ_pl==null)publ_pl="";
            if(pub_name==null)pub_name="";
            if(pub_yr==null)pub_yr="";
            if(pages==null)pages="";
            if(index==null)index="";
            if(callno==null)callno="";
            if(isbn==null)isbn="";
            if(ed==null)ed="";
            if(lib_id==null)lib_id="";
            if(phy_width==null)phy_width="";
            if(status==null)status="";
            if(accno==null)accno="";
            DocumentCategory docc = (DocumentCategory)DocumentCategoryDAO.searchDocumentCategory(lib_id, sublib_id, booktype);
            String issuetype ="";
            if(docc!=null)
                issuetype=docc.getIssueCheck();
 %>             


<button  onmousedown="fontResize('-5')">Font Size -&nbsp;-</button><button  onmousedown="fontResize('+5')">Font Size ++</button>
<br>
<TABLE  border='0' class="datagrid" cellspacing='0' cellpadding='0' valign="top" width="200px">
   
   
    <TR>
        <TD NOWRAP valign='top'  width=10%>Title:</TD>
        <TD valign='top' >&nbsp; </TD>
        <TD  colspan=2><%=title%></TD>
    </TR>

    <TR>
        <TD NOWRAP valign='top'  width="10%">Main Author:</TD>
        <TD valign='top' >&nbsp; </TD>
        <TD  colspan=2 ><%=author%></TD>
    </TR>
      
    <TR>
        <TD NOWRAP valign='top'  width=10%>Publication Place:  </TD>
        <TD valign='top' >&nbsp; </TD>
        <TD  colspan=2><%=publ_pl%></TD>
    </TR>
    <TR>
        <TD NOWRAP valign='top'  width=10%>Publisher Name:  </TD>
        <TD valign='top' >&nbsp; </TD>
        <TD colspan=2><%=pub_name%></TD>
    </TR>
    <TR>
        <TD NOWRAP valign='top'  width=10%>Publishing Year:  </TD>
        <TD valign='top' >&nbsp; </TD>
        <TD  colspan=2><%=pub_yr%></TD>
    </TR>
    <TR>
        <TD NOWRAP valign='top'  width=10%>Pages:</TD>
        <TD valign='top' >&nbsp; </TD>
        <TD  colspan=2><%=index%>,<%=pages%></TD>
    </TR>
    <TR>
        <TD NOWRAP valign='top'  width=10%>Call No: </TD>
        <TD valign='top' >&nbsp; </TD>
        <TD  colspan=2><%=callno%></TD>
    </TR>
    <TR>
        <TD NOWRAP valign='top'  width=10%>Accession No: </TD>
        <TD valign='top' >&nbsp; </TD>
        <TD  colspan=2><%=accno%></TD>
    </TR>
    <TR>
        <TD NOWRAP valign='top'  width=10%>ISBN:</TD>
        <TD valign='top' >&nbsp; </TD>
        <TD  colspan=2><%=isbn%></TD>
    </TR>
    <TR>
        <TD NOWRAP valign='top'  width=10%>Status:</TD>
        <TD valign='top' >&nbsp; </TD>
        <TD  colspan=2><%=status%></TD>
    </TR>
    <TR>
        <TD NOWRAP valign='top'  width=10%>Issue Type:</TD>
        <TD valign='top' >&nbsp; </TD>
        <TD  colspan=2><%=issuetype%></TD>
    </TR>
    <TR><TD NOWRAP valign='top'  width="10%">Location:</TD>
        <TD valign='top' >&nbsp; </TD>
        <TD  colspan=2><%=location%><br></TD>
    </TR>
    <tr><td colspan="2"><a href="#">View Detail</a></td>
        <%if(status.equalsIgnoreCase("available")&& issuetype.equals("Issuable")){%><td colspan="2"><a href="<%=request.getContextPath()%>/OPAC/checkoutRequest.do?docId=<%=dd.getId().getDocumentId()%>&libId=<%=dd.getId().getLibraryId()%>&sublibId=<%=dd.getId().getSublibraryId()%>">Request for Check Out</a></td><%}%></tr>
</TABLE><br><br>
<%       }
%>


</body>
</html>