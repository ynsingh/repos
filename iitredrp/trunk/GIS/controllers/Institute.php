<?php
/**
*	@author : Abhay Rana <captn3m0@gmail.com>
*	This is the institute controller
*/
class Controller_Institute extends Controller_Default{
	/**
	 * This is the default action for the controller 
	 */
	function index(){
		//This should probably feature a list of instis
	}
	
	/**
	 * Registration action for institutes
	 */
	function register(){
		try{
			$this->requireLogin();
			//TODO: Check if user has already created an institute.
			//User is logged in, try to do some work
			switch($this->method){
				case 'GET':
					$content = $this->render('institute_registration');
					break;
				case 'POST':
					$institute = R::dispense('institute');
					$institute->import($_POST,'name,latitude,state,longitude,address,phone,year,url');
					$id = R::store($institute);
					/**
					 * Here insti_db is one table for data regarding the institute
					 * insti_meta contains meta information about the insti
					 * and user is associated with insti_meta
					 * See db schema for more details
					 */
					$status = R::dispense('status');//This contains status of the institute in our db
					$status->registration = true;
					$status->verification = false;
					$status->institute_id = $id;
					R::store($status);
					//Associate the things together :
					R::associate($institute,$this->loggedInUser);//associate institute with a person
					$content = $this->render("message",array('msg'=>'Your institute has been successfully registered. ','redirect'=>url('institute','edit',$id)));
					break;
			}
		}
		catch(exception $e){
			//If there was an error anywhere, go to the error page.
			$content = $this->render('error',array('exception'=>$e));	
		}
		$page = $this->render('default',array('content'=>$content));
		return $page;
	}
	/** 
	 * Edit an institute
	 * meaning add, remove courses etc
	 */
	function edit($id){
		//First some sanity tests
		try{
			if(!$id)
			{
				$institutes = R::related($this->loggedInUser,'institute');
				$content = $this->render('list_institutes',array('institutes'=>$institutes));
				$page =  $this->render('default',array('content'=>$content));
				return $page;
			}
			$this->requireLogin();//will throw exception
			$institute = R::load("institute",$id);
			$person = R::relatedOne($institute,'user');//find the user associated with this insti
			if($person->id != $this->loggedInUser->id)
				throw new Exception("You cannot edit this institute");
			//now we setup the method switch
			switch($this->method){
				case 'GET':				
					$courses = R::find('course','institute_id=?',array($id));
					$content = $this->render(
						'institute_edit',
						array(
							'institute'=>$institute,
							'courses'=>$courses
						)
					);
					$page = $this->render('default',array('content'=>$content));
					break;
				case 'POST':
					$course = R::dispense('course');//Course = degree + discipline + seats
					$course->degree = $_POST['degree'];
					$course->discipline = $_POST['discipline'];
					$course->discipline2 = $_POST['discipline2'];
					$course->seats = $_POST['seats'];
					$course->institute_id = $id;
					$course_id = R::store($course);
					
					//everything went well
					$message = $this->render("message",array('msg'=>'Added the course successfully ','redirect'=>url('institute','edit',$id)));
					$courses = R::find('course','institute_id=?',array($id));
					$content = $this->render(
						'institute_edit',
						array(
							'institute'=>$institute,
							'courses'=>$courses
						)
					);
					$page = $this->render('default',array('message'=>$message,'content'=>$content));
			}
		}
		catch(exception $e){
			//If there was an error anywhere, go to the error page
			$content = $this->render('error',array('exception'=>$e));	
		}
		
		return $page;		
	}
}
