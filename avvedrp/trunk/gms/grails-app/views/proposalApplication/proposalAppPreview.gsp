<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251"/>
        <meta name="layout" content="main" />
        
        <title><g:message code="default.ProposalPreview.head"/></title>   
        <script src="${createLinkTo(dir:'js',file:'vk_popup.js?vk_layout=AM Armenian Eastern')}"> </script>           
    </head>
    <body>
    	
		<div class="wrapper">   
		<div class="wrapper">  
		<div id="spinner" class="spinner" style="display:none;">
            <img src="${createLinkTo(dir:'images',file:'spinner.gif')}" alt="Spinner" />
        </div>	
        
    <div class="innnerBanner">
	<div class="loginLink">
	<span>
	
	
	</a>&nbsp;&nbsp;  
<img src="${createLinkTo(dir:'images/themesky',file:'help.gif')}" onClick="window.open('../images/HELPDOC/UntitledFrameset-15.html','mywindow','width=800,height=500,left=0,top=100,screenX=0,screenY=100')">  
</a> &nbsp;&nbsp;|</a>&nbsp;&nbsp;<a href="#"><img src="${createLinkTo(dir:'images/themesky',file:'aboutUs.jpg')}" onClick="window.open('../images/aboutUs/AboutUs_MGMS_new.html','mywindow','width=600,height=300,left=0,top=100,screenX=0,screenY=100')">&nbsp;&nbsp;</a>
	
   	</span>
   	</div>
	</div> 
        <div class="body">
        
        
            <h1><g:message code="default.ProposalPreview.head"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectsInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="" method="post" controller="proposal" action="proposalSubmission">
                <div class="dialog">
                    <table>
                        <tbody>
                        <input type="hidden" name="proposalApplication.id" value="${proposalApplicationInstance?.id}">
                        <input type="hidden" name="actionName" value="${params.action}">
                         <tr class="prop">
                                <td valign="top" colspan="2" style="width:200px;" class="prname">
                                    <div class="horizontalLine"><g:message code="default.PartIPersonalDetails.head"/></div>
                                </td>
                                
                            </tr>
                         <g:each in="${proposalApplicationExtInstance}" var="proposalApplicationExtInstance">
                            <g:if test="${proposalApplicationExtInstance.page==1}">
                              <tr class="prop">
                                <td valign="top" style="width:200px;" class="prname">
                                    <label for="name"><g:message code="${proposalApplicationExtInstance.label}"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                
                                    <label for="name">
                                    ${proposalApplicationExtInstance.value}
                                    
                                    </label>
                                </td>
                            </tr> 
                            </g:if>
                            </g:each>
                            <tr class="prop">
                                <td valign="top" colspan="2" style="width:200px;text-align:center;">
                                    <g:link class="inputbutton" action="proposalAppPart1PersonalDetails" params="['proposalApplication.id':proposalApplicationInstance?.id,'status':'update']"><label for="name"><g:message code="default.Edit.label"/></label></g:link>
                                </td>
                           </tr>
                            <tr class="prop">
                                <td valign="top" colspan="2" style="width:200px;" class="prname">
                                    <div class="horizontalLine"><g:message code="default.PARTIInformationRelatingDepartment.head"/></div>
                                </td>
                           </tr>
                            <g:each in="${proposalApplicationExtInstance}" var="proposalApplicationExtInstance">
                            <g:if test="${proposalApplicationExtInstance.page==2}">
                              <tr class="prop">
                                <td valign="top" style="width:200px;BORDER-BOTTOM: #E6F3F7 1px solid;" >
                                    <label for="name"><g:message code="${proposalApplicationExtInstance.label}"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                
                                    <label for="name">
                                    ${proposalApplicationExtInstance.value}
                                    
                                    </label>
                                </td>
                            </tr> 
                            </g:if>
                            </g:each>
                            <tr class="prop">
                                <td valign="top" colspan="2" style="width:200px;text-align:center;">
                                    <g:link class="inputbutton" action="proposalAppPartInformationOfDepartment" params="['proposalApplication.id':proposalApplicationInstance?.id,'status':'update']"><label for="name"><g:message code="default.Edit.label"/></label></g:link>
                                </td>
                           </tr>
                            <tr class="prop">
                                <td valign="top" colspan="2" style="width:200px;" class="prname">
                                    <div class="horizontalLine"><g:message code="default.PARTIIInformationRelatingDepartment.head"/></div>
                                </td>
                                
                            </tr>
                            <g:each in="${proposalApplicationExtInstance}" var="proposalApplicationExtInstance">
                            <g:if test="${proposalApplicationExtInstance.page==3}">
                              <tr class="prop">
                                <td valign="top" style="width:200px;BORDER-BOTTOM: #E6F3F7 1px solid;">
                                    <label for="name"><g:message code="${proposalApplicationExtInstance.label}"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                
                                    <label for="name">
                                    ${proposalApplicationExtInstance.value}
                                    
                                    </label>
                                </td>
                            </tr> 
                            </g:if>
                            </g:each>
                            <tr class="prop">
                                <td valign="top" colspan="2" style="width:200px;text-align:center;">
                                    <g:link class="inputbutton" action="proposalAppPartThreeInformationRelatingDepartment" params="['proposalApplication.id':proposalApplicationInstance?.id,'status':'update']"><label for="name"><g:message code="default.Edit.label"/></label></g:link>
                                </td>
                           </tr>
                            <tr class="prop">
                                <td valign="top" colspan="2" style="width:200px;" class="prname">
                                    <div class="horizontalLine"><g:message code="default.PARTFiveAboutResearchProject.head"/></div>
                                </td>
                                
                            </tr>
                            <g:each in="${proposalApplicationExtInstance}" var="proposalApplicationExtInstance">
                            <g:if test="${proposalApplicationExtInstance.page==4}">
                              <tr class="prop">
                                <td valign="top" style="width:200px;BORDER-BOTTOM: #E6F3F7 1px solid;">
                                    <label for="name"><g:message code="${proposalApplicationExtInstance.label}"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                
                                    <label for="name">
                                    ${proposalApplicationExtInstance.value}
                                    
                                    </label>
                                </td>
                            </tr> 
                            </g:if>
                            </g:each>
                            <tr class="prop">
                                <td valign="top" colspan="2" style="width:200px;text-align:center;">
                                    <g:link class="inputbutton" action="proposalAppPartFourAboutResearchProject" params="['proposalApplication.id':proposalApplicationInstance?.id,'status':'update']"><label for="name"><g:message code="default.Edit.label"/></label></g:link>
                                </td>
                           </tr>
                            <tr class="prop">
                                <td valign="top" colspan="2" style="width:200px;" class="prname">
                                    <div class="horizontalLine"><g:message code="default.PartFiveDetailProjectReport.head"/></div>
                                </td>
                                
                            </tr>
                            <g:each in="${proposalApplicationExtInstance}" var="proposalApplicationExtInstance">
                            <g:if test="${proposalApplicationExtInstance.page==5}">
                              <tr class="prop">
                                <td valign="top" style="width:200px;BORDER-BOTTOM: #E6F3F7 1px solid;">
                                    <label for="name"><g:message code="${proposalApplicationExtInstance.label}"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                
                                    <label for="name">
                                    ${proposalApplicationExtInstance.value}
                                    
                                    </label>
                                </td>
                            </tr> 
                            </g:if>
                            </g:each>
                            <tr class="prop">
                                <td valign="top" colspan="2" style="width:200px;text-align:center;">
                                    <g:link class="inputbutton" action="proposalAppPartFiveDetailProjectReport" params="['proposalApplication.id':proposalApplicationInstance?.id,'status':'update']"><label for="name"><g:message code="default.Edit.label"/></label></g:link>
                                </td>
                           </tr>
                            <tr class="prop">
                                <td valign="top" colspan="2" style="width:200px;" class="prname">
                                    <div class="horizontalLine"><g:message code="default.PartSixUploadDocuments.head"/></div>
                                </td>
                                
                            </tr>
                            <g:each in="${proposalApplicationExtInstance}" var="proposalApplicationExtInstance">
                            <g:if test="${proposalApplicationExtInstance.page==6}">
                              <tr class="prop">
                                <td valign="top" style="width:200px;BORDER-BOTTOM: #E6F3F7 1px solid;">
                                    <label for="name"><g:message code="${proposalApplicationExtInstance.label}"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                
                                    <label for="name">
                                    ${proposalApplicationExtInstance.value}
                                    
                                    </label>
                                </td>
                            </tr> 
                            </g:if>
                            </g:each>
                             <tr class="prop">
                                <td valign="top" class="prname">
                                    <g:link action="downloadAttachments" controller="attachments" id="${attachmentsInstanceGetCV.id}"><label for="name"><g:message code="default.ClickHereforCV.label"/></label></g:link>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                &nbsp;
                                    
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="prname">
                                    <g:link action="downloadAttachments" controller="attachments" id="${attachmentsInstanceGetDPR.id}"><label for="name"><g:message code="default.ClickHereforDPR.label"/></label></g:link>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                &nbsp;
                                    
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" colspan="2" style="width:200px;text-align:center;">
                                    <g:link class="inputbutton" action="proposalAppPartSixUploadDocuments" params="['proposalApplication.id':proposalApplicationInstance?.id,'status':'update']"><label for="name"><g:message code="default.Edit.label"/></label></g:link>
                                </td>
                           </tr>
                            <tr class="prop">
                                <td valign="top" colspan="2" style="width:200px;" class="prname">
                                    <div class="horizontalLine"><g:message code="default.PartSevenDPRSummary.head"/></div>
                                </td>
                                
                            </tr>
                            <g:each in="${proposalApplicationExtInstance}" var="proposalApplicationExtInstance">
                            <g:if test="${proposalApplicationExtInstance.page==7}">
                              <tr class="prop">
                                <td valign="top" style="width:50%;BORDER-BOTTOM: #E6F3F7 1px solid;">
                                    <label for="name"><g:message code="${proposalApplicationExtInstance.label}"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                
                                    <label for="name">
                                    ${proposalApplicationExtInstance.value}
                                    
                                    </label>
                                </td>
                            </tr> 
                            </g:if>
                            
                        </g:each>
                            <tr class="prop">
                                <td valign="top" colspan="2" style="width:200px;text-align:center;">
                                    <g:link class="inputbutton" action="proposalAppSummary" params="['proposalApplication.id':proposalApplicationInstance?.id,'status':'update']"><label for="name"><g:message code="default.Edit.label"/></label></g:link>
                                </td>
                           </tr>
                         
                        </tbody>
                    </table>
                    <input type=hidden id="proposalId" name="proposalId" value="${proposalApplicationInstance?.proposal?.id}">
                </div>
                <div>
                    <span class="button"><input class="inputbutton" type="submit" value="${message(code: 'default.Submit.button')}" />
                    &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="inputbutton" onClick="Redirect()"  value="${message(code: 'default.Cancel.button')}"/></span>
                    
                </div>
            </g:form>
        </div>
                        </td>
        </tr><br/>
        
         </table>
         </div>
    </body>
</html>
