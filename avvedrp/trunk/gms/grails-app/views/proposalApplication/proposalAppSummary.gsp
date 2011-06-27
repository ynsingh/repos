<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251"/>
        <meta name="layout" content="main" />
        <g:javascript library="jquery"/>
        <title><g:message code="default.SummaryOfDPR.head"/></title>   
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
        <g:pageNavigation status="${params.status}" page="7" proposalApplicationInstance="${proposalApplicationInstance?.id}"/>
        
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
                    		<label for="name"><b><g:message code="default.Version.label"/></b></label>:-V${proposalApplicationInstance?.proposal?.proposalVersion}
                    		</span></font></td>
                            </tr>
                              <tr class="prop">
                                <td valign="top" class="nameline">
                                    <label for="name"><g:message code="default.NameOfTheProject.label"/>:-</label>
                                </td>
                               
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <label for="TitleOfTheResearchProject_2">${projectTitle}</label>
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
                                <td valign="top" class="nameline">
                                    <label for="name"><g:message code="default.NameOfPI.label"/>:-</label>
                                </td>
                               
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <label for="TitleOfTheResearchProject_2">${proposalApplicationInstance.name}</label>
                                </td>
                            </tr> 
                            <tr class="prop">
                                <td valign="top" class="nameline">
                                    <label for="name"><g:message code="default.NameOfTheInstitute.label"/>:-</label>
                                </td>
                               
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <label for="organisation">${proposalApplicationInstance.organisation}</label>
                                </td>
                            </tr>
                              <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="name">1.<g:message code="default.Objective.label"/>:-</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                </tr>
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <ckeditor:editor name="SummaryObjective_1" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="code">2.<g:message code="default.Deliverables.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                </tr>
                            <tr class="prop">
                            	<td valign="top" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <ckeditor:editor name="Deliverables_2" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr> 
                        	
                        	<tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="code">3.<g:message code="default.SuggestionsWithR.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                </tr>
                                               
                         <tr class="prop">
                         	<td valign="top" class="prvalue"><label for="code">(i)<g:message code="default.ArrangementForQuality.label"/>:</label>
                         	<label for="name" class="mandatory"> * </label></td> 
                                <td valign="top" class="prvalue">
                                    <ckeditor:editor name="ArrangementForQuality_3" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr>
                                               
                                                                       
                         <tr class="prop">
                         	<td valign="top" class="prvalue"><label for="code">(ii)<g:message code="default.Accuracy.label"/>:</label>
                         	<label for="name" class="mandatory"> * </label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                   <ckeditor:editor name="Accuracy_4" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr>
                                                 
                                                                           
                         <tr class="prop">
                         	<td valign="top" class="prvalue"><label for="code">(iii)<g:message code="default.Coverage.label"/>:</label>
                         	<label for="name" class="mandatory"> * </label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                   <ckeditor:editor name="Coverage_5" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr>
                                                                           
                         <tr class="prop">
                         	<td valign="top" class="prvalue"><label for="code">(iv)<g:message code="default.UpdationMechanism.label"/>:</label>
                         	<label for="name" class="mandatory"> * </label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                   <ckeditor:editor name="UpdationMechanism_6" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr>
                            
                                                                          
                         <tr class="prop">
                         	<td valign="top" class="prvalue"><label for="code">(v)<g:message code="default.TestingByUsers.label"/></label>
                         	<label for="name" class="mandatory"> * </label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <ckeditor:editor name="TestingByUsers_7" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr>
                            
                                               
                         <tr class="prop">
                         	<td valign="top" class="prvalue"><label for="code">(vi)<g:message code="default.TestingByPeerGroup.label"/></label>
                         	<label for="name" class="mandatory"> * </label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <ckeditor:editor name="TestingByPeerGroup_8" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="code">4.<g:message code="default.ScalingUp.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                </tr>
                            
                          <tr class="prop">
                         	<td valign="top" class="prvalue"><g:message code="default.a.label"/>)<label for="code"><g:message code="default.Plan.label"/></label>
                         	<label for="name" class="mandatory"> * </label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <ckeditor:editor name="Plan_9" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                           </tr>
                           <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="code"><g:message code="default.b.label"/>)<g:message code="default.Strategy.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                </tr>
                                <tr class="prop">
                         	<td valign="top" class="nameline">
                                    <label for="code">(i)<g:message code="default.InHouse.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <g:textArea rows="2" cols="20" size="45" id="InHouse" name="InHouse_10" />
                                </td>
                            </tr>
                            <td valign="top" class="nameline">
                                    <label for="code">(ii)<g:message code="default.OutSourcing.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <g:textArea rows="2" cols="20" size="45" id="OutSourcing" name="OutSourcing_11" />
                                    <br/><label for="code"><g:message code="default.InCaseOfOutsourcing.label"/>:</label>
                                </td>
                            </tr>
                           
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="name">5.<g:message code="default.PopularizingAndExtension.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                </tr>
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue"><label for="name"><g:message code="default.a.label"/>)<g:message code="default.StrategyForPopularization.label"/>:</label>
                                 <label for="name" class="mandatory"> * </label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <ckeditor:editor name="StrategyForPopularization_12" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="name"><g:message code="default.b.label"/>)<g:message code="default.ExtensionActivitiesPlans.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                </tr>
                         <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue"><label for="name">(i)<g:message code="default.Maintenance.label"/>:</label>
                                 <label for="name" class="mandatory"> * </label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <ckeditor:editor name="Maintenance_13" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue"><label for="name">(ii)<g:message code="default.UserFeedbackMechanism.label"/>:</label>
                                 <label for="name" class="mandatory"> * </label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <ckeditor:editor name="UserFeedbackMechanism_14" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue"><label for="name"><g:message code="default.c.label"/>)<g:message code="default.FrequencyOfReview.label"/>:</label>
                                 <label for="name" class="mandatory"> * </label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <ckeditor:editor name="FrequencyOfReview_15" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="name">6.<g:message code="default.ReviewMechanism.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                </tr>
                            
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue"><label for="name"><g:message code="default.a.label"/>)<g:message code="default.ListAtLeastReviewers.label"/>:</label>
                                 <label for="name" class="mandatory"> * </label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <ckeditor:editor name="ListAtLeastReviewers_16" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="name"><g:message code="default.b.label"/>)<g:message code="default.CapitalExpenditure.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                </tr>
                            
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue"><label for="name">(i).<g:message code="default.DetailsCapturedItemwise.label"/>:</label>
                                 <label for="name" class="mandatory"> * </label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <ckeditor:editor name="DetailsCapturedItemwise_17" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue"><label for="name">(ii).<g:message code="default.FocusedCommentsHighCostEquipment.label"/>:</label>
                                 <label for="name" class="mandatory"> * </label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <ckeditor:editor name="FocusedCommentsHighCostEquipment_18" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue">
                                 <label for="name"><g:message code="default.c.label"/>)<g:message code="default.RevenueExpe.label"/></label>
                                 <label for="name" class="mandatory"> * </label>
                                 <br/>
                                 <label for="name">i).<g:message code="default.RevenueDeCapturedItemwise.label"/>:</label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <ckeditor:editor name="RevenueDeCapturedItemwise_19" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="name">7.<g:message code="default.SumBudget.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                </tr>
                            
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue"><label for="name">(i).<g:message code="default.FocusedCommentsOn.label"/>:</label>
                                 <label for="name" class="mandatory"> * </label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <ckeditor:editor name="FocusedCommentsOn_20" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue"><label for="name">(ii).<g:message code="default.TotalConsultancyFee.label"/>:</label>
                                 <label for="name" class="mandatory"> * </label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <ckeditor:editor name="TotalConsultancyFee_21" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue"><label for="name">(iii).<g:message code="default.IndicateAnchorInstitution.label"/>:</label>
                                 <label for="name" class="mandatory"> * </label></td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <ckeditor:editor name="IndicateAnchorInstitution_22" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="name">8.<g:message code="default.CostBenefitAnalysis.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                </tr>
                            
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <ckeditor:editor name="CostBenefitAnalysis_23" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="name">9.<g:message code="default.SocialImpact.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                </tr>
                            
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <ckeditor:editor name="SocialImpact_24" height="200px" width="100%" toolbar="custom">
								</ckeditor:editor>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" colspan="2" class="nameline">
                                    <label for="name">10.<g:message code="default.OutcomeExtentToProject.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                </tr>
                            
                            <tr class="prop">
                           
                                 <td valign="top" style="width:200px;" class="prvalue">&nbsp;</td> 
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <ckeditor:editor name="OutcomeExtentToProject_25" height="200px" width="100%" toolbar="custom">
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
                    <input class="inputbutton" type="submit" value="${message(code: 'default.Update.button')}"  onClick="return validateAppSummary();"/>
                    <g:actionSubmit class="inputbutton" action="proposalAppPreview" value="${message(code: 'default.Cancel.button')}" />
                    </g:if>
                    <g:else>
                    <input class="inputbutton" type="submit" value="${message(code: 'default.Submit.button')}"  onClick="return validateAppSummary();" />
                    </g:else>
                    </span>
                    
                </div>
            </g:form>
            <g:pageNavigation status="${params.status}" page="7" proposalApplicationInstance="${proposalApplicationInstance?.id}"/>
        </div>
                        
        
         
         </div>
         <div class="footerdBar">
<br>
<label style="text-align: center;font: bold 9px Verdana;color: #104d6b;"><g:message code="default.footerMsg.label"/></label>
</div>
    </body>
</html>
