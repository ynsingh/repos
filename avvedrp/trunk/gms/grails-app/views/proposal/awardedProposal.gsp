

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.AwardDetails.head"/></title>
    </head>
    <body>
       <g:javascript library="application"/>
       <modalbox:modalIncludes/>
       <div class="wrapper">
        <div class="tablewrapper">
          <div class="body">
            <h1><g:message code="default.AwardDetails.head"/></h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
              <table>
                  <tbody>
                    <tr class="prop">
                        <td valign="top" class="name"><g:message code="default.ProposalCode.label"/></td>
                        <td valign="top" class="value"><b>${awardInstance?.proposal?.code}</b></td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name"><g:message code="default.ProjectName.label"/></td>
                        <td valign="top" class="value"><b>${awardInstance?.projects?.name}</b></td>
                    </tr>
                    <tr class="prop">
                        <td valign="top" class="name"><g:message code="default.GrantAllocation.Recipient.label"/></td>
                        <td valign="top" class="value"><b>${grantAllocationInstance?.party?.nameOfTheInstitution}</b></td>
                    </tr>
                    <tr class="prop">
                        <td valign="top" class="name"><g:message code="default.AmountAllocated(Rs).label"/></td>
                        <td valign="top" class="value"><b>${currencyFormat?.ConvertToIndainRS(grantAllocationSum[0].doubleValue())}</b></td>
                    </tr>
                        
                    <tr class="prop">
                        <td valign="top" class="name"><g:message code="default.DateofAward.label"/></td>
                        <td><g:formatDate format="dd-MM-yyyy" date="${awardInstance?.awardedDate}"/></td>
                    </tr>
                    <tr class="prop">
                        <td valign="top" class="name"><g:message code="default.FundList.ProjectDuration.label"/></td>
                        <td><g:formatDate format="dd MMM yyyy" date="${awardInstance.projects.projectStartDate}"/>- 
								      						<g:formatDate format="dd MMM yyyy" date="${awardInstance.projects.projectEndDate}"/> 
									      					(${Math.round((awardInstance.projects.projectEndDate.getTime()-
									      					awardInstance.projects.projectStartDate.getTime())/86400000)} days)</td>
                    </tr>
                    
                   
                  </tbody>
              </table>
            </div>
           
          </div>
        </div>
       </div>
    </body>
</html>
