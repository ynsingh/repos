

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'gmsSettings.label', default: 'GmsSettings')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'gmsSettings.id.label', default: 'Id')}" />
                                                                            
                            <g:sortableColumn property="name" title="${message(code: 'gmsSettings.name.label', default: 'Name')}" />
                            
                            <g:sortableColumn property="name" title="${message(code: 'gmsSettings.name.label', default: 'Value')}" />
                        
                        	<th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${gmsSettingsInstanceList}" status="i" var="gmsSettingsInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${i+1}</td>
                                                                            
                            <td>${fieldValue(bean: gmsSettingsInstance, field: "name")}</td>
                            
                            <td>${fieldValue(bean: gmsSettingsInstance, field: "value")}</td>
                            
                            <td><g:link action="show" id="${gmsSettingsInstance.id}">Edit</g:link></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${gmsSettingsInstanceTotal}" />
            </div>
        </div></div>
    </body>
</html>
