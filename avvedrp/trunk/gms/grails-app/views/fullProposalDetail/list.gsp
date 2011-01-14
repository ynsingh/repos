

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'fullProposalDetail.label', default: 'FullProposalDetail')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'fullProposalDetail.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="fileName" title="${message(code: 'fullProposalDetail.fileName.label', default: 'File Name')}" />
                        
                            <g:sortableColumn property="comments" title="${message(code: 'fullProposalDetail.comments.label', default: 'Comments')}" />
                        
                            <g:sortableColumn property="proposalSubmittedDate" title="${message(code: 'fullProposalDetail.proposalSubmittedDate.label', default: 'Proposal Submitted Date')}" />
                        
                            <g:sortableColumn property="activeYesNo" title="${message(code: 'fullProposalDetail.activeYesNo.label', default: 'Active Yes No')}" />
                        
                            <th><g:message code="fullProposalDetail.fullProposal.label" default="Full Proposal" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${fullProposalDetailInstanceList}" status="i" var="fullProposalDetailInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${fullProposalDetailInstance.id}">${fieldValue(bean: fullProposalDetailInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: fullProposalDetailInstance, field: "fileName")}</td>
                        
                            <td>${fieldValue(bean: fullProposalDetailInstance, field: "comments")}</td>
                        
                            <td><g:formatDate date="${fullProposalDetailInstance.proposalSubmittedDate}" /></td>
                        
                            <td>${fieldValue(bean: fullProposalDetailInstance, field: "activeYesNo")}</td>
                        
                            <td>${fieldValue(bean: fullProposalDetailInstance, field: "fullProposal")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${fullProposalDetailInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
