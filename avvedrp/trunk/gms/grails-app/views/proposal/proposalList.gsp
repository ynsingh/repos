

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.Proposal.ProposalList.head"/></title>
         <resource:rating />
    </head>
    <body>
      <div class="wrapper">
      
        <div class="tablewrapper">
          <div class="body">
          <img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help">
          		  	<h1><g:message code="default.Proposal.ProposalList.head"/></h1>
            <table width="100%">
     				<tr>
                    	
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
		               	        <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}"/>
		               	        
		               	        <th><g:message code="default.TitleOfTheResearchProject.label"/></th>
		               	        <th><g:message code="default.SubmittedBy.label"/></th>
		                    	<th><g:message code="default.Organisation.label"/></th>
		                    	
		               	       <!-- <th><g:message code="default.View.label"/></th>-->
		               	        <th><g:message code="default.ProposalVersion.label"/></th>
		               	        <th>Proposal Review</th>
		               	        <th><g:message code="default.Award.label"/></th>
		               	        <th><g:message code="default.proposalRating.label"/></th>
		                    </tr>
		                </thead>
		                <tbody>
			                <g:each in="${proposalApplicationInstanceList}" status="i" var="proposalApplicationInstance">
			                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			                    
			                        <td>${i+1}</td>
			                        <% def proposalApplicationExtProjectInstance = ProposalApplicationExt.find("from ProposalApplicationExt PE where PE.field='TitleOfTheResearchProject_2' and PE.proposalApplication.id="+proposalApplicationInstance?.id)%>
			                         
			                         
			                         <td><g:link action="proposalApplicationReview" controller='proposalApplication' id="${proposalApplicationInstance.id}">${proposalApplicationExtProjectInstance?.value}</g:link>
			                         </td>
			                         <td>${proposalApplicationInstance?.name}</td>
			                         <td>${proposalApplicationInstance?.organisation}</td>
			                         
			                        <!--<td><g:link action="proposalApplicationReview" controller='proposalApplication' id="${proposalApplicationInstance.id}"><g:message code="default.View.label"/></g:link></td>-->
			                    	  <td>V${proposalApplicationInstance?.proposal?.proposalVersion}</td>
			                    	  <td><g:link action="proposalReviewDetails" controller='proposal' id="${proposalApplicationInstance.proposal.id}">Proposal Review</g:link></td>
			                    	  <td>
			                    	  <g:if test="${proposalApplicationInstance?.proposal.proposalStatus == 'Reviewed'}">
			                    	  <g:if test="${proposalApplicationInstance?.award=='Y'}">
			                    	  <g:link action="awardedProposal" controller='proposal' id="${proposalApplicationInstance.proposal.id}"><g:message code="default.Awarded.label"/></g:link>
			                    	  </g:if>
			                    	  <g:else>
			                    	   <g:link action="award" controller='proposal' id="${proposalApplicationInstance.proposal.id}"><g:message code="default.Award.label"/></g:link>
			                    	 </g:else>
			                    	  </g:if>
			                    	  <g:else></g:else>
			                    	  </td>
			                    	 	<td>
			                        	<richui:rating dynamic="false" units="${maxScaleList[i]}" rating="${evalScoreInstanceList[i]?.totalScore}" showCurrent="false"/>
			                       		<g:message code="default.AvgRating.label"/>:
			                       		<g:if test="${evalScoreInstanceList[i]?.totalScore}">${evalScoreInstanceList[i]?.totalScore}</g:if>
			                       		<g:else>0.0</g:else>
			                       		(<g:if test="${evalScoreInstanceList[i]?.noOfReviewers}">${evalScoreInstanceList[i]?.noOfReviewers} </g:if>
			                       		<g:else>0</g:else>
			                       		<g:message code="default.votescast.label"/>)
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
