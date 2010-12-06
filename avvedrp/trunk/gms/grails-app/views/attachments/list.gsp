

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'attachments.label', default: 'Attachments')}" />
        <title><g:message code="default.projectDocuments.label"/></title>
    </head>
    <body>
    <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.projectDocuments.label"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'default.SINo.label')}"/>
                        
                            <th><g:message code="${message(code: 'default.DocumentType.label')}"/></th>
                        
                            <th><g:message code="${message(code: 'default.Project.label')}"/></th>
                            <g:sortableColumn property="attachmentPath" title="${message(code: 'default.DocumentName.label')}" />
                                                                            	                                                
                            <th><g:message code="${message(code: 'default.View.label')}"/></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${attachmentsInstanceList}" status="i" var="attachmentsInstance">
                       <tr class="${(i % 2) == 0 ? 'odd' : 'even'}" >
                       
                       
                        
                            <td>${(i + 1)}</td>
                        
                            <td>${fieldValue(bean: attachmentsInstance, field: "attachmentType.type")}</td>
                        
                                                    
                            <td>${(Projects.get(attachmentsInstance.domainId)).name}</td>
                            <td>${fieldValue(bean: attachmentsInstance, field: "attachmentPath")}</td>
                        	                            
                            <td><a href="${g.createLink(action:'downloadAttachments', id:attachmentsInstance.id)}"><g:message code="${message(code: 'default.View.label')}" encodeAs="HTML" target="_blank"/></a>
       							</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            
        </div>
        </div>
    </body>
</html>
