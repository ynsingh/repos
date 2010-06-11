

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <title>Show Proposal</title>
    </head>
     <style>
    .tablewrapperpopup
	{ 
	padding: 0px;
	text-align:left;
	width: 500px; 
	}
	</style>
    <body>
    <div class="tablewrapperpopup">
    <div class="proptable"> 
       
            <h1>Submited Proposal</h1>
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
                            <td valign="top" class="name">Notification:</td>
                            
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
    </body>
</html>
