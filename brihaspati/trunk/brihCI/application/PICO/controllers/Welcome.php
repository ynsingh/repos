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
	 */
	function __construct() {
        	parent::__construct();
        	$this->load->model("login_model", "login");
                $this->load->model("User_model", "usrmodel");
                $this->load->model("Common_model", "commodel");
		$this->load->model("SIS_model", "sismodel");
		$this->load->model("PICO_model", "picomodel");
		$data = ['username' => ''];
		$this->session->set_userdata($data);
    	}

    public function vendor() {

                 if(isset($_POST['vendor'])) {
                 
                 $this->form_validation->set_rules('vendor_companyname','Firm name','trim|xss_clean|required|alpha_numeric_spaces|callback_isvendorExist');
                 $this->form_validation->set_rules('vendor_name','Owner Name','trim|xss_clean|required');
                 $this->form_validation->set_rules('vendor_address','Postal Address','trim|xss_clean|required');
                 $this->form_validation->set_rules('vendor_pincode','Pincode','trim|xss_clean|required|exact_length[6]');
                 $this->form_validation->set_rules('vendor_hqaddress','HQ Address','trim|xss_clean|required');
                 $this->form_validation->set_rules('vendor_hqpincode','HQ Pincode','trim|xss_clean|required|exact_length[6]');
                 $this->form_validation->set_rules('vendor_email','Email','trim|xss_clean|required');
                 $this->form_validation->set_rules('vendor_website','Website','trim|xss_clean|required');
                 $this->form_validation->set_rules('vendor_cpn','Contact Person Name','trim|xss_clean|required');
                 $this->form_validation->set_rules('vendor_phone','Phone','trim|xss_clean|required|numeric');
                 $this->form_validation->set_rules('vendor_mobile','Mobile','trim|xss_clean|required|numeric');
                 $this->form_validation->set_rules('vendor_fax','Fax','trim|xss_clean|required|numeric');
                 $this->form_validation->set_rules('vendor_city','City','trim|xss_clean|required|alpha_numeric_spaces');
                 $this->form_validation->set_rules('vendor_state','State','trim|xss_clean|required|alpha_numeric_spaces');
                 $this->form_validation->set_rules('vendor_gstno','Gst No','trim|xss_clean|required|alpha_numeric');
                 $this->form_validation->set_rules('vendor_pan','Pan No','trim|xss_clean|required|alpha_numeric');
                 $this->form_validation->set_rules('vendor_sarn','Shop ACT Registration No','trim|xss_clean|required');
                 $this->form_validation->set_rules('vendor_ern','Excise Registration No','trim|xss_clean|required');
                 $this->form_validation->set_rules('vendor_ban','Bank Account No','trim|xss_clean|required');
                 $this->form_validation->set_rules('vendor_type','Manufacturer Supplier','trim|xss_clean|required');
                 $this->form_validation->set_rules('vendor_supply[]','Items Supply','trim|xss_clean|required');
                 $this->form_validation->set_rules('vendor_blackliststatus','vendor blacklist status','trim|xss_clean');
                  	
                 if($this->form_validation->run()==TRUE){
                 //echo 'form-validated';
                 
                 $vendor_supply = $this->input->post('vendor_supply', TRUE);
                 $l=(join(", ", $vendor_supply));
                 
                 $data = array(
                'vendor_companyname'=>$_POST['vendor_companyname'],
                'vendor_name'=>$_POST['vendor_name'],
                'vendor_address'=>$_POST['vendor_address'],
                'vendor_pincode'=>$_POST['vendor_pincode'],
                'vendor_hqaddress'=>$_POST['vendor_hqaddress'],
                'vendor_hqpincode'=>$_POST['vendor_hqpincode'],
		'vendor_email'=>$_POST['vendor_email'],
                'vendor_website'=>$_POST['vendor_website'],      
                'vendor_contact_person_name'=>$_POST['vendor_cpn'],
                'vendor_phone'=>$_POST['vendor_phone'],
                'vendor_mobile'=>$_POST['vendor_mobile'],
                'vendor_fax'=>$_POST['vendor_fax'],
                'vendor_city'=>$_POST['vendor_city'],
                'vendor_state'=>$_POST['vendor_state'],
                'vendor_gstno'=>$_POST['vendor_gstno'],
                'vendor_pan'=>$_POST['vendor_pan'],
                'vendor_shop_act_registration_no'=>$_POST['vendor_sarn'],
                'vendor_excise_registration_no'=>$_POST['vendor_ern'],
                'vendor_bank_account_no'=>$_POST['vendor_ban'],
                'vendor_type'=>$_POST['vendor_type'],
                'vendor_pre_order'=>$_POST['vendor_list'],
                'vendor_item_supply'=>$l,
                'vendor_blackliststatus'=>$_POST['vendor_blackliststatus'],
                  );
                
                $entryid=$this->PICO_model->insertdata('vendor', $data);
                
                if(!$entryid)
                {
	                $rflag=false;  }
                else
                {
					 $rflag=true;   }
                if (!$rflag)
                {
                    $this->logger->write_logmessage("insert","Trying to add vendor", "vendor is not added ".$vendor_companyname);
                    $this->logger->write_dblogmessage("insert","Trying to add vendor", "vendor is not added ".$vendor_companyname);
                    $this->session->set_flashdata('err_message','Error in Adding Supplier setting - '  , 'error');
                    redirect('picosetup/vendor');
                }
                else{
               	  
               $id=$entryid; 
                       $desired_dir = './uploads/PICO/Supplier/';
                           if(is_dir($desired_dir)==false){
                           mkdir("$desired_dir",0777);
                                                          }
                       $desired_dir1 = './uploads/PICO/Supplier/'.$id.'/';
                           if(is_dir($desired_dir1)==false){
                           mkdir("$desired_dir1",0777);
                                                           }
                     
                       $target_dir = $desired_dir1;    // path computer/opt/lampp/htdocs/brihCI/uploads/supplier/id folder

			              $target_file1 = $target_dir . basename($_FILES["f_gst"]["name"]);
			              $target_file2 = $target_dir . basename($_FILES["f_pan"]["name"]);
			              $target_file3 = $target_dir . basename($_FILES["f_shop"]["name"]);
			              $target_file4 = $target_dir . basename($_FILES["f_excise"]["name"]);
			              $target_file5 = $target_dir . basename($_FILES["f_bank"]["name"]);
			               
			              $uploadOk = 1;
			              
			              $imageFileType1 = strtolower(pathinfo($target_file1,PATHINFO_EXTENSION));
			              $imageFileType2 = strtolower(pathinfo($target_file2,PATHINFO_EXTENSION));
			              $imageFileType3 = strtolower(pathinfo($target_file3,PATHINFO_EXTENSION));
			              $imageFileType4 = strtolower(pathinfo($target_file4,PATHINFO_EXTENSION));
			              $imageFileType5 = strtolower(pathinfo($target_file5,PATHINFO_EXTENSION));
                       //name change
			
			 if ($_FILES["f_gst"]["size"] > 500000 && $_FILES["f_pan"]["size"] > 500000 && $_FILES["f_shop"]["size"] > 500000 && $_FILES["f_excise"]["size"] > 500000 && $_FILES["f_bank"]["size"] > 500000 ) //5mb
		    { 
  	       $this->session->set_flashdata("success", "Sorry, your file is too large (must be below 500 kb ).");
  	       $this->PICO_model->deleterow('vendor','vendor_id', $id);
  	 	    $this->load->view('setup/vendor');
          $uploadOk = 0;
          return;}
          
          if($imageFileType1 != "jpg" && $imageFileType1 != "pdf" && $imageFileType2 != "jpg" && $imageFileType2 != "pdf" && $imageFileType3 != "jpg" && $imageFileType3 != "pdf" &&
           $imageFileType4 != "jpg" &&  $imageFileType4 != "pdf" && $imageFileType5 != "jpg" && $imageFileType5 != "pdf" ) 
		    {
  			 $this->session->set_flashdata("success",  "Sorry, only JPG & pdf files are allowed (check your files format).");
  			 $this->PICO_model->deleterow('vendor','vendor_id', $id);
  			 $this->load->view('setup/vendor');
          $uploadOk = 0;
 			 return;}
          
          if ($uploadOk == 0)
		    {
          $this->session->set_flashdata("success", "Sorry, your file was not uploaded.");
          $this->PICO_model->deleterow('vendor','vendor_id', $id);
          $this->load->view('setup/vendor');
          return;
          }
          else // if everything is ok, try to upload file
          {
          	$name1 = $target_dir .'gst.'.$imageFileType1;
              	$name2 = $target_dir .'pan.'.$imageFileType2 ;
              	$name3 = $target_dir .'shop.'.$imageFileType3;
              	$name4 = $target_dir . 'exise.'.$imageFileType4;
              	$name5 = $target_dir . 'bank.'.$imageFileType5;
          	
          if (move_uploaded_file($_FILES["f_gst"]["tmp_name"], $name1) && move_uploaded_file($_FILES["f_pan"]["tmp_name"], $name2) && move_uploaded_file($_FILES["f_shop"]["tmp_name"], $name3)
              &&   move_uploaded_file($_FILES["f_excise"]["tmp_name"], $name4) && move_uploaded_file($_FILES["f_bank"]["tmp_name"], $name5) ) 
            {
          //code here from sis
                 	//generate 10 digit random password
			$passwd=$this->common_model->randNum(10);
			
          $isdupl= $this->lgnmodel->isduplicate('edrpuser','username',$_POST['vendor_email']);
		    if(!$isdupl){

                    /* generate the hash of password */
                    $password=md5($passwd);
                    $dataedrpusr = array(
                        'username'=> $_POST['vendor_email'],
                        'password'=> $password,
                        'email'=> $_POST['vendor_email'],
                        'componentreg'=> '*',
                        'mobile'=>$_POST['vendor_phone'],
                        'status'=>1,
                        'category_type'=>'Supplier',
                        'is_verified'=>1
                    );
                    /* insert record in edrpuser */
                    $this->lgnmodel->insertrec('edrpuser',$dataedrpusr);
                    $this->logger->write_logmessage("insert", "data insert in edrpuser table.");
                    $this->logger->write_dblogmessage("insert", "data insert in edrpuser table." );
                    
                    /*get user id from login (edrpuser table)*/
                    $getid= $this->lgnmodel->get_listspfic1('edrpuser','id','username',$_POST['vendor_email']);
                    $usrid=$getid->id;
                    
                    $datausrpf = array(
                        'userid'=> $usrid,
                        'firstname'=>$_POST['vendor_name'],
                        'lang'=> 'english',
                        'mobile'=>$_POST['vendor_phone'],
                        'status'=>1
                    );
                    /* insert record in userprofile table */
                    $this->lgnmodel->insertrec('userprofile', $datausrpf);
                    $this->logger->write_logmessage("insert", "data insert in userprofile table.");
                    $this->logger->write_dblogmessage("insert", "data insert in userprofile table." );
                }//edusr
          
                    $getid= $this->lgnmodel->get_listspfic1('edrpuser','id','username',$_POST['vendor_email']);
                    $usrid=$getid->id;
                    $dataurt = array(
                        'userid'=> $usrid,
                        'roleid'=> 12,
                        'scid'  => '',
                        'deptid'=>'',
                        'usertype'=>"Supplier"
                    );
                    /* insert record in user_role_type */
                    $isdupl= $this->PICO_model->isduplicatemore('user_role_type',$dataurt);
                    
		              if(!$isdupl){
                    $r=array('vendor_userid'=>$usrid);
                    $v_userid=$this->PICO_model->updaterec('vendor',$r,'vendor_id',$id);
                    
                    $this->PICO_model->insertrec('user_role_type',$dataurt);
                    $this->logger->write_logmessage("insert", "data insert in user_role_type table.");
                    $this->logger->write_dblogmessage("insert", "data insert in user_role_type table." );
          
                    }
           //if sucess send mail to user with login details 
                    $sub='Supplier Registration in PICO System';
                    $mess="Your registration is completed. The user id ".$_POST['vendor_email']." and password is ".$passwd ."\r\n".'Kindly check with website:'."\r\n". site_url('welcome');
			
                    $mailstoperson =$this->mailmodel->mailsnd($_POST['vendor_email'], $sub, $mess,'');
                    //  mail flag check 
                    if($mailstoperson){
                        //echo "in if part mail";
                        $mailmsg='Please check your mail for username and password....Mail send successfully';
                        $this->logger->write_logmessage("insert"," add user profile in edrpuser,profile and user role type ",'mail send successfully  to '.$_POST['vendor_email'] );
                        $this->logger->write_dblogmessage("insert"," add user profile in edrpuser,profile and user role type ",'mail send successfully  to '.$_POST['vendor_email'] );
                    }
                     else{
                        //echo "in else part";
                        $mailmsg='Mail does not sent';
                        $this->logger->write_logmessage("insert"," add user profile in  edrpuser,userprofile vendor and user role type ", "Mail does not sent to ".$_POST['vendor_email']);
                        $this->logger->write_dblogmessage("insert"," add user profile in edrpuser,userprofile,vendor and user role type ", "Mail does not sent to ".$_POST['vendor_email']);
                    }
          $this->session->set_flashdata("success", "The files has been uploaded....<br>");
          $this->logger->write_logmessage("insert","Add vendor Setting", "vendor".$_POST['vendor_companyname']." added  successfully...");
          $this->logger->write_dblogmessage("insert","Add vendor Setting", "vendor".$_POST['vendor_companyname']."added  successfully...");
	  $this->session->set_flashdata("success", "Supplier Added Successfully & ".$mailmsg);
	  	$sunme = $this->session->userdata['username'];
                if((strcasecmp($sunme,"admin" )) == 0){
	          	redirect("picosetup/displayvendor");          
		}else{
          		$this->load->view('setup/vendor');
		}
          return;}
          else 
          {
          $this->session->set_flashdata("success", "Sorry, there was an error uploading your file.");
          $this->PICO_model->deleterow('vendor','vendor_id', $id);
          $this->load->view('setup/vendor');
          return;
          }
          }
                    // $this->logger->write_logmessage("insert","Add vendor Setting", "vendor".$_POST['vendor_companyname']." added  successfully...");
                    // $this->logger->write_dblogmessage("insert","Add vendor Setting", "vendor".$_POST['vendor_companyname']."added  successfully...");
                    // $this->session->set_flashdata("success", "Supplier Added Successfully...");
                    // redirect("picosetup/displayvendor");
            }

            }
        }
        $this->load->view('setup/vendor');
    }

    /** This function check for duplicate vendor/supplier
     * @return type
    */

    public function isvendorExist($vendor_companyname) {

        $is_exist = $this->PICO_model->isduplicate('vendor','vendor_companyname',$vendor_companyname);
        if ($is_exist)
        {
            $this->form_validation->set_message('isvendorExist', 'Supplier With This Company Name Is Already Registered.');
            return false;
        }
        else {
            return true;
        }
    }
	public function index() {
		$cdate = date('Y-m-d');
                $wharray = array('anou_cname=' => 'PICO', 'anou_publishdate<=' => $cdate,'anou_expdate>=' => $cdate);
                $this->annoresult = $this->commodel->get_listarry('announcement','*',$wharray);

            if($_POST) {
                $result = $this->login->validate_user($_POST);
                /*get role by using model class and set templates according to role*/
                //$roles=$this->commodel->get_listspficarry('user_role_type','roleid','userid',$result->id);
		$empid=$this->sismodel->get_listspfic1('employee_master', 'emp_id', 'emp_email', $result->username)->emp_id;
                $wharray=array('userid'=>$result->id);
		$roles=$this->sismodel->get_listspficemore('user_role_type','roleid,deptid',$wharray);
                if(!empty($result)) {
             		if(!empty($roles)){   
                    		if(count($roles) == 1){
                        		foreach($roles as $row):
                            			if($row->roleid == 1){
                                			$data = [
							'id_emp' => $empid,
			                                'id_user' => $result->id,
                        			        'username' => $result->username,
			                                'id_role' => $row->roleid
                        			        ];
			                                $this->session->set_userdata($data);
                        			        redirect('home');
                           			} 
                            			if($row->roleid == 2){
			                                $data = [
							'id_emp' => $empid,
                        			        'id_user' => $result->id,
			                                'username' => $result->username,
                        			        'id_role' => $row->roleid
			                                ];
                        			        $this->session->set_userdata($data);
			                                redirect('facultyhome'); 
                        			}
                            			if($row->roleid == 3){
			                                $data = [
							'id_emp' => $empid,
                        			        'id_user' => $result->id,
			                                'username' => $result->username,
                        			        'id_role' => $row->roleid
			                                ];
                        			        $this->session->set_userdata($data);
			                                redirect('studenthome'); 
                        			}
                            			if($row->roleid == 4){
			                                $data = [
							'id_emp' => $empid,
                        			        'id_user' => $result->id,
			                                'username' => $result->username,
                        			        'id_role' => $row->roleid
			                                ];
                        			        $this->session->set_userdata($data);
			                                redirect('staffhome'); 
                        			}
                            			if($row->roleid == 5){
			                                $data = [
							'id_emp' => $empid,
                        			        'id_user' => $result->id,
			                                'username' => $result->username,
                        			        'id_role' => $row->roleid,
                        			        'id_dept' => $row->deptid
			                                ];
                        			        $this->session->set_userdata($data);
			                                redirect('hodhome'); 
                        			}
                            			if($row->roleid == 6){
			                                $data = [
							'id_emp' => $empid,
                        			        'id_user' => $result->id,
			                                'username' => $result->username,
                        			        'id_role' => $row->roleid
			                                ];
                        			        $this->session->set_userdata($data);
			                                redirect('coehome'); 
                        			}
                            			if($row->roleid == 7){
			                                $data = [
							'id_emp' => $empid,
                        			        'id_user' => $result->id,
			                                'username' => $result->username,
                        			        'id_role' => $row->roleid
			                                ];
                        			        $this->session->set_userdata($data);
			                                redirect('accoffhome'); 
                        			}
                            			if($row->roleid == 10){
			                                $data = [
							'id_emp' => $empid,
                        			        'id_user' => $result->id,
			                                'username' => $result->username,
                        			        'id_role' => $row->roleid
			                                ];
                        			        $this->session->set_userdata($data);
			                                redirect('uohome'); 
                        			}
                        		endforeach;   
                    		}else{
                        		foreach($roles as $row):
                            			$data = [
							'id_emp' => $empid,
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
            $this->load->view("welcome_message");
        }//close index function
	
    }//close class
