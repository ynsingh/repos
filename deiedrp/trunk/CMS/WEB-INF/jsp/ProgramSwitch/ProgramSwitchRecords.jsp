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

<ProgramSwitchDetails>
<c:forEach var="resultObject" items="${resultObject}">
	<Details>
	
	<fromEntityId>
	<c:out value="${resultObject.fromEntityId}" />
	</fromEntityId>
	
	<fromEntityName>
	<c:out value="${resultObject.fromEntityName}" />
	</fromEntityName>
	
	<oldProgramId>
	<c:out value="${resultObject.oldProgramId}" />
	</oldProgramId>
	
	<oldProgramName>
	<c:out value="${resultObject.oldProgramName}" />
	</oldProgramName>

	<oldBranchId>
	<c:out value="${resultObject.oldBranchId}" />
	</oldBranchId>
	
	<oldBranchName>
	<c:out value="${resultObject.oldBranchName}" />
	</oldBranchName>
	
	<oldSpecializationId>
	<c:out value="${resultObject.oldSpecializationId}"/>
	</oldSpecializationId>
	
	<oldSpecializationName>
	<c:out value="${resultObject.oldSpecializationName}" />
	</oldSpecializationName>
	
	<oldSemesterCode>
	<c:out value="${resultObject.oldSemesterCode}" />
	</oldSemesterCode>
	
	<oldSemesterName>
	<c:out value="${resultObject.oldSemesterName}"/>
	</oldSemesterName>
	
	<entityId>
	<c:out value="${resultObject.entityId}" />
	</entityId>
	
	<entityName>
	<c:out value="${resultObject.entityName}" />
	</entityName>
	
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
	<c:out value="${resultObject.specializationId}"/>
	</specializationId>
	
	<specializationName>
	<c:out value="${resultObject.specializationName}" />
	</specializationName>
	
	<semesterCode>
	<c:out value="${resultObject.semesterCode}" />
	</semesterCode>
	
	<semesterName>
	<c:out value="${resultObject.semesterName}"/>
	</semesterName>
	
	<componentId>
	<c:out value="${resultObject.componentId}" />
	</componentId>
	
	<componentDescription>
	<c:out value="${resultObject.componentDescription}"/>
	</componentDescription>
	
	<switchRuleId>
	<c:out value="${resultObject.switchRuleId}" />
	</switchRuleId>	
	
	
	</Details>
</c:forEach>
</ProgramSwitchDetails>
