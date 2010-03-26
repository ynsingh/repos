

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>GrantDetails List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New GrantDetails</g:link></span>
        </div>
        <div class="body">
            <h1>GrantDetails List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="grantMaster.code" title="Grant Code" />
                        
                   	        <g:sortableColumn property="financialYear" title="Financial Year" />
                   	        
                   	        <g:sortableColumn property="budgetStartDate" title="Budget Start Date" />
                        
                   	        <g:sortableColumn property="budgetEndDate" title="Budget End Date" />
                        
                   	        <g:sortableColumn property="grantAmount" title="Grant Amount" />
                        
                   	        <g:sortableColumn property="description" title="Description" />
                   	        
                   	        <g:sortableColumn property="activeYesNo" title="Active Yes No" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${grantDetailsInstanceList}" status="i" var="grantDetailsInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${grantDetailsInstance.id}">${fieldValue(bean:grantDetailsInstance, field:'id')}</g:link></td>
                        
                        	<td>${fieldValue(bean:grantDetailsInstance, field:'grantMaster.code')}</td>
                        	
                        	<td>${fieldValue(bean:grantDetailsInstance, field:'financialYear')}</td>
                        	
                            <td>${fieldValue(bean:grantDetailsInstance, field:'budgetStartDate')}</td>
                        
                            <td>${fieldValue(bean:grantDetailsInstance, field:'budgetEndDate')}</td>
                        
                            <td>${fieldValue(bean:grantDetailsInstance, field:'grantAmount')}</td>
                        
                            <td>${fieldValue(bean:grantDetailsInstance, field:'description')}</td>
                            
                            <td>${fieldValue(bean:grantDetailsInstance, field:'activeYesNo')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${GrantDetails.count()}" />
            </div>
        </div>
    </body>
</html>
