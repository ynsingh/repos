<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.EditSubProjectAllotment.head"/></title>
    </head>
    <body>
    <div class="wrapper">
    <g:subMenuList/>
    <div class="proptable">
     			<table width="100%" align="left">
     				<tr>
                    	<td valign="top" class="name">
                        	<label for="projects"><g:message code="default.Project.label"/>:</label>
                        </td>
                        <td valign="top" >
                         	<strong>  ${fieldValue(bean:projectsInstance,field:'parent.code')} </strong>
                        </td>
                        <td valign="top" class="name">
                            <label for="party"> <g:message code="default.Grantor.label"/> :</label>
                        </td>
                        <td valign="top" class="name" >
                          <strong>  ${fieldValue(bean:partyInstance,field:'code')} </strong>  
                        </td>
                        <td valign="top" class="name">
                            <label for="party"> <g:message code="default.AmountAllocated.label"/>:</label>
                        </td>
                        <td valign="top" >
						<strong>
                             	<g:message code="default.Rs.label" />
                             	${currencyFormat.ConvertToIndainRS(projectsInstance.parent.totAllAmount)}
                             </strong>
                        </td>
                        <td valign="top" class="name">
                            <label for="party"> <g:message code="default.BalanceAmount.label"/>:</label>
                        </td>
                        <td valign="top" >
                         	<strong>
                             	<g:message code="default.Rs.label" />
                             	${currencyFormat.ConvertToIndainRS(grantAllocationInstance.balanceAmount)}
                             </strong>
                        </td>
                     </tr> 
                </table>
            </div>
    <g:hiddenField name="parentProjectStartDate" value="${grantAllocationInstance?.projects?.parent?.projectStartDate}"/>
    <g:hiddenField name="parentProjectEndDate" value="${grantAllocationInstance?.projects?.parent?.projectEndDate}"/>
        <div class="body">
	        <g:if test="${grantAllocationInstance.projects.parent ==null}"> 
	        	<h1><g:message code="default.EditSubProjectAllotment.head"/></h1>
			</g:if>
			<g:else>
				<h1><g:message code="default.EditSubProjectAllotment.head"/></h1>
			</g:else>
            <g:if test="${flash.message}">
            	<div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${grantAllocationInstance}">
	            <div class="errors">
	                <g:renderErrors bean="${grantAllocationInstance}" as="list" />
	            </div>
            </g:hasErrors>
            <g:form method="post" action="updateProAllot" >
            <input type="hidden" name="projects.id" value="${grantAllocationInstance?.projects?.id}" />
                <input type="hidden" name="id" value="${grantAllocationInstance?.id}" />
                 <input type="hidden" id="balance" name="balance" value="${grantAllocationInstance.balanceAmount}"/>
                <div class="dialog">
                  <table>
                    <tbody>
                       <tr class="prop">
                            <td valign="top" class="name">
                                <label for="projects"><g:message code="default.Project.label"/>:</label>
                                <label for="projects" style="color:red;font-weight:bold"> * </label>
                            </td>
                            <td valign="top" 
                            	class="value ${hasErrors(bean:grantAllocationInstance,field:'projects','errors')}">
                                <input type="text" size="45" id="name" name="name" value="${fieldValue(bean:grantAllocationInstance?.projects,field:'name')}"/>
                            </td>
                       </tr> 
                       <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code">Code:</label>
                                    <label for="code" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <input type="text" id="code" name="code" value="${fieldValue(bean:grantAllocationInstance?.projects,field:'code')}"/>
                                </td>
                            </tr>
                       
                       <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projectStartDate">Start Date:</label>
                                    <label for="projectStartDate" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'projectStartDate','errors')}">
                                    <calendar:datePicker name="projectStartDate" value="${grantAllocationInstance?.projects?.projectStartDate}" defaultValue="${new Date()}" dateFormat= "%d/%m/%Y" />
                                </td>
                            </tr> 
                            
                            
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projectEndDate">End Date:</label>
                                    <label for="projectEndDate" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'projectEndDate','errors')}">
                                    <calendar:datePicker name="projectEndDate" value="${grantAllocationInstance?.projects?.projectEndDate}" defaultValue="${new Date()}" dateFormat= "%d/%m/%Y"/>
                                </td>
                            </tr>
                       
                       <tr class="prop">
	                       	<td valign="top" class="name">
	                            <label for="party">
	                            	<g:message code="default.GrantAllocation.Recipient.label"/>:
	                            	<label for="party" style="color:red;font-weight:bold"> * </label>
	                        	</label>
	                        </td>
	                         <td>
                            	<strong>${grantAllocationInstance?.party?.code}</strong>
	                        </td>
                    	</tr> 
                        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="investigator"><g:message code="default.Investigator.label"/>:</label>
                                    <label for="investigator" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'investigator','errors')}">
                                 <g:select id="investigator.id" optionKey="id" optionValue="fullName" from="${investigatorInstanceList}" name="investigator.id" value="${projectsInstance?.investigator?.id}"></g:select>
	                            </td>
                         	</tr>   
                         <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="investigator"><g:message code="default.COPI.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'copi','errors')}">
                                 <g:select id="copi.id" optionKey="id" optionValue="fullName" from="${investigatorInstanceList}" name="copi.id" value="${projectsInstance?.copi?.id}"></g:select>
	                            </td>
                         </tr>   
                            
                        <tr class="prop">
                            <td valign="top" class="name">
                                <label for="party">
                                	<g:message code="default.GrantAllocation.SanctionOrderNo.label"/>:
                            	</label>
                            	<label for="party" style="color:red;font-weight:bold"> * </label>
                            </td>
                            <td valign="top" 
                            	class="value ${hasErrors(bean:grantAllocationInstance,field:'sanctionOrderNo','errors')}">
                                <input type="text" id="sanctionOrderNo" name="sanctionOrderNo" 
                                	value="${fieldValue(bean:grantAllocationInstance,field:'sanctionOrderNo')}" 
                                	style="text-align: right"/>
                            </td>
                        </tr> 
                            
                         <tr class="prop">
                            <td valign="top" class="name">
                                <label for="dateOfAllocation">
                                	<g:message code="default.DateOfAllocation.label"/>:
                            	</label>
                            </td>
                            <td valign="top" 
                            	class="value ${hasErrors(bean:grantAllocationInstance,field:'dateOfAllocation','errors')}">
                                <calendar:datePicker name="dateOfAllocation" defaultValue="${new Date()}" 
                                	value="${grantAllocationInstance?.dateOfAllocation}" dateFormat= "%d/%m/%Y"/>
                            </td>
                        </tr> 
                            
                        <tr class="prop">
                            <td valign="top" class="name">
                                <label for="amountAllocated"><g:message code="default.AmountAllocated(Rs).label"/>:</label>
                                <label for="amountAllocated" style="color:red;font-weight:bold"> * </label>
                            </td>
                            <td valign="top" 
                            	class="value ${hasErrors(bean:grantAllocationInstance,field:'amountAllocated','errors')}">
                                <input type="text" id="amountAllocated" name="amountAllocated" 
                                	value="${amount}" style="text-align: right" />
                               <input type="hidden" id="totAllAmount" name="totAllAmount" 
                               		value="${projectInstance.totAllAmount}"/>
                               <input type="hidden" id="amount" name="amount" value="${grantAllocationInstance.totAllAmount}"/>
                            </td>
                        </tr> 
                    	<tr class="prop">
                            <td valign="top" class="name">
                                <label for="remarks"><g:message code="default.Remarks.label"/>:</label>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'remarks','errors')}">
                                  <g:textArea name="remarks" value="${fieldValue(bean:grantAllocationInstance,field:'remarks')}" rows="3" cols="30"/>
                            </td>
                        </tr> 
                        
                      </tbody>
                	</table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" action="updateProAllot"   
                    	value="${message(code: 'default.Update.button')}" 
                    	onclick="return validateSubProject()" />
                	</span>
                </div>
            </g:form>
         </div>
       </div>
     </body>
</html>
