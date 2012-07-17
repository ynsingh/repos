<!--
/*
 * @(#) studentDetails.jsp
 * Copyright (c) 2012 EdRP, Dayalbagh Educational Institute.
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

<StudentDetails>

	<c:forEach var="studentList" items="${studentList}">
		<detail>
			<entityId><c:out value="${studentList.entityId}"/></entityId>
			<programId><c:out value="${studentList.programId}"/></programId>
			<registrationNo><c:out value="${studentList.registrationNo}"/></registrationNo>
			<studentId><c:out value="${studentList.studentId}"/></studentId>
			<enrollmentNo><c:out value="${studentList.enrollmentNo}"/></enrollmentNo>
			<studentFirstName><c:out value="${studentList.studentFirstName}"/></studentFirstName>
			<studentMiddleName><c:out value="${studentList.studentMiddleName}"/></studentMiddleName>
			<studentLastName><c:out value="${studentList.studentLastName}"/></studentLastName>
			<fatherFirstName><c:out value="${studentList.fatherFirstName}"/></fatherFirstName>
			<fatherMiddleName><c:out value="${studentList.fatherMiddleName}"/></fatherMiddleName>
			<fatherLastName><c:out value="${studentList.fatherLastName}"/></fatherLastName>
			<motherFirstName><c:out value="${studentList.motherFirstName}"/></motherFirstName>
			<motherMiddleName><c:out value="${studentList.motherMiddleName}"/></motherMiddleName>
			<motherLastName><c:out value="${studentList.motherLastName}"/></motherLastName>
			<dateOfBirth><c:out value="${studentList.dateOfBirth}"/></dateOfBirth>
			<category><c:out value="${studentList.category}"/></category>
			<primaryMail><c:out value="${studentList.primaryMail}"/></primaryMail>
			<secondaryMail><c:out value="${studentList.secondaryMail}"/></secondaryMail>
			<nationality><c:out value="${studentList.nationality}"/></nationality>
			<religion><c:out value="${studentList.religion}"/></religion>
			<guardian><c:out value="${studentList.guardian}"/></guardian>
			<gender><c:out value="${studentList.gender}"/></gender>
			<addressKey><c:out value="${studentList.addressKey}"/></addressKey>
			<perAddress><c:out value="${studentList.perAddress}"/></perAddress>
			<perCity><c:out value="${studentList.perCity}"/></perCity>
			<perState><c:out value="${studentList.perState}"/></perState>
			<perPincode><c:out value="${studentList.perPincode}"/></perPincode>
			<phone><c:out value="${studentList.phone}"/></phone>
		</detail>
	</c:forEach>
</StudentDetails>
