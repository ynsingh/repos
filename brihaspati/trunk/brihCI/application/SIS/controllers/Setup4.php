<?php

/* 
 * @name Setup4.php
 * @author Nagendra Kumar Singh (nksinghiitk@gmail.com) 
 * @author Deepika Chaudhary (chaudharydeepika88@gmail.com) Saving Master
 */
 
defined('BASEPATH') OR exit('No direct script access allowed');


class Setup4 extends CI_Controller
{
    function __construct() {
        parent::__construct();
	$this->load->model('common_model','commodel'); 
	$this->load->model('login_model','logmodel');
	$this->load->model('SIS_model',"sismodel");
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
		redirect('welcome');
        }
    }
//add saving master
public function savingmaster(){
	if(isset($_POST['savingmaster'])) {
	$this->form_validation->set_rules('sm_fyear','Financial Year','trim|xss_clean|required|callback_value_exists');
 	$this->form_validation->set_rules('sm_80C','U/S 80C','trim|xss_clean|required');
	$this->form_validation->set_rules('sm_80CCD','U/S 80CCD (1-B)','trim|xss_clean|numeric');
 	$this->form_validation->set_rules('sm_80D','U/S 80D','trim|xss_clean|numeric');
	$this->form_validation->set_rules('sm_80DD','U/S 80DD','trim|xss_clean|numeric');
	$this->form_validation->set_rules('sm_80E','U/S 80E','trim|xss_clean|numeric');
 	$this->form_validation->set_rules('sm_80G','U/S 80G','trim|xss_clean|numeric');
 	$this->form_validation->set_rules('sm_80GGA','U/S 80GGA','trim|xss_clean|numeric');
	$this->form_validation->set_rules('sm_80U','U/S 80U','trim|xss_clean|numeric');
 	$this->form_validation->set_rules('sm_24B','U/S 24B','trim|xss_clean|numeric');
	$this->form_validation->set_rules('sm_other','Other','trim|xss_clean|numeric');
		if($this->form_validation->run()==TRUE){
		  $data = array(
                    'sm_fyear'=>$_POST['sm_fyear'],
                    'sm_80C'=>$_POST['sm_80C'],
                    'sm_80CCD'=>$_POST['sm_80CCD'],
                    'sm_80D'=>$_POST['sm_80D'],
		    'sm_80DD'=>$_POST['sm_80DD'],
                    'sm_80E'=>$_POST['sm_80E'],
                    'sm_80G'=>$_POST['sm_80G'],
                    'sm_80GGA'=>$_POST['sm_80GGA'],
		    'sm_80U'=>$_POST['sm_80U'],
                    'sm_24B'=>$_POST['sm_24B'],
		    'sm_other'=>$_POST['sm_other'],
                    'sm_creatorid'=> $this->session->userdata('username'),
                    'sm_creatordate'=>date('y-m-d'),
                    'sm_modifierid'=>$this->session->userdata('username'),
                    'sm_modifydate'=>date('y-m-d')
                );
		$savingflag=$this->sismodel->insertrec('saving_master', $data) ;
                if ( ! $savingflag)
                {
                    $this->logger->write_logmessage("error", "Error in adding saving master detail");
                    $this->logger->write_dblogmessage("error", "Error in adding saving master detail");
                    $this->session->set_flashdata("err_message",'Error in adding saving master detail');
                    redirect('setup4/savingmaster');

                }
                else{
                    $this->logger->write_logmessage("insert","saving master record insert successfully");
                    $this->logger->write_dblogmessage("insert", "Add saving master record");
                    $this->session->set_flashdata("success", "saving master add successfully...");
                    redirect("setup4/dispsavingmaster");
                }

            }
        }
        $this->load->view('setup4/savingmaster');
    }
/** This function check for duplicate entry
    * @return type
    */

 public function value_exists($key)
 {
     $is_exist = $this->sismodel->isduplicate('saving_master','sm_fyear',$key);

     if($is_exist) {
        $this->form_validation->set_message(
            'value_exists', 'Financial Year' . $key. '  is already exist.'
        );
        return false;
    } else {
    return true;
  }

}

 /** This function Display the Service Master records
     * @return type
     */
    public function dispsavingmaster() {
        $this->savresult=$this->sismodel->get_list('saving_master');
        $this->logger->write_logmessage("view"," view Service master", "view service details...");
        $this->load->view('setup4/dispsavingmaster',$this->savresult);
    }

