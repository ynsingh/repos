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
                	<h3 id="adduser">Course Activity Chart</h3>
                        <br />
                     <ofchart:resources/>
                    <g:javascript library="prototype"/>
                <center> <ofchart:chart name="demo-chart" url="${createLink(action:'PIE_CHART')}" width="600" height="400"/> </center>
                 <g:if test="${flash.message}">
                 <div class="message">${flash.message}</div>
            </g:if>
                <br>
                   <table>
                    <thead>
                        <tr>
                   	        <th><b>SlNo<b></th>
                   	        <th><b>Course<b></th>
                   	         <th><b>Event<b></th>
                   	        <th><b>Count<b></th>
                         </tr>
                    </thead>
                    <g:each in="${studentResourceList}" status="i" var="studentResourceInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                             <td>${i+1}</td>
                             <td><%= studentResourceInstance.userId  %></td>
                            <td><%= studentResourceInstance.eventId %></td>
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
