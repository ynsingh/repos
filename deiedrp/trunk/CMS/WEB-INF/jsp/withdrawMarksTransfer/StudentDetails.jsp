<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	response.setContentType("text/xml");
%>

<Details>
	<c:forEach var="result" items="${result}">
	<StudentDetail>
		<rollNumber><c:out value="${result.rollNumber}"/></rollNumber>
		<enrollmentNumber><c:out value="${result.enrollmentNumber}"/></enrollmentNumber>
		<studentName><c:out value="${result.studentName}"/></studentName>
		<fatherName><c:out value="${result.fatherName}"/></fatherName>
		<dob><c:out value="${result.dateOfBirth}"/></dob>
		<gender><c:out value="${result.gender}"/></gender>
		<genderName><c:out value="${result.genderName}"/></genderName>
		<category><c:out value="${result.category}"/></category>
		<categoryName><c:out value="${result.categoryName}"/></categoryName>
		<entityId><c:out value="${result.entityId}"/></entityId>
		<entityName><c:out value="${result.entityName}"/></entityName>
		<programId><c:out value="${result.programId}"/></programId>
		<programName><c:out value="${result.programName}"/></programName>
		<branchId><c:out value="${result.branchId}"/></branchId>
		<branchName><c:out value="${result.branchName}"/></branchName>
		<specializationId><c:out value="${result.specializationId}"/></specializationId>
		<specializationName><c:out value="${result.specializationName}"/></specializationName>
		<semesterCode><c:out value="${result.semesterCode}"/></semesterCode>
		<semesterName><c:out value="${result.semesterName}"/></semesterName>
		<status><c:out value="${result.status}"/></status>
		<attemptNumber><c:out value="${result.attemptNumber}"/></attemptNumber>
		<programCourseKey><c:out value="${result.programCourseKey}"/></programCourseKey>
		<withdrawalSession><c:out value="${result.withdrawalSession}"/></withdrawalSession>
		<withdrawalSemesterStartDate><c:out value="${result.withdrawalSemesterStartDate}"/></withdrawalSemesterStartDate>
		<withdrawalSemesterEndDate><c:out value="${result.withdrawalSemesterEndDate}"/></withdrawalSemesterEndDate>
	</StudentDetail>
	</c:forEach>
	
</Details>
