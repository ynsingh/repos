


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'budgetMaster.label', default: 'BudgetMaster')}" />
        <title><g:message code="default.Budgetmasteredit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="wrapper">
       <div class="body">
            <h1><g:message code="default.Budgetmasteredit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${budgetMasterInstance}">
            <div class="errors">
                <g:renderErrors bean="${budgetMasterInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${budgetMasterInstance?.id}" />
                <g:hiddenField name="version" value="${budgetMasterInstance?.version}" />
                <div class="dialog">
                    <table>
                       <tbody>
                        
                            <tr class="prop">
                              <td valign="top" class="name">
                                    <label for="party"><g:message code="default.budgetMaster.party.label" /></label>
                                </td>
                                 <td valign="top" class="value ${hasErrors(bean:budgetMasterInstance, field:'party', 'errors')}">
                                     <strong>  ${partyInstance.code} </strong>
                                     <input type="hidden" id="party.id" name="party.id" value="${fieldValue(bean:partyInstance,field:'id')}"/>
                                 </td>  
                            
                                <td valign="top" class="name">
                                    <label for="financialYear"><g:message code="default.budgetMaster.financialYear.label" /></label>
                                    <label for="financialYear" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: budgetMasterInstance, field: 'financialYear', 'errors')}">
                                    <g:select name="financialYear.id" from="${FinancialYear.list()}" optionKey="id" optionValue="financialPeriod" value="${budgetMasterInstance?.financialYear?.id}" noSelection="['null': '-Select-']" />
                                </td>
                               
                            </tr>
                       
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="budgetCode"><g:message code="default.budgetMaster.budgetCode.label" /></label>
                                    <label for="budgetCode" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: budgetMasterInstance, field: 'budgetCode', 'errors')}">
                                    <g:textField name="budgetCode" value="${budgetMasterInstance?.budgetCode}" />
                                </td>
                                
                               <td valign="top" class="name">
                                    <label for="title"><g:message code="default.budgetMaster.title.label" /></label>
                                    <label for="title" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: budgetMasterInstance, field: 'title', 'errors')}">
                                    <g:textField name="title" value="${budgetMasterInstance?.title}" />
                                </td>
                                
                            </tr>
                        
                            <tr class="prop">
                                 <td valign="top" class="name">
                                    <label for="totalBudgetAmount"><g:message code="default.budgetMaster.totalBudgetAmount(Rs).label" /></label>
                                    <label for="totalBudgetAmount" style="color:red;font-weight:bold"> * </label>
                                 </td>
                                 <td valign="top" class="value ${hasErrors(bean: budgetMasterInstance, field: 'totalBudgetAmount', 'errors')}">
                                    <g:textField name="totalBudgetAmount" value="${amount}" style="text-align: right" format="0.00" />
                                 </td>
                                
                                <td valign="top" class="name">
                                    <label for="budgetDescription"><g:message code="default.budgetMaster.budgetDescription.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: budgetMasterInstance, field: 'budgetDescription', 'errors')}">
                                    <g:textArea name="budgetDescription" value="${budgetMasterInstance?.budgetDescription}" style='width: 200px; height:75px;'/>
                                </td>
                            </tr>
                                                                            
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" onClick="return validateMaster()" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                </div>
            </g:form>
        </div>
        </div>
    </body>
</html>
