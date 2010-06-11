

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Proposal List</title>
    </head>
    <body>
        <div class="wrapper">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
        </div>
        <div class="body">
            <h1>Proposal List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                         <input type="hidden" id="notificationId" name="notificationId" value="${fieldValue(bean:proposalInstance, field:'notification.id')}"/>
                   	        <g:sortableColumn property="id" title="Id" />
                   	        
                   	        <th>Party</th>
                        
                   	        <g:sortableColumn property="proposalDocumentationPath" title="UploadAttachments" />
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${proposalInstanceList}" status="i" var="proposalInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${proposalInstance.id}">${fieldValue(bean:proposalInstance, field:'id')}</g:link></td>
                             
                             <td>${fieldValue(bean:proposalInstance, field:'party.code')}</td>
                        
                            <td><g:link action="create" controller='notificationsAttachments' id="${fieldValue(bean:proposalInstance, field:'id')}">upload attachments</g:link></td>
                        
                        
                        
                           
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Proposal.count()}" />
            </div>
            </div>
        </div>
    </body>
</html>
