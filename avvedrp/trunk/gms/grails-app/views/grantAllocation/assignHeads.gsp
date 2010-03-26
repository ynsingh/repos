

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>GrantAllocation List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
           <span class="menuButton"><a class="list"  href="${createLinkTo(dir:'/grantAllocation/list.gsp')}">Project List</a></span>
        </div>
        <div class="body">
            <h1>Head Allocation</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
             <g:form action="assHeadSave" method="post" >
            <tr>
                                <td valign="top" class="name">
                                    <label for="projects">Projects:</label>
                                </td>
                                <td valign="top" >
                                 <strong>  ${fieldValue(bean:projectInstance,field:'code')} </strong>
                                    <input type="hidden" id="project" name="project" value="${fieldValue(bean:projectInstance, field:'id')}"/>
                                    <input type="hidden" id="grantAllotId" name="grantAllotId" value="${fieldValue(bean:grantAllocation, field:'id')}"/>
                                  </td>
                                  
                                  <td valign="top" class="name">
                                    <label for="party"> Party Code:</label>
                                </td>
                                <td valign="top" >
                                  <strong> ${fieldValue(bean:partyInstance,field:'code')} </strong>
                                    <input type="hidden" id="partyInstance" name="party" value="${fieldValue(bean:partyInstance, field:'id')}"/>
                                  </td>
                                  
                                  <td valign="top" class="name">
                                    <label for="party"> Allocated Amount:</label>
                                </td>
                                <td valign="top" >
                              
                                  <strong> <g:formatNumber number="${fieldValue(bean:grantAllocation,field:'amountAllocated')}" format="###,##0.00" /> </strong>
                                   
                                  </td>
                            </tr> 
                            
                            <br>
                            <br>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="SlNo" />
                            
                             <g:sortableColumn property="AccountHead" title="Account Head" />
                             
                              <g:sortableColumn property="code" title="Grant Code" />
                             
                   	         <g:sortableColumn property="amountAllocated" title="Amount Allocated" />
                   	        
                   	       <g:sortableColumn property="remarks" title="Remaks" />
                        
                   	       
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${grantAllocationListInstance}" status="i" var="grantAllocationInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${(i + 1)}
                            <input type="hidden" id="grantID" name="grantID" value="${grantAllocationInstance.value.id}"/>
                            </td>
                                                                           
                            <td>${grantAllocationInstance.value.accountHeads.code}
                              <input type="hidden" id="accountHeads" name="accountHeads" value="${grantAllocationInstance.value.accountHeads.id}"/>
                            </td>
                            
                            <td> 
                             <input type="text" id="code" name="code" value="${fieldValue(bean:grantAllocationInstance.value,field:'code')}" />
                            </td>
                          
                            <td> 
                             <input type="text" id="amountAllocated" name="amountAllocated" value="${fieldValue(bean:grantAllocationInstance.value,field:'amountAllocated')}" />
                            </td>
                          
                            <td>
                            <g:textArea name="remarks" value="${fieldValue(bean:grantAllocationInstance.value,field:'remarks')}" rows="3" cols="30"/>
                            </td>
                        
                                                  
                                              
                        </tr>
                    </g:each>
                    
                    
                    </tbody>
                </table>
            </div>

         <div class="buttons">
                    <input type="submit" name="funtSave" value="Save" >
                </div>        
        </div>
        
          </g:form>
          
          
    </body>
</html>
