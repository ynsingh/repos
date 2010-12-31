<head>
	<meta name="layout" content="main" />
	<title>Per Course Activity List</title>       
       </head>

<body>

	<div id="container">

        <div id="wrapper">
            <div id="content">

                <br />
                <div id="box">
          <h3 id="adduser">Course Activity Chart</h3>
        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>


<table>
       <tbody>
         <tr>
                        <td>
                            <label><strong>Select Course:</strong></label>&nbsp;&nbsp;
                            <g:select id="authorSelection" name="modname" from="${lst}" value="" noSelection="[null:'-- Select --']" onchange="${remoteFunction(controller:'courseActivity', action:'studSingleAct',update:'updateChart', params:'\'modname=\' + this.value' )}" />
                        </td>
                    </tr>
                 </tbody>
            </table>

            <br>

            <g:javascript library="prototype" />
            <div id="updateChart" class="list">
            <center>

                <br>
                </center>

	                <ofchart:resources/>
	   	     	<g:javascript library="prototype"/>
	   	     	<center> <ofchart:chart name="demo-chart" url="${createLink(action:'STUD_ALL_Course')}" width="600" height="400"/> </center>
             </div>



                </div>
            </div>
          
            <g:sideMenu/>
          
      </div>
         <g:styleSwitcher/>
</div>

</body>
