<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');
class Reportlist
{
	var $id = 0;
	var $name = "";
	var $code = "";
	var $status = 0;
	var $total = 0.00;
	var $total2 = 0.00;
	var $optype = "";
	var $opbalance = 0;
	var $schedule = 0;
	var $children_groups = array();
	var $children_ledgers = array();
	var $counter = 0;
	//var $check = 0;

	var $dr_total = 0.00;
        var $cr_total = 0.00;
        var $old_dr_total = 0.00;
        var $old_cr_total = 0.00;
        var $netpl = 0.00;
        var $netpl_old = 0.00;
	var $prevYearDB = "";
        var $old_total = 0.00;
	var $opening_balance = 0.00;
	var $opening_balance_prev = 0.00;
	//var $opening_balance_type = "";
	var $db_username = "";
	var $db_password = "";
	var $host_name = "";
	var $port = "";

	public static $temp_max = 0;
	public static $max_depth = 0;
	public static $csv_data = array();
	public static $csv_row = 0;

	function __construct()
	{
		$CI =& get_instance();
                $CI->load->model('Payment_model');
		$CI->load->model('Setting_model');
		$CI->load->model('investment_model');
		$CI->load->model('ledger_model');
		return;
	}

	/**
	 * Method for getting previous year's
	 * database name and other details.
	 * @author Priyanka Rawat <rpriyanka12@ymail.com>
	 */
	function getPreviousYearDetails()
        {
                $CI =& get_instance();
                $CI->db->from('settings')->where('id', 1);
                $settings_q = $CI->db->get();
                $settings= $settings_q->row();
                $ins_name = $settings->ins_name;
                $uni_name = $settings->uni_name;
		$date1 = explode("-", $settings->fy_start);
                $old_year_start = $date1[0]-1;
                $date1 = explode("-", $settings->fy_end);
                $old_year_end = $date1[0]-1;
                $previous_year = $old_year_start . "-" . $old_year_end;

                //$current_year = explode("-", $settings->fy_start);
                //$previous_year = $current_year[0]-1;
                $CI->db->close();

                //fetch previous year details
                $db = $CI->load->database('login', TRUE);
                $db->select('databasename, uname, dbpass, hostname, port');

                $db->from('bgasAccData')->where('organization', $ins_name)->where('unit', $uni_name)->where('fyear', $previous_year);
                $login_q = $db->get();
                if($login_q->num_rows()>0){
                        $login = $login_q->row();
                        $this->prevYearDB = $login->databasename;
			$this->db_username = $login->uname;
        		$this->db_password = $login->dbpass;
        		$this->host_name = $login->hostname;
        		$this->port = $login->port;
                }
                $CI->db->close();
        }

	function init($id)
	{
		$CI =& get_instance();
		if ($id == 0)
		{
			$this->id = 0;
			$this->name = "None";
			$this->total = 0;

		}
		else {
			$CI->db->from('groups')->where('id', $id)->limit(1);
                        $group_q = $CI->db->get();
                        if($group_q->num_rows() != 0){
                                $group = $group_q->row();
                                $this->id = $group->id;
                                $this->name = $group->name;
                                $this->code = $group->code;
                                $this->status = $group->status;
                        //$this->schedule = $group->schedule;
                                $this->total = 0;
                                $this->total2 = 0;
                        }

		}
		if($this->status==0)
		{
			$new_code = substr($this->code, 0, $this->code < 0 ? 3 : 2);
			if($new_code == 10 || $new_code == 20)
			{
				$this->add_sub_groups();
				$this->add_balancesheet_sub_ledgers();
			}
			elseif($new_code == 30 || $new_code == 40)
			{
				$this->add_sub_groups();
				$this->add_income_expense_sub_ledgers();
			}
		}
	}

	function add_sub_groups()
	{
		$CI =& get_instance();
		$CI->db->from('groups')->where('parent_id', $this->id);
		$child_group_q = $CI->db->get();
		$counter = 0;
		foreach ($child_group_q->result() as $row)
		{
			$this->children_groups[$counter] = new Reportlist();
			$this->children_groups[$counter]->init($row->id);
			$this->total = float_ops($this->total, $this->children_groups[$counter]->total, '+');

			$this->total2 = float_ops($this->total2, $this->children_groups[$counter]->total2, '+');
			$counter++;
		}
	}
	function add_balancesheet_sub_ledgers()
	{
		$CI =& get_instance();
		$CI->db->from('ledgers')->where('group_id', $this->id);
		$child_ledger_q = $CI->db->get();
		$counter = 0;
		foreach ($child_ledger_q->result() as $row)
		{
			$this->children_ledgers[$counter]['id'] = $row->id;
			$this->children_ledgers[$counter]['code'] = $row->code;
			$this->children_ledgers[$counter]['name'] = $row->name;

			$this->children_ledgers[$counter]['total'] = $CI->Ledger_model->get_balancesheet_ledger_balance($row->id);
			list ($this->children_ledgers[$counter]['opbalance'], $this->children_ledgers[$counter]['optype']) = $CI->Ledger_model->get_op_balance($row->id);
			$this->total = float_ops($this->total, $this->children_ledgers[$counter]['total'], '+');

			$this->children_ledgers[$counter]['total2'] = $CI->Ledger_model->get_balancesheet_old_ledger_balance($row->id);
			list ($this->children_ledgers[$counter]['opbalance'], $this->children_ledgers[$counter]['optype']) = $CI->Ledger_model->get_prev_year_op_balance($row->id);
			$this->total2 = float_ops($this->total2, $this->children_ledgers[$counter]['total2'], '+');
		//this->cr_total = float_ops($this->cr_total, $CI->Ledger_model->get_cr_total1($row->id), '+');
	
                        //$this->dr_total = float_ops($this->dr_total, $CI->Ledger_model->get_dr_total1($row->id), '+');

			$counter++;
		}
	}

	function add_income_expense_sub_ledgers()
	{
		$CI =& get_instance();
		$CI->db->from('ledgers')->where('group_id', $this->id);
		$child_ledger_q = $CI->db->get();
		$counter = 0;
		foreach ($child_ledger_q->result() as $row)
		{
			if($row->name != 'Transfer Account'){
			$this->children_ledgers[$counter]['id'] = $row->id;
			$this->children_ledgers[$counter]['code'] = $row->code;
			$this->children_ledgers[$counter]['name'] = $row->name;

			$this->children_ledgers[$counter]['total'] = $CI->Ledger_model->get_ledger_balance1($row->id);
			list ($this->children_ledgers[$counter]['opbalance'], $this->children_ledgers[$counter]['optype']) = $CI->Ledger_model->get_op_balance($row->id);
			$this->total = float_ops($this->total, $this->children_ledgers[$counter]['total'], '+');

			$this->children_ledgers[$counter]['total2'] = $CI->Ledger_model->get_old_ledger_balance($row->id);
			list ($this->children_ledgers[$counter]['opbalance'], $this->children_ledgers[$counter]['optype']) = $CI->Ledger_model->get_prev_year_op_balance($row->id);
			$this->total2 = float_ops($this->total2, $this->children_ledgers[$counter]['total2'], '+');
			$counter++;
			}
		}
	}

