
 
    <%@page import="com.myapp.struts.opac.ReservationDoc,com.myapp.struts.systemsetupDAO.BookCategoryDAO,com.myapp.struts.utility.DateCalculation,com.myapp.struts.hbm.*,com.myapp.struts.opacDAO.*"%>
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
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
  

</head>

<body>
   

     <%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
  
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";}
    else{ rtl="RTL";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>

  <%
  String Title="Title";
pageContext.setAttribute("Title", Title);

String Status=resource.getString("circulation.cirviewall.status");
pageContext.setAttribute("Status", Status);
String DocumentId=resource.getString("circulation.cir_viewmem_chkoutreport.docid");
pageContext.setAttribute("DocumentId", DocumentId);
String Author=resource.getString("opac.simplesearch.auth");
pageContext.setAttribute("Author",Author);
String CallNo=resource.getString("opac.myaccount.reservationrequest.callno");
pageContext.setAttribute("CallNo",CallNo);
String IssueDate=resource.getString("circulation.cir_view_book.issuedate");
pageContext.setAttribute("IssueDate",IssueDate);
String DueDate=resource.getString("circulation.cir_view_book.duedate");
pageContext.setAttribute("DueDate",DueDate);
String Edition=resource.getString("opac.myaccount.newdemand.edition");
pageContext.setAttribute("Edition",Edition);

%>



<%!
   
   
   ReservationDoc Ob;
   
   int fromIndex=0, toIndex;
%>
 <%
 
 //String name=(String)session.getAttribute("mem_name");
  List<CheckoutDeocumentDetails>  requestList1=(List<CheckoutDeocumentDetails>)session.getAttribute("membercheckoutDetail");
  List<CheckoutDeocumentDetails>  requestList=new ArrayList<CheckoutDeocumentDetails>();

   Iterator is=requestList1.iterator();
  int i=0;

  while(is.hasNext()){
      CheckoutDeocumentDetails doc=requestList1.get(i++);
      if(doc.getCirCheckout().getStatus().equalsIgnoreCase("issued")){
     if(DateCalculation.getDifference( DateCalculation.now(),doc.getCirCheckout().getDueDate())>0){

           String document_category= doc.getDocumentDetails().getBookType();
           //  System.out.println(document_category);
           com.myapp.struts.CirDAO.CirculationDAO cirdao=new com.myapp.struts.CirDAO.CirculationDAO();
           CirMemberAccount cma=cirdao.getAccount(doc.getCirCheckout().getId().getLibraryId(),doc.getCirCheckout().getId().getSublibraryId(), doc.getCirCheckout().getMemid());

            String mem_type=cma.getMemType();
        String submem_type=cma.getSubMemberType();
        
BookCategoryDAO bookdao=new BookCategoryDAO();
      BookCategory bookobj=bookdao.searchBookTypeDetails(doc.getCirCheckout().getId().getLibraryId(), mem_type, submem_type, document_category);
//System.out.println(bookobj.getFine().toString());

long f=(long)(bookobj.getFine()*DateCalculation.getDifference( DateCalculation.now(),doc.getCirCheckout().getDueDate()));
//System.out.println(bookobj.getFine().toString()+f);
         Integer ob=new Integer(String.valueOf(f));

 //System.out.println("Fine"+ob);
doc.setFine(ob);
     }
}else{
         // System.out.println("Fine"+0);
     doc.setFine(0);
}

 requestList.add(doc);
  
  is.next();
  }



   int tcount =requestList.size();
   
 

//System.out.println("Si"+tcount );

%>
       
<%
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+4) >= requestList.size ())
   toIndex = requestList.size();
   request.setAttribute ("requestList", requestList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
%>
<br>
 
<table dir="<%=rtl%>" class="table" width="100%" height="300px">
    <tr><td dir="<%=rtl%>" class="headerStyle" valign="top" align="center" height="25px"><%=resource.getString("circulation.viewformember.memberchkoutdetail")%></td></tr>
  <tr><td dir="<%=rtl%>" valign="top"><br>


<%if(tcount==0)
{%>
<p class="err" style="font-size:12px"><%=resource.getString("circulation.cir_viewall_mem_detail.norecfond")%></p>
<%}
else
{
    System.out.println("here");
    %>

<ui:dataGrid items="${requestList}"  var="doc" name="datagrid1" cellPadding="2" cellSpacing="0" styleClass="datagrid">
    
  <columns>
      <column width="15%">
      <header value="MemberID" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.cirCheckout.memid}"  hAlign="left"    styleClass="item"/>
    </column>
    

    <column width="15%">
      <header value="${Title}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.documentDetails.title}"  hAlign="left"    styleClass="item"/>
    </column>

    <column width="10%">
      <header value="${Author}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.documentDetails.mainEntry}" hAlign="left"   styleClass="item"/>
    </column>

    <column width="10%">
      <header value="${CallNo}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.documentDetails.callNo}"   hAlign="left" styleClass="item"/>
    </column>
    <column width="15%">
      <header value="AccessionNo" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.documentDetails.accessionNo}"   hAlign="left" styleClass="item"/>
    </column>
      <column width="10%">
      <header value="${IssueDate}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.cirCheckout.issueDate}" hAlign="left" styleClass="item"/>
    </column>

    <column width="10%">
      <header value="${DueDate}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.cirCheckout.dueDate}" hAlign="left" styleClass="item"/>
    </column>
       <column width="15%">
      <header value="${Edition}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.documentDetails.edition}"   hAlign="left" styleClass="item"/>
    </column>
      <column width="15%">
      <header value="Fine" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.fine}"   hAlign="left" styleClass="item"/>
    </column>
 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="4" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>
<table width="600px" style="font-family: arial; font-size: 10pt" border=0>
<tr>
<td align="left" width="150px">
     
<c:if test="${previous != null}">
<a href="<c:out value="${previous}"/>"><%=resource.getString("opac.accountdetails.previous")%></a>
</c:if>&nbsp;
<c:if test="${next != null}">
<a href="<c:out value="${next}"/>"><%=resource.getString("opac.accountdetails.next")%></a>
</c:if>
</td><td align="center" >


<c:forEach items="${pages}" var="page">
<c:choose>
  <c:when test="${page.current}">
    <b><a href="<c:out value="${page.url}"/>"><c:out value="${page.index}"/></a></b>
  </c:when>
  <c:otherwise>
    <a href="<c:out value="${page.url}"/>"><c:out value="${page.index}"/></a>
  </c:otherwise>
</c:choose>
</c:forEach>
</td>

</tr>
</table>

  
 
     

  

<%}%>

</td></tr>
  <tr><td align="center">
 </td></tr>
</table>


  
    </body>

</html>


