


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<g:set var="entityName" value="${message(code: 'externalFundAllocation.label', default: 'ExternalFundAllocation')}" />
<title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>
  <div class="wrapper"> 
     <g:subMenuList/>
<div class="body">
<h1><g:message code="default.ExternalFundAllocationList.label" /></h1>
<g:if test="${flash.message}">
<div class="message">${flash.message}</div>
</g:if>
<g:hasErrors bean="${externalFundAllocationInstance}">
<div class="errors">
<g:renderErrors bean="${externalFundAllocationInstance}" as="list" />
</div>
</g:hasErrors>
          <div class="list">
            <g:if test="${grantAllocationInstance}">
                <table>
                    <thead>
                        <tr>
                           <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                                                  
                            <th><g:message code="default.Grantor.label" /></th>
                                                        
                            <th><g:message code="default.AmountAllocated(Rs).label"/></th>
                            
                            <g:sortableColumn property="receiveExternalFund" title="${message(code: 'default.receiveExternalFund.label')}" />
                            
                            <g:sortableColumn property="viewExpenses" title="${message(code: 'default.viewExpenses.label')}" />
                            
                            <g:sortableColumn property="refundGrant" title="${message(code: 'default.refundGrant.label')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                   
                    <g:each in="${grantAllocationInstance}" status="i" var="grantAlocationInstance">
                         <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${i+1}</td>
                           
                            <td>${fieldValue(bean: grantAlocationInstance, field: "granter.code")}</td>
                            
                            <td>${currencyFormat.ConvertToIndainRS(grantAlocationInstance.amountAllocated)}</td>
                                                    
                       	    <td><g:link action="receiveExternalFund" params="[projectId:grantAlocationInstance.projects.id]" id="${fieldValue(bean:grantAlocationInstance, field:'id')}"><g:message code="default.receiveExternalFund.label"/></g:link></td>
                        
                       	    <td><g:link action="viewExpenses" id="${fieldValue(bean:grantAlocationInstance, field:'id')}"><g:message code="default.viewExpenses.label"/></g:link></td>
                       	   
                       	    <td><g:link controller="externalFundRefund" action="create" id="${fieldValue(bean:grantAlocationInstance, field:'id')}"><g:message code="default.refundGrant.label"/></g:link></td>
                       	 
                        </tr>
                    </g:each>
                    </tbody>
                </table>
           </g:if>
           <g:else>
		   	<br><g:message code="default.NoRecordsAvailable.label"/></br>
		   </g:else>
            </div>
</body>
</html>
