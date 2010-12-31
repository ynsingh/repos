    <table width="50%" >

	                        <thead>
	                            <tr>
	                       	        <th>Event</th>
	                                 <th>Event Count</th>
	                             </tr>
	                        </thead>
	                        <tbody>
	                        <g:each status="i" in="${lst}" var="myvar">
                                    <tr>
                                            <td> ${crs[i]} </td>
                                            <td> ${lst[i]} </td>
                                    <tr>
                                    </g:each>
	                        </tbody>
	                    </table>

		<br>
		<div class="dialog" >
		<ofchart:resources/>
		<g:javascript library="prototype"/>
	<center> <ofchart:chart name="demo-chart" url="${createLink(action:'STUD_SINGLE',params:[mod:params.modname])}" width="600" height="400"/> </center>
	</div>

