<?php

 /* 
 * @name Report.php
 * @author Nagendra Kumar Singh(nksinghiitk@gmail.com)
 * @author Deepika Chaudhary (chaudharydeepika88@gmail.com)
 * @author Malvika Upadhyay (malvikaupadhyay644@gmail.com)
 * @author Manorama Pal (palseema30@gmail.com)// staff profile and service particulars,Reports(Designation wise,position-summary
 *  vacancy position,professorlist,hodlist.) 
 * @author Sumit Saxena(sumitsesaxena@gmail.com)[view employee profile]
 * @author Om Prakas (omprakashkgp@gmail.com) Discipline Wise List, List Staff Position 
 */

class Report  extends CI_Controller
{

   function __construct() {
        parent::__construct();
        $this->load->model('Common_model',"commodel");
        $this->load->model('Login_model',"lgnmodel"); 
        $this->load->model('SIS_model',"sismodel");
	$this->load->helper('download');
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
         }
    }

// View faculty list
    public function listfac() {
        $datawh = array('roleid' => '2');
        $this->tresult=$this->commodel->get_listspficarry('user_role_type','userid,scid,deptid','roleid',2);
        $this->load->view('report/listfac');
        return;
	}

// View staff list
    public function liststaff() {
        $datawh = array('roleid' => '4');
        $this->tresult=$this->commodel->get_listspficarry('user_role_type','userid,scid,deptid','roleid',4);
        $this->load->view('report/liststaff');
        return;
	}

    public function deptemployeelist(){
        $selectfield ="emp_uocid, emp_dept_code,emp_name, emp_post";
        $whorder = "emp_uocid asc, emp_dept_code  asc";
        $data['records'] = $this->sismodel->get_orderlistspficemore('employee_master',$selectfield,'',$whorder);
        $this->logger->write_logmessage("view"," view departmentt employee list" );
        $this->logger->write_dblogmessage("view"," view department employee list");
        $this->load->view('report/deptemployeelist',$data);
    }

    public function staffstrengthlist(){
        $selectfield ="sp_uo, sp_dept,sp_grppost, sp_sancstrenght , sp_position , sp_vacant";
        $whorder = "sp_uo asc, sp_dept  asc";
        $data['records'] = $this->sismodel->get_orderlistspficemore('staff_position',$selectfield,'',$whorder);
        $this->logger->write_logmessage("view"," view staff strength list" );
        $this->logger->write_dblogmessage("view"," view staff strength list");
        $this->load->view('report/staffstrengthlist',$data);
    }

    public function staffvacposition(){
        $selectfield ="sp_uo, sp_dept,sp_grppost,sp_schemecode, sp_emppost,sp_sancstrenght , sp_position , sp_vacant, sp_remarks";
        $whorder = "sp_grppost asc, sp_uo  asc";
        $data['records'] = $this->sismodel->get_orderlistspficemore('staff_position',$selectfield,'',$whorder);
        $this->logger->write_logmessage("view"," view staff vacancy position list" );
        $this->logger->write_dblogmessage("view"," view staff vacancy position list");
        $this->load->view('report/staffvacposition',$data);
    }

        /***************************************View Employee List******************************************************/
    public function viewprofile($id=0) {
        $worktype=$this->input->post('workingtype',TRUE);
        $empdata['filter']=$id;
        if(!empty($worktype) && ($id!== 0)){
            $filter=$this->input->post('filter',TRUE);
            $empdata['wtype']=$worktype; 
            $datawh=array('emp_worktype' => $worktype);
            $empdata['emprecord'] = $this->sismodel->searchemp_profile('employee_master',$worktype,$filter);
        }
        else{
            $empdata1=array();
            $empdata['emprecord']=$empdata1;
        }
        $this->load->view('report/viewprofile',$empdata);
        return;
	}
    
   public function viewfull_profile() {
	  
	//get id for employee to show data	
	$emp_id = $this->uri->segment(3);
        $emp_data['emp_id']=$emp_id;
	//get all profile and service data
	$emp_data['data'] = $this->sismodel->get_listrow('employee_master','emp_id',$emp_id)->row();
        $emp_data['servicedata'] = $this->sismodel->get_listrow('employee_servicedetail','empsd_empid',$emp_id);
        $emp_data['performancedata'] = $this->sismodel->get_listrow('Staff_Performance_Data','spd_empid',$emp_id)->row();
        $this->load->view('report/viewfull_profile',$emp_data);
  }

