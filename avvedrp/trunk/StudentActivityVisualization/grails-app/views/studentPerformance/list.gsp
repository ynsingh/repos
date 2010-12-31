

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>StudentPerformance List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New StudentPerformance</g:link></span>
        </div>
        <div class="body">
            <h1>StudentPerformance List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="courseId" title="Course Id" />
                        
                   	        <g:sortableColumn property="scorePercent" title="Score Percent" />
                        
                   	        <g:sortableColumn property="studentId" title="Student Id" />
                        
                   	        <g:sortableColumn property="test_method" title="Testmethod" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${studentPerformanceInstanceList}" status="i" var="studentPerformanceInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${studentPerformanceInstance.id}">${fieldValue(bean:studentPerformanceInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:studentPerformanceInstance, field:'courseId')}</td>
                        
                            <td>${fieldValue(bean:studentPerformanceInstance, field:'scorePercent')}</td>
                        
                            <td>${fieldValue(bean:studentPerformanceInstance, field:'studentId')}</td>
                        
                            <td>${fieldValue(bean:studentPerformanceInstance, field:'test_method')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${StudentPerformance.count()}" />
            </div>
        </div>
    </body>
</html>
