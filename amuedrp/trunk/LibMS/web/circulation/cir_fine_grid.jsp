 <%@page import="com.myapp.struts.circulation.CheckInDocumentDetails,com.myapp.struts.systemsetupDAO.BookCategoryDAO,com.myapp.struts.utility.DateCalculation,com.myapp.struts.hbm.*,com.myapp.struts.opacDAO.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridParameters"%>
    <%@ page import="org.apache.taglibs.datagrid.DataGridTag"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="java.io.*,java.lang.*"   %>
    <%@ taglib uri="http://jakarta.apache.org/taglibs/datagrid-1.0" prefix="ui" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title>LibMS : Manage CheckInReport</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script language="javascript" >
function b1click()
{
location.href="<%=request.getContextPath()%>/admin/main.jsp";
}
function b2click()
{
f.action="<%=request.getContextPath()%>/admin/main.jsp";
f.method="post";
f.target="_self";
f.submit();
}

</script>
<%!
    Locale locale=null;
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link href="common" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/newformat.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />

</head>


<body style="margin:0px 0px 0px 0px" onload="random_number(); " >
    <html:form    action="/insertfine_details" method="post" target="f1" styleId="form1" >
        <table border="1px"  dir="<%=rtl%>" width="100%" align="center">



       <tr><td dir="<%=rtl%>" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;"><%=resource.getString("circulation.cir_chekin_report_grid.viewallchekinreport")%></td></tr>
                <tr><td dir="<%=rtl%>" valign="top" align="center"> <br/>


  <%
String LibraryId=resource.getString("opac.browse.table.Libraryid");
pageContext.setAttribute("LibraryId", LibraryId);
String MemberId=resource.getString("circulation.cir_newmember.memberid");
pageContext.setAttribute("MemberId", MemberId);
String SubLibraryId=resource.getString("circulation.cir_chekin_report_grid.sublib");
pageContext.setAttribute("SubLibraryId",SubLibraryId);
String ReturningDate=resource.getString("circulation.cir_checkinbookdetail.returndate");
pageContext.setAttribute("ReturningDate",ReturningDate);


%>

<%
String path= request.getContextPath();
 pageContext.setAttribute("path", path);

%>

<%!

   ResultSet rs=null;
   ArrayList opacList;
   int fromIndex, toIndex,fromindex1,toindex1;
%>

<%
 List<CheckInDocumentDetails>  requestList=(List<CheckInDocumentDetails>)session.getAttribute("finedetails");
 List<FineDetails> finedetaillist=(List<FineDetails>)session.getAttribute("finedetaillist");
 double tfine=((Double)session.getAttribute("fine")).doubleValue();
 tfine=Math.round(tfine);
 double paid=((Double)session.getAttribute("paid")).doubleValue();
 paid=Math.round(paid);
 double remain;
 String slpno,paymod,cheque,bankname,issuedate;
 if(finedetaillist==null){
  paid=0.0;
  remain=tfine-paid;
  
 
  //slpno="-";
  cheque="-";
  bankname="-";
  issuedate="-";


 }
 else{
 remain=tfine-paid;
}

   int tcount =requestList.size();
   int tcount1=finedetaillist.size();

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




  fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+4) >= requestList.size ())
   toIndex = requestList.size();
   request.setAttribute ("requestList", requestList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);


   fromindex1 = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid2");
   if ((toindex1 = fromindex1+4) >= finedetaillist.size ())
   toindex1 = finedetaillist.size();
   request.setAttribute ("finedetaillist", finedetaillist.subList(fromindex1, toindex1));
   pageContext.setAttribute("tCount1", tcount1);
   
%>
<%

