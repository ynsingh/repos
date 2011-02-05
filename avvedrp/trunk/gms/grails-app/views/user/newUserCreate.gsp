<head>
	<meta name="layout" content="main" />
	<title><g:message code="default.CreateSiteAdmin.head"/></title>
	<g:javascript library="jquery" />
</head>
<body>
	<div class="wrapper">
	<div id="spinner" class="spinner" style="display:none;">
            <img src="${createLinkTo(dir:'images',file:'spinner.gif')}" alt="Spinner" />
        </div>	
        
    <div class="innnerBanner">
	<div class="loginLink">
	<span>
	
	
 
<img src="${createLinkTo(dir:'images/themesky',file:'help.gif')}" onClick="window.open('../images/HELPDOC/UntitledFrameset-15.html','mywindow','width=800,height=500,left=0,top=100,screenX=0,screenY=100')"> 
</a> &nbsp;&nbsp;|</a>&nbsp;&nbsp;<a href="#"><img src="${createLinkTo(dir:'images/themesky',file:'aboutUs.jpg')}" onClick="window.open('../images/aboutUs/AboutUs_MGMS_new.html','mywindow','width=600,height=300,left=0,top=100,screenX=0,screenY=100')">&nbsp;&nbsp;</a>
	
   	</span>
   	</div>
	</div>  
<div id="messageBox">
		</div>
	<div class="body">
		<h1><g:message code="default.CreateSiteAdmin.head"/></h1>
		<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${person}">
		<div class="errors">
			<g:renderErrors bean="${person}" as="list" />
		</div>
		</g:hasErrors>
		<g:form action="saveNewUser">
		
			<div class="dialog">
			<table><tr><td></td></tr><tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td>
				<table>
				<tbody>

					<tr class="prop">
						<td valign="top" class="name">
						<label for="email"><g:message code="default.Email.label"/>:</label>
						<label for="email" style="color:red;font-weight:bold"> * </label>
						</td>
						<td valign="top" class="value ${hasErrors(bean:person,field:'user','errors')}">
							<input type="text" id="email" name="email" value="${person?.user?.email?.encodeAsHTML()}"/>
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
	                            <label for="party"><g:message code="default.InstitutionCode.label"/>:</label>
	                            <label for="party" style="color:red;font-weight:bold"> * </label>
	                        </td>
	                        <td valign="top" class="value ${hasErrors(bean:person,field:'party','errors')}">
	                            <input type="text" id="code" name="party.code" value="${person?.party?.code}" >
	                            <label for="code" style="color:blue;font-weight:bold"> <g:message code="default.register.instituitonCode.label"/></label>
	                        </td>
	                    </tr> 

				</tbody>
				</table></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr></table>
			</div>

			<div class="buttons">
				<span class="buttons"><input class="inputbutton" id="submit" type="submit"  onClick='return combineAlertAndRegister();' disableOnClick="true" value="${message(code: 'default.Create.button')}"/></span>
				<span class="buttons"><input class="inputbutton" type="button" onClick="Redirect()"  value="${message(code: 'default.Cancel.button')}" /></span>
			</div>

		</g:form>
	</div>
	</div>
</body>
