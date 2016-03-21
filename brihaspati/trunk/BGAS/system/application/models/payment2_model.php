<?php	if ( ! defined('BASEPATH')) exit('No direct script access allowed');
class Payment2_model extends Model {

        function Payment2_model()
        {
                parent::Model();
        }
	
	public function p2record_count() {
                 return $this->db->count_all("bill_voucher_create");
        }
	
	function p2bill_uploadvalues($pagination_counter, $page_count)
        {
		$user = $this->session->userdata('user_name');
                $data=array();
               	$this->db->distinct();
		$this->db->select('bill_voucher_create.id,bill_voucher_create.purchase_order_no,bill_voucher_create.supplier_bill_no,bill_voucher_create.submit_date,bill_voucher_create.submitted_by,bill_voucher_create.submitter_id,bill_voucher_create.bill_name,bill_voucher_create.expense_type,bill_voucher_create.total_amount,bill_voucher_create.narration,bill_voucher_create.decision,bill_voucher_create.entry_id,bill_voucher_create.vc_date,bill_voucher_create.bank_cash_account,bill_voucher_create.mode_of_payment,bill_voucher_create.payment_status,bill_voucher_create.payment_date,bill_voucher_create.party_id,bill_voucher_create.fund_id,bill_voucher_create.expenditure_type,bill_voucher_create.sanc_type,bill_voucher_create.sanc_value,bill_voucher_create.current_location')->from('bill_voucher_create')->join('bill_approval_status','bill_voucher_create.id = bill_approval_status.bill_no');
		if(!check_access('administer'))
			$this->db->where('bill_approval_status.forward_from',$user)->or_where('bill_approval_status.forward_to',$user);
		$this->db->order_by('bill_voucher_create.id', 'desc')->limit($pagination_counter, $page_count);
                $query = $this->db->get();
                if ($query->num_rows() > 0) {
                	foreach ($query->result() as $row) {
                		$data[] = $row;
                	}
                	return $data;
                }
                return false;
        }
	
	function p2bill_printvalue($bill_no)
        {
		$this->db->from('bill_voucher_create')->where('id',$bill_no);
                $show_bill = $this->db->get();
                return $show_bill->row();
        }

	function p2bill_pri_ntvalue($bill_no)
        {
		$this->db->select_max('id')->from('bill_approval_status')->where('bill_no',$bill_no);
		$printvoucherid = $this->db->get();
		foreach($printvoucherid->result() as $row)
		{
			$maxprint_id = $row->id;
			$this->db->from('bill_approval_status')->where('id',$maxprint_id);
                	$show_bi_ll = $this->db->get();
                	return $show_bi_ll->row();
        	}
	}

	function p2_forward_to($bill_no)
	{
		$options = array();
                $counter = 0;
		$this->db->select('authority_name')->from('bill_approval_status')->where('bill_no',$bill_no)->where('status',"Approved")->order_by('id','asc');
		$approv_dbase = $this->db->get();
                foreach($approv_dbase->result() as $row)
		{
			$auth_name = $row->authority_name;
			$name1 = explode('(',$auth_name);
			$name2 = explode(')',$name1[1]);
			$name3 = explode('/',$name2[0]);
			$name4 = $name3[0];//."(".$name3[1].")";
			$options[$counter]['name'] = $name1[0];
                     	$options[$counter]['auth'] = $name4;
                      	$counter++;
		}
		return $options;
	}

	function p2_approv_maxid($bill_no)
	{
		$this->db->select_max('id')->from('bill_approval_status')->where('bill_no',$bill_no);
		$maxbillid = $this->db->get();
		foreach($maxbillid->result() as $row){
			$maxbill_id = $row->id;
			$this->db->select('forward_to')->from('bill_approval_status')->where('id',$maxbill_id);
			$maxim_id = $this->db->get();
			foreach($maxim_id->result() as $row1){
				$max_for_id = $row1->forward_to;
			}
			$max_status_id = $maxbill_id." ".$max_for_id;
		}
		return $max_status_id;
	}

	function p2_reject_by($bill_no)
        {
		$options = array();
                $counter = 0;
                $this->db->select('authority_name')->from('bill_approval_status')->where('bill_no',$bill_no)->where('status',"Rejected")->order_by('id','asc');
                $approv_dbase = $this->db->get();
                foreach($approv_dbase->result() as $row)
                {
                        $auth_name = $row->authority_name;
                        $name1 = explode('(',$auth_name);
                        $name2 = explode(')',$name1[1]);
                        $name3 = explode('/',$name2[0]);
                        $name4 = $name3[0];//."(".$name3[1].")";
                        $options[$counter]['name'] = $name1[0];
                        $options[$counter]['auth'] = $name4;
                        $counter++;
                }
                return $options;
        }

	function p2_submitter_name($bill_no)
	{
		$name = 0;
		$db1 = $this->load->database('login',TRUE); 
		$db1->select('userprofile.firstname as firstname, userprofile.lastname as lastname')->from('userprofile')->join('edrpuser','userprofile.userid = edrpuser.id')->where('edrpuser.username',$subm_id);
		$su_id = $db1->get();
		foreach($su_id->result() as $row){
                    	$firstname = $row->firstname;
                     	$lastname = $row->lastname;
                }
               	$db1->close();
                $name = $firstname. " ".$lastname;
		return $name;
	}
}
?>
