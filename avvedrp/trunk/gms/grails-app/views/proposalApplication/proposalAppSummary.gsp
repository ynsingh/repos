<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251"/>
        <meta name="layout" content="main" />
        <g:javascript library="jquery"/>
        <title><g:message code="default.SummaryOfDPR.head"/></title>   
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
        <g:pageNavigation page="7" proposalApplicationInstance="${proposalApplicationInstance?.id}"/>
        
            <h1><g:message code="default.SummaryOfDPR.head"/></h1>
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
                        <input type="hidden" name="Page" value="${7}">
                             <tr class="prop">
                                <td colspan="2" class="nameline" style="text-align:right;"><font face="Arial, sans-serif" size="-1"><span style="color:#663366;">
                    		<label for="name"><b><g:message code="default.ControlNumber.label"/></b></label>:-${proposalApplicationInstance?.controllerId}<br/><br/>
                    		<label for="name"><b><g:message code="default.Version.label"/></b></label>:-V${proposalApplicationInstance?.version}
                    		</span></font></td>
                            </tr>
                              <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="name"><g:message code="default.NameOfTheProject.label"/>:-</label>
                                </td>
                               
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <label for="TitleOfTheResearchProject_2"></label>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="nameline">
                                    <label for="name"><g:message code="default.ControlNo.label"/>:-</label>
                                </td>
                               
                                <td valign="top" class="prvalue">
                                    ${proposalApplicationInstance.controllerId}
                                </td>
                            </tr>  
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="name"><g:message code="default.NameOfPI.label"/>:-</label>
                                </td>
                               
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    
                                </td>
                            </tr> 
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="name"><g:message code="default.NameOfTheInstitute.label"/>:-</label>
                                </td>
                               
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    
                                </td>
                            </tr>
                              <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="name">1.<g:message code="default.Objective.label"/>:-</label>
                                </td>
                                </tr>
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <fckeditor:editor name="SummaryObjective_1" width="100%" height="300" fileBrowser="default">
						    </fckeditor:editor>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="code">2.<g:message code="default.Deliverables.label"/>:</label>
                                </td>
                                </tr>
                            <tr class="prop">
                            	<td valign="top" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <fckeditor:editor name="Deliverables_2" width="100%" height="300" fileBrowser="default">
						    </fckeditor:editor>
                                </td>
                            </tr> 
                        	
                        	<tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="code"><g:message code="default.SuggestionsWithR.label"/>:</label>
                                </td>
                                </tr>
                                               
                         <tr class="prop">
                         	<td valign="top" class="prvalue"><label for="code">(i)<g:message code="default.ArrangementForQuality.label"/>:</label></td> 
                                <td valign="top" class="prvalue">
                                    <fckeditor:editor name="ArrangementForQuality_3" width="100%" height="300" fileBrowser="default">
						    </fckeditor:editor>
                                </td>
                            </tr>
                                               
                                                                       
                         <tr class="prop">
                         	<td valign="top" class="prvalue"><label for="code">(ii)<g:message code="default.Accuracy.label"/>:</label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                   <fckeditor:editor name="Accuracy_4" width="100%" height="300" fileBrowser="default">
						    </fckeditor:editor>
                                </td>
                            </tr>
                                                 
                                                                           
                         <tr class="prop">
                         	<td valign="top" class="prvalue"><label for="code">(iii)<g:message code="default.Coverage.label"/>:</label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                   <fckeditor:editor name="Coverage_5" width="100%" height="300" fileBrowser="default">
						    </fckeditor:editor>
                                </td>
                            </tr>
                                                                           
                         <tr class="prop">
                         	<td valign="top" class="prvalue"><label for="code">(iv)<g:message code="default.UpdationMechanism.label"/>:</label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                   <fckeditor:editor name="UpdationMechanism_6" width="100%" height="300" fileBrowser="default">
						    </fckeditor:editor>
                                </td>
                            </tr>
                            
                                                                          
                         <tr class="prop">
                         	<td valign="top" class="prvalue"><label for="code">(v)<g:message code="default.TestingByUsers.label"/></label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <fckeditor:editor name="TestingByUsers_7" width="100%" height="300" fileBrowser="default">
						    </fckeditor:editor>
                                </td>
                            </tr>
                            
                                               
                         <tr class="prop">
                         	<td valign="top" class="prvalue"><label for="code">(vi)<g:message code="default.TestingByPeerGroup.label"/></label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <fckeditor:editor name="TestingByPeerGroup_8" width="100%" height="300" fileBrowser="default">
						    </fckeditor:editor>
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="code"><g:message code="default.ScalingUp.label"/>:</label>
                                </td>
                                </tr>
                            
                          <tr class="prop">
                         	<td valign="top" class="prvalue"><g:message code="default.a.label"/>)<label for="code">7.<g:message code="default.Plan.label"/></label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <fckeditor:editor name="Plan_9" width="100%" height="300" fileBrowser="default">
						    </fckeditor:editor>
                                </td>
                           </tr>
                           <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="code"><g:message code="default.b.label"/><g:message code="default.Strategy.label"/>:</label>
                                </td>
                                </tr>
                                <tr class="prop">
                         	<td valign="top" class="nameline">
                                    <label for="code">(i)<g:message code="default.InHouse.label"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <g:textArea rows="2" cols="20" size="45" id="InHouse" name="InHouse_10" />
                                </td>
                            </tr>
                            <td valign="top" class="nameline">
                                    <label for="code">(ii)<g:message code="default.OutSourcing.label"/>:</label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <g:textArea rows="2" cols="20" size="45" id="OutSourcing" name="OutSourcing_11" />
                                    <br/><label for="code"><g:message code="default.InCaseOfOutsourcing.label"/>:</label>
                                </td>
                            </tr>
                           
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="name">5.<g:message code="default.PopularizingAndExtension.label"/>:</label>
                                </td>
                                </tr>
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue"><label for="name"><g:message code="default.a.label"/>.<g:message code="default.StrategyForPopularization.label"/>:</label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <fckeditor:editor name="StrategyForPopularization_12" width="100%" height="300" fileBrowser="default">
						    </fckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="name">5.<g:message code="default.ExtensionActivitiesPlans.label"/>:</label>
                                </td>
                                </tr>
                         <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue"><label for="name">i)<g:message code="default.Maintenance.label"/>:</label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <fckeditor:editor name="Maintenance_13" width="100%" height="300" fileBrowser="default">
						    </fckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue"><label for="name">ii)<g:message code="default.UserFeedbackMechanism.label"/>:</label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <fckeditor:editor name="UserFeedbackMechanism_14" width="100%" height="300" fileBrowser="default">
						    </fckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue"><label for="name"><g:message code="default.a.label"/>)<g:message code="default.FrequencyOfReview.label"/>:</label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <fckeditor:editor name="FrequencyOfReview_15" width="100%" height="300" fileBrowser="default">
						    </fckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="name">6.<g:message code="default.ReviewMechanism.label"/>:</label>
                                </td>
                                </tr>
                            
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue"><label for="name"><g:message code="default.b.label"/>.)<g:message code="default.ListAtLeastReviewers.label"/>:</label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <fckeditor:editor name="ListAtLeastReviewers_16" width="100%" height="300" fileBrowser="default">
						    </fckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="name"><g:message code="default.a.label"/><g:message code="default.CapitalExpenditure.label"/>:</label>
                                </td>
                                </tr>
                            
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue"><label for="name">i).<g:message code="default.DetailsCapturedItemwise.label"/>:</label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <fckeditor:editor name="DetailsCapturedItemwise_17" width="100%" height="300" fileBrowser="default">
						    </fckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue"><label for="name">ii).<g:message code="default.FocusedCommentsHighCostEquipment.label"/>:</label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <fckeditor:editor name="FocusedCommentsHighCostEquipment_18" width="100%" height="300" fileBrowser="default">
						    </fckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue">
                                 <label for="name"><g:message code="default.a.label"/>.<g:message code="default.RevenueExpe.label"/></label>
                                 <br/>
                                 <label for="name">i).<g:message code="default.RevenueDeCapturedItemwise.label"/>:</label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <fckeditor:editor name="RevenueDeCapturedItemwise_19" width="100%" height="300" fileBrowser="default">
						    </fckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="name">7.<g:message code="default.SumBudget.label"/>:</label>
                                </td>
                                </tr>
                            
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue"><label for="name">ii).<g:message code="default.FocusedCommentsOn.label"/>:</label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <fckeditor:editor name="ListAtLeastReviewers_20" width="100%" height="300" fileBrowser="default">
						    </fckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue"><label for="name">ii).<g:message code="default.TotalConsultancyFee.label"/>:</label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <fckeditor:editor name="TotalConsultancyFee_21" width="100%" height="300" fileBrowser="default">
						    </fckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue"><label for="name">ii).<g:message code="default.IndicateAnchorInstitution.label"/>:</label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <fckeditor:editor name="IndicateAnchorInstitution_22" width="100%" height="300" fileBrowser="default">
						    </fckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="name">8.<g:message code="default.CostBenefitAnalysis.label"/>:</label>
                                </td>
                                </tr>
                            
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <fckeditor:editor name="CostBenefitAnalysis_23" width="100%" height="300" fileBrowser="default">
						    </fckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="name">9.<g:message code="default.SocialImpact.label"/>:</label>
                                </td>
                                </tr>
                            
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <fckeditor:editor name="SocialImpact_24" width="100%" height="300" fileBrowser="default">
						    </fckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="name">10.<g:message code="default.OutcomeExtentToProject.label"/>:</label>
                                </td>
                                </tr>
                            
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <fckeditor:editor name="OutcomeExtentToProject_24" width="100%" height="300" fileBrowser="default">
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
                    <input class="inputbutton" type="submit" value="${message(code: 'default.Submit.button')}" />
                    </g:else>
                    </span>
                    
                </div>
            </g:form>
            <g:pageNavigation page="7" proposalApplicationInstance="${proposalApplicationInstance?.id}"/>
        </div>
                        
        
         
         </div>
    </body>
</html>
