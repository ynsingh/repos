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
//     include_once "db/connection.php";
       
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

<script src="js/jquery-1.2.6.js" type="text/javascript"></script>
<script type="text/javascript" src="js/imageflow.js"></script>  
</head>
<div id="top">
<div id="content" style="margin-top: 0px; padding: 0px 0px 20px 0px;">
<input class="button" value="CONTACT US" type="submit" onclick="location.href='contactus.php'"/>
<input class="button" value="ABOUT US" type="submit"  onclick="location.href='aboutus.php'"/>
<input class="button" value="HOME" type="submit"  onclick="location.href='index.php'"/>
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
        <h1>EdRP MISSION</h1>
<span style="float:right;color:#333;margin-top:-53px;"> <img src="images/missionlogo.png"  height="98" > </span>
</div>
<div style="background-color: #1C364F;margin-top:-15px;font-size: 10pt;margin-bottom:31px;">
                <marquee behavior="scroll" style="height:50px;"  ><font color="white "><h2> Welcome to EdRP Portal</h2></font></marquee>
</div>
<div  id="content">
<?php
if( empty($_SESSION['username']) )
{
?>     
        <div id="columnB">
       <marquee behavior="alternate" direction="up" width=100% height=100% scrollamount="2" text-decoration: "none">

        <a href='bgas.php' style="text-decoration: none;" title='Go To BGAS'>&nbsp;&nbsp;BGAS</a> is standard integrated accounting and genral ledger &nbsp;&nbsp;system.<br>
        <a href='brihaspati.php' style="text-decoration: none;"  title='Go To BRIHASPATI'>&nbsp;&nbsp;BRIHASPATI</a> is the open platform of learning. It is the java servlets &nbsp; based content delivery system.<br>
        <a href='payroll.php' style="text-decoration: none;"  title='Go To Payroll'>&nbsp;&nbsp;Payroll System</a> is Perfect Solution for maintenance of Employee &nbsp;&nbsp;Attendance and their Provident Fund etc.
        <br>
        <a href='pico.php'   style="text-decoration: none;"  title='Go To PICO'>&nbsp;&nbsp;PICO</a> offers comprehensive reporting capabilities to keep you on &nbsp;&nbsp;top of inventory status.<br>
        <a href='ems.php'    style="text-decoration: none;"  title='Go To EMS'>&nbsp;&nbsp; Election Management System</a> consist of two parts: a &nbsp;&nbsp;management &nbsp;&nbsp;system and voting system.<br>
        <a href='oars.php'   style="text-decoration: none;"  title='Go To OARS'>&nbsp;&nbsp;OARS</a> is designed to handel Admission & Course resgistraions of &nbsp;&nbsp;students.<br>
        <a href='bsync.php'  style="text-decoration: none;"  title='Go To BRIHASPATISYNC'>&nbsp;&nbsp;BRIHASPATISYNC</a> is a platform-independent highly scalable live &nbsp;&nbsp;lecture delivery and interaction tool.<br>
	<a href='gms.php'    style="text-decoration: none;"  title='Go To GMS'>&nbsp;&nbsp;GMS</a> is available online N-level project and fund allocations.<br>
	<a href='tms.php'    style="text-decoration: none;"  title='Go To PMS'>&nbsp;&nbsp;PMS</a> Organization Managing Only Super Admin can add new &nbsp;&nbsp;organization, view organization details.<br>
	<a href='libms.php'  style="text-decoration: none;"  title='Go To LIBMS'>&nbsp;&nbsp;LIBMS</a> provides Online Public Access Catalog, Administration and &nbsp;&nbsp;Security, Utility etc services.<br>
	<a href='sfmc.php'   style="text-decoration: none;"  title='Go To SFMC'>&nbsp;&nbsp;SFMC</a> incorporates GUI technology that enables users for easy &nbsp;&nbsp;working on this module.<br>
      
	<p></p>
	<p></p>
       </marquee>
       
        </div>
       
<?php
}
?>
<div style="float:right;color:#333;margin-top:-45px;" class="container" id="columnA">
<!--input class="button" value= "REGISTER" type="submit" onclick="location.href='registration.php'" /--> 
<!--input class="button" value="LOGIN"  type="submit" onclick="location.href='Login.php'"/--> 
        <table border="0" cellspacing="0" cellpadding="0" class="master_table" style="margin-left:-100px;">
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
                                <!--div id="myImageFlow" class="imageflow" style="width:690px; margin-bottom:-20px;">
                               <img src="images/home/sfm.png" border="0" usemap="#MapZinnov4" />
                                <img src="images/home/PICO.jpg" border="0" usemap="#MapZinnov" />
                                <img src="images/home/oals.png" border="0" usemap="#MapZinnov2" />
                                <img src="images/home/BRIHASPATI.jpg" border="0" usemap="#Map5c" />
                                <img src="images/home/LMS.jpg" border="0" usemap="#Map3" />
                                <img src="images/home/EMS.jpg" border="0" usemap="#Map5" />
                                <img src="images/home/BGAS.jpg" border="0" usemap="#Map5d" />
                                <img src="images/home/oil-slide.jpg" border="0" usemap="#Map8" />
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
                                <img src="images/home/oil-slide.jpg" border="0" usemap="#Map8" /> 
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
                                <img src="images/home/oil-slide.jpg" border="0" usemap="#Map8" />
                                <img src="images/home/EMS.jpg" border="0" usemap="#Map9" />
                                <img src="images/home/LMS.jpg" border="0" usemap="#Map10" />
                                <img src="images/home/PMS.jpg" border="0" usemap="#Map11" />
                                <img src="images/home/brihsync.png" border="0" usemap="#MapZinnov3" />
                                <img src="images/home/TMS.jpg" border="0" usemap="#Map12" />
                                <img src="images/home/gms.png" border="0" usemap="#Map13" />
                                <img src="images/home/sfm.png" border="0" usemap="#MapZinnov4" />
                                <img src="images/home/PICO.jpg" border="0" usemap="#MapZinnov" /-->

