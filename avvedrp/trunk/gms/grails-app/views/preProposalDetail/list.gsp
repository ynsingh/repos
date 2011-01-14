

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'preProposalDetail.label', default: 'PreProposalDetail')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'preProposalDetail.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="field" title="${message(code: 'preProposalDetail.field.label', default: 'Field')}" />
                        
                            <g:sortableColumn property="value" title="${message(code: 'preProposalDetail.value.label', default: 'Value')}" />
                        
                            <g:sortableColumn property="preProposalSubmittedDate" title="${message(code: 'preProposalDetail.preProposalSubmittedDate.label', default: 'Pre Proposal Submitted Date')}" />
                        
                            <g:sortableColumn property="activeYesNo" title="${message(code: 'preProposalDetail.activeYesNo.label', default: 'Active Yes No')}" />
                        
                            <th><g:message code="preProposalDetail.preProposal.label" default="Pre Proposal" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${preProposalDetailInstanceList}" status="i" var="preProposalDetailInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${preProposalDetailInstance.id}">${fieldValue(bean: preProposalDetailInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: preProposalDetailInstance, field: "field")}</td>
                        
                            <td>${fieldValue(bean: preProposalDetailInstance, field: "value")}</td>
                        
                            <td><g:formatDate date="${preProposalDetailInstance.preProposalSubmittedDate}" /></td>
                        
                            <td>${fieldValue(bean: preProposalDetailInstance, field: "activeYesNo")}</td>
                        
                            <td>${fieldValue(bean: preProposalDetailInstance, field: "preProposal")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${preProposalDetailInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
