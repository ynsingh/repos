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

<roles>
<c:forEach var="resultObject" items="${resultObject}">
	<role>
	<id>
	<c:out value="${resultObject.menuItemId}" />
	</id>
	<description>
	<c:out value="${resultObject.menuItemName}" />
	</description>
	<authority>
	<c:out value="${resultObject.secondaryAuthority}" />
	</authority>
	<role_id>
	<c:out value="${resultObject.roleId}" />
	</role_id>	
	<role_description>
	<c:out value="${resultObject.roleDescription}" />
	</role_description>	
	<user_id>
	<c:out value="${resultObject.userId}" />
	</user_id>	
	</role>
</c:forEach>
</roles>

