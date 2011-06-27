<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    
        <g:set var="entityName" value="${message(code: 'preProposal.label', default: 'PreProposal')}" />
        
      
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <calendar:resources lang="en" theme="tiger"/>
        <g:javascript library="jquery"/>
        <g:javascript library="prototype" />
        <g:javascript library="applicationValidation" />
        <g:javascript library="appFormValidation" />
        <script src="${createLinkTo(dir:'images',file:'jquery-1.3.2.js')}"></script>
        <script src="${createLinkTo(dir:'images',file:'jquery.colorbox.js')}"></script>
        <link rel='stylesheet' href='/gms/plugins/modalbox-0.4/css/modalbox.css' />
        <link rel="stylesheet"  href="${createLinkTo(dir:'images',file:'colorbox.css')}" type="text/css" media="screen" /> 
        <script  src="${createLinkTo(dir:'images',file:'jquery.event.hover.js')}" type="text/javascript" ></script> 
       
		<g:javascript library="application" />				
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
                <g:renderErrors bean="${proposalInstance}" as="preProposalList" />
            </div>
            </g:hasErrors>
            
                <div class="dialog">
                    <table>
                        <tbody>
                        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="projectTitle"><g:message code="default.ProposalTitle.label"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalApplicationInstance, field: 'projectTitle', 'errors')}">
                                    <g:textField name="projectTitle" value="${proposalApplicationInstance?.projectTitle}" disabled="true"/>
                                </td>
                        </tr>
                        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description"><g:message code="default.Description.label" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: proposalInstance, field: 'description', 'errors')}">
                                    <g:textField name="description" value="${proposalInstance?.description}" disabled="true"/>
                                </td>
                            </tr>
                        
                        
                            <tr class="prop">
                                
                            <td valign="top" class="name">
                                    <label for="proposalCategory"><g:message code="preProposal.proposalCategory.label" default="Proposal Category" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: ProposalApplicationInstance, field: 'proposalCategory', 'errors')}">
                                 <g:select  optionKey="id" optionValue="name" id="proposalCategory" from="${ProposalCategory.findAll('from ProposalCategory PC where PC.activeYesNo=\'Y\' ')}"  name="proposalCategory.id" value="${proposalApplicationInstance?.proposalCategory?.id}"  disabled="true"></g:select>
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
                
           
        </div></div>
      </body>  
         
</html>