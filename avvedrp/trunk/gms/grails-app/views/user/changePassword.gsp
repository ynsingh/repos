<head>
	<meta name="layout" content="main" />
	<title>Change Password</title>
	<script>
	function validatePassword()
	{
		if(document.getElementById("newPasswd").value == "" || document.getElementById("newPasswd").value == null )
		{
			document.getElementById("newPasswd").focus();
			alert("Please Enter New Password");
			return false;
		}
		if(document.getElementById("confirmNewPasswd").value == "" || document.getElementById("confirmNewPasswd").value == null )
		{
			document.getElementById("confirmNewPasswd").focus();
			alert("Confirm Password");
			return false;
		}
		if(document.getElementById("confirmNewPasswd").value != document.getElementById("newPasswd").value)
    	{
    		document.getElementById("confirmNewPasswd").focus();
    		alert("Incorrect Password");
    		return false;
    	}
		return true;
	}
	</script>
</head>

<body>
<div class="wrapper">
	<div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
              
           
        </div>

	<div class="body">
		<h1>Change Password</h1>
		<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${person}">
		<div class="errors">
			<g:renderErrors bean="${person}" as="list" />
		</div>
		</g:hasErrors>
		<g:form action="updatePassword">
			<div class="dialog">
				<table>
				<tbody>
				
					<input type="hidden" name="id" value="${person.id}" />
					<input type="hidden" name="version" value="${person.version}" />
				
					<tr class="prop">
						<td valign="top" class="name"><label for="username">Login Name:</label></td>
						<td valign="top" class="value ${hasErrors(bean:person,field:'username','errors')}">
							
							${fieldValue(bean:person, field:'username')}
						</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name"><label for="passwd">Old Password:</label></td>
						<td valign="top" >
							<input type="password" id="oldPasswd" name="oldPasswd" />
						</td>
					</tr>
					
					<tr class="prop">
						<td valign="top" class="name"><label for="passwd">New Password:</label></td>
						<td valign="top" >
							<input type="password" id="newPasswd" name="newPasswd" />
						</td>
					</tr>
					<tr class="prop">
						<td valign="top" class="name"><label for="passwd">Confirm Password:</label></td>
						<td valign="top" >
							<input type="password" id="confirmNewPasswd" name="confirmNewPasswd" />
						</td>
					</tr>
								
					<!-- <tr class="prop">
						<td valign="top" class="name"><label for="passwd">New Password:</label></td>
						<td valign="top" class="value ${hasErrors(bean:person,field:'passwd','errors')}">
							<input type="password" id="passwd" name="passwd" value="${person.passwd?.encodeAsHTML()}"/>
						</td>
					</tr> -->
                        

				</tbody>
				</table>
			</div>

			<div >
				<input class="inputbutton" name="updatePassword" type="submit" value="Change Password" onClick="return validatePassword()" />
			</div>

		</g:form>
</div>
	</div>
</body>
