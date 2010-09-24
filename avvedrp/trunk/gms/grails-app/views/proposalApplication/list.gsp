

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.ProposalApplication.ProposalApplicationList.head"/></title>
    </head>
    <body>
        <div class="body">
            <h1><g:message code="default.ProposalApplication.ProposalApplicationList.head"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                        
                   	        <g:sortableColumn property="createdBy" title="${message(code: 'default.CreatedBy.label')}" />
                        
                   	        <g:sortableColumn property="createdDate" title="${message(code: 'default.CreatedDate.label')}" />
                        
                   	        <g:sortableColumn property="modifiedBy" title="${message(code: 'default.ModifiedBy.label')}" />
                        
                   	        <g:sortableColumn property="modifiedDate" title="${message(code: 'default.ModifiedDate.label')}" />
                        
                   	        <g:sortableColumn property="applicationSubmitDate" title="${message(code: 'default.ApplicationSubmitDate.label')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${proposalApplicationInstanceList}" status="i" var="proposalApplicationInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${proposalApplicationInstance.id}">${fieldValue(bean:proposalApplicationInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:proposalApplicationInstance, field:'createdBy')}</td>
                        
                            <td>${fieldValue(bean:proposalApplicationInstance, field:'createdDate')}</td>
                        
                            <td>${fieldValue(bean:proposalApplicationInstance, field:'modifiedBy')}</td>
                        
                            <td>${fieldValue(bean:proposalApplicationInstance, field:'modifiedDate')}</td>
                        
                            <td>${fieldValue(bean:proposalApplicationInstance, field:'applicationSubmitDate')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            
        </div>
    </body>
</html>
