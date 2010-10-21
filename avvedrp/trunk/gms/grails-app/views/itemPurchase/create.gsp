<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'itemPurchase.label', default: 'ItemPurchase')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <script type="text/javascript">
		</script>
    </head>
    <body>
    <div class="wrapper">   
		<div class="body">
		    <h1><g:message code="default.ProjectItemPurchase.create.head" /></h1>
		    <g:if test="${flash.message}">
		          <div class="message">${flash.message}</div>
		    </g:if>
		    <g:hasErrors bean="${itemPurchaseInstance}">
		    </g:hasErrors>
		    <g:form action="save" method="post" >
				<div class="dialog">
					<table>
						<tbody>
				            <tr>
					    		<td><label><g:message code="default.ProjectName.label" /></label></td>
					    		<td><strong>${projectInstance.name}</strong></td>
					    		<input type="hidden" name="projectId" value="${projectInstance.id}"/>
					    		<td><label><g:message code="default.itemPurchase.NameOfItem.label" /></label></td>
					    		<td><g:textField name="name" value="${itemPurchaseInstance?.name}" /></td>
				 		    </tr>
				  			<tr>
					  		    <td><label><g:message code="default.itemPurchase.AssetCode.label"/></label></td>
					    		<td><g:textField name="assetCode" value="${itemPurchaseInstance?.assetCode}" /></td>
					    		<td><label><g:message code="default.itemPurchase.DateReceived.label" /></label></td>
					    		<td><calendar:datePicker name="dateReceived" defaultValue="${new Date()}" 
					    				dateFormat="%d/%m/%Y"/></td>
				  			</tr>
				  			<tr>
					    		<td><label><g:message code="default.itemPurchase.OrderNo.label" /></label></td>
					    		<td><g:textField name="orderNo" 
					    			value="${fieldValue(bean: itemPurchaseInstance, field: 'orderNo')}" />
				    			</td>
					   			<td><label><g:message code="default.itemPurchase.BillNo.label" /></label></td>
					      		<td><g:textField name="billNo" 
					      			value="${fieldValue(bean: itemPurchaseInstance, field: 'billNo')}" />
				      			</td>
				  			</tr>
				  			<tr>
					   			<td><label><g:message code="default.itemPurchase.Cost(Rs).label" /></label></td>
					   			
					   			<td><g:textField name="cost" 
					   				value="${fieldValue(bean: itemPurchaseInstance, field: 'cost')}" /></td>
					   			
					   			<td><label><g:message code="default.Remarks.label" /></label></td>
					   			
					   			<td><g:textArea name="remarks" value="${itemPurchaseInstance?.remarks}" /></td>
				   			</tr>
				   			<tr>
					   			<td><label><g:message code="default.Active.label" /></label></td>
					   			<td><g:select name="status" from="${['Y','N']}" /> </td>
						   	</tr>
					   	</tbody>
				   	</table>
			   	</div>	
		   		<div class="buttons">
		   			<span class="button"><g:submitButton name="create" onClick="return validateItemPurchase()" 
						class="save" value="${message(code: 'default.Save.button')}" />
					</span>
				</div>
			</g:form>
		</div>
		<div class="body">
			<div class="list">
	 			<g:if test="${itemPurchaseInstanceList}">		
					<table> 			     
  						<thead>
	  						<tr>
	  							<g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
	                       
	                   	        <g:sortableColumn property="Name" 
	                   	        	title="${message(code: 'default.itemPurchase.NameOfItem.label')}" />
	                   	        
	                   	        <g:sortableColumn property="AssetCode" 
	                   	        	title="${message(code: 'default.itemPurchase.AssetCode.label')}" />
	                                              
	                   	        <g:sortableColumn property="OrderNo" 
	                   	        	title="${message(code: 'default.itemPurchase.OrderNo.label')}" />
	                	       
	                   	       	<g:sortableColumn property="billNo" 
	                   	       		title="${message(code: 'default.itemPurchase.BillNo.label')}" />
	                   	       	
	                   	       	<g:sortableColumn property="itemPurchaseInstance.cost" 
	                   	       		title="${message(code: 'default.itemPurchase.Cost(Rs).label')}" />
	                   	      
	                   	         <th><g:message code="default.Edit.label"/></th>
	           	             </tr>
	   	               </thead>
	   	               <tbody>	
					       <g:each in="${itemPurchaseInstanceList}" status="i" var="itemPurchaseInstance">
							       <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
										<td>${i+1}</td>
								        <td>${fieldValue(bean: itemPurchaseInstance, field: "Name")}</td>
								        
								        <td>${fieldValue(bean: itemPurchaseInstance, field: "AssetCode")}</td>
								        
								        <td>${fieldValue(bean: itemPurchaseInstance, field: "OrderNo")}</td>
								        
								        <td>${fieldValue(bean: itemPurchaseInstance, field: "billNo")}</td>
								        
								        <td>${currencyFormat.ConvertToIndainRS(itemPurchaseInstance.cost)}</td>
								            <g:hiddenField name="id" value="${itemPurchaseInstance?.id}" />
								        
								        <td><g:link controller="itemPurchase" action="edit" 
								        			id="${itemPurchaseInstance.id}"><g:message code="default.Edit.label"/>
						        			</g:link>
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
