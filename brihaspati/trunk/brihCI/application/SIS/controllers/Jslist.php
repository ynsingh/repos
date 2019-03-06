<?php

defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Jslist.php
 * @author Nagendra Kumar Singh nksinghiitk@gmail.com
 */

class Jslist extends CI_Controller
{
 
    	function __construct() {
        	parent::__construct();

        	$this->load->model('Common_model',"commodel");
        	$this->load->model('Login_model',"lgnmodel"); 
        	$this->load->model('SIS_model',"sismodel");
        	$this->load->model('Dependrop_model',"depmodel");

        	if(empty($this->session->userdata('id_user'))) {
            		$this->session->set_flashdata('flash_data', 'You don\'t have access!');
            		redirect('welcome');
        	}
    	}
 
    	public function index(){
    	}

	/* This function has been created for get list of Designation on the basis of  selected Working type */
        public function getwdesiglist(){
                $groups = $this->input->post('wtype');

                $datawh=array('desig_type' => $groups);
		$rlid=$this->session->userdata('id_role');
       	        $usrid=$this->session->userdata('id_user');

		$whorder = "desig_name asc";
        	$grp_data = $this->commodel->get_orderlistspficemore('designation','desig_id,desig_name,desig_code',$datawh,$whorder);
                //$grp_data = $this->commodel->get_listspficemore('designation','desig_id,desig_name,desig_code',$datawh);
                $desig_select_box ='';
                $desig_select_box.='<option value=null>--Select Designation--';
		$desig_select_box.='<option value='.All.'>'.All. ' ';
	        if(count($grp_data)>0){
	                foreach($grp_data as $grprecord){
                        	$desig_select_box.='<option value='.$grprecord->desig_id.'>'.$grprecord->desig_name.'('. $grprecord->desig_code .')'.' ';
                	}
        	}
                echo json_encode($desig_select_box);
        }

    	/* This function has been created for get list of Designation on the basis of  selected Group */
    	public function getgdesiglist(){
        	$groups = $this->input->post('group');
        	$datawh=array('desig_group' => $groups);
		$whorder = ("desig_name asc");
                $grp_data = $this->commodel->get_orderlistspficemore('designation','desig_id,desig_name,desig_code',$datawh,$whorder);
//	        $grp_data = $this->commodel->get_listspficemore('designation','desig_id,desig_name,desig_code',$datawh);
        	$desig_select_box ='';
	        $desig_select_box.='<option value="">--Select Designation--';
        	foreach($grp_data as $grprecord){
	        	$desig_select_box.='<option value='.$grprecord->desig_id.'>'.$grprecord->desig_name.'('. $grprecord->desig_code .')'.' ';
        	}
        	echo json_encode($desig_select_box);
    	}

    	/* This function has been created for get list of Designation on the basis of  selected Group and working type*/
    	public function getgwdesiglist(){
		$combid= $this->input->post('gwt');
		$parts = explode(',',$combid);
	        $datawh=array('desig_group' => $parts[0],'desig_type' => $parts[1]);
		$whorder = ("desig_name asc");
                $grp_data = $this->commodel->get_orderlistspficemore('designation','desig_id,desig_name,desig_code',$datawh,$whorder);
//        	$grp_data = $this->commodel->get_listspficemore('designation','desig_id,desig_name,desig_code',$datawh);
	        $desig_select_box ='';
        	$desig_select_box.='<option value="">--Select Designation--';
	        foreach($grp_data as $grprecord){
        		$desig_select_box.='<option value='.$grprecord->desig_id.'>'.$grprecord->desig_name.'('. $grprecord->desig_code .')'.' ';
        	}
        	echo json_encode($desig_select_box);
	}    

	 /* This function has been created for get list of Pay scales on the basis of  selected Group, working type and designation*/
        public function getgwdesigpaylist(){
                $combid= $this->input->post('gwtdesig');
                $parts = explode(',',$combid);
                $datawh=array('desig_group' => $parts[0],'desig_type' => $parts[1],'desig_id'=> $parts[2]);
                $grp_data = $this->commodel->get_listspficemore('designation','desig_id,desig_payscale',$datawh);
                $desig_select_box ='';
                $desig_select_box.='<option value="">--Select Payscale--';
                foreach($grp_data as $grprecord){
                        $desig_select_box.='<option value='.$grprecord->desig_id.'>'.$grprecord->desig_payscale.'' ;
                }
                echo json_encode($desig_select_box);
        }

