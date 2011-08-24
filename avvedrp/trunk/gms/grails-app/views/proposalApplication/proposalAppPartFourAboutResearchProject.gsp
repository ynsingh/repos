<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251"/>
        <meta name="layout" content="main" />
        <g:javascript library="jquery"/>
        <title><g:message code="default.PARTFiveAboutResearchProject.head"/></title>   
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
        <g:pageNavigation status="${params.status}" page="4" proposalApplicationInstance="${proposalApplicationInstance?.id}"/>
        
            <h1><g:message code="default.PARTFiveAboutResearchProject.head"/></h1>
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
                        <input type="hidden" name="Page" value="${4}">
                              <tr class="prop">
                                <td colspan="2" class="nameline" style="text-align:right;"><font face="Arial, sans-serif" size="-1"><span style="color:#663366;">
                    		<label for="name"><b><g:message code="default.ControlNumber.label"/></b></label>:-${proposalApplicationInstance?.controllerId}<br/><br/>
                    		<label for="name"><b><g:message code="default.Version.label"/></b></label>:-V${proposalApplicationInstance?.proposal?.proposalVersion}
                    		</span></font></td>
                            </tr>
                              <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="name">15.(<g:message code="default.a.label"/>)<g:message code="default.SummaryOfProject.label"/>:</label>
                                </td>
                                </tr>
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <ckeditor:editor name="SummaryOfProject_1" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="code">(<g:message code="default.b.label"/>)<g:message code="default.JustificationImportanceOfProjects.label"/>:</label>
                                </td>
                                </tr>
                            <tr class="prop">
                            	<td valign="top" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <ckeditor:editor name="JustificationImportanceOfProjects_2" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr> 
                        	
                        	<tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="code">(<g:message code="default.c.label"/>)<g:message code="default.DetailsOfWorkAlreadyDoneByPI.label"/>:</label>
                                </td>
                                </tr>
                                               
                         <tr class="prop">
                         	<td valign="top" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue">
                                <ckeditor:editor name="DetailsOfWorkAlreadyDoneByPI_3" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr>
                                               
                         <tr class="prop">
                                <td valign="top" colspan="2" class="prvalue">
                                    <label for="code">16.<g:message code="default.TotalAmountRequired.label"/></label>
                                </td>
                                </tr>
                                               
                         <tr class="prop">
                         	<td valign="top" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                   <ckeditor:editor name="TotalAmountRequired_4" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr>
                                                 
                             <tr class="prop">
                                <td valign="top" colspan="2" class="prvalue">
                                    <label for="code">17.(<g:message code="default.a.label"/>)<g:message code="default.RecurringBudgetOfProposal.label"/></label>
                                </td>
                                </tr>
                                               
                         <tr class="prop">
                         	<td valign="top" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <ckeditor:editor name="RecurringBudgetOfProposal_5" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" colspan="2" class="prvalue">
                                    <label for="code">(<g:message code="default.b.label"/>)<g:message code="default.DetailedBreakupOfNonRecurringItems.label"/></label>
                                </td>
                                </tr>
                                               
                         <tr class="prop">
                         	<td valign="top" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                   <ckeditor:editor name="DetailedBreakupOfNonRecurringItems_6" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="code">18.<g:message code="default.SUMMARYSHEET.label"/>:</label>
                                </td>
                                </tr>
                                               
                         <tr class="prop">
                         	<td valign="top" class="nameline">
                                    <label for="code">1.<g:message code="default.NameOfInstitution.label"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                     <input type="text" size="45" id="NameOfInstitution" name="NameOfInstitution_7" />
                                </td>
                            </tr>
                            <tr class="prop">
                         	<td valign="top" class="nameline">
                                    <label for="code">2.<g:message code="default.TitleOfTheProject.label"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                     <input type="text" size="45" id="TitleOfTheProject" name="TitleOfTheProject_8" />
                                </td>
                            </tr>
                            <tr class="prop">
                         	<td valign="top" class="nameline">
                                    <label for="code">3.<g:message code="default.NameOfTheDepartment.label"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                     <input type="text" size="45" id="NameOfTheDepartment" name="NameOfTheDepartment_9" />
                                </td>
                            </tr>
                            <tr class="prop">
                         	<td valign="top" class="nameline">
                                    <label for="code">4.<g:message code="default.CostOfTheProject.label"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                     <input type="text" size="45" id="CostOfTheProject" name="CostOfTheProject_10" />
                                </td>
                            </tr>
                            <tr class="prop">
                            <td valign="top" class="nameline" >
                                    <label for="code">5.<g:message code="default.AmountReleasedEarlierIfAny.label"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                     <input type="text" size="45" id="AmountReleasedEarlierIfAny" name="AmountReleasedEarlierIfAny_11" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="code">6.<g:message code="default.UtilizationPositionInRespectOfGrantsReleased.label"/>:</label>
                                </td>
                            </tr>
                            <tr class="prop">
                            <td colspan="1" style="width:200px;">
                			<br />
                 			(i) <label for="code"><g:message code="default.FullySpent.label"/>:           
                 				<br /><br /><br />
                 			(ii)<label for="code"><g:message code="default.UnspentproposalToUtilize.label"/>:   </td>
                            
                                    
                               <td valign="top">
                               <br />
                                     <input type="text" size="45" id="FullySpent" name="FullySpent_12" />
                              <br /><br />      
                                     <input type="text" size="45" id="UnspentproposalToUtilize" name="UnspentproposalToUtilize_13" />
                                </td>
                            </tr>
                         <tr class="prop">
                         <td valign="top" class="prvalue">&nbsp;</td> <td valign="top" class="prvalue">&nbsp;</td></tr>
                           <tr class="prop">
                            <td valign="top" class="nameline" >
                                    <label for="code">7.<g:message code="default.ReasonsForUnspentBalance.label"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                     <input type="text" size="45" id="ReasonsForUnspentBalance" name="ReasonsForUnspentBalance_14" />
                                </td>
                            </tr>
                             <tr class="prop">
                            <td valign="top" colspan="2"  class="nameline" >
                                    <label for="code">8.<g:message code="default.NameOfPIResponsibleForImplementationOfProject.label"/>:</label>
                                </td>
                                </tr>
                                               
                         <tr class="prop">
                         <td valign="top" class="prvalue">&nbsp;</td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                     <input type="text" size="45" id="NameOfPIResponsibleForImplementationOfProject" name="NameOfPIResponsibleForImplementationOfProject_15" />
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
            <g:pageNavigation status="${params.status}" page="4" proposalApplicationInstance="${proposalApplicationInstance?.id}"/>
        </div>
                        
         </div>
         <div class="footerdBar">
<br>
<label style="text-align: center;font: bold 9px Verdana;color: #104d6b;"><g:message code="default.footerMsg.label"/></label>
</div>
    </body>
</html>
