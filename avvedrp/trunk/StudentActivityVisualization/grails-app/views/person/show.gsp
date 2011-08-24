<meta name="layout" content="main" />	
<!-- ##################################  Layout body starts here  ###########################################-->
	<div id="wrapper">
		<div id="head">
			<div id="logo_user_details">&nbsp;</div>
		      <g:if test="${session.ROLE == 'ROLE_SUPERADMIN' || session.ROLE == 'ROLE_ADMIN'}">
                        <g:menu/>
                        </g:if >
		</div>

	<div id="content"> <!-- Start of content div -->
	<br />
       <div align="center"><h3>User Details</h3></div>
		<br />	<br />
		<div align="center">						
			<g:if test="${flash.message}">
						<div class="message">${flash.message}</div>
						</g:if>
						<g:hasErrors bean="${person}">
						<div class="errors">
						<g:renderErrors bean="${person}" as="list" />
						</div>
						</g:hasErrors>

						<g:form action="save">
						<table>
						<tbody>

						<tr class="prop">
						<td valign="top" class="name"><label for="username">Login Name:</label></td>
						<td valign="top" class="value ${hasErrors(bean:person,field:'username','errors')}">
						<input type="text" id="username" name="username" value="${person.username?.encodeAsHTML()}"/>
						</td>
						</tr>

						<tr class="prop">
						<td valign="top" class="name"><label for="userRealName">Full Name:</label></td>
						<td valign="top" class="value ${hasErrors(bean:person,field:'userRealName','errors')}">
						<input type="text" id="userRealName" name="userRealName" value="${person.userRealName?.encodeAsHTML()}"/>
						</td>
						</tr>

						<tr class="prop">
						<td valign="top" class="name"><label for="passwd">Password:</label></td>
						<td valign="top" class="value ${hasErrors(bean:person,field:'passwd','errors')}">
						<input type="password" id="passwd" name="passwd" value="${person.passwd?.encodeAsHTML()}"/>
						</td>
						</tr>

						<tr class="prop">
						<td valign="top" class="name"><label for="enabled">Enabled:</label></td>
						<td valign="top" class="value ${hasErrors(bean:person,field:'enabled','errors')}">
						<g:checkBox name="enabled" value="${person.enabled}" ></g:checkBox>
						</td>
						</tr>

						<tr class="prop">
						<td valign="top" class="name"><label for="description">Description:</label></td>
						<td valign="top" class="value ${hasErrors(bean:person,field:'description','errors')}">
						<input type="text" id="description" name="description" value="${person.description?.encodeAsHTML()}"/>
						</td>
						</tr>

						
						<tr class="prop">
						<td valign="top" class="name" align="left">Assign Roles:</td>
						</tr>

						<g:each in="${authorityList}">
						<tr>
						<td valign="top"  align="left">${it.authority.encodeAsHTML()}</td>
						<td align="left"><g:checkBox name="${it.authority}"/></td>
						</tr>
						</g:each>
						<tr class="prop">
						<td colspan="3">
						<div class="buttons">
						<span><input class="save" type="submit" value="Create" /></span>
						</div>
						</td>
						</tr>

						</tbody>
						</table>
						</g:form>  
		</div>
		
		<div style="clear: both;">&nbsp;</div>
		<br />
<!-- Middle area ends here -->		
         </div> <!-- End of content div -->
	</div>
<g:footer/>
<!-- ##################################  Layout body ends here  ###########################################-->