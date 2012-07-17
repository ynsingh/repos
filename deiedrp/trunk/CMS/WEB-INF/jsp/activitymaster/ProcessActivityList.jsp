
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% response.setContentType("text/xml"); %>
<activityMasterDetails>
	<c:forEach var="processActivity" items="${processActivityList}">
		<activityMaster>
			
			<activityCode><c:out value="${processActivity.activityId}"/></activityCode>
			<activityName><c:out value="${processActivity.componentDescription}"/></activityName>
			<activitySequence><c:out value="${processActivity.activitySequence}"/></activitySequence>
			<status><c:out value="${processActivity.processStatus}"/></status>
		</activityMaster>
	</c:forEach>
</activityMasterDetails>