<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 
<html>
 <head>
        <title>Welcome to GMS</title>


      <meta name="layout" content="main" />

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Untitled Document</title>
	<script type="text/javascript" src="images/jquery-1.3.2.min.js"></script>
	

	<script type="text/javascript" src="images/jquery.pngFix.pack.js"></script>
	
<script type="text/javascript"> 
    $(document).ready(function(){ 
        $(document).pngFix(); 
    }); 
</script>

<script type="text/JavaScript">



<!--
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
</script>
</head>	
<body  onload="MM_preloadImages('images/projectsOver.png','images/InstitutionOver.png','images/accountHeadOver.png','images/grantAgencyOver.png','images/grantPeriodOver.png','images/fundAllocOver.png','images/usrMngmtOver.png','images/reportsOver.png')" >
<table width="960" border="0"  cellspacing="0" cellpadding="0">
  <tr>
    <td width="2%">&nbsp;</td>
    <td width="97%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
  </tr>
  <tr>
    <td height="104">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr  >
    <td height="266">&nbsp;</td>
     <td width="100%" background="images/menuBG.png" style="background-repeat:no-repeat" >
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr valign="top">
        <td width="18%" height="235"><div align="center">
		<g:link url="[action:'list',controller:'projects']">
		<img src="images/projects.png" 
		 onmouseover="this.src='images/projectsOver.png'" onMouseOut="this.src='images/projects.png'" 
		name="project" width="90" height="219" border="0" id="project" /></g:link> 
		</div></td>
        <td width="14%"><a href="party/list" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('instituion','','images/InstitutionOver.png',1)"><img src="images/Institution.png" name="instituion" width="90" height="219" border="0" id="instituion" /></a> </td>
        <td width="14%"><a href="partyGrantAgency/list" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('projAlloc','','images/grantAgencyOver.png',1)"><img src="images/grantAgency.png" name="projAlloc" width="90" height="219" border="0" id="projAlloc" /></a></td>
        <td width="12%"><a href="accountHeads/list" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('accounts','','images/accountHeadOver.png',1)"><img src="images/accountHead.png" name="accounts" width="90" height="219" border="0" id="accounts" /></a></td>
        
        <td width="13%"><a href="grantPeriod/list" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('projMngmt','','images/grantPeriodOver.png',1)"><img src="images/grantPeriod.png" name="projMngmt" width="90" height="219" border="0" id="projMngmt" /></a></td>
        <td width="12%"><a href="grantAllocation/fundAllot.gsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('exp','','images/fundAllocOver.png',1)"><img src="images/fundAlloc.png" name="exp" width="90" height="219" border="0" id="exp" /></a></td>
        <td width="17%"><a href="user/list" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('usrMngmt','','images/usrMngmtOver.png',1)"><img src="images/usrMngmt.png" name="usrMngmt" width="90" height="219" border="0" id="usrMngmt" /></a></td>
       
      </tr>
    </table>	</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</body>
</html>
