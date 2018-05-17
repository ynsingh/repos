<?php
/* 
 * @name leavemgmt.php
 * @author Shobhit Tiwari(tshobhit206@gmail.com)
 * @author Ankur Singh(ankursingh206@gmail.com)
*/

defined('BASEPATH') OR exit('No direct script access allowed');


class Leavemgmt extends CI_Controller
{
    function __construct() {
        parent::__construct();
        $this->load->model('login_model','logmodel'); 
		  $this->load->model('common_model','commodel'); 
		  $this->load->model('User_model','usermodel'); 
        $this->load->model('SIS_model','sismodel');
        $this->load->model('dependrop_model','depmodel');
        $this->load->model('university_model','unimodel');
		  $this->load->helper('download');
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
		  redirect('welcome');
        }
    }


/****************************************** Leave Type ********************************************/

public function leavetype(){

        if(isset($_POST['leavetype'])) {
            $this->form_validation->set_rules('lt_name','Leave Name','trim|xss_clean|required|alpha_numeric_spaces|callback_isLeaveTypeExist');
            $this->form_validation->set_rules('lt_code','Leave Code','trim|xss_clean|required|alpha_numeric');
            $this->form_validation->set_rules('lt_short','Leave Short','trim|xss_clean|required|alpha_numeric');
            $this->form_validation->set_rules('lt_value','Leave Value','trim|xss_clean|required|numeric|is_natural');

            if($this->form_validation->run()==TRUE){

            $data = array(
                'lt_name'=>ucwords(strtolower($_POST['lt_name'])),
                'lt_code'=>strtoupper($_POST['lt_code']),
                'lt_short'=>strtoupper($_POST['lt_short']),
                'lt_value'=>$_POST['lt_value'],
					 'lt_remarks'=>$_POST['lt_remarks']

            );
           $ltflag=$this->sismodel->insertrec('leave_type_master', $data) ;
           if(!$ltflag)
           {
                $this->logger->write_logmessage("insert"," Error in adding  leavetype ", " Leave Type Master data insert error . "  );
                $this->logger->write_dblogmessage("insert"," Error in adding  leavetype ", "Leave Type Master data insert error . " );
                $this->session->set_flashdata('err_message','Error in adding leavetype - ' . $_POST['ltname'] , 'error');
                $this->load->view('leavemgmt/leavetype');
           }
          else{
                $this->logger->write_logmessage("insert"," add leavetypemaster ", "Leave added successfully..."  );
                $this->logger->write_dblogmessage("insert"," add leavetypemaster ", "Leave added successfully..." );
                $this->session->set_flashdata("success", "Leave [" .$_POST['lt_name']. "] added successfully...");
                redirect("leavemgmt/displayleavetype", "refresh");
              }
           }
        }
      $this->load->view('leavemgmt/leavetype');
}

/*Check for existing leave type*/

public function isLeaveTypeExist($lt_name) {

        $is_exist = $this->sismodel->isduplicate('leave_type_master','lt_name',$lt_name);
        if ($is_exist)
        {
            $this->form_validation->set_message('isLeaveTypeExist', 'Leave Type [' .$lt_name. '] already exist.');
            return false;
        }
        else {
            return true;
        }
}

/*Display Leave Type*/

public function displayleavetype(){
		
        $this->result = $this->sismodel->get_list('leave_type_master');
        $this->logger->write_logmessage("view"," View ", "Leave display successfully..." );
        $this->logger->write_dblogmessage("view"," View Leave Type Master", "Leave successfully..." );
        $this->load->view('leavemgmt/displayleavetype',$this->result);
}

/*Edit Leave Type*/

