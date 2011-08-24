


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'default.financialYear.label')}" />
        <title><g:message code="default.Financialyearcreate.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="wrapper">
       <div class="body">
            <h1><g:message code="default.Financialyearcreate.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${financialYearInstance}">
            <div class="errors">
                <g:renderErrors bean="${financialYearInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                         	  <tr class="prop">
                                <td valign="top" class="name">
    							   <label for="financialPeriod"><g:message code="default.financialYear.financialYear.label" /></label>
    							   <label for="financialPeriod" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: financialYearInstance, field: 'financialPeriod', 'errors')}">
                                    <g:textField name="financialPeriod" value="${financialYearInstance?.financialPeriod}" />
                                </td>
                              </tr> 
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="financialStartDate"><g:message code="default.StartDate.label"/>:</label>
                                    <label for="financialStartDate" style="color:red;font-weight:bold"> * </label> 
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:financialYearInstance,field:'financialStartDate','errors')}">
                                <calendar:datePicker id="financialStartDate" name="financialStartDate" defaultValue="${new Date()}" value="${financialYearInstance?.financialStartDate}" dateFormat= "%d/%m/%Y"/>
                                    </td>
                            </tr> 
                            
                            
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="financialEndDate"><g:message code="default.EndDate.label"/>:</label>
                                    <label for="financialEndDate" style="color:red;font-weight:bold"> * </label> 
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:financialYearInstance,field:'financialEndDate','errors')}">
                                    <calendar:datePicker id="financialEndDate" name="financialEndDate" value="${financialYearInstance?.financialEndDate}" defaultValue="${new Date()}"  dateFormat= "%d/%m/%Y"/>
                                </td>
                            </tr>
                         
                         
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description"><g:message code="default.financialYear.description.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: financialYearInstance, field: 'description', 'errors')}">
                                    <g:textArea name="description" value="${financialYearInstance?.description}" style='width: 200px; height:75px;' />
                                </td>
                            </tr>
                        
                         
                          
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                       <span class="button"><g:submitButton name="create" class="save"  onClick="return validateFinancialYear()" value="${message(code: 'default.Create.button')}" /></span>
                </div>
            </g:form>
        </div>
         <div class="wrapper">
       <div class="body">
            <h1><g:message code="default.Financialyearlist.label" args="[entityName]" /></h1>
         
            <div class="list">
                <table>
                    <thead>
                        <tr>
                            <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
                        
                            <g:sortableColumn property="financialYear" title="${message(code: 'default.financialYear.financialYear.label')}" />
                        
                            <g:sortableColumn property="financialStartDate" title="${message(code: 'default.StartDate.label')}" />
                        
                            <g:sortableColumn property="financialEndDate" title="${message(code: 'default.EndDate.label')}" />
                        
                            <g:sortableColumn property="description" title="${message(code: 'default.financialYear.description.label')}" />
                        
                            <g:sortableColumn property="edit" title="${message(code: 'default.Edit.label')}" />
                          
                       </tr>
                    </thead>
                    <tbody>
                    <g:each in="${financialYearInstanceList}" status="i" var="financialYearInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${i+1}</td>
                          
                            <td>${fieldValue(bean: financialYearInstance, field: "financialPeriod")}</td>
                           
                            <td><g:formatDate format="dd/MM/yyyy" date="${financialYearInstance.financialStartDate}"/></td>
                            
                            <td><g:formatDate format="dd/MM/yyyy" date="${financialYearInstance.financialEndDate}"/></td>
                         
                            <td>${fieldValue(bean: financialYearInstance, field: "description")}</td>
                      
                           <td><g:link action="edit" id="${fieldValue(bean:financialYearInstance, field:'id')}"><g:message code="default.Edit.label"/></g:link></td>
                      
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            
            </div>
        </div>
        </div>
    </body>
</html>
