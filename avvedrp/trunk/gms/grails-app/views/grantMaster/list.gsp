

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>GrantMaster List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New GrantMaster</g:link></span>
        </div>
        <div class="body">
            <h1>GrantMaster List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="code" title="Code" />
                   	        
                   	        <g:sortableColumn property="title" title="Title" />
                   	        
                   	        <g:sortableColumn property="manager" title="Manager" />
                   	        
                   	        <g:sortableColumn property="description" title="Description" />
                   	        
                   	        <g:sortableColumn property="activeYesNo" title="Active Yes No" />
                   	        
                   	        <g:sortableColumn property="id" title="Grant Details" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${grantMasterInstanceList}" status="i" var="grantMasterInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${grantMasterInstance.id}">${fieldValue(bean:grantMasterInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:grantMasterInstance, field:'code')}</td>
                        
                            <td>${fieldValue(bean:grantMasterInstance, field:'title')}</td>
                        
                            <td>${fieldValue(bean:grantMasterInstance, field:'manager.userRealName')}</td>
                        
                            <td>${fieldValue(bean:grantMasterInstance, field:'description')}</td>
                            
                            <td>${fieldValue(bean:grantMasterInstance, field:'activeYesNo')}</td>
                            
                            <td><g:link  action="showGrantDetails" controller ="grantDetails" id="${grantMasterInstance.id}">Grant Details</g:link></td>
                            
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${GrantMaster.count()}" />
            </div>
        </div>
    </body>
</html>