public function editleave($lt_id) {
        $lt_data_q=$this->sismodel->get_listrow('leave_type_master','lt_id', $lt_id);
         
        if ($lt_data_q->num_rows() < 1)
        {
           redirect('leavemgmt/editleave');
        }
        $LeaveType_data = $lt_data_q->row();
        /* Form fields */


        $data['lt_name'] = array(
            'name' => 'lt_name',
            'id' => 'lt_name',
            'maxlength' => '50',
            'size' => '40',
            'value' => $LeaveType_data->lt_name,
         
        );

         $data['lt_code'] = array(
            'name' => 'lt_code',
            'id' => 'lt_code',
            'maxlength' => '50',
            'size' => '40',
            'value' => $LeaveType_data->lt_code,
            'readonly' => 'readonly'
        );

         $data['lt_short'] = array(
            'name' => 'lt_short',
            'id' => 'lt_short',
            'maxlength' => '50',
            'size' => '40',
            'value' => $LeaveType_data->lt_short,
        );


        $data['lt_value'] = array(
           'name' => 'lt_value',
           'id' => 'lt_value',
           'maxlength' => '50',
           'size' => '40',
           'value' => $LeaveType_data->lt_value,
        );
			
			
			$data['lt_remarks'] = array(
           'name' => 'lt_remarks',
           'id' => 'lt_remarks',
           'maxlength' => '50',
           'size' => '40',
           'value' => $LeaveType_data->lt_remarks,
        );

    	 $data['lt_id'] = $lt_id;

        $this->form_validation->set_rules('lt_name','Leave Name','trim|xss_clean|required|alpha_numeric_spaces');
        $this->form_validation->set_rules('lt_code','Leave Code','trim|xss_clean|required|alpha_numeric');
        $this->form_validation->set_rules('lt_short','Leave Short','trim|xss_clean|required|alpha_numeric');
        $this->form_validation->set_rules('lt_value','Leave Value','trim|xss_clean|required|numeric|is_natural');


        if ($_POST)
        {
            $data['lt_name']['value'] = $this->input->post('lt_name', TRUE);
            $data['lt_code']['value'] = $this->input->post('lt_code', TRUE);
            $data['lt_short']['value'] = $this->input->post('lt_short', TRUE);            
	    		$data['lt_value']['value'] = $this->input->post('lt_value', TRUE);
            $data['lt_remarks']['value'] = $this->input->post('lt_remarks', TRUE);
        }
        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('leavemgmt/editleave', $data);
            return;
        }
        else
        {
            $lt_name = ucwords(strtolower($this->input->post('lt_name', TRUE)));
            $lt_code = strtoupper($this->input->post('lt_code', TRUE));
            $lt_short = strtoupper($this->input->post('lt_short', TRUE));
            $lt_value = $this->input->post('lt_value', TRUE);
            $lt_remarks = $this->input->post('lt_remarks', TRUE);
            $logmessage = "";

            if($LeaveType_data->lt_name != $lt_name)
                $logmessage = "Add Leave Type " .$LeaveType_data->lt_name. " changed by " .$lt_name;


            if($LeaveType_data->lt_code != $lt_code)
                $logmessage = "Add Leave Type " .$LeaveType_data->lt_code. " changed by " .$lt_code;

            if($LeaveType_data->lt_short != $lt_short)
                $logmessage = "Add Leave Type " .$LeaveType_data->lt_short. " changed by " .$lt_short; 


            if($LeaveType_data->lt_value != $lt_value)
                $logmessage = "Add Leave Type " .$LeaveType_data->lt_value. " changed by " .$lt_value;

				if($LeaveType_data->lt_remarks != $lt_remarks)
                $logmessage = "Add Leave Type " .$LeaveType_data->lt_remarks. " changed by " .$lt_remarks;

            $update_data = array(
               'lt_name' =>$lt_name,
               'lt_code' =>$lt_code,
               'lt_short' =>$lt_short,
               'lt_value' => $lt_value,
					'lt_remarks' => $lt_remarks,
            );

           $ltflag=$this->sismodel->updaterec('leave_type_master', $update_data, 'lt_id', $lt_id);
           if(!$ltflag)
            {
                $this->logger->write_logmessage("error","Error in updating Leave Type ", "Error in Leave record update. $logmessage . " );
                $this->logger->write_dblogmessage("error","Error in updating LeaveType ", "Error in Leave record update. $logmessage ." );
                $this->session->set_flashdata('err_message','Error in updating Leave Type - ' . $logmessage . '.', 'error');
                $this->load->view('leavemgmt/editleave', $data);
            }
            else{
                $this->logger->write_logmessage("update","Edit Leave Type", "Leave updated successfully... $logmessage . " );
                $this->logger->write_dblogmessage("update","Edit Leave Type", "Leave updated successfully... $logmessage ." );
                $this->session->set_flashdata('success','Leave [' .$lt_name.'] updated successfully...');
                redirect('leavemgmt/displayleavetype/');
                }
        }
        redirect('leavemgmt/editleave/');
    }

/*View Pending Leave Requests*/

public function pendingreq(){
	
	//   get the record whose status is pending
	$id=$this->session->userdata('id_user');
	$whdata = array('la_status'=>0);
	$record= $this->sismodel->get_listspficemore('leave_apply','la_id,la_userid,la_deptid,la_type,la_year,applied_la_from_date,applied_la_to_date,la_days,la_taken,la_desc',$whdata);
	$i=0;
	//   get one by one record
	foreach ($record as $row)
	{
	//	  get the leave type
		$ltype = $row->la_type;
	
	//   get master values on the basis of leave type
		$msval = $this->sismodel->get_listspfic1('leave_type_master','lt_value','lt_id',$ltype)->lt_value;
	//   get the academic year
		$cyear = date('Y');
	//	  get the sum of leave taken on the basis of year, user, leave type
		$whdata1 = array('la_status'=>1,'la_userid' => $row->la_userid,'la_type'=>$ltype, 'la_year'=>$cyear);
	//	$res= $this->sismodel->get_listspficemore('leave_apply','sum(la_taken) as latk',$whdata1);
		$res= $this->sismodel->get_listspficemore('leave_apply','sum(la_days) as ldays',$whdata1);
		foreach ($res as $row1)
        	{
			// $ltval = $row1->latk;
			$ltval = $row1->ldays;
			}
	//	  calculte the leave remaining = master value - taken value
			$lrmain = $msval - $ltval ;
	
	//	  add in array  and send to view
		$ldata['leaveid'] =  $row->la_id;
		$ldata['userid'] =  $row->la_userid;
		$ldata['deptid'] = $row->la_deptid; 	
		$ldata['ltype'] = $ltype; 	
		$ldata['acady'] = $cyear; 	
		$ldata['frmdate'] = $row->applied_la_from_date; 	
		$ldata['todate'] = $row->applied_la_to_date; 	
		$ldata['appday'] = $row->la_days; 	
		$ldata['lremain'] = $lrmain; 	
		$ldata['desc'] = $row->la_desc; 	
		$this->fldata[$i] = $ldata;
		$i++;
	
	}
	 	$this->load->view('leavemgmt/pendingreq');
}

/*View Approved Leave Requests*/

