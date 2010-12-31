

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>StudentSessionLog List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New StudentSessionLog</g:link></span>
        </div>
        <div class="body">
            <h1>StudentSessionLog List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="loginDateTime" title="Login Date Time" />
                        
                   	        <g:sortableColumn property="logoffDateTime" title="Logoff Date Time" />
                        
                   	        <g:sortableColumn property="studentId" title="Student Id" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${studentSessionLogInstanceList}" status="i" var="studentSessionLogInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${studentSessionLogInstance.id}">${fieldValue(bean:studentSessionLogInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:studentSessionLogInstance, field:'loginDateTime')}</td>
                        
                            <td>${fieldValue(bean:studentSessionLogInstance, field:'logoffDateTime')}</td>
                        
                            <td>${fieldValue(bean:studentSessionLogInstance, field:'studentId')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${StudentSessionLog.count()}" />
            </div>
        </div>
    </body>
</html>
