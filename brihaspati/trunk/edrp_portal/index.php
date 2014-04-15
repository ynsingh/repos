<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 2.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
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
<?php
	include_once "db/connection.php"; 
            if (isset($_POST['login'])){
		if ($_POST['username']!="") {
                        $username = $_POST['username'];

                        $pass = $_POST['password'];

                        $username = strip_tags($username);

                        $pass = strip_tags($pass);

                        $username = mysql_real_escape_string($username);

                        $pass = mysql_real_escape_string($pass);
                        $pass= md5($pass);
			$sql = mysql_query("select * from member where username = '$username' and password = '$pass'");
			$login_check = mysql_num_rows($sql);
			if ($login_check == 0) {
						header("Location: admin.php?errorMssg=".urlencode("The username or password you entered is incorrect."));
			
			}
			while ($row = mysql_fetch_array($sql)) {

						$username = $row['username'];
						$_SESSION['username'] = $username;

						$password = $row['password'];

						$_SESSION['password'] = $password;

						//header('brihaspati.php');
						header("Location: index.php");
				}

			}

		}

	?>


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
<span style="float:right;color:#333;margin-top:-53px;">	<img src="images/missionlogo.png"  height="98" > </span>
	<h2></h2>
</div>
<div id="menu">
        <ul>
                <li> <script type="text/javascript">
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


		<marquee behavior="scroll"><h2>Welcome to ERP MISSION PORTAL</h2></marquee>



</div>
<?php
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
        echo "<input name='redirect' type='hidden' value='index.php'/>";
        echo "<h3>ABOUT ERP MISSION</h3>";
	echo "<textarea name=\"UserAddress1\" rows=\"9\" cols=\"30\"> $valueAEM </textarea>";
	echo "<input type='submit' value='update'>";
        echo "<h3>Responsibilities</h3>";
	echo "<textarea name=\"UserAddress2\" rows=\"17\" cols=\"30\"> $valueRES </textarea>";
	echo "<input type='submit' value='update'>";

	echo "<h3>CURRENT PROJECTS </h3>";
	echo "<textarea name=\"UserAddress3\" rows=\"12\" cols=\"30\"> $valueCurrentProject </textarea>";
        echo "<ul class=\"list1\">";
	echo "</ul>";
        echo "<input type='submit' value='update'>";
        echo "</form>";
        echo "</div>";
}


