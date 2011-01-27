<?php 
session_start();
getHeader(); 
echo "<div align=\"left\" style=\"position: relative; margin:auto; width: 1024px; background-color:#e3f2fc\">";
?>
<link href="<?php echo getThemeCss('home_styles.css'); ?>" rel="stylesheet" type="text/css"/>
<?php

	$req = $_SERVER['REQUEST_URI'];
	$cadena = explode( "?", $req); 
	$mi_url = $cadena[0]; 
	$resto = $cadena[1]; 
	// here you can put your suspicions chains at your will. Just be careful of 
	// possible coincidences with your URL's variables and parameters 
	//$strangeChar='/script|http|<|>|%3c|%3e|SELECT|UNION|AND|exe|exec|INSERT|tmp/i'; 
	//$strangeChar='/http|<|>|%3c|%3e|SELECT|UNION|AND|exe|exec|INSERT|tmp/'; 
	$strangeChar='/script|http|<|>|%3c|%3e/i'; 
	//ip query string 
	?>    
<table height="606" border="0" align="center" cellpadding="0" cellspacing="0" id="main" style="background-color:#FFF">
	<tr>
		<td width="300" align="center" valign="top">
<div style="padding-left:35px; padding-top:5px; padding-right:10px">

	<?php
	/* to display sign in details */
	//
	//
	 //echo "sess role id:".$_SESSION['sessUserRoleId'];
	if($_SESSION['sessUserRoleId'])
    {
	?>
    	<div align="left">
        	<?php displayWelcomeMessage($_SESSION['sessFullName'],$_SESSION['sessUserRoleId'],HOME);?>
    
      	</div>
    <?php
    } 
    ?>  
    <div class="menuText" align="left">Featured Simulation</div>
			<div align="left">				                
                <a href="">
                <div><span class="featuredTitle">Neuronal Model</span></span><br />
                <img src="<?php echo getThemeImage('icon-neuron-model.jpg');?>" /> 
                <p class="text-yellowBox"> The Hodgkin&ndash;Huxley model is a scientific model that describes how action potentials in neurons are initiated and propagated. It is a set of nonlinear ordinary differential equations that approximates the electrical characteristics of excitable cells such as neurons and cardiac myocytes. </p> 
               <img align="right" src="<?php echo getThemeImage('read-more.jpg');?>" width="61" height="14" alt="Simulator" />
            </div>
            </a> 
           
            
	</div>
            
       <div style="padding-top:20px">     
   
<img src="<?php echo getThemeImage('left-sidebar_02.jpg');?>" alt="Developed at Amrita University" width="250" height="65" />
<img src="<?php echo getThemeImage('left-sidebar_03.jpg');?>" width="250" height="65" alt="Our Inspiration" />

<div class="menuText" align="left"><br />
  Sponsors</div>
