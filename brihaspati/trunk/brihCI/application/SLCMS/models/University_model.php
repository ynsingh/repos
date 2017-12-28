<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name: University model
 * @author: Nagendra Kumar Singh
 */
class University_model extends CI_Model
{
 
    function __construct() {
        parent::__construct();
        $this->load->database();
    }

 /*
    public function validate_user($data) {
        $this->db->where('username', $data['username']);
        $this->db->where('password', md5($data['password']));
        return $this->db->get('edrpuser')->row();
    }
 */
    public function get_countryname($contcode){
	$this->db->select("name");
	$this->db->where('id',$contcode);
	return $this->db->get('countries')->row();
    }	

    public function get_udetails(){
	$this->db->select ('*');
	return $this->db->get('org_profile')->row();
    }

    public function get_scnamecode(){
   	 $this->db->select("sc_code,sc_name");
   	 return $this->db->get('study_center')->result();
    }   
   
    public function get_unamecode(){
   	 $this->db->select("org_code,org_name");
   	 return $this->db->get('org_profile')->row();
    }

    public function totalnoofseat() {
                $seattmp=0;
                $seatresult = $this->common_model->get_listmore('program','prg_seat');
                foreach($seatresult as $row){
                        $sno=$row->prg_seat;
                        $seattmp+=$sno;
                }
                return $seattmp;
        }

	public function generat_rollnumber($tablename,$prgid,$field,$whfield,$Sid){
		
                if($prgid<=9){
                        $prgid = '0'.$prgid;
                }
                $ydate = date('Y');
                $rollno = '';
                $datas = $ydate.$prgid;
		
                $max = $this->commodel->get_listspficemore($tablename,"MAX($field) AS maxca_rollno","$field LIKE '$datas%'");
		
                foreach($max as $row){
                        $maxrollno = $row->maxca_rollno;
                }
                if((!empty($maxrollno))||$maxrollno>0)
                {
                        $rollno = $maxrollno+1;
                }
                else{
                        $rollno = $ydate.$prgid.'0001';
                }

                $is_rollno = $this->commodel->isduplicate($tablename,$field,$rollno);
                if(!($is_rollno)){
                        $center = array(
                                $field         => $rollno,
                         );

                //update student master table(application_no)
                $updata = $this->commodel->updaterec($tablename,$center,$whfield,$Sid);
		
                $this->logger->write_logmessage("update", "update enrollment number in ".$tablename . " table - ".$Sid.' Enrollment No '.$rollno);
                $this->logger->write_dblogmessage("update", "update enrollment number in ". $tablename ." table - ".$Sid .' Enrollment No '.$rollno);

                }//ducplicate if close
        }


    function __destruct() {
        $this->db->close();
    }
}


