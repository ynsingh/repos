<?php

class Depreciation_model extends CI_Model {

function __construct() {
        parent::__construct();
    }


	function Depreciation_model()
	{
		parent::Model();
	}


	function get_ledger_parent($name)
	{	
		$options = '';
		//$options = array();
		$this->db->select('group_id')->from('ledgers')->where('name', $name);
		$led_details=$this->db->get();
		foreach ($led_details->result() as $row)
                {
			$this->db->select('parent_id, name, code')->from('groups')->where('id', $row->group_id);
                	$group_details=$this->db->get();
			foreach ($group_details->result() as $row1)
                	{
				$options = $row1->code;
			}
                }
		return $options;
	}
	
	function is_asset_in_depreciation_master_table($code)
        {
                $options = '';
		//echo"==>".$code;
                $this->db->select('percentage, life_time')->from('depreciation_master')->where('code', $code);
                $dep_percentage=$this->db->get();
                foreach ($dep_percentage->result() as $row)
                {
                                $options = $row->percentage.'#'.$row->life_time;
                }
                return $options;
        }

	function is_asset_in_depreciation_master_table1($code)
        {
                $options = '';
		$this->db->from('depreciation_master')->where('name'. '  ' . 'LIKE', '%' . $code . '%');
                $dep_percentage = $this->db->get();
                foreach ($dep_percentage->result() as $row)
                {
                	$options = $row->percentage.'#'.$row->life_time;
                }
                return $options;
        }


	function is_asset_in_asset_register($name)
        {
                $options = '';
                $this->db->select('date_of_purchase')->from('asset_register')->where('asset_name', $name);
                $asset_register=$this->db->get();
                foreach ($asset_register->result() as $row)
                {
                                $options = $row->date_of_purchase;
                }
                return $options;
        }
	
	function is_asset_register_exist()
        {
                $options = '';
                $this->db->from('old_asset_register');
                $asset_register=$this->db->get();
		if($asset_register->num_rows()>0){
			$options='1';
		}else{
			$options='0';
		}
                return $options;
        }
	
	 function dep_master_details()
        {
                $options = array();
		$counter = 0;
		
		$this->db->from('depreciation_master')->where('parent_id', 1);
                $depreciation_master=$this->db->get();
                foreach ($depreciation_master->result() as $row)
                {
			$options[$counter]['id'] = $row->id;
                        $options[$counter]['name'] = $row->name;
                        $options[$counter]['code'] = $row->code;
			$options[$counter]['life_time'] = $row->life_time;
                        $options[$counter]['percentage'] = $row->percentage;
                        $counter++;
		}
			
		$this->db->from('depreciation_master')->where('parent_id', 2);
                $depreciation_master1=$this->db->get();
                foreach ($depreciation_master1->result() as $row)
                {
                        $options[$counter]['id'] = $row->id;
                        $options[$counter]['name'] = $row->name;
                        $options[$counter]['code'] = $row->code;
			$options[$counter]['life_time'] = $row->life_time;
                        $options[$counter]['percentage'] = $row->percentage;
                        $counter++;
                }

                return $options;
        }

