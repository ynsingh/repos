<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	response.setContentType("text/pdf");
%>

<reportDetails>

<c:forEach var="report" items="${resultObject}">
	<report>

	<reportCode>
	<c:out value="${report.reportCode}"></c:out>
	</reportCode>
	<reportDescription>
	<c:out value="${report.reportDescription}"></c:out>
	</reportDescription>
	<reportTypeCode>
	<c:out value="${report.reportTypeCode}"></c:out>
	</reportTypeCode>
	<reportTypeDescription>
	<c:out value="${report.reportTypeDescription}"></c:out>
	</reportTypeDescription>
	<generateAuthority>
	<c:out value="${report.generateAuthority}"></c:out>
	</generateAuthority>
    <downloadAuthority>
	<c:out value="${report.downloadAuthority}"></c:out>
	</downloadAuthority>
	</report>

</c:forEach>
</reportDetails>