	 /* Display Account list in Balance sheet and Profit and Loss st */
        function account_st_short($c = 0)
        {
                $this->counter = $c;
                if ($this->id != 0)
                {
                        echo "<tr class=\"tr-group\">";
                        echo "<td class=\"td-group\">";
                        echo "&nbsp;" .  $this->name;
                        echo "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($this->total) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($this->total2) . "</td>";
                        echo "</tr>";
                }
                foreach ($this->children_groups as $id => $data)
                {
                        $this->counter++;
                        $data->account_st_short($this->counter);
                        $this->counter--;
                }
                if (count($this->children_ledgers) > 0)
                {
                        $this->counter++;
                        foreach ($this->children_ledgers as $id => $data)
                        {
                                echo "<tr class=\"tr-ledger\">";
                                echo "<td class=\"td-ledger\">";
                                echo "&nbsp;" . anchor('report/ledgerst/' . $data['id'], $data['name'], array('title' => $data['name'] . ' Ledger Statement', 'style' => 'color:#000000'));
                                echo "</td>";
                                echo "<td align=\"right\">" . convert_amount_dc($data['total']) . "</td>";
                                echo "<td align=\"right\">" . convert_amount_dc($data['total2']) . "</td>";
                                echo "</tr>";
                        }
                        $this->counter--;
                }
        }
/** Method for displaying Balance Sheet
 * in MHRD format.
 */
 function new_balance_sheet($c,$id1,$type,$database,$i)
 {
 	$CI = & get_instance();
	 //Get current label.
        $current_active_account = $CI->session->userdata('active_account');
        $prev_sum = 0;
        $mhrdlist2 = "";
	$mhrdlist3 = "";
	$incomelist2 = "";
	$old_income_total = "";
	$old_expense_total = "";
        $check = 0;
        $this->counter = $c;
	$c1 = $c;
	$type_total = "";
        $sum = 0;
        $total = "";
	$id2 = $id1;
        $CI->db->from('settings');
        $detail = $CI->db->get();
        foreach ($detail->result() as $row)
        {
        $date1 = $row->fy_start;
        $date2 = $row->fy_end;
        }
        $fy_start=explode("-",$date1);
        $fy_end=explode("-",$date2);

        $curr_year = $fy_start[0] ."-" .$fy_end[0];
       	$prev_year = ($fy_start[0]-1) ."-" . ($fy_end[0]-1);
        $CI->load->model('Setting_model');
        $ledger_name = $CI->Setting_model->get_from_settings('ledger_name');
        $CI->load->model('Payment_model');
        $db = $CI->Payment_model->database_name();

        $CI->db->select('name,code,id')->from('groups')->where('parent_id =',$id1);
        $main = $CI->db->get();
       	$no_row = $main->num_rows();
        $main_result= $main->result();
        foreach($main_result as $row)
	{
                $this->name = $row->name;
                $this->code =$row->code;
                $this->id = $row->id;
                $liability = new Reportlist();
                $liability->init($this->id);
                $total = $liability->total;
                $sum = $sum+$total;
	        if(($this->countDigits() == 4) && ($this->id != 0) && ($this->code > 100) && ($this->code!= '1006') && ($this->code!= 1005))
	        {
	       	if($this->name == 'Current Liabilities')
		$this->name = ('Current Liabilities And Provisions');
		if($this->name == 'General Funds')
		{
		$check++;
		}
		else
		{
		$check = 0;
		}
		if($type == 'view'){
		echo "<tr class=\"tr-group\">";
                echo "<td class=\"td-group\">";
                echo "&nbsp;" .  $this->name;
                echo "</td>";
                echo "<td class=\"td-group\">";
		}
		if(($c == $c1) && ($id1 == $id2) && ($type == "view") && ($database == "NULL"))
                {
		if($check == 0)
		{
		$this->counter++;
                echo "&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/schedule/' . $this->code . '/' . $this->counter, $this->counter, array('title' => $this->name, 'style' => 'color:#000000'));
		/* Get Balance of net income/(expenditure) for 'this' ledger head*/
	       //if($c == 2){
		if($ledger_name == $this->name)
		{
			$income = new Reportlist();
			$income->init(3);
			$income_total = -$income->total;
			$expense = new Reportlist();
			$expense->init(4);
			$expense_total = $expense->total;
			$old_income_total = -$this->income_total(3,$type,$database);
                        $old_expense_total = $this->expense_total(4,$type,$database);
			$pandl = float_ops($income_total, $expense_total, '-');
			$old_pandl = float_ops($old_income_total, $old_expense_total, '-');
                        if ($pandl != 0 || $old_pandl !=0)
                        {
                        //the change in sign is needed
                        if($pandl > 0)
                        $total = float_ops($total, -$pandl, '+');
                        else
                      	$total = float_ops($total, -$pandl, '+');
                        if($old_pandl > 0)
                        $mhrdlist2 = float_ops($mhrdlist2, -$old_pandl, '+');
                        else
                       	$mhrdlist2 = float_ops($mhrdlist2, -$old_pandl, '+');
                        }
		}//if
	}//ifcheck
		else
		{
			$income = new Reportlist();
                	$income->init(3);
			$income_total = -$income->total;
                	$expense = new Reportlist();
                	$expense->init(4);
               		$expense_total = $expense->total;
			$old_income_total = -$this->income_total(3,$type,$database);
                        $old_expense_total = $this->expense_total(4,$type,$database);
			$pandl = float_ops($income_total, $expense_total, '-');
                        $old_pandl = float_ops($old_income_total, $old_expense_total, '-');
                        if ($pandl != 0 || $old_pandl !=0)
                        {
                        //the change in sign is needed
                        if($pandl > 0)
                        $total = float_ops($total, -$pandl, '+');
                        else
                        $total = float_ops($total, -$pandl, '+');
                        if($old_pandl > 0)
                        $mhrdlist2 = float_ops($mhrdlist2, -$old_pandl, '+');
                        else
                        $mhrdlist2 = float_ops($mhrdlist2, -$old_pandl, '+');
                        }

		}//else
			echo "</td>";
        	        echo "<td align=\"right\">" . convert_amount_dc(+$total) . "</td>";
			// code for reading previous year data from xml
			$acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
               		$file_name="MHRD"."-".$current_active_account."-".$prev_year.".xml";
               		$tt=$acctpath."/".$file_name;
                	if(file_exists($tt))
			{
                     	$doc = new DomDocument();
                        $doc->formatOutput = true;
                        $doc->load($tt);
                        $xpath = new DomXPath($doc);
                        $xpath->query("/MHRD/MHRD_Name/Group_Name");
                        $xpath->query("/MHRD/MHRD_Name/Amount");
                        $xpath->query("/MHRD/MHRD_Name/Group_ID");
                        $mhrdnode1 = $xpath->query("/MHRD/MHRD_Name/Group_Name");
                        $mhrdnode2 = $xpath->query("/MHRD/MHRD_Name/Amount");
                        $mhrdnode3 = $xpath->query("/MHRD/MHRD_Name/Group_ID");
                        $mhrdlist1 = @$mhrdnode1->item($i)->nodeValue;
                        $mhrdlist2 = @$mhrdnode2->item($i)->nodeValue;
                        $mhrdlist3 = @$mhrdnode3->item($i)->nodeValue;
                        }
                        $prev_sum = $prev_sum + $mhrdlist2;
                        $i++;
			if($mhrdlist3 == '5')
                        echo "<td align=\"right\">" . convert_amount_dc(float_ops($mhrdlist2, -$old_pandl, '+')) . "</td>";
			else
			echo "<td align=\"right\">" . convert_amount_dc(+$mhrdlist2) . "</td>";
			echo "</tr>";
			}//ifview
			if(($c == $c1) && ($id1 == $id2) &&  ($type == "CF") && ($database != "NULL"))
                        {
                        $t_name = "MHRD";
			$liability = new Reportlist();
	                $liability->init($this->id);
        	        $total = $liability->total;
                        $CI =& get_instance();
                        $CI->load->model('payment_model');
                        $data = $CI->payment_model->xml_creation($t_name,$this->id,$database,$this->name,$curr_year,$total);
                       	}
			}//ifcondition

			else

			$CI = & get_instance();
                        $CI->db->select('id,name,code')->from('groups')->where('parent_id',$this->id);
                        $query1 = $CI->db->get();
                        $q1 = $query1->result();
                        foreach($q1 as $row){
                        $this->name = $row->name;
                        $this->code =$row->code;
                        $this->id = $row->id;
                        $liability = new Reportlist();
                        $liability->init($this->id);
                        $total = $liability->total;
		        if(($this->countDigits() == 6) && ($this->id != 0) && ($this->code > 100) && ($this->id < 98) && ($this->id!= '48') && ($this->id!= '49') && ($this->id!= '50') && ($this->id!= '51') && ($this->id!= '52') && ($this->id!= '53'))
			{
				if($type == 'view'){
				echo "<tr>";
                        	echo "<td class=\"td-group\">";
                        	echo "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" .  $this->name;
                        	echo "</td>";
				}
				$CI =& get_instance();
                        	$CI->db->select('parent_id');
                        	$CI->db->from('groups')->where('id', $this->id);
                        	$groups_q = $CI->db->get();
				$groups= $groups_q->row();
	                	$parent_id = $groups->parent_id;
				$CI =& get_instance();
                        	$CI->db->select('name');
                        	$CI->db->from('groups')->where('id', $parent_id);
                        	$groups_q = $CI->db->get();
                        	$groups= $groups_q->row();
                        	$name = $groups->name;

                        	echo "<td class=\"td-group\">";
				if(($c == $c1) && ($id1 == $id2) && ($type == "view") && ($database == "NULL"))
				{
				if($name  == 'General Funds')
				{
				$this->counter++;
				echo "&nbsp;&nbsp;&nbsp;&nbsp;" . anchor_popup('report/schedule/' . $this->code . '/' . $this->counter, $this->counter, array('title' => $this->name, 'style' => 'color:#000000'));
				//if($c == 2){
				if($ledger_name == $this->name)
				{
                                	$income = new Reportlist();
                                        $income->init(3);
                                        $expense = new Reportlist();
                                        $expense->init(4);
                                        $income_total = -$income->total;
                                        $expense_total = $expense->total;
					$pandl = float_ops($income_total, $expense_total, '-');
		                        $old_pandl = float_ops($old_income_total, $old_expense_total, '-');
                		        if ($pandl != 0 || $old_pandl !=0)
                       			{
                        		//the change in sign is needed
                        		if($pandl > 0)
                        		$total = float_ops($total, -$pandl, '+');
                        		else
                        		$total = float_ops($total, -$pandl, '+');
                        		if($old_pandl > 0)
                        		$mhrdlist2 = float_ops($mhrdlist2, -$old_pandl, '+');
                        		else
                        		$mhrdlist2 = float_ops($mhrdlist2, -$old_pandl, '+');
                        		}
                        	}
				}//if(name = general fund)
				if($this->name == "General Reserve")
				{
					$income = new Reportlist();
                                        $income->init(3);
                                        $expense = new Reportlist();
                                        $expense->init(4);
                                        $income_total = -$income->total;
                                        $expense_total = $expense->total;
					$old_income_total = -$this->income_total(3,$type,$database);	
					$old_expense_total = $this->expense_total(4,$type,$database);
                                        $pandl = float_ops($income_total, $expense_total, '-');
					$old_pandl = float_ops($old_income_total, $old_expense_total, '-');
                                        if ($pandl != 0 || $old_pandl !=0)
                                        {
                                        //the change in sign is needed
                                        if($pandl > 0)
                                        $total = float_ops($total, -$pandl, '+');
                                        else
                                        $total = float_ops($total, -$pandl, '+');
					if($old_pandl > 0)
                                        $mhrdlist2 = float_ops($mhrdlist2, -$old_pandl, '+');
                                      	else
                                        $mhrdlist2 = float_ops($mhrdlist2, -$old_pandl, '+');
					}

                                }//if  
                        	echo "</td>";
                        	echo "<td align=\"right\">" . convert_amount_dc($total) . "</td>";

				$acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
        	                $file_name="MHRD"."-".$current_active_account."-".$prev_year.".xml";
                	        $tt=$acctpath."/".$file_name;
                        	if(file_exists($tt))
                        	{
                        	$doc = new DomDocument();
                                $doc->formatOutput = true;
                                $doc->load($tt);
                                $xpath = new DomXPath($doc);
                                $xpath->query("/MHRD/MHRD_Name/Group_Name");
                                $xpath->query("/MHRD/MHRD_Name/Amount");
                                $xpath->query("/MHRD/MHRD_Name/Group_ID");
                                $mhrdnode1 = $xpath->query("/MHRD/MHRD_Name/Group_Name");
                                $mhrdnode2 = $xpath->query("/MHRD/MHRD_Name/Amount");
                                $mhrdnode3 = $xpath->query("/MHRD/MHRD_Name/Group_ID");
                                $mhrdlist1 = @$mhrdnode1->item($i)->nodeValue;
                                $mhrdlist2 = @$mhrdnode2->item($i)->nodeValue;
                                $mhrdlist3 = @$mhrdnode3->item($i)->nodeValue;
                        	}
                                $i++;
				if($mhrdlist3 != '10')
                                echo "<td align=\"right\">" . convert_amount_dc(+$mhrdlist2) . "</td>";
				else
                                echo "<td align=\"right\">" . convert_amount_dc(float_ops($mhrdlist2, -$old_pandl, '+')) . "</td>";
				echo "</tr>";
                       }//ifview

			if(($c == $c1) && ($id1 == $id2) && ($type == "CF") && ($database != "NULL"))
                        {
                                $t_name = "MHRD";
                                $total = "";
                                $CI =& get_instance();
				$liability = new Reportlist();
				$liability->init($this->id);
				$total = $liability->total;
                                $CI->load->model('payment_model');
                                $data = $CI->payment_model->xml_creation($t_name,$this->id,$database,$this->name,$curr_year,$total);
                        }
                       }//elseif
		}//foreach
	}//foreach

	/*		foreach ($this->children_groups as $id => $data)
                	{
                        	$len = $data->countDigits();
                        	$this->counter = $data->new_balance_sheet($this->counter,$id1,$type,$database);
				print_r($this->counter);
                	} 

		/*foreach ($this->children_ledgers as $id => $led_data){
			$search = '1234567890';
	                $code = strlen($led_data['code']) - strlen(str_replace(str_split($search), '', $led_data['code']));
			if($code == 6){
			echo "<tr>";
                        	echo "<td class=\"td-group\">";
		                        echo "&nbsp;" .  $led_data['name'];
                	        echo "</td>";
				echo "<td></td>";	
				echo "<td align=\"right\">" . convert_amount_dc($led_data['total']) . "</td>";
	                        echo "<td align=\"right\">" . convert_amount_dc($led_data['total2']) . "</td>";
                        echo "</tr>";
			}
		}*/
	$this->prev_total = $prev_sum;
	$this->curr_total = $sum;
        }//main
function income_total($id,$type,$database)
{
	$CI = & get_instance();
	//Get current label.
        $current_active_account = $CI->session->userdata('active_account');
	$incomelist2 = "";
	$type_total = "";
	$i = 0;
	$id = "";
        $total = "";
        $CI->db->from('settings');
        $detail = $CI->db->get();
        foreach ($detail->result() as $row)
        {
        $date1 = $row->fy_start;
        $date2 = $row->fy_end;
        }
        $fy_start=explode("-",$date1);
        $fy_end=explode("-",$date2);

        $curr_year = $fy_start[0] ."-" .$fy_end[0];
        $prev_year = ($fy_start[0]-1) ."-" . ($fy_end[0]-1);
        $db = $CI->Payment_model->database_name();
	
	$income = new Reportlist();
	$income->init(3);
	$total = $income->total;
	$id = $income->id;
        $CI->db->select('id,name,code')->from('groups')->where('parent_id',$id);
       	$main2 =  $CI->db->get();
        foreach($main2->result() as $row)
       	{
        $ledg_id = $row->id;
        $result1 = $CI->payment_model->income_xml_data($ledg_id);
	$acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
        $file_name="Income"."-".$current_active_account."-".$prev_year.".xml";
        $tt=$acctpath."/".$file_name;
       	if(file_exists($tt)){
        $doc = new DomDocument();
      	$doc->formatOutput = true;
        $doc->load($tt);
        $xpath = new DomXPath($doc);
     	$xpath->query("/Income/Income_Name/Group_Name");
        $xpath->query("/Income/Income_Name/Amount");
        $xpath->query("/Income/Income_Name/Group_ID");

     	$incomenode1 = $xpath->query("/Income/Income_Name/Group_Name");
        $incomenode2 = $xpath->query("/Income/Income_Name/Amount");
       	$incomenode3 = $xpath->query("/Income/Income_Name/Group_ID");
        $incomelist1 = @$incomenode1->item($i)->nodeValue;
        $incomelist2 = @$incomenode2->item($i)->nodeValue;
      	$incomelist3 = @$incomenode3->item($i)->nodeValue;
        }
        $type_total = $type_total + $incomelist2;
	$i++;
	}
	$old_income_total = $type_total;
	return $old_income_total;
}

function expense_total($id,$type,$database)
{
	$CI = & get_instance();
	//Get current label.
        $current_active_account = $CI->session->userdata('active_account');
        $expenselist2 = "";
        $type_total = "";
        $i = 0;
        $id = "";
        $total = "";
        $CI->db->from('settings');
        $detail = $CI->db->get();
        foreach ($detail->result() as $row)
        {
        $date1 = $row->fy_start;
        $date2 = $row->fy_end;
        }
	$fy_start=explode("-",$date1);
        $fy_end=explode("-",$date2);

        $curr_year = $fy_start[0] ."-" .$fy_end[0];
        $prev_year = ($fy_start[0]-1) ."-" . ($fy_end[0]-1);
        $db = $CI->Payment_model->database_name();


        $expense = new Reportlist();
        $expense->init(4);
        $expense_total = $expense->total;
        $id = $expense->id;
        $CI->db->select('id,name,code')->from('groups')->where('parent_id',$id);
        $main2 =  $CI->db->get();
        foreach($main2->result() as $row)
        {
        $ledg_id = $row->id;
	$result = $CI->payment_model-> get_all_expense_detail($id);
        $acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
        $file_name="Expense"."-".$current_active_account."-".$prev_year.".xml";
        $tt=$acctpath."/".$file_name;
        if(file_exists($tt)){
        $doc = new DomDocument();
        $doc->formatOutput = true;
        $doc->load($tt);
        $xpath = new DomXPath($doc);
        $xpath->query("/Expense/Expense_Name/Group_Name");
        $xpath->query("/Expense/Expense_Name/Amount");
        $xpath->query("/Expense/Expense_Name/Group_ID");

        $expensenode1 = $xpath->query("/Expense/Expense_Name/Group_Name");
        $expensenode2 = $xpath->query("/Expense/Expense_Name/Amount");
        $expensenode3 = $xpath->query("/Expense/Expense_Name/Group_ID");
        $expenselist1 = @$expensenode1->item($i)->nodeValue;
        $expenselist2 = @$expensenode2->item($i)->nodeValue;
        $expenselist3 = @$expensenode3->item($i)->nodeValue;
        }
        $type_total = $type_total + $expenselist2;
        $i++;
	}
	$old_expensetotal = $type_total;
	return $old_expensetotal;
}
        function countDigits()
        {
                //preg_match_all( "/[0-9]/", $str, $arr );
                $search = '1234567890';
                $count = strlen($this->code) - strlen(str_replace(str_split($search), '', $this->code));
                return $count;
        }


//Method for display MHRD format of income ad expenditure @megha pal
    function income_exp_mhrd($id ,$type,$database)
    {
        $total1="";
        $total2 = "";
        $total3 ="";
        $total4 ="";
        $c ="16";
        $counter=10;
        $sum = "0";
	$sum1 =0;
	$sum2 =0;
	$sum3 = 0;
	$sum4 =0;
	$sum5 =0;
	$sum6 =0;
	$total07 = 0;
	$total09 = 0;
	$type_total = 0;
	$income_total = "";
        $expense_total = "";
        $transit=0;
	$i=0;
	$incomelist2 = "";
	$expenselist2 = "";
	$CI =& get_instance();
	$CI->db->from('settings');
        $detail = $CI->db->get();
        foreach ($detail->result() as $row)
        {
            $date_1 = $row->fy_start;
            $date_2 = $row->fy_end;
        }
        $fy_start=explode("-",$date_1);
        $fy_end=explode("-",$date_2);

        $curr_year = $fy_start[0] ."-" .$fy_end[0];
	$prev_year = ($fy_start[0]-1) ."-" . ($fy_end[0]-1);
	//Get current label.
	$current_active_account = $CI->session->userdata('active_account');
        $CI->db->select('name,code,id')->from('groups')->where('parent_id',$id);
        $main = $CI->db->get();
        $main_result= $main->result();
	$income1 = new Reportlist();
        $income1->init('26');
        $income_total = $income1->total;
        $db = $CI->Payment_model->database_name();
	$transit= $CI->Ledger_model->get_ledger_balance1('123');
        $transit = 0-$transit;
        foreach($main_result as $row)
        {
            	$name = $row->name;
            	$code =$row->code;
            	$ledg_id = $row->id;
		if($type == 'view'){
            	echo "<tr class=\"tr-group\">";
            	echo "<td class=\"td-group\">";
            	echo "&nbsp;" .  $name;
            	echo "</td>";
            	echo "<td class=\"td-group\">";
		}
            	if($id == 3 && $type == "view" && $database == "NULL" )
		{ 
                $counter++;
                echo "&nbsp;" . anchor_popup('report2/schedule/' . $code . '/' . $counter, $counter, array('title' => $name, 'style' => 'color:#000000'));
        	echo"</td>";
                $result = $CI->Payment_model->get_all_income_detail($ledg_id);
		$my_values = explode('#',$result);
                $total1 =$my_values[0];
                $total2 =$my_values[1];
                $total3 =$my_values[2];
                $total4 =$my_values[3];
		$total5 =$my_values[4];
                $total6 =$my_values[5];
                $total7 =$my_values[6];
		$total9=$my_values[7];

                $income = new Reportlist();
                $income->init($ledg_id);
                $total = $income->total;

                $sum= $sum + $total;
		$total8=0;

		if($name == "Grant and Donations")
		{
			$total = (-$total) + $total4 + $total5 + $total7 + $total6;
			$total07 = $total1 +$total7 +$total07;
			echo "<td align=\"right\">".money_format('%!i', convert_cur($total4))."</td>";
			echo "<td align=\"right\">".money_format('%!i', convert_cur($total5))."</td>";
			echo "<td align=\"right\">".money_format('%!i', convert_cur($total07))."</td>";
			echo "<td align=\"right\">".money_format('%!i', convert_cur($total6))."</td>";
			echo "<td align=\"right\">" . convert_amount_dc(-$total) . "</td>";
			$sum3 = $sum3 + $total07;
			$this->total3 =$sum3;
			}elseif($name == "Other Incomes"){
				$total= (-$income_total) - $transit;
				$total09 = $total09 + $total +$total9;
				echo "<td align=\"right\">".money_format('%!i',convert_cur($total8))."</td>";
        			echo "<td align=\"right\">".money_format('%!i',convert_cur($total8))."</td>";
        			echo"<td align=\"right\">".money_format('%!i',convert_cur($total09))."</td>";
        			echo "<td align=\"right\">".money_format('%!i',convert_cur($total8))."</td>";
        			echo "<td align=\"right\">" . convert_amount_dc(-$total) . "</td>";
				$sum5 = $sum5 + $total09;
                        	$this->total5 =$sum5;
			}else{
				echo "<td align=\"right\">".money_format('%!i',convert_cur($total2))."</td>";
				echo "<td align=\"right\">".money_format('%!i',convert_cur($total8))."</td>";
				echo "<td align=\"right\">".money_format('%!i',convert_cur($total1))."</td>";
				echo "<td align=\"right\">".money_format('%!i',convert_cur($total3))."</td>";
				echo "<td align=\"right\">" . convert_amount_dc($total) . "</td>";
				$sum6 = $sum6 + $total1;
				$this->total6 = $sum6;
			}
				$sum1 = $sum1 + $total4 + $total8 + $total2;
				$sum2 = $sum2 + $total5 + $total8 + $total8;
				$sum4 = $sum4 + $total6 +$total8 + $total3;
				$this->total1 = $sum1;
        			$this->total2 = $sum2;
        			$this->total4 = $sum4;
				$acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
				$file_name="Income"."-".$current_active_account."-".$prev_year.".xml";
				$tt=$acctpath."/".$file_name;
				if(file_exists($tt)){
        				$doc = new DomDocument();
        				$doc->formatOutput = true;
        				$doc->load($tt);
        				$xpath = new DomXPath($doc);
        				$xpath->query("/Income/Income_Name/Group_Name");
        				$xpath->query("/Income/Income_Name/Amount");
        				$xpath->query("/Income/Income_Name/Group_ID");

        				$incomenode1 = $xpath->query("/Income/Income_Name/Group_Name");
        				$incomenode2 = $xpath->query("/Income/Income_Name/Amount");
        				$incomenode3 = $xpath->query("/Income/Income_Name/Group_ID");
        				$incomelist1 = @$incomenode1->item($i)->nodeValue;
        				$incomelist2 = @$incomenode2->item($i)->nodeValue;
        				$incomelist3 = @$incomenode3->item($i)->nodeValue;
				}
				if ($incomelist2 > 0){
				$incomelist2 = 0 -  $incomelist2;
				}	
                		$type_total = $type_total + $incomelist2;
				$i++;
				if($incomelist2 == 0)	
		               		echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
				else
					echo "<td align=\"right\">" . convert_amount_dc($incomelist2) . "</td>";
			}			
			
			if(($type == "CF") && ($id == 3) && ($database != "NULL"))
			{	
				$t_name = "Income";
				$total = 0;
                        	$data = $CI->Payment_model->xml_creation($t_name,$ledg_id,$database,$name,$curr_year,$total);
			}
                
		if($id == 4 && $type == "view" && $database == "NULL")
		{
                	$c++;
                	echo "&nbsp;" . anchor_popup('report2/schedule/' . $code . '/' . $c, $c, array('title' => $name, 'style' => 'color:#000000'));
                	echo"</td>";
			$CI->db->select('code')->from('groups')->where('id', $ledg_id);
			$code_result= $CI->db->get();
			$code = $code_result->row();
			$CI->db->select('id')->from('ledgers');
			foreach( $code as $code1){
	       			$CI->db->like('code', $code1);
	 		}		
    			$query_result =$CI->db->get();
    			$no_row = $query_result->num_rows(); 
                	if($no_row != 0)
			{
                    		$result = $CI->Payment_model->get_all_expense_detail($ledg_id);
                    		$my_values = explode('#',$result);
                    		$total1 =$my_values[0];
                    		$total2 =$my_values[1];
                    		$total3 =$my_values[2];
                    		$total4 =$my_values[3];
                    		$total="";
                    		$expense1="";
                    		$expense = new Reportlist();
                    		$expense->init($ledg_id);
                    		$total = $expense->total;
                    		$sum= $sum + $total;
				$sum1 = $sum1 + $total1;
				$sum2 = $sum2 + $total2;
				$sum3 = $sum3 + $total3;
				$sum4 = $sum4 + $total4;
				$this->total1 = $sum1;
				$this->total2 = $sum2;
				$this->total3 = $sum3;
                    		$this->total4 = $sum4;
                		echo "<td align=\"right\">".money_format('%!i', convert_cur($total1))."</td>";
            			echo "<td align=\"right\">".money_format('%!i', convert_cur($total3))."</td>";
           			echo "<td align=\"right\">".money_format('%!i', convert_cur($total2))."</td>";
            			echo "<td align=\"right\">".money_format('%!i', convert_cur($total4))."</td>";
            			echo "<td align=\"right\">" . convert_amount_dc($total) . "</td>";
			}else{
                    		echo "<td align=\"right\">".money_format('%!i', convert_cur(0))."</td>";
                    		echo "<td align=\"right\">".money_format('%!i', convert_cur(0))."</td>";
                    		echo "<td align=\"right\">".money_format('%!i', convert_cur(0))."</td>";
                    		echo "<td align=\"right\">".money_format('%!i', convert_cur(0))."</td>";
                    		echo "<td align=\"right\">".convert_amount_dc(0)."</td>";
                	}

                	$acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
                	$file_name="Expense"."-".$current_active_account."-".$prev_year.".xml";
                	$tt=$acctpath."/".$file_name;
                	if(file_exists($tt))
                	{
                    		$doc = new DomDocument();
                    		$doc->formatOutput = true;
                    		$doc->load($tt);
                    		$xpath = new DomXPath($doc);
				$xpath->query("/Expense/Expense_Name/Group_Name");
				$xpath->query("/Expense/Expense_Name/Amount");
				$xpath->query("/Expense/Expense_Name/Group_ID");
				$expensenode1 = $xpath->query("/Expense/Expense_Name/Group_Name");
				$expensenode2 = $xpath->query("/Expense/Expense_Name/Amount");
				$expensenode3 = $xpath->query("/Expense/Expense_Name/Group_ID");
				$expenselist1 = @$expensenode1->item($i)->nodeValue;
				$expenselist2 = @$expensenode2->item($i)->nodeValue;
				$expenselist3 = @$expensenode3->item($i)->nodeValue;
                	}

				$type_total = $type_total + $expenselist2;
        	        	$i++;
					
			if($expenselist2 == 0)
                    	echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
			else
			echo "<td align=\"right\">" . convert_amount_dc($expenselist2) . "</td>";		
            }

	if($type == "CF" && $id == 4 && $database != "NULL")
	{
		$t_name = "Expense";
		$total = "";
                $data = $CI->Payment_model->xml_creation($t_name,$ledg_id,$database,$name,$curr_year,$total);					
	}
	}
		$this->prev_total = $type_total;
        return $sum;
    }
	


