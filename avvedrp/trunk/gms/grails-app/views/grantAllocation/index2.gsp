<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<html lang="en">
    <head>
    <gui:resources components="['richEditor','dialog']"/>
   
    
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <meta http-equiv="Content-Script-Type" content="text/javascript">
            
      
          	 <script type="text/javascript">
<!--
function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
</script>
<!--Make sure page contains valid doctype at the very top!-->




<script type="text/javascript" src="../images/jquery-1.2.3.pack.js"></script>

<script type="text/javascript" src="../images/stepcarousel.js">

/***********************************************
* Step Carousel Viewer script- (c) Dynamic Drive DHTML code library (www.dynamicdrive.com)
* Visit http://www.dynamicDrive.com for hundreds of DHTML scripts
* This notice must stay intact for legal use
***********************************************/

</script>

<style type="text/css">

.stepcarousel{
margin: auto;
position: relative; /*leave this value alone*/
border: 0px none ;
overflow: scroll; /*leave this value alone*/
width: 470px; /*Width of Carousel Viewer itself*/
height: 120px; /*Height should enough to fit largest content's height*/
}

.stepcarousel .belt{
position: absolute; /*leave this value alone*/
left: 0;
top: 0;
}

.stepcarousel .panel{
float: left; /*leave this value alone*/
overflow: hidden; /*clip content that go outside dimensions of holding panel DIV*/
margin: 5px 20px 20px 20px; /*margin around each panel*/
width: 210px; /*Width of each panel holding each content. If removed, widths should be individually defined on each content DIV then. */
}

</style>



<script type="text/javascript">

stepcarousel.setup({
	galleryid: 'mygallery', //id of carousel DIV
	beltclass: 'belt', //class of inner "belt" DIV containing all the panel DIVs
	panelclass: 'panel', //class of panel DIVs each holding content
	autostep: {enable:true, moveby:1, pause:3000},
	panelbehavior: {speed:500, wraparound:false, persist:true},
	defaultbuttons: {enable: true, moveby: 1, leftnav: ['../images/arrowl.png', -5, 50], rightnav: ['../images/arrowr.png', -20, 50]},
	statusvars: ['statusA', 'statusB', 'statusC'], //register 3 variables that contain current panel (start), current panel (last), and total panels
	contenttype: ['inline'] //content setting ['inline'] or ['ajax', 'path_to_external_file']
})

</script>


    
         
		 



       

       
<style>
/*pgr*/

dl, dt, dd{margin:0;padding:0;}

dd{
	width:116px;
	height:26px;
	background:url(../images/bg_bar.gif) no-repeat 0 0;
	position:relative;
}
dd span{
	position:absolute;
	display:block;
	width:100px;
	height:10px;
	background:url(../images/bar_O.gif) no-repeat 0 0;
	top:8px;
	left:8px;
	overflow:hidden;
	text-indent:-8000px;
}


dd em{
	position:absolute;
	display:block;
	width:100px;
	height:10px;
	background:url(../images/bg_cover.gif) repeat-x;
	
}



dl2, dt2, dd2{margin:0;padding:0;}

dd2{
	width:116px;
	height:26px;
	background:url(../images/bg_bar.gif) no-repeat 0 0;
	position:absolute;
}

dd2 span{
	position:absolute;
	display:block;
	width:100px;
	height:10px;
	background:url(../images/bar.gif) no-repeat 0 0;
	top:8px;
	left:8px;
	overflow:hidden;
	text-indent:-8000px;
}


dd2 em{
	position:absolute;
	display:block;
	width:100px;
	height:10px;
	background:url(../images/bg_cover.gif) repeat-x;
	top:0;
}

.pbar {

margin-top:3px;
 border: 0;
 margin-left:10px;
}

.pbarlink  {
    color: #a9021a;
    font-weight: bold;
    text-decoration: none;
        letter-spacing: 1px;

}

.pbarlink a {
    color: #a9021a;
    font-weight: bold;
    text-decoration: none;
    letter-spacing: 1px;
} 


