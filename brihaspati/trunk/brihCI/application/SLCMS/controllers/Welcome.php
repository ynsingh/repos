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
		$this->load->model("DateSem_model","datmodel");
        	//if(!empty($_SESSION['id_user'])){
                    
                //}
            	//	redirect('home');
    	}
	public function work_underprocess(){
		$this->load->view('template/work_under');
	}	
		
	 public function ginstruction()
	 {
	   $prg_id=$this->uri->segment(3);
	   $data['prg_id']=$prg_id;		
       	   $this->load->view('enterence/declaration',$data);
	 }


	public function welcome_login(){
		$cdate = date('Y-m-d');
        	$wharray = array('anou_cname=' => 'SLCMS', 'anou_publishdate<=' => $cdate,'anou_expdate>=' => $cdate);
        	$annoresult = $this->commodel->get_listarry('announcement','*',$wharray);
		$data['annoresult'] = $annoresult;
		$scrlist = $this->commodel->get_listmore('study_center','sc_id');
		$data['scrlist'] = $scrlist;
		//print_r($scrlist);
		//$field=array('prg_category');
		//foreach($scrlist as $row){
		//	$scid = $row->sc_id;
		//	$wharray = array('prg_scid'  =>  $scid);
		//	$prgcat = $this->commodel->get_distinctrecord('program','prg_category',$wharray);
			//print_r($prgcat);
			//$data['prgcat'] = $prgcat;
		//}
		$this->load->view('welcome_message',$data);	
	}

	public function welcome_form(){
		$this->scresult = $this->commodel->get_list('study_center');
		
		$acadyear = $this->usrmodel->getcurrentAcadYear();
		$cdate = date('Y-m-d H:i:s');
		$field=array('prgcat_id','prgcat_name');
		$this->prgcat = $this->commodel->get_listmore('programcategory',$field);
		$wharray = array('anou_cname=' => 'SLCMS', 'anou_publishdate<=' => $cdate,'anou_expdate>=' => $cdate);
        	$annoresult = $this->commodel->get_listarry('announcement','*',$wharray);
		$data['annoresult'] = $annoresult;

		$this->load->view('welcome_form',$data);
	}

	public function index() {
		
		$acadyear = $this->usrmodel->getcurrentAcadYear();
		$cdate = date('Y-m-d H:i:s');
		$field=array('prgcat_id','prgcat_name');
		$prgcat = $this->commodel->get_listmore('programcategory',$field);
		$data['prgcat'] = $prgcat;
		$cdate = date('Y-m-d');
        	$wharray = array('anou_cname=' => 'SLCMS', 'anou_publishdate<=' => $cdate,'anou_expdate>=' => $cdate);
        	$annoresult = $this->commodel->get_listarry('announcement','*',$wharray);
		$data['annoresult'] = $annoresult;
		//get certificate list in add_admission open
		
            if(isset($_POST['login'])) {
                $result = $this->login->validate_user($_POST);
		//print_r($result);die;
                /*get role by using model class and set templates according to role*/
                $roles=$this->commodel->get_listspficarry('user_role_type','roleid','userid',$result->id);
		//print_r($roles);die;
                if(!empty($result)) {
             		if(!empty($roles)){   
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
                            			if($row->roleid == 3){
			                                $data = [
                        			        'id_user' => $result->id,
			                                'username' => $result->username,
                        			        'id_role' => $row->roleid
			                                ];
                        			        $this->session->set_userdata($data);
			                                redirect('studenthome'); 
                        			}
                            			if($row->roleid == 4){
			                                $data = [
                        			        'id_user' => $result->id,
			                                'username' => $result->username,
                        			        'id_role' => $row->roleid
			                                ];
                        			        $this->session->set_userdata($data);
			                                redirect('staffhome'); 
                        			}
                            			if($row->roleid == 5){
			                                $data = [
                        			        'id_user' => $result->id,
			                                'username' => $result->username,
                        			        'id_role' => $row->roleid
			                                ];
                        			        $this->session->set_userdata($data);
			                                redirect('hodhome'); 
                        			}
                            			if($row->roleid == 6){
			                                $data = [
                        			        'id_user' => $result->id,
			                                'username' => $result->username,
                        			        'id_role' => $row->roleid
			                                ];
                        			        $this->session->set_userdata($data);
			                                redirect('coehome'); 
                        			}
                            			if($row->roleid == 7){
			                                $data = [
                        			        'id_user' => $result->id,
			                                'username' => $result->username,
                        			        'id_role' => $row->roleid
			                                ];
                        			        $this->session->set_userdata($data);
			                                redirect('accoffhome'); 
                        			}
                        		endforeach;   
                    		}else{
                        		foreach($roles as $row):
                            			$data = [
		                                'id_user' => $result->id,
                		                'username' => $result->username,                                    
                                		];
                            			$this->session->set_userdata($data);
                            			redirect('rolehome'); 
                        		endforeach;
                    		}
			}else{ //if close role empty
                    		$this->session->set_flashdata('err_message', 'You do not have any role in this system!');
                    		redirect('welcome');
            		}
                }//if empty result validate close 
                else {
                    $this->session->set_flashdata('err_message', 'Username or password is wrong!');
                    redirect('welcome');
                }
            }    
            $this->load->view("welcome_login",$data);
        }//close index function


    }//close class
