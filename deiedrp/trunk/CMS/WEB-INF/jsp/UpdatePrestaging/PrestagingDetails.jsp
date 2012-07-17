<%--
 * @(#) PrestagingDetails.jsp
 * Author :Rohit
 * Date :3/08/2011
 * Version 1.0
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
 * Redistribution in binary form must reproducuce the above copyright
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
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	response.setContentType("text/xml");
%>

<PrestagingDetails>
<c:forEach var="resultObject1" items="${resultObject}">
	<Details>
	
	<studentId>
	<c:out value="${resultObject1.studentId}" />
	</studentId>

	<regRollNo>
	<c:out value="${resultObject1.regRollNo}" />
	</regRollNo>
	
	<enrollNo>
	<c:out value="${resultObject1.enrollNo}" />
	</enrollNo>
	
	<dob>
	<c:out value="${resultObject1.dob}" />
	</dob>

	<category>
	<c:out value="${resultObject1.category}" />
	</category>
	
	<categoryId>
	<c:out value="${resultObject1.categoryId}" />
	</categoryId>
	
	<gender>
	<c:out value="${resultObject1.gender}" />
	</gender>

	<studentfname>
	<c:out value="${resultObject1.studentfname}" />
	</studentfname>
	
	<studentmname>
	<c:out value="${resultObject1.studentmname}" />
	</studentmname>
	
	<studentlname>
	<c:out value="${resultObject1.studentlname}" />
	</studentlname>
	
	<fatherfname>
	<c:out value="${resultObject1.fatherfname}" />
	</fatherfname>
	
	<fathermname>
	<c:out value="${resultObject1.fathermname}" />
	</fathermname>
	
	<fatherlname>
	<c:out value="${resultObject1.fatherlname}" />
	</fatherlname>

	<motherfname>
	<c:out value="${resultObject1.motherfname}" />
	</motherfname>
	
	<mothermname>
	<c:out value="${resultObject1.mothermname}" />
	</mothermname>
	
	<motherlname>
	<c:out value="${resultObject1.motherlname}" />
	</motherlname>
	
	<newEntity>
	<c:out value="${resultObject1.newEntity}" />
	</newEntity>
	
	<newProgram>
	<c:out value="${resultObject1.newProgram}" />
	</newProgram>
	
	<newBranch>
	<c:out value="${resultObject1.newBranch}" />
	</newBranch>
	
	<newSpecialization>
	<c:out value="${resultObject1.newSpecialization}" />
	</newSpecialization>

	<newSemester>
	<c:out value="${resultObject1.newSemester}" />
	</newSemester>
	
	<newEntityId>
	<c:out value="${resultObject1.newEntityId}" />
	</newEntityId>
	
	<newProgramId>
	<c:out value="${resultObject1.newProgramId}" />
	</newProgramId>
	
	<newBranchId>
	<c:out value="${resultObject1.newBranchId}" />
	</newBranchId>
	
	<newSpecializationId>
	<c:out value="${resultObject1.newSpecializationId}" />
	</newSpecializationId>

	<newSemesterId>
	<c:out value="${resultObject1.newSemesterId}" />
	</newSemesterId>
	
	<attemptNumber>
	<c:out value="${resultObject1.attemptNumber}" />
	</attemptNumber>

	<admissionMode>
	<c:out value="${resultObject1.admissionMode}" />
	</admissionMode>
	
	<processsedFlag>
	<c:out value="${resultObject1.processsedFlag}" />
	</processsedFlag>

	<registrationDueDate>
	<c:out value="${resultObject1.registrationDueDate}" />
	</registrationDueDate>
	
	<semesterStartDate>
	<c:out value="${resultObject1.semesterStartDate}" />
	</semesterStartDate>
	
	<semesterEndDate>
	<c:out value="${resultObject1.semesterEndDate}" />
	</semesterEndDate>
	
	<primaryMail>
	<c:out value="${resultObject1.primaryMail}" />
	</primaryMail>
	
	<processStatus>
	<c:out value="${resultObject1.processStatus}" />
	</processStatus>

	<reasoncode>
	<c:out value="${resultObject1.reasoncode}" />
	</reasoncode>
	
	<description>
	<c:out value="${resultObject1.description}" />
	</description>
	
	<perAddress>
	<c:out value="${resultObject1.perAddress}" />
	</perAddress>
	
	<perCity>
	<c:out value="${resultObject1.perCity}" />
	</perCity>
	
	<perState>
	<c:out value="${resultObject1.perState}" />
	</perState>
	
	<perPincode>
	<c:out value="${resultObject1.perPincode}" />
	</perPincode>

	<corAddress>
	<c:out value="${resultObject1.corAddress}" />
	</corAddress>
	
	<corCity>
	<c:out value="${resultObject1.corCity}" />
	</corCity>
	
	<corState>
	<c:out value="${resultObject1.corState}" />
	</corState>
	
	<corPincode>
	<c:out value="${resultObject1.corPincode}" />
	</corPincode>
	
	<officePhone>
	<c:out value="${resultObject1.officePhone}" />
	</officePhone>
	
	<extraPhone>
	<c:out value="${resultObject1.extraPhone}" />
	</extraPhone>
	
	<otherPhone>
	<c:out value="${resultObject1.otherPhone}" />
	</otherPhone>
	
	<fax>
	<c:out value="${resultObject1.fax}" />
	</fax>
	</Details>
</c:forEach>
</PrestagingDetails>


