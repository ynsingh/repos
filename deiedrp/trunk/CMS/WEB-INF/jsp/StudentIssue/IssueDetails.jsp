<%--
 * @(#) IssueDetails.jsp
 * Author :Rohit
 * Date :05/09/2011
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

<StudentRemedials>
<c:forEach var="resultObject" items="${resultObject}">
	<Details>
	
	<entityId>
	<c:out value="${resultObject.entityId}" />
	</entityId>
	
	<entity>
	<c:out value="${resultObject.entity}" />
	</entity>

	<programId>
	<c:out value="${resultObject.programId}" />
	</programId>
	
	<programName>
	<c:out value="${resultObject.programName}" />
	</programName>
	
	<branchId>
	<c:out value="${resultObject.branchId}" />
	</branchId>
	
	<branchName>
	<c:out value="${resultObject.branchName}" />
	</branchName>

	<specializationId>
	<c:out value="${resultObject.specializationId}" />
	</specializationId>
	
	<specializationName>
	<c:out value="${resultObject.specializationName}" />
	</specializationName>

	<semesterId>
	<c:out value="${resultObject.semesterId}" />
	</semesterId>
	
	<semesterName>
	<c:out value="${resultObject.semesterName}" />
	</semesterName>
	
	<programCourseKey>
	<c:out value="${resultObject.programCourseKey}" />
	</programCourseKey>
	
	<semesterStartDate>
	<c:out value="${resultObject.semesterStartDate}" />
	</semesterStartDate>
	
	<semesterEndDate>
	<c:out value="${resultObject.semesterEndDate}" />
	</semesterEndDate>

	<penalityCode>
	<c:out value="${resultObject.penalityCode}" />
	</penalityCode>
	
	<penalityFlag>
	<c:out value="${resultObject.penalityFlag}" />
	</penalityFlag>
	
	<issueId>
	<c:out value="${resultObject.issueId}" />
	</issueId>
	
	<issueStatus>
	<c:out value="${resultObject.issueStatus}" />
	</issueStatus>
	
	<penalityCourse1>
	<c:out value="${resultObject.penalityCourse1}" />
	</penalityCourse1>
	
	<penalityCourse2>
	<c:out value="${resultObject.penalityCourse2}" />
	</penalityCourse2>

	<penalityCourse3>
	<c:out value="${resultObject.penalityCourse3}" />
	</penalityCourse3>
	
	<penalityCourse4>
	<c:out value="${resultObject.penalityCourse4}" />
	</penalityCourse4>
	
	<remarks>
	<c:out value="${resultObject.remarks}" />
	</remarks>
	
	<issue>
	<c:out value="${resultObject.issue}" />
	</issue>
	
	<issueCode>
	<c:out value="${resultObject.issueCode}" />
	</issueCode>
	
	<rollNo>
	<c:out value="${resultObject.rollNo}" />
	</rollNo>
	
	</Details>
</c:forEach>
</StudentRemedials>


