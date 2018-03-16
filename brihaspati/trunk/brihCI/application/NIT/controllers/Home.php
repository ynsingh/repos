<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name Home.php
 * @author Nagendra Kumar Singh
 * modified by Manorama Pal 06june2017  
 */
class Home extends CI_Controller
{
 
    function __construct() {
        parent::__construct();
	$this->load->model("university_model", "universitym");
	$this->load->model("common_model", "commodel");
        if(empty($this->session->userdata('id_user'))) {
            $this->session->set_flashdata('flash_data', 'You don\'t have access!');
            redirect('welcome');
        }
    }
 	
    public function index() {
	/* set user role in session, Role id 1 is for Administrator */
	$data = [ 'id_role' => 1 ];
	$this->session->set_userdata($data);
	// get the values of program and seat
	$selectfield = 'prg_scid,prg_category,prg_name,prg_branch,prg_seat';
	$whorder = "prg_category asc,prg_name asc,prg_branch asc";
	//get_orderlistspficemore($tbname,$selectfield,$whdata,$whorder)
	$this->prgseat=$this->commodel->get_orderlistspficemore('program',$selectfield,'',$whorder);
//	$this->prgseat=$this->commodel->get_listmore('program',$selectfield);
	// get the values of university 
	$this->result = $this->universitym->get_udetails();
	$contcode=$this->result->org_countrycode;
	$this->contryname = $this->universitym->get_countryname($contcode);
	//get values of fees record
	
	// get the programid
	$prglist=$this->commodel->get_distinctrecord('fees_master','fm_programid','');
	// program loop
	$i=0;
	$fdata = array();
	foreach($prglist as $row){
		$prgname=$row->fm_programid;
		$whdata= array('fm_programid'=>$prgname);
		$semlist=$this->commodel->get_distinctrecord('fees_master','fm_semester',$whdata);
		//	 semester loop 
		foreach($semlist as $row1){
			$sem=$row1->fm_semester;
			$whdata1= array('fm_programid'=>$prgname, 'fm_semester'=>$sem);
			$catlist=$this->commodel->get_distinctrecord('fees_master','fm_category',$whdata1);
			//	gender and cat loop
			foreach($catlist as $row2){
				$catid = $row2->fm_category;
				$catname=$this->commodel->get_listspfic1('category','cat_name','cat_id',$catid)->cat_name;
				$gen = "Male";
				// get the fees data as per program, semester, gender and category
			//	if($catid !=1){
					$whdata = "fm_category IN (1, $catid) AND fm_gender IN ('All', '$gen') AND fm_semester = $sem AND fm_programid = '$prgname'";
			//	}
			//	else{
			//		$whdata = "fm_category = 1 AND fm_gender IN array('All', $gen) AND fm_semester = $sem AND fm_programid = $prgname";
			//	}
				$query = $this->commodel->get_sumofvalue('fees_master','fm_amount',$whdata);
				foreach($query as $famt){
					$amt = $famt->fm_amount;
				}
				//put in array
				$fdata['prgname']= $prgname;
				$fdata['prgsem']= $sem;
				$fdata['prgcat']= $catname;
				$fdata['prggen']= $gen;
				$fdata['prgfee']= $amt;
				$data['frecord'][$i] = $fdata;
				$i++;
				//
				$gen = "Female";
				$whdata = "fm_category IN (1, $catid) AND fm_gender IN ('All', '$gen') AND fm_semester = $sem AND fm_programid = '$prgname'";
				$query = $this->commodel->get_sumofvalue('fees_master','fm_amount',$whdata);
				foreach($query as $famt){
					$amt = $famt->fm_amount;
				}
			        //put in array
			        $fdata['prgname']= $prgname;
			        $fdata['prgsem']= $sem;
			        $fdata['prgcat']= $catname;
			        $fdata['prggen']= $gen;
				$fdata['prgfee']= $amt;
			        //
				$data['frecord'][$i] = $fdata;
			        $i++;
			       //
			}
		}
	}
        $this->load->view('home',$data);
    }
 
    public function logout() {
        $data = ['id_user'=> '', 'id'=> '','id_role'=> '','username'=> ''];
	$this->session->unset_userdata($data);
	$this->session->sess_destroy();
        redirect('welcome');
    }
}
