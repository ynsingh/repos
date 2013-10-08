<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	response.setContentType("text/xml");
%>

<Details>

	<c:forEach var="detailsList" items="${detailsList}">
		<detail>
			<smtpAddress><c:out value="${detailsList.smtpAddress}"/></smtpAddress>
			<smtpPort><c:out value="${detailsList.smtpPort}"/></smtpPort>
			<userName><c:out value="${detailsList.userName}"/></userName>
			<password><c:out value="${detailsList.password}"/></password>
			<universityId><c:out value="${detailsList.universityId}"/></universityId>
		</detail>
	</c:forEach>
</Details>
