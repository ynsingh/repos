<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	response.setContentType("text/xml");
%>

<personalInfos>
<c:forEach var="details" items="${records}" >
<personalInfo>

<parentEntity><c:out value="${details.parentEntity}"/></parentEntity>
<studentId><c:out value="${details.studentId}"/></studentId>
<studentFirstName><c:out value="${details.studentFirstName}"/></studentFirstName>
<fatherFirstName><c:out value="${details.fatherFirstName}"/></fatherFirstName>
<categoryName><c:out value="${details.categoryName}"/></categoryName>
<primaryMail><c:out value="${details.primaryEmailId}"/></primaryMail>
<dateOfBirth><c:out value="${details.dateOfBirth}"/></dateOfBirth>
<enrollmentNumber><c:out value="${details.enrollmentNumber}"/></enrollmentNumber>
<status><c:out value="${details.status}"/></status>
<gender><c:out value="${details.gender}"/></gender>
<hindiName><c:out value="${details.hindiName}"/></hindiName>
<rollCode><c:out value="${details.path}"/></rollCode>
<address><c:out value="${details.addressLineOne}"/></address>

</personalInfo>
</c:forEach>
</personalInfos>
