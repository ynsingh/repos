


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
        <img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help">
            <h1><g:message code="default.AssignProposalToApprovalAuthority.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:if test="${flash.error}">
            <div class="errors">${flash.error}</div>
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
                                    <label for="proposalType"><g:message code="default.ProposalType.label"/>:</label>
                                    <label for="proposalType" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalAuthorityMapInstance, field: 'proposalType', 'errors')}">
                                    <g:select name="proposalType" from="${['PreProposal','FullProposal']}" value="${proposalApprovalAuthorityMapInstance?.proposalType}" 
                                    onchange="${remoteFunction(controller:'proposalApprovalAuthorityMap',action:'getProposals',update:'proposalSel',params:'\'proposal=\'+this.value')};" noSelection="['null':'-Select-']" />
                                </td>
                            </tr>
                     
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="proposalId"><g:message code="default.ProposalTitle.label"/>:</label>
                                    <label for="proposalId" style="color:red;font-weight:bold"> * </label>
                                </td>
                                
                                <td id="proposalSel" valign="top" class="value ${hasErrors(bean: proposalApprovalAuthorityMapInstance, field: 'proposalId', 'errors')}">
                                    <g:select name="proposalId" from="${proposalApplicationList}" optionKey="id" optionValue="projectTitle"  value="${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: 'proposalId')}" noSelection="['null':'-Select-']"/>
                                    
                                </td>
                                
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="approvalAuthority"><g:message code="default.ApprovalAuthority.label"/>:</label>
                                    <label for="approvalAuthority" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalAuthorityMapInstance, field: 'approvalAuthority', 'errors')}">
                                    <g:select name="approvalAuthority.id" from="${approvalAuthorityInstance}" optionKey="id" optionValue = "name" value="${proposalApprovalAuthorityMapInstance?.approvalAuthority?.id}"  noSelection="['null':'-Select-']"/>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="approveOrder"><g:message code="default.ApproveOrder.label" />:</label>
                                     <label for="approveOrder" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApprovalAuthorityMapInstance, field: 'approveOrder', 'errors')}">
                                    <g:textField name="approveOrder" value="${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: 'approveOrder')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="processRestartOrder"><g:message code="default.ProcessRestartOrder.label" />:</label>
                                    <label for="processRestartOrder" style="color:red;font-weight:bold"> * </label>
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                        
                            <g:sortableColumn property="proposalType" title="${message(code: 'default.ProposalType.label')}" />
                            <g:sortableColumn property="proposalType" title="${message(code: 'default.ProposalTitle.label')}" />
                            <g:sortableColumn property="proposalType" title="${message(code: 'default.ApprovalAuthority.label')}" />
                        
                                                    
                            <g:sortableColumn property="approveOrder" title="${message(code: 'default.ApproveOrder.label')}" />
                        
                            <g:sortableColumn property="processRestartOrder" title="${message(code: 'default.ProcessRestartOrder.label')}" />
                        
                            <g:sortableColumn property="remarks" title="${message(code: 'default.Remarks.label')}" />
                        
                        	<g:sortableColumn property="edit" title="${message(code: 'default.Edit.label')}" />
                        </tr>
                    </thead>
                    <tbody>
                    
                    <g:each in="${proposalApprovalAuthorityMapInstanceList}" status="i" var="proposalApprovalAuthorityMapInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${(i +1)}</td>
                        
                            <td>${proposalApprovalAuthorityMapInstance.proposalType}</td>
                            <g:if test="${proposalApprovalAuthorityMapInstance?.proposalType=='PreProposal'}">
                            <%def proposalApplication =  ProposalApplication.find("from ProposalApplication P where P.proposal.id="+proposalApprovalAuthorityMapInstance.proposalId)%>
                        	<td>${proposalApplication?.projectTitle}</td>
                        	
                        	</g:if>
                        	<g:elseif test="${proposalApprovalAuthorityMapInstance?.proposalType=='FullProposal'}">
                        	<%def proposal = ProposalApplication.find("from ProposalApplication P where P.proposal.id="+proposalApprovalAuthorityMapInstance.proposalId)%>
                        	<td>${proposal?.projectTitle}</td>
                        	</g:elseif>
                        	
                            <td>${proposalApprovalAuthorityMapInstance.approvalAuthority.name}</td>                    
                            <td>${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: "approveOrder")}</td>
                        
                            <td>${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: "processRestartOrder")}</td>
                        
                            <td>${fieldValue(bean: proposalApprovalAuthorityMapInstance, field: "remarks")}</td>
                        	
                        	<td><g:link action="edit" id="${proposalApprovalAuthorityMapInstance.id}"><g:message code="default.Edit.label"/></g:link></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>
        </div>
    </body>
</html>
