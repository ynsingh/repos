

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>UserMap List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New UserMap</g:link></span>
        </div>
        <div class="body">
            <h1>UserMap List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <th>Account Heads</th>
                   	    
                   	        <g:sortableColumn property="createdDate" title="Created Date" />
                        
                   	        <g:sortableColumn property="modifiedDate" title="Modified Date" />
                        
                   	        <g:sortableColumn property="createdBy" title="Created By" />
                        
                   	        <g:sortableColumn property="modifiedBy" title="Modified By" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${userMapInstanceList}" status="i" var="userMapInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${userMapInstance.id}">${fieldValue(bean:userMapInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:userMapInstance, field:'accountHeads')}</td>
                        
                            <td>${fieldValue(bean:userMapInstance, field:'createdDate')}</td>
                        
                            <td>${fieldValue(bean:userMapInstance, field:'modifiedDate')}</td>
                        
                            <td>${fieldValue(bean:userMapInstance, field:'createdBy')}</td>
                        
                            <td>${fieldValue(bean:userMapInstance, field:'modifiedBy')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${UserMap.count()}" />
            </div>
        </div>
    </body>
</html>
