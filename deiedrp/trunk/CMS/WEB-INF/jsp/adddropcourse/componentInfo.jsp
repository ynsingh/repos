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

<componentDetails>

<c:forEach var="item" items="${detailsList}">

<Details>

<courseCode><c:out value="${item.courseCode}"/></courseCode>
<courseName><c:out value="${item.courseName}"/></courseName>
<credits><c:out value="${item.credits}"/></credits>
<courseClass><c:out value="${item.courseClass}"/></courseClass>
<courseGroup><c:out value="${item.courseGroup}"/></courseGroup>
<courseType><c:out value="${item.courseType}"/></courseType>
<groupName><c:out value="${item.courseGroupName}"/></groupName>
<entityId><c:out value="${item.entityId}"/></entityId>
<programCourseKey><c:out value="${item.programName}"/></programCourseKey>
<semesterStartDate><c:out value="${item.semesterStartDate}"/></semesterStartDate>
<semesterEndDate><c:out value="${item.semesterEndDate}"/></semesterEndDate>
<courseCategory><c:out value="${item.name}"/></courseCategory>
<branchName><c:out value="${item.branchName}"/></branchName>
</Details>
</c:forEach>

</componentDetails>