	 /* This function has been created for get list of Pay scales on the basis of  selected working type and pay commission*/
        public function getwpcpaylist(){
                $combid= $this->input->post('wpc');
                $parts = explode(',',$combid);
                $datawh=array();
		if($parts[1] == '6th'){
			$datawh['sgm_pc'] = "6th";
		}else{
			$datawh['sgm_pc'] = "7th";
		}
	        $ps_data = $this->sismodel->get_listspficemore('salary_grade_master','sgm_id,sgm_name,sgm_max,sgm_min,sgm_gradepay,sgm_level',$datawh);
                $ps_select_box ='';
                $ps_select_box.='<option value="">--Select Payscale--';
		if(!empty($ps_data)){
	                foreach($ps_data as $psrecord){
        	                $ps_select_box.='<option value='.$psrecord->sgm_id.'>'.$psrecord->sgm_name.'('.$psrecord->sgm_min.' - '.$psrecord->sgm_max.')'.$psrecord->sgm_gradepay.$psrecord->sgm_level.'' ;
                	}
		} //if close
                echo json_encode($ps_select_box);
        }

	
    /* This function has been created for get the plan, non plan, ugc, ICAR, GOI shown against position */
    public function getemppnp(){
        $combval = $this->input->post('combfive');
        $parts = explode(',',$combval);
        $datawh=array('sp_campusid' => $parts[0],'sp_uo' => $parts[1], 'sp_dept' => $parts[2],'sp_emppost' => $parts[3], 'sp_tnt' => $parts[4]);
        $emptype_data = $this->sismodel->get_listspficemore('staff_position', 'sp_plan_nonplan',$datawh);
        $emptype_select_box ='';
        $emptype_select_box.='<option value="">------ Select Plan  Non Plan -----------';
        if(!empty($emptype_data)){
            foreach($emptype_data as $empdata){
                $emptype_select_box.='<option value='.$empdata->sp_plan_nonplan.'>'.$empdata->sp_plan_nonplan.' ';
            }//foreach
        } //if close   
        echo json_encode($emptype_select_box);
    }
   
	/* get the dept list on the basis of UO */
	public function getdeptlist(){
		$combid= $this->input->post('worktypeuo');
        	$parts = explode(',',$combid);
       // echo "sc===".$combid;
        	if($parts[1]!="All"){
            		$datawh=array('dept_uoid' => $parts[1]);
            	//	$datawh=array('emp_worktype' => $parts[0],'emp_uocid' => $parts[1]);
        	}
        	else{
            	//	$datawh=array('emp_worktype' => $parts[0]);
        	}
//      get_orderdistinctrecord($tbname,$selectfield,$whdata,$whorder)
        $whorder = 'dept_name asc';
	$comb_data = $this->commodel->get_orderlistspficemore('Department','dept_id,dept_name,dept_code',$datawh,$whorder);
//        $comb_data = $this->sismodel->get_orderdistinctrecord('employee_master','emp_dept_code',$datawh,$whorder);
        $dept_select_box =' ';
        $dept_select_box.='<option value=null>--Select Department--';
        $dept_select_box.='<option value='.All.'>'.All. ' ';
        if(count($comb_data)>0){
            foreach($comb_data as $detail){
//                $deptname=$this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id',$detail->emp_dept_code)->dept_name;
  //              $deptcode=$this->commodel->get_listspfic1('Department', 'dept_code', 'dept_id',$detail->emp_dept_code)->dept_code;

    //            $dept_select_box.='<option value='.$detail->emp_dept_code.'>'.$deptname. '(' .$deptcode. ')'.' ';
                $dept_select_box.='<option value='.$detail->dept_id.'>'.$detail->dept_name. '(' .$detail->dept_code. ')'.' ';

            }
        }
        echo json_encode($dept_select_box);

	}

