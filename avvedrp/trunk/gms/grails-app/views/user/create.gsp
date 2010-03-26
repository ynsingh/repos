<head>
	<meta name="layout" content="main" />
	<title>Create User</title>
</head>
<script>
    function validateProject(){
    	 if(document.getElementById("username").value == ""){
    		alert("Please Enter Login Name");
		    document.getElementById("username").focus();
		    return false;
    	}
    	if(document.getElementById("userRealName").value == ""){
    		alert("Please Enter Full Name");
		    document.getElementById("userRealName").focus();
		    return false;
    	}
    	if(document.getElementById("confirmPasswd").value != document.getElementById("passwd").value)
    	{
    		document.getElementById("passwd").focus();
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
		<h1>Create User</h1>
		<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
		</g:if>
		
		<g:if test="${session.Role == 'ROLE_USER'}">
		<% response.sendRedirect("../../invalidAccess.gsp")%>
		</g:if>
		<g:hasErrors bean="${person}">
		<div class="errors">
			<g:renderErrors bean="${person}" as="list" />
		</div>
		</g:hasErrors>
		<g:form action="save">
			<div class="dialog">
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
						<td valign="top" class="name"><label for="confirmPasswd">Confirm Password:</label></td>
						<td valign="top" class="value ${hasErrors(bean:person,field:'passwd','errors')}">
							<input type="password" id="confirmPasswd" name="confirmPasswd" value="${person.passwd?.encodeAsHTML()}"/>
						</td>
					</tr>
					

					
					<tr class="prop">
						<td valign="top" class="name"><label for="email">Email:</label></td>
						<td valign="top" class="value ${hasErrors(bean:person,field:'email','errors')}">
							<input type="text" id="email" name="email" value="${person.email?.encodeAsHTML()}"/>
						</td>
					</tr>

					 <g:if test="${session.Role == 'ROLE_ADMIN'}"> 

					<tr class="prop">
						<td valign="top" class="name" align="left">Assign Roles:</td>
					

						<td> <g:select optionKey="id" optionValue="authority" from= "${Role.list()}" name="authorities" value="${person?.authorities}" noSelection="['null':'Select']"></g:select>
						</td>
					</tr>
				
				         
				                 
                         <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="party">Institution:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:person,field:'party','errors')}">
                                    <g:select optionKey="id" optionValue="code" from="${Party.list()}" name="party.id" value="${grantAllocationInstance?.party?.id}" ></g:select>
                                </td>
                            </tr> 
                         	</g:if>
				</tbody>
				</table>
			</div>

			<div class="buttons">
				<span class="button"><input class="save" type="submit" onClick="return validateProject()" value="Create" /></span>
			</div>

		</g:form>
	</div>
	</div>
</body>
