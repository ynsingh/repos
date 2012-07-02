    <%@page import="com.myapp.struts.opac.FineDoc,com.myapp.struts.hbm.*,com.myapp.struts.opac.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="java.util.*,com.myapp.struts.opacDAO.*"%>
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

    <title>View Reservation Detail</title>

<script language="javascript" >

function b2click()
{
f.action="login.jsp";
f.method="post";
f.target="_self";
f.submit();
}

</script>
</head>
<jsp:include page="opacheader.jsp"></jsp:include>
<body>
  <%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
    boolean page=true;
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



<%!
String ID,status,lastchkoutdate,no_of_chkout ,reservation_made, fine ,name;
ResultSet rs=null,rs1=null;
%>

<%!
   
   
  FineDoc Ob;
   ArrayList requestList;
   int fromIndex=0, toIndex=4;
%>
 <%
 String name=(String)session.getAttribute("mem_name");
 ID= (String)session.getAttribute("mem_id");
 if(ID==null)ID=(String)session.getAttribute("id");
 String lib_id=(String)session.getAttribute("memlib");
 String sublibrary_id=(String)session.getAttribute("memsublib");

 //session.getAttribute("fine_details");
List<MemberFinewithDocument> rs=(List<MemberFinewithDocument>)session.getAttribute("memberFineDetails");
System.out.println(rs.size());
   requestList = new ArrayList ();
   int tcount =0;
   int perpage=4;
   int tpage=0;
 
Iterator it = rs.iterator();

   while (it.hasNext()) {
	MemberFinewithDocument mem = (MemberFinewithDocument)it.next();
	Ob = new FineDoc ();

 
	Ob.setTitle(String.valueOf(mem.getDocumentDetails().getTitle()==null?"":mem.getDocumentDetails().getTitle()));
	Ob.setAuthor(String.valueOf(mem.getDocumentDetails().getMainEntry()==null?"":mem.getDocumentDetails().getMainEntry()));
	Ob.setCallno(String.valueOf(mem.getDocumentDetails().getCallNo()==null?"":mem.getDocumentDetails().getCallNo().toString()));
	Ob.setPubl(String.valueOf(mem.getDocumentDetails().getPublisherName()==null?"":mem.getDocumentDetails().getPublisherName().toString()));
        Ob.setDate(String.valueOf(mem.getCirCheckin().getReturningDate()==null?"":mem.getCirCheckin().getReturningDate().toString()));
        Ob.setFine(String.valueOf(mem.getCirTransactionHistory().getFineAmt()==null?"":mem.getCirTransactionHistory().getFineAmt().toString()));
        
   requestList.add(Ob);
    
tcount++;
   //System.out.println("tcount="+tcount);
		     }

%>

 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<%
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+4) >= requestList.size ())
   toIndex = requestList.size();
   request.setAttribute ("requestList", requestList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
%>
<br>

<table  align="center" dir="<%=rtl%>" width="80%" class="datagrid" style="border: dashed 1px cyan;">




   <tr><td   dir="<%=rtl%>" style="font-family:Tahoma;font-size:12px" height="28px" align="<%=align%>">
          <table width="100%">
              <tr><td   dir="<%=rtl%>" colspan="2" style="font-family:Arial;font-size:12px;border-bottom: dashed 1px cyan" height="28px" align="center" ><b>Circulation Member : My Account Section OPAC</b></td></tr>
              <tr><td  dir="<%=rtl%>" style="font-family:Tahoma;font-size:12px;border-bottom: dashed 1px cyan" height="28px" align="<%=align%>"><b>


	&nbsp;&nbsp;
                <a href="<%=request.getContextPath()%>/OPAC/accountdetails.jsp"  style="text-decoration: none;"><%=resource.getString("opac.accountdetails.home")%></a>&nbsp;|&nbsp;
            <a href="<%=request.getContextPath()%>/OPAC/OpacLib.do?name=newdemand"  style="text-decoration: none;"> <%=resource.getString("opac.accountdetails.newdemand")%></a>&nbsp;
    |&nbsp;<a href="#"  style="text-decoration: none;"> <%=resource.getString("opac.accountdetails.reservationrequest")%></a>




          </b>
                  </td><td align="right" dir="<%=rtl%>" style="font-family:Tahoma;font-size:12px;border-bottom: dashed 1px cyan;"><%=resource.getString("opac.accountdetails.hi")%>&nbsp;<%=name%>&nbsp;<b>|</b>&nbsp;<a href="home.do"  style="text-decoration: none;"><%=resource.getString("opac.accountdetails.logout")%></a></td></tr>
          </table>
        </td></tr>




  <tr><td  valign="top" >

          
<%if(tcount==0)
{%>
<p class="err" style="font-size:12px">No Record Found</p>
<%}
else
{%>
<ui:dataGrid items="${requestList}"  var="doc" name="datagrid1" cellPadding="2" cellSpacing="0" styleClass="datagrid">
    
  <columns>
      
    <column width="10">
      <header value="" hAlign="left" styleClass="header"/>
    </column>

    <column width="100">
      <header value="Title" hAlign="left" styleClass="header"/>
      <item   value="${doc.title}"  hAlign="left"    styleClass="item"/>
    </column>

    <column width="200">
      <header value="Author" hAlign="left" styleClass="header"/>
      <item   value="${doc.author}" hAlign="left"   styleClass="item"/>
    </column>

    <column width="200">
      <header value="CallNo" hAlign="left" styleClass="header"/>
      <item   value="${doc.callno}"   hAlign="left" styleClass="item"/>
    </column>
   
    <column width="100">
      <header value="Publisher" hAlign="left" styleClass="header"/>
      <item   value="${doc.publ}" hAlign="left" styleClass="item"/>
    </column>
       <column width="100">
      <header value="Date" hAlign="left" styleClass="header"/>
      <item   value="${doc.date}"   hAlign="left" styleClass="item"/>
    </column>
        <column width="100">
      <header value="Fine" hAlign="left" styleClass="header"/>
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
 <a href="accountdetails.jsp" > <%=resource.getString("opac.accountdetails.back")%></a>&nbsp;&nbsp;

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

</td></tr></table>
    </body>
    <jsp:include page="opacfooter.jsp"></jsp:include>

</html>


