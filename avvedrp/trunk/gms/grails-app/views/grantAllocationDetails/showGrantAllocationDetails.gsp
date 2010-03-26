

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>GrantAllocationDetails List</title>
    </head>
    <body>
        <div class="nav">
           <span class="menuButton"><a class="home" href="${createLinkTo(dir:'projectManager.gsp')}">Home</a></span> 
            <span class="menuButton"><g:link class="list" action="list" controller="grantAllocation">GrantAllocation List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New GrantAllocationDetails</g:link></span>
        </div>
        <div class="body">
            <h1>Grant Allocation Details List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                   	        
                   	        <g:sortableColumn property="grantAllocation.projectModules.projects.projectName" title="Project" />
                   	        
                   	        <g:sortableColumn property="grantAllocation.projectModules.projectName" title="Project Module" />
                   	        
                   	        <g:sortableColumn property="expenseHeadMaster.expenseHeadName" title="Expense Head Master" />
                        
                   	        <g:sortableColumn property="expenseAmount" title="Expense Amount" />
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${grantAllocationDetailsInstanceList}" status="i" var="grantAllocationDetailsInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${grantAllocationDetailsInstance.id}">${fieldValue(bean:grantAllocationDetailsInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:grantAllocationDetailsInstance, field:'grantAllocation.projectModules.projects.projectName')}</td>
                            
                            <td>${fieldValue(bean:grantAllocationDetailsInstance, field:'grantAllocation.projectModules.projectName')}</td>
                        
                            <td>${fieldValue(bean:grantAllocationDetailsInstance, field:'expenseHeadMaster.expenseHeadName')}</td>
                        
                            <td>${fieldValue(bean:grantAllocationDetailsInstance, field:'expenseAmount')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${GrantAllocationDetails.count()}" />
            </div>
        </div>
    </body>
</html>