	public function getrentpayrange(){
                $pcom = $this->input->post('pcom');

                if($pcom == '6th'){
                        $gpr_select_box ='';
                        $gpr_select_box.='<option value=>-------Select Pay Range--------';
                        $gpr_select_box.='<option value=6000-10199>6000-10199';
                        $gpr_select_box.='<option value=10200-18599>10200-18599';
                        $gpr_select_box.='<option value=18600-inf>18600-inf';
                }else{
                        $gpr_select_box ='';
                        $gpr_select_box.='<option value=>-------Select Pay Range--------';
                        $gpr_select_box.='<option value=18201-26200>18201-26200';
                        $gpr_select_box.='<option value=26201-48700>26201-48700';
                        $gpr_select_box.='<option value=48700-inf>48700-inf';
                }
                echo json_encode($gpr_select_box);
        }


	 public function getccapayrange(){
                $pcom = $this->input->post('pcom');

                if($pcom == '6th'){
                        $gpr_select_box ='';
                        $gpr_select_box.='<option value=>-------Select Pay Range--------';
                        $gpr_select_box.='<option value=0-8000>0-8000';
                        $gpr_select_box.='<option value=8001-12000>8001-12000';
                        $gpr_select_box.='<option value=12001-16000>12001-16000';
                        $gpr_select_box.='<option value=16001-inf>16001-inf';
                }else{
                        $gpr_select_box ='';
                        $gpr_select_box.='<option value=>-------Select Pay Range--------';
                        $gpr_select_box.='<option value=0-20600>0-20600';
                        $gpr_select_box.='<option value=20601-30800>20601-30800';
                        $gpr_select_box.='<option value=30801-41100>30801-41100';
                        $gpr_select_box.='<option value=41101-inf>41101-inf';
                }
                echo json_encode($gpr_select_box);
        }



	public function getpayrange(){
		$pcom = $this->input->post('pcom');
		
		if($pcom == '6th'){
			$gpr_select_box ='';
                        $gpr_select_box.='<option value=>-------Select Pay Range--------';
			$gpr_select_box.='<option value=0-5299>0-5299';
			$gpr_select_box.='<option value=5300-6699>5300-6699';
			$gpr_select_box.='<option value=6700-8189>6700-8189';
			$gpr_select_box.='<option value=8190-9299>8190-9299';
			$gpr_select_box.='<option value=9300-10599>9300-10599';
			$gpr_select_box.='<option value=10600-11899>10600-11899';
			$gpr_select_box.='<option value=11900-13969>11900-13769';
			$gpr_select_box.='<option value=13770-14509>13770-14509';
			$gpr_select_box.='<option value=14510-15999>14510-15999';
			$gpr_select_box.='<option value=16000-17299>16000-17299';
			$gpr_select_box.='<option value=17300-19529>17300-19529';
			$gpr_select_box.='<option value=19530-20089>19530-20089';
			$gpr_select_box.='<option value=20090-21019>20090-21019';
			$gpr_select_box.='<option value=21020-21579>21020-21579';
			$gpr_select_box.='<option value=21580-22139>21580-22139';
			$gpr_select_box.='<option value=22140-24999>22140-24999';
			$gpr_select_box.='<option value=25000-inf>25000-inf';
		}else{
			$gpr_select_box ='';
                        $gpr_select_box.='<option value=>-------Select Pay Range--------';
			$gpr_select_box.='<option value=0-13600>0-13600';
			$gpr_select_box.='<option value=13601-17200>13601-17200';
			$gpr_select_box.='<option value=17201-21000>17201-21000';
			$gpr_select_box.='<option value=21001-23900>21001-23900';
			$gpr_select_box.='<option value=23901-27200>23901-27200';
			$gpr_select_box.='<option value=27201-30600>27201-30600';
			$gpr_select_box.='<option value=30601-35400>30601-35400';
			$gpr_select_box.='<option value=35401-37300>35401-37300';
			$gpr_select_box.='<option value=37301-41100>37301-41100';
			$gpr_select_box.='<option value=41101-44500>41101-44500';
			$gpr_select_box.='<option value=44501-50200>44501-50200';
			$gpr_select_box.='<option value=50201-51600>50201-51600';
			$gpr_select_box.='<option value=51601-54000>51601-54000';
			$gpr_select_box.='<option value=54001-55500>54001-55500';
			$gpr_select_box.='<option value=55601-56900>55501-56900';
			$gpr_select_box.='<option value=56901-64200>56901-64200';
			$gpr_select_box.='<option value=64201-inf>64201-inf';
		}
		echo json_encode($gpr_select_box);
	}