public function editsavingmaster($id){
	$savingmasterrow=$this->sismodel->get_listrow('saving_master','sm_id', $id);
        if ($savingmasterrow->num_rows() < 1)
        {
           redirect('setup4/editsavingmaster');
        }
        $sav_data_q = $savingmasterrow->row();
       
	 $data['sm_fyear'] = array(
               'name' => 'sm_fyear',
               'id' => 'sm_80C',
               'size' => '30',
               'value' => $sav_data_q->sm_fyear,
               );
	$data['sm_80C'] = array(
               'name' => 'sm_80C',
               'id' => 'sm_80C',
               'size' => '40',
               'value' => $sav_data_q->sm_80C,
               );
	$data['sm_80CCD'] = array(
               'name' => 'sm_80CCD',
               'id' => 'sm_80CCD',
               'size' => '40',
               'value' => $sav_data_q->sm_80CCD,
               );
	$data['sm_80D'] = array(
               'name' => 'sm_80D',
               'id' => 'sm_80D',
               'size' => '40',
               'value' => $sav_data_q->sm_80D,
               );
	$data['sm_80DD'] = array(
               'name' => 'sm_80DD',
               'id' => 'sm_80DD',
               'size' => '40',
               'value' => $sav_data_q->sm_80DD,
               );
	$data['sm_80E'] = array(
               'name' => 'sm_80E',
               'id' => 'sm_80E',
               'size' => '40',
               'value' => $sav_data_q->sm_80E,
               );
	$data['sm_80G'] = array(
               'name' => 'sm_80G',
               'id' => 'sm_80G',
               'size' => '40',
               'value' => $sav_data_q->sm_80G,
               );
	$data['sm_80GGA'] = array(
               'name' => 'sm_80GGA',
               'id' => 'sm_80GGA',
               'size' => '40',
               'value' => $sav_data_q->sm_80GGA,
               );
	$data['sm_80U'] = array(
               'name' => 'sm_80U',
               'id' => 'sm_80U',
               'size' => '40',
               'value' => $sav_data_q->sm_80U,
               );
	$data['sm_24B'] = array(
               'name' => 'sm_24B',
               'id' => 'sm_24B',
               'size' => '40',
               'value' => $sav_data_q->sm_24B,
               );
	 $data['sm_other'] = array(
               'name' => 'sm_other',
               'id' => 'sm_other',
               'size' => '40',
               'value' => $sav_data_q->sm_other,
               );

	 $data['id'] = $id;

	$this->form_validation->set_rules('sm_fyear','Financial Year','trim|xss_clean|required');
        $this->form_validation->set_rules('sm_80C','U/S 80C','trim|xss_clean|required|numeric');
        $this->form_validation->set_rules('sm_80CCD','U/S 80CCD (1-B)','trim|xss_clean|numeric');
        $this->form_validation->set_rules('sm_80D','U/S 80D','trim|xss_clean|numeric');
        $this->form_validation->set_rules('sm_80DD','U/S 80DD','trim|xss_clean|numeric');
        $this->form_validation->set_rules('sm_80E','U/S 80E','trim|xss_clean|numeric');
        $this->form_validation->set_rules('sm_80G','U/S 80G','trim|xss_clean|numeric');
        $this->form_validation->set_rules('sm_80GGA','U/S 80GGA','trim|xss_clean|numeric');
        $this->form_validation->set_rules('sm_80U','U/S 80U','trim|xss_clean|numeric');
        $this->form_validation->set_rules('sm_24B','U/S 24B','trim|xss_clean|numeric');
        $this->form_validation->set_rules('sm_other','Other','trim|xss_clean|numeric');

	if($this->form_validation->run() == FALSE){
                $this->load->view('setup4/editsavingmaster', $data);
            }//formvalidation
            else{
		     $sm_fyear = $this->input->post('sm_fyear', TRUE);
		     $sm_80C = $this->input->post('sm_80C', TRUE);
		     $sm_80CCD = $this->input->post('sm_80CCD', TRUE);
		     $sm_80D = $this->input->post('sm_80D', TRUE);
		     $sm_80DD = $this->input->post('sm_80DD', TRUE);
		     $sm_80E = $this->input->post('sm_80E', TRUE);
		     $sm_80G = $this->input->post('sm_80G', TRUE);
		     $sm_80GGA = $this->input->post('sm_80GGA', TRUE);
	             $sm_80U = $this->input->post('sm_80U', TRUE);
                     $sm_24B = $this->input->post('sm_24B', TRUE);
                     $sm_other = $this->input->post('sm_other', TRUE);

 	$logmessage = "";

                if($sav_data_q->sm_fyear != $sm_fyear)
                    $logmessage = "Edit Saving Data " .$sav_data_q->sm_fyear. " changed by " .$sm_fyear;
 		if($sav_data_q->sm_80C != $sm_80C)
                    $logmessage = "Edit Saving Data " .$sav_data_q->sm_80C. " changed by " .$sm_80C;
		if($sav_data_q->sm_80CCD != $sm_80CCD)
                    $logmessage = "Edit Saving Data " .$sav_data_q->sm_80CCD. " changed by " .$sm_80CCD;
		if($sav_data_q->sm_80D != $sm_80D)
                    $logmessage = "Edit Saving Data " .$sav_data_q->sm_80D. " changed by " .$sm_80D;
		if($sav_data_q->sm_80DD != $sm_80DD)
                    $logmessage = "Edit Saving Data " .$sav_data_q->sm_80DD. " changed by " .$sm_80DD;
		if($sav_data_q->sm_80E != $sm_80E)
                    $logmessage = "Edit Saving Data " .$sav_data_q->sm_80E. " changed by " .$sm_80E;
		if($sav_data_q->sm_80G != $sm_80G)
                    $logmessage = "Edit Saving Data " .$sav_data_q->sm_80G. " changed by " .$sm_80G;
		if($sav_data_q->sm_80GGA != $sm_80GGA)
                    $logmessage = "Edit Saving Data " .$sav_data_q->sm_80GGA. " changed by " .$sm_80GGA;
		if($sav_data_q->sm_80U != $sm_80U)
                    $logmessage = "Edit Saving Data " .$sav_data_q->sm_80U. " changed by " .$sm_80U;
		if($sav_data_q->sm_24B != $sm_24B)
                    $logmessage = "Edit Saving Data " .$sav_data_q->sm_24B. " changed by " .$sm_24B;
		if($sav_data_q->sm_other != $sm_other)
                    $logmessage = "Edit Saving Data " .$sav_data_q->sm_other. " changed by " .$sm_other;

 	$edit_data = array(
                    'sm_fyear'    =>$sm_fyear,
		    'sm_80C'      =>$sm_80C,
		    'sm_80CCD'    =>$sm_80CCD,
		    'sm_80D'      =>$sm_80D,
		    'sm_80DD'     =>$sm_80DD,
		    'sm_80E'      =>$sm_80E,
		    'sm_80G'      =>$sm_80G,
		    'sm_80GGA'    =>$sm_80GGA,
		    'sm_80U'      =>$sm_80U,
		    'sm_24B'      =>$sm_24B,
		    'sm_other'    =>$sm_other,
                );

		$savingmasflag=$this->sismodel->updaterec('saving_master', $edit_data,'sm_id', $id);
                        if(!$savingmasflag) {
                                $this->logger->write_logmessage("error","Edit Saving Setting error", "Edit Saving Setting details. $logmessage ");
                                $this->logger->write_dblogmessage("error","Edit Saving Setting error", "Edit Saving Setting details. $logmessage ");
                                $this->session->set_flashdata('err_message','Error updating Saving Master- ' . $logmessage . '.', 'error');
                                $this->load->view('setup4/editsavingmaster', $data);
                        }else{
                                $this->logger->write_logmessage("update","Edit Saving Setting", "Edit Saving Setting details. $logmessage ");
                                $this->logger->write_dblogmessage("update","Edit Saving Setting", "Edit Saving Setting details. $logmessage ");
                                $this->session->set_flashdata('success','Saving Master detail updated successfully..');
                                redirect('setup4/dispsavingmaster/');
                        }
                }//else
        }//end edit saving master function

