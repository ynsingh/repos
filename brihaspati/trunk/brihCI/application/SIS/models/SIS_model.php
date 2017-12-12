
<?php
defined('BASEPATH') OR exit('No direct script access allowed');
 
/**
 * @name: SIS_model
 * @author: Nagendra Kumar Singh (nksinghiitk@gmail.com)
 * @author: Manorama pal (palseema30@gmail.com)
 * @author: Om Prakash (omprakashkgp@gmail.com) check the record is already exist 
 */
class SIS_model extends CI_Model
{
 
    function __construct() {
        parent::__construct();
	$this->db2=$this->load->database('payroll', TRUE);
    }
    //insert the complete record from specific table
    public function insertrec($tbname, $datar){
         $this->db2->trans_start();
         if(! $this->db2->insert($tbname, $datar))
         {
            $this->db2->trans_rollback();
            return false;
         }
         else {
            $this->db2->trans_complete();
            return true;
         }
    }
    //update the complete record from specific table
    public function updaterec($tbname, $datar,$fieldname,$fieldvalue){
         $this->db2->trans_start();
         if(! $this->db2->where($fieldname, $fieldvalue)->update($tbname, $datar))
         {
            $this->db2->trans_rollback();
            return false;
         }
         else {
            $this->db2->trans_complete();
            return true;
         }
    }
    // check the record is already exist
    public function isduplicate($tbname,$fieldname,$fieldvalue) {
        $this->db2->from($tbname);
        $this->db2->where($fieldname, $fieldvalue);
        $query = $this->db2->get();
        if ($query->num_rows() > 0) {
                return true;
        } else {
                return false;
        }
    }
    
    //get the list of one/specific  records with  one specific fields for specific values
    public function get_listspfic1($tbname,$selfield1,$fieldname='',$fieldvalue=''){
	$this->db2->flush_cache();
	$this->db2->select($selfield1);
	$this->db2->from($tbname);
	$this->db2->limit(1);
	if (($fieldname != '') && ($fieldvalue != '')){
            $this->db2->where($fieldname, $fieldvalue);
	}
        return $this->db2->get()->row();
    }

//get the list of all records with  two specific fields for specific values
    public function get_listspfic2($tbname,$selfield1,$selfield2,$fieldname='',$fieldvalue='',$grpby=''){
                $this->db2->flush_cache();
                $this->db2->from($tbname);
                $this->db2->select($selfield1);
                $this->db2->select($selfield2);
                if($grpby != ''){
                        $this->db2->group_by($grpby);
                }
                if (($fieldname != '') && ($fieldvalue !='')){
                        $this->db2->where($fieldname, $fieldvalue);
                }
       // print_r($this->db->get()->result());
        return $this->db2->get()->result();
    }

    //    getting different field from table - $selectfield ('a,b,c');
    public function get_listspficemore($tbname,$selectfield,$data){
	$this->db2->flush_cache();
	$this->db2->from($tbname);
        $this->db2->select($selectfield);
        $this->db2->where($data);
        return $this->db2->get()->result();
    }
	//    getting different field from table - $selectfield ('a,b,c');
    //    $whdata = array('name' => $name, 'title' => $title, 'status' => $status);
    //    $whorder = ("column1 asc,column2 desc");
    public function get_orderlistspficemore($tbname,$selectfield,$whdata,$whorder){
        $this->db2->flush_cache();
        $this->db2->from($tbname);
        $this->db2->select($selectfield);
        if($whdata != ''){
                $this->db2->where($whdata);
        }
        if($whorder != ''){
                $this->db2->order_by($whorder);
        }
        return $this->db2->get()->result();
    }

     public function get_schemeslist($deptid){
        $this->db2->select('map_scheme_department.msd_schmid','scheme_department.sd_name');
       $this->db2->from('map_scheme_department')->where('msd_deptid',$deptid);
        $this->db2->join('scheme_department','scheme_department.sd_id = map_scheme_department.msd_schmid');
        $query=$this->db2->get();
//	print_r($query->result());
        return $query->result();
	
    }
    //get the complete record from specific table
    public function get_list($tbname){
         $this->db2->from($tbname);
         return $this->db2->get()->result();
    }
    
    //get the complete of record for specific value
    public function get_listrow($tbname,$fieldname,$fieldvalue){
         $this->db2->from($tbname);
	 		$this->db2->where($fieldname, $fieldvalue);
         return $this->db2->get();
    }

// check the record is already exist with as many field you want
    public function isduplicatemore($tbname,$data) {
                $this->db2->flush_cache();
                $this->db2->from($tbname);
                $this->db2->where($data);
        $query = $this->db2->get();
        if ($query->num_rows() > 0) {
                return true;
        } else {
                return false;
        }
    }
    
    
    
    /*************************************Start transfer order pdf *****************************************************************************/
    
