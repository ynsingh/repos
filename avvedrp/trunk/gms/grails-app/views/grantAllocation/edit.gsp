<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.FundAllocation.EditFundAllocation.head"/> </title>
    </head>
    <body>
    <div class="wrapper"> 
        <div class="body">
            <h1><g:message code="default.FundAllocation.EditFundAllocation.head"/></h1>
            <g:if test="${flash.message}">
            	<div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${grantAllocationInstance}">
	            <div class="errors">
	                <g:renderErrors bean="${grantAllocationInstance}" as="list" />
	            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${grantAllocationInstance?.id}" />
                	<div class="dialog">
                      <table>
                        <tbody>
                        	<tr class="prop">
                            	<td valign="top" class="name">
                                    <label for="projects"><g:message code="default.Projects.label"/>:</label>
                                </td>
                                <td>
                                <strong>  ${fieldValue(bean:grantAllocationInstance,field:'projects.code')} </strong>
                                <input type="hidden" id="grantor" name="projects.id" 
                                	value=" ${fieldValue(bean:grantAllocationInstance,field:'projects.id')}"/>
                                </td>
                            </tr> 
                         	<tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dateOfAllocation"><g:message code="default.DateOfAllocation.label"/>:</label>
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
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'amountAllocated','errors')}">
                                    <input type="text" id="amountAllocated" name="amountAllocated" 
                                    	value="${grantAllocationInstance.amountAllocated}" style="text-align: right" />
                                </td>
                            </tr> 
                        
                      
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="remarks"><g:message code="default.Remarks.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'remarks','errors')}">
                                      <g:textArea name="remarks" value="${fieldValue(bean:grantAllocationInstance,field:'remarks')}" 
                                      		rows="3" cols="30"/>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">

                    <span class="button"><g:actionSubmit class="save" value="${message(code: 'default.Update.button')}" 
                    	onClick="return validateGrantAllocationEdit()" /></span>

                    <!-- <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" 
                    	value="Delete" /></span> -->
                </div>
            </g:form>
        </div>
      </div>
    </body>
</html>
