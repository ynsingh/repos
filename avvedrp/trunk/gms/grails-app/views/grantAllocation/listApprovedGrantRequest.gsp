

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>GrantApproval List</title>
    </head>
    <body>
        <div class="nav">
           <span class="menuButton"><a class="home" href="${createLinkTo(dir:'projectManager.gsp')}">Home</a></span> 
            
        </div>
        <div class="body">
            <h1>Grant Approval List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                   	                              
                   	        <g:sortableColumn property="grantRequest.grantDetails.grantMaster.code" title="Grant Code" />
                        
                   	        <g:sortableColumn property="grantRequest.grantDetails.grantMaster.title" title="Grant Title" />
                   	        
                   	        <g:sortableColumn property="grantRequest.grantDetails.financialYear" title="Financial Year" />
                   	        
                   	        <g:sortableColumn property="grantRequest.projects.projectName" title="Project Name" />
                        
                   	        <g:sortableColumn property="approvedAmount" title="Approved Amount" />
                   	    
                   	        <g:sortableColumn property="approvedDate" title="Approved Date" />
                        
                   	        <g:sortableColumn property="approvedBy.userRealName" title="Approved By" />
                   	        
                   	        <th>Allocation Details</th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${grantApprovalInstanceList}" status="i" var="grantApprovalInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                        	<td><g:link action="show" id="${grantApprovalInstance.id}">${fieldValue(bean:grantApprovalInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:grantApprovalInstance, field:'grantRequest.grantDetails.grantMaster.code')}</td>
                        
                            <td>${fieldValue(bean:grantApprovalInstance, field:'grantRequest.grantDetails.grantMaster.title')}</td>
                            
                            <td>${fieldValue(bean:grantApprovalInstance, field:'grantRequest.grantDetails.financialYear')}</td>
                            
                            <td>${fieldValue(bean:grantApprovalInstance, field:'grantRequest.projects.projectName')}</td>
                        
                            <td>${fieldValue(bean:grantApprovalInstance, field:'approvedAmount')}</td>
                            
                            <td>${fieldValue(bean:grantApprovalInstance, field:'approvedDate')}</td>
                        
                            <td>${fieldValue(bean:grantApprovalInstance, field:'approvedBy.userRealName')}</td>
                            
                            <td><g:link  action="listFromApprovedGrantRequest" controller="grantAllocation" id="${grantApprovalInstance.id}">Allocation Details</g:link></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${GrantApproval.count()}" />
            </div>
            
        </div>
    </body>
</html>
