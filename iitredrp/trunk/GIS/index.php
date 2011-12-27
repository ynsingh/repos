<?php
/**
*	@author : Abhay Rana <captn3m0@gmail.com>
*	The main index file which calls the Master Controller
*/

//Need to define the dir root so that it can be used ahead
if ( !defined('__DIR__') ) define('__DIR__', dirname(__FILE__));
define('DISK_ROOT',__DIR__);
//Load up the database and other init stuff
require_once DISK_ROOT."/includes/framework.php";


//Fire up the master controller
require DISK_ROOT."/includes/controller.php";

//Ideally, this should do nothing, but in case their were any errors
//Or something wrong happened, we do have a fallback
require_once DISK_ROOT."/includes/cleanup.php";
