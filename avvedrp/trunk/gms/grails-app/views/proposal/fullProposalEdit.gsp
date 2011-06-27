


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'fullProposal.label', default: 'FullProposal')}" />
        <title><g:message code="default.EditFullProposal.label"/></title>
    </head>
    <body>
        <div class="wrapper">
        
           
        <div class="body">
           <h1><g:message code="default.EditFullProposal.label"/><h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            
            
            <g:form method="post"  enctype="multipart/form-data" controller="proposal">
            
            <div class="dialog">
              
                <table>
                    <tbody>
                    <g:hiddenField name="id" value="${fullProposalInstance?.proposal.id}" />
                        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projectTitle"><g:message code="default.ProjectTitle.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalInstance, field: 'projectTitle', 'errors')}">
                                   
                                    <strong>${fullProposalInstance.projectTitle}</strong>
                                </td>
                            </tr>
                    
                        
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="default.AuthorityComments.label" /></td>
                            
                            <td valign="top" class="value"><strong>${fullProposalInstance.proposal.proposalStatus}</strong></td>
                            
                        </tr>
                        
                        <tr class="prop">
                        <td>
                            <label for="createdBy"><g:message code="default.UploadFile.label" /></label>
                        </td>
                        <td valign="top" class="value">
                            <a href="${g.createLink(controller:'fullProposal', action:'download', id:fullProposalInstance.proposal.id)}"><g:message code="${fullProposalInstance.proposal.proposalDocumentationPath}" encodeAs="HTML"/></a>
                            <input type="file" id="myFile" name="myFile"/>  
                        </td>
                        
                     
                    </tr> 
                    
                    <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="modifiedBy"><g:message code="default.Comments.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: fullProposalInstance, field: 'modifiedBy', 'errors')}">
                                    <g:textArea  name="lockedYN" value="${fullProposalInstance.proposal.lockedYN}" style='width: 200px; height:75px;'/>
                                </td>
                            </tr>
                    
                      
                        
                    
                    </tbody>
                </table>
            </div>
         <g:form>
              
             <g:hiddenField name="status" value="" />
             <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="fullProposalUpdate" value="${message(code: 'default.Update.button')}" onClick="return (document.getElementById('status').value = 'Saved');"/></span>
                    <span class="button"><g:actionSubmit class="save" action="fullProposalUpdate" value="${message(code: 'default.Submit.button')}" onClick="return (document.getElementById('status').value = 'Submitted');"/></span>
               </div>
         </g:form>
                 
            </g:form>
        </div>
        </div>
    </body>
</html>
