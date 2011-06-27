

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'university.label', default: 'University')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'university.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="univ_name" title="${message(code: 'university.univ_name.label', default: 'Univname')}" />
                        
                            <g:sortableColumn property="univ_address" title="${message(code: 'university.univ_address.label', default: 'Univaddress')}" />
                        
                            <g:sortableColumn property="univ_email" title="${message(code: 'university.univ_email.label', default: 'Univemail')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${universityInstanceList}" status="i" var="universityInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${universityInstance.id}">${fieldValue(bean: universityInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: universityInstance, field: "univ_name")}</td>
                        
                            <td>${fieldValue(bean: universityInstance, field: "univ_address")}</td>
                        
                            <td>${fieldValue(bean: universityInstance, field: "univ_email")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${universityInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
