<%--jsp:include page="adminheader.jsp" flush="true" />--%>

<%@page import="com.myapp.struts.admin.*,com.myapp.struts.hbm.*,java.io.File,org.w3c.dom.Document,org.w3c.dom.*,javax.xml.parsers.DocumentBuilderFactory,javax.xml.parsers.DocumentBuilder,org.xml.sax.SAXException,org.xml.sax.SAXParseException"%>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="java.util.*,java.lang.Object"%>
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


<script>
function fun()
{


 top.location="<%=request.getContextPath()%>/log/print.do";

}
</script>
    <%
try{
if(session.getAttribute("library_id")!=null){
System.out.println("library_id"+session.getAttribute("library_id"));
}
else{
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/login.jsp?session=\"expired\"";</script><%
    }
}catch(Exception e){
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %>sessionout();<%
    }

%>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>



</head>

<body>

<%!


   RequestDoc Ob;
   ArrayList requestList;
   int fromIndex=0, toIndex;
%>
 <%
List<Item> rs=null;
 if(request.getAttribute("search_institute_resultset1")!=null){
rs = (List<Item>)(request.getAttribute("search_institute_resultset1"));
session.setAttribute("search_institute_resultset1", rs);
}
else{
rs = (List<Item>)(session.getAttribute("search_institute_resultset1"));
}



   requestList = new ArrayList ();
   int tcount =0;
   int perpage=10;
   int tpage=0;
 /*Create a connection by using getConnection() method
   that takes parameters of string type connection url,
   user name and password to connect to database.*/

//rs.beforeFirst();
if(rs!=null)
{
    Iterator it = rs.iterator();
   while (it.hasNext()) {
	System.out.println(rs.get(0).getClass());
       Item adminReg = (Item)rs.get(tcount);
       System.out.println(adminReg.getClass());
       tcount++;
	Ob = new RequestDoc ();
       // Ob.setSno(adminReg.getLogs().getSno());
	Ob.setDate(adminReg.getDateTime());
	Ob.setUserId(adminReg.getUserid());
	Ob.setLibrary_id(adminReg.getLibrary_id());
        Ob.setUrl(adminReg.getUrl());
        Ob.setSublibrary_id(adminReg.getSublibrary_id());
        Ob.setUser_name(adminReg.getUsername());
        Ob.setRole(adminReg.getRole());
        
   requestList.add(Ob);
it.next();
   //System.out.println("tcount="+tcount);
		     }

System.out.println("tcount="+tcount);

%>

<%
   fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+10) >= requestList.size ())
   toIndex = requestList.size();
   session.setAttribute ("requestList", requestList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);
   String pagecontext = request.getContextPath();
   System.out.println(pagecontext);
   pageContext.setAttribute("pagecontext", pagecontext);
   }