<div id="content">
        <div class="register" style="background-color: #1C364F;">
          <h3 align=center><b>Create User Profile</h3>
          <form id="reg-form" action='registration.php' method="post">
            <div>
              <label for="first_name"><b>First Name<b></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

              <input type="text" name="first_name" id="first_name" spellcheck="false" />
            </div>

	    <div>
              <label for="last_name"><b>Last Name<b></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

              <input type="text" id="last_name" name="last_name"spellcheck="false" />
            </div>

             <div>
              <label for="mob_no">Mobile No</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

              <input type="text" id="mob_no" name="mob_no"  />
            </div>

            <div>
              <label for="Ins_name">Institute Name</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

              <input type="text" id="ins_name" name="ins_name" spellcheck="false" />
            </div>

	   <div>
              <label for="hint_ques">Hint Ques</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

              <input type="text" id="hint_ques" name="hint_ques" spellcheck="false" />
            </div>

	   <div>
              <label for="hint_ans">Hint_Ans</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

              <input type="text" id="hint_ans" name="hint_ans" spellcheck="false" />
            </div>

	   <div>
              <label for="address">Address</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

              <input type="text" id="address" name="address" spellcheck="false" />
            </div>

	   <div>
              <label for="lang">Language</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

              <input type="text" id="lang" name="lang" spellcheck="false" />
            </div>

            <div>
              <label for="secon_email">Secondary Email</label>&nbsp;&nbsp;&nbsp;
              <input type="text" id="email" name="email"/>
            </div>

             <div>
              <label for="mob_no">Mobile No</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

              <input type="text" id="mobno" name="mobno"/>
            </div>

	  <div>
              <label for="active">Active</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

              <input type="checkbox" id="active" />
           </div>

            <div>
              <label></label>

              <input type="submit" value="Create Account" id="create-account" class="submit" align="left"/>
            </div>

          </form>
            </div>

<?php
$first_name=$_POST['first_name'];
$last_name=$_POST['last_name'];
$mob_no=$_POST['mob_no'];
$Ins_name=$_POST['Ins_name'];
$hint_ques=$_POST['hint_ques'];
$hint_ans=$_POST['hint_ans'];
$address=$_POST['address'];
$lang=$_POST['lang'];
$secon_email=$_POST['secon_email'];
$mob_no=$_POST['mob_no'];
    $mysql_hostname = "localhost";
    $mysql_user = "root";
    $mysql_password = "root";
    $mysql_database = "erp";
    $prefix = "";
        if(!empty($_POST['usr_name']))
        {
                $this->HandleError("UserName is empty!");
                return false;
        }
$con = @mysql_connect($mysql_hostname, $mysql_user, $mysql_password);
                        if($con){
                                $value = mysql_select_db($mysql_database, $con);
                                $cl = " INSERT INTO UserProfile(FirstName,LastName) VALUES ('$first_name', '$last_name')";
//				  $cl = " INSERT INTO UserProfile(FirstName,LastName,MobileNo,HintQuestion,HintAnswer,Address,SecondaryEmail,Language) VALUES ('$first_name', '$last_name'.'$mob_no'.'$hint_ques'.'$hint_ans'.'$address'.'$secon_email'.'$lang')";

                                $val = mysql_query($cl);
                                if($val != ''){
                                        while($row = mysql_fetch_assoc($val))
                                        {
                                                print_r($row);
                                        }
                                }
                        }
