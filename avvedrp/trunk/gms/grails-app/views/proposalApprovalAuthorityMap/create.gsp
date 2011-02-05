


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:javascript library="jquery" />  
        <g:set var="entityName" value="${message(code: 'proposalApprovalAuthorityMap.label', default: 'ProposalApprovalAuthorityMap')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="wrapper">
            
        <div class="body">
            <h1><g:message code="default.AssignProposalToApprovalAuthority.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${proposalApprovalAuthorityMapInstance}">
            <div class="errors">
                <g:renderErrors bean="${proposalApprovalAuthorityMapInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="proposalType"><g:message code="proposalApprovalAuthorityMap.proposalType.label" default="Proposal Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalAuthorityMapInstance, field: 'proposalType', 'errors')}">
                                    <g:select name="proposalType" from="${['PreProposal','FullProposal']}" value="${proposalApprovalAuthorityMapInstance?.proposalType}" 
                                    onchange="${remoteFunction(controller:'proposalApprovalAuthorityMap',action:'getProposals',update:'proposalSel',params:'\'proposal=\'+this.value')};" />
                                </td>
                            </tr>
                     
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="proposalId"><g:message code="proposalApprovalAuthorityMap.proposalId.label" default="Proposal Title" /></label>
                                </td>
                                
                                <td id="proposalSel" valign="top" class="value ${hasErrors(bean: proposalApprovalAuthorityMapInstance, field: 'proposalId', 'errors')}">
                                    <g:select name="proposalId" from="${preProposalList}" optionKey="id" optionValue="projectTitle"  value="${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: 'proposalId')}" />
                                    
                                </td>
                                
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="approvalAuthority"><g:message code="proposalApprovalAuthorityMap.approvalAuthority.label" default="Approval Authority" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalAuthorityMapInstance, field: 'approvalAuthority', 'errors')}">
                                    <g:select name="approvalAuthority.id" from="${approvalAuthorityInstance}" optionKey="id" optionValue = "name" value="${proposalApprovalAuthorityMapInstance?.approvalAuthority?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="approveOrder"><g:message code="proposalApprovalAuthorityMap.approveOrder.label" default="Approve Order" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalAuthorityMapInstance, field: 'approveOrder', 'errors')}">
                                    <g:textField name="approveOrder" value="${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: 'approveOrder')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="processRestartOrder"><g:message code="proposalApprovalAuthorityMap.processRestartOrder.label" default="Process Restart Order" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalAuthorityMapInstance, field: 'processRestartOrder', 'errors')}">
                                    <g:textField name="processRestartOrder" value="${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: 'processRestartOrder')}" />
                                </td>
                            </tr>
                        
                            
                            
                        
                            
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" onClick="return validateApprovalAuthorityMap()" value="${message(code: 'default.Create.button')}" /></span>
                </div>
            </g:form>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'proposalApprovalAuthorityMap.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="proposalType" title="Proposal Type" />
                            <g:sortableColumn property="proposalType" title="Project Title" />
                            <g:sortableColumn property="proposalType" title="${message(code: 'proposalApprovalAuthorityMap.proposalType.label', default: 'Approval Authority')}" />
                        
                                                    
                            <g:sortableColumn property="approveOrder" title="${message(code: 'proposalApprovalAuthorityMap.approveOrder.label', default: 'Approve Order')}" />
                        
                            <g:sortableColumn property="processRestartOrder" title="${message(code: 'proposalApprovalAuthorityMap.processRestartOrder.label', default: 'Process Restart Order')}" />
                        
                            <g:sortableColumn property="remarks" title="${message(code: 'proposalApprovalAuthorityMap.remarks.label', default: 'Remarks')}" />
                        
                        	<th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${proposalApprovalAuthorityMapInstanceList}" status="i" var="proposalApprovalAuthorityMapInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${(i +1)}</td>
                        
                            <td>${proposalApprovalAuthorityMapInstance.proposalType}</td>
                            <g:if test="${proposalApprovalAuthorityMapInstance?.proposalType=='PreProposal'}">
                            <%def proposal = PreProposal.get(proposalApprovalAuthorityMapInstance.proposalId)%>
                        	<td>${proposal?.projectTitle}</td>
                        	</g:if>
                        	<g:elseif test="${proposalApprovalAuthorityMapInstance?.proposalType=='FullProposal'}">
                        	<%def proposal = FullProposal.get(proposalApprovalAuthorityMapInstance.proposalId)%>
                        	<td>${proposal?.preProposal?.projectTitle}</td>
                        	</g:elseif>
                        	
                            <td>${proposalApprovalAuthorityMapInstance.approvalAuthority.name}</td>                    
                            <td>${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: "approveOrder")}</td>
                        
                            <td>${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: "processRestartOrder")}</td>
                        
                            <td>${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: "remarks")}</td>
                        	
                        	<td><g:link action="edit" id="${proposalApprovalAuthorityMapInstance.id}">Edit</g:link></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>
        </div>
    </body>
</html>