<div style="text-align:justify; padding-right:10px" class="text-yellowBox">
<img src="<?php echo getThemeImage('sponsors.jpg');?>" width="63" height="96" alt="Sponsor of Virtual Lab" align="left" />This project is an initiative of Ministry of Human Resource Department under National Mission on Education through ICT. These experiments and labs will be hosted for open access through the main project website <a href="http://vlab.co.in" title="Link to Virtual Lab Project Website" target="_blank">http://vlab.co.in</a></div>
       </div>

         </div>
         </td>
		<td valign="top">
		  <table id="content-side" width="728" height="606" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="2">
			<img src="<?php echo getThemeImage('main-sidebar_01.jpg');?>" width="728" height="228" alt="" style="padding-bottom:5px"></td>
	</tr>
	<tr>
		<td align="left" valign="top">
 			<table width="100%" id="mainC" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>
        
        <div>
          <b class="title-box-round">
          <b class="title-box-round1"><b></b></b>
          <b class="title-box-round2"><b></b></b>
          <b class="title-box-round3"></b>
          <b class="title-box-round4"></b>
          <b class="title-box-round5"></b></b>
        
          <div class="title-box-roundfg">
            <div class="title-main-page">&nbsp;Virtual Labs at Amrita</div>
          </div>
        
        </div>
                <table width="100%" border="0" cellspacing="2" cellpadding="0">
                <tr>
                <td>
                <?php displaySubjectMenu(UNIVERSITY_ID); ?>
                </td>
                <!--<td>
                <div style="background-image: url(<?php echo getThemeImage('subjectBox.jpg');?>); height: 100%; width: 100%;">
                <img align="left" style="padding-left:15px" src="<?php echo getThemeImage('icon-phy.jpg');?>" width="53" height="52" alt="Physics Virtual Lab" /><div class="subjects"><a href="?sub=PHY" title="Physics Virtual Lab">Physical Sciences</a></div>
                <div class="subjects-small"><a href="?sub=PHY&amp;brch=CLA" title="Classical Mechanics Lab">Mechanics</a>, <a href="?sub=PHY&amp;brch=TDM" title="Thermodynamic Lab">Thermodynamics</a>, <a href="?sub=PHY&amp;brch=OPT" title="Optics Virtual Lab">Optics</a>, <a href="?sub=PHY&amp;brch=EMM" title="Electricity and Magnetism Lab">Electricity and Magnetism</a>, <a href="?sub=PHY&amp;brch=BEC" title="Basic Electric Circuits">Basic Electric Circuits</a>, <a href="?sub=PHY&amp;brch=MPY">Modern Physics</a> Virtual Labs...</div>
                
                </div></td>
                </tr>
                <tr>
                <td>
                
                <div style="background-image: url(<?php echo getThemeImage('subjectBox.jpg');?>); height: 100%; width: 100%;">
                <img align="left" style="padding-left:15px" src="<?php echo getThemeImage('icon-che.jpg');?>" width="53" height="52" alt="Chemistry Virtual Lab" />
                <div class="subjects"><a href="?sub=CHE" title="Chemistry Virtual Lab">Chemical Sciences</a></div>
                <div class="subjects-small"><a href="?sub=CHE&amp;brch=SOL" title="Physical Chemistry Virtual Lab">Physical Chemistry</a>, <a href="?sub=CHE&amp;brch=ORG" title="Organic Chemistry Virtual Lab">Organic Chemistry</a>, <a href="?sub=CHE&amp;brch=INC">Inorganic Chemistry</a>, Electrochemistry Virtual Labs...</div></div></td>
                </tr>
                <tr>
                <td> <div style="background-image: url(images/subjectBox.jpg); height: 100%; width: 100%;">
                <img align="left" style="padding-left:15px" src="<?php echo getThemeImage('icon-bio.jpg');?>" width="53" height="52" alt="Biotechnology Virtual Lab" />
                <div class="subjects"><a href="?sub=BIOTECH" title="Biotechnology Virtual Labs">Biotechnology Labs</a></div>
                <div class="subjects-small"><a href="?sub=BIOTECH&amp;brch=NEO" title="Virtual Neurophysiology Labs">Neurophysiology</a>, Cell biology, Immunology Lab, Microbiology, Molecular Biology, <?php /*<a href="?sub=BIOTECH&amp;brch=POE"> */?>Population Ecology<?php /*</a> */ ?>,<?php /* <a href="?sub=BIOTECH&amp;brch=BIC"> */?>Biochemistry<?php /*</a>*/?> Virtual Labs...</div></div></td>
                </tr>
                 <tr>
                <td> <div style="background-image: url(images/subjectBox.jpg); height: 100%; width: 100%;">
                <img align="left" style="padding-left:15px" src="<?php echo getThemeImage('ico_cs.gif');?>" width="53" height="52" alt="Biotechnology Virtual Lab" />
                <div class="subjects"><a href="?sub=COM" title="Computer Science">Computer Science</a></div>
                <div class="subjects-small">Intrusion Detection, Network Security, Wireless Sensor Networks Virtual Labs...<br/><br/></div></div></td>-->
                </tr>
                </table>
        <div>
        <b class="title-box-round">
        <b class="title-box-round1"><b></b></b>
        <b class="title-box-round2"><b></b></b>
        <b class="title-box-round3"></b>
        <b class="title-box-round4"></b>
        <b class="title-box-round5"></b></b>
        
        <div class="title-box-roundfg">
        <div class="title-main-page">&nbsp;Technologies</div>
        <img src="<?php echo getThemeImage('icons-all.jpg');?>" width="480" border="0" usemap="#Map" align="right" /></div>
        </div>
      </td>
	</tr>
	
</table>
</td>
		<td valign="top"><table id="Table_01" border="0" cellpadding="0" cellspacing="1">
		  <tr>
		    <td>
  <div style="padding-top:0px">
  <b class="yellowBox">
  <b class="yellowBox1"><b></b></b>
  <b class="yellowBox2"><b></b></b>
  <b class="yellowBox3"></b>
  <b class="yellowBox4"></b>
  <b class="yellowBox5"></b></b>

  <div class="yellowBoxfg">
    <div class="yellowBoxHead">The Philosophy</div>
  </div>
  
  <div class="text-yellowBox" style="background-color:#FBF5F7">
    <img src="<?php echo getThemeImage('icon-earth.jpg');?>" alt="Virtual Lab" width="49" height="59" align="left" />A virtual laboratory is a tool for distance learning and/or experimentation that allows people to share knowledge, data, voice, video, tools, and many other resources.  It provides a suitable environment to  extend, improve, integrate, refine, and assist the learning and/or experimentation process of many subjects, thus contributing to an increase of the effectiveness of scientific research and widening the use of scarce or costly equipments. <br/><a href="#">More>></a> <br/></div>
    

  
</div>
            
<br/>

<div>
  <b class="yellowBox">
  <b class="yellowBox1"><b></b></b>
  <b class="yellowBox2"><b></b></b>
  <b class="yellowBox3"></b>
  <b class="yellowBox4"></b>
  <b class="yellowBox5"></b></b>

  <div class="yellowBoxfg">
    <div class="yellowBoxHead">Salient Features</div>
  </div>
  
  <div class="text-yellowBox" style="background-color:#FBF5F7">
    <img src="<?php echo getThemeImage('icon-earth.jpg');?>" alt="Virtual Lab" width="49" height="59" align="left" />Amrita lab courses richly rely upon new up-to-date content and various techniques that require a new synergy of knowledge and experimental implementation. <br/><a href="#">More>></a> <br/></div>
    </div>
		    <td width="46">&nbsp;</td>
		    </tr>
		  </table></td>
	</tr>
</table>
</td>
	</tr>
</table>

</div>
<?php getFooter(); ?>
<!--<map name="Map" id="Map">
  <area shape="rect" coords="246,5,354,101" href="?sub=PHY&amp;brch=MPY&amp;sim=Solar-Panel-Experiment&amp;cnt=theory" target="_blank" alt="Remote Trigger" />
  <area shape="rect" coords="135,6,240,101" href="?sub=PHY&amp;brch=MPY&amp;sim=Soldering&amp;cnt=theory" target="_blank" alt="Haptics" />
</map>-->