?>
</div>
<div  id="content">
<div style="float:right;color:#333;margin-top:-53px;" class="container" id="columnA">

	<table border="0" cellspacing="0" cellpadding="0" class="master_table">
		<tr>
			<td class="home_bg">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td colspan="2">
							
				<div id="myImageFlow" class="imageflow" style="width:890px; _margin-bottom:-20px;">
				<img src="images/home/sfm.png" border="0" usemap="#MapZinnov4" />		
				<img src="images/home/PICO.jpg" border="0" usemap="#MapZinnov" />		
				<img src="images/home/oals.png" border="0" usemap="#MapZinnov2" />		
                                <img src="images/home/BRIHASPATI.jpg" border="0" usemap="#Map5c" />		
				<img src="images/home/LMS.jpg" border="0" usemap="#Map3" />										
				<img src="images/home/EMS.jpg" border="0" usemap="#Map5" />
                                <img src="images/home/BGAS.jpg" border="0" usemap="#Map5d" />
                                <!--<img src="/images/home/oil-slide.jpg" border="0" usemap="#Map8" /> -->
                                <img src="images/home/EMS.jpg" border="0" usemap="#Map9" />
                                <img src="images/home/LMS.jpg" border="0" usemap="#Map10" />	
                                <img src="images/home/PMS.jpg" border="0" usemap="#Map11" />
				<img src="images/home/brihsync.png" border="0" usemap="#MapZinnov3" />		
                                <img src="images/home/TMS.jpg" border="0" usemap="#Map12" />
                                <img src="images/home/gms.png" border="0" usemap="#Map13" />
				<img src="images/home/sfm.png" border="0" usemap="#MapZinnov4" />		
				<img src="images/home/PICO.jpg" border="0" usemap="#MapZinnov" />
				<img src="images/home/oals.png" border="0" usemap="#MapZinnov2" />
                                <img src="images/home/BRIHASPATI.jpg" border="0" usemap="#Map5c" />		
				<img src="images/home/LMS.jpg" border="0" usemap="#Map3" />										
				<img src="images/home/EMS.jpg" border="0" usemap="#Map5" />
                                <img src="images/home/BGAS.jpg" border="0" usemap="#Map5d" />
                                <!-- <img src="/images/home/oil-slide.jpg" border="0" usemap="#Map8" />	-->
				<img src="images/home/EMS.jpg" border="0" usemap="#Map9" />
                                <img src="images/home/LMS.jpg" border="0" usemap="#Map10" />
                                <img src="images/home/PMS.jpg" border="0" usemap="#Map11" />
				<img src="images/home/brihsync.png" border="0" usemap="#MapZinnov3" />		
                                <img src="images/home/TMS.jpg" border="0" usemap="#Map12" />
                                <img src="images/home/gms.png" border="0" usemap="#Map13" />
				<img src="images/home/sfm.png" border="0" usemap="#MapZinnov4" />		
        			<img src="images/home/PICO.jpg" border="0" usemap="#MapZinnov" />
        			<img src="images/home/oals.png" border="0" usemap="#MapZinnov2" />
                                <img src="images/home/BRIHASPATI.jpg" border="0" usemap="#Map5c" />		
				<img src="images/home/LMS.jpg" border="0" usemap="#Map3" />										
				<img src="images/home/EMS.jpg" border="0" usemap="#Map5" />	
                                <img src="images/home/BGAS.jpg" border="0" usemap="#Map5d" />
                                <!-- <img src="/images/home/oil-slide.jpg" border="0" usemap="#Map8" /> -->
                                <img src="images/home/EMS.jpg" border="0" usemap="#Map9" />
                                <img src="images/home/LMS.jpg" border="0" usemap="#Map10" />
                                <img src="images/home/PMS.jpg" border="0" usemap="#Map11" />
				<img src="images/home/brihsync.png" border="0" usemap="#MapZinnov3" />		
                                <img src="images/home/TMS.jpg" border="0" usemap="#Map12" />
                                <img src="images/home/gms.png" border="0" usemap="#Map13" />
				<img src="images/home/sfm.png" border="0" usemap="#MapZinnov4" />		
				<img src="images/home/PICO.jpg" border="0" usemap="#MapZinnov" />
        			<img src="images/home/oals.png" border="0" usemap="#MapZinnov2" />
                                <img src="images/home/BRIHASPATI.jpg" border="0" usemap="#Map5c" />		
				<img src="images/home/LMS.jpg" border="0" usemap="#Map3" />										
				<img src="images/home/EMS.jpg" border="0" usemap="#Map5" />	
                                <img src="images/home/BGAS.jpg" border="0" usemap="#Map5d" />
                                <!-- <img src="/images/home/oil-slide.jpg" border="0" usemap="#Map8" /> -->	
                                <img src="images/home/EMS.jpg" border="0" usemap="#Map9" />
                                <img src="images/home/LMS.jpg" border="0" usemap="#Map10" />
                                <img src="images/home/PMS.jpg" border="0" usemap="#Map11" />
				<img src="images/home/brihsync.png" border="0" usemap="#MapZinnov3" />		
                                <img src="images/home/TMS.jpg" border="0" usemap="#Map12" />
                                <img src="images/home/gms.png" border="0" usemap="#Map13" />
				<img src="images/home/sfm.png" border="0" usemap="#MapZinnov4" />		
				<img src="images/home/PICO.jpg" border="0" usemap="#MapZinnov" />
        			<img src="images/home/oals.png" border="0" usemap="#MapZinnov2" />
                                <img src="images/home/BRIHASPATI.jpg" border="0" usemap="#Map5c" />		
				<img src="images/home/LMS.jpg" border="0" usemap="#Map3" />										
				<img src="images/home/EMS.jpg" border="0" usemap="#Map5" />	
                                <img src="images/home/BGAS.jpg" border="0" usemap="#Map5d" />
                                <!-- <img src="/images/home/oil-slide.jpg" border="0" usemap="#Map8" /> -->
                                <img src="images/home/EMS.jpg" border="0" usemap="#Map9" />
                                <img src="images/home/LMS.jpg" border="0" usemap="#Map10" />
                                <img src="images/home/PMS.jpg" border="0" usemap="#Map11" />
				<img src="images/home/brihsync.png" border="0" usemap="#MapZinnov3" />		
                                <img src="images/home/TMS.jpg" border="0" usemap="#Map12" />
                                <img src="images/home/gms.png" border="0" usemap="#Map13" />
				</div>   
				</div>
				</table>
				</td>
				</tr>
				</table>
				</td>
				</tr>

