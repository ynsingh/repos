

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Proposal List</title>
    </head>
    <body>
        <div class="wrapper">
        <div class="body">
            <h1>Proposal List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                         <input type="hidden" id="notificationId" name="notificationId" value="${fieldValue(bean:proposalInstance, field:'notification.id')}"/>
                   	       <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}"/>
                   	        
                   	        <th><g:message code="default.TitleOfTheResearchProject.label"/></th>
                   	        <th><g:message code="default.SubmittedBy.label"/></th>
                        	<th><g:message code="default.Organisation.label"/></th>
                        	
                   	        <th><g:message code="default.View.label"/></th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${proposalInstanceList}" status="i" var="proposalApplicationInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${i+1}</td>
                            <% def proposalApplicationExtProjectInstance = ProposalApplicationExt.find("from ProposalApplicationExt PE where PE.field='TitleOfTheResearchProject_2' and PE.proposalApplication.id="+proposalApplicationInstance?.id)%>
                             <td>${proposalApplicationExtProjectInstance?.value}</td>
                             <% def proposalApplicationExtInstance = ProposalApplicationExt.find("from ProposalApplicationExt PE where PE.field='FirstName_1' and PE.proposalApplication.id="+proposalApplicationInstance?.id)%>
                             <td>${proposalApplicationExtInstance?.value}</td>
                        	<% def proposalApplicationExtOrgInstance = ProposalApplicationExt.find("from ProposalApplicationExt PE where PE.field='Organisation_4' and PE.proposalApplication.id="+proposalApplicationInstance?.id)%>
                             <td>${proposalApplicationExtOrgInstance?.value}</td>
                             
                            <td><g:link action="proposalApplicationReview" controller='proposalApplication' id="${proposalApplicationInstance.id}"><g:message code="default.View.label"/></g:link></td>
                        
                        
                        
                           
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            
            </div>
        </div>
    </body>
</html>
