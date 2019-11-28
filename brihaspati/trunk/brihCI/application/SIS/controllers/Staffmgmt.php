<?php

defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Staffmgmt.php
 * @author Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @author Manorama Pal (palseema30@gmail.com) Staff Profile,  Staff transfer and posting
 * re-engineering in add profile and edit profile according to tanuvas structure - 16 OCT 2017  
 * @author Om Prakash (omprakashkgp@gmail.com) Staff Position , Staff Position Archive
 */

class Staffmgmt extends CI_Controller
{
 
    	function __construct() {
        	parent::__construct();
        	$this->load->model('Common_model',"commodel");
        	$this->load->model('Login_model',"lgnmodel"); 
        	$this->load->model('SIS_model',"sismodel");
        	$this->load->model('Dependrop_model',"depmodel");
        	$this->load->model("Mailsend_model","mailmodel");
        	if(empty($this->session->userdata('id_user'))) {
            		$this->session->set_flashdata('flash_data', 'You don\'t have access!');
            		redirect('welcome');
        	}
    	}
 
    	public function index(){
    		//$this->load->view('staffmgmt/staffprofile');    
    	}
   
//get all uoid	 
	public function getempuoid(){
                $selectfield='emp_id';
                //$whdata = array ('emp_leaving' => NULL,'emp_dor>='=>date('Y-m-d'),'ul_status'=>'Fulltime','ul_dateto'=> '1000-01-01 00:00:00');
		 $cdate = date('Y-m-d');
                $whdata = "emp_leaving = 'NULL' and emp_dor>='".$cdate."' and ul_status='Fulltime' and (ul_dateto='1000-01-01 00:00:00'  or ul_dateto >='".$cdate."')";

                $joincond = 'employee_master.emp_code = uo_list.ul_empcode';
                //$emp_data['uoempid']=$this->sismodel->get_jointbrecord('uo_list',$selectfield,'employee_master',$joincond,'LEFT',$whdata);
                $empuoempid=$this->sismodel->get_jointbrecord('uo_list',$selectfield,'employee_master',$joincond,'LEFT',$whdata);
                $empuoid = array();
                foreach($empuoempid as $row){
                        $empuoid[]=$row->emp_id;
                }
                return $empuoid;
        }

//get all hod empid
        public function getemphodid(){
                $selectfield='emp_id';
                $whdata = array ('emp_leaving' => NULL,'emp_dor>='=>date('Y-m-d'),'hl_status'=>'Fulltime','hl_dateto'=> '1000-01-01 00:00:00');

                $joincond = 'employee_master.emp_code = hod_list.hl_empcode';
                //$emp_data['hodempid']=$this->sismodel->get_jointbrecord('hod_list',$selectfield,'employee_master',$joincond,'LEFT',$whdata);
                $emphodempid = $this->sismodel->get_jointbrecord('hod_list',$selectfield,'employee_master',$joincond,'LEFT',$whdata);
                $emphodid = array();
                foreach($emphodempid as $row){
                        $emphodid[]=$row->emp_id;
                }
                return $emphodid;
        }
/*    	public function getuohodempid(){
		$data['loguname']=$this->session->userdata('username');
		$rlid=$this->session->userdata('id_role');

		$selectfield='emp_id';

		$whdata = array ('emp_leaving' => NULL,'emp_dor>='=>date('Y-m-d'),'ul_status'=>'Fulltime','ul_dateto'=> '1000-01-01 00:00:00');
                $joincond = 'employee_master.emp_code = uo_list.ul_empcode';
                $data['uoempid']=$this->sismodel->get_jointbrecord('uo_list',$selectfield,'employee_master',$joincond,'LEFT',$whdata);

		$whdata = array ('emp_leaving' => NULL,'emp_dor>='=>date('Y-m-d'),'hl_status'=>'Fulltime','hl_dateto'=> '1000-01-01 00:00:00');
		$joincond = 'employee_master.emp_code = hod_list.hl_empcode';
                $data['hodempid']=$this->sismodel->get_jointbrecord('hod_list',$selectfield,'employee_master',$joincond,'LEFT',$whdata);
    	}
*/
    	public function addhead($id){
		$uname=$this->session->userdata('username');
	        if(($uname == "admin")){
			$whdata = array ('emp_id' => $id);
			$idata = array('emp_head' => "HEAD");
			$upflag=$this->sismodel->updaterec('employee_master', $idata,'emp_id',$id);
	
			$cdate = date("Y-m-d");
			$cdatetime = date('Y-m-d H:i:s');
		
			//insert record in hod list
			$empid = $id;
			$uoid = $this->sismodel->get_listspfic1('employee_master','emp_uocid','emp_id',$empid)->emp_uocid;
			$uopid = $this->lgnmodel->get_listspfic1('authorities', 'priority', 'id',$uoid)->priority;
			$hldata = array(
				'hl_userid' => $this->sismodel->get_listspfic1('employee_master','emp_userid','emp_id',$empid)->emp_userid,
				'hl_empcode' => $this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$empid)->emp_code,
				'hl_deptid' => $this->sismodel->get_listspfic1('employee_master','emp_dept_code','emp_id',$empid)->emp_dept_code,
				'hl_scid' => $this->sismodel->get_listspfic1('employee_master','emp_scid','emp_id',$empid)->emp_scid,
				'hl_uopid' => $uopid,
				'hl_datefrom' => $cdatetime,
				'hl_fromsession' => 'Forenoon',
				'hl_status' => 'Fulltime',
				'hl_creatorid' => $this->session->userdata('username'),
				'hl_creatordate' => $cdatetime,
				'hl_modifierid' => $this->session->userdata('username'),
				'hl_modifydate' => $cdatetime,
			); 
			$hiflag=$this->sismodel->insertrec('hod_list',$hldata);	
			$this->logger->write_logmessage("insert", "HEAD data insert in hod table table.".$id);
	                $this->logger->write_dblogmessage("insert", "HEAD data insert in hod table table.".$id );

			//insert record in service details
			$emppost =  $this->sismodel->get_listspfic1('employee_master','emp_post','emp_id',$empid)->emp_post;
			$pstid =$this->commodel->get_listspfic1('designation','desig_id','desig_name',$emppost)->desig_id;
			$sddata = array(
				'empsd_empid' => $empid,
				'empsd_authority' =>'Head',
				'empsd_campuscode' =>$this->sismodel->get_listspfic1('employee_master','emp_scid','emp_id',$empid)->emp_scid,
				'empsd_ucoid' =>$this->sismodel->get_listspfic1('employee_master','emp_uocid','emp_id',$empid)->emp_uocid,
				'empsd_deptid' =>$this->sismodel->get_listspfic1('employee_master','emp_dept_code','emp_id',$empid)->emp_dept_code,
				'empsd_schemeid' =>$this->sismodel->get_listspfic1('employee_master','emp_schemeid','emp_id',$empid)->emp_schemeid,
				'empsd_ddoid' =>$this->sismodel->get_listspfic1('employee_master','emp_ddoid','emp_id',$empid)->emp_ddoid,
				'empsd_worktype' =>$this->sismodel->get_listspfic1('employee_master','emp_worktype','emp_id',$empid)->emp_worktype,
				'empsd_group' =>$this->sismodel->get_listspfic1('employee_master','emp_group','emp_id',$empid)->emp_group,
				'empsd_shagpstid' =>$pstid,
				'empsd_desigcode' =>$this->sismodel->get_listspfic1('employee_master','emp_desig_code ','emp_id',$empid)->emp_desig_code ,
				'empsd_pbid' =>$this->sismodel->get_listspfic1('employee_master','emp_salary_grade','emp_id',$empid)->emp_salary_grade,
				'empsd_level' =>'',
				'empsd_gradepay' =>'',
				'empsd_pbdate' =>$cdate,
				'empsd_dojoin' =>$cdate,
				'empsd_dorelev' =>$cdate,
				'empsd_filename' =>'',
				'empsd_fsession' =>'Forenoon',
				'empsd_tsession' =>'Forenoon',
			);
			$sdiflag=$this->sismodel->insertrec('employee_servicedetail',$sddata);
			$this->logger->write_logmessage("insert", "HEAD data insert in employee_service detail table.".$id);
        	        $this->logger->write_dblogmessage("insert", "HEAD data insert in employee_service detail table.".$id );

			$this->logger->write_logmessage("insert", "HEAD data insert in employee_master table.".$id);
                	$this->logger->write_dblogmessage("insert", "HEAD data insert in employee_master table.".$id );
			$this->employeelist();
		}
	        else{
        		$this->session->set_flashdata('err_message', 'You do not have the right to add as Head.');
			$this->employeelist();
         		//redirect('staffmgmt/employeelist');
        	}
	}

	public function removehead($id){
		$uname=$this->session->userdata('username');
                if(($uname == "admin")){
//			$whdata = array ('emp_id' => $id);
			$cdatetime = date('Y-m-d H:i:s');
			$rdata = array('emp_head' => "");
			$upflag=$this->sismodel->updaterec('employee_master', $rdata,'emp_id',$id);
	//		update in hod list 
			$uoid = $this->sismodel->get_listspfic1('employee_master','emp_uocid','emp_id',$id)->emp_uocid ;
        	        $uopid = $this->lgnmodel->get_listspfic1('authorities', 'priority', 'id',$uoid)->priority;
			$whdata = array(
                        	'hl_userid' => $this->sismodel->get_listspfic1('employee_master','emp_userid','emp_id',$id)->emp_userid,
                	        'hl_empcode' => $this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$id)->emp_code,
        	                'hl_deptid' => $this->sismodel->get_listspfic1('employee_master','emp_dept_code','emp_id',$id)->emp_dept_code,
	                        'hl_scid' => $this->sismodel->get_listspfic1('employee_master','emp_scid','emp_id',$id)->emp_scid,
                        	'hl_uopid' => $uopid,
			);
        	        $hldata = array(
				'hl_dateto' => $cdatetime,
                        	'hl_tosession' => 'Forenoon',
                	        'hl_modifierid' => $this->session->userdata('username'),
        	                'hl_modifydate' => $cdatetime,
	                ); 
                	$hiflag=$this->sismodel->updaterecarry('hod_list',$hldata,$whdata); 
        	        $this->logger->write_logmessage("insert", "HEAD data insert in hod table table.".$id);
	                $this->logger->write_dblogmessage("insert", "HEAD data insert in hod table table.".$id );

			$this->logger->write_logmessage("insert", "HEAD data remove in employee_master table.".$id);
        	        $this->logger->write_dblogmessage("insert", "HEAD data remove in employee_master table.".$id );
			$this->employeelist();
		}
                else{
                        $this->session->set_flashdata('err_message', 'You do not have the right to remove as Head.');
			$this->employeelist();
                        //redirect('staffmgmt/employeelist');
                }

	}