############################## Discipline Wise List ##########################################

public function disciplinewiselist(){
        $selectfield ="emp_dept_code, emp_name, emp_desig_code,emp_specialisationid";
	$whdata = array ('emp_specialisationid >' => 0);
        $whorder = "emp_specialisationid";
        $this->result = $this->sismodel->get_orderlistspficemore('employee_master',$selectfield,$whdata,$whorder);
//      $this->result = $this->sismodel->get_list('employee_master');
        $this->logger->write_logmessage("view"," view  Discipline Wise Report " );
        $this->logger->write_dblogmessage("view"," view  Discipline Wise Report ");
        $this->load->view('report/disciplinewiselist');
}

    public function listofstaffposition(){
        $selectfield ="sp_uo, sp_dept,sp_grppost, sp_emppost, sp_sancstrenght , sp_position , sp_vacant";
	$whdata = array('sp_position >'=>0);
        $whorder = "sp_uo, sp_dept, sp_emppost";
        $data['records'] = $this->sismodel->get_orderlistspficemore('staff_position',$selectfield, $whdata, $whorder);
        $this->logger->write_logmessage("view"," view list staff position list" );
        $this->logger->write_dblogmessage("view"," view list staff position list");
        $this->load->view('report/listofstaffposition',$data);
    }


    public function desigemployeelist(){
        $selectfield ="emp_desig_code,emp_dept_code,emp_name";
        $whdata = array ('emp_worktype' => 'Teaching');
        $whorder = "emp_desig_code  asc";
        $data['records'] = $this->sismodel->get_orderlistspficemore('employee_master',$selectfield,$whdata,$whorder);
        $this->logger->write_logmessage("view"," view designation wise employee list" );
        $this->logger->write_dblogmessage("view"," view designation wise employee list");
        $this->load->view('report/desigemployeelist',$data);
    }
    public function positionsummary(){
        $selectfield ="sp_emppost,sp_sancstrenght,sp_position,sp_vacant";
        //$whdata = array ('sp_tnt' => 'Non Teaching');
        $whdata = '';
        $whorder = "sp_emppost asc";
        $data['records'] = $this->sismodel->get_orderlistspficemore('staff_position',$selectfield,$whdata,$whorder);
        
        $this->logger->write_logmessage("view"," view position Summary" );
        $this->logger->write_dblogmessage("view"," view position Summary");
        $this->load->view('report/positionsummary',$data);
    }   
    public function positionvacancy(){
        $data['allpost']=$this->sismodel->get_distinctrecord('staff_position','sp_emppost','');
        $this->logger->write_logmessage("view"," view position vacancy" );
        $this->logger->write_dblogmessage("view"," view position vacancy");
        $this->load->view('report/positionvacancy',$data);
    }
    /*Professor list report and service period*/
    public function professorlist(){
        $getdesgid=$this->commodel->get_listspfic1('designation','desig_id','desig_name','Professor')->desig_id;
        $selectfield ="emp_name,emp_dor,emp_specialisationid,emp_dept_code,emp_doj";
        $whdata=array('emp_desig_code' => $getdesgid);
        $whorder = "emp_doj asc";
        $data['emplist'] = $this->sismodel->get_orderlistspficemore('employee_master',$selectfield,$whdata,$whorder);
        $this->logger->write_logmessage("view"," view list of professors in report " );
        $this->logger->write_dblogmessage("view"," view list of professors in report");
        $this->load->view('report/professorlist',$data);
    } 
    public function hodlist(){
        $today= date("Y-m-d H:i:s"); 
        $whdata=array('hl_dateto >='=> $today);
        $selectfield ="hl_scid";
        $data['allsc']=$this->sismodel->get_distinctrecord('hod_list',$selectfield,$whdata);
        $this->logger->write_logmessage("view"," view list of HOD in report " );
        $this->logger->write_dblogmessage("view"," view list of HOD in report");
        $this->load->view('report/hodlist',$data);
    } 
    
    
}