/*View Pending User Master Requests*/
public function pendingincomereq(){
	$id=$this->session->userdata('id_user');
	$whdata = array('usm_status'=>0);
	$record= $this->sismodel->get_listspficemore('user_saving_master','usm_id,usm_fyear,usm_empid,usm_pfno,usm_80C,usm_80CCD,usm_80D,usm_80DD,usm_80E,usm_80G,usm_80GGA,usm_80U,usm_24B,usm_other',$whdata);
	$i=0;
foreach ($record as $row){
	$smdata['usmid'] =  $row->usm_id;
	$smdata['usmfyear'] =  $row->usm_fyear;
	$smdata['userid'] = $row->usm_empid;
	$smdata['usmpfno'] = $row->usm_pfno;
	$smdata['usm80C'] =  $row->usm_80C;
	$smdata['usm80CCD'] = $row->usm_80CCD;
	$smdata['usm80D'] =  $row->usm_80D;
	$smdata['usm80DD'] =  $row->usm_80DD;
	$smdata['usm80E'] = $row->usm_80E;
	$smdata['usm80G'] =  $row->usm_80G;
	$smdata['usm80GGA'] =  $row->usm_80GGA;
	$smdata['usm80U'] =  $row->usm_80U;
	$smdata['usm24B'] =  $row->usm_24B;
	$smdata['usmother'] = $row->usm_other;

	$this->usmresult[$i] = $smdata;
		$i++;
		}
$this->load->view('setup4/pendingincomereq');
}

