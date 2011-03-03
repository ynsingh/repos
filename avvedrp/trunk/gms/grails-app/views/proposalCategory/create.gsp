


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'proposalCategory.label', default: 'ProposalCategory')}" />
        <title><g:message code="default.ProposalCategory.label" args="[entityName]" /></title>
    </head>
    <body>
       <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.NewProposalCategory.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${proposalCategoryInstance}">
            <div class="errors">
                <g:renderErrors bean="${proposalCategoryInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="default.Name.label" />:</label>
                                    <label for="name" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalCategoryInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${proposalCategoryInstance?.name}" />
                                </td>
                            </tr>
                            <tr class="prop">
                            
                             <td valign="top" class="name">
			                     <label for="defaultYesNo"><g:message code="default.Active.label" />:</label>
			                       </td>
		                  <td valign="top" class="value ${hasErrors(bean:proposalCategoryInstance,field:'activeYesNo','errors')}">
		                  <g:select name="activeYesNo" from="${['Y', 'N']}"  value="${fieldValue(bean:proposalCategoryInstance,field:'activeYesNo')}" />
                                </td>  
                                 </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="remarks"><g:message code="default.Remarks.label" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalCategoryInstance, field: 'remarks', 'errors')}">
                                    <g:textArea name="remarks" value="${proposalCategoryInstance?.remarks}" />
                                </td>
                            </tr>
                        
                            
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" onClick="return validateProposalCategory()" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
        
        <div class="body">
            <h1><g:message code="default.ProposalCategoryList.label" args="[entityName]" /></h1>
            
            <div class="list">
            <g:if test="${ProposalCategory.list(params)}">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'default.Name.label')}" />
                        
                            <g:sortableColumn property="remarks" title="${message(code: 'default.Remarks.label')}" />
                        
                            <g:sortableColumn property="activeYesNo" title="${message(code: 'default.Active.label')}" />
                        
                          	<th><g:message code="default.Edit.label"/></th>
							
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${ProposalCategory.list(params)}" status="i" var="proposalCategoryInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                           <td>${i+1}</td>
                        
                            <td>${fieldValue(bean: proposalCategoryInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: proposalCategoryInstance, field: "remarks")}</td>
                        
                            <td>${fieldValue(bean: proposalCategoryInstance, field: "activeYesNo")}</td>
                            
                          <td><g:link action="edit" id="${fieldValue(bean:proposalCategoryInstance, field:'id')}"><g:message code="default.Edit.label" /></g:link></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
                  </g:if>
            </div>
    </body>
</html>
