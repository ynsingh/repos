<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251"/>
      	<meta name="layout" content="main" />
        <g:javascript library="jquery"/>
       
        <g:javascript library="applicationValidation" />
        <g:javascript library="appFormValidation" />
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
        <g:pageNavigation status="${params.status}" page="1" proposalApplicationInstance="${proposalApplicationInstance?.id}"/>
        
            <h1><g:message code="default.PartIPersonalDetails.head"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectsInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="saveProposalAppPart" method="post">
                <div class="dialog">
                    <table>
                        <tbody>
                        <input type="hidden" name="proposalApplication.id" value="${proposalApplicationInstance?.id}">
                        <input type="hidden" name="actionName" value="${params.action}">
                        <input type="hidden" name="Page" value="${page}">
                        <tr class="prop">
                                <td colspan="2" style="text-align:right;"><font face="Arial, sans-serif" size="-1"><span style="color:#4c99cc;">
                    All fields are mandatory</span></font></td>
                            </tr>
                              <tr class="prop">
                                <td valign="top" class="prname">
                                    <label for="name"><g:message code="default.FirstName.label"/>:</label>
                                	<label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'name','errors')}">
                                    <input type="text" size="45" id="FirstName" name="FirstName_1" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="prname">
                                    <label for="code"><g:message code="default.LastName.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <input type="text" size="45" id="LastName" name="LastName_2" />
                                </td>
                            </tr> 
                        	
                        	<tr class="prop">
                                <td valign="top" class="prname">
                                    <label for="code"><g:message code="default.Designation.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <input type="text" size="45" id="Designation" name="Designation_3" />
                                </td>
                            </tr> 
                           <tr class="prop">
                                <td valign="top" class="prname">
                                    <label for="code"><g:message code="default.Organisation.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <input type="text" size="45" id="Organisation" name="Organisation_4" />
                                </td>
                            </tr>
                                               
                         <tr class="prop">
                                <td valign="top" class="prname">
                                    <label for="code"><g:message code="default.PostalAddress.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <g:textArea rows="2" cols="20" size="45" id="PostalAddress" name="PostalAddress_5" />
                                </td>
                            </tr>
                                                 
                             <tr class="prop">
                                <td valign="top" class="prname">
                                    <label for="code"><g:message code="default.City.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <input type="text" size="45" id="City" name="City_6" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="prname">
                                    <label for="code"><g:message code="default.State.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <input type="text" size="45" id="State" name="State_7" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="prname">
                                    <label for="code"><g:message code="default.STD.label"/>-<g:message code="default.Phone.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <input type="text"  id="STD" name="STD_8" size="5" />--<input type="text"  id="Phone" name="Phone_9" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="prname">
                                    <label for="code"><g:message code="default.Fax.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <input type="text"  id="FaxSTD" name="FaxSTD_10" size="5" />--<input type="text"  id="FaxPhone" name="FaxPhone_11" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="prname">
                                    <label for="code"><g:message code="default.Email.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <input type="text" size="35" id="Email" name="Email_12" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="prname">
                                    <label for="code"><g:message code="default.Mobile.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <input type="text" size="35" id="Mobile" name="Mobile_13" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="prname">
                                    <label for="code"><g:message code="default.ProjectCategory.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                     <g:select  optionKey="name" optionValue="name" id="ProjectCategory" from="${proposalCategoryList}"  name="ProjectCategory_14"  ></g:select>
                                </td>
                            </tr>
                         
                        </tbody>
                    </table>
                </div>
                <div>
                    <span class="button">
                    <g:if test="${params.status=='update'}">
                    <input type="hidden" name="status" value="update">
                    <input class="inputbutton" type="submit" value="${message(code: 'default.Update.button')}" onClick="return validateApplicationForm();"/>
                    <g:actionSubmit class="inputbutton" action="proposalAppPreview" value="${message(code: 'default.Cancel.button')}" />
                    </g:if>
                    <g:else>
                    <input type="hidden" name="status" value="save">
                    <input class="inputbutton" type="submit" value="${message(code: 'default.Create.button')}" onClick="return validateApplicationForm();"/>
                    <input type="button" class="inputbutton" onClick="Redirect()"  value="${message(code: 'default.Cancel.button')}"/>
                    </g:else>
                    </span>
                    
                </div>
            </g:form>
            <g:pageNavigation status="${params.status}" page="1" proposalApplicationInstance="${proposalApplicationInstance?.id}"/>
        </div>
                        </td>
        </tr><br/>
        
         </table>
         </div>
         <div class="footerdBar">
<br>
<label style="text-align: center;font: bold 9px Verdana;color: #104d6b;"><g:message code="default.footerMsg.label"/></label>
</div>
    </body>
</html>
