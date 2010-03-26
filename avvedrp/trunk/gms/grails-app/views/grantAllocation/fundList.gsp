

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Fund List</title>
    </head>
    <body>
    <div class="wrapper"> 
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
 
        </div>
        <table  cellspacing="0" cellpadding="0">
         <tr>
         <td>
        <div class="body">
            <h1>Fund List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table  cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="SlNo" />
                                                                                 
                   	        <g:sortableColumn property="project" title="Project Code " />
                   	         <g:sortableColumn property="project" title="PI Name " />
                            <g:sortableColumn property="project" title="Project Start Date " />
                            <g:sortableColumn property="project" title="Project End date " />
                            
                   	        <g:sortableColumn property="party" title="Grant Agency" />
                   	                   	        <g:sortableColumn property="amountAllocated" title="Amount Allocated(Rs)" />
                           <g:sortableColumn property="Remarks" title="Remarks" /> 
                           <g:sortableColumn property="Remarks" title="Allot Projects" />     
                   	       
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${grantAllocationInstanceList}" status="i" var="grantAllocationInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${(i + 1)}</td>
                           
                                                       
                            <td>${fieldValue(bean:grantAllocationInstance, field:'projects.code')}</td>
                            <td>${fieldValue(bean:grantAllocationInstance, field:'projects.principalInvestigatorName')}</td>
                               <td><g:formatDate format="dd-MM-yyyy" date="${grantAllocationInstance.projects.projectStartDate}"/></td>
                            <td><g:formatDate format="dd-MM-yyyy" date="${grantAllocationInstance.projects.projectEndDate}"/></td>
                               <td>${fieldValue(bean:grantAllocationInstance, field:'party.code')}</td>
    	                     <td><g:formatNumber number="${grantAllocationInstance.amountAllocated}" format="###,##0.00" /></td>
                           <td>${fieldValue(bean:grantAllocationInstance, field:'remarks')}</td>
                            <td><g:link action="create" id="${grantAllocationInstance.id}">Allot projects</g:link></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
          </div>
         </tr>
         </td>   
        </div>
    </body>
</html>
