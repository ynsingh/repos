<!-- /*Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.-->
<!-- * All Rights Reserved.-->
<!-- *-->
<!-- * Redistribution and use in source and binary forms, with or-->
<!-- * without modification, are permitted provided that the following-->
<!-- * conditions are met:-->
<!-- *-->
<!-- * Redistributions of source code must retain the above copyright-->
<!-- * notice, this  list of conditions and the following disclaimer.-->
<!-- *-->
<!-- * Redistribution in binary form must reproduce the above copyright-->
<!-- * notice, this list of conditions and the following disclaimer in-->
<!-- * the documentation and/or other materials provided with the-->
<!-- * distribution.-->
<!-- *-->
<!-- *-->
<!-- * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED-->
<!-- * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES-->
<!-- * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE-->
<!-- * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE-->
<!-- * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR-->
<!-- * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT-->
<!-- * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR-->
<!-- * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,-->
<!-- * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE-->
<!-- * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,-->
<!-- * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.-->
<!-- *-->
<!-- * Contributors: Members of EdRP, Dayalbagh Educational Institute-->
<!-- */-->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	response.setContentType("text/xml");
%>

<StudentMarksSummary>
<c:forEach var="resultObject" items="${resultObject}">
	<rollNumber>
		<rollNo><c:out value="${resultObject.rollNumber}" /></rollNo>
		<programId><c:out value="${resultObject.programId}" /></programId>
		<programName><c:out value="${resultObject.programName}" /></programName>
		<branchId><c:out value="${resultObject.branchId}" /></branchId>
		<branchName><c:out value="${resultObject.branchName}" /></branchName>
		<specializationId><c:out value="${resultObject.specializationId}" /></specializationId>
		<specialization><c:out value="${resultObject.specialization}" /></specialization>	
	</rollNumber>
	<course >
		<courseName><c:out value="${resultObject.courseName}" /></courseName>
		<courseCode><c:out value="${resultObject.courseCode}" /></courseCode>
		<semesterStartDate><c:out value="${resultObject.semesterStartDate}" /></semesterStartDate>
		<semesterEndDate><c:out value="${resultObject.semesterEndDate}" /></semesterEndDate>
		<courseStatus><c:out value="${resultObject.courseStatus}" /></courseStatus>
		<studentStatus><c:out value="${resultObject.studentStatus}" /></studentStatus>
		<attemptNumber><c:out value="${resultObject.attemptNumber}" /></attemptNumber>
	</course>
	<marks>
		<studentLeft><c:out value="${resultObject.studentLeft}" /></studentLeft>
		<markSavedDate><c:out value="${resultObject.markSavedDate}" /></markSavedDate>
		<displayStartDate><c:out value="${resultObject.displayStartDate}" /></displayStartDate>
		<displayEndDate><c:out value="${resultObject.displayEndDate}" /></displayEndDate>
		<semesterName><c:out value="${resultObject.semesterName}" /></semesterName>
		<evaluationId><c:out value="${resultObject.evaluationId}" /></evaluationId>
		<mark><c:out value="${resultObject.marks}" /></mark>
		<status><c:out value="${resultObject.status}" /></status>
		<semesterStartDate><c:out value="${resultObject.semesterStartDate}" /></semesterStartDate>
		<semesterEndDate><c:out value="${resultObject.semesterEndDate}" /></semesterEndDate>
		<totalInternal><c:out value="${resultObject.totalInternal}" /></totalInternal>
		<totalExternal><c:out value="${resultObject.totalExternal}" /></totalExternal>
		<totalMarks><c:out value="${resultObject.totalMarks}" /></totalMarks>
		<internalGrade><c:out value="${resultObject.internalGrade}" /></internalGrade>
		<externalGrade><c:out value="${resultObject.externalGrade}" /></externalGrade>
		<finalGradePoint><c:out value="${resultObject.finalGradePoint}" /></finalGradePoint>
		<displayType><c:out value="${resultObject.displayType}" /></displayType>	
	</marks>
	<semester>
		<programCourseKey><c:out value="${resultObject.programCourseKey}" /></programCourseKey>
		<semesterCode><c:out value="${resultObject.semesterCode}" /></semesterCode>
		<semesterName><c:out value="${resultObject.semesterName}" /></semesterName>	
		<semesterStartDate><c:out value="${resultObject.semesterStartDate}" /></semesterStartDate>
		<semesterEndDate><c:out value="${resultObject.semesterEndDate}" /></semesterEndDate>
		<name><c:out value="${resultObject.name}" /></name>
	</semester>
	<exception>
		<message><c:out value="${resultObject.message}" /></message>
	</exception>
	<semesterSummary>
		<semesterStartDate><c:out value="${resultObject.semesterStartDate}" /></semesterStartDate>
		<semesterEndDate><c:out value="${resultObject.semesterEndDate}" /></semesterEndDate>
		<sgpa><c:out value="${resultObject.sgpa}" /></sgpa>
		<cgpa><c:out value="${resultObject.cgpa}" /></cgpa>
		<theoryCgpa><c:out value="${resultObject.theoryCgpa}" /></theoryCgpa>
		<theorySgpa><c:out value="${resultObject.theorySgpa}" /></theorySgpa>
		<practicalCgpa><c:out value="${resultObject.practicalCgpa}" /></practicalCgpa>
		<practicalSgpa><c:out value="${resultObject.practicalSgpa}" /></practicalSgpa>
	</semesterSummary>
</c:forEach>

</StudentMarksSummary>
