<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	response.setContentType("text/xml");
%>

<Dates>
	<c:forEach var="result" items="${result}">
	<DateDetails>
		<semesterStartDate><c:out value="${result.semesterStartDate}"/></semesterStartDate>
		<semesterEndDate><c:out value="${result.semesterEndDate}"/></semesterEndDate>
		<sessionStartDate><c:out value="${result.sessionStartDate}"/></sessionStartDate>
		<sessionEndDate><c:out value="${result.sessionEndDate}"/></sessionEndDate>
		<registeringSession><c:out value="${result.registeringSession}"/></registeringSession>
		<startDate><c:out value="${result.startDate}"/></startDate>
		<endDate><c:out value="${result.endDate}"/></endDate>
	</DateDetails>
	</c:forEach>
	
</Dates>
