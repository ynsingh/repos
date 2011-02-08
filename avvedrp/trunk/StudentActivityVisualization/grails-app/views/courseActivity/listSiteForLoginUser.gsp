<head>
	<meta name="layout" content="main" />
	<title>LMS List</title>
       </head>

<body>
	<div id="wrapper">
		<div id="head">
			<div id="logo_user_details">&nbsp;</div>
		       <g:menu/>
		</div>

			<div id="content">

                          <div style="padding-left:30px">
                   	<h3>Course Selector</h3>
					<g:if test="${flash.message}">
					<div class="message">${flash.message}</div>
					</g:if>
					<g:form action="listGraph" method="post" name="graph">
					<table>
					<tbody>
					<tr>
					<td width="250">
					<label><strong>Course:</strong></label>&nbsp;&nbsp;
					<g:select optionKey="siteName" optionValue="siteName" from="${siteList}" id="siteName" name="siteName" noSelection="['null':'-Select-']" value="${siteId}" ></g:select>
					&nbsp;&nbsp; <input  name="listSite" type="submit" value="Go" onClick="return validatelistGraph(document.graph)"  style="cursor: pointer;"" />
					</td>
					</tr>
					</tbody>
					</table>
					</g:form>
					<br>

					<g:javascript library="prototype" />
					<div class="list"  id="updateMe">
					<ofchart:resources/>
					<g:javascript library="prototype"/>
                                       </div>
                          </div>


                        </div> <!-- End of content div -->


	</div>
	 <g:footer/>
</body>