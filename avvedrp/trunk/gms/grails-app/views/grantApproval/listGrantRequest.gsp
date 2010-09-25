

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>GrantRequest List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            
        </div>
        <div class="body">
            <h1>GrantRequest List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="grantDetails.grantMaster.code" title="Grant Code" />
                        
                   	        <g:sortableColumn property="grantDetails.grantMaster.title" title="Grant Title" />
                   	        
                   	        <g:sortableColumn property="grantDetails.financialYear" title="Financial Year" />
                   	        
                   	        <g:sortableColumn property="projects.projectName" title="Project Name" />
                        
                   	        <g:sortableColumn property="dateOfRequest" title="Date Of Request" />
                   	        
                   	        <g:sortableColumn property="amountRequested" title="Amount Requested" />
                        
                   	        <g:sortableColumn property="description" title="Description" />
                   	        
                   	        <g:sortableColumn property="requestStatus" title="Request Status" />
                   	        
                   	        <th>Approve/Deny</th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${grantRequestInstanceList}" status="i" var="grantRequestInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${fieldValue(bean:grantRequestInstance, field:'id')}</td>
                        
                            <td>${fieldValue(bean:grantRequestInstance, field:'grantDetails.grantMaster.code')}</td>
                        
                            <td>${fieldValue(bean:grantRequestInstance, field:'grantDetails.grantMaster.title')}</td>
                            
                            <td>${fieldValue(bean:grantRequestInstance, field:'grantDetails.financialYear')}</td>
                            
                            <td>${fieldValue(bean:grantRequestInstance, field:'projects.projectName')}</td>
                        
                            <td>${fieldValue(bean:grantRequestInstance, field:'dateOfRequest')}</td>
                            
                            <td>${fieldValue(bean:grantRequestInstance, field:'amountRequested')}</td>
                        
                            <td>${fieldValue(bean:grantRequestInstance, field:'description')}</td>
                            
                            <td>${fieldValue(bean:grantRequestInstance, field:'requestStatus')}</td>
                            
                            <td><g:link  action="create" controller ="grantApproval" id="${grantRequestInstance.id}">Approve/Deny</g:link></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${GrantRequest.count()}" />
            </div>
            <h1>GrantRequest Approved/Denied List</h1>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	                               
                   	        <g:sortableColumn property="grantRequest.grantDetails.grantMaster.code" title="Grant Code" />
                        
                   	        <g:sortableColumn property="grantRequest.grantDetails.grantMaster.title" title="Grant Title" />
                   	        
                   	        <g:sortableColumn property="grantRequest.grantDetails.financialYear" title="Financial Year" />
                   	        
                   	        <g:sortableColumn property="grantRequest.projects.projectName" title="Project Name" />
                        
                   	        <g:sortableColumn property="grantRequest.dateOfRequest" title="Date Of Request" />
                   	        
                   	        <g:sortableColumn property="grantRequest.amountRequested" title="Amount Requested" />
                   	        
                   	        <g:sortableColumn property="approvalStatus" title="Approval Status" />
                        
                   	        <g:sortableColumn property="approvedAmount" title="Approved Amount" />
                   	    
                   	        <g:sortableColumn property="approvedDate" title="Approved Date" />
                        
                   	        <g:sortableColumn property="approvedBy.userRealName" title="Approved By" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${grantApprovalInstanceList}" status="i" var="grantApprovalInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${fieldValue(bean:grantApprovalInstance, field:'grantRequest.grantDetails.grantMaster.code')}</td>
                        
                            <td>${fieldValue(bean:grantApprovalInstance, field:'grantRequest.grantDetails.grantMaster.title')}</td>
                            
                            <td>${fieldValue(bean:grantApprovalInstance, field:'grantRequest.grantDetails.financialYear')}</td>
                            
                            <td>${fieldValue(bean:grantApprovalInstance, field:'grantRequest.projects.projectName')}</td>
                        
                            <td>${fieldValue(bean:grantApprovalInstance, field:'grantRequest.dateOfRequest')}</td>
                            
                            <td>${fieldValue(bean:grantApprovalInstance, field:'grantRequest.amountRequested')}</td>
                            
                            <td>${fieldValue(bean:grantApprovalInstance, field:'approvalStatus')}</td>
                        
                            <td>${fieldValue(bean:grantApprovalInstance, field:'approvedAmount')}</td>
                            
                            <td>${fieldValue(bean:grantApprovalInstance, field:'approvedDate')}</td>
                        
                            <td>${fieldValue(bean:grantApprovalInstance, field:'approvedBy.userRealName')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            
        </div>
    </body>
</html>