%>
<br><br>
<%if(tcount==0)
{%>
<p class="err" style="font-size:12px">No Record Found</p>
<%}
else
{%>

<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";
%>
<%
try{
locale1=(String)session.getAttribute("locale");

    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
       // System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;align="left";}
    else{ rtl="RTL";page=false;align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>

<%
 String sno="Sno";
  pageContext.setAttribute("Sno", sno);
  String Date="Date";
  pageContext.setAttribute("Date", Date);
  String UserId="User Id";
  pageContext.setAttribute("UserId", UserId);
  String Library="Library";
  pageContext.setAttribute("Library",Library);
  String Url="Request URL";
  pageContext.setAttribute("URL",Url);
  String SubLibrary="Sub/Departmental Library";
  pageContext.setAttribute("SubLibrary",SubLibrary);
  String UserName="User Name";
  pageContext.setAttribute("UserName",UserName);
  String role="User Role";
  pageContext.setAttribute("UserRole",role);
  

%>
<ui:dataGrid items="${requestList}"  var="doc" name="datagrid1" cellPadding="0" cellSpacing="0" styleClass="datagrid">

  <columns>

    

    <column width="200">
      <header value="${Date}" hAlign="left" styleClass="header"/>
      <item   value="${doc.date}" hyperLink="${pagecontext}/admin/index7.jsp?id=${doc.sno}"  hAlign="left"    styleClass="item"/>
    </column>

    <column width="200">
      <header value="${UserId}" hAlign="left" styleClass="header"/>
      <item   value="${doc.userId}" hAlign="left" hyperLink="${pagecontext}/admin/index7.jsp?id=${doc.sno}"  styleClass="item"/>
    </column>
 <column width="200">
      <header value="${URL}" hAlign="left" styleClass="header"/>
      <item   value="${doc.url}" hAlign="left" hyperLink="${pagecontext}/admin/index7.jsp?id=${doc.sno}"  styleClass="item"/>
    </column>


    <column width="150">
      <header value="${Library}" hAlign="left" styleClass="header"/>
      <item   value="${doc.library_id}" hyperLink="${pagecontext}/admin/index7.jsp?id=${doc.sno}"  hAlign="left" styleClass="item"/>
    </column>
        <column width="150">
      <header value="${SubLibrary}" hAlign="left" styleClass="header"/>
      <item   value="${doc.sublibrary_id}" hyperLink="${pagecontext}/admin/index7.jsp?id=${doc.sno}"  hAlign="left" styleClass="item"/>
    </column>
        <column width="150">
      <header value="${UserName}" hAlign="left" styleClass="header"/>
      <item   value="${doc.user_name}" hyperLink="${pagecontext}/admin/index7.jsp?id=${doc.sno}"  hAlign="left" styleClass="item"/>
    </column>
  <column width="150">
      <header value="${UserRole}" hAlign="left" styleClass="header"/>
      <item   value="${doc.role}" hyperLink="${pagecontext}/admin/index7.jsp?id=${doc.sno}"  hAlign="left" styleClass="item"/>
    </column>
 </columns>

<rows styleClass="rows" hiliteStyleClass="hiliterows"/>
  <alternateRows styleClass="alternaterows"/>

  <paging size="10" count="${tCount}" custom="true" nextUrlVar="next"
       previousUrlVar="previous" pagesVar="pages"/>
  <order imgAsc="up.gif" imgDesc="down.gif"/>
</ui:dataGrid>
<table width="80%" style="font-family: arial; font-size: 10pt" border=0>
<tr>
<td align="left" width="100px">
<c:if test="${previous != null}">
<a href="<c:out value="${previous}"/>">Previous</a>
</c:if>&nbsp;
<c:if test="${next != null}">
<a href="<c:out value="${next}"/>">Next</a>
</c:if>

</td><td width="60%" align="center">

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
</td><td align="center">
     Import :<img src="<%=request.getContextPath()%>/images/excel.jpeg" border="1" height="25" width="25">
    <img src="<%=request.getContextPath()%>/images/xml.jpeg" height="25" border="1" width="25">
    <img src="<%=request.getContextPath()%>/images/pdf.jpeg" onclick="fun()" height="25"border="1" width="25">
</td>

</tr>
<tr><td colspan="3">
        <%

String msg=(String)request.getAttribute("msg");
if(msg!=null)
    {%>
    <p class="err" style="font-size:12px"><%=msg%></p>


<%}%>

    </td></tr>
</table>
<%}%>



<%
/*
    try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            String path=request.getRealPath(request.getContextPath().toString());
            path=path.substring(0,path.lastIndexOf("/"));
path=path.substring(0,path.lastIndexOf("/"));
path=path.substring(0,path.lastIndexOf("/"));
            Document doc = docBuilder.parse (new File(path+"/web/logs/list.xml"));

            // normalize text representation
            doc.getDocumentElement ().normalize ();
            System.out.println ("Root element of the doc is " +
                 doc.getDocumentElement().getNodeName());


            NodeList listOfPersons = doc.getElementsByTagName("userinfo");
            int totalPersons = listOfPersons.getLength();
            System.out.println("Total no of users : " + totalPersons);

            for(int s=0; s<listOfPersons.getLength() ; s++){


                Node firstPersonNode = listOfPersons.item(s);
                if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){


                    Element firstPersonElement = (Element)firstPersonNode;

                    //-------
                    NodeList firstNameList = firstPersonElement.getElementsByTagName("dateTime");
                    Element firstNameElement = (Element)firstNameList.item(0);

                    NodeList textFNList = firstNameElement.getChildNodes();
                    System.out.print("dateTime : " +
                           ((Node)textFNList.item(0)).getNodeValue().trim());

                    //-------
                    NodeList lastNameList = firstPersonElement.getElementsByTagName("username");
                    Element lastNameElement = (Element)lastNameList.item(0);

                    NodeList textLNList = lastNameElement.getChildNodes();
                    System.out.print("username : " +
                           ((Node)textLNList.item(0)).getNodeValue().trim());

                    //----
                    NodeList ageList = firstPersonElement.getElementsByTagName("url");
                    Element ageElement = (Element)ageList.item(0);

                    NodeList textAgeList = ageElement.getChildNodes();
                    System.out.print("url : " +
                           ((Node)textAgeList.item(0)).getNodeValue().trim());

                    //------


                }//end of if clause
System.out.println();

            }//end of for loop with s var


        }catch (SAXParseException err) {
        System.out.println ("** Parsing error" + ", line "
             + err.getLineNumber () + ", uri " + err.getSystemId ());
        System.out.println(" " + err.getMessage ());

        }catch (SAXException e) {
        Exception x = e.getException ();
        ((x == null) ? e : x).printStackTrace ();

        }catch (Throwable t) {
        t.printStackTrace ();
        }
        //System.exit (0);

   */
%>



















    </body>


</html>


