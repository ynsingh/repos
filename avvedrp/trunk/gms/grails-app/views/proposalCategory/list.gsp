

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'proposalCategory.label', default: 'ProposalCategory')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'proposalCategory.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'proposalCategory.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="remarks" title="${message(code: 'proposalCategory.remarks.label', default: 'Remarks')}" />
                        
                            <g:sortableColumn property="activeYesNo" title="${message(code: 'proposalCategory.activeYesNo.label', default: 'Active Yes No')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${proposalCategoryInstanceList}" status="i" var="proposalCategoryInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${proposalCategoryInstance.id}">${fieldValue(bean: proposalCategoryInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: proposalCategoryInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: proposalCategoryInstance, field: "remarks")}</td>
                        
                            <td>${fieldValue(bean: proposalCategoryInstance, field: "activeYesNo")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${proposalCategoryInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
