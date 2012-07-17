<%--
* @(#) personalInfo.jsp
* Author :Mohd Amir* Date :21/3/2011
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

<personalInfos>

<personalInfo>

<parentEntity><c:out value="${details.parentEntity}"/></parentEntity>
<studentId><c:out value="${details.studentId}"/></studentId>
<studentFirstName><c:out value="${details.studentFirstName}"/></studentFirstName>
<studentMiddleName><c:out value="${details.studentMiddleName}"/></studentMiddleName>
<studentLastName><c:out value="${details.studentLastName}"/></studentLastName>
<fatherFirstName><c:out value="${details.fatherFirstName}"/></fatherFirstName>
<fatherMiddleName><c:out value="${details.fatherMiddleName}"/></fatherMiddleName>
<fatherLastName><c:out value="${details.fatherLastName}"/></fatherLastName>
<motherFirstName><c:out value="${details.motherFirstName}"/></motherFirstName>
<motherMiddleName><c:out value="${details.motherMiddleName}"/></motherMiddleName>
<motherLastName><c:out value="${details.motherLastName}"/></motherLastName>
<categoryName><c:out value="${details.categoryName}"/></categoryName>
<primaryMail><c:out value="${details.primaryEmailId}"/></primaryMail>
<secondaryMail><c:out value="${details.secondaryEmailId}"/></secondaryMail>
<dateOfBirth><c:out value="${details.dateOfBirth}"/></dateOfBirth>
<registeredInSession><c:out value="${details.registeredInSession}"/></registeredInSession>
<status><c:out value="${details.status}"/></status>
<gender><c:out value="${details.gender}"/></gender>
<hindiName><c:out value="${details.hindiName}"/></hindiName>
<fatherHindiName><c:out value="${details.fatherHindiName}"/></fatherHindiName>
<motherHindiName><c:out value="${details.motherHindiName}"/></motherHindiName>
<religion><c:out value="${details.religion}"/></religion>
<nationality><c:out value="${details.nationality}"/></nationality>
<guardian><c:out value="${details.guardian}"/></guardian>
<path><c:out value="${details.path}"/></path>

<addressInfo>

<c:forEach var="record" items="${details.studentAddress}" >

<addresses>

<addressKey><c:out value="${record.key}"/></addressKey>
<address><c:out value="${record.value.addressLineOne}"/></address>
<city><c:out value="${record.value.city}"/></city>
<state><c:out value="${record.value.state}"/></state>
<pinCode><c:out value="${record.value.pinCode}"/></pinCode>
<officePhone><c:out value="${record.value.officePhone}"/></officePhone>
<homePhone><c:out value="${record.value.homePhone}"/></homePhone>
<otherPhone><c:out value="${record.value.otherPhone}"/></otherPhone>
<fax><c:out value="${record.value.fax}"/></fax>

</addresses>

</c:forEach>

</addressInfo>

</personalInfo>

</personalInfos>