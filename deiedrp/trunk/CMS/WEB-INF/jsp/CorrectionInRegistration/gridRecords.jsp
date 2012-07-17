<%--
 * @(#) gridRecords.jsp
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
	
	<regNo>
	<c:out value="${resultObject1.regNo}" />
	</regNo>
	
	<rollNo>
	<c:out value="${resultObject1.rollNo}"/>
	</rollNo>
	
	<enrollNo>
	<c:out value="${resultObject1.enrollNo}"/>
	</enrollNo>
	
	<admissionMode>
	<c:out value="${resultObject1.admissionMode}"/>
	</admissionMode>
	
	<programName>
	<c:out value="${resultObject1.programName}"/>
	</programName>
	
	<branchName>
	<c:out value="${resultObject1.branchName}"/>
	</branchName>
	
	<specializationName>
	<c:out value="${resultObject1.specializationName}" />
	</specializationName>

	<semesterName>
	<c:out value="${resultObject1.semesterName}" />
	</semesterName>
	
	<programId>
	<c:out value="${resultObject1.programId}" />
	</programId>
	
	<branchId>
	<c:out value="${resultObject1.branchId}" />
	</branchId>
	
	<specializationId>
	<c:out value="${resultObject1.specializationId}" />
	</specializationId>

	<semesterId>
	<c:out value="${resultObject1.semesterId}" />
	</semesterId>
	
	<studentId>
	<c:out value="${resultObject1.studentId}" />
	</studentId>
	
	<sequenceNo>
	<c:out value="${resultObject1.sequenceNo}"/>
	</sequenceNo>

	<reasoncode>
	<c:out value="${resultObject1.reasoncode}" />
	</reasoncode>
	
	<description>
	<c:out value="${resultObject1.description}" />
	</description>
	
	<studentfname>
	<c:out value="${resultObject1.studentfname}" />
	</studentfname>
	
	<studentmname>
	<c:out value="${resultObject1.studentmname}"/>
	</studentmname>
	
	<studentlname>
	<c:out value="${resultObject1.studentlname}"/>
	</studentlname>
	
	<fatherfname>
	<c:out value="${resultObject1.fatherfname}"/>
	</fatherfname>
	
	<fathermname>
	<c:out value="${resultObject1.fathermname}"/>
	</fathermname>
	
	<fatherlname>
	<c:out value="${resultObject1.fatherlname}"/>
	</fatherlname>
	
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
	
	<oldEntity>
	<c:out value="${resultObject1.oldEntity}"/>
	</oldEntity>
	
	<oldProgram>
	<c:out value="${resultObject1.oldProgram}" />
	</oldProgram>

	<oldBranch>
	<c:out value="${resultObject1.oldBranch}" />
	</oldBranch>
	
	<oldSpecialization>
	<c:out value="${resultObject1.oldSpecialization}" />
	</oldSpecialization>

	<oldSemester>
	<c:out value="${resultObject1.oldSemester}" />
	</oldSemester>
	</Details>
</c:forEach>
</PrestagingDetails>


