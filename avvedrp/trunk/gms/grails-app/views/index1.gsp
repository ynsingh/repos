<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<html lang="en">
    <head>
    <gui:resources components="['richEditor','dialog']"/>
    <g:javascript library="jquery"/>
    
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <meta http-equiv="Content-Script-Type" content="text/javascript">

        <title>Tabs - jQuery plugin for accessible, unobtrusive tabs</title>

        <script src="images/jquery-1.1.3.1.pack.js" type="text/javascript"></script>
        <script src="images/jquery.history_remote.pack.js" type="text/javascript"></script>
        <script src="images/jquery.tabs.pack.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(function() {


                 $('#container-9').tabs({ remote: true });


            });
        </script>

        <link rel="stylesheet" href="images/jquery.tabs.css" type="text/css" media="print, projection, screen">
        <!-- Additional IE/Win specific style sheet (Conditional Comments) -->
        <!--[if lte IE 7]>
        <link rel="stylesheet" href="jquery.tabs-ie.css" type="text/css" media="projection, screen">
        <![endif]-->


    </head>
    
  
    
    
    <body>


     

        <h2>Ajax tabs</h2>

        <div id="container-9">
            <ul>
                <li><a href="grantAllocation/projectDash/1"><span>One</span></a></li>
                <li><a href="grantAllocation/projectDash/2"><span>Two</span></a></li>
                <li><a href="ahah_3.html"><span>Three</span></a></li>
            </ul>
        </div>



    </body>
</html>