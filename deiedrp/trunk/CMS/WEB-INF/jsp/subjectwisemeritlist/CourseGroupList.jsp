<% response.setContentType("text/xml"); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<courseGroupList>
	<c:forEach var="courseGroupLis" items="${courseGroupList}">
		<courseGroup>
			<group><c:out value="${courseGroupLis.courseGroup}"/></group>			
		</courseGroup>
	</c:forEach>
</courseGroupList>