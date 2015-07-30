<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<?php

session_start();

$xmlDoc = new DOMDocument();

$xmlDoc->load( 'bgas.xml' );

$searchNode = $xmlDoc->getElementsByTagName( "BGAS" );



foreach( $searchNode as $searchNode )

{



        $xmlInfo = $searchNode->getElementsByTagName( "INFO" );

        $valueInfo = $xmlInfo->item(0)->nodeValue;

        $xmlUrl = $searchNode->getElementsByTagName( "URL" );

        $valueUrl= $xmlUrl->item(0)->nodeValue;

        $xmlANAME = $searchNode->getElementsByTagName( "ANAME" );

        $valueANAME = $xmlANAME->item(0)->nodeValue;

        $xmlIMG = $searchNode->getElementsByTagName( "IMG" );

        $valueIMG= $xmlIMG->item(0)->nodeValue;

        $xmlNAME = $searchNode->getElementsByTagName( "NAME" );

        $valueNAME= $xmlNAME->item(0)->nodeValue;

}

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



<head>

<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<title>ERP-MISSION</title>

<meta name="keywords" content="" />

<meta name="description" content="" />

<link href="default.css" rel="stylesheet" type="text/css" />



<style>

        .social-icons{

                font: 14px/140% Arial, Helvetica, sans-serif;

                color: #666;

        }



        .social-icons a {

                color: #669;

                text-decoration: none;

        }

</style>



<link href="social-buttons.css" rel="stylesheet">



</head>

<div id="top">

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

xmlhttp.open("GET","bgas.xml",false);

xmlhttp.send();

xmlDoc=xmlhttp.responseXML; 

var info=xmlDoc.getElementsByTagName("BGAS");

var server=xmlDoc.getElementsByTagName("SERVER");

xmlhttp.open("GET","headerfooter.xml",false);

xmlhttp.send();

xmlDoc=xmlhttp.responseXML; 

var pro=xmlDoc.getElementsByTagName("PROJECTS");

var y=xmlDoc.getElementsByTagName("FOOTER");



var link=xmlDoc.getElementsByTagName("SECONDARYLINK");



</script>



<div id="header">

        <h1>EdRP MISSION</h1>

<span style="float:right;color:#333;margin-top:-53px;"> <img src="images/missionlogo.png"  height="98" > </span>

        <h2></h2>

</div>

<div style="background-color: #1C364F;margin-top:-15px;font-size: 10pt;margin-bottom:15px; height:47px;">

<div id="menu">

<table  border="0" cellspacing="0" cellpadding="0" class="master_table" style="width:1100px;">

<tr>

	<script type="text/javascript">

        for (i=0;i<pro.length;i++)

        { 

                document.write("<td><a href=");

                document.write(pro[i].getElementsByTagName("TITLE")[0].childNodes[0].nodeValue);

                document.write(">");

                document.write(pro[i].getElementsByTagName("NAME")[0].childNodes[0].nodeValue);

                document.write("</a></td>");

        }

	</script>

</tr>

</table>

</div>

