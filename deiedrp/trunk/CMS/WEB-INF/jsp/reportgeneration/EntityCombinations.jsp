<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	response.setContentType("text/pdf");
%>

<combinationDetails>

<c:forEach var="report" items="${resultObject}">
	<report>

	<programId>
	<c:out value="${report.programId}"></c:out>
	</programId>
	<programName>
	<c:out value="${report.programName}"></c:out>
	</programName>
	<programCode>
	<c:out value="${report.programCode}"></c:out>
	</programCode>
	<branchId>
	<c:out value="${report.branchId}"></c:out>
	</branchId>
	<branchName>
	<c:out value="${report.branchName}"></c:out>
	</branchName>
	<specializationId>
	<c:out value="${report.specializationId}"></c:out>
	</specializationId>
	<specializationName>
	<c:out value="${report.specializationName}"></c:out>
	</specializationName>
	<semesterCode>
	<c:out value="${report.semesterCode}"></c:out>
	</semesterCode>
	<semesterName>
	<c:out value="${report.semesterName}"></c:out>
	</semesterName>
	<semesterStartDate>
	<c:out value="${report.semesterStartDate}"></c:out>
	</semesterStartDate>
	<semesterEndDate>
	<c:out value="${report.semesterEndDate}"></c:out>
	</semesterEndDate>
	<finalSemCode>
	<c:out value="${report.finalSemCode}"></c:out>
	</finalSemCode>
	<semesterSequence>
	<c:out value="${report.semesterSequence}"></c:out>
	</semesterSequence>
	<reportGenerated>
	<c:out value="${report.reportGenerated}"></c:out>
	</reportGenerated>
	<printStatus>
	<c:out value="${report.printStatus}"></c:out>
	</printStatus>
	<courseCode>
	<c:out value="${report.courseCode}"></c:out>
	</courseCode>
	<courseName>
	<c:out value="${report.courseName}"></c:out>
	</courseName>
	<companyName>
	<c:out value="${report.companyName}"></c:out>
	</companyName>
	<reqDate>
	<c:out value="${report.reqDate}"></c:out>
	</reqDate>

	</report>

</c:forEach>
</combinationDetails>