public function approvedreq(){
	
	//   get the record whose status is approved
	$id=$this->session->userdata('id_user');
	$whdata = array('la_status'=>1);
	$record= $this->sismodel->get_listspficemore('leave_apply','la_id,la_userid,la_deptid,la_type,la_year,granted_la_from_date,granted_la_to_date,applied_la_from_date,applied_la_to_date,la_days,la_taken,la_desc',$whdata);
	$i=0;
	//   get one by one record
	foreach ($record as $row)
	{
	//	  get the leave type
		$ltype = $row->la_type;
	
		//   get master values on the basis of leave type
		$msval = $this->sismodel->get_listspfic1('leave_type_master','lt_value','lt_id',$ltype)->lt_value;
		//   get the academic year
		$cyear = date('Y');
		//	  get the sum of leave taken on the basis of year, user, leave type
		$whdata1 = array('la_status'=>1,'la_userid' => $row->la_userid,'la_type'=>$ltype, 'la_year'=>$cyear);
		//	$res= $this->sismodel->get_listspficemore('leave_apply','sum(la_taken) as latk',$whdata1);
		$res= $this->sismodel->get_listspficemore('leave_apply','sum(la_days) as ldays',$whdata1);
		foreach ($res as $row1)
        	{
			//	$ltval = $row1->latk;			
				$ltval = $row1->ldays;			
			}
		//	  calculte the leave remaining = master value - taken value
			$lrmain = $msval - $ltval ;
	 
	//	  add in array  and send to view
		$ldata['leaveid'] =  $row->la_id;
		$ldata['userid'] =  $row->la_userid;
		$ldata['deptid'] = $row->la_deptid; 	
		$ldata['ltype'] = $ltype; 	
		$ldata['acady'] = $cyear; 	
	//	$ldata['frmdate'] = $row->granted_la_from_date; 	
		$ldata['frmdate'] = $row->applied_la_from_date; 	
		$ldata['todate'] = $row->applied_la_to_date; 	
	//	$ldata['todate'] = $row->granted_la_to_date; 	
	//	$ldata['appday'] = $row->la_taken; 	
		$ldata['appday'] = $row->la_days; 	
		$ldata['lremain'] = $lrmain; 	
		$ldata['desc'] = $row->la_desc; 	
		$this->fldata[$i] = $ldata;
		$i++;
	}
	 	$this->load->view('leavemgmt/approvedreq');
}

/*View Rejected Leave Requests*/

public function rejectedreq(){
	
	//   get the record whose status is rejected
	$id=$this->session->userdata('id_user');
	$whdata = array('la_status'=>2);
	$record= $this->sismodel->get_listspficemore('leave_apply','la_id,la_userid,la_deptid,la_type,la_year,applied_la_from_date,applied_la_to_date,la_days,la_taken,la_desc',$whdata);
	$i=0;
	//   get one by one record
	foreach ($record as $row)
	{
	//	  get the leave type
		$ltype = $row->la_type;
	
		//   get master values on the basis of leave type
		$msval = $this->sismodel->get_listspfic1('leave_type_master','lt_value','lt_id',$ltype)->lt_value;
		//   get the academic year
		$cyear = date('Y');
		//	  get the sum of leave taken on the basis of year, user, leave type
		$whdata1 = array('la_status'=>1,'la_userid' => $row->la_userid,'la_type'=>$ltype, 'la_year'=>$cyear);
		//	$res= $this->sismodel->get_listspficemore('leave_apply','sum(la_taken) as latk',$whdata1);
		$res= $this->sismodel->get_listspficemore('leave_apply','sum(la_days) as ldays',$whdata1);
		foreach ($res as $row1)
        	{
		//		$ltval = $row1->latk;
			$ltval = $row1->ldays;
			}
		//	  calculte the leave remaining = master value - taken value
			$lrmain = $msval - $ltval ;
	
	//	  add in array  and send to view
		$ldata['leaveid'] =  $row->la_id;
		$ldata['userid'] =  $row->la_userid;
		$ldata['deptid'] = $row->la_deptid; 	
		$ldata['ltype'] = $ltype; 	
		$ldata['acady'] = $cyear; 	
		$ldata['frmdate'] = $row->applied_la_from_date; 	
		$ldata['todate'] = $row->applied_la_to_date; 	
		$ldata['appday'] = $row->la_days; 	
		$ldata['lremain'] = $lrmain; 	
		$ldata['desc'] = $row->la_desc; 	
		$this->fldata[$i] = $ldata;
		$i++;
	}
	 	$this->load->view('leavemgmt/rejectedreq');
}

