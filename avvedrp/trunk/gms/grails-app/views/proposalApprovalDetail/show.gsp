

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'proposalApprovalDetail.label', default: 'ProposalApprovalDetail')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="proposalApprovalDetail.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: proposalApprovalDetailInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="proposalApprovalDetail.proposalDetailId.label" default="Proposal Detail Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: proposalApprovalDetailInstance, field: "proposalDetailId")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="proposalApprovalDetail.proposalStatus.label" default="Proposal Status" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: proposalApprovalDetailInstance, field: "proposalStatus")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="proposalApprovalDetail.approvalDate.label" default="Approval Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${proposalApprovalDetailInstance?.approvalDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="proposalApprovalDetail.moreComments.label" default="More Comments" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: proposalApprovalDetailInstance, field: "moreComments")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="proposalApprovalDetail.remarks.label" default="Remarks" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: proposalApprovalDetailInstance, field: "remarks")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="proposalApprovalDetail.activeYesNo.label" default="Active Yes No" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: proposalApprovalDetailInstance, field: "activeYesNo")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="proposalApprovalDetail.proposalApproval.label" default="Proposal Approval" /></td>
                            
                            <td valign="top" class="value"><g:link controller="proposalApproval" action="show" id="${proposalApprovalDetailInstance?.proposalApproval?.id}">${proposalApprovalDetailInstance?.proposalApproval?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${proposalApprovalDetailInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