	public function getpayband(){
                $wtype = $this->input->post('pcwt');
                $parts = explode(',',$wtype);
       // echo json_encode("this is testing----".$wtype);
                if(($parts[0] == 'Teaching')&&($parts[1] == '6th')){
                        $pb_select_box ='';
                        $pb_select_box.='<option value=>-------Select Pay Band--------';
                        $pb_select_box.='<option value=UGC1> UGC1';
                        $pb_select_box.='<option value=UGC2> UGC2';
                        $pb_select_box.='<option value=UGC3> UGC3';
                        $pb_select_box.='<option value=UGC4> UGC4';
                        $pb_select_box.='<option value=UGC5> UGC5';
                        $pb_select_box.='<option value=HGP> HGP';
                        $pb_select_box.='<option value=Fixed> Fixed';
                }
                elseif(($parts[0] == 'Non Teaching')&&($parts[1] == '6th')){
                        $pb_select_box ='';
                        $pb_select_box.='<option value= >-------Select Pay Band--------';
                        $pb_select_box.='<option value=PB1A> PB1A';
                        $pb_select_box.='<option value=PB1> PB1';
                        $pb_select_box.='<option value=PB2> PB2';
                        $pb_select_box.='<option value=PB3> PB3';
                        $pb_select_box.='<option value=PB4> PB4';
                }else{
                        $pb_select_box ='';
                }
                echo json_encode($pb_select_box);
        }

        public function getlevelpay(){
                $wtype = $this->input->post('wt');
       // echo json_encode("this is testing----".$wtype);
                if($wtype == 'Teaching'){
                        $lpb_select_box ='';
                        $lpb_select_box.='<option value=>-------Select Level of Pay -------';
                        $lpb_select_box.='<option value=Level10> Level10';
                        $lpb_select_box.='<option value=Level11> Level11';
                        $lpb_select_box.='<option value=Level12> Level12';
                        $lpb_select_box.='<option value=Level13A> Level13A';
                        $lpb_select_box.='<option value=Level14> Level14';
                        $lpb_select_box.='<option value=Level15> Level15';
                        $lpb_select_box.='<option value=Fixed> Fixed';
                }
		 else{
                        $lpb_select_box ='';
                        $lpb_select_box.='<option value=>-------Select Level of Pay -------';
                        $lpb_select_box.='<option value=Level1> Level1';
                        $lpb_select_box.='<option value=Level2> Level2';
                        $lpb_select_box.='<option value=Level3> Level3';
                        $lpb_select_box.='<option value=Level4> Level4';
                        $lpb_select_box.='<option value=Level5> Level5';
                        $lpb_select_box.='<option value=Level6> Level6';
                        $lpb_select_box.='<option value=Level7> Level7';
                        $lpb_select_box.='<option value=Level8> Level8';
                        $lpb_select_box.='<option value=Level9> Level9';
                        $lpb_select_box.='<option value=Level10> Level10';
                        $lpb_select_box.='<option value=Level11> Level11';
                        $lpb_select_box.='<option value=Level12> Level12';
                        $lpb_select_box.='<option value=Level13> Level13';
                        $lpb_select_box.='<option value=Level14> Level14';
                        $lpb_select_box.='<option value=Level15> Level15';
                        $lpb_select_box.='<option value=Level16> Level16';
                        $lpb_select_box.='<option value=Level17> Level17';
                        $lpb_select_box.='<option value=Level18> Level18';
                        $lpb_select_box.='<option value=Level19> Level19';
                        $lpb_select_box.='<option value=Level20> Level20';
                        $lpb_select_box.='<option value=Level21> Level21';
                        $lpb_select_box.='<option value=Level22> Level22';
                        $lpb_select_box.='<option value=Level23> Level23';
                        $lpb_select_box.='<option value=Level24> Level24';
                        $lpb_select_box.='<option value=Level25> Level25';
                        $lpb_select_box.='<option value=Level26> Level26';
                        $lpb_select_box.='<option value=Level27> Level27';
                        $lpb_select_box.='<option value=Level28> Level28';
                        $lpb_select_box.='<option value=Level29> Level29';
                        $lpb_select_box.='<option value=Level30> Level30';
                        $lpb_select_box.='<option value=Level31> Level31';
                        $lpb_select_box.='<option value=Level32> Level32';
                }
                echo json_encode($lpb_select_box);
        }


