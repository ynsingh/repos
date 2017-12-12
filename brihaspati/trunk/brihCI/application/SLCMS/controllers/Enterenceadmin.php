<?php

/* 
 * @name Enterenceadmin.php
 * @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 * @author Sumit saxena(sumitsesaxena@gmail.com)[reconcile fees/roll number genrate/hallticket/sticker/attendence]
 * @author Sharad Singh(sharad23nov@gmail.com) All Graphical reports generation
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

	public function viewhallticket(){

	$data=array('ca_hallticketstatus' =>'Y');
        $stud_master = $this->commodel->get_listspficemore('admissionstudent_centerallocation','ca_asmid,ca_rollno',$data);
	$data['stud_master'] = $stud_master;
			
        $this->load->view('enterenceadmin/hallticket',$data);
        }

        public function generatehallticket(){

                $data=array('ca_hallticketstatus' => NULL , 'ca_rollno !=' => NULL);
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
		//if(!empty($centerlist)){
		if(!empty($stud_master)){
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

			$cname = $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_name','eec_id',$centerid)->eec_name;
			$cadd = $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_address','eec_id',$centerid)->eec_address;
			$ccity = $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_city','eec_id',$centerid)->eec_city;
			$venue= $cname.','.$cadd.','.$ccity;
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
    			$updhallstatus = $this->commodel->updaterec('admissionstudent_centerallocation', $master,'ca_asmid',$asmid);
			$this->logger->write_logmessage("update", "Hall ticket status update yes in admissionstudent_centerallocation");
                    	$this->logger->write_dblogmessage("update", "Hall ticket status update yes in admissionstudent_centerallocation" );
			
        }
		$message = 'Hall Ticket Successfully Generated for whom roll no. generated.';
		$this->session->set_flashdata('success',$message);
	 	redirect('enterenceadmin/viewhallticket');
	}
	//else{
		//$message = 'Hall Ticket is not Generated because roll no is not generated.So first click on the roll no generation.';
		//$this->session->set_flashdata('err_message',$message);
	 	//redirect('enterenceadmin/viewhallticket');
	//}
	redirect('enterenceadmin/viewhallticket');
 }

	public function viewstikerlist(){
		//$this->examcenter = $this->commodel->get_listmore('admissionstudent_enterenceexamcenter','eec_name,eec_city,eec_id');
        	$this->centerlist = $this->commodel->get_distinctrecord('admissionstudent_centerallocation','ca_centername,ca_stickerstatus','');
		
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
		//print_r($exmceter);die;
		//$cname = $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_name','eec_id',$exmceter)->eec_name;
		if(!empty($exmceter)){
			$attselectdata=array('ca_asmid','ca_rollno','ca_centername','ca_prgid');
			$attrecord=array(
				'ca_hallticketstatus' => 'Y','ca_centername'  => $exmceter, 'ca_rollno !=' => NULL
			);
			//print_r($attrecord);die;
       			$getsticker = $this->commodel->get_listspficemore('admissionstudent_centerallocation',$attselectdata,$attrecord);
			//print_r($getsticker);die;
			$this->genstickerpdf($getsticker);
		}
		
		else{
		//collect the distinct list of center
			$clist=$this->commodel->get_distinctrecord('admissionstudent_centerallocation','ca_centername','');
			foreach($clist as $row1){
				$exmceter1 = $row1->ca_centername;
				if(!empty($exmceter1)){
					$attselectdata=array('ca_hallticketstatus' => 'Y','ca_asmid','ca_rollno','ca_centername','ca_prgid');
					$attrecord=array('ca_centername'  => $exmceter1,'ca_rollno !=' => NULL);
       					$getsticker1 = $this->commodel->get_listspficemore('admissionstudent_centerallocation',$attselectdata,$attrecord);
					$this->logger->write_logmessage("update", "Attendence sheet data for each enter".$getsticker1);
					//print_r($getatt1);
					$this->genstickerpdf($getsticker1);
				}
			}
		}	
		//$message = '<h3 style="font-size:20px;text-align:center;background-color:#DFF2BF;width:50%;height:30px;color:green;">Centerwise attendance sheet generated Successfully .</h3>';
		//$this->session->set_flashdata('success',$message);
		$flag = true;
			if($getsticker){
				$message = 'Centerwise sticker sheet generated Successfully .';
				$this->session->set_flashdata('success',$message);
				redirect('enterenceadmin/viewstikerlist');
			}
			else{
				$message = 'Centerwise sticker sheet not generated.So first click on hall ticket generation.';
				$this->session->set_flashdata('err_message',$message);
				redirect('enterenceadmin/viewstikerlist');
				}
	 	
	 	redirect('enterenceadmin/viewstikerlist',$data, TRUE);

	 }
	//sticker pdf create function
        public function genstickerpdf($getsticker){
		$data['getsticker']=$getsticker;
		//get asmid to update sticker status
		foreach($getsticker as $row){
			$asmid=$row->ca_asmid;
			$cname=$row->ca_centername;
			$centerid = $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_name','eec_id',$cname)->eec_name;
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
			$pth='uploads/SLCMS/enterenceadmin_student/'.$year.'/sticker/'.$cname.'Sticker'.'.pdf';
			$this->genpdf($temp,$pth);
			$master = array(
		      		'ca_stickerstatus'   => 'Y',
	           		);
    			$this->commodel->updaterec('admissionstudent_centerallocation', $master,'ca_asmid',$asmid);
			$this->logger->write_logmessage("update", "Sticker status update yes in admissionstudent_centerallocation");
                	$this->logger->write_dblogmessage("update", "Sticker status update yes in admissionstudent_centerallocation" );
		}
       
	}
	
	public function viewattendancesheet(){
		//$this->examcenter = $this->commodel->get_listmore('admissionstudent_enterenceexamcenter','eec_name,eec_city,eec_id');
		$this->centerlist = $this->commodel->get_distinctrecord('admissionstudent_centerallocation','ca_centername,ca_attendencesheetstatus','');
		
   		$this->load->view('enterenceadmin/attendencesheet');
        }

	public function generateattendence(){
		$attexmceter = $this->input->post('attexamcenter',TRUE);
		if(!empty($attexmceter)){
			$attselectdata=array('ca_asmid','ca_rollno','ca_centername','ca_prgid');
			$attrecord=array(
				'ca_hallticketstatus' => 'Y','ca_centername'  => $attexmceter,'ca_rollno !=' => NULL
			);
       			$getatt = $this->commodel->get_listspficemore('admissionstudent_centerallocation',$attselectdata,$attrecord);
			$this->genattpdf($getatt);
		}else{
			//collect the distinct list of center
			$clist = $this->commodel->get_distinctrecord('admissionstudent_centerallocation','ca_centername','');
			foreach($clist as $row1){
				$attexmceter1 = $row1->ca_centername;
				if(!empty($attexmceter1)){
					$attselectdata=array('ca_asmid','ca_rollno','ca_centername','ca_prgid');
					$attrecord=array('ca_hallticketstatus' => 'Y','ca_centername'  => $attexmceter1,'ca_rollno !=' => NULL);
       					$getatt1 = $this->commodel->get_listspficemore('admissionstudent_centerallocation',$attselectdata,$attrecord);
					$this->logger->write_logmessage("update", "Attendence sheet data foe each enter".$getatt1);
					//print_r($getatt1);
					$this->genattpdf($getatt1);
				}
			}
		}
		//$message = '<h3 style="font-size:20px;text-align:center;background-color:#DFF2BF;width:50%;height:30px;color:green;">Centerwise attendance sheet generated Successfully .</h3>';
		//$this->session->set_flashdata('success',$message);
		$flag = true;
			if($getatt){
				$message = 'Centerwise attendance sheet generated Successfully .';
				$this->session->set_flashdata('success',$message);
				redirect('enterenceadmin/viewattendancesheet');
			}
			else{
				$message = 'Centerwise attendance sheet not generated.So First Click on the sticker generation.';
				$this->session->set_flashdata('err_message',$message);
				redirect('enterenceadmin/viewattendancesheet');
				}
	
	 	redirect('enterenceadmin/viewattendancesheet');
 	}
	
	//attendence pdf create function
	public function genattpdf($getatt){
		$data['getatt']=$getatt;
			foreach($getatt as $row){
				$asmid=$row->ca_asmid;
				$centerid=$row->ca_centername;
				$cname = $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_name','eec_id',$centerid)->eec_name;
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
                    /** Graphical Reports **/

    /* bar chart b/w form submission vs time */

   public function viewgraphicalreport(){
        $this->load->model('chart_model', 'chart1');
        
        /* Form submit vs date*/

        $this->chart1->truncatetable('barchart');

        $currdate = date("Y-m-d");
        str_replace("-","",$currdate);

        // get application start and end date of current academic year

        $acadyear = $this->usermodel->getcurrentAcadYear();
        $startdate = $this->commodel->get_listrow('admissionopen','admop_acadyear',$acadyear)->row()->admop_startdate;
        $enddate = $this->commodel->get_listrow('admissionopen','admop_acadyear',$acadyear)->row()->admop_lastdate;
        $enddate = date("Y-m-d",strtotime($enddate));

        if ($enddate < $currdate)
            $performancedate = $enddate;
        else
            $performancedate = $currdate;

        $noofrec = array();
        $daterec = array();
        $start_date = date('Y-m-d', strtotime($performancedate .' -10 day'));

        for($i = 0; $i < 10 ; $i++)
        {
            $results = $this->chart1->get_chart_data($start_date);
            $istart_date = date("Ymd", strtotime($start_date));
            $insdata = array('pdate'=>$istart_date,'pval'=>$results);
            $this->commodel->insertrec('barchart', $insdata);

            $next_date = date('Y-m-d', strtotime($start_date .' +1 day'));
            $start_date =  $next_date;
        }

        $results1 = $this->chart1->get_chart_data1();

        /* Fees submit vs time */
        
        // get fee reconcile status
        $feepaid = $this->chart1->feesdata('admissionstudent_fees');
        //get total no of form submitted
        $totalsubmitted = $this->commodel->getnoofrows('admissionstudent_registration',''); 
        $feenotpaid = $totalsubmitted - $feepaid;
        
        $data['chart_data1'] = $results1['chart_data1'];
        $data['min_date'] = $results1['min_date'];
        $data['max_date'] = $results1['max_date'];
        //$data['feepaid'] = $results1['feepaid'];
        $data['feepaid'] = $feepaid;
        //$data['feenotpaid'] = $results1['feenotpaid'];
        $whdata = array('step3_status' => 1);
        $totalregistered =  $this->commodel->getnoofrows('admissionstudent_enterencestep',$whdata);
        $data['feenotpaid'] = $feenotpaid;
        $data['totalsubmitted'] = $totalsubmitted;
        $data['totalregistered'] = $totalregistered;
        
        //get list of registered application
        $registeredapplicant = $this->commodel->get_listspficarry('admissionstudent_enterencestep','','','');
        //print_r($registeredapplicant);
        $registeredapplicant = array_reverse($registeredapplicant);
        $data['registeredapplicant'] = $registeredapplicant;
        
        // Course Stat
        $allprogramid = $this->commodel->get_listspficarry('admissionopen','admop_prgname_branch','admop_acadyear',$acadyear);
        //print_r($allprogramid);  
        $data['allprogramid']  = $allprogramid;       

        

        $this->load->view('enterenceadmin/graphicalreports',$data);
   }

