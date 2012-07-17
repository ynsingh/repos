<!-- /*Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.-->
<!-- * All Rights Reserved.-->
<!-- ExaminarBasicDetail.jsp*-->
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

<basicDetails>
<c:forEach var="resultObject" items="${resultObject}">
	<Details>
	<ExaminarId>
	<c:out value="${resultObject.examinarId}" />
	</ExaminarId>
	
	<Prefix>
	<c:out value="${resultObject.prefix}" />
	</Prefix>
	
	<ExaminarFirstName>
	<c:out value="${resultObject.firstName}" />
	</ExaminarFirstName>

	<ExaminarMiddleName>
	<c:out value="${resultObject.middleName}" />
	</ExaminarMiddleName>
	
	<ExaminarLastName>
	<c:out value="${resultObject.lastName}" />
	</ExaminarLastName>

	
	<designation>
	<c:out value="${resultObject.designation}" />
	</designation>


	<gender>
	<c:out value="${resultObject.gender}" />
	</gender>
	
	<Department>
	<c:out value="${resultObject.department}" />
	</Department>
	
    <CollegeName>
	<c:out value="${resultObject.collegeName}" />
	</CollegeName>

    <CollegeAddress>
	<c:out value="${resultObject.collegeAddress}" />
	</CollegeAddress>
	
	</Details>
</c:forEach>
</basicDetails>