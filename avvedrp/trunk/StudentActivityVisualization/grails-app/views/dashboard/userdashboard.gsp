<head>
	<meta name="layout" content="main" />
	<title>LMS List</title>
       </head>

<body>

	<div id="container">

        <div id="wrapper">
            <div id="content">

                <br />
                <div id="box">
          <h3 id="adduser">DASH BOARD</h3>



               <!-- Resources for chart -->
               <g:javascript library="prototype" />
               <ofchart:resources/>
               <!-- Resources for chart -->

<!-- Table starts here -->
                  <table width="700" border="1">
                 <tr height="350">
                  <td align="center">
                    <br />  
                    <center> <ofchart:chart name="demo-chart" url="${createLink(action:'DASHBOARD_BAR')}" width="300" height="300"/> </center>
                   </td>
                  <td align="center">
                     <br />  
                  <center> <ofchart:chart name="demo-chart1" url="${createLink(action:'DASHBOARD_BAR1')}" width="300" height="300"/> </center>
                  </td>
                </tr>
                <tr>
                  <td>
                    <label><strong>Select Course:</strong></label>&nbsp;&nbsp;<g:select id="authorSelection" name="modname" from="${courseList}" style="width:200px" value="" onchange="${remoteFunction(controller:'courseActivity', action:'dash_courseusage',update:'updateMe', params:'\'cname=\' + this.value' )}" />
                       <div class="list"  id="updateMe">                         
                        <!-- Chart will be updated here -->
                        <br />
                        <ofchart:resources/>
                        <g:javascript library="prototype"/>
                        <center> <ofchart:chart name="demo-chart2" url="${createLink(action:'dash_courseusagebar')}" width="300" height="300"/> </center>

                        </div>
                  </td>
                  <td>&nbsp;</td>
                </tr>
              </table>
<!-- Table ends here -->

                </div>
            </div>

            <g:sideMenu/>

      </div>
         <g:styleSwitcher/>
</div>

</body>