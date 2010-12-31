<%@ page session="true" contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="http://www.tonbeller.com/jpivot" prefix="jp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<jp:mondrianQuery id="query01" jdbcDriver="com.mysql.jdbc.Driver" jdbcUrl="jdbc:mysql://192.168.18.95/sakai_mhrd?user=root&password=devima" 
catalogUri="/WEB-INF/queries/StudentVisualization_activity.xml"> 


select NON EMPTY {[Measures].[Activity Count]} ON COLUMNS,
  NON EMPTY Hierarchize(Union({([Site.Site].[All site], [Activity.Activity].[All Activities])}, Crossjoin([Site.Site].[All site].Children, {[Activity.Activity].[All Activities]}))) ON ROWS
from [StudentVisualization_activity]

</jp:mondrianQuery> <c:set var="title01" scope="session">Course Analysis</c:set> 















