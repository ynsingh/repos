<!--
/*
 * @(#) StudentList.jsp
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

<studentdata>

<c:forEach var="StudentList" items="${studentList}">
<student>
<!--<student_id><c:out value="${StudentList.studentId}"/></student_id>-->
<roll_number><c:out value="${StudentList.rollNumber}"/></roll_number>
<enrollment_number><c:out value="${StudentList.enrollmentNumber}"/></enrollment_number>
<registrationNumber><c:out value="${StudentList.registrationNumber}"/></registrationNumber>
<status><c:out value="${StudentList.status}"/></status>
<admission_mode><c:out value="${StudentList.admissionMode}"/></admission_mode>
<student_name><c:out value="${StudentList.studentName}"/></student_name>
<father_name><c:out value="${StudentList.fatherName}"/></father_name>
<date_of_birth><c:out value="${StudentList.dateOfBirth}"/></date_of_birth>
<program_name><c:out value="${StudentList.programName}"/></program_name>
<sequence_number><c:out value="${StudentList.sequenceNumber}"/></sequence_number>
</student>

</c:forEach>
</studentdata>