/*Approve Leave Request*/
/*
public function approve($la_id) {

	$laid=$this->sismodel->get_listspfic1('leave_apply','la_type','la_id',$la_id)->la_type;
	$lauserid=$this->sismodel->get_listspfic1('leave_apply','la_userid','la_id',$la_id)->la_userid;
	//   get master values on the basis of leave type
		$msval = $this->sismodel->get_listspfic1('leave_type_master','lt_value','lt_id',$laid)->lt_value;
	//   get the academic year
		$cyear = date('Y');
	//	  get the sum of leave taken on the basis of year, user, leave type
		$whdata1 = array('la_status'=>1,'la_userid' => $lauserid,'la_type'=>$laid, 'la_year'=>$cyear);
		$res= $this->sismodel->get_listspficemore('leave_apply','sum(la_taken) as latk',$whdata1);
		foreach ($res as $row1)
        	{
			$ltval = $row1->latk;
			}
	//	  calculte the leave remaining = master value - taken value
			$this->lrmain = $msval - $ltval ;

        $leave=$this->sismodel->get_listrow('leave_apply','la_id', $la_id);
        if ($leave->num_rows() < 1)
        {
           redirect('leavemgmt/pendingreq');
        }
        $Leavedata = $leave->row();
        $laname=$this->sismodel->get_listspfic1('leave_type_master','lt_name','lt_id',$Leavedata->la_type)->lt_name;
        
		  $data['la_type'] = array(
            'name' => 'la_type',
            'id' => 'la_type',
            'maxlength' => '50',
            'size' => '40',
            'value' => $laname,
           'readonly' => 'readonly'
        );

         $data['la_desc'] = array(
            'name' => 'la_desc',
            'id' => 'la_desc',
            'maxlength' => '50',
            'size' => '40',
            'value' => $Leavedata->la_desc,
            'readonly' => 'readonly'
        );

        $data['applied_la_from_date'] = array(
            'name' => 'applied_la_from_date',
            'id' => 'applied_la_from_date',
            'maxlength' => '50',
            'size' => '40',
            'value' => $Leavedata->applied_la_from_date,
        );

        $data['applied_la_to_date'] = array(
           'name' => 'applied_la_to_date',
           'id' => 'applied_la_to_date',
           'maxlength' => '50',
           'size' => '40',
           'value' => $Leavedata->applied_la_to_date,

        );

		  
		  $data['la_status'] = array(
            'name' => 'la_status',
            'id' => 'la_status',
            'value' => $Leavedata->la_status,
        );

    	  $data['la_id'] = $la_id;

		  $this->form_validation->set_rules('granted_la_from_date','Leave From Date','required');
		  $this->form_validation->set_rules('granted_la_to_date','Leave To Date','required');
        
        if ($_POST)
        {
		      $data['granted_la_from_date']['value'] = $this->input->post('granted_la_from_date', TRUE);            
	    		$data['granted_la_to_date']['value'] = $this->input->post('granted_la_to_date', TRUE);
				$data['la_status']['value'] = $this->input->post('la_status', TRUE);
        }

        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('leavemgmt/approve', $data);
            return;
        }
        else
        {
		      $granted_la_from_date = $this->input->post('granted_la_from_date', TRUE);
            $granted_la_to_date = $this->input->post('granted_la_to_date', TRUE);
				$la_status = $this->input->post('la_status', TRUE);
				$frmdt=new DateTime($granted_la_from_date);
				$todt=new DateTime($granted_la_to_date);
					$laf1=explode(" ",$granted_la_from_date);
               $lat1=explode(" ",$granted_la_to_date);
					if(($laf1[0]==$lat1[0])&&($laf1[1]<12)&&($lat1[1]<12))
					{
					$ldays=0.5;
					$ltaken=$ldays;
					}
					elseif(($laf1[0]==$lat1[0])&&($laf1[1]>=12)&&($lat1[1]>12))
					{
					$ldays=0.5;
					$ltaken=$ldays;
					}
					else				
					{
					$ldays=$frmdt->diff($todt);
					$ltaken=$ldays->format('%R%a days')+1; 
					}

			   $logmessage = "";
				if(($ltaken<=$this->lrmain)&&($this->lrmain>0))
				{
				
            if($Leavedata->granted_la_from_date != $granted_la_from_date)
                $logmessage = "Leave Granted From Date " .$Leavedata->granted_la_from_date. " changed by " .$granted_la_from_date; 

            if($Leavedata->granted_la_to_date != $granted_la_to_date)
                $logmessage = "Leave Granted To Date " .$Leavedata->granted_la_to_date. " changed by " .$granted_la_to_date;
			
				if($Leavedata->la_status != $la_status)
                $logmessage = "Leave Status " .$Leavedata->la_status. " changed by " .$la_status;

            $update_data = array(              
               'granted_la_from_date' =>$granted_la_from_date,
               'granted_la_to_date' =>$granted_la_to_date,
					'la_status' =>  1,
					'la_taken' => $ltaken
            );

           $ltflag=$this->sismodel->updaterec('leave_apply', $update_data, 'la_id', $la_id);
			  }
			  else
			  {
				$leaverej=$this->sismodel->get_listrow('leave_apply','la_id', $la_id);
				$leavedata = $leaverej->row();
		  		$logmessage = "";
    	  		$logmessage = "Leave Status " .$leavedata->la_status. " changed To Rejected" ;
       		$update_data = array('la_status' =>  2);
        		$ltflag=$this->sismodel->updaterec('leave_apply', $update_data, 'la_id', $la_id);
				$this->session->set_flashdata('err_message','Leave not possible');
        		redirect('leavemgmt/pendingreq/');
			  }
           if(!$ltflag)
            {
                $this->logger->write_logmessage("error","Error in updating Leave Type ", "Error in Leave update. $logmessage . " );
                $this->logger->write_dblogmessage("error","Error in updating LeaveType ", "Error in Leave update. $logmessage ." );
                $this->session->set_flashdata('err_message','Error in updating Leave Type - ' . $logmessage . '.', 'error');
                $this->load->view('leavemgmt/approve', $data);
            }
            else{
                $this->logger->write_logmessage("update","Leave Approved", "Leave approved... $logmessage . " );
                $this->logger->write_dblogmessage("update","Leave Approved", "Leave approved... $logmessage ." );
                $this->session->set_flashdata('success','Leave approved');
				    redirect('leavemgmt/pendingreq/');
                }
        }
        redirect('leavemgmt/approve/');
}
*/


/*Approve Leave Request*/

public function approve($la_id){
	
		$laid=$this->sismodel->get_listspfic1('leave_apply','la_type','la_id',$la_id)->la_type;
		$lauserid=$this->sismodel->get_listspfic1('leave_apply','la_userid','la_id',$la_id)->la_userid;		
		//   get master values on the basis of leave type
		$msval = $this->sismodel->get_listspfic1('leave_type_master','lt_value','lt_id',$laid)->lt_value;
		//   get the academic year
		$cyear = date('Y');
		//	  get the sum of leave taken on the basis of year, user, leave type
		$whdata1 = array('la_status'=>1,'la_userid' => $lauserid,'la_type'=>$laid, 'la_year'=>$cyear);

		//	$res= $this->sismodel->get_listspficemore('leave_apply','sum(la_taken) as latk',$whdata1);
		$res= $this->sismodel->get_listspficemore('leave_apply','sum(la_days) as ldays',$whdata1);
		$res1= $this->sismodel->get_listspfic1('leave_apply','la_days','la_id',$la_id)->la_days;	
		foreach ($res as $row1)
        	{
		//		$ltval = $row1->latk;
			$ltval = $row1->ldays;
			}
		//	  calculte the leave remaining = master value - taken value
			$this->lrmain = $msval - $ltval ;
		
			$lapprove=$this->sismodel->get_listrow('leave_apply','la_id', $la_id);
			if ($lapprove->num_rows() < 1)
        {
           redirect('leavemgmt/pendingreq');
        }
		  if($res1<=$this->lrmain)
		  {
   	  $leavedata = $lapprove->row();
		  $logmessage = "";
    	  $logmessage = "Add Leave Type " .$leavedata->la_status. " changed by Approved" ;
        $update_data = array('la_status' =>  1);
		  $ltflag=$this->sismodel->updaterec('leave_apply', $update_data, 'la_id', $la_id);
		  
        if(!$ltflag)
        {
        $this->logger->write_logmessage("error","Error in Approving Leave ", "Error in Approving Leave . $logmessage . " );
        $this->logger->write_dblogmessage("error","Error in Approving Leave ", "Error in Approving Leave . $logmessage ." );
        $this->session->set_flashdata('err_message','Error in Approving Leave  - ' . $logmessage . '.', 'error');
        $this->load->view('leavemgmt/pendingreq', $data);
        }
        else
		  {
        $this->logger->write_logmessage("update","Leave Approved", "Leave approved... $logmessage . " );
        $this->logger->write_dblogmessage("update","Leave Approved", "Leave approved... $logmessage ." );
        $this->session->set_flashdata('success','Leave approved');
        redirect('leavemgmt/approvedreq/');
        }
		  }
		  else
		  {
			$this->session->set_flashdata('err_message','Leave can not be approved because Days Remaining is less than Days Requested');
         redirect('leavemgmt/pendingreq/');        
		  }
   redirect('leavemgmt/approve/');
}

