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
                	<h3 id="adduser">Authority Details</h3>

					  <table>
						<tbody>

							<tr class="prop">
								<td valign="top" class="name">ID:</td>
								<td valign="top" class="value">${authority.id}</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">Authority Name:</td>
								<td valign="top" class="value">${authority.authority}</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">Description:</td>
								<td valign="top" class="value">${authority.description}</td>
							</tr>

							 <tr class="prop">
                                                       <td valign="top" class="name" colspan="2">
                                                      <div class="buttons">
						       <g:form>
							<input type="hidden" name="id" value="${authority?.id}" />
							<span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
							<span class="button"><g:actionSubmit class="delete"
							onclick="return confirm('Are you sure?');" value="Delete" /></span>
						       </g:form>
		                                    </div>
                                                      </td>
                                                      </tr>

						</tbody>
						</table>
                
                </div>
            </div>
            <g:if test="${session.ROLE == 'ROLE_ADMIN'}">
            <g:sideMenu/>
            </g:if >
      </div>
         <g:styleSwitcher/>
</div>

</body>
