


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'externalFundAllocation.label', default: 'ExternalFundAllocation')}" />
        <title><g:message code="default.editGrantFund.label" args="[entityName]" /></title>
    </head>
    <body>
       
        <div class="body">
        <g:subMenuList/>
        <div class="wrapper">
            <h1><g:message code="default.editGrantFund.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${externalFundAllocationInstance}">
            <div class="errors">
                <g:renderErrors bean="${externalFundAllocationInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                      <input type=hidden name="id" id = "id" value="${externalFundAllocationInst.id}"> 
                       <input type=hidden name="projects" id = "projects" value="${grantAllocationInstance.projects.id}">
                     <tr class="prop">
                       <td>
				        <label for="party">
							<g:message code="default.ExternalAgency.label"/>:</label>
						</td>
						 <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'party','errors')}">
				          <strong>${grantAllocationInstance?.granter.code}</strong> 
						 <input type=hidden name="granter" id = "granter" value="${grantAllocationInstance?.granter.id}">
				        </td>
				        
			    </tr>
                      
			         <tr >
				       <td valign="top" class="name">
					   	<label for="dateOfAllocation"><g:message code="default.DateOfGranting.label"/>: </label>
					   </td>
					   <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance?.dateOfAllocation,field:'dateOfAllocation','errors')}">
					   	<calendar:datePicker id="dateOfAllocation" name="dateOfGranting" defaultValue="${new Date()}" value="${grantAllocationInstance?.dateOfAllocation}" dateFormat= "%d/%m/%Y"   />
					   </td>
					   <td valign="top" class="name">
						    <label for="sanctionOrderNo"><g:message code="default.GrantAllocation.SanctionOrderNo.label"/>:</label>
						    <label for="sanctionOrderNo" style="color:red;font-weight:bold"> * </label>
					   </td>
					   <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance?.sanctionOrderNo,field:'sanctionOrderNo','errors')}">
							<input type="text" id="sanctionOrderNo" name="sanctionOrderNo" value="${grantAllocationInstance?.sanctionOrderNo}" style="text-align: right"/>
						</td>
				    </tr>    
			               
			       <!-- <tr class="prop">
			          
			               <td valign="top" class="name">
			                <label for="refundable"><g:message code="default.TypeOfGrant.label"  /></label>
			                 <label for="refundable" style="color:red;font-weight:bold"> * </label>
			            </td>
			             <td valign="top" class="value ${hasErrors(bean:externalFundAllocationInst,field:'refundable','errors')}">
			                <g:select name="refundable" from="${['Y', 'N']}"  value="${fieldValue(bean:externalFundAllocationInst,field:'refundable')}" noSelection="['null':'-Select-']" />
			            </td>
			        </tr> -->
			        
			        <tr class="prop">
			           </td>
			              <td valign="top" class="name">
			                <label for="refundable"><g:message code="default.TypeOfGrant.label"  /></label>
			                 <label for="refundable" style="color:red;font-weight:bold"> * </label>
			            </td>
			             <td valign="top" class="value ${hasErrors(bean:externalFundAllocationInstance,field:'refundable','errors')}">
			             	<g:select  name="refundable" optionValue="key" optionKey="value" from ="${['Refundable':'Y', 'NotRefundable':'N']}"  value="${fieldValue(bean:externalFundAllocationInst,field:'refundable')}" noSelection="['null':'-Select-']" />
			            </td>
			            
			             <td valign="top" class="name">
				          <label for="amountAllocated"><g:message code="default.AmountAllocated(Rs).label"/>:</label>
						  <label for="amountAllocated" style="color:red;font-weight:bold"> * </label>
					  </td>
					  <td valign="top" class="value ${hasErrors(bean:externalFundAllocationInst?.grantAllocation?.amountAllocated,field:'amountAllocated','errors')}"> 
					  	<input type="text" id="amountAllocated" name="amountAllocated" value="${amount}" style="text-align: right"/>
					  </td>
					  
			       </tr>
			                        
			        <tr class="prop">
			            <td valign="top" class="name">
			                <label for="remarks"><g:message code="externalFundAllocation.remarks.label" default="Remarks" /></label>
			            </td>
			            <td valign="top" class="value ${hasErrors(bean: grantAllocationInstance, field: 'remarks', 'errors')}">
			                <g:textArea name="remarks" value="${grantAllocationInstance?.remarks}" />
			            </td>
			         </tr>        
                           
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="agencyUpdate" id="${fieldValue(bean:externalFundAllocationInstance, field:'id')}" onClick="return validateEditGrantFund()" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                 </div>
            </g:form>
        </div>
    </body>
</html>
