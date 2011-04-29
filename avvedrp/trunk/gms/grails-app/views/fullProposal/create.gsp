

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        
       <title><g:message code="default.FullProposal.label" /></title> 
    </head>
    <body>
        
        <div class="body">
        <div class ="wrapper">
           <h1><g:message code="default.FullProposal.label" /><h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hiddenField name="fullProposalStatus" value="fullProposalStatus" />
            <g:if test="${fullProposalStatus == null}">
            <g:form action="save" method="post"  enctype="multipart/form-data" controller="fullProposal">
            
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projectTitle"><g:message code="default.ProjectTitle.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: preProposalInstance, field: 'projectTitle', 'errors')}">
                                    <strong>${preProposalInstance.projectTitle}</strong>
                                </td>
                            </tr>
                    
                        
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="default.AuthorityComments.label" /></td>
                            
                            <td valign="top" class="value"><strong>${fieldValue(bean: preProposalInstance, field: "preProposalStatus")}</strong></td>
                            
                        </tr>
                        
                        <tr class="prop">
                        <td valign="top" class="name">
                            <label for="createdBy"><g:message code="default.UploadFile.label" /></label>
                        </td>
                        <td valign="top" class="value">
                            <input type="file" id="myFile" name="myFile"/> 
                        </td>
                    </tr> 
                    
                       <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="modifiedBy"><g:message code="default.Comments.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: fullProposalInstance, field: 'modifiedBy', 'errors')}">
                                    <g:textArea  name="modifiedBy" value="${fullProposalInstance.modifiedBy}" style='width: 200px; height:75px;'/>
                                </td>
                            </tr>
                    
                        
                    
                    </tbody>
                </table>
            </div>
         
            <div class="buttons">
              <g:form>
                    <g:hiddenField name="id" value="${preProposalInstance?.id}" />
                    <g:hiddenField name="status" value="" />
                    
                     <g:if test ="${fullProposalSavedStatus == null}">
                    
                    
                    <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="save"  value="${message(code: 'default.Save.button')}" onClick="return (document.getElementById('status').value = 'Saved');"/>
                    
                    <g:actionSubmit class="save" action="save"  value="${message(code: 'default.Submit.button')}" onClick="return (document.getElementById('status').value = 'Submitted');"/></span>
                </div>
                
                </g:if>
             
               </g:form>
                
                   </g:form>
            </div>
        </div>
        
        <div class="body">
           <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                           
                            <th><g:message code="default.ProjectTitle.label" /></th>
                            
                            <th><g:message code="default.UploadProjectDocuments.File.label" /></th>
                            <th><g:message code="default.Comments.label" /></th>
                            <th><g:message code="default.Edit.label" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <% int j=0 %>
                     
                       
                    <g:each in="${preProposalInstanceList}" status="i" var="fullProposalInstance">
                        <% j++ %>
                       <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                          
                        
                        
                            
                        
                            <td>${fieldValue(bean: fullProposalInstance, field: "preProposal.projectTitle")}
                        
                            
                            <td><a href="${g.createLink(controller:'fullProposal', action:'download', id:fullProposalInstance.id)}"><g:message code="${fullProposalInstance.createdBy}" encodeAs="HTML"/></a></td>
                              
                            <td>${fieldValue(bean: fullProposalInstance, field: "modifiedBy")}</td>
                            <td><g:link action="edit" id="${fieldValue(bean:fullProposalInstance, field:'id')}"><g:message code="default.Edit.label" /></g:link></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            
        </div>
        </g:if>
        <g:else>
         
         <div class="dialog">
                <table>
                
                <tr>
                
      <td> <strong><g:message code="default.ProposalTitle.label" />:<strong><td>
      <td> ${preProposalInstance.projectTitle} </td>
      </tr>
      
      <tr>
       </tr>
       
       <tr>
       </tr>
      
      
      <tr>
     
                
      <td><strong><g:message code="default.AuthorityComments.label" />:</strong><td>
      <td> ${preProposalInstance.preProposalStatus} </td>
      </tr>
      
      <tr>
      </tr>
      
      <tr>
       </tr>
      
      
      <td><strong><g:message code="default.FullProposalStatus.label" />:<strong><td>
      <td> ${fullProposalStatus.proposalStatus} </td>
      </tr>
      
      <tr>
      </tr>
      
      <tr>
       </tr>
      
      
      <tr>
      <td><strong><g:message code="default.UploadProjectDocuments.File.label" />:<strong><td>
      <td><a href="${g.createLink(controller:'fullProposal', action:'download', id:fullProposalStatus.id)}"><g:message code="${fullProposalStatus.createdBy}" encodeAs="HTML"/></a></td>
      </tr>
      
      <tr>
      </tr>
      
      <tr>
       </tr>
      
      
      <tr>
      <td><strong><g:message code="default.Comments.label" />:<strong><td>
      <td>${fullProposalStatus.modifiedBy}</td>
      </tr>
      
      <tr>
      </tr>
      
      <tr>
       </tr>
      
      
      
      </table>
    
       </div> 
       
       </g:else>
       </div>
    </body>
</html>
