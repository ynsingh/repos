<div align="center">
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
	if (preg_match($strangeChar , $resto)){
			@include("error.php");
	}else if ($sub!="" && $brch!="" && $sim!="" && $cnt!=""){
			$cur_path='vlab/'.$sub.'/'.$brch.'/'.$sim.'/';
			@include('vlab/'.$sub.'/'.$brch.'/'.$sim.'/'.$cnt.'.php');
	} else if ($sub!="" && $brch!="" && $sim!=""){
			$cnt='theory';
			$cur_path='vlab/'.$sub.'/'.$brch.'/';
			@include('vlab/'.$sub.'/'.$brch.'/'.$sim.'/theory.php');
		
	} else if ($sub!="" && $brch!=""){
		$cur_path='vlab/'.$sub.'/'.$brch.'/';
		@include('vlab/'.$sub.'/'.$brch.'/index.php');
		
	} else if ($sub!=""){
		$cur_path='vlab/'.$sub.'/';
		@include('vlab/'.$sub.'/index.php');
	} else {
	?>
    
<table height="606" border="0" align="center" cellpadding="0" cellspacing="0" id="main" style="background-color:#FFF">
	<tr>
		<td width="300" align="center" valign="top">
			<div style="padding-left:35px; padding-top:5px; padding-right:10px">
            
            <div class="menuText" align="left">Featured Simulation</div>
			<div class="slideshow" align="left">
				                
                <a href="?sub=BIOTECH&brch=NEO&sim=NEURONAL">
                <div><span class="featuredTitle">Neuronal Model</span></span><br />
                <img src="images/icon-neuron-model.jpg" /> 
                <p class="text-yellowBox"> The Hodgkin&ndash;Huxley model is a scientific model that describes how action potentials in neurons are initiated and propagated. It is a set of nonlinear ordinary differential equations that approximates the electrical characteristics of excitable cells such as neurons and cardiac myocytes. </p> 
               <img align="right" src="images/read-more.jpg" width="61" height="14" alt="Simulator" />
            </div>
            </a> 
            
             <a href="?sub=PHY&brch=BEC">
            <div><span class="featuredTitle">Electric Circuits</span></span><br />
                <img src="images/icon-dc-circut.jpg" width="250" height="111" alt="DC Circuit Simulation" />
                <p class="text-yellowBox"> It is a flash based simulation of DC electrical circuits. Users can build circuits of their choice using components like resistors, batteries, wires, switches and bulbs and they can take the voltage and current readings across any points using a voltmeter, ammeters and a non-contact ammeter.</p> 
                <img align="right" src="images/read-more.jpg" width="61" height="14" alt="Simulator" />
            </div> </a> 
            
            <a href="?sub=PHY&brch=CLA&sim=Torson-Pendulum&cnt=theory">
            <div><span class="featuredTitle">Torsion Pendulum</span></span><br />
               <img src="images/icon-torision.jpg" width="250" height="111" alt="Torsion Pendulum Simulation" />
                <p class="text-yellowBox"> The simulation virtualizes the torsion pendulum experiment .The user can view the effect of torsional oscillation in different experimental environment. Demonstration of Tensional oscillations of different objects such as Disc, Cylinder, Dumbbell.</p> 
                <img align="right" src="images/read-more.jpg" width="61" height="14" alt="Simulator" />
            </div> 
            </a>             
             <a href="?sub=BIOTECH&brch=NEO&sim=MEA&cnt=theory">
            <div><span class="featuredTitle">Measuring Field potentials using MEA chips</span></span><br />
               <img src="images/icon-mea_chips.jpg" width="250" height="111" alt="Simulation of Measuring Field potentials using MEA chips" />
                <p class="text-yellowBox">Multielectrode arrays (MEAs) or microelectrode arrays are devices that contain multiple plates or shanks through which neural signals are obtained or delivered, essentially serving as neural interfaces that connect neurons to electronic circuitry.</p> 
                <img align="right" src="images/read-more.jpg" width="61" height="14" alt="Simulator" />
            </div> 
            </a> 
            <a href="?sub=PHY&brch=MPY&sim=Millikan-Oil-Drop&cnt=theory">
            <div><span class="featuredTitle">Millikan's oil drop experiment</span></span><br />
               <img src="images/ico-millikans.jpg" width="250" height="111" alt="Millikan's oil drop experiment Simulation" />
                <p class="text-yellowBox"> Oil-drop experiment was the first direct and compelling measurement of the electric charge of a single electron. It was performed originally in 1909 by the American physicist Robert A. Millikan.</p> 
                <img align="right" src="images/read-more.jpg" width="61" height="14" alt="Simulator" />
            </div> 
            </a> 
             <a href="?sub=CHE&brch=ORG&sim=DetectionofelementsLassaignesTest&cnt=theory">
            <div><span class="featuredTitle">Lassaigne's Test</span></span><br />
            
            <img src="images/icon-lassaigne.jpg" width="250" height="111" alt="Detection of elements: Lassaigne's Test" />
            
        
                <p class="text-yellowBox"> It is a general test for the detection of halogens, nitrogen & sulphur in an organic compound. These elements are bonded covalently in the organic compounds. In order to detect them, these have to be converted into their ionic forms.</p> 
                <img align="right" src="images/read-more.jpg" width="61" height="14" alt="Simulator" />
            </div> 
             <a href="?sub=PHY&brch=MPY&sim=Emission-Spectra&cnt=theory">
            <div><span class="featuredTitle">Emission Spectra</span></span><br />
               <img src="images/icon-emissionspectra.jpg" width="250" height="111" alt="Emission-Spectra Simulation" />
                <p class="text-yellowBox"> A characteristic pattern of spectral lines, either absorption or emission, produced by the hydrogen atom. The various series of lines are named according to the lowest energy level involved in the transitions that give rise to the lines.Diffraction grating, an optical device is used to disperse light into a spectrum.</p> 
                <img align="right" src="images/read-more.jpg" width="61" height="14" alt="Simulator" />
                
            </div> 
            </a>
            <a href="?sub=PHY&brch=ACS&sim=Sonometer&cnt=simulator">
            <div><span class="featuredTitle">A.C Sonometer</span></span><br />
               <img src="images/icon-acsonometer.jpg" width="250" height="111" alt="A.C Sonometer Simulation" />
                <p class="text-yellowBox">Every object has a natural frequency of vibration. If kinetic energy is applied to an object at a rate that matches its natural frequency, resonance occurs and the object vibrates. In this experiment a small current, produced by a signal generator, causes the sonometer wire to move up and down due to interaction with the magnetic field of a U-shaped magnet.</p> 
                <img align="right" src="images/read-more.jpg" width="61" height="14" alt="Simulator" />
            </div> 
            </a> 
            </a> 
            
            
	</div>
            
       <div style="padding-top:270px">     
   
<img src="images/left-sidebar_02.jpg" alt="Developed at Amrita University" width="250" height="65" />
<img src="images/left-sidebar_03.jpg" width="250" height="65" alt="Our Inspiration" />

<div class="menuText" align="left"><br />
  Sponsors</div>
<div style="text-align:justify; padding-right:10px" class="text-yellowBox">
<img src="images/sponsors.jpg" width="63" height="96" alt="Sponsor of Virtual Lab" align="left" />This project is an initiative of Ministry of Human Resource Department under National Mission on Education through ICT. These experiments and labs will be hosted for open access through the main project website <a href="http://vlab.co.in" title="Link to Virtual Lab Project Website" target="_blank">http://vlab.co.in</a></div>
       </div>

         </div>
         </td>
		<td>
		  <table id="content-side" width="728" height="606" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="2">
			<img src="images/main-sidebar_01.jpg" width="728" height="228" alt=""></td>
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
                <table width="100%" border="0" cellspacing="8" cellpadding="0">
                <tr>
                <td>
                <div style="background-image: url(images/subjectBox.jpg); height: 100%; width: 100%;">
                <img align="left" style="padding-left:15px" src="images/icon-phy.jpg" width="53" height="52" alt="Physics Virtual Lab" /><div class="subjects"><a href="?sub=PHY" title="Physics Virtual Lab">Physical Sciences</a></div>
                <div class="subjects-small"><a href="?sub=PHY&amp;brch=CLA" title="Classical Mechanics Lab">Mechanics</a>, <a href="?sub=PHY&amp;brch=TDM" title="Thermodynamic Lab">Thermodynamics</a>, <a href="?sub=PHY&amp;brch=OPT" title="Optics Virtual Lab">Optics</a>, <a href="?sub=PHY&amp;brch=EMM" title="Electricity and Magnetism Lab">Electricity and Magnetism</a>, <a href="?sub=PHY&amp;brch=BEC" title="Basic Electric Circuits">Basic Electric Circuits</a>, <a href="?sub=PHY&amp;brch=MPY">Modern Physics</a> Virtual Labs...</div>
                
                </div></td>
                </tr>
                <tr>
                <td>
                
                <div style="background-image: url(images/subjectBox.jpg); height: 100%; width: 100%;">
                <img align="left" style="padding-left:15px" src="images/icon-che.jpg" width="53" height="52" alt="Chemistry Virtual Lab" />
                <div class="subjects"><a href="?sub=CHE" title="Chemistry Virtual Lab">Chemical Sciences</a></div>
                <div class="subjects-small"><a href="?sub=CHE&amp;brch=SOL" title="Physical Chemistry Virtual Lab">Physical Chemistry</a>, <a href="?sub=CHE&amp;brch=ORG" title="Organic Chemistry Virtual Lab">Organic Chemistry</a>, <a href="?sub=CHE&amp;brch=INC">Inorganic Chemistry</a>, Electrochemistry Virtual Labs...</div></div></td>
                </tr>
                <tr>
                <td> <div style="background-image: url(images/subjectBox.jpg); height: 100%; width: 100%;">
                <img align="left" style="padding-left:15px" src="images/icon-bio.jpg" width="53" height="52" alt="Biotechnology Virtual Lab" />
                <div class="subjects"><a href="?sub=BIOTECH" title="Biotechnology Virtual Labs">Biotechnology Labs</a></div>
                <div class="subjects-small"><a href="?sub=BIOTECH&amp;brch=NEO" title="Virtual Neurophysiology Labs">Neurophysiology</a>, Cell biology, Immunology Lab, Microbiology, Molecular Biology, <?php /*<a href="?sub=BIOTECH&amp;brch=POE"> */?>Population Ecology<?php /*</a> */ ?>,<?php /* <a href="?sub=BIOTECH&amp;brch=BIC"> */?>Biochemistry<?php /*</a>*/?> Virtual Labs...</div></div></td>
                </tr>
                 <tr>
                <td> <div style="background-image: url(images/subjectBox.jpg); height: 100%; width: 100%;">
                <img align="left" style="padding-left:15px" src="images/ico_cs.gif" width="53" height="52" alt="Biotechnology Virtual Lab" />
                <div class="subjects"><a href="?sub=COM" title="Computer Science">Computer Science</a></div>
                <div class="subjects-small">Intrusion Detection, Network Security, Wireless Sensor Networks Virtual Labs...<br/><br/></div></div></td>
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
        <img src="images/icons-all.jpg" width="480" border="0" usemap="#Map" align="right" /></div>
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
    <img src="images/icon-earth.jpg" alt="Virtual Lab" width="49" height="59" align="left" />A virtual laboratory is a tool for distance learning and/or experimentation that allows people to share knowledge, data, voice, video, tools, and many other resources.  It provides a suitable environment to  extend, improve, integrate, refine, and assist the learning and/or experimentation process of many subjects, thus contributing to an increase of the effectiveness of scientific research and widening the use of scarce or costly equipments. <br/><a href="#">More>></a> <br/></div>
    

  
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
    <img src="images/icon-earth.jpg" alt="Virtual Lab" width="49" height="59" align="left" />Amrita lab courses richly rely upon new up-to-date content and various techniques that require a new synergy of knowledge and experimental implementation. <br/><a href="#">More>></a> <br/></div>
    </div>
		    <td width="46">&nbsp;</td>
		    </tr>
		  </table></td>
	</tr>
</table>
</td>
	</tr>
</table>
	<?php
	}
	?>
</div>

<map name="Map" id="Map">
  <area shape="rect" coords="246,5,354,101" href="?sub=PHY&amp;brch=MPY&amp;sim=Solar-Panel-Experiment&amp;cnt=theory" target="_blank" alt="Remote Trigger" />
  <area shape="rect" coords="135,6,240,101" href="?sub=PHY&amp;brch=MPY&amp;sim=Soldering&amp;cnt=theory" target="_blank" alt="Haptics" />
</map>
