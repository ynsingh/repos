<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.Proposal.ProposalList.head"/></title>
        <resource:rating />
    </head>
    <body>
        <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.Proposal.ProposalList.head"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div style="text-align:right">
            <g:form>
            <b><label for="remarks${i}"><g:message code="default.Search.button"/></label></b>:
            <g:select optionKey="key" optionValue="value" id="projectType" from="${['Reviewed':'Reviewed', 'NotReviewed':'Not Reviewed']}" name="status" noSelection="['null':'All']" ></g:select>
                   <g:submitToRemote action="proposalSearch" value="${message(code: 'default.Search.button')}" update="search" />
             </g:form>   
                </div>
            <div class="list">
            <div id="search">
                <table>
                    <thead>
                        <tr>
                         <input type="hidden" id="notificationId" name="notificationId" value="${fieldValue(bean:proposalInstance, field:'notification.id')}"/>
                   	       <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}"/>
                   	        <th><g:message code="default.NotificationTitle.label"/></th>
                   	        <th><g:message code="default.TitleOfTheResearchProject.label"/></th>
                   	        
                   	        <th><g:message code="default.SubmittedBy.label"/></th>
                        	<th><g:message code="default.Organisation.label"/></th>
                        	
                   	        <th><g:message code="default.ProposalVersion.label"/></th>
                   	        <th><g:message code="default.proposalReview.label"/></th>
                   	        <th><g:message code="default.proposalRating.label"/></th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${proposalInstanceList}" status="i" var="proposalApplicationInstance">
                           <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${i+1}</td>
                            <td>${proposalApplicationInstance?.proposal?.notification?.notificationTitle}</td>
                            <% def proposalApplicationExtProjectInstance = ProposalApplicationExt.find("from ProposalApplicationExt PE where PE.field='TitleOfTheResearchProject_2' and PE.proposalApplication.id="+proposalApplicationInstance?.id)%>
                             <td>${proposalApplicationExtProjectInstance?.value}</td>
                             
                             <td>
                             <g:link action="downloadAttachments" controller="attachments" id="${attachmentsInstanceCVList[i]?.id}"><label for="name">${proposalApplicationInstance?.name}</label></g:link></td>
                        	 <td>${proposalApplicationInstance?.organisation}</td>
                             
                            <td>V${proposalApplicationInstance?.proposal?.proposalVersion}</td>
                        	 <td>
	                        	<g:link action="evalForm" controller='evalAnswer' id="${fieldValue(bean:proposalApplicationInstance, field:'id')}"><g:message code="default.proposalReview.label"/></g:link>
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
