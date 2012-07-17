<!--
/*
 * @(#) Details.jsp
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */
-->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	response.setContentType("text/xml");
%>

<Details>
	<c:forEach var="result" items="${result}">
	<StudentDetail>
		<enrollmentNumber><c:out value="${result.enrollmentNumber}"/></enrollmentNumber>
		<studentName><c:out value="${result.studentName}"/></studentName>
		<entityId><c:out value="${result.entityId}"/></entityId>
		<programId><c:out value="${result.programId}"/></programId>
		<branchId><c:out value="${result.branchId}"/></branchId>
		<specializationId><c:out value="${result.specializationId}"/></specializationId>
		<semesterCode><c:out value="${result.semesterCode}"/></semesterCode>
		<programCourseKey><c:out value="${result.programCourseKey}"/></programCourseKey>
		<semesterStartDate><c:out value="${result.semesterStartDate}"/></semesterStartDate>
		<semesterEndDate><c:out value="${result.semesterEndDate}"/></semesterEndDate>
		<gradeLimitActive><c:out value="${result.gradelimit}"/></gradeLimitActive>
		<reason><c:out value="${result.reason}"/></reason>
		<status><c:out value="${result.status}"/></status>
		<courseCode><c:out value="${result.courseCode}"/></courseCode>
		<courseName><c:out value="${result.courseName}"/></courseName>
		<employeeCode><c:out value="${result.employeeCode}"/></employeeCode>
		<employeeName><c:out value="${result.employeeName}"/></employeeName>
	</StudentDetail>
	</c:forEach>
	
</Details>
