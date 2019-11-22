<?php

 /* 
 * @name Report.php
 * @author Nagendra Kumar Singh(nksinghiitk@gmail.com)
 */

class Report  extends CI_Controller
{

   function __construct() {
        parent::__construct();
        $this->load->model('Common_model',"commodel");
        $this->load->model('Login_model',"lgnmodel"); 
        $this->load->model('SIS_model',"sismodel");
        $this->load->model('PICO_model',"picomodel");
  	$this->load->helper('download');
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
         }
    }

   public function stocklist() {
	   	$whorder='stock_mtid asc';
        	$data['sresult']=$this->picomodel->get_orderlistspficemore('stock_items','*','',$whorder);
	        $this->load->view('report/liststock',$data);
        	return;
        }
	
   public function issueditemlist() {
	   	$whorder='sii_mtid asc';
        	$data['sresult']=$this->picomodel->get_orderlistspficemore('stock_items_issued','*','',$whorder);
	        $this->load->view('report/issuestock',$data);
        	return;
        }


   /**
    * Get Download PDF File
    *
    * @return Response
    */
  // function convertpdf($hdata,$filename){
   function convertpdf(){
	$filename = $this->input->post('fname');
	$hdata =$this->input->post('rdata') ; 
//	print_r($filename);die();
//	$html=$this->load->view('report/disciplinewiselist',$hdata);        
	$html=$this->load->view($filename,$hdata);        
        // Load pdf library
//	print_r($html);die();
        $this->load->library('pdf');
        
        // Load HTML content
        $this->pdf->load_html($html);
        
        // (Optional) Setup the paper size and orientation
        $this->pdf->set_paper('A4', 'landscape');
        
        // Render the HTML as PDF
        $this->pdf->render();
        
        // Output the generated PDF (1 = download and 0 = preview)
        //$this->pdf->stream("welcome.pdf", array("Attachment"=>0));
        $this->pdf->stream("disciplinewiselist.pdf", array("Attachment"=>0));
   }

