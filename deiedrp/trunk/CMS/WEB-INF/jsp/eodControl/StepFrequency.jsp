
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% response.setContentType("text/xml"); %>
<stepFrequency>
	<c:forEach var="stepFrequencyList" items="${stepFrequencyList}">
		<frequency>			
			<stepFrequencyCode><c:out value="${stepFrequencyList.stepFrequencyCode}"/></stepFrequencyCode>
			<stepFrequencyDescription><c:out value="${stepFrequencyList.stepFrequencyDescription}"/></stepFrequencyDescription>			
		</frequency>
	</c:forEach>
</stepFrequency>