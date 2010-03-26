<head>
	<meta name="layout" content="main" />
	<title>Create Site Admin</title>
</head>
<script>
    function validateProject(){
    	if(document.getElementById("user.username").value == ""){
    		alert("Please Enter Login Name");
		    document.getElementById("user.username").focus();
		    return false;
    	}
    	if(document.getElementById("user.userRealName").value == ""){
    		alert("Please Enter Full Name");
		    document.getElementById("user.userRealName").focus();
		    return false;
    	}
    	if(document.getElementById("party.code").value == ""){
    		alert("Please Enter Institution Code");
		    document.getElementById("party.code").focus();
		    return false;
    	}
    	if(document.getElementById("confirmPasswd").value != document.getElementById("user.passwd").value)
    	{
    		document.getElementById("user.passwd").focus();
    		alert("Incorrect Password");
    		return false;
    	}
    	return true;
    
    }
    </script>
<body>
	<div class="wrapper">

	<div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'/login')}">Home</a></span>
             <span class="menuButton"><g:link class="list" action="list">User List</g:link></span>
           
        </div>

	<div class="body">
		<h1>Create Site Admin</h1>
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
				<table>
				<tbody>

					<tr class="prop">
						<td valign="top" class="name"><label for="username">Login Name:</label></td>
						<td valign="top" class="value ${hasErrors(bean:person,field:'user','errors')}">
							<input type="text" id="user.username" name="user.username" value="${person?.user?.username?.encodeAsHTML()}"/>
						</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name"><label for="userRealName">Full Name:</label></td>
						<td valign="top" class="value ${hasErrors(bean:person,field:'user','errors')}">
							<input type="text" id="user.userRealName" name="user.userRealName" value="${person?.user?.userRealName?.encodeAsHTML()}"/>
						</td>
					</tr>

					<tr class="prop">
						<td valign="top" class="name"><label for="passwd">Password:</label></td>
						<td valign="top" class="value ${hasErrors(bean:person,field:'user','errors')}">
							<input type="password" id="user.passwd" name="user.passwd" value="${person?.user?.passwd?.encodeAsHTML()}"/>
						</td>
					</tr>
					
					<tr class="prop">
						<td valign="top" class="name"><label for="confirmPasswd">ConfirmPassword:</label></td>
						<td valign="top" class="value">
							<input type="password" id="confirmPasswd" name="confirmPasswd" value=""/>
						</td>
					</tr>

					

					
					<tr class="prop">
						<td valign="top" class="name"><label for="email">Email:</label></td>
						<td valign="top" class="value ${hasErrors(bean:person,field:'user','errors')}">
							<input type="text" id="user.email" name="user.email" value="${person?.user?.email?.encodeAsHTML()}"/>
						</td>
					</tr>                
                         <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="party">Institution Code:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:person,field:'party','errors')}">
                                    <input type="text" id="party.code" name="party.code" value="${person?.party?.code}" >
                                </td>
                            </tr> 

				</tbody>
				</table>
			</div>

			<div class="buttons">
				<span class="button"><input class="save" type="submit"  onClick="return validateProject()" value="Create" /></span>
			</div>

		</g:form>
	</div>
	</div>
</body>
