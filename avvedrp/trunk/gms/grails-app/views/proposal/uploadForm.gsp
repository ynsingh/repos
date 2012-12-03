<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        
        <title>Upload Proposal Form</title>
    </head>
    <body>
    	<div class="wrapper">
    	
        	<div class="proptable"> 
				<table class="tablewrapper" width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
  					<tr>
    					<td scope="col"> 
					        <div class="body">
					        <img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help">
					            <h1><g:message code="default.UploadForm.head"/></h1>
					            <g:if test="${flash.message}">
					            	<div class="message">${flash.message}</div>
					            </g:if>
					            <g:hasErrors bean="${proposalSettingsInstance}">
						            <div class="errors">
						                <g:renderErrors bean="${proposalSettingsInstance}" as="list" />
						            </div>
					            </g:hasErrors>
					            <g:form action="saveUploadedProposalForm" method="post" enctype="multipart/form-data">
					                <div class="dialog">
					                    <table>
					                        <tbody>
					                        	
                                                	<tr class="prop">
						                                <td valign="top" class="name">
						                                    <label for="notification"><g:message code="default.NotificationTitle.label" /></label>
						                                    <label for="notification" style="color:red;font-weight:bold"> * </label>
						                                </td>
						                                <td valign="top" class="value ${hasErrors(bean: partyProposalFormInstance, field: 'notification', 'errors')}">
						                                    <g:select id="notification.id"  name="notification.id" from="${notificationInstanceList}" optionKey="id" optionValue="notificationCode" value="${partyProposalFormInstance?.notofication?.id}" noSelection="['null': '-Select-']" />
						                                </td>
						                            </tr>                
						                            <tr class="prop">
						                                <td valign="top" class="name">
						                                    <label for="attachmentPath"><g:message code="default.ProposalApplicationForm.label"/>:</label>
						                         	    <label for="attachmentPath" style="color:red;font-weight:bold"> * </label>
						                                 </td>
						                                 <td valign="top" class="value">
						                                    <input type="file" name="attachmentPath" id="attachmentPath"  /><g:message code="default.ApplicationFormat.label"/> 
						                                 </td>
						                                 <input type="hidden" name="formType" value="Proposal" />
						                             </tr>
                    							  </tbody>
                    							</table>
                							</div>
							                <div class="buttons">
							                    <span class="button">
								                    <g:submitButton name="create" class="save" onClick="return validateUploadProposalForm()"
								                    	value="${message(code: 'default.Create.button',default: 'Create')}" />
							                    </span>
							                </div>
							            </g:form>
							        </div>
						        </div>
						        
						     <g:if test="${partyProposalFormOldInstanceList}">
						        <div class="body">
									            <h1>Details of Uploaded Proposal Forms</h1>
									           
									            <div class="list">
									                <table>
									                    <thead>
									                        <tr>
									                    	    <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}"/>
																<th><g:message code="default.proposalForm.label" /></th>
																<th><g:message code="default.Notification.label" /></th>
									                   	        <th><g:message code="default.activeYesNo.label" /></th>
									                        </tr>
									                    </thead>
									                    <tbody>
									                    <g:each in="${partyProposalFormOldInstanceList}" status="i" var="partyProposalFormOldInstance">
									                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
									                        
									                        	<td>${i+1}</td>
									                        	<td><g:link action="downloadUploadProposalForm" controller="attachments" id="${partyProposalFormOldInstance.id}">${fieldValue(bean:partyProposalFormOldInstance, field:'value')}</g:link></td>
									                        	 <td>${fieldValue(bean:partyProposalFormOldInstance, field:'notification.notificationCode')}</td>
									                            <td>${fieldValue(bean:partyProposalFormOldInstance, field:'activeYesNo')}</td>
															                        	
									                        </tr> 
									                    </g:each>
									                    </tbody>
									                </table>
									            </div>
									            
								</div>
						       </g:if>  
				              </td>
						  </tr>
						  
						    </table> 
					     </td>
				     </tr>
			     </table>  
		      </div>     
	      </div>      
       </div>
   </body>
</html>
