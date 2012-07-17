<!--
/*
 * @(#) MarksList.jsp
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


<MarksList>
<c:forEach var="marksList" items="${result}">
<marks>
<evaluationId><c:out value="${marksList.evaluationId}"/></evaluationId>
<rollNumber><c:out value="${marksList.rollNumber}"/></rollNumber>
<marks><c:out value="${marksList.marks}"/></marks>
<grades><c:out value="${marksList.grades}"/></grades>
<passfail><c:out value="${marksList.passfail}"/></passfail>
<totalInternal><c:out value="${marksList.totalInternal}"/></totalInternal>
<totalExternal><c:out value="${marksList.totalExternal}"/></totalExternal>
<totalMarks><c:out value="${marksList.total}"/></totalMarks>
<internalGrade><c:out value="${marksList.internalGrade}"/></internalGrade>
<externalGrade><c:out value="${marksList.externalGrade}"/></externalGrade>
<finalGrade><c:out value="${marksList.finalGrade}"/></finalGrade>

<attendence><c:out value="${marksList.attendence}"/></attendence>

<inserttime><c:out value="${marksList.inserttime}"/></inserttime>
<modificationtime><c:out value="${marksList.modificationtime}"/></modificationtime>

<requestedmarks><c:out value="${marksList.requestedmarks}"/></requestedmarks>
<requesterremarks><c:out value="${marksList.requesterremarks}"/></requesterremarks>

<issuestatus><c:out value="${marksList.issuestatus}"/></issuestatus>

</marks>
</c:forEach>
</MarksList>
