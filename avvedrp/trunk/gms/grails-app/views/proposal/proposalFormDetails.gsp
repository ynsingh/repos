<html>
    <head>
    	
        <title>Create ProposalApplication</title>
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <calendar:resources lang="en" theme="tiger"/>
 
        <g:javascript library="prototype" />
        <g:javascript library="applicationValidation" />
        <g:javascript library="appFormValidation" />
    
        <link rel='stylesheet' href='/gms/plugins/modalbox-0.4/css/modalbox.css' />
       
		<g:javascript library="application" />				
    </head>

    <body topmargin="0" leftmargin="0" onload="${remoteFunction(action:'getProposalForm', controller:'proposal',onSuccess:'returnResult(e)')};fitThePage();">    	
      <div class="wrapper">
   		<div class="body">
   		<h1>Personal Details Page <h1>
   		 <span style="float: right;color:#4c99cc;">All fields are mandatory</span>
   		 	<div class="dialog">
   		     	<table>
	   		 	  	 <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projectTitle"><g:message code="default.TitleOfTheResearchProject.label"/></label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApplicationInstance, field: 'projectTitle', 'errors')}">
                                    <g:textField name="projectTitle" value="${proposalApplicationInstance?.projectTitle}" />
                                </td>
                        </tr>
                        
                        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="organisation"><g:message code="default.Organisation.label"/></label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApplicationInstance, field: 'organisation', 'errors')}">
                                    <g:textField name="organisation" value="${proposalApplicationInstance?.organisation}" />
                                </td>
                                
                        </tr>
                        
                        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="default.FullName.label"/>:</label>
                                	<label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:proposalApplicationInstance,field:'name','errors')}">
                                   <g:textField name="name" value="${proposalApplicationInstance?.name}" />
                                </td>
                        </tr>
                        
                                                
                        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="default.Email.label"/>:</label>
                                	<label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:proposalApplicationInstance,field:'email','errors')}">
                                   <g:textField name="email" value="${proposalApplicationInstance?.email}" />
                                </td>
                        </tr>
                        
                         <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="default.Phone.label"/>:</label>
                                	<label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:proposalApplicationInstance,field:'phone','errors')}">
                                   <g:textField name="phone" value="${proposalApplicationInstance?.phone}" />
                                </td>
                        </tr>
                        
                        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code"><g:message code="default.ProposalCategory.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                     <g:select  optionKey="name" optionValue="name" id="ProjectCategory" from="${proposalCategoryList}"  name="ProjectCategory" noSelection="['null':'-Select-']"></g:select>
                                </td>
                                
                               </tr>
                        
                       
                       
			  	</table>
			</div>
 		</div>
		<div class="body">
   		 	<div class="dialog">
   		 		   		<h1>Proposal Form<h1>	
   		 		<table>
	   		 	   <tbody>
	   		 	     
		   		 	 <tr class="prop">
		   		 	     <td>
		   		 	     <g:if test="${proposalApplicationForm != null}">
							 <iframe id='editframe' name='editframe' src="${createLinkTo(dir:'proposal',file:proposalApplicationForm)}"  width="995" height="450" scrolling="auto" frameborder="0">
							 </iframe>
							 </g:if>
						 </td>
						  <input type=hidden name="proposalDocumentationPath" value="${proposalApplicationForm}" />
						  <input type=hidden name="notificationId" value="${notificationInstance.id}" />
					 </tr>
				   </tbody>
				</table>
				<div class="buttons">
      					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" onclick="submitProposalForm(document.getElementById('projectTitle').value,document.getElementById('organisation').value,document.getElementById('name').value,document.getElementById('email').value,document.getElementById('phone').value,document.getElementById('ProjectCategory').value)"/>
      			</div>
 			</div>
		</div>
      </div>
    </body>
</html>