/*	
	public function changepf($empid){
		$this->empid = $empid;
		if(isset($_POST['changepf'])) {
            		// Form Validation
            		$this->form_validation->set_rules('empcode','EmployeeCode','trim|required|xss_clean|alpha_numeric|callback_isEmpPFNoExist');
			if($this->form_validation->run() == FALSE){
                		$this->load->view('staffmgmt/changepf');
                		return;
            		}
            		else{
				//	get new pf
				$empcode=$_POST['empcode'];
				$empid=$_POST['empid'];
				// get the old pf number
				$oldpfno=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$empid)->emp_code;
				$oldeduinme= $oldpfno.'@tanuvas.org.in';
				$edrpuid=$this->lgnmodel->get_listspfic1('edrpuser','id','username',$oldeduinme)->id;
				
				// generate emalid as per new pf
				$nemail = $empcode .'@tanuvas.org.in';
				
				$upms="The Old PF no is ".$oldpfno." The new PF no is ".$empcode ;

				//update edrpuser email
				$dataedrpusr = array(
		                        'username'=> $nemail,
                    		);
				// insert record in edrpuser 
		                $this->lgnmodel->updaterec('edrpuser',$dataedrpusr,'id',$edrpuid);
                    		$this->logger->write_logmessage("update", "pf data updated in edrpuser table.".$upms);
                    		$this->logger->write_dblogmessage("update", "pf data updated in edrpuser table.".$upms );

				// update pf and email in employee master and employee master support
				$dataem = array(
                                        'emp_code'=> $empcode,
                                        'emp_email'=> $nemail,
                                );

				$this->sismodel->updaterec('employee_master', $dataem,'emp_id',$empid);
                    		$this->logger->write_logmessage("update", "pf data updated in  employee master  table.".$upms);
                    		$this->logger->write_dblogmessage("update", "pf data updated in  employee master  table.".$upms );

				$dataems = array(
                                        'ems_code'=> $empcode,
                                );

				$this->sismodel->updaterec('employee_master_support', $dataems,'ems_empid',$empid);
                    		$this->logger->write_logmessage("update", "pf data updated in employee master support  table.".$upms);
                    		$this->logger->write_dblogmessage("update", "pf data updated in  employee master support table.".$upms );

				$mess = 'Your PF updated Successfully.';
            			$sub = 'Employee PF Updated';
            			$secmail=$this->sismodel->get_listspfic1('employee_master','emp_secndemail','emp_id',$empid)->emp_secndemail;
                  		if((!empty($secmail))&&($secmail != '')&&($secmail != null)){
                         		$mails=$this->mailmodel->mailsnd($secmail,$sub,$mess,'');
                  		}
				$this->employeelist();
			}
		}
		$this->load->view('staffmgmt/changepf');
	}
*/


    /* Display Employee record */

    public function employeelist(){
	$cdate = date('Y-m-d');
	// add doris geater than current date and reason is null  in whdata
	$whdata = array ('emp_leaving ' =>NULL ,'emp_dor>=' =>$cdate);
        //  get role id and user id
        $rlid=$this->session->userdata('id_role');
        if ($rlid == 5){
                $usrid=$this->session->userdata('id_user');
                $deptid = '';
                $whdatad = array('userid' => $usrid,'roleid' => $rlid);
                $resu = $this->sismodel->get_listspficemore('user_role_type','deptid',$whdatad);
                foreach($resu as $rw){
                        $deptid=$rw->deptid;
                }
                $whdata['emp_dept_code'] = $deptid;
		$uopid=$this->sismodel->get_listspfic1('hod_list','hl_uopid','hl_deptid',$deptid)->hl_uopid;
        }
	 $selectfield ="emp_id,emp_code,emp_head,emp_photoname,emp_scid,emp_uocid,emp_dept_code,emp_schemeid,emp_specialisationid,emp_desig_code,emp_post,emp_email,emp_phone,emp_aadhaar_no,emp_name,emp_worktype";
         $whorder = "emp_name asc,emp_dept_code asc,emp_desig_code asc";
	 $this->wtyp='';
	$uname=$this->session->userdata('username');
         if(isset($_POST['filter'])) {
            //echo "ifcase post of filter";
         	$wtype = $this->input->post('wtype');
	    	$post  = $this->input->post('post');
	    	$desigid  = $this->input->post('desig');
	    	$strin = $this->input->post('strin');
            	$uoid = $this->input->post('uoff');
            	$deptid1 = $this->input->post('dept');

	    	if((!empty($wtype)) && ($wtype != 'null') && ($wtype != ' ')){
                       $whdata['emp_worktype'] = $wtype;
            	}
	    	if((!empty($uoid)) && ($uoid != 'null') && ($uoid != ' ')){
                        if($uoid != 'All'){
                               $whdata['emp_uocid']=$uoid;
                        }
            	}
	    	if((!empty($deptid1)) && ($deptid1 != 'null') && ($deptid1 != ' ')){
                         if($deptid1 != 'All'){
                                $whdata['emp_dept_code'] = $deptid1;
                        }
            	}
		if((!empty($desigid)) && ($desigid != 'null') && ($desigid != ' ')){
                         if($desigid != 'All'){
                                $whdata['emp_desig_code'] = $desigid;
                        }
                }
	    	if((!empty($post)) && ($post != 'null') && ($post != ' ')){
                        if($post != 'All'){
				$emppostname = $this->commodel->get_listspfic1('designation','desig_name','desig_id',$post)->desig_name;
                                $whdata['emp_post'] =$emppostname;
                        }
            	}
	    	else{
                        $post="All";
	    	}
	    // for string search
	            if(!empty($strin)) {
                        $whdata['emp_name LIKE ' ] ='%'.$strin.'%';
        	    }
		 $this->wtyp = $wtype;
	         $this->desigm = $post;
	    	 $this->strin = $strin;
		if(($uname == 'deancppmoffice@tanuvas.org.in')||($uname == 'deanffsoffice@tanuvas.org.in')){
			unset($whdata['emp_dept_code']);
			$whdata['emp_uocid']=$this->commodel->get_listspfic1('Department','dept_uoid','dept_id',$deptid)->dept_uoid;
		}	
		//print_r($whdata); die();
	         $data['records'] = $this->sismodel->get_orderlistspficemore('employee_master',$selectfield, $whdata,$whorder);
         }
         else{
		if(($uname == 'deancppmoffice@tanuvas.org.in')||($uname == 'deanffsoffice@tanuvas.org.in')){
			unset($whdata['emp_dept_code']);
			$whdata['emp_uocid']=$this->commodel->get_listspfic1('Department','dept_uoid','dept_id',$deptid)->dept_uoid;
		}	
         	$data['records']=$this->sismodel->get_orderlistspficemore('employee_master',$selectfield,$whdata,$whorder);
         }
	if($uname == 'ro@tanuvas.org.in'){
		$whdata = array ('emp_leaving' => NULL,'emp_dor>='=>$cdate,'ul_status'=>'Fulltime','ul_dateto'=> '1000-01-01 00:00:00');
		$joincond = 'employee_master.emp_code = uo_list.ul_empcode';
		$data['records1']=$this->sismodel->get_jointbrecord('uo_list',$selectfield,'employee_master',$joincond,'LEFT',$whdata);
	}
	$rest = substr($uname, -21);
	if($rest == 'office@tanuvas.org.in'){
		$whdata = array ('emp_leaving' => NULL,'emp_dor>='=>$cdate,'hl_uopid' =>$uopid,'hl_dateto'=> '1000-01-01 00:00:00');
                $joincond = 'employee_master.emp_code = hod_list.hl_empcode';
		if(($uname == 'deancppmoffice@tanuvas.org.in')||($uname == 'deanffsoffice@tanuvas.org.in')){
			$data['records1']='';
		}else{	
                	$data['records1']=$this->sismodel->get_jointbrecord('hod_list',$selectfield,'employee_master',$joincond,'LEFT',$whdata);
		}
	}
	//$data['records'] = $this->sismodel->get_orderlistspficemore('employee_master','*',$whdata,'emp_dept_code asc,emp_desig_code asc');
//	$data['records'] = $this->sismodel->get_list('employee_master');
	$data['uoempid']=$this->getempuoid();
        $data['hodempid']=$this->getemphodid();
        $this->logger->write_logmessage("view"," view employee list" );
        $this->logger->write_dblogmessage("view"," view employee list");
        $this->load->view('staffmgmt/employeelist',$data);
    }

    public function staffprofile(){
	$uname=$this->session->userdata('username');
	if(($uname == "admin")||($uname == "rsection@tanuvas.org.in")){
        $this->subject= $this->commodel->get_listspfic2('subject','sub_id','sub_name');
        $this->orgcode=$this->commodel->get_listspfic1('org_profile','org_code','org_id',1)->org_code;
        $this->campus=$this->commodel->get_listspfic2('study_center','sc_id','sc_name','org_code',$this->orgcode);
       // $this->uoc=$this->lgnmodel->get_list('authorities');
        /*In future this code may be replace when either campusid added in the 
         authority or authority added in campus.*/
        //$this->uoc=$this->lgnmodel->get_list('authority_map');
	$desigorder='desig_name asc';
        $this->desig= $this->commodel->get_orderlistspficemore('designation','desig_id,desig_name','',$desigorder);
	$whdata=array('sgm_pc'=> "6th");
        $this->salgrd=$this->sismodel->get_orderlistspficemore('salary_grade_master','*',$whdata,'');
	$whdata=array('sgm_pc'=> "7th");
	//$whdata=array('sgm_gradepay'=> "");
        $this->salgrdn=$this->sismodel->get_orderlistspficemore('salary_grade_master','*',$whdata,'');
        /*********************select category/community list*****************************************/
        $this->community=$this->commodel->get_listspfic2('category','cat_id','cat_name');
        $this->leavedata=$this->sismodel->get_list('leave_type_master');
        //$datawh = array('country_id' => '101');
        $this->states=$this->commodel->get_listspficarry('states','id,name','country_id',101);  
        /**********************here we check that vacancy is available or not in staff position******************************************/
                    
        if(isset($_POST['staffprofile'])) {
      
            /*Form Validation*/
            $this->form_validation->set_rules('empcode','EmployeeCode','trim|required|xss_clean|alpha_numeric|callback_isEmpPFNoExist');
            //$this->form_validation->set_rules('emppfno','EmployeePFNO','trim|required|xss_clean|alpha_numeric');
            $this->form_validation->set_rules('empname','EmployeeName','trim|required|xss_clean');
            $this->form_validation->set_rules('specialisation','Specialisation','trim|xss_clean');
            $this->form_validation->set_rules('splsubo','Specialisation Other','trim|xss_clean');
            $this->form_validation->set_rules('campus','Campus','trim|required|xss_clean');
            $this->form_validation->set_rules('uocontrol','UniversityOfficerControl','trim|required|xss_clean');
            $this->form_validation->set_rules('department','Department','trim|required|xss_clean');
            
            $this->form_validation->set_rules('schemecode','Scheme Name','trim|required|xss_clean');
            $this->form_validation->set_rules('designation','Designation','trim|required|xss_clean');
            $this->form_validation->set_rules('emppost','Employeepost','trim|required|xss_clean');
            $this->form_validation->set_rules('gender','Gender','trim|xss_clean');
            $this->form_validation->set_rules('community','Community','trim|xss_clean');
            $this->form_validation->set_rules('religion','Religion','trim|xss_clean');
            $this->form_validation->set_rules('caste','Caste','trim|xss_clean|alpha_numeric_spaces');
            $this->form_validation->set_rules('workingtype','Workingtype','trim|required|xss_clean');
            
            $this->form_validation->set_rules('emptype','Post Type','trim|required|xss_clean');
            $this->form_validation->set_rules('payband','PayBand','trim|required|xss_clean');
            $this->form_validation->set_rules('newpayband','New PayBand','trim|xss_clean');
            $this->form_validation->set_rules('basicpay','Basicpay','trim|xss_clean|numeric');
            $this->form_validation->set_rules('emolution','Emolution','trim|xss_clean|numeric');
            $this->form_validation->set_rules('empnhisidno','NHisIDno','trim|xss_clean');
            $this->form_validation->set_rules('dateofjoining','Date of Joining','trim|required|xss_clean');
            $this->form_validation->set_rules('dateofjoiningvc','Date of Joining as VC','trim|xss_clean');
        //    $this->form_validation->set_rules('pnp','Plan / Non Plan','trim|xss_clean');
            $this->form_validation->set_rules('phdstatus','Phd Status','trim|xss_clean');
            
            $this->form_validation->set_rules('Dateofphd','Date of Phd Finish','trim|xss_clean');
            $this->form_validation->set_rules('assrexam','AssrExam','trim|xss_clean');
            $this->form_validation->set_rules('assrexamdate','Date of AssrExam','xss_clean');
            $this->form_validation->set_rules('dateofretirement','Date of Retirement','trim|xss_clean');
            $this->form_validation->set_rules('dateofhgp','Date of HGP','trim|xss_clean');
            $this->form_validation->set_rules('panno','Pan No','trim|xss_clean|alpha_numeric');
            $this->form_validation->set_rules('Aadharrno','Aadhaar No','trim|xss_clean|numeric');
            
            $this->form_validation->set_rules('bankname','Bank Name','trim|xss_clean');
            //$this->form_validation->set_rules('bankbranch','Bank Branch Name','trim|xss_clean');
            $this->form_validation->set_rules('ifsccode','IFSC CODE','trim|xss_clean|alpha_numeric');
            $this->form_validation->set_rules('bankacno','Bank ACC No','trim|xss_clean|alpha_numeric');
            $this->form_validation->set_rules('DateofBirth','Date of Birth','trim|required|xss_clean');
            $this->form_validation->set_rules('fathername','Father Name','trim|xss_clean');
            $this->form_validation->set_rules('emailid','E-Mail ID','trim|xss_clean|valid_email');
            $this->form_validation->set_rules('Address','Address','trim|xss_clean');
            
            $this->form_validation->set_rules('mothertongue','MotherTongue','trim|xss_clean');
            $this->form_validation->set_rules('nativity','Nativity','trim|xss_clean');
            $this->form_validation->set_rules('phonemobileno','Phone/Mobile','trim|xss_clean|numeric');
            //some more  fields added on demand 
            $this->form_validation->set_rules('ddo','Drawing and Disbursing Officer','trim|xss_clean|required');
            $this->form_validation->set_rules('group','Group','trim|xss_clean|required');
            $this->form_validation->set_rules('orderno','Order No','trim|xss_clean');
            $this->form_validation->set_rules('phstatus','phstatus','trim|xss_clean');
            $this->form_validation->set_rules('phdetail','phdetail','trim|xss_clean|alpha_numeric_spaces');
            $this->form_validation->set_rules('Sabgroup','BloodGroup','trim|xss_clean');
            $this->form_validation->set_rules('dateofprob','Date of Probation','trim|xss_clean');
            $this->form_validation->set_rules('dateofregular','Date of Regularisation','trim|xss_clean');
            $this->form_validation->set_rules('qual','Qualification','trim|xss_clean');
            $this->form_validation->set_rules('remarks','Remarks','trim|xss_clean');
            $this->form_validation->set_rules('empgrade','Grade','trim|xss_clean');
            //more  fields added on demand
            $this->form_validation->set_rules('phddiscipline','Discipline','trim|xss_clean');
            $this->form_validation->set_rules('phdtype','phdtype','trim|xss_clean');
            $this->form_validation->set_rules('phdinstname','InstName','trim|xss_clean');
            $this->form_validation->set_rules('phdsplname','Specialisation Name','trim|xss_clean');
            $this->form_validation->set_rules('phdcollname','College Name','trim|xss_clean');
            $this->form_validation->set_rules('univdeput','univdeput','trim|xss_clean');
            $this->form_validation->set_rules('udeput','If YES','trim|xss_clean');
            $this->form_validation->set_rules('udt','If NO','trim|xss_clean');
            $this->form_validation->set_rules('leavedatefrom','Leave From','trim|xss_clean');
            $this->form_validation->set_rules('leavedateto','Leave To','trim|xss_clean');

            $this->form_validation->set_rules('netqual','NET qualified','trim|xss_clean');
            $this->form_validation->set_rules('netqualyes','NET Organiser','trim|xss_clean');
            $this->form_validation->set_rules('netqualno','NET Reason','trim|xss_clean');
            $this->form_validation->set_rules('passyear','NET passyear','trim|xss_clean');
            $this->form_validation->set_rules('netdiscipline','NET Discipline','trim|xss_clean');
            
            /*********************modification in vcr registration*****************************/
            $this->form_validation->set_rules('vcrapp','vcrapp','trim|xss_clean');
            $this->form_validation->set_rules('chapter','chapter','trim|xss_clean');
            
            $this->form_validation->set_rules('vciregno','VCIregno','trim|xss_clean');
            $this->form_validation->set_rules('vciregdate','VCIregdate','trim|xss_clean');
            $this->form_validation->set_rules('vcrvaliddate','vcrvaliddate','trim|xss_clean');
            
            $this->form_validation->set_rules('allvciregno','allvciregno','trim|xss_clean');
            $this->form_validation->set_rules('allvciregdate','allvciregdate','trim|xss_clean');
            $this->form_validation->set_rules('allvcrvaliddate','allvcrvaliddate','trim|xss_clean');
            
            /********************************************************************************/
        //    $this->form_validation->set_rules('asignname','Assignment Name','trim|xss_clean');
          //  $this->form_validation->set_rules('asignother','Assignment Others','trim|xss_clean');
            //$this->form_validation->set_rules('asigndatefrom','Assignment datefrom','trim|xss_clean');
//            $this->form_validation->set_rules('asigndateto','Assignment dateto','trim|xss_clean');
  //          $this->form_validation->set_rules('asignplace','Assignment place','trim|xss_clean');
            $this->form_validation->set_rules('secndemailid','secondary emailid','trim|xss_clean|valid_email');
            $this->form_validation->set_rules('seniorityno','Seniority No','trim|xss_clean|numeric');
            $this->form_validation->set_rules('maritalstatus','Marital Status','trim|xss_clean');
            $this->form_validation->set_rules('spousename','Spouse Name','trim|xss_clean');
            $this->form_validation->set_rules('jsession','Session','trim|xss_clean');
            $this->form_validation->set_rules('ppwpref1','Preferred Place of Working - First ','trim|xss_clean');
            $this->form_validation->set_rules('ppwpref2','Preferred Place of Working - Second ','trim|xss_clean');
            $this->form_validation->set_rules('ppwpref3','Preferred Place of Working - Third ','trim|xss_clean');
            
            $this->form_validation->set_rules('elpost','Entry level Post','trim|xss_clean');
            $this->form_validation->set_rules('elps','Entry level Pay Scale','trim|xss_clean');
            
            //Repopulate forms value
            /* if($_POST){
		$this->data['empcode']['value']=$this->input->get_post('empcode',TRUE);
	    */    
            if($this->form_validation->run() == FALSE){
                
                $this->load->view('staffmgmt/staffprofile');
                //redirect('staffmgmt/staffprofile');
                return;
            }
            //if($this->form_validation->run()==TRUE){
            else{
                //$udval='';
                $pfno=0;
                //$bank_ifsccode=$_POST['bankname']."#".$_POST['ifsccode']."#";
                $bank_ifsccode=$_POST['ifsccode'];
                $emppostname = $this->commodel->get_listspfic1('designation','desig_name','desig_id',$_POST['emppost'])->desig_name; 
               // $uocid=$this->lgnmodel->get_listspfic1('authority_map', 'authority_id', 'user_id',$_POST['uocontrol'])->authority_id;
                //$ddoid=$this->lgnmodel->get_listspfic1('authority_map', 'authority_id', 'user_id',$_POST['ddo'])->authority_id;
                //-----------change employee photo name ----------------//
                $empcode=$_POST['empcode'];
                if(!empty($_FILES['userfile']['name'])){
                $newFileName = $_FILES['userfile']['name'];
                $fileExt1 = explode('.', $newFileName);
                $file_ext = end( $fileExt1);
                $new_name = $empcode.".".$file_ext; 
		}
                else{
                   $new_name=''; 
                }
		$email = $_POST['emailid'];
		if(empty($email)){
                    $email = $empcode .'@tanuvas.org.in';
                }
                if(!empty($_POST['univdeput'])){
                    if($_POST['univdeput'] =='Yes'){
                        $udval=$_POST['univdeput'].",".$_POST['udeput'];
                    }
                    else{
                        $udval=$_POST['univdeput'].",".$_POST['udt'].",".$_POST['leavedatefrom'].",".$_POST['leavedateto'];
                       
                    }
                } 
                else{
                    $udval=NULL;
                }
                if(!empty($_POST['netqual'])){
                    if($_POST['netqual'] =='Yes'){
                        $netdetail=$_POST['netqual'].",".$_POST['netqualyes'];
                        $netpassyear = $_POST['passyear'];
                        $netdispln = $_POST['netdiscipline'];   
                    }
                    else{
                        $netdetail=$_POST['netqual'].",".$_POST['netqualno'];
                        $netpassyear=NULL;
                        $netdispln=NULL;
                    }
                    
                }
                else{
                    $netdetail=NULL;
                    $netpassyear=NULL;
                    $netdispln=NULL;
                }
                if(!empty($_POST['vcrapp'])){
                    if($_POST['vcrapp'] =='Applicable'){
                        $vcrstat="Applicable";
                        $chapter=$_POST['chapter'];
                        $vcrregno=$_POST['vciregno'];
                        $vcrregdate=$_POST['vciregdate'];
                        $vcrregvaliddate=$_POST['vcrvaliddate'];
                        
                    }
                    else{
                        $vcrstat="Not Applicable";
                        $chapter=NULL;
                        $vcrregno=NULL;
                        $vcrregdate=NULL;
                        $vcrregvaliddate=NULL;
                    }
                }
                else{
                    $vcrstat="Not Applicable";
                    $chapter=NULL;
                    $vcrregno=NULL;
                    $vcrregdate=NULL;
                    $vcrregvaliddate=NULL;
                }
		$splsubo=$_POST['splsubo'];
		if(empty($splsubo)){
			$splsubo='';
		}
                    //'emp_pnp'                   =>$_POST['pnp'],
		//-------------------------------------------------------------
		$phstat=$this->input->get_post('phstatus',TRUE);
		$phddispln=$this->input->get_post('phddiscipline',TRUE);
		$maritalstus=$this->input->get_post('maritalstatus',TRUE);
		$entlpst=$this->input->get_post('elpost',TRUE);
		$dateofjvc=$_POST['dateofjoiningvc'] ;
		$dateofhgp=$_POST['dateofhgp'] ;
		$dateofprob=$_POST['dateofprob'] ;
		$dateofregu=$_POST['dateofregular'] ;
		$dateodassrex=$_POST['assrexamdate'];
		$dateophd=$_POST['dateofphd'];

                $data = array(
                    'emp_code'                  =>$_POST['empcode'],
                   // 'emp_pfno'                =>$_POST['emppfno'],
                    'emp_name'                  =>$_POST['empname'],
                    'emp_specialisationid'      =>$_POST['specialisation'],
                    'emp_splsuboth'     		=>$splsubo,
                    
                    'emp_scid'                  =>$_POST['campus'],
                    'emp_uocid'                 =>$_POST['uocontrol'],
                   // 'emp_uocid'                 =>$uocid,
                    'emp_dept_code'             =>$_POST['department'],
                    'emp_schemeid'              =>$_POST['schemecode'],
                    'emp_desig_code'            =>$_POST['designation'],
                    //'emp_post'                  =>$_POST['emppost'],
                    'emp_post'                  =>$emppostname,
                    
                    'emp_gender'                =>$_POST['gender'],
                    'emp_community'             =>$_POST['community'],
                    'emp_religion'              =>$_POST['religion'],
                    'emp_caste'                 =>$_POST['caste'],
                  
                    
                    'emp_type_code'             =>$_POST['emptype'],
                    'emp_salary_grade'          =>$_POST['payband'],
                    'emp_salary_gradenew'       =>$_POST['newpayband'],
                    'emp_basic'                 =>$_POST['basicpay'],
                    'emp_emolution'             =>$_POST['emolution'],
                    'emp_nhisidno'              =>$_POST['empnhisidno'],
                    'emp_doj'                   =>$_POST['dateofjoining'],
                    'emp_dojvc'                 =>$_POST['dateofjoiningvc'],
                    //'emp_pnp'                   =>'',
                    'emp_phd_status'            =>$_POST['phdstatus'],
                        
                    'emp_dateofphd'             =>$_POST['dateofphd'],
                    'emp_AssrExam_status'       =>$_POST['assrexam'],
                    'emp_dateofAssrExam'        =>$_POST['assrexamdate'],
                    'emp_dor'                   =>$_POST['dateofretirement'],
                    'emp_dateofHGP'             =>$_POST['dateofhgp'],
                    'emp_pan_no'                =>$_POST['panno'],
                    
                    'emp_aadhaar_no'            =>$_POST['Aadharrno'],
                    'emp_bank_ifsc_code'        =>$bank_ifsccode,
                    'emp_bankname'       	=>$_POST['bankname'],
                    'emp_bankbranch'        	=>'',
                    'emp_bank_accno'            =>$_POST['bankacno'],
                    'emp_dob'                   =>$_POST['DateofBirth'],
                    'emp_father'                =>$_POST['fathername'],
                    'emp_email'                 =>$email,
                    
                    'emp_address'               =>$_POST['Address'],
                    'emp_mothertongue'          =>$_POST['mothertongue'],
                    'emp_citizen'               =>$_POST['nativity'],
                    'emp_phone'                 =>$_POST['phonemobileno'],
                    //more field added
                    'emp_worktype'              =>$_POST['workingtype'],
                    'emp_uocuserid'             =>$_POST['uocontrol'],
                    'emp_ddouserid'             =>$_POST['ddo'],
                    'emp_ddoid'                 =>$_POST['ddo'],
                    'emp_group'                 =>$_POST['group'],
                    'emp_apporderno'            =>$_POST['orderno'],
                    'emp_phstatus'              =>$phstat,
                    'emp_phdetail'              =>$_POST['phdetail'],
                    'emp_bloodgroup'            =>$_POST['Sabgroup'], 
                    'emp_doprobation'           =>$_POST['dateofprob'], 
                    'emp_doregular'             =>$_POST['dateofregular'], 
                    'emp_qual'                  =>$_POST['qual'], 
                    'emp_remarks'               =>$_POST['remarks'],
                    'emp_grade'			=>$_POST['empgrade'],  
                    'emp_secndemail'            =>$_POST['secndemailid'],
                    'emp_phddiscipline'         =>$phddispln,
                    'emp_phdtype'               =>$_POST['phdtype'],
                    'emp_phdinstname'           =>$_POST['phdinstname'],
                    'emp_phdcollege'            =>$_POST['phdcollname'],
                    'emp_phdspecialisation'     =>$_POST['phdsplname'],
                    'emp_phdunivdeput'          =>$udval,
                    'emp_netqualified'          =>$netdetail,
                    'emp_netpassingyear'        =>$netpassyear,
                    'emp_netdiscipline'         =>$netdispln,
                    //'emp_vciregno'              =>$_POST['vciregno'],
                    //'emp_vciregdate'            =>$_POST['vciregdate'],
                    'emp_photoname'             =>$new_name, 
		    'emp_maritalstatus'		=>$maritalstus,
		    'emp_seniortyid'		=>$_POST['seniorityno'],	    
		    'emp_spousename'		=>$_POST['spousename'],	    
		    'emp_jsession'		=>$_POST['jsession'],	    
                    'emp_entrylevelpost'	=> $entlpst,    
                    'emp_entrylevelpayscle' 	=> $_POST['elps'],    
                );
		if ((strpos($email, 'temp') === 0)||(strpos($email, $pfno) === 0)) {
                       	$passwd = $empcode;
                }else{
                
                	//generate 10 digit random password
			$passwd=$this->commodel->randNum(10);
		}
                $isdupl= $this->lgnmodel->isduplicate('edrpuser','username',$_POST['emailid']);
		if(!$isdupl){

                    /* generate the hash of password */
                    $password=md5($passwd);
                    $dataedrpusr = array(
                        'username'=> $_POST['emailid'],
                        'password'=> $password,
                        'email'=> $_POST['emailid'],
                        'componentreg'=> '*',
                        'mobile'=>$_POST['phonemobileno'],
                        'status'=>1,
                        'category_type'=>'Employee',
                        'is_verified'=>1
                    );
                    /* insert record in edrpuser */
                    $this->lgnmodel->insertrec('edrpuser',$dataedrpusr);
                    $this->logger->write_logmessage("insert", "data insert in edrpuser table.");
                    $this->logger->write_dblogmessage("insert", "data insert in edrpuser table." );
                    
                    /*get user id from login (edrpuser table)*/
                    $getid= $this->lgnmodel->get_listspfic1('edrpuser','id','username',$_POST['emailid']);
                    $usrid=$getid->id;
                    $datausrpf = array(
                        'userid'=> $usrid,
                        'firstname'=>$_POST['empname'],
                        'lang'=> 'english',
                        'mobile'=>$_POST['phonemobileno'],
                        'status'=>1
                    );
                    /* insert record in userprofile table */
                    $this->lgnmodel->insertrec('userprofile', $datausrpf);
                    $this->logger->write_logmessage("insert", "data insert in userprofile table.");
                    $this->logger->write_dblogmessage("insert", "data insert in userprofile table." );
                }//edusr
                //check for duplicate record in sis (payroll)
                $emdupl= $this->sismodel->isduplicate('employee_master','emp_email',$_POST['emailid']);
                if(!$emdupl){
                        
                    /* insert record in  employeemaster */
		    $data['emp_userid']=$usrid;
                    $this->sismodel->insertrec('employee_master', $data);
                    $this->logger->write_logmessage("insert", "data insert in employee_master table.");
                    $this->logger->write_dblogmessage("insert", "data insert in employee_master table." );

                    $empid= $this->sismodel->get_listspfic1('employee_master','emp_id','emp_email',$_POST['emailid'])->emp_id;
                    /* insert record in service details , if uo then update in uolist table  and if hod then update in hod list table */
                    $desigcode=$this->commodel->get_listspfic1('designation','desig_code','desig_id',$_POST['designation'])->desig_code;
                    //$shownap=$this->commodel->get_listspfic1('designation','desig_id','desig_name',$_POST['emp_post'])->desig_id;
                    $this->sismodel->insertsdetail($empid,$_POST['campus'],$_POST['uocontrol'],$_POST['department'],$desigcode,$_POST['schemecode'],$_POST['ddo'],$_POST['group'],$_POST['payband'],'',$_POST['emppost'],'','','',$_POST['orderno']);    
                                       
      /*              
                    if(!empty($_POST['asignname'])){
                        if($_POST['asignname'] == 'Others'){
                            $asignname=$_POST['asignname'].",".$_POST['asignother'];
                        }
                        else{
                            $asignname=$_POST['asignname'];
                        }
                    }
                    else{
                        $asignname=NULL;
                    }
                    $dataasign=array(
                        'aa_empid'              =>$empid,
                        'aa_asigname'           =>$asignname,
                        'aa_asigperiodfrom'     =>$_POST['asigndatefrom'],
                        'aa_asigperiodto'       =>$_POST['asigndateto'],
                        'aa_place'              =>$_POST['asignplace'],
                        'aa_creatorid'          =>$this->session->userdata('username'),
                        'aa_creatordate'        =>date('y-m-d'),
                        'aa_modifierid'         =>$this->session->userdata('username'),
                        'aa_modifydate'         =>date('y-m-d'),
                        
                    );
                    /* insert record in  additional assignments *
                    $this->sismodel->insertrec('additional_assignments', $dataasign);
                    $this->logger->write_logmessage("insert", "data insert in additional_assignments table.");
                    $this->logger->write_dblogmessage("insert", "data insert in additional_assignments table." );
    */                
                    $dataems = array(
                        'ems_empid'                 =>$empid,
                        'ems_code'                  =>$_POST['empcode'],
                        'ems_vci_status'            =>$vcrstat,
                        'ems_vci_statchapter'       =>$chapter,
                        'ems_vci_statregno'         =>$vcrregno,
                        'ems_vci_statregdate'       =>$vcrregdate,
                        'ems_vci_statvaliddate'     =>$vcrregvaliddate,
                    
                        'ems_vci_alliregno'         =>$_POST['allvciregno'],
                        'ems_vci_alliregdate'       =>$_POST['allvciregdate'],
                        'ems_vci_allivaliddate'     =>$_POST['allvcrvaliddate'],
                        'ems_pwplace1'                  =>$_POST['ppwpref1'],
                        'ems_pwplace2'                  =>$_POST['ppwpref2'],
                        'ems_pwplace3'                  =>$_POST['ppwpref3'],
                      
		    );
                    /* insert record in  employe_emaster_support */
                    $this->sismodel->insertrec('employee_master_support', $dataems);
                    $this->logger->write_logmessage("insert", "data insert in employee_master_support table.");
                    $this->logger->write_dblogmessage("insert", "data insert in employee_master_support table." );
                    
                    $getid= $this->lgnmodel->get_listspfic1('edrpuser','id','username',$_POST['emailid']);
                    $usrid=$getid->id;
                    $dataurt = array(
                        'userid'=> $usrid,
                        'roleid'=> 4,
                        'scid'  => $_POST['campus'],
                        'deptid'=> $_POST['department'],
                        'usertype'=>"Employee"
                    );
                    /* insert record in user_role_type */
                    $this->sismodel->insertrec('user_role_type',$dataurt);
                    $this->logger->write_logmessage("insert", "data insert in user_role_type table.");
                    $this->logger->write_dblogmessage("insert", "data insert in user_role_type table." );
                    /*************************************updating the staff position table*****************/
                   
                    $this->sismodel->updatestaffposition($_POST['campus'],$_POST['uocontrol'], $_POST['department'],$_POST['emppost'],$_POST['workingtype'],$_POST['emptype']) ;
                   
                    /*************************************close updating the staff position table*****************/
                    /* upload photo*/
                    $msg='';
                    $msgphoto='';
                    if(!empty($_FILES['userfile']['name'])){
                        $empcode=$_POST['empcode'];
                        $new_name = $empcode; 
                                                
                        $config = array(
                            'upload_path' =>  "./uploads/SIS/empphoto",
                            'allowed_types' => "gif|jpg|png|jpeg",
                            'overwrite' => TRUE,
                            'max_size' => "100000", // Can be set to particular file size 
                            'max_height' => "768",
                            'max_width' => "1024",
                            //'encrypt_name' => TRUE,
                            'file_name' => $new_name
                        );
                        $this->load->library('upload',$config);
                        if(! $this->upload->do_upload()){
                            $ferror='';
                            $error = array('error' => $this->upload->display_errors()); 
                            foreach ($error as $item => $value):
                               $ferror = $ferror ."</br>". $item .":". $value;
                            endforeach;
                            // $ferror=str_replace("\r\n","",$ferror);
                            $simsg = "The permitted size of Photo is 100kb";
                            $ferror = $simsg.$ferror;
                            $this->logger->write_logmessage("uploadphoto","photo upload in sis error", $ferror);
                            $this->logger->write_dblogmessage("uploadphoto","photo upload in sis error", $ferror);
                            $this->session->set_flashdata('err_message', $ferror);
                            $this->load->view('staffmgmt/staffprofile');
                      
                        }
                        else { 
                            $upload_data=$this->upload->data();
                            $msgphoto=" and photo" ;
                        } 
                    }//check for empphoto
                    //if sucess send mail to user with login details 
                    $sub='User Registration in Staff information System';
                    $mess="Your registration is completed. The user id ".$_POST['emailid']." and password is ".$passwd ."\r\n".'Kindly check with website:'."\r\n". site_url('welcome');
			$secmail=$this->sismodel->get_listspfic1('employee_master','emp_secndemail','emp_email',$_POST['emailid'])->emp_secndemail;
                                if((!empty($secmail))&&($secmail != '')&&($secmail != null)){
                                        $mails=$this->mailmodel->mailsnd($secmail,$sub,$mess,'');
                                }

                    $mailstoperson =$this->mailmodel->mailsnd($_POST['emailid'], $sub, $mess,'');
                    //  mail flag check 
                    if($mailstoperson){
                        //echo "in if part mail";
                        $mailmsg='Please check your mail for username and password....Mail send successfully';
                        $this->logger->write_logmessage("insert"," add user profile in edrpuser,profile and user role type ",'mail send successfully  to '.$_POST['emailid'] );
                        $this->logger->write_dblogmessage("insert"," add user profile in edrpuser,profile and user role type ",'mail send successfully  to '.$_POST['emailid'] );
                    }
                    else{
                        //echo "in else part";
                        $mailmsg='Mail does not sent';
                        $this->logger->write_logmessage("insert"," add user profile in  edrpuser,userprofile employeemaster and user role type ", "Mail does not sent to ".$_POST['emailid']);
                        $this->logger->write_dblogmessage("insert"," add user profile in edrpuser,userprofile,employeemaster and user role type ", "Mail does not sent to ".$_POST['emailid']);
                    }
                    $this->logger->write_logmessage("insert", "record insert successfully.");
                    $this->logger->write_dblogmessage("insert", "record insert successfully." );
                    $this->session->set_flashdata('success','User profile'.$msgphoto.' add successfully.'.$mailmsg);
                    redirect('staffmgmt/employeelist');
                    
                } //emif  
                else{
                    $this->session->set_flashdata('err_message', 'User profile is already exist.');
                    redirect('staffmgmt/staffprofile');
                  
                }
                   
            }//else valid true form
            
        }//post
        
    $this->load->view('staffmgmt/staffprofile');    
	}
	else{
	 $this->session->set_flashdata('err_message', 'You do not have the right to add user profile.');
	 redirect('staffmgmt/employeelist');
	}
    }
    
    /* This function has been created for get list of Department on the basis of campus */
    public function getdeptlist(){
        $scid = $this->input->post('campusname');
        $this->depmodel->getdeptlist_model($scid);
    }
    
    /* This function has been created for get list of schemes on the basis of  selected department */
    public function getdeptschemelist(){
        $deptid = $this->input->post('deptid');
       // echo json_encode("this is testing----".$dept);
        $this->depmodel->get_sschemelist($deptid);
    }
    public function isEmpPFNoExist(){
        
        $empcode = $this->input->post('empcode');
        if(!empty($empcode)){
            $is_exist= $this->sismodel->isduplicate('employee_master','emp_code',$empcode);
            if ($is_exist)
            {
                $this->form_validation->set_message('isEmpPFNoExist', 'Employee PF No ' . $empcode .' is already exist.');
                return false;
            }
            else {
                return true;
            } 
        }    
        
       
    }
    /****************************  START OPEN EDIT FORM WITH DATA *************/
    function editempprofile($id){
	 $emdupl= $this->sismodel->isduplicate('employee_master','emp_id',$id);
         if($emdupl){
        $this->roleid=$this->session->userdata('id_role');
        /*get detail of selected emplyee by passing id for edit*/
        $this->subject= $this->commodel->get_listspfic2('subject','sub_id','sub_name');
        $this->orgcode=$this->commodel->get_listspfic1('org_profile','org_code','org_id',1)->org_code;
        $this->campus=$this->commodel->get_listspfic2('study_center','sc_id','sc_name','org_code',$this->orgcode);
        //$this->uoc=$this->lgnmodel->get_list('authorities');
        $this->uoc=$this->lgnmodel->get_list('authority_map');
        $this->ddo=$this->sismodel->get_list('ddo');
	$desigorder='desig_name asc';
        $editemp_data['desig']= $this->commodel->get_orderlistspficemore('designation','desig_id,desig_name','',$desigorder);
	$whdata=array('sgm_pc'=> "6th");
        $editemp_data['salgrd']=$this->sismodel->get_orderlistspficemore('salary_grade_master','*',$whdata,'');
        //$whdata=array('sgm_gradepay'=> "");
	$whdata=array('sgm_pc'=> "7th");
        $editemp_data['salgrdn']=$this->sismodel->get_orderlistspficemore('salary_grade_master','*',$whdata,'');

        $this->states=$this->commodel->get_listspficarry('states','id,name','country_id',101); 
        /*********************select category/community list*****************************************/
        $this->community=$this->commodel->get_listspfic2('category','cat_id','cat_name');
        $this->leavedata=$this->sismodel->get_list('leave_type_master');
        $editemp_data['id'] = $id;
        $empmaster_data=$this->sismodel->get_listrow('employee_master','emp_id', $id);
        $editemp_data['editdata'] = $empmaster_data->row();
        /******************************employee master support******************************************************/
        $fieldems="ems_empid,ems_vci_status,ems_vci_statchapter,ems_vci_statregno,ems_vci_statregdate,ems_vci_statvaliddate,ems_vci_alliregno,ems_vci_alliregdate,ems_vci_allivaliddate";
        $whdataems = array ('ems_empid' => $id);
        $whorderems = '';
        $editemp_data['editems'] = $this->sismodel->get_orderlistspficemore('employee_master_support',$fieldems,$whdataems,$whorderems);
        /*********************************select additional assignments***********************************/
//	$selectfield="*";
  //      $whdata = array ('aa_empid' => $id);
    //    $whorder = 'aa_asigperiodfrom desc';
      //  $editemp_data['editasign'] = $this->sismodel->get_orderlistspficemore('additional_assignments',$selectfield,$whdata,$whorder);
        $this->load->view('staffmgmt/editempprofile',$editemp_data);     
        }
	else{
		$this->session->set_flashdata('err_message', 'You are tring to approach wrong way. Kindly follow the system process');
		redirect('home/logout');
	}
    }
    /****************************  START OPEN EDIT FORM WITH DATA *************/
    
    /****************************  START UPDATE DATA *************************/
    public function update_profile($id)
    {
        
        $this->roleid=$this->session->userdata('id_role');
        $editemp_data['id'] = $id;
        $empmaster_data=$this->sismodel->get_listrow('employee_master','emp_id', $id);
        $editemp_data['editdata'] = $empmaster_data->row();     
        if(isset($_POST['updateprofile'])) {
            /*Form Validation*/
            //$this->form_validation->set_rules('empcode','EmployeeCode','trim|required|xss_clean|alpha_numeric|callback_isEmpPFNoExist');
            $this->form_validation->set_rules('empcode','EmployeeCode','trim|required|xss_clean|alpha_numeric');
            $this->form_validation->set_rules('empname','EmployeeName','trim|required|xss_clean');
            $this->form_validation->set_rules('specialisation','Specialisation','trim|xss_clean');
		 $this->form_validation->set_rules('splsubo','Specialisation Other','trim|xss_clean');
// enabled by nks
            $this->form_validation->set_rules('campus','Campus','trim|required|xss_clean');
            $this->form_validation->set_rules('uocontrol','UniversityOfficerControl','trim|required|xss_clean');
            $this->form_validation->set_rules('department','Department','trim|required|xss_clean');           
           $this->form_validation->set_rules('schemecode','Scheme Name','trim|required|xss_clean');
            $this->form_validation->set_rules('designation','Designation','trim|required|xss_clean');
            $this->form_validation->set_rules('emppost','Employeepost','trim|xss_clean');
// enabled by nks
            $this->form_validation->set_rules('gender','Gender','trim|xss_clean');
            $this->form_validation->set_rules('community','Community','trim|xss_clean');
            $this->form_validation->set_rules('religion','Religion','trim|xss_clean');
            $this->form_validation->set_rules('caste','Caste','trim|xss_clean|alpha_numeric_spaces');
// enabled by nks
            $this->form_validation->set_rules('workingtype','Workingtype','trim|xss_clean');            
            $this->form_validation->set_rules('emptype','Post Type','trim|xss_clean');
            $this->form_validation->set_rules('payband','PayBand','trim|xss_clean');
	    $this->form_validation->set_rules('newpayband','New PayBand','trim|xss_clean');
            $this->form_validation->set_rules('dateofjoiningvc','Date of Joining as VC','trim|xss_clean');

// enabled by nks
            $this->form_validation->set_rules('basicpay','Basicpay','trim|xss_clean|numeric');
            $this->form_validation->set_rules('emolution','Emolution','trim|xss_clean|numeric');
            $this->form_validation->set_rules('empnhisidno','NHisIDno','trim|xss_clean');
            $this->form_validation->set_rules('dateofjoining','Date of Joining','trim|required|xss_clean');
        //    $this->form_validation->set_rules('pnp','Paln / Non Plan','trim|xss_clean');
            $this->form_validation->set_rules('phdstatus','Phd Status','trim|xss_clean');
            
            $this->form_validation->set_rules('Dateofphd','Date of Phd Finish','trim|xss_clean');
            $this->form_validation->set_rules('assrexam','AssrExam','xss_clean');
            $this->form_validation->set_rules('assrexamdate','Date of AssrExam','xss_clean');
            $this->form_validation->set_rules('dateofretirement','Date of Retirement','trim|xss_clean');
            $this->form_validation->set_rules('dateofhgp','Date of HGP','trim|xss_clean');
            $this->form_validation->set_rules('panno','Pan No','trim|alpha_numeric');
            $this->form_validation->set_rules('Aadharrno','Aadhaar No','trim|xss_clean|numeric');
            
            $this->form_validation->set_rules('bankname','Bank Name','trim|xss_clean');
            //$this->form_validation->set_rules('bankbranch','Bank Branch Name','trim|xss_clean');
            $this->form_validation->set_rules('ifsccode','IFSC CODE','trim|xss_clean|alpha_numeric');
            $this->form_validation->set_rules('bankacno','Bank ACC No','trim|xss_clean|alpha_numeric');
            $this->form_validation->set_rules('DateofBirth','Date of Birth','trim|required|xss_clean');
            $this->form_validation->set_rules('fathername','Father Name','trim|xss_clean');
            //$this->form_validation->set_rules('emailid','E-Mail ID','trim|required');
            $this->form_validation->set_rules('Address','Address','trim|xss_clean');
            
            $this->form_validation->set_rules('mothertongue','MotherTongue','trim|xss_clean');
            $this->form_validation->set_rules('nativity','Nativity','trim|xss_clean');
            $this->form_validation->set_rules('phonemobileno','Phone/Mobile','trim|xss_clean|numeric');
            
            //some extra field 
            $this->form_validation->set_rules('ddo','Drawing and Disbursing Officer','trim|xss_clean|required');
            $this->form_validation->set_rules('group','Group','trim|xss_clean|required');
            $this->form_validation->set_rules('orderno','Order No','trim|xss_clean');
            $this->form_validation->set_rules('phstatus','phstatus','trim|xss_clean');
            $this->form_validation->set_rules('phdetail','phdetail','trim|xss_clean|alpha_numeric_spaces');
            $this->form_validation->set_rules('Sabgroup','BloodGroup','trim|xss_clean');
            $this->form_validation->set_rules('dateofprob','Date of Probation','trim|xss_clean');
            $this->form_validation->set_rules('dateofregular','Date of Regularisation','trim|xss_clean');
            $this->form_validation->set_rules('qual','Qualification','trim|xss_clean');
            $this->form_validation->set_rules('remarks','Remarks','trim|xss_clean');
            $this->form_validation->set_rules('empgrade','Grade','trim|xss_clean');
            //more  fields added on demand
            $this->form_validation->set_rules('phddiscipline','Discipline','trim|xss_clean');
            $this->form_validation->set_rules('phdtype','phdtype','trim|xss_clean');
            $this->form_validation->set_rules('phdinstname','InstName','trim|xss_clean');
            $this->form_validation->set_rules('univdeput','univdeput','trim|xss_clean');
            $this->form_validation->set_rules('udeput','If YES','trim|xss_clean');
            $this->form_validation->set_rules('udt','If NO','trim|xss_clean');
            $this->form_validation->set_rules('leavedatefrom','Leave From','trim|xss_clean');
            $this->form_validation->set_rules('leavedateto','Leave To','trim|xss_clean');
            $this->form_validation->set_rules('netqual','NET qualified','trim|xss_clean');
            $this->form_validation->set_rules('netqualyes','NET Organiser','trim|xss_clean');
            $this->form_validation->set_rules('netqualno','NET Reason','trim|xss_clean');
            $this->form_validation->set_rules('passyear','NET passyear','trim|xss_clean');
            $this->form_validation->set_rules('netdiscipline','NET Discipline','trim|xss_clean');
            /*********************modification in vcr registration*****************************/
            $this->form_validation->set_rules('vcrapp','vcrapp','trim|xss_clean');
            $this->form_validation->set_rules('chapter','chapter','trim|xss_clean');
            
            $this->form_validation->set_rules('vciregno','VCIregno','trim|xss_clean');
            $this->form_validation->set_rules('vciregdate','VCIregdate','trim|xss_clean');
            $this->form_validation->set_rules('vcrvaliddate','vcrvaliddate','trim|xss_clean');
            
            $this->form_validation->set_rules('allvciregno','allvciregno','trim|xss_clean');
            $this->form_validation->set_rules('allvciregdate','allvciregdate','trim|xss_clean');
            $this->form_validation->set_rules('allvcrvaliddate','allvcrvaliddate','trim|xss_clean');
            
            /********************************************************************************/
            
            //$this->form_validation->set_rules('vciregno','VCIregno','trim|xss_clean');
            //$this->form_validation->set_rules('vciregdate','VCIregdate','trim|xss_clean');
            
        //    $this->form_validation->set_rules('asignname','Assignment Name','trim|xss_clean');
      //      $this->form_validation->set_rules('asignother','Assignment Others','trim|xss_clean');
  //          $this->form_validation->set_rules('asigndatefrom','Assignment datefrom','trim|xss_clean');
    //        $this->form_validation->set_rules('asigndateto','Assignment dateto','trim|xss_clean');
//            $this->form_validation->set_rules('asignplace','Assignment place','trim|xss_clean');
            $this->form_validation->set_rules('secndemailid','secondary emailid','trim|xss_clean|valid_email');

	    $this->form_validation->set_rules('seniorityno','Seniority No','trim|xss_clean|numeric');
            $this->form_validation->set_rules('maritalstatus','Marital Status','trim|xss_clean');
            $this->form_validation->set_rules('spousename','Spouse Name','trim|xss_clean');
            $this->form_validation->set_rules('session','Session','trim|xss_clean');

	    $this->form_validation->set_rules('phdsplname','Specialisation Name','trim|xss_clean');
            $this->form_validation->set_rules('phdcollname','College Name','trim|xss_clean');
	    $this->form_validation->set_rules('ppwpref1','Preferred Place of Working - First ','trim|xss_clean');
            $this->form_validation->set_rules('ppwpref2','Preferred Place of Working - Second ','trim|xss_clean');
            $this->form_validation->set_rules('ppwpref3','Preferred Place of Working - Third ','trim|xss_clean');
            $this->form_validation->set_rules('elpost','Entry level Post','trim|xss_clean');
            $this->form_validation->set_rules('elps','Entry level Pay Scale','trim|xss_clean');


            if($this->form_validation->run() == FALSE){
                //redirect('staffmgmt/editempprofile/'.$id);
                $this->load->view('staffmgmt/editempprofile',$editemp_data);
                return;
               
            }//formvalidation
            else{
		
       	    $empoldcode=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$id)->emp_code;     
            $bankname=$this->input->post('bankname');
            $ifsccode= $this->input->post('ifsccode');
            $emppostname = $this->commodel->get_listspfic1('designation','desig_name','desig_id',$_POST['emppost'])->desig_name; 
            //$emp_post= $this->input->post('emppost');
            //$emppostid= $this->commodel->get_listspfic1('designation','desig_id','desig_namedesig_id',$emp_post)->desig_id;     
           // $uocuid= $this->input->post('uocontrol');  
            //$uocid=$this->lgnmodel->get_listspfic1('authority_map', 'authority_id', 'user_id',$uocuid)->authority_id;
            //$ddoid=$this->lgnmodel->get_listspfic1('authority_map', 'authority_id', 'user_id',$_POST['ddo'])->authority_id;
            $empcode= $this->input->post('empcode');
		if(!($empcode === $empoldcode )){
			$is_exist= $this->sismodel->isduplicate('employee_master','emp_code',$empcode);
	        	if ($is_exist)
            		{
				$this->session->set_flashdata('err_message', 'Employee PF No ' . $empcode .' is already exist.');
//				$this->load->view('staffmgmt/editempprofile',$editemp_data);
	                    	redirect('staffmgmt/employeelist');
				return;
            		}
		}
            if(!empty($_FILES['userfile']['name'])){
            $newFileName = $_FILES['userfile']['name'];
            $fileExt1 = explode('.', $newFileName);
            $file_ext = end( $fileExt1);
           // $empcode=$_POST['empcode'];
            $new_name = $empcode.".".$file_ext; 
            }
            else{
                $new_name=$this->sismodel->get_listspfic1('employee_master','emp_photoname','emp_id',$id)->emp_photoname;
            }
            
            if(!empty($_POST['univdeput'])){
                if($_POST['univdeput'] =='Yes'){
                    $udval=$_POST['univdeput'].",".$_POST['udeput'];
                }
                else{
                    $udval=$_POST['univdeput'].",".$_POST['udt'].",".$_POST['leavedatefrom'].",".$_POST['leavedateto'];
                }
            } 
            else{
                $udval=NULL;
            }
            if(!empty($_POST['netqual'])){
                if($_POST['netqual'] =='Yes'){
                    $netdetail=$_POST['netqual'].",".$_POST['netqualyes'];
                    $netpass=$_POST['passyear'];
                    $netdpln=$_POST['netdiscipline'];
                }
                else{
                    $netdetail=$_POST['netqual'].",".$_POST['netqualno'];
                    $netpass=NULL;
                    $netdpln=NULL;
                }
                    
            }
            else{
                $netdetail=NULL;
                $netpass=NULL;
                $netdpln=NULL;
            }
           
		$dateofjoiningvc = $_POST['dateofjoiningvc'];
		if(empty($dateofjoiningvc)){
			$dateofjoiningvc='';
		} 
		$splsubo=$_POST['splsubo'];
                if(empty($splsubo)){
                        $splsubo='';
                }

                //'emp_pnp'                        => $this->input->post('pnp'),
            $data = array(
		'emp_code'			=> $this->input->post('empcode'),
                'emp_specialisationid'           => $this->input->post('specialisation'),
		 'emp_splsuboth'              =>$splsubo,
// enabled by nks start
                'emp_scid'                       => $this->input->post('campus'),
                //'emp_uocuserid'                  => $this->input->post('uocontrol'),
                'emp_uocid'                      => $this->input->post('uocontrol'),
                //'emp_uocid'                      => $uocid,
                'emp_dept_code'                  => $this->input->post('department'),
                'emp_schemeid'                   => $this->input->post('schemecode'),
                'emp_desig_code'                 => $this->input->post('designation'),
                
                'emp_post'                       =>$emppostname,
// enabled by nks close
                'emp_gender'                     => $this->input->post('gender'),
                'emp_community'                  => $this->input->post('community'),
                'emp_religion'                   => $this->input->post('religion'),                
                'emp_caste'                      => $this->input->post('caste'),
// enabled by nks start
                'emp_worktype'                   => $this->input->post('workingtype'),
                'emp_type_code'                  => $this->input->post('emptype'),
                'emp_salary_grade'               => $this->input->post('payband'),
		'emp_salary_gradenew'       	=>$_POST['newpayband'],
                'emp_dojvc'                 	=>$dateofjoiningvc,
// enabled by nks     close           
                'emp_basic'                      => $this->input->post('basicpay'),
                'emp_emolution'                  => $this->input->post('emolution'),
                'emp_nhisidno'                   => $this->input->post('empnhisidno'),
                'emp_doj'                        => $this->input->post('dateofjoining'),
                
                'emp_pnp'                        => '',
                'emp_phd_status'                 => $this->input->post('phdstatus'),
                'emp_dateofphd'                  => $this->input->post('dateofphd'),
                'emp_AssrExam_status'            => $this->input->post('assrexam'),
                
                'emp_dateofAssrExam'             => $this->input->post('assrexamdate'),
                'emp_dor'                        => $this->input->post('dateofretirement'),
                'emp_dateofHGP'                  => $this->input->post('dateofhgp'),
                'emp_pan_no'                     => $this->input->post('panno'),
                
                'emp_aadhaar_no'                 => $this->input->post('Aadharrno'),
                'emp_bank_ifsc_code'             => $ifsccode,
                'emp_bankname'             	=> $bankname,
                'emp_bankbranch'             	=> '',
                'emp_dob'                        => $this->input->post('DateofBirth'),
                'emp_father'                     => $this->input->post('fathername'),
                
                'emp_address'                    => $this->input->post('Address'),
                'emp_mothertongue'               => $this->input->post('mothertongue'),
                'emp_citizen'                    => $this->input->post('nativity'),
                'emp_phone'                      => $this->input->post('phonemobileno'),
                'emp_name'                       => $this->input->post('empname'),
                'emp_bank_accno'		 => $this->input->post('bankacno'),
               // 'emp_ddouserid'                  => $this->input->post('ddo'),
//// enabled by nks start
                'emp_ddoid'                      => $this->input->post('ddo'),
                'emp_ddouserid'                  => $this->input->post('ddo'),
                'emp_group'                      => $this->input->post('group'),
// enabled by nks close
                'emp_apporderno'                 => $this->input->post('orderno'),
                'emp_phstatus'                   => $this->input->post('phstatus'),
                'emp_phdetail'                   => $this->input->post('phdetail'),
                'emp_bloodgroup'                 => $this->input->post('Sabgroup'),
                'emp_doprobation'                => $this->input->post('dateofprob'), 
                'emp_doregular'                  => $this->input->post('dateofregular'), 
                'emp_qual'                       => $this->input->post('qual'), 
                'emp_remarks'                    => $this->input->post('remarks'), 
                'emp_photoname'                  => $new_name,
		'emp_grade'                      => $this->input->post('empgrade'), 
                'emp_secndemail'                 =>$_POST['secndemailid'],
                'emp_phddiscipline'              =>$this->input->post('phddiscipline'),
                'emp_phdtype'                    =>$_POST['phdtype'],
                'emp_phdinstname'                =>$_POST['phdinstname'],
		'emp_phdcollege'          	=>$_POST['phdcollname'],
                'emp_phdspecialisation'          =>$_POST['phdsplname'],
                'emp_phdunivdeput'               =>$udval,
                'emp_netqualified'               =>$netdetail,
                'emp_netpassingyear'             =>$netpass,
                'emp_netdiscipline'              =>$netdpln,
                //'emp_vciregno'                   =>$_POST['vciregno'],
		//'emp_vciregdate'                 =>$_POST['vciregdate'],   
		'emp_maritalstatus'         	 =>$_POST['maritalstatus'],
                'emp_seniortyid'            	 =>$_POST['seniorityno'],
                'emp_spousename'            	 =>$_POST['spousename'],
                'emp_jsession'            	 =>$this->input->post('jsession'),
		'emp_entrylevelpost'        => $this->input->post('elpost'),
                'emp_entrylevelpayscle'     => $this->input->post('elps'),

            );
//print_r($data);
		$msgphoto="";
            /* upload photo*/
            $msg='';
            if(!empty($_FILES['userfile']['name'])){
                $empcode=$_POST['empcode'];
                //$newFileName = $_FILES['userfile']['name'];
                // $fileExt1 = explode('.', $newFileName);
                //$file_ext = end( $fileExt1);
                $new_name = $empcode; 
                                                
                $config = array(
                    'upload_path' =>  "./uploads/SIS/empphoto",
                    'allowed_types' => "gif|jpg|png|jpeg",
                    'overwrite' => TRUE,
                    'max_size' => "100000", // Can be set to particular file size 
                    'max_height' => "768",
                    'max_width' => "1024",
                            //'encrypt_name' => TRUE,
                    'file_name' => $new_name
                );
                $this->load->library('upload',$config);
                if(! $this->upload->do_upload()){
                    $ferror='';
                    $error = array('error' => $this->upload->display_errors()); 
                    foreach ($error as $item => $value):
                    $ferror = $ferror ."</br>". $item .":". $value;
                    endforeach;
                    // $ferror=str_replace("\r\n","",$ferror);
                    $simsg = "The permitted size of Photo is 100kb";
                    $ferror = $simsg.$ferror;
                    $this->logger->write_logmessage("uploadphoto","photo upload in sis error", $ferror);
                    $this->logger->write_dblogmessage("uploadphoto","photo upload in sis error", $ferror);
                    $this->session->set_flashdata('err_message', $ferror);
                    $this->load->view('staffmgmt/staffprofile');
                      
                }
                else { 
                    $upload_data=$this->upload->data();
                    $msgphoto=" and photo" ;
                } 
            }//check for empphoto
            
            $upempdata_flag=$this->sismodel->updaterec('employee_master', $data,'emp_id',$id);
            if(!empty($_POST['vcrapp'])){
                    if($_POST['vcrapp'] =='Applicable'){
                        $vcrstat="Applicable";
                        $chapter=$_POST['chapter'];
                        $vcrregno=$_POST['vciregno'];
                        $vcrregdate=$_POST['vciregdate'];
                        $vcrregvaliddate=$_POST['vcrvaliddate'];
                    }
                    else{
                        $vcrstat="Not Applicable";
                        $chapter=NULL;
                        $vcrregno=NULL;
                        $vcrregdate=NULL;
                        $vcrregvaliddate=NULL;
                    }
                }
                else{
                    $vcrstat="Not Applicable";
                    $chapter=NULL;
                    $vcrregno=NULL;
                    $vcrregdate=NULL;
                    $vcrregvaliddate=NULL;
            }
            $dataems = array(
                //'ems_empid'                 =>$id,
               // 'ems_code'                  =>$_POST['empcode'],
                'ems_vci_status'            =>$vcrstat,
                'ems_vci_statchapter'       =>$chapter,
                   
                'ems_vci_statregno'         =>$vcrregno,
                'ems_vci_statregdate'       =>$vcrregdate,
                'ems_vci_statvaliddate'     =>$vcrregvaliddate,
                  
                'ems_vci_alliregno'         =>$_POST['allvciregno'],
                'ems_vci_alliregdate'       =>$_POST['allvciregdate'],
                'ems_vci_allivaliddate'     =>$_POST['allvcrvaliddate'],
		'ems_pwplace1'                  =>$_POST['ppwpref1'],
                'ems_pwplace2'                  =>$_POST['ppwpref2'],
                'ems_pwplace3'                  =>$_POST['ppwpref3'],
            );
            $upempdata_flag=$this->sismodel->updaterec('employee_master_support', $dataems,'ems_empid',$id);
        /*    
	    if(!empty($_POST['asignname'])){
                if($_POST['asignname'] == 'Others'){
                    $asignname=$_POST['asignname'].",".$_POST['asignother'];
                }
                else{
                    $asignname=$_POST['asignname'];
                }
            }
            else{
                $asignname=NULL;
            }
            
          */  
            /* update record in  additional assignments */
  /*           $dupcheck = array(
                'aa_empid'     =>$id,   
                'aa_asigname'  =>$asignname,
                'aa_place'    =>$_POST['asignplace']      
            ); 
            $emidexits= $this->sismodel->isduplicatemore('additional_assignments',$dupcheck);
            if(! $emidexits)
            {
                $dataasign=array(
               'aa_empid'              =>$id,
               'aa_asigname'           =>$asignname,
               'aa_asigperiodfrom'     =>$_POST['asigndatefrom'],
               'aa_asigperiodto'       =>$_POST['asigndateto'],
               'aa_place'              =>$_POST['asignplace'],
               'aa_creatorid'          =>$this->session->userdata('username'),
               'aa_creatordate'        =>date('y-m-d'),
               'aa_modifierid'         =>$this->session->userdata('username'),
               'aa_modifydate'         =>date('y-m-d'),
                        
            );
*/
                /* insert record in  additional assignments */
  /*                  $this->sismodel->insertrec('additional_assignments', $dataasign);
                    $this->logger->write_logmessage("insert", "data insert in additional_assignments table.");
                    $this->logger->write_dblogmessage("insert", "data insert in additional_assignments table." );
            }
            else{
                $dataasign=array(
               //'aa_empid'              =>$empid,
               'aa_asigname'           =>$asignname,
               'aa_asigperiodfrom'     =>$_POST['asigndatefrom'],
               'aa_asigperiodto'       =>$_POST['asigndateto'],
               'aa_place'              =>$_POST['asignplace'],
              // 'aa_creatorid'          =>$this->session->userdata('username'),
               //'aa_creatordate'        =>date('y-m-d'),
               'aa_modifierid'         =>$this->session->userdata('username'),
               'aa_modifydate'         =>date('y-m-d'),
                        
            );
                $this->sismodel->updaterec('additional_assignments', $dataasign,'aa_empid',$id);
                $this->logger->write_logmessage("insert", "data insert in additional_assignments table.");
                $this->logger->write_dblogmessage("insert", "data insert in additional_assignments table." );
            }
*/
       //    $emailid= 
            $mess = 'Your Employee data updated Successfully.';
            $sub = 'Employee Profile Updated';
	    $secmail=$this->sismodel->get_listspfic1('employee_master','emp_secndemail','emp_id',$id)->emp_secndemail;
                  if((!empty($secmail))&&($secmail != '')&&($secmail != null)){
                         $mails=$this->mailmodel->mailsnd($secmail,$sub,$mess,'');
                  }
		
            /* insert record in service details with check duplicate , if uo then update in uolist table  and if hod then update in hod list table */
           // $desigcode=$this->commodel->get_listspfic1('designation','desig_code','desig_id',$_POST['designation'])->desig_code;
           // $this->sismodel->insertsdetail($id,$_POST['campus'],$_POST['uocontrol'],$_POST['department'],$desigcode,$_POST['schemecode'],$_POST['ddo'],$_POST['group'],$_POST['payband'],'',$_POST['emppost'],'','','',$this->input->post('orderno'));

            if(!$upempdata_flag){
                $this->logger->write_logmessage("error","Error in update staff profile ", "Error in staff profile record update" );
                $this->logger->write_dblogmessage("error","Error in update staff profile", "Error in staff profile record update");
                $this->session->set_flashdata('err_message','Error in updating staff profile - ', 'error');
                $this->load->view('staffmgmt/editempprofile', $data);
            }
            else{
                $this->roleid=$this->session->userdata('id_role');
                $this->logger->write_logmessage("update","update staff profile ", " Employee record updated successfully ");
                $this->logger->write_dblogmessage("update","staff profile", "Employee record updated successfully");
                $this->session->set_flashdata('success', 'Employee data' .$msgphoto." ".'updated Successfully......'." "."["." "."Employee PF NO:"." ".$_POST['empcode']." ] .");
                if($this->roleid == 4){
                    redirect('empmgmt/viewempprofile');
                }
                else{
                   redirect('staffmgmt/editempprofile/'.$id);
                   //$this->load->view('staffmgmt/editempprofile', $data);
                   // redirect('staffmgmt/employeelist');
                }    
            }
            }//form true    
        }//closeissetform    
    }
    /****************************  END UPDATE DATA ****************************/
    
    /****************************  START stafftransfer ****************************/
    function stafftransfer(){ 
                
        //$this->usrlist=$this->sismodel->get_list('employee_master');;
        $this->orgcode=$this->commodel->get_listspfic1('org_profile','org_code','org_id',1)->org_code;
        $this->campus=$this->commodel->get_listspfic2('study_center','sc_id','sc_name','org_code',$this->orgcode);
        $this->uoc=$this->lgnmodel->get_list('authorities');
        $this->salgrd=$this->sismodel->get_list('salary_grade_master'); 
        $transtype=$this->uri->segment(3);
       // echo "uriseg===".$this->uri->segment(3);
        if(!empty($transtype)){
           //$datatype['ttype']=$transtype;
        }
        else{
            $transtype='singletransfer';
            //$datatype['ttype']=$transtype;
        }
        $datatype['ttype']=$transtype;
        //$this->desig= $this->commodel->get_listspfic2('designation','desig_id','desig_name');
        if(isset($_POST['stafftransfer'])){
            /* Form validation*/
            $this->form_validation->set_rules('registrarname','RegistrarName','trim|required|xss_clean|alpha_numeric_spaces');
            $this->form_validation->set_rules('designation','Designation','trim|required|xss_clean');
            $this->form_validation->set_rules('usono','universitysancofficerno','trim|xss_clean');
            $this->form_validation->set_rules('rcno','RcNo','trim|required|xss_clean');
            $this->form_validation->set_rules('subject','Subject','trim|required|xss_clean');
            $this->form_validation->set_rules('referenceno','ReferenceNo','trim|xss_clean');
            $this->form_validation->set_rules('employeetype','EmployeeType','trim|required|xss_clean');
            $this->form_validation->set_rules('empname','Employee Name','trim|required|xss_clean');
            $this->form_validation->set_rules('uocfrom','uocFrom','trim|required|xss_clean');
            $this->form_validation->set_rules('uocontrolto','uocontrolTo','trim|required|xss_clean');
            $this->form_validation->set_rules('deptfrom','DepartmentFrom','trim|required|xss_clean');
            $this->form_validation->set_rules('deptto','DepartmentTo','trim|required|xss_clean');
            $this->form_validation->set_rules('desigfrom','DesignationFrom','trim|required|xss_clean');
            $this->form_validation->set_rules('desigto','DesignationTo','trim|required|xss_clean');
            $this->form_validation->set_rules('postfrom','PostFrom','trim|required|xss_clean');
            $this->form_validation->set_rules('postto','PostTo','trim|required|xss_clean');
            $this->form_validation->set_rules('ttadetail','TTADetail','trim|xss_clean');
            $this->form_validation->set_rules('dateofrelief','Dateofrelief','trim|xss_clean');
            $this->form_validation->set_rules('expdoj','expecteddoj','trim|xss_clean');
            //$this->form_validation->set_rules('postto','PostTo','trim|required|xss_clean');
            $this->form_validation->set_rules('emailsentto','EmailSentto','trim|required|xss_clean');
            $this->form_validation->set_rules('schemfrom','Scheme Name From','trim|xss_clean');
            $this->form_validation->set_rules('schemto','Scheme Name To','trim|xss_clean');
            $this->form_validation->set_rules('emptypeto','Post Type To','trim|xss_clean');
            $this->form_validation->set_rules('empmutual','Employee Name for Mutual Transfer','trim|xss_clean');
            
            $this->form_validation->set_rules('emppfno','PF No','trim|xss_clean');
            
           
            if($this->form_validation->run() == FALSE){
                redirect('staffmgmt/stafftransfer');
            }
            else{
               // $ttype=$this->input->post('ttype');
               
                $usrinputtfr_flag=false;
                $upempdata_flag=false;
                $lastentryid='';
                if($transtype == 'budgetpost'){
                    $sapostnew=$this->commodel->get_listspfic1('designation','desig_id','desig_name',$_POST['postto'])->desig_id; 
                
                }
                else{
                    $sapostnew=$_POST['postto'];
                }
                if($transtype !='mutual'){
                    $data = array(
                    'uit_registrarname'                => $this->input->post('registrarname'),
                    'uit_desig'                        => $this->input->post('designation'),
                    'uit_uso_no'                       => $this->input->post('usono'),
                    'uit_date'                         => date('y-m-d'),
                    'uit_rc_no'                        => $this->input->post('rcno'),
                    'uit_subject'                      => $this->input->post('subject'),
                
                    'uit_referenceno'                  => $this->input->post('referenceno'),
                    'uit_ordercontent'                 => $this->input->post('ordercontent'),
                    'uit_emptype'                      => $this->input->post('employeetype'),
                    'uit_uoc_from'                     => $this->input->post('uocfrom'),
                    'uit_workdept_from'                => $this->input->post('deptfrom'),
                    'uit_desig_from'                   => $this->input->post('desigfrom'),
                
                    'uit_staffname'                  => $this->input->post('empname'),
                    'uit_workingpost_from'             => $this->input->post('postfrom'),
                    'uit_scid_from'                    => $this->input->post('campusfrom'),
                    'uit_scid_to'                      => $this->input->post('campus'),
                    'uit_uoc_to'                       => $this->input->post('uocontrolto'),
                    'uit_dept_to'                      => $this->input->post('deptto'),
                    'uit_desig_to'                     => $this->input->post('desigto'),
                   // 'uit_post_to'                      => $this->input->post('postto'),
                    'uit_post_to'                      => $sapostnew,
                    'uit_tta_detail'                   => $this->input->post('ttadetail'),
                
                    'uit_dateofrelief'                 => $this->input->post('dateofrelief'),
                    'uit_dateofjoining'                => $this->input->post('expdoj'),
                    'uit_email_sentto'                 => $this->input->post('emailsentto'),
                    'uit_emptypeto'                    => $this->input->post('emptypeto'),
                    'uit_schm_from'                    => $this->input->post('schemfrom'),
                    'uit_schm_to'                      => $this->input->post('schemto'),
                    'uit_ddoid_to'                     => $this->input->post('ddo'),
                    'uit_group_to'                     => $this->input->post('group'),
                    'uit_paybandid_to'                 => $this->input->post('payband'),
                    'uit_vacanttype_to'                => $this->input->post('vacanttype'),
                    'uit_transfertype'                 => $transtype,
                
                ); 
             
                if($transtype == 'budgetpost'){
                   /********************check vacancy available or not if not then create vacancy**********/ 
                    $this->sismodel->checkvacancy($_POST['campus'],$_POST['uocontrolto'],$_POST['deptto'],
                    $_POST['schemto'],$sapostnew,$_POST['emptypeto'],$_POST['group'],$_POST['vacanttype'],$_POST['payband']);
                    //echo "check===".$_POST['campus'].$_POST['uocontrolto'].$_POST['deptto'].$_POST['schemto'].$sapostnew.
                     //$_POST['emptypeto']. $_POST['group'] . $_POST['vacanttype']. $_POST['payband'];
                   // die();
                   /***********************************************************************************/
                 
                   $usrinputtfr_flag=$this->sismodel->insertdata('user_input_transfer', $data); 
                   $lastentryid = $usrinputtfr_flag;
                }
                else{
                    $usrinputtfr_flag=$this->sismodel->insertrec('user_input_transfer', $data);
                }
               
                /* write code for update staff_position table and staff_position_archive.*/
                if(!$usrinputtfr_flag){
                    $this->logger->write_logmessage("error","Error in Staff Transfer and Posting", "Error in Staff Transfer and Posting" );
                    $this->logger->write_dblogmessage("error","Error in Staff Transfer and Posting", "Error in Staff Transfer and Posting");
                    $this->session->set_flashdata('err_message','Error in Staff Transfer and Posting - ', 'error');
                    $this->load->view('staffmgmt/stafftransfer', $data);
                }
                else{
                    /* insert record in service details with check duplicate and  update in empprofile table table */
                    $id=$_POST['empname'];
                    if($transtype == 'budgetpost'){
                        $postto=$_POST['postto'];
                    }
                    else{
                        $postto=$this->commodel->get_listspfic1('designation','desig_name','desig_id',$_POST['postto'])->desig_name;
                    }    
                    $empdata = array(
                        'emp_dept_code'    => $_POST['deptto'],
                        'emp_desig_code'   => $_POST['desigto'],
                        'emp_post'         => $postto,
                        'emp_worktype'     => $_POST['emptypeto'],
                        'emp_salary_grade' => $_POST['payband'],
                        'emp_schemeid'     => $_POST['schemto'],
                        'emp_scid'         => $_POST['campus'] ,
                        'emp_uocid'        => $_POST['uocontrolto'],
                        'emp_uocuserid'    => $_POST['uocontrolto'],
                        'emp_ddouserid'    => $_POST['ddo'],
                        'emp_ddoid'        => $_POST['ddo'],
                        'emp_group'        => $_POST['group'],
                    
                    );
                    $upempdata_flag=$this->sismodel->updaterec('employee_master', $empdata,'emp_id',$id);
                    if($transtype == 'budgetpost'){
                        $this->transferpostbudget($_POST['empname'],$lastentryid);
                        /******************update in from data(staff position ) of budgetpost************/
                        $postfrom=$this->commodel->get_listspfic1('designation','desig_id','desig_name',$_POST['postfrom'])->desig_id;
                        //echo "postbudget==".$_POST['campusfrom'].$_POST['uocfrom'].$_POST['deptfrom'].$_POST['schemfrom'].$postfrom.
                        //$postfrom,$_POST['employeetype'].$_POST['empptfrom'];
                       // die();
                        $this->sismodel->updatespbudgetpost($_POST['campusfrom'],$_POST['uocfrom'],$_POST['deptfrom'],
                        $_POST['schemfrom'],$postfrom,$_POST['employeetype'],$_POST['empptfrom']);
                        /******************update in from data(staff position ) of budgetpost*************************************/          
                    }
                    if($transtype == 'workorder'){
                        $this->workorder($_POST['empname']);
                    }
                    else{
                    /****************************************insert record in service particular************************************************/
                    $desigcode=$this->commodel->get_listspfic1('designation','desig_code','desig_id',$_POST['desigto'])->desig_code;
                    // $shownap=$this->commodel->get_listspfic1('designation','desig_id','desig_name',$_POST['emppost'])->desig_id;
                    $this->sismodel->insertsdetail($id,$_POST['campus'],$_POST['uocontrolto'],$_POST['deptto'],$desigcode,$_POST['schemto'],$_POST['ddo'],$_POST['group'],$_POST['payband'],'',$_POST['postto'],'','','',$this->input->post('usono'));
                    }  
                    /*************************************updating the staff position table******************************************************************/
                    if($transtype =='singletransfer' ||$transtype =='singlepromtion'){
                    // echo "update2==to=".$_POST['empptfrom'];
                     //echo "update".$_POST['vacanttype'];
                     //die();
                    //echo "from data====".$_POST['campusfrom'].$_POST['uocfrom'].$_POST['deptfrom'].$postfrom,$_POST['employeetype'].$_POST['empptfrom'].$_POST['schemfrom'];
                    $postfrom=$this->commodel->get_listspfic1('designation','desig_id','desig_name',$_POST['postfrom'])->desig_id;    
                    //descrease position and increase vacancy from old data(means from )
                    $this->sismodel->updatestaffposition2($_POST['campusfrom'],$_POST['uocfrom'],$_POST['deptfrom'],$postfrom,$_POST['employeetype'],$_POST['empptfrom'],$_POST['schemfrom']);
                    //echo"to datta===".$_POST['campus'].$_POST['uocontrolto'].$_POST['deptto'].$_POST['postto'].$_POST['emptypeto'].$_POST['vacanttype'];
                    //increase in position and decrease vacancy from new data(means to)
                    $this->sismodel->updatestaffposition($_POST['campus'],$_POST['uocontrolto'], $_POST['deptto'],$_POST['postto'],$_POST['emptypeto'],$_POST['vacanttype']) ;
                    }
                
                }//else$usrinputtfr_flag
                }//if not mutual
                else{
                   // $upempdata_flag=false;
                    $upempdata_flag=$this->mutualtransfer($_POST['empname'],$_POST['empmutual'],$transtype);
                    if(!$upempdata_flag){
                    $this->logger->write_logmessage("error","Error in Staff Transfer and Posting", "Error in Staff Transfer and Posting" );
                    $this->logger->write_dblogmessage("error","Error in Staff Transfer and Posting", "Error in Staff Transfer and Posting");
                    $this->session->set_flashdata('err_message','Error in Staff Transfer and Posting - ', 'error');
                    $this->load->view('staffmgmt/stafftransfer');
                    return;
                }   
                  
                }  //else mutaulcondition 
                if($upempdata_flag){
                    // echo"maincontroller2===".$upempdata_flag;           
                    /*************************************close updating the staff position table************************************************/
                    $emppfno=$this->sismodel->get_listspfic1('employee_master', 'emp_code', 'emp_id', $_POST['empname'])->emp_code;
                    $empname=$this->sismodel->get_listspfic1('employee_master', 'emp_name', 'emp_id', $_POST['empname'])->emp_name;
                    $deptto=$this->commodel->get_listspfic1('Department','dept_name','dept_id',$_POST['deptto'])->dept_name; 
                    $this->orgname=$this->commodel->get_listspfic1('org_profile','org_name','org_id',1)->org_name;
                   // $this->regname=$this->sismodel->get_listspfic1('user_input_transfer','uit_registrarname','uit_staffname',$id)->uit_registrarname;
                    //$this->uitdesig=$this->sismodel->get_listspfic1('user_input_transfer','uit_desig','uit_staffname',$id)->uit_desig;
                    $this->regname=$this->input->post('registrarname');
                    $this->uitdesig=$this->input->post('designation');
                    $mail_sent_to=$_POST['emailsentto'];
                   // $mailarray=explode(',',$mail_sent_to);
                    //if sucess send mail to user with transfer order copy 
                    $camto=$this->input->post('campus');
                    $cmpnameto=$this->commodel->get_listspfic1('study_center', 'sc_name', 'sc_id',$camto)->sc_name;
                    $year=date('Y');
                    $sub='Employee Transfer And Posting - Letter  ' ;
                    $mess="OFFICE ORDER"." \r\n"." Dear Madam/Sir,"."\r\n"." This is to inform you that you will be transferred at ".$deptto.",". $cmpnameto." with immediate effect"
                     . "\r\n"."Please find the attachment of transfer order copy"."\r\n"." Wish you all the best"."\r\n"
                     . "\r\n"."if found, any data mismatched"."\r\n"."please contact Payroll Admin"."\r\n\r\n\r\n"
                     . " Regards "."\r\n"." Payroll Admin";
                    //$this->regname."\r\n".$this->uitdesig;
                    if($transtype =='mutual'){
                        $emppfno1=$this->sismodel->get_listspfic1('employee_master', 'emp_code', 'emp_id',$_POST['empmutual'])->emp_code;
                        $attachment=$this->sismodel->gentransferordertpdf($_POST['empname']);
                        $attachment1=$this->sismodel->gentransferordertpdf($_POST['empmutual']);
                        $desired_dir = 'uploads/SIS/transferorder/'.$year;
                        $Attachfile=$desired_dir.'/'.$emppfno.'.pdf';
                        $Attachfile1=$desired_dir.'/'.$emppfno1.'.pdf';
                        $this->mailstoperson =$this->mailmodel->mailsnd($mail_sent_to,$sub,$mess,$Attachfile);
                        $this->mailstoperson =$this->mailmodel->mailsnd($mail_sent_to,$sub,$mess,$Attachfile1);
                        
                    }
                    else{
                    $attachment=$this->sismodel->gentransferordertpdf($_POST['empname']);
                    
                    $desired_dir = 'uploads/SIS/transferorder/'.$year;
                    $Attachfile=$desired_dir.'/'.$emppfno.'.pdf';
                    
                 //   echo "Attach====".$Attachfile."==desiredpath===".$desired_dir;
                   // die();
                   // $this->mailstoperson =$this->mailmodel->mailsnd($mail_sent_to, $sub, $mess,$Attachfile);
                    $this->mailstoperson =$this->mailmodel->mailsnd($mail_sent_to,$sub,$mess,$Attachfile);
                    }
                    //$this->mailmodel->mailsnd('$mail_sent_to', $sub, $mess,$Attachfile,'Sis');
                    if($this->mailstoperson){
                        //echo "in if part mail";
                        $mailmsg='Transfer and Promotion order ....Mail send successfully';
                        $this->logger->write_logmessage("insert"," Transfer and Promotion order ",'mail send successfully  to '.$_POST['emailid'] );
                        $this->logger->write_dblogmessage("insert"," Transfer and Promotion order",'mail send successfully  to '.$_POST['emailid'] );
                    }
                    else{
                        //echo "in else part";
                        $mailmsg='Mail does not sent';
                        $this->logger->write_logmessage("insert"," Transfer and Promotion order", "Mail does not sent to ".$_POST['emailid']);
                        $this->logger->write_dblogmessage("insert"," Transfer and Promotion order", "Mail does not sent to ".$_POST['emailid']);
                    }
                    $this->logger->write_logmessage("insert","Staff Transfer and Posting", " Employee transfer record insert successfully ");
                    $this->logger->write_dblogmessage("update","Staff Transfer and Posting", "Employee transfer record insert successfully");
                    $this->session->set_flashdata('success', 'Employee transfer record insert successfully ......'." "."["." "."Employee PF NO:"." ".$emppfno." and "."Employee Name:"." ".$empname." "."]");
                    redirect('staffmgmt/stafftransferlist');
                }//if $usrinputtfr_flag
            }////elseof form validation    
        }//ifpost
        $this->load->view('staffmgmt/stafftransfer', $datatype);
       
    }
   
   /****************************  END stafftransfer ****************************/

    
    
    /* This function has been created for get employee detail on the basis of  selected employee name */
    public function getempdetail(){
        $emp= $this->input->post('employee');
        $emp_data=$this->sismodel->get_listrow('employee_master','emp_id',$emp);
        $empdetail = $emp_data->result();
        if(count($empdetail)>0){
            foreach($empdetail as $detail){
                //$uocname=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id',$detail->emp_uocid)->name;
                //$deptname=$this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id',$detail->emp_dept_code)->dept_name;
                //$designame=$this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id',$detail->emp_desig_code)->desig_name;
            //    $values='uocfrom='. $detail->emp_uocid.', deptfrom=' .$detail->emp_dept_code.',desigfrom='. $detail->emp_desig_code.',postfrom='.$detail->emp_post;
                //$values=$uocname.',' .$deptname.','. $designame.','.$detail->emp_post;
                $values=$detail->emp_post.",".$detail->emp_type_code;
                     
            }
            /*$scid=$this->sismodel->get_listspfic1('employee_master', 'emp_scid', 'emp_id',$emp)->emp_scid;
            $deptcode=$this->commodel->get_listspfic1('study_center', 'sc_code', 'sc_id', $scid)->sc_code;
            $resultsc = $this->commodel->get_listrow('Department','dept_sccode', $deptcode);
            $dept_data = $resultsc->result();
            if(count($dept_data)>0){
                $dept_select_box = '';
                $dept_select_box.= '<option value="">---------------------- Select Department -------------------';
                foreach($dept_data as $dept){
                        $dept_select_box.='<option value='.$dept->dept_id.'>'.$dept->dept_name;
                }
            }  */  
            //echo json_encode($values.",".$dept_select_box);
            echo json_encode($values);
                       
        }            
    
    }
    
    /* Display Employee Tansfer record */

    public function stafftransferlist(){

	$data['records'] = $this->sismodel->get_list('user_input_transfer');
        $this->logger->write_logmessage("view"," view staff tansfer and posting list" );
        $this->logger->write_dblogmessage("view"," view staff tansfer and posting list");
        $this->load->view('staffmgmt/stafftransferlist',$data);
    }
    
    /**
    * Get Download PDF File for transfer order copy
    * @return Response
    */
    public function transferordercopy($id){
        $this->orgname=$this->commodel->get_listspfic1('org_profile','org_name','org_id',1)->org_name;
        $this->orgaddres=$this->commodel->get_listspfic1('org_profile','org_address1','org_id',1)->org_address1;
        $this->orgpincode=$this->commodel->get_listspfic1('org_profile','org_pincode','org_id',1)->org_pincode;
        $this->regname=$this->sismodel->get_listspfic1('user_input_transfer','uit_registrarname','uit_id',$id)->uit_registrarname;
        $this->uitdesig=$this->sismodel->get_listspfic1('user_input_transfer','uit_desig','uit_id',$id)->uit_desig;
        $this->data=$this->sismodel->get_listrow('user_input_transfer','uit_id',$id);
        $spec_data['detail'] = $this->data->row();
        $this->load->library('pdf');
        $this->pdf->set_paper("A4", "portrait");
        $this->pdf->load_view('staffmgmt/transferordercopy',$spec_data);
        $this->pdf->render();
        $this->pdf->stream("transferorder.pdf");
        
    }

