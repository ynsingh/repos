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

<basicDetails>
<c:forEach var="resultObject" items="${resultObject}">
	<Details>
	
	<parentEntity>
	<c:out value="${resultObject.parentEntity}" />
	</parentEntity>
	
	<employeeFirstName>
	<c:out value="${resultObject.employeeFirstName}" />
	</employeeFirstName>

	<employeeMiddleName>
	<c:out value="${resultObject.employeeMiddleName}" />
	</employeeMiddleName>
	
	<employeeLastName>
	<c:out value="${resultObject.employeeLastName}" />
	</employeeLastName>

	<primaryEmailId>
	<c:out value="${resultObject.primaryEmailId}" />
	</primaryEmailId>
	
	<secondryEmailId>
	<c:out value="${resultObject.secondryEmailId}" />
	</secondryEmailId>

	<qualification>
	<c:out value="${resultObject.qualification}" />
	</qualification>
	
	<designation>
	<c:out value="${resultObject.designation}" />
	</designation>

	<dateOfBirth>
	<c:out value="${resultObject.dateOfBirth}" />
	</dateOfBirth>
	
	<dateOfJoining>
	<c:out value="${resultObject.dateOfJoining}" />
	</dateOfJoining>

	<gender>
	<c:out value="${resultObject.gender}" />
	</gender>
	
	<category>
	<c:out value="${resultObject.category}" />
	</category>

	<employeeCode>
	<c:out value="${resultObject.employeeCode}" />
	</employeeCode>
	
	<employeeStatus>
	<c:out value="${resultObject.employeeStatus}" />
	</employeeStatus>
	
	<postGraduate>
	<c:out value="${resultObject.postGraduate}" />
	</postGraduate>
	
	<netQualified>
	<c:out value="${resultObject.netQualified}" />
	</netQualified>

	<handicapped>
	<c:out value="${resultObject.handicapped}" />
	</handicapped>
	
	<minority>
	<c:out value="${resultObject.minority}" />
	</minority>

	<pensionCode>
	<c:out value="${resultObject.pensionCode}" />
	</pensionCode>
	
	<minorityFlag>
	<c:out value="${resultObject.minorityFlag}" />
	</minorityFlag>
	
	</Details>
</c:forEach>
</basicDetails>