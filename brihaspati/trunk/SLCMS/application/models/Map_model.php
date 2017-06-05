<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name: Map model
 * @author: Manorama Pal (palseema30@gmail.com)
 */
class Map_model extends CI_Model
{
 
    function __construct() {
        parent::__construct();
	$this->load->database();
      
    }
    public function get_Campus(){
        $options = array();
        $options[0] = "------Select Campus------";
        $this->db->from('study_center')->order_by('sc_name', 'asc');
        $sc_q = $this->db->get();
        foreach ($sc_q->result() as $row)
        {
            //$nme= $row->sc_name;
            $nname= $row->sc_nickname;
            $new_id ="$row->sc_code"."#"."$nname"."#"."$row->sc_name";
            $options[$new_id] =$row->sc_name." "."( $row->sc_nickname )";
            
        }
        return $options;

    }
    public function get_Programlist(){
        $options = array();
        $options[0] = "------Select Program------";
        $this->db->from('program')->order_by('prg_name', 'asc');
        $prg_q = $this->db->get();
        foreach ($prg_q->result() as $row)
        {
            $prg_ctgry= $row->prg_category;
            $prg_name= $row->prg_name;
            $prg_brh= $row->prg_branch;
            $new_id = "$row->prg_id"."#"."$row->prg_name"."#"."$row->prg_branch"."#"."$row->prg_seat";
            $options[$new_id] =$row->prg_name." "."( $row->prg_branch )";
        }
        return $options;

    }
    public function get_Programseat($prgnameid){
        $this->db->select('prg_name,prg_branch')->from('program')->where('prg_id',$prgnameid);
        $prg_seat = $this->db->get()->result();
        foreach($prg_seat as $row)
        {
            $name = $row->prg_name."(" .$row->prg_branch. ")";
        }
        return $name;
     
    }
    public function get_seat_program_studycenter(){
        $this->db->from('seat_program_studycenter');
        $query = $this->db->get();
        if($query->num_rows()>0){
            return $query->result();
        }

    }
    public function get_studycenername($sccode){
        //$scname = array();
        $this->db->select('sc_name')->from('study_center')->where('sc_code',$sccode);
        $scd = $this->db->get()->result();
        foreach($scd as $row)
        {
            //$new_scname = $row->sc_name."#". $row->sc_code;
            $scname=$row->sc_name;
        }
        return $scname;

    }
     
    public function get_Programsno($prgnameid){
        
        $this->db->from('program')->where('prg_id',$prgnameid);
        $prg_seat = $this->db->get();
        foreach($prg_seat->result() as $row)
        {
            
            $seatno =$row->prg_seat;
           
        }
        return $seatno;
     
    }
    public function isduplicate($tbname,$pgrid,$sccode,$gender,$academicyear){
        $this->db->from($tbname);
        $this->db->where("spsc_prg_id", $pgrid);
        $this->db->where("spsc_sc_code", $sccode);
        $this->db->where("spsc_gender",$gender);
        $this->db->where("spsc_acadyear", $academicyear);
        $query = $this->db->get();
        if ($query->num_rows() > 0) {
        	return true;
    	}else {
        	return false;
    	}
        
         
    }
    
    public function totalnoofseat($prgid,$academicyear) {
        $seattmp=0;
        $this->db->select('spsc_totalseat')->from('seat_program_studycenter')->where('spsc_prg_id',$prgid)->where('spsc_acadyear',$academicyear);
        $query = $this->db->get();
        foreach($query->result() as $row){
            $sno=$row->spsc_totalseat;
            $seattmp+=$sno;
           
        }
        return $seattmp;
        
        
    }
    
    /* get subject record */

    public function getsubject(){
        $subsel = array();
        //$subsel[0] = "Please Select";
        $this->db->from('subject')->order_by('sub_name', 'asc');
        $subject = $this->db->get();
        foreach ($subject->result() as $row)
        {
            
            $subid ="$row->sub_name"."#"."$row->sub_id";
            $subsel[$subid] = $row->sub_name;
        }
        return $subsel;
    }

    /* get program degree */   

    public function getprogram(){
        $prgsel = array();
        //$subsel[0] = "Please Select";
        $this->db->from('program')->order_by('prg_name', 'asc');
        $program = $this->db->get();
        foreach ($program->result() as $row)
        {

            $prgid ="$row->prg_name"."#"."$row->prg_id";
            $prgsel[$prgid] = $row->prg_name;
        }
        return $prgsel;
    }
    
    public function get_subjectname($subjectid)
    {
        $this->db->from('subject');
        $this->db->where("sub_id", $subjectid);
        $subname = $this->db->get();
        foreach ($subname->result() as $row)
        {
            $sub_name = $row->sub_name;
        }
        $sub_name;
        return $sub_name;
    }
    public function get_programname($programid)
    {
        $this->db->from('program');
        $this->db->where("prg_id", $programid);
        $prgname = $this->db->get();
        foreach ($prgname->result() as $row)
        {
            $prg_name = $row->prg_name;
        }
        $prg_name;
        return $prg_name;
    }

    public function ispaper($tbname,$subjectname,$subjecttype,$subjectno,$acadyear,$degree)
    {
        $this->db->distinct();
        $this->db->from($tbname);
        $this->db->where("subp_sub_id", $subjectname);
        $this->db->where("subp_subtype", $subjecttype);
        $this->db->where("subp_paperno",$subjectno);
        $this->db->where("subp_acadyear", $acadyear);
        $this->db->where("subp_degree", $degree);
        $query = $this->db->get();
        if ($query->num_rows() > 0) 
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
}
?>
