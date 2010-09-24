<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.EditSubProjectAllotment.head"/></title>
    </head>
    <body>
    <div class="wrapper">
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
                <input type="hidden" name="id" value="${grantAllocationInstance?.id}" />
                <div class="dialog">
                  <table>
                    <tbody>
                       <tr class="prop">
                            <td valign="top" class="name">
                                <label for="projects"><g:message code="default.Project.label"/>:</label>
                            </td>
                            <td valign="top" 
                            	class="value ${hasErrors(bean:grantAllocationInstance,field:'projects','errors')}">
                                <g:select optionKey="id" optionValue="code" from="${Projects.list()}" 
                                	name="projects.id" value="${grantAllocationInstance?.projects?.id}" 
                                	disabled="true">
                            	</g:select>
                            </td>
                       </tr> 
                       
                       <tr class="prop">
	                       	<td valign="top" class="name">
	                            <label for="party">
	                            	<g:message code="default.GrantAllocation.Recipient.label"/>:
	                        	</label>
	                        </td>
	                        <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'party','errors')}">
	                            <g:select optionKey="id" optionValue="code" from="${Party.list()}" name="party.id" 
	                            	value="${grantAllocationInstance?.party?.id}" disabled="true">
	                        	</g:select>
	                        </td>
                    	</tr> 
                            
                        <tr class="prop">
                            <td valign="top" class="name">
                                <label for="party">
                                	<g:message code="default.GrantAllocation.SanctionOrderNo.label"/>:
                            	</label>
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
                            </td>
                            <td valign="top" 
                            	class="value ${hasErrors(bean:grantAllocationInstance,field:'amountAllocated','errors')}">
                                <input type="text" id="amountAllocated" name="amountAllocated" 
                                	value="${grantAllocationInstance.amountAllocated}" style="text-align: right" />
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
                    <span class="button"><input class="save" type="submit" 
                    	value="${message(code: 'default.Update.button')}" 
                    	onclick="return validateEditProAllot()" />
                	</span>
                    <span class="button"><g:actionSubmit class="delete" 
                    	onclick="return confirm('Are you sure?');" 
                    	value="${message(code: 'default.Delete.button')}" />
                    </span>
                </div>
            </g:form>
         </div>
       </div>
     </body>
</html>
