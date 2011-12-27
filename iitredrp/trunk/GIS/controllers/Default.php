<?php
/**
 * This is the default controller
 * and all others inherit this one
 */

class Controller_Default{
	var $loggedInUser;
	function __construct(){
		$this->method = $_SERVER['REQUEST_METHOD'];
		$this->session = Session::getInstance();
		if($this->session->userid)
			$this->loggedInUser = R::findOne('user','userid=?',array($this->session->userid));
		else
			$this->loggedInUser = false;
	}
	function render($view,$vars=null){
	  ob_start();
	  if(count($vars) > 0) { extract($vars); }
	  require DISK_ROOT.'/views/'.strtolower($view).'.php';
	  return ob_get_clean();
	}
	function requireLogin(){
		if(!$this->loggedInUser)
				throw new Exception('Invalid session');
	}
	
	/** 
	 * Caching mechanism
	 * Simple function that caches whatever you ask it to
	 * @file is the filename that resides in the cache directory
	 * @view is the view to be used
	 * @time can be either:
	 * 		true/false : true makes it re render the view
	 * 					 false would would'nt
	 * 		time	   : Maximum time difference to re render
	 * @params are the parameters to be passed to the view
	 * @returns True/false depending on the re-rendering 
	 */
	function cache($file,$view,$time,$param){
		$file = DISK_ROOT.'/cache/'.$file;
		if($time===true){
			$this->renderAndCache($file,$view,$param);
		}
		elseif(is_numeric($time)){
			if(time()-filemtime($file)>$time){
				$this->renderAndCache($file,$view,$param);
			}
		}
	}
	/**
	 * Private function to render a view
	 * and cache it in the given file
	 */
	private function renderAndCache($file,$view,$param){
		$contents = $this->render($view,$param);
		file_put_contents($file,$contents);
	}
	
	
}
