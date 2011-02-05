

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.StatementOfAccountsReport.head"/></title>         
    </head>
    <body>
      <div class="wrapper"> 
      <g:subMenuList/>    
       <div class="proptable">
       </div>
         <table class="tablewrapper" cellspacing="0" cellpadding="0">
          <tr>
            <td>
               <div class="body">
                 <h1><g:message code="default.StatementOfAccountsReport.head"/></h1>
                 <g:hasErrors bean="${grantAllocationTrackingInstance}">
                   <div class="errors">
                     <g:renderErrors bean="${grantAllocationTrackingInstance}" as="list" />
                   </div>
                 </g:hasErrors>
                 <g:form action="showReports" name="viewStatementOfAccounts" controller="grantAllocation">
                    <div class="dialog">
                       <table>
                         <tbody>
                         
                            <tr class="prop">
                                <td valign="top" class="name">
                                   <label for="project"><g:message code="default.Projects.label"/></label>
                                </td>
                                <td valign="top">
                                   ${(Projects.get(session.ProjectID)).code}
                                </td>
                            </tr> 
                                             	
                            <tr>
								<td class='name'><g:message code="default.ReportDateFrom.label"/></td>
								<td> 
							        <input type="hidden" name="reportDate" value="struct" />

									<calendar:datePicker name="reportDate" id="reportDate" value="${(Projects.get(session.ProjectID)).projectStartDate}" defaultValue="${new Date()}"  dateFormat= "%d/%m/%Y"/>
								

							    </td>
				   				<td class='name'><g:message code="default.ReportDateTo.label"/></td>
							    <td> 
									<input type="hidden" name="reportDateTo" value="struct" />
									<calendar:datePicker name="reportDateTo" id="reportDateTo" value="" defaultValue="${new Date()}"  dateFormat= "%d/%m/%Y"/>
									
								</td>
	                        </tr> 
                            
                         </tbody>
                       </table>
                    </div>
                    <div>
                       <input type="hidden" name="projects" value="${(Projects.get(session.ProjectID)).id}" />
                       <g:actionSubmit class="inputbutton" value="${message(code: 'default.StatementOfAccounts.button')}" action="showReports" target="_blank"  onclick="return validateReportViewConfirmPrint();"/>
             	       <g:actionSubmit class="inputbutton" value="${message(code: 'default.UtilizationCertificate.button')}" action="utilizationCertificate" target="_blank"  onclick="return validateReportViewConfirmPrint();"/>
                    </div>
                 </g:form>
               </div>
            </td>
          </tr> 
         </table>
       </div>
    </body>
</html>
