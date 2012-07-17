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

<basicDetails>
<c:forEach var="resultObject" items="${resultObject}">
	<Details>
	
	<ruleId>
	<c:out value="${resultObject.ruleId}" />
	</ruleId>
	
	<ruleCodeOne>
	<c:out value="${resultObject.ruleCode1}" />
	</ruleCodeOne>

	<ruleCodeTwo>
	<c:out value="${resultObject.ruleCode2}" />
	</ruleCodeTwo>
	
	<ruleCodeThree>
	<c:out value="${resultObject.ruleCode3}" />
	</ruleCodeThree>

	<ruleCodeFour>
	<c:out value="${resultObject.ruleCode4}" />
	</ruleCodeFour>
	
	<ruleCodeFive>
	<c:out value="${resultObject.ruleCode5}" />
	</ruleCodeFive>

	<ruleCodeSix>
	<c:out value="${resultObject.ruleCode6}" />
	</ruleCodeSix>
	
	<ruleFormula>
	<c:out value="${resultObject.ruleFormula}" />
	</ruleFormula>
	
	<ruleDescthree>
	<c:out value="${resultObject.ruleDesc3}" />
	</ruleDescthree>
	
	<ruleDescFour>
	<c:out value="${resultObject.ruleDesc4}" />
	</ruleDescFour>
	
	<ruleDescFive>
	<c:out value="${resultObject.ruleDesc5}" />
	</ruleDescFive>

	<ruleDescSix>
	<c:out value="${resultObject.ruleDesc6}" />
	</ruleDescSix>	
	
	</Details>
</c:forEach>
</basicDetails>
