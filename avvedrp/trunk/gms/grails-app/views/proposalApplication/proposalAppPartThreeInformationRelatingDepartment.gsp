<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251"/>
        <meta name="layout" content="main" />
        <g:javascript library="jquery"/>
        <title><g:message code="default.PartIPersonalDetails.head"/></title>   
        <script src="${createLinkTo(dir:'js',file:'vk_popup.js?vk_layout=AM Armenian Eastern')}"> </script>           
    	<script>
    $(document).ready(function(){${remoteFunction(action:'getForm', controller:'proposalApplication',onSuccess:'returnFormResult(data)')};});
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
        <g:pageNavigation status="${params.status}" page="3" proposalApplicationInstance="${proposalApplicationInstance?.id}"/>
        
            <h1><g:message code="default.PARTIIInformationRelatingDepartment.head"/></h1>
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
                        <input type="hidden" name="Page" value="${3}">
                        <tr class="prop">
                                <td colspan="2" class="nameline" style="text-align:right;"><font face="Arial, sans-serif" size="-1"><span style="color:#663366;">
                    		<label for="name"><b><g:message code="default.ControlNumber.label"/></b></label>:-${proposalApplicationInstance?.controllerId}<br/><br/>
                    		<label for="name"><b><g:message code="default.Version.label"/></b></label>:-V${proposalApplicationInstance?.proposal?.proposalVersion}
                    		</span></font></td>
                            </tr>
                              <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="name">10.<g:message code="default.PrincipalInvestigatorDetails.label"/>:</label>
                                </td>
                                </tr>
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <ckeditor:editor name="PrincipalInvestigatorDetails_1" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="code">10A.<g:message code="default.CoPrincipalInvestigatorDetails.label"/>:</label>
                                </td>
                                </tr>
                            <tr class="prop">
                            	<td valign="top" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <ckeditor:editor name="CoPrincipalInvestigatorDetails_2" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr> 
                        	
                        	<tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="code">11.<g:message code="default.InCaseJointProjectWithOtherInstitution.label"/>:</label>
                                </td>
                                </tr>
                                               
                         <tr class="prop">
                         	<td valign="top" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue">
                                    <ckeditor:editor name="InCaseJointProjectWithOtherInstitution_3" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr>
                                               
                         <tr class="prop">
                                <td valign="top" colspan="2" class="prvalue">
                                    <label for="code">12.<g:message code="default.InCaseIndustryOruserAgencyIsParticipating.label"/></label>
                                </td>
                                </tr>
                                               
                         <tr class="prop">
                         	<td valign="top" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                   <ckeditor:editor name="InCaseIndustryOruserAgencyIsParticipating_4" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr>
                                                 
                             <tr class="prop">
                                <td valign="top" colspan="2" class="prvalue">
                                    <label for="code">13.<g:message code="default.PresentCommitmentsOfThePrincipalInvestigators.label"/></label>
                                </td>
                                </tr>
                                               
                         <tr class="prop">
                         	<td valign="top" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                   <ckeditor:editor name="PresentCommitmentsOfThePrincipalInvestigators_5" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" colspan="2" class="prvalue">
                                    <label for="code">13A.<g:message code="default.PresentCommitmentsOfTheCoPrincipal.label"/></label>
                                </td>
                                </tr>
                                               
                         <tr class="prop">
                         	<td valign="top" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                   <ckeditor:editor name="PresentCommitmentsOfTheCoPrincipal_6" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="code">14.<g:message code="default.OtherMembersOfResearchGroup.label"/></label>
                                </td>
                                </tr>
                                               
                         <tr class="prop">
                         	<td valign="top" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <ckeditor:editor name="OtherMembersOfResearchGroup_7" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
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
                    <g:actionSubmit class="inputbutton" action="proposalAppPreview" value="${message(code: 'default.Cancel.button')}" />
                    </g:if>
                    <g:else>
                    <input class="inputbutton" type="submit" value="${message(code: 'default.Next.button')}" />
                    </g:else>
                    </span>
                    
                </div>
            </g:form>
             <g:pageNavigation status="${params.status}" page="3" proposalApplicationInstance="${proposalApplicationInstance?.id}"/>
        </div>
                        
       
      
         </div>
         <div class="footerdBar">
<br>
<label style="text-align: center;font: bold 9px Verdana;color: #104d6b;"><g:message code="default.footerMsg.label"/></label>
</div>
    </body>
</html>
