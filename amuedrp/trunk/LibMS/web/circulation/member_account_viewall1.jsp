 <%@page import="com.myapp.struts.admin.StaffDoc,com.myapp.struts.hbm.*"%>
    <jsp:include page="/admin/header.jsp" flush="true" />
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

    <title>LibMS : Manage Staff Details</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script language="javascript" >
function b1click()
{
location.href="<%=request.getContextPath()%>/admin/main.jsp";
}
function show(mem,x)
{
    f.action="<%=request.getContextPath()%>/circulation/showAccount1.do?id="+document.getElementById(mem).value+"&status="+x;
f.method="post";
f.target="_self";
f.submit();
}

</script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

</head>
<div
   style="  top:150px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

<body>
    <form name="f">
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
    %>

    <%!

   ResultSet rs=null;
   ArrayList opacList,opacList1;
   int fromIndex, toIndex,fromIndex1,toIndex1;
%>

<%
String path= request.getContextPath();
 pageContext.setAttribute("path", path);

   opacList = new ArrayList ();
   opacList=(ArrayList)session.getAttribute("cirmemacclist");
   int tcount =0;
   int perpage=4;
   int tpage=0;
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");

   if(opacList!=null)
   {



   tcount=opacList.size();



   if ((toIndex = fromIndex+4) >= opacList.size ())
   toIndex = opacList.size();
   request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
   }

   //view all cancel members
   
 
   opacList1 = new ArrayList ();
   opacList1=(ArrayList)session.getAttribute("cirmemacclist1");
   int tcount1 =0;
    
   fromIndex1 = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");

   if(opacList1!=null)
   {



   tcount1=opacList1.size();



   if ((toIndex1 = fromIndex1+4) >= opacList1.size ())
   toIndex1 = opacList1.size();
   request.setAttribute ("opacList1", opacList1.subList(fromIndex1, toIndex1));
   pageContext.setAttribute("tCount1", tcount1);
   }


%>
         <%

String MemberId=resource.getString("circulation.cir_newmember.memberid");
pageContext.setAttribute("MemberId", MemberId);
String Status=resource.getString("circulation.memberaccviewall.Status");
pageContext.setAttribute("Status",Status);
String cardId=resource.getString("circulation.cir_newmember.cardid");
pageContext.setAttribute("cardId", cardId);
String Semester=resource.getString("circulation.memberaccviewall.Semester");
pageContext.setAttribute("Semester",Semester);


%>

<%
String msg=(String)request.getAttribute("msg");
String msg1=(String)request.getAttribute("msg1");

%>
<table border="1" class="table" width="80%" align="center" dir="<%=rtl%>">

<tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Change Status to Delinquent Members</td><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Change Status to Cancel Members</td></tr>
 <tr><td valign="top" align="center"> <br/>
         <table cellspacing="10px">

                    <tr><td dir="<%=rtl%>" rowspan="5" class="txt2"><%=resource.getString("circulation.cir_member_reg.entermemid")%><br>
                            <input type="textbox" name="memid" value="" id="memid"/><br/>
                          <br>
                        </td><td dir="<%=rtl%>" width="150px" align="center"> <input type="button" class="btn"  id="btnstatus"  value="Blocked" onclick="show('memid','Blocked')"  /></td></tr>
                    <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="button" id="btnstatus"  value="UnBlock" class="btn"  onclick="show('memid','Active')"/></td></tr>
                  


                </table>
 
     </td>
     <td valign="top" align="center"> <br/>
      <table cellspacing="10px">

                    <tr><td dir="<%=rtl%>" rowspan="5" class="txt2"><%=resource.getString("circulation.cir_member_reg.entermemid")%><br>
                            <input type="textbox" name="memid" value="" id="mem_id"/><br/>
                          <br>
                        </td><td dir="<%=rtl%>" width="150px" align="center"> <input type="button" class="btn"  id="btnstatus"  value="Cancel" onclick="show('mem_id','Cancel')"  /></td></tr>
                    <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="button" id="btnstatus"  value="Renewal" class="btn"  onclick="show('mem_id','Renewal')"/></td></tr>



                </table>

     </td>
 </tr>
 <tr><td>
      <p   align="<%=align%>" class="mess">
                     <%if(msg!=null){%>
                     
                      <%=msg%>
                     
                        <%}%>




                    </p>
                     <p   align="<%=align%>" class="err">
                     <%if(msg1!=null){%>
                     
          <%=msg1%>
                     
                        
                        <%}%>




                    </p>
     </td>
 </tr>


       <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">View All Delinquent Members</td>
       <td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">View All Cancel Members</td>
       </tr>
                <tr><td valign="top" align="center"> 


