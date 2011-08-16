
<%@page import="com.myapp.struts.circulation.OpacCheckOutDoc,com.myapp.struts.hbm.*"%>
    
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
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/formatstyle.css"/>
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
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

</head>

<body style="margin:0px 0px 0px 0px">



<%
String path= request.getContextPath();
 pageContext.setAttribute("path", path);
ArrayList staffList=new ArrayList();
%>

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

<%

String MemberId=resource.getString("circulation.cir_newmember.memberid");
pageContext.setAttribute("MemberId", MemberId);
String MemberName=resource.getString("circulation.cir_createaccount1.memname");
pageContext.setAttribute("MemberName",MemberName);
String AccessionNo=resource.getString("circulation.showcirreqopac.accessno");
pageContext.setAttribute("AccessionNo", AccessionNo);
String CallNo=resource.getString("circulation.showcirreqopac.callno");
pageContext.setAttribute("CallNo",CallNo);

%>




<%!

   
  OpacCheckOutDoc  obj;
   
   int fromIndex=0, toIndex;
%>
 <%
List<CirOpacRequest> staffList1=null;

 String status=(String)session.getAttribute("status");
 if(status!=null)
 {
   %>
   <p class="err">  <%=status%></p>
   <%
 }
 else{


   staffList = new ArrayList ();
   int tcount =0;
   int perpage=4;
   int tpage=0;



 staffList1=(List<CirOpacRequest>)session.getAttribute("opaclist1");
 Iterator it = staffList1.iterator();
 while(it.hasNext())
     {
        obj = new OpacCheckOutDoc();
        obj.setMemid(staffList1.get(tcount).getCirMemberDetail().getId().getMemId());
        obj.setMemname(staffList1.get(tcount).getMemname());
        obj.setAccession_no(staffList1.get(tcount).getAccessionNo());
        obj.setCall_no(staffList1.get(tcount).getCallNo());
        staffList.add(obj);
        it.next();
        tcount++;
     }
System.out.println("staffList="+staffList.size());
tcount = staffList.size();
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+4) >= staffList.size())
   toIndex = staffList.size();
   request.setAttribute ("staffList", staffList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);



%>

<%

%>



    <table dir="<%=rtl%>" width="100%" align="center" style="margin:0px 0px 0px 0px">




    <tr><td dir="<%=rtl%>"  valign="top" align="center">

<%if(tcount==0)
{%>
<p class="err" style="font-size:12px"><%=resource.getString("circulation.cir_viewall_mem_detail.norecfond")%></p>
<%}
else
{%>


<ui:dataGrid items="${staffList}"  var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid">

  <columns>

     <column width="100">
      <header value="${MemberId}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.memid}" hyperLink="${path}/showbook1.do?id=${doc.accession_no}&amp;memId=${doc.memid}"  hAlign="left"    styleClass="item" hyperLinkTarget="_parent"/>
    </column>
       <column width="100">
      <header value="${MemberName}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.memname}" hyperLink="${path}/showbook1.do?id=${doc.accession_no}&amp;memId=${doc.memid}"  hAlign="left"    styleClass="item" hyperLinkTarget="_parent"/>
    </column>


    <column width="150">
      <header value="${AccessionNo}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.accession_no}" hAlign="left" hyperLink="${path}/showbook1.do?id=${doc.accession_no}&amp;memId=${doc.memid}" hyperLinkTarget="_parent"  styleClass="item"/>
    </column>

    

       <column width="200">
      <header value="${CallNo}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.call_no}" hyperLink="${path}/showbook1.do?id=${doc.accession_no}&amp;memId=${doc.memid}" hyperLinkTarget="_parent"  hAlign="left" styleClass="item"/>
    </column>
 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="4" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>
<table width="550" style="font-family: arial; font-size: 10pt;margin:0px 0px 0px 0px" >
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
  <%}}%>
  <br><br><br>
  </td></tr>
  <tr><td align="center" width="400px">


      </td></tr></table>



    </body>


</html>
