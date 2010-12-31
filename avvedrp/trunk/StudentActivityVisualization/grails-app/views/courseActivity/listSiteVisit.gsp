<head>
	<meta name="layout" content="main" />
	<title>Course Usage</title>
       </head>

<body>

	<div id="container">

        <div id="wrapper">
            <div id="content">

                <br />
                <div id="box">
                	<h3 id="adduser">Course Usage</h3>
                 <br>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
            <ofchart:resources/>
	       <g:javascript library="prototype"/>
		<center> <ofchart:chart name="demo-chart" url="${createLink(action:'sitevisit_PIE')}" width="700" height="400"/> </center>
		<br>
		</div>
                    <table width="60%" align="center" >
                    <thead>
                        <tr>
				<th>SlNo</th>
                                <th>Course</th>
                                <th>Activity Count</th>
                   	</tr>
                    </thead>
                    <g:each in="${studentResourceList}" status="i" var="studentResourceInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                             <td>${i+1}</td>
                            <td><%= studentResourceInstance.userId  %></td>
                            <td><%= studentResourceInstance.eventCount  %></td>
                          </tr>
                    </g:each>
                </table>

                </div>
            </div>     
            <g:sideMenu/>          
      </div>
        <g:styleSwitcher/>
</div>

</body>
