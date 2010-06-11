

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Notification List</title>
    </head>
    <body>
    	<div class="wrapper"> 
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>

        </div>
        <div class="tablewrapper">
        <div class="body">
            <h1>Notification List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:if test="${partyNotificationsInstance}">
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                            <th>Project</th>
                   	        <th>View</th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${partyNotificationsInstance}" status="i" var="partyNotificationsInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${i+1}</td>
                        
                            
                        
                            <td>${fieldValue(bean:partyNotificationsInstance, field:'notification.project.name')}</td>
							<td><g:link  action="showPartyNotifications"  id="${partyNotificationsInstance.notification.id}">View</g:link></td>                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            </g:if>
            <g:else>
            <br>
            No Records Available</br>
            </g:else>
            
            </div>
           </div>
        </div>
        
    </body>
</html>
