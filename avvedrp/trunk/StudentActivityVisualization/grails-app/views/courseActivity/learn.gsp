<html>
    
    <body>
Site: ${session.sitename} 

         <ofchart:resources/>
  	<g:javascript library="prototype"/>
	<center> <ofchart:chart name="demo-chart" url="${createLink(action:'stud_CHART',id:session.modname)}" width="800" height="400"/> </center>             
   
  </body>
</html>

