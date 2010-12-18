

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.Proposal.ProposalList.head"/></title>
    </head>
    <body>
      <div class="wrapper">
        <div class="tablewrapper">
          <div class="body">
		  	<h1><g:message code="default.Proposal.ProposalList.head"/></h1>
            <table width="100%">
     				<tr>
                    	<td valign="top" class="name">
                        	<label for="projects"><g:message code="default.Project.label"/>:</label>
                        </td>
                        <td valign="top" >
                         	<strong>  ${fieldValue(bean:notificationInstance,field:'project.name')} </strong>
                        </td>
                        <td valign="top" class="name">
                        	<label for="notification"><g:message code="default.NotificationDate.label"/>:</label>
                        </td>
                        <td valign="top" >
                         	<strong> 
                         	<g:formatDate format="dd MMM yyyy" date="${notificationInstance.notificationDate}"/> 
                         	</strong>
                        </td>
                        <td valign="top" class="name">
                        	<label for="notification"><g:message code="default.LastProposalSubmissionDate.label"/>:</label>
                        </td>
                        <td valign="top" >
                         	<strong> 
                         	<g:formatDate format="dd MMM yyyy" date="${notificationInstance.proposalSubmissionLastDate}"/> 
                         	</strong>
                        </td>
                    </tr>  
            </table>
		    <g:if test="${flash.message}">
	              <div class="message">${flash.message}</div>
            </g:if>
		            <div class="list">
		                <table>
		                    <thead>
		                        <tr>
		                          <input type="hidden" id="notificationId" name="notificationId" value="${fieldValue(bean:proposalInstance, field:'notification.id')}"/>
		                   	      <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}" />
		                   	      <th><g:message code="default.ProposalCode.label"/></th>
		                   	      <th><g:message code="default.Institution.label"/></th>
		                          <th><g:message code="default.ViewProposal.label"/></th>
		                          <th><g:message code="default.Eligibility.label"/></th>
		                          <th><g:message code="default.ProposalStatus.label"/></th>
		                   	    </tr>
		                    </thead>
		                    <tbody>
			                    <% int j=0 %>
				                    <g:each in="${proposalInstanceList}" status="i" var="proposalInstance">
				                    	<% j++ %>
				                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				                        	<td>${j}</td>
				                            <td>${fieldValue(bean:proposalInstance, field:'code')}</td>
				                            <td>${fieldValue(bean:proposalInstance, field:'party.code')}</td>
				                            <td><g:link action="show" id="${proposalInstance.id}"><g:message code="default.View.label"/></g:link></td>
				                            <td>
				                            	<g:if test="${EligibilityStatusList[i]}">   
						                       		<g:link action="create" controller='eligibilityCheck' params="[eligibilityStatus:'Reviewed']" id="${fieldValue(bean:proposalInstance, field:'id')}"><g:message code="default.Reviewed.label"/></g:link>
						                       			
						                        </g:if>
						                        <g:else>
						                        	<g:link action="create" controller='eligibilityCheck' params="[eligibilityStatus:'Review']" id="${fieldValue(bean:proposalInstance, field:'id')}"><g:message code="default.Review.label"/></g:link>
					                          		
					                            </g:else>
				                            </td>
				                            <td>
					                          <g:if test="${EligibilityStatusList[i]}">   
					                          		${EligibilityStatusList[i]?.eligibilitysStatus}
						                       		
						                      </g:if>
						                      <g:else>
						                      		<g:message code="default.Pending.label"/>
					                          		
					                          </g:else>
				                        	</td>
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
