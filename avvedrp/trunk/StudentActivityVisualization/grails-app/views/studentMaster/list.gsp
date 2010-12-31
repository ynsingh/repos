

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>StudentMaster List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New StudentMaster</g:link></span>
        </div>
        <div class="body">
            <h1>StudentMaster List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="studentId" title="Student Id" />
                        
                   	        <g:sortableColumn property="studentName" title="Student Name" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${studentMasterInstanceList}" status="i" var="studentMasterInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${studentMasterInstance.id}">${fieldValue(bean:studentMasterInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:studentMasterInstance, field:'studentId')}</td>
                        
                            <td>${fieldValue(bean:studentMasterInstance, field:'studentName')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${StudentMaster.count()}" />
            </div>
        </div>
    </body>
</html>
