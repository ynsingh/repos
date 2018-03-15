<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Addsubgroup
{
	//echo "<br>";
        var $id = 0;
        var $name = "";
        //var $code = "";
        //var $status = 0;
        //var $total = 0;
        //var $optype = "";
        //var $opbalance = 0;
        //var $schedule = "";
        var $children_groups = array();
        //var $children_ledgers = array();
        var $counter = 0;
        //var $dr_total = 0;
        //var $cr_total = 0;
        //var $old_dr_total = 0;
        //var $old_cr_total = 0;

        //public static $temp_max = 0;
        //public static $max_depth = 0;
        //public static $csv_data = array();
        //public static $csv_row = 0;

        function __construct()
        {
                return;
        }

        function init($id)
        {
                $CI =& get_instance();
		$options = array();
                if ($id == 0)
                {
                        $this->id = 0;
                        $this->name = "None";
                        $this->total = 0;

                }

                 else {
                        $CI->db->from('groups')->where('id', $id)->limit(1);
                        $group_q = $CI->db->get();
                        $group = $group_q->row();
                        $this->id = $group->id;
                        $this->name = $group->name;
                        //$this->code = $group->code;
                        //$this->status = $group->status;
                        //$this->total = 0;
                        //$this->schedule = $group->schedule;
                }
               // if($this->status==0)
               // {
                                $this->add_sub_groups();
                        //      $this->add_sub_ledgers();
               // }
		return ;
        }
        function add_sub_groups()
        {
                $CI =& get_instance();
                $CI->db->from('groups')->where('parent_id', $this->id);
                $child_group_q = $CI->db->get();
                $counter = 0;
                foreach ($child_group_q->result() as $row)
                {
                        $row->id;
			//$row->code; 
			//$row->code; 
                        $this->children_groups[$counter] = new Addsubgroup();
                        $this->children_groups[$counter]->init($row->id);
                        //$this->total = float_ops($this->total, $this->children_groups[$counter]->total, '+');
                        $counter++;
                }
		
        }
}
