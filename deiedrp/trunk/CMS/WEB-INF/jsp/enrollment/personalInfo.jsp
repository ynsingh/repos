<%--
* @(#) personalInfo.jsp
* Author :Mohd Amir
* Date :21/3/2011
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

<personalInfo>

<c:forEach var="record" items="${details}" >

<personalInfo>

<enrollmentNo><c:out value="${record.enrollmentNo}"/></enrollmentNo>
<studentId><c:out value="${record.studentId}"/></studentId>
<studentFirstName><c:out value="${record.studentFirstName}"/></studentFirstName>
<studentMiddleName><c:out value="${record.studentMiddleName}"/></studentMiddleName>
<studentLastName><c:out value="${record.studentLastName}"/></studentLastName>
<fatherFirstName><c:out value="${record.fatherFirstName}"/></fatherFirstName>
<fatherMiddleName><c:out value="${record.fatherMiddleName}"/></fatherMiddleName>
<fatherLastName><c:out value="${record.fatherLastName}"/></fatherLastName>
<motherFirstName><c:out value="${record.motherFirstName}"/></motherFirstName>
<motherMiddleName><c:out value="${record.motherMiddleName}"/></motherMiddleName>
<motherLastName><c:out value="${record.motherLastName}"/></motherLastName>
<categoryCode><c:out value="${record.categoryCode}"/></categoryCode>
<categoryName><c:out value="${record.categoryName}"/></categoryName>
<dob><c:out value="${record.dob}"/></dob>
<primaryMail><c:out value="${record.primaryMail}"/></primaryMail>
<secondaryMail><c:out value="${record.secondaryMail}"/></secondaryMail>
<guardianName><c:out value="${record.guardianName}"/></guardianName>
<nationality><c:out value="${record.nationality}"/></nationality>
<religion><c:out value="${record.religion}"/></religion>
<entityCode><c:out value="${record.entityCode}"/></entityCode>
<entity><c:out value="${record.entity}"/></entity>
<programCode><c:out value="${record.programCode}"/></programCode>
<program><c:out value="${record.program}"/></program>
<branchCode><c:out value="${record.branchCode}"/></branchCode>
<branch><c:out value="${record.branch}"/></branch>
<specializationCode><c:out value="${record.specializationCode}"/></specializationCode>
<specialization><c:out value="${record.specialization}"/></specialization>
<sessionStartDate><c:out value="${record.sessionStartDate}"/></sessionStartDate>
<sessionEndDate><c:out value="${record.sessionEndDate}"/></sessionEndDate>
<status><c:out value="${record.status}"/></status>
<facRegNo><c:out value="${record.facRegNo}"/></facRegNo>
<gender><c:out value="${record.gender}"/></gender>
<photoPath><c:out value="${record.photoPath}"/></photoPath>
<studentHindiName><c:out value="${record.hindiName}"/></studentHindiName>
<fatherHindiName><c:out value="${record.fatherHindiName}"/></fatherHindiName>
<motherHindiName><c:out value="${record.motherHindiName}"/></motherHindiName>

</personalInfo>

</c:forEach>

</personalInfo>