//get all uo empid
	public function getempuoid(){
		$selectfield='emp_id';
                $whdata = array ('emp_leaving' => NULL,'emp_dor>='=>date('Y-m-d'),'ul_status'=>'Fulltime','ul_dateto'=> '1000-01-01 00:00:00');
		
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
        $selectfield ="emp_id,emp_code,emp_uocid, emp_dept_code,emp_name,emp_head, emp_post,emp_desig_code,emp_schemeid";
        $whorder = "emp_uocid asc, emp_dept_code  asc, emp_desig_code asc, emp_post asc";
	$cdate = date('Y-m-d');
        // add doris geater than current date and reason is null  in whdata
//	$whdata = array ('emp_leaving' => NULL,'emp_dor>='=>$cdate);
	$whdata = $this->getprofilefilerdata();
	$whdata['emp_leaving'] = NULL;
	$whdata['emp_dor>=']=$cdate;

        if(isset($_POST['filter'])) {
            //echo "ifcase post of filter";
            $wtype = $this->input->post('wtype');
            $uoff  = $this->input->post('uoff');
            $dept[]  = $this->input->post('dept');
		 
	    $this->wtyp = $wtype;
            $this->uolt = $uoff;
	    $whdata['emp_worktype']=$wtype;
            if($uoff !="All"){
	    	$whdata['emp_uocid']=$uoff;
	    }
	    $i=0;
	    if((!empty($dept))&&($dept != "null")){
                        foreach($dept as $row){
                        $this->deptmt = $row[$i];
                        $names = $row;
                        $i++;
                        }
                }
		if(!empty($names)){
			$data['records']= $this->sismodel->get_orderlistspficemoreorwh('employee_master',$selectfield,$whdata,'emp_dept_code',$names,$whorder);
	
		}else{
			$data['records']= $this->sismodel->get_orderlistspficemoreorwh('employee_master',$selectfield,$whdata,'','',$whorder);
		}
            //$data['records'] = $this->sismodel->get_orderlistspficemore('employee_master',$selectfield,$whdata,$whorder);
        }
        else{
            $data['records'] = $this->sismodel->get_orderlistspficemore('employee_master',$selectfield,$whdata,$whorder);
        }
        
        $this->logger->write_logmessage("view"," view departmentt employee list" );
        $this->logger->write_dblogmessage("view"," view department employee list");
        $this->load->view('report/deptemployeelist',$data);
    }

    public function getwhdata(){
		//get roleid from session
		$whdata ='';
		$rlid=$this->session->userdata('id_role');
		if ($rlid == 5){
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
		if ($rlid == 10){
			$usrname=$this->session->userdata('username');
//			print_r( $usrname); die; 
			if(($usrname === 'vc@tanuvas.org.in')||($usrname === 'registrar@tanuvas.org.in')||($usrname === 'admin')||($usrname === 'asection@tanuvas.org.in')||($usrname === 'rsection@tanuvas.org.in')){
			}else{
				$uoid=$this->lgnmodel->get_listspfic1('authorities','id','authority_email',$usrname)->id;
				$whdata = array ('sp_uo' => $uoid);
			}
		}
	return $whdata;
    }

    public function hodlist(){
	// get list of uo form authority table priority wise
	$data['uoc']=$this->lgnmodel->get_orderlistspficemore('authorities','priority,name,code','','name ASC');
        $today= date("Y-m-d H:i:s"); 
	$whdata=array('hl_status'=>'Fulltime','hl_dateto'=> '1000-01-01 00:00:00');
//        $whdata=array('hl_dateto >='=> $today);
	 if(isset($_POST['filter'])) {
            $uoff  = $this->input->post('uoff');
		if(!empty($uoff)){
			$whdata['hl_uopid']=$uoff;
			$data['uolt'] = $uoff;
		}
	}
        $selectfield ="hl_userid,hl_empcode,hl_deptid,hl_scid,hl_uopid";
	$whorder = "hl_uopid asc";
        $data['allsc']=$this->sismodel->get_orderlistspficemore('hod_list',$selectfield,$whdata,$whorder);
        $this->logger->write_logmessage("view"," view list of HOD in report " );
        $this->logger->write_dblogmessage("view"," view list of HOD in report");
        $this->load->view('report/hodlist',$data);
    }

	public function uolist(){
        $today= date("Y-m-d H:i:s");
	$selectfield ="ul_authuoid,ul_userid,ul_empcode, ul_uocode,ul_uoname,ul_id,  ul_modifydate";
	$whorder="ul_id asc,ul_authuoid ASC,  ul_modifydate DESC";
	$whdata=array('ul_status'=>'Fulltime','ul_dateto'=> '1000-01-01 00:00:00');
        $data['allsc']=$this->sismodel->get_orderdistinctrecord('uo_list',$selectfield,$whdata,$whorder);
        $this->logger->write_logmessage("view"," view list of UO in report " );
        $this->logger->write_dblogmessage("view"," view list of UO in report");
        $this->load->view('report/uolist',$data);
    }

    /********************slect uo list according to selection type**********************/
    public function getuolist(){
        $combid= $this->input->post('worktype');
       // $parts = explode(',',$combid); 
       // echo "sc===".$combid;
        $datawh=array('emp_worktype' => $combid);
        $comb_data = $this->sismodel->get_distinctrecord('employee_master','emp_uocid',$datawh);
        $uo_select_box =' ';
        $uo_select_box.='<option value=null>--Select University Officer--';
//	$usrname=$this->session->userdata('username');
  //      if(($usrname === 'vc@tanuvas.org.in')||($usrname === 'registrar@tanuvas.org.in')){
        	$uo_select_box.='<option value='.All.'>'.All. ' ';
    //    }
        if(count($comb_data)>0){
            foreach($comb_data as $detail){
                $auoname=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id',$detail->emp_uocid)->name;
                $auocode=$this->lgnmodel->get_listspfic1('authorities', 'code', 'id',$detail->emp_uocid)->code;
              
               $uo_select_box.='<option value='.$detail->emp_uocid.'>'.$auoname. '(' .$auocode. ')'.' ';
            }
        }
//	arsort($uo_select_box);
        echo json_encode($uo_select_box);
    } 
    
    
    /********************slect dept list according to selection type**********************/
    public function getdeptlist(){
        $combid= $this->input->post('worktypeuo');
        $parts = explode(',',$combid); 
       // echo "sc===".$combid;
        if($parts[1]!="All"){
            $datawh=array('emp_worktype' => $parts[0],'emp_uocid' => $parts[1]);
        }
        else{
            $datawh=array('emp_worktype' => $parts[0]);
        }
//	get_orderdistinctrecord($tbname,$selectfield,$whdata,$whorder)
	$whorder = 'emp_dept_code asc';
        $comb_data = $this->sismodel->get_orderdistinctrecord('employee_master','emp_dept_code',$datawh,$whorder);
        $dept_select_box =' ';
        $dept_select_box.='<option value=null>--Select Department--';
        $dept_select_box.='<option value='.All.'>'.All. ' ';
        if(count($comb_data)>0){
            foreach($comb_data as $detail){
                $deptname=$this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id',$detail->emp_dept_code)->dept_name;
                $deptcode=$this->commodel->get_listspfic1('Department', 'dept_code', 'dept_id',$detail->emp_dept_code)->dept_code;
                $dept_select_box.='<option value='.$detail->emp_dept_code.'>'.$deptname. '(' .$deptcode. ')'.' ';
            }
        }
        echo json_encode($dept_select_box);
        
    } 
    
    /********************slect designation list according to selection type**********************/
    public function getdesiglist(){
        $combid= $this->input->post('worktype');
       // $parts = explode(',',$combid); 
       // echo "sc===".$combid;
        $datawh=array('emp_worktype' => $combid);
	$rlid=$this->session->userdata('id_role');
        if ($rlid == 5){
                $usrid=$this->session->userdata('id_user');
                $deptid = '';
                $whdatad = array('userid' => $usrid,'roleid' => $rlid);
                $resu = $this->sismodel->get_listspficemore('user_role_type','deptid',$whdatad);
                foreach($resu as $rw){
                        $deptid=$rw->deptid;
                }
                $datawh['emp_dept_code'] = $deptid;
        }

	$whorder = 'emp_desig_code asc';
        $comb_data = $this->sismodel->get_orderdistinctrecord('employee_master','emp_desig_code',$datawh,$whorder);
        $desig_select_box =' ';
        $desig_select_box.='<option value=null>-- Select Designation --';
        $desig_select_box.='<option value='.All.'>'.All. ' ';
        if(count($comb_data)>0){
            foreach($comb_data as $detail){
                $designame=$this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id',$detail->emp_desig_code)->desig_name;
                $desigcode=$this->commodel->get_listspfic1('designation', 'desig_code', 'desig_id',$detail->emp_desig_code)->desig_code;
                $desig_select_box.='<option value='.$detail->emp_desig_code.'>'.$designame. '(' .$desigcode. ')'.' ';
            }
        }
        echo json_encode($desig_select_box);
    } 
    
    /********************slect uo list according to selection type**********************/
    public function getuodesiglist(){
        $combid= $this->input->post('wtdesig');
        $parts = explode(',',$combid); 
       // echo "sc===".$combid;
        if($parts[1]!='All'){
            $datawh=array('emp_worktype' => $parts[0],'emp_desig_code' => $parts[1]);
        }
        else{
            $datawh=array('emp_worktype' => $parts[0]);
        }
	$whorder = 'emp_uocid asc';
        $comb_data = $this->sismodel->get_orderdistinctrecord('employee_master','emp_uocid',$datawh,$whorder);
        $uo_select_box =' ';
        $uo_select_box.='<option value=null>-- Select University Officer --';
        $uo_select_box.='<option value='.All.'>'.All. ' ';
        if(count($comb_data)>0){
            foreach($comb_data as $detail){
                $auoname=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id',$detail->emp_uocid)->name;
                $auocode=$this->lgnmodel->get_listspfic1('authorities', 'code', 'id',$detail->emp_uocid)->code;
                $uo_select_box.='<option value='.$detail->emp_uocid.'>'.$auoname. '(' .$auocode. ')'.' ';
            }
        }
        echo json_encode($uo_select_box);
    }
    
    /********************slect dept list according to selection type**********************/
    public function getdeptuodesiglist(){
        $combid= $this->input->post('wtdesiguo');
        $parts = explode(',',$combid); 
       // echo "sc===".$combid;
        if($parts[1]!='All' && $parts[2]!='All'){
            $datawh=array('emp_worktype' => $parts[0],'emp_desig_code' => $parts[1],'emp_uocid' =>$parts[2] );
        }
        if($parts[1]=='All' && $parts[2]!='All'){
            $datawh=array('emp_worktype' => $parts[0],'emp_uocid' =>$parts[2] );
        }
        if($parts[1] != 'All' && $parts[2] == 'All'){
            $datawh=array('emp_worktype' => $parts[0],'emp_desig_code' => $parts[1]);
        }
        if($parts[1] == 'All' && $parts[2] == 'All'){
            $datawh=array('emp_worktype' => $parts[0]);
        }
	$whorder ='emp_dept_code asc';
        $comb_data = $this->sismodel->get_orderdistinctrecord('employee_master','emp_dept_code',$datawh,$whorder);
        $dept_select_box =' ';
        $dept_select_box.='<option value=null>-- Select Department --';
   //     $dept_select_box.='<option value='.All.'>'.All. ' ';
        if(count($comb_data)>0){
            foreach($comb_data as $detail){
                
                $deptname=$this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id',$detail->emp_dept_code)->dept_name;
                $deptcode=$this->commodel->get_listspfic1('Department', 'dept_code', 'dept_id',$detail->emp_dept_code)->dept_code;
                $dept_select_box.='<option value='.$detail->emp_dept_code.'>'.$deptname. '(' .$deptcode. ')'.' ';
                               
            }
        }
        echo json_encode($dept_select_box);
    } 
    /********************slect uo list according to selection type**********************/
    public function getspuolist(){
        $combid= $this->input->post('worktype');
	//$datawh ='';
	$whdata = '';
        $whdata = $this->getwhdata();
        $whdata['sp_tnt'] = $combid;
	$whorder = 'sp_uo asc';
        $comb_data = $this->sismodel->get_orderdistinctrecord('staff_position','sp_uo',$whdata,$whorder);
        $uo_select_box =' ';
        $uo_select_box.='<option value=null>--Select University Officer--';
	$usrname=$this->session->userdata('username');
        if(($usrname === 'vc@tanuvas.org.in')||($usrname === 'registrar@tanuvas.org.in')||($usrname === 'admin')||($usrname === 'asection@tanuvas.org.in')||($usrname === 'rsection@tanuvas.org.in')){
        	$uo_select_box.='<option value='.All.'>'.All. ' ';
        }
        //$uo_select_box.='<option value='.All.'>'.All. ' ';
        if(count($comb_data)>0){
            foreach($comb_data as $detail){
                $auoname=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id',$detail->sp_uo)->name;
                $auocode=$this->lgnmodel->get_listspfic1('authorities', 'code', 'id',$detail->sp_uo)->code;
              
               $uo_select_box.='<option value='.$detail->sp_uo.'>'.$auoname. '(' .$auocode. ')'.' ';
            }
        }
        echo json_encode($uo_select_box);
    } 
    
        
    /********************slect designation list according to selection type**********************/
    public function getuo_postlist(){
        $combid= $this->input->post('wtuoid');
        $parts = explode(',',$combid); 
        if($parts[1]!='All'){
            $datawh=array('sp_tnt' => $parts[0],'sp_uo' =>$parts[1]);
        }
        else{
            $datawh=array('sp_tnt' => $parts[0]);
        }

	$rlid=$this->session->userdata('id_role');
        if ($rlid == 5){
                $usrid=$this->session->userdata('id_user');
                $deptid = '';
                $whdatad = array('userid' => $usrid,'roleid' => $rlid);
                $resu = $this->sismodel->get_listspficemore('user_role_type','deptid',$whdatad);
                foreach($resu as $rw){
                        $deptid=$rw->deptid;
                }
                $datawh['sp_dept'] = $deptid;
        }

	$whorder = 'sp_emppost asc';
        $comb_data = $this->sismodel->get_orderdistinctrecord('staff_position','sp_emppost',$datawh,$whorder);
        $pt_select_box =' ';
        $pt_select_box.='<option value=null>--Select Post--';
	$usrname=$this->session->userdata('username');
        if(($usrname === 'vc@tanuvas.org.in')||($usrname === 'registrar@tanuvas.org.in')||($usrname ==='admin')||($usrname === 'asection@tanuvas.org.in')||($usrname === 'rsection@tanuvas.org.in')){
        	$pt_select_box.='<option value='.All.'>'.All. ' ';
        }
        //$pt_select_box.='<option value='.All.'>'.All. ' ';
        if(count($comb_data)>0){
            foreach($comb_data as $detail){
                
                $designame=$this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id',$detail->sp_emppost)->desig_name;
                $desigcode=$this->commodel->get_listspfic1('designation', 'desig_code', 'desig_id',$detail->sp_emppost)->desig_code;
                $pt_select_box.='<option value='.$detail->sp_emppost.'>'.$designame. '(' .$desigcode. ')'.' ';
               
            }
        }
        echo json_encode($pt_select_box);
    } 
    
     public function getuolist_sp(){
        $combid= $this->input->post('worktype');
	 $whdata = '';
        $whdata = $this->getwhdata();
        $whdata['sp_tnt'] = $combid;
	$whorder = 'sp_uo asc';
        $comb_data = $this->sismodel->get_orderdistinctrecord('staff_position','sp_uo',$whdata,$whorder);
        $uo_select_box =' ';
        $uo_select_box.='<option value=null>--Select University Officer--';
	$usrname=$this->session->userdata('username');
        if(($usrname === 'vc@tanuvas.org.in')||($usrname === 'registrar@tanuvas.org.in')||($usrname === 'admin')||($usrname === 'asection@tanuvas.org.in')||($usrname === 'rsection@tanuvas.org.in')){
        	$uo_select_box.='<option value='.All.'>'.All. ' ';
        }
        //$uo_select_box.='<option value='.All.'>'.All. ' ';
        if(count($comb_data)>0){
            foreach($comb_data as $detail){
                $auoname=$this->lgnmodel->get_listspfic1('authorities', 'name', 'id',$detail->sp_uo)->name;
                $auocode=$this->lgnmodel->get_listspfic1('authorities', 'code', 'id',$detail->sp_uo)->code;
              
               $uo_select_box.='<option value='.$detail->sp_uo.'>'.$auoname. '(' .$auocode. ')'.' ';
            }
        }
        echo json_encode($uo_select_box);
    } 
    /********************slect dept list according to selection type**********************/
    public function getdeptlist_sp(){
        $combid= $this->input->post('worktypeuo');
        $parts = explode(',',$combid); 
        if($parts[1]!="All"){
            $datawh=array('sp_tnt' => $parts[0],'sp_uo' => $parts[1]);
        }
        else{
            $datawh=array('sp_tnt' => $parts[0]);
        }
	$rlid=$this->session->userdata('id_role');
        if ($rlid == 5){
                $usrid=$this->session->userdata('id_user');
                $deptid = '';
                $whdatad = array('userid' => $usrid,'roleid' => $rlid);
                $resu = $this->sismodel->get_listspficemore('user_role_type','deptid',$whdatad);
                foreach($resu as $rw){
                        $deptid=$rw->deptid;
                }
                $datawh['sp_dept'] = $deptid;
        }

	$whorder = 'sp_dept asc ';
        $comb_data = $this->sismodel->get_orderdistinctrecord('staff_position','sp_dept',$datawh,$whorder);
        $dept_select_box =' ';
        $dept_select_box.='<option value=null>--Select Department--';
	$usrname=$this->session->userdata('username');
        if(($usrname === 'vc@tanuvas.org.in')||($usrname === 'registrar@tanuvas.org.in')||($usrname === 'admin')||($usrname === 'asection@tanuvas.org.in')||($usrname === 'rsection@tanuvas.org.in')){
        	$dept_select_box.='<option value='.All.'>'.All. ' ';
        }
        //$dept_select_box.='<option value='.All.'>'.All. ' ';
        if(count($comb_data)>0){
            foreach($comb_data as $detail){
                $deptname=$this->commodel->get_listspfic1('Department', 'dept_name', 'dept_id',$detail->sp_dept)->dept_name;
                $deptcode=$this->commodel->get_listspfic1('Department', 'dept_code', 'dept_id',$detail->sp_dept)->dept_code;
              
               $dept_select_box.='<option value='.$detail->sp_dept.'>'.$deptname. '(' .$deptcode. ')'.' ';
            }
        }
        echo json_encode($dept_select_box);
    } 
    
    /********************select post list according to selection type**********************/
    public function getpostlist_sp(){
        $combid= $this->input->post('worktype');
	 $whdata = '';
        $whdata = $this->getwhdata();
        $whdata['sp_tnt'] = $combid;
	$whorder = 'sp_emppost asc';
        $comb_data = $this->sismodel->get_orderdistinctrecord('staff_position','sp_emppost',$whdata,$whorder);
        $post_select_box =' ';
        $post_select_box.='<option value=null>-- Select Post --';
	$usrname=$this->session->userdata('username');
        if(($usrname === 'vc@tanuvas.org.in')||($usrname === 'registrar@tanuvas.org.in')||($usrname === 'admin')||($usrname === 'asection@tanuvas.org.in')||($usrname === 'rsection@tanuvas.org.in')){
        	$post_select_box.='<option value='.All.'>'.All. ' ';
        }
        //$post_select_box.='<option value='.All.'>'.All. ' ';
        if(count($comb_data)>0){
            foreach($comb_data as $detail){
                $postname=$this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id',$detail->sp_emppost)->desig_name;
                $postcode=$this->commodel->get_listspfic1('designation', 'desig_code', 'desig_id',$detail->sp_emppost)->desig_code;
              
               $post_select_box.='<option value='.$detail->sp_emppost.'>'.$postname. '(' .$postcode. ')'.' ';
            }
        }
        echo json_encode($post_select_box);
    } 
/********************select post list according to selection type**********************/
    public function getuodeptpostlist_sp(){
        $combid= $this->input->post('wtuodept');
        $parts = explode(',',$combid);

        $whdata = '';
        $whdata = $this->getwhdata();
        $whdata['sp_tnt']= $parts[0];

        if($parts[1]!="All"){
                $whdata['sp_uo'] = $parts[1];
        }
        if($parts[2] !="All"){
                $whdata['sp_dept']=$parts[2];
        }
        $whorder = 'sp_emppost asc';

        $comb_data = $this->sismodel->get_orderdistinctrecord('staff_position','sp_emppost',$whdata,$whorder);
        $post_select_box =' ';
        $post_select_box.='<option value=null>-- Select --';
        $usrname=$this->session->userdata('username');
        if(($usrname === 'vc@tanuvas.org.in')||($usrname === 'registrar@tanuvas.org.in')||($username === 'admin')||($usrname === 'asection@tanuvas.org.in')||($usrname === 'rsection@tanuvas.org.in')){
                $post_select_box.='<option value='.All.'>'.All. ' ';
        }
        //$post_select_box.='<option value='.All.'>'.All. ' ';
        if(count($comb_data)>0){
            foreach($comb_data as $detail){
                $postname=$this->commodel->get_listspfic1('designation', 'desig_name', 'desig_id',$detail->sp_emppost)->desig_name;
                $postcode=$this->commodel->get_listspfic1('designation', 'desig_code', 'desig_id',$detail->sp_emppost)->desig_code;

               $post_select_box.='<option value='.$detail->sp_emppost.'>'.$postname. '(' .$postcode. ')'.' ';
            }
        }
        echo json_encode($post_select_box);
    }
    
    
}
