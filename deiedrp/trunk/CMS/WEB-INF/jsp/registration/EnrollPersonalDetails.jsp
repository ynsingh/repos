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

<PersonalDetails>
	<c:forEach var="personalDetails" items="${personalDetails}">
		<details>
			<enrollmentNo><c:out value="${personalDetails.enrollmentNo}" /></enrollmentNo>
			<studentId><c:out value="${personalDetails.studentId}" /></studentId>
			<studentName><c:out value="${personalDetails.studentName}" /></studentName>
			<fatherName><c:out value="${personalDetails.fatherName}" /></fatherName>
			<motherName><c:out value="${personalDetails.motherName}" /></motherName>
			<primaryMail><c:out value="${personalDetails.primaryMail}" /></primaryMail>
			<dob><c:out value="${personalDetails.dob}" /></dob>
			<nationality><c:out value="${personalDetails.nationality}" /></nationality>
			<religion><c:out value="${personalDetails.religion}"/></religion>
			<categoryName><c:out value="${personalDetails.categoryName}" /></categoryName>
			<entity><c:out value="${personalDetails.entity}" /></entity>
			<branch><c:out value="${personalDetails.branch}" /></branch>
			<specialization><c:out value="${personalDetails.specialization}" /></specialization>			
			<address><c:out value="${personalDetails.address}" /></address>
			<corAddress><c:out value="${personalDetails.corAddress}"/></corAddress>
			<city><c:out value="${personalDetails.city}" /></city>	
			<corCity><c:out value="${personalDetails.corCity}"></c:out></corCity>		
			<state><c:out value="${personalDetails.state}"></c:out></state>
			<corState><c:out value="${personalDetails.corState}"/></corState>			
			<pincode><c:out value="${personalDetails.pinCode}" /></pincode>
			<corPincode><c:out value="${personalDetails.corPincode}" /></corPincode>				
		</details>
	</c:forEach>
</PersonalDetails>