	/**
	 * Supplementary method for calling method: schedule().	
	 * @author Priyanka Rawat <rpriyanka12@ymail.com>
	 */
function callToOpBalance($year, $name)
{
	$credit_total = 0;
        $debit_total = 0;
	$old_credit_total = 0;
	$old_debit_total = 0;
	$op_balance = 0;
	$old_op_balance = 0;
	$total = 0;
	$total2 = 0;

	if($year == 'new')
	{
		if( $name == 'schedule')
		{
                list($credit_total, $debit_total, $op_balance) = $this->calculate_op_balance($year, $name);
		$this->cr_total = $this->cr_total + $credit_total;
		$this->dr_total = $this->dr_total + $debit_total;
		$this->opening_balance = $this->opening_balance + $op_balance;
		}
		else
		{
		list($total, $op_balance) = $this->calculate_op_balance($year, $name);
	        $this->total = $this->total + $total;
        	$this->opening_balance = $this->opening_balance + $op_balance;
		}
	}
	elseif($year == 'old')
	{ 
		if( $name == 'schedule')
		{
	        list($old_credit_total, $old_debit_total, $old_op_balance) = $this->calculate_op_balance($year, $name);
		$this->old_cr_total = $this->old_cr_total + $old_credit_total;
		$this->old_dr_total = $this->old_dr_total + $old_debit_total;
		$this->opening_balance_prev = $this->opening_balance_prev + $old_op_balance;
		}
		else
		{
                list($total2, $old_op_balance) = $this->calculate_op_balance($year, $name);
	        $this->total2 = $this->total2 + $total2;
        	$this->opening_balance = $this->opening_balance + $old_op_balance;
                }
	}//elseif

}//main

function calculate_op_balance($year, $name)
{
	static $credit_total = 0;
	static $debit_total = 0;
	static $old_credit_total = 0;
        static $old_debit_total = 0;
        static $total = 0;
        static $total2 = 0;
	static $op_balance = 0;
        static $old_op_balance = 0;
	if($year == null)
	{
	$credit_total = null;
	$debit_total = null;
        $old_credit_total = null;
        $old_debit_total = null;
	$op_balance = null;	
        $old_op_balance = null;
	$total = null;
	$total2 = null;
	}
	else
	{
	foreach ($this->children_groups as $id => $data)
	{
        $this->counter++;
	$data->calculate_op_balance($year, $name);
      	$this->counter--;
        }
	if (count($this->children_ledgers) > 0)
     	{
        foreach ($this->children_ledgers as $id => $data)
	{
		//Get opening balance
        	$CI =& get_instance();
		if($year == 'new')	
		{
			list($opBal, $optype) = $CI->Ledger_model->get_op_balance($data['id']);
        		$op_balance = $op_balance + $opBal;
			if($name == 'schedule')
			{
			if($optype == 'C')
			$credit_total = $credit_total + $opBal;
			elseif($optype == 'D')
                	$debit_total = $debit_total + $opBal;
			}
			else
			{
			if($optype == 'C')
                        $total = $total - $opBal;
	                elseif($optype == 'D')
                	$total = $total + $opBal;
			}
	
		}//if
			elseif($year == 'old')
			{
				list($opBal, $optype) = $CI->Ledger_model->get_prev_year_op_balance($data['id']);
                                $old_op_balance = $old_op_balance + $opBal;
				if($name == 'schedule')
				{
		                if($optype == 'C')
        	        	$old_credit_total = $old_credit_total + $opBal;
                	   	elseif($optype == 'D')
                        	$old_debit_total = $old_debit_total + $opBal;	
				}	
				else
				{
				if($optype == 'C')
                                $total2 = $total2 - $opBal;
                                elseif($optype == 'D')
		                $total2 = $total2 + $opBal;
				}
                       }//elseif
		}
	}
	}//else null

	if($year == 'new')
	{
	if($name == 'schedule')
	return array($credit_total, $debit_total, $op_balance);
	else
	return array($total, $op_balance);
	}
	elseif($year == 'old')
	{
	if($name == 'schedule')
	return array($old_credit_total, $old_debit_total, $old_op_balance);
	else
	return array($total2, $old_op_balance);
	}
	}

function startsWith($str1, $str2)
{
	return !strncmp($str1, $str2, strlen($str2));
}

function callToSchedule($c = 0,$id,$code,$count,$type,$database)
{
	$CI =& get_instance();
        $db = $CI->payment_model->database_name();
	
	$credit_total = 0;
	$debit_total = 0;
	$old_credit_total = 0;
        $old_debit_total = 0;
	list($credit_total, $debit_total, $old_credit_total, $old_debit_total) = $this->schedule($c,$id,$code,$count,$type,$database);
	$this->cr_total = $this->cr_total + $credit_total;
	$this->dr_total = $this->dr_total + $debit_total;
	$this->old_cr_total = $this->old_cr_total + $old_credit_total;
        $this->old_dr_total = $this->old_dr_total + $old_debit_total;
}

function callToOldSchedule($c = 0)
{
	$old_credit_total = 0;
        $old_debit_total = 0;
        list($old_credit_total, $old_debit_total) = $this->previous_year_data($c);
        $this->old_cr_total = $this->old_cr_total + $old_credit_total;
        $this->old_dr_total = $this->old_dr_total + $old_debit_total;
}

/* Displays schedule */

/////////////////////@kanchan
function schedule($c = 1,$id,$code,$count,$type,$database)
{//main
	
	$sum = 0;
	$sum1 = 0;
	$schedulelist2 = "";
        $schedulelist3 = "";
	$schedulelist4 = "";
        $prev_sum = 0;
	$prev_sum1 = 0;
	$i = 0;
	static $credit_total = 0.00;
	static $debit_total = 0.00;
	static $old_credit_total = 0.00;
	static $old_debit_total = 0.00;

	$CI = & get_instance();
        $counter = 1;
	//Get current label.
        $current_active_account = $CI->session->userdata('active_account');
        $CI->db->from('settings');
        $detail = $CI->db->get();
        foreach ($detail->result() as $row)
        {
        $date1 = $row->fy_start;
        $date2 = $row->fy_end;
        }
        $fy_start=explode("-",$date1);
        $fy_end=explode("-",$date2);
        $curr_year = $fy_start[0] ."-" .$fy_end[0];
        $prev_year = ($fy_start[0]-1) ."-" . ($fy_end[0]-1);
        $db = $CI->payment_model->database_name();
		
	if($c == null)
	{
		$credit_total = null;
                $debit_total = null;
		$old_credit_total = null;
                $old_debit_total = null;
	}else{

                $len = $this->countDigits();
		
	if($this->id != 0  && $len > 6)
        {	
        	echo "<tr class=\"tr-group\">";
                echo "<td class=\"td-group\">";
                echo "&nbsp;" . "<b>" . $this->name . "</b>";
                echo "</td>";
                echo "</tr>";
        }
        foreach ($this->children_groups as $id => $data)
        {
        	$this->counter++;
               	$data->schedule($this->counter,$id,$code,$count,$type,$database);
                $this->counter--;
        }
        if (count($this->children_ledgers) > 0)
        {//1
        foreach ($this->children_ledgers as $id => $data)
        {//2
		$c_total = 0.00;
		$d_total = 0.00;
		$old_c_total = 0.00;
                $old_d_total = 0.00;
		$ledger_id = $data['id'];
		$ledger_name = $data['name'];

		$ledger_name = $data['name'];
		$ledger_id = $data['id'];
		$object  = new Reportlist();
                $diff = $object->income_expense_diff();

		if(($type == 'view') && ($database == 'NULL'))
		{
		echo "<tr class=\"tr-ledger\">";
	        echo "<td class=\"td-ledger\">";
        	echo $this->print_space($this->counter);
        	echo "&nbsp;" . "<b>" . $data['name'] . "<b>";
                echo "</td>";
		$result_1 = $CI->investment_model->schedule_1($ledger_id);
		$val = explode('#', $result_1);
		if($ledger_id == '2')
		{
		$c_total1 = $val[0];
		$d_total1 = $val[1]; 
		
		if($diff > 0)
		$c_total = $diff+$c_total1;	
		else
		$d_total = -($diff+$d_total1);

		$sum = $sum+$c_total;
		$sum1 = $sum1+$d_total;
		}
		else 
		{
		$c_total = $val[0];
                $sum = $sum+$c_total;
                $d_total = $val[1]; 
                $sum1 = $sum1+$d_total;
		}

		echo "<td align=\"right\">" . convert_amount_dc(+$d_total) . "</td>";
                echo "<td align=\"right\">" . convert_amount_dc(-$c_total) . "</td>";

		/* code for reading previous year data from xml */
           	$acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
           	$file_name="schedule".$count."-".$current_active_account."-".$prev_year.".xml";
           	$tt=$acctpath."/".$file_name;
           	if(file_exists($tt))
           	{
           		$doc = new DomDocument();
           		$doc->formatOutput = true;
           		$doc->load($tt);
           		$xpath = new DomXPath($doc);
           		$schedule1 = "schedule".$count;
           		$schedule2 = $schedule1."_Name";

           		$xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
           		$xpath->query("/".$schedule1."/".$schedule2."/Dr_Amount");
           		$xpath->query("/".$schedule1."/".$schedule2."/Cr_Amount");
           		$xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
           		$schedulenode1 = $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
           		$schedulenode2 = $xpath->query("/".$schedule1."/".$schedule2."/Dr_Amount");
           		$schedulenode3 = $xpath->query("/".$schedule1."/".$schedule2."/Cr_Amount");
           		$schedulenode4 = $xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
           		$schedulelist1 = @$schedulenode1->item($i)->nodeValue;
           		$schedulelist2 = @$schedulenode2->item($i)->nodeValue;
           		$schedulelist3 = @$schedulenode3->item($i)->nodeValue;
           		$schedulelist4 = @$schedulenode4->item($i)->nodeValue;
           	}
			$i++;
			if($schedulelist4 == "2")
			{
				$old_income_total = -$this->income_total(3,$type,$database);
                        	$old_expense_total = $this->expense_total(4,$type,$database);
                        	$old_diff = float_ops($old_income_total, $old_expense_total, '-');
				if($old_diff > 0)
	                	$schedulelist3 = $schedulelist3+$old_diff;
        	        	else
                		$schedulelist2 = $schedulelist2+$old_diff;

				$prev_sum = $prev_sum+$schedulelist3;
				$prev_sum1 = $prev_sum1+$schedulelist2;
			}
			elseif($schedulelist4 != "2")
			{
           			$prev_sum = $prev_sum+$schedulelist3;
           			$prev_sum1 = $prev_sum1+$schedulelist2;
			}
           			if($schedulelist2 == 0)
           			echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
           			else
           			echo "<td align=\"right\">" . convert_amount_dc($schedulelist2) . "</td>";
           			if($schedulelist3 == 0)
           			echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
           			else
           			echo "<td align=\"right\">" . convert_amount_dc(-$schedulelist3) . "</td>";
		}//ifview
		if(($type == 'CF') && ($database != 'NULL'))
		{
			$t_name = "schedule".$count;
			$CI = & get_instance();
			$result_1 = $CI->investment_model->schedule_1($ledger_id);
			$val = explode('#', $result_1);
	                $c_total = $val[0];
                	$d_total = $val[1];
			$CI->load->library('balancesheet');
			$data = $CI->balancesheet->xml_creation($t_name,$ledger_id,$database,$ledger_name,$curr_year,$d_total,$c_total);	
		}
        	}//else for null
		}//foreach
		}//if
	$credit_total = $sum;
	$debit_total = $sum1;
	$old_credit_total = $prev_sum;
	$old_debit_total = $prev_sum1;

	return array($credit_total,$debit_total,$old_credit_total,$old_debit_total);

}//main

///////////////////////////



