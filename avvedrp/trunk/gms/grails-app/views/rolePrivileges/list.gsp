

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>RolePrivileges List</title>
    </head>
    <body>
    <div class="wrapper">
	<g:menuList />
        <div class="body">
            <h1>RolePrivileges List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="description" title="Description" />
							                       
                   	        <g:sortableColumn property="controllerName" title="controllerName" />
                        
                   	        <g:sortableColumn property="actionName" title="actionName" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${rolePrivilegesInstanceList}" status="i" var="rolePrivilegesInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${rolePrivilegesInstance.id}">${fieldValue(bean:rolePrivilegesInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:rolePrivilegesInstance, field:'description')}</td>
                        
                            <td>${fieldValue(bean:rolePrivilegesInstance, field:'controllerName')}</td>
                                                    
                            <td>${fieldValue(bean:rolePrivilegesInstance, field:'actionName')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${RolePrivileges.count()}" />
            </div>
        </div>
    </body>
</html>