/****************************************Fees Reconcile start******************************************/
	//It gives all fees
	public function viewentfeereconcile_complete(){
		$whdata= array('asfee_feeamount >' => 0);
        	$this->stu_feedata = $this->commodel->get_listarry('admissionstudent_fees','*',$whdata);
		$this->message ="Entrance All Fees Detail";
		$this->load->view('enterenceadmin/ent_feesreconcile_complete');
		$this->logger->write_logmessage("view", "Reconcile all fees view.");
                $this->logger->write_dblogmessage("view", "Reconcile all fees view." );
	}

 	//It return all conciled fees
	public function viewentfeereconcile(){
		$userid = $this->session->userdata('id_user');
        	$whdata= array('asfee_feeamount >' => 0,'asfee_reconcilestatus' =>'Y');
		$this->message ="Entrance Reconcile Fees Detail";
        	$this->stu_feedata = $this->commodel->get_listarry('admissionstudent_fees','*',$whdata);
		$this->load->view('enterenceadmin/ent_feesreconcile_complete');
		$this->logger->write_logmessage("view", "Reconcile fees view.");
                $this->logger->write_dblogmessage("view", "Reconcile fees view." );
	}

	//It return to show all record of non-conciled fees
	public function fees_nonreconcile(){
		$who_reconame = $this->session->userdata('username');
        	$whdata= array('asfee_feeamount >' => 0,'asfee_reconcilestatus' =>'N');
        	$this->stu_feedata = $this->commodel->get_listarry('admissionstudent_fees','*',$whdata);
		$sfee_id=$this->uri->segment(3);

		$this->load->view('enterenceadmin/ent_feesnonreconcile');
		$this->logger->write_logmessage("view","Non reconcile fee page show.");
                $this->logger->write_dblogmessage("view","Reconcile is update." );
	}

	//It conciled one by one  fees
	public function nonreconcile_fee(){
		$whdata= array('asfee_feeamount >' => 0,'asfee_reconcilestatus' =>'N');
        	$this->stu_feedata = $this->commodel->get_listarry('admissionstudent_fees','*',$whdata);
		$who_reconame = $this->session->userdata('username');
        	$sfee_id=$this->uri->segment(3);
		
		$cdate = date('Y-m-d H:i:s');
			$fee = array(
				'asfee_reconcilestatus'  =>	'Y',
                		'asfee_whoreconcile'  	=>	$who_reconame,
                		'asfee_reconciledate'  	=>	$cdate
                		);
								
			$this->commodel->updaterec('admissionstudent_fees', $fee,'asfee_amid',$sfee_id);
			$this->logger->write_logmessage("update", "Reconcile fee is done.");
	               	$this->logger->write_dblogmessage("update", "Reconcile fee is done.");

			$flag = true;
			if($flag){
				$message = 'Enterance Admission fees reconcile successfully done !';
				$this->session->set_flashdata('success',$message);
				redirect('enterenceadmin/viewentfeereconcile');
			}
			else{
				$message = 'Enterance Admission fees reconcile not updated !';
				$this->session->set_flashdata('err_message',$message);
				$this->load->view('enterenceadmin/ent_feesnonreconcile');
				}

		$this->load->view('enterenceadmin/ent_feesnonreconcile');
		$this->logger->write_logmessage("view","Non reconcile fee page show.");
                $this->logger->write_dblogmessage("view","Non reconcile fee page show." );
	}//function close

