<h2><center>User Wise Learning Chart</center></h2>
    <center>
    <b>User Name : ${params.uname}<b>
 <table width="50%" >

	                        <thead>
	                            <tr>
	                       	        <th>Activity Name</th>
	                       	        <th>Activity Rate</th>
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
	<center> <ofchart:chart name="demo-chart" url="${createLink(action:'USER_ALLCOMP_CHART',params:[id:params.uname])}" width="600" height="400"/> </center>
	</div>














