

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>NotificationsAttachments List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New NotificationsAttachments</g:link></span>
        </div>
        <div class="body">
            <h1>NotificationsAttachments List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        
                   	    
                   	        <th>Type</th>
                   	    
                   	        <g:sortableColumn property="attachmentPath" title="attachmentPath" />
                         <th>Delete</th>
                   	        
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${notificationsAttachmentsInstanceList}" status="i" var="notificationsAttachmentsInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${notificationsAttachmentsInstance.id}">${fieldValue(bean:notificationsAttachmentsInstance, field:'id')}</g:link></td>
                        
                            
                        
                            <td>${fieldValue(bean:notificationsAttachmentsInstance, field:'attachmentType.type')}</td>
                        
                            <td><a href="${g.createLink(controller:'notificationsAttachments', action:'download', id:notificationsAttachmentsInstance.id)}"><g:message code="${notificationsAttachmentsInstance.attachmentPath}" encodeAs="HTML"/></a></td>
                        
                            <g:form>
                   				 <input type="hidden" name="id" value="${notificationsAttachmentsInstance?.id}" />
                   				   <input type="hidden"  name="notificationId" value="${notificationsAttachmentsInstance?.notification?.id}"/> 
                                 <input type="hidden"  name="proposalId" value=""${notificationsAttachmentsInstance?.proposal?.id}"/>
                                 <input type="hidden" id="documentType" name="documentType" value="${params.documentType}"/> 
                                 <td> <g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></td>
                         </g:form>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${NotificationsAttachments.count()}" />
            </div>
        </div>
    </body>
</html>
