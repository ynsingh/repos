<?php

class Payrollsetup_model extends CI_Model {
function __construct() {
        parent::__construct();
    }

	function Payrollsetup_model()
	{
		parent::Model();
	}

	function get_all_ledgers_bankcash()
        {
                $options = array();
                $options[0] = "(Please Select)";
                $this->db->from('ledgers')->where('type', 1)->order_by('name', 'asc');
                $ledger_q = $this->db->get();
                foreach ($ledger_q->result() as $row)
                {
                        $cd = $row->code;
                        $nme= $row->name;
                        $new_id = $cd."#".$nme;
                        $options[$new_id] =$row->name;
                }
                return $options;
        }


	/** 
         * Returns code of the requested account, 
         * as specified in the 'groups' table
         */
        function get_account_code($account_name)
        {
                $this->db->from('groups');
                $this->db->select('code');
                $this->db->where('name =', $account_name);
                if($account_name == 'Expenses')
                        $this->db->or_where('name = ', 'Expenditure');
                if($account_name == 'Liabilities and Owners Equity')
                        $this->db->or_where('name = ', 'Sources of Funds');
                if($account_name == 'Assets')
                        $this->db->or_where('name = ', 'Application of Funds');
                $group = $this->db->get();
                foreach($group->result() as $row)
                        return $row->code;
        }

	function get_selected_groups_withcode($account_name)
        {
                $options = array();
                $options[0] = "(Please Select)";
                //Get account code
                $account_code = $this->get_account_code($account_name);
                $this->db->from('groups');
                //$this->db->where('code LIKE', '40%');
                $this->db->where('code LIKE', $account_code.'%');
                //$this->db->where('code NOT LIKE', '40');
                if($account_name == 'Expenses' || $account_name == 'Expenditure')
                        $this->db->where('code NOT LIKE', $account_code);
                $this->db->where('status', '0')->order_by('name', 'asc');
                $group_code = $this->db->get();
                foreach ($group_code->result() as $row)
                {
                        $new_id = "$row->code"."#"."$row->name";
                        $options[$new_id] = $row->name."($row->code)";
                }

                //$this->db->from('ledgers')->where('code LIKE', '40%')->order_by('name', 'asc');
                $this->db->from('ledgers')->where('code LIKE', $account_code.'%')->order_by('name', 'asc');
                $group_code = $this->db->get();
                foreach ($group_code->result() as $row)
                {
                        $new_id = "$row->code"."#"."$row->name";
                        $options[$new_id] = $row->name."($row->code)";
                }
                return $options;
        }

}