		/*		$this->getPreviousYearDetails();
                                if($this->prevYearDB != "" ){//3
                                        /* database connectivity for getting previous year opening balance */
               /*                         $con = mysql_connect($this->host_name, $this->db_username, $this->db_password);
                                        $op_balance = array();
                                        if($con){//4
                                                $value = mysql_select_db($this->prevYearDB, $con);
                                                $id = mysql_real_escape_string($data['id']);
                                                $cl = "select entry_id, id, amount, dc from entry_items where ledger_id = '$id'";
                                                $val = mysql_query($cl);
                                                if($val != ''){//5
                                                        while($row = mysql_fetch_assoc($val))
                                                        {//6
                                                                if($row != null){//7
                                                                                                        if($row['dc'] == 'C'){//12
                                                                                                                $old_credit_total = $old_credit_total + $row['amount'];
                                                                                                                $old_c_total = $old_c_total + $row['amount'];
                                                                                                        }//12
                                                                                                        else{//13
                                                                                                                $old_debit_total = $old_debit_total + $row['amount'];
                                                                                                                $old_d_total = $old_d_total + $row['amount'];
                                                                                                        }//13
                                                                }//7
                                                        }//6
						}//5
					}//4
				}//3 */
////////////////////////////////////////////////////
function previous_year_data($c = 0,$id,$code,$type,$database)
{
	$narration = '';
	$amount = 0;
	static $old_credit_total = 0;
	static $old_debit_total = 0;

	$c_total = 0;
	$d_total = 0;

	if($c == null)
       	{
        $old_credit_total = null;
        $old_debit_total = null;
      	}else{

        foreach ($this->children_groups as $id => $data)
        {
        	$this->counter++;
		$data->previous_year_data($this->counter,$id,$code,$type,$database);
                $this->counter--;
        }
        if (count($this->children_ledgers) > 0)
        {//1
        foreach ($this->children_ledgers as $id => $data)
        {//2
	$c_total = 0.00;
	$d_total = 0.00;			
	$this->getPreviousYearDetails();
        if($this->prevYearDB != "" ){//3
        /* database connectivity for getting previous year opening balance */
	$con = mysqli_connect($this->host_name, $this->db_username, $this->db_password);
	$op_balance = array();
       	if($con){//4
        $value = mysqli_select_db($con,$this->prevYearDB);
        $id = mysqli_real_escape_string($data['id']);
	$cl = "select entry_id, id, amount, dc from entry_items where ledger_id = '$id'";
   	$val = mysqli_query($con,$cl);
	if($val != ''){//5
      	while($row = mysqli_fetch_assoc($val))
        {//6
		if($row != null){//7
	        if($row['dc'] == 'C'){//12
		$old_credit_total = $old_credit_total + $row['amount'];
		$c_total = $c_total + $row['amount'];
		}//12
                else{//13
		$old_debit_total = $old_debit_total + $row['amount'];
		$d_total = $d_total + $row['amount'];
                }//13
                              }//7
	}//6
	if($d_total != '' || $c_total != ''){								
	echo "<tr>";
	echo "<td>";
        echo "";
        echo "</td>";

	echo "<td>";
	echo "";
	echo "</td>";

	echo "<td>";
	echo "";
	echo "</td>";

	echo "<td align=\"right\">";
        echo convert_amount_dc($d_total);
        echo "</td>";

       	echo "<td align=\"right\">";
        echo convert_amount_dc(-$c_total);
        echo "</td>";
                                                                        
	echo "</tr>";
        }       
		
        			}//5
       			}//4
		}//3
	}//2
	}//1
	}//else null
	return array($old_credit_total, $old_debit_total);
        }//method

////////////////@kanchan
function callschedule($id,$code,$count,$type,$database)
{
	$CI =& get_instance();
        $counter = 1;
        $CI->db->from('settings');
        $detail = $CI->db->get();
        foreach ($detail->result() as $row)
        {
        $date1 = $row->fy_start;
        $date2 = $row->fy_end;
        }
        $fy_start=explode("-",$date1);
        $fy_end=explode("-",$date2);
        $curr_year = $fy_start[0] ."-" .$fy_end[0];
        $prev_year = ($fy_start[0]-1) ."-" . ($fy_end[0]-1);
        $db = $CI->payment_model->database_name();

	$liability = new Reportlist();
        $liability->init($id);
	$liability->callToSchedule(1,$id,$code,$count,$type,$database);
        $cr_total = $liability->cr_total;
        $dr_total = $liability->dr_total;
        $old_cr_total = $liability->old_cr_total;
        $old_dr_total = $liability->old_dr_total;

	$this->dr_total = $dr_total;
        $this->cr_total = $cr_total;
        $this->old_dr_total = $old_dr_total;
        $this->old_cr_total = $old_cr_total;
}

////////////////////////////////////////////////////
	/* Display chart of accounts view */
	function account_st_main($c = 0)
	{
		$this->counter = $c;
		if ($this->id != 0)
		{
			echo "<tr class=\"tr-group\">";
			echo "<td class=\"td-group\">";
			//$this->messages->add('Value of status field==>');
			//echo $this->status;
			//echo "Test";	
			if ($this->id <= 4)
				{echo "&nbsp;<strong>" .  $this->code . "</strong>";
				//echo "&nbsp;<strong>" .  $this->status . "</strong>";
				}
			else
				{echo "&nbsp;" .  $this->code;
				//echo "&nbsp;<strong>" .  $this->status . "</strong>";
				}
			echo "</td>";
			echo "<td class=\"td-group\">";
			echo $this->print_space($this->counter);
			if ($this->id <=4)
				echo "&nbsp;<strong>" .  $this->name. "</strong>";
			else
				echo "&nbsp;" .  $this->name;
			echo "</td>";
			echo "<td>Group Account</td>";
			echo "<td>-</td>";
			echo "<td>-</td>";
			if ($this->id <= 4)
			{
				echo "<td class=\"td-actions\"></tr>";
			} else {
				echo "<td class=\"td-actions\">" . anchor('group/edit/' . $this->id , "Edit", array('title' => 'Edit Group', 'class' => 'red-link'));
				$id1=$this->id;
				$status1=$this->status;
				if (  check_access('administer'))
				{
				if($this->status == 0)				
					echo " &nbsp;" . anchor('group/enabledisable/' . $id1 . "/" .  $status1, 'Hide',  array('title' => 'Edit Group', 'class' => 'red-link')) ;
				else
					echo " &nbsp;" . anchor('group/enabledisable/' . $id1 . "/" .  $status1, 'Unhide', array('title' => 'Edit Group', 'class' => 'red-link')) ;
				}
				echo " &nbsp;" . anchor('group/delete/' . $this->id, img(array('src' => asset_url() . "assets/bgas/images/icons/delete.png", 'border' => '0', 'alt' => 'Delete group')), array('class' => "confirmClick", 'title' => "Delete Group")) . "</td>";
			}
			echo "</tr>";
		}
		foreach ($this->children_groups as $id => $data)
		{
			"$this->counter++ ";
			$data->account_st_main($this->counter);
			$this->counter--;
		}
		//} 
		if (count($this->children_ledgers) > 0)
		{
			$this->counter++;
			foreach ($this->children_ledgers as $id => $data)
			{
				echo "<tr class=\"tr-ledger\">";
				echo "<td class=\"td-ledger\">";
				echo "&nbsp;" . anchor('report/ledgerst/' . $data['id'], $data['code'], array('title' => $data['code'] . ' Ledger Statement', 'style' => 'color:#000000'));
				echo "</td>";
				echo "<td class=\"td-ledger\">";
				echo $this->print_space($this->counter);
				echo "&nbsp;" . anchor('report/ledgerst/' . $data['id'], $data['name'], array('title' => $data['name'] . ' Ledger Statement', 'style' => 'color:#000000'));
				echo "</td>";
				echo "<td>Ledger Account</td>";
				echo "<td>" . convert_opening($data['opbalance'], $data['optype']) . "</td>";
				echo "<td>" . convert_amount_dc($data['total']) . "</td>";
				echo "<td class=\"td-actions\">" . anchor('ledger/edit/' . $data['id'], 'Edit', array('title' => "Edit Ledger", 'class' => 'red-link'));
				echo " &nbsp;" . anchor('ledger/delete/' . $data['id'], img(array('src' => asset_url() . "assets/bgas/images/icons/delete.png", 'border' => '0', 'alt' => 'Delete Ledger')), array('class' => "confirmClick", 'title' => "Delete Ledger")) . "</td>";
				echo "</tr>";
			}
			$this->counter--;
		}
	}