/*Approve User Master Request*/
public function approve($usm_id){
if(isset($_POST['approve']))
                $data['usm_status']['value'] = $this->input->post('usm_status', TRUE);
 		$usm_status = $this->input->post('usm_status', TRUE);
                $logmessage = "";
                if($apply_data_q->usm_status != $usm_status)
                $logmessage = "User Saving Master Status " .$apply_data_q->usm_status. " changed by " .$usm_status;
 
		$update_data = array(              
    		'usm_status' =>  1,
	        );
	        $saflag=$this->sismodel->updaterec('user_saving_master', $update_data, 'usm_id', $usm_id);
	        if(!$saflag)
            	{
                $this->logger->write_logmessage("error","Error in updating User Saving Master Approved ", "Error in User Saving Master Approved. $logmessage . " );
                $this->logger->write_dblogmessage("error","Error in updating User Saving Master Approved ", "Error in User Saving Master Approved. $logmessage ." );
                $this->session->set_flashdata('err_message','Error in updating User Saving Master - ' . $logmessage . '.', 'error');
                $this->load->view('setup4/pendingincomereq', $data);
            	}
            	else{
                $this->logger->write_logmessage("update","User Saving Master Approved", "User Saving Master approved... $logmessage . " );
                $this->logger->write_dblogmessage("update","User Saving Master Approved", "User Saving MasterUser Saving Master approved... $logmessage ." );
                $this->session->set_flashdata('success','User Saving Master approved');	
		redirect('setup4/approvedincomereq/');
                }
            } //end approve function 
/*View Approved Leave Requests*/
public function approvedincomereq(){
        $id=$this->session->userdata('id_user');
        $whdata = array('usm_status'=>1);
        $record= $this->sismodel->get_listspficemore('user_saving_master','usm_id,usm_fyear,usm_empid,usm_pfno,usm_80C,usm_80CCD,usm_80D,usm_80DD,usm_80E,usm_80G,usm_80GGA,usm_80U,usm_24B,usm_other',$whdata);
        $i=0;
foreach ($record as $row){
        $smdata['usmid'] =  $row->usm_id;
        $smdata['usmfyear'] =  $row->usm_fyear;
        $smdata['userid'] = $row->usm_empid;
        $smdata['usmpfno'] = $row->usm_pfno;
        $smdata['usm80C'] =  $row->usm_80C;
        $smdata['usm80CCD'] = $row->usm_80CCD;
        $smdata['usm80D'] =  $row->usm_80D;
        $smdata['usm80DD'] =  $row->usm_80DD;
        $smdata['usm80E'] = $row->usm_80E;
        $smdata['usm80G'] =  $row->usm_80G;
        $smdata['usm80GGA'] =  $row->usm_80GGA;
        $smdata['usm80U'] =  $row->usm_80U;
        $smdata['usm24B'] =  $row->usm_24B;
        $smdata['usmother'] = $row->usm_other;

        $this->usmresult[$i] = $smdata;
                $i++;
                }
$this->load->view('setup4/approvedincomereq');

}//end view approve function

/*Reject User Master Request*/

