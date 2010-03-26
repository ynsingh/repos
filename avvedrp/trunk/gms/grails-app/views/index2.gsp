<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<html lang="en">
    <head>
    <gui:resources components="['richEditor','tab','accordion']" mode="debug"/>
   
 
         <script type="text/javascript" src="images/jquery.js"></script>
		<script type="text/javascript" src="images/jquery.progressbar.min.js"></script>
	   		<script type="text/javascript">
			var progress_key = '<?= $uuid ?>';

			$(document).ready(function() {
				$("#pb1").progressBar();
				
			});
         
          </script>
    </head>
    
  
    
    
    <body>


		   <div id="container">
			
			<div class="contentblock">
				
					<table>
					<tr><td></td><td><span class="progressBar" id="pb1">20%</span></td></tr>
					
				</table>
				
		 	</div> 
			
	   


    </body>
</html>