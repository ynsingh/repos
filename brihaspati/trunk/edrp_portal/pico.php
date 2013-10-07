<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<?php
session_start();
$xmlDoc = new DOMDocument();
$xmlDoc->load( 'pico.xml' );
$searchNode = $xmlDoc->getElementsByTagName( "PICO" );

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
</head>
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
xmlhttp.open("GET","pico.xml",false);
xmlhttp.send();
xmlDoc=xmlhttp.responseXML; 
var info=xmlDoc.getElementsByTagName("PICO");
var server=xmlDoc.getElementsByTagName("SERVER");
xmlhttp.open("GET","headerfooter.xml",false);
xmlhttp.send();
xmlDoc=xmlhttp.responseXML; 
var pro=xmlDoc.getElementsByTagName("PROJECTS");
var y=xmlDoc.getElementsByTagName("FOOTER");

</script>

<div id="header">
        <h1>ERP MISSION</h1>
<span style="float:right;color:#333;margin-top:-53px;"> <img src="images/missionlogo.png"  height="98" > </span>
        <h2></h2>
</div>
<div id="menu">
        <ul>

                <li>
             <script type="text/javascript">
                for (i=0;i<pro.length;i++)
                { 
                document.write("<tr><td><a href=");
                document.write(pro[i].getElementsByTagName("TITLE")[0].childNodes[0].nodeValue);
                document.write(">");
                document.write(pro[i].getElementsByTagName("NAME")[0].childNodes[0].nodeValue);
                document.write("</a></td><td>");
                        }
       
 </script>
	<div style="float:right;" >
                <?php
if( !empty($_SESSION['username']) )
{
echo "<font color=\"white\">Wellcome Admin</font><a href=\"logout.php\">Log Out</a>";
}
?>
</div>
</li>
        </ul>
</div>
<div id="content">
        <div id="columnA">
                <h2>                <script type="text/javascript">
                for (i=0;i<x.length;i++)
                { 
                document.write("<tr><td>");
                document.write(info[i].getElementsByTagName("NAME")[0].childNodes[0].nodeValue);
                document.write("</td><td>");
                document.write(info[i].getElementsByTagName("ANAME")[0].childNodes[0].nodeValue);
                        }
       
 </script>

</h2>
                <div style="width:500px">
                <p>
	<?php
                if( empty($_SESSION['username']) )
                        {
                        echo $valueInfo ;
                        }
                        else
                        {
                echo    "<form action=\"project.php\" method=\"post\">";
                echo    "<input name='filenm' type='hidden' value='pico.xml'/>";
                echo "<input name='redirect' type='hidden' value='pico.php'/>";
                echo    "<textarea name=\"UserAddress1\" rows=\"11\" cols=\"70\">$valueInfo </textarea>";
                echo    "<input type='submit'value='update'>";
                                }
                ?>

                </p>
                </div>
         <span style="float:right;color:#333;margin-top:-110px;"> <script type="text/javascript">
                for (i=0;i<x.length;i++)
                { 
                document.write("<tr><td><img src=");
                document.write(info[i].getElementsByTagName("IMG")[0].childNodes[0].nodeValue);
                document.write("usemap=#pico></td><td>");
                        }
       
 </script>
		<map name="pico">
		<script type="text/javascript">
                for (i=0;i<x.length;i++)
                { 
                                        document.write("<area shape=rect coords=21,251,127,301 alt=login href=");
                document.write(info[i].getElementsByTagName("URL")[0].childNodes[0].nodeValue);
                document.write("></map>");
                        }
       
 </script>
		 </span>
              <p><b><i>
<?php
if( empty($_SESSION['username']) )
                        {
                        echo $valueNAME ;
                        }
                        else
                        {
                echo    "<form action=\"project.php\" method=\"post\">";
                echo    "<input name='filenm' type='hidden' value='pico.xml'/>";
                echo    "<input name='element' type='hidden' value='PICO'/>";
                echo    "<input name='UserAddress2' type='hidden' value= '$valueUrl'/>";
                echo    "<input name='UserAddress5' type='hidden' value= '$valueIMG'/>";
                echo    "<input name='UserAddress4' type='hidden' value= '$valueANAME'/>";
              echo    "<textarea name=\"UserAddress3\" rows=\"2\" cols=\"70\">$valueNAME </textarea>";
                        echo "<input type='submit'value='update'>";
                        }
                ?>

		 Login Page:-</i></b></p>
                <script type="text/javascript">
                for (i=0;i<server.length;i++)
                {
                var count = i+1 
                document.write("<tr><td><a href=");
                document.write(server[i].getElementsByTagName("URL")[0].childNodes[0].nodeValue);
                document.write(">");
                document.write("PICO SERVER"+count);
                document.write("</a></td><td><br>");
                        }
 </script>
   <br>	


<?php
if( !empty($_SESSION['username']) )
{
	  echo "<b>Add Server Here:-</b>";
	echo "<form action=\"project.php\" method=\"post\">";
        echo "<input name='filenm' type='hidden' value='pico.xml'/>";
         echo "<input name='redirect' type='hidden' value='pico.php'/>";
        echo "<div id=\"my_div\">";
        echo "</div>";
        echo "<p>";
        echo "<textarea name=\"SERVER\" rows=\"2\" cols=\"70\"></textarea>";
        echo "<br>";
        echo "<input type='submit'value='ADD'>";
        echo "</form>";
}
       echo"</div>";
if( empty($_SESSION['username']) )
{
        echo "<div id=\"columnB\">";
        echo "<h3>ABOUT ERP MISSION</h3>";
        echo $valueAEM ;
        echo "<h3>Responsibilities</h3>";
        echo $valueRES;

        echo "<h3>CURRENT PROJECTS </h3>";
        echo $valueCurrentProject;
        echo "</div>";

}
else
{
        echo "<div id=\"columnB\">";
        echo "<form action=\"xmlsave.php\" method=\"post\">";
        echo "<input name='filenm' type='hidden' value='main.xml'/>";
        echo "<input name='redirect' type='hidden' value='pico.php'/>";
        echo "<h3>ABOUT ERP MISSION</h3>";
        echo "<textarea name=\"UserAddress1\" rows=\"9\" cols=\"30\"> $valueAEM </textarea>";
        echo "<input type='submit'value='update'>";
        echo "<h3>Responsibilities</h3>";
        echo "<textarea name=\"UserAddress2\" rows=\"17\" cols=\"30\"> $valueRES </textarea>";
        echo "<input type='submit'value='update'>";

        echo "<h3>CURRENT PROJECTS </h3>";
        echo "<textarea name=\"UserAddress3\" rows=\"12\" cols=\"30\"> $valueCurrentProject </textarea>";
        echo "<ul class=\"list1\">";
        echo "</ul>";
        echo "<input type='submit'value='update'>";
        echo "</form>";
        echo "</div>";
}
?>

</div>
<div id="footer">
<p>
<script type="text/javascript">
                for (i=0;i<x.length;i++)
  { 
  document.write("<tr><td>");
  document.write(y[i].getElementsByTagName("p1")[0].childNodes[0].nodeValue);
        document.write("<br>");
  document.write(y[i].getElementsByTagName("p2")[0].childNodes[0].nodeValue);
        document.write("<br>");
  document.write(y[i].getElementsByTagName("p3")[0].childNodes[0].nodeValue);
        document.write("<br>");
  document.write(y[i].getElementsByTagName("p4")[0].childNodes[0].nodeValue);
  document.write("</td><td>");
  }
       
 </script>
</p>
</div>
</html
