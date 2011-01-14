

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'preProposalCoPI.label', default: 'PreProposalCoPI')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'preProposalCoPI.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="coPiCommitments" title="${message(code: 'preProposalCoPI.coPiCommitments.label', default: 'Co Pi Commitments')}" />
                        
                            <th><g:message code="preProposalCoPI.faculty.label" default="Faculty" /></th>
                        
                            <th><g:message code="preProposalCoPI.preProposal.label" default="Pre Proposal" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${preProposalCoPIInstanceList}" status="i" var="preProposalCoPIInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${preProposalCoPIInstance.id}">${fieldValue(bean: preProposalCoPIInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: preProposalCoPIInstance, field: "coPiCommitments")}</td>
                        
                            <td>${fieldValue(bean: preProposalCoPIInstance, field: "faculty")}</td>
                        
                            <td>${fieldValue(bean: preProposalCoPIInstance, field: "preProposal")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${preProposalCoPIInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
