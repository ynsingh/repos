<?php
//$userType='AA';
//print_r($_SERVER);
/*******************************************/
$subLink="?sub=".$_GET['sub'];
$brchLink="?sub=".$_GET['sub']."&brch=".$_GET['brch'];
$simLink="?sub=".$_GET['sub']."&brch=".$_GET['brch']."&sim=".$_GET['sim'];
/*$cntLink="?sub=".$_GET['sub']."&brch=".$_GET['brch']."&sim=".$_GET['sim']."&cnt=".$_GET['cnt'];*/
if(isset($_GET['sub']))
{
	$subName=getSubName($_GET['sub']);
}
if(isset($_GET['brch']))
{
	$brchName=getTopicName($_GET['brch']);
	
}
if(isset($_GET['sim']))
{
	$simName=getExperimentName($_GET['sim']);
}
/******************************/
//
if($_GET['pg']!="")
{
	/*$_GET['pg']?$pg='/'.$_GET['pg']:$pg="";
	$_GET['sb']?$sb=$_GET['sb']:$sb="";
	$_GET['ssb']?$ssb='/'.$_GET['ssb']:$ssb="";
	if($_GET['sb']=="")
	{
		$sb='admin';
	}
	//echo "path:".$sb.$ssb.$pg.'.php';
	//include($sb.$ssb.$pg.'.php');
	//break;
	$pageName=$sb.$ssb.$pg;*/
	$pageName="";
	if(defined($_GET['pg']))
	{
		$pageName=constant($_GET['pg']);
	}
	else
	{
		$pageName=$_GET['pg'];
	}
	//$_GET['pg']!=""?$pageName=constant($_GET['pg']):$pageName="";
	//$pageName=constant($_GET['pg']);
	if($pageName=='Admin Home' )
	{
		if($_SESSION['sessUserRoleId']=="")
		{
			$breadCumDisplay="";
		}
		else
		{
		$breadCumDisplay=getUserRoleName($_SESSION['sessUserRoleId'])." ".HOME2;
		}
	}
	else
	{$breadCumDisplay=$pageName;}
	$arrItems=array('home',$breadCumDisplay);
	$arrLink=array('?pg=HOME','?pg='.$_GET['pg']);
}
else
{

	if((isset($_GET['sim'])|| $_GET['sim']!=''))
	{
		$pageName="st_experiment";
		//for bread cum
		$arrItems=array('home',$subName,$brchName,$simName);
		$arrLink=array('?',$subLink,$brchLink,$simLink);
		
	}
	else if((isset($_GET['brch'])|| $_GET['brch']!=''))
	{
		$pageName="lab";
		//for bread cum
		$arrItems=array('home',$subName,$brchName);
		$arrLink=array('?',$subLink,$brchLink);
	}
	else if((isset($_GET['sub'])|| $_GET['sub']!=''))
	{
		$pageName="topic";
		//for bread cum
		$arrItems=array('home',$subName);
		$arrLink=array('?',$subLink);
	}
	else
	{
		$pageName="home";
		//for bread cum
		$arrItems=array();
		$arrLink=array();
	}
}
//for bread cum
$breadcumStartText=BREAD_CUM_TEXT;
$seperator="->";
//
//
/*$arrItems=array();
$arrLink='';*/
//
if(isThemeFileExists($pageName))
{
	include(THEME_ROOT.$_SESSION['sess_current_theme'].'/'.$pageName.'.php');
}
else
{
	include(THEME_ROOT.$_SESSION['sess_current_theme'].'/index.php');
}

?>