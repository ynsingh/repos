 <table width="50%" >
	                    
	                        <thead>
	                            <tr>
					 <th>SlNo</th>
	                            
	                       	        <th>User Name</th>              	        
	                       	        <th>Site Name</th>
	                                 <th>Event Count</th>
	                                 
	                            
	                            </tr>
	                        </thead>
	                        <tbody>
	                        <g:each in="${siteResourceList}" status="i" var="siteResourceInstance">
	                            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
	                            
	                                <td>${i+1}</td>
					<td>${siteResourceInstance.userName}</td>
	                                 <td>${siteResourceInstance.siteId}</td>
            
	                                <td>${siteResourceInstance.eventCount}</td>
	                            
	                            </tr>
	                        </g:each>
	                        </tbody>
	                    </table>
		
		<br>
		<div class="dialog" >
		<ofchart:resources/>
		<g:javascript library="prototype"/>
	<center> <ofchart:chart name="demo-chart" url="${createLink(action:'stud_CHART',id:session.modname)}" width="600" height="400"/> </center>
	</div>

