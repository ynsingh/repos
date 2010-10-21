<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'fundTransfer.label', default: 'FundTransfer')}" />
        <title><g:message code="default.FundTransfer.EditFundTransfer.label" /></title>
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
            <h1><g:message code="default.FundTransfer.EditFundTransfer.label"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${fundTransferInstance}">
            <div class="errors">
                <g:renderErrors bean="${fundTransferInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${fundTransferInstance?.id}" />
                <g:hiddenField name="version" value="${fundTransferInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        	<tr class="prop">
                        		<td valign="top" class="name">
                                   <label for="projects"><g:message code="default.Project.label"/>:</label>
                                </td>
                                <td>                             
                                	<strong>  ${fieldValue(bean:fundTransferInstance.grantAllocation,field:'projects.code')} </strong>
                                </td>
                                <td valign="top" class="name">
                                   <label for="Grantor"><g:message code="default.Grantor.label"/>:</label>
                                </td>
                                <td> <strong> ${fieldValue(bean:fundTransferInstance.grantAllocation,field:'granter.code')} </strong></td>
                                <input type="hidden" name="grantAllocationId" id="grantAllocationId" value="${fundTransferInstance.grantAllocation.id}"/>
                        	</tr>
                        	<tr>
                        		<td valign="top" class="name">
                                   <label for="Recepient"><g:message code="default.Recepient.label"/>:</label>
                            	</td>
                            	<td> <strong> ${fieldValue(bean:fundTransferInstance.grantAllocation,field:'party.code')} </strong></td>
                                <td align="left" class="name">
                                   <label for="Amount Allocated(Rs)"><g:message code="default.AmountAllocated.label"/>:</label>
                                </td>
                                <td> <strong> ${currencyFormat.ConvertToIndainRS(fundTransferInstance.grantAllocation.amountAllocated.doubleValue())}</strong></td>
                        	</tr>  
                        	<tr class="prop">
                        		<td valign="top" class="name">
                                    <label for="amount"><g:message code="fundTransfer.amounttransferred.label" />:</label>
                                </td>
                                <td valign="top" class="name">
                                	<g:textField name="amount" value="${amount}" />
                        		</td>
                        	</tr>                             
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="dateOfTransfer"><g:message code="fundTransfer.dateOfTransfer.label" default="Date Of Transfer" /></label>
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
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" onClick="return validateFundTransffered()"/></span>
                   
                </div>
                </div>
            </g:form>
        </div>
    </body>
</html>