	function print_space($count)
	{
		$html = "";
		for ($i = 1; $i <= $count; $i++)
		{
			$html .= "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		}
		return $html;
	}
	
	/* Build a array of groups and ledgers */
	function build_array()
	{
		$item = array(
			'id' => $this->id,
			'name' => $this->name,
			'type' => "G",
			'total' => $this->total,
			'child_groups' => array(),
			'child_ledgers' => array(),
			'depth' => self::$temp_max,
		);
		$local_counter = 0;
		if (count($this->children_groups) > 0)
		{
			self::$temp_max++;
			if (self::$temp_max > self::$max_depth)
				self::$max_depth = self::$temp_max;
			foreach ($this->children_groups as $id => $data)
			{
				$item['child_groups'][$local_counter] = $data->build_array();
				$local_counter++;
			}
			self::$temp_max--;
		}
		$local_counter = 0;
		if (count($this->children_ledgers) > 0)
		{
			self::$temp_max++;
			foreach ($this->children_ledgers as $id => $data)
			{
				$item['child_ledgers'][$local_counter] = array(
					'id' => $data['id'],
					'name' => $data['name'],
					'type' => "L",
					'total' => $data['total'],
					'child_groups' => array(),
					'child_ledgers' => array(),
					'depth' => self::$temp_max,
				);
				$local_counter++;
			}
			self::$temp_max--;
		}
		return $item;
	}

	/* Show array of groups and ledgers as created by build_array() method */
	function show_array($data)
	{
		echo "<tr>";
		echo "<td>";
		echo $this->print_space($data['depth']);
		echo $data['depth'] . "-";
		echo $data['id'];
		echo $data['name'];
		echo $data['type'];
		echo $data['total'];
		if ($data['child_ledgers'])
		{
			foreach ($data['child_ledgers'] as $id => $ledger_data)
			{
				$this->show_array($ledger_data);
			}
		}
		if ($data['child_groups'])
		{
			foreach ($data['child_groups'] as $id => $group_data)
			{
				$this->show_array($group_data);
			}
		}
		echo "</td>";
		echo "</tr>";
	}

	function to_csv($data)
	{
		$counter = 0;
		while ($counter < $data['depth'])
		{
			self::$csv_data[self::$csv_row][$counter] = "";
			$counter++;
		}

		self::$csv_data[self::$csv_row][$counter] = $data['name'];
		$counter++;

		while ($counter < self::$max_depth + 3)
		{
			self::$csv_data[self::$csv_row][$counter] = "";
			$counter++;
		}
		self::$csv_data[self::$csv_row][$counter] = $data['type'];
		$counter++;

		if ($data['total'] == 0)
		{
			self::$csv_data[self::$csv_row][$counter] = "";
			$counter++;
			self::$csv_data[self::$csv_row][$counter] = "";
		} else if ($data['total'] < 0) {
			self::$csv_data[self::$csv_row][$counter] = "Cr";
			$counter++;
			self::$csv_data[self::$csv_row][$counter] = -$data['total'];
		} else {
			self::$csv_data[self::$csv_row][$counter] = "Dr";
			$counter++;
			self::$csv_data[self::$csv_row][$counter] = $data['total'];
		}

		if ($data['child_ledgers'])
		{
			foreach ($data['child_ledgers'] as $id => $ledger_data)
			{
				self::$csv_row++;
				$this->to_csv($ledger_data);
			}
		}
		if ($data['child_groups'])
		{
			foreach ($data['child_groups'] as $id => $group_data)
			{
				self::$csv_row++;
				$this->to_csv($group_data);
			}
		}
	}

	public static function get_csv()
	{
		return self::$csv_data;
	}
	
	public static function add_blank_csv()
	{
		self::$csv_row++;
		self::$csv_data[self::$csv_row] = array("", "");
		self::$csv_row++;
		self::$csv_data[self::$csv_row] = array("", "");
		return;
	}
	
	public static function add_row_csv($row = array(""))
	{
		self::$csv_row++;
		self::$csv_data[self::$csv_row] = $row;
		return;
	}

	public static function reset_max_depth()
	{
		self::$max_depth = 0;
		self::$temp_max = 0;
	}

	/*
	 * Return a array of sub ledgers with the object
	 * Used in CF ledgers of type Assets and Liabilities
	*/
	function get_ledger_ids()
	{
		$ledgers = array();
		if (count($this->children_ledgers) > 0)
		{
			foreach ($this->children_ledgers as $id => $data)
			{
				$ledgers[] = $data['id'];
			}
		}
		if (count($this->children_groups) > 0)
		{
			foreach ($this->children_groups as $id => $data)
			{
				foreach ($data->get_ledger_ids() as $row)
					$ledgers[] = $row;
			}
		}
		return $ledgers;
	}

	
	/* Display print preview of all schedules*/
        function print_all_schedules($c =0)
        {
		$check = 0;
                $this->counter = $c;
		$CI =& get_instance();
                $ledger_name = $CI->Setting_model->get_from_settings('ledger_name');
		if($this->countDigits() == 4 && $this->id != 0 && $this->code > 100){
			/*foreach($this->children_groups as $id => $data)
                	{
				if($data->countDigits() == 6)
					$check++;
                	}*/
				
			if($this->name == 'Unrestricted Funds'){
	                        $check++;
                        }else{
                                $check = 0;
                        }

			if($check == 0){
                        	//echo "&nbsp;" . anchor_popup('report/schedule/' . $this->code . '/' . $this->counter, $this->counter, array('title' => $this->name, 'style' => 'color:#000000'));
				//$this->schedule
				/* Get Balance of net income/(expenditure) for 'this' ledger head*/
				if($ledger_name == $this->name){
	                        //if($c == 2){
					$income = new Reportlist();
			                $income->init(3);
			                $expense = new Reportlist();
			                $expense->init(4);
					$income_total = -$income->total;
			                $old_income_total = -$income->total2;
			                $expense_total = $expense->total;
			                $old_expense_total = $expense->total2;
					$pandl = float_ops($income_total, $expense_total, '-');
				        $old_pandl = float_ops($old_income_total, $old_expense_total, '-');
					if ($pandl != 0 || $old_pandl !=0)
				        {
				                if($pandl > 0)
							$this->total = float_ops($this->total, -$pandl, '+');
						else
							$this->total = float_ops($this->total, $pandl, '+');
						if($old_pandl > 0)
				                        $this->total2 = float_ops($this->total2, -$old_pandl, '+');
						else
				                        $this->total2 = float_ops($this->total2, $old_pandl, '+');
					}
	                        }
			}

                        echo "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($this->total) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($this->total2) . "</td>";
                        echo "</tr>";
		}elseif($this->countDigits() == 6 && $this->id != 0 && $this->code > 100){
		
			echo "<tr>";
                        echo "<td class=\"td-group\">";
                       // echo $this->print_space($this->counter);
                        echo "&nbsp;" .  $this->name;
                        echo "</td>";

                        $CI->db->select('parent_id');
                        $CI->db->from('groups')->where('id', $this->id);
                        $groups_q = $CI->db->get();
                        $groups= $groups_q->row();
                        $parent_id = $groups->parent_id;

                        $CI->db->select('name');
                        $CI->db->from('groups')->where('id', $parent_id);
                        $groups_q = $CI->db->get();
                        $groups= $groups_q->row();
                        $name = $groups->name;

                        echo "<td class=\"td-group\">";
			if($name  == 'Unrestricted Funds'){
                        //echo "&nbsp;" . anchor_popup('report/schedule/' . $this->code, $this->counter, array('title' => $this->name, 'style' => 'color:#000000'));
			//echo "&nbsp;" . anchor_popup('report/schedule/' . $this->code . '/' . $this->counter, $this->counter, array('title' => $this->name, 'style' => 'color:#000000'));
				if($ledger_name == $this->name){
				//if($c == 2){
                                        $income = new Reportlist();
                                        $income->init(3);
                                        $expense = new Reportlist();
                                        $expense->init(4);
                                        $income_total = -$income->total;
                                        $old_income_total = -$income->total2;
                                        $expense_total = $expense->total;
                                        $old_expense_total = $expense->total2;
                                        $pandl = float_ops($income_total, $expense_total, '-');
                                        $old_pandl = float_ops($old_income_total, $old_expense_total, '-');
                                        if ($pandl != 0 || $old_pandl !=0)
                                        {
                                                if($pandl > 0)
                                                        $this->total = float_ops($this->total, -$pandl, '+');
                                                else
                                                        $this->total = float_ops($this->total, $pandl, '+');
                                                if($old_pandl > 0)
                                                        $this->total2 = float_ops($this->total2, -$old_pandl, '+');
                                                else
                                                        $this->total2 = float_ops($this->total2, $old_pandl, '+');
                                        }
                        	}
			}
                        echo "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($this->total) . "</td>";
                        echo "<td align=\"right\">" . convert_amount_dc($this->total2) . "</td>";
                        echo "</tr>";
		}

		foreach ($this->children_groups as $id => $data)
                {
			$len = $data->countDigits();
                        $this->counter = $data->new_balance_sheet($this->counter);
                        /*$len = $data->countDigits();
                        if($len == 4){
				if($check == 0)
                                	$this->counter++;
                                $this->counter = $data->new_balance_sheet($this->counter);
                        }elseif($len == 6){
				//$this->counter++;
                                $this->counter = $data->new_balance_sheet($this->counter);
				$this->counter++;
			}*/
                }

                //$this->counter = $this->counter + 1;
                return $this->counter;
	}

function get_IE_schedule($code,$type,$database,$count)
{
	$i =0;
	$sum = 0;
	$sum1 =0;
	$prev_sum = 0;
	$prev_sum1 =0;
	$schedulelist1 = "";
	$schedulelist2 ="";
	$schedulelist3 = "";
	$CI = & get_instance();
	//Get current label.
        $current_active_account = $CI->session->userdata('active_account');
    	$CI->db->from('settings');
        $detail = $CI->db->get();
        foreach ($detail->result() as $row)
        {
            $date1 = $row->fy_start;
            $date2 = $row->fy_end;
        }
        $fy_start=explode("-",$date1);
        $fy_end=explode("-",$date2);
        $curr_year = $fy_start[0] ."-" .$fy_end[0];
        $prev_year = ($fy_start[0]-1) ."-" . ($fy_end[0]-1);

	$id = $CI->ledger_model->get_group_id($code);
	$parent = $CI->ledger_model->get_group_name($id);
        $CI->db->select('name,code,id')->from('groups')->where('parent_id',$id);
	//$CI->db->select('name,code,id')->from('ledgers')->where('group_id',$id);
        $main = $CI->db->get();
        $main_result= $main->result();
	$CI->db->select('name,code,id')->from('ledgers')->where('group_id',$id);
	$ledger_detail = $CI->db->get();
	$ledger_result = $ledger_detail->result();
	$db = $CI->payment_model->database_name();
        foreach($main_result as $row)
        {
		$name = $row->name;
	        $group_id =$row->id;
		if(($type == 'view') && ($database == 'NULL'))
		{
		echo "<tr class=\"tr-group\">";
            	echo "<td class=\"td-group\">";
            	echo "&nbsp;" .  $name;
            	echo "</td>";
		$object = new Reportlist();
                $object->init($group_id);
                $total = $object->total;
		if($group_id == '127'){
	                $transit = $CI->ledger_model->get_ledger_balance1('123');
			$total = $total - $transit;
		}
			echo "<td align=\"right\">". convert_amount_dc($total). "</td>";
	            	$sum = $sum + $total;
			/* code for reading previous year data from xml @megha */
			$acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
	                $file_name="schedule".$count."-".$current_active_account."-".$prev_year.".xml";
	                $tt=$acctpath."/".$file_name;
	                if(file_exists($tt))
			{
		    		$doc = new DomDocument();
		            	$doc->formatOutput = true;
		            	$doc->load($tt);
		            	$xpath = new DomXPath($doc);
				$schedule1 = "schedule".$count;
				$schedule2 = $schedule1."_Name";
		                $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
				$xpath->query("/".$schedule1."/".$schedule2."/Amount");
				$xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
				$schedulenode1 = $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
				$schedulenode2 = $xpath->query("/".$schedule1."/".$schedule2."/Amount");
				$schedulenode3 = $xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
		                $schedulelist1 = @$schedulenode1->item($i)->nodeValue;
		                $schedulelist2 = @$schedulenode2->item($i)->nodeValue;
		           	$schedulelist3 = @$schedulenode3->item($i)->nodeValue;
			}
	                                
			$prev_sum = $prev_sum + $schedulelist2;
	            	$i++;
	            	if($schedulelist2 == 0)
	            	echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
	            	else
	            	echo "<td align=\"right\">" . convert_amount_dc($schedulelist2) . "</td>";
	   		echo "</tr>";

		if (($id == '30')||($id == '26'))
	        {
			$CI->db->select('name,code,id')->from('ledgers')->where('group_id',$group_id)->where('id !=' ,'123');
		        $sub_groups = $CI->db->get();
		        $sub_group_result = $sub_groups->result();
		        foreach($sub_group_result as $row3)
		        {
		        	$name = $row3->name;
		                $sub_g_id = $row3->id;
		                echo "<tr class=\"tr-ledger\">";
		                echo "<td class=\"td-ledger\">";
		                echo "&nbsp;&nbsp;&nbsp;&nbsp" .  $name;
		                echo "</td>";
		                $total = $CI->ledger_model->get_ledger_balance1($sub_g_id);
		                echo "<td align=\"right\">" . convert_amount_dc($total) . "</td>";
		                $acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
		    		$file_name="schedule".$count.$db.$prev_year.".xml";
	            		$tt=$acctpath."/".$file_name;
	            		if(file_exists($tt))
	            		{
	                		$doc = new DomDocument();
	                		$doc->formatOutput = true;
	                		$doc->load($tt);
	                		$xpath = new DomXPath($doc);
	                		$schedule1 = "schedule".$count;
	                		$schedule2 = $schedule1."_Name";
	                		$xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
	                		$xpath->query("/".$schedule1."/".$schedule2."/Amount");
	                		$xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
	                		$schedulenode1 = $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
	                		$schedulenode2 = $xpath->query("/".$schedule1."/".$schedule2."/Amount");
	                		$schedulenode3 = $xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
	                		$schedulelist1 = @$schedulenode1->item($i)->nodeValue;
	                		$schedulelist2 = @$schedulenode2->item($i)->nodeValue;
	                		$schedulelist3 = @$schedulenode3->item($i)->nodeValue;
	                    	}
				$i++;
				if($schedulelist2 == 0)
	                		echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
	                	else
	                		echo "<td align=\"right\">" . convert_amount_dc($schedulelist2) . "</td>";
	                	echo "</tr>";
	                }//for each sub group result
		}//if id 30 and id 26
	      	}

		if(($type == 'CF')&&($database != 'NULL'))
		{
			$t_name = "schedule".$count;
			$object = new Reportlist();
                	$object->init($group_id);
                	$total = $object->total;
			if($group_id == '127')
			{
                 		$transit = $CI->ledger_model->get_ledger_balance('123');
                    		$total = $total - $transit;
                	}
                		$data = $CI->Payment_model->xml_creation($t_name,$group_id,$database,$name,$curr_year,$total);
				if(($id == '30') || ($id == '26'))
				{
					$CI->db->select('name,code,id')->from('ledgers')->where('group_id',$group_id);
	                		$sub_groups = $CI->db->get();
	                		$sub_group_result = $sub_groups->result();
	                		foreach($sub_group_result as $row3)
	                		{
                        			$name = $row3->name;
                        			$sub_g_id = $row3->id;
						$t_name = "schedule".$count;
                        			$total = $CI->ledger_model->get_ledger_balance($sub_g_id);
                        			$data = $CI->Payment_model->xml_creation($t_name,$sub_g_id,$database,$name,$curr_year,$total);
					}
				}
		}//close if cf
		}
		foreach($ledger_result as $row1)
		{
			$ledger_name = $row1->name;
			$ledger_id =$row1->id;
			if(($type == 'view') && ($database == 'NULL'))
			{
            			echo "<tr class=\"tr-group\">";
		            	echo "<td class=\"td-group\">";                  
       				echo "&nbsp;" .  $ledger_name;
		                echo "</td>";
            			$total1 = $CI->ledger_model->get_ledger_balance1($ledger_id);
		            	echo "<td align=\"right\">" . convert_amount_dc($total1) . "</td>";
	            		$sum1 = $sum1 + $total1;
				$this->ledger_id = $ledger_id;
				/* code for reading previous year data form xml by 'megha'  */

				$acctpath= $this->upload_path1= realpath(BASEPATH.'../uploads/xml');
                		$file_name="schedule".$count.$db.$prev_year.".xml";
                		$tt=$acctpath."/".$file_name;
                		if(file_exists($tt))
                		{
                    			$doc = new DomDocument();
			                $doc->formatOutput = true;
                    			$doc->load($tt);
			                $xpath = new DomXPath($doc);
                    			$schedule1 = "schedule".$count;
                    			$schedule2 = $schedule1."_Name";
                    			$xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
		                    	$xpath->query("/".$schedule1."/".$schedule2."/Amount");
                			$xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
		                    	$schedulenode1 = $xpath->query("/".$schedule1."/".$schedule2."/Group_Name");
                    			$schedulenode2 = $xpath->query("/".$schedule1."/".$schedule2."/Amount");
		                   	$schedulenode3 = $xpath->query("/".$schedule1."/".$schedule2."/Group_ID");
                		    	$schedulelist1 = @$schedulenode1->item($i)->nodeValue;
		                    	$schedulelist2 = @$schedulenode2->item($i)->nodeValue;
                    			$schedulelist3 = @$schedulenode3->item($i)->nodeValue;
                		}
				$prev_sum1 = $prev_sum1 + $schedulelist2;
				$i++;
				if($schedulelist2 == 0)
                			echo "<td align=\"right\">" . convert_amount_dc(0) . "</td>";
                		else
                			echo "<td align=\"right\">" . convert_amount_dc($schedulelist2) . "</td>";
                		echo "</tr>";

			}
			if(($type == 'CF') && ($database != 'NULL'))
			{
                		$t_name = "schedule".$count;
	            		$total1 = $CI->ledger_model->get_ledger_balance($ledger_id);
                		$data = $CI->Payment_model->xml_creation($t_name,$ledger_id,$database,$ledger_name,$curr_year,$total1);
			}
		}//close ledger foreach loop

		$curr_total= $sum + $sum1;
		$prev_total = $prev_sum + $prev_sum1;
		$this->curr_total = $curr_total;
		$this->prev_total = $prev_total;	
    }

	function income_expense_diff()
	{

		$income = new Reportlist();
                $income->init('3');
                $total = $income->total;
		
		$expense = new Reportlist();
                $expense->init('4');
                $total1 = $expense->total;

		$total = 0 - $total;
	
		$diff = $total - $total1;
		
//		echo "diff==$diff";
//		print_r($total);
//		print_r($total1);
		return $diff;
	}
}
?>
