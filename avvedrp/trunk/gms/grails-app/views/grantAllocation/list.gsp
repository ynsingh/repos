

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Project List</title>
    </head>
    <body>
        <div class="wrapper"> 
            <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
         
        </div>
        <div class="body">
            <h1>Project List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="SlNo" />
                            
                            
                             
                   	        <g:sortableColumn property="project" title="Project Code " />
                        
                   	        <g:sortableColumn property="party" title="Institution" />
                   	        
                   	        <g:sortableColumn property="amountAllocated" title="Amount Allocated(Rs)" />
                   	        
                   	        <g:sortableColumn property="amountAllocated" title="Sub Allocate Projects " />
                   	        
                   	       <g:sortableColumn property="amountAllocated" title="Allocate Projects to Heads" />
                   	       
                   	         <g:sortableColumn property="amountAllocated" title="Receive Grant" />
                   	         
                   	         <g:sortableColumn property="amountAllocated" title="Grant Surrender/Closure" />
                   	         
                   	       
                        
                   	       
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${grantAllocationInstanceList}" status="i" var="grantAllocationInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${(i + 1)}</td>
                                                                           
                            <td>${fieldValue(bean:grantAllocationInstance, field:'projects.code')}</td>
                            
                           
                            
                           
                        
                            <td>${fieldValue(bean:grantAllocationInstance, field:'party.code')}</td>
                        
                          
                       <td><g:formatNumber number="${grantAllocationInstance?.amountAllocated}" format="###,##0.00" /></td>

                           
                            
                              <td><g:link action="subGrantAllot" id="${grantAllocationInstance.id}">Sub Allocate Projects</g:link></td>
                        
                             <td><g:link action="create"  controller='grantAllocationSplit' id="${grantAllocationInstance.id}">Allocate Projects to Heads</g:link></td>
                              <td><g:link action="create"  controller='grantReceipt' id="${grantAllocationInstance.id}">Receive Grant</g:link></td>
                              <td><g:link action="create"  controller='grantAllocationTracking' id="${grantAllocationInstance.id}" params="[trackType:'surrender']">Grant Surrender/Closure</g:link></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
           </div> 
        </div>
    </body>
</html>