%>
<%if(tcount==0)
{%>
<p class="err" style="font-size:12px">Sorry <%=resource.getString("circulation.cir_viewall_mem_detail.norecfond")%></p>
<%}
else
{%>


<ui:dataGrid items="${requestList}" var="doc"  name="datagrid1" cellPadding="0"
    cellSpacing="0" styleClass="datagrid"  >




<columns>
      <column width="15%">
      <header value="MemberID" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.cirCheckin.memberId}"  hAlign="left"      styleClass="item"/>
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
      <header value="IssueDate" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.cirTransactionHistory.issueDate}" hAlign="left" styleClass="item"/>
    </column>

    <column width="10%">
      <header value="Returningdate" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.cirCheckin.returningDate}" hAlign="left" styleClass="item"/>
    </column>
       <column width="15%">
      <header value="${Edition}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.documentDetails.edition}"   hAlign="left" styleClass="item"/>
    </column>
      <column width="15%">
      <header value="Fine" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.cirTransactionHistory.fineAmt}"   hAlign="left" styleClass="item"/>
    </column>
 </columns>
<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="4" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>

 
  <br><br><br>
  </td></tr>
  <tr><td>

<table width="600" style="font-family: arial; font-size: 10pt" border=0>
<tr>
<td align="left">
<c:if test="${previous != null}">
<a href="<c:out value="${previous}"/>"><%=resource.getString("circulation.cir_viewall_mem_detail.previos")%></a>
</c:if>&nbsp;
<c:if test="${next != null}">
<a href="<c:out value="${next}"/>"><%=resource.getString("circulation.cir_viewall_mem_detail.next")%></a>
</c:if>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

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

      </td></tr>



  <tr><td dir="<%=rtl%>" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Fine Detail List</td></tr>
<tr><td dir="<%=rtl%>" valign="top" align="center"> <br/>

        <%if(tcount1==0)
{%>
<p class="err" style="font-size:12px">No Payment Made Till Date<%--<%=resource.getString("circulation.cir_viewall_mem_detail.norecfond")%>--%></p>
<%}
else
{%>


<ui:dataGrid items="${finedetaillist}" var="doc1"  name="datagrid2" cellPadding="0"
    cellSpacing="0" styleClass="datagrid"  >




<columns>
    <column width="15%">
      <header value="MemberId" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc1.id.memid}"  hAlign="left"    styleClass="item"/>
    </column>


    <column width="15%">
      <header value="TotalFine" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc1.tfine}"  hAlign="left"    styleClass="item"/>
    </column>

    <column width="10%">
      <header value="Paid" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc1.paid}" hAlign="left"   styleClass="item"/>
    </column>

    <column width="10%">
      <header value="Remain" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc1.remaining}"   hAlign="left" styleClass="item"/>
    </column>
    <column width="15%">
      <header value="Date" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc1.paydate}"   hAlign="left" styleClass="item"/>
    </column>
<column width="10%">
      <header value="PayMod" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc1.paymod}" hAlign="left" styleClass="item"/>
    </column>

    <column width="10%">
      <header value="Slipno" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc1.id.slipno}" hAlign="left" styleClass="item"/>
    </column>

    
 </columns>
<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="4" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>

  <%}%>
  <br><br><br>
 </td></tr>
  <tr><td>

