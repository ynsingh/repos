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


<universityDetails>
<c:forEach var="resultObject" items="${resultObject}">

<Details>

<universityCode>
	<c:out value="${resultObject.universityCode}" />
</universityCode>

<universityName>
	<c:out value="${resultObject.universityName}" />
</universityName>

<sessionStartDate>
	<c:out value="${resultObject.sessionStartDate}" />
</sessionStartDate>

<sessionEndDate>
	<c:out value="${resultObject.sessionEndDate}" />
</sessionEndDate>

<universityAddress>
	<c:out value="${resultObject.universityAddress}" />
</universityAddress>

<universityCity>
	<c:out value="${resultObject.universityCity}" />
</universityCity>

<universityState>
	<c:out value="${resultObject.universityState}" />
</universityState>

<universityPhoneNumber>
	<c:out value="${resultObject.universityPhoneNumber}" />
</universityPhoneNumber>

<otherPhoneNumber>
	<c:out value="${resultObject.universityOtherPhoneNumber}" />
</otherPhoneNumber>

<faxNumber>
	<c:out value="${resultObject.universityFaxNumber}" />
</faxNumber>

<pinCode>
	<c:out value="${resultObject.universityPincode}" />
</pinCode>

<status>
	<c:out value="${resultObject.currentStatus}" />
</status>

<nickName>
	<c:out value="${resultObject.nickName}" />
</nickName>

<countryName>
	<c:out value="${resultObject.countryName}" />
</countryName>

</Details>

</c:forEach>
</universityDetails>