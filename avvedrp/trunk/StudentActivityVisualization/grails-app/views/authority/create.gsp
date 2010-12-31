<head>
	<meta name="layout" content="main" />
	<title>Authority Details</title>
       </head>

<body>

	<div id="container">

        <div id="wrapper">
            <div id="content">

                <br />
                <div id="box">
                	<h3 id="adduser">Create Authority</h3>

                        <g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
		</g:if>
		<g:hasErrors bean="${authority}">
		<div class="errors">
		<g:renderErrors bean="${authority}" as="list" />
		</div>
		</g:hasErrors>

		<g:form action="save">
					  <table>
		<tbody>
			<tr class="prop">
				<td valign="top" class="name"><label for="authority">Authority Name:</label></td>
				<td valign="top" class="value ${hasErrors(bean:authority,field:'authority','errors')}">
					<input type="text" id="authority" name="authority" value="${authority?.authority?.encodeAsHTML()}"/>
				</td>
			</tr>

			<tr class="prop">
				<td valign="top" class="name"><label for="description">Description:</label></td>
				<td valign="top" class="value ${hasErrors(bean:authority,field:'description','errors')}">
					<input type="text" id="description" name="description" value="${authority?.description?.encodeAsHTML()}"/>
				</td>
			</tr>
                         <tr class="prop">
				<td valign="top" class="name" colspan="3">
                                 <div class="buttons">
			<span class="button"><input class="save" type="submit" value="Create" /></span>
		              </div>
                                </td>

			</tr>
		</tbody>
		</table>
                      
                  	</g:form>


                </div>
            </div>
            <g:if test="${session.ROLE == 'ROLE_ADMIN'}">
            <g:sideMenu/>
            </g:if >
      </div>
         <g:styleSwitcher/>
</div>

</body>