public function reject ($usm_id) {

        $usmrej=$this->sismodel->get_listrow('user_saving_master','usm_id', $usm_id);
        if ($usmrej->num_rows() < 1)
        {
           redirect('setup4/pendingincomereq');
        }
        $usersavdata = $usmrej->row();

             $data['usm_rejres'] = array(
            'name' => 'usm_rejres',
            'id' => 'usm_rejres',
            'maxlength' => '50',
            'size' => '40',
            'value' => $usersavdata->usm_rejres,
            );
            $data['usm_status'] = array(
            'name' => 'usm_status',
            'id' => 'usm_status',
            'value' => $usersavdata->usm_status,
            );
          	$usm_rejres = $this->input->post('usm_rejres', TRUE);
          	$data['usm_id'] = $usm_id;

                  $this->form_validation->set_rules('usm_rejres','Reason for Rejecting User Saving Master','required');

           if ($_POST) {
             $data['usm_rejres']['value'] = $this->input->post('usm_rejres', TRUE);
             $data['usm_status']['value'] = $this->input->post('usm_status', TRUE);
            }
	
        	if ($this->form_validation->run() == FALSE){
                  $this->load->view('setup4/reject', $data);
                  return;
        }

            $logmessage = "";
            if($Leavedata->usm_rejres != $usm_rejres)
            $logmessage = "Reason for Rejecting User Saving Master " .$usersavdata->usm_rejres. " changed by " .$usm_rejres;

            $update_data = array(
               'usm_status' =>  2,
               'usm_rejres' => $usm_rejres
            );

           $usflag=$this->sismodel->updaterec('user_saving_master', $update_data, 'usm_id', $usm_id);

           if(!$usflag)
            {
                $this->logger->write_logmessage("error","Error in updating User Saving Master", "Error in User Saving Master update. $logmessage . " );
                $this->logger->write_dblogmessage("error","Error in updating User Saving Master ", "Error in User Saving Master update. $logmessage ." );
                $this->session->set_flashdata('err_message','Error in updating User Saving Master - ' . $logmessage . '.', 'error');
                $this->load->view('setup4/reject', $data);
            }
            else{
                $this->logger->write_logmessage("update","User Saving Master Rejected", "User Saving Master Rejected... $logmessage . " );
                $this->logger->write_dblogmessage("update","User Saving Master Rejected", "User Saving Master Rejected... $logmessage ." );
                $this->session->set_flashdata('err_message','User Saving Master Rejected');
                redirect('setup4/rejectedincomereq/');
                }
        redirect('setup4/reject');
}

