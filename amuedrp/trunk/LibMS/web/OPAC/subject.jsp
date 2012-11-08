<%@ page import="com.myapp.struts.hbm.*,java.util.*,com.myapp.struts.systemsetupDAO.DocumentCategoryDAO;" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
    <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
    <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<script language="javaScript" src="fulldetail.js"></script>
</head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<body> 
<%! String title,author,doctype,publ_pl,pub_name,pub_yr,pages,index,callno,phy_width,loc,pubyr,copy,vol,ed,publ,place,isbn,accno,subtitle,subject,id,lib_id,status,location,booktype,lccno;
int no_of_copy=0;
List<BibliographicDetails> dd=new ArrayList<BibliographicDetails>();
    
%> 
<%
if(session.getAttribute("documentDetail")!=null)  {
    List<DocumentDetails> list=(List<DocumentDetails>)session.getAttribute("documentDetail");
no_of_copy=list.size();
loc=list.get(0).getLocation();
phy_width=list.get(0).getPhysicalWidth();
index=list.get(0).getIndexNo();
pages=list.get(0).getNoOfPages();
 booktype=list.get(0).getBookType();
 doctype=list.get(0).getDocumentType();
}
dd = (List<BibliographicDetails>)session.getAttribute("documentDetail1");
        if(dd!=null){
            
           //booktype=dd.get(0).getBookType();
            title=dd.get(0).getTitle();
            subtitle=dd.get(0).getSubtitle();
            author=dd.get(0).getMainEntry();
            publ_pl=dd.get(0).getPublicationPlace();
            pub_name=dd.get(0).getPublisherName();
            pub_yr=String.valueOf(dd.get(0).getPublishingYear());
            lccno=dd.get(0).getLccNo();
            subject=dd.get(0).getSubject();
            callno=dd.get(0).getCallNo();
            isbn=dd.get(0).getIsbn10();
            ed=dd.get(0).getEdition();
           // location=dd.getLocation();
            lib_id = dd.get(0).getId().getLibraryId();
            
         //   status = dd.getStatus();
            String sublib_id = (String)dd.get(0).getId().getSublibraryId();
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
            DocumentCategoryDAO docdao=new DocumentCategoryDAO();
            DocumentCategory docc = (DocumentCategory)docdao.searchDocumentCategory(lib_id, sublib_id, booktype);

            String issuetype ="";

            if(docc!=null)
             {   issuetype=docc.getIssueCheck();
                
}
System.out.println(issuetype+"  "+booktype+docc+lib_id+sublib_id);
 %>

<%!
    Locale locale;
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>


    <TABLE align="center" dir="<%=rtl%>"  style="text-align: justify;"  border='0' class="datagrid" cellspacing='0' cellpadding='0' valign="top" width="60%">
   <TR dir="<%=rtl%>">
       <td colspan="3" align="center" style="margin:0px 0px 0px 0px" class="header">
           <%--<a href="" style="border: none;text-decoration: none;" onclick="document.getElementById('s1').src='<%=request.getContextPath()%>/images/sub.gif'">  <img id="s1" src="<%=request.getContextPath()%>/images/sub1.gif"/></a>
           <img id="b1" src="<%=request.getContextPath()%>/images/brief1.gif"/>
       <img id="m1" src="<%=request.getContextPath()%>/images/marc1.gif"/>
       <img id="f1" src="<%=request.getContextPath()%>/images/full1.gif"/>--%>
      <a href="../OPAC/newjsp.jsp" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b >Brief Details</b></a>&nbsp;|&nbsp;
       <a href="../OPAC/subject.jsp"  style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b > Subject Details</b></a>&nbsp;|&nbsp;
      <a href="../OPAC/fulldetails.jsp"  style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b> Full Details</b></a>&nbsp;|&nbsp;
     <a href="../OPAC/marc_details.jsp" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b> MARC Details</b></a>

       </td>
    </TR>
<TR dir="<%=rtl%>">
        <td   valign='top' dir="<%=rtl%>"   ><b>LC Code:</b></td>

        <td>:</td><td><%=lccno!=null?lccno:"" %></td>
    </TR>
    <TR dir="<%=rtl%>">
        <td   valign='top' dir="<%=rtl%>"   ><b>Type of material:</b></td>

        <td>:</td><td><%=doctype!=null?doctype:"" %></td>
    </TR>
   
    <TR dir="<%=rtl%>">
        <TD   valign='top' dir="<%=rtl%>"   ><b>Main <%=resource.getString("opac.newjsp.title")%></b></TD>
        <td>:</td>
      
        <td> <%=title%></td>
    </TR>

    <TR dir="<%=rtl%>">
        <TD NOWRAP valign='top' dir="<%=rtl%>"  ><b>Personal name//Main Entry/<%=resource.getString("opac.newjsp.mainauthor")%></b></TD>
        <td>:</td>
      
       <TD><%=author%></TD>
    </TR>
       <TR dir="<%=rtl%>">
        <TD NOWRAP valign='top' dir="<%=rtl%>"  ><b>Subject</b></TD>
        <td>:</td>

       <TD><%=subject%></TD>
    </TR>
  
    <TR dir="<%=rtl%>">
        <TD NOWRAP valign='top' dir="<%=rtl%>"><b>Published/Created : </b></TD>
        <td>:</td><TD>
        <%=pub_name%>, <%=publ_pl%>,<%=pub_yr%></TD>
    </TR>
    
    <TR dir="<%=rtl%>">
        <TD NOWRAP valign='top' dir="<%=rtl%>"><b>Physical Description : </b></TD>
        <td>:</td>

        <TD><%=index %>, <%=pages%>,<%=phy_width %></TD>
    </TR>
   
    
    <TR dir="<%=rtl%>">
        <TD NOWRAP valign='top' dir="<%=rtl%>" ><b><%=resource.getString("opac.newjsp.isbn")%></b></TD>
        <td>:</td>
    
        <TD><%=isbn%></TD>
    </TR>
 <TR dir="<%=rtl%>">
        <TD NOWRAP valign='top' dir="<%=rtl%>"><b><%=resource.getString("opac.newjsp.callno")%> </b></TD>
        <td>:</td>
        <TD><%=callno%></TD>
    </TR>
    <TR>
        <TD NOWRAP valign='top' dir="<%=rtl%>" width=10%><b><%=resource.getString("opac.newjsp.issuetype")%></b></TD>
        <td>:</td>
        <TD>
<%=issuetype%></TD>
    </TR>
 <TR dir="<%=rtl%>">
        <TD NOWRAP valign='top' dir="<%=rtl%>"  width=10%><b>No of Copies</b></TD>
        <td>:</td>
        <TD><%=no_of_copy %></TD>
    </TR>
<TR dir="<%=rtl%>">
        <TD NOWRAP valign='top' dir="<%=rtl%>"  width=10%><b>Request In</b></TD>
        <td>:</td>
        <TD><%=loc %></TD>
    </TR>

<tr><td colspan="3"><a href="<%= request.getContextPath()%>/OPAC/record.jsp"><br/>View Detail</a><br/><br/>
        <%--<button class="buttonhome"  onmousedown="fontResize('-5')">Font Size -&nbsp;-</button><button  class="buttonhome" onmousedown="fontResize('+5')">Font Size ++</button>--%>
    </td></tr>
</TABLE><br><br>
<%       }
%>


</body>
</html>