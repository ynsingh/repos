<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'asset.label', default: 'Asset')}" />
        <title><g:message code="default.Create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class ="wrapper">
           <g:subMenuList/> 
        <div class="body">
            <h1><g:message code="default.CreateAsset.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${assetInstance}">
            <div class="errors">
                <g:renderErrors bean="${assetInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                              <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="assetName"><g:message code="default.AssetName.label"/></label>
                                     <label for="grantAllocationSplit" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: assetInstance, field: 'assetName', 'errors')}">
                                    <g:textField name="assetName" value="${assetInstance?.assetName}" />
                                </td>
                                <input type="hidden" name="projectId" value="${projectInstance.id}"/>
                                <td valign="top" class="name">
                                    <label for="assetCode"><g:message code="default.itemPurchase.AssetCode.label"/></label>
                                     <label for="grantAllocationSplit" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: assetInstance, field: 'assetCode', 'errors')}">
                                    <g:textField name="assetCode" value="${assetInstance?.assetCode}" />
                                </td>
                               </tr>
                        
                               <tr class="prop">
	                               <td valign="top" class="name">
	                                    <label for="manufacturer"><g:message code="default.manufacturer.label"/></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: assetInstance, field: 'manufacturer', 'errors')}">
	                                    <g:textField name="manufacturer" value="${assetInstance?.manufacturer}" />
	                                </td>
	                                <td valign="top" class="name">
	                                    <label for="storageLocation"><g:message code="default.storageLocation.label"/></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: assetInstance, field: 'storageLocation', 'errors')}">
	                                    <g:textField name="storageLocation" value="${assetInstance?.storageLocation}" />
	                                </td>
                               </tr>
                        
                           	   <tr class="prop">
	                               <td valign="top" class="name">
	                                    <label for="cost"><g:message code="default.cost.label(Rs)"/></label>
	                                    <label for="grantAllocationSplit" style="color:red;font-weight:bold"> * </label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: assetInstance, field: 'cost', 'errors')}">
	                                    <g:textField name="cost" value="${amount}" />
	                                </td>
	                                 <td valign="top" class="name">
	                                    <label for="invoiceNo"><g:message code="default.InvoiceNo.label"/></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: assetInstance, field: 'invoiceNo', 'errors')}">
	                                    <g:textField name="invoiceNo" value="${assetInstance?.invoiceNo}" />
	                                </td>
                                </tr>  
                                
                                <tr class="prop"> 
	                                <td valign="top" class="name">
	                                    <label for="barCode"><g:message code="default.barCode.label"/></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: assetInstance, field: 'barCode', 'errors')}">
	                                    <g:textField name="barCode" value="${assetInstance?.barCode}" />
	                                </td>
	                                 <td valign="top" class="name">
	                                    <label for="modalNo"><g:message code="default.modalNo.label"/></label>
	                                </td>
	                                <td valign="top" class="value ${hasErrors(bean: assetInstance, field: 'modalNo', 'errors')}">
	                                    <g:textField name="modalNo" value="${assetInstance?.modalNo}" />
	                                </td>
                           	    </tr>
                        
                            <tr class="prop">
                               
                               <td valign="top" class="name">
                                    <label for="modalName"><g:message code="default.modalName.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: assetInstance, field: 'modalName', 'errors')}">
                                    <g:textField name="modalName" value="${assetInstance?.modalName}" />
                                </td>
                                 <td valign="top" class="name">
                                    <label for="serialNo"><g:message code="default.serialNo.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: assetInstance, field: 'serialNo', 'errors')}">
                                    <g:textField name="serialNo" value="${assetInstance?.serialNo}" />
                                </td>
                            </tr>
                        
                           
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" onClick="return validateAssetTracking()" /></span>
                </div>
            </g:form>
        </div>
        <div class="body">
			<div class="list">
	 			<g:if test="${assetInstanceList}">		
					<table> 			     
  						<thead>
	  						<tr>
	  							<g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
	                   	        <th><g:message code="default.AssetName.label"/></th>
	                   	        
	                   	        <th><g:message code="default.itemPurchase.AssetCode.label"/></th>
	                   	        
	                   	        <th><g:message code="default.cost.label(Rs)"/></th>
	                   	         
	                   	        <th><g:message code="default.modalName.label"/></th>
	                   	        
	                   	        <th><g:message code="default.manufacturer.label"/></th>
	                   	         
	                   	        <th><g:message code="default.storageLocation.label"/></th>
	                   	        
	                   	        <th><g:message code="default.Edit.label"/></th>
	                   	        
	                   	        <th><g:message code="default.AssetTransfer.label"/></th>
	                   	        
	                   	        <th><g:message code="default.Assetdepreciation.label"/></th>
	           	             </tr>
	   	               </thead>
	   	               <tbody>	
					       <g:each in="${assetInstanceList}" status="i" var="assetInstance">
							       <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
										<td>${i+1}</td>
								        <td>${fieldValue(bean: assetInstance, field: "assetName")}</td>
								        
								        <td>${fieldValue(bean: assetInstance, field: "assetCode")}</td>
								        
								        <td>${currencyFormat.ConvertToIndainRS(assetInstance.cost)}</td>
								        
								        <td>${fieldValue(bean: assetInstance, field: "modalName")}</td>
								        
								        <td>${fieldValue(bean: assetInstance, field: "manufacturer")}</td>
								        
								        <td>${fieldValue(bean: assetInstance, field: "storageLocation")}</td>
								        
								        <td>
								        	<g:link controller="asset" action="edit" id="${assetInstance.id}"><g:message code="default.Edit.label"/></g:link>
					        			</td>
					        			
					        			<td>
								        	<g:link controller="assetTransfer" action="create" id="${assetInstance.id}"><g:message code="default.AssetTransfer.label"/></g:link>
					        			</td>
					        			
					        			<td>
								        	<g:link controller="assetDepreciate" action="create" id="${assetInstance.id}"><g:message code="default.Assetdepreciation.label"/></g:link>
					        			</td>
							        </tr>
			       				</g:each> 
		       				</tbody>
		       			</table>  
			       		</g:if>
	    				<g:else>
		   		 			<br><g:message code="default.NoRecordsAvailable.label"/></br>
		    			</g:else>	
	       			</div>
		       	</div>
      </div>
     </body>
</html>
