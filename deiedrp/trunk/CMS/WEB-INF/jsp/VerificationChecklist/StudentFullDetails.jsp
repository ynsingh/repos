<!--
/*
 * @(#) StudentFullDetails.jsp
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
<student>
<roll_number><c:out value="${studentDetails.rollNumber}"/></roll_number>
<enrollment_number><c:out value="${studentDetails.enrollmentNumber}"/></enrollment_number>
<registrationNumber><c:out value="${studentDetails.registrationNumber}"/></registrationNumber>
<status><c:out value="${studentDetails.status}"/></status>
<admission_mode><c:out value="${studentDetails.admissionMode}"/></admission_mode>
<student_name><c:out value="${studentDetails.studentName}"/></student_name>
<father_name><c:out value="${studentDetails.fatherName}"/></father_name>
<mother_name><c:out value="${studentDetails.motherName}"/></mother_name>
<date_of_birth><c:out value="${studentDetails.dateOfBirth}"/></date_of_birth>
<entity_name><c:out value="${studentDetails.entityName}"/></entity_name>
<program_name><c:out value="${studentDetails.programName}"/></program_name>
<category><c:out value="${studentDetails.category}"/></category>
<gender><c:out value="${studentDetails.gender}"/></gender>
<branch_name><c:out value="${studentDetails.branchName}"/></branch_name>
<old_entity_name><c:out value="${studentDetails.oldEntityName}"/></old_entity_name>
<old_program_name><c:out value="${studentDetails.oldProgramName}"/></old_program_name>
<old_branch_name><c:out value="${studentDetails.oldBranchName}"/></old_branch_name>
<old_specialization><c:out value="${studentDetails.oldSpecialization}"/></old_specialization>
<new_specialization><c:out value="${studentDetails.newSpecialization}"/></new_specialization>
<semester_code><c:out value="${studentDetails.semesterCode}"/></semester_code>
<old_semester_code><c:out value="${studentDetails.oldSemesterCode}"/></old_semester_code>
<session_start_date><c:out value="${studentDetails.sessionStartDate}"/></session_start_date>
<session_end_date><c:out value="${studentDetails.sessionEndDate}"/></session_end_date>
<sequence_number><c:out value="${studentDetails.sequenceNumber}"/></sequence_number>

</student>

</studentdata>
