

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>GrantAllocation List</title>
    </head>
    <body>
        <div class="nav">
           <span class="menuButton"><a class="home" href="${createLinkTo(dir:'projectManager.gsp')}">Home</a></span> 
            <span class="menuButton"><g:link class="list" action="listApprovedGrantRequest">GrantApproval List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New GrantAllocation</g:link></span>
        </div>
        <div class="body">
            <h1>Grant Allocation List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                   	        
                   	        <g:sortableColumn property="projectModules.projects.projectName" title="Project" />
                   	        
                   	        <g:sortableColumn property="projectModules.projectName" title="Project Module" />
                        
                   	        <g:sortableColumn property="installmentNo" title="Installment No" />
                        
                   	        <g:sortableColumn property="installmentAmount" title="Installment Amount" />
                        
                   	        <g:sortableColumn property="description" title="Description" />
                   	        
                   	        <g:sortableColumn property="id" title="Grant Allocation Details" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${grantAllocationInstanceList}" status="i" var="grantAllocationInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${grantAllocationInstance.id}">${fieldValue(bean:grantAllocationInstance, field:'id')}</g:link></td>
                            
                            <td>${fieldValue(bean:grantAllocationInstance, field:'projectModules.projects.projectName')}</td>
                        
                            <td>${fieldValue(bean:grantAllocationInstance, field:'projectModules.projectName')}</td>
                        
                            <td>${fieldValue(bean:grantAllocationInstance, field:'installmentNo')}</td>
                            
                            <td>${fieldValue(bean:grantAllocationInstance, field:'installmentAmount')}</td>
                        
                            <td>${fieldValue(bean:grantAllocationInstance, field:'description')}</td>
                            
                            <td><g:link  action="showGrantAllocationDetails" controller ="grantAllocationDetails" id="${grantAllocationInstance.id}">Grant Allocation Details</g:link></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${GrantAllocation.count()}" />
            </div>
        </div>
    </body>
</html>
