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
                        <input type="hidden" id="proposalApplication.id" name="proposalApplication.id" value="${proposalApplicationInstance?.id}">
                        <input type="hidden" name="actionName" value="${params.action}">
                        <input type="hidden" name="Page" value="${page}">
                        <input type="hidden" id="party.id" name="party.id" value="${session.PartyId}">
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
                                    <input type="text" size="45" id="FirstName" name="FirstName_1" onBlur="validateFormOnTabOut(this.id)" />
                                </td>
                                
                                <td >
									<div  id="FirstNamevalue" class="validationmsg">The value is required.</div>
									<div id="FirstNameformat" class="validationmsg">Invalid format.</div>
								</td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="prname">
                                    <label for="code"><g:message code="default.LastName.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <input type="text" size="45" id="LastName" name="LastName_2" onBlur="validateFormOnTabOut(this.id)"/>
                                </td>
                                
                                <td >
									<div  id="LastNamevalue" class="validationmsg">The value is required.</div>
									<div id="LastNameformat" class="validationmsg">Invalid format.</div>
								</td>
                            </tr> 
                        	
                        	<tr class="prop">
                                <td valign="top" class="prname">
                                    <label for="code"><g:message code="default.Designation.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <input type="text" size="45" id="Designation" name="Designation_3" onBlur="validateFormOnTabOut(this.id)"/>
                                </td>
                                
                                <td >
									<div  id="Designationvalue" class="validationmsg">The value is required.</div>
									<div id="Designationformat" class="validationmsg">Invalid format.</div>
								</td>
                            </tr> 
                           <tr class="prop">
                                <td valign="top" class="prname">
                                    <label for="code"><g:message code="default.Organisation.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <input type="text" size="45" id="Organisation" name="Organisation_4" onBlur="validateFormOnTabOut(this.id)"/>
                                </td>
                                
                                <td >
									<div  id="Organisationvalue" class="validationmsg">The value is required.</div>
									<div id="Organisationformat" class="validationmsg">Invalid format.</div>
								</td>
                            </tr>
                                               
                         <tr class="prop">
                                <td valign="top" class="prname">
                                    <label for="code"><g:message code="default.PostalAddress.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <g:textArea rows="2" cols="20" size="45" id="PostalAddress" name="PostalAddress_5" onBlur="validateFormOnTabOut(this.id)"/>
                                </td>
                                
                                <td >
									<div  id="PostalAddressvalue" class="validationmsg">The value is required.</div>
									<div id="PostalAddressformat" class="validationmsg">Invalid format.</div>
								</td>
                            </tr>
                                                 
                             <tr class="prop">
                                <td valign="top" class="prname">
                                    <label for="code"><g:message code="default.City.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <input type="text" size="45" id="City" name="City_6" onBlur="validateFormOnTabOut(this.id)"/>
                                </td>
                                
                                <td >
									<div  id="Cityvalue" class="validationmsg">The value is required.</div>
									<div id="Cityformat" class="validationmsg">Invalid format.</div>
								</td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="prname">
                                    <label for="code"><g:message code="default.State.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <g:select from= "${['Andhra Pradesh','Arunachal Pradesh','Assam','Bihar','Chattisgarh','Goa','Gujarat','Haryana','Himachal Pradesh','Jammu & Kashmir',
                                    'Jharkhand','Karnataka','Kerala','Madhya Pradesh','Maharashtra','Manipur','Meghalaya','Mizoram','Nagaland','Orissa','Punjab','Rajasthan','Sikkim','Tamil Nadu',
                                    'Tripura','Uttarakhand','Uttar Pradesh','West Bengal','','-UNION  TERRITORIES-','','Andaman & Nicobar','Chandigarh','Dadra and Nagar Haveli',
                                    'Daman & Diu','Delhi','Lakshadweep','Puducherry']}" id="State" name="State_7" noSelection="['null':'-Select-']" onBlur="validateFormOnTabOut(this.id)"/>
                                </td>
                                
                                <td >
									<div  id="Statevalue" class="validationmsg">The value is required.</div>
									<div id="Stateformat" class="validationmsg">Invalid format.</div>
								</td>
                            </tr>
                            
                           <tr class="prop">
                                <td valign="top" class="prname">
                                    <label for="code"><g:message code="default.ZipCode.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'zipCode','errors')}">
                                    <input type="text" size="35" id="ZipCode" name="ZipCode_15" onBlur="validateFormOnTabOut(this.id)"/>
                                </td>
                                
                                <td >
									<div  id="ZipCodevalue" class="validationmsg">The value is required.</div>
									<div id="ZipCodeformat" class="validationmsg">Invalid format/Digits needed is 6.</div>
								</td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="prname">
                                    <label for="code"><g:message code="default.STD.label"/>-<g:message code="default.Phone.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <input type="text"  id="STD" name="STD_8" size="5" onBlur="validateFormOnTabOut(this.id)"/>--<input type="text"  id="Phone" name="Phone_9" onBlur="validateFormOnTabOut(this.id)"/>
                                </td>
                                
                                <td >
									<div  id="STDvalue" class="validationmsg">The value is required.</div>
									<div id="STDformat" class="validationmsg">Invalid format/Digits needed is 4.</div>
									<div  id="Phonevalue" class="validationmsg">The value is required.</div>
									<div id="Phoneformat" class="validationmsg">Invalid format/Digits needed is 7.</div>
								</td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="prname">
                                    <label for="code"><g:message code="default.Fax.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <input type="text"  id="FaxSTD" name="FaxSTD_10" size="5" onBlur="validateFormOnTabOut(this.id)"/>--<input type="text"  id="FaxPhone" name="FaxPhone_11" onBlur="validateFormOnTabOut(this.id)"/>
                                </td>
                                
                                <td >
									<div  id="FaxSTDvalue" class="validationmsg">The value is required.</div>
									<div id="FaxSTDformat" class="validationmsg">Invalid format/Digits needed is 4.</div>
									<div  id="FaxPhonevalue" class="validationmsg">The value is required.</div>
									<div id="FaxPhoneformat" class="validationmsg">Invalid format/Digits needed is 7.</div>
								</td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="prname">
                                    <label for="code"><g:message code="default.Email.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <input type="text" size="35" id="Email" name="Email_12" onBlur="validateFormOnTabOut(this.id)"/>
                                </td>
                                
                                <td >
									<div  id="Emailvalue" class="validationmsg">The value is required.</div>
									<div id="Emailformat" class="validationmsg">Invalid format.</div>
								</td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="prname">
                                    <label for="code"><g:message code="default.Mobile.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                    <input type="text" size="35" id="Mobile" name="Mobile_13" onBlur="validateFormOnTabOut(this.id)"/>
                                </td>
                                
                                <td >
									<div  id="Mobilevalue" class="validationmsg">The value is required.</div>
									<div id="Mobileformat" class="validationmsg">Invalid format/Digits needed is 12.</div>
								</td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="prname">
                                    <label for="code"><g:message code="default.ProposalCategory.label"/>:</label>
                                    <label for="name" class="mandatory"> * </label>
                                </td>
                                <td valign="top" class="prvalue ${hasErrors(bean:projectsInstance,field:'code','errors')}">
                                     <g:select  optionKey="name" optionValue="name" id="ProjectCategory" from="${proposalCategoryList}"  name="ProjectCategory_14" noSelection="['null':'-Select-']" onBlur="validateFormOnTabOut(this.id)"></g:select>
                                </td>
                                
                                <td >
									<div  id="ProjectCategoryvalue" class="validationmsg">The value is required.</div>
									<div id="ProjectCategoryformat" class="validationmsg">Invalid format.</div>
								</td>
                            </tr>
                         
                        </tbody>
                    </table>
                </div>
                <div>
                    <span class="button">
                    <g:if test="${params.status=='update'}">
                    <input type="hidden" name="status" value="update">
                    <input class="inputbutton" type="submit" value="${message(code: 'default.Update.button')}" onClick="return validateForm(this.form);"/>
                    <g:actionSubmit class="inputbutton" action="proposalAppPreview" value="${message(code: 'default.Cancel.button')}" />
                    </g:if>
                    <g:else>
                    <input type="hidden" name="status" value="save">
                    <input class="inputbutton" type="submit" value="${message(code: 'default.Create.button')}" onClick="return validateForm(this.form);"/>
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