/*Reject Leave Request*/

public function reject($la_id) {

	
        $leave=$this->sismodel->get_listrow('leave_apply','la_id', $la_id);
        if ($leave->num_rows() < 1)
        {
           redirect('leavemgmt/pendingreq');
        }
        $Leavedata = $leave->row();
               
		  $data['la_rejres'] = array(
            'name' => 'la_rejres',
            'id' => 'la_rejres',
            'maxlength' => '50',
            'size' => '40',
            'value' => $Leavedata->la_rejres,
        );
        $data['la_status'] = array(
            'name' => 'la_status',
            'id' => 'la_status',
            'value' => $Leavedata->la_status,
        );
		  $la_rejres = $this->input->post('la_rejres', TRUE);
    	  $data['la_id'] = $la_id;

		  $this->form_validation->set_rules('la_rejres','Reason for Rejecting Leave  ','required');
		          
        if ($_POST)
        {
		      $data['la_rejres']['value'] = $this->input->post('la_rejres', TRUE);
				$data['la_status']['value'] = $this->input->post('la_status', TRUE);
        }

        if ($this->form_validation->run() == FALSE)
        {
            $this->load->view('leavemgmt/reject', $data);
            return;
        }
              
			   $logmessage = "";
				
            if($Leavedata->la_rejres != $la_rejres)
                $logmessage = "Reason for Rejecting leave " .$Leavedata->la_rejres. " changed by " .$la_rejres;
			
				
            $update_data = array(              
               'la_status' =>  2,
					'la_rejres' => $la_rejres
            );

           $ltflag=$this->sismodel->updaterec('leave_apply', $update_data, 'la_id', $la_id);
					  
			  if(!$ltflag)
            {
                $this->logger->write_logmessage("error","Error in updating Leave Type ", "Error in Leave update. $logmessage . " );
                $this->logger->write_dblogmessage("error","Error in updating LeaveType ", "Error in Leave update. $logmessage ." );
                $this->session->set_flashdata('err_message','Error in updating Leave Type - ' . $logmessage . '.', 'error');
                $this->load->view('leavemgmt/reject', $data);
            }
            else{
                $this->logger->write_logmessage("update","Leave Rejected", "Leave Rejected... $logmessage . " );
                $this->logger->write_dblogmessage("update","Leave Rejected", "Leave Rejected... $logmessage ." );
                $this->session->set_flashdata('err_message','Leave Rejected');
				    redirect('leavemgmt/rejectedreq/');
                }
        
        redirect('leavemgmt/reject');
}

/*Staff Leave Details*/

public function staffleavedetails() {

	$id=$this->session->userdata('id_user');
	$i=0;
	$record=$this->sismodel->get_list('leave_type_master');

	//   get one by one record
	foreach ($record as $row)
	{
	//	  get the leave type
		$ltype = $row->lt_id;
	//   get master values on the basis of leave type
		$msval = $row->lt_value;
	//   get the academic year
		$cyear = date('Y');
	//	  get the sum of leave taken on the basis of year, user, leave type
		$whdata1 = array('la_status'=>1,'la_userid' => $id,'la_type'=>$ltype, 'la_year'=>$cyear);
	//	$ires= $this->sismodel->get_listspficemore('leave_apply','sum(la_taken) as latk',$whdata1);
		$ires= $this->sismodel->get_listspficemore('leave_apply','sum(la_days) as ldays',$whdata1);
		if(!empty($ires))
      
		{
		foreach ($ires as $row1)
        	{
			// $ltval = $row1->latk;
			$ltval = $row1->ldays;
			}
		}
		if($ltval==0) 
		{
		$ltval = "--";
		//	  calculte the leave remaining = master value - taken value
		$lrmain = $msval - $ltval ;
		//  send data to view	
		$ldata['msval'] = $row->lt_value;
		$ldata['ltname'] = $row->lt_name;
		$ldata['lremarks'] = $row->lt_remarks;
		$ldata['lttaken'] = $ltval;
		$ldata['ltremain'] = $lrmain;
		$this->fldata[$i] = $ldata;
		$i++;
		}
		else{
		//	  calculte the leave remaining = master value - taken value
		$lrmain = $msval - $ltval ;
		//  send data to view	
		$ldata['msval'] = $row->lt_value;
		$ldata['ltname'] =  $row->lt_name;
		$ldata['lremarks'] = $row->lt_remarks;
		$ldata['lttaken'] = $ltval;
		$ldata['ltremain'] = $lrmain;
		$this->fldata[$i] = $ldata;
		$i++;
		}}
	 	$this->load->view('leavemgmt/staffleavedetails');
}

/*Apply for leave*/

