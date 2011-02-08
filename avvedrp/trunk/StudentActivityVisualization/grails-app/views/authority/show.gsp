<head>
	<meta name="layout" content="main" />
	<title>LMS List</title>
       </head>
<body>
	<div id="wrapper">
		<div id="head">
			<div id="logo_user_details">&nbsp;</div>
		      <g:if test="${session.ROLE == 'ROLE_ADMIN'}">
                        <g:menu/>
                        </g:if >
		</div>

	<div id="content"> <!-- Start of content div -->
          <br /><br />
                    <div style="padding-left: 400px;"><h3>Authority Details</h3></div>
                    <table align="center">
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
					<span><g:actionSubmit class="edit" value="Edit" /></span>
					<span><g:actionSubmit class="delete"
					onclick="return confirm('Are you sure?');" value="Delete" /></span>
					</g:form>
					</div>
					</td>
					</tr>

					</tbody>
					</table>



         </div> <!-- End of content div -->


	</div>
<g:footer/>
</body>