<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	response.setContentType("text/xml");
%>
<printMechansim>	
	<c:forEach var="entity" items="${entityList}">
		<entity>
			<entityName>  <c:out value="${entity.entityName}"></c:out> </entityName>
			<entityId> <c:out value="${entity.entityId}"></c:out> </entityId>
			<entityCode> <c:out value="${entity.entityCode}"></c:out> </entityCode>
		</entity>	
	</c:forEach>	
</printMechansim>
