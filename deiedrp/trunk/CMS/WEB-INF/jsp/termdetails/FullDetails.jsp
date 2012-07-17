<!--
/*
 * @(#) FullDetails.jsp
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
<term>
<minimumSgpa><c:out value="${programList.minimumSgpa}"/></minimumSgpa>
<minimumCgpa><c:out value="${programList.minimumCgpa}"/></minimumCgpa>
<numberOfTeachingDays><c:out value="${programList.numberOfTeachingDays}"/></numberOfTeachingDays>
<durationInWeeks><c:out value="${programList.durationInWeeks}"/></durationInWeeks>
<semesterStartDate><c:out value="${programList.semesterStartDate}"/></semesterStartDate>
<semesterEndDate><c:out value="${programList.semesterEndDate}"/></semesterEndDate>
<semesterSequence><c:out value="${programList.semesterSequence}"/></semesterSequence>
<semesterGroup><c:out value="${programList.semesterGroup}"/></semesterGroup>
<finalSemesterCode><c:out value="${programList.finalSemesterCode}"/></finalSemesterCode>
<minimumCredit><c:out value="${programList.minimumCredit}"/></minimumCredit>
<maximumCredit><c:out value="${programList.maximumCredit}"/></maximumCredit>
<minimumLectureCredit><c:out value="${programList.minimumLectureCredit}"/></minimumLectureCredit>
<maximumLectureCredit><c:out value="${programList.maximumLectureCredit}"/></maximumLectureCredit>
<maximumCreditSpecialcase><c:out value="${programList.maximumCreditSpecialcase}"/></maximumCreditSpecialcase>
<maxSpecLectureCourse><c:out value="${programList.maxSpecLectureCourse}"/></maxSpecLectureCourse>
</term>

</c:forEach>
</ProgramList>