public function leaveapply()
{
		 $id=$this->session->userdata('id_user');

		 $this->leaveresult=$this->sismodel->get_listspfic2('leave_type_master','lt_id', 'lt_name');
       		 if(isset($_POST['leaveapply']))
				 {
                      $this->form_validation->set_rules('la_type','Leave Type','trim|xss_clean|required');
                      $this->form_validation->set_rules('la_desc','Leave Purpose','trim|xss_clean|required'); 
				 			 $this->form_validation->set_rules('applied_la_from_date','From Date','trim|xss_clean|required');
                      $this->form_validation->set_rules('applied_la_to_date','To Date','trim|xss_clean|required');
             }
 				 //if form validation true
              	if($this->form_validation->run()==TRUE)
					{
					// get the dept id of this user
					$deptid=$this->commodel->get_listspfic1('Department','dept_id','dept_id')->dept_id;
					$lat=$_POST['applied_la_to_date'];
					$laf=$_POST['applied_la_from_date'];
					$frmdt=new DateTime($laf);
					$todt=new DateTime($lat);
               $lat1=explode(" ",$lat);
					$laf1=explode(" ",$laf);
					if($lat==$laf)
					{
					$this->session->set_flashdata("err_message", "Leave From Date and To Date can not be same...");
               redirect("leavemgmt/leaveapply");
					}
	
					if(($laf1[0]==$lat1[0])&&($laf1[1]<12)&&($lat1[1]<12))
					{
					$lday=0.5;
					$data = array(
					'la_type'=>$_POST['la_type'],
					'la_userid' => $this->session->userdata('id_user'),
					'la_deptid' => $deptid,
					'la_year' => date('Y'),
					'la_days'=> $lday,
					'la_desc'=>$_POST['la_desc'],
			      'applied_la_from_date'=>$_POST['applied_la_from_date'],
               'applied_la_to_date'=>$_POST['applied_la_to_date'],
					'granted_la_from_date'=>0,
					'granted_la_to_date'=>0
               );					
					}
					elseif(($laf1[0]==$lat1[0])&&($laf1[1]>=12)&&($lat1[1]>12))
					{
					$lday=0.5;
					$data = array(
					'la_type'=>$_POST['la_type'],
					'la_userid' => $this->session->userdata('id_user'),
					'la_deptid' => $deptid,
					'la_year' => date('Y'),
					'la_days'=> $lday,
					'la_desc'=>$_POST['la_desc'],
			      'applied_la_from_date'=>$_POST['applied_la_from_date'],
               'applied_la_to_date'=>$_POST['applied_la_to_date'],
					'granted_la_from_date'=>0,
					'granted_la_to_date'=>0
               );
					
					}
					else
					{
					$lauserid=$this->sismodel->get_listspfic1('leave_apply','la_userid','la_id',$id)->la_userid;
					// get master values on the basis of leave type
					$msval = $this->sismodel->get_listspfic1('leave_type_master','lt_value','lt_id',$_POST['la_type'])->lt_value;
					// get the academic year
					$cyear = date('Y');
					//	get the sum of leave taken on the basis of year, user, leave type
					$whdata1 = array('la_status'=>1,'la_userid' => $lauserid,'la_type'=>$_POST['la_type'], 'la_year'=>$cyear);
					// $res= $this->sismodel->get_listspficemore('leave_apply','sum(la_taken) as latk',$whdata1);
					$res= $this->sismodel->get_listspficemore('leave_apply','sum(la_days) as ldays',$whdata1);
						foreach ($res as $row1)
			        	{
						//$ltval = $row1->latk;
						$ltval = $row1->ldays;
						}
						//	calculte the leave remaining = master value - taken value
						$this->lrmain = $msval - $ltval;
						$ldays=$frmdt->diff($todt);
               	$ldays=$ldays->format('%R%a days')+1;
												
						if($ldays<=($this->lrmain))
						{			
						$data = array(
						'la_type'=>$_POST['la_type'],
						'la_userid' => $this->session->userdata('id_user'),
						'la_deptid' => $deptid,
						'la_year' => date('Y'),
						'la_days'=> $ldays,
						'la_desc'=>$_POST['la_desc'],
			   	   'applied_la_from_date'=>$_POST['applied_la_from_date'],
            	   'applied_la_to_date'=>$_POST['applied_la_to_date'],
						'granted_la_from_date'=>0,
						'granted_la_to_date'=>0
             		 );
						 
						 }	
						 else
						 {
		 				 $this->session->set_flashdata("err_message", "Leave can not be applied because " .$msval. " leave count is available only ...");
         	       redirect("leavemgmt/leaveapply");
					 	 }
					 }
		
					 $leaveapply=$this->sismodel->insertrec('leave_apply', $data);
					 if(!$leaveapply)
					 {
                  $this->logger->write_logmessage("insert"," Error in Applying Leave", "Error in Applying Leave " );
                  $this->logger->write_dblogmessage("insert","Error in Applying Leave", " Error in Applying Leave " );
                  $this->session->set_flashdata('err_message','Error in adding From Date ' . $_POST['lt_from_date'] , 'error');
                  redirect('leavemgmt/leaveapply');
                } 
					 elseif($lday==0.5)
					 {
					  $this->logger->write_logmessage("insert"," Half Day Leave applied successfully ", " Leave applied successfully..."  );
              	  $this->logger->write_dblogmessage("insert"," Half Day Leave applied successfully ", "Leave applied successfully..." );
              	  $this->session->set_flashdata("success", "Half Day Leave applied successfully... ");
              	  redirect("leavemgmt/leavestatus");
					 }
					 else
					 {
					  $this->logger->write_logmessage("insert"," Leave applied successfully ", " Leave applied successfully..."  );
              	  $this->logger->write_dblogmessage("insert"," Leave applied successfully ", "Leave applied successfully..." );
              	  $this->session->set_flashdata("success", "Leave applied successfully... ");
              	  redirect("leavemgmt/leavestatus");
					 }
					 }
	$this->load->view('leavemgmt/leaveapply');
   return;
}

