<head>
	<meta name="layout" content="main" />
	<title>LMS List</title>
       </head>

<body>

  <%
      def labels = ['First','Second','Third']
      def colors = ['FF0000','00ff00','0000ff']
      def values = [35,45,10]
      def values2 = [[35,45,10],[3,987,2]]
      def values5 = [[0,16.7,23.3,33.3,60,76.7,83.3,86.7,93.3,96.7,100],[30,45,20,50,15,80,60,70,40,55,80],[0,10,16.7,26.7,33.3],[50,10,30,55,60]]
      def values3 = [97,12,454,12,5,32,78,4,99,89,98,77,7,77]
      def values4 = [[97,12,454,12,5,32,78,4,99,89,98,77,7,77],[1,6,42,15,78,94,26,45,12,10,21,22,33,33]]
      def values6 = [[-500,30,700,253],[2,-5,3,6]]
      def values8 = [[35],[20],[15]]
    %>

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
                  <td align="center" width="400">
                    <br />
                    <div style="padding-left:30px;"
                   <chart:barChart title='LMS Usage' size="${[300,400]}" colors=['FF0000','00ff00','0000ff'] type="bvg"
      	labels="${labels}" zeroLine="${'0.5'}" axes="x,y" axesLabels="${axesLabel}" fill="${'bg,s,efefef'}" dataType='simple' data='${lmsUsage}' /></td>

                   </td>
                    <td width="300">
                      <div>
                        Recent Visits
                      </div>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
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