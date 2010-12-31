<%@ page session="true" contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="http://www.tonbeller.com/jpivot" prefix="jp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<jp:mondrianQuery id="query01" jdbcDriver="com.mysql.jdbc.Driver" jdbcUrl="jdbc:mysql://192.168.18.95/StudViz?user=root&password=devima"
catalogUri="/WEB-INF/queries/moodleLog.xml"> 



SELECT  NON EMPTY {([Measures].[Activity Count],[Course].[All Courses])} ON COLUMNS,
 NON EMPTY {[User].[All Users]}  ON ROWS
FROM [MoodleLog]

</jp:mondrianQuery> <c:set var="title01" scope="session">Course Analysis</c:set> 















