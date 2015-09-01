<?php

class Depreciation_model extends Model {

	function Depreciation_model()
	{
		parent::Model();
	}

	function get_ledger_parent($name)
	{
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



}

?>

     
