<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%response.setContentType("text/xml"); %>

<programList>
	<c:forEach var="program" items="${programList}">
		<program>
			<programName>			
				<c:out value="${program.programName}"></c:out>
			</programName>
			<programId>
				<c:out value = "${program.programId}"></c:out>
			</programId>
			
		</program>
	</c:forEach>
</programList>


