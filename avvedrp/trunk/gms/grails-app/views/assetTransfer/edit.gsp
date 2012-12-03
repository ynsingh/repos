


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'assetTransfer.label', default: 'AssetTransfer')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
            <div class ="wrapper">
         <g:subMenuList/> 
        <div class="body"> 
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${assetTransferInstance}">
            <div class="errors">
                <g:renderErrors bean="${assetTransferInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${assetTransferInstance?.id}" />
                <g:hiddenField name="version" value="${assetTransferInstance?.version}" />
                <div class="dialog">
                    <table>
                                               <tbody>
                        
                           <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="asset"><g:message code="default.AssetName.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: assetTransferInstance, field: 'asset', 'errors')}">
                                    <strong>${assetTransferInstance?.asset?.assetName}</strong>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="fromDepartment"><g:message code="default.fromDepartment.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: assetTransferInstance, field: 'fromDepartment', 'errors')}">
                                    <g:textField name="fromDepartment" value="${assetTransferInstance?.fromDepartment}" />
                                </td>
                               <td valign="top" class="name">
                                    <label for="toDepartment"><g:message code="default.toDepartment.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: assetTransferInstance, field: 'toDepartment', 'errors')}">
                                    <g:textField name="toDepartment" value="${assetTransferInstance?.toDepartment}" />
                                </td>
                            </tr>
                        
                          

                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="fromLocation"><g:message code="default.fromLocation.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: assetTransferInstance, field: 'fromLocation', 'errors')}">
                                    <g:textField name="fromLocation" value="${assetTransferInstance?.fromLocation}" />
                                </td>
                                 <td valign="top" class="name">
                                    <label for="toLocation"><g:message code="default.toLocation.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: assetTransferInstance, field: 'toLocation', 'errors')}">
                                    <g:textField name="toLocation" value="${assetTransferInstance?.toLocation}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="requestedBy"><g:message code="default.requestedBy.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: assetTransferInstance, field: 'requestedBy', 'errors')}">
                                    <g:textField name="requestedBy" value="${assetTransferInstance?.requestedBy}" />
                                </td>
                            	<td>
						    		<label><g:message code="default.TransferDate.label"/></label>:
						    	</td>
						    	<td>
						    		<calendar:datePicker name="transferDate" value="${assetTransferInstance?.transferDate}" defaultValue="${new Date()}" dateFormat= "%d/%m/%Y"/>
						    	</td>
						   	</tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="transferNo"><g:message code="default.transferNo.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: assetTransferInstance, field: 'transferNo', 'errors')}">
                                    <g:textField name="transferNo" value="${assetTransferInstance?.transferNo}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
        </div>
    </body>
</html>
