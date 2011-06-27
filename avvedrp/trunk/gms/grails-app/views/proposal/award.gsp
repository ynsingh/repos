

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Proposal</title> 
        <ckeditor:resources />            
    </head>
    <g:javascript library="application"/>
    <body>
    
    <div class="wrapper">
        <div class="body">
            <h1>Award Project</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${proposalInstance}">
            <div class="errors">
                <g:renderErrors bean="${proposalInstance}" as="list" />
            </div>
            </g:hasErrors>
        <g:form action="saveAward" method="post" enctype="multipart/form-data">
                <div class="dialog">
                    <table class="tablewrapper" border="0" cellspacing="0" cellpadding="0">
                        <tbody>
                        <tr class="prop">
                         <td>
                              <input type="hidden" id="proposalId" name="proposalId" value="${proposalApplicationInstance.proposal.id}"/>
                               <input type="hidden" id="parentProjectStartDate" name="parentProjectStartDate" value="${projectInstance?.projectStartDate}"/> 
                               <input type="hidden" id="parentProjectEndDate" name="parentProjectEndDate" value="${projectInstance?.projectEndDate}"/> 
		                <input type="hidden" id="balanceAmount" name="balanceAmount" value="${balanceAmountValue}"/>        
		                  <input type="hidden" id="notificationAmount" name="notificationAmount" value="${notificationAmount}"/>
                                        
                                                   </td> 
                            </tr>
                       		<tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code"><g:message code="default.ProposalTitle.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:proposalInstance,field:'code','errors')}">
                                <input type="text" id="proposalTitle" name="proposalTitle" value="${proposalTitle}"/ readOnly="true">
                                 </td>
                                 <td valign="top" class="name">
                                    <label for="code"><g:message code="default.ProjectTitle.label"/>:</label>
                                    <label for="code" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:proposalInstance,field:'code','errors')}">
                                <input type="text" id="name" name="name" value="${proposalTitle}"//>
                                 </td>
                                </tr> 
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code"><g:message code="default.PI.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:proposalInstance,field:'code','errors')}">
                                <input type="text" id="pi" name="pi" value="${proposalApplicationInstance?.name}"/>
                                 </td>
                                 <td valign="top" class="name">
                                    <label for="code"><g:message code="default.Institution.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:proposalInstance,field:'code','errors')}">
                                <input type="text" id="party" name="party" value="${proposalApplicationInstance?.organisation}"/>
                                 </td>
                                </tr>
                                <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code"><g:message code="default.ProjectCode.label"/>:</label>
                                    <label for="code" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:proposalInstance,field:'code','errors')}">
                                <input type="text" id="code" name="code" value="${fieldValue(bean:proposalInstance,field:'code')}"/>
                                 </td>
                                 <td valign="top" class="name">
                                    <label for="code"><g:message code="default.ProjectType.label"/>:</label>
                                    <label for="code" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:proposalInstance,field:'code','errors')}">
                                   <g:if test="${projectInstance}">
                                   <g:select optionKey="id" optionValue="type" id="parentProjectType" from="${projectTypeInstance}"  name="parentProjectType.id" value="${projectInstance?.projectType?.id}" disabled="true" ></g:select>
                                   <input type="hidden" id="projectType" name="projectType.id" value="${projectInstance?.projectType?.id}"/>
                                   </g:if>
                                   <g:else>
                                   <g:select optionKey="id" optionValue="type" id="projectType" from="${projectTypeInstance}"  name="projectType.id" value="${projectsInstance?.projectType?.id}" noSelection="['null':'-Select-']" ></g:select>
                                   </g:else>
                                 </td>
                             </tr>      
                             <g:if test="${projectInstance}">
                             <tr class="prop">
	                   				<td colspan="3"><div align="left">
			                   		<label for="dateRangeFrom"><g:message code="default.projects.MainProjectStartDate.label"/>:</label>
			                    		<strong><g:formatDate date="${projectInstance?.projectStartDate}" format="dd/MM/yyyy"/> </strong>
			                    		<label for="dateRangeTo"><g:message code="default.EndDate.label"/>: </label>              
			                    		<strong><g:formatDate date="${projectInstance?.projectEndDate}" format="dd/MM/yyyy"/></strong> 
		                    		</td>
	                    	</tr> 
	                    	</g:if>           
                            <tr>
                            <td valign="top" class="name">
                                    <label for="proposalSubmitteddate"><g:message code="default.StartDate.label"/>:</label>
                                    <label for="code" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:proposalInstance,field:'proposalSubmitteddate','errors')}">
                                   <calendar:datePicker name="projectStartDate" defaultValue="${new Date()}" value="${notificationInstance?.proposalSubmitteddate}" dateFormat= "%d/%m/%Y" disabled="true" />
                           
                                </td>
                                 <td valign="top" class="name">
                                    <label for="proposalSubmitteddate"><g:message code="default.EndDate.label"/>:</label>
                                    <label for="code" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:proposalInstance,field:'proposalSubmitteddate','errors')}">
                                   <calendar:datePicker name="projectEndDate" defaultValue="${new Date()}" value="${notificationInstance?.proposalSubmitteddate}" dateFormat= "%d/%m/%Y" disabled="true" />
                           
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code"><g:message code="default.TotalAmountAllocated.label"/>:</label>
                                    <label for="code" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:proposalInstance,field:'code','errors')}">
                                <input type="text" id="amountAllocated" name="amountAllocated" value="${fieldValue(bean:proposalInstance,field:'code')}"/>
                                 </td>
                                 <td valign="top" class="name">
                                    <label for="code"><g:message code="default.GrantAllocation.SanctionOrderNo.label"/>:</label>
                                    <label for="code" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:proposalInstance,field:'code','errors')}">
                                <input type="text" id="sanctionOrderNo" name="sanctionOrderNo" value="${fieldValue(bean:proposalInstance,field:'code')}"/>
                                 </td>
                                </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description"><g:message code="default.TermsOfProject.label"/>:</label>
                                </td>
                                <td valign="top" colspan="3" class="value ${hasErrors(bean:proposalInstance,field:'description','errors').encodeAsHTML()}">
								                                
								<g:textArea name="description" value="${fieldValue(bean:grantAllocationInstance,field:'remarks')}" rows="3" cols="30"/>
                                </td>
                            </tr> 
                          <tr class="prop">
						        <td valign="top" class="name">
                                  
                                    <label for="attachmentType"><g:message code="default.AttachmentType.label" />:</label>
                                	
                                 </td>
                                 <td valign="top"  class="value ${hasErrors(bean: attachmentType, field:'type','errors')}">
                                    <g:select name="attachmentType" optionKey="id" optionValue="type" from="${attachmentTypeList}"  noSelection="['null':'-Select-']" />
                                 </td>
                            </tr>         
                            
                         <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code"><g:message code="default.UploadAttachments.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:proposalInstance,field:'code','errors')}">
                                <input type="file" name="attachmentPath" id="attachmentPath"  />
                                 </td>
                                 
                                </tr>
                        </tbody>
                    </table>
                    
                </div>
                <div>
                
                    <input class="inputbutton" type="submit" value="${message(code: 'default.Award.label')}" onClick="return validateAwardProposal()" />
                    
                    <g:actionSubmit class="inputbutton" action="redirectNotificationList" value="${message(code: 'default.Cancel.button')}" />                 

                </div>
            </g:form>
</div></div>
    </body>
</html>
