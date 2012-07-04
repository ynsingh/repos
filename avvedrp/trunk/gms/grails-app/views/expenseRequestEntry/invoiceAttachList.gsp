<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'attachments.label', default: 'Attachments')}" />
        <title><g:message code="default.InvoiceDocuments.head"/></title>
    </head>
    <body>
    <div class="wrapper">
        <div class="body">
            <h1><g:message code="default.InvoiceDocuments.head"/></h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <td scope="row">
			   <div class="list">
	                <table width="97%" align="center" border="0" cellspacing="0" cellpadding="0">
	                    <thead>
	                        <tr>
								<th><g:message code="default.SINo.label" /></th>
                                <th><g:message code="default.AttachmentType.label" /></th>
                    			<th><g:message code="default.ExpenseDescription.label"/></th>
						    	<th><g:message code="default.DocumentName.label" /></th>
                        		<th><g:message code="default.View.label"/></th>
                      		</tr>
                        </thead>
	                    <tbody>
	                    	<g:each in="${attachmentsInstanceList}" status="i" var="attachmentsInstance">
	                        	<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
	                        
	                            <td>${(i + 1)}</td>
	                        
	                            <td>${fieldValue(bean: attachmentsInstance, field: "attachmentType.type")}</td>
	                        
	                            <td>${(ExpenseRequestEntry.get(attachmentsInstance.domainId)).expenseDescription}</td>
								
								<td>${fieldValue(bean: attachmentsInstance, field: "attachmentPath")}</td>
	                                                      
	                            <td>
	                            	<a href="${g.createLink(controller:'attachments', action:'downloadAttachments', id:attachmentsInstance.id)}">
		                            	<g:message code="default.View.label" encodeAs="HTML" target="_blank"/>
		                            </a>
       							</td>	
	       						<g:form>
	                   				 <input type="hidden" name="id" value="${attachmentsInstance?.id}" />
	           				   		 <input type="hidden"  name="attachmentType" 
	           				   		 	value="${attachmentsInstance?.attachmentType?.id}"/> 
	                                 <input type="hidden"  name="attachmentPath" 
	                                 	value=""${attachmentsInstance?.attachmentPath}"/>
	                                 <input type="hidden" id="domainId" name="domainId" 
	                                 	value="${attachmentsInstance.domainId}"/> 
	                                 <input type="hidden" id="domainId" name="domain" 
	                                 	value="${attachmentsInstance.domain}"/> 
	                                 
								</g:form>
                				</tr>
                			</g:each>
                		</tbody>
            		</table>
     			</div>
            </td>
         </div>
      </div>
    </body>
</html>