<%


%>


<%

%>
<%if(tcount==0)
{%>
<p class="err" style="font-size:12px"><%=resource.getString("circulation.cir_viewall_mem_detail.norecfond")%></p>
<%}
else
{%>
<%--hyperLink="${path}/circulation/showAccount1.do?id=${doc.id.memid}" --%>

<ui:dataGrid items="${opacList}" var="doc" name="datagrid1" cellPadding="0"
    cellSpacing="0" styleClass="datagrid"  >

  <columns>



    <column width="100">
      <header value="${MemberId}" hAlign="left" styleClass="admingridheader"  />
      <item   value="${doc.id.memid}"   hAlign="left"   styleClass="item"/>

    </column>

    <column width="100">
      <header value="${Status}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.status}"   hAlign="left"  styleClass="item"/>
    </column>

    <column width="100">
      <header value="${cardId}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.cardId}"   hAlign="left"  styleClass="item"/>
    </column>

    <column width="100">
      <header value="${Semester}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.semester}"   hAlign="left"  styleClass="item"/>
    </column>

       <column width="100">
      <header value="RegistrationDate" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.reqDate}"   hAlign="left"  styleClass="item"/>
    </column>

       <column width="100">
      <header value="ExpiryDate" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.expiryDate}"  hAlign="left"  styleClass="item"/>
    </column>

      

 </columns>
<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="4" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>

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
  <%}%>
  <br><br><br>
  </td>
  <td>


<%


%>


<%

%>
<%if(tcount1==0)
{%>
<p class="err" style="font-size:12px"><%=resource.getString("circulation.cir_viewall_mem_detail.norecfond")%></p>
<%}
else
{%>


<ui:dataGrid items="${opacList1}" var="doc" name="datagrid1" cellPadding="0"
    cellSpacing="0" styleClass="datagrid"  >

  <columns>



    <column width="100">
      <header value="${MemberId}" hAlign="left" styleClass="admingridheader"  />
      <item   value="${doc.id.memid}" hyperLink="${path}/circulation/showAccount1.do?id=${doc.id.memid}"   hAlign="left"   styleClass="item"/>

    </column>

    <column width="100">
      <header value="${Status}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.status}" hyperLink="${path}/circulation/showAccount1.do?id=${doc.id.memid}"  hAlign="left"  styleClass="item"/>
    </column>

    <column width="100">
      <header value="${cardId}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.cardId}" hyperLink="${path}/circulation/showAccount1.do?id=${doc.id.memid}"  hAlign="left"  styleClass="item"/>
    </column>

    <column width="100">
      <header value="${Semester}" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.semester}" hyperLink="${path}/circulation/showAccount1.do?id=${doc.id.memid}"  hAlign="left"  styleClass="item"/>
    </column>

       <column width="100">
      <header value="RegistrationDate" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.reqDate}" hyperLink="${path}/circulation/showAccount1.do?id=${doc.id.memid}"  hAlign="left"  styleClass="item"/>
    </column>

       <column width="100">
      <header value="ExpiryDate" hAlign="left" styleClass="admingridheader"/>
      <item   value="${doc.expiryDate}" hyperLink="${path}/circulation/showAccount1.do?id=${doc.id.memid}"  hAlign="left"  styleClass="item"/>
    </column>



 </columns>
<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="4" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>

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
  <%}%>
  <br><br><br>

  </td>
  </tr>

 
</table>
</form>
    </body>

</div>

</html>

