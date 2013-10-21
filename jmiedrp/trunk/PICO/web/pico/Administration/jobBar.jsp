<%-- 
    Document   : jobBar
    Created on : 13 May, 2012, 1:17:40 PM
    Author     : sknaqvi
--%>
<div id="jobBar" style="max-height: 200px">
    <% out.println("<b>" + session.getAttribute("username") + "</b><br>");%>
    <% out.println("<b>" + session.getAttribute("isAdministrator") + "</b>");%>
    <p><b>Notifications</b></p>
     You have  <s:property value="jobs" />
     pending job(s). Click 
            <s:url action="showJobs" id="url"></s:url>
            <s:a href="%{url}">here</s:a>
     to retrieve
</div>
