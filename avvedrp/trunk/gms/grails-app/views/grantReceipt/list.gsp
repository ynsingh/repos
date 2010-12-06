

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>GrantReceipt List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New GrantReceipt</g:link></span>
        </div>
        <div class="body">
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <th>Grant Allocation</th>
                   	    
                   	        <g:sortableColumn property="amount" title="Amount" />
                        
                   	        <g:sortableColumn property="dateOfReceipt" title="Date Of Receipt" />
                        
                   	        <th>Grant Allocation Split</th>
                   	    
                   	        <g:sortableColumn property="modifiedBy" title="Modified By" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${grantReceiptInstanceList}" status="i" var="grantReceiptInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${grantReceiptInstance.id}">${fieldValue(bean:grantReceiptInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:grantReceiptInstance, field:'grantAllocation')}</td>
                        
                            <td>${fieldValue(bean:grantReceiptInstance, field:'amount')}</td>
                        
                            <td>${fieldValue(bean:grantReceiptInstance, field:'dateOfReceipt')}</td>
                        
                            <td>${fieldValue(bean:grantReceiptInstance, field:'grantAllocationSplit')}</td>
                        
                            <td>${fieldValue(bean:grantReceiptInstance, field:'modifiedBy')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${GrantReceipt.count()}" />
            </div>
        </div>
    </body>
</html>
