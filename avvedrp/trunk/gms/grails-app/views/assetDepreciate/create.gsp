


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'assetDepreciate.label', default: 'AssetDepreciate')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
    	<div class="wrapper">
    	<g:subMenuList/>
          <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${assetDepreciateInstance}">
            <div class="errors">
                <g:renderErrors bean="${assetDepreciateInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                           
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="asset"><g:message code="default.AssetName.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: assetTransferInstance, field: 'asset', 'errors')}">
                                    <strong>${assetInstance?.assetName}</strong>
                                    <g:hiddenField name="id" value="${assetInstance?.id}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="depreciateValue"><g:message code="default.depreciateValue.label"/></label>
                                     <label for="grantAllocationSplit" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: assetDepreciateInstance, field: 'depreciateValue', 'errors')}">
                                 <g:textField name="depreciateValue" value="${fieldValue(bean:assetDepreciateInstance,field:'depreciateValue')}"/>
                                </td>
                                 <td valign="top" class="name">
                                    <label for="depreciationDate"><g:message code="default.depreciationDate.label"/></label>
                                </td>
                               <td>
						    		<calendar:datePicker name="depreciationDate" value="${assetDepreciateInstance?.depreciationDate}" defaultValue="${new Date()}" dateFormat= "%d/%m/%Y"/>
						    	</td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" onClick="return validateAssetDepreciation()" /></span>
                </div>
            </g:form>
        </div>
        
        <div class="body">
			<div class="list">
	 			<g:if test="${assetDepreciateInstanceList}">		
					<table> 			     
  						<thead>
	  						<tr>
	  							<g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
	  							
	  							<th><g:message code="default.depreciationDate.label"/></th>
	                   	       
	                   	        <th><g:message code="default.depreciateValue.label"/></th>
	                   	        
	                   	        <th><g:message code="default.Edit.label"/></th>
	                   	      
	           	             </tr>
	   	               </thead>
	   	               <tbody>	
					       <g:each in="${assetDepreciateInstanceList}" status="i" var="assetDepreciateInstance">
							       <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
										<td>${i+1}</td>
										
								        <td><g:formatDate format="dd/MM/yyyy" date="${assetDepreciateInstance.depreciationDate}"/></td>
								       								        
								        <td>${fieldValue(bean: assetDepreciateInstance, field: "depreciateValue")}</td>
								        
								        <td>
								        	<g:link controller="assetDepreciate" action="edit" id="${assetDepreciateInstance.id}"><g:message code="default.Edit.label"/></g:link>
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
