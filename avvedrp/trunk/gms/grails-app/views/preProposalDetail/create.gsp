


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'preProposalDetail.label', default: 'PreProposalDetail')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${preProposalDetailInstance}">
            <div class="errors">
                <g:renderErrors bean="${preProposalDetailInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="field"><g:message code="preProposalDetail.field.label" default="Field" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: preProposalDetailInstance, field: 'field', 'errors')}">
                                    <g:textField name="field" value="${preProposalDetailInstance?.field}" />
                                </td>
                            
                                <td valign="top" class="name">
                                    <label for="value"><g:message code="preProposalDetail.value.label" default="Value" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: preProposalDetailInstance, field: 'value', 'errors')}">
                                    <g:textField name="value" value="${preProposalDetailInstance?.value}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="preProposalSubmittedDate"><g:message code="preProposalDetail.preProposalSubmittedDate.label" default="Pre Proposal Submitted Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: preProposalDetailInstance, field: 'preProposalSubmittedDate', 'errors')}">
                                    <g:datePicker name="preProposalSubmittedDate" precision="day" value="${preProposalDetailInstance?.preProposalSubmittedDate}" noSelection="['': '']" />
                                </td>
                          
                               <td valign="top" class="name">
                                    <label for="defaultYesNo"><g:message code="default.Active.label" default="Active"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:preProposalDetailInstance,field:'activeYesNo','errors')}">
                                    <g:select name="activeYesNo" from="${['Y', 'N']}"  value="${fieldValue(bean:preProposalDetailInstance,field:'activeYesNo')}" />
                                </td>   
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="preProposal"><g:message code="preProposalDetail.preProposal.label" default="Pre Proposal" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: preProposalDetailInstance, field: 'preProposal', 'errors')}">
                                    <g:select name="preProposal.id" from="${PreProposal.list()}" optionKey="id" value="${preProposalDetailInstance?.preProposal?.id}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
        
        <div class="body">
             <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
             <g:if test="${PreProposalDetail.list(params)}">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'preProposalDetail.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="field" title="${message(code: 'preProposalDetail.field.label', default: 'Field')}" />
                        
                            <g:sortableColumn property="value" title="${message(code: 'preProposalDetail.value.label', default: 'Value')}" />
                        
                            <g:sortableColumn property="preProposalSubmittedDate" title="${message(code: 'preProposalDetail.preProposalSubmittedDate.label', default: 'Pre Proposal Submitted Date')}" />
                        
                            <g:sortableColumn property="activeYesNo" title="${message(code: 'preProposalDetail.activeYesNo.label', default: 'Active Yes No')}" />
                        
                            <th><g:message code="preProposalDetail.preProposal.label" default="Pre Proposal" /></th>
                        
                          <g:sortableColumn property="edit" title="${message(code: 'preProposal.edit.label', default: 'Edit')}" />
                        
                        </tr>
                     </thead>
                    <tbody>
                    <g:each in="${PreProposalDetail.list(params)}" status="i" var="preProposalDetailInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${i+1}</td>
                        
                            <td>${fieldValue(bean: preProposalDetailInstance, field: "field")}</td>
                        
                            <td>${fieldValue(bean: preProposalDetailInstance, field: "value")}</td>
                        
                            <td><g:formatDate date="${preProposalDetailInstance.preProposalSubmittedDate}" /></td>
                        
                            <td>${fieldValue(bean: preProposalDetailInstance, field: "activeYesNo")}</td>
                        
                            <td>${fieldValue(bean: preProposalDetailInstance, field: "preProposal")}</td>
                            
                            <td><g:link action="edit" id="${fieldValue(bean:preProposalDetailInstance, field:'id')}">Edit</g:link></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
                 </g:if>
            </div>
    </body>
</html>
