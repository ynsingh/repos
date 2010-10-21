

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'attachments.label', default: 'Attachments')}" />
        <title><g:message code="project Documents" args="[entityName]" /></title>
    </head>
    <body>
    <div class="wrapper">
        <div class="body">
            <h1><g:message code="Project Documents" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'attachments.id.label', default: 'Id')}" />
                        
                            <th><g:message code="attachments.attachmentType.label" default="Document Type" /></th>
                        
                            <th>Project</th>
                            <g:sortableColumn property="attachmentPath" title="${message(code: 'attachments.attachmentPath.label', default: 'Document Name')}" />
                                                                            	                                                
                            <th>View</th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${attachmentsInstanceList}" status="i" var="attachmentsInstance">
                       <tr class="${(i % 2) == 0 ? 'odd' : 'even'}" >
                       
                       
                        
                            <td>${(i + 1)}</td>
                        
                            <td>${fieldValue(bean: attachmentsInstance, field: "attachmentType.type")}</td>
                        
                                                    
                            <td>${(Projects.get(attachmentsInstance.domainId)).name}</td>
                            <td>${fieldValue(bean: attachmentsInstance, field: "attachmentPath")}</td>
                        	                            
                            <td><a href="${g.createLink(action:'downloadAttachments', id:attachmentsInstance.id)}"><g:message code="View" encodeAs="HTML" target="_blank"/></a>
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
