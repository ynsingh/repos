

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>ProjectTracking List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New ProjectTracking</g:link></span>
        </div>
        <div class="body">
            <h1>ProjectTracking List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="remarks" title="Remarks" />
                        
                   	        <g:sortableColumn property="createdBy" title="Created By" />
                        
                   	        <g:sortableColumn property="createdDate" title="Created Date" />
                        
                   	        <g:sortableColumn property="dateOfTracking" title="Date Of Tracking" />
                        
                   	        <g:sortableColumn property="modifiedBy" title="Modified By" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${projectTrackingInstanceList}" status="i" var="projectTrackingInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${projectTrackingInstance.id}">${fieldValue(bean:projectTrackingInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:projectTrackingInstance, field:'remarks')}</td>
                        
                            <td>${fieldValue(bean:projectTrackingInstance, field:'createdBy')}</td>
                        
                            <td>${fieldValue(bean:projectTrackingInstance, field:'createdDate')}</td>
                        
                            <td>${fieldValue(bean:projectTrackingInstance, field:'dateOfTracking')}</td>
                        
                            <td>${fieldValue(bean:projectTrackingInstance, field:'modifiedBy')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${ProjectTracking.count()}" />
            </div>
        </div>
    </body>
</html>
