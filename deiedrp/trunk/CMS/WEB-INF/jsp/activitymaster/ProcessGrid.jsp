<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% response.setContentType("text/xml"); %>
<processGrid>
	<c:forEach var="prcessGrid" items="${prcessGridList}">
		<activityMaster>
			<programId><c:out value="${prcessGrid.programId}"/></programId>
			<branchId><c:out value="${prcessGrid.branchId}"/></branchId>
			<specializationId><c:out value="${prcessGrid.specializationId}"/></specializationId>
			<programCourseKey><c:out value="${prcessGrid.programCourseKey}"/></programCourseKey>
			<programName><c:out value="${prcessGrid.programName}"/></programName>
			<branchName><c:out value="${prcessGrid.branchName}"/></branchName>
			<specializationName><c:out value="${prcessGrid.specializationName}"/></specializationName>
			<semesterStartDate><c:out value="${prcessGrid.semesterStartDate}"/></semesterStartDate>
			<semesterEndDate><c:out value="${prcessGrid.semesterEndDate}"/></semesterEndDate>
		   <status><c:out value="${prcessGrid.processStatus}"/></status>
		</activityMaster>
	</c:forEach>
</processGrid>
