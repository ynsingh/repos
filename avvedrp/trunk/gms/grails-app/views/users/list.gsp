

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Users List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Users</g:link></span>
        </div>
        <div class="body">
            <h1>Users List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                   	        
                   	        <g:sortableColumn property="userName" title="User Name" />
                   	        
                   	        <g:sortableColumn property="userPassword" title="User Password" />
                   	        
                   	        <g:sortableColumn property="name" title="Name" />
                   	        
                   	        <g:sortableColumn property="email" title="Email" />
                   	        
                   	        <g:sortableColumn property="phone" title="Phone" />
                   	        
                   	        <g:sortableColumn property="role" title="Role" />
                        
                   	        <g:sortableColumn property="activeYesNo" title="Active Yes No" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${usersInstanceList}" status="i" var="usersInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${usersInstance.id}">${fieldValue(bean:usersInstance, field:'id')}</g:link></td>
                            
                            <td>${fieldValue(bean:usersInstance, field:'userName')}</td>
                        
                            <td>${fieldValue(bean:usersInstance, field:'userPassword')}</td>
                            
                            <td>${fieldValue(bean:usersInstance, field:'name')}</td>
                        
                            <td>${fieldValue(bean:usersInstance, field:'email')}</td>
                            
                            <td>${fieldValue(bean:usersInstance, field:'phone')}</td>
                            
                            <td>${fieldValue(bean:usersInstance, field:'role')}</td>
                            
                            <td>${fieldValue(bean:usersInstance, field:'activeYesNo')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Users.count()}" />
            </div>
        </div>
    </body>
</html>
