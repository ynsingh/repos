

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>ExpenseRequest List</title>
    </head>
    <body>
    <div class="wrapper">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New ExpenseRequest</g:link></span>
        </div>
        <div class="body">
            <h1>ExpenseRequest List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <th>Account Head</th>
                   	    
                   	        <g:sortableColumn property="requestedAmount" title="Requested Amount" />
                        
                   	        <g:sortableColumn property="requestedDate" title="Requested Date" />
                   	        
                   	        <th>Fund Availability</th>
                        
                   	        <th>Edit</th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${expenseRequestInstanceList}" status="i" var="expenseRequestInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${(i + 1)}</td>
                        
                            <td>${fieldValue(bean:expenseRequestInstance, field:'accountHead.name')}</td>
                        
                            <td>${fieldValue(bean:expenseRequestInstance, field:'requestedAmount')}</td>
                             
                            <td><g:formatDate format="dd-MM-yyyy" date="${expenseRequestInstance.requestedDate}"/></td>
                            <td>${fieldValue(bean:expenseRequestInstance, field:'fundAvailableYesNo')}</td>
                            
                            <td><g:link action="edit" id="${fieldValue(bean:expenseRequestInstance, field:'id')}">Edit</g:link></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            
        </div>
        </div>
    </body>
</html>
