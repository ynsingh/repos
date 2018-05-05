<?php
// if (!defined('BASEPATH')) exit('No direct script access allowed');

//include Rest Controller library
require APPPATH . '/libraries/REST_Controller.php';

class Bgasapi extends REST_Controller {

    public function __construct() { 
        parent::__construct();
        
        //load user model, ledger model, secunit model
        $this->load->model('Ledger_model','ledger');
        $this->load->model('User_model','user');
	$this->load->model('Secunit_model','secunit');
	$this->load->model('Restapi_model','bgasmod');
	$this->load->model('Entry_model');	
    }
    
    
    public function sal_post() {
		// entry number,entry date, entry type name, cr amt, dr amt, cr ledger code, dr ledger code,partyid, narration
		// insert data in entries table, entry items, cheque print
	//	print_r("I am Herer");	
		// check for budget
		// $this->Budget_model->check_budget($data_ledger_id,$cr_total,$dr_total,$data_amount,$data);
		$data_entry_name=$this->post('entrytypename');
		// check for duplicate voucher number
		$number=$this->post('entrynumber');
		$entry_type_id= $this->Entry_model->get_id_of_entry_type(strtolower($data_entry_name));
                $duplicate = $this->Entry_model->check_duplicacy($number,$entry_type_id);
                if ($duplicate)
                {
                         $this->messages->add('Voucher No. Already Exist. Please Input a Different Voucher No.', 'error');
                         return;
		}			
		//get the entry number if null
		if (empty($number))
			$number = $this->Entry_model->next_entry_number($entry_type_id);
		// check for db and cr amount
		$dr_total=$this->post('drtotal');
		$cr_total=$this->post('crtotal');
		if (float_ops($dr_total, $cr_total, '!='))
                        {
                                $this->messages->add('Debit and Credit Total does not match!', 'error');
                                $this->template->load('template', 'entry/add', $data);
                                return;
                        }
		$today = date("Y-m-d H:i:s");
		$data_date=$this->post('entrydate');
		$data_narration=$this->post('narration');
		$data_tag = '';
		$uname = 'Payroll_Admin';
			//insert into entries
		$insert_data = array(
                                'number' => $number,
                                'date' => $data_date,
                                'narration' => $data_narration,
                                'entry_type' => $entry_type_id,
                                'tag_id' => $data_tag,
                                'update_date' => $today,
                                'submitted_by' => $uname,
                                'forward_refrence_id' => '0',
				'backward_refrence_id' => '0',
				'dr_total' => $dr_total,
                                'cr_total' => $cr_total,
                        );
            	$insert = $this->bgasmod->insert('entries',$insert_data);
		$entry_id = $this->db->insert_id();
		$data_ledger_ccode=$this->post('crledgercode');
		$data_ledger_cid=$this->Ledger_model->get_ledger_id($data_ledger_ccode);
		
		$secunitid = $this->post('partycode');
		//insert values in entry items
		$insert_ledger_datac = array(
                                        'entry_id' => $entry_id,
                                        'ledger_id' => $data_ledger_cid,
                                        'amount' => $cr_total,
                                        'dc' => 'C',
                                        'update_date' => $data_date,
                                        'forward_refrence_id' => '0',
                                        'backward_refrence_id' => '0',
                                        'secunitid' => $secunitid,
                                        'ledger_code' =>  $data_ledger_ccode,
                                );

		$insert = $this->bgasmod->insert('entry_items', $insert_ledger_datac);
		$data_ledger_dcode=$this->post('drledgercode');
                $data_ledger_did= $this->Ledger_model->get_ledger_id($data_ledger_dcode);
		$insert_ledger_datad = array(
                                        'entry_id' => $entry_id,
                                        'ledger_id' => $data_ledger_did,
                                        'amount' => $dr_total,
                                        'dc' => 'D',
                                        'update_date' => $data_date,
                                        'forward_refrence_id' => '0',
                                        'backward_refrence_id' => '0',
                                        'secunitid' => $secunitid,
                                        'ledger_code' => $data_ledger_dcode,
                                );
                $insert = $this->bgasmod->insert('entry_items', $insert_ledger_datad);
		//insert cheque data
            
            //check if the user data inserted
            if($insert){
                //set the response and exit
                $this->response([
                    'status' => TRUE,
                    'message' => 'Data has been added successfully.'
                ], REST_Controller::HTTP_OK);
            }else{
                //set the response and exit
                $this->response("Some problems occurred, please try again.", REST_Controller::HTTP_BAD_REQUEST);
            }
	}
	public function sal_put() {
		//entry number,entry date, entry type name, tot amt, cr ledger code, dr ledger code,partyid, narration
		// insert data in entries table, entry items, 

                $number=$this->post('entrynumber', TRUE);
		$data_entry_name=$this->post('entrytypename');
		$data_ledger_ccode=$this->post('crledgercode');
                $data_ledger_cid=$this->Ledger_model->get_ledger_id($data_ledger_ccode);
		$data_ledger_dcode=$this->post('drledgercode');
		$data_ledger_did= $this->Ledger_model->get_ledger_id($data_ledger_dcode);
		$dr_total=$this->post('drtotal');
		$secunitid = $this->post('partycode');
		$today = date("Y-m-d H:i:s");
                $data_date=$this->post('entrydate');
		$data_narration=$this->post('narration');
		$uname = 'Payroll_Admin';

		$entry_type_id= $this->Entry_model->get_id_of_entry_type(strtolower($data_entry_name));
		$entry_id = $this->Entry_model->get_entryid($number, $entry_type_id);
		$update_data = array(
				'date' => $data_date,
                                'narration' => $data_narration,
                                'entry_type' => $entry_type_id,
                                'tag_id' => $data_tag,
                                'update_date' => $today,
                                'submitted_by' => $uname,
                                'forward_refrence_id' => '0',
                                'backward_refrence_id' => '0',
                                'dr_total' => $dr_total,
                                'cr_total' => $cr_total,
                        );
		$whdata = array( 'id' => $entry_id);
                $update = $this->bgasmod->update('entries',$update_data,$whdata);
               // $entry_id = $this->db->insert_id();
                $data_ledger_ccode=$this->post('crledgercode');
                $data_ledger_cid=$this->Ledger_model->get_ledger_id($data_ledger_ccode);

		$secunitid = $this->post('partycode');
		
		$entryitemid=$this->Entry_model->get_entryitemid($entry_id, "C",$data_ledger_cid);
                //insert values in entry items
                $update_ledger_datac = array(
                                        'ledger_id' => $data_ledger_cid,
                                        'amount' => $cr_total,
                                        'dc' => 'C',
                                        'update_date' => $data_date,
                                        'forward_refrence_id' => '0',
                                        'backward_refrence_id' => '0',
                                        'secunitid' => $secunitid,
                                        'ledger_code' =>  $data_ledger_ccode,
                                );
		
		$whdata = array( 'id' => $entryitemid);
                $update = $this->bgasmod->update('entry_items', $update_ledger_datac,$whdata);
                $data_ledger_dcode=$this->post('drledgercode');
		$data_ledger_did= $this->Ledger_model->get_ledger_id($data_ledger_dcode);
		$entryitemid=$this->Entry_model->get_entryitemid($entry_id, "D",$data_ledger_did);
                 $update_ledger_datad = array(
                                        'ledger_id' => $data_ledger_did,
                                        'amount' => $dr_total,
                                        'dc' => 'D',
                                        'update_date' => $data_date,
                                        'forward_refrence_id' => '0',
                                        'backward_refrence_id' => '0',
                                        'secunitid' => $secunitid,
                                        'ledger_code' => $data_ledger_dcode,
                                );
		$whdata = array( 'id' => $entryitemid);
		$update = $this->bgasmod->update('entry_items', $update_ledger_datad, $whdata);
		//check if the user data updated
            if($update){
                //set the response and exit
                $this->response([
                    'status' => TRUE,
                    'message' => 'Data has been updated successfully.'
                ], REST_Controller::HTTP_OK);
            }else{
                //set the response and exit
                $this->response("Some problems occurred, please try again.", REST_Controller::HTTP_BAD_REQUEST);
            }

 
	}
    
}

?>
