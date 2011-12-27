<?php
/**
 * This is the controller for the user
 */
require(DISK_ROOT.'/includes/openid/openid.php');
class Controller_User extends Controller_Default{
	function index($id=null){
		if($id){
			//show this user
		}
		else{
			//show all users
		}
	}
	function logout(){
		$this->session->destroy();
		$message = $this->render('message',array('msg'=>'You have been logged out','redirect'=>url('','')));//redirect to home page
		$page = $this->render("default",array('content'=>'','message'=>$message));
		return $page;
	}
	function login(){
		switch($this->method){
			case 'GET':
				if (isset($_GET['openid_mode'])) {
					//If we are returning from a auth URL
					$openid = new LightOpenID(SITE_DOMAIN);
					if(!$openid->validate()){
						$content = $this->render('error',array('exception'=>'OpenID authentication failed.'));
					}
					else{
						$user = R::findOne('user','userid=?',array($openid->identity));
						if($user){
							//Existing user
							//Login 
							$this->session->userid = $user->userid;
							$content = $this->render('message',array('msg'=>'You\'ve been logged in successfully','redirect'=>url('','')));
						}
						else{
							//Register this user
							$user = R::dispense('user');
							$user->userid=$openid->identity;
							$user->role='user';
							$this->session->userid = $user->userid;
							R::store($user);
						}
						$content = $this->render('message',array('msg'=>'You\'ve been logged in successfully','redirect'=>url('','')));
					}
					$page = $this->render("default",array('content'=>$content));
				}
				else{
					$form = $this->render('login');
					$page = $this->render("default",array('content'=>$form));
				}
				return $page;
				break;
			case 'POST':
				$openid = new LightOpenID(SITE_DOMAIN);
				$openid->identity = BRIHASPATI_OPEN_ID_URL.$_POST['userid'];
				//$openid->returnUrl = SITE_DOMAIN;
				//die(urldecode($openid->authUrl()));
				header('DD: ' . $openid->authUrl());
				header('Location: ' . $openid->authUrl());
		}
	}
	/**
	 * Now using Brihaspati
	 * so this is redundant
	 * but useful to understand RedBeans
	function register(){
		switch($this->method){
			case 'GET':			
				$form = $this->render('user_registration');
				$page = $this->render('default',array('content'=>$form));
				return $page;
				break;//redundant
			case 'POST':
				$user = R::dispense('user');
				try{
					//Import the data into the bean
					$user->import($_POST,'email,name,pass');
					//Some validation
					//We only check the passwords during registration:
					if($user->pass!=$_POST['pass2'])
						throw new Exception("Passwords do not match");
					//Registration specific stuff
					$user->salt = sha1(uniqid());//we need a salt
					$user->pass = sha1($user->salt.$user->pass);
					$user->role = 'user';//assign the default role only.
					R::store($user);//Save the user
					$content = "The user was successfully created. You can login now.";//give the user a message
				}
				catch(exception $e)
				{
					$content = $this->render('error',array('exception'=>$e));					
				}
				$page = $this->render('default',array('content'=>$content));
				return $page;
				break;
		}
	}
	*/
}
