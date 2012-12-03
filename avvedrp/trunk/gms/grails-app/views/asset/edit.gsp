<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'asset.label', default: 'Asset')}" />
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
            <g:hasErrors bean="${assetInstance}">
            <div class="errors">
                <g:renderErrors bean="${assetInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${assetInstance?.id}" />
                <g:hiddenField name="version" value="${assetInstance?.version}" />
                <div class="dialog">
                       <table>
                        <tbody>
                        
                              <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="assetName"><g:message code="asset.assetName.label" default="Asset Name" /></label>
                                     <label for="grantAllocationSplit" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: assetInstance, field: 'assetName', 'errors')}">
                                    <g:textField name="assetName" value="${assetInstance?.assetName}" />
                                </td>
                                <input type="hidden" name="projectId" value="${projectInstance.id}"/>
                               <td valign="top" class="name">
                                    <label for="assetCode"><g:message code="asset.assetCode.label" default="Asset Code" /></label>
                                     <label for="grantAllocationSplit" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: assetInstance, field: 'assetCode', 'errors')}">
                                    <g:textField name="assetCode" value="${assetInstance?.assetCode}" />
                                </td>
                               </tr>
                        
                               <tr class="prop">
	                               <td valign="top" class="name">
	                                    <label for="manufacturer"><g:message code="asset.manufacturer.label" default="Manufacturer" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: assetInstance, field: 'manufacturer', 'errors')}">
	                                    <g:textField name="manufacturer" value="${assetInstance?.manufacturer}" />
	                                </td>
	                                <td valign="top" class="name">
	                                    <label for="storageLocation"><g:message code="asset.storageLocation.label" default="Storage Location" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: assetInstance, field: 'storageLocation', 'errors')}">
	                                    <g:textField name="storageLocation" value="${assetInstance?.storageLocation}" />
	                                </td>
                               </tr>
                        
                           	   <tr class="prop">
	                               <td valign="top" class="name">
	                                    <label for="cost"><g:message code="default.cost.label(Rs)" default="Cost" /></label>
	                                     <label for="grantAllocationSplit" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: assetInstance, field: 'cost', 'errors')}">
	                                    <g:textField name="cost" value="${amount}" />
	                                </td>
	                                 <td valign="top" class="name">
	                                    <label for="invoiceNo"><g:message code="asset.invoiceNo.label" default="Invoice No" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: assetInstance, field: 'invoiceNo', 'errors')}">
	                                    <g:textField name="invoiceNo" value="${assetInstance?.invoiceNo}" />
	                                </td>
                                </tr>  
                                
                                <tr class="prop"> 
	                                <td valign="top" class="name">
	                                    <label for="barCode"><g:message code="asset.barCode.label" default="Bar Code" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: assetInstance, field: 'barCode', 'errors')}">
	                                    <g:textField name="barCode" value="${assetInstance?.barCode}" />
	                                </td>
	                                 <td valign="top" class="name">
	                                    <label for="modalNo"><g:message code="asset.modalNo.label" default="Modal No" /></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: assetInstance, field: 'modalNo', 'errors')}">
	                                    <g:textField name="modalNo" value="${assetInstance?.modalNo}" />
	                                </td>
                           	    </tr>
                        
                            <tr class="prop">
                               
                               <td valign="top" class="name">
                                    <label for="modalName"><g:message code="asset.modalName.label" default="Modal Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: assetInstance, field: 'modalName', 'errors')}">
                                    <g:textField name="modalName" value="${assetInstance?.modalName}" />
                                </td>
                                 <td valign="top" class="name">
                                    <label for="serialNo"><g:message code="asset.serialNo.label" default="Serial No" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: assetInstance, field: 'serialNo', 'errors')}">
                                    <g:textField name="serialNo" value="${assetInstance?.serialNo}" />
                                </td>
                            </tr>
                        
                           
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" onClick="return validateAssetTracking()" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
         </div>
    </body>
</html>
