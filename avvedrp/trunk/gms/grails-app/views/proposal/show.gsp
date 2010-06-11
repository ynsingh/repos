

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show Proposal</title>
    </head>
    <body>
    <div class="wrapper">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="proposalList" action="proposalList" params='[id:proposalInstance.notification.id]'>Proposal List</g:link></span>
            
        </div>
        <div class="body">
            <h1>Show Proposal</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                                            
                        <tr class="prop">
                            <td valign="top" class="name">Proposal Code:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:proposalInstance, field:'code')}</td>
                            
                        </tr>
                        
                         <tr class="prop">
                            <td valign="top" class="name">Project:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:proposalInstance, field:'notification.project.name')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Notification Code:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:proposalInstance, field:'notification.notificationCode')}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Party:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:proposalInstance, field:'party.code')}</td>
                            
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Proposal Submitted date:</td>
                            
                            <td valign="top" class="value"><g:formatDate format="dd-MM-yyyy" date="${proposalInstance.proposalSubmitteddate}"/></td>
                            
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Description:</td>
                            
                            <td valign="top" class="value">${proposalInstance.description}</td>
                            
                        </tr>
                        
                         <tr class="prop">
           					<td valign="top" class="name">Documentation:</td>
           					
           					<td valign="top" class="value">
           					<g:if test="${notificationsAttachmentsInstance}">
       							<div class="list">
       							<table>
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
                     		
                     		</td>
                     		
              			</tr>
                        
                    
                    </tbody>
                </table>
            </div>
            </div>
        </div>
    </body>
</html>
