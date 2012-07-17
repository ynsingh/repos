<%--
* @(#) programswitchinfo.jsp
* Author :Arush Kumar
* Date :27/12/2012
* Version 1.0
* Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
* All Rights Reserved.
*
* Redistribution and use in source and binary forms, with or
* without modification, are permitted provided that the following
* conditions are met:
*
* Redistributions of source code must retain the above copyright
* notice, this list of conditions and the following disclaimer.
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
* DISCLAIMED. IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	response.setContentType("text/xml");
%>

<programswitch>



<c:forEach var="record" items="${switchstudents}" >

<switchinfo>
<%
System.out.println("in loop");
System.out.println("${record.fmentityId}") ;
 %>
<FmentityId><c:out value="${record.fmentityId}"/></FmentityId>
<FmprogramId><c:out value="${record.fmprogramId}"/></FmprogramId>
<FmbranchId><c:out value="${record.fmbranchId}"/></FmbranchId>
<FmspecializationId><c:out value="${record.fmspecializationId}"/></FmspecializationId>
<FmsemesterCode><c:out value="${record.fmsemesterCode}"/></FmsemesterCode>
<ToentityId><c:out value="${record.toentityId}"/></ToentityId>
<ToprogramId><c:out value="${record.toprogramId}"/></ToprogramId>
<TobranchId><c:out value="${record.tobranchId}"/></TobranchId>
<TospecializationId><c:out value="${record.tospecializationId}"/></TospecializationId>
<TosemesterCode><c:out value="${record.tosemesterCode}"/></TosemesterCode>

<FmentityIdName><c:out value="${record.fmentityIdName}"/></FmentityIdName>
<FmprogramIdName><c:out value="${record.fmprogramIdName}"/></FmprogramIdName>
<FmbranchIdName><c:out value="${record.fmbranchIdName}"/></FmbranchIdName>
<FmspecializationIdName><c:out value="${record.fmspecializationIdName}"/></FmspecializationIdName>
<FmsemesterCodeName><c:out value="${record.fmsemesterCodeName}"/></FmsemesterCodeName>
<ToentityIdName><c:out value="${record.toentityIdName}"/></ToentityIdName>
<ToprogramIdName><c:out value="${record.toprogramIdName}"/></ToprogramIdName>
<TobranchIdName><c:out value="${record.tobranchIdName}"/></TobranchIdName>
<TospecializationIdName><c:out value="${record.tospecializationIdName}"/></TospecializationIdName>
<TosemesterCodeName><c:out value="${record.tosemesterCodeName}"/></TosemesterCodeName>


</switchinfo>

</c:forEach>



</programswitch>
