<head>
	<meta name="layout" content="main" />
	<g:javascript library="jquery" />
</head>
<body>

  <div class="body">
   <div class="wrapper">
     <g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
	 </g:if>
	 <g:if test="${flash.error}">
		<div class="errors">${flash.error}</div>
	 </g:if>
<div class="dialog">
<table>
  <tbody>
		<img src="${createLinkTo(dir:'images/themesky',file:'contxthelp.gif')}" align="right" onClick="window.open('${application.contextPath}/images/help/${session.Help}','mywindow','width=500,height=250,left=0,top=100,screenX=0,screenY=100,scrollbars=yes')" title="Help" alt="Help">
   <h1><label for="SiteAdminLoginDetails"><g:message code="default.SiteAdminLoginDetails.label"/>:</label></h1>
    <tr class="prop">
       <td>
		<g:hasErrors bean="${person}">
			<div class="errors">
				<g:renderErrors bean="${person}" as="list" />
			</div>
		</g:hasErrors>
			<g:form action="saveNewUserBySuperAdmin">
				
				<table>
					<tbody>
						<tr class="prop">
							<td valign="top" class="name">
								<label for="email"><g:message code="default.Email.label"/>:</label>
								<label for="email" style="color:red;font-weight:bold"> * </label>
							</td>
							<td valign="top" class="value ${hasErrors(bean:person,field:'user','errors')}">
								<input type="text" id="email" name="email" value="${person?.user?.email?.encodeAsHTML()}"/>
								<label for="userId" style="color:blue;font-weight:bold"> <g:message code="default.register.userId.label"/></label>
							</td>
						</tr>    
						<tr class="prop">
							<td valign="top" class="name">
								<label for="passwd"><g:message code="default.Password.label"/>:</label>
								<label for="passwd" style="color:red;font-weight:bold"> * </label>
							</td>
							<td valign="top" class="value ${hasErrors(bean:person,field:'user','errors')}">
								<input type="password" id="password" name="password" value="${person?.user?.passwd?.encodeAsHTML()}"/>
							</td>
						</tr>
					
						<tr class="prop">
							<td valign="top" class="name">
								<label for="confirmPasswd"><g:message code="default.ConfirmPassword.label"/>:</label>
								<label for="confirmPasswd" style="color:red;font-weight:bold"> * </label>
							</td>
							<td valign="top" class="value">
								<input type="password" id="confirmPasswd" name="confirmPasswd" value=""/>
							</td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name">
								<label for="userRealName"><g:message code="default.FirstName.label"/>:</label>
								<label for="userRealName" style="color:red;font-weight:bold"> * </label>
							</td>
				           	<td valign="top" class="value ${hasErrors(bean:person,field:'user','errors')}">
								<input type="text" id="userRealName" name="userRealName" value="${person?.user?.userRealName?.encodeAsHTML()}"/>
							</td>
						</tr>
                          
	                   <tr class="prop">
						    <td valign="top" class="name">
								<label for="userSurName"><g:message code="default.LastName.label"/>:</label>
								<label for="userSurName" style="color:red;font-weight:bold"> * </label>
							</td>
							<td valign="top" class="value ${hasErrors(bean:person,field:'user','errors')}">
								<input type="text" id="userSurName" name="userSurName" value="${person?.user?.userSurName?.encodeAsHTML()}"/>
							</td>
						</tr>  
						
						<tr class="prop">
							<td valign="top" class="name">
								<label for="userDesignation"><g:message code="default.UserDesignation.label"/>:</label>
							</td>
	
							<td valign="top" class="value ${hasErrors(bean:person,field:'userDesignation','errors')}">
								<input type="text" id="userDesignation" name="userDesignation" value="${person?.user?.userDesignation?.encodeAsHTML()}"/>
							</td>
						</tr>   
						
						<tr class="prop">
							<td valign="top" class="name">
								<label for="phNumber"><g:message code="default.ContactNo.label"/>:</label>
							</td>
	
							<td valign="top" class="value ${hasErrors(bean:person,field:'phNumber','errors')}">
								<input type="text" id="phNumber" name="phNumber" value="${person?.user?.phNumber?.encodeAsHTML()}"/>
							</td>
						</tr>   
				</tbody>
				</table></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr></table>
			
  </td>
  <td>
	 <h1>Institution Details</h1>
	   
		  <table>	
			 <tbody>  
				  <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nameOfTheInstitution"><g:message code="default.Name.label"/>:</label>
                                    <label for="nameOfTheInstitution" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'nameOfTheInstitution','errors')}">
                                    <input type="text" size="35" id="nameOfTheInstitution" name="nameOfTheInstitution" value="${fieldValue(bean:partyInstance,field:'nameOfTheInstitution')}"/>
                                </td>
                           </tr> 
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="code"><g:message code="default.Code.label"/>:</label>
                                    <label for="code" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'code','errors')}">
                                    <input type="text" id="code" name="code" value="${fieldValue(bean:partyInstance,field:'code')}"/>
                                    <label for="code" style="color:blue;font-weight:bold"> <g:message code="default.institution.code.label"/></label>
                                </td>
                            </tr> 
                        
                          
                          
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="address"><g:message code="default.Address.label"/>:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'address','errors')}">
                                   <g:textArea name="address" value="${fieldValue(bean:partyInstance,field:'address')}" rows="3" cols="30"/>
                                </td>
                            </tr> 
                        
                        <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="phone"><g:message code="default.Phone.label"/>:</label>
                                    
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'phone','errors')}">
                                    <input type="text" id="phone" name="phone" value="${fieldValue(bean:partyInstance,field:'phone')}"/>
                                </td>
                           </tr> 
                            
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="institutionemail"><g:message code="default.Email.label"/>:</label>
                                    <label for="institutionemail" style="color:red;font-weight:bold"> * </label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'email','errors')}">
                                    <input type="text" id="institutionemail" name="institutionemail" value="${fieldValue(bean:partyInstance,field:'email')}"/>
                                </td>
                            </tr> 
  		
             </tbody>
		  </table>
			
	</td>
			
</tr>
</tbody>
	</table>
</div>	
	
		<div class="buttons">
			<span class="buttons"><input class="inputbutton" id="submit" type="submit" onClick='return superAdminUservalidation();' disableOnClick="true" value="${message(code: 'default.Create.button')}"/></span>
		</div>
	
</g:form>
	
	
	</div>
   </div>
</body>
