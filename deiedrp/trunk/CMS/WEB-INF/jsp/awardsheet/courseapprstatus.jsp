<!--
/*
 * @(#) gradelimit.jsp
 * Copyright (c) 2012 EdRP, Dayalbagh Educational Institute.
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

<% response.setContentType("text/xml");%>

<CodeList>
	<c:forEach var="result" items="${result}">
	<root>
		<courseCode><c:out value="${result.courseCode}"/></courseCode>
		<entityId><c:out value="${result.entityId}"/></entityId>
		
		<requestSender><c:out value="${result.requestSender}"/></requestSender>
		<requestSendername><c:out value="${result.requestSendername}"/></requestSendername>
		<requestSenderdesignation><c:out value="${result.requestSenderdesignation}"/></requestSenderdesignation>
		
		<requestGetter><c:out value="${result.requestGetter}"/></requestGetter>
		<requestgettername><c:out value="${result.requestgettername}"/></requestgettername>
		<requestGetterdesignation><c:out value="${result.requestGetter}"/></requestGetterdesignation>
		
		<requestdate><c:out value="${result.requestdate}"/></requestdate>
		<completiondate><c:out value="${result.completiondate}"/></completiondate>
		<approvalOrder><c:out value="${result.approvalOrder}"/></approvalOrder>
		<status><c:out value="${result.status}"/></status>
		
		<submitdates><c:out value="${result.submitdates}"/></submitdates>
	</root>
	</c:forEach>
</CodeList>