<map name="Map3" id="Map3">
<area shape="rect" coords="18,306,144,347" href="http://202.141.40.218:8080/LibMS/" / alt="Library Management System" title="Library Management System">
</map>


<map name="Map5" id="Map5"> 
<area shape="rect" coords="16,266,129,300" href="https://202.141.40.218:8443/EMS" / alt="Election Management System" title="Election Management System">
</map>



<map name="Map5c" id="Map5c">   
   <area shape="rect" coords="0,303,100,353"  href="http://202.141.40.215:8080/brihaspati/servlet/brihaspati" alt="Brihaspati" title="Brihaspati" target="_blank"/>  
</map>
<map name="MapZinnov" id="MapZinnov">   
   <area shape="rect" coords="21,251,127,301"  href="http://202.141.40.218:8080/pico/Administration/Index.action" alt="Purchase and Inventory Control System" title="Purchase and Inventory Control System" target="_blank"/>  
</map>
<map name="MapZinnov2" id="MapZinnov2">
   <area shape="rect" coords="21,251,127,301"  href="http://180.149.53.46:8084/CMS" alt="Online Admission and Course Registration System" title="Online Admission and Course Registration System" target="_blank"/>
</map>
<map name="MapZinnov3" id="MapZinnov3">
   <area shape="rect" coords="19,317,109,367"  href="https://202.141.40.216:8443/brihaspatisync_client/" alt="Brihaspati_Sync" title="Brihaspati_Sync" target="_blank"/>
</map>
<map name="MapZinnov4" id="MapZinnov4">
   <area shape="rect" coords="11,275,114,306"  href="https://202.141.40.215:8443/studentfeesmgms/" alt="SFMC" title="SFMC" target="_blank"/>
</map>
<map name="Map5d" id="Map5d">
  <area shape="rect" coords="14,311,119,361" href="http://202.141.40.215/~brihaspati/BGAS/index.php/user/login"  target="_blank" alt="Brihaspati Genral Accounting System" title="Brihaspati Genral Accounting System"/>
</map>
<map name="Map8" id="Map8">
  <area shape="rect" coords="17,252,109,280" href="http://202.141.40.218:8080/LibMS/" target="_blank" alt="Library Management System" title="Library Management System" />
</map>
<map name="Map9" id="Map9">
  <area shape="rect" coords="17,278,109,306" href="https://202.141.40.218:8443/EMS" target="_blank" alt="Election Management System" title="Election Management System" />
</map>
<map name="Map10" id="Map10">
  <area shape="rect" coords="18,306,144,347" href="http://202.141.40.218:8080/LibMS/" target="_blank" alt="Library Management System" title="Library Management System" />
</map>
<map name="Map11" id="Map11">
  <area shape="rect" coords="17,278,109,306" href="http://202.141.40.218:8081/PMS/" target="_blank" alt="Project Management System" title="Project Management System" />
</map>
<map name="Map12" id="Map12">
  <area shape="rect" coords="17,278,109,306" href="http://202.141.40.218:8081/PayrollSys/" target="_blank" alt="Payroll and Tax Management System" title="Payroll and Tax Management System" />
</map>
<map name="Map13" id="Map13">
  <area shape="rect" coords="17,278,109,306" href="http://202.141.40.218:8081/PayrollSys/" target="_blank" alt="Grant management System" title="Grant management System"" />
</map>
</div></div>
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

</body>
</html>
