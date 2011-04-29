<head>
	<meta name="layout" content="main" />
	<title><g:message code="default.User.CreateRolePrivileges.head"/></title>
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
	<g:subMenuLogin/>
	<div class="wrapper">
	<div class="body">
		<h1><g:message code="default.SelectParty.message"/></h1>
		<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${authority}">
		<div class="errors">
		<g:renderErrors bean="${authority}" as="list" />
		</div>
		</g:hasErrors>
		<g:form action="proposalAppPart1PersonalDetails">
		<div class="dialog">
		<table>
		<tbody>
			<tr class="prop">
				<td valign="top" class="name" ><label for="party"><g:message code="default.Institution.label"/>:</label></td>
				<td valign="top" class="value ${hasErrors(bean:party,field:'nameOfTheInstitution','errors')}" width="88%">				 
				<g:select optionKey="id" optionValue="nameOfTheInstitution" from="${partyInstanceList}" name="party" value="partyInstanceList.id" noSelection="['null':'-Select-']"> </g:select>				 	 
				</td>
			</tr>			
		</tbody>
		</table>
		</div>
		<div class="buttons">
			<span class="button"><input class="save" type="submit" value="${message(code: 'default.Create.button')}" /></span>
		</div>
		</g:form>
	</div>
	</div>
</body>
