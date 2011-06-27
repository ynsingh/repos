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
              		<g:form action="fullProposalSave" method="post"  enctype="multipart/form-data" controller="proposal">
		 			            <div class="dialog">
					                <table>
					                    <tbody>
					                        <tr class="prop">
					                        <input type="hidden" id="proposalId" name="proposalId" value="">
					                        </tr>
					                        <tr class="prop">
				                                <td valign="top" class="name">
				                                    <label for="projectTitle"><g:message code="default.ProjectTitle.label" /></label>
				                                </td>
				                                  <td valign="top" class="value ${hasErrors(bean: proposalInstance, field: 'projectTitle', 'errors')}">
				                                    <strong>${proposalTitleInstance.projectTitle}</strong>
				                                    <input type="hidden" name="projectTitle" value="fullProposalProjectTitileInstance?.id">
				                                </td>
			                            	</tr>
					                    
				                            <tr class="prop">
					                            <td valign="top" class="name"><g:message code="default.AuthorityComments.label" /></td>
					                            <td valign="top" class="value"><strong>${fieldValue(bean: proposalInstance, field: "proposalStatus")}</strong></td>
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
				                                    <label for="lockedYN"><g:message code="default.Comments.label" /></label>
				                                </td>
				                                <td valign="top" class="value ${hasErrors(bean: proposalInstance, field: 'lockedYN', 'errors')}">
				                                    <g:textArea  name="lockedYN" value="${proposalInstance.lockedYN}" style='width: 200px; height:75px;'/>
				                                </td>
			                            	</tr>
					                      </tbody>
					                </table>
					            </div>
					             
		          		<div class="buttons">
			          		<g:form>
			                  <g:hiddenField name="id" value="${proposalInstance?.id}" />
			                  <g:hiddenField name="status" value="" />
			                    <g:if test ="${fullProposalSavedStatus == null}">
		                           <div class="buttons">
				                      <span class="button"><g:actionSubmit class="save" action="fullProposalSave" controller="proposal" value="${message(code: 'default.Save.button')}" onClick="return (document.getElementById('status').value = 'Saved');"/>
				                      <g:actionSubmit class="save" action="fullProposalSave" controller="proposal" value="${message(code: 'default.Submit.button')}" onClick="return (document.getElementById('status').value = 'Submitted');"/></span>
			                	   </div>
				                </g:if>
				            </g:form>
			         </div>  
	          </g:form>
	</div>
 </div>
      
	 
	  <g:if test ="${fullProposalSavedStatus != null}">
	  
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
		                     <% j++ %>
		                         <tr class="${(j % 2) == 0 ? 'odd' : 'even'}">
		                             <td>${proposalTitleInstance.projectTitle}</td> 
		                             <td><a href="${g.createLink(controller:'proposal', action:'download', id:fullProposalSavedStatus.id)}"><g:message code="${fullProposalSavedStatus.proposalDocumentationPath}" encodeAs="HTML"/></a></td>
		                           	 <td>${fullProposalSavedStatus.lockedYN}</td>
		                           	 <input type="hidden" name="fullProposalId" value="fullProposalSavedStatus?.id">
		                            <td><g:link action="fullProposalEdit" id="${fullProposalSavedStatus.id}"><g:message code="default.Edit.label" /></g:link></td> 
		                         </tr>
	                          
	                     </tbody> 
	             </table>     
          </div>
	   </div>
	 </g:if> 
   </g:if>  
     
     <g:else>
          <div class="dialog">
                	<table>
 	                	<tr>
					      <td> <strong><g:message code="default.ProposalTitle.label" />:<strong><td>
					      <td> ${proposalTitleInstance.projectTitle} </td>
	      				</tr>
      
				       <tr>
				       </tr>
 					   <tr>
					   </tr>
       
				      <tr>
				      </tr>
      
				      <tr>
				      </tr>
				      <tr>
					      <td><strong><g:message code="default.FullProposalStatus.label" />:<strong><td>
					      <td> ${fullProposalStatus.proposalStatus} </td>
      				  </tr>
				      <tr>
				      </tr>
				      
				      <tr>
				      </tr>
				      
				      
				      <tr>
					      <td><strong><g:message code="default.UploadProjectDocuments.File.label" />:<strong><td>
					      <td><a href="${g.createLink(controller:'proposal',action:'download',id:fullProposalStatus.id)}"><g:message code="${fullProposalStatus.proposalDocumentationPath}" encodeAs="HTML"/></a></td>
      				 </tr>
				      <tr>
				      </tr>
				      
				      <tr>
				      </tr>
       				  <tr>
					      <td><strong><g:message code="default.Comments.label" />:<strong><td>
					      <td>${fullProposalStatus.lockedYN}</td>
      				  </tr>
      
				      <tr>
				      </tr>
				      
				      <tr>
				      </tr>
                </table>
            </div>
         </g:else> 
  </body>
</html>
