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

                						<div align ="center">
						<h2> Course: ${params.siteName}</h2>
						</div>
						<br />
						<div id="box">
						<br>
						<center>
						<ofchart:resources/>
						<g:javascript library="prototype"/>
						<ofchart:chart name="demo-chart" url="${createLink(action:'COURSE_USAGE',params:[siteName:params.siteName])}" width="600" height="400"/>
						</center>
						<br />
						<h3>User Activity Summary</h3>
						<g:if test="${flash.message}">
						<div class="message">${flash.message}</div>
						</g:if>
						<table width="50%">

						<thead>
						<tr>
						<th>User Name</th>
						<th>Event Count</th>


						</tr>
						</thead>
						<tbody>
						<g:each in="${lst}" status="i" var="siteResourceInstance">
						<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

						<td>${urs[i]}</td>
						<td>${lst[i]}</td>

						</tr>
						</g:each>
						</tbody>
						</table>
						</div>



     </div> <!-- End of content div -->
	</div>
	 <g:footer/>
</body>