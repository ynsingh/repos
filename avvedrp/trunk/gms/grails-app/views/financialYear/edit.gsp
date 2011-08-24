


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'financialYear.label', default: 'FinancialYear')}" />
        <title><g:message code="default.Financialyearedit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.Financialyearedit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${financialYearInstance}">
            <div class="errors">
                <g:renderErrors bean="${financialYearInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${financialYearInstance?.id}" />
                <g:hiddenField name="version" value="${financialYearInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                         <tr class="prop">
                             <td valign="top" class="name">
                                    <label for="financialPeriod"><g:message code="default.financialYear.financialYear.label" /></label>
                                    <label for="financialStartDate" style="color:red;font-weight:bold"> * </label> 
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
                             	<calendar:datePicker name="financialStartDate" value="${financialYearInstance?.financialStartDate}" dateFormat= "%d/%m/%Y" />
				      		  </td>
                            </tr> 
                            
                            
                               <tr class="prop">
                                <td valign="top" class="name">
                                   <label for="financialEndDate"><g:message code="default.EndDate.label"/>:</label>
                                   <label for="financialEndDate" style="color:red;font-weight:bold"> * </label>  
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:financialYearInstance,field:'financialEndDate','errors')}">
                              	 <calendar:datePicker name="financialEndDate" value="${financialYearInstance?.financialEndDate}"  dateFormat= "%d/%m/%Y"/>
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
                  <span class="button"><g:actionSubmit class="save" action="update" onClick="return validateFinancialYear()" value="${message(code: 'default.Update.button')}" /></span>
		        </div>
            </g:form>
        </div>
        </div>
    </body>
</html>
