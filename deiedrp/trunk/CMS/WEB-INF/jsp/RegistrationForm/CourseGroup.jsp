<!--
/*
 * @(#) CourseGroup.jsp
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

<CoreSubjects>

<c:forEach var="CourseGroup" items="${coreGroup}">
<compulsory>
<groupCode><c:out value="${CourseGroup.groupCode}"/></groupCode>
<groupName><c:out value="${CourseGroup.groupName}"/></groupName>
<groupOrder><c:out value="${CourseGroup.groupOrder}"/></groupOrder>
<minimumSelection><c:out value="${CourseGroup.minimumSelection}"/></minimumSelection>
<maximumSelection><c:out value="${CourseGroup.maximumSelection}"/></maximumSelection>
<subGroupCode><c:out value="${CourseGroup.subGroupCode}"/></subGroupCode>
<subGroupName><c:out value="${CourseGroup.subGroupName}"/></subGroupName>
<conditionalGroup><c:out value="${CourseGroup.conditionalGroup}"/></conditionalGroup>
<linkedGroup><c:out value="${CourseGroup.linkedGroup}"/></linkedGroup>
<linkedMinimumSelection><c:out value="${CourseGroup.linkedMinimumSelection}"/></linkedMinimumSelection>
<linkedMaximumSelection><c:out value="${CourseGroup.linkedMaximumSelection}"/></linkedMaximumSelection>
</compulsory>

</c:forEach>
</CoreSubjects>