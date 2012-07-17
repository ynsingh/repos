<!--
* @(#) gradeLimit.jsp
* @Author :Ashish Mohan
* @Date :27/2/2012
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
<courseCode><c:out value="${item.courseCode}"/></courseCode>
<courseName><c:out value="${item.courseName}"/></courseName>
<marksContEval><c:out value="${item.marksContEval}"/></marksContEval>
<marksEndSemester><c:out value="${item.marksEndSemester}"/></marksEndSemester>
<totalMarks><c:out value="${item.totalMarks}"/></totalMarks>
<grade><c:out value="${item.grade}"/></grade>
<marksTo><c:out value="${item.marksTo}"/></marksTo>
<marksFrom><c:out value="${item.marksFrom}"/></marksFrom>
<displayType><c:out value="${item.displayType}"/></displayType>
<userId><c:out value="${item.userId}"/></userId>
<internalActive><c:out value="${item.internalActive}"/></internalActive>
<limit><c:out value="${item.limitActive}"/></limit>
<universityCode><c:out value="${item.universityCode}"/></universityCode>
<startDate><c:out value="${item.startDate}"/></startDate>
<endDate><c:out value="${item.endDate}"/></endDate>
<lowerA><c:out value="${item.lowerA}"/></lowerA>
<lowerAM><c:out value="${item.lowerAM}"/></lowerAM>
<lowerB><c:out value="${item.lowerB}"/></lowerB>
<lowerBM><c:out value="${item.lowerBM}"/></lowerBM>
<lowerC><c:out value="${item.lowerC}"/></lowerC>
<lowerCM><c:out value="${item.lowerCM}"/></lowerCM>
<lowerD><c:out value="${item.lowerD}"/></lowerD>
<lowerDM><c:out value="${item.lowerDM}"/></lowerDM>
<lowerE><c:out value="${item.lowerE}"/></lowerE>
<lowerEM><c:out value="${item.lowerEM}"/></lowerEM>
<lowerF><c:out value="${item.lowerF}"/></lowerF>
</Details>

</c:forEach>

</courseDetails>
