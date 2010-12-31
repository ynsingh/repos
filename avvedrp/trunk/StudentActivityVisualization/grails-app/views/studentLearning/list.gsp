

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>StudentLearning List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New StudentLearning</g:link></span>
        </div>
        <div class="body">
            <h1>StudentLearning List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="courseElement" title="Course Element" />
                        
                   	        <g:sortableColumn property="courseId" title="Course Id" />
                        
                   	        <g:sortableColumn property="durationActual" title="Duration Actual" />
                        
                   	        <g:sortableColumn property="studentId" title="Student Id" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${studentLearningInstanceList}" status="i" var="studentLearningInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${studentLearningInstance.id}">${fieldValue(bean:studentLearningInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:studentLearningInstance, field:'courseElement')}</td>
                        
                            <td>${fieldValue(bean:studentLearningInstance, field:'courseId')}</td>
                        
                            <td>${fieldValue(bean:studentLearningInstance, field:'durationActual')}</td>
                        
                            <td>${fieldValue(bean:studentLearningInstance, field:'studentId')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${StudentLearning.count()}" />
            </div>
        </div>
    </body>
</html>
