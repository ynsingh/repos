

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>ExpenseHeadMaster List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New ExpenseHeadMaster</g:link></span>
        </div>
        <div class="body">
            <h1>ExpenseHeadMaster List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                   	        
                   	        <g:sortableColumn property="expenseHeadName" title="Expense Head Name" />
                   	        
                   	        <g:sortableColumn property="remarks" title="Remarks" />
                        
                   	        <g:sortableColumn property="activeYesNo" title="Active Yes No" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${expenseHeadMasterInstanceList}" status="i" var="expenseHeadMasterInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${expenseHeadMasterInstance.id}">${fieldValue(bean:expenseHeadMasterInstance, field:'id')}</g:link></td>
                        
                        	<td>${fieldValue(bean:expenseHeadMasterInstance, field:'expenseHeadName')}</td>
                        	
                        	<td>${fieldValue(bean:expenseHeadMasterInstance, field:'remarks')}</td>
                        	
                            <td>${fieldValue(bean:expenseHeadMasterInstance, field:'activeYesNo')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${ExpenseHeadMaster.count()}" />
            </div>
        </div>
    </body>
</html>
