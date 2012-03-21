<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'fundTransfer.label', default: 'FundTransfer')}" />
        <title><g:message code="default.FundTransfer.head"/></title>
    </head>
    
    <body>
    	<g:if test="${params.subMenu == 'fundAllot'}">
        	<g:subMenuProjects/>
        </g:if>
       	<g:elseif test="${params.subMenu == 'subGrantAllot'}">
    		<g:subMenuList/>
		</g:elseif>
         <div class="body">
        <div class="wrapper">
            <h1><g:message code="default.FundTransfer.head"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${fundTransferInstance}">
            <div class="errors">
                <g:renderErrors bean="${fundTransferInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                <g:hiddenField name="ProjectStartDate" 
                value="${grantAllocationInstance.projects.projectStartDate}"/>
                    <table>
                        <tbody>
                                                                         
                             <tr class="prop">
                                <td valign="top" class="name">
                                   <label for="projects"><g:message code="default.Project.label"/>:</label>
                                </td>
                               
                                <td><strong>${grantAllocationInstance.projects.code}</strong></td>
                                <input type="hidden" name="grantAllocationId" id="grantAllocationId" value="${grantAllocationInstance.id}"/>
                                <input type="hidden" name="project" id="projectCode" value="${grantAllocationInstance.projects.code}"/>
                                <td align="left" class="name">
                                   <label for="Grantor"><g:message code="default.Grantor.label"/>:</label>
                                </td>
                                <td>
                                     <g:if test="${grantAllocationInstance.granter}">
                                     	<strong> ${grantAllocationInstance.granter.code}  </strong>
                                     	<input type="hidden" name="grantor" id="grantorCode" value="${grantAllocationInstance.granter.code}"/>
                                     </g:if>
                                </td>
                            </tr>  
                            <tr class="prop">
                            	<td valign="top" class="name">
                                   <label for="Recepient"><g:message code="default.Recepient.label"/>:</label>
                                </td>
                                <td valign="top" class="name">
                                  <strong>  ${grantAllocationInstance.party.code}</strong>
                                  <input type="hidden" name="Recepient" id="recepientName" value="${grantAllocationInstance.party.id}"/>
                                </td>
                                <td align="left" class="name">
                                   <label for="Amount Allocated(Rs)"><g:message code="default.AmountAllocated.label"/>:</label>
                                </td>
                                <td>
                                	<strong> ${currencyFormat.ConvertToIndainRS(grantAllocationInstance.amountAllocated.doubleValue())}</strong>                          	
                                </td>
                            </tr>                  
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="amount"><g:message code="fundTransfer.amounttransferred.label" />:</label>
                                    <label for="amount" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: fundTransferInstance, field: 'amount', 'errors')}">
                                    <g:textField id="amount" name="amount" value="${fieldValue(bean: fundTransferInstance, field: 'amount')}" />
                                </td>
                                <td valign="top" class="name">
                                    <label for="dateOfTransfer"><g:message code="fundTransfer.dateOfTransfer.label" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: fundTransferInstance, field: 'dateOfTransfer', 'errors')}">
                                	<calendar:datePicker name="dateOfTransfer" defaultValue="${new Date()}" value="${fundTransferInstance?.dateOfTransfer}" dateFormat= "%d/%m/%Y"/>
                                    
                                </td>
                                <input type="hidden" name="subMenu" value="${params.subMenu}"/>
                            </tr>                                                 
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}"  onClick="return validateFundTransffered()"/></span>
            </div>
                </div>
                <div class="list">
                <table>
                    <thead>
                        <tr>
                             <th><g:message code="default.SINo.label"/></th>
                            
                             <th><g:message code="default.Project.label"/></th>
                              
                             <th><g:message code="default.Grantor.label"/></th>
                           
							 <th><g:message code="default.Recepient.label"/></th>
                            
                             <th><g:message code="default.AmountAllocated.label"/></th>
                           
                             <th><g:message code="fundTransfer.amounttransferred.label"/></th>
                           
                             <th><g:message code="fundTransfer.dateOfTransfer.label"/></th>   
                            
                        	 <th><g:message code="default.Edit.label"/></th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${fundTransferInstanceList}" status="i" var="fundTransferInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${i+1}</td>
                        
                            <td>${fieldValue(bean:fundTransferInstance,field:'grantAllocation.projects.code')}</td>
                            
                            <td>${fieldValue(bean:fundTransferInstance, field:'grantAllocation.granter.code')}</td>
                            
                            <td>${fieldValue(bean:fundTransferInstance, field:'grantAllocation.party.code')}</td>
                            
                            <td>${currencyFormat.ConvertToIndainRS(fundTransferInstance.grantAllocation.amountAllocated)}</td>
                            
                            <td>${currencyFormat.ConvertToIndainRS(fundTransferInstance.amount)}</td>                       
                                                 
                            <td><g:formatDate date="${fundTransferInstance.dateOfTransfer}" format= "dd/MM/yyyy"/></td>
                            
                        	<td><g:link action="edit" id="${fundTransferInstance.id}" params="[subMenu:'subGrantAllot']"><g:message code="default.Edit.label"/></g:link></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
                            
            </g:form>
        </div>
    </body>
</html>
