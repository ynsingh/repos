<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	response.setContentType("text/xml");
%>

<details>
<c:forEach var="resultObject" items="${resultObject}">
	<details>
		<instituteName>
		<c:out value="${resultObject.instituteName}" />
		</instituteName>
	
		<address>
		<c:out value="${resultObject.address}" />
		</address>
		
		<city>
		<c:out value="${resultObject.city}" />
		</city>
		
		<state>
		<c:out value="${resultObject.state}" />
		</state>
		
		<country>
		<c:out value="${resultObject.country}" />
		</country>
		
		<name>
		<c:out value="${resultObject.employeeFirstName}" />
		</name>
		
		<adminEmail>
		<c:out value="${resultObject.primaryEmailId}" />
		</adminEmail>
		
		<requestStatus>
		<c:out value="${resultObject.status}" />
		</requestStatus>
		
		<instituteNickName>
			<c:out value="${resultObject.instituteNickName}" />
		</instituteNickName>	
	</details>
</c:forEach>
</details>