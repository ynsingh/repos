<!--
/*
 * @(#) SysTwoList.jsp
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% response.setContentType("text/xml");%>

<CodeList>
	<c:forEach var="result" items="${result}">
	<root>
		<entityId><c:out value="${result.entityId}"/></entityId>
		<entityType><c:out value="${result.entityType}"/></entityType>
		<entityName><c:out value="${result.entityName}"/></entityName>
		<programId><c:out value="${result.programId}"/></programId>
		<programName><c:out value="${result.programName}"/></programName>
		<branchId><c:out value="${result.branchId}"/></branchId>
		<branchName><c:out value="${result.branchName}"/></branchName>
		<specializationId><c:out value="${result.specializationId}"/></specializationId>
		<specializationName><c:out value="${result.specializationName}"/></specializationName>
		<semesterCode><c:out value="${result.semesterCode}"/></semesterCode>
		<semesterName><c:out value="${result.semesterName}"/></semesterName>
		<semesterStartDate><c:out value="${result.semesterStartDate}"/></semesterStartDate>
		<semesterEndDate><c:out value="${result.semesterEndDate}"/></semesterEndDate>
		<courseCode><c:out value="${result.courseCode}"/></courseCode>
		<courseName><c:out value="${result.courseName}"/></courseName>
		<programCourseKey><c:out value="${result.programCourseKey}"/></programCourseKey>
		<resultSystem><c:out value="${result.resultSystem}"/></resultSystem>
		<employeeCode><c:out value="${result.employeeCode}"/></employeeCode>
		<employeeName><c:out value="${result.employeeName}"/></employeeName>
		<startDate><c:out value="${result.startDate}"/></startDate>
		<endDate><c:out value="${result.endDate}"/></endDate>	
		<gradelimit><c:out value="${result.gradelimit}"/></gradelimit>	
	</root>
	</c:forEach>
</CodeList>