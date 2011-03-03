

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title><g:message code="default.Proposal.ProposalList.head"/></title>
        
    </head>
    <body>
    <g:javascript library="application" /> 
		<modalbox:modalIncludes />
    	<div class="wrapper"> 
    	<div id="spinner" class="spinner" style="display:none;">
            <img src="${createLinkTo(dir:'images',file:'spinner.gif')}" alt="Spinner" />
        </div>	
        
    <div class="innnerBanner">
	<div class="loginLink">
	<span>
	
	
	</a>&nbsp;&nbsp;  
<img src="${createLinkTo(dir:'images/themesky',file:'help.gif')}" onClick="window.open('../images/HELPDOC/UntitledFrameset-15.html','mywindow','width=800,height=500,left=0,top=100,screenX=0,screenY=100')">  
</a> &nbsp;&nbsp;|</a>&nbsp;&nbsp;<a href="#"><img src="${createLinkTo(dir:'images/themesky',file:'aboutUs.jpg')}" onClick="window.open('../images/aboutUs/AboutUs_MGMS_new.html','mywindow','width=600,height=300,left=0,top=100,screenX=0,screenY=100')">&nbsp;&nbsp;</a>
	
   	</span>
   	</div>
	</div> 
	<g:subMenuLogin/>
          <div class="body">
            <h1><g:message code="default.NotificationList.label"/></h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <g:if test="${notificationInstanceList}">
              <div class="list">
                <table>
                    <thead>
                        <tr>
                            <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}"  />
                            <th><g:message code="default.NotificationTitle.label"/></th>
                            <g:sortableColumn property="notificationDate" title="${message(code: 'default.NotificationDate.label')}" />
                            <g:sortableColumn property="proposalSubmissionLastDate" title="${message(code: 'default.LastProposalSubmissionDate.label')}" />
                   	        <th><g:message code="default.Grantor.label"/></th>
                   	        <th><g:message code="default.NotificationDetails.label"/></th>
                   	        <th><g:message code="default.Apply.label"/></th>
                   	    </tr>
                    </thead>
                    <tbody>
                    
                      <g:each in="${notificationInstanceList}" status="i" var="notificationInstanceList">
                        
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td>${i+1}</td>
                            <td>${fieldValue(bean:notificationInstanceList, field:'notificationTitle')}</td>
                            <td><g:formatDate format="dd-MM-yyyy" date="${notificationInstanceList.notificationDate}"/></td>
                           	<td><g:formatDate format="dd-MM-yyyy" date="${notificationInstanceList.proposalSubmissionLastDate}"/></td>
                           	<g:hiddenField name="proposalSubmissionLastDate${i}" value="${notificationInstanceList.proposalSubmissionLastDate}" />
                           	<td>${fieldValue(bean:notificationInstanceList, field:'party.nameOfTheInstitution')}</td>
                           	<td>
                           	<modalbox:createLink controller="proposal" action="notificationDetails" id="${notificationInstanceList.id}" title="${message(code: 'default.NotificationDetails.label')}" width="900"><g:message code="${message(code: 'default.View.label')}"/></modalbox:createLink>
                           	</td>
                           	
                           	
                           	                           	
                           	
							<td>
							    <g:form controller="proposal" action="save" id="${notificationInstanceList.id}">
							    <g:link action="uploadProposalApplication" controller="proposal" id="${notificationInstanceList.id}" onClick="return validateProposalsubmit('${notificationInstanceList.proposalSubmissionLastDate}')"><g:message code="${message(code: 'default.Apply.label')}"/></g:link>  
                     			</g:form>
							</td>                        
                        </tr>
                      </g:each>
                    </tbody>
                </table>
              </div>
            </g:if>
            <g:else>
              <br><g:message code="default.NoRecordsAvailable.label"/></br>
            </g:else>
          </div>
       </div>
       <div class="footerdBar">
<br>
<label style="text-align: center;font: bold 9px Verdana;color: #104d6b;"><g:message code="default.footerMsg.label"/></label>
</div>
    </body>
</html>