.pbarlink  a:hover {
    color: #a9021a;
    font-weight: bold;
    text-decoration: none;
        letter-spacing: 1px;

} 
/*pgr*/
body {
text-align: center;
font-family:Arial, Helvetica, sans-serif;
font-size: 12px;
background-color: #b9dcef;
}

/* Login Bar Start */
.loginLink{
	margin: 0 auto; 
	padding-top: 17px;
	
	width:847px;
	height:30px;
	text-align:right;
	}
.loginLink span { 
  text-align:right;
  padding-right: 25px;
  }
	
.loginLink span A:link { color:#01518e;  text-decoration: none}
.loginLink span A:visited { color:#01518e; text-decoration: none}
.loginLink span A:active { color:#01518e;  text-decoration: none}
.loginLink span A:hover { color:#01518e;  text-decoration: none}
/* Login Bar Start */

/* Banner Start */
.banner {
	margin: 0 auto; 
	width:847px;
	height:158px;
	background-color: #b9dcef;
	background-image: url(../images/themesky/indexnew_02.jpg);
	background-repeat: no-repeat;  
	}
/* Banner end */

/* dashboard Start */
.dashboard {
	margin: 0 auto; 
	width:847px;
	height: 220px;
	background-color: #b9dcef;
	background-image: url(../images/themesky/indexnew_03.jpg);
	background-repeat: no-repeat;  
	}
/* dashboard end */

/* dashboardBar Start */
.dashboardBar {
	margin: 0 auto; 
	width:847px;
	height:48px;
	text-align: right;
	background-color: #b9dcef;
	background-image: url(../images/themesky/indexnew_04.jpg);
	background-repeat: no-repeat;  
	}
.dashboardBar span { 
    position: relative;
	float: right;
	top: 10px;
   	right: 25px;
	color:#01518e;
	font-weight:bold; 

  }
  .dashboardBar span a { 
    
	
	color:#01518e;
	font-weight:bold; 

  }
/* dashboardBar end */

/* progressBar Start */	
.progressBar {
	margin: auto; 
	width: 847px;
	height: 150px;
	text-align: center;
	background-color: #b9dcef;
	background-image: url(../images/themesky/indexnew_05.jpg);
	background-repeat: no-repeat;  
	}
/* progressBar end */	


/* dashboard icon listing  */

.dash ul {
  float: left;
  width: 500px;
  margin: 15px;
  padding: 0;
  list-style: none;
}
 
.dash li{
  float: left;
  width: 120px;
  margin: 0px;
  padding: 2px;
 
} 


a:link, img  a:visited,  a:hover {
    border:0px;
    text-decoration: none;
   
}
a img{ 
border:0; 
}

</style>
</head>
  
  
  
<title>Grant Management System </title>   
<body onload=MM_preloadImages('../images/themesky/projectsOver.png','../images/themesky/institutionOver.png','../images/themesky/grantAgencyOver.png','../images/themesky/accountHeadOver.png','../images/themesky/grantPeriodOver.png','../images/themesky/fundAllocOver.png','../images/themesky/usrManagemntOver.png') >
 
<!-- Banner Start-->
<div class="banner">
<div class="loginLink">
 	<span>
 	<a href="../user/changePassword"><img src="../images/themesky/key.gif" alt="Change Password"/>&nbsp;&nbsp;|</a>&nbsp;&nbsp; <a href="../UserDoc/GMS_DOC_Revised.html" target="_blank" ><img src="../images/themesky/help.gif" alt="Help" />&nbsp;&nbsp;|</a>&nbsp;&nbsp;
 	<font face="verdana" color:#01518e; font-weight:bold; text-decoration: none>
    	<g:isLoggedIn>
    	<b><g:loggedInUsername/></b> (<g:link  controller='logout'>Logout</g:link>)
    	</g:isLoggedIn></font>
    	</span>
	</div>
</div>
<!-- Banner end-->    
<!-- dashboard Start-->
	<div class="dashboard">
	
	
<div class="dash">
<ul>
<li><a href="../projects/list" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image1','','../images/themesky/projectsOver.png',1)"><img src="../images/themesky/projects.png" name="Image1" width="117" height="84" border="0" id="Image1" /></a></li>
<li><a href="../party/list" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image2','','../images/themesky//institutionOver.png',1)"><img src="../images/themesky/institution.png" name="Image2" width="117" height="84" border="0" id="Image2" /></a></li>
<li><a href="../partyGrantAgency/list" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image3','','../images/themesky/grantAgencyOver.png',1)"><img src="../images/themesky/grantAgency.png" name="Image3" width="117" height="84" border="0" id="Image3" /></a></li>
<li><a href="../accountHeads/list" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image4','','../images/themesky/accountHeadOver.png',1)"><img src="../images/themesky/accountHead.png" name="Image4" width="117" height="84" border="0" id="Image4" /></a></li>
<li><a href="../grantPeriod/list" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image5','','../images/themesky/grantPeriodOver.png',1)"><img src="../images/themesky/grantPeriod.png" name="Image5" width="117" height="84" border="0" id="Image5" /></a></li>
<li><a href="../grantAllocation/fundAllot.gsp" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image6','','../images/themesky/fundAllocOver.png',1)"><img src="../images/themesky/fundAlloc.png" name="Image6" width="117" height="84" border="0" id="Image6" /></a></li>
<li><a href="../user/list" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image7','','../images/themesky/usrManagemntOver.png',1)"><img src="../images/themesky/usrManagemnt.png" name="Image7" width="117" height="84" border="0" id="Image7" /></a></li>
</ul>
</div>
	
	
	
	
	</div>
<!-- dashboard end-->

<!-- progressBar Start-->
	<div class="progressBar">
			
	<table width="98%" class="pbar">
	 <tr>
	 <td>
	  <div id="mygallery" class="stepcarousel">
<div class="belt">





	<g:each in="${grantAllocationInstanceList}" status="i" var="grantAllocationInstance">
                       
       
        <%
        
    
   	def sumAmount = GrantExpense.executeQuery("select sum(GE.expenseAmount) as SumAmt from GrantExpense GE where GE.grantAllocation.id ="+ grantAllocationInstance.id) 
      def expAmt=0;
      if(sumAmount[0]==null)
            expAmt=0
            else
            expAmt=sumAmount[0]
            def fund=Math.round((grantAllocationInstance.amountAllocated-expAmt)*100/grantAllocationInstance.amountAllocated)
            
               def time=0;
               if((grantAllocationInstance.projects.projectEndDate.getTime()-grantAllocationInstance.projects.projectStartDate.getTime())>0)
              time=Math.round((grantAllocationInstance.projects.projectEndDate.getTime()-new Date().getTime())/(grantAllocationInstance.projects.projectEndDate.getTime()-grantAllocationInstance.projects.projectStartDate.getTime())*100)
             
             
             if(time<0)
             time=0
             if(fund<0)
             fund=0
               
             
    %>      
  
         
     <div class="panel">

                 
              
              <table width="50%" class="pbar" cellspacing="0" cellpadding="0">
           
  <tr>
     <td>&nbsp;</td>
     
    <td width="14%"><img src="../images/themesky/information.png" />&nbsp;&nbsp;<g:link action="projectDash" id="${grantAllocationInstance.id}"  class="pbarlink" >${grantAllocationInstance.projects.code}</g:link> </td>
    <td width="86%"> </td>
  </tr>
  <tr>
     <td>&nbsp;</td>
    <td>
    Fund remaining
	<dl>
		
		<dd>
			
			
		<span><em style="left:${fund}px">${fund}</em></span>
		</dd>

	</dl></td>
    <td>${fund}%</td>
  </tr>
  <tr>
     <td>&nbsp;</td>
    <td> 
    
Time remaining 
    <dl>  
		
		<dd>
		
			<span><em style="left:${time}px">${time}</em></span>
		</dd>
		
      </dl> </td>
    <td>${time}%</td>
  </tr>
</table>

</div>
</g:each>

</div>
</div>
 </table>

 
	</div>

<!-- progressBar  end-->

    </body>
</html>