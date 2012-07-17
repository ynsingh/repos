<!--
/*
 * @(#) StudentRecord.jsp
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

<studentdata>

<c:forEach var="StudentDetails" items="${studentRecord}">
	<student>
	<student_id>
	<c:out value="${StudentDetails.studentId}" />
	</student_id>
	<roll_number>
	<c:out value="${StudentDetails.rollNumber}" />
	</roll_number>
	<enrollment_number>
	<c:out value="${StudentDetails.enrollmentNumber}" />
	</enrollment_number>
	<date_of_birth>
	<c:out value="${StudentDetails.dateOfBirth}" />
	</date_of_birth>
	<category>
	<c:out value="${StudentDetails.category}" />
	</category>
	<category_description>
	<c:out value="${StudentDetails.categoryDescription}" />
	</category_description>
	<gender>
	<c:out value="${StudentDetails.gender}" />
	</gender>
	<gender_description>
	<c:out value="${StudentDetails.genderDescription}" />
	</gender_description>
	<student_name>
	<c:out value="${StudentDetails.studentName}" />
	</student_name>
	<father_name>
	<c:out value="${StudentDetails.fatherName}" />
	</father_name>
	<mother_name>
	<c:out value="${StudentDetails.motherName}" />
	</mother_name>
	<entity_name>
	<c:out value="${StudentDetails.entityName}" />
	</entity_name>
	<program_name>
	<c:out value="${StudentDetails.programName}" />
	</program_name>
	<branch_name>
	<c:out value="${StudentDetails.branchName}" />
	</branch_name>
	<entity_id>
	<c:out value="${StudentDetails.entityId}" />
	</entity_id>
	<program_id>
	<c:out value="${StudentDetails.programId}" />
	</program_id>
	<branch_code>
	<c:out value="${StudentDetails.branchCode}" />
	</branch_code>
	<old_entity_name>
	<c:out value="${StudentDetails.oldEntityName}" />
	</old_entity_name>
	<old_program_name>
	<c:out value="${StudentDetails.oldProgramName}" />
	</old_program_name>
	<old_branch_name>
	<c:out value="${StudentDetails.oldBranchName}" />
	</old_branch_name>
	<old_entity_id>
	<c:out value="${StudentDetails.oldEntityId}" />
	</old_entity_id>
	<old_program_id>
	<c:out value="${StudentDetails.oldProgramId}" />
	</old_program_id>
	<old_branch_code>
	<c:out value="${StudentDetails.oldBranchCode}" />
	</old_branch_code>
	<old_specialization>
	<c:out value="${StudentDetails.oldSpecialization}" />
	</old_specialization>
	<old_specialization_description>
	<c:out value="${StudentDetails.oldSpecializationDescription}" />
	</old_specialization_description>
	<new_specialization>
	<c:out value="${StudentDetails.newSpecialization}" />
	</new_specialization>
	<new_specialization_description>
	<c:out value="${StudentDetails.newSpecializationDescription}" />
	</new_specialization_description>
	<semester_code>
	<c:out value="${StudentDetails.semesterCode}" />
	</semester_code>
	<semester>
	<c:out value="${StudentDetails.semester}" />
	</semester>
	<old_semester_code>
	<c:out value="${StudentDetails.oldSemesterCode}" />
	</old_semester_code>
	<old_semester>
	<c:out value="${StudentDetails.oldSemester}" />
	</old_semester>
	<admission_mode>
	<c:out value="${StudentDetails.admissionMode}" />
	</admission_mode>
	<session_start_date>
	<c:out value="${StudentDetails.sessionStartDate}" />
	</session_start_date>
	<session_end_date>
	<c:out value="${StudentDetails.sessionEndDate}" />
	</session_end_date>
	<attempt>
	<c:out value="${StudentDetails.attempt}" />
	</attempt>
	<first_name>
	<c:out value="${StudentDetails.firstName}" />
	</first_name>
	<middle_name>
	<c:out value="${StudentDetails.middleName}" />
	</middle_name>
	<last_name>
	<c:out value="${StudentDetails.lastName}" />
	</last_name>
	<primary_email_id>
	<c:out value="${StudentDetails.primaryEmailId}" />
	</primary_email_id>
	<sequence_number>
	<c:out value="${StudentDetails.sequenceNumber}" />
	</sequence_number>
	<probable_semester>
	<c:out value="${StudentDetails.probableSemester}" />
	</probable_semester>
	<probable_semester_start_date>
	<c:out value="${StudentDetails.probableSemesterStartDate}" />
	</probable_semester_start_date>
	<probable_semester_end_date>
	<c:out value="${StudentDetails.probableSemesterEndDate}" />
	</probable_semester_end_date>
	<probable_attempt_number>
	<c:out value="${StudentDetails.probableAttemptNumber}" />
	</probable_attempt_number>
	<probable_register_due_date>
	<c:out value="${StudentDetails.probableRegisterDueDate}" />
	</probable_register_due_date>
	<status_in_semester>
	<c:out value="${StudentDetails.statusInSemester}" />
	</status_in_semester>
	<fatherFirstName>
	<c:out value="${StudentDetails.fatherFirstName}" />
	</fatherFirstName>
	<fatherMiddleName>
	<c:out value="${StudentDetails.fatherMiddleName}" />
	</fatherMiddleName>
	<fatherLastName>
	<c:out value="${StudentDetails.fatherLastName}" />
	</fatherLastName>
	<motherFirstName>
	<c:out value="${StudentDetails.motherFirstName}" />
	</motherFirstName>
	<motherMiddleName>
	<c:out value="${StudentDetails.motherMiddleName}" />
	</motherMiddleName>
	<motherLastName>
	<c:out value="${StudentDetails.motherLastName}" />
	</motherLastName>
	</student>

</c:forEach>
</studentdata>