/*****************************************************Roll number genration start***********************************************/
	public function viewcentrollno(){
		$centerlist = $this->commodel->get_distinctrecord('admissionstudent_centerallocation','ca_centername','');
		if(!empty($centerlist)){
			foreach($centerlist as $row){
				$center = $row->ca_centername;
				if(!empty($center)){
					$whdata = array('ca_centername' => $center);
					$prglist = $this->commodel->get_distinctrecord('admissionstudent_centerallocation','ca_prgid',$whdata);
					if(!empty($prglist)){
						foreach($prglist as $row1){
							$prgid = $row1->ca_prgid;
							if(!empty($prgid)){
								$whdata1 = array('ca_centername' => $center,'ca_prgid' => $prgid,'ca_rollno' => NULL); 
								$asmidlist = $this->commodel->get_listspficemore('admissionstudent_centerallocation','ca_asmid',$whdata1);
								if(!empty($asmidlist)){
									foreach($asmidlist as $row2){
										$Sid = $row2->ca_asmid;		
										if(!empty($Sid)){
											$this->generat_rollnumber($prgid,$Sid);
										}//sid empty check
									}//asm id foreach
								}//asm id list check empty
							}//prg id check empty 					
						}//program name foreach
					}//check program list empty
				}//check center name empty
			}//exm center foreach 
		}//exm centerlist empty check
		$prgname = $this->commodel->get_distinctrecord('admissionstudent_centerallocation','ca_prgid','');
		$data['prgname'] = $prgname;
		$data['examcenter'] = $centerlist;

		$this->load->view('enterenceadmin/gen_rollnumber',$data);
    	}	

	public function generat_rollnumber($prgid,$Sid){
		if($prgid<=9){
			$prgid = '0'.$prgid;
		}				
		$ydate = date('Y');
		$rollno = '';
		$datas = $ydate.$prgid;
			
		$max = $this->commodel->get_listspficemore('admissionstudent_centerallocation','MAX(ca_rollno) AS maxca_rollno',"ca_rollno LIKE '$datas%'");
			
		foreach($max as $row){
			$maxrollno = $row->maxca_rollno;
		}
		if((!empty($maxrollno))||$maxrollno>0)
		{
			$rollno = $maxrollno+1;
		}
		else{
			$rollno = $ydate.$prgid.'0001';
		}
		//$cid = $this->commodel->get_listspfic1('admissionstudent_master','asm_enterenceexamcenter','asm_id',$Sid)->asm_enterenceexamcenter;
		//$cname = $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_name','eec_id',$cid)->eec_name;
		//$clocation = $this->commodel->get_listspfic1('admissionstudent_enterenceexamcenter','eec_city','eec_id',$cid)->eec_city;
		//$pegid = $this->commodel->get_listspfic1('admissionstudent_master','asm_coursename','asm_id',$Sid)->asm_coursename;

		$is_rollno = $this->commodel->isduplicate('admissionstudent_centerallocation','ca_rollno',$rollno);
		if(!($is_rollno)){
			$center = array(
		  		'ca_rollno'	     =>	$rollno,
				//'ca_centerlocation'  => $clocation,
				//'ca_centername'	     => $cname,
				//'ca_prgid'	     => $pegid
		      	 );
		
		$this->commodel->updaterec('admissionstudent_centerallocation',$center,'ca_asmid',$Sid);
		$this->logger->write_logmessage("update", "Admission Step 4 update roll no in centerallocation table.");
                $this->logger->write_dblogmessage("update", "Admission Step 4 update roll no in centerallocation table." );
				
		//update student master table(application_no)
		$master = array(
		       	'asm_applicationno'   =>	$rollno,
	         );
					
    		$this->commodel->updaterec('admissionstudent_master', $master,'asm_id',$Sid);
		$this->logger->write_logmessage("update", "Admission Step 4 update application no in master table.");
                $this->logger->write_dblogmessage("update", "Admission Step 4 update application no in master table." );
		}//ducplicate if close
	}

	public function search_rollnumber(){
		$centerlist = $this->commodel->get_distinctrecord('admissionstudent_centerallocation','ca_centername','');
		$prgname = $this->commodel->get_distinctrecord('admissionstudent_centerallocation','ca_prgid','');
		$data['prgname'] = $prgname;
		$data['examcenter'] = $centerlist;

		
		
		/*if(!empty($rollexmceter.$rollprgid)){
			$attselectdata=array('ca_asmid','ca_rollno','ca_centername','ca_prgid');
			$attrecord=array(
				'ca_centername'  => $rollexmceter,
				'ca_prgid'       => $rollprgid
				
			);
       			$this->getatt = $this->commodel->get_listspficemore('admissionstudent_centerallocation',$attselectdata,$attrecord);
		}
		else{
			//collect the distinct list of center
			$clist = $this->commodel->get_distinctrecord('admissionstudent_centerallocation','ca_centername,ca_prgid','');
			foreach($clist as $row1){
				$rollexmceter1 = $row1->ca_centername;
				$rollprgid1 = $row1->ca_prgid;
				if(!empty($attexmceter1)){
					$attselectdata=array('ca_asmid','ca_rollno','ca_centername','ca_prgid');
					$attrecord=array('ca_centername'  => $attexmceter1,
							  'ca_prgid'   => $rollprgid1
						);
       					$getatt1 = $this->commodel->get_listspficemore('admissionstudent_centerallocation',$attselectdata,$attrecord);
					$this->logger->write_logmessage("update", "Attendence sheet data foe each enter".$getatt1);
				}
			}
		 }*/
		//$rollexmcenter = $this->input->post('ronoexamcenter');
		//$rollprogid    = $this->input->post('ronoprg');

		$examceter = $this->input->post('ronoexamcenter');
		$progid    = $this->input->post('ronoprg');
		if(isset($_POST['searchsticker'])){
			
			$attselectdata=array('ca_asmid','ca_rollno','ca_centername','ca_prgid');
			$attrecord=array(
				'ca_centername'  => $examceter,
				'ca_prgid'       => $progid
				
			);
       			$this->combigetatt = $this->commodel->get_listspficemore('admissionstudent_centerallocation',$attselectdata,$attrecord);
			//print_r($this->combigetatt);die;
		}

		if($examceter == TRUE){
			$rollexmcenter = $this->input->post('ronoexamcenter');
			$attselectdata=array('ca_asmid','ca_rollno','ca_centername','ca_prgid');
			$attrecord=array(
				'ca_centername'  => $rollexmcenter,
			);
       			$this->exmgetatt = $this->commodel->get_listspficemore('admissionstudent_centerallocation',$attselectdata,$attrecord);
			//print_r($this->exmgetatt);die;
		}

		elseif($progid == TRUE){
			$rollprogid    = $this->input->post('ronoprg');
			$attselectdata=array('ca_asmid','ca_rollno','ca_centername','ca_prgid');
			$attrecord=array(
				'ca_prgid' => $rollprogid,
			);
       			$this->prggetatt = $this->commodel->get_listspficemore('admissionstudent_centerallocation',$attselectdata,$attrecord);
			//print_r($this->prggetatt);die;
		}
			

		$this->load->view('enterenceadmin/gen_rollnumber',$data);
	}


   public function viewnumericalreport(){
        //$this->load->model('chart_model', 'chart1');
        

        //$this->chart1->truncatetable('barchart');

        $currdate = date("Y-m-d");
        str_replace("-","",$currdate);

        // get application start and end date of current academic year

        $acadyear = $this->usermodel->getcurrentAcadYear();
        $startdate = $this->commodel->get_listrow('admissionopen','admop_acadyear',$acadyear)->row()->admop_startdate;
        $enddate = $this->commodel->get_listrow('admissionopen','admop_acadyear',$acadyear)->row()->admop_lastdate;
        $enddate = date("Y-m-d",strtotime($enddate));

        if ($enddate < $currdate)
            $performancedate = $enddate;
        else
            $performancedate = $currdate;

        $noofrec = array();
        $daterec = array();
        $start_date = date('Y-m-d', strtotime($performancedate .' -10 day'));

/*      

        $results1 = $this->chart1->get_chart_data1();
*/
        
        // get fee reconcile status
//        $feepaid = $this->chart1->feesdata('admissionstudent_fees');
        //get total no of form submitted
        $totalsubmitted = $this->commodel->getnoofrows('admissionstudent_registration',''); 
//        $feenotpaid = $totalsubmitted - $feepaid;
        
//        $data['chart_data1'] = $results1['chart_data1'];
//        $data['min_date'] = $results1['min_date'];
//        $data['max_date'] = $results1['max_date'];
        //$data['feepaid'] = $results1['feepaid'];
//        $data['feepaid'] = $feepaid;
        //$data['feenotpaid'] = $results1['feenotpaid'];
        $whdata = array('step3_status' => 1);
        $totalregistered =  $this->commodel->getnoofrows('admissionstudent_enterencestep',$whdata);
//        $data['feenotpaid'] = $feenotpaid;
//        $data['totalsubmitted'] = $totalsubmitted;
        $data['totalregistered'] = $totalregistered;
        
        //get list of registered application
        $registeredapplicant = $this->commodel->get_listspficarry('admissionstudent_enterencestep','','','');
        //print_r($registeredapplicant);
        $registeredapplicant = array_reverse($registeredapplicant);
        $data['registeredapplicant'] = $registeredapplicant;
        
        // Course Stat
        $allprogramid = $this->commodel->get_listspficarry('admissionopen','admop_prgname_branch','admop_acadyear',$acadyear);
        $data['allprogramid']  = $allprogramid;       

        // Exam center stat

        $centerid = $this->commodel->get_listspficarry('admissionstudent_enterenceexamcenter','eec_id','','');
        $data['centerid'] = $centerid;

        $this->load->view('enterenceadmin/numericalreports',$data);
    }
}//end class