?>

</div>


                                 </div>
                                </div>
                                </table>
                                </td>
                                </tr>
                                </table>
                                </td>
                                </tr>
<map name="Map3" id="Map3">
<area shape="rect" coords="18,306,144,347" href="http://202.141.40.218:8081/LibMS/" / alt="Library Management System" title="Library Management System">
</map>


<map name="Map5" id="Map5">
<area shape="rect" coords="16,266,129,300" href="https://202.141.40.218:8443/EMS" / alt="Election Management System" title="Election Management System">
</map>



<map name="Map5c" id="Map5c">
   <area shape="rect" coords="0,303,100,353"  href="http://202.141.40.215:8080/brihaspati/servlet/brihaspati" alt="Brihaspati" title="Brihaspati" target="_blank"/>
</map>
<map name="MapZinnov" id="MapZinnov">
   <area shape="rect" coords="21,251,127,301"  href="http://202.141.40.218:8081/pico/Administration/Index.action" alt="Purchase and Inventory Control System" title="Purchase and Inventory Control System" target="_blank"/>
</map>
<map name="MapZinnov2" id="MapZinnov2">
   <area shape="rect" coords="21,251,127,301"  href="http://202.141.40.218:8081/coursemanagement1.3/" alt="Online Admission and Course Registration System" title="Online Admission and Course Registration System" target="_blank"/>
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
  <area shape="rect" coords="17,252,109,280" href="http://202.141.40.218:8081/LibMS/" target="_blank" alt="Library Management System" title="Library Management System" />
</map>
<map name="Map9" id="Map9">
  <area shape="rect" coords="17,278,109,306" href="https://202.141.40.218:8443/EMS" target="_blank" alt="Election Management System" title="Election Management System" />
</map>
<map name="Map10" id="Map10">
  <area shape="rect" coords="18,306,144,347" href="http://202.141.40.218:8081/LibMS/" target="_blank" alt="Library Management System" title="Library Management System" />
</map>
<map name="Map11" id="Map11">
  <area shape="rect" coords="17,278,109,306" href="http://202.141.40.218:8080/PMS/" target="_blank" alt="Project Management System" title="Project Management System" />
</map>
<map name="Map12" id="Map12">
  <area shape="rect" coords="17,278,109,306" href="http://202.141.40.218:8081/PayrollSys/" target="_blank" alt="Payroll and Tax Management System" title="Payroll and Tax Management System" />
</map>
<map name="Map13" id="Map13">
  <area shape="rect" coords="17,278,109,306" href="http://202.141.40.218:8081/PayrollSys/" target="_blank" alt="Grant management System" title="Grant management System" />
</map>
</div></div>

<div id="footer">
<div id="header">
<table  border="0" cellspacing="0" cellpadding="0" class="master_table" style="width:1080px;">
<tr>
<td width="50%">
<script type="text/javascript">
                for (i=0;i<x.length;i++)
  { 
  document.write("<p>");
  document.write(y[i].getElementsByTagName("p1")[0].childNodes[0].nodeValue);
        document.write("<br>");
  document.write(y[i].getElementsByTagName("p2")[0].childNodes[0].nodeValue);
        document.write("<br>");
  document.write(y[i].getElementsByTagName("p3")[0].childNodes[0].nodeValue);
        document.write("<br>");
  document.write(y[i].getElementsByTagName("p4")[0].childNodes[0].nodeValue);
  document.write("</p>");
  }

 </script>

</td>
<td align="right">

      <p id="social-icons">
	<a href='http://www.blogger.com/share-post.g?blogID=361933389169269365&postID=3433546715559606585&target=twitter' class="sb flat twitter"></a>
	<a href='https://www.facebook.com/profile.php?id=100005620098367' target=email' class="sb flat facebook"></a>
	<a href='https://in.linkedin.com/pub/brihaspati-erp-mission-iitk/98/93a/456' class="sb flat linkedin"></a>
	<a href="#" class="sb flat pinterest"></a>
	<a href='http://www.blogger.com/share-post.g?blogID=361933389169269365&postID=3433546715559606585&target=email' class="sb flat email"></a>
	

      </p>
</td>
</tr>
</table>
</div>
</div>
<!-- demo js -->
<script src="js/demo/demo.js"></script>

<!-- ad -->
<script src="js/common/fusionad.js"></script>
</body>

</html>
