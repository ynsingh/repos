<?php
/**
*	@author : Abhay Rana <captn3m0@gmail.com>
*	This is the institute controller
*/
class Controller_Query extends Controller_Default{
	/**
	 * This is the default action for the controller 
	 */
	function index(){
		//Display the query builder

		$content = $this->render('query_builder',array('states'=>$states));
		return $page = $this->render('default',array('content'=>$content));
	}
}
