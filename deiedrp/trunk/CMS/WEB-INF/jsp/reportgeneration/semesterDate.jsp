
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% response.setContentType("text/xml"); %>

<semesterDate>
	<semesterStartDate>
		<c:out value="${semesterDate.semesterStartDate}"></c:out>
	</semesterStartDate>
	<semesterEndDate>
		<c:out value="${semesterDate.semesterEndDate}"/>
	</semesterEndDate>
	<semesterSequence>
		<c:out value="${semesterDate.semesterSequence}"/>
	</semesterSequence>
	<finalSemesterCode>
		<c:out value="${semesterDate.finalSemesterCode}"/>
	</finalSemesterCode>
</semesterDate>	