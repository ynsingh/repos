<%@ page session="true" contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="http://www.tonbeller.com/jpivot" prefix="jp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>


<%

HttpSession sess=request.getSession();
System.out.println(sess.getAttribute("siteName"));
System.out.println("request.getParameter(siteName)"+request.getParameter("siteName"));
if(request.getParameter("siteName")!=null)
{
sess.setAttribute("siteName",request.getParameter("siteName"));
}
System.out.println(sess.getAttribute("LMS"));
if(sess.getAttribute("LMS").toString().equals("Sakai"))
{
%>

<jp:mondrianQuery id="query01" jdbcDriver="com.mysql.jdbc.Driver" jdbcUrl="jdbc:mysql://192.168.18.90/sakai_mhrd?user=root&password=devima" 
catalogUri="/WEB-INF/queries/Student_Visualization.xml"> 


select NON EMPTY Crossjoin({[Measures].[Event Count]},{[Event.Event].[All event]}) ON COLUMNS,
  NON EMPTY Hierarchize(Union({([First Name.First Name].[All Students])}, [First Name.First Name].[All Students].Children)) ON ROWS
from [Student_Visualization] where [Site.Site].[All site].[<%=sess.getAttribute("siteName")%>]


</jp:mondrianQuery> <c:set var="title01" scope="session">Per Student Summary using Mondrian OLAP</c:set> 
<%
}
else
%>

<jp:mondrianQuery id="query01" jdbcDriver="com.mysql.jdbc.Driver" jdbcUrl="jdbc:mysql://localhost/sakai_mhrd?user=root&password=amma" 
catalogUri="/WEB-INF/queries/Student_Visualization.xml"> 

select NON EMPTY Crossjoin({[Measures].[Event Count]},{[Event.Event].[All event]}) ON COLUMNS,
  NON EMPTY Hierarchize(Union({([First Name.First Name].[All Students])}, [First Name.First Name].[All Students].Children)) ON ROWS
from [Student_Visualization] where [Site.Site].[All site].[<%=sess.getAttribute("siteName")%>]


</jp:mondrianQuery> <c:set var="title01" scope="session">Per Student Summary  from Moodle using Mondrian OLAP</c:set> 
















