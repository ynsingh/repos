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

                                               <div style="padding-left:10px;"><h3>Authorities</h3></div>
                                             <table width="100%"\ >
                                             <thead>
                                                    <tr>
                                                            <th>SI.No</th>
                                                            <g:sortableColumn property="authority" title="Authority Name" />
                                                            <g:sortableColumn property="description" title="Description" />
                                                            <th>Action</th>
                                                    </tr>
                                            </thead>
                                            <tbody>
                                            <g:each in="${authorityList}" status="i" var="authority">
                                                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                                                            <td style="padding-left:15px;">${i+1}</td>
                                                            <td>${authority.authority?.encodeAsHTML()}</td>
                                                            <td>${authority.description?.encodeAsHTML()}</td>
                                                            <td class="actionButtons">
                                                                    <span class="actionButton">
                                                                            <g:link action="show" id="${authority.id}">View</g:link>
                                                                            <g:link action="edit" id="${authority.id}">&nbsp;/&nbsp;Edit</g:link>
                                                                    </span>
                                                            </td>
                                                    </tr>
                                            </g:each>
                                           
                                            <tr><td colspan="6">
                                                <div class="paginateButtons">
                                            <g:paginate total="${Authority.count()}" />
                                        </div>

                                              </td></tr>
                                             <tr><td colspan="6">
                                                 <span class="menuButton"><g:link class="create" action="create"><strong>New Authority</strong></g:link></span></td></tr>
                                            </tbody>
                                            </table>
                                        
                                    </div>
                                    <br />

                                </div>

         </div> <!-- End of content div -->


	</div>
<g:footer/>
</body>