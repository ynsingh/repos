

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'graphs.label', default: 'Graphs')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'graphs.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="graph_type" title="${message(code: 'graphs.graph_type.label', default: 'Graphtype')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${graphsInstanceList}" status="i" var="graphsInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${graphsInstance.id}">${fieldValue(bean: graphsInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: graphsInstance, field: "graph_type")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${graphsInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
