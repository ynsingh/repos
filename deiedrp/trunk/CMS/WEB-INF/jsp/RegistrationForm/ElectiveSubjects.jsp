<!--
/*
 * @(#) ElectiveSubjects.jsp
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

<ElectiveSubjects>

<c:forEach var="ElectiveSubjects" items="${electiveSubjects}">
<elective>
<course_code><c:out value="${ElectiveSubjects.courseCode}"/></course_code>
<course_name><c:out value="${ElectiveSubjects.courseName}"/></course_name>
<credits><c:out value="${ElectiveSubjects.credits}"/></credits>
<course_type><c:out value="${ElectiveSubjects.courseType}"/></course_type>
<course_group><c:out value="${ElectiveSubjects.courseGroup}"/></course_group>
<minimum_credits><c:out value="${ElectiveSubjects.minimumCourses}"/></minimum_credits>
<maximum_credits><c:out value="${ElectiveSubjects.maximumCourses}"/></maximum_credits>
<program_course_key><c:out value="${ElectiveSubjects.programCourseKey}"/></program_course_key>
<course_classification><c:out value="${ElectiveSubjects.courseClassification}"/></course_classification>
<course_classification_name><c:out value="${ElectiveSubjects.courseClassificationName}"/></course_classification_name>
<course_group_code><c:out value="${ElectiveSubjects.courseGroupCode}"/></course_group_code>
</elective>

</c:forEach>
</ElectiveSubjects>