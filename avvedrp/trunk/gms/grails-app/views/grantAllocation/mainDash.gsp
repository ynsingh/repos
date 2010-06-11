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
width: 670px; /*Width of Carousel Viewer itself*/
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

	<script src="../images/themesky/jquery.tools.min.js"></script> 

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
	float:left;
	margin-top:3px;
 	border: 0;
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
.dashboardbg {
	margin: 0 auto; 
	width:847px;
	background-color: #b9dcef;
	background-image: url(../images/themesky/indexnew_03_bg.jpg);
	background-repeat: repeat-y;  
	}
.dashboard {
	margin: 0 auto; 
	width:847px;
	height: 300px;
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


div.overlay {
	
	/* growing background image */
	background-image:url(../images/themesky/white.png);
	
	/* dimensions after the growing animation finishes  */
	width:430px;
	height:420px;		

	/* initially overlay is hidden */
	display:none;
	/* some padding to layout nested elements nicely  */
	 
	padding:55px;
	 
}

/* default close button positioned on upper right corner */
div.overlay div.close {
	background-image:url(../images/themesky/close.png);
	position:absolute;
	right:25px;
	top:30px;
	cursor:pointer;
	height:35px;
	width:35px;
}
 
 
  
/* use a semi-transparent image for the overlay */
#overlay {
	background-image:url(../images/themesky/transparent.png);
	color:#333333;
	height:430px;
}
 
/* container for external content. uses vertical scrollbar, if needed */
div.contentWrap {
	height:450px;
	overflow-y:auto;
}
 
 
div.intro {
	
    font-family:Verdana;
	font-size:11px;
	width:400px;
	line-height:20px;
	height:190px;
	color:#333333;	
	padding:5px;
		
}

div.info {
	
    font-family:Verdana;
	font-size:11px;
	width:400px;
	line-height:25px;
	height:190px;
	color:#333333;
	padding:5px;				
} 
</style>
</head>
  
  
  
<title>Grant Management System </title>   
<body onload=MM_preloadImages('../images/themesky/projectsOver.png','../images/themesky/institutionOver.png','../images/themesky/grantAgencyOver.png','../images/themesky/accountHeadOver.png','../images/themesky/grantPeriodOver.png','../images/themesky/fundAllocOver.png','../images/themesky/usrManagemntOver.png','../images/themesky/notificationover.jpg') >
 
<!-- Banner Start-->
<div class="banner">
<div class="loginLink">
 	<span>
 	<a href="../user/changePassword"><img src="../images/themesky/key.gif"  title="Change Password" alt="Change Password"/>&nbsp;&nbsp;|</a>&nbsp;&nbsp; <a href="${createLinkTo(dir:'UserDoc/GMS_DOC.html')}" target="_blank"><img src="${createLinkTo(dir:'images/themesky',file:'help.gif')}" alt="Help" title="Help"/>&nbsp;&nbsp;|</a>&nbsp;&nbsp;<a href="#"><img src="../images/themesky/aboutUs.jpg" title="About Us" alt="About Us"/>&nbsp;&nbsp;|</a>
 	<font face="verdana" color:#01518e; font-weight:bold; text-decoration: none>
    	<g:isLoggedIn>
    	<b><g:loggedInUsername/></b> (<g:link  controller='logout'>Logout</g:link>)
    	</g:isLoggedIn></font>
    	</span>
	</div>
</div>
<!-- Banner end-->    
<!-- dashboard Start-->
<div class="dashboardbg">
	<div class="dashboard">
	
	
