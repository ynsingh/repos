

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>ProjectDepartmentMap List</title>
    </head>
    <body>
    <div class="wrapper">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New ProjectDepartmentMap</g:link></span>
        </div>
        <div class="body">
            <h1>ProjectDepartmentMap List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                            <th>Projects</th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${projectDepartmentMapInstanceList}" status="i" var="projectDepartmentMapInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${projectDepartmentMapInstance.id}">${fieldValue(bean:projectDepartmentMapInstance, field:'id')}</g:link></td>
                      
                            <td>${fieldValue(bean:projectDepartmentMapInstance, field:'projects.code')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${ProjectDepartmentMap.count()}" />
            </div>
        </div>
        </div>
    </body>
</html>
