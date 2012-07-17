<!-- 
/*
 * @(#) ProgramBranchSpeclWithId.jsp
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
<datalist>

<c:forEach var="list" items="${listWithId[0]}">
<program name="<c:out value="${list.programName}"/>">
<programId><c:out value="${list.programId}"/></programId>

<branch name="<c:out value="${list.branchName}"/>">
<branchId><c:out value="${list.branchId}"/></branchId>
</branch>
<specialization name="<c:out value="${list.specializationName}"/>">
<specializationId><c:out value="${list.specializationId}"/></specializationId>
</specialization>
</program>
</c:forEach>

<c:forEach var="emp" items="${listWithId[1]}">
<employee name="<c:out value="${emp.firstName} ${emp.lastName}"/>">
<employeeCode><c:out value="${emp.employeeCode}"/></employeeCode>
</employee>
</c:forEach>

<c:forEach var="programTypeList" items="${listWithId[2]}">
<programType name="<c:out value="${programTypeList.programType}"/>">
<programCode><c:out value="${programTypeList.programTypeCode}"></c:out></programCode>
</programType>
</c:forEach>

</datalist>
