

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'attachments.label', default: 'MessageAttachments')}" />
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
               
            <table width="97%" align="center" border="0" cellspacing="0" cellpadding="0">
                <thead>
                    <tr>
			<th><g:message code="default.SINo.label" /></th>
               
                   					<th><g:message code="default.Project.label"/></th>
	    	
                                       <th><g:message code="default.DocumentName.label" /></th>
                     			<th><g:message code="default.View.label"/></th>
                   					
                   					<th><g:message code="default.Delete.label"/></th>
               					</tr>
                </thead>
                <tbody>
                <g:each in="${messageAttachmentsInstanceList}" status="i" var="attachmentsInstance">
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                    <g:if test="${attachmentsInstance.viewedYesNo == 'N'}">
                       
                        <td><strong>${(i + 1)}</strong></td>
                    
                        <td><strong>${(attachmentsInstance.projects.name)}</strong></td>
		       
                        <td><strong>${fieldValue(bean: attachmentsInstance, field: "attachmentPath")}</strong></td>
                                                  
                        <td>
                         <a href="${g.createLink(action:'downloadAttachments', id:attachmentsInstance.id)}">
                         	<strong><g:message code="default.View.label" encodeAs="HTML" target="_blank"/></strong>
                         </a>
  						</td>
  						
  						</g:if>	
  						<g:else>
  						<td>${(i + 1)}</td>
                    
                        <td>${(attachmentsInstance.projects.name)}</td>
		       
                        <td>${fieldValue(bean: attachmentsInstance, field: "attachmentPath")}</td>
                                                  
                        <td>
                         <a href="${g.createLink(action:'downloadAttachments', id:attachmentsInstance.id)}">
                         	<g:message code="default.View.label" encodeAs="HTML" target="_blank"/>
                         </a>
  						</td>
  						</g:else>
   						<g:form>
              				 <input type="hidden" name="id" value="${attachmentsInstance?.id}" />
  				   		 
                        <input type="hidden"  name="attachmentPath" 
                        	value=""${attachmentsInstance?.attachmentPath}"/>
                       <td> 
                        <g:actionSubmit class="delete" action="deleteMessageAttach" onclick="return confirm('Are you sure?');" 
                        	value="${message(code: 'default.Delete.button')}"/>
                       	 </td>
               			  </g:form>
  						   </tr>
  						 </g:each>
  					 </tbody>
			  </table>
            </div>
            
        </div>
        </div>
    </body>
</html>