    public function gentransferordertpdf($empid){
        
        $this->orgname=$this->commodel->get_listspfic1('org_profile','org_name','org_id',1)->org_name;
        $this->orgaddres=$this->commodel->get_listspfic1('org_profile','org_address1','org_id',1)->org_address1;
        $this->orgpincode=$this->commodel->get_listspfic1('org_profile','org_pincode','org_id',1)->org_pincode;
        $this->regname=$this->sismodel->get_listspfic1('user_input_transfer','uit_registrarname','uit_staffname',$empid)->uit_registrarname;
        $this->uitdesig=$this->sismodel->get_listspfic1('user_input_transfer','uit_desig','uit_staffname',$empid)->uit_desig;
        $this->data=$this->sismodel->get_listrow('user_input_transfer','uit_staffname',$empid);
        $spec_data['detail'] = $this->data->row();
        $year=date('Y');
        // move file to directory code for photo
	$desired_dir = 'uploads/SIS/transferorder/'.$year;
        // Create directory if it does not exist
        if(is_dir($desired_dir)==false){
            mkdir("$desired_dir", 0700);
        }
        $emp_pf=$this->sismodel->get_listspfic1('employee_master', 'emp_code', 'emp_id',$empid)->emp_code;
       	//add pdf code to store and view pdf file
	$temp = $this->load->view('staffmgmt/transferordercopy', $spec_data, TRUE);
	$pth='uploads/SIS/transferorder/'.$year.'/'.$emp_pf.'.pdf';
	$this->genpdf($temp,$pth);
    }
    public function genpdf($content,$path){
	$this->load->library('pdf');
	$this->pdf = new DOMPDF();	
     	// pass html to dompdf object
    	$this->pdf->load_html($content);
	$this->pdf->set_paper("A4", "portrait");
        $this->pdf->render();
	//set paper size
        $pdf = $this->pdf->output();
	file_put_contents($path, $pdf); 
    }
    
    /************************************* closer transfer order pdf *****************************************************************************/
   public function searchemp_profile($tbname,$worktype,$keyword)
    {
      
       $this->db2->select('emp_id,emp_code,emp_name,emp_scid,emp_uocid,emp_dept_code,emp_desig_code,emp_email,emp_phone,emp_aadhaar_no')->from('employee_master')->where("emp_name LIKE '$keyword%'")->where("emp_worktype", $worktype);
        return $this->db2->get()->result();
    }
    
    /*************************************updating the staff position table*****************/
    public function updatestaffposition($campus,$uocid,$deptid,$emppost,$worktype,$emptype){
    // public function updatestaffposition($campus,$uocid,$deptid,$schmid,$emppost,$worktype,$emptype){
        /*$datawh=array('sp_campusid' => $campus,'sp_uo' => $uocid, 'sp_dept' => $deptid,
            'sp_schemecode'=> $schmid,'sp_emppost' => $emppost, 'sp_tnt' => $worktype,'sp_type' =>$emptype);*/
        $datawh=array('sp_campusid' => $campus,'sp_uo' => $uocid, 'sp_dept' => $deptid,
            'sp_emppost' => $emppost, 'sp_tnt' => $worktype,'sp_type' =>$emptype);
        $emppost_data = $this->sismodel->get_listspficemore('staff_position','sp_id,sp_type,sp_position,sp_vacant,sp_pospermanent,sp_postemporary,sp_vpermanenet,sp_vtemporary',$datawh);
        if(!empty($emppost_data)){
            $update_data = array();
            $upempdata_flag='';
            foreach($emppost_data as $empdata){
                
                if($empdata->sp_type == 'Permanent'){
                    
                    $position = $empdata->sp_position+1;
                    $vacant   = $empdata->sp_vacant-1;
                    $pospermanent=$empdata->sp_pospermanent+1;
                    $vpermanenet =$empdata->sp_vpermanenet-1;
                    $update_data = array(
                        'sp_position'=>$position,
                        'sp_vacant'=>$vacant,
                        'sp_pospermanent'=>$pospermanent,
                        'sp_vpermanenet'=>$vpermanenet,
                        'sp_org_id'=> '1'
                    );
                    //echo "vacacny=per==".$position.$vacant.$pospermanent.$vpermanenet;
                    $upempdata_flag=$this->sismodel->updaterec('staff_position', $update_data,'sp_id',$empdata->sp_id);
                }
                if($empdata->sp_type == 'Temporary'){
                    
                    $position = $empdata->sp_position+1;
                    $vacant   = $empdata->sp_vacant-1;
                    $postemporary =$empdata->sp_postemporary+1;
                    $vtemporary = $empdata->sp_vtemporary-1;
                    $update_data = array(
                       'sp_position'=>$position,
                       'sp_vacant'=>$vacant,
                       'sp_postemporary'=>$postemporary,
                       'sp_vtemporary'=>$vtemporary,
                       'sp_org_id'=> '1'
                    );
                   // echo "vacacny tempo===".$position.$vacant.$postemporary.$vtemporary;
                    $upempdata_flag=$this->sismodel->updaterec('staff_position', $update_data,'sp_id',$empdata->sp_id);
                }
               
            } //foreach   
            if(!upempdata_flag){
                $this->logger->write_logmessage("error","Error in update staff position ", "Error in staff position record update" );
                $this->logger->write_dblogmessage("error","Error in update staff position", "Error in staff position record update");
            
            }
            else{
                $this->logger->write_logmessage("update","update staff position ", "staff position record updated successfully ");
                $this->logger->write_dblogmessage("update","staff position", "staff position record updated successfully");
            
            }
           
        }  //ifempty  
        
    }//function close
    /***********************************close of staff position*********************************************/   

    function __destruct() {
        $this->db2->close();
    }
}    
