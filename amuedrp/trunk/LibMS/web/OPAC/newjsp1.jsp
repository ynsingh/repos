<%@ page import="com.myapp.struts.hbm.*,java.util.*,com.myapp.struts.systemsetupDAO.DocumentCategoryDAO,com.myapp.struts.opacDAO.*;" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
    <%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
    <%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<script>
        function getprint()
{


window.print();
}


</script>

<script language="javaScript" src="fulldetail.js"></script>
</head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<body onload="parent.setIframeHeight();">
<%! String title,author,doctype,publ_pl,pub_name,pub_yr,pages,index,callno,phy_width,loc,pubyr,copy,vol,ed,publ,place,isbn,accno,subtitle,subject,id,lib_id,status,location,booktype,lccno;
int no_of_copy=0;
List<BibliographicDetailsLang> dd=new ArrayList<BibliographicDetailsLang>();
String image;
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
 OpacSearchDAO opacdao=new OpacSearchDAO();
 List ob=(List<String>)opacdao.searchVol(list.get(0).getId().getLibraryId(),list.get(0).getId().getSublibraryId(),list.get(0).getBiblioId());
 int i=0;
 String totvol="";
 System.out.println(ob.size());
 if(ob!=null && ob.size()>0){
 while(i<ob.size())
     {
     if(ob.get(i).toString()!=null && ob.get(i).toString().isEmpty()==false)
     totvol+=ob.get(i).toString()+",";
     i++;
 }


 }
 if(totvol!=null && totvol!="")
 vol=totvol;
 else
     vol="No Given";
}
dd = (List<BibliographicDetailsLang>)session.getAttribute("MLIdocumentDetail");
        if(dd!=null){

           image=dd.get(0).getImage();
            title=dd.get(0).getTitle();
            subtitle=dd.get(0).getSubtitle();
            author=dd.get(0).getMainEntry();
            publ_pl=dd.get(0).getPublicationPlace();
            pub_name=dd.get(0).getPublisherName();
            pub_yr=String.valueOf(dd.get(0).getPublishingYear());
            lccno=dd.get(0).getLccNo();

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
    String locale1="en",head;
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
   <%
head=(String)   session.getAttribute("head");
if(head!=null){
%>
<jsp:include page="opacheader.jsp"/>
<%}%>
    <TABLE align="center" dir="<%=rtl%>"  style="text-align: justify;border-top: dashed 1px cyan;"  border='0' class="datagrid" cellspacing='0' cellpadding='0' valign="top" width="100%">
        <tr dir="<%=rtl%>"><td valign="top" align="center"  height="200px" width="200px">
                <br><br>Book Cover Image<br>
                 <img src="<%=request.getContextPath()%>/admin/logo1.jsp?x=<%=image %>" style="border: dashed 1px cyan;margin-left: 20px;" height="180px" width="180px" style="margin:5px 5px 5px 5px;" >
<br><br><u>View Book Index Content</u><br>
            </td>
            <td valign="top" style="margin-left: 10px; font-family: arial; font-size: 13pt;
	    font-weight: bold;color: red;height:20px;font-style: italic;border: dashed 1px cyan;">
                <table width="100%">
                    <tr><td>&nbsp;&nbsp;<%=title%></td><td align="right"><a  href="#"><font color="blue">Book Rating</font></a>&nbsp;|&nbsp;<a  href="#"><font color="blue">Reader Critics</font></a>&nbsp;|&nbsp;<a  href="<%= request.getContextPath()%>/OPAC/allitemmli.jsp"><font color="blue">View Detail</font></a>
        <button class="buttonhome"  onmousedown="fontResize('-5')">Font Size -&nbsp;-</button><button  class="buttonhome" onmousedown="fontResize('+5')">Font Size ++</button></td>
                </table>




         <TABLE align="center" dir="<%=rtl%>"  style="text-align: justify;margin-left: 10px;line-height: 20px"  border='0' class="datagrid" cellspacing='0' cellpadding='0' valign="top" width="99%">
        <TR dir="<%=rtl%>">
            <td colspan="3" style=" color: blue;font-weight: bold;font-size: 12px;text-align:left;height:20px;
 border-top:dashed 1px cyan;border-bottom: dashed 1px cyan;"><table width="100%">
                    <tr><td><font color="blue">Full Description</font></td><td align="right"><img src="<%=request.getContextPath()%>/images/print.jpeg" onclick="getprint();" height="20px" width="25px"/>&nbsp;|&nbsp;Export to <select class="btnapp"><option>XML</option></select>
        </td>
                </table></td>

    </TR>
    <TR dir="<%=rtl%>" >
        <td   valign='top'  dir="<%=rtl%>"   ><b>Type of material:</b></td>

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
        <TD NOWRAP valign='top' dir="<%=rtl%>"  width=10%><b>Total No's of Volumns</b></TD>
        <td>:</td>
        <TD><%=vol%></TD>
    </TR>
<TR dir="<%=rtl%>">
        <TD NOWRAP valign='top' dir="<%=rtl%>"  width=10%><b>Request In</b></TD>
        <td>:</td>
        <TD><%=loc %></TD>
    </TR>


</TABLE>
     <%--    <TABLE align="center" dir="<%=rtl%>"  style="text-align: justify;margin-left: 10px;line-height: 20px;"  border='0' class="datagrid" cellspacing='0' cellpadding='0' valign="top" width="99%">
        <TR dir="<%=rtl%>">
            <td colspan="3" style=" color: blue;font-weight: bold;font-size: 13px;text-align:left;height:20px;
 border-top:dashed 1px cyan;border-bottom: dashed 1px cyan;border-right: dashed 1px cyan;"><table width="100%">
                    <tr><td><font color="blue">Marc Details</font></td><td align="right"><img src="<%=request.getContextPath()%>/images/print.jpeg" height="20px" width="25px"/>&nbsp;|&nbsp;Export to <select class="btnapp"><option>XML</option></select>
        </td>
                </table></td>

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
        <TD NOWRAP valign='top' dir="<%=rtl%>"  width=10%><b>Total No's of Volumns</b></TD>
        <td>:</td>
        <TD><%=vol%></TD>
    </TR>
<TR dir="<%=rtl%>">
        <TD NOWRAP valign='top' dir="<%=rtl%>"  width=10%><b>Request In</b></TD>
        <td>:</td>
        <TD><%=loc %></TD>
    </TR>


</TABLE>--%>
            </td></tr>
    </TABLE>
    <br><br>
<%       }

if(head!=null){
%>
<jsp:include page="opacfooter.jsp"/>
<%}%>

</body>
</html>