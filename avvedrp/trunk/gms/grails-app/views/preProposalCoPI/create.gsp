


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'preProposalCoPI.label', default: 'PreProposalCoPI')}" />
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
            <g:hasErrors bean="${preProposalCoPIInstance}">
            <div class="errors">
                <g:renderErrors bean="${preProposalCoPIInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="coPiCommitments"><g:message code="preProposalCoPI.coPiCommitments.label" default="Co Pi Commitments" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: preProposalCoPIInstance, field: 'coPiCommitments', 'errors')}">
                                    <g:textField name="coPiCommitments" value="${preProposalCoPIInstance?.coPiCommitments}" />
                                </td>
                       
                                <td valign="top" class="name">
                                    <label for="faculty"><g:message code="preProposalCoPI.faculty.label" default="Faculty" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: preProposalCoPIInstance, field: 'faculty', 'errors')}">
                                    <g:select name="faculty.id" from="${Faculty.list()}" optionKey="id" value="${preProposalCoPIInstance?.faculty?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="preProposal"><g:message code="preProposalCoPI.preProposal.label" default="Pre Proposal" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: preProposalCoPIInstance, field: 'preProposal', 'errors')}">
                                    <g:select name="preProposal.id" from="${PreProposal.list()}" optionKey="id" value="${preProposalCoPIInstance?.preProposal?.id}"  />
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
             <g:if test="${PreProposalCoPI.list(params)}">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'preProposalCoPI.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="coPiCommitments" title="${message(code: 'preProposalCoPI.coPiCommitments.label', default: 'Co Pi Commitments')}" />
                        
                            <th><g:message code="preProposalCoPI.faculty.label" default="Faculty" /></th>
                        
                            <th><g:message code="preProposalCoPI.preProposal.label" default="Pre Proposal" /></th>
                            
                            <g:sortableColumn property="edit" title="${message(code: 'preProposal.edit.label', default: 'Edit')}" />
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${PreProposalCoPI.list(params)}" status="i" var="preProposalCoPIInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        <td>${i+1}</td>
                        
                           
                            <td>${fieldValue(bean: preProposalCoPIInstance, field: "coPiCommitments")}</td>
                        
                            <td>${fieldValue(bean: preProposalCoPIInstance, field: "faculty")}</td>
                        
                            <td>${fieldValue(bean: preProposalCoPIInstance, field: "preProposal")}</td>
                            
                            
                            <td><g:link action="edit" id="${fieldValue(bean:preProposalCoPIInstance, field:'id')}">Edit</g:link></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
                 </g:if>
            </div>
    </body>
</html>
