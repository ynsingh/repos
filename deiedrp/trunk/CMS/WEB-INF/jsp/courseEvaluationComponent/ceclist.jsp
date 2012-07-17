<%--
 * @(#) ceclist.jsp
 * Author :Mohd Amir
 * Date :21/3/2011
 * Version 1.0
 * Author :Ashish Mohan
 * Date :31/1/2012
 * Version 2.0
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
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	response.setContentType("text/xml");
%>


<ceclist>
<c:forEach var="ceclist" items="${ceclist}">
	<rec>
	<examdate>
	<c:out value="${ceclist.examdate}" />
	</examdate>
	<evaluationid>
	<c:out value="${ceclist.evaluationid}" />
	</evaluationid>
	<iddescription>
	<c:out value="${ceclist.iddescription}" />
	</iddescription>
	<groupid>
	<c:out value="${ceclist.groupid}" />
	</groupid>
	<groupName>
	<c:out value="${ceclist.groupName}" />
	</groupName>

	<rule>
	<c:out value="${ceclist.rule}" />
	</rule>
	<ruleName>
	<c:out value="${ceclist.ruleName}" />
	</ruleName>
	<orderinmarksheet>
	<c:out value="${ceclist.orderinmarksheet}" />
	</orderinmarksheet>
	<datetodisplay>
	<c:out value="${ceclist.datetodisplay}" />
	</datetodisplay>
	<datefromdisplay>
	<c:out value="${ceclist.datefromdisplay}" />
	</datefromdisplay>
	<maximummark>
	<c:out value="${ceclist.maximummark}" />
	</maximummark>

	</rec>
</c:forEach>

</ceclist>






