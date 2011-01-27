<?php
/**
   * This file contains the logic for applying new theme, theme Preview 
   * and incase of no theme selected, apply the default theme defined in the config file
   * @author Mukesh Kumar M
   * @version 13-09-2010
*/

//echo "theme:".getCurrentTheme();
/* if the new theme has been applied*/
//echo "theme:".getCurrentTheme();
if($_POST['btnApply'])// if pressing Apply 
{
	if($_POST['hdTheme'])
	{
		$_SESSION['sess_current_theme']=$_POST['hdTheme']; //replace the old theme with new theme
		setTheme($_POST['hdTheme']);
	}
	$_SESSION['sess_original_theme']=""; //clear the temperory session variable which keeps old theme
}
//
/* this is to preview a theme*/
if($_POST['btnPreview'])//in case of preview of theme
{	
	if($_SESSION['sess_original_theme']=="")
	{
		$_SESSION['sess_original_theme']=$_SESSION['sess_current_theme'];
	}
	$_SESSION['sess_current_theme']=$_POST['hdTheme'];
}
/*	if it is not a preview case 
	and old theme is not null
	rewrite the currrent theme with the old one
*/
else if ($_SESSION['sess_original_theme']!="")
{
	$_SESSION['sess_current_theme']=$_SESSION['sess_original_theme'];
	$_SESSION['sess_original_theme']="";
}
//
/*if ($_SERVER['HTTP_HOST'] == 'amrita.amritalearning.org')
{
	$_SESSION['sess_current_theme']	='amrita';
}
else if ($_SERVER['HTTP_HOST'] == 'iitb.amritalearning.org')
{
	$_SESSION['sess_current_theme']	='iitb';
}
else if ($_SERVER['HTTP_HOST'] == 'iitd.amritalearning.org')
{
	$_SESSION['sess_current_theme']	='iitd';
}*/
//$_SESSION['sess_current_theme']	='iitd';
/* after all, if there is no current theme
	apply the DEFAULT_THEME
*/
if($_SESSION['sess_current_theme']=='')
{
	$_SESSION['sess_current_theme']=getCurrentTheme();
	if(!$_SESSION['sess_current_theme'])
		$_SESSION['sess_current_theme']=DEFAULT_THEME;
}
?>