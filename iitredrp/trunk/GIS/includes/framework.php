<?php
/**
*	@author : Abhay Rana <captn3m0@gmail.com>
*	This is the framework called for some initialization work or the bootstrapper 
*/

/** Read the configuration details */
	require_once DISK_ROOT."/includes/config.php";

/** Load all the modules */
	foreach(glob(DISK_ROOT."/modules/*.php") as $module)
		require_once $module;
		
/** Initialize the Database System (Using RedBeans) */
	R::setup("mysql:host={$config['db_host']};dbname={$config['db_name']}",$config['db_user'],$config['db_pass']);
	
/** Other init stuff follows */

if(defined('DEBUG')){
	error_reporting(-1);
	ini_set('display_errors',1);
}

/**
 * The autoloading function
 * to initialize models, controllers, and views
 * automatically
 */
function __autoload($className){
	//echo "Autoloading $className<br>";
	list($type,$name)=explode("_",$className);
	switch($type){
		case 'Controller':
			$file = (DISK_ROOT."/controllers/$name.php");
			break;
		case 'View':
			$file = (DISK_ROOT."/views/$name.php");
			break;
		case 'Model':
			$file = (DISK_ROOT."/models/$name.php");
			break;
		default:
			throw new Exception("Class not found: $type");
	}
	if(is_readable($file))//if the class exists
		require_once($file);
}
/**
 * Returns the url of the required resource
 */
function url($control,$action,$param=null){
	return SITE_ROOT.'/'.$control.'/'.$action.'/'.$param;
}
/**
 * HTML Helper to generate links on the fly
 */
function link_to($control,$action,$param,$text,$attributes = array()){
	foreach($attributes as $attr=>$value)
		$attrString.=$attr.' = "'.$value.'" ';
	return '<a '.$attrString.'href="'.SITE_ROOT.$control.'/'.$action.'/'.$param.'">'.$text.'</a>';
}
