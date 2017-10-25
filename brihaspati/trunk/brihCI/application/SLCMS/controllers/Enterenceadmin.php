<?php

/* 
 * @name Enterenceadmin.php
 * @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 * @author Sumit saxena(sumitsesaxena@gmail.com)
 */

defined('BASEPATH') OR exit('No direct script access allowed');


class Enterenceadmin extends CI_Controller
    {
    function __construct() {
        parent::__construct();
		  $this->load->model("user_model","usermodel");
                $this->load->model('Common_model',"commodel");
		$this->load->model('dependrop_model','depmodel');
        if(empty($this->session->userdata('id_user'))) {
          $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
         }
    }
/** This function add the examcenter
     * @return type
     */
	public function addexamcenter(){
		$data = array('country_id' => 101);
		 $this->cresult = $this->commodel->get_listspficemore('states','id,name',$data);

		 if(isset($_POST['addexamcenter'])) {
                 	$this->form_validation->set_rules('eec_code','Enterance Exam Center Code','trim|xss_clean|required');
	                 $this->form_validation->set_rules('eec_name','Enterance Exam Center Name','trim|xss_clean|required|callback_isECExist');
                	 $this->form_validation->set_rules('eec_address','Enterance Exam Center Address','trim|xss_clean|required');
        	         $this->form_validation->set_rules('eec_state','Enterance Exam Center State','trim|xss_clean|required');
	                 $this->form_validation->set_rules('eec_city','Enterance Exam Center City','trim|xss_clean|required');
        	         $this->form_validation->set_rules('eec_incharge','Enterance Exam Center Incharge','trim|xss_clean');
                	 $this->form_validation->set_rules('eec_noofroom','Enterance Exam Center Number of Room','trim|xss_clean|integer');
	                 $this->form_validation->set_rules('eec_capacityinroom','Enterance Exam Center Capacity in Room','trim|xss_clean|integer');
        	         $this->form_validation->set_rules('eec_totalcapacity','Enterance Exam Center Total Capacity','trim|xss_clean');
                	 $this->form_validation->set_rules('eec_contactno','Enterance Exam Center Contact No','trim|xss_clean|integer');
	                 $this->form_validation->set_rules('eec_contactemail','Enterance Exam Center Contact Email','trim|xss_clean|valid_email');

        	         if($this->form_validation->run()==TRUE){
                	        $data = array(
                        	        'eec_code'=>strtoupper($_POST['eec_code']),
                                	'eec_name'=>$_POST['eec_name'],
	                                'eec_address'=>ucwords(strtolower($_POST['eec_address'])),
					'eec_state'=>$_POST['eec_state'],
                	                'eec_city'=>$_POST['eec_city'],
					'eec_incharge'=>$_POST['eec_incharge'],
                                	'eec_noofroom'=>$_POST['eec_noofroom'],
	                                'eec_capacityinroom'=>$_POST['eec_capacityinroom'],
        	                        'eec_totalcapacity'=>$_POST['eec_totalcapacity'],
					'eec_contactno'=>$_POST['eec_contactno'],
                        	        'eec_contactemail'=>$_POST['eec_contactemail'],
                           		);
	                           $examcflag=$this->commodel->insertrec('admissionstudent_enterenceexamcenter', $data);
        	                   if (!$examcflag)
                	           {
                                	$this->logger->write_logmessage("insert","Trying to Exam Center", "Exam Center is not added ".$_POST['eec_name']);
                                	$this->logger->write_dblogmessage("insert","Trying to Exam Center", "Exam Center is not added".$_POST['eec_name']);
                                	$this->session->set_flashdata('err_message','Error in adding exam center setting - '  , 'error');
                                	redirect('enterenceadmin/addexamcenter');
                        	}
                        	else{
                                	$this->logger->write_logmessage("insert","Add Exam Center Setting", "Exam Center".$_POST['eec_name']." added  successfully...");
	                                $this->logger->write_dblogmessage("insert","Add Exam Center Setting", "Exam Center".$_POST['eec_name']."added  successfully...");
        	                        $this->session->set_flashdata("success", "Exam Center add successfully...");
                	                redirect("enterenceadmin/examcenter");
                        	}
                	}
        	}

    	$this->load->view('enterenceadmin/addexamcenter');
    }
/** This function check for duplicate exam center
     * @return type
     */
    public function isECExist($eec_name) {
        $is_exist = $this->commodel->isduplicate('admissionstudent_enterenceexamcenter','eec_name',$eec_name);
        if ($is_exist)
        {
            $this->form_validation->set_message('isECExist', 'Exam Center is already exist.');
            return false;
        }
        else {
            return true;
        }
    }

/** This function display the Exam Center                                                                                                                                                 
  * @param type  
  * @return type
  */

     public function examcenter() {
        $this->result = $this->commodel->get_list('admissionstudent_enterenceexamcenter');
        $this->logger->write_logmessage("view"," View Exam Center", "Exam Center details...");
        $this->logger->write_dblogmessage("view"," View Exam Center" , "Exam Center record display successfully..." );
        $this->load->view('enterenceadmin/examcenter',$this->result);
       }

 /**This function is used for update exam center records
     */
	
   public function editexamcenter($id){
	$data = array('country_id' => 101);
	$this->cresult = $this->commodel->get_listspficemore('states','id,name',$data);
	$examcenterrow=$this->commodel->get_listrow('admissionstudent_enterenceexamcenter','eec_id', $id);
        if ($examcenterrow->num_rows() < 1)
        {
            redirect('enterenceadmin/editexamcenter');
        }
        $exam_data_q = $examcenterrow->row();

/* Form fields */

               $data['eec_code'] = array(
               'name' => 'eec_code',
               'id' => 'eec_code',
               'size' => '40',
               'value' => $exam_data_q->eec_code,
               );
		$data['eec_name'] = array(
               'name' => 'eec_name',
               'id' => 'eec_name',
               'size' => '40',
               'value' => $exam_data_q->eec_name,
               );
		$data['eec_address'] = array(
               'name' => 'eec_address',
               'id' => 'eec_address',
               'size' => '40',
               'value' => $exam_data_q->eec_address,
               );
		$data['eec_state'] = array(
               'name' => 'eec_state',
               'id' => 'eec_state',
               'size' => '40',
               'value' => $exam_data_q->eec_state,
	       'readonly' => 'readonly'
               );

		$data['eec_city'] = array(
               'name' => 'eec_city',
               'id' => 'eec_city',
               'size' => '40',
               'value' => $exam_data_q->eec_city,
		'readonly' => 'readonly'
               );
		$data['eec_incharge'] = array(
               'name' => 'eec_incharge',
               'id' => 'eec_incharge',
               'size' => '40',
               'value' => $exam_data_q->eec_incharge,
               );
	       $data['eec_noofroom'] = array(
               'name' => 'eec_noofroom',
               'id' => 'eec_noofroom',
               'size' => '40',
               'value' => $exam_data_q->eec_noofroom,
               );
		$data['eec_capacityinroom'] = array(
               'name' => 'eec_capacityinroom',
               'id' => 'eec_capacityinroom',
               'size' => '40',
               'value' => $exam_data_q->eec_capacityinroom,
               );
	       $data['eec_totalcapacity'] = array(
               'name' => 'eec_totalcapacity',
               'id' => 'eec_totalcapacity',
               'size' => '40',
               'value' => $exam_data_q->eec_totalcapacity,
               );
	       $data['eec_contactno'] = array(
               'name' => 'eec_contactno',
               'id' => 'eec_contactno',
               'size' => '40',
               'value' => $exam_data_q->eec_contactno,
               );
	      $data['eec_contactemail'] = array(
               'name' => 'eec_contactemail',
               'id' => 'eec_contactemail',
               'size' => '40',
               'value' => $exam_data_q->eec_contactemail,
               );
	       $data['id'] = $id;
/*Form Validation*/
		 $this->form_validation->set_rules('eec_code','Enterance Exam Center Code','trim|xss_clean|required');
                 $this->form_validation->set_rules('eec_name','Enterance Exam Center Name','trim|xss_clean|required');
                 $this->form_validation->set_rules('eec_address','Enterance Exam Center Address','trim|xss_clean|required');
		 //$this->form_validation->set_rules('eec_state','Enterance Exam Center State','trim|xss_clean');
                 //$this->form_validation->set_rules('eec_city','Enterance Exam Center City','trim|xss_clean');
                 $this->form_validation->set_rules('eec_incharge','Enterance Exam Center Incharge','trim|xss_clean');
                 $this->form_validation->set_rules('eec_noofroom','Enterance Exam Center Number of Room','trim|xss_clean|integer');
                 $this->form_validation->set_rules('eec_capacityinroom','Enterance Exam Center Capacity in Room','trim|xss_clean|integer');
                 $this->form_validation->set_rules('eec_totalcapacity','Enterance Exam Center Total Capacity','trim|xss_clean|integer');
                 $this->form_validation->set_rules('eec_contactno','Enterance Exam Center Contact No','trim|xss_clean|integer');
                 $this->form_validation->set_rules('eec_contactemail','Enterance Exam Center Contact Email','trim|xss_clean|valid_email');
/* Re-populating form */
   	         if ($_POST){
            	 $data['eec_code']['value'] = $this->input->post('eec_code', TRUE);
            	 $data['eec_name']['value'] = $this->input->post('eec_name', TRUE);
		 $data['eec_address']['value'] = $this->input->post('eec_address', TRUE);
                 //$data['eec_city']['value'] = $this->input->post('eec_city', TRUE);
		 $data['eec_incharge']['value'] = $this->input->post('eec_incharge', TRUE);
                 $data['eec_noofroom']['value'] = $this->input->post('eec_noofroom', TRUE);
		 $data['eec_capacityinroom']['value'] = $this->input->post('eec_capacityinroom', TRUE);
                 $data['eec_totalcapacity']['value'] = $this->input->post('eec_totalcapacity', TRUE);
		 $data['eec_contactno']['value'] = $this->input->post('eec_contactno', TRUE);
                 $data['eec_contactemail']['value'] = $this->input->post('eec_contactemail', TRUE);
       		 }

 	         if ($this->form_validation->run() == FALSE){
                 	$this->load->view('enterenceadmin/editexamcenter', $data);
                 	return;
                 }
		else{
	            	$eec_code = $this->input->post('eec_code', TRUE);
        	    	$eec_name = $this->input->post('eec_name', TRUE);
			$eec_address = ucwords(strtolower($this->input->post('eec_address', TRUE)));
			//$eec_city = ucwords(strtolower($this->input->post('eec_city', TRUE)));
			$eec_incharge = ucwords(strtolower($this->input->post('eec_incharge', TRUE)));
			$eec_noofroom = $this->input->post('eec_noofroom', TRUE);
	                $eec_capacityinroom = $this->input->post('eec_capacityinroom', TRUE);
			$eec_totalcapacity = $this->input->post('eec_totalcapacity', TRUE);
                	$eec_contactno =$this->input->post('eec_contactno', TRUE);
			$eec_contactemail =$this->input->post('eec_contactemail', TRUE);
        	        $logmessage = "";
	
			if($exam_data_q->eec_code != $eec_code)
                		$logmessage = "Add Exam Center" .$exam_data_q->eec_code. " changed by " .$eec_code;
			if($exam_data_q->eec_name != $eec_name)
        		        $logmessage = "Add Exam Center" .$exam_data_q->eec_name. " changed by " .$eec_name;
			if($exam_data_q->eec_address != $eec_address)
        		        $logmessage = "Add Exam Center" .$exam_data_q->eec_address. " changed by " .$eec_address;
			//if($exam_data_q->eec_city != $eec_city)
        		        //$logmessage = "Add Exam Center" .$exam_data_q->eec_city. " changed by " .$eec_city;
			if($exam_data_q->eec_incharge != $eec_incharge)
        	        	$logmessage = "Add Exam Center" .$exam_data_q->eec_incharge. " changed by " .$eec_incharge;
			if($exam_data_q->eec_noofroom != $eec_noofroom)
		                $logmessage = "Add Exam Center" .$exam_data_q->eec_noofroom. " changed by " .$eec_noofroom;
			if($exam_data_q->eec_capacityinroom != $eec_capacityinroom)
		                $logmessage = "Add Exam Center" .$exam_data_q->eec_capacityinroom. " changed by " .$eec_capacityinroom;
			if($exam_data_q->eec_totalcapacity != $eec_totalcapacity)
		                $logmessage = "Add Exam Center" .$exam_data_q->eec_totalcapacity. " changed by " .$eec_totalcapacity;
			if($exam_data_q->eec_contactno != $eec_contactno)
		                $logmessage = "Add Exam Center" .$exam_data_q->eec_contactno. " changed by " .$eec_contactno;
			if($exam_data_q->eec_contactemail != $eec_contactemail)
		                $logmessage = "Add Exam Center" .$exam_data_q->eec_contactemail. " changed by " .$eec_contactemail;
			$arins_data = array(
		                'eeca_eecid' => $id,
		                'eeca_code' => $exam_data_q->eec_code,
               			'eeca_name' => $exam_data_q->eec_name,
		                'eeca_address' => $exam_data_q->eec_address,
               			'eeca_city' => $exam_data_q->eec_city,
               			'eeca_state' => $exam_data_q->eec_state,
		                'eeca_incharge' => $exam_data_q->eec_incharge,
               			'eeca_noofroom' => $exam_data_q->eec_noofroom,
		                'eeca_capacityinroom' => $exam_data_q->eec_capacityinroom,
               			'eeca_totalcapacity' => $exam_data_q->eec_totalcapacity,
		               	'eeca_contactno' => $exam_data_q->eec_contactno,
               			'eeca_contactemail' =>$exam_data_q->eec_contactemail,
               			'eeca_archivename' => $this->session->userdata('username'),
               			'eeca_archivedate' => date('y-m-d')
            		);
	                $examcenterflaga=$this->commodel->insertrec('admissionstudent_enterenceexamcentera', $arins_data);


			$update_data = array(
               			'eec_code' => $eec_code,
		               	'eec_name' => $eec_name,
	       			'eec_address' => $eec_address,
	       			'eec_incharge' => $eec_incharge,
               			'eec_noofroom' => $eec_noofroom,
	       			'eec_capacityinroom' => $eec_capacityinroom,
               			'eec_totalcapacity' => $eec_totalcapacity,
	       			'eec_contactno' => $eec_contactno,
               			'eec_contactemail' => $eec_contactemail,
            		);
            		$examcenterflag=$this->commodel->updaterec('admissionstudent_enterenceexamcenter', $update_data,'eec_id', $id);
            		if(!$examcenterflag)
            		{
                		$this->logger->write_logmessage("error","Edit Exam Center Setting error", "Edit Exam Center Setting details. $logmessage ");
		                $this->logger->write_dblogmessage("error","Edit Exam Center Setting error", "Edit Exam Center Setting details. $logmessage ");
                		$this->session->set_flashdata('err_message','Error updating Exam Center- ' . $logmessage . '.', 'error');
		                $this->load->view('enterenceadmin/editexamcenter', $data);
            		}
            		else{
		                $this->logger->write_logmessage("update","Edit Exam Center Setting", "Edit Exam Center Setting details. $logmessage ");
                		$this->logger->write_dblogmessage("update","Edit Exam Center Setting", "Edit Exam Center Setting details. $logmessage ");
		                $this->session->set_flashdata('success','Exam Center detail updated successfully..');
                		redirect('enterenceadmin/examcenter/');
                	}
        	}//else
   }//end edit exam center function
//This function get city
        public function get_city(){
               $statid = $this->input->post('sid');
               $this->depmodel->get_citylist($statid);
        }

	public function viewstikerlist(){
		$this->examcenter = $this->commodel->get_listmore('admissionstudent_enterenceexamcenter','eec_name,eec_city,eec_id');
        	
		$exmceter = $this->input->post('stiexamcenter',TRUE);
		if(isset($_POST['searchsticker'])){
			$selectdata=array('ca_asmid','ca_rollno','ca_centername');
			$record=array(
				'ca_hallticketstatus' => 'Y',
   				'ca_centername'  => $exmceter,
      				);
       			$this->getsticker = $this->commodel->get_listspficemore('admissionstudent_centerallocation',$selectdata,$record);
			
		}//close post
		$this->load->view('enterenceadmin/stickersheet');
        }

	public function generatesticker(){
		$exmceter = $this->input->post('stiexamcenter',TRUE);
		if(!empty($exmceter)){
			$attselectdata=array('ca_asmid','ca_rollno','ca_centername','ca_prgid');
			$attrecord=array(
				'ca_centername'  => $exmceter,
			);
       			$getsticker = $this->commodel->get_listspficemore('admissionstudent_centerallocation',$attselectdata,$attrecord);
			$this->genstickerpdf($getsticker);
		}else{
		//collect the distinct list of center
			$clist=$this->commodel->get_distinctrecord('admissionstudent_centerallocation','ca_centername','');
			foreach($clist as $row1){
				$exmceter1 = $row1->ca_centername;
				if(!empty($exmceter1)){
					$attselectdata=array('ca_asmid','ca_rollno','ca_centername','ca_prgid');
					$attrecord=array('ca_centername'  => $exmceter1);
       					$getsticker1 = $this->commodel->get_listspficemore('admissionstudent_centerallocation',$attselectdata,$attrecord);
					$this->logger->write_logmessage("update", "Attendence sheet data foe each enter".$getsticker1);
					//print_r($getatt1);
					$this->genstickerpdf($getsticker1);
				}
			}
		}	
			
	 	echo $message = '<h3 style="font-size:20px;text-align:center;background-color:#DFF2BF;width:50%;height:30px;color:green;">Centerwise attendance sheet generated Successfully .</h3>';
	 	redirect('enterenceadmin/viewstikerlist',$data, TRUE);

	 }
	//sticker pdf create function
        public function genstickerpdf($getsticker){
		$data['getsticker']=$getsticker;
		//get asmid to update sticker status
		foreach($getsticker as $row){
			$asmid=$row->ca_asmid;
			$cname=$row->ca_centername;
			$centerid = $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_id','eec_name',$cname)->eec_id;
			$year=date('Y');
                	// move file to directory code for photo
			$desired_dir = 'uploads/SLCMS/enterenceadmin_student/'.$year;
                       	// Create directory if it does not exist
                       	if(is_dir($desired_dir)==false){
                       		mkdir("$desired_dir", 0700);
                       	}

                	$desired_dir = 'uploads/SLCMS/enterenceadmin_student/'.$year.'/sticker';
                       	// Create directory if it does not exist
                       	if(is_dir($desired_dir)==false){
                       		mkdir("$desired_dir", 0700);
                	 }

			$this->acadyear = $this->usermodel->getcurrentAcadYear();
						
			//add pdf code to store and view pdf file
			$temp = $this->load->view('enterenceadmin/stickerpdf', $data, TRUE);
			$pth='uploads/SLCMS/enterenceadmin_student/'.$year.'/sticker/'.$centerid.'Sticker'.'.pdf';
			$this->genpdf($temp,$pth);
			$master = array(
		      		'ca_stickerstatus'   => 'Y',
	           		);
    			$this->commodel->updaterec('admissionstudent_centerallocation', $master,'ca_asmid',$asmid);
			$this->logger->write_logmessage("update", "Sticker status update yes in admissionstudent_centerallocation");
                	$this->logger->write_dblogmessage("update", "Sticker status update yes in admissionstudent_centerallocation" );
		}
       
	}
	
	public function viewhallticket(){

	$data=array('ca_hallticketstatus' =>'Y');
        $stud_master = $this->commodel->get_listspficemore('admissionstudent_centerallocation','ca_asmid,ca_rollno',$data);
	$data['stud_master'] = $stud_master;
			
        $this->load->view('enterenceadmin/hallticket',$data);
        }

        public function generatehallticket(){

                $data=array('ca_hallticketstatus' => NULL , 'ca_centername !=' => NULL);
                $stud_master = $this->commodel->get_listspficemore('admissionstudent_centerallocation','ca_asmid,ca_rollno',$data);
		$year=date('Y');
			
                       // move file to directory code for photo
			$desired_dir = 'uploads/SLCMS/enterenceadmin_student/'.$year;
                        // Create directory if it does not exist
                        if(is_dir($desired_dir)==false){
                              mkdir("$desired_dir", 0700);
                        }

                        $desired_dir = 'uploads/SLCMS/enterenceadmin_student/'.$year.'/hallticket';
                        // Create directory if it does not exist
                        if(is_dir($desired_dir)==false){
                              mkdir("$desired_dir", 0700);
                        }

                foreach($stud_master as $row){
			$asmid=$row->ca_asmid;
			$data['asmid'] = $asmid;
			$gender=$this->commodel->get_listspfic1('admissionstudent_master','asm_gender','asm_id',$row->ca_asmid)->asm_gender;
			$data['gender'] = $gender;
			$caste=$this->commodel->get_listspfic1('admissionstudent_master','asm_caste','asm_id',$row->ca_asmid)->asm_caste;
        		$data['caste'] = $caste;               
			$prgid  = $this->commodel->get_listspfic1('admissionstudent_master','asm_coursename','asm_id',$row->ca_asmid)->asm_coursename;
			$data['prgid'] = $prgid;                        
			$progname = $this->commodel->get_listspfic1('program','prg_name','prg_id',$prgid)->prg_name.'('.$this->commodel->get_listspfic1('program','prg_branch','prg_id',$prgid)->prg_branch.')';
			$data['progname'] = $progname;
                        $rollno=$row->ca_rollno;
			$data['rollno'] = $rollno;
                        $sname = $this->commodel->get_listspfic1('admissionstudent_master','asm_fname','asm_id',$row->ca_asmid)->asm_fname;
			$data['sname'] = $sname;
                        $faname=$this->commodel->get_listspfic1('admissionstudent_parent','aspar_fathername','aspar_asmid',$row->ca_asmid)->aspar_fathername;
			$data['faname'] = $faname;
                        $moname=$this->commodel->get_listspfic1('admissionstudent_parent','aspar_mothername','aspar_asmid',$row->ca_asmid)->aspar_mothername;
			$data['moname'] = $moname;
                        $padd=$this->commodel->get_listspfic1('admissionstudent_parent','aspar_paddress','aspar_asmid',$row->ca_asmid)->aspar_paddress;
			$data['padd'] = $padd;
                        $pcity=$this->commodel->get_listspfic1('admissionstudent_parent','aspar_pcity','aspar_asmid',$row->ca_asmid)->aspar_pcity;
			$data['pcity'] = $pcity;
                        $pstate=$this->commodel->get_listspfic1('admissionstudent_parent','aspar_pstate','aspar_asmid',$row->ca_asmid)->aspar_pstate;
			$data['pstate'] = $pstate;
                        $pcountry=$this->commodel->get_listspfic1('admissionstudent_parent','aspar_pcountry','aspar_asmid',$row->ca_asmid)->aspar_pcountry;
			$data['pcountry'] = $pcountry;
			$photo=$this->commodel->get_listspfic1('admissionstudent_uploaddata','asupd_photo','asupd_asmid',$row->ca_asmid)->asupd_photo;
			$data['photo'] = $photo;
			$signature = $this->commodel->get_listspfic1('admissionstudent_uploaddata','asupd_signature','asupd_asmid',$row->ca_asmid)->asupd_signature;
			$data['signature'] = $signature;
			$centerid=$this->commodel->get_listspfic1('admissionstudent_master','asm_enterenceexamcenter','asm_id',$row->ca_asmid)->asm_enterenceexamcenter;
			$venue=$this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_address','eec_id',$centerid)->eec_address.','.$this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_city','eec_id',$centerid)->eec_city;
			$data['venue'] = $venue;
			$exmdate = $this->commodel->get_listspfic1('admissionopen','admop_entexam_date','admop_prgname_branch',$prgid)->admop_entexam_date;
			$data['exmdate'] = $exmdate;
			
			$acadyear = $this->usermodel->getcurrentAcadYear();
			$data['acadyear'] = $acadyear;
                      	//add pdf code to store and view pdf file			   	
			$temp = $this->load->view('enterenceadmin/hallticketpdf', $data, TRUE);
    			$pth='uploads/SLCMS/enterenceadmin_student/'.$year.'/hallticket/'.$row->ca_asmid.'hallticket.pdf';
			$this->genpdf($temp,$pth);
			$master = array(
		                		'ca_hallticketstatus'   => 'Y',
	           	     		);
    			$this->commodel->updaterec('admissionstudent_centerallocation', $master,'ca_asmid',$asmid);
			$this->logger->write_logmessage("update", "Hall ticket status update yes in admissionstudent_centerallocation");
                    	$this->logger->write_dblogmessage("update", "Hall ticket status update yes in admissionstudent_centerallocation" );
			
        }
	 echo $message = '<h3 style="font-size:20px;text-align:center;background-color:#DFF2BF;width:50%;height:30px;color:green;">Hall Ticket Successfully Generated.</h3>';
	 redirect('enterenceadmin/viewhallticket');

 }

	public function viewattendancesheet(){
		$this->examcenter = $this->commodel->get_listmore('admissionstudent_enterenceexamcenter','eec_name,eec_city,eec_id');
   		$this->load->view('enterenceadmin/attendencesheet');
        }

	public function generateattendence(){
		$attexmceter = $this->input->post('attexamcenter',TRUE);
		if(!empty($attexmceter)){
			$attselectdata=array('ca_asmid','ca_rollno','ca_centername','ca_prgid');
			$attrecord=array(
				'ca_centername'  => $attexmceter,
			);
       			$getatt = $this->commodel->get_listspficemore('admissionstudent_centerallocation',$attselectdata,$attrecord);
			$this->genattpdf($getatt);
		}else{
			//collect the distinct list of center
			$clist=$this->commodel->get_distinctrecord('admissionstudent_centerallocation','ca_centername','');
			foreach($clist as $row1){
				$attexmceter1 = $row1->ca_centername;
				if(!empty($attexmceter1)){
					$attselectdata=array('ca_asmid','ca_rollno','ca_centername','ca_prgid');
					$attrecord=array('ca_centername'  => $attexmceter1);
       					$getatt1 = $this->commodel->get_listspficemore('admissionstudent_centerallocation',$attselectdata,$attrecord);
					$this->logger->write_logmessage("update", "Attendence sheet data foe each enter".$getatt1);
					//print_r($getatt1);
					$this->genattpdf($getatt1);
				}
			}
		}
		echo $message = '<h3 style="font-size:20px;text-align:center;background-color:#DFF2BF;width:50%;height:30px;color:green;">Centerwise attendance sheet generated Successfully .</h3>';
	 	redirect('enterenceadmin/viewattendancesheet');
 	}
	
	//attendence pdf create function
	public function genattpdf($getatt){
		$data['getatt']=$getatt;
			foreach($getatt as $row){
				$asmid=$row->ca_asmid;
				$cname=$row->ca_centername;
				$centerid = $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_id','eec_name',$cname)->eec_id;
				$year=date('Y');
                      	 	// move file to directory code for photo
				$desired_dir = 'uploads/SLCMS/enterenceadmin_student/'.$year;
                        	// Create directory if it does not exist
                        	if(is_dir($desired_dir)==false){
                              		mkdir("$desired_dir", 0700);
                        	}

                       	 	$desired_dir = 'uploads/SLCMS/enterenceadmin_student/'.$year.'/attendence';
                        	// Create directory if it does not exist
                        	if(is_dir($desired_dir)==false){
                              		mkdir("$desired_dir", 0700);
                       		 }

				$this->acadyear = $this->usermodel->getcurrentAcadYear();
						
				//add pdf code to store and view pdf file
				$temp = $this->load->view('enterenceadmin/attendencepdf', $data, TRUE);
				//$pth='uploads/SLCMS/enterenceadmin_student/'.$year.'/attendence/'.$cname.'.pdf';
				$pth='uploads/SLCMS/enterenceadmin_student/'.$year.'/attendence/'.$centerid.'.pdf';
				$this->genpdf($temp,$pth);
				$master = array(
		                		'ca_attendencesheetstatus'   => 'Y',
	           	     		);
    				$this->commodel->updaterec('admissionstudent_centerallocation', $master,'ca_asmid',$asmid);
				$this->logger->write_logmessage("update", "Attendence sheet status update yes in admissionstudent_centerallocation");
                    		$this->logger->write_dblogmessage("update", "Attendence sheet status update yes in admissionstudent_centerallocation" );
			}
	}

	public function genpdf($content,$path){
		$this->load->library('pdf');
		$this->pdf = new DOMPDF();	
     		// pass html to dompdf object
    		$this->pdf->load_html($content);
		$this->pdf->set_paper("A4", "portrait");
                $this->pdf->render();
		//set paper size
                $pdf = $this->pdf->output();
		file_put_contents($path, $pdf); 
	}

}//end class
