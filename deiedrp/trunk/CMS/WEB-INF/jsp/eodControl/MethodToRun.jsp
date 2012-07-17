
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% response.setContentType("text/xml"); %>
<methodList>
	<c:forEach var="MethodsList" items="${MethodsList}">
		<methods>			
			<methodToRunCode><c:out value="${MethodsList.methodToRunCode}"/></methodToRunCode>
			<methodToRunDescription><c:out value="${MethodsList.methodToRunDescription}"/></methodToRunDescription>			
		</methods>
	</c:forEach>
</methodList>