	 //function get_selected_groups()
        function get_all_bank_cash()
        {
                $options = array();
                $this->db->from('ledgers');
                $this->db->where('type', '1');
                $bank_name = $this->db->get();
		$name = "--Select--";
                $options[$name] = "--Select--";
                foreach ($bank_name->result() as $row)
                {
                        $name = "$row->id"."#"."$row->name";
                        $options[$name] = $row->name;
                }

                return $options;
        }


function get_asset_data($search,$text,$asset_type,$database_type)
{
	if($database_type == "Previous Year Assets"){	
	$field = $search . '      ' . 'LIKE';
	//$CI =& get_instance();
	$val=explode("#",$field);
	$b = trim($text);
	$today_date=date("Y-m-d");
	$fy_end_date=date_mysql_to_php_display($this->config->item('account_fy_end'));
        $d=strtotime($fy_end_date);
        $fy_end_date= date("Y-m-d", $d);
	$asset_type_value = $asset_type;
	//$this->get_deprecvalue($search,$text,$asset_type);

	$this->db->select();
        $this->db->from('old_asset_register');
        $this->db->distinct();
        $this->db->group_by("asset_name");
        $asset_register=$this->db->get();
	$check_asset_register = $this->is_asset_register_exist();
        if($check_asset_register == '1' && $today_date != $fy_end_date){//if2
		if($search == ''){
			$this->db->select();
                        $this->db->from('old_asset_register');
                        $this->db->distinct();
                        $this->db->group_by("asset_name");
                        $asset_register=$this->db->get();
		}elseif($b != NULL && $val[1] != NULL){
			if($search == "IRD_WEF_Date#date_of_purchase"){
				$exp_date=explode("/",$b);
				@$b=$exp_date[2]."-".$exp_date[1]."-".$exp_date[0]." "."00:00:00";
			}
				if($asset_type_value == 'all_asset'){
					$this->db->select();
                                	$this->db->from('old_asset_register')->where($val[1], '%' . $b . '%');
                                	$this->db->distinct();
                                	$this->db->group_by("asset_name");
                                	$asset_register=$this->db->get();
				}
			  if($asset_type_value == 'project_asset'){
				if($search == "IRD_WEF_Date#date_of_purchase"){
					$this->db->select('old_asset_register.asset_name as asset_name, old_asset_register.date_of_purchase as date_of_purchase, old_asset_register.cost as cost, old_asset_register.sanc_type as sanc_type, old_asset_register.id as id, old_asset_register.narration as narration, old_asset_register.asset_status as asset_status');
                                        $this->db->from('old_sponsored_asset_register')->join('old_asset_register', 'old_sponsored_asset_register.asset_id = old_asset_register.id')->where('old_asset_register.'.$val[1], '%' . $b . '%');  
                                        $asset_register=$this->db->get();
				}else{
                                        $this->db->select('old_asset_register.asset_name as asset_name, old_asset_register.date_of_purchase as date_of_purchase, old_asset_register.cost as cost, old_asset_register.sanc_type as sanc_type, old_asset_register.id as id, old_asset_register.narration as narration, old_asset_register.asset_status as asset_status');
                                        $this->db->from('old_sponsored_asset_register')->join('old_asset_register', 'old_sponsored_asset_register.asset_id = old_asset_register.id')->where($val[1], '%' . $b . '%');
                                        $asset_register=$this->db->get();
				}

			}elseif($asset_type_value == 'fund_asset'){
				if($search == "IRD_WEF_Date#date_of_purchase"){
                                        $this->db->select('old_asset_register.asset_name as asset_name, old_asset_register.date_of_purchase as date_of_purchase, old_asset_register.cost as cost, old_asset_register.sanc_type as sanc_type, old_asset_register.id as id, old_asset_register.narration as narration, old_asset_register.asset_status as asset_status');
                                        $this->db->from('old_sponsored_asset_register')->join('old_asset_register', 'old_sponsored_asset_register.asset_id = old_asset_register.id')->where('old_asset_register.'.$val[1], '%' . $b . '%');
                                        $asset_register=$this->db->get();
				}else{
                                        $this->db->select('old_asset_register.asset_name as asset_name, old_asset_register.date_of_purchase as date_of_purchase, old_asset_register.cost as cost, old_asset_register.sanc_type as sanc_type, old_asset_register.id as id, old_asset_register.narration as narration, old_asset_register.asset_status as asset_status');
                                        $this->db->from('old_fund_asset_register')->join('old_asset_register', 'old_fund_asset_register.asset_id = old_asset_register.id')->where($val[1], '%' . $b . '%');
                                        $asset_register=$this->db->get();
				}
                      	}elseif($asset_type_value == 'plan'){
				$this->db->select();
                      		$this->db->from('old_asset_register')->where('sanc_type', 'plan')->where($val[1], '%' . $b . '%');
                      		$this->db->distinct();
                      		$this->db->group_by("asset_name");
                      		$asset_register=$this->db->get();
                      	}elseif($asset_type_value == 'non_plan'){
                                $this->db->select();
                                $this->db->from('old_asset_register')->where('sanc_type', 'non_plan')->where($val[1], '%' . $b . '%');
                                $this->db->distinct();
                                $this->db->group_by("asset_name");
                                $asset_register=$this->db->get();
                        }
	
		}elseif($b != NULL && $val[1] == NULL){
			$this->messages->add('Please Select Any Option From Dropdown.', 'error');
			redirect('report/depreciation');
		}else{
                                if($asset_type_value == 'project_asset'){
                                        $this->db->select('old_asset_register.asset_name as asset_name, old_asset_register.date_of_purchase as date_of_purchase, old_asset_register.cost as cost, old_asset_register.sanc_type as sanc_type, old_asset_register.id as id, old_asset_register.narration as narration, old_asset_register.asset_status as asset_status');
                                        $this->db->from('old_sponsored_asset_register')->join('old_asset_register', 'old_sponsored_asset_register.asset_id = old_asset_register.id');
                                        $asset_register=$this->db->get();
                                }elseif($asset_type_value == 'fund_asset'){
                                        $this->db->select('old_asset_register.asset_name as asset_name, old_asset_register.date_of_purchase as date_of_purchase, old_asset_register.cost as cost, old_asset_register.sanc_type as sanc_type, old_asset_register.id as id, old_asset_register.narration as narration, old_asset_register.asset_status as asset_status');
                                        $this->db->from('old_fund_asset_register')->join('old_asset_register', 'old_fund_asset_register.asset_id = old_asset_register.id');
                                        $asset_register=$this->db->get();
                                }elseif($asset_type_value == 'plan'){
                                        $this->db->select();
                                        $this->db->from('old_asset_register')->where('sanc_type', 'plan');
                                        $this->db->distinct();
                                        $this->db->group_by("asset_name");
                                        $asset_register=$this->db->get();
                                }elseif($asset_type_value == 'non_plan'){
                                        $this->db->select();
                                        $this->db->from('old_asset_register')->where('sanc_type', 'non_plan');
                                        $this->db->distinct();
                                        $this->db->group_by("asset_name");
                                        $asset_register=$this->db->get();
                                }
                }
	}
	}elseif($database_type == "Current Year Assets")
	{
	$this->db->select();
        $this->db->from('new_asset_register');
       	$this->db->distinct();
       	$this->db->group_by("asset_name");
        $asset_register=$this->db->get();
        if($search == ''){
        	$this->db->select();
                $this->db->from('new_asset_register');
                $this->db->distinct();
                $this->db->group_by("asset_name");
                $asset_register=$this->db->get();
                }elseif($b != NULL){
                if($search == "IRD_WEF_Date#date_of_purchase"){
                }       
                if($asset_type_value == 'all_asset'){
                	$this->db->select();
                        $this->db->from('new_asset_register')->where($val[1], '%' . $b . '%');
                        $this->db->distinct();
                        $this->db->group_by("asset_name");
                        $asset_register=$this->db->get();
                        }     
			if($asset_type_value == 'project_asset'){
                        if($search == "IRD_WEF_Date#date_of_purchase"){
                        	$this->db->select('new_asset_register.asset_name as asset_name, new_asset_register.date_of_purchase as date_of_purchase, new_asset_register.cost as cost, new_asset_register.sanc_type as sanc_type, new_asset_register.id as id, new_asset_register.narration as narration, new_asset_register.asset_status as asset_status');
                                                $this->db->from('new_sponsored_asset_register')->join('new_asset_register', 'new_sponsored_asset_register.asset_id = new_asset_register.id')->where('new_asset_register.'.$val[1], '%' . $b . '%');
                                $asset_register=$this->db->get();
                                }else{
                                $this->db->select('new_asset_register.asset_name as asset_name, new_asset_register.date_of_purchase as date_of_purchase, new_asset_register.cost as cost, new_asset_register.sanc_type as sanc_type, new_asset_register.id as id, new_asset_register.narration as narration, new_asset_register.asset_status as asset_status');
                                $this->db->from('new_sponsored_asset_register')->join('new_asset_register', 'new_sponsored_asset_register.asset_id = new_asset_register.id')->where($val[1], '%' . $b . '%');
                                $asset_register=$this->db->get();
                                }
                                }elseif($asset_type_value == 'fund_asset'){
                                if($search == "IRD_WEF_Date#date_of_purchase"){
                                $this->db->select('new_asset_register.asset_name as asset_name, new_asset_register.date_of_purchase as date_of_purchase, new_asset_register.cost as cost, new_asset_register.sanc_type as sanc_type, new_asset_register.id as id, new_asset_register.narration as narration, new_asset_register.asset_status as asset_status');
                                $this->db->from('new_sponsored_asset_register')->join('new_asset_register', 'new_sponsored_asset_register.asset_id = new_asset_register.id')->where('new_asset_register.'.$val[1], '%' . $b . '%');         
                               	$asset_register=$this->db->get();
                              	}else{
                                $this->db->select('new_asset_register.asset_name as asset_name, new_asset_register.date_of_purchase as date_of_purchase, new_asset_register.cost as cost, new_asset_register.sanc_type as sanc_type, new_asset_register.id as id, new_asset_register.narration as narration, new_asset_register.asset_status as asset_status');
                               	$this->db->from('new_fund_asset_register')->join('new_asset_register', 'new_fund_asset_register.asset_id = new_asset_register.id')->where($val[1], '%' . $b . '%');
                                $asset_register=$this->db->get();
                                }
                                }elseif($asset_type_value == 'plan'){
                                        $this->db->select();
                                        $this->db->from('new_asset_register')->where('sanc_type', 'plan')->where($val[1], '%' . $b . '%');
                                        $this->db->distinct();
                                        $this->db->group_by("asset_name");
                                        $asset_register=$this->db->get();
                                }elseif($asset_type_value == 'non_plan'){
                                        $this->db->select();
                                        $this->db->from('new_asset_register')->where('sanc_type', 'non_plan');
                                        $this->db->distinct();
                                        $this->db->group_by("asset_name")->where($val[1], '%' . $b . '%');
                                        $asset_register=$this->db->get();
                                }
                }elseif($b != NULL && $val[1] == NULL){
                        $this->messages->add('Please Select Any Option From Dropdown', 'error');
                        redirect('report/depreciation');
                }else{
                        if($asset_type_value != 'all_asset'){
                                if($asset_type_value == 'project_asset'){
				 $this->db->select('new_asset_register.asset_name as asset_name, new_asset_register.date_of_purchase as date_of_purchase, new_asset_register.cost as cost, new_asset_register.sanc_type as sanc_type, new_asset_register.id as id, new_asset_register.narration as narration, new_asset_register.asset_status as asset_status');
                                        $this->db->from('new_sponsored_asset_register')->join('new_asset_register', 'new_sponsored_asset_register.asset_id = new_asset_register.id');
                                        $asset_register=$this->db->get();       
                                }elseif($asset_type_value == 'fund_asset'){
                                        $this->db->select('new_asset_register.asset_name as asset_name, new_asset_register.date_of_purchase as date_of_purchase, new_asset_register.cost as cost, new_asset_register.sanc_type as sanc_type, new_asset_register.id as id, new_asset_register.narration as narration, new_asset_register.asset_status as asset_status');
                                        $this->db->from('new_fund_asset_register')->join('new_asset_register', 'new_fund_asset_register.asset_id = new_asset_register.id');
                                        $asset_register=$this->db->get();
                                }elseif($asset_type_value == 'plan'){
                                        $this->db->select();
                                        $this->db->from('new_asset_register')->where('sanc_type', 'plan');
                                        $this->db->distinct();
                                        $this->db->group_by("asset_name");
                                        $asset_register=$this->db->get();
                                }elseif($asset_type_value == 'non_plan'){
                                        $this->db->select();
                                        $this->db->from('new_asset_register')->where('sanc_type', 'non_plan');
                                        $this->db->distinct();
                                        $this->db->group_by("asset_name");
                                        $asset_register=$this->db->get();
                                }       
                        }
                }

	}elseif($database_type == "PICO"){
	if($search == '' || $asset_type_value != NULL && $b == NULL){
     	//load database pico
        $logndb = $this->load->database('pico', TRUE);
     	$this->logndb =$logndb;
        $this->logndb->select('a.ERPMIM_ID, a.ERPMIM_Depreciation_Percentage, a.ERPMIM_Item_Brief_Desc, b.IRD_Rate, b.IR_Item_ID, b.IRD_WEF_Date');
        $this->logndb->from('erpm_item_master a, erpm_item_rate b')->where('a.ERPMIM_ID  = b.IR_Item_ID ');
       	$this->logndb->group_by("ERPMIM_Item_Brief_Desc");
        $asset_register = $this->logndb->get();
	}else{
	$val=explode("#",$field);
       	$b = trim($text);
        //load database pico
       	$logndb = $this->load->database('pico', TRUE);
      	$this->logndb = $logndb;
        if($search != "total_cost" && $search != "dep_amount" && $search != "curr_value") {
     	if($search == 'total_cost#cost' || $search == 'IRD_WEF_Date#date_of_purchase'){
       	if($search == 'total_cost#cost'){
       	$val[0]='IRD_Rate';
        }else{
        $val = explode('#',$field);
        $exp_date=explode("/",$b);
       	@$b=$exp_date[2]."-".$exp_date[1]."-".$exp_date[0];
        }
     	$this->logndb->select('a.ERPMIM_ID, a.ERPMIM_Depreciation_Percentage, a.ERPMIM_Item_Brief_Desc, b.IRD_Rate, b.IR_Item_ID, b.IRD_WEF_Date');
   	$this->logndb->from('erpm_item_master a, erpm_item_rate b')->where('a.ERPMIM_ID  = b.IR_Item_ID ')->where('b.'.$val[0] . ' LIKE','%' . $b . '%');
       	$asset_register = $this->logndb->get();
        }else{
      	$val = explode('#',$field);
        $this->logndb->select('a.ERPMIM_ID, a.ERPMIM_Depreciation_Percentage, a.ERPMIM_Item_Brief_Desc, b.IRD_Rate, b.IR_Item_ID, b.IRD_WEF_Date');
       	$this->logndb->from('erpm_item_master a, erpm_item_rate b')->where('a.ERPMIM_ID  = b.IR_Item_ID ')->where('a.'.$val[0] . ' LIKE','%' . $b . '%');
       	$asset_register = $this->logndb->get();
        }
      	}else{
        $this->logndb->select('a.ERPMIM_ID, a.ERPMIM_Depreciation_Percentage, a.ERPMIM_Item_Brief_Desc, b.IRD_Rate, b.IR_Item_ID, b.IRD_WEF_Date');
        $this->logndb->from('erpm_item_master a, erpm_item_rate b')->where('a.ERPMIM_ID  = b.IR_Item_ID ');
     	$this->logndb->group_by("ERPMIM_Item_Brief_Desc");
       	$asset_register = $this->logndb->get();
        }
	}//else
	$this->logndb->close();
	}
	return $asset_register->result(); 
}
	
/*	function get_deprecvalue($search,$text,$asset_type,$database_type)
	{
		$fund_name='';
		$fy_start_date = date_mysql_to_php($this->config->item('account_fy_start'));
                $fy_st_date=explode("/",$fy_start_date);
                $fy_year=$fy_st_date[2].$fy_st_date[2]+1;
                $next_year=$fy_st_date[2]+1;

		$asset_register = $this->get_asset_data($search,$text,$asset_type,$database_type);
		foreach ($asset_register as $row)
		{
               	$led_code = $this->get_ledger_parent($row->asset_name);
                $master_group_code = $this->is_asset_in_depreciation_master_table($led_code);
                if($master_group_code == NULL){
                $this->db->select('name');
                $this->db->from('groups')->where('code =', $led_code);
                $group_result = $this->db->get();	
                foreach ($group_result->result() as $row1){
              	$group_name=$row1->name;
                }
               	$master_group_code = $this->is_asset_in_depreciation_master_table1($group_name);
                }
               	if($led_code != '20010101')
		{
               	$exp_dep_percentage=explode("#",$master_group_code);
                $date = date_mysql_to_php($row->date_of_purchase);
               	$today_date1=date("Y/m/d");
                $date1=date_create($today_date1);
                $date2=date_create($row->date_of_purchase);
                $diff=date_diff($date1,$date2);
              //  $no_of_days=$diff->format("%a days");
                //echo"No.of.days==>".$no_of_days;
                	//how many years till now
                        $years=$no_of_days/365;
                      	$check_year=is_int($years);//check year is in integer
                        $get_asset_used_years=explode(".", $years);
                        $days=$get_asset_used_years[0]*365;
                        $tot_day=$no_of_days-$days;

			if($type == "Previous Year")
			{
			for($i=0; $i<=$get_asset_used_years[0]; $i++){
        		if($i == 0){
        		$depepreciation_on_day=$row->cost*($tot_day/365)*($exp_dep_percentage[0]/100);
        		$net_amount= $depepreciation_on_day;
        		}elseif($i == 1 ){
        		$val=($row->cost*$exp_dep_percentage[0])/100;
        		$amount=$row->cost-$val;
        		$depepreciation_on_day=$row->cost*($tot_day/365)*($exp_dep_percentage[0]/100);
        		$net_amount= $depepreciation_on_day+$amount;
        		}else{
        		$val=($amount*$exp_dep_percentage[0])/100;
        		$amount=$amount-$val;
        		$depepreciation_on_day=$row->cost*($tot_day/365)*($exp_dep_percentage[0]/100);
        		$net_amount= $depepreciation_on_day+$amount;
        		}
        		}//for
			}//iftype
			elseif($type == "Current Year")
			{
			$value= $row->cost * $exp_dep_percentage[0]/(100*365); //how to calculate total depreciation value
			// calculate the depreciation value
                       	$dep_value_till_now=$no_of_days*$value;
			$curr_value=$row->cost-$dep_value_till_now; 
			}//if 
		}//foreach
	return;
	}//f */


}
?>

     
