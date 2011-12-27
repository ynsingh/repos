<?php
/**
 * This is the default home controller
 */
class Controller_Home extends Controller_Default{
	function index(){
		$content = $this->render('home');
		return $this->render('default',array('content'=>$content));
	}
}