<table width="600" style="font-family: arial; font-size: 10pt" border=0>
<tr>
<td align="left">
<c:if test="${previous != null}">
<a href="<c:out value="${previous}"/>"><%=resource.getString("circulation.cir_viewall_mem_detail.previos")%></a>
</c:if>&nbsp;
<c:if test="${next != null}">
<a href="<c:out value="${next}"/>"><%=resource.getString("circulation.cir_viewall_mem_detail.next")%></a>
</c:if>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

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

      </td></tr>


  <tr><td><table class="datagrid">
              <tr><td dir="<%=rtl%>" align="left">Total Fine</td><td dir="<%=rtl%>"><input type="text" name="totalfine" value="<%=tfine%>" id="tf"/><%--<html:text property="totalfine" styleId="totalfine" styleClass="textBoxWidth"/>--%></td></tr>
               <tr><td dir="<%=rtl%>">Total Paid</td><td dir="<%=rtl%>"><input type="text" name="paid1" value="<%=paid%>" id="tpf" /></td></tr>
              <tr><td dir="<%=rtl%>">Remaining Fine</td><td dir="<%=rtl%>"><input type="text" name="remain" id="rf" value="<%=remain%>"  onfocus="calculation()"/><%--<html:text property="remain" styleId="remain" styleClass="textBoxWidth"/>--%></td></tr>
               <tr><td dir="<%=rtl%>">Pay</td><td dir="<%=rtl%>"><input type="text" name="paid"  id="pf" /></td></tr>
              
              <tr><td dir="<%=rtl%>">payment Mode</td><td dir="<%=rtl%>"><input type="radio" name="paymod" id="pm" value="Cash" onchange="display(value);" checked /> Cash <input type="radio" name="paymod" id="pm" value="Cheque/DD" onchange="display(value);"/> Cheque/DD<%--<html:text property="paymod" styleId="paymod" styleClass="textBoxWidth"/>--%></td></tr>
              <tr><td dir="<%=rtl%>">Cheque/DD No.</td><td dir="<%=rtl%>"><input type="text" name="chequeno" id="chequeno"  disabled /></td></tr>
               <tr><td dir="<%=rtl%>">Name of Bank</td><td dir="<%=rtl%>"><input type="text" name="bankname" id="bankname"  disabled /></td></tr>
               <tr><td dir="<%=rtl%>">Issue Date</td><td dir="<%=rtl%>"><input type="text" name="date" id="date"  disabled /></td></tr>
              <tr><td dir="<%=rtl%>">Slip No.</td><td dir="<%=rtl%>"><input type="text" name="slipno" id="slipno" /></td></tr>
              <tr><td dir="<%=rtl%>"><input type="button" name="Pay" id="pay"  value="Pay Now" onclick="return validation();"/></td><td dir="<%=rtl%>"><input type="submit" name="FineTransaction" id="FineTransaction"  value="Generate Fine Payment Report" onclick="return finetransaction();"/><br/>
                  
                  </td></tr>

                            </table></td></tr>
 </table>
 <%}%>
</html:form>
    </body>

</html>
<script language="javascript" type="text/javascript">

  function calculation()
    {
        <%--var tf=parseFloat(document.getElementById('tf').value);
        var p1=parseFloat(document.getElementById('pd1').value);
        var p2=parseFloat(document.getElementById('pd2').value);
        var p3=parseFloat(document.getElementById('pd3').value);
        var p4=parseFloat(document.getElementById('pd4').value);
        var pt=p1+p2+p3+p4;
        document.getElementById('pf').value=pt;--%>

        var pf=document.getElementById('pf').value;
        var rf=document.getElementById('rf').value;
        var cf=rf-pf;
       // var ff=Math.round((cf*1000)/1000);

        document.getElementById('rf').value=cf;
        return true;


    }

    function display(input)
    {
        if(input=='Cheque/DD')
            {
             document.getElementById('chequeno').disabled = false;
        document.getElementById('bankname').disabled = false;
        document.getElementById('date').disabled = false;
 }else if(input='cash')
     {
         document.getElementById('chequeno').disabled =true;
        document.getElementById('bankname').disabled = true;
        document.getElementById('date').disabled = true;
     }
    }
function random_number() {
var n;
n=(Math.round((9999999999-100000) * Math.random() + 1000000));
var s="slp"+n;
document.getElementById('slipno').value=s;

    return true;
}

function insert()
{

    document.getElementById("form1").action = "<%=request.getContextPath()%>/insertfine_details.do"
    document.getElementById("form1").method="post";
    document.getElementById("form1").target="f1";
    document.getElementById("form1").submit();
 
 parent.location.href="<%=request.getContextPath()%>/circulation/cir_fine_detail.jsp";
}
function finetransaction()
{

    document.getElementById("form1").action = "<%=request.getContextPath()%>/PrintFineDetails.do"
    document.getElementById("form1").method="post";
    document.getElementById("form1").target="f1";
    document.getElementById("form1").submit();

 parent.location.href="<%=request.getContextPath()%>/circulation/cir_fine_detail.jsp";
}

function validation()
    {

        var t= document.getElementById('pf');
        
        var str="";
        if(t.value=="")
        {
            str+="\nPlease Enter Fine Amount";
            alert(str);
            document.getElementById('pf').focus();
            
             return false;
            }

  insert();
         
            return true;

         }


</script>

