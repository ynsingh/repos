

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Current Projects</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
        </div>
        <div class="body">
            <h1>Current Projects</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <th>SlNo</th>
                        
                   	        <g:sortableColumn property="code" title="Grant Code" />
                   	        
                   	        <g:sortableColumn property="projects.code" title="Project Code" />
                   	    
                   	        <g:sortableColumn property="party.code" title="Party Code" />
                        
                   	        <g:sortableColumn property="amountAllocated" title="Amount Allocated" />
                        
                   	        <th>Expenditure</th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${grantAllocationInstanceList}" status="i" var="grantAllocationInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${(i + 1)}</td>
                        
                            <td>${fieldValue(bean:grantAllocationInstance, field:'code')}</td>
                        
                            <td>${fieldValue(bean:grantAllocationInstance, field:'projects.code')}</td>
                        
                            <td>${fieldValue(bean:grantAllocationInstance, field:'party.code')}</td>
                            
                            <td><g:formatNumber number="${grantAllocationInstance.amountAllocated}" format="###,##0.00" /></td>
                            
                            <td><g:link  action="create" controller ="grantExpense" id="${grantAllocationInstance.id}">Expenditure</g:link></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
           
        </div>
    </body>
</html>
