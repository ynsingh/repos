


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<g:set var="entityName" value="${message(code: 'externalFundAllocation.label', default: 'ExternalFundAllocation')}" />
<title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>
  <div class ="wrapper">
<div class="body">
<h1><g:message code="default.GrantFund.label" args="[entityName]" /></h1>
<g:if test="${flash.message}">
<div class="message">${flash.message}</div>
</g:if>
<g:if test="${flash.error}">
<div class="errors">${flash.error}</div>
</g:if>
<g:hasErrors bean="${externalFundAllocationInstance}">
<div class="errors">
<g:renderErrors bean="${externalFundAllocationInstance}" as="list" />
</div>
</g:hasErrors>
<g:form action="save" method="post" >
<div class="dialog">
<table>
    <tbody>
    
      <input type="hidden" id="amount" name="amount" value="${partyInstance}"/>  
         <tr class="prop">
	       <td>
	        <label for="party">
				<g:message code="default.GrantAllocation.Recipient.label"/>:</label>
			<label for="party" style="color:red;font-weight:bold"> * </label>
			</td>
	        <td valign="top" class="value ${hasErrors(bean:grantAllocationInstance,field:'party','errors')}">
	          <g:select id="recipient" name="recipient" optionKey="id" optionValue="code"  from="${Party.findAll('from Party P where P.activeYesNo=\'Y\' and P.partyType is null ')}" value="${externalFundAllocationInstance?.grantAllocation?.party}" 
	          onchange="${remoteFunction(controller:'externalFundAllocation',action:'getRecipientProjects',update:'projectSelect',params:'\'party=\'+this.value')};" noSelection="['null':'-Select-']" />
	        </td>
            <td valign="top" class="name">
                <label for="projects"><g:message code="externalFundAllocation.Projects.label" default="Projects" /></label>
                <label for="projects" style="color:red;font-weight:bold"> * </label>
            </td>
          <td id="projectSelect" valign="top" class="value ${hasErrors(bean: externalFundAllocationInstance?.grantAllocation?.projects, field: 'projects', 'errors')}">
             <g:select name="projects" from="${projectList}"  optionKey="id" optionValue="code" value="${externalFundAllocationInstance?.grantAllocation?.projects}" noSelection="['null':'-Select-']" />
       </td>
        </tr>
       
       <tr >
	       <td valign="top" class="name">
		   	<label for="dateOfAllocation"><g:message code="default.DateOfGranting.label"/>: </label>
		   </td>
		   <td valign="top" class="value ${hasErrors(bean:externalFundAllocationInstance.grantAllocation?.dateOfAllocation,field:'dateOfAllocation','errors')}">
		   	<calendar:datePicker id="dateOfAllocation" name="dateOfGranting" defaultValue="${new Date()}" value="${externalFundAllocationInstance.grantAllocation?.dateOfAllocation}" dateFormat= "%d/%m/%Y"   />
		   </td>
		   <td valign="top" class="name">
			    <label for="sanctionOrderNo"><g:message code="default.GrantAllocation.SanctionOrderNo.label"/>:</label>
			    <label for="sanctionOrderNo" style="color:red;font-weight:bold"> * </label>
		   </td>
		   <td valign="top" class="value ${hasErrors(bean:externalFundAllocationInstance.grantAllocation?.sanctionOrderNo,field:'sanctionOrderNo','errors')}">
				<input type="text" id="sanctionOrderNo" name="sanctionOrderNo" value="${externalFundAllocationInstance.grantAllocation?.sanctionOrderNo}" style="text-align: right"/>
			</td>
	  </tr>
	  
	  <tr class="prop">
           </td>
              <td valign="top" class="name">
                <label for="refundable"><g:message code="default.TypeOfGrant.label"  /></label>
                 <label for="refundable" style="color:red;font-weight:bold"> * </label>
            </td>
             <td valign="top" class="value ${hasErrors(bean:externalFundAllocationInstance,field:'refundable','errors')}">
             	<g:select  name="refundable" optionValue="key" optionKey="value" from ="${['Refundable':'Y', 'Non-Refundable':'N']}"  value="${fieldValue(bean:externalFundAllocationInstance,field:'refundable')}" noSelection="['null':'-Select-']" />
            </td>
            
             <td valign="top" class="name">
	          <label for="amountAllocated"><g:message code="default.AmountAllocated(Rs).label"/>:</label>
			  <label for="amountAllocated" style="color:red;font-weight:bold"> * </label>
		  </td>
		  <td valign="top" class="value ${hasErrors(bean:externalFundAllocationInstance.grantAllocation?.amountAllocated,field:'amountAllocated','errors')}"> 
		  	<input type="text" id="amountAllocated" name="amountAllocated" value="${allocatedAmount}" style="text-align: right"/>
		  </td>
		  
       </tr>
	  
        <tr class="prop">
            <td valign="top" class="name">
                <label for="remarks"><g:message code="externalFundAllocation.remarks.label" default="Remarks" /></label>
            </td>
            <td valign="top" class="value ${hasErrors(bean: externalFundAllocationInstance, field: 'remarks', 'errors')}">
                <g:textArea name="remarks" value="${externalFundAllocationInstance?.remarks}" />
            </td>
        </tr>
    
     
    
    </tbody>
</table>
</div>
<div class="buttons">
<span class="button"><g:submitButton name="create" class="save" onClick="return validateGrantFund()" value="${message(code: 'default.Create.button')}"  /></span>
</div>
</g:form>
</div>
<div class="body">
         <h1><g:message code="default.GrantFundList.label" args="[entityName]" /></h1>
         
         <div class="list">
            <g:if test="${allocationInstanceList}">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                                                  
                            <th><g:message code="default.GrantAllocation.Recipient.label" /></th>
                                                        
                            <th><g:message code="default.Projects.label" /></th>
                            
                            <th><g:message code="default.AmountAllocated(Rs).label"/></th>
                            
                            <th><g:message code="default.TypeOfGrant.label"/></th>
                            
                            <g:sortableColumn property="tranferFund" title="${message(code: 'default.fundTransfer.label')}" />
                            
                            <g:sortableColumn property="receiveReFund" title="${message(code: 'default.RefundList.head')}" />
                            
                            <g:sortableColumn property="edit" title="${message(code: 'default.Edit.label')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <% int l=0 %>
                    <g:each in="${allocationInstanceList}" status="i" var="grantAlocationInstance">
                         <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${i+1}</td>
                           
                            <td>${fieldValue(bean: grantAlocationInstance, field: "party.code")}</td>
                             
                            <td>${fieldValue(bean: grantAlocationInstance, field: "projects.code")}</td>
                            
                            <td>${currencyFormat.ConvertToIndainRS(grantAlocationInstance.amountAllocated)}</td>  
                            
                            <g:if test="${externalFundAllocationInstanceList[l].refundable == 'Y'}">
                            	<td>Refundable</td>  
                            </g:if>	
             				<g:else>
             					<td>NonRefundable</td>  
             				</g:else>
                            <%  l++ %>   
                                                 
                       	    <td><g:link action="tranferFund" id="${fieldValue(bean:grantAlocationInstance, field:'id')}"><g:message code="default.fundTransfer.label"/></g:link></td>
                        
                       	    <td><g:link controller="externalFundRefund" action="refundList" id="${fieldValue(bean:grantAlocationInstance, field:'id')}"><g:message code="default.RefundList.head"/></g:link></td>
                       	   
                       	    <td><g:link action="edit" id="${fieldValue(bean:grantAlocationInstance, field:'id')}"><g:message code="default.Edit.label"/></g:link></td>
                       	   
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
</body>
</html>
