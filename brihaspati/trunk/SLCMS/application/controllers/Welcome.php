<?php
defined('BASEPATH') OR exit('No direct script access allowed');
/**
 * @name Login.php
 * @author Nagendra Kumar Singh
 * @Modified by Manorama Pal(palseema30@gmail.com) 06june2017	
 */
class Welcome extends CI_Controller {

	/**
	 * Index Page for this controller.
	 *
	 * Maps to the following URL
	 * 		http://example.com/index.php/welcome
	 *	- or -
	 * 		http://example.com/index.php/welcome/index
	 *	- or -
	 * Since this controller is set as the default controller in
	 * config/routes.php, it's displayed at http://example.com/
	 *
	 * So any other public methods not prefixed with an underscore will
	 * map to /index.php/welcome/<method_name>
	 * @see https://codeigniter.com/user_guide/general/urls.html
	 */
	function __construct() {
        	parent::__construct();
        	$this->load->model("login_model", "login");
                $this->load->model("User_model", "usrmodel");
                $this->load->model("Common_model", "commodel");
        	//if(!empty($_SESSION['id_user'])){
                    
                //}
            	//	redirect('home');
    	}

	public function index() {
            if($_POST) {
          
                $result = $this->login->validate_user($_POST);
                /*get role by using model class and set templates according to role*/
                $roles=$this->commodel->get_listspficarry('user_role_type','roleid','userid',$result->id);
                if(!empty($result)) {
                
                    if(count($roles) == 1){
                        foreach($roles as $row):
                            if($row->roleid == 1){
                                $data = [
                                'id_user' => $result->id,
                                'username' => $result->username,
                                'id_role' => $row->roleid
                                ];
                                $this->session->set_userdata($data);
                                redirect('home');
                    
                            } 
                            if($row->roleid == 2){
                                $data = [
                                'id_user' => $result->id,
                                'username' => $result->username,
                                'id_role' => $row->roleid
                                ];
                                $this->session->set_userdata($data);
                                redirect('facultyhome'); 
                  
                            }
            
                        endforeach;   
                    } 
                    else{
                        foreach($roles as $row):
                            $data = [
                                'id_user' => $result->id,
                                'username' => $result->username,
                                                                    
                                ];
                            $this->session->set_userdata($data);
                            redirect('rolehome'); 
                        endforeach;
                        
                    }
                  
                }//ifempty close 
                else {
                    $this->session->set_flashdata('flash_data', 'Username or password is wrong!');
                    redirect('welcome');
                }
           
            }    
            $this->load->view("welcome_message");
        }
	
    }
