<!--/**
 * @(#) ResultVerification.jsp
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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	response.setContentType("text/xml");
%>

<ResultVerification>
	<c:forEach var="resultObject" items="${resultObject}">
	<request>
		<requestNo>
		<c:out value="${resultObject.requestNo}" />
		</requestNo>
	</request>	
	<rollNo>
		<rollNumber>
		<c:out value="${resultObject.rollNumber}" />
		</rollNumber>
	</rollNo>	
	<info>
		<message>
		<c:out value="${resultObject.extra}" />
		</message>
	</info>
	<detail>
		<requestNo>
		<c:out value="${resultObject.requestNo}" />
		</requestNo>
		<requestType>
		<c:out value="${resultObject.requestType}" />
		</requestType>
		<receiveDate>
		<c:out value="${resultObject.receiveDate}" />
		</receiveDate>
		<requester>
		<c:out value="${resultObject.requester}" />
		</requester>
		<compName>
		<c:out value="${resultObject.compName}" />
		</compName>
		<compAdd>
		<c:out value="${resultObject.compAdd}" />
		</compAdd>
		<processDate>
		<c:out value="${resultObject.processDate}" />
		</processDate>
		<refNo>
		<c:out value="${resultObject.refNo}" />
		</refNo>
		<refDate>
		<c:out value="${resultObject.refDate}" />
		</refDate>
		<processStatus>
		<c:out value="${resultObject.processStatus}" />
		</processStatus>
		<rollNumber>
		<c:out value="${resultObject.rollNumber}" />
		</rollNumber>
		<message>
		<c:out value="${resultObject.extra}" />
		</message>
	</detail>
	<requestType>
		<requestTypeCode>
		<c:out value="${resultObject.requestTypeCode}" />
		</requestTypeCode>
		<requestTypeName>
		<c:out value="${resultObject.requestTypeName}" />
		</requestTypeName>
	</requestType>
	</c:forEach>
</ResultVerification>

