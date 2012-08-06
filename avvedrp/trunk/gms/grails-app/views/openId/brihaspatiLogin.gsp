<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Brihaspati Login</title>

</head>
<body>
 <div class="wrapper">
       <div class="body">


		<h1>Login</h1>
		<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
		</g:if>
		
		<g:form action="register">
			<div class="dialog">
				<table>
				<tbody>
					<tr class="prop">
						<td valign="top" class="name"><label for="username">Email:</label></td>
						<td>
							<input type="text" id='email' name="email" value="${emailValue}" "/>
						</td>
					</tr>

				</tbody>
				</table>
			</div>

			<div class="buttons">
				<span class="button"><input class="save" type="submit" value="Register" /></span>
			</div>

		</g:form>
						
						
</div>
</div>
</body>
</html>