	public function getemppdata(){
	        $values=array();
        	$pfno= $this->input->post('emplypfno');
	        $empid=$this->sismodel->get_listspfic1('employee_master', 'emp_id', 'emp_code',$pfno)->emp_id;
       // echo "pfno---".$pfno;
        	$emp_data=$this->sismodel->get_listrow('employee_master','emp_code',$pfno);
	        $empdetail = $emp_data->result();
        	if(count($empdetail)>0){
			 foreach($empdetail as $detail){
		                $campus=$this->commodel->get_listspfic1('study_center', 'sc_name', 'sc_id',$detail->emp_scid)->sc_name;
                		$uocname=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id',$detail->emp_uocid)->name;
		                $deptname=$this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id',$detail->emp_dept_code)->dept_name;
		                $schme=$this->sismodel->get_listspfic1('scheme_department', 'sd_name', 'sd_id',$detail->emp_schemeid)->sd_name;
				$designame=$this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id',$detail->emp_desig_code)->desig_name;
		                $empname=$detail->emp_name;
				$ddo=$this->sismodel->get_listspfic1('ddo','ddo_name','ddo_id',$detail->emp_ddoid)->ddo_name;
				$hragrade = $this->sismodel->get_listspfic1('employee_master_support','ems_hragrade','ems_empid',$empid)->ems_hragrade;
				$ccagrade = $this->sismodel->get_listspfic1('employee_master_support','ems_ccagrade','ems_empid',$empid)->ems_ccagrade;
				array_push($values, $campus,$uocname,$deptname,$schme,$ddo,$detail->emp_worktype,$designame,$empname,$hragrade,$ccagrade,$empid);
			}
		}
		else{
			$mess="Please enter the valid PF Number";
			array_push($values,$mess);
		}
		//implode("+",$values);
		echo json_encode($values);
	}

 
    /***** This function has been created for get the employee detail by pf no ********************************/
    public function getempdata(){
        $this->orgcode=$this->commodel->get_listspfic1('org_profile','org_code','org_id',1)->org_code;
        $this->campus=$this->commodel->get_listspfic2('study_center','sc_id','sc_name','org_code',$this->orgcode);
        $this->uoc=$this->lgnmodel->get_list('authorities');
        $pfno= $this->input->post('emplypfno');
        $emp_data=$this->sismodel->get_listrow('employee_master','emp_code',$pfno);
        $empdetail = $emp_data->result();
        if(count($empdetail)>0){
            foreach($empdetail as $detail){
                $campus=$this->commodel->get_listspfic1('study_center', 'sc_name', 'sc_id',$detail->emp_scid)->sc_name;
                $campusbox='<option value='.$detail->emp_scid.'>'.$campus.' ';
                foreach($this->campus as $camdata){	
                 $campusbox.='<option  value='.$camdata->sc_id.'>'.$camdata->sc_name.''; 
                }            
                $uocname=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id',$detail->emp_uocid)->name;
                $uocbox='<option value='.$detail->emp_uocid.'>'.$uocname.' ';
                foreach($this->uoc as $ucodata){	
                    $uocbox.='<option  value='.$ucodata->id.'>'.$ucodata->name.''; 
                }            
                $deptname=$this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id',$detail->emp_dept_code)->dept_name;
                $deptcode=$this->commodel->get_listspfic1('Department', 'dept_code', 'dept_id',$detail->emp_dept_code)->dept_code;
                $deptbox='<option value='.$detail->emp_dept_code.'>'.$deptname."(".$deptcode.")".' ';
                
                $schme=$this->sismodel->get_listspfic1('scheme_department', 'sd_name', 'sd_id',$detail->emp_schemeid)->sd_name;
                $schmecd=$this->sismodel->get_listspfic1('scheme_department', 'sd_code', 'sd_id',$detail->emp_schemeid)->sd_code;
                $schmbox='<option value='.$detail->emp_schemeid.'>'.$schme."(".$schmecd.")".' ';
                
                /*$workbox='<option value='.$detail->emp_worktype.'>'.$detail->emp_worktype.' ';
                $workbox.='<option value='.'Teaching'.'>'.'Teaching'.' ';
                $workbox.='<option value='."Non Teaching".'>'."Non Teaching".' ';*/
                
                $designame=$this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id',$detail->emp_desig_code)->desig_name;
                $desigcd=$this->commodel->get_listspfic1('designation', 'desig_code', 'desig_id',$detail->emp_desig_code)->desig_code;
                $desigbox='<option value='.$detail->emp_desig_code.'>'.$designame."(".$desigcd.")".' ';
                
                $empname=$detail->emp_name;
                $empbox='<option value='.$detail->emp_id.'>'.$detail->emp_name.' ';
                
               // array_push($values,$campusbox,$uocbox,$deptbox,$schmbox,$detail->emp_worktype,$desigbox,
                $values=$campusbox."^".$uocbox."^".$deptbox."^".$schmbox."^".$detail->emp_worktype."^".$desigbox.
                "^".$empbox."^".$detail->emp_post."^".$detail->emp_type_code;
               
                                
            }
            echo json_encode($values);
                       
        }            
              
    }
    /****************************************closer employee detail by pf no *********************************/
    
    
    
