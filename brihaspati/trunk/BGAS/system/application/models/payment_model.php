<?php
     if ( ! defined('BASEPATH')) exit('No direct script access allowed');
class Payment_model extends Model {

        function Payment_model()
        {
                parent::Model();
        }
	
	public function record_count() {
                 return $this->db->count_all("bill_approval");
        }

	
	function bill_uploadvalues($pagination_counter, $page_count)
        {
                $data=array();
                $this->db->select('bill_no,submit_date,submitted_by,total_amount,bill_name,expense_type,decision,approval_date,approved_amount,approved_by,vc_date,bank_cash_account,mode_of_payment,payment_status,payment_date')->from('bill_approval')->order_by('bill_no', 'desc')->limit($pagination_counter, $page_count);
                $query = $this->db->get();
                if ($query->num_rows() > 0) {
                foreach ($query->result() as $row) {
                $data[] = $row;
                }
                return $data;
                }
                return false;

        }

	function  amount_for_approval($expensetype)
	{
		$app_amount='';
		$this->db->select('approved_amount')->from('bill_approval')->where('expense_type',$expensetype);
		$query = $this->db->get();
		if($query->num_rows() > 0) {
			foreach ($query->result() as $row){
				$app_amount1= $row->approved_amount;
				if($app_amount1!='0.00'){
					$app_amount=$app_amount+$app_amount1;
				}
			}
			return $app_amount;
		}
		
	}
	
	function bill_printvalue($bill_no)
        {
                $this->db->from('bill_approval')->where('bill_no',$bill_no);
                $show_bill = $this->db->get();
                return $show_bill->row();
        }

}
?>
