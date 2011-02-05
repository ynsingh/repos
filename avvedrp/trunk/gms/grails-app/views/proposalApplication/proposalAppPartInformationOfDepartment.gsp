<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251"/>
        <meta name="layout" content="main" />
        <g:javascript library="jquery"/>
        <title><g:message code="default.PARTIInformationRelatingDepartment.head"/></title>   
        <script src="${createLinkTo(dir:'js',file:'vk_popup.js?vk_layout=AM Armenian Eastern')}"> </script>           
   		<script>
    ${remoteFunction(action:'getForm', controller:'proposalApplication',onSuccess:'returnFormResult(data)')};
    </script>
    </head>
    <body>
    	
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
        <g:pageNavigation page="2" proposalApplicationInstance="${proposalApplicationInstance?.id}"/>
        
            <h1><g:message code="default.PARTIInformationRelatingDepartment.head"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectsInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="saveProposalAppPart" method="post" >
                <div class="dialog">
                    <table border='0'>
                        <tbody>
                        <input type="hidden" name="proposalApplication.id" value="${proposalApplicationInstance?.id}">
                        <input type="hidden" name="actionName" value="${params.action}">
                        <input type="hidden" name="Page" value="${2}">
                              <tr class="prop">
                                <td colspan="2" class="nameline" style="text-align:right;"><font face="Arial, sans-serif" size="-1"><span style="color:#663366;">
                    		<label for="name"><b><g:message code="default.ControlNumber.label"/></b></label>:-${proposalApplicationInstance?.controllerId}<br/><br/>
                    		<label for="name"><b><g:message code="default.Version.label"/></b></label>:-V${proposalApplicationInstance?.version}
                    		</span></font></td>
                            </tr>
                              <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="name">1.<g:message code="default.NameOfInstituteWithCompleteAddress.label"/>:</label>
                                </td>
                                </tr>
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <g:textArea rows="2" cols="20" size="45" id="NameOfInstituteWithCompleteAddress" name="NameOfInstituteWithCompleteAddress_1" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="code">2.<g:message code="default.TitleOfTheResearchProject.label"/>:</label>
                                </td>
                                </tr>
                            <tr class="prop">
                            	<td valign="top" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <input type="text" size="45" id="TitleOfTheResearchProject" name="TitleOfTheResearchProject_2" />
                                </td>
                            </tr> 
                        	
                        	<tr class="prop">
                                <td valign="top" class="nameline">
                                    <label for="code">3.<g:message code="default.Department.label"/>/<g:message code="default.BroadArea.label"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <input type="text" size="45" id="DepartmentOrBroadArea" name="DepartmentOrBroadArea_3" />
                                </td>
                            </tr> 
                           <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="code">4.<g:message code="default.MajorAreasOfResearchInTheDepartment.label"/>:</label>
                                </td>
                                </tr>
                                               
                         <tr class="prop">
                         	<td valign="top" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue">
                                    <fckeditor:editor name="MajorAreasOfResearchInTheDepartment_4" width="100%" height="300" fileBrowser="default">
						    </fckeditor:editor>
                                </td>
                            </tr>
                                               
                         <tr class="prop">
                                <td valign="top" colspan="2" class="prvalue">
                                    <label for="code">5.<g:message code="default.NamesDesignationOfPrincipalResearchers.label"/>:</label>
                                </td>
                                </tr>
                                               
                         <tr class="prop">
                         	<td valign="top" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                   <fckeditor:editor name="NamesDesignationOfPrincipalResearchers_5" width="100%" height="300" fileBrowser="default">
						    </fckeditor:editor>
                                </td>
                            </tr>
                                                 
                             <tr class="prop">
                                <td valign="top" class="nameline">
                                    <label for="code">6.<g:message code="default.IsTtInterdisciplinaryProject.label"/></label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <g:radio name="IsTtInterdisciplinaryProject_6" value="Y"/><label for="IsTtInterdisciplinaryProject"><g:message code="default.YES.label"/></label>
									<g:radio name="IsTtInterdisciplinaryProject_6" value="N" checked="true"/><label for="IsTtInterdisciplinaryProject"><g:message code="default.NO.label"/></label>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="nameline">
                                    <label for="code">7.<g:message code="default.IsitInterInstitutionalProject.label"/></label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <g:radio name="IsitInterInstitutionalProject_7" value="Y"/><label for="IsitInterInstitutionalProject"><g:message code="default.YES.label"/></label>
									<g:radio name="IsitInterInstitutionalProject_7" value="N" checked="true"/><label for="IsitInterInstitutionalProject"><g:message code="default.NO.label"/></label>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="nameline">
                                    <label for="code">8.<g:message code="default.IsanyIndustryUseragencyparticipating.label"/></label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <g:radio name="IsanyIndustryUseragencyparticipating_8" value="Y"/><label for="IsitInterInstitutionalProject"><g:message code="default.YES.label"/></label>
									<g:radio name="IsanyIndustryUseragencyparticipating_8" value="N" /><label for="IsitInterInstitutionalProject"><g:message code="default.NO.label"/></label>
                                
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="code">9.<g:message code="default.BriefOfCompletedOngoingResearchProjects.label"/></label>
                                </td>
                                </tr>
                                               
                         <tr class="prop">
                         	<td valign="top" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <fckeditor:editor name="BriefOfCompletedOngoingResearchProjects_9" width="100%" height="300" fileBrowser="default">
						    </fckeditor:editor>
                                </td>
                            </tr>
                            
                         
                        </tbody>
                    </table>
                </div>
                <div>
                    <span class="button">
                    <g:if test="${params.status=='update'}">
                    <input type="hidden" name="status" value="update">
                    <input class="inputbutton" type="submit" value="${message(code: 'default.Update.button')}" />
                    </g:if>
                    <g:else>
                    <input class="inputbutton" type="submit" value="${message(code: 'default.Next.button')}" />
                    </g:else>
                    </span>
                    
                </div>
            </g:form>
             <g:pageNavigation page="2" proposalApplicationInstance="${proposalApplicationInstance?.id}"/>
        </div>
                        </td>
        </tr><br/>
       
         </table>
         </div>
    </body>
</html>