//====================Staff Position===================================

  /*this function has been created for display the record of staff position */
  public function staffposition(){
        $whdata = array();
	//  get role id and user id
	$rlid=$this->session->userdata('id_role');
        $usrname=$this->session->userdata('username');
        if ($rlid == 5){
		if(($usrname === 'asection@tanuvas.org.in')||($usrname === 'rsection@tanuvas.org.in')){
                }else{
               	 	$usrid=$this->session->userdata('id_user');
			$deptid = '';
			$whdatad = array('userid' => $usrid,'roleid' => $rlid);
        		$resu = $this->sismodel->get_listspficemore('user_role_type','deptid',$whdatad);
	                foreach($resu as $rw){
        	                $deptid=$rw->deptid;
                	}
	                $whdata = array ('sp_dept' => $deptid);
        	        //array_push($whdata,'sp_dept' => $deptid);
		}
        }
        if ($rlid == 10){
		// get uo authid
		// default is null and for VC,  R also null but others uo pass it in filter
                $usrname=$this->session->userdata('username');
//              print_r( $usrname); die;
                if(($usrname === 'vc@tanuvas.org.in')||($usrname === 'registrar@tanuvas.org.in')||($usrname === 'asection@tanuvas.org.in')||($usrname === 'rsection@tanuvas.org.in')){
                }else{
                      $uoid=$this->lgnmodel->get_listspfic1('authorities','id','authority_email',$usrname)->id;
                      $whdata = array ('sp_uo' => $uoid);
                }
        }
        //print_r($whdata); die;
	$selectfield ="sp_emppost,sp_campusid,sp_uo,sp_dept,sp_schemecode,sp_tnt,sp_type,sp_emppost,sp_scale,sp_methodRect,sp_sancstrenght,sp_position,sp_vacant,sp_id";
        $whorder = "sp_dept asc,sp_emppost asc";
	$whdata['sp_sancstrenght >']=0;
        //$whdata = array('sp_uo'=> $uo);
        if(isset($_POST['filter'])) {
            //echo "ifcase post of filter";
            	$wtype = $this->input->post('wtype');
            	$uoid = $this->input->post('uoff');
            	$deptid = $this->input->post('dept');
            	$post  = $this->input->post('post');

	    	if((!empty($wtype)) && ($wtype != 'null') && ($wtype != ' ')){
            		$whdata['sp_tnt']= $wtype;
		}

	    	if((!empty($uoid)) && ($uoid != 'null') && ($uoid != ' ')){
		   	if($uoid != 'All'){
				if($rlid != 10){
					$whdata['sp_uo']=$uoid;
				}
			}
	   	}

	    	if((!empty($deptid)) && ($deptid != 'null') && ($deptid != ' ')){
			 if($deptid != 'All'){
				if ($rlid != 5){
					 $whdata['sp_dept'] = $deptid;
				}
			}
		}
	
	    	if((!empty($post)) && ($post != 'null') && ($post != ' ')){
			if($post != 'All'){
				 $whdata['sp_emppost'] =$post;
			}
		}
           /* 
	if($uoff != "null" && $uoff != "All" && $uoff != "")

	if(!empty($post) && (!empty($deptid))){
		if($post != 'All'){
                	$whdata['sp_emppost'] =$post;
		        if ($rlid != 5){
                		$whdata['sp_dept'] = $deptid;
			}
		}
		else{
		        if ($rlid != 5){
                		$whdata['sp_dept'] = $deptid;
			}
		}

            }
	    elseif (!empty($post)){
		if($post != 'All'){
			$whdata['sp_emppost'] =$post;
		}
	    }*/
		$this->wtyp = $wtype;
                $this->desigm = $post;	
//		if(($usrname === 'asection@tanuvas.org.in')||($usrname === 'rsection@tanuvas.org.in')){
//			 unset($whdata['emp_dept_code']);		
  //              }
 	 	$data['records'] = $this->sismodel->get_orderlistspficemore('staff_position',$selectfield, $whdata,$whorder);
         }
         else{
         	$data['records']=$this->sismodel->get_orderlistspficemore('staff_position',$selectfield,$whdata,$whorder);
         }

	//$this->result = $this->sismodel->get_orderlistspficemore('staff_position','*',$whdata,'sp_dept asc,sp_emppost asc');
        $this->logger->write_logmessage("view"," View staff position ", "Staff position details...");
        $this->logger->write_dblogmessage("view"," View staff position ", "Staff position details...");
        $this->load->view('staffmgmt/staffposition',$data);
  }

  /*this function has been created for add new staff Position record */
  public function newstaffposition(){
	$uname=$this->session->userdata('username');
        if(($uname == "admin")||($uname == "rsection@tanuvas.org.in")){
        	$this->scresult = $this->commodel->get_listspfic2('study_center','sc_id,sc_code', 'sc_name');
	        $this->desigresult = $this->commodel->get_listspfic2('designation','desig_id', 'desig_name');
        	$this->authorty = $this->lgnmodel->get_list('authorities', 'id', 'name');
	        $this->salgrd=$this->sismodel->get_list('salary_grade_master');

        	if(isset($_POST['newstaffposition'])) {
                	$this->form_validation->set_rules('campus','Campus Name','xss_clean|required');
	                $this->form_validation->set_rules('uo','U O Control','xss_clean|required');
        	        $this->form_validation->set_rules('dept','Department Name','xss_clean|required');
                	$this->form_validation->set_rules('schemecode','Scheme Name','xss_clean|required');
	        //        $this->form_validation->set_rules('pnp','Plan / Non Plan','xss_clean|required');
        	        $this->form_validation->set_rules('group','Group','xss_clean|required');
	                $this->form_validation->set_rules('tnt','Teaching non teaching ','xss_clean|required');
        	        $this->form_validation->set_rules('type','Post Type','xss_clean|required');
                	$this->form_validation->set_rules('emppost','Employee Post','xss_clean|required');
	                $this->form_validation->set_rules('grouppost','Group Post','xss_clean|required');
        	        $this->form_validation->set_rules('scale','Grade Pay','xss_clean|required');
                	$this->form_validation->set_rules('methodrect','Method of Recruitment','xss_clean|required');
	                $this->form_validation->set_rules('ss','Position Sanction Strength','trim|xss_clean|required|numeric');
        	        $this->form_validation->set_rules('p','Position Present','trim|xss_clean|required|numeric');
                	$this->form_validation->set_rules('v','Position Vacant','trim|xss_clean|required|numeric');
	                $this->form_validation->set_rules('ssper','Sanction Strength Permanent','trim|xss_clean|required|numeric');
        	        $this->form_validation->set_rules('pper','Position Permanent','trim|xss_clean|required|numeric');
                	$this->form_validation->set_rules('vper','Vacancy Permanent','trim|xss_clean|required|numeric');
	                $this->form_validation->set_rules('sstem','Sanction Strength Temporary','trim|xss_clean|required|numeric');
        	        $this->form_validation->set_rules('ptem','Position Temporary ','trim|xss_clean|required|numeric');
                	$this->form_validation->set_rules('vtem','Vacancy Temporary','trim|xss_clean|required|numeric');
	                $this->form_validation->set_rules('address1','Address','xss_clean');
        	        $this->form_validation->set_rules('ssdetail','Sanction Strength Detail','xss_clean');
                	$this->form_validation->set_rules('remarks','Remarks','xss_clean');

		       if($this->form_validation->run()==TRUE){

			        $sptnt= $this->input->post("tnt");
			        $spemppostid = $this->input->post("emppost");
			        $spemppost = $this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id', $spemppostid)->desig_name ;
			        $spss = $this->input->post("ss");
			        $spp = $this->input->post("p");
			        $spv = $this->input->post("v");
			        $spssper = $this->input->post("ssper");
			        $sppper = $this->input->post("pper");
			        $spvper = $this->input->post("vper");
			        $spsstem = $this->input->post("sstem");
			        $spptem = $this->input->post("ptem");
			        $spvtem = $this->input->post("vtem");

				if($spss == 0 ) {
			                $this->session->set_flashdata('err_message','The value of Position Sanction Strength should be greater than zero .');
			                $this->load->view('staffmgmt/newstaffposition');
					return false; 
				}

				if($spss != $spp+$spv) {
			                $this->session->set_flashdata('err_message','The value of Position Sanction Strength is not equals to sum of Position Present and Position Vacant .');
			                $this->load->view('staffmgmt/newstaffposition');
					return false; 
				}

				if($spssper != $sppper+$spvper) {
			                $this->session->set_flashdata('err_message','The value of Sanction Strength Permanent is not equals to sum of Position Permanent and Vacancy Permanent .');
			                $this->load->view('staffmgmt/newstaffposition');
					return false; 
				}
				if($spsstem != $spptem+$spvtem){
			                $this->session->set_flashdata('err_message','The value of Sanction Strength Temporary is not equals to sum of Position Temporary and Vacancy Temporary .');
			                $this->load->view('staffmgmt/newstaffposition');
					return false; 
				}
				if($spss != $spssper+$spsstem){
			                $this->session->set_flashdata('err_message','The value of Position Sanction Strength is not equals to sum of Sanction Strength Permanent and Sanction Strength Temporary .');
			                $this->load->view('staffmgmt/newstaffposition');
					return false; 
				}
				if($spp != $sppper+$spptem){
			                $this->session->set_flashdata('err_message','The value of Position Present is not equals to sum of Position Permanent and Position Temporary .');
			                $this->load->view('staffmgmt/newstaffposition');
					return false; 
				}
				if($spv != $spvper+$spvtem){
			                $this->session->set_flashdata('err_message','The value of Position Vacant is not equals to sum of Vacancy Permanent and Vacancy Temporary .');
			                $this->load->view('staffmgmt/newstaffposition');
					return false; 
				}
				$sptype=$this->input->post("type");
				$err_message='';
				$err_message1='';
				$err_message2='';
				$positionflag=true;
				$positionflag1=true;
				$positionflag2=true;
								
				$duppositionflag=0;
				$duppositionflag1=0;
				$duppositionflag2=0;

				if($sptype == "PT"){
			        $datadupposition1 = array('sp_tnt'=>$_POST['tnt'], 'sp_type'=>'Permanent', 'sp_emppost'=>$_POST['emppost'], 'sp_grppost'=>$_POST['grouppost'], 'sp_scale'=>$_POST['scale'], 'sp_methodRect'=>$_POST['methodrect'], 'sp_group'=>$_POST['group'], 'sp_uo'=>$_POST['uo'], 'sp_dept'=>$_POST['dept'], 'sp_campusid'=>$_POST['campus'], 'sp_schemecode'=>$_POST['schemecode'],'sp_org_id'=> '1' );
			        $dataposition1 = array(
				        'sp_tnt'=>$_POST['tnt'],
				        'sp_type'=>'Permanent',
				        'sp_emppost'=>$_POST['emppost'],
				        'sp_grppost'=>$_POST['grouppost'],
				        'sp_scale'=>$_POST['scale'],
				        'sp_plan_nonplan'=>'',
				        'sp_methodRect'=>$_POST['methodrect'],
				        'sp_group'=>$_POST['group'],
				        'sp_uo'=>$_POST['uo'],
				        'sp_dept'=>$_POST['dept'],
				        'sp_address1'=>$_POST['address1'],
				        'sp_address2'=>'Null',
				        'sp_address3'=>'Null',
				        'sp_campusid'=>$_POST['campus'],
				        'sp_per_temporary'=>'Null',
				        'sp_schemecode'=>$_POST['schemecode'],
				        'sp_sancstrenght'=>$_POST['ss'],
				        'sp_position'=>$_POST['p'],
				        'sp_vacant'=>$_POST['v'],
				        'sp_remarks'=>$_POST['remarks'],
				        'sp_ssdetail'=>$_POST['ssdetail'],
				        'sp_sspermanent'=>$_POST['ssper'],
				        'sp_sstemporary'=>0,
				        'sp_pospermanent'=>$_POST['pper'],
				        'sp_postemporary'=>0,
				        'sp_vpermanenet'=>$_POST['vper'],
				        'sp_vtemporary'=>0,
				        'sp_org_id'=> '1'
			        );
				$duppositionflag1 = $this->sismodel->isduplicatemore('staff_position', $datadupposition1) ;
                                if($duppositionflag1 == 1)
                                {
                                        $err_message1="Record is already exist with this combination under PT-per";
                                }
                                else{
                                        $positionflag1 = $this->sismodel->insertrec('staff_position', $dataposition1) ;
					if(!$positionflag1)
                                        {
                                                $this->logger->write_logmessage("insert"," Error in adding Staff Position ", " Data insert error .'Teaching /Non Teaching :' = $sptnt , 'Employee_Post' = $spemppost "  );
                                                $this->logger->write_dblogmessage("insert"," Error in adding Staff Position ", " Data insert error .'Teaching /Non Teaching :' = $sptnt , 'Employee_Post' = $spemppost " );
					}
                                        else{
                                                $this->logger->write_logmessage("insert"," Staff Position ", "Record added successfully. 'Teaching /Non Teaching :' = $sptnt , 'Employee_Post :' = $spemppost  " );
                                                $this->logger->write_dblogmessage("insert"," Staff Position ", "Record added successfully. 'Teaching /Non Teaching :' = $sptnt, 'Employee_Post :' = $spemppost  " );
                                	}
				}

			        $datadupposition2 = array('sp_tnt'=>$_POST['tnt'], 'sp_type'=>'Temporary', 'sp_emppost'=>$_POST['emppost'], 'sp_grppost'=>$_POST['grouppost'], 'sp_scale'=>$_POST['scale'], 'sp_methodRect'=>$_POST['methodrect'], 'sp_group'=>$_POST['group'], 'sp_uo'=>$_POST['uo'], 'sp_dept'=>$_POST['dept'], 'sp_campusid'=>$_POST['campus'], 'sp_schemecode'=>$_POST['schemecode'],'sp_org_id'=> '1' );
			        $dataposition2 = array(
				        'sp_tnt'=>$_POST['tnt'],
				        'sp_type'=>'Temporary',
				        'sp_emppost'=>$_POST['emppost'],
				        'sp_grppost'=>$_POST['grouppost'],
				        'sp_scale'=>$_POST['scale'],
				        'sp_plan_nonplan'=>'',
				        'sp_methodRect'=>$_POST['methodrect'],
				        'sp_group'=>$_POST['group'],
				        'sp_uo'=>$_POST['uo'],
				        'sp_dept'=>$_POST['dept'],
				        'sp_address1'=>$_POST['address1'],
				        'sp_address2'=>'Null',
				        'sp_address3'=>'Null',
				        'sp_campusid'=>$_POST['campus'],
				        'sp_per_temporary'=>'Null',
				        'sp_schemecode'=>$_POST['schemecode'],
				        'sp_sancstrenght'=>$_POST['ss'],
				        'sp_position'=>$_POST['p'],
				        'sp_vacant'=>$_POST['v'],
				        'sp_remarks'=>$_POST['remarks'],
				        'sp_ssdetail'=>$_POST['ssdetail'],
				        'sp_sspermanent'=>0,
				        'sp_sstemporary'=>$_POST['sstem'],
				        'sp_pospermanent'=>0,
				        'sp_postemporary'=>$_POST['ptem'],
				        'sp_vpermanenet'=>0,
				        'sp_vtemporary'=>$_POST['vtem'],
				        'sp_org_id'=> '1'
			        );
				$duppositionflag2 = $this->sismodel->isduplicatemore('staff_position', $datadupposition2) ;
                                if($duppositionflag1 == 1)
                                {
                                        $err_message2="Record is already exist with this combination under PT-temp";
                                }
                                else{
                                        $positionflag2 = $this->sismodel->insertrec('staff_position', $dataposition2) ;
					if(!$positionflag2)
                                        {
                                                $this->logger->write_logmessage("insert"," Error in adding Staff Position ", " Data insert error .'Teaching /Non Teaching :' = $sptnt , 'Employee_Post' = $spemppost "  );
                                                $this->logger->write_dblogmessage("insert"," Error in adding Staff Position ", " Data insert error .'Teaching /Non Teaching :' = $sptnt , 'Employee_Post' = $spemppost " );
					}
                                        else{
                                                $this->logger->write_logmessage("insert"," Staff Position ", "Record added successfully. 'Teaching /Non Teaching :' = $sptnt , 'Employee_Post :' = $spemppost  " );
                                                $this->logger->write_dblogmessage("insert"," Staff Position ", "Record added successfully. 'Teaching /Non Teaching :' = $sptnt, 'Employee_Post :' = $spemppost  " );
                                	}
                                }


				}else{ //PT if end and else start
			        $datadupposition = array('sp_tnt'=>$_POST['tnt'], 'sp_type'=>$_POST['type'], 'sp_emppost'=>$_POST['emppost'], 'sp_grppost'=>$_POST['grouppost'], 'sp_scale'=>$_POST['scale'], 'sp_methodRect'=>$_POST['methodrect'], 'sp_group'=>$_POST['group'], 'sp_uo'=>$_POST['uo'], 'sp_dept'=>$_POST['dept'], 'sp_campusid'=>$_POST['campus'], 'sp_schemecode'=>$_POST['schemecode'],'sp_org_id'=> '1' );

			        $dataposition = array(
				        'sp_tnt'=>$_POST['tnt'],
				        'sp_type'=>$_POST['type'],
				        'sp_emppost'=>$_POST['emppost'],
				        'sp_grppost'=>$_POST['grouppost'],
				        'sp_scale'=>$_POST['scale'],
				        'sp_plan_nonplan'=>'',
				        'sp_methodRect'=>$_POST['methodrect'],
				        'sp_group'=>$_POST['group'],
				        'sp_uo'=>$_POST['uo'],
				        'sp_dept'=>$_POST['dept'],
				        'sp_address1'=>$_POST['address1'],
				        'sp_address2'=>'Null',
				        'sp_address3'=>'Null',
				        'sp_campusid'=>$_POST['campus'],
				        'sp_per_temporary'=>'Null',
				        'sp_schemecode'=>$_POST['schemecode'],
				        'sp_sancstrenght'=>$_POST['ss'],
				        'sp_position'=>$_POST['p'],
				        'sp_vacant'=>$_POST['v'],
				        'sp_remarks'=>$_POST['remarks'],
				        'sp_ssdetail'=>$_POST['ssdetail'],
				        'sp_sspermanent'=>$_POST['ssper'],
				        'sp_sstemporary'=>$_POST['sstem'],
				        'sp_pospermanent'=>$_POST['pper'],
				        'sp_postemporary'=>$_POST['ptem'],
				        'sp_vpermanenet'=>$_POST['vper'],
				        'sp_vtemporary'=>$_POST['vtem'],
				        'sp_org_id'=> '1'
			        );
				$duppositionflag = $this->sismodel->isduplicatemore('staff_position', $datadupposition) ;
                                if($duppositionflag == 1)
                                {
                                        $err_message="Record is already exist with this combination";
                                }
                                else{
                                        $positionflag = $this->sismodel->insertrec('staff_position', $dataposition) ;
					if(!$positionflag)
                                        {
                                                $this->logger->write_logmessage("insert"," Error in adding Staff Position ", " Data insert error .'Teaching /Non Teaching :' = $sptnt , 'Employee_Post' = $spemppost "  );
                                                $this->logger->write_dblogmessage("insert"," Error in adding Staff Position ", " Data insert error .'Teaching /Non Teaching :' = $sptnt , 'Employee_Post' = $spemppost " );
					}
                                        else{
                                                $this->logger->write_logmessage("insert"," Staff Position ", "Record added successfully. 'Teaching /Non Teaching :' = $sptnt , 'Employee_Post :' = $spemppost  " );
                                                $this->logger->write_dblogmessage("insert"," Staff Position ", "Record added successfully. 'Teaching /Non Teaching :' = $sptnt, 'Employee_Post :' = $spemppost  " );
                                	}
				}

			}// PT else end

//				$duppositionflag = $this->sismodel->isduplicatemore('staff_position', $datadupposition) ;
//				if($duppositionflag == 1)
				if(($duppositionflag == 1)||($duppositionflag1 == 1)||($duppositionflag2 == 1))
				{
					$this->session->set_flashdata("err_message", $err_message.",  ".$err_message1."  ,".$err_message2);
//			                $this->session->set_flashdata("err_message", "Record is already exist with this combination......... ");
			                redirect('staffmgmt/newstaffposition');
			                return;
				}
				else{
//					$positionflag = $this->sismodel->insertrec('staff_position', $dataposition) ;
//				        if(!$positionflag)
					if((!$positionflag)||(!$positionflag1)||(!$positionflag2))
        				{
				//                $this->logger->write_logmessage("insert"," Error in adding Staff Position ", " Data insert error .'Teaching /Non Teaching :' = $sptnt , 'Employee_Post' = $spemppost "  );
				  //              $this->logger->write_dblogmessage("insert"," Error in adding Staff Position ", " Data insert error .'Teaching /Non Teaching :' = $sptnt , 'Employee_Post' = $spemppost " );
//				                $this->session->set_flashdata('err_message','Error in adding Staff Position - ' .  '.', 'error');
						$this->session->set_flashdata("err_message", $err_message.",  ".$err_message1."  ,".$err_message2);
				             //   $this->load->view('staffmgmt/newstaffposition');
        				}
				        else{
				    //            $this->logger->write_logmessage("insert"," Staff Position ", "Record added successfully. 'Teaching /Non Teaching :' = $sptnt , 'Employee_Post :' = $spemppost  " );
				      //          $this->logger->write_dblogmessage("insert"," Staff Position ", "Record added successfully. 'Teaching /Non Teaching :' = $sptnt, 'Employee_Post :' = $spemppost  " );
				                $this->session->set_flashdata("success", "Record added successfully...'Teaching /Non Teaching :' = $sptnt, 'Employee_Post : ' = $spemppost ");
						redirect('staffmgmt/staffposition');
						return;
        				}
      				}//else duplicate
    			}//validation
   		}// post
   		$this->load->view('staffmgmt/newstaffposition');
	}//close if admin
	else{
		$this->session->set_flashdata('err_message','You do not have the right to add new staff position.' .  '.', 'error');
		redirect('staffmgmt/staffposition');
	}//close else admin
   }//close function

  /*This function has been created for update the staff position record */
  public function editstaffposition($sp_id){
	$uname=$this->session->userdata('username');
        if(($uname == "admin")||($uname == "rsection@tanuvas.org.in")){
	        $this->authorty = $this->lgnmodel->get_list('authorities', 'id', 'name');
        	$sp_data_q=$this->sismodel->get_listrow('staff_position','sp_id', $sp_id);
	        if ($sp_data_q->num_rows() < 1)
        	{
	           redirect('staffmgmt/editstaffposition');
        	}
	        $editsp_data = $sp_data_q->row();

        	/* Form fields */

	        $data['tnt']= array('name' => 'tnt', 'id' => 'tnt', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_tnt, 'readonly' => 'readonly' );
        	$data['type']= array('name' => 'type', 'id' => 'type', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_type, 'readonly' => 'readonly' );
	        $data['emppost'] = array('name' => 'emppost', 'id' => 'emppost', 'maxlength' => '40', 'size' => '30', 'value' => $this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id', $editsp_data->sp_emppost)->desig_name, 'readonly' => 'readonly' );
        	$data['grouppost'] = array('name' => 'grouppost', 'id' => 'grouppost', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_grppost, 'readonly' => 'readonly' );
	        $data['scale'] = array('name' => 'scale', 'id' => 'scale', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_scale, 'readonly' => 'readonly' );
        	$data['methodrect'] = array('name' => 'methodrect', 'id' => 'methodrect', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_methodRect, 'readonly' => 'readonly' );
	        $data['group'] = array('name' => 'group', 'id' => 'group', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_group, 'readonly' => 'readonly' );
        	$data['uo'] = array('name' => 'uo', 'id' => 'uo', 'maxlength' => '40', 'size' => '30', 'value' => $this->lgnmodel->get_listspfic1('authorities', 'name', 'id', $editsp_data->sp_uo)->name, 'readonly' => 'readonly' );
	        $data['dept'] = array('name' => 'dept', 'id' => 'teachername', 'maxlength' => '40', 'size' => '30', 'value' => $this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id', $editsp_data->sp_dept)->dept_name, 'readonly' => 'readonly');
        	$data['address1'] = array('name' => 'address1', 'id' => 'address1', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_address1, );
	        $data['campus'] = array('name' => 'campus', 'id' => 'campus', 'maxlength' => '40', 'size' => '30', 'value' => $this->commodel->get_listspfic1('study_center', 'sc_name', 'sc_id', $editsp_data->sp_campusid)->sc_name, 'readonly' => 'readonly' );
//        $data['pnp'] = array('name' => 'pnp', 'id' => 'pnp', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_plan_nonplan, 'readonly' => 'readonly' );
        	$data['schemecode'] = array('name' => 'schemecode', 'id' => 'schemecode', 'maxlength' => '40', 'size' => '30', 'value' => $this->sismodel->get_listspfic1('scheme_department', 'sd_name', 'sd_id', $editsp_data->sp_schemecode)->sd_name, 'readonly' => 'readonly' );
	        $data['ss'] = array('name' => 'ss', 'id' => 'ss', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_sancstrenght, );
        	$data['p'] = array('name' => 'p', 'id' => 'p', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_position, );
        //$data['p'] = array('name' => 'p', 'id' => 'p', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_position  );
	        $data['v'] = array('name' => 'v', 'id' => 'v', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_vacant, 'readonly' => 'readonly' );
        	$data['remarks'] = array('name' => 'remarks', 'id' => 'remarks', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_remarks, );
	        $data['ssdetail'] = array('name' => 'ssdetail', 'id' => 'ssdetail', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_ssdetail, );
        	$data['ssper'] = array('name' => 'ssper', 'id' => 'ssper', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_sspermanent, 'readonly' => 'readonly' );
	        $data['sstem'] = array('name' => 'sstem', 'id' => 'sstem', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_sstemporary, 'readonly' => 'readonly' );
        	$data['pper'] = array('name' => 'pper', 'id' => 'pper', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_pospermanent, 'readonly' => 'readonly' );
	        $data['ptem'] = array('name' => 'ptem', 'id' => 'ptem', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_postemporary, 'readonly' => 'readonly' );
        	$data['vper'] = array('name' => 'vper', 'id' => 'vper', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_vpermanenet, 'readonly' => 'readonly' );
	        $data['vtem'] = array('name' => 'vtem', 'id' => 'vtem', 'maxlength' => '40', 'size' => '30', 'value' => $editsp_data->sp_vtemporary, 'readonly' => 'readonly' );
        	$data['sp_id'] = $sp_id;
		if(isset($_POST['editstaffposition'])) {
                	$this->form_validation->set_rules('campus','Campus Name','xss_clean|required');
	                $this->form_validation->set_rules('uo','U O Control','xss_clean|required');
        	        $this->form_validation->set_rules('dept','Department Name','xss_clean|required');
                	$this->form_validation->set_rules('schemecode','Scheme Name','xss_clean|required');
	  //              $this->form_validation->set_rules('pnp','Plan / Non Plan','xss_clean|required');
        	        $this->form_validation->set_rules('group','Group','xss_clean|required');
                	$this->form_validation->set_rules('tnt','Teaching non teaching ','xss_clean|required');
	                $this->form_validation->set_rules('type','Post Type','xss_clean|required');
        	        $this->form_validation->set_rules('emppost','Employee Post','xss_clean|required');
                	$this->form_validation->set_rules('grouppost','Group Post','xss_clean|required');
	                $this->form_validation->set_rules('scale','Grade Pay','xss_clean|required');
        	        $this->form_validation->set_rules('methodrect','Method of Recruitment','xss_clean|required');
                	$this->form_validation->set_rules('ss','Position Sanction Strength','trim|xss_clean|required|numeric');
	                $this->form_validation->set_rules('p','Position Present','trim|xss_clean|required|numeric');
        	        $this->form_validation->set_rules('v','Position Vacant','trim|xss_clean|required|numeric');
                	$this->form_validation->set_rules('ssper','Sanction Strength Permanent','trim|xss_clean|required|numeric');
	                $this->form_validation->set_rules('pper','Position Permanent','trim|xss_clean|required|numeric');
        	        $this->form_validation->set_rules('vper','Vacancy Permanent','trim|xss_clean|required|numeric');
                	$this->form_validation->set_rules('sstem','Sanction Strength Temporary','trim|xss_clean|required|numeric');
	                $this->form_validation->set_rules('ptem','Position Temporary ','trim|xss_clean|required|numeric');
        	        $this->form_validation->set_rules('vtem','Vacancy Temporary','trim|xss_clean|required|numeric');
                	$this->form_validation->set_rules('address1','Address','xss_clean');
	                $this->form_validation->set_rules('ssdetail','Sanction Strength Detail','xss_clean');
        	        $this->form_validation->set_rules('remarks','Remarks','xss_clean');
		        if ($this->form_validation->run() == TRUE)
			{
		                $tnt = $this->input->post('tnt', TRUE);
                		$type = $this->input->post('type', TRUE);
		                $emppost = $this->input->post('emppost', TRUE);
                		$grppost = $this->input->post('grouppost', TRUE);
		                $scale = $this->input->post('scale', TRUE);
                		$methodRect = $this->input->post('methodrect', TRUE);
		                $group = $this->input->post('group', TRUE);
                		$uo = $this->input->post('uo', TRUE);
		                $dept = $this->input->post('dept', TRUE);
                		$address = $this->input->post('address1', TRUE);
		                $campusid = $this->input->post('campus', TRUE);
    //          		  $plannonplan = $this->input->post('pnp', TRUE);
		                $schemecode = $this->input->post('schemecode', TRUE);
		                $sancstrenght = $this->input->post('ss', TRUE);
                		$position = $this->input->post('p', TRUE);
		                $vacant = $this->input->post('v', TRUE);
                		$remarks = $this->input->post('remarks', TRUE);
		                $ssdetail = $this->input->post('ssdetail', TRUE);
                		$sspermanent = $this->input->post('ssper', TRUE);
		                $sstemporary = $this->input->post('sstem', TRUE);
                		$pospermanent = $this->input->post('pper', TRUE);
		                $postemporary = $this->input->post('ptem', TRUE);
                		$vpermanenet = $this->input->post('vper', TRUE);
		                $vtemporary = $this->input->post('vtem', TRUE);
		
				if($sancstrenght != $position+$vacant) {
			                $this->session->set_flashdata('err_message','The value of Position Sanction Strength is not equals to sum of Position Present and Position Vacant .');
			                $this->load->view('staffmgmt/editstaffposition', $data);
					return false; 
				}

				if($sspermanent != $pospermanent+$vpermanenet) {
			                $this->session->set_flashdata('err_message','The value of Sanction Strength Permanent is not equals to sum of Position Permanent and Vacancy Permanent .');
			                $this->load->view('staffmgmt/editstaffposition', $data);
					return false; 
				}
				if($sstemporary != $postemporary+$vtemporary){
			                $this->session->set_flashdata('err_message','The value of Sanction Strength Temporary is not equals to sum of Position Temporary and Vacancy Temporary .');
			                $this->load->view('staffmgmt/editstaffposition', $data);
					return false; 
				}
				if($sancstrenght != $sspermanent+$sstemporary){
			                $this->session->set_flashdata('err_message','The value of Position Sanction Strength is not equals to sum of Sanction Strength Permanent and Sanction Strength Temporary .');
			                $this->load->view('staffmgmt/editstaffposition', $data);
					return false; 
				}
				if($position != $pospermanent+$postemporary){
			                $this->session->set_flashdata('err_message','The value of Position Present is not equals to sum of Position Permanent and Position Temporary .');
			                $this->load->view('staffmgmt/editstaffposition', $data);
					return false; 
				}
				if($vacant != $vpermanenet+$vtemporary){
			                $this->session->set_flashdata('err_message','The value of Position Vacant is not equals to sum of Vacancy Permanent and Vacancy Temporary .');
			                $this->load->view('staffmgmt/editstaffposition', $data);
					return false; 
				}

			        $instdataspa = array(
					'spa_spid'=>$sp_id,
				        'spa_tnt'=> $editsp_data->sp_tnt,
				        'spa_type'=> $editsp_data->sp_type,
				        'spa_emppost'=> $editsp_data->sp_emppost,
				        'spa_grppost'=> $editsp_data->sp_grppost,
				        'spa_scale'=>$editsp_data->sp_scale,
				        'spa_methodRect'=>$editsp_data->sp_methodRect,
				        'spa_group'=>$editsp_data->sp_group,
				        'spa_uo'=>$editsp_data->sp_uo,
				        'spa_dept'=>$editsp_data->sp_dept,
				        'spa_address1'=>$editsp_data->sp_address1,
				        'spa_address2'=>'Null',
				        'spa_address3'=>'Null',
				        'spa_campusid'=>$editsp_data->sp_campusid,
				        'spa_per_temporary'=>'Null',
				        'spa_plan_nonplan'=>$editsp_data->sp_plan_nonplan,
				        'spa_schemecode'=>$editsp_data->sp_schemecode,
				        'spa_sancstrenght'=>$editsp_data->sp_sancstrenght,
				        'spa_position'=>$editsp_data->sp_position,
				        'spa_vacant'=>$editsp_data->sp_vacant,
				        'spa_remarks'=>$editsp_data->sp_remarks,
				        'spa_ssdetail'=>$editsp_data->sp_ssdetail,
				        'spa_sspermanent'=>$editsp_data->sp_sspermanent,
				        'spa_sstemporary'=>$editsp_data->sp_sstemporary,
				        'spa_pospermanent'=>$editsp_data->sp_pospermanent,
				        'spa_postemporary'=>$editsp_data->sp_postemporary,
				        'spa_vpermanenet'=>$editsp_data->sp_vpermanenet,
				        'spa_vtemporary'=>$editsp_data->sp_vtemporary,
				        'spa_org_id'=> '1',
					'spa_archuserid'=>$this->session->userdata('id_user'),
					'spa_archdate'=>date('y-m-d')
			        );

			        $spflag=$this->sismodel->insertrec('staff_position_archive', $instdataspa);
			        if(!$spflag)
			        {
			              $this->logger->write_dblogmessage("error","Error in insert staff position archive ", "Error in  staff position archive record insert" .$sp_id );
			         }else{
			              $this->logger->write_dblogmessage("insert","Insert staff position archive", "Record inserted in staff position archive successfully.." .$sp_id );
         			}

			        //        'sp_plan_nonplan'=>$plannonplan,
			      $update_data = array(
			                'sp_tnt'=> $tnt,
			                'sp_type'=> $type,
			                'sp_emppost'=> $this->commodel->get_listspfic1('designation', 'desig_id', 'desig_name', $emppost)->desig_id ,
			                'sp_grppost'=> $grppost,
			                'sp_scale'=> $scale,
			                'sp_methodRect'=> $methodRect,
			                'sp_group'=>$group,
			                'sp_uo'=> $this->lgnmodel->get_listspfic1('authorities', 'id', 'name', $uo)->id,
			                'sp_dept'=> $this->commodel->get_listspfic1('Department', 'dept_id', 'dept_name', $dept)->dept_id, 
			                'sp_address1'=>$address,
			                'sp_address2'=>'Null',
			                'sp_address3'=>'Null',
			                'sp_campusid'=> $this->commodel->get_listspfic1('study_center', 'sc_id', 'sc_name', $campusid)->sc_id,
			                'sp_per_temporary'=>'Null',
			                'sp_schemecode'=> $editsp_data->sp_schemecode, 
			                'sp_sancstrenght'=>$sancstrenght,
			                'sp_position'=>$position,
			                'sp_vacant'=>$vacant,
			                'sp_remarks'=>$remarks,
			                'sp_ssdetail'=>$ssdetail,
			                'sp_sspermanent'=>$sspermanent,
			                'sp_sstemporary'=>$sstemporary,
			                'sp_pospermanent'=>$pospermanent,
			                'sp_postemporary'=>$postemporary,
			                'sp_vpermanenet'=>$vpermanenet,
			                'sp_vtemporary'=>$vtemporary,
			                'sp_org_id'=> '1'
            			);

			        $spflag=$this->sismodel->updaterec('staff_position', $update_data, 'sp_id', $sp_id);
			        if(!$spflag)
            			{
			                $this->logger->write_logmessage("error","Error in updating Staff Position ", "Error in record updating. " );
			                $this->logger->write_dblogmessage("error","Error in updating Staff Position ", "Error in record updating. " );
			                $this->session->set_flashdata('err_message','Error in updating Staff Position ' . '.', 'error');
			                $this->load->view('staffmgmt/editstaffposition', $data);
			        }		
			        else{
			                $this->logger->write_logmessage("update","Edit Staff Position", " Record updated successfully. " );
		                	$this->logger->write_dblogmessage("update","Edit Staff Position", "Record updated successfully. " );
                			$this->session->set_flashdata('success',"Record updated successfully....." );
			                redirect('staffmgmt/staffposition/');
			        } 
			}
		}
        	$this->load->view('staffmgmt/editstaffposition', $data);
	}else{
                $this->session->set_flashdata('err_message','You do not have the right to Modify staff position.' .  '.', 'error');
                redirect('staffmgmt/staffposition');
        }

    }

 /* This function has been created for get list of schemes on the basis of  selected department */
    public function getdeptscheme(){
        $deptid = $this->input->post('deptid');
       // echo json_encode("this is testing----".$dept);
        $this->depmodel->get_deptschemelist($deptid);
    }

 /* This function has been created for get list of group post on the basis of  selected working type */
    public function getworkingtype(){
        $wtype = $this->input->post('groupp');
       // echo json_encode("this is testing----".$wtype);
	if ($wtype=='Teaching'){
            $uco_select_box ='';
            $uco_select_box.='<option value=>-------select Group Post--------';
            $uco_select_box.='<option value=UO> UO';
            $uco_select_box.='<option value=Professor> Professor';
            $uco_select_box.='<option value=AssociateProfessor> Associate Professor';
            $uco_select_box.='<option value=AssistantProfessor> Assistant Professor';
            $uco_select_box.='<option value=Librarians> Librarians';
            $uco_select_box.='<option value=PhysicalDirector> Physical Director';
        }
	else{
            $uco_select_box ='';
            $uco_select_box.='<option value= >-------select Group Post--------';
            $uco_select_box.='<option value=MultitaskingStaff> Multitasking staff';
            $uco_select_box.='<option value=TechnicalStaf> Technical staff';
            $uco_select_box.='<option value=MinisterialStaff> Ministerial staff';
            $uco_select_box.='<option value=AdministrativStaff> Administrativ staff';
            $uco_select_box.='<option value=Officer> Officer';
            $uco_select_box.='<option value=TechnicalOfficer> Technical Officer';
            $uco_select_box.='<option value=SupportingStaff> Supporting Staff';
            
	}
       echo json_encode($uco_select_box);
    }

 /* This function has been created for get position of Post Type */
    public function getemptypevalue(){
       $emptype = $this->input->post('emptype');
       //echo "get emp type vale====1==>".$emptype;
       $parts = explode(',',$emptype);
       $datawh=array('sp_campusid' => $parts[0],'sp_uo' => $parts[1], 'sp_dept' => $parts[2], 'sp_schemecode'=> $parts[3],
			'sp_group' => $parts[4], 'sp_tnt' => $parts[5], 'sp_emppost' =>$parts[6], 'sp_type' =>$parts[7]);
       $emptype_data = $this->sismodel->get_listspficemore('staff_position', 'sp_position', $datawh);
       if(!empty($emptype_data)){
       foreach($emptype_data as $empdata){ 
//	echo "get emp type vale====2==>".$empdata->sp_position;
	 	$p = $empdata->sp_position;
		$ss= 0;
		$v='';	
        	$p1='';
        	$p2='';
        	$p3='';
        	$p4='';
        	$p5='';
        	$p6='';
	   }
         }
	else{
		$p=0;
		$ss=0;
		$v='';	
        	$p1='';
        	$p2='';
        	$p3='';
        	$p4='';
        	$p5='';
        	$p6='';
       }
	 echo json_encode($p.','.$ss.','.$v.','.$p1.','.$p2.','.$p3.','.$p4.','.$p5.','.$p6);
	
    }
 
 /* This function has been created for calculate position of Post Type */
    public function getsstype(){
	    $emptype = $this->input->post('sstype');
	    $parts = explode(',',$emptype);
	    if(empty($parts[2])){
	    	$parts[2]=0;
	    }

       if($parts[1]=='Permanent'){
	 	$p=trim($parts[0]);
		$v=$parts[2]-$parts[0];	
        	$p1=$parts[2];
        	$p2=trim($parts[0]);
        	$p3=$parts[2]-$parts[0];
        	$p4=0;
        	$p5=0;
        	$p6=0;
	    }
         elseif($parts[1]=='Temporary'){
	 	$p=trim($parts[0]);
		$v=$parts[2]-$parts[0];	
        	$p1=0;
        	$p2=0;
        	$p3=0;
        	$p4=$parts[2];
        	$p5=trim($parts[0]);
        	$p6=$parts[2]-$parts[0];
	    }
	elseif($parts[1]=='PT'){
		$p=trim($parts[0]);
                $v=$parts[2]-$parts[0];
                $p1=$parts[2];
                $p2=trim($parts[0]);
                $p3=$parts[2]-$parts[0];
                $p4=$parts[2];
                $p5=trim($parts[0]);
                $p6=$parts[2]-$parts[0];
            }
        else{	
	 	$p=0;
		$v='';	
        	$p1='';
        	$p2='';
        	$p3='';
        	$p4='';
        	$p5='';
        	$p6='';
	   }
	  echo json_encode($p.','.$v.','.$p1.','.$p2.','.$p3.','.$p4.','.$p5.','.$p6);
    }

	/* This function has been created for calculate position of Post Type */
    public function getsstypeper(){
       $emptype = $this->input->post('sstype');
       $parts = explode(',',$emptype);
       if(empty($parts[2])){
                $parts[2]=0;
            }
       if($parts[1]=='Permanent'){
                $p=trim($parts[0]);
                $v=$parts[2]-$parts[0];
                $p1=$parts[2];
                $p2=trim($parts[0]);
                $p3=$parts[2]-$parts[0];
                $p4=0;
                $p5=0;
                $p6=0;
            }
         elseif($parts[1]=='Temporary'){
                $p=trim($parts[0]);
                $v=$parts[2]-$parts[0];
                $p1=0;
                $p2=0;
                $p3=0;
                $p4=$parts[2];
                $p5=trim($parts[0]);
                $p6=$parts[2]-$parts[0];
            }
        elseif($parts[1]=='PT'){
                $p=trim($parts[0]);
                $v=$parts[2]-$parts[0];
                $p1=$parts[3];
                $p2=$parts[4];
                $p3=$parts[3]-$parts[4];
                $p4=$parts[2]-$parts[3];
                $p5=$parts[0]-$parts[4];
                $p6=$p4-$p5;
            }
        else{
                $p=0;
                $v='';
                $p1='';
                $p2='';
                $p3='';
                $p4='';
                $p5='';
                $p6='';
           }
          echo json_encode($p.','.$v.','.$p1.','.$p2.','.$p3.','.$p4.','.$p5.','.$p6);
    }


  /*This function has been created for display Designation and PayBand on the Basis of Group */
  public function getdesigpaybandlist(){
	$group = $this->input->post('group');
	//echo "Om Prakash======>".$group;
        $datawh=array('desig_group' => $group);
	$whorder = ("desig_name asc");
        $desig = $this->commodel->get_orderlistspficemore('designation','desig_id,desig_name,desig_payscale,desig_code',$datawh,$whorder);
//        $desig = $this->commodel->get_listspficemore('designation','desig_id,desig_name,desig_payscale,desig_code',$datawh);
        $desig_select_box ='';
        $desig_select_box.='<option value="">----------Select Designation-----------';
        foreach($desig as $drecord){
        // echo "Om Prakash====2====>".$drecord->desig_id;   
            $desig_select_box.='<option value='.$drecord->desig_id.'>'.$drecord->desig_name.'('. $drecord->desig_code .')'.' ';
        }
        $payb_select_box ='';
        $payb_select_box.='<option value="">----------Select Payband-----------';
        foreach($desig as $drecord){
      //  echo "Om Prakash====3====>".$drecord->desig_payscale;   
            $payb_select_box.='<option value='.$drecord->desig_payscale.'>'.$drecord->desig_payscale ;
        }
        echo json_encode($desig_select_box.','.$payb_select_box);
  }

 //===================End of Staff Position ============================

   
   /* This function has been created for get list of uco on the basis of campus */
    /*In future this code may be replace when either campusid added in the 
     authority or authority added in campus.*/
    
    public function getuoclist(){
        $scid = $this->input->post('campusname');

        $auco_data = $this->sismodel->get_listrow('map_sc_uo','scuo_scid',$scid);
        $aucolist = $auco_data->result();
        $uco_select_box ='';
        $uco_select_box.='<option value="">-------University Officer Control--------';
        foreach($aucolist as $aucoid){
            $auoname=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id',$aucoid->scuo_uoid)->name;
            $auocode=$this->lgnmodel->get_listspfic1('authorities', 'code', 'id',$aucoid->scuo_uoid)->code;
            $uco_select_box.='<option value='.$aucoid->scuo_uoid.'>'.$auoname." (".$auocode.") ".' ';
        } 
        echo json_encode($uco_select_box);
    }
    
   
    /* This function has been created for get list of schemes on the basis of  selected campus and uco */
     
    public function getnewdeptlist(){
        $combid = $this->input->post('campuoc');
        //echo json_encode("combination===".$combid);
        $parts = explode(',',$combid); 
       // echo json_encode("this is test===".$parts[0].",".$parts[1]);
        $sccode=$this->commodel->get_listspfic1('study_center', 'sc_code', 'sc_id',$parts[0])->sc_code;
        //$datawh=array('cudsd_scid' => $parts[0],'cudsd_auoid' => $parts[1]);
        $datawh=array('dept_uoid' => $parts[1],'dept_sccode' => $sccode);
	$whorder="dept_name asc";
        //$comb_data = $this->sismodel->get_listspficemore('cudsdmap','cudsd_deptid',$datawh);
        //$comb_data = $this->commodel->get_listspficemore('Department','dept_id,dept_name,dept_code',$datawh);
        $comb_data = $this->commodel->get_orderlistspficemore('Department','dept_id,dept_name,dept_code',$datawh,$whorder);
        //$comblist = $comb_data->result();
        $dept_select_box ='';
        $dept_select_box.='<option value="">-------Select Department--------';
        foreach($comb_data as $combdataid){
           // $deptname=$this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id',$combdataid->cudsd_deptid)->dept_name;
            //$dept_select_box.='<option value='.$combdataid->cudsd_deptid.'>'.$deptname.' ';
            $dept_select_box.='<option value='.$combdataid->dept_id.'>'.$combdataid->dept_name.'('.$combdataid->dept_code.')'.' ';
            
        }
        echo json_encode($dept_select_box);
         
    }
     /* This function has been created for get list of schemes on the basis of  selected campus, uco and department */
    public function getnewdeptschemelist(){
        //$campuocdept = $this->input->post('combdept');
        //$parts = explode(',',$campuocdept);
        $campdept = $this->input->post('combdept');
        //$datawh=array('cudsd_scid' => $parts[0],'cudsd_auoid' => $parts[1],'cudsd_deptid' => $parts[2]);
        $datawh=array('sd_deptid' => $campdept);
        $comb_data = $this->sismodel->get_listspficemore('scheme_department','sd_id,sd_name,sd_code',$datawh);
        $schm_select_box ='';
        $schm_select_box.='<option value="">-------Select Scheme Name--------';
        foreach($comb_data as $combdataid){
            //$schmname=$this->sismodel->get_listspfic1('scheme_department', 'sd_name', 'sd_id',$aucoid->cudsd_schid)->sd_name;
            $schm_select_box.='<option value='.$combdataid->sd_id.'>'.$combdataid->sd_name.'('.$combdataid->sd_code.')'.' ';
            
        }
        echo json_encode($schm_select_box);
       // $this->depmodel->get_sschemelist($deptid);
    }
    /* This function has been created for get list of DDO on the basis of  selected campus, uco,department,schemes */
    public function getddolist(){
        //$campuocdeptschm = $this->input->post('combfour');
        $campdeptschm = $this->input->post('combthree');
       //$parts = explode(',',$campuocdeptschm);
        $parts = explode(',',$campdeptschm);
        //$datawh=array('cudsd_scid' => $parts[0],'cudsd_auoid' => $parts[1],'cudsd_deptid' => $parts[2],'cudsd_schid' => $parts[3]);
        $datawh=array('ddo_scid' => $parts[0],'ddo_deptid' => $parts[1],'ddo_schid' => $parts[2]);
        $comb_data = $this->sismodel->get_listspficemore('ddo','ddo_id,ddo_name,ddo_code',$datawh);
//print_r("nksc".$comb_data); die();
        $ddo_select_box ='';
        $ddo_select_box.='<option value="">-------Drawing and Disbursing Officer--------';
        foreach($comb_data as $combdataid){
            //$ddouserid=$this->lgnmodel->get_listspfic1('authority_map', 'user_id', 'authority_id',$combdataid->cudsd_ddoid)->user_id;
            //$ddofname=$this->lgnmodel->get_listspfic1('userprofile', 'firstname', 'userid',$ddouserid)->firstname;
            //$ddolname=$this->lgnmodel->get_listspfic1('userprofile', 'lastname', 'userid',$ddouserid)->lastname;
            //$ddoflname=$ddofname." ".$ddolname;
            //$ddo_select_box.='<option value='.$combdataid->cudsd_ddoid.'>'.$ddoflname.' ';
	
            $ddo_select_box.='<option value='.$combdataid->ddo_id.'>'.$combdataid->ddo_name. '(' .$combdataid->ddo_code. ')'.' ';
//	print_r("nksc".$ddo_select_box);
            
        }
        echo json_encode($ddo_select_box);
    }
    /* This function has been created for get list of Designation on the basis of  selected Group */
    public function getdesiglist(){
        $groups = $this->input->post('group');
        //echo json_encode("group=incontroller=".$groups);
        $datawh=array('desig_group' => $groups);
	
	$whorder = ("desig_name asc");
        //$grp_data = $this->commodel->get_listspficemore('designation','desig_id,desig_name,desig_code',$datawh);
        $grp_data = $this->commodel->get_orderlistspficemore('designation','desig_id,desig_name,desig_code',$datawh,$whorder);
        //echo json_encode("grouplist==".$grp_data);
        $desig_select_box ='';
        $desig_select_box.='<option value="">----------Select Designation-----------';
        foreach($grp_data as $grprecord){
           // echo json_encode("grouplist==".$grprecord->desig_name);
            $desig_select_box.='<option value='.$grprecord->desig_id.'>'.$grprecord->desig_name.'('. $grprecord->desig_code .')'.' ';
            
        }
        echo json_encode($desig_select_box);
    }
    /* This function has been created for get the vacant shown against position */
    public function getemppostposition(){
        $combval = $this->input->post('combsix');
        	//echo json_encode("post=vaccancy==3=".$combval);
        $parts = explode(',',$combval);
        /******************Query for filteraion the post************************************/
        $datawh=array('sp_campusid' => $parts[0],'sp_uo' => $parts[1], 'sp_dept' => $parts[2], 'sp_tnt' => $parts[4]);
        $emppost_data = $this->sismodel->get_listspficemore('staff_position','sp_vacant',$datawh);
        //echo json_encode("post====".$emppost_data);
	// for same department
	$empdept=$this->sismodel->get_listspfic1('employee_master', 'emp_dept_code', 'emp_id',$parts[5])->emp_dept_code;	
	$emppostname=$this->sismodel->get_listspfic1('employee_master', 'emp_post', 'emp_id',$parts[5])->emp_post;
	$emppostid=$this->commodel->get_listspfic1('designation', 'desig_id', 'desig_name',$emppostname)->desig_id;
//        echo "nks123".$emppostname."xxxxxx".$emppostid;	
        $emppost_select_box ='';
        $emppost_select_box.='<option value="">-------------- Select Post -----------------';
	if($empdept == $parts[2]){
		$emppost_select_box.='<option value='.$emppostid.'>'.$emppostname.' ';
	}
	
        if(!empty($emppost_data)){ 
                    $datawh2=array('sp_campusid' => $parts[0],'sp_uo' => $parts[1], 'sp_dept' => $parts[2],'sp_tnt' => $parts[4],'sp_vacant>' =>0); 
                    $emppost_finaldata = $this->sismodel->get_distinctrecord('staff_position', 'sp_emppost',$datawh2);
                    foreach($emppost_finaldata as $empdata){
                        $emppost_name=$this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id',$empdata->sp_emppost)->desig_name;
                       // if($empdata->sp_vacant > 0){    
                            $emppost_select_box.='<option value='.$empdata->sp_emppost.'>'.$emppost_name.' ';
                        //}  
                    }//foreach    
              //  }//if
            //}//foreach    
        } //if close   
        else{
            $emppost_select_box='No vacancy';
        }
        //echo json_encode("post=22===".$emppost_select_box);
        echo json_encode($emppost_select_box);
                        
    }
       
    /***********************************Employee type from staff position*********************************************/   
    /* This function has been created for get the employee type vacant shown against position */
    public function getemptypeposition(){
        $combval = $this->input->post('combfive');
        $parts = explode(',',$combval);
      //  $uoid=$this->lgnmodel->get_listspfic1('authority_map', 'authority_id', 'user_id',$parts[1])->authority_id;
       // $emppost_id=$this->commodel->get_listspfic1('designation', 'desig_id', 'desig_name',$parts[4])->desig_id;
       //  echo json_encode("seema=27===".$emppost_id);
        /*$datawh=array('sp_campusid' => $parts[0],'sp_uo' => $parts[1], 'sp_dept' => $parts[2],
                        'sp_schemecode'=> $parts[3],'sp_emppost' => $parts[4], 'sp_tnt' => $parts[5]);*/
        $datawh=array('sp_campusid' => $parts[0],'sp_uo' => $parts[1], 'sp_dept' => $parts[2],
                        'sp_emppost' => $parts[3], 'sp_tnt' => $parts[4]);
        
        $emptype_data = $this->sismodel->get_listspficemore('staff_position', 'sp_type',$datawh);
        // echo json_encode("seema=77===".$emptype_data);
        $emptype_select_box ='';
        $emptype_select_box.='<option value="">------ Select Post Type -----------';
        if(!empty($emptype_data)){ 
                      
            foreach($emptype_data as $empdata){
                // echo json_encode("seema=999===".$empdata);
                	$emptype_select_box.='<option value='.$empdata->sp_type.'>'.$empdata->sp_type.' ';
            }//foreach
        } //if close   
        else{
            $emptype_select_box='No vacancy';
        }
        echo json_encode($emptype_select_box);
    }
    /*********************************** closer Employee type from staff position*********************************************/   
  
    
    /* This function has been created for get list of department on the basis of  selected uco */
     
    public function getuocdeptlist(){
        $combid = $this->input->post('campuoc');
        $parts = explode(',',$combid);
        $sccode=$this->commodel->get_listspfic1('study_center', 'sc_code', 'sc_id',$parts[0])->sc_code;
        $datawh=array('dept_sccode'=> $sccode,'dept_uoid' => $parts[1]);
	$whorder="dept_name asc";
       // $comb_data = $this->commodel->get_listspficemore('Department','dept_id,dept_name,dept_code',$datawh);
        $comb_data = $this->commodel->get_orderlistspficemore('Department','dept_id,dept_name,dept_code',$datawh,$whorder);
        $dept_select_box ='';
        $dept_select_box.='<option value="">------- Select Department ----------------------';
        foreach($comb_data as $combdataid){
            $dept_select_box.='<option value='.$combdataid->dept_id.'>'.$combdataid->dept_name.'('.$combdataid->dept_code.')'.' ';
            
        }
        echo json_encode($dept_select_box);
         
    }
    /************************** close of department uco ************************************************************************************/
    /* This function has been created for get list of designation on the basis of  selected employee type */
    public function gettypedesiglist(){
        $etpye = $this->input->post('emptypeid');
        $datawh=array('desig_type' => $etpye);
	$whorder = ("desig_name asc");
        $comb_data = $this->commodel->get_orderlistspficemore('designation','desig_id,desig_name,desig_code',$datawh,$whorder);
   //     $comb_data = $this->commodel->get_listspficemore('designation','desig_id,desig_name,desig_code',$datawh);
        $desig_select_box ='';
        $desig_select_box.='<option value="">------------- Select Designation -----------------';
        foreach($comb_data as $combdataid){
            $desig_select_box.='<option value='.$combdataid->desig_id.'>'.$combdataid->desig_name.'('.$combdataid->desig_code.')'.' ';
            
        }
        echo json_encode($desig_select_box);
         
    }
    /************************** close of designation on the basis of  uco and department **********************************************/
     public function getempnamelist(){
        $combfour = $this->input->post('uddempt');
        $parts = explode(',',$combfour);
       // echo "values====".$combfour ;  
        $datawh=array('emp_uocid' => $parts[0],'emp_dept_code' => $parts[1], 'emp_worktype' => $parts[2], 'emp_desig_code' => $parts[3]);
        $comb_data = $this->sismodel->get_listspficemore('employee_master','emp_id,emp_code,emp_name',$datawh);
        $emplst_select_box ='';
        $emplst_select_box.='<option value="">---------- Select Employee Name -------------';
        foreach($comb_data as $combdataid){
            $emplst_select_box.='<option value='.$combdataid->emp_id.'>'.$combdataid->emp_name.'('.$combdataid->emp_code.')'.' ';
            
        }
        echo json_encode($emplst_select_box);
         
    }
    
    /****************************staff retirement**********************************************************/
    public function staffretirement($selempid=0){
        $selectfield ="emp_id,emp_code,emp_name,emp_dept_code,emp_desig_code,emp_worktype,emp_email,emp_doj,emp_dor,emp_uocid,emp_schemeid,emp_scid,emp_photoname";
        $whorder = "emp_name  asc";
        $crit=$this->input->post('filter',TRUE);
        $data['search']=$crit;
        $data['selempid']=$selempid;
        if(isset($_POST['filter'])) {
           
            $wtype = $this->input->post('wtype');
            $uoff  = $this->input->post('uoff');
            $dept  = $this->input->post('dept');
            $desig  = $this->input->post('desig');
            $emp    = $this->input->post('emp');
            $data['selempid']=$emp;
            if($uoff!="All" && $dept!="All"){
                $whdata = array ('emp_worktype' => $wtype,'emp_uocid' => $uoff,'emp_dept_code' => $dept, 'emp_desig_code' =>$desig,'emp_id'=>$emp);
            }
            if($uoff =="All" && $dept!="All" ){
                $whdata = array ('emp_worktype' => $wtype,'emp_dept_code' => $dept, 'emp_desig_code' =>$desig,'emp_id'=>$emp);
            }
            if($uoff !="All" && $dept =="All" ){
                $whdata = array ('emp_worktype' => $wtype,'emp_uocid' => $uoff, 'emp_desig_code' =>$desig,'emp_id'=>$emp);
            }
            if($uoff =="All" && $dept == "All" ){
                $whdata = array ('emp_worktype' => $wtype,'emp_desig_code' =>$desig,'emp_id'=>$emp);
            }
		 $whdata['emp_leaving'] = NULL;
		 $whdata['emp_dor>='] = date('y-m-d');
            $data['empret'] = $this->sismodel->get_orderlistspficemore('employee_master',$selectfield,$whdata,$whorder);
                       
        }
        
        if(isset($_POST['update'])){
            $reason = $this->input->post('resret');
            $dateofleaving = $this->input->post('dateofleaving');
            $remark = $this->input->post('remark');
           
            $empcode=$this->sismodel->get_listspfic1('employee_master', 'emp_code', 'emp_id',$selempid)->emp_code;
            $empemail=$this->sismodel->get_listspfic1('employee_master', 'emp_email', 'emp_id',$selempid)->emp_email;
            $doj=$this->sismodel->get_listspfic1('employee_master', 'emp_doj', 'emp_id',$selempid)->emp_doj;
            $dor=$this->sismodel->get_listspfic1('employee_master', 'emp_dor', 'emp_id',$selempid)->emp_dor;
            $update_data = array(
               'sre_empid' => $selempid,
               'sre_empcode' => $empcode,
               'sre_empemailid' => $empemail,
               'sre_doj'  => $doj,
               'sre_dor'  => $dor,
               'sre_reason' => $reason,
               'sre_reasondate' => $dateofleaving,
	       'sre_remark' => $remark,	
               'sre_creatorid' => $this->session->userdata('username'),
               'sre_creatordate' => date('y-m-d'),
               'sre_modifierid' => $this->session->userdata('username'), 
               'sre_modifydate' => date('y-m-d')
            );
            $dupdetail=array(
               'sre_empid' => $selempid, 
               'sre_empcode' => $empcode,
               'sre_empemailid' => $empemail,
            );
            $dupdata = $this->sismodel->isduplicatemore('staff_retirement', $dupdetail);

            if($dupdata == 1 ){
                $this->session->set_flashdata("err_message", "Record is already exist . 'Employee PF No' = $empcode  , ' Email'= $empemail .");
                redirect('staffmgmt/staffretirement');
                return;
            }
            $retupdateflag=$this->sismodel->insertrec('staff_retirement', $update_data);
            if(!$retupdateflag)
            {
                $this->logger->write_logmessage("error"," Trying to staff retirement record", "staff retirement record is not added. Employee PF No' = $empcode  , ' Email'= $empemail");
                $this->logger->write_dblogmessage("error","Trying to staff retirement record", "staff retirement record is not added. Employee PF No' = $empcode  , ' Email'= $empemail ");
                $this->session->set_flashdata('err_message','Error staff retirement data - ' .' Employee PF No ='. $empcode .' Email='. $empemail. '.', 'error');
                $this->load->view('staffmgmt/staffretirement', $update_data);
            }
            else{
                /*update employeemaster table*/
               /* if($reason == 'Disqualify')
                {
                    $reason='Disqualified';
                } 
                if($reason == 'Expire'){
                    $reason='Expired';
                }
                if($reason == 'Resign'){
                   $reason =$reason.'ed'; 
                }*/
                $empup_data = array(
                  'emp_leaving' => $reason 
                );
                $empmasterflag=$this->sismodel->updaterec('employee_master', $empup_data, 'emp_id', $selempid);
                /*update staff position table on staff retirement*/
                $dept=$this->sismodel->get_listspfic1('employee_master', 'emp_dept_code', 'emp_id',$selempid)->emp_dept_code;
                $desig=$this->sismodel->get_listspfic1('employee_master', 'emp_desig_code', 'emp_id',$selempid)->emp_desig_code;
                $worktype=$this->sismodel->get_listspfic1('employee_master', 'emp_worktype', 'emp_id',$selempid)->emp_worktype;
                $emptype=$this->sismodel->get_listspfic1('employee_master', 'emp_type_code', 'emp_id',$selempid)->emp_type_code;
                $empscid=$this->sismodel->get_listspfic1('employee_master', 'emp_scid', 'emp_id',$selempid)->emp_scid;
                $empuocid=$this->sismodel->get_listspfic1('employee_master', 'emp_uocid', 'emp_id',$selempid)->emp_uocid;
                $empschmid=$this->sismodel->get_listspfic1('employee_master', 'emp_schemeid', 'emp_id',$selempid)->emp_schemeid;
             //   echo "==dept==".$dept."===desig===".$desig."==wtype===".$worktype."===etype===".$emptype."==scid===".$empscid."==uocid===".$empuocid."===schmid===".$empschmid;
                $upspdata_flag=$this->sismodel->updatestaffposition2($empscid,$empuocid,$dept,$desig,$worktype,$emptype,$empschmid);
                if(!$upspdata_flag){
                    $this->logger->write_logmessage("error","Error in update staff position ", "Error in staff position record update" );
                    $this->logger->write_dblogmessage("error","Error in update staff position", "Error in staff position record update");
                }
                else{
                    $this->logger->write_logmessage("update","update staff position ", "staff position record updated successfully ");
                    $this->logger->write_dblogmessage("update","staff position", "staff position record updated successfully");
                }
                $this->logger->write_logmessage("insert","staff retirement record insert " , "staff retirement record inserted successfully... ");
                $this->logger->write_dblogmessage("insert","staff retirement record insert" , "staff retirement record inserted successfully... ");
                $this->session->set_flashdata('success','staff retirement record inserted successfully '.' Employee PF No ='. $empcode .' Email='. $empemail);
                redirect('staffmgmt/staffretirement');
            }
        }//if update button
        $fields="sre_empid,sre_empcode,sre_empemailid,sre_doj,sre_dor,sre_reason,sre_reasondate";  
	$whdata = array('sre_reason !='=>'superannuation');
        $data['records'] = $this->sismodel->get_orderlistspficemore('staff_retirement',$fields,$whdata,'sre_reasondate asc');
        $this->logger->write_logmessage("view"," view staff retirement list" );
        $this->logger->write_dblogmessage("view"," view staff retirement list");
        $this->load->view('staffmgmt/staffretirement',$data);
    }
    /*******************************************closer staff retirement********************************/
    /*******************************************designation list********************************/
    public function getcombdesiglist(){
        $combthree = $this->input->post('wtuodept');
        $parts = explode(',',$combthree);
        if($parts[1]!="All" && $parts[2]!="All"){
            $datawh=array('emp_worktype' => $parts[0],'emp_uocid' => $parts[1],'emp_dept_code' => $parts[2]);
        }
        if($parts[1]== "All" && $parts[2]!="All" ){
            $datawh=array('emp_worktype' => $parts[0],'emp_dept_code' => $parts[2]);
        }
        if($parts[1] != "All" && $parts[2] =="All" ){
            $datawh=array('emp_worktype' => $parts[0],'emp_uocid' => $parts[1]);
        }
        if($parts[1] == "All" && $parts[2] == "All" ){
            $datawh=array('emp_worktype' => $parts[0]);
        }
        $comb_data = $this->sismodel->get_distinctrecord('employee_master','emp_desig_code',$datawh);
        $desig_select_box ='';
        $desig_select_box.='<option value="">------------- Select Designation ----------';
        foreach($comb_data as $combdataid){
            $designame=$this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id',$combdataid->emp_desig_code)->desig_name;
            $desigcode=$this->commodel->get_listspfic1('designation', 'desig_code', 'desig_id',$combdataid->emp_desig_code)->desig_code;
            $desig_select_box.='<option value='.$combdataid->emp_desig_code.'>'.$designame. '(' .$desigcode. ')'.' ';
                           
        }
        echo json_encode($desig_select_box);
         
    }
    /*******************************************closer designation list********************************/
    /*******************************************employee list********************************/
    public function getempnamelist2(){
        $combfour = $this->input->post('uddempt');
        $parts = explode(',',$combfour);
        //echo "values====".$combfour ;  
        if($parts[0]!="All" && $parts[1]!="All"){
            $datawh=array('emp_uocid' => $parts[0],'emp_dept_code' => $parts[1], 'emp_worktype' => $parts[2], 'emp_desig_code' => $parts[3],'emp_leaving' =>NULL);  
        }
        if($parts[0] == "All" && $parts[1]!="All" ){
            $datawh=array('emp_dept_code' => $parts[1], 'emp_worktype' => $parts[2], 'emp_desig_code' => $parts[3],'emp_leaving' =>NULL);  
        }
        if($parts[0]!= "All" && $parts[1] =="All" ){
            $datawh=array('emp_uocid' => $parts[0],'emp_worktype' => $parts[2], 'emp_desig_code' => $parts[3],'emp_leaving' =>NULL);     
        }
        if($parts[0] == "All" && $parts[1] =="All" ){
            $datawh=array('emp_worktype' => $parts[2], 'emp_desig_code' => $parts[3],'emp_leaving' =>NULL);  
        }
        $comb_data = $this->sismodel->get_listspficemore('employee_master','emp_id,emp_code,emp_name',$datawh);
        $emplst_select_box ='';
        $emplst_select_box.='<option value="">---------- Select Employee Name -------------';
        if(!empty($comb_data)){ 
            foreach($comb_data as $combdataid){
                $emplst_select_box.='<option value='.$combdataid->emp_id.'>'.$combdataid->emp_name.'('.$combdataid->emp_code.')'.' ';
            
            }
        }
        else{
            $emplst_select_box ='novalue';
        }
        echo json_encode($emplst_select_box);
    
    }
    /*******************************************closer employeenamelist********************************/
    
    /* This function has been created for get Grades on the basis of  selected working type */
     
    public function getgradelist(){
        $combid = $this->input->post('wtype');
        echo json_encode($combid);
        $grade_select_box ='';
        $grade_select_box.='<option value>------- Select Grade -----------------';
        if($combid == 'Teaching'){
            $grade_select_box.='<option value='.'Career'.''.'Advance(CA)'.'>'.'Career'.''.'Advance(CA)'.'';
            $grade_select_box.='<option value='.'Regular(R)'.'>'.'Regular(R)'.'';    
               
        }
        else{
            $grade_select_box.='<option value='.'Selection Grade(SG)'.'>'.'Selection Grade(SG)'.'';
            $grade_select_box.='<option value='.'Special Grade(SplG)'.'>'.'Special Grade(SplG)'.'';    
        }
        echo json_encode($grade_select_box);
         
    }
    
    /*******************************************closer grade list********************************/
    
    /* This function has been created for get the vacant shown against position */
    public function getemppostpositionnew(){
        $combval = $this->input->post('combfive');
        $parts = explode(',',$combval);
        /******************Query for filteraion the post************************************/
        $datawh=array('sp_campusid' => $parts[0],'sp_uo' => $parts[1], 'sp_dept' => $parts[2],
                       'sp_schemecode' => $parts[3], 'sp_tnt' => $parts[4]);
        $emppost_data = $this->sismodel->get_listspficemore('staff_position','sp_emppost,sp_vacant',$datawh);
        $emppost_select_box ='';
        $emppost_select_box.='<option value="">-------------- Select Post -----------------';
        if(!empty($emppost_data)){ 
        	
            foreach($emppost_data as $records){ 
                if($records->sp_vacant > 0){ 
                    $emppost_name=$this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id',$records->sp_emppost)->desig_name;
                    $emppost_select_box.='<option value='.$records->sp_emppost.'>'.$emppost_name.' ';
                  
                }//if
            }//foreach    
        } //if close   
        else{
            $emppost_select_box='No vacancy';
        }
        echo json_encode($emppost_select_box);
                        
    }
       
    /***********************************shown against position*********************************************/   
    
     /* get retired staff list*/
    public function retiredstafflist(){ 
        $fields="sre_empid,sre_empcode,sre_empemailid,sre_doj,sre_dor,sre_reason,sre_reasondate";  
	$whdata = array('sre_reason'=>'superannuation');
        $data['records'] = $this->sismodel->get_orderlistspficemore('staff_retirement',$fields,$whdata,'sre_reasondate asc');
        $this->logger->write_logmessage("view"," view staff retirement list" );
        $this->logger->write_dblogmessage("view"," view staff retirement list");
        $this->load->view('staffmgmt/retiredstafflist',$data);
        
    }
   
    /* This function has been created for get the vacant shown against position (but not check for the vacancy available)  */
    public function getposttonew(){
        $combval = $this->input->post('combfive');
        $parts = explode(',',$combval);
        /******************Query for filteraion the post************************************/
        $datawh=array('sp_campusid' => $parts[0],'sp_uo' => $parts[1], 'sp_dept' => $parts[2],
                       'sp_schemecode' => $parts[3], 'sp_tnt' => $parts[4]);
        $emppost_data = $this->sismodel->get_listspficemore('staff_position','sp_emppost',$datawh);
        $emppost_select_box ='';
        $emppost_select_box.='<option value="">-------------- Select Post -----------------';
        if(!empty($emppost_data)){ 
        	
            foreach($emppost_data as $records){ 
             //   if($records->sp_vacant > 0){ 
                    $emppost_name=$this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id',$records->sp_emppost)->desig_name;
                    $emppost_select_box.='<option value='.$records->sp_emppost.'>'.$emppost_name.' ';
                  
               // }//if
            }//foreach    
        } //if close   
        else{
            $emppost_select_box='No data';
        }
        echo json_encode($emppost_select_box);
                        
    }
    public function mutualtransfer($empname,$empmutual,$transtype){
        
        /**uit tranfer data for mutal transfer one employee from to is became to from for other employe**/
      // $flag=false;
        $transtype = $transtype.",".$_POST['empmutual']; 
        $ddoidfrom = $this->sismodel->get_listspfic1('employee_master','emp_ddoid','emp_id',$_POST['empname'])->emp_ddoid;
        $groupfrom = $this->sismodel->get_listspfic1('employee_master','emp_group','emp_id',$_POST['empname'])->emp_group;
        $posto=$this->commodel->get_listspfic1('designation','desig_id','desig_name',$_POST['postto'])->desig_id;
        $data = array(
            'uit_registrarname'                => $this->input->post('registrarname'),
            'uit_desig'                        => $this->input->post('designation'),
            'uit_uso_no'                       => $this->input->post('usono'),
            'uit_date'                         => date('y-m-d'),
            'uit_rc_no'                        => $this->input->post('rcno'),
            'uit_subject'                      => $this->input->post('subject'),
            'uit_referenceno'                  => $this->input->post('referenceno'),
            'uit_ordercontent'                 => $this->input->post('ordercontent'),
            'uit_emptype'                      => $this->input->post('employeetype'),
            'uit_uoc_from'                     => $this->input->post('uocfrom'),
            'uit_workdept_from'                => $this->input->post('deptfrom'),
            'uit_desig_from'                   => $this->input->post('desigfrom'),
            'uit_staffname'                    => $this->input->post('empname'),
            'uit_workingpost_from'             => $this->input->post('postfrom'),
            'uit_scid_from'                    => $this->input->post('campusfrom'),
            'uit_scid_to'                      => $this->input->post('campus'),
            'uit_uoc_to'                       => $this->input->post('uocontrolto'),
            'uit_dept_to'                      => $this->input->post('deptto'),
            'uit_desig_to'                     => $this->input->post('desigto'),
            'uit_post_to'                      => $posto, //$this->input->post('postto'),
            'uit_tta_detail'                   => $this->input->post('ttadetail'),
                
            'uit_dateofrelief'                 => $this->input->post('dateofrelief'),
            'uit_dateofjoining'                => $this->input->post('expdoj'),
            'uit_email_sentto'                 => $this->input->post('emailsentto'),
            'uit_emptypeto'                    => $this->input->post('emptypeto'),
            'uit_schm_from'                    => $this->input->post('schemfrom'),
            'uit_schm_to'                      => $this->input->post('schemto'),
            'uit_ddoid_to'                     => $this->input->post('ddo'),
            'uit_group_to'                     => $this->input->post('group'),
            'uit_paybandid_to'                 => $this->input->post('payband'),
            'uit_vacanttype_to'                => $this->input->post('vacanttype'),
            'uit_transfertype'                 => $transtype,
            'uit_ddoid_from'                   => $ddoidfrom,
            'uit_group_from'                   => $groupfrom
        );  
        $usrinputtfr_flag=$this->sismodel->insertrec('user_input_transfer', $data);
        $transtype = "mutual,".$_POST['empname']; 
        $postto2=$this->commodel->get_listspfic1('designation','desig_name','desig_id',$_POST['postto'])->desig_name;
      //  echo "postto2===".$postto2;
        $postfrom2=$this->commodel->get_listspfic1('designation','desig_id','desig_name',$_POST['postfrom'])->desig_id;
        $ddoidfrom = $this->sismodel->get_listspfic1('employee_master','emp_ddoid','emp_id',$_POST['empname'])->emp_ddoid;
        $ddouseridfrom = $this->sismodel->get_listspfic1('employee_master','emp_ddouserid','emp_id',$_POST['empname'])->emp_ddouserid;
        $data2=array(
            'uit_registrarname'                => $this->input->post('registrarname'),
            'uit_desig'                        => $this->input->post('designation'),
            'uit_uso_no'                       => $this->input->post('usono'),
            'uit_date'                         => date('y-m-d'),
            'uit_rc_no'                        => $this->input->post('rcno'),
            'uit_subject'                      => $this->input->post('subject'),
              
            'uit_referenceno'                  => $this->input->post('referenceno'),
            'uit_ordercontent'                 => $this->input->post('ordercontent'),
            'uit_emptype'                      => $this->input->post('emptypeto'),
            'uit_uoc_from'                     => $this->input->post('uocontrolto'),
            'uit_workdept_from'                => $this->input->post('deptto'),
            'uit_desig_from'                   => $this->input->post('desigto'),
                
            'uit_staffname'                    => $this->input->post('empmutual'),
          //  'uit_workingpost_from'           => $postto2,
            'uit_workingpost_from'             => $_POST['postto'],
            'uit_scid_from'                    => $this->input->post('campus'),
            'uit_scid_to'                      => $this->input->post('campusfrom'),
            'uit_uoc_to'                       => $this->input->post('uocfrom'),
            'uit_dept_to'                      => $this->input->post('deptfrom'),
            'uit_desig_to'                     => $this->input->post('desigfrom'),
            'uit_post_to'                      =>$postfrom2,
            'uit_tta_detail'                   => $this->input->post('ttadetail'),
                
            'uit_dateofrelief'                 => $this->input->post('dateofrelief'),
            'uit_dateofjoining'                => $this->input->post('expdoj'),
            'uit_email_sentto'                 => $this->input->post('emailsentto'),
            'uit_emptypeto'                    => $this->input->post('employeetype'),
            'uit_schm_from'                    => $this->input->post('schemto'),
            'uit_schm_to'                      => $this->input->post('schemfrom'),
          // 'uit_ddoid_to'                     => $ddoid, // from ddo
            'uit_ddoid_to'                     => $ddoidfrom, // from ddo
            //'uit_group_to'                     => $this->input->post('group'),
            'uit_group_to'                     => $groupfrom,
            'uit_paybandid_to'                 => $this->input->post('payband'),
            'uit_vacanttype_to'                => $this->input->post('empptfrom'),
            'uit_transfertype'                 => $transtype,
            'uit_ddoid_from'                   => $this->input->post('ddo'),
            'uit_group_from'                   => $this->input->post('group')
                    
        );
            //'uit_ddoid_to'                     => $this->input->post('ddo'), // from ddo
        $usrinputtfr_flag=$this->sismodel->insertrec('user_input_transfer', $data2);
        if(!$usrinputtfr_flag){
            $this->logger->write_logmessage("error","Error in Staff Transfer and Posting", "Error in Staff Transfer and Posting" );
            $this->logger->write_dblogmessage("error","Error in Staff Transfer and Posting", "Error in Staff Transfer and Posting");
            $this->session->set_flashdata('err_message','Error in Staff Transfer and Posting - ', 'error');
            $this->load->view('staffmgmt/stafftransfer');
        }
        /****************same for employee master updation*************************/
                
        /* insert record in service details with check duplicate and  update in empprofile table table */
        $id=$_POST['empname'];
        $postto=$this->commodel->get_listspfic1('designation','desig_name','desig_id',$_POST['postto'])->desig_name;
        $empdata = array(
            'emp_dept_code'    => $_POST['deptto'],
            'emp_desig_code'   => $_POST['desigto'],
          //  'emp_post'         => $postto,
            'emp_post'         => $_POST['postto'],
            'emp_worktype'     => $_POST['emptypeto'],
            'emp_salary_grade' => $_POST['payband'],
            'emp_schemeid'     => $_POST['schemto'],
            'emp_scid'         => $_POST['campus'] ,
            'emp_uocid'        => $_POST['uocontrolto'],
            'emp_uocuserid'    => $_POST['uocontrolto'],
            'emp_ddouserid'    => $_POST['ddo'],
            'emp_ddoid'        => $_POST['ddo'],
            'emp_group'        => $_POST['group'],
                    
        );
        $upempdata_flag=$this->sismodel->updaterec('employee_master', $empdata,'emp_id',$id);
                    
        /****************************************insert record in service particular************************************************/
        $desigcode=$this->commodel->get_listspfic1('designation','desig_code','desig_id',$_POST['desigfrom'])->desig_code;
        // $shownap=$this->commodel->get_listspfic1('designation','desig_id','desig_name',$_POST['emppost'])->desig_id;
        $this->sismodel->insertsdetail($id,$_POST['campus'],$_POST['uocontrolto'],$_POST['deptto'],$desigcode,
        $_POST['schemto'],$_POST['ddo'],$_POST['group'],$_POST['payband'],'',$_POST['postto'],'','','',$this->input->post('usono'));
        /************************************************************************************************************/
        $id=$_POST['empmutual'];
        //$postto=$this->commodel->get_listspfic1('designation','desig_name','desig_id',$_POST['postfrom'])->desig_name;
                    
        $empdata2 = array(
           'emp_dept_code'    => $_POST['deptfrom'],
           'emp_desig_code'   => $_POST['desigfrom'],
           'emp_post'         => $_POST['postfrom'],
           'emp_worktype'     => $_POST['employeetype'],
           'emp_salary_grade' => $_POST['payband'],
           'emp_schemeid'     => $_POST['schemfrom'],
           'emp_scid'         => $_POST['campusfrom'] ,
           'emp_uocid'        => $_POST['uocfrom'],
           'emp_uocuserid'    => $_POST['uocfrom'],
           'emp_ddouserid'    => $ddouserid,//from ddo
           //'emp_ddoid'      =>$ddoid,//from ddo
           'emp_ddoid'        =>$ddoidfrom,//from ddo
           'emp_group'        =>$groupfrom,
                    
        );
           //'emp_ddouserid'    => $_POST['ddo'],//from ddo
//           'emp_ddoid'        => $_POST['ddo'],//from ddo
        $upempdata_flag=$this->sismodel->updaterec('employee_master', $empdata2,'emp_id',$id);
                    
        /****************************************insert record in service particular************************************************/
        $desigcode=$this->commodel->get_listspfic1('designation','desig_code','desig_id',$_POST['desigto'])->desig_code;
        // $shownap=$this->commodel->get_listspfic1('designation','desig_id','desig_name',$_POST['emppost'])->desig_id;
        $this->sismodel->insertsdetail($id,$_POST['campusfrom'],$_POST['uocfrom'],$_POST['deptfrom'],$desigcode,
        $_POST['schemfrom'],$ddoidfrom,$groupfrom,$_POST['payband'],'',$_POST['postto'],'','','',$this->input->post('usono'));
        /***********************************************************************************************/
        return $upempdata_flag;
        
    }
    /***********************insert record of staff for working arrangement data************/
    public function workorder($empname){
        $empuserid=$this->sismodel->get_listspfic1('employee_master','emp_userid','emp_id',$empname)->emp_userid;
        $empcode=$this->sismodel->get_listspfic1('employee_master','emp_code','emp_id',$empname)->emp_code;
        
        $Wdata=array(
            'swap_userid'       =>$empuserid,
            'swap_empcode'      =>$empcode,
            'swap_ocampus'      =>$this->input->post('campusfrom'),
            'swap_ouo'          =>$this->input->post('uocfrom'),
            'swap_odept'        =>$this->input->post('deptfrom'),
            'swap_wcampus'      =>$this->input->post('campus'),
            'swap_wuo'          =>$this->input->post('uocontrolto'),
            'swap_wdept'        =>$this->input->post('deptto'),    
            'swap_creatorid'    =>$this->session->userdata('username'),
            'swap_creatordate'  =>date('y-m-d'),
            'swap_modifierid'   =>$this->session->userdata('username'),
            'swap_modifydate'   =>date('y-m-d'),
        
        );
        $workorder_flag=$this->sismodel->insertrec('staff_working_arrangements_perticulars', $Wdata);
        if(!$workorder_flag){
            $this->logger->write_logmessage("error","Error in Staff Transfer and Posting", "Error in Staff Transfer and Posting[Working Arrangement]" );
            $this->logger->write_dblogmessage("error","Error in Staff Transfer and Posting", "Error in Staff Transfer and Posting[Working Arrangement]");
            $this->session->set_flashdata('err_message','Error in Staff Transfer and Posting[Working Arrangement] - ', 'error');
            $this->load->view('staffmgmt/stafftransfer');
        }
    
    }
       
    /***********************************closer working arrangement data *********************************************/  
    /***********************insert record of staff Transfer with Post and budget data************/
    public function transferpostbudget($empname,$lastentryid){
        $empid=$this->sismodel->get_listspfic1('employee_master','emp_id','emp_id',$empname)->emp_id;
        $pbudgetdata=array(
            'spwp_empid'      =>$empid,
            'spwp_uitid'      =>$lastentryid, 
            'spwp_ocampus'    =>$this->input->post('campusfrom'), 
            'spwp_ouo'        =>$this->input->post('uocfrom'),  
            'spwp_odept'      =>$this->input->post('deptfrom'),  
            'spwp_wcampus'    =>$this->input->post('campus'),   
            'spwp_wuo'        =>$this->input->post('uocontrolto'),  
            'spwp_wdept'      =>$this->input->post('deptto'),     
            'spwp_creatorid'  =>$this->session->userdata('username'),  
            'spwp_creatordate'  =>date('y-m-d'),  
            'spwp_modifierid'   =>$this->session->userdata('username'),
            'spwp_modifydate'   => date('y-m-d'),
        );
        $pbudget_flag=$this->sismodel->insertrec('staff_postwbudget_particulars', $pbudgetdata);
        if(!$pbudget_flag){
            $this->logger->write_logmessage("error","Error in Staff Transfer and Posting", "Error in Staff Transfer and Posting[Transfer with post and budget]" );
            $this->logger->write_dblogmessage("error","Error in Staff Transfer and Posting", "Error in Staff Transfer and Posting[Transfer with post and budget]");
            $this->session->set_flashdata('err_message','Error in Staff Transfer and Posting[Transfer with post and budget] - ', 'error');
            $this->load->view('staffmgmt/stafftransfer');
        }
        
    }
    /***********************************closer Transfer with Post and budget data************************/ 
    
}    

