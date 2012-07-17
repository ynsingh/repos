<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% response.setContentType("text/xml"); %>

<semesterList>
	<c:forEach var="semester" items="${semesterList}">
	<semester>
		<semesterId>
		<c:out value="${semester.semesterId}"/>
		</semesterId>
		<semesterName>
			<c:out value="${semester.semesterName}"></c:out>
		</semesterName>		
	</semester>
	</c:forEach>
</semesterList>