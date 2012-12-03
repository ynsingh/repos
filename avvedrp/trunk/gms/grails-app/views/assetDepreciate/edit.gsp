


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'assetDepreciate.label', default: 'AssetDepreciate')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
    	<div class="wrapper">
    	<g:subMenuList/>
         <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${assetDepreciateInstance}">
            <div class="errors">
                <g:renderErrors bean="${assetDepreciateInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${assetDepreciateInstance?.id}" />
                <g:hiddenField name="version" value="${assetDepreciateInstance?.version}" />
                <div class="dialog">
                     <table>
                        <tbody>
                        
                           
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="asset"><g:message code="default.AssetName.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: assetDepreciateInstance, field: 'asset', 'errors')}">
                                    <strong>${assetDepreciateInstance?.asset?.assetName}</strong>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="depreciateValue"><g:message code="assetDepreciate.depreciateValue.label" default="Depreciate Value" /></label>
                                     <label for="grantAllocationSplit" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: assetDepreciateInstance, field: 'depreciateValue', 'errors')}">
                                  <input type="text" name="depreciateValue" value="${fieldValue(bean:assetDepreciateInstance,field:'depreciateValue')}" />
                                </td>
                                 <td valign="top" class="name">
                                    <label for="depreciationDate"><g:message code="assetDepreciate.depreciationDate.label" default="Depreciation Date" /></label>
                                </td>
                               <td>
						    		<calendar:datePicker name="depreciationDate" value="${assetDepreciateInstance?.depreciationDate}" defaultValue="${new Date()}" dateFormat= "%d/%m/%Y"/>
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
