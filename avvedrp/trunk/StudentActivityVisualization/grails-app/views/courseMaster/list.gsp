

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>CourseMaster List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New CourseMaster</g:link></span>
        </div>
        <div class="body">
            <h1>CourseMaster List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="courseDescription" title="Course Description" />
                        
                   	        <g:sortableColumn property="courseElement" title="Course Element" />
                        
                   	        <g:sortableColumn property="courseId" title="Course Id" />
                        
                   	        <g:sortableColumn property="durationExpected" title="Duration Expected" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${courseMasterInstanceList}" status="i" var="courseMasterInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${courseMasterInstance.id}">${fieldValue(bean:courseMasterInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:courseMasterInstance, field:'courseDescription')}</td>
                        
                            <td>${fieldValue(bean:courseMasterInstance, field:'courseElement')}</td>
                        
                            <td>${fieldValue(bean:courseMasterInstance, field:'courseId')}</td>
                        
                            <td>${fieldValue(bean:courseMasterInstance, field:'durationExpected')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${CourseMaster.count()}" />
            </div>
        </div>
    </body>
</html>
