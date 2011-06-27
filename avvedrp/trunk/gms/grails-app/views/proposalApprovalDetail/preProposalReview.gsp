<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:set var="entityName" value="${message(code: 'preProposal.label', default: 'PreProposal')}" />
        <calendar:resources lang="en" theme="tiger"/>
        <g:javascript library="prototype" />
        <g:javascript library="appFormValidation" />
        <script type="text/javascript" src="/gms/js/appFormValidation.js" ></script> 
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body topmargin="0" leftmargin="0" onload="${remoteFunction(action:'getForm', controller:'proposal',onSuccess:'returnResult(e)')};disableAll();fitThePage();">
        <script>
        function disableAll(){
       	var el = parent.right.frames['editframe'].document.forms[0].elements;
		for(var i=0;i<el.length;i++){
		el[i].setAttribute('readonly',true);
		el[i].style.backgroundColor='transparent';
		el[i].style.border='0px';
		}
		}
		</script>
		<div class="wrapper">
        <div class="body">
            <h1><g:message code="default.PreProposalDetail.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${proposalInstance}">
            <div class="errors">
                <g:renderErrors bean="${proposalInstance}" as="list" />
            </div>
            </g:hasErrors>
            
                <div class="dialog">
                    <table>
                        <tbody>
                        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projectTitle"><g:message code="preProposal.proposalTitle.label" default="Proposal Title" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApplicationInstance, field: 'projectTitle', 'errors')}">
                                    <g:textField name="projectTitle" value="${proposalApplicationInstance?.projectTitle}" disabled="true"/>
                                </td>
                        </tr>
                        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="remarks"><g:message code="preProposal.description.label" default="Description" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalInstance, field: 'description', 'errors')}">
                                    <g:textField name="description" value="${proposalInstance?.description}" disabled="true"/>
                                </td>
                            </tr>
                        
                            
                        
                            
                        
                           
                        
                            <tr class="prop">
                               
                           
                                <td valign="top" class="name">
                                    <label for="person"><g:message code="preProposal.faculty.label" default="User" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalInstance, field: 'person', 'errors')}">
                                    <g:select name="person.id" from="${Person.list()}" optionValue="username" optionKey="id" value="${proposalInstance?.person?.id}" disabled="true" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                
                          
                                <td valign="top" class="name">
                                    <label for="proposalCategory"><g:message code="preProposal.proposalCategory.label" default="Proposal Category" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApplicationInstance, field: 'proposalCategory', 'errors')}">
                                    <g:select name="proposalCategory.id" from="${ProposalCategory.list()}" optionKey="id" optionValue="name" value="${proposalApplicationInstance?.proposalCategory?.id}" disabled="true" />
                                </td>
                            </tr>
                            </tbody>
                    </table>
                    <table>
                        <tbody>
                        <tr>
		   		 	     <td>
		   		 	     	<input type=hidden name="proposalDocumentationPath" value="${proposalApplicationForm}">
							 <iframe id='editframe' name='editframe' src="${createLinkTo(dir:'proposal',file:proposalApplicationForm)}" width="785" height="450" scrolling="auto" frameborder="0">
							 </iframe>
						 </td>
					 </tr>
					  
                        </tbody>
                    </table>
                    </div>
                    <fieldset>
                    <table>
                        <tbody>
                        <tr>
                    <td>
		   		 	     	 <g:message code="default.Remarks.label" default="Project Title" />
						 </td>
		   		 	     <td>
		   		 	     	 <g:textArea id="myField" name="myField" value="" rows="5" cols="40"/>
						 </td>
					 </tr>
                        </tbody>
                    </table>
                    
                </fieldset>
                <div class="button">
                <table>
                        <tbody><tr>
		   		 	     <td>
		   		 	    
                    <g:form method="post" controller="proposalApprovalDetail" >
     <input type=hidden id="proposalApprovalAuthorityMap.id" name="proposalApprovalAuthorityMap.id" value="${proposalApprovalAuthorityMapInstance.id}">
     <input type=hidden id="approvalAuthorityDetail.id" name="approvalAuthorityDetail.id" value="${approvalAuthorityDetailInstance.id}">
     <input type=hidden id="proposalId" name="proposalId" value="${proposalInstance.id}">
     <input type=hidden id="proposalStatus" name="proposalStatus" value="Approved">
     <input type=hidden id="remar" name="remarks" value="">
      <g:actionSubmit class="inputbutton" value="Approve" action="save"   onclick="(document.getElementById('remar').value = document.getElementById('myField').value);"/>
      </g:form> </td>
                            
		   		 	     <td>
      <g:form method="post" controller="proposalApprovalDetail" >
     <input type=hidden id="proposalApprovalAuthorityMap.id" name="proposalApprovalAuthorityMap.id" value="${proposalApprovalAuthorityMapInstance.id}">
     <input type=hidden id="approvalAuthorityDetail.id" name="approvalAuthorityDetail.id" value="${approvalAuthorityDetailInstance.id}">
      <input type=hidden id="proposalId" name="proposalId" value="${proposalInstance.id}">
     <input type=hidden id="proposalStatus" name="proposalStatus" value="NeedMoreInfo">
     <input type=hidden id="remar1" name="remarks" value="">
      <g:actionSubmit class="inputbutton" value="Need More Info" action="save" onclick="(document.getElementById('remar1').value = document.getElementById('myField').value);"/>
     </g:form></td>
                           
		   		 	     <td>
     <g:form method="post" controller="proposalApprovalDetail" >
     <input type=hidden id="proposalApprovalAuthorityMap.id" name="proposalApprovalAuthorityMap.id" value="${proposalApprovalAuthorityMapInstance.id}">
     <input type=hidden id="approvalAuthorityDetail.id" name="approvalAuthorityDetail.id" value="${approvalAuthorityDetailInstance.id}">
     <input type=hidden id="proposalId" name="proposalId" value="${proposalInstance.id}">
     <input type=hidden id="proposalStatus" name="proposalStatus" value="Rejected">
     <input type=hidden id="remar2" name="remarks" value="">
      <g:actionSubmit class="inputbutton" value="Reject" action="save" onclick="(document.getElementById('remar2').value = document.getElementById('myField').value);"/>
       </g:form> </td>
                            </tr>
                            </tbody>
                    </table>
                </div>
     	</div>
      </div>
    </body>
</html>