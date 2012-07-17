<!--/**
 * @(#) DuplicateStudentList.jsp
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

<%
	response.setContentType("text/xml");
%>

<duplicateStudentList>
	<c:forEach var="duplicateStudent" items="${duplicateStudent}">
		<studentList>
			<universityId><c:out value="${duplicateStudent.universityId}"/></universityId>
			<studentId><c:out value="${duplicateStudent.studentId}"/></studentId>
			<registrationNumber><c:out value="${duplicateStudent.registrationNumber}"/></registrationNumber>
			<studentName><c:out value="${duplicateStudent.studentName}"/></studentName>
			<fatherName><c:out value="${duplicateStudent.fatherName}"/></fatherName>
			<motherName><c:out value="${duplicateStudent.motherName}"/></motherName>
			<category><c:out value="${duplicateStudent.category}"/></category>
			<gender><c:out value="${duplicateStudent.gender}"/></gender>
			<dateOfBirth><c:out value="${duplicateStudent.dateOfBirth}"/></dateOfBirth>
			<programId><c:out value="${duplicateStudent.programId}"/></programId>
			<programName><c:out value="${duplicateStudent.programName}"/></programName>
			<branchId><c:out value="${duplicateStudent.branchId}"/></branchId>
			<branchName><c:out value="${duplicateStudent.branchName}"/></branchName>
			<specializationId><c:out value="${duplicateStudent.specializationId}"/></specializationId>
			<specializationName><c:out value="${duplicateStudent.newSpecialization}"/></specializationName>
			<semesterCode><c:out value="${duplicateStudent.semesterCode}"/></semesterCode>
			<semesterName><c:out value="${duplicateStudent.semester}"/></semesterName>
			<insertTime><c:out value="${duplicateStudent.insertTime}"/></insertTime>
		</studentList>
	</c:forEach>
</duplicateStudentList>
