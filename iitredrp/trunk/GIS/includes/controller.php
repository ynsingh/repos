<?php
/**
*	@author : Abhay Rana <captn3m0@gmail.com>
*	This is the master controller which decides where to send the user
*/

//Parse the url
$var = explode('/',substr($_SERVER['REQUEST_URI'],strlen(SITE_ROOT)));
//url = http://site.com/folder/erp/index.php/controller/action/params
//REQUEST_URI = /folder/erp/index.php/controller/action/params
//var = ['',controller,action,params]
//var[0] is blank
$controller = isset($var[1])?(empty($var[1])?"home":$var[1]):"home";
$action = isset($var[2])?(empty($var[2])?"index":$var[2]):"index";
$params = array_slice($var,3);//parameters for the function

//Get the class name and instantiate it
$className = "Controller_".ucwords($controller);
$object = new $className();

try{
	$method = new ReflectionMethod($className, $action);
	$result = $method->invokeArgs($object,$params);
	echo $result;
}
catch(ReflectionException $r){
	echo "This has not yet been implemented";
}