/*View leave Status*/

public function leavestatus() {
		   $id=$this->session->userdata('id_user');
		   $whdata=array('la_userid'=>$id);
		   $this->rec = $this->sismodel->get_listspficemore('leave_apply','la_type,la_desc,applied_la_from_date,applied_la_to_date,la_days,la_rejres,la_status',$whdata);
		   $i=0;
				//   get one by one record
				foreach ($this->rec as $row)
				{
				//  send data to view	
				$ldata['lname'] = $row->la_type;
				$ldata['ldesc'] = $row->la_desc;
				$ldata['lfdate'] = $row->applied_la_from_date;
				$ldata['ltdate'] = $row->applied_la_to_date;
				$ldata['ldays'] = $row->la_days;
				$ldata['lrejres'] = $row->la_rejres;
				$ldata['lstatus'] = $row->la_status;
				$this->fldata[$i] = $ldata;
				$i++;
				}
				$this->load->view('leavemgmt/leavestatus');
        		return;
}

/* earned leave*/

public function earnedleave() { 
		  
        $letype = "Earned Leave";
		  $earn=15;
		  
		  //  get the current year
		  $cyear = date('Y');
		  //  get the current month
		  $cmonth = date('m');
		  //  get the current day
		  $cday = date('d');
		  //if(($cmonth==12)&&($cday==31))
		  //{
			//get the list of users			
			$rest=$this->sismodel->get_orderlistspficemore('employee_master','emp_email','','');
			foreach($rest as $rw)
			{
				$email = $rw->emp_email;
				$eid = $this->logmodel->get_listspfic1('edrpuser','id','username',$email)->id;
				//check the entry exist for this user
		  		$whdata = array('le_userid'=>$eid,'le_type'=>$letype,'le_year'=>$cyear);
				$is_exist = $this->sismodel->isduplicatemore('leave_earned',$whdata);
			if(!($is_exist))
			{
				// check for earned leave  used this year or not if yes then calculate remaining  
					
					$lid=$this->sismodel->get_listspfic1('leave_type_master','lt_id','lt_name',$letype)->lt_id;
					// get master values on the basis of leave type

					$msval=$this->sismodel->get_listspfic1('leave_type_master','lt_value','lt_id',$lid)->lt_value;
					// get the academic year
					$cyear = date('Y');
					//	get the sum of leave taken on the basis of year, user, leave type
					$whdata1 = array('la_status'=>1,'la_userid' => $eid,'la_type'=>$lid, 'la_year'=>$cyear);
					// $res= $this->sismodel->get_listspficemore('leave_apply','sum(la_taken) as latk',$whdata1);
					$res= $this->sismodel->get_listspficemore('leave_apply','sum(la_days) as ldays',$whdata1);
						foreach ($res as $row1)
			        	{
						//$ltval = $row1->latk;
						$ltval = $row1->ldays;
						}
						//	calculte the leave remaining = master value - taken value
					$lrmain = $msval - $ltval;
						
					//	create data array for this user
					$data = array(
						'le_type'=>$letype,
						'le_userid' => $eid ,						
						'le_year' => $cyear,
						'le_earned'=> $lrmain
             		 		);
		  			$leaveearned=$this->sismodel->insertrec('leave_earned', $data);
					$this->lrmain=$lrmain;
		  		}
		   	
	}		  
		//}
		$this->result = $this->sismodel->get_list('leave_earned');
		$this->load->view('leavemgmt/earnedleave');
        	return;
	}

/*View earned leave details single staff member*/

public function viewels()
{
		$id=$this->session->userdata('id_user');
		$whdata=array('le_userid'=>$id);
		$this->rec = $this->sismodel->get_listspficemore('leave_earned','le_userid,le_type,le_year,le_earned',$whdata);
		$i=0;
		//   get one by one record
		foreach ($this->rec as $row)
		{	
			$lvid = $this->sismodel->get_listspfic1('leave_type_master','lt_id','lt_name',$row->le_type)->lt_id;
			// get master values on the basis of leave type			
			$msval = $this->sismodel->get_listspfic1('leave_type_master','lt_value','lt_id',$lvid)->lt_value;
			$whdata1 = array('la_status'=>1,'la_userid' => $id,'la_type'=>$lvid, 'la_year'=>($row->le_year));
			$res= $this->sismodel->get_listspficemore('leave_apply','sum(la_days) as ldays',$whdata1);
			foreach ($res as $row1)
			{
					//$ltval = $row1->latk;
					$ltval = $row1->ldays;
			}
			//	calculte the leave remaining = master value - taken value
			$this->lrmain = $msval - $ltval;
			// send data to view	
			if($ltval=="")
			{
			$ltval="--";
			$ldata['ltyear'] =  $row->le_year;
			$ldata['lttaken'] = $ltval;
			$ldata['ltremain'] =  $this->lrmain;
			$this->fldata[$i] = $ldata;
			$i++;		
			}
			else
			{
			$ldata['ltyear'] =  $row->le_year;
			$ldata['lttaken'] = $ltval;
			$ldata['ltremain'] =  $this->lrmain;
			$this->fldata[$i] = $ldata;
			$i++;	
			}
		}					
	$this->load->view('leavemgmt/viewels');
}

/* View earned leave details admin */

