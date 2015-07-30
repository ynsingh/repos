<html xmlns="http://www.w3.org/1999/xhtml">
<?php
$xmlDoc = new DOMDocument();
$xmlDoc->load( 'main.xml' );
$searchNode = $xmlDoc->getElementsByTagName( "CD" );

foreach( $searchNode as $searchNode )
{

    $xmlAEM = $searchNode->getElementsByTagName( "AEM" );
    $valueAEM = $xmlAEM->item(0)->nodeValue;
    $xmlRES = $searchNode->getElementsByTagName( "RES" );
    $valueRES = $xmlRES->item(0)->nodeValue;

    $xmlCurrentProject = $searchNode->getElementsByTagName( "CP" );
    $valueCurrentProject = $xmlCurrentProject->item(0)->nodeValue;

}
?>

<?php session_start(); ?>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>ERP-MISSION PORTAL</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/imageflow.packed.css" type="text/css" />
<script src="js/jquery-1.2.6.js" type="text/javascript"></script>
<script type="text/javascript" src="js/imageflow.js"></script>  
</head>
<div id="top">
<div id="content" style="margin-top: 0px; padding: 0px 0px 20px 0px;">
<input class="button" value="CONTACT US" type="submit" />
<input class="button" value="ABOUT US" type="submit" />
<input class="button" value="HOME" type="submit" />
</div>
</div>
<body>
<script>
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.open("GET","main.xml",false);
xmlhttp.send();
xmlDoc=xmlhttp.responseXML; 
var x=xmlDoc.getElementsByTagName("CD");
var z=xmlDoc.getElementsByTagName("IP");
xmlhttp.open("GET","headerfooter.xml",false);
xmlhttp.send();
xmlDoc=xmlhttp.responseXML; 
var pro=xmlDoc.getElementsByTagName("PROJECTS");
var y=xmlDoc.getElementsByTagName("FOOTER");
</script>
<div id="header">
        <h1>ERP MISSION</h1>
<span style="float:right;color:#333;margin-top:-53px;"> <img src="images/missionlogo.png"  height="98" > </span>
</div>
<div style="background-color: #1C364F;margin-top:-15px;font-size: 10pt;margin-bottom:15px;">
                <marquee behavior="scroll" style="height:47px;"  ><font color="white "><h2>GOOD MORNING & Welcome to ERP MISSION PORTAL</h2></font></marquee>
</div>

