

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'approvalAuthority.label', default: 'ApprovalAuthority')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'approvalAuthority.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'approvalAuthority.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="email" title="${message(code: 'approvalAuthority.email.label', default: 'Email')}" />
                        
                            <g:sortableColumn property="approveLevel" title="${message(code: 'approvalAuthority.approveLevel.label', default: 'Approve Level')}" />
                        
                            <g:sortableColumn property="approveMandatory" title="${message(code: 'approvalAuthority.approveMandatory.label', default: 'Approve Mandatory')}" />
                        
                            <g:sortableColumn property="approveAll" title="${message(code: 'approvalAuthority.approveAll.label', default: 'Approve All')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${approvalAuthorityInstanceList}" status="i" var="approvalAuthorityInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${approvalAuthorityInstance.id}">${fieldValue(bean: approvalAuthorityInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: approvalAuthorityInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: approvalAuthorityInstance, field: "email")}</td>
                        
                            <td>${fieldValue(bean: approvalAuthorityInstance, field: "approveLevel")}</td>
                        
                            <td>${fieldValue(bean: approvalAuthorityInstance, field: "approveMandatory")}</td>
                        
                            <td>${fieldValue(bean: approvalAuthorityInstance, field: "approveAll")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${approvalAuthorityInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