public function viewela()
{
			
			$lrm="";
			$i=0;
			//get the current year
			//$cyear = date('Y');
			//get the list of users			
		  	$rest=$this->sismodel->get_orderlistspficemore('employee_master','emp_email','','');
			foreach($rest as $rw)
			{
				$m="";
				$email = $rw->emp_email;
				$eid = $this->logmodel->get_listspfic1('edrpuser','id','username',$email)->id;
				$whdata = array('le_userid'=>$eid);
				$lr = $this->sismodel->get_listspficemore('leave_earned','le_earned',$whdata);
				 foreach($lr as $row)
					{
						$lrm=$row->le_earned;
						$m=$lrm+$m;
					}
					$fname=$this->logmodel->get_listspfic1('userprofile','firstname','userid',$eid)->firstname;
					$lname=$this->logmodel->get_listspfic1('userprofile','lastname','userid',$eid)->lastname;
					if($lrm=="")
					{
					$lrm="--";
					$ldata['fname'] =  $fname ;
					$ldata['lname'] =  $lname ;
               $ldata['userid'] =  $email ;
               $ldata['ltremain'] = $m; 
               $this->fldata[$i] = $ldata;
               $i++;
					}
					else
					{
					$ldata['fname'] =  $fname ;
					$ldata['lname'] =  $lname ;
					$ldata['userid'] =  $email ;
               $ldata['ltremain'] = $m; 
               $this->fldata[$i] = $ldata;
               $i++;
					}
            
		}
		$this->load->view('leavemgmt/viewela');
}	

/*View earned leave details single staff member from admin*/

public function viewell()
{
	$adt="";
	$emp_id = $this->uri->segment(3);
   $eid = $this->logmodel->get_listspfic1('edrpuser','id','username',$emp_id)->id;
	$whdata=array('le_userid'=>$eid);
		$this->rec = $this->sismodel->get_listspficemore('leave_earned','le_userid,le_type,le_year,le_earned',$whdata);
		$i=0;
		//   get one by one record
		foreach ($this->rec as $row)
		{	
			$lvid = $this->sismodel->get_listspfic1('leave_type_master','lt_id','lt_name',$row->le_type)->lt_id;
			$msval = $this->sismodel->get_listspfic1('leave_type_master','lt_value','lt_id',$lvid)->lt_value;
			$whdata1 = array('la_status'=>1,'la_userid' => $eid,'la_type'=>$lvid, 'la_year'=>($row->le_year));
			$res= $this->sismodel->get_listspficemore('leave_apply','sum(la_days) as ldays',$whdata1);
			foreach ($res as $row1)
			{
					//$ltval = $row1->latk;
					$ltval = $row1->ldays;
			}
			//	calculte the leave remaining = master value - taken value
			$this->lrmain = $msval - $ltval;
			// send data to view	
			if($ltval=="")
			{
			$ltval="--";
			$ldata['ltyear'] =  $row->le_year;
			$ldata['lttaken'] = $ltval;
			$ldata['ltremain'] =  $this->lrmain;
			$this->fldata[$i] = $ldata;
			$i++;		
			}
			else
			{
			$ldata['ltyear'] =  $row->le_year;
			$ldata['lttaken'] = $ltval;
			$ldata['ltremain'] =  $this->lrmain;
			$this->fldata[$i] = $ldata;
			$i++;	
			}			
		}		
$this->load->view('leavemgmt/viewell');
}	

/* This function has been created for getting leave remaining */

public function myfunc(){
		$lvid = $this->input->post('leavet');
		$id=$this->session->userdata('id_user');
		$lausid=$this->sismodel->get_listspfic1('leave_apply','la_userid','la_userid',$id);
		if(!empty($lausid)){
			$lauserid=$lausid->la_userid;			
		}
		else{
			$lauserid="";			
		}
		if($lvid!=6)
		{
					if(!empty($lauserid))
					{
					// get master values on the basis of leave type
					$msval = $this->sismodel->get_listspfic1('leave_type_master','lt_value','lt_id',$lvid)->lt_value;
					// get the academic year
					$cyear = date('Y');
					//	get the sum of leave taken on the basis of year, user, leave type
					$whdata1 = array('la_status'=>1,'la_userid' => $lauserid,'la_type'=>$lvid, 'la_year'=>$cyear);
					// $res= $this->sismodel->get_listspficemore('leave_apply','sum(la_taken) as latk',$whdata1);
					$res= $this->sismodel->get_listspficemore('leave_apply','sum(la_days) as ldays',$whdata1);
						foreach ($res as $row1)
	       			{
						//$ltval = $row1->latk;
						$ltval = $row1->ldays;
						}
					//	calculte the leave remaining = master value - taken value
					$this->lrmain = $msval - $ltval;
					echo json_encode($this->lrmain);
					}
					else
					{
					// get master values on the basis of leave type
					$msval = $this->sismodel->get_listspfic1('leave_type_master','lt_value','lt_id',$lvid)->lt_value;
					echo json_encode($msval);
					}
		}
		else
		{
					if(!empty($lauserid))
					{
					$lname= $this->sismodel->get_listspfic1('leave_type_master','lt_name','lt_id',$lvid)->lt_name;
					// get master values on the basis of leave type
					$msval = $this->sismodel->get_listspfic1('leave_earned','le_earned','le_type',$lname)->le_earned;
					// get the academic year
					$cyear = date('Y');
					//	get the sum of leave taken on the basis of year, user, leave type
					$whdata1 = array('la_status'=>1,'la_userid' => $lauserid,'la_type'=>$lvid, 'la_year'=>$cyear);
					// $res= $this->sismodel->get_listspficemore('leave_apply','sum(la_taken) as latk',$whdata1);
					$res= $this->sismodel->get_listspficemore('leave_apply','sum(la_days) as ldays',$whdata1);
						foreach ($res as $row1)
	       			{
						//$ltval = $row1->latk;
						$ltval = $row1->ldays;
						}
					//	calculte the leave remaining = master value - taken value
					$this->lrmain = $msval - $ltval;
					echo json_encode($this->lrmain);
					}
					else
					{
					$lname= $this->sismodel->get_listspfic1('leave_type_master','lt_name','lt_id',$lvid)->lt_name;
					// get master values on the basis of leave type
					$msval = $this->sismodel->get_listspfic1('leave_earned','le_earned','le_type',$lname)->le_earned;
					echo json_encode($msval);
					}
		}
	}
	/*****************************************end**********************************************/

}
