
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'proposalApproval.label', default: 'ProposalApproval')}" />
        <title><g:message code=" default.ExpenseRequestReviewerStatusList.label"/></title>
    </head>
    <body>
       <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.ExpenseRequestReviewerStatusList.label" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:if test="${proposalStatusList}">
            <div class="list">
                <table>
                    <thead>
                        <tr>
                           <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
						   <th><g:message code ="default.Projects.label" /></th>
                           <th><g:message code="default.ExpenseDescription.label" /></th>
                           <th><g:message code="default.ExpenseAmount.label" /></th>
                           <th> <g:message code="default.ReviewerStatus.label" /></th>
						   <th><g:message code ="default.Comments.label" /></th>
                        </tr>    
                    </thead>
                      <tbody>
                      
               <g:each in="${expenseRequestEntryList}" status="i" var="expenseRequestEntryInstance">
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                      <td>${i+1}</td>
					  <td>${fieldValue(bean: expenseRequestEntryInstance, field: "projects.code")}</td>
                      <td>${fieldValue(bean: expenseRequestEntryInstance, field: "expenseDescription")}</td>
                      <td>${currencyFormat.ConvertToIndainRS(expenseRequestEntryInstance.expenseAmount)}</td>
                      <g:if test="${proposalStatusList[i]}">
					  <td>${fieldValue(bean: proposalStatusList[i], field: "proposalStatus")}</td>
                      <td>${fieldValue(bean: proposalStatusList[i], field: "remarks")}</td>
                      </g:if>
                      <g:else>
                      <td></td>
                      <td></td>
                      </g:else>
					</tr>
                      </g:each>
                
                      </tbody>
                </table>
            </div>
            </g:if>
            <g:else>
            <g:message code="default.Noreview.label"/>
            </g:else>
        </div>
        </div>
    </body>
</html>
                      