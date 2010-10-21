

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show Notifications</title>
    </head>
    <body>
    <g:javascript library="application"/>
    <modalbox:modalIncludes/>
    <div class="wrapper">
        <div class="tablewrapper">
        <div class="body">
            <h1>Show Notification</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                      <tr class="prop">
                            <td valign="top" class="name">Project:</td>
                            
                            <td valign="top" style="font-size:100%" class="value"><b>${notificationsInstance?.project?.name.encodeAsHTML()}</b></td>
                            
                        </tr>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Date Of Notification:</td>
                           <td><g:formatDate format="dd-MM-yyyy" date="${notificationsInstance.notificationDate}"/></td>
                            
                            
                        </tr>
                        
                         <tr class="prop">
                            <td valign="top" class="name">Last Date for ProposalSubmission:</td>
                            <td><g:formatDate format="dd-MM-yyyy" date="${notificationsInstance.proposalSubmissionLastDate}"/></td>
                            
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Description:</td>
                            
                            <td valign="top" class="value">${notificationsInstance.description}</td>
                            
                        </tr>
                    
                       
                    
                      
                    
                        
                    
                        
                    
                        <tr class="prop">
                            <td valign="top" class="name">Type:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:notificationsInstance, field:'project.projectType.type')}</td>
                          
                        </tr>
                          
                        <tr class="prop">
           					<td valign="top" class="name">Documentation:</td>
           					
           					<td valign="top" class="value">
           					
       							<g:if test="${notificationsAttachmentsInstance}">
       							<div class="list">
       							<table >
       							 <thead>
       							 <th>Document Type</th>
       							 <th>View</th>
       							  </thead>
       							<tbody>
       							<tr>
       							
       							<g:each in="${notificationsAttachmentsInstance}" status="j" var="notificationsAttachmentsInstance">
       							
       							
       							<tr class="${(j % 2) == 0 ? 'odd' : 'even'}">
       							<td>
       							${fieldValue(bean:notificationsAttachmentsInstance, field:'attachmentType.type')}
       							</td>
       							<td>
       							<a href="${g.createLink(controller:'notificationsAttachments', action:'download', id:notificationsAttachmentsInstance.id)}"><g:message code="View" encodeAs="HTML" target="_blank"/></a>
       							</td>
       							</tr>
       							
       							</g:each>
       							
       							</tr>
       							</tbody>
       							</table>
       							</div>
       							</g:if>
       							<g:else>
     						No Documentation Attached
							</g:else>
                     		
                     		
                     		
                     	</tr>
              			   
                    
                    </tbody>
                </table>
            </div>
            
           
             </div>
              </div>
        </div>
    </body>
</html>
