

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'approvalAuthorityDetail.label', default: 'ApprovalAuthorityDetail')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'approvalAuthorityDetail.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="remarks" title="${message(code: 'approvalAuthorityDetail.remarks.label', default: 'Remarks')}" />
                        
                            <g:sortableColumn property="activeYesNo" title="${message(code: 'approvalAuthorityDetail.activeYesNo.label', default: 'Active Yes No')}" />
                        
                            <th><g:message code="approvalAuthorityDetail.approvalAuthority.label" default="Approval Authority" /></th>
                        
                            <th><g:message code="approvalAuthorityDetail.faculty.label" default="Faculty" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${approvalAuthorityDetailInstanceList}" status="i" var="approvalAuthorityDetailInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${approvalAuthorityDetailInstance.id}">${fieldValue(bean: approvalAuthorityDetailInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: approvalAuthorityDetailInstance, field: "remarks")}</td>
                        
                            <td>${fieldValue(bean: approvalAuthorityDetailInstance, field: "activeYesNo")}</td>
                        
                            <td>${fieldValue(bean: approvalAuthorityDetailInstance, field: "approvalAuthority")}</td>
                        
                            <td>${fieldValue(bean: approvalAuthorityDetailInstance, field: "faculty")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${approvalAuthorityDetailInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