<div class="dash">
<ul>
<li><a href="../projects/list" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image1','','../images/themesky/projectsOver.png',1)"><img src="../images/themesky/projects.png" name="Image1" width="117" height="84" border="0" id="Image1" /></a></li>
<li><a href="../notification/list" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image8','','../images/themesky/notificationover.png',1)"><img src="../images/themesky/notification.png" name="Image8" width="117" height="84" border="0" id="Image8" /></a></li>
<li><a href="../notificationsEmails/partyNotificationsList" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image9','','../images/themesky/proposalover.png',1)"><img src="../images/themesky/proposal.png" name="Image9" width="117" height="84" border="0" id="Image9" /></a></li>
<li><a href="../party/list" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image2','','../images/themesky/institutionOver.png',1)"><img src="../images/themesky/institution.png" name="Image2" width="117" height="84" border="0" id="Image2" /></a></li>
<li><a href="../partyGrantAgency/list" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image3','','../images/themesky/grantAgencyOver.png',1)"><img src="../images/themesky/grantAgency.png" name="Image3" width="117" height="84" border="0" id="Image3" /></a></li>
<li><a href="../accountHeads/list" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image4','','../images/themesky/accountHeadOver.png',1)"><img src="../images/themesky/accountHead.png" name="Image4" width="117" height="84" border="0" id="Image4" /></a></li>
<li><a href="../grantPeriod/list" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image5','','../images/themesky/grantPeriodOver.png',1)"><img src="../images/themesky/grantPeriod.png" name="Image5" width="117" height="84" border="0" id="Image5" /></a></li>
<li><a href="../grantAllocation/fundAllot.gsp" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image6','','../images/themesky/fundAllocOver.png',1)"><img src="../images/themesky/fundAlloc.png" name="Image6" width="117" height="84" border="0" id="Image6" /></a></li>
<li><a href="../user/list" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image7','','../images/themesky/usrManagemntOver.png',1)"><img src="../images/themesky/usrManagemnt.png" name="Image7"  width="117" height="84" border="0" id="Image7" /></a></li>
<li><a href="../grantAllocation/grantReports.gsp" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('Image10','','../images/themesky/reportsOver.png',1)"><img src="../images/themesky/reports.png" name="Image10" width="117" height="84" border="0" id="Image10" /></a></li>
</ul>
</div>
	
	
	
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
    <!-- 
    	Fund Utilization in % -> Here the Utilization is calculated in the basis of the Recieved grant.
    	1.If the expense amount is less than Recieved Grant Amount,
    	  Fund Utilization in % will be 
    		(expense amount/Recieved Grant Amount)*100
    	2.If the expense amount is grater than Recieved Grant Amount, 
    	  Fund Utilization will be set as 100%
    	3.The Expense amount can be entered before Receiving the Grant amount,
    	  then the Fund Utilization will be set as 0%
    	4.The Expense amount can be entered before the funt is allocated,
    	  then the Fund Utilization will be set as 0%
    -->   
	<%
	    def sumAmount = GrantExpense.executeQuery("select sum(GE.expenseAmount) as SumAmt from GrantExpense GE where GE.grantAllocation.projects.id ="+ grantAllocationInstance.projects.id) 
     	def allocatedAmt = GrantReceipt.executeQuery("select sum(GR.amount) as total from GrantReceipt GR where GR.projects= "+grantAllocationInstance.projects.id+" group by GR.projects");
		def fundAllocated = GrantAllocation.executeQuery("select sum(GA.amountAllocated) as amt from GrantAllocation GA where GA.projects.id ="+ grantAllocationInstance.projects.id)
		def fund=0;
		if(fundAllocated[0].intValue()>0)
		{
	      	if(sumAmount[0] < allocatedAmt[0])
	      	{
	      		def expAmt=0;
	      		if(sumAmount[0]==null)
	            	expAmt=0
	            else
	            	expAmt=sumAmount[0]
	        	if(allocatedAmt[0]==null)
					fund=0
				else
	            	fund=Math.round((expAmt)*100/allocatedAmt[0])
			}
			else if(sumAmount[0] > allocatedAmt[0])
			{
				if(allocatedAmt[0] == null)
					fund = 0
				else
	            	fund = 100
			}
		}
		else
		{
			fund = 0
		}
            
		def time=0;
		if((grantAllocationInstance.projects.projectEndDate.getTime()-grantAllocationInstance.projects.projectStartDate.getTime())>0)
		{
			if(grantAllocationInstance.projects.projectStartDate.getTime() <= new Date().getTime())
			{
				time=Math.round(((grantAllocationInstance.projects.projectEndDate.getTime()-new Date().getTime())/(grantAllocationInstance.projects.projectEndDate.getTime()-grantAllocationInstance.projects.projectStartDate.getTime()))*100)
			}
			else
			{
				time=Math.round(((grantAllocationInstance.projects.projectEndDate.getTime()-grantAllocationInstance.projects.projectStartDate.getTime())/(grantAllocationInstance.projects.projectEndDate.getTime()-grantAllocationInstance.projects.projectStartDate.getTime()))*100)
			}
		}
		if(time<0)
			time=0
		if(fund<0)
			fund=0
    %>      
  
         
     <div class="panel">

                 
              
              <table width="50%" class="pbar" cellspacing="0" cellpadding="0">
           
  <tr>
     <td>&nbsp;</td>
     
    <td width="14%"><img src="../images/themesky/information.png" />&nbsp;&nbsp;<g:link action="projectDash" id="${grantAllocationInstance.projects.id}"  class="pbarlink" >${grantAllocationInstance.projects.code}</g:link> </td>
    <td width="20%"> </td>
  </tr>
  <tr>
     <td>&nbsp;</td>
    <td>
    Fund Utilized
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
<div class="overlay" id="overlay"> 
	<!-- the external content is loaded inside this tag --> 
	<div class="contentWrap">
	
	
		<div class="intro" align="justify">
		   <strong>MSMGMS</strong>  has theflexibility to track grants against projects across  multiple sites,multiple users.
			It has been specifically designed to meet the requirements of government / research grants in 
			universities,and government organizations.
			It assists grant administrators with the arduous task of tracking project and grant-related
			expenditures in a heirarchial setup.
			In MS-GMS, Independent site admins can setup and manage each institute.
			- Each institute  will have  a site admin to create  users / projects
		  
		</div>
		
		<div class="info" align="justify" >
		
		<strong>Projects </strong>: Self Grant-- An Institute can create projects of its own and allocate grants to it.<br>
		
		<strong>Grantee </strong>--  An Institute may be allocated a project by another Institute / Grant Agency and revieve grants.  <br>
		
		<strong>Grantor</strong> --  An Institute can sub-allocate a Self Created Project or a Grantee Project in part or full to other Institutes or to itself. <br>
		
		<strong>Institution</strong> :  Has the details of the Institution. <br>
		
		<strong>Grant Agency</strong> : An Institution can create Grant Agencies from which it can recieve grants and assign projects against them when funds are allocated. <br>
		
		<strong>Account Head</strong> : Facility to divide grants against Account Heads and Sub-Account Heads.<br>
		
		<strong>Grant Period</strong> : Ability to create different Grant Periods - in days, months, years .<br>
		<strong>Fund Allocation</strong> : Grant amount allocated against each project can be captured along with the Grant Agency.<br>
		
		<strong>User Management</strong> : Only the Site-Admin can create new Users. A User is assigned to Project /Institute  for aunthentication / access to  projects.
		</div>
	
	
	
	
	
	
	
	 </div> 
</div> 
<!-- make all links with the 'rel' attribute open overlays --> 
<script> 
 
$(function() {
 
	// if the function argument is given to overlay,
	// it is assumed to be the onBeforeLoad event listener
	$("a[rel]").overlay({
 		left: 472,
		top:50,
		effect: 'apple',
 		closeOnClick: false,
		onBeforeLoad: function() {

			// grab wrapper element inside content
			var wrap = this.getContent().find(".contentWrap");

			// load the page specified in the trigger
			
		}

		
  	});
});
</script> 

    </body>
</html>