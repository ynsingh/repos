

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>ProjectsPIMap List</title>
    </head>
    <body>
    <div class="wrapper">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Projects PI Map</g:link></span>
        </div>
        <div class="body">
            <h1>Projects PI Map List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <th>Investigator</th>
                   	    
                   	        <th>Projects</th>
                   	    
                   	        <g:sortableColumn property="role" title="Role" />
                        
                        	<th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${projectsPIMapInstanceList}" status="i" var="projectsPIMapInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${fieldValue(bean:projectsPIMapInstance, field:'id')}</td>
                        
                            <td>${fieldValue(bean:projectsPIMapInstance, field:'investigator.name')}</td>
                        
                            <td>${fieldValue(bean:projectsPIMapInstance, field:'projects.code')}</td>
                        
                            <td>${fieldValue(bean:projectsPIMapInstance, field:'role')}</td>
                            
                            <td><g:link action="edit" id="${projectsPIMapInstance.id}">Edit</g:link></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${ProjectsPIMap.count()}" />
            </div>
        </div>
        </div>
    </body>
</html>
