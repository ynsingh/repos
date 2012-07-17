<!--
/*
 * @(#) BranchList.jsp
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
-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	response.setContentType("text/xml");
%>


<ProgramList>

<c:forEach var="programList" items="${programList}">
<program>
<programId><c:out value="${programList.programId}"/></programId>
<programCode><c:out value="${programList.programCode}"/></programCode>
<programName><c:out value="${programList.programName}"/></programName>
<programType><c:out value="${programList.programType}"/></programType>
<programMode><c:out value="${programList.programMode}"/></programMode>
<numberOfTerms><c:out value="${programList.numberOfTerms}"/></numberOfTerms>
<totalCredits><c:out value="${programList.totalCredits}"/></totalCredits>
<numberOfAttemptAllowed><c:out value="${programList.numberOfAttemptAllowed}"/></numberOfAttemptAllowed>
<maxNumberOfFailSubjects><c:out value="${programList.maxNumberOfFailSubjects}"/></maxNumberOfFailSubjects>
<printAggregate><c:out value="${programList.printAggregate}"/></printAggregate>
<ugOrPg><c:out value="${programList.ugOrPg}"/></ugOrPg>
<tencodes><c:out value="${programList.tencodes}"/></tencodes>
<maxRegSemester><c:out value="${programList.maxRegSemester}"/></maxRegSemester>
<dgpa><c:out value="${programList.dgpa}"/></dgpa>
<creditRequired><c:out value="${programList.creditRequired}"/></creditRequired>
<fixedOrVariableCredit><c:out value="${programList.fixedOrVariableCredit}"/></fixedOrVariableCredit>
<programDescription><c:out value="${programList.programDescription}"/></programDescription>
</program>

</c:forEach>
</ProgramList>