     /***** This function has been created for get the employee detail by pf no ********************************/
    
    public function getempdata2(){
        $values=array();
        $pfno= $this->input->post('emplypfno');
	$empid=$this->sismodel->get_listspfic1('employee_master', 'emp_id', 'emp_code',$pfno)->emp_id;
       // echo "pfno---".$pfno;
        $emp_data=$this->sismodel->get_listrow('employee_master','emp_code',$pfno);
        $empdetail = $emp_data->result();
        if(count($empdetail)>0){
            
            foreach($empdetail as $detail){
                $campus=$this->commodel->get_listspfic1('study_center', 'sc_name', 'sc_id',$detail->emp_scid)->sc_name;
                $uocname=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id',$detail->emp_uocid)->name;
                $deptname=$this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id',$detail->emp_dept_code)->dept_name;
                $deptcode=$this->commodel->get_listspfic1('Department', 'dept_code', 'dept_id',$detail->emp_dept_code)->dept_code;
                $schme=$this->sismodel->get_listspfic1('scheme_department', 'sd_name', 'sd_id',$detail->emp_schemeid)->sd_name;
                $schmecd=$this->sismodel->get_listspfic1('scheme_department', 'sd_code', 'sd_id',$detail->emp_schemeid)->sd_code;
                $designame=$this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id',$detail->emp_desig_code)->desig_name;
                $desigcd=$this->commodel->get_listspfic1('designation', 'desig_code', 'desig_id',$detail->emp_desig_code)->desig_code;
                $empname=$detail->emp_name;

                $ifcbank=$detail->emp_bank_ifsc_code;
		$bank=$detail->emp_bankname;
		$bnkadd=str_replace(","," ",$detail->emp_bankbranch);
//		$ifcbank=$bank[0]."#".$bank[1]."#".$bnkadd;

                $accno=$detail->emp_bank_accno;
                $phno=$detail->emp_phone;
                $address=str_replace(","," ",$detail->emp_address);
		$address=preg_replace("/[\n\r]/","", $address);
                $email=$detail->emp_secndemail;
                $ddo=$this->sismodel->get_listspfic1('ddo','ddo_name','ddo_id',$detail->emp_ddoid)->ddo_name;
                $dor=date('d-m-Y',strtotime($detail->emp_dor));
                $doj=date('d-m-Y',strtotime($detail->emp_doj));
                $dob=date('d-m-Y',strtotime($detail->emp_dob));
                $payband=$this->sismodel->get_listspfic1('salary_grade_master','sgm_name','sgm_id',$detail->emp_salary_grade)->sgm_name;
                $pay_max=$this->sismodel->get_listspfic1('salary_grade_master','sgm_max','sgm_id',$detail->emp_salary_grade)->sgm_max;
                $pay_min=$this->sismodel->get_listspfic1('salary_grade_master','sgm_min','sgm_id',$detail->emp_salary_grade)->sgm_min;
                $gardepay=$this->sismodel->get_listspfic1('salary_grade_master','sgm_gradepay','sgm_id',$detail->emp_salary_grade)->sgm_gradepay;
                $payscale=$payband."(".$pay_min."-".$pay_max.")".$gardepay;
                $aadhaarno=substr($detail->emp_aadhaar_no, -4);
                $paycomm=$detail->emp_paycomm;
//		if(empty($paycomm)){
//			$paycomm ="";
//		}
       // 22 item push                         
                array_push($values, $campus,$uocname,$deptname,$schme,$ddo,$detail->emp_worktype,$detail->emp_group,$designame,$detail->emp_type_code,
                $doj,$empname,$accno,$aadhaarno,$dob, $address,$detail->emp_phone,$dor,$payscale,$bank,$ifcbank,$bnkadd,$paycomm);

//		array_push($values, $campus,$uocname,$deptname,$schme,$ddo,$detail->emp_worktype,$detail->emp_group,$designame,$detail->emp_type_code,
  //              $doj,$empname,$accno,$aadhaarno,$dob, $address,$detail->emp_phone,$dor."16",$payscale."17",$ifcbank.'181920',$paycomm.'21');

            }
            
        } 
        $emp_data2=$this->sismodel->get_listrow('employee_master_support','ems_code',$pfno);
        $empdetail2 = $emp_data2->result();
        if(count($empdetail2)>0){
            
            foreach($empdetail2 as $detail2){
                $housetype=$detail2->ems_house_type;
                $houseno=$detail2->ems_house_no;
                $pensioncontri=$detail2->ems_pensioncontri;
                $upfno=$detail2->ems_upfno;
                $univemp=$detail2->ems_universityemp;
                $washallowance=$detail2->ems_washingallowance;
                $dedtupf=$detail2->ems_deductupf;
                $hragrade=$detail2->ems_hragrade;
                $ccagrade=$detail2->ems_ccagrade;
                $inclsummary=$detail2->ems_inclsummary;
                $lic1no=$detail2->ems_lic1no;
                $lic1amount=$detail2->ems_lic1amount;
                $lic2no=$detail2->ems_lic2no;
                $lic2amount=$detail2->ems_lic2amount;
                $lic3no=$detail2->ems_lic3no;
                $lic3amount=$detail2->ems_lic3amount;
                $lic4no=$detail2->ems_lic4no;
                $lic4amount=$detail2->ems_lic4amount;
                $lic5no=$detail2->ems_lic5no;
                $lic5amount=$detail2->ems_lic5amount;
                $prdno1=$detail2->ems_prdno1;
                $prdno2=$detail2->ems_prdno2;
                $prdno3=$detail2->ems_prdno3;
                $plino1=$detail2->ems_plino1;
                $plino2=$detail2->ems_plino2;
                $society=$detail2->ems_society;
                $socmem=$detail2->ems_societymember;
                
                // 28 item push
                array_push($values,$pensioncontri,$upfno,$houseno,$housetype,$univemp,$washallowance,$dedtupf,$hragrade,$ccagrade,
                $inclsummary,$lic1no,$lic1amount,$lic2no,$lic2amount,$lic3no,$lic3amount,$lic4no,$lic4amount,$lic5no,$lic5amount,$prdno1,
                $prdno2,$prdno3,$plino1,$plino2,$society, $socmem,$empid);      

//                array_push($values,$pensioncontri,$upfno,$houseno,$housetype,$univemp,$washallowance,$dedtupf,$hragrade,$ccagrade,
  //              $inclsummary,$lic1no,$lic1amount,$lic2no,$lic2amount,$lic3no,$lic3amount,$lic4no,$lic4amount,$lic5no,$lic5amount,$prdno1,
    //            $prdno2,$prdno3,$plino1,$plino2,$society, $socmem);      
            }
            
        }
       // echo "values in controller===".$values;
        echo json_encode($values);
    }
    /****************************************closer employee detail by pf no *********************************/
    
    
    /* This function has been created for get society members on the basis of society */
    public function getsocietymembers(){
        $selval = $this->input->post('society');
        $socmember=$this->sismodel->get_listspfic1('societies','society_members','society_id',$selval)->society_members;
        echo json_encode($socmember);
    }
}    