/*View Rejected User Master Requests*/
public function rejectedincomereq(){
        $id=$this->session->userdata('id_user');
        $whdata = array('usm_status'=>2);
        $record= $this->sismodel->get_listspficemore('user_saving_master','usm_id,usm_fyear,usm_empid,usm_pfno,usm_80C,usm_80CCD,usm_80D,usm_80DD,usm_80E,usm_80G,usm_80GGA,usm_80U,usm_24B,usm_other',$whdata);
        $i=0;
    foreach ($record as $row){  
        $smdata['usmid'] =  $row->usm_id;
        $smdata['usmfyear'] =  $row->usm_fyear;
        $smdata['userid'] = $row->usm_empid;
        $smdata['usmpfno'] = $row->usm_pfno;
        $smdata['usm80C'] =  $row->usm_80C;
        $smdata['usm80CCD'] = $row->usm_80CCD;
        $smdata['usm80D'] =  $row->usm_80D;
        $smdata['usm80DD'] =  $row->usm_80DD;
        $smdata['usm80E'] = $row->usm_80E;
        $smdata['usm80G'] =  $row->usm_80G;
        $smdata['usm80GGA'] =  $row->usm_80GGA;
        $smdata['usm80U'] =  $row->usm_80U;
        $smdata['usm24B'] =  $row->usm_24B;
        $smdata['usmother'] = $row->usm_other;

        $this->usmresult[$i] = $smdata;
                $i++;
        }
	$this->load->view('setup4/rejectedincomereq');

	}//end view rejected function

	public function displaypaymatrix(){
		//$data['paymat']=$this->sismodel->get_list('paymatrix');	
		$selectfield = '*';
		$whorder='pm_wt asc,pm_pc asc';
		$data['paymat']=$this->sismodel->get_orderlistspficemore('paymatrix',$selectfield,'',$whorder);	
		$this->logger->write_logmessage("view"," view Pay Matrix", "view pay matrix");		
		$this->load->view('setup4/displaypaymatrix',$data);
	}

	public function paymatrix(){
		if(isset($_POST['paymatrix'])) {
		        $this->form_validation->set_rules('pmlevel','Salary Level','trim|xss_clean|required');
			for($i=1;$i<=40;$i++){
                        	$this->form_validation->set_rules('subpaylevel'.$i, 'Sub Pay Level', 'trim|xss_clean');
			}
	                if($this->form_validation->run()==TRUE){
	        	 		$datapm = array(
			                    	'pm_pc'=>$_POST['paycomm'],
			                    	'pm_wt'=>$_POST['workingtype'],
			                    	'pm_level'=>$_POST['pmlevel'],
					);
//				$level=$_POST['pmlevel'];
			//	$is_exist = $this->sismodel->isduplicate('paymatrix','pm_level',$level);
				$is_exist = $this->sismodel->isduplicatemore('paymatrix',$datapm);
				if($is_exist){
					$this->session->set_flashdata("err_message", "Level is already exist.");
				}
				else{
					for($i=1;$i<=40;$i++){
                                                $field='pm_sublevel'.$i ;
						$fieldv = $this->input->post('subpaylevel'.$i,'0');
						$datapm[$field]=$fieldv;
					}
	//				print_r($data); die();
                    			$pmflag=$this->sismodel->insertrec('paymatrix', $datapm) ;
		        	        if ( ! $pmflag)
                			{
			                    	$this->logger->write_logmessage("error", "Error in adding pay matrix detail");
	                    			$this->logger->write_dblogmessage("error", "Error in adding pay matrix detail");
                    				$this->session->set_flashdata("err_message",'Error in adding pay matrix detail');
                    				redirect('setup4/paymatrix');

                			}
                			else{
			                    	$this->logger->write_logmessage("insert","pay matrix record insert successfully");
                    				$this->logger->write_dblogmessage("insert", "Add pay matrix record");
                    				$this->session->set_flashdata("success", "Pay matrix added successfully...");
                    				redirect("setup4/displaypaymatrix");
                			}
				
				}
			}
		}
		$this->load->view('setup4/paymatrix');
	}

	public function editpaymatrix($id){
		$data['id'] = $id;
                $whdata = array('pm_id'=>$id);
                $data['pmdata'] = $this->sismodel->get_orderlistspficemore('paymatrix','*',$whdata,'');
		if(isset($_POST['editpaymatrix'])) {
                                //form validation
                	$this->form_validation->set_rules('pmlevel','Salary Level','trim|xss_clean|required');
			for($i=1;$i<=40;$i++){
                                $this->form_validation->set_rules('subpaylevel'.$i, 'Sub Pay Level', 'trim|xss_clean');
                        }
			if($this->form_validation->run()==TRUE){
                                $level=$_POST['pmlevel'];
				$data = array(
                                                'pm_pc'=>$_POST['paycomm'],
                                                'pm_wt'=>$_POST['workingtype'],
                                                'pm_level'=>$_POST['pmlevel'],
                                );
                                for($i=1;$i<=40;$i++){
                        	        $field='pm_sublevel'.$i ;
                                        $fieldv = $this->input->post('subpaylevel'.$i,'0');
                                        $data[$field]=$fieldv;
                                }
				$editpmflag=$this->sismodel->updaterec('paymatrix', $data,'pm_id',$id) ;
				if (!$editpmflag)
                                        {
                                                $this->logger->write_logmessage("error", "Error in updating pay matrix detail");
                                                $this->logger->write_dblogmessage("error", "Error in updating pay matrix detail");
                                                $this->session->set_flashdata("err_message",'Error in updating pay matrix detail');
                                                redirect('setup4/editpaymatrix/'.$id);

                                        }
                                        else{
                                                $this->logger->write_logmessage("update","pay matrix record updated successfully");
                                                $this->logger->write_dblogmessage("update", "Update pay matrix record");
                                                $this->session->set_flashdata("success", "Updated matrix added successfully...");
                                                redirect("setup4/displaypaymatrix");
                                        }

			}
		}	
                $this->load->view('setup4/editpaymatrix',$data);
	}

}//end class
