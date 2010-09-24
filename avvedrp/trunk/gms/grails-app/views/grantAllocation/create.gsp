

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title> Project Allocation</title>         
    </head>
    <body>
     <div class="wrapper"> 
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
         <span class="menuButton"><a class="list" href="${createLinkTo(dir:'/grantAllocation/fundList.gsp')}">Fund list</a></span>
        </div>
        
        
       
<table width="100%" class="tablewrapper" cellspacing="0" cellpadding="0">
  <tr>
    <td scope="col">
<div class="body">
            <h1>Project Allocation</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${grantAllocationInstance}">
            <div class="errors">
                <g:renderErrors bean="${grantAllocationInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                         <tr>
                                <td valign="top" class="name">
                                    <label for="projects">Projects:</label>
                                </td>
                                <td valign="top" >
                                 <strong> ${fieldValue(bean:projectInstance.projects,field:'code')} </strong>
                                    <input type="hidden" id="project" name="project" value="${fieldValue(bean:projectInstance.projects, field:'id')}"/>
                                    <input type="hidden" id="grantAllot" name="grantAllot" value="${fieldValue(bean:projectInstance, field:'id')}"/>
                            
                        
                         <td valign="top" class="name">
                                    <label for="projects">Fund Amount(Rs)</label>
                                </td>
                                <td valign="top" >
                                 <strong>
                                <g:formatNumber number="${projectInstance.amountAllocated}" format="###,##0.00" /> </strong>
                                     <input type="hidden" id="totAmt" name="totAmt" value="${fieldValue(bean:projectInstance, field:'amountAllocated')}"/>
                                     <input type="hidden" id="totAloAmt" name="totAloAmt" value="${fieldValue(bean:projectInstance, field:'totAllAmount')}"/>    
                                </td>
                            </tr> 
                        
                        
                         <tr >
                                <td valign="top" class="name">
                                    <label for="party">Party:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'party','errors')}">
                                    <g:select optionKey="id" optionValue="code" from="${Party.findAll('from Party P where P.activeYesNo=\'Y\' and P.partyType is NULL ')}" name="party.id" value="${grantAllocationInstance?.party?.id}" ></g:select>
                                </td>
                       
                               
                         
                        
                                <td valign="top" class="name">
                                    <label for="dateOfAllocation">Date Of Allocation:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'dateOfAllocation','errors')}">
                                  
                                    <calendar:datePicker name="dateOfAllocation" defaultValue="${new Date()}" value="${grantAllocationInstance?.dateOfAllocation}" dateFormat= "%d/%m/%Y" dateFormat= "%d/%m/%Y"/>
                                </td>
                            </tr> 
                            
                        
                            <tr>
                                <td valign="top" class="name">
                                    <label for="amountAllocated">Amount Allocated(Rs):</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'amountAllocated','errors')}">
                                    <input type="text" id="amountAllocated" name="amountAllocated" value="${fieldValue(bean:grantAllocationInstance,field:'amountAllocated')}" style="text-align: right"/>
                                </td>
                          
                                <td valign="top" class="name">
                                    <label for="remarks">Remarks:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'remarks','errors')}">
                                      <g:textArea name="remarks" value="${fieldValue(bean:grantAllocationInstance,field:'remarks')}" rows="3" cols="30"/>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" onClick="return validateGrantAllocationCreate()"  value="Create" /></span>
                </div>
              </g:form>
                
            </div>



</td>
  </tr>
  <tr>
    <td >
 <div class="list">
                <table cellspacing="0" cellpadding="0">
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="SlNo" />
							 <g:sortableColumn property="project" title="Project " />
                        
                   	        <g:sortableColumn property="party" title="Institution" />
                   	        <g:sortableColumn property="DateOfAllocation" title="Date Of Allocation" />
                   	    
                   	        <g:sortableColumn property="amountAllocated" title="Amount Allocated(Rs)" />
                   	        <g:sortableColumn property="granter" title="Granter" />
                              <g:sortableColumn property="amountAllocated" title="Grant Surrender/Closure" />   
                   	       
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${grantAllocationInstanceList}" status="i" var="grantAllocationInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="edit" id="${grantAllocationInstance.id}" fromurl="create">${(i + 1)}</g:link></td>
                           
                         
                            
                            <td>${fieldValue(bean:grantAllocationInstance, field:'projects.code')}</td>
                             <td>${fieldValue(bean:grantAllocationInstance, field:'party.code')}</td>
                            
                            <td><g:formatDate format="dd-MM-yyyy" date="${grantAllocationInstance.DateOfAllocation}"/></td>
                        
                           
                        
                          
                        
                            <td><g:formatNumber number="${grantAllocationInstance.amountAllocated}" format="###,##0.00" /></td>
                            <td>${fieldValue(bean:grantAllocationInstance, field:'granter.code')}</td>
                             <td><g:link action="create"  controller='grantAllocationTracking' id="${grantAllocationInstance.id}" params="[trackType:'surrender']">Grant Surrender/Closure</g:link></td>
                            
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
</div> 

</td>
  </tr>
</table>  
        
</div>        
        
           
      
       
    </body>
</html>
