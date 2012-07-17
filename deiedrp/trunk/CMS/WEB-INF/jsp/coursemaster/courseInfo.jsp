<!--
* @(#) courseInfo.jsp
* @Author :Mohd Amir
* @Date :21/3/2011
* Version 1.0
* Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
* All Rights Reserved.
*
* Redistribution and use in source and binary forms, with or
* without modification, are permitted provided that the following
* conditions are met:
*
* Redistributions of source code must retain the above copyright
* notice, this list of conditions and the following disclaimer.
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
* DISCLAIMED. IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
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

<courseDetails>

<c:forEach var="item" items="${detailsList}">

<Details>

<ownerEntityId><c:out value="${item.ownerEntityId}"/></ownerEntityId>
<ownerEntityName><c:out value="${item.ownerEntityName}"/></ownerEntityName>
<ownerProgramId><c:out value="${item.ownerProgramId}"/></ownerProgramId>
<ownerProgramName><c:out value="${item.ownerProgramName}"/></ownerProgramName>
<ownerBranchId><c:out value="${item.ownerBranchId}"/></ownerBranchId>
<ownerBranchName><c:out value="${item.ownerBranchName}"/></ownerBranchName>
<ownerSpecializationId><c:out value="${item.ownerSpecializationId}"/></ownerSpecializationId>
<ownerSpecializationName><c:out value="${item.ownerSpecializationName}"/></ownerSpecializationName>
<courseGroupId><c:out value="${item.courseGroupId}"/></courseGroupId>
<courseGroupName><c:out value="${item.courseGroupName}"/></courseGroupName>
<courseTypeId><c:out value="${item.courseTypeId}"/></courseTypeId>
<courseTypeName><c:out value="${item.courseTypeName}"/></courseTypeName>
<courseClassificationId><c:out value="${item.courseClassificationId}"/></courseClassificationId>
<courseClassificationName><c:out value="${item.courseClassificationName}"/></courseClassificationName>
<courseCode><c:out value="${item.courseCode}"/></courseCode>
<courseName><c:out value="${item.courseName}"/></courseName>
<marksContEval><c:out value="${item.marksContEval}"/></marksContEval>
<marksEndSemester><c:out value="${item.marksEndSemester}"/></marksEndSemester>
<marksTotal><c:out value="${item.marksTotal}"/></marksTotal>
<units><c:out value="${item.units}"/></units>
<credits><c:out value="${item.credits}"/></credits>
<lectures><c:out value="${item.lectures}"/></lectures>
<tutorials><c:out value="${item.tutorials}"/></tutorials>
<practicals><c:out value="${item.practicals}"/></practicals>
<sinceSession><c:out value="${item.sinceSession}"/></sinceSession>
<resultSystem><c:out value="${item.resultSystem}"/></resultSystem>
<dummyFlag><c:out value="${item.dummyFlag}"/></dummyFlag>
<gradeLimit><c:out value="${item.gradeLimit}"/></gradeLimit>
<edeiStatus><c:out value="${item.edeiStatus}"/></edeiStatus>

</Details>

</c:forEach>

</courseDetails>