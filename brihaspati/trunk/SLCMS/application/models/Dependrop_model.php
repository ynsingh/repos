<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * @name: Dependrop model
 * @author: Om Prakash (omprakashkgp@gmail.com)
 */

class Dependrop_model extends CI_Model
{

    function __construct() {
        parent::__construct();
        $this->load->database();
    }


    /*This function has been created for display the list of branch on the basis of program*/
    public function get_branchlist($pgid){
	$brlist = $this->commodel->get_listrow('program','prg_name', $pgid);
        $branch_data = $brlist->result();
        if(count($branch_data)>0){
                $pro_select_box = '';
                $pro_select_box.= '<option value="">-------Select Branch --------';
                foreach($branch_data as $branch){
                        $pro_select_box.='<option value='.$branch->prg_id.'>'.$branch->prg_branch;
                }
                echo json_encode($pro_select_box);
        }
     }

 function __destruct() {
        $this->db->close();
    }
}

