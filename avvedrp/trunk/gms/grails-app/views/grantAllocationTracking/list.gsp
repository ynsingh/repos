

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>GrantAllocationTracking List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New GrantAllocationTracking</g:link></span>
        </div>
        <div class="body">
            <h1>GrantAllocationTracking List</h1>
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
                        
                   	        <th>Grant Allocation</th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${grantAllocationTrackingInstanceList}" status="i" var="grantAllocationTrackingInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${grantAllocationTrackingInstance.id}">${fieldValue(bean:grantAllocationTrackingInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:grantAllocationTrackingInstance, field:'remarks')}</td>
                        
                            <td>${fieldValue(bean:grantAllocationTrackingInstance, field:'createdBy')}</td>
                        
                            <td>${fieldValue(bean:grantAllocationTrackingInstance, field:'createdDate')}</td>
                        
                            <td>${fieldValue(bean:grantAllocationTrackingInstance, field:'dateOfTracking')}</td>
                        
                            <td>${fieldValue(bean:grantAllocationTrackingInstance, field:'grantAllocation')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${GrantAllocationTracking.count()}" />
            </div>
        </div>
